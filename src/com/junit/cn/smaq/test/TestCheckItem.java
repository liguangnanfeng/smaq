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
         list.add(new CheckLevel(7927,37097,"2","原料仓库","工厂","11111","22222222","777777"));
         list.add(new CheckLevel(7927,37097,"2","反应釜","工厂","现场管理 / 特种设备（ 压力管道） / 运行情况","不应存在介质泄漏现象。","77777"));
         list.add(new CheckLevel(7927,37097,"1","车间","卸货","现场管理 / 特种设备（ 压力管道） / 运行情况","不应存在介质泄漏现象。","777777"));
         list.add(new CheckLevel(7927,37097,"1","应用车间","卸货","现场管理 / 特种设备（ 压力管道） / 运行情况","不应存在介质泄漏现象。","77777"));



        // 创建一个对象
        CheckItem checkItem = new CheckItem();
        checkItem.setTemplate("随机检查表1");
        checkItem.setTitle(2);
        checkItem.setCheckType(2);
        checkItem.setCycle(7);
        checkItem.setCheckLevels(list);

        ZzjgPersonnel zzjg = new ZzjgPersonnel();
        zzjg.setId(777777);
        zzjg.setUid(37097);
        zzjg.setMobile("15137970728");
        zzjg.setName("777777");

        Integer integer = checkManual.saveCheck(checkItem, zzjg);
        System.out.println(integer);

    }

}
