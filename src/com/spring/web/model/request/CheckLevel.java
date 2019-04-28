package com.spring.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.logging.Level;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/26 10:20
 * TODO 查询的风险点 对数据进行统一的封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckLevel implements Serializable {

    /**
     * 对应的company_manual表主键id
     */
    public Integer id;

    /**
     *  对应总公司的id
     */
    public Integer uid;

    /**
     * 不同的查询方式
     */
    public String type;

    /**
     * 一级(部门)
     */
    public String level1;

    /**
     * 二级(岗位)
     */
    public String level2;

    /**
     * 三级(风险点)
     */
    public String level3;

    /**
     * 四级(检查内容)
     */
    public String level4;



}
