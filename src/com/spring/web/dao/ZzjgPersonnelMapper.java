package com.spring.web.dao;

import java.util.List;
import java.util.Map;

import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.ZzjgPersonnel;
import org.apache.ibatis.annotations.Param;

public interface ZzjgPersonnelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_personnel_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_personnel_tbl
     *
     * @mbggenerated
     */
    int insert(ZzjgPersonnel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_personnel_tbl
     *
     * @mbggenerated
     */
    int insertSelective(ZzjgPersonnel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_personnel_tbl
     *
     * @mbggenerated
     */
    ZzjgPersonnel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_personnel_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ZzjgPersonnel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_personnel_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ZzjgPersonnel record);
    
    List<LlHashMap<Object, Object>> selectByMap(LlHashMap<Object, Object> m);
    
    List<LlHashMap<Object, Object>>selectByMapLimit(LlHashMap<Object, Object>m);

    Integer selectByMapCount(LlHashMap<Object, Object>m);
    
    List<Map<String, Object>> selectCountByTrade(Map<String, Object> m);
    
    Map<String, Object> selectCountForClique(Map<String, Object> m);

    ZzjgPersonnel selectById(Integer userId);

    ZzjgPersonnel selectPersonnelByNameAndPwd(String name);

    List<Map<Integer, String>> findUserByIdAndSTatus(Integer uid);

    /**
     * 根据安全责任人的id查询部门和对应的岗位
     * @param personnelId
     * @return
     */
    String selectdpidById( Integer personnelId);

    /**
     * 政府端
     * @param id
     * @return
     */
    List<Map<Integer, String>> findUserByIdAndStatus1(Integer id);
}