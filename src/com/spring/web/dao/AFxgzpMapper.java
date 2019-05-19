package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.AFxgzp;

public interface AFxgzpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_fxgzp_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_fxgzp_tbl
     *
     * @mbggenerated
     */
    int insert(AFxgzp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_fxgzp_tbl
     *
     * @mbggenerated
     */
    int insertSelective(AFxgzp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_fxgzp_tbl
     *
     * @mbggenerated
     */
    AFxgzp selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_fxgzp_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AFxgzp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_fxgzp_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AFxgzp record);
    
    /** 
    * @param uid
    * @return 公司公告牌，一个公司只能一个
    */
    AFxgzp selectFlag1(@Param("uid") Integer uid);
    
    List<Map<String, Object>> selectByMap(Map<String, Object> m);
    
    AFxgzp selectByName(Map<String, Object> m);
}