package com.spring.web.service.AppMessage;

import com.spring.web.dao.AppControllerMessageMapper;
import com.spring.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("AppMessageService")
public class AppMessageServiceImpl implements AppMessageService {

    @Autowired
    private AppControllerMessageMapper appControllerMessageMapper;

    @Override
    public List<Messages> findMessageList(String userId,String pageNo,String pageSize) {
        return  appControllerMessageMapper.findMessageList(userId,pageNo,pageSize);
    }

    @Override
    public Messages findMessageDetail(String id) {
        return appControllerMessageMapper.findMessageDetail(id);
    }

    @Override
    public List<TCheck> findTCheckList(String cid,String pageNo,String pageSize) {
        return appControllerMessageMapper.findTCheckList(cid,pageNo,pageSize);
    }

    @Override
    public TCheck findCheckById(String checkId) {
        return appControllerMessageMapper.findCheckById(checkId);
    }

    @Override
    public ZzjgPersonnel findPersonnel(String userId) {
        return appControllerMessageMapper.findPersonnel(userId);
    }

    @Override
    public List<Map> findRectificationList(String checkId) {
        return appControllerMessageMapper.findRectificationList(checkId);
    }

    @Override
    public TRectification findTRectification(String checkId) {
        return appControllerMessageMapper.findTRectification(checkId);
    }


}
