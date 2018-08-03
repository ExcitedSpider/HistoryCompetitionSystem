package qe.service;

import qe.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    boolean checkForLogin(User user);

    boolean checkForAdminLogin(User user);

    boolean changeUserName(String oldUsername, String newUsername);

    boolean changeUserPassword(String username,String newPassword);

    User getOne(String username);

    boolean saveGrade(String username,int grade);

    boolean hasExamed(String username);

    int getGrade(String username);

    List<User> getAllUser();
}
