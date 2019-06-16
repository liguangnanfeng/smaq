package com.spring.web.dao;

import com.spring.web.model.TLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TLevelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_tbl
     *
     * @mbggenerated
     */
    int insert(TLevel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_tbl
     *
     * @mbggenerated
     */
    int insertSelective(TLevel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_tbl
     *
     * @mbggenerated
     */
    TLevel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TLevel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_level_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TLevel record);

    List<TLevel> selectByIndustry(@Param("industryId") Integer industryId);

    /**
     * @param industryId
     * @return 返回现场检查分类，包含特种设备
     */
    List<TLevel> selectByIndustryAndTz(@Param("industryId") Integer industryId);

    List<TLevel> selectByIds(@Param("ids") String ids);

    /**
     * 根据 industryId 查询基础检查对应的关系,并进行去重处理
     * @param
     * @return
     */
    List<String> selectLevel1ByIndustry(Integer industryId);

    /**
     * 根据level1 和 industryId查询level2
     * @param level1
     * @param industryId
     * @return
     */
    List<Map<String,Object>> selectlevel2Bylevel1AndIndustryId(@Param("level1") String level1, @Param("industryId") Integer industryId);

    /**
     * 基础, 高危 详细数据的保存
     * @param
     * @return
     */
    List<Map> selectGaoweiAndJiChu(@Param("industryId") Integer industryId, @Param("level1") String level1, @Param("level2") String level2);


    /**
     * 获取所有的高危选项level1
     * @param industryId
     */
    List<Map> selectByIndustryId(Integer industryId);

    /**
     * 查询高危lecel1
     * @param industryId
     * @return
     */
    List<Map<String, Object>> checkGaoWei2(Integer industryId);

    List<TLevel> selectAll();

    List<TLevel> selectAllIds( @Param("ids")Integer[] ids);

    List<String> selectAllDep3();

    List<TLevel> selectLevel1(@Param("level1")String level1);
}