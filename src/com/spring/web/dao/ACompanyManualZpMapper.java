package com.spring.web.dao;

import com.spring.web.model.ACompanyManualZp;

public interface ACompanyManualZpMapper {

    int deleteByPrimaryKey(Integer acmId);


    int insert(ACompanyManualZp record);


    int insertSelective(ACompanyManualZp record);


    ACompanyManualZp selectByPrimaryKey(Integer acmId);


    int updateByPrimaryKeySelective(ACompanyManualZp record);


    int updateByPrimaryKey(ACompanyManualZp record);
}