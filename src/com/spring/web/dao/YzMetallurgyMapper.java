package com.spring.web.dao;

import java.util.List;

import com.spring.web.model.YzLiquidAmmonia;
import com.spring.web.model.YzMetallurgy;

public interface YzMetallurgyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yz_metallurgy_permit_t
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yz_metallurgy_permit_t
     *
     * @mbggenerated
     */
    int insert(YzMetallurgy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yz_metallurgy_permit_t
     *
     * @mbggenerated
     */
    int insertSelective(YzMetallurgy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yz_metallurgy_permit_t
     *
     * @mbggenerated
     */
    YzMetallurgy selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yz_metallurgy_permit_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(YzMetallurgy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yz_metallurgy_permit_t
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(YzMetallurgy record);
    
    List<YzMetallurgy> selectByUid(Integer uid);
    
    Integer selectCount(List<Integer> m);
    
    List<Integer> selectUidsByList(List<Integer> m);
}