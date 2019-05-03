package com.cx.lost_found.service.impl;

import com.cx.lost_found.controller.BaseController;
import com.cx.lost_found.dao.UserDAOMapper;
import com.cx.lost_found.entity.UserDAO;
import com.cx.lost_found.error.EmErr;
import com.cx.lost_found.error.UserException;
import com.cx.lost_found.response.CommonReturnType;
import com.cx.lost_found.service.UserService;
import com.cx.lost_found.service.model.MessageModel;
import com.cx.lost_found.service.model.UserModel;
import com.cx.lost_found.validator.ValidationResult;
import com.cx.lost_found.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOMapper userDAOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(String studentid){
        UserDAO userDAO = userDAOMapper.selectByPrimaryKey(studentid);
        return convertFromDAO(userDAO);
    }

    @Override
    public void register(UserModel userModel) throws UserException {
        if (userModel == null){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR);
        }
//        if (StringUtils.isEmpty(userModel.getPassword()) ||
//                StringUtils.isEmpty(userModel.getRealname()) ||
//                StringUtils.isEmpty(userModel.getTelephone()) ||
//                StringUtils.isEmpty(userModel.getEmail())){
//            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR);
//        }

        ValidationResult result = validator.validator(userModel);
        if (result.isHasErrors()){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR, result.getErrMsgs());
        }

        UserDAO userDAO = convertFromModel(userModel);
        try {
            userDAOMapper.insertSelective(userDAO);
        }catch (DuplicateKeyException ex){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR, "手机号重复注册");
        }
    }

    @Override
    public UserModel login(String studentId, String password) throws UserException {

        UserDAO userDAO = userDAOMapper.selectByPrimaryKey(studentId);
        if (userDAO == null){
            throw new UserException(EmErr.USER_LOGIN_FAIL);
        }
        UserModel userModel = convertFromDAO(userDAO);

        if (!StringUtils.equals(password, userModel.getPassword())){
            throw new UserException(EmErr.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    private UserModel convertFromDAO(UserDAO userDAO){
        if (userDAO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDAO, userModel);
        return userModel;
    }

    private UserDAO convertFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserDAO userDAO = new UserDAO();
        BeanUtils.copyProperties(userModel, userDAO);
        userDAO.setStudentid(userModel.getStudentId());
        return userDAO;
    }

    @Controller("message")
    @CrossOrigin(allowCredentials="true", allowedHeaders="*")
    @RequestMapping("/message")
    public static class MessageController extends BaseController {

        @Autowired
        private MessageServiceImpl messageService;

        @Autowired
        private HttpServletRequest httpServletRequest;

        //TODO:待处理数据库id问题
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
}
