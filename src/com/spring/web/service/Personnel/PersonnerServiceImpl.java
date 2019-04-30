package com.spring.web.service.Personnel;

import com.spring.web.dao.PersonnelMapper;
import com.spring.web.model.ZzjgPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PersonnerService")
public class PersonnerServiceImpl implements PersonnerService{

    @Autowired
    private PersonnelMapper personnelMapper;

    @Override
    public ZzjgPersonnel findPersonnel(String userId) {
        return personnelMapper.findPersonnel(userId);
    }
}
