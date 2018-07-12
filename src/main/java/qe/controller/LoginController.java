package qe.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qe.entity.User;
import qe.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    private static Log log = LogFactory.getLog(LoginController.class);

    @PostMapping("/login")
    String login(@RequestParam(name = "username") String username, @RequestParam(name="password") String password, Model model,HttpSession session){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if(userService.checkForLogin(user)) {
            model.addAttribute("username", username);
            session.setAttribute("username",username);
            session.setAttribute("type","common");

            log.info("user "+username+" login success");
            log.info("session lives: "+session.getMaxInactiveInterval());

            return "main_page";
        }else {
            log.info("user "+username+" login failed");
            return "redirect:index.html";
        }
    }

    @PostMapping("/logout")
    String logout(HttpSession session){

        log.info("user "+session.getAttribute("username")+" logout success");
        session.invalidate();
        return "redirect:index.html";
    }

    @PostMapping("/admin_login")
    String adminLogin(@RequestParam(name = "username") String username, String password, Model model,HttpSession session){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if(userService.checkForAdminLogin(user)) {
            model.addAttribute("username", username);
            session.setAttribute("username",username);
            session.setAttribute("type","admin");

            log.info("admin "+username+" login success");
            log.info("session lives: "+session.getMaxInactiveInterval());

            return "admin_main_page";
            //
        }else {
            log.info("user "+username+" login failed");
            return "redirect:admin_login";
        }
    }
}
