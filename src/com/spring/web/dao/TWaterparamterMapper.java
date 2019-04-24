package com.spring.web.dao;

import java.util.List;
import com.spring.web.model.TWaterparamter;

public interface TWaterparamterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TWaterparamter record);

    int insertSelective(TWaterparamter record);

    TWaterparamter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TWaterparamter record);

    int updateByPrimaryKey(TWaterparamter record);
    
    List<TWaterparamter> selectWaterParamters();
}