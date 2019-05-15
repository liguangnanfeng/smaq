package com.spring.web.model.request;

import lombok.Data;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 18:29
 * 消息传递的组合实体类,对数据进行了封装
 * id :就是风险点的id
 * value  :就是状态 是否合格  1 :合格  2 :不合格
 */
@Data
public class SaveDataMessage {

    public Integer id;
    public String value;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
