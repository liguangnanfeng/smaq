package com.spring.web.service.TRectiflcationConfirm;

import com.spring.web.model.TRectificationConfirm;

import java.util.List;
import java.util.Map;

public interface TRectificationConfirmService {

    void saveTRectificationConfirm(TRectificationConfirm confirm);

    List<Map> findTRectificationConfirm(String userId);

}
