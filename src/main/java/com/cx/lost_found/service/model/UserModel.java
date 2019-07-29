package com.cx.lost_found.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;

public class UserModel {

    @NotBlank(message = "学号不能为空")
    private String studentid;
    @NotBlank(message = "手机号不能为空")
    private String telephone;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotNull(message = "真实姓名不能不填写")
    private String realname;
    @NotBlank(message = "邮箱不能为空")
    private String email;

    private Integer isadmin;

    public String getStudentId() {
        return studentid;
    }

    public void setStudentId(String studentid) {
        this.studentid = studentid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserModel userModel = (UserModel) o;
        return Objects.equals(studentid, userModel.studentid) &&
                Objects.equals(telephone, userModel.telephone) &&
                Objects.equals(password, userModel.password) &&
                Objects.equals(realname, userModel.realname) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(isadmin, userModel.isadmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentid, telephone, password, realname, email, isadmin);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", studentId='" + studentid + '\'' +
                ", isadmin=" + isadmin +
                '}';
    }
}
