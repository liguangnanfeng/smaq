package com.junit.cn.smaq.test;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/16 09:02
 * 测试短信模版
 */
public class SmsTest {
    // 短信应用 SDK AppID
    int appid = 1400155854; // SDK AppID 以1400开头

    // 短信应用 SDK AppKey
    String appkey = "805382a8b97561a3f8c329d96e28fcf4";

    // 需要发送短信的手机号码
    String[] phoneNumbers = {"1751647884"};

    // 短信模板 ID，需要在短信应用中申请
    int templateId = 249693; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请

    // 签名
    String smsSign = "腾讯云"; // NOTE: 签名参数使用的是`签名内容`，

    @Test
    public void test() throws HTTPException, IOException {
        SmsMultiSender msender = new SmsMultiSender(appid, appkey);
      //  SmsSingleSenderResult result = msender.sendWithParam("86","17516470224",templateId)

    }



}
