package com.spring.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.TWarnmsg;

public interface TWarnmsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TWarnmsg record);

    int insertSelective(TWarnmsg record);

    TWarnmsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TWarnmsg record);

    int updateByPrimaryKey(TWarnmsg record);
    
    Integer getZdAndGwTotal(@Param("map")Map<String, Object> params);
    
    Integer getWarnMsgListCount(@Param("map")Map<String, Object> params);
    
    List<Integer> getWarnMsgCompanyListCount(@Param("map")Map<String, Object> params);
    
    List<Map<String, Object>>  getWarnMsgList(@Param("map")Map<String, Object> params);
    
    List<Map<String, Object>>  getWarnMsgCompanyList(@Param("map")Map<String, Object> params);
    
    int updateCleanTimeByMonth(Date wareTime);
    
    List<Map<String, Object>> getSensorTypes(@Param("type")Integer type);
    
    List<Map<String, Object>>  getWaterWarnCompanyList(@Param("map")Map<String, Object> params);
    
    List<Integer> getWaterWarnCompanyListCount(@Param("map")Map<String, Object> params);
    
    Integer getWaterWarnListCount(@Param("map")Map<String, Object> params);
    
    List<Map<String, Object>>  getWaterWarnMsgList(@Param("map")Map<String, Object> params);
    
}