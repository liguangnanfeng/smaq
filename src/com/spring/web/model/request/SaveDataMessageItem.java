package com.spring.web.model.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/06 08:39
 * 检查项组合表
 */
@Data
public class SaveDataMessageItem {
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
     *
     */

    /**
     * 立即整改/限时整改
     *  为空表示立即整改
     *  不为空表示限期整改
     */
    public String type;

    public List<SaveDataMessage> list;

}
