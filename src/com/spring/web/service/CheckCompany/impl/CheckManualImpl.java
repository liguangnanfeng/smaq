package com.spring.web.service.CheckCompany.impl;

import com.alibaba.fastjson.JSON;
import com.spring.web.dao.ACompanyManualMapper;
import com.spring.web.dao.TModelMapper;
import com.spring.web.model.ACompanyManual;
import com.spring.web.model.TModel;
import com.spring.web.model.TModelPart;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.response.MeasuresBean;
import com.spring.web.service.CheckCompany.ICheckManual;
import lombok.javac.handlers.HandleEqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 11:52
 * 1.小程序查询level和风险点
 *
 *
 */
@Service
@Transactional
public class CheckManualImpl implements ICheckManual {

    /**
     * 检查表信息
     */
    @Autowired
    private ACompanyManualMapper companyManualMapper;

    /**
     * 模版表信息
     */
    @Autowired
    private TModelMapper modelMapper;

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

    /**
     * TODO 根据 部门 岗位检查详细信息
     * @param checkLevel
     * @return
     */
    @Override
    public List<CheckLevel> selectLevel4AndId(CheckLevel checkLevel) {
        List<CheckLevel> list = new ArrayList<>();
            list .addAll(companyManualMapper.selectLevel3AndId(checkLevel));

        return list;
    }

    /**
    *
    * 根据level3 查询level4
    * */
    @Override
    public List<CheckLevel> selectLevel5AndId(CheckLevel checkLevel) {
        List<CheckLevel> list = new ArrayList<>();
       list .addAll(companyManualMapper.selectLevel4AndId(checkLevel));

        return list;
    }

    /**
     * 保存数据到检查表,并获取检查表id信息,
     * 检查类型 1: 自查 2: 第三方
     * 危险类型 1  基础 2  现场  3 临时
     * @param checkItem
     * @param zzjg
     * @return
     */
    @Override
    public Integer saveCheck(CheckItem checkItem,ZzjgPersonnel zzjg) {

        //获取企业的名称和查询企业对应的风险等级
        Integer uid = zzjg.getUid();// 企业id

        try {

            // 获取部门
            List<CheckLevel> checkLevels = checkItem.getCheckLevels();

            List<String> departmentName = new ArrayList<>();
            for (CheckLevel checkLevel : checkLevels) {
                departmentName.add(checkLevel.level1);
            }
            // 获取全部部门数据
            String  departmentNametr= JSON.toJSONString(departmentName);
            System.out.println(departmentNametr);
            // 获取分类

            // 保存model信息并返回id
            TModel tModel = saveTmodel(zzjg);
            int modelId = modelMapper.insertSelective(tModel);
            // 保存model_part() 计划检查模块表
            saveTmodelPath(modelId);

            // 保存check表数据
            Integer chaeckId = saveCheckAndPath();




        } catch (Exception e) {

            e.printStackTrace();
            throw  new RuntimeException("保存失败");

        }

        return null;
    }

    /**
     * 传递的模块信息进行保存括其他的数据进行保存
     * @param zzjg
     * @return
     */
    private TModel saveTmodel(ZzjgPersonnel zzjg ){

        // 获取行业id

        // 查询危险类型

        TModel tModel =new TModel();
        tModel.setUserId(zzjg.getUid()); // 公司id
        tModel.setFlag(1);               // 检查类型
        tModel.setPart("");              // 被检查的部门
        tModel.setIndustryId(1);         // 被检查的行业id
        tModel.setIndustryType(1);       // 被检查的危险类型
        tModel.setType(1);               //  1. 日常 2. 定期 3. 临时
        tModel.setCreateTime(new Date()); // 模版的创建时间


        return null;
    }

    /**
     * 保存模块表数据
     */
    private void saveTmodelPath(Integer Tmodel){
        TModelPart tModelPart = new TModelPart();

        tModelPart.setModelId(Tmodel);
        tModelPart.setLevels(""); //查询等级??
        tModelPart.setName("");   // 检查的部门

    }

    private Integer  saveCheckAndPath(){

        return null;

    }

}





