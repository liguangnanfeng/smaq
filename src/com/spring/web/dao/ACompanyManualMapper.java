package com.spring.web.dao;

import com.spring.web.model.ACompanyManual;
import com.spring.web.model.request.CheckLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ACompanyManualMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ACompanyManual record);

    int insertSelective(ACompanyManual record);

    ACompanyManual selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ACompanyManual record);

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

    /**
     * 查询level3
     * @param check
     * @return
     */
    List<Map<String, Object>> selectLevel3AndId(CheckLevel check);

    /**
     * 直接根据 公司id  level1  level2  level3 获取level4 获取最后一步的数据
     * @param checkLevel
     * @return
     */
    List<Map> selectLevel4AndId(CheckLevel checkLevel);

    int selectDmidById(String name);

    /**
     * 根据公司总id和部门名称(level1) 查询level2
     * @param name
     * @param uid
     * @return
     */
    List<String> findLevel2ByPersonelId(@Param("name") String name, @Param("uid") Integer uid);

    // 根据公司id，部门名，岗位名获取详细信息 但是要去除重复
    List<String> findInspection(@Param("id") String id,@Param("dept") String dept,@Param("station") String station);

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

    void updateAllUid(@Param("uid")Integer uid, @Param("level1")String level1, @Param("level2")String level2);

    void insertAdd(ACompanyManual aCompanyManual);

    void updateAllUids(@Param("uid")Integer uid, @Param("level1")String level1, @Param("level2")String level2);

    List<ACompanyManual> selectIdsAll(@Param("level1")String level1, @Param("level2")String level2,@Param("uid") Integer uid);

    List<ACompanyManual> selectIdsAlls(@Param("level1")String level1, @Param("level2")String level2,@Param("uid") Integer uid);

    List<Map<String, Object>> selectByAll(Map<String, Object> m);
    /**
     * 根据部门的名称和uid获取level3
     */
    List<String> selectlevel3BydmName(@Param("uid")Integer id, @Param("level1")String name);

    /**
     * 直接根据 level3 和uid 获取信息
     * @param id
     * @param level3
     * @return
     */
    List<ACompanyManual> selectAllByLevel3(@Param("uid")Integer id, @Param("level1")String name,@Param("level3")String level3);

    /**
     * 选择基础和现场的部门的名称
     * @param id  公司的id
     * @param name 基础/现场
     * @return
     */
    List<Map<Object, Object>> findJiChuItem(@Param("uid")Integer id, @Param("name")String name);

    /**
     * 根据基础/现场/高危获取数据
     * @param uid   公司id
     * @param dpName 部门名称
     * @param level3 level3字段
     * @return
     */
    List<String> selectlevel3BydmNameAndLevel3(@Param("uid")Integer uid, @Param("level1")String dpName, @Param("level3")String level3);

    /**
     * 不知道啥方法
     * @param var1
     * @return
     */
    List<Map<String, Object>> selectByFlag3(Map<String, Object> var1);

}

