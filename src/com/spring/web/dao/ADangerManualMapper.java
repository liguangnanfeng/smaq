package com.spring.web.dao;

import com.spring.web.model.ACompanyManual;
import com.spring.web.model.ADangerManual;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ADangerManualMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_manual_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_manual_tbl
     *
     * @mbggenerated
     */
    int insert(ADangerManual record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_manual_tbl
     *
     * @mbggenerated
     */
    int insertSelective(ADangerManual record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_manual_tbl
     *
     * @mbggenerated
     */
    ADangerManual selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_manual_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ADangerManual record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_danger_manual_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ADangerManual record);

    List<ADangerManual> selectByIndustry(@Param("industry") String industry);

    List<ACompanyManual> selectByIds(@Param("ids") Integer[] ids);

    List<ACompanyManual> selectById(@Param("id") Integer ids);

    String selectIndustryByid(Integer id);

    List<ADangerManual> selectByMap(Map<String, Object> m);

    List<ADangerManual> selectFactors(@Param("flag") String flag, @Param("industry") String industry);

    List<ADangerManual> selectByAllIds(@Param("ids") Integer[] ids);

    List<String> selectAll();

    List<ADangerManual> selectAllDep3();

    List<Map<String,Object>> selectLevel1(String level1);

    List<Map<String,Object>> selectLevel3(@Param("level1")String level1, @Param("level2")String level2);

    List<Map<String,Object>> selectMeasures(@Param("level1")String level1, @Param("level2")String level2, @Param("level3")String level3);

    List<ADangerManual> selectByIndustryAll(@Param("industrys")String industrys);
}