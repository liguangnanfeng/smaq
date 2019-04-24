package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import com.spring.web.model.Sequipment;

public interface SequipmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    int insert(Sequipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Sequipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    Sequipment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Sequipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Sequipment record);

    /** 
    * @param m
    * @return 
    */
    List<com.spring.web.ibatis.LlHashMap<String, Object>> selectSequipmentList(Map<String, Object> m);
    
    int insertBatch(Map<String, Object> m);

    /** 
    * @param m 
    */
    int insertBatch2(Map<String, Object> m);
    
    List<Sequipment> selectByUserId(Map<String, Object> map);
    
    List<com.spring.web.ibatis.LlHashMap<String, Object>> selectClique(Map<String, Object> m);
}