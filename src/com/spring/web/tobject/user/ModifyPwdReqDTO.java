/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.tobject.user;

import com.spring.web.tobject.DTO;

/**
 * @Title: ModifyPwdReqDTO
 * @Description: 修改密码输入DTO
 * @author FL
 * @date 2017年7月10日 上午10:21:54
 * @version V1.0
 */
public class ModifyPwdReqDTO implements DTO {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = -4942857229830246052L;
    /**
     * 旧密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 旧登录名
     */
    private String oldUserName;
    /** 
     * @return oldUserName 
     */
    public String getOldUserName() {
        return oldUserName;
    }

    /** 
     * @param oldUserName 要设置的 oldUserName 
     */
    public void setOldUserName(String oldUserName) {
        this.oldUserName = oldUserName;
    }

    /** 
     * @return newUserName 
     */
    public String getNewUserName() {
        return newUserName;
    }

    /** 
     * @param newUserName 要设置的 newUserName 
     */
    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    /**
     * 新登录名
     */
    private String newUserName;
    
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
     * @return newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword 要设置的 newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
