package com.spring.web.dao;

import com.spring.web.model.TRectificationConfirm;

import java.util.List;
import java.util.Map;

public interface TRectificationConfirmMapper {

    void saveTRectificationConfirm(TRectificationConfirm confirm);

    List<Map> findTRectificationConfirm(String userId);

}
