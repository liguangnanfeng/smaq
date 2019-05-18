package com.spring.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.TCleanWarnSetting;

public interface TCleanWarnSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCleanWarnSetting record);

    int insertSelective(TCleanWarnSetting record);

    TCleanWarnSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCleanWarnSetting record);

    int updateByPrimaryKey(TCleanWarnSetting record);
    
    List<TCleanWarnSetting> getCleanSettings(@Param("creator") Integer creator);
    
    List<TCleanWarnSetting> getAllCleanSettings();
}