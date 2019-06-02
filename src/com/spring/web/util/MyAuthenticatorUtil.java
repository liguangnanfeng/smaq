package com.spring.web.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @Title: MyAuthenticatorUtil
 * @Description: 权限认证
 * @author FL
 * @date 2016年1月13日 下午2:35:56
 * @version V1.0
 */
public class MyAuthenticatorUtil extends Authenticator {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * @param username
     * @param password
     */
    public MyAuthenticatorUtil(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
