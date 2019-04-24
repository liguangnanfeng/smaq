/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: CookieUtil
 * @Description: cookie工具类
 * @author CGF
 * @date 2015-1-14 下午4:46:49
 * @version V1.0
 */
public class CookieUtil {
    /**
     * 获取cookie值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (name.equalsIgnoreCase(cookie.getName().trim())) {
                    return cookie.getValue().trim();
                }
            }
        }
        return "";
    }

    /**
     * 设置cookie
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int time) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(time);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
