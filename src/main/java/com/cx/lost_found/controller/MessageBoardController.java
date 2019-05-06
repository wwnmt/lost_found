package com.cx.lost_found.controller;


import com.cx.lost_found.error.EmErr;
import com.cx.lost_found.error.UserException;
import com.cx.lost_found.response.CommonReturnType;
import com.cx.lost_found.service.MessageBoardService;
import com.cx.lost_found.service.model.MessageBoardModel;
import com.cx.lost_found.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller("messageboard")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
@RequestMapping("/messageboard")
public class MessageBoardController extends BaseController {

    @Autowired
    private MessageBoardService messageBoardService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {CONTENT_FORM})
    @ResponseBody
    public CommonReturnType createMessage(@RequestParam(name = "studentid")String studentid,
                                          @RequestParam(name = "message")String message
    ) throws UserException{
        //从session中获取发布信息用户数据
        UserModel user = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        if(user == null){
            throw new UserException(EmErr.NOT_LOGIN);
        }
        String studentId = user.getStudentId();

        MessageBoardModel messageBoardModel = new MessageBoardModel();
        messageBoardModel.setStudentid(studentId);
        messageBoardModel.setMessage(message);

        Date currentTime = new Date();
        messageBoardModel.setUptime(currentTime);


        MessageBoardModel messageBoardModelFromReturn = messageBoardService.createMessage(messageBoardModel);

        return CommonReturnType.create(messageBoardModelFromReturn);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType deleteMessage(@RequestParam(name = "id") Integer id
    ) throws UserException {
        messageBoardService.deleteMsgById(id);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType listMessages(){
        List<MessageBoardModel> messageModelList = messageBoardService.listMsg();

        return CommonReturnType.create(messageModelList);
    }
}
