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
}
