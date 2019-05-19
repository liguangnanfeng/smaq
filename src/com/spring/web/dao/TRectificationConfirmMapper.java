package com.spring.web.dao;

import com.spring.web.model.TRectificationConfirm;

import java.util.List;
import java.util.Map;

public interface TRectificationConfirmMapper {

    void saveTRectificationConfirm(TRectificationConfirm confirm);

    void updateTRectificationConfirm(TRectificationConfirm confirm);

    List<Map> findTRectificationConfirm(String userId);

    TRectificationConfirm findTRectificationConfirmByItemId(String id);

    TRectificationConfirm findByCheckId(Integer checkId);


    /**
     * 修改数据的方法
     * @param byCheckId
     */
    void updateByTRectificationConfirm(TRectificationConfirm byCheckId);

    /**
     * 根据 checkItem表数据进行查询
     */
    List<TRectificationConfirm> selectByCheckItemId(Integer checkItemId);
}
