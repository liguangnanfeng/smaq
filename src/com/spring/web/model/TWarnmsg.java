package com.spring.web.model;

import java.io.Serializable;
import java.util.Date;

public class TWarnmsg implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String deviceSn;

    private String sensorSn;

    private Byte warn;

    private Date warntime;

    private Date cleantime;

    private String operator;

    private Integer creator;

    private Date createTime;

    private Integer updateName;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn == null ? null : deviceSn.trim();
    }

    public String getSensorSn() {
        return sensorSn;
    }

    public void setSensorSn(String sensorSn) {
        this.sensorSn = sensorSn == null ? null : sensorSn.trim();
    }

    public Byte getWarn() {
        return warn;
    }

    public void setWarn(Byte warn) {
        this.warn = warn;
    }

    public Date getWarntime() {
        return warntime;
    }

    public void setWarntime(Date warntime) {
        this.warntime = warntime;
    }

    public Date getCleantime() {
        return cleantime;
    }

    public void setCleantime(Date cleantime) {
        this.cleantime = cleantime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateName() {
        return updateName;
    }

    public void setUpdateName(Integer updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}