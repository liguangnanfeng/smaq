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
    public List<Messages> findMessageList(String userId,Integer pageNo,Integer pageSize) {
        return  appControllerMessageMapper.findMessageList(userId,pageNo,pageSize);
    }

    @Override
    public Messages findMessageDetail(String id) {
        return appControllerMessageMapper.findMessageDetail(id);
    }

    @Override
    public List<Map> findTCheckList(String userId,Integer pageNo,Integer pageSize) {
        return appControllerMessageMapper.findTCheckList(userId,pageNo,pageSize);
    }

    @Override
    public List<Map> findTCheckListByStatus(String userId, Integer pageNo, Integer pageSize) {
        return appControllerMessageMapper.findTCheckListByStatus(userId,pageNo,pageSize);
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

    @Override
    public List<Map<String, Object>> selectRecheckByCheckId(Integer checkId) {
        return appControllerMessageMapper.selectRecheckByCheckId(checkId);
    }

    @Override
    public List<Map<String, Object>> selectRecheckByCheckId2(Integer userId) {
        return appControllerMessageMapper.selectRecheckByCheckId2(userId);
    }

    @Override
    public List<Map> selectCheckByFlag() {
        return appControllerMessageMapper.selectCheckByFlag();
    }




}
