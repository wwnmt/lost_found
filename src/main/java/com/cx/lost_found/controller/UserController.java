package com.cx.lost_found.controller;


import com.cx.lost_found.error.EmErr;
import com.cx.lost_found.error.UserException;
import com.cx.lost_found.response.CommonReturnType;
import com.cx.lost_found.service.UserService;
import com.cx.lost_found.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


@Controller("user")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    //用户登录模块
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {CONTENT_FORM})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="studentid")String studentid,
                                     @RequestParam(name="password")String password
    ) throws UserException {

        if (StringUtils.isEmpty(studentid) || StringUtils.isEmpty(password)){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR);
        }
        //登陆
        UserModel userModel = userService.login(studentid, password);

        //将登陆凭证加入到用户登陆成功session中
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(null);
    }

    //用户注册模块
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {CONTENT_FORM})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="studentid")String studentid,
                                     @RequestParam(name="password")String password,
                                     @RequestParam(name="realname")String realname,
                                     @RequestParam(name="telephone")String telephone,
                                     @RequestParam(name="email")String email,
                                     @RequestParam(name="optCode")String optCode
                                     ) throws UserException {
        //验证手机号和验证码
        String inSessionOptCode = (String) httpServletRequest.getSession().getAttribute(telephone);
        if (!equals(optCode, inSessionOptCode)){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR, "短信验证码出错");
        }

        UserModel userModel = new UserModel();
        userModel.setStudentId(studentid);
        userModel.setPassword(password);
        userModel.setTelephone(telephone);
        userModel.setEmail(email);
        userModel.setRealname(realname);
        userModel.setIsadmin(false);

        userService.register(userModel);
        return CommonReturnType.create(null);
    }


    //用户获取手机验证码
    @RequestMapping(value = "/getopt", method = RequestMethod.POST, consumes = {CONTENT_FORM})
    @ResponseBody
    public CommonReturnType getOpt(@RequestParam(name="telephone")String telephone){

        //生成随机验证码
        Random random = new Random();
        int randomInt = random.nextInt(999);
        randomInt += 1000;

        String optCode = String.valueOf(randomInt);

        httpServletRequest.getSession().setAttribute(telephone, optCode);

        //发送给用户
        System.out.println("手机号" + telephone + "，验证码 " + optCode);

        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="studentid")String studentid) throws UserException {
        UserModel userModel = userService.getUserById(studentid);
        if (userModel == null){
            throw new UserException(EmErr.USER_NOT_EXIST);
        }
        return CommonReturnType.create(userModel);
    }
}
