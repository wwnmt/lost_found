package com.cx.lost_found.service.impl;

import com.cx.lost_found.dao.AreaDAOMapper;
import com.cx.lost_found.dao.MessageDAOMapper;
import com.cx.lost_found.dao.TypeDAOMapper;
import com.cx.lost_found.entity.MessageDAO;
import com.cx.lost_found.error.EmErr;
import com.cx.lost_found.error.UserException;
import com.cx.lost_found.service.MessageService;
import com.cx.lost_found.service.model.MessageModel;
import com.cx.lost_found.validator.ValidationResult;
import com.cx.lost_found.validator.ValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private MessageDAOMapper messageDAOMapper;

    @Autowired
    private TypeDAOMapper typeDAOMapper;

    @Autowired
    private AreaDAOMapper areaDAOMapper;

    @Override
    @Transactional
    public MessageModel createMessage(MessageModel messageModel) throws UserException {

        //校验
//        ValidationResult result = validator.validator(messageModel);
//        if (result.isHasErrors()){
//            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR, result.getErrMsgs());
//        }

        MessageDAO messageDAO = convertFromModel(messageModel);
        messageDAO.setId(10);
        messageDAOMapper.insertSelective(messageDAO);
        messageModel.setId(messageDAO.getId());

        return getFindMsgById(messageModel.getId());
    }

    @Override
    public List<MessageModel> listFindMsg() {
        return null;
    }

    @Override
    public MessageModel getFindMsgById(Integer id) {
        MessageDAO messageDAO = messageDAOMapper.selectByPrimaryKey(id);
        if (messageDAO == null){
            return null;
        }
        MessageModel messageModel = convertFromDAO(messageDAO);
        return messageModel;
    }

    private MessageModel convertFromDAO(MessageDAO messageDAO){
        if (messageDAO == null){
            return null;
        }
        MessageModel messageModel = new MessageModel();
        messageModel.setUserTelephone(messageDAO.getUserTelephone());
        messageModel.setTitle(messageDAO.getTitle());
        messageModel.setDescription(messageDAO.getDescription());
        messageModel.setMessageType(messageDAO.getMsgtype());
        messageModel.setType(typeDAOMapper.selectByPrimaryKey(messageDAO.getTypeId()).getTypename());
        messageModel.setArea(areaDAOMapper.selectByPrimaryKey(messageDAO.getAreaId()).getAreaname());
        messageModel.setFindTime(messageDAO.getLosttime());
        messageModel.setUpTime(messageDAO.getUptime());
        messageModel.setPicture(messageDAO.getPicture());
        messageModel.setContactPhone(messageDAO.getContactPhone());
        messageModel.setContactName(messageDAO.getContactName());
        messageModel.setAdminJudge(messageDAO.getAdminjudge());
        messageModel.setStatus(messageDAO.getStatus());

        return messageModel;
    }

    private MessageDAO convertFromModel(MessageModel messageModel){
        if (messageModel == null){
            return null;
        }
        MessageDAO messageDAO = new MessageDAO();
        messageDAO.setUserTelephone(messageModel.getUserTelephone());
        messageDAO.setTitle(messageModel.getTitle());
        messageDAO.setDescription(messageModel.getDescription());
        messageDAO.setMsgtype(messageModel.getMessageType());
        messageDAO.setTypeId(typeDAOMapper.selectByType(messageModel.getType()).getId());
        messageDAO.setAreaId(areaDAOMapper.selectByArea(messageModel.getArea()).getId());
        messageDAO.setLosttime(messageModel.getFindTime());
        messageDAO.setUptime(messageModel.getUpTime());
        messageDAO.setPicture(messageModel.getPicture());
        messageDAO.setContactName(messageModel.getContactName());
        messageDAO.setContactPhone(messageModel.getUserTelephone());
        messageDAO.setAdminjudge(messageModel.getAdminJudge());
        messageDAO.setStatus(messageModel.getStatus());
        return messageDAO;
    }
}
