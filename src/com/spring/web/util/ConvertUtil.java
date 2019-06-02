/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.util;

import com.spring.web.exception.LlSystemException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Title: ConvertUtil
 * @Description: 转换工具类
 * @author FL
 * @date 2016年1月12日 下午4:30:25
 * @version V1.0
 */
public class ConvertUtil {
    private Logger log = Logger.getLogger(ConvertUtil.class);
    public static final String setMethodModify = "set";

    /**
     * map->bean的转换
     * 
     * @param map
     * @param obj
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Object convert2Bean(Map<String, Object> map, Object obj) throws Exception {
        Class class1 = obj.getClass();
        Method[] methods = class1.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith(ConvertUtil.setMethodModify)) {
                String propertyName = methodName.substring(3);
                Object value = map.get(propertyName);
                try {
                    method.invoke(obj, value);
                } catch (IllegalArgumentException e) {
                    log.error(StackTraceUtil.getStackTrace(e));
                    throw new LlSystemException(MessageConstantsUtil.SYSTEM_EX);
                } catch (IllegalAccessException e) {
                    log.error(StackTraceUtil.getStackTrace(e));
                    throw new LlSystemException(MessageConstantsUtil.SYSTEM_EX);
                } catch (InvocationTargetException e) {
                    log.error(StackTraceUtil.getStackTrace(e));
                    throw new LlSystemException(MessageConstantsUtil.SYSTEM_EX);
                }
            }
        }
        return obj;
    }

    /**
     * 字符串转换成Set
     * 
     * @param s1 逗号隔开的字符串
     * @return set
     */
    public static Set<String> getSetFrom(String s1) {
        Set<String> set = new HashSet<String>();
        if (StringUtils.isNotBlank(s1)) {
            for (String s : s1.split(",")) {
                set.add(s);
            }
        }
        return set;
    }

    /**
     * 由hashSet转化为String
     * 
     * @param s1 set1
     * @param s2 set2
     * @return string
     */
    @SuppressWarnings("rawtypes")
    public static String mergeStringFromSet(Set<String> s1, Set<String> s2) {
        StringBuilder sBuilder = new StringBuilder();
        if (s1 != null && s2 != null) {
            s1.addAll(s2);
        }
        for (Iterator iter = s1.iterator(); iter.hasNext();) {
            sBuilder.append(iter.next()).append(",");
        }
        if (sBuilder.length() > 0) {
            sBuilder = sBuilder.deleteCharAt(sBuilder.length() - 1);
        }
        return sBuilder.toString();
    }

    /**
     * 由list转换为string
     * 
     * @param list
     * @return
     */
    public static String getStringFromList(List<String> list) {
        StringBuilder sBuilder = new StringBuilder();
        for (String s : list) {
            sBuilder.append(s).append(",");
        }
        if (sBuilder.length() > 0) {
            sBuilder = sBuilder.deleteCharAt(sBuilder.length() - 1);
        }
        return sBuilder.toString();
    };

    /**
     * 区分字母与数字返回数组
     * 
     * @param str
     * @return array
     */
    public static String[] getArrayFromAlphbetAndDigit(String str) {
        String[] strs = str.split("((?<=[0-9])(?=[a-zA-Z]))|((?=[0-9])(?<=[a-zA-Z]))");
        return strs;
    }

    /**
     * 从用逗号隔开的字符串中移除指定字符串
     * 
     * @param str
     * @param removeStr
     * @return
     */
    public static String stringsRemoveString(String str, String removeStr) {
        String newStr = "";
        List<String> list = new ArrayList<String>(Arrays.asList(str.split(",")));
        if (list.contains(removeStr)) {
            list.remove(removeStr);
        }
        String[] midStr = new String[list.size()];
        list.toArray(midStr);
        newStr = StringUtils.join(midStr, ",");
        return newStr;
    }
}
