package com.spring.web.service.CheckCompany.impl;

import com.spring.web.dao.ZzjgDepartmentMapper;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.service.CheckCompany.Zzig_departmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 08:24
 * TODO 查询zzjgDepartment
 */
@Service
public class Zzig_departmentImpl implements Zzig_departmentService {

    @Autowired
    private ZzjgDepartmentMapper zzjgDepartmentMapper;

    /**
     * TODO 根据分公司id查询所有的的分公司
     * @param companyId
     * @return
     */
    @Override
    public List<ZzjgDepartment> selectDepartmentByCid(Integer companyId) {

        List<ZzjgDepartment> list =  zzjgDepartmentMapper.selectDepartmentByCompidAndLEvel(companyId);


        return list;
    }
}
