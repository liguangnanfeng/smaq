package com.spring.web.dao;

import com.spring.web.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppControllerMessageMapper {

    List<Messages>  findMessageList(@Param("userId") String userId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    TCheck findCheckById(String checkId);

    Messages findMessageDetail(String id);

    List<TCheck>  findTCheckList(@Param("cid") String cid,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    ZzjgPersonnel findPersonnel(String userId);

    List<Map> findRectificationList(String checkId);

    TRectification findTRectification(String checkId);

}
