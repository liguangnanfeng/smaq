package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Notice;

public interface NoticeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_tbl
     *
     * @mbggenerated
     */
    int insert(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_tbl
     *
     * @mbggenerated
     */
    Notice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Notice record);

    /** 
    * @param m
    * @return 
    */
    List<DynamicParameter<String, Object>> selectNoticeList(Map<String, Object> m);
    
    /** 
     * @param m
     * @return 
     */
     List<DynamicParameter<String, Object>> selectNoticeAppList(Map<String, Object> m);
}