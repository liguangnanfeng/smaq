/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.web.filter;

import com.spring.web.dao.UserMapper;
import com.spring.web.model.User;
import com.spring.web.util.ConstantsUtil;
import com.spring.web.util.CookieUtil;
import com.spring.web.util.EncryptUtil;
import com.spring.web.util.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: LoginFilter
 * @Description: 登录过滤
 * @author FL
 * @date 2017年3月21日 上午9:05:58
 * @version V1.0
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Autowired
    private UserMapper userMapper;

    /**
     * 微信端未登录拦截 跳转到登录页面
     */
    @Override
    public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) request1;
        HttpServletResponse response = (HttpServletResponse) response1;
        User user = SessionUtil.getUser((HttpServletRequest) request);
        if (user == null) { // 前台自动登录
            String userName = CookieUtil.getCookie(request, ConstantsUtil.COOKIE_USERNAEM);
            String validate = CookieUtil.getCookie(request, ConstantsUtil.COOKIE_VALIDATE);
            if (!StringUtils.isEmpty(validate)) {
                try {
                    User user2 = userMapper.selectUserByUserName(userName);
                    if (user2 != null && user2.getUserType().intValue() == 2
                            && EncryptUtil.match(validate, user2.getUserName() + ";" + user2.getId())) {
                        String autoLoginName = ConstantsUtil.NO_PASSWORD_LOGIN2;
                        UsernamePasswordToken token = new UsernamePasswordToken(autoLoginName,
                                ConstantsUtil.NO_PASSWORD_LOGIN_P);
                        token.setRememberMe(true);
                        Subject currentUser = SecurityUtils.getSubject();
                        currentUser.logout();
                        currentUser.login(token);
                        SessionUtil.setUser(request, user2);
                        CookieUtil.setCookie(response, ConstantsUtil.COOKIE_USERNAEM, user2.getUserName(),
                                30 * 24 * 60 * 60);
                        CookieUtil.setCookie(response, ConstantsUtil.COOKIE_VALIDATE,
                                EncryptUtil.encrypt(user2.getUserName() + ";" + user2.getId()), 7 * 24 * 60 * 60);
                    } else {
                        CookieUtil.setCookie(response, "validate", "", -1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        chain.doFilter(request1, response1);
        return;
    }

    @Override
    public void destroy() {
    }
}