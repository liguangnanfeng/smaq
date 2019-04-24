package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.TSensors;

public interface TSensorsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSensors record);

    int insertSelective(TSensors record);

    TSensors selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSensors record);

    int updateByPrimaryKey(TSensors record);
    
    List<Map<String, Object>> getCompanyDevice(@Param("companyId")Integer companyId);
    
    Integer checkSensorSnExsist(@Param("map")Map<String, Object> params);
    
    List<Map<String, Object>> getSensorType();
    
    List<Map<String, Object>> getSensorsList(@Param("map")Map<String, Object> map);
    
    Integer getSensorsListCount(@Param("map")Map<String, Object> map);
    
    List<Map<String, Object>> getDeviceListBySensor(@Param("companyId")Integer companyId);
}