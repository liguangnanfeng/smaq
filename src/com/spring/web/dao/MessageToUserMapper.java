package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.MessageToUser;

public interface MessageToUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(MessageToUser record);

    int insertSelective(MessageToUser record);

    MessageToUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MessageToUser record);

    int updateByPrimaryKey(MessageToUser record);
    
    MessageToUser getMessageStatus(@Param("map")Map<String, Object> params);
    
    int updateInfoByMessageAndUserId(MessageToUser record);
    
    Long insertMessagesListBatch(List<MessageToUser> users);
    
    List<MessageToUser> getUnReviceMessages(@Param("userId")Integer userId);
    
    Integer getUnreadMessageCount(@Param("userId")Integer userId);
}