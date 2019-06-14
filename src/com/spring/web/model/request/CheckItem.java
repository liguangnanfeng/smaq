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
     * 企业id
     */
    public Integer uid;


    /**
     * 检查表标题
     */
    public String template;

    /**
     *
     *  检查类型 定期检查,日常检查 临时检查
     */
    public Integer title;

    // 检查方式 1. 企业自查  2 行政检查   3 部门抽查
    public Integer flag;

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
    public String access_token;

    /**
     * 封装的危险点信息
     */
    public List<CheckLevel> checkLevels;

}
