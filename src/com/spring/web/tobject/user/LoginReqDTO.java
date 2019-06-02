package com.spring.web.tobject.user;

import com.spring.web.tobject.DTO;

/**
 * @Title: LoginReqDTO
 * @Description: 用户登录输入DTO
 * @author FL
 * @date 2016年1月21日 上午10:20:14
 * @version V1.0
 */
public class LoginReqDTO implements DTO {
    /**
     * @Fields serialVersionUID : 序列化号
     */
    private static final long serialVersionUID = 5391760749820824240L;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类型
     */
    private String type;
    /**
     * 登录标志 1.前台登录;2.后台登录;3.
     */
    private String flag;
    /**
     * 验证码
     */
    private String validateCode;
    /**
     * 记住我
     */
    private String rememberMe;

    /**
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 要设置的 userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 要设置的 password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 要设置的 type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag 要设置的 flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return validateCode
     */
    public String getValidateCode() {
        return validateCode;
    }

    /**
     * @param validateCode 要设置的 validateCode
     */
    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    /**
     * @return rememberMe
     */
    public String getRememberMe() {
        return rememberMe;
    }

    /**
     * @param rememberMe 要设置的 rememberMe
     */
    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
