package com.spring.web.service.CheckCompany.impl;


import com.spring.web.dao.ZzjgPersonnelMapper;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 桃红梨白
 * TODO 进行登陆
 * TODO 根据用户Id进行响应的数据的查询
 */
@Service
public class Zzjg_PersonnelServiceImpl implements Zzjg_PersonnelService {

    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;


    /**
     * 进行登陆的的验证工作
     * @param username
     * @param password
     * @return
     */
    @Override
    public ZzjgPersonnel selectPersonnelByNameAndPwd(String username, String password) {
        ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectPersonnelByNameAndPwd(username);


        return zzjgPersonnel;
    }

    /**
     * 根据用户id查询对应的分公司的id
     * @param userId
     * @return
     */
    @Override
    public Integer selectCompanyIdByuserId(Integer userId) {

        ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectById(userId);
        return zzjgPersonnel.getCid();
    }
}
