package com.spring.web.service.CheckCompany;

import java.util.List;
import java.util.Map;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/08 18:02
 * 政府端检查获取数据
 */
public interface CountryCheck {

    /**
     * 村级查询企业
     * @param id
     * @return
     */
    List<Map<String,Object>> selectCompanyByVillageId(Integer id);

    /**
     * 根据镇级查询所有的村
     * @param id
     * @return
     */
    List<Map<String, Object>> selectVillageBytownId(Integer id);
}
