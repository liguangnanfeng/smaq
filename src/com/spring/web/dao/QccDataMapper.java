package com.spring.web.dao;

import java.util.List;
import com.spring.web.model.QccData;

public interface QccDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qcc_data_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qcc_data_tbl
     *
     * @mbggenerated
     */
    int insert(QccData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qcc_data_tbl
     *
     * @mbggenerated
     */
    int insertSelective(QccData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qcc_data_tbl
     *
     * @mbggenerated
     */
    QccData selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qcc_data_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(QccData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qcc_data_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(QccData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qcc_data_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(QccData record);
    
    /**
     * 查询企查查数据
     */
    QccData selectByCompanyName(String companyName);
}