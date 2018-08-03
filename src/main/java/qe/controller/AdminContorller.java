package qe.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.net.URLEncoder;
import java.util.List;

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
        return "users_manage_template";
    }

    @PostMapping("/get_grades_excel")
    public void getGradesExcel(HttpServletResponse response) throws Exception{
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("grades","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        List<User> list = userService.getAllUser();//获得用户
        log.info(list.get(0));
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), User.class, list);
        workbook.write(response.getOutputStream());
    }

}
