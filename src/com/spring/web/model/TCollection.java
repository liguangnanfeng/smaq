package com.spring.web.model;

import java.io.Serializable;
import java.util.Date;

public class TCollection implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String deviceSn;

    private String sensorSn;

    private Float collect;

    private Date colltime;

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

    public Float getCollect() {
        return collect;
    }

    public void setCollect(Float collect) {
        this.collect = collect;
    }

    public Date getColltime() {
        return colltime;
    }

    public void setColltime(Date colltime) {
        this.colltime = colltime;
    }
}