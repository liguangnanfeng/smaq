package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import com.spring.web.model.Officials;

public interface OfficialsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table officials_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table officials_tbl
     *
     * @mbggenerated
     */
    int insert(Officials record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table officials_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Officials record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table officials_tbl
     *
     * @mbggenerated
     */
    Officials selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table officials_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Officials record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table officials_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Officials record);
    
    List<Map<String, Object>> selectList(Map<String, Object> m);

    /**
     * 根据编号进行查询 所有的数据
     * @param username
     * @return
     */
    Officials selectByCode(String username);
}