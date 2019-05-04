package com.cx.lost_found.controller;

import com.cx.lost_found.error.UserException;
import com.cx.lost_found.response.CommonReturnType;
import com.cx.lost_found.service.CommentService;
import com.cx.lost_found.service.impl.CommentServiceImpl;
import com.cx.lost_found.service.model.CommentModel;
import com.cx.lost_found.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller("comment")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {CONTENT_FORM})
    @ResponseBody
    public CommonReturnType createComment(@RequestParam(name = "msgid")Integer msgid,
                                          @RequestParam(name = "comment")String comment) throws UserException {
        UserModel user = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        String studentid = user.getStudentId();

        CommentModel commentModel = new CommentModel();
        commentModel.setStudentid(studentid);
        commentModel.setMsgid(msgid);
        commentModel.setComment(comment);

        Date commentTime = new Date();
        commentModel.setCommentTime(commentTime);

        CommentModel commentModelFromReturn = commentService.createComment(commentModel);

        return CommonReturnType.create(commentModelFromReturn);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType listComment(@RequestParam(name = "msgid")Integer msgid){

        List<CommentModel> commentModelList = commentService.listCommentsByMsgId(msgid);

        return CommonReturnType.create(commentModelList);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType deleteComment(@RequestParam(name = "id")Integer id) throws UserException {

        commentService.deleteCommentById(id);
        return CommonReturnType.create(null);
    }

}
