package com.junit.cn.smaq.test;

import com.spring.web.dao.TCheckItemMapper;
import com.spring.web.dao.TCheckMapper;
import com.spring.web.model.TCheck;
import com.spring.web.model.TCheckItem;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.service.CheckCompany.SaveMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void test01(){

        List<TCheck> allByUidAndUserId = tCheckMapper.findAllByUidAndUserId(844, 37097);
        System.out.println(allByUidAndUserId);

    }


    /**
     * 测试根据模版id查询模版数据
     */
    @Test
    public void test02(){
        CheckItemS checkItemByModelId = saveMessageService.findCheckItemByModelId(1545);

        System.out.println(checkItemByModelId);


    }

}
