package com.cx.lost_found.error;

public class UserException extends Exception implements CommonError{

    private CommonError commonError;

    public UserException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    public UserException(CommonError commonError, String errMsg){
        super();
        this.commonError = commonError;
        commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return commonError.getErrCode();
    }

    @Override
    public String gerErrMsg() {
        return commonError.gerErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        commonError.setErrMsg(errMsg);
        return this;
    }
}
