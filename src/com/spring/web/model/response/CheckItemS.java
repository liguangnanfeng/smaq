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

    public Integer type;

    public List<TCheckItem> items;


}
