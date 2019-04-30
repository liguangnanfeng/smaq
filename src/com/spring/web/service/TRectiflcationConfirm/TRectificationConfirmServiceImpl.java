package com.spring.web.service.TRectiflcationConfirm;

import com.spring.web.dao.TRectificationConfirmMapper;
import com.spring.web.model.TRectificationConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TRectificationConfirmService")
public class TRectificationConfirmServiceImpl implements TRectificationConfirmService{

    @Autowired
    private TRectificationConfirmMapper tRectificationConfirmMapper;

    @Override
    public void saveTRectificationConfirm(TRectificationConfirm confirm) {
        tRectificationConfirmMapper.saveTRectificationConfirm(confirm);
    }

    @Override
    public List<TRectificationConfirm> findTRectificationConfirm(String userId) {
        return tRectificationConfirmMapper.findTRectificationConfirm(userId);
    }
}
