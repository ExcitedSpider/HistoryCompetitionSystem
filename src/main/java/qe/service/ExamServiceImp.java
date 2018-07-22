package qe.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import qe.entity.CQuestion;
import qe.entity.ExamPaper;
import qe.entity.Question;
import qe.entity.TFQuestion;
import qe.mapper.QuestionMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExamServiceImp implements ExamService {

    private static Log log = LogFactory.getLog(ExamService.class);

    @Autowired
    private QuestionMapper mapper;

    private final static int REQURED_C = 20;
    private final static int REQURED_TF = 10;

    //todo: 禁止同一号码的出现
    @Override
    public ExamPaper getRandomPaper() {
        List<Question> questions = new ArrayList<>();
        Random rand = new Random();
        int CAmount = getAmount(1);
        int TFAmount = getAmount(2);
        for(int i = 0;i < REQURED_TF;i++)
            questions.add(getOneCQ(rand.nextInt(CAmount)));
        for(int j = 0;j <REQURED_C;j++)
            questions.add(getOneTFQ(rand.nextInt(TFAmount)));

        int code;
        if(questions.size() == (REQURED_C+REQURED_TF))
            code = 1;
        else
            code = 0;

        return new ExamPaper(code,questions);
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
}
