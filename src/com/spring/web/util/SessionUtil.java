package com.spring.web.util;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.spring.web.model.User;

/**
 * @Title: SessionUtil
 * @Description: Session工具类
 * @author FL
 * @date 2016年1月13日 下午3:17:08
 * @version V1.0
 */
public final class SessionUtil {
    protected static final Logger logger = Logger.getLogger(SessionUtil.class);
    private static final String SESSION_USER = "session_user";
    private static final String SESSION_VALIDATECODE = "validateCode";// 验证码
    private static final String SESSION_ACCESS_URLS = "session_access_urls"; // 系统能够访问的URL
    private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; // 系统菜单按钮

    /**
     * 设置session的值
     * 
     * @param request
     * @param key
     * @param value
     */
    public static void setAttr(HttpServletRequest request, String key, Object value) {
        request.getSession(true).setAttribute(key, value);
    }

    /**
     * 获取session的值
     * 
     * @param request
     * @param key
     * @param value
     */
    public static Object getAttr(HttpServletRequest request, String key) {
        return request.getSession(true).getAttribute(key);
    }

    /**
     * 删除Session值
     * 
     * @param request
     * @param key
     */
    public static void removeAttr(HttpServletRequest request, String key) {
        request.getSession(true).removeAttribute(key);
    }

    /**
     * 设置用户信息 到session
     * 
     * @param request
     * @param user
     */
    public static void setUser(HttpServletRequest request, User user) {
        request.getSession(true).setAttribute(SESSION_USER, user);
    }

    /**
     * 从session中获取用户信息
     * 
     * @param request
     * @return SysUser
     */
    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession(true).getAttribute(SESSION_USER);
    }

    /**
     * 从session中获取用户信息
     * 
     * @param request
     * @return SysUser
     */
    public static void removeUser(HttpServletRequest request) {
        removeAttr(request, SESSION_USER);
    }

    /**
     * 设置验证码 到session
     * 
     * @param request
     * @param user
     */
    public static void setValidateCode(HttpServletRequest request, String validateCode) {
        request.getSession(true).setAttribute(SESSION_VALIDATECODE, validateCode);
    }

    /**
     * 从session中获取验证码
     * 
     * @param request
     * @return SysUser
     */
    public static String getValidateCode(HttpServletRequest request) {
        return (String) request.getSession(true).getAttribute(SESSION_VALIDATECODE);
    }

    /**
     * 从session中获删除验证码
     * 
     * @param request
     * @return SysUser
     */
    public static void removeValidateCode(HttpServletRequest request) {
        removeAttr(request, SESSION_VALIDATECODE);
    }

    /**
     * 判断当前登录用户是否超级管理员
     * 
     * @param request
     * @return
     */
    public static void setAccessUrl(HttpServletRequest request, List<String> accessUrls) { // 判断登录用户是否超级管理员
        setAttr(request, SESSION_ACCESS_URLS, accessUrls);
    }

    /**
     * 设置菜单按钮
     * 
     * @param request
     * @param btnMap
     */
    @SuppressWarnings("rawtypes")
    public static void setMemuBtnMap(HttpServletRequest request, Map<String, List> btnMap) { // 判断登录用户是否超级管理员
        setAttr(request, SESSION_MENUBTN_MAP, btnMap);
    }

    /**
     * 获取菜单按钮
     * 
     * @param request
     * @param btnMap
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<String> getMemuBtnListVal(HttpServletRequest request, String menuUri) { // 判断登录用户是否超级管理员
        Map btnMap = (Map) getAttr(request, SESSION_MENUBTN_MAP);
        if (btnMap == null || btnMap.isEmpty()) {
            return null;
        }
        return (List<String>) btnMap.get(menuUri);
    }
}