package com.cx.lost_found.service;

import com.cx.lost_found.error.UserException;
import com.cx.lost_found.service.model.UserModel;

public interface UserService {

    UserModel getUserById(String studentid);
    void register(UserModel userModel) throws UserException;
    UserModel login(String studentid, String password) throws UserException;
}
