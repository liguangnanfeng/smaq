package com.spring.web.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 12:58
 * 企业检查者 => 选择企业之后,发送给后台的数据的pojo
 */
@Data
public class Department implements Serializable {
    public Integer uid;
    public List<String> names;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
