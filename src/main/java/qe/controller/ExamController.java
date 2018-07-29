package qe.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qe.entity.*;
import qe.mapper.UserMapper;
import qe.service.ExamService;
import qe.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/exam")
@Controller
public class ExamController {

    private final ExamService service;
    private final UserService userService;
    private static Log log = LogFactory.getLog(ExamController.class);

    @Autowired
    public ExamController(ExamService service,UserService userService) {
        this.service = service;
        this.userService = userService;
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

    /**
     * 这个是考试完成的同学把成绩发过来对答案用的
     */
    @PostMapping("/getgrade")
    public String getGrade(@RequestBody PaperAnswer answer,HttpSession session){
        int grade = service.getFinalScore(answer);
        String username = (String)session.getAttribute("username");
        userService.saveGrade(username,grade);
        return "redirect:check_grade";
    }

}
