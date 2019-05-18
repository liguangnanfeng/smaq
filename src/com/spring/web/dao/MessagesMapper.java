package com.spring.web.dao;

import java.util.List;
import java.util.Map;

import com.spring.web.model.Messages;
import org.apache.ibatis.annotations.Param;


public interface MessagesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Messages record);

    int insertSelective(Messages record);

    Messages selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKeyWithBLOBs(Messages record);

    int updateByPrimaryKey(Messages record);
    
    Messages getMessageByCondition(@Param("map") Map<String, Object> params);

    List<Map<String, Object>> getRecviceMessages(@Param("map") Map<String, Object> params);

    Integer getRecviceMessagesCount(@Param("map") Map<String, Object> params);

    List<Map<String, Object>> getSendMessages(@Param("map") Map<String, Object> params);

    Integer getSendMessagesCount(@Param("map") Map<String, Object> params);
}