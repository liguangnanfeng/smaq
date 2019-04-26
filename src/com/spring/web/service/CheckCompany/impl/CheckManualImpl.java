package com.spring.web.service.CheckCompany.impl;

import com.spring.web.dao.ACompanyManualMapper;
import com.spring.web.model.ACompanyManual;
import com.spring.web.model.request.CheckLevel;
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
 * 1.小程序查询level和风险点
 *
 *
 */
@Service
public class CheckManualImpl implements ICheckManual {


    @Autowired
    private ACompanyManualMapper companyManualMapper;


    /**
     * 根据公司id和部门id查询部门下面的岗位
     * 根据可以进行传递
     * @param uid
     * @param names
     * @return
     */
    @Override
    public  Map<String,List> selectDangerAndManual(Integer uid, List<String> names) {

        Map<String,List> map = new HashMap<>();



        for (String name : names) {
            map.put(name,companyManualMapper.selectDangerAndManual(uid, name));

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
//
//        List<MeasuresBean> measuresBeans=new ArrayList<>();
//
//        for (String name : names) {
//            measuresBeans = companyManualMapper.selectDangerBy( name,uid);
//
//        }
//        System.out.println(measuresBeans);
//
//        return measuresBeans;
            return  null;
   }

     /**
     * TODO 根据uid(企业总id) level2(岗位) 查询level3 及其id
     * @param checkLevel
     */
    @Override
    public  List<CheckLevel> selectLevel3AndId(List<CheckLevel> checkLevel) {

        List<CheckLevel> list = new ArrayList<>();


        for (CheckLevel check : checkLevel) {
         list .addAll(companyManualMapper.selectLevel3AndId(check));
        }
        return list;
    }

    @Override
    public List<CheckLevel> selectLevel4AndId(CheckLevel checkLevel) {
        List<CheckLevel> list = new ArrayList<>();
        list .addAll(companyManualMapper.selectLevel3AndId(checkLevel));
        return list;
    }

}
