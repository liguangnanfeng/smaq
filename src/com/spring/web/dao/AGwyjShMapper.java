package com.spring.web.dao;

import java.util.List;
import com.spring.web.model.AGwyjSh;

public interface AGwyjShMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gwyj_sh_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gwyj_sh_tbl
     *
     * @mbggenerated
     */
    int insert(AGwyjSh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gwyj_sh_tbl
     *
     * @mbggenerated
     */
    int insertSelective(AGwyjSh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gwyj_sh_tbl
     *
     * @mbggenerated
     */
    AGwyjSh selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gwyj_sh_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AGwyjSh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gwyj_sh_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AGwyjSh record);
    
    List<AGwyjSh> selectAll();
}