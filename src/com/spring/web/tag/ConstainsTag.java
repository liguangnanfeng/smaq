/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.tag;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/** 
 * @Title: ConstainsTag 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author CGF   
 * @date 2017年9月14日 上午10:43:47 
 * @version V1.0   
 */
public class ConstainsTag {
    
    public static Boolean constains(String value, String key, String s) {
        if(StringUtils.isEmpty(s)) {
            s = ",";
        }
        return Arrays.asList(value.split(s)).contains(key);
    }
    
}
