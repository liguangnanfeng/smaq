package com.spring.web.util;

import java.io.Serializable;

/**
 * @Title: TokenUtil
 * @Description: 令牌工具类
 * @author FL
 * @date 2017年7月10日 下午1:38:21
 * @version V1.0
 */
public class TokenUtil implements Serializable {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = -8248822386098548933L;
    private static String cons = "repit_cscfbcf";

    public static String setToken(javax.servlet.http.HttpServletRequest request) {
        javax.servlet.http.HttpSession session = request.getSession();
        String token = String.valueOf(java.util.UUID.randomUUID()) + String.valueOf(System.currentTimeMillis());
        session.setAttribute("repit_cscfbcf", token);
        String html = "<input type=\"hidden\" name=\"repit_cscfbcf\" value='" + token + "'/>";
        return html;
    }

    public static boolean isToken(javax.servlet.http.HttpServletRequest request) {
        javax.servlet.http.HttpSession session = request.getSession();
        String token = (String) session.getAttribute(cons);
        if (token == null)
            token = "";
        String tv = request.getParameter(cons);
        session.removeAttribute(cons);
        return token.equals(tv);
    }
}