package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.TDevice;

public interface TDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDevice record);

    int insertSelective(TDevice record);

    TDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDevice record);

    int updateByPrimaryKey(TDevice record);
    
    List<Map<String, Object>> getDeviceCompanyList();
    
    Integer checkDeviceSnExsits(@Param("deviceSn") String deviceSn);

    List<Map<String, Object>> getDeviceListByCondition(@Param("map") Map<String, Object> map);

    Integer getDeviceCountByCondition(@Param("map") Map<String, Object> map);

    int deleteDeviceWithSensors(@Param("deviceSn") String deviceSn);

    Integer getCompanyIdByDeviceSn(@Param("deviceSn") String deviceSn);
}