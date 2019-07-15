package com.spring.web.dao;

import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.TCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TCheckMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TCheck record);

    int insertSelective(TCheck record);

    TCheck selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCheck record);

    int updateByPrimaryKey(TCheck record);

    /*政府端检查指定公司的所有的检查记录*/
    List<Map<String, Object>> selectList(Map<String, Object> m);

    /**
     * 根据条件查询只有检查不合格的进行显示
     * @param m
     * @return
     */
    List<Map<String, Object>> selectListTwo(Map<String, Object> m);

    /*企业端获取本公司所有的检查记录*/
    List<Map<String, Object>> selectListByMap(Map<String, Object> m);

    List<Map<String, Object>> selectList1(Map<String, Object> m);//APP端修改使用

    List<Map<String, Object>> selectList2(Map<String, Object> m);//首页定期检查——企业自查修改使用

    List<Map<String, Object>> selectList3(Map<String, Object> m);

    DynamicParameter<String, Object> selectCompany(@Param("id") Integer id);

    int deleteById(@Param("sql") String sql);

    /**
     * 根据modelId查询所有的检查表 只有未检查的才能给它
     * @param modelId
     * @return
     */
    TCheck selectByModelId(Integer modelId);

    /**
     * 根据用户登陆的信息查询所有的表信息
     * @param id
     * @param uid
     * @return
     */
    List<TCheck> findAllByUidAndUserId(@Param("id") Integer id, @Param("Uid") Integer uid);

    /**
     * 找寻该模版最早的内容进行覆盖更新
     * @param id
     * @return
     */
    TCheck selectOldByModelId(Integer id);

    /**
     * 政府端查询检查记录
     * @param id
     * @return
     */
    List<Map> selectRecordByCreateUser(Integer id);

    List<Map<String, Object>> selectAll(@Param("userIds")String userIds);

    void updateDel(@Param("id")Integer id);

    List<Map<String, Object>> selectLevels(@Param("id") Integer id);

    List<Map<String,Object>> selectAllLevel(@Param("id")Integer id);

    List<Map<String,Object>> selectAllDanger(@Param("id")Integer id);

    /**
     * 根据模版id查询最开始模版记录详情
     * @param id
     * @param i
     * @return
     */
    TCheck selectCheckBymodelIdAndStatus(@Param("modelId")Integer id, @Param("status")int i);

    Integer selectHiddenDangerNumber(Integer id);


}