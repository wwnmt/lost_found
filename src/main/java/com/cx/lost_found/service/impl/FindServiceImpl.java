package com.cx.lost_found.service.impl;

import com.cx.lost_found.service.FindService;
import com.cx.lost_found.service.model.FindModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FindServiceImpl implements FindService {

    @Autowired


    @Override
    @Transactional
    public FindModel createFindMsg() {

        return null;
    }

    @Override
    public List<FindModel> listFindMsg() {
        return null;
    }

    @Override
    public FindModel getFindMsgById(Integer id) {
        return null;
    }
}
