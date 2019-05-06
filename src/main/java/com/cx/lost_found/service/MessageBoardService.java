package com.cx.lost_found.service;

import com.cx.lost_found.error.UserException;
import com.cx.lost_found.service.model.MessageBoardModel;

import java.util.List;

public interface MessageBoardService {

    MessageBoardModel createMessage(MessageBoardModel messageBoardModel) throws UserException;

    List<MessageBoardModel> listMsg();

    MessageBoardModel getMsgById(Integer id);

    void deleteMsgById(Integer id) throws UserException;
}
