package com.spring.web.service.TRectiflcationConfirm;

import com.spring.web.dao.TRectificationConfirmMapper;
import com.spring.web.model.TRectificationConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TRectificationConfirmService")
public class TRectificationConfirmServiceImpl implements TRectificationConfirmService {

    @Autowired
    private TRectificationConfirmMapper tRectificationConfirmMapper;

    @Override
    public void saveTRectificationConfirm(TRectificationConfirm confirm) {
        tRectificationConfirmMapper.saveTRectificationConfirm(confirm);
    }

    @Override
    public void updateTRectificationConfirm(TRectificationConfirm confirm) {
        tRectificationConfirmMapper.updateTRectificationConfirm(confirm);
    }

    @Override
    public List<Map> findTRectificationConfirm(String userId) {
        return tRectificationConfirmMapper.findTRectificationConfirm(userId);
    }

    @Override
    public TRectificationConfirm findTRectificationConfirmByItemId(String id) {
        return tRectificationConfirmMapper.findTRectificationConfirmByItemId(id);
    }
}
