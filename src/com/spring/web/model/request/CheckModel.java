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
    public String token;
}
