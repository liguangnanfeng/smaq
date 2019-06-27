package com.spring.web.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/06 08:39
 * 检查项组合表
 */
@Data
public class SaveDataMessageItem {

    /**
     * 企业的id
     */
    public Integer uid;

    /**
     * 检查表id
     */
    public Integer checkId;

    public String access_token;

    public String sessionId;

    /**
     * 责任人id
     */
    public Integer zrrId;

    /**
     * 经度
     */
    public BigDecimal longitude;

    /**
     * 维度
     */
    public BigDecimal latitude;


    /**
     * 立即整改/限时整改
     * 为空表示立即整改
     * 不为空表示限期整改
     */
    public String type;

    /**
     * 消息集合
     */
    public List<SaveDataMessage> list;

    public SaveDataMessageItem() {
    }

    public SaveDataMessageItem(Integer uid, Integer checkId, String access_token, String sessionId, Integer zrrId, BigDecimal longitude, BigDecimal latitude, String type, List<SaveDataMessage> list) {
        this.uid = uid;
        this.checkId = checkId;
        this.access_token = access_token;
        this.sessionId = sessionId;
        this.zrrId = zrrId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
        this.list = list;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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
