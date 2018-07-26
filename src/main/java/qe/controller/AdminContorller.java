package qe.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qe.entity.User;
import qe.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminContorller {

    @Autowired
    private UserService userService;

    private static Log log = LogFactory.getLog(AdminContorller.class);

    @RequestMapping("/search_user")
    public String searchUser(@RequestParam(name = "username", defaultValue = "", required = false) String username, Model model){

        User user = userService.getOne(username);
        if(user!=null){
            model.addAttribute("user",user);
        }
        if(!model.containsAttribute("user")){
            User user0 = new User();
            model.addAttribute("user",user0);
        }
        return "users_manage";
    }

    @PostMapping("/change_username")
    public String changeUsername (
            @RequestParam(name="old_username") String oldUsername,
            @RequestParam(name = "new_username") String newUsername,
            Model model, HttpServletResponse response) throws IOException{

        boolean result = userService.changeUserName(oldUsername,newUsername);
        if(result){
            log.info("old usernamer "+oldUsername+" change success");
            response.setContentType("text/html;charset=utf8");
            response.getWriter().print("<script language=\"javascript\">alert('更改成功');window.location.href='/admin/search_user'</script>");
        }else {
            log.info("old username: "+oldUsername+" change failed");
        }
        User user = new User();
        model.addAttribute("user",user);
        return "users_manage";
    }

}
