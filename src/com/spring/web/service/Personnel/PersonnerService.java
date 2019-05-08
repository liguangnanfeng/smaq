package com.spring.web.service.Personnel;

import com.spring.web.model.ZzjgPersonnel;

public interface PersonnerService {

    // 根据员工id查询所属部门，公司
    ZzjgPersonnel findPersonnel(String userId);

}
