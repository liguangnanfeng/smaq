/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: RequestListener
 * @Description: 输入监听
 * @author CGF
 * @date 2017年2月13日 下午4:39:32
 * @version V1.0
 */
@Component
public class RequestListener implements ServletRequestListener {
    public void requestInitialized(ServletRequestEvent sre) {
        // 将所有request请求都携带上httpSession
        ((HttpServletRequest) sre.getServletRequest()).getSession();
    }

    public RequestListener() {
    }

    public void requestDestroyed(ServletRequestEvent arg0) {
    }
}