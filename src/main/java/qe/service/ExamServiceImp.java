package qe.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import qe.entity.*;
import qe.mapper.QuestionMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExamServiceImp implements ExamService {

    private static Log log = LogFactory.getLog(ExamService.class);
    private static AtomicInteger paperIDCounter = new AtomicInteger(0);
    @Autowired
    private QuestionMapper mapper;

    @Autowired
    private StringRedisTemplate redis;

    @Autowired
    private ObjectMapper objectMapper;

    private final static int REQURED_C = 20;
    private final static int REQURED_TF = 10;
    private static long exam_timesec = 3600; //默认一个小时考试时间


    //todo: 记录答案
    @Override
    public ExamPaper getRandomPaper() throws Exception{
        List<Question> questions = new ArrayList<>();
        List<Integer> answers = new ArrayList<>();

        int cAmount = getAmount(1);
        int tfAmount = getAmount(2);

        HashSet<Integer> cSet = getRandomSet(cAmount,REQURED_C);
        for(Integer i:cSet){
            Question question = mapper.getOneCQ(i);
            questions.add(question);
            answers.add(question.getAnswer());
        }

        HashSet<Integer> tfSet = getRandomSet(tfAmount,REQURED_TF);
        for(Integer i:tfSet){
            Question question = mapper.getOneTFQ(i);
            questions.add(question);
            answers.add(question.getAnswer());
        }


        int code;
        int paperID = paperIDCounter.addAndGet(1);
        if(questions.size() == (REQURED_C+REQURED_TF))
        {
            code = 1;
            saveAnswersToRedis(new PaperAnswer(answers,paperID));
            return new ExamPaper(code,questions,paperID);
        }
        else{
            code = 0;
            return new ExamPaper(code,null,paperID);
        }


    }

    @Override
    public int getFinalScore() {
        return 0;
    }

    public CQuestion getOneCQ(int id){
        return mapper.getOneCQ(id);
    };

    public TFQuestion getOneTFQ(int id){
        return mapper.getOneTFQ(id);
    }

    /**
     *
     * @param which:1代表选择题，2代表判断题
     * @return 数量
     */
    public int getAmount(int which){
        if(which == 1){
            return mapper.countCQ();
        }else {
            return mapper.countTFQ();
        }
    }

    private HashSet<Integer> getRandomSet(int range,int amount) throws Exception{
        if(range<amount){
            throw new Exception("Exception in generate random set: cannot generate caused by invalid amount");
        }
        Random rand = new Random();
        HashSet<Integer> set = new HashSet<>();
        while (set.size()<amount){
            set.add(rand.nextInt(range-1)+1);
        }
        return set;
    }

    private boolean saveAnswersToRedis(PaperAnswer paperAnswer){
        try{
            String json = objectMapper.writeValueAsString(paperAnswer);
            String key = "answer::"+paperAnswer.getPaperID();

            redis.opsForValue().set(key,json,exam_timesec, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
