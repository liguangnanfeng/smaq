package com.spring.web.bean;

import java.io.Serializable;

/**
 * @Title: WebAccessToken
 * @Description: 接收AccessToken信息
 * @author FL
 * @date 2017年3月20日 下午7:17:29
 * @version V1.0
 */
public class WebAccessTokenBean implements Serializable {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = -503761506219547958L;
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String openid;
    private String scope;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}