package com.spring.web.dao;

import com.spring.web.model.TCollection;

public interface TCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCollection record);

    int insertSelective(TCollection record);

    TCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCollection record);

    int updateByPrimaryKey(TCollection record);
}