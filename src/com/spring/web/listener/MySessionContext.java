package com.spring.web.listener;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/29 10:44
 * 创建session监听机制
 */
public class MySessionContext {
    private static MySessionContext instance;
    private HashMap<String, HttpSession> sessionMap;

    // 懒汉式
    private MySessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    // 当需要他时,判断为空的时候在创建
    public static MySessionContext getInstance() {
        if (instance == null) {
            instance = new MySessionContext();
        }
        return instance;
    }

    // session不为null的时候,表示已经有map集合,直接添加session数据
    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }

    }

    // 删除session
    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }


}
