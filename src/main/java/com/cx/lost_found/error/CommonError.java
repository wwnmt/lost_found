package com.cx.lost_found.error;

public interface CommonError {

    int getErrCode();
    String gerErrMsg();
    CommonError setErrMsg(String message);
}
