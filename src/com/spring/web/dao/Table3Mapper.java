package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Table3;

public interface Table3Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table3_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table3_tbl
     *
     * @mbggenerated
     */
    int insert(Table3 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table3_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Table3 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table3_tbl
     *
     * @mbggenerated
     */
    Table3 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table3_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Table3 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table3_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Table3 record);

    /** 
    * @param m
    * @return 
    */
    List<DynamicParameter<String, Object>> selectTable(Map<String, Object> m);
}