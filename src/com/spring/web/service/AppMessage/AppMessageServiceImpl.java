package com.spring.web.service.AppMessage;

import com.spring.web.dao.AppControllerMessageMapper;
import com.spring.web.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("AppMessageService")
public class AppMessageServiceImpl implements AppMessageService {

    @Autowired
    private AppControllerMessageMapper appControllerMessageMapper;

    @Override
    public List<Messages> findMessageList(String userId) {
        return  appControllerMessageMapper.findMessageList(userId);
    }
}
