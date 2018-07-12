package qe.service;

import qe.entity.User;

public interface UserService {

    boolean checkForLogin(User user);

    boolean checkForAdminLogin(User user);

    boolean changeUserName(String oldUsername, String newUsername);

    boolean changeUserPassword(String username,String newPassword);

    User getOne(String username);
}
