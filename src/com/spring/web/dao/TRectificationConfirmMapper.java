package com.spring.web.dao;

import com.spring.web.model.TRectificationConfirm;

import java.util.List;

public interface TRectificationConfirmMapper {

    void saveTRectificationConfirm(TRectificationConfirm confirm);

    List<TRectificationConfirm> findTRectificationConfirm(String userId);

}
