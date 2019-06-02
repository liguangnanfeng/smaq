package com.spring.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Title: SessionListener
 * @Description: Session监听
 * @author FL
 * @date 2017年7月10日 下午3:01:18
 * @version V1.0
 */
public class SessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent event) {
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        if (null != session.getAttribute("session_user")) {
        }
    }
}