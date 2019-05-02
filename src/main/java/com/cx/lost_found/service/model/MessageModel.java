package com.cx.lost_found.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

public class MessageModel {

    //招领标语的id
    private Integer id;
    //标语发布人
    @NotNull(message = "手机号不能不填写")
    private String userTelephone;
    //标语状态 true|已被领取 false|未被领取
    private Integer status;
    //招领标题
    @NotNull(message = "标题不能为空")
    private String title;
    //标语描述
    @NotNull(message = "描述信息不能为空")
    private String description;
    //标语类型
    @NotNull(message = "信息类型不能为空")
    private Integer messageType;
    //标语发布时间
    private Date upTime;
    //招领物品类型
    @NotBlank(message = "物品类型不能不填写")
    private String type;
    //招领物品照片
    @NotBlank(message = "照片不能为空")
    private String picture;
    //招领物品发现地点
    @NotBlank(message = "物品发现或遗失地点不能不填写")
    private String area;
    //招领物品发现时间
    @NotNull(message = "物品发现或遗失时间不能不填写")
    private Date findTime;
    @NotBlank(message = "联系人姓名不能不填写")
    private String contactName;
    @NotBlank(message = "联系人电话不能不填写")
    private String contactPhone;
    //是否被管理员审核
    private Integer adminJudge;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public Integer getAdminJudge() {
        return adminJudge;
    }

    public void setAdminJudge(Integer adminJudge) {
        this.adminJudge = adminJudge;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MessageModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userTelephone='" + userTelephone + "'")
                .add("status=" + status)
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .add("messageType=" + messageType)
                .add("upTime=" + upTime)
                .add("type='" + type + "'")
                .add("picture='" + picture + "'")
                .add("area='" + area + "'")
                .add("findTime=" + findTime)
                .add("contactName='" + contactName + "'")
                .add("contactPhone='" + contactPhone + "'")
                .add("adminJudge=" + adminJudge)
                .toString();
    }
}
