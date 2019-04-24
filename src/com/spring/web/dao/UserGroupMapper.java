package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.UserGroup;

public interface UserGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
    
    List<Map<String, Object>> getUsersByOrgId(@Param("map")Map<String, Object> params);
    
    List<Map<String, Object>> getCompanyByCondition(@Param("map")Map<String, Object> params);
    
    List<Map<String, Object>> getVillageNameByUserId(@Param("userId")Integer userId);
    
    List<Map<String, Object>> getVillageNameByUserId1(@Param("userId")Integer userId);//add wz 190119 添加行业端、企业端名称
    
    List<Map<String, Object>> searchCompanyByCondition(@Param("map")Map<String, Object> params);
    
    List<Map<String, Object>> searchCompanyByConditionTrade(@Param("map")Map<String, Object> params); //add wz 190116 行业端向下推送
    
    List<Map<String, Object>> searchCompanyByConditionCompany(@Param("map")Map<String, Object> params); //企业向上 村
    
    List<Map<String, Object>> searchCompanyByConditionCompanyTrade(@Param("map")Map<String, Object> params); //企业向上 行业
    
    
    List<UserGroup> getUserGroupList(@Param("map")Map<String, Object> params);
    
    String getCompanyNameByUserId(@Param("userId")Integer userId);
    
    Map<String, Object> getCompanyInfoById(@Param("userId")Integer userId);
}