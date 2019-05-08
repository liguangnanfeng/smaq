package com.spring.web.service.TRectiflcationConfirm;

import com.spring.web.model.TRectificationConfirm;

import java.util.List;

public interface TRectificationConfirmService {

    void saveTRectificationConfirm(TRectificationConfirm confirm);

    List<TRectificationConfirm> findTRectificationConfirm(String userId);

}
