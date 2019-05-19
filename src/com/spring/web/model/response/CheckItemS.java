package com.spring.web.model.response;

import com.spring.web.model.TCheckItem;
import lombok.Data;

import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 13:55
 * 组合实体类
 *
 */
@Data
public class CheckItemS {

    public String levle1;

    public List<TCheckItem> items;

    public Integer type;



    public String getLevle1() {
        return levle1;
    }

    public void setLevle1(String levle1) {
        this.levle1 = levle1;
    }

    public List<TCheckItem> getItems() {
        return items;
    }

    public void setItems(List<TCheckItem> items) {
        this.items = items;
    }
}
