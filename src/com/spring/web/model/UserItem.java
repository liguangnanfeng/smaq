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

    public String token;

    public ZzjgPersonnel zzjgPersonnel;

}
