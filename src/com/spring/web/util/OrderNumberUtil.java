/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Title: OrderNumberUtil
 * @Description:订单号工具类
 * @author FL
 * @date 2016年8月30日 下午4:14:19
 * @version V1.0
 */
public class OrderNumberUtil {
    /**
     * 获取订单订单号
     */
    public static String getOrdersNumber() {
        String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        String[] beforeShuffle = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        List<?> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(4, 6);
        return date.substring(2, date.length() - 1) + result;
        /*
         * long timemillis = System.currentTimeMillis(); return String.valueOf(timemillis).substring(1);
         */
    }

    public static void main(String[] args) {
        System.out.println(getOrdersNumber());
    }
}
