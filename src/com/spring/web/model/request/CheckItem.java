package com.spring.web.model.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/27 17:01
 * 组合实体类
 * 封装前端传递过来的模版表信息
 */
@Data
public class CheckItem {
    /**
     * 检查表信息
     */
    public String template;

    /**
     * 检查类型
     */
    public Integer title;

    /**
     * 身份验证 token
     */

    public String token;


    /**
     * 封装的危险点信息
     */
    public List<CheckLevel> checkLevels;


}
