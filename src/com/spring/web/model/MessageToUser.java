package com.spring.web.model;

import java.io.Serializable;
import java.util.Date;

public class MessageToUser implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String id;

    private String messageId;

    private Integer userId;

    private Byte reviceFlag;

    private Byte readFlag;

    private Date readTime;
    
    private Byte delFlag;
    
    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getReviceFlag() {
        return reviceFlag;
    }

    public void setReviceFlag(Byte reviceFlag) {
        this.reviceFlag = reviceFlag;
    }

    public Byte getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Byte readFlag) {
        this.readFlag = readFlag;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}