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

    /**
     * 检查参照
     */
    public String reference;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLevel4() {
        return level4;
    }

    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
