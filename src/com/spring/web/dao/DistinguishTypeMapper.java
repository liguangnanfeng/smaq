package com.spring.web.dao;

import com.spring.web.model.DistinguishType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistinguishTypeMapper {


    List<DistinguishType> selectPoint(@Param("dep_id") Integer dep_id, @Param("flag")Integer flag, @Param("user_id")Integer user_id);
}

