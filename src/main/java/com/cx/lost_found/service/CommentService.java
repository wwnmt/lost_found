package com.cx.lost_found.service;

import com.cx.lost_found.error.UserException;
import com.cx.lost_found.service.model.CommentModel;

import java.util.List;

public interface CommentService {

    CommentModel createComment(CommentModel commentModel) throws UserException;

    List<CommentModel> listCommentsByMsgId(Integer msgId);

    CommentModel getCommentById(Integer id);

    Boolean deleteComment(Integer id);
}
