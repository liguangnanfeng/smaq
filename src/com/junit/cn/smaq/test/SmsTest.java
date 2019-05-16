package com.junit.cn.smaq.test;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.spring.web.model.request.Msg;
import com.spring.web.util.SmsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/16 09:02
 * 测试短信模版
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/applicationContext.xml", /*"file:WebContent/WEB-INF/ehcache.xml",*/"file:WebContent/WEB-INF/spring-servlet.xml"})

public class SmsTest {

    // 短信应用 SDK AppID
    @Value("${javasms.appid}")
    String appid  ; // SDK AppID 以1400开头

    // 短信应用 SDK AppKey
    @Value("${javasms.appkey}")
    String appkey ;

    // 需要发送短信的手机号码
    String[] phoneNumbers = {"17516470884"};

    // 短信模板 ID，需要在短信应用中申请
    @Value("${javasms.template}")
    int templateId; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请

    // 签名
    @Value("${javasms.Sign}")
    String smsSign = "腾讯云"; // NOTE: 签名参数使用的是`签名内容`，

/*    @Test
    public void test() throws HTTPException, IOException {

        String[] params ={"111112"};

        SmsMultiSender msender = new SmsMultiSender(Integer.parseInt(appid), appkey);

        SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers, templateId, params, "", "", "");
        System.out.println(result);
    }*/

    @Test
    public void Test03(){
        SmsUtil smsUtil = new SmsUtil() ;
            smsUtil.sendSMS("17516470884","111222");
    }

}
