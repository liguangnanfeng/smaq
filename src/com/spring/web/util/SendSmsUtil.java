/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @Title: SendSmsUtil
 * @Description: 短信发送工具类
 * @author FL
 * @date 2016年1月13日 下午3:12:54
 * @version V1.0
 */
@SuppressWarnings("deprecation")
public class SendSmsUtil {
    /**
     * 发送短信
     * 
     * @param id 短信平台ID
     * @param pwd 短信平台密码
     * @param phone 接收短信的手机号
     * @param txt 发送的短信内容
     * @return
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static String getHtmlTxt(String id, String pwd, String[] phone, String txt) throws Exception {
        // 获取短信内容设置GBK编码
        txt = URLEncoder.encode(txt, "GBk");
        List<String> stringList = new ArrayList<String>();
        if (null == phone || phone.length <= 0) {
            throw new RuntimeException("收件人号码不得为空！");
        } else {
            for (int i = 0; i < phone.length; i++) {
                if ((null != phone[i]) && (!"".equals(phone[i])) && (!stringList.contains(phone[i]))) {
                    stringList.add(phone[i]);
                }
            }
        }
        if (null == stringList || stringList.size() <= 0) {
            throw new RuntimeException("收件人号码不得为空！");
        } else {
            // 定义短信链接客户端
            DefaultHttpClient httpclient = new DefaultHttpClient();
            // 定义短信发送URL
            StringBuffer messageUrl = new StringBuffer();
            for (int i = 0; i < stringList.size(); i++) {
                // 拼接发送URl
                messageUrl.append(stringList.get(i) + ",");
                // 设置每100个号码作为一个发送组,直到号码结束
                if ((i + 1) % 100 == 0 || (i + 1) == stringList.size()) {
                    messageUrl.setLength(messageUrl.length() - 1);
                    LoggerUtil.cLog.info(messageUrl);
                    messageUrl
                            .insert(0, "http://service.winic.org/sys_port/gateway/?id=" + id + "&pwd=" + pwd + "&to=");
                    messageUrl.append("&content=" + txt);
                    // 发送短信
                    HttpGet httpGet = new HttpGet(messageUrl.toString());
                    HttpResponse response1 = httpclient.execute(httpGet);
                    try {
                        LoggerUtil.cLog.info(response1.getStatusLine());
                        HttpEntity entity1 = response1.getEntity();
                        EntityUtils.consume(entity1);
                    } finally {
                        httpGet.releaseConnection();
                        messageUrl.setLength(0);
                    }
                }
            }
        }
        return "0";
    }

    /**
     * @param phone
     * @param txt
     * @return 短信发送
     * @throws Exception
     */
    public static String getHtmlTxt(String phone, String txt) throws Exception {
        return "0";
    }

    public static void main(String[] args) {
        String phones[] = {"18952731595"};
        try {
            getHtmlTxt("chidexiao", "lanlinnet123", phones, "老工程！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
