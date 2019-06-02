package com.spring.web.model;

import java.io.Serializable;

public class UserGroupList implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String id;

    private String groupId;

    private Integer groupUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public Integer getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(Integer groupUserId) {
        this.groupUserId = groupUserId;
    }
}