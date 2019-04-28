package com.junit.cn.smaq.test;

import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.service.CheckCompany.ICheckManual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/27 18:25
 * 单元测试
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/applicationContext.xml", /*"file:WebContent/WEB-INF/ehcache.xml",*/"file:WebContent/WEB-INF/spring-servlet.xml"})
public class TestCheckItem {

    @Autowired
    private ICheckManual checkManual;


    @Test
    public void saveCheckTest(){
        List<CheckLevel> list = new  ArrayList<>();
         list.add(new CheckLevel(7927,37097,"1","原料仓库","卸货","现场管理","应急照明"));
         list.add(new CheckLevel(7927,37097,"1","反应釜","卸货","现场管理","应急照明"));
         list.add(new CheckLevel(7927,37097,"1","车间","卸货","现场管理","应急照明"));
         list.add(new CheckLevel(7927,37097,"1","应用车间","卸货","现场管理","应急照明"));




        // 创建一个对象
        CheckItem checkItem = new CheckItem();
        checkItem.setTemplate("随机检查表1");
        checkItem.setTitle(1);
        checkItem.setCheckLevels(list);

        ZzjgPersonnel zzjg = new ZzjgPersonnel();


        checkManual.saveCheck(checkItem,  zzjg);



    }



}
