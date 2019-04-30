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
     *
     * 检查危险程度
     */
    public Integer title;

    /**
     * 检查类型 定期检查,日常检查 随机检查
     */
    public Integer checkType;

    /**
     * 检查时间
     */

    public Integer cycle;

    /**
     * 对应的负责人id
     */
    public Integer departmentId;


    /**
     * sessionId
     */
    public String SessionId;
    /**
     * 身份验证
     */
    public String token;

    /**
     * 封装的危险点信息
     */
    public List<CheckLevel> checkLevels;


}
