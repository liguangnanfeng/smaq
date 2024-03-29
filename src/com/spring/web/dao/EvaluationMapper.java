package com.spring.web.dao;

import java.util.List;
import java.util.Map;

import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Evaluation;

public interface EvaluationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evaluation_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evaluation_tbl
     *
     * @mbggenerated
     */
    int insert(Evaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evaluation_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Evaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evaluation_tbl
     *
     * @mbggenerated
     */
    Evaluation selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evaluation_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Evaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table evaluation_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Evaluation record);
    
    /** 
    * @param m
    * @return 
    */
    List<DynamicParameter<String, Object>> selectEvaluationList(Map<String, Object> m);
    
    /** 
     * @param map
     * @return 
     */
    int insertBatch(Map<String, Object> map);
    
    List<DynamicParameter<String, Object>> selectClique(Map<String, Object> m);
}