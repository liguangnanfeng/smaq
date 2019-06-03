package com.spring.web.util;

import java.util.LinkedList;
import java.util.List;

/**
 * @Title: StringOperUtil
 * @Description: 字符串处理工具类
 * @author FL
 * @date 2016年1月13日 下午3:23:21
 * @version V1.0
 */
public class StringOperUtil {
    /**
     * 分隔字符串数组
     * 
     * @param strs 源字符串数组
     * @param countMax 分隔一段最大数量,为null时,默认20条
     * @return
     */
    public List<String[]> splitArray(String[] strs, Integer countMax) {
        Integer index;
        List<String[]> list = new LinkedList<String[]>();
        if (strs.length <= 0) {
            return list;
        }
        if (countMax.intValue() <= 0 || countMax == null) {
            countMax = 20;
        }
        Integer result = strs.length % countMax;
        if (result.intValue() != 0) {
            String[] temp = new String[result];
            System.arraycopy(strs, strs.length - result, temp, 0, result);
            list.add(temp);
        }
        for (int i = 0; i < strs.length / countMax; i++) {
            String[] temp = new String[countMax];
            index = i * countMax;
            System.arraycopy(strs, index, temp, 0, countMax);
            list.add(temp);
            System.arraycopy(strs, 0, strs, 0, strs.length - index);
        }
        return list;
    }
}
