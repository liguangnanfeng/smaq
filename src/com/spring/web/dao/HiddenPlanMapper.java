package com.spring.web.dao;

import com.spring.web.model.ACompanyManual;
import com.spring.web.model.HiddenPlan;
import com.spring.web.model.request.CheckLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HiddenPlanMapper {


    Integer insertSelective(HiddenPlan hiddenPlan1);

    HiddenPlan selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(HiddenPlan hiddenPlan1);

    Integer insert(HiddenPlan hiddenPlan1);

    Integer updateByPrimaryKey(HiddenPlan hiddenPlan1);

    List<HiddenPlan> selectDpid(Integer dpid);

    List<Map<String,Object>> selectCountAll(@Param("uid") Integer uid);
}

