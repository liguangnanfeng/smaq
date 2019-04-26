package com.spring.web.service.CheckCompany;

import com.spring.web.model.ACompanyManual;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.response.MeasuresBean;

import java.util.List;
import java.util.Map;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 11:52
 */
public interface ICheckManual {
    Map<String,List> selectDangerAndManual(Integer cid, List<String> names);

    List<MeasuresBean> selectmeasures(Integer uid, List<String> names);

    List<CheckLevel> selectLevel3AndId(List<CheckLevel> checkLevel);


    List<CheckLevel> selectLevel4AndId(CheckLevel checkLevel);
}
