package com.junit.cn.smaq.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.dao.ImportPhotoMapper;
import com.spring.web.model.request.ImportPhoto;
import com.spring.web.util.SmsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        smsUtil.sendSMS("17516470884","111222");

    }
@Autowired
private ImportPhotoMapper importPhotoMapper;


    @Test
    public void Test04(){
        List<ImportPhoto> importPhotos = importPhotoMapper.selectPhoto(37097);
        // 不是就表示有数据
        for (ImportPhoto importPhoto : importPhotos) {
            String coordinate = importPhoto.getCoordinate();
            String s = coordinate.replaceAll("\'", "");
            Map<String,String> map = new LinkedHashMap<String,String>();
            String[] array = s.split(",");
            for (String s1 : array) {
                String[] split = s1.split(":");
                map.put(split[0],split[1]);
            }

            System.out.println(map);

        }
    }

}
