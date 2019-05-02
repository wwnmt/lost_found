package com.cx.lost_found.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class UserModel {

    @NotBlank(message = "手机号不能为空")
    private String telephone;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotNull(message = "真实姓名不能不填写")
    private String realname;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    private Boolean isadmin;

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

    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserModel.class.getSimpleName() + "[", "]")
                .add("password='" + password + "'")
                .add("realname='" + realname + "'")
                .add("telephone='" + telephone + "'")
                .add("email='" + email + "'")
                .add("isadmin=" + isadmin)
                .toString();
    }
}