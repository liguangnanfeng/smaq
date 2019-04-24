package com.spring.web.service.CheckCompany.impl;


import com.spring.web.dao.ZzjgPersonnelMapper;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 桃红梨白
 * TODO 根据用户Id进行部门的查询
 */
@Service
public class Zzjg_PersonnelServiceImpl implements Zzjg_PersonnelService {

    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;


    /**
     *  根据用户ID查询所属的一级公司 在根据一级公司查询所属的二级公司
     *  Uid => 查询的总公司
     * @param userId
     * @return
     */
    @Override
    public Integer selectCompanyIdByuserId(Integer userId) {
        ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectById(userId);
        if(null != zzjgPersonnel ){
            return zzjgPersonnel.getUid();
        }
        return null;
    }
}
