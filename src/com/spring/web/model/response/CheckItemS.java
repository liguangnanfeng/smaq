package com.spring.web.model.response;

import com.spring.web.model.TCheckItem;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 13:55
 * 组合实体类
 *
 */
@Data
public class CheckItemS {

    public Integer checkId;

    public String levle1;

    public List<Map> items;

    public Integer type;



    public String getLevle1() {
        return levle1;
    }

    public void setLevle1(String levle1) {
        this.levle1 = levle1;
    }

    public List<Map> getItems() {
        return items;
    }

    public void setItems(List<Map> items) {
        this.items = items;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
