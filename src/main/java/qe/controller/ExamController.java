package qe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qe.entity.CQuestion;
import qe.entity.ExamPaper;
import qe.entity.Question;
import qe.entity.TFQuestion;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/exam")
@Controller
public class ExamController {

    //还没有实现，只是mock
    @GetMapping("/get_paper")
    @ResponseBody
    public ExamPaper getExamPaper(){

        List<Question> questions= new ArrayList<>();
        for(int i = 0; i < 10; i++){
            questions.add(new TFQuestion("题目"+i,1));
        }
        for(int j = 0; j < 20; j++){
            List<String> choices = new ArrayList<>();
            choices.add("选项1");
            choices.add("选项2");
            choices.add("选项3");
            choices.add("选项4");
            questions.add(new CQuestion("题目"+j,choices,1));
        }

        return new ExamPaper(1,questions);
    }
}
