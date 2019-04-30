package com.spring.web.dao;

import com.spring.web.model.ZzjgPersonnel;

public interface PersonnelMapper {

    // 根据员工id查询所属部门，公司
    ZzjgPersonnel findPersonnel(String userId);

}
