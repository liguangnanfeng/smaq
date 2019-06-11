package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import com.spring.web.model.TModel;
import org.apache.ibatis.annotations.Param;

public interface TModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_model_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_model_tbl
     *
     * @mbggenerated
     */
    int insert(TModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_model_tbl
     *
     * @mbggenerated
     */
    int insertSelective(TModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_model_tbl
     *
     * @mbggenerated
     */
    TModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_model_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_model_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TModel record);

    /**
     * 综合检查
     * 日检查表
     * @param m
     * @return
     */
    List<Map<String, Object>> selectByMap(Map<String, Object> m);
    
    List<Map<String, Object>> selectByMap1(Map<String, Object> m);//检索条件增加，检查周期

    /**
     * 根据传递的条件进行查询数据
     * 部门名称, 检查方式, 检查类型
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByMap4(Map<String, Object> map);
    
    List<Map<String, Object>> selectByMap2(Map<String, Object> m);//检索条件增加，检查周期不为1
    
    List<TModel> selectAuto();

    /**
     * TODO  企业端 公司id查询所有的模版信息
     * @param uid
     * @return
     */
    List<Map<Integer, String>> selectModelByUid(@Param("uid") Integer uid,@Param("dpName") String dpName);

    /**
     * TODO 政府端 公司id查询所有的模版信息
     * @param uid
     * @return
     */
    List<Map<Integer, String>> selectCountryModelByUid(Integer uid);


    /**
     * TODO 点击设定获取参数,每一次取出最新的数据,没有返回为空
     * @param map
     * @return
     */
    TModel selectModelByMapAndLimit1(Map<String, Object> map);
}