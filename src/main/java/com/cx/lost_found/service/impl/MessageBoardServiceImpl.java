package com.cx.lost_found.service.impl;

import com.cx.lost_found.dao.MessageBoardDAOMapper;
import com.cx.lost_found.entity.MessageBoardDAO;
import com.cx.lost_found.error.EmErr;
import com.cx.lost_found.error.UserException;
import com.cx.lost_found.service.MessageBoardService;
import com.cx.lost_found.service.model.MessageBoardModel;
import com.cx.lost_found.validator.ValidationResult;
import com.cx.lost_found.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageBoardServiceImpl implements MessageBoardService {

    @Autowired
    private MessageBoardDAOMapper messageBoardDAOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public MessageBoardModel createMessage(MessageBoardModel messageBoardModel) throws UserException {
        if (messageBoardModel == null){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR);
        }

        //校验
        ValidationResult result = validator.validator(messageBoardModel);
        if (result.isHasErrors()){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR, result.getErrMsgs());
        }
        MessageBoardDAO messageBoardDAO = convertFromModel(messageBoardModel);
        messageBoardDAOMapper.insertSelective(messageBoardDAO);
        messageBoardModel.setId(messageBoardDAO.getId());
        return getMsgById(messageBoardModel.getId());
    }

    @Override
    public List<MessageBoardModel> listMsg() {
        List<MessageBoardDAO> messageBoardDAOList = messageBoardDAOMapper.listMessages();
        List<MessageBoardModel> messageModelList = messageBoardDAOList.stream().map(messageBoardDAO->{
            MessageBoardModel messageBoardModel = this.convertFromDAO(messageBoardDAO);
            return messageBoardModel;
        }).collect(Collectors.toList());
        return messageModelList;
    }

    @Override
    public MessageBoardModel getMsgById(Integer id) {
        MessageBoardDAO messageBoardDAO = messageBoardDAOMapper.selectByPrimaryKey(id);
        if (messageBoardDAO == null){
            return null;
        }
        MessageBoardModel messageBoardModel = convertFromDAO(messageBoardDAO);
        return messageBoardModel;
    }

    @Override
    public void deleteMsgById(Integer id) throws UserException {
        if (getMsgById(id) == null){
            throw new UserException(EmErr.MESSAGE_NOT_EXIST);
        }
        messageBoardDAOMapper.deleteByPrimaryKey(id);
    }

    private MessageBoardModel convertFromDAO(MessageBoardDAO messageBoardDAO){
        if (messageBoardDAO == null){
            return null;
        }
        MessageBoardModel messageBoardModel = new MessageBoardModel();
        BeanUtils.copyProperties(messageBoardDAO, messageBoardModel);
        return messageBoardModel;
    }

    private MessageBoardDAO convertFromModel(MessageBoardModel messageBoardModel){
        if (messageBoardModel == null){
            return null;
        }
        MessageBoardDAO messageBoardDAO = new MessageBoardDAO();
        BeanUtils.copyProperties(messageBoardModel, messageBoardDAO);
        return messageBoardDAO;
    }
}
