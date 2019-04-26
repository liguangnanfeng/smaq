package com.spring.web.service.AppMessage;

import com.spring.web.model.Messages;

import java.util.List;

public interface AppMessageService {

    List<Messages> findMessageList(String userId);

}
