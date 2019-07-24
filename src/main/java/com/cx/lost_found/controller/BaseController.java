package com.cx.lost_found.controller;

import com.cx.lost_found.error.EmErr;
import com.cx.lost_found.error.UserException;
import com.cx.lost_found.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    static final String CONTENT_FORM = "application/x-www-form-urlencoded";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof UserException) {
            UserException userException = (UserException) ex;
            responseData.put("errCode", userException.getErrCode());
            responseData.put("errMsg", userException.gerErrMsg());
        } else {
            responseData.put("errCode", EmErr.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmErr.UNKNOWN_ERROR.gerErrMsg());
        }
        return CommonReturnType.create(responseData, "fail");
    }

    boolean equals(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }
}
