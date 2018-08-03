package qe.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import qe.entity.Admin;
import qe.entity.User;
import qe.mapper.UserMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    private static Log log = LogFactory.getLog(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean checkForLogin(User user) {
        User tendUser = userMapper.getOne(user.getUsername());
        if (tendUser!=null){
            String md5Password = DigestUtils.md5DigestAsHex(tendUser.getPassword().getBytes());
            if(md5Password.equals(user.getPassword())){
                log.info("user "+user.getUsername()+" password correct");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkForAdminLogin(User user) {
        Admin tendUser = userMapper.getAdmin(user.getUsername());
        if (tendUser!=null){
            String md5Password = DigestUtils.md5DigestAsHex(tendUser.getPassword().getBytes());
            if(md5Password.equals(user.getPassword())){
                log.info("admin "+user.getUsername()+" password correct");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changeUserName(String oldUsername, String newUsername) {
        log.info("change username, old:"+oldUsername+" new:"+newUsername);
        int result = userMapper.changeUsername(oldUsername,newUsername);
        return result != 0;
    }

    @Override
    public boolean changeUserPassword(String username, String newPassword) {
        int result = userMapper.changeUserPassword(username,newPassword);
        return result !=0 ;
    }

    @Override
    public User getOne(String username) {
        return userMapper.getOne(username);
    }

    @Override
    public boolean saveGrade(String username, int grade) {
        int result = userMapper.updateGrade(username,grade);
        return result!=0;
    }

    @Override
    public boolean hasExamed(String username) {
        return userMapper.getGradeByUsername(username)!=-1;
    }

    @Override
    public int getGrade(String username) {
        return userMapper.getGradeByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUserInfo();
    }
}
