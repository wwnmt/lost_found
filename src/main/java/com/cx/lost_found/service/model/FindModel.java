package com.cx.lost_found.service.model;

import java.util.Date;
import java.util.StringJoiner;

public class FindModel {

    //招领标语的id
    private Integer id;
    //标语发布人
    private String userTelephone;
    //标语状态 true|已被领取 false|未被领取
    private boolean status;
    //招领标题
    private String title;
    //标语描述
    private String description;
    //标语发布时间
    private Date upTime;

    //招领物品类型
    private String type;
    //招领物品照片
    private String picture;
    //招领物品发现地点
    private String area;
    //招领物品发现时间
    private Date findTime;

    //是否被管理员审核
    private boolean adminJudge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getsetDescription() {
        return description;
    }

    public void setDescrption(String description) {
        this.description = description;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getFindTime() {
        return findTime;
    }

    public void setFindTime(Date findTime) {
        this.findTime = findTime;
    }

    public boolean isAdminJudge() {
        return adminJudge;
    }

    public void setAdminJudge(boolean adminJudge) {
        this.adminJudge = adminJudge;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FindModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userTelephone='" + userTelephone + "'")
                .add("status=" + status)
                .add("title='" + title + "'")
                .add("describe='" + description + "'")
                .add("upTime=" + upTime)
                .add("type='" + type + "'")
                .add("picture='" + picture + "'")
                .add("area='" + area + "'")
                .add("findTime=" + findTime)
                .add("adminJudge=" + adminJudge)
                .toString();
    }
}
