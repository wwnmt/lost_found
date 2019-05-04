package com.cx.lost_found.controller;

import com.cx.lost_found.error.EmErr;
import com.cx.lost_found.error.UserException;
import com.cx.lost_found.response.CommonReturnType;
import com.cx.lost_found.service.impl.MessageServiceImpl;
import com.cx.lost_found.service.model.MessageModel;
import com.cx.lost_found.service.model.UserModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("message")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {CONTENT_FORM})
    @ResponseBody
    public CommonReturnType createMessage(@RequestParam(name = "title")String title,
                                          @RequestParam(name = "description")String description,
                                          @RequestParam(name = "messageType")String messageType,
                                          @RequestParam(name = "type")String type,
                                          @RequestParam(name = "area")String area,
                                          @RequestParam(name = "time")String findtime,
                                          @RequestParam(name = "picture")String picture,
                                          @RequestParam(name = "contactName")String contactName,
                                          @RequestParam(name = "contactPhone")String contactPhone
    ) throws UserException, ParseException {
        //从session中获取发布信息用户数据
        UserModel user = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        if(user == null){
            throw new UserException(EmErr.NOT_LOGIN);
        }
        String studentId = user.getStudentId();

        MessageModel messageModel = new MessageModel();
        messageModel.setStudentid(studentId);
        messageModel.setTitle(title);
        messageModel.setDescription(description);

        if (messageType.equals("寻物启事")){
            messageModel.setMessageType(1);
        }else {
            messageModel.setMessageType(2);
        }

        Date currentTime = new Date();
        messageModel.setUpTime(currentTime);

        messageModel.setType(type);
        messageModel.setArea(area);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messageModel.setFindTime(sdf.parse(findtime));
        messageModel.setPicture(picture);
        messageModel.setContactName(contactName);
        messageModel.setContactPhone(contactPhone);
        messageModel.setStatus(0);
        messageModel.setAdminJudge(0);

        MessageModel messageModelFromReturn = messageService.createMessage(messageModel);

        return CommonReturnType.create(messageModelFromReturn);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getMessage(@RequestParam(name = "id") Integer id
    ){
        MessageModel messageModel = messageService.getMsgById(id);

        return CommonReturnType.create(messageModel);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType listMessages(){
        List<MessageModel> messageModelList = messageService.listMsg();

        return CommonReturnType.create(messageModelList);
    }
}