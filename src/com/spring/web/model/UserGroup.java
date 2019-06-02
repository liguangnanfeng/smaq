package com.spring.web.model;

import java.io.Serializable;
import java.util.Date;

public class UserGroup implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String id;

    private String groupName;

    private Integer userId;

    private Date createTime;
    
    private Integer groupType;
    
    private Integer groupVillage;
    
    private Integer groupVillageType;
    

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getGroupVillage() {
        return groupVillage;
    }

    public void setGroupVillage(Integer groupVillage) {
        this.groupVillage = groupVillage;
    }

    public Integer getGroupVillageType() {
        return groupVillageType;
    }

    public void setGroupVillageType(Integer groupVillageType) {
        this.groupVillageType = groupVillageType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}