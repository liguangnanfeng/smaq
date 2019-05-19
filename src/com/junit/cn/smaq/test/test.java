package com.junit.cn.smaq.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.dao.TCheckItemMapper;
import com.spring.web.dao.TCheckMapper;
import com.spring.web.dao.TCheckPartMapper;
import com.spring.web.dao.TCompanyMapper;
import com.spring.web.model.*;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.CheckCompany.SaveMessageService;
import com.spring.web.service.SaveModelService;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/06 14:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/applicationContext.xml", /*"file:WebContent/WEB-INF/ehcache.xml",*/"file:WebContent/WEB-INF/spring-servlet.xml"})
public class test {

    @Autowired
    private TCheckMapper tCheckMapper;
    @Autowired
    private SaveMessageService saveMessageService;
    @Autowired
    private ICheckManual iCheckManual;
    @Autowired
    private TCompanyMapper tCompanyMapper;

    @Autowired
    private TCheckItemMapper tCheckItemMapper;

    @Autowired
    private TCheckPartMapper tCheckPartMapper;

    @Autowired
    private SaveMessageService   SaveDataImpl;



    @Test
    public void test01() {

        List<TCheck> allByUidAndUserId = tCheckMapper.findAllByUidAndUserId(844, 37097);
        System.out.println(allByUidAndUserId);

    }
    /**
     * 测试根据模版id查询模版数据
     */
    @Test
    public void test02() {
        CheckItemS checkItemByModelId = saveMessageService.findCheckItemByModelId(1545);
        System.out.println(checkItemByModelId);

    }

    /**
     * ["2984","2985","2986"] 进行解析
     */
    @Test
    public void test03() {
        String str = "[\"2984\",\"2985\",\"2986\"]";

        str.replace("\"", " ");
        str.replace("]", " ");
        str.replace("[", " ");
        System.out.println(str);

        List<String> list = JSON.parseObject(str, List.class);
        System.out.println(list);

    }

    /**
     * 获取所有的高危检查
     */
    @Test
    public void test04() {
        List<Map> maps = iCheckManual.checkGaoWei(6);
        System.out.println(maps);

    }

    /**
     * 获取所有的基础查询
     */
    @Test
    public void test06(){
        TCompany tCompany = tCompanyMapper.selectByPrimaryKey(37097);
        System.out.println(tCompany);
    }

    /**
     * 检查item表结构信息
     */
    @Test
    public void test07(){
        List<TCheckItem> tCheckItems = tCheckItemMapper.selectItemByCheckId(38302);
        System.out.println(tCheckItems);
    }

    @Test
    public void test08(){
       Integer checkId = 38302;

        TCheck tCheck = tCheckMapper.selectByPrimaryKey(38302); //获取主表
        List<TCheckPart> tCheckParts = tCheckPartMapper.findAllByCheckId(38302);  //获取岗位
        List<TCheckItem> tCheckItems = tCheckItemMapper.selectItemByCheckId(38302); //获取详情

        System.out.println(tCheck);
        System.out.println(tCheckParts);
        System.out.println(tCheckItems);
    }

    /**
     * 获取检查内容
     */
    @Test
    public void test09(){
        CheckItemS checkItemByModelId = SaveDataImpl.findCheckItemByModelId(1630);
        System.out.println(checkItemByModelId);
    }


}
