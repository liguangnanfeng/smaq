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

    public CheckItem() {
    }


    public CheckItem(Integer uid, String template, Integer title, Integer flag, Integer checkType, Integer cycle, Integer departmentId, String sessionId, String access_token, List<CheckLevel> checkLevels) {
        this.uid = uid;
        this.template = template;
        this.title = title;
        this.flag = flag;
        this.checkType = checkType;
        this.cycle = cycle;
        this.departmentId = departmentId;
        SessionId = sessionId;
        this.access_token = access_token;
        this.checkLevels = checkLevels;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public List<CheckLevel> getCheckLevels() {
        return checkLevels;
    }

    public void setCheckLevels(List<CheckLevel> checkLevels) {
        this.checkLevels = checkLevels;
    }
}
