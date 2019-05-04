package com.cx.lost_found.service.model;

import java.util.Date;
import java.util.StringJoiner;

public class CommentModel {

    private Integer id;

    private String studentid;

    private Integer msgid;

    private String comment;

    private Date commenttime;

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

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentTime() {
        return commenttime;
    }

    public void setCommentTime(Date commenttime) {
        this.commenttime = commenttime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CommentModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("studentid='" + studentid + "'")
                .add("msgid=" + msgid)
                .add("comment='" + comment + "'")
                .add("commentTime=" + commenttime)
                .toString();
    }
}
