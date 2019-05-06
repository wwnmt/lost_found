package com.cx.lost_found.service.model;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class MessageBoardModel {

    private Integer id;

    @NotBlank(message = "学号不能为空")
    private String studentid;
    @NotBlank(message = "留言日期不能为空")
    private Date uptime;
    @NotBlank(message = "留言内容不能为空")
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageBoardModel{" +
                "id=" + id +
                ", studentid='" + studentid + '\'' +
                ", uptime=" + uptime +
                ", message='" + message + '\'' +
                '}';
    }
}
