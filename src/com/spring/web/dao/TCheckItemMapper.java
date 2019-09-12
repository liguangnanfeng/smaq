package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.TCheckItem;

import javax.persistence.criteria.CriteriaBuilder;

public interface TCheckItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_item_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_item_tbl
     *
     * @mbggenerated
     */
    int insert(TCheckItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_item_tbl
     *
     * @mbggenerated
     */
    int insertSelective(TCheckItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_item_tbl
     *
     * @mbggenerated
     */
    TCheckItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_item_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TCheckItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_item_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TCheckItem record);

    int insertBath(@Param("list") List<Map<String, Object>> list, @Param("checkId") Integer checkId, @Param("partId") Integer partId);

    int insertThreeBath(@Param("list") List<TCheckItem> list, @Param("checkId") Integer checkId, @Param("partId") Integer partId);

    List<Map<String, Object>> selectByCheckId(@Param("checkId") Integer checkId);

    List<Map<String, Object>> selectByAppCheckId(@Param("checkId") Integer checkId);

    List<TCheckItem> selectByAppList(Map<String, Object> m);

    List<TCheckItem> selectByList3(Map<String, Object> m);

    List<TCheckItem> selectByMap1(Map<String, Object> m); //zhangcl 2018.12.29

    List<Map<String, Object>> selectDangerByCheckId(@Param("checkId") Integer checkId, @Param("status") Integer status);

    int updSuggest(Map<String, Object> m);

    int updRecheck(Map<String, Object> m);

    int deleteByCheckId(@Param("checkId") Integer checkId);

    /**
     * @param userId
     * @return 企业/村/镇/区首页 隐患汇总数据
     */
    List<Map<String, Object>> selectCount(Map<String, Object> m);

    /**
     * @param userId
     * @return 企业/村/镇/区首页 隐患汇总数据 重大隐患
     */
    List<Map<String, Object>> selectZhongCount(Map<String, Object> m);

    /**
     * @return 根据隐患类型汇总
     */
    List<DynamicParameter<String, Object>> selectGroupIndustryType(Map<String, Object> m);

    /**
     * @return 根据隐患等级汇总
     */
    List<DynamicParameter<String, Object>> selectGroupItem3(Map<String, Object> m);

    /**
     * @return 根据整改率汇总
     */
    List<DynamicParameter<String, Object>> selectGroupItemStatus(Map<String, Object> m);

    /**
     * @return 根据整改来源汇总
     */
    List<DynamicParameter<String, Object>> selectGroupFlag(Map<String, Object> m);

    /**
     * @return 隐患排查治理汇总
     */
    List<Map<String, Object>> selectDangerIndexList(Map<String, Object> m);

    /**
     * @return 隐患排查治理汇总
     */
    Integer selectDangerIndexListCount(Map<String, Object> m);

    /**
     * @return 隐患排查治理汇总 根据企业
     */
    List<Map<String, Object>> selectDangerCollectByCompany(Map<String, Object> m);

    /**
     * @return 隐患排查治理汇总 根据企业类型汇总
     */
    List<Map<String, Object>> selectDangerCollectGroup(Map<String, Object> m);

    /**
     * @param
     * @return 监管端 隐患汇总数据
     */
    List<Map<String, Object>> selectCount2(Map<String, Object> m);

    List<Map<String, Object>> selectNeedDangerIndexList(Map<String, Object> m);

    /**
     * 直接根据checkId进行查询,,把所有相关记录的数据进行保存
     * @param checkId
     * @return
     */
    List<Map> selectAllByCheckId(@Param("checkId") Integer checkId);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    TCheckItem selectAllById(Integer id);

    /**
     * 查询有几个不合格项
     * @param id
     * @return
     */
    Integer selectNumBytCheckId(Integer id);

    /**
     * 2019-05-07  新增隐患治理记录(复查记录,主要合格的)
     */
    List<Map> selectRecheckList(Integer userId);

    /**
     * 根据检查记录主表id获取数据
     */
    List<TCheckItem> selectItemByCheckId(Integer checkId);

    /**
     * pc端查询所有的整改合格的记录
     * @param id
     * @param status
     * @return
     */
    List<Map> selectRecheckListByRecheckStatus(@Param("uid") Integer id, @Param("status") Integer status);

    List<Map> selectAll(@Param("userIds")String userIds);

    List<TCheckItem> selectAllLevelId(@Param("checkId")Integer checkId);

    /**
     * 隐患治理记录查询之企业自查
     * 该公司全部的未删除的, 已检查的,和部门 不合格和已复查的
     * @param userId 公司id
     * @return
     */
    List<Map> selectListBystatus(@Param("userId") Integer userId,@Param("flag") Integer flag);

    /**
     * 隐患治理记录之行政检查
     * @param userId
     * @param flag
     * @return
     */
    List<Map> selectXZListBystatus(@Param("userId") Integer userId,@Param("flag") Integer flag);
    /**
     * 隐患治理记录之部门抽查
     * @param userId
     * @param flag
     * @return
     */
    List<Map> selectBMCCListBystatus(@Param("userId") Integer userId,@Param("flag") Integer flag);

    /**
     * 根据合格不合格区生成图表
     */
    List<DynamicParameter<String, Object>> selectFailedByMap(Map<String, Object> m);

    /**
     * 治理数据分析<已复查合格/复查不合格的参数>(单位: 天)
     * 根据检查详情获取复查合格/复查不合格
     * @param m
     * @return
     */
    List<DynamicParameter<String, Object>> selectRecheckFileByMap(Map<String, Object> m);

    /**
     * 排查数据分析之隐患类型
     * @param m
     * @return
     */
    List<DynamicParameter<String, Object>> selectHiddenDangerTypeByMap(Map<String, Object> m);

    /**
     * 排查数据分析之隐患来源
     * @param m
     * @return
     */
    List<DynamicParameter<String, Object>> selectHiddenSourceTypeByMap(Map<String, Object> m);

    /**
     * 排查数据分析之隐患等级
     * @param m
     * @return
     */
    List<DynamicParameter<String, Object>> selectHiddenLevelTypeByMap(Map<String, Object> m);

    /**
     * 根据checkId查询levelId
     * @param id
     * @return
     */
    List<Integer> selectLevelIdByCheckId(Integer id);


    List<DynamicParameter<String,Object>> selectMapzhuChartData2(Map<String,Object> m);

    Integer findHiddenSourceTypeByMap(@Param("flag") Integer flag, @Param("depart")String depart, @Param("userIds")String userIds, @Param("industry_type")Integer industry_type);

    Integer findHiddenLevelTypeByMap(@Param("depart")String depart, @Param("user_id")Integer user_id, @Param("flag")Integer flag);

    Integer findRecheckFileByMap(@Param("status")Integer status, @Param("user_id")Integer user_id, @Param("flag")Integer flag,@Param("depart")String depart);

    Integer findMapzhuChartData2(@Param("control")String control, @Param("depart")String depart, @Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer zhuChartData66(@Param("control")String control, @Param("depart")String depart, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhuChartData661(@Param("depart")String depart, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhuChartData123(@Param("control")String control, @Param("depart")String depart, @Param("user_id")Integer user_id);

    Integer zhuChartData1231(@Param("depart")String depart, @Param("user_id")Integer user_id);

    Integer zhuChartData67(@Param("control")String control, @Param("check_company")String check_company, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhuChartData671(@Param("check_company")String check_company, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer lookHiddenSource(@Param("flag") Integer flag, @Param("check_company")String check_company, @Param("userIds")String userIds, @Param("industry_type")Integer industry_type);

    Integer zhuChartData22(@Param("control")String control, @Param("depart")String depart, @Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer zhuChartData221(@Param("depart")String depart, @Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer zhuChartData23(@Param("control")String control, @Param("check_company")String check_company, @Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer zhuChartData231(@Param("check_company")String check_company, @Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer selectHiddenSources(@Param("flag") Integer flag, @Param("userIds") String userIds);

    Integer findTypeByMap(@Param("user_ids") String user_ids, @Param("flag") Integer flag);

    Integer findFileByMap(@Param("user_id") Integer user_id, @Param("flag") Integer flag);

    Integer zhuChartData77(@Param("control")String control, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhuChartData78(@Param("control")String control, @Param("flag")Integer flag, @Param("user_ids")String user_ids, @Param("departs")String departs);

    Integer zhuChartData771(@Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhuChartData781(@Param("flag")Integer flag, @Param("user_ids")String user_ids, @Param("departs")String departs);

    Integer zhuChartData88(@Param("control")String control, @Param("flag")Integer flag, @Param("user_ids")String user_ids, @Param("departs")String departs);

    Integer zhuChartData89(@Param("control")String control, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhuChartData881(@Param("flag")Integer flag, @Param("user_ids")String user_ids, @Param("departs")String departs);

    Integer zhuChartData891(@Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhuChartData124(@Param("control")String control, @Param("user_id")Integer user_id);

    Integer zhuChartData1241(@Param("user_id")Integer user_id);

    List<Integer> selectCompanyId(@Param("userId") Integer userId, @Param("type") Integer type);

    Integer zhuChartData124Geo(@Param("control")String control,@Param("userId") Integer userId, @Param("type") Integer type);

    Integer findFileByGeo(@Param("userId") Integer userId, @Param("flag") Integer flag, @Param("type") Integer type);

    Integer findTypeByGeo(@Param("userId") Integer userId, @Param("flag") Integer flag, @Param("type") Integer type);

    Integer zhuChartData88Geo(@Param("control")String control, @Param("flag")Integer flag, @Param("userId")Integer userId, @Param("type") Integer type);

    Integer zhuChartData77ByGeo(@Param("control")String control, @Param("flag")Integer flag, @Param("userId")Integer userId, @Param("type") Integer type);

    Integer selectHiddenSourcesByGvo(@Param("flag") int flag, @Param("userId") Integer userId, @Param("type") Integer type);

    //新增:
    List<Map> selectListBystatusGeo(@Param("userId") Integer userId,@Param("flag") Integer flag, @Param("type") Integer type);

    List<Map> selectXZListBystatusGeo(@Param("userId") Integer userId,@Param("flag") Integer flag,  @Param("type") Integer type);

    List<Map> selectBMCCListBystatusGeo(@Param("userId") Integer userId,@Param("flag") Integer flag, @Param("type") Integer type);

    Map<String, Object> selectNumByhangye(@Param("userId") Integer userId,@Param("flag") Integer flag, @Param("type") Integer type);

    Map<String, Object> selectHiddenSourcesByGvo2(@Param("userId") Integer userId, @Param("type") Integer type);

    Map<String, Object> zhuChartData882(@Param("userId") Integer userId,@Param("flag") Integer flag, @Param("type") Integer type);

    Map<String, Object> findTypeByGeo2(@Param("userId") Integer userId, @Param("type") Integer type);

    Map<String, Object> findFileByGeo2(@Param("userId") Integer userId, @Param("type") Integer type);

    Map<String, Object> zhuChartData124Geo2(@Param("userId") Integer userId, @Param("type") Integer type);

    List<Map<String,Object>> findALL(@Param("userId")Integer userId, @Param("type")Integer type);

    List<Map<String, Object>> selectBasicMessage(@Param("userId") Integer userId, @Param("type") Integer type, @Param("start") Integer start);

    Integer zfHiddenTrouble(@Param("control")String control, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zfHiddenTrouble11(@Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zfTrouble(@Param("control")String control, @Param("user_ids")String user_ids);

    Integer zfTrouble11(@Param("user_ids")String user_ids);

    Integer zfHiddenTrouble22(@Param("control")String control, @Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer zfHiddenTroubleType(@Param("control")String control, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zfHiddenTrouble33(@Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer zhengFuChartData88(@Param("control")String control, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhengFuChartData881(@Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zfData88(@Param("control")String control, @Param("user_ids")String user_ids);

    Integer zfData881(@Param("user_ids")String user_ids);

    Integer zhengFuChartData99(@Param("control")String control, @Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer zhengFuChartData991(@Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer zhengFuChartDataDanger(@Param("flag")Integer flag, @Param("industry")String industry, @Param("user_ids")String user_ids);

    Integer zhengFuChartDataDanger2(@Param("flag")Integer flag, @Param("userId")Integer user_ids);

    Integer zhengFuChartDataDanger11(@Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer zhengFuDanger(@Param("industry")String industry, @Param("user_ids")String user_ids);

    Integer zhengFuDanger11(@Param("user_ids")String user_ids);

    Integer zhengFuChartDataDanger22(@Param("flag")Integer flag, @Param("industry")String industry, @Param("user_id")Integer user_id);

    Integer zhengFuChartDataDanger33(@Param("flag")Integer flag, @Param("user_id")Integer user_id);

    Integer manageHiddenCompany(@Param("status")Integer status, @Param("user_ids")String user_ids, @Param("flag")Integer flag);

    Integer manageHiddenCompany11(@Param("status")Integer status, @Param("user_id")Integer user_id, @Param("flag")Integer flag);

    Integer manageHiddenDanger(@Param("status")Integer status, @Param("control")String control, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer manageHiddenDanger11(@Param("status")Integer status, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer manageHiddenIndustry11(@Param("status")Integer status, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer manageHiddenIndustry(@Param("status")Integer status, @Param("industry")String industry, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer getTotalPage(@Param("userId") Integer userId, @Param("type") Integer type);

    List<Map<String, Object>> getData(@Param("userId") Integer userId, @Param("type") Integer type, @Param("start") Integer start);

    Integer manageHiddenDanger22(@Param("status")Integer status, @Param("controls")String controls,@Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer manageHiddenIndustry22(@Param("status")Integer status, @Param("flag")Integer flag, @Param("user_ids")String user_ids);

    Integer findAllCounte(@Param("userIds")String userIds);
}



