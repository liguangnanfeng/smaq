package com.spring.web.service.CheckCompany;

import com.spring.web.model.ZzjgDepartment;

import java.util.List;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 08:23
 */
public interface Zzig_departmentService {
    /**
     * 根据公司id查询所有的部门
     * @param companyId
     * @return
     */
    List<ZzjgDepartment> selectDepartmentByCid(Integer companyId);

    /**
     *
     * @param companyId
     * @param depId
     * @return
     */
    List<ZzjgDepartment> selectDepByCompanyIdandSome(Integer companyId,Integer depId);
}
