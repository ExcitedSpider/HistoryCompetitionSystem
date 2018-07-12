package qe.mapper;

import org.apache.ibatis.annotations.*;
import qe.entity.Admin;
import qe.entity.User;

public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "username", column = "id")
    })
    User getOne(String username);

    @Select("SELECT * FROM admin WHERE id = #{id}")
    Admin getAdmin(String username);

    @Update("UPDATE users SET id = #{newUsername} WHERE id = #{oldUsername}")
    int changeUsername(String oldUsername, String newUsername);

    @Update("UPDATE users SET password = #{newPassword} WHERE id = #{username}")
    int changeUserPassword(String username,String newPassword);
}
