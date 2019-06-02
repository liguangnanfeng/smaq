package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.UserGroupList;

public interface UserGroupListMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserGroupList record);

    int insertSelective(UserGroupList record);

    UserGroupList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserGroupList record);

    int updateByPrimaryKey(UserGroupList record);
    
    Long insertCompanyListBatch(List<UserGroupList> list);
    
    List<Map<String, Object>> getGroupUserList(@Param("groupId") String groupId);

    int deleteByGroupId(@Param("groupId") String groupId);
}