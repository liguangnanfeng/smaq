package com.spring.web.service.CheckCompany;

import com.spring.web.model.ZzjgPersonnel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author  桃红梨白
 *
 *
 *
 */
public interface Zzjg_PersonnelService {


    ZzjgPersonnel selectPersonnelByNameAndPwd(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    Integer selectCompanyIdByuserId(Integer id);
}
