package com.spring.web.model.request;

import lombok.Data;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/15 10:13
 * 保存截图功能
 */

@Data
public class TMap {
    public Integer userId;
    public String files;

    public TMap() {
    }

    public TMap(Integer userId, String files) {
        this.userId = userId;
        this.files = files;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
