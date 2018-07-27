package qe.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qe.entity.CQuestion;
import qe.entity.ExamPaper;
import qe.entity.Question;
import qe.entity.TFQuestion;
import qe.service.ExamService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/exam")
@Controller
public class ExamController {

    private final ExamService service;
    private static Log log = LogFactory.getLog(ExamController.class);

    @Autowired
    public ExamController(ExamService service) {
        this.service = service;
    }

    @GetMapping("/get_paper")
    @ResponseBody
    public ExamPaper getExamPaper(HttpSession session) throws Exception{

        ExamPaper paper = service.getRandomPaper();
        session.setAttribute("paper_id",paper.getPaperID());
        log.info("get one paper, id: "+paper.getPaperID());
        return paper;
    }

    /**
     * 这个是已经有成绩的同学查询成绩用的
     */
    @GetMapping("/check_grade")
    public String checkGrade(Model model){
        Integer grade = 100;
        model.addAttribute("grade",grade);
        return "grade_page";
    }

    //todo: 对答案的程序
    /**
     * 这个是考试完成的同学把成绩发过来对答案用的
     */
    @PostMapping("/getgrade")
    public String getGrade(){
        return "redirect:check_grade";
    }

}
