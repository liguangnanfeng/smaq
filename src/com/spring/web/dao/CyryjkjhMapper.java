package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import com.spring.web.model.Cyryjkjh;

public interface CyryjkjhMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cyryjkjh_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cyryjkjh_tbl
     *
     * @mbggenerated
     */
    int insert(Cyryjkjh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cyryjkjh_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Cyryjkjh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cyryjkjh_tbl
     *
     * @mbggenerated
     */
    Cyryjkjh selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cyryjkjh_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Cyryjkjh record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cyryjkjh_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Cyryjkjh record);
    
    List<Map<String, Object>> selectList(Map<String, Object> m);
}