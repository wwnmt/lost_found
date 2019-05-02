package com.cx.lost_found.service;

import com.cx.lost_found.service.model.FindModel;

import java.util.List;

public interface FindService {

    //创建find标语
    FindModel createFindMsg();

    //find标语列表浏览
    List<FindModel> listFindMsg();

    //find标语详情浏览
    FindModel getFindMsgById(Integer id);
}
