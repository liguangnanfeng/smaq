package com.junit.cn.smaq.test;

import com.spring.web.util.SmsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/16 09:02
 * 测试短信模版
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/applicationContext.xml", /*"file:WebContent/WEB-INF/ehcache.xml",*/"file:WebContent/WEB-INF/spring-servlet.xml"})

public class SmsTest {

   @Autowired
   private SmsUtil smsUtil;

/*    @Test
    public void test() throws HTTPException, IOException {

        String[] params ={"111112"};

        SmsMultiSender msender = new SmsMultiSender(Integer.parseInt(appid), appkey);

        SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers, templateId, params, "", "", "");
        System.out.println(result);
    }*/

    /**
     * 修改服务
     */
    @Test
    public void Test03(){
        smsUtil.sendSMS("15670382411","111222");

    }

}
