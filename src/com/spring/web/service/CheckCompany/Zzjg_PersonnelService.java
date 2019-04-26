package com.spring.web.service.CheckCompany;

import com.spring.web.model.ZzjgPersonnel;

/**
 * @author  桃红梨白
 *
 *
 *
 */
public interface Zzjg_PersonnelService {


    ZzjgPersonnel selectPersonnelByNameAndPwd(String username, String password);

    Integer selectCompanyIdByuserId(Integer id);
}
