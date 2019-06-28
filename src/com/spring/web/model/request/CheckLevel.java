package com.spring.web.model.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

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
     * 对应总公司的id
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

    /**
     * 较大的危险因素
     */
    public String factors;

    /**
     * 易发生的事故类型,风险名称
     */
    public String types;

    /**
     * 较大重大的风险点
     */
    public String flag;

    /**
     * 管控措施
     */
    public String gkcs;

    /**
     * 管控主体
     */
    public String gkzt;

    /**
     * 图片路径
     */
    public String files;

    /**
     * 不同的检查内容1. 基础  2. 现场  3. 高危
     */
    public Integer checkType;

    /**
     * 行业id
     */
    public Integer industryId;

    /*令牌*/
    public String access_token;
    /*sessionId*/
    public String sessionId;


    public CheckLevel() {
    }

    public CheckLevel(Integer id, Integer uid, String type, String level1, String level2, String level3, String level4, String reference, String factors, String types, String flag, String gkcs, String gkzt, String files, Integer checkType, Integer industryId, String access_token, String sessionId) {
        this.id = id;
        this.uid = uid;
        this.type = type;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
        this.reference = reference;
        this.factors = factors;
        this.types = types;
        this.flag = flag;
        this.gkcs = gkcs;
        this.gkzt = gkzt;
        this.files = files;
        this.checkType = checkType;
        this.industryId = industryId;
        this.access_token = access_token;
        this.sessionId = sessionId;
    }

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

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGkcs() {
        return gkcs;
    }

    public void setGkcs(String gkcs) {
        this.gkcs = gkcs;
    }

    public String getGkzt() {
        return gkzt;
    }

    public void setGkzt(String gkzt) {
        this.gkzt = gkzt;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
