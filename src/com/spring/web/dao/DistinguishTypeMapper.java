package com.spring.web.dao;

import com.spring.web.model.DistinguishType;
import org.apache.ibatis.annotations.Param;

public interface DistinguishTypeMapper {

    int updateByPrimaryKeySelective(DistinguishType distinguishType);

    int insert(DistinguishType distinguishType);

    DistinguishType selectNum(@Param("dep_id")Integer dep_id, @Param("flag")String flag, @Param("user_id")Integer user_id);

    boolean updatePoint(DistinguishType distinguishType);
}

