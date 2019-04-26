package com.spring.web.dao;

import com.spring.web.model.Messages;
import com.spring.web.model.TCheck;

import java.util.List;

public interface AppControllerMessageMapper {

    List<Messages>  findMessageList(String userId);

    List<TCheck>  findTCheckList(String userId);

}
