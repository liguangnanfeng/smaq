package com.spring.web.service.CheckCompany.impl;

import com.spring.web.dao.CompanyMapper;
import com.spring.web.dao.VillageMapper;
import com.spring.web.service.CheckCompany.CountryCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/08 18:02
 *
 * 小程序政府端进行检查,获取数据
 */
@Service
public class CountryCheckImpl implements CountryCheck {

    /**
     * 企业数据
     */
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 村级数据
     */

    @Autowired
    private VillageMapper villageMapper;


    /**
     * 村级查询企业
     * @param id
     * @return
     */
    @Override
    public List<Map<String,Object>> selectCompanyByVillageId(Integer id) {
        List<Map<String,Object>>  list =  companyMapper.selectByVillageId(id);

        return list;
    }

    /**
     * 根据镇级查询所对应的村
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectVillageBytownId(Integer id) {
        List<Map<String,Object>>  list =villageMapper.selectByTownId(id);

        return list;
    }
}
