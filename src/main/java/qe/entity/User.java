package qe.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.List;

@ExcelTarget("user")
public class User implements Serializable {

    @Excel(name = "学号")
    private String username;
    @Excel(name = "一卡通号", orderNum = "1")
    private String password;
    @Excel(name = "成绩", orderNum = "2")
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
