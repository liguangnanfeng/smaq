package com.spring.web.dao;

import com.spring.web.model.DangerCoordinate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DangerCoordinateMapper {


    boolean insert(DangerCoordinate dangerCoordinate);

    List<DangerCoordinate> selectOne(@Param("user_id") Integer user_id);

    boolean updateByPrimaryKey(DangerCoordinate dangerCoordinate);
}

