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
import java.util.stream.Collectors;

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
        if (messageModel == null){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR);
        }

        //校验
        ValidationResult result = validator.validator(messageModel);
        if (result.isHasErrors()){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR, result.getErrMsgs());
        }

        MessageDAO messageDAO = convertFromModel(messageModel);
        messageDAOMapper.insertSelective(messageDAO);
        messageModel.setId(messageDAO.getId());

        return getMsgById(messageModel.getId());
    }

    @Override
    public List<MessageModel> listMsg() {
        List<MessageDAO> messageDAOList = messageDAOMapper.listMessages();
        List<MessageModel> messageModelList = messageDAOList.stream().map(messageDAO->{
            MessageModel messageModel = this.convertFromDAO(messageDAO);
            return messageModel;
        }).collect(Collectors.toList());
        return messageModelList;
    }

    @Override
    public MessageModel getMsgById(Integer id) {
        MessageDAO messageDAO = messageDAOMapper.selectByPrimaryKey(id);
        if (messageDAO == null){
            return null;
        }
        MessageModel messageModel = convertFromDAO(messageDAO);
        return messageModel;
    }

    @Override
    public MessageModel updateMessage(MessageModel messageModel) throws UserException {
        if (messageModel == null){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR);
        }
        MessageDAO messageDAO = convertFromModel(messageModel);
        messageDAO.setId(messageModel.getId());
        messageDAOMapper.updateByPrimaryKeySelective(messageDAO);
        messageModel  = getMsgById(messageDAO.getId());
        return messageModel;
    }

    private MessageModel convertFromDAO(MessageDAO messageDAO){
        if (messageDAO == null){
            return null;
        }
        MessageModel messageModel = new MessageModel();
        messageModel.setId(messageDAO.getId());
        messageModel.setStudentid(messageDAO.getStudentid());
        messageModel.setTitle(messageDAO.getTitle());
        messageModel.setDescription(messageDAO.getDescription());
        messageModel.setMessageType(messageDAO.getMsgtype());
        //type不存在时，将新type加入到数据库
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
        messageDAO.setStudentid(messageModel.getStudentid());
        messageDAO.setTitle(messageModel.getTitle());
        messageDAO.setDescription(messageModel.getDescription());
        messageDAO.setMsgtype(messageModel.getMessageType());
        messageDAO.setTypeId(typeDAOMapper.selectByType(messageModel.getType()).getId());
        messageDAO.setAreaId(areaDAOMapper.selectByArea(messageModel.getArea()).getId());
        messageDAO.setLosttime(messageModel.getFindTime());
        messageDAO.setUptime(messageModel.getUpTime());
        messageDAO.setPicture(messageModel.getPicture());
        messageDAO.setContactName(messageModel.getContactName());
        messageDAO.setContactPhone(messageModel.getContactPhone());
        messageDAO.setAdminjudge(messageModel.getAdminJudge());
        messageDAO.setStatus(messageModel.getStatus());
        return messageDAO;
    }
}
