package com.spring.web.service.CheckCompany;

import com.spring.web.model.Officials;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.SaveDataMessageItem;

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

    /**
     * 根据区级id查询所有的镇
     * @param id
     * @return
     */
    List<Map<String, Object>> selectTownByDistrictId(Integer id);

    /**
     * 查询所有的区
     */
    List<Map<String, Object>> selectDistrict();

    /**
     * 根据政府端传递的查询条件进行数据的包存并返回一个模版id
     * @param checkItem
     * @param officials
     * @return
     */
    Integer saveCheck(CheckItem checkItem, Officials officials,Integer id);

    /**
     * 政府账号保存检查信息
     * @param saveDataMessageItem
     * @param officials
     * @param id
     * @return
     */
    String saveCheckMessage(SaveDataMessageItem saveDataMessageItem, Officials officials, Integer id);

    /**
     * 查询政府端登录用户的详细信息
     * @param id
     * @param flag
     * @return
     */
    Map selectParticular(Integer id, Integer flag);

    /**
     * 政府端获取检查记录
     * @return
     */
    List findRecordByCreateUser(Integer id);


}
