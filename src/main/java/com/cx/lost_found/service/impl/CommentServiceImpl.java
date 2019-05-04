package com.cx.lost_found.service.impl;

import com.cx.lost_found.dao.CommentDAOMapper;
import com.cx.lost_found.entity.CommentDAO;
import com.cx.lost_found.error.EmErr;
import com.cx.lost_found.error.UserException;
import com.cx.lost_found.service.CommentService;
import com.cx.lost_found.service.model.CommentModel;
import com.cx.lost_found.validator.ValidationResult;
import com.cx.lost_found.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private CommentDAOMapper commentDAOMapper;

    @Override
    public CommentModel createComment(CommentModel commentModel) throws UserException {
        if (commentModel == null){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR);
        }

        //校验
        ValidationResult result = validator.validator(commentModel);
        if (result.isHasErrors()){
            throw new UserException(EmErr.PARAMETER_VAILDATION_ERROR, result.getErrMsgs());
        }

        CommentDAO commentDAO = convertFromModel(commentModel);
        commentDAOMapper.insertSelective(commentDAO);
        commentModel.setId(commentDAO.getId());
        return getCommentById(commentModel.getId());
    }

    @Override
    public List<CommentModel> listCommentsByMsgId(Integer msgId) {
        List<CommentDAO> commentDAOList = commentDAOMapper.listByMsgId(msgId);
        List<CommentModel> commentModelList = commentDAOList.stream().map(commentDAO->{
            CommentModel commentModel = this.convertFromDAO(commentDAO);
            return commentModel;
        }).collect(Collectors.toList());
        return commentModelList;
    }

    @Override
    public CommentModel getCommentById(Integer id) {
        CommentDAO commentDAO = commentDAOMapper.selectByPrimaryKey(id);
        if (commentDAO == null){
            return null;
        }
        CommentModel commentModel = convertFromDAO(commentDAO);
        return commentModel;
    }

    @Override
    public Boolean deleteCommentById(Integer id) {
        commentDAOMapper.deleteByPrimaryKey(id);
        return true;
    }

    private CommentModel convertFromDAO(CommentDAO commentDAO){
        if (commentDAO == null){
            return null;
        }
        CommentModel commentModel = new CommentModel();
        BeanUtils.copyProperties(commentDAO, commentModel);
        commentModel.setCommentTime(commentDAO.getCommenttime());
        return commentModel;
    }

    private CommentDAO convertFromModel(CommentModel commentModel){
        if (commentModel == null){
            return null;
        }
        CommentDAO commentDAO = new CommentDAO();
        BeanUtils.copyProperties(commentModel, commentDAO);
        commentDAO.setCommenttime(commentModel.getCommentTime());
        return commentDAO;
    }
}
