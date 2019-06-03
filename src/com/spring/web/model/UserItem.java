package com.spring.web.model;

import lombok.Data;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/27 09:43
 * 登陆组合类
 */
@Data
public class UserItem {
    /**
     * 身份判定
     */
    public  Integer type;

    /**
     * token信息
     */
    public String token;

    /**
     * session id
     */
    public String sessionId;

    public ZzjgPersonnel zzjgPersonnel;


    public User user;

}
