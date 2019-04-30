package com.spring.web.dao;

import com.spring.web.model.*;

import java.util.List;
import java.util.Map;

public interface AppControllerMessageMapper {

    List<Messages>  findMessageList(String userId,String pageNo,String pageSize);

    TCheck findCheckById(String checkId);

    Messages findMessageDetail(String id);

    List<TCheck>  findTCheckList(String cid,String pageNo,String pageSize);

    ZzjgPersonnel findPersonnel(String userId);

    List<Map> findRectificationList(String checkId);

    TRectification findTRectification(String checkId);

}
