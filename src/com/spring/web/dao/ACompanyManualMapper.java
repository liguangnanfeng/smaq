package com.spring.web.dao;

import com.spring.web.model.ACompanyManual;
import com.spring.web.model.request.CheckLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ACompanyManualMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    int insert(ACompanyManual record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    int insertSelective(ACompanyManual record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    ACompanyManual selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ACompanyManual record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ACompanyManual record);

    List<Map<String, Object>> selectByMap(Map<String, Object> m);

    List<Map<String, Object>> selectByMapGroupByLevel1Level2(Map<String, Object> m);

    List<Map<String, Object>> selectByMap2(Map<String, Object> m);

    List<Map<String, Object>> selectByMap3(Map<String, Object> m);//add wz 20190119 政府端_风险分级管控_企业页面查询

    /**
     * @param level
     * @param uid
     * @return 风险评估后自动设置值
     */
    int updateLevel(@Param("level") String level, @Param("uid") Integer uid);

    int insertBath(Map<String, Object> m);

    int deleteFlag2(@Param("uid") Integer uid);

    List<Map<String, Object>> selectByMap2Limit(Map<String, Object> m);

    Integer selectByMap2LimitCount(Map<String, Object> m);

    List<Map<String, Object>> selectDangerCompanyList(Map<String, Object> m);

    int updateByL1L2(ACompanyManual ac);

    int updateLnglat(@Param("id") Integer id, @Param("lnglat") String lnglat);

    /**
     * @param userId
     * @return a_company_manual 变动时 处理企业风险等级，取最大值（红-橙-黄-蓝）
     */
    int updateCompanyDlevel(@Param("userId") Integer userId);

    int updateDelByIds(@Param("ids") String ids);

    /**
     * 查询level2 工位和岗位
     *
     * @param uid
     * @param name
     * @return
     */
    List<String> selectDangerAndManual(@Param("uid") Integer uid, @Param("name") String name);

    List<Map<String, Object>> selectLevel3AndId(CheckLevel check);

    List<Map> selectLevel4AndId(CheckLevel checkLevel);

    int selectDmidById(String name);

    /**
     * 根据公司总id和部门名称(level1) 查询level2
     * @param name
     * @param uid
     * @return
     */
    List<String> findLevel2ByPersonelId(@Param("name") String name, @Param("uid") Integer uid);

    // 根据公司id，部门名，岗位名
    List<ACompanyManual> findInspection(@Param("id") String id,@Param("dept") String dept,@Param("station") String station);

    /**
     * 根据level1 和Level 2 和检查的类型 (基础),查询level3
     * @param checkLevel
     * @return
     */
    List<CheckLevel> findLevel3ByjcType1(CheckLevel checkLevel);

    /**
     * 根据level1 和Level 2 和检查的类型 (现场),查询level3
     * @param checkLevel
     * @return
     */
    List<CheckLevel> findLevel3ByjcType2(CheckLevel checkLevel);

    /**
     * 根据level1 和Level 2 和检查的类型 (高危),查询level3
     * @param checkLevel
     * @return
     */
    List<CheckLevel> findLevel3ByjcType3(CheckLevel checkLevel);

    int add(ACompanyManual aCompanyManuals);


    List<ACompanyManual> selectAll(@Param("uid") Integer uid);

    boolean updateDeltess(@Param("id")Integer id);

    List<ACompanyManual> selectIds(@Param("uid")Integer uid);

    boolean updateAll(Integer id);

    void insertBathByIndustry(Map<String, Object> m);

    void updateDelByUserId(Integer id);

    void updateDelByUserIddel(Integer id);

    List<ACompanyManual> selectIdss(Integer userId);

    void updateDelByUserId2(Integer id);

    List<ACompanyManual> selectAlls(Integer userId);

    void save(ACompanyManual aCompanyManual1);

    List<ACompanyManual> findALLsss(@Param("flag")String flag, @Param("uid")Integer uid);

}

