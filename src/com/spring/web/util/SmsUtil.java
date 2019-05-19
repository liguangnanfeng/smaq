package com.spring.web.util;


import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.spring.web.controller.LoginController;
import org.apache.log4j.Logger;
import org.json.JSONException;

import java.io.IOException;

//import javax.json.Json;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/16 11:02
 */
public  class SmsUtil {



    private final static Logger logger = Logger.getLogger(LoginController.class);
    // 短信应用SDK AppID

    String appid; // 1400开头
    // 短信应用SDK AppKey

    String appkey ;
    // 短信模板ID，需要在短信应用中申请

    int templateId; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    // 签名

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

    public void sendSMS(String phoneNumber,String parem) {

        try { //String[] params = {};//参数，验证码为5678，30秒内填写
            String[] params = {parem};//参数，验证码为123456，100秒内填写
           // SmsSingleSender ssender = new SmsSingleSender(Integer.parseInt(appid), appkey);
            SmsSingleSender ssender = new SmsSingleSender(Integer.parseInt(appid),appkey );
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, templateId, params, "", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            logger.info(result.toString());
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
