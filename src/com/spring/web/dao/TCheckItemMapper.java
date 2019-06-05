package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.TCheckItem;

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
     * 隐患治理记录查询
     * 该公司全部的未删除的, 已检查的,和部门 不合格和已复查的
     * @param userId 公司id
     * @return
     */
    List<Map> selectListBystatus(Integer userId);
}



