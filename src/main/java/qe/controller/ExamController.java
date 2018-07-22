package qe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qe.entity.CQuestion;
import qe.entity.ExamPaper;
import qe.entity.Question;
import qe.entity.TFQuestion;
import qe.service.ExamService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/exam")
@Controller
public class ExamController {

    private final ExamService service;

    @Autowired
    public ExamController(ExamService service) {
        this.service = service;
    }

    @GetMapping("/get_paper")
    @ResponseBody
    public ExamPaper getExamPaper(){
        return service.getRandomPaper();
    }
}
