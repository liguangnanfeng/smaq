package com.spring.web.dao;

import com.spring.web.model.Commerce;
import org.apache.ibatis.annotations.Param;

public interface CommerceMapper {

    Commerce selectComFlag(@Param("user_id") Integer user_id);

    int insert(Commerce commerce1);

    int updateByPrimaryKey(Commerce commerce1);
}

