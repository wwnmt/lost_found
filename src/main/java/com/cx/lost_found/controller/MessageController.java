package com.cx.lost_found.controller;

import com.cx.lost_found.error.UserException;
import com.cx.lost_found.response.CommonReturnType;
import com.cx.lost_found.service.impl.MessageServiceImpl;
import com.cx.lost_found.service.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller("message")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageServiceImpl messageService;


    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {CONTENT_FORM})
    @ResponseBody
    public CommonReturnType createMessage(@RequestParam(name = "信息标题")String title,
                                       @RequestParam(name = "信息描述")String description,
                                       @RequestParam(name = "信息类型")String messageType,
                                       @RequestParam(name = "信息发布时间") Date uptime,
                                       @RequestParam(name = "物品类型")String type,
                                       @RequestParam(name = "物品发现地点")String area,
                                       @RequestParam(name = "物品发现时间")Date findtime,
                                       @RequestParam(name = "物品照片")String picture,
                                          @RequestParam(name = "联系人姓名")String contactName,
                                          @RequestParam(name = "联系人电话")String contactPhone
                                       ) throws UserException {
        MessageModel messageModel = new MessageModel();
        messageModel.setTitle(title);
        messageModel.setDescription(description);
        if (messageType.equals("寻物启事")){
            messageModel.setMessageType(1);
        }else {
            messageModel.setMessageType(2);
        }
        messageModel.setUpTime(uptime);
        messageModel.setType(type);
        messageModel.setArea(area);
        messageModel.setFindTime(findtime);
        messageModel.setUpTime(uptime);
        messageModel.setPicture(picture);
        messageModel.setContactName(contactName);
        messageModel.setContactPhone(contactPhone);
        messageModel.setStatus(0);
        messageModel.setAdminJudge(0);

        MessageModel messageModelFromReturn = messageService.createMessage(messageModel);

        return CommonReturnType.create(messageModelFromReturn);
    }
}
