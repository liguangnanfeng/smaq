package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Table5;

public interface Table5Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table5_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table5_tbl
     *
     * @mbggenerated
     */
    int insert(Table5 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table5_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Table5 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table5_tbl
     *
     * @mbggenerated
     */
    Table5 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table5_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Table5 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table5_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Table5 record);

    /** 
    * @param m
    * @return 
    */
    List<DynamicParameter<String, Object>> selectTable(Map<String, Object> m);

    /** 
    * @param isType
    * @return 
    */
    Table5 selectByIsType(Integer isType);
}