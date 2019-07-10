package com.spring.web.dao;

import java.util.List;
import com.spring.web.model.ADangerType;

public interface ADangerTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_type_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_type_tbl
     *
     * @mbggenerated
     */
    int insert(ADangerType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_type_tbl
     *
     * @mbggenerated
     */
    int insertSelective(ADangerType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_type_tbl
     *
     * @mbggenerated
     */
    ADangerType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_type_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ADangerType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_type_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ADangerType record);
    
    List<String> selectAll();

    List<ADangerType> findAll();
}