package com.spring.web.bean;

import java.io.Serializable;

public class AccessTokenBean implements Serializable {
    /**
     * @Fields serialVersionUID
     * ??
     */

    private static final long serialVersionUID = -503761506219547958L;
    /**
     * 令牌
     */
    private String access_token;
    /**
     * 到期时间
     */
    private Integer expires_in;

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