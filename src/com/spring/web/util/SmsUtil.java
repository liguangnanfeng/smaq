package com.spring.web.util;


import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import com.spring.web.model.request.Msg;

//import javax.json.Json;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/16 11:02
 */
public  class SmsUtil {

    // 短信应用SDK AppID
    @Value("${javasms.appid}")
    String appid; // 1400开头
    // 短信应用SDK AppKey
    @Value("${javasms.appkey}")
    String appkey ;
    // 短信模板ID，需要在短信应用中申请
    @Value("${javasms.template}")
    int templateId; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    // 签名
    @Value("${javasms.Sign}")
    String smsSign; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public void setSmsSign(String smsSign) {
        this.smsSign = smsSign;
    }

    public SmsUtil() {
    }



    public void sendSMS(String phoneNumber,String parem) {

        try { //String[] params = {};//参数，验证码为5678，30秒内填写
            String[] params = {parem};//参数，验证码为123456，100秒内填写
           // SmsSingleSender ssender = new SmsSingleSender(Integer.parseInt(appid), appkey);
            SmsSingleSender ssender = new SmsSingleSender(1400155854,"805382a8b97561a3f8c329d96e28fcf4" );
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, 249693, params, "", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            /*logger.info(result.toString());*/
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();

        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

}
