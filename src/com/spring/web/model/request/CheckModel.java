package com.spring.web.model.request;

import lombok.Data;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 16:46
 * 获取模版id
 */
@Data
public class CheckModel {
    public Integer modelId;
    public String sessionId;
    public String access_token;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
