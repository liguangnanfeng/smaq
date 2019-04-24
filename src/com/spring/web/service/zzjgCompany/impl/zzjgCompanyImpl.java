package com.spring.web.service.zzjgCompany.impl;

import com.spring.web.dao.ZzjgCompanyMapper;
import com.spring.web.dao.ZzjgDepartmentMapper;
import com.spring.web.dao.ZzjgPersonnelMapper;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.User;
import com.spring.web.model.ZzjgCompany;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.service.zzjgCompany.IzzjgCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * IzzjgCompany对应的接口实现类
 */
@Service
public class zzjgCompanyImpl implements IzzjgCompanyService {

    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;

    @Autowired
    private ZzjgCompanyMapper zzjgCompanyMapper;

    @Autowired
    private ZzjgDepartmentMapper zzjgDepartmentMapper;

    @Override
    public List<ZzjgCompany> selectAll(Integer id) {
        return zzjgCompanyMapper.selectAll(id);
    }


    @Override
    public List<LlHashMap<Object, Object>> selectByMap(LlHashMap<Object, Object> m) {
        return zzjgPersonnelMapper.selectByMap(m);
    }

    @Override
    public List<ZzjgDepartment> selectByCompanyId(ZzjgDepartment dep) {
        return zzjgDepartmentMapper.selectByCompanyId(dep);
    }
}
