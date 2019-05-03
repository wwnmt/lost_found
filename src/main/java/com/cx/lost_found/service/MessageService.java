package com.cx.lost_found.service;

import com.cx.lost_found.error.UserException;
import com.cx.lost_found.service.model.MessageModel;

import java.util.List;

public interface MessageService {

    //创建find标语
    MessageModel createMessage(MessageModel messageModel) throws UserException;

    //find标语列表浏览
    List<MessageModel> listMsg();

    //find标语详情浏览
    MessageModel getMsgById(Integer id);
}
