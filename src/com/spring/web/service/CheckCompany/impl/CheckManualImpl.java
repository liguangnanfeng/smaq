package com.spring.web.service.CheckCompany.impl;

import com.spring.web.dao.ACompanyManualMapper;
import com.spring.web.model.ACompanyManual;
import com.spring.web.model.response.MeasuresBean;
import com.spring.web.service.CheckCompany.ICheckManual;
import lombok.javac.handlers.HandleEqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 11:52
 * 1.关联前台查询后台
 *
 *
 */
@Service
public class CheckManualImpl implements ICheckManual {

    @Autowired
    private ACompanyManualMapper companyManualMapper;


    /**
     * 根据公司id和部门id查询部门下面的岗位及其风险点
     * @param uid
     * @param names
     * @return
     */
    @Override
    public  Map<String ,List> selectDangerAndManual(Integer uid, List<String> names) {

        Map<String, List> map = new HashMap<>();
        for (String name : names) {
            List<String> strings = companyManualMapper.selectDangerAndManual(uid, name);
            map.put(name, strings);

        }


        return map;
    }

    /**
     * 根据条件进行查询
     * @param uid
     * @param names
     * @return
     */
    @Override
    public List<MeasuresBean> selectmeasures(Integer uid, List<String> names) {

        List<MeasuresBean> measuresBeans=new ArrayList<>();

        for (String name : names) {
            measuresBeans = companyManualMapper.selectDangerBy( name,uid);

        }
        System.out.println(measuresBeans);

        return measuresBeans;
    }


}
