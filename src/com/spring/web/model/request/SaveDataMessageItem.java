package com.spring.web.model.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/06 08:39
 * 检查项组合表
 *
 */
@Data
public class SaveDataMessageItem {
    /**
     * 检查表id
     */
    public String checkId;

    public String access_token;

    public String sessionId;

    /**
     * 责任人id
     */
    public Integer zrrId;

    /**
     * 立即整改/限时整改
     *  为空表示立即整改
     *  不为空表示限期整改
     */
    public String type;

    public List<SaveDataMessage> list;

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
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

    public Integer getZrrId() {
        return zrrId;
    }

    public void setZrrId(Integer zrrId) {
        this.zrrId = zrrId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SaveDataMessage> getList() {
        return list;
    }

    public void setList(List<SaveDataMessage> list) {
        this.list = list;
    }
}
