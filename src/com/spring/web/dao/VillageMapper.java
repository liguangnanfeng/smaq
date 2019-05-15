package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Village;

public interface VillageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table village_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table village_tbl
     *
     * @mbggenerated
     */
    int insert(Village record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table village_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Village record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table village_tbl
     *
     * @mbggenerated
     */
    Village selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table village_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Village record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table village_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Village record);

    /** 
    * @return 
    */
    List<DynamicParameter<String, Object>> selectVillageList(@Param("name")String name);

    Village selectByName(Village record);

    /** 
    * @param village
    * @return 
    */
    Village selectVillageByName(@Param("name")String village);

    /** 
    * @return 
    */
    List<Village> selectAllVillage();
    
    /** 
    * @return 管理员首页 数据
    */
    List<Integer> selectCount();
    
    /** 
     * @return 村 首页预警
     */
    List<Integer> selectYuCount(Map<String, Object> m);
    
    /** 
     * 批量跟新townid
    * @return 
    */
    int updTown(Map<String, Object> m);
    
    int updTownNull(Map<String, Object> m);
    
    List<DynamicParameter<String, Object>> selectListByTown(Map<String, Object> m);
    
    /** 
     * @param m 获取村
     * @return 
     */
    List<DynamicParameter<String, Object>> selectVillage();
    
    /** 
     * @param m 获取村对应的用户
     * @return 
     */
    List<Integer> selectUserId(Map<String, Object> m);

    /**
     * 获取所有的对应的村级数据
     * @param id
     * @return
     */
    List<Map<String, Object>> selectByTownId(Integer id);

    /**
     * 村级账号查询详细信息
     * @param id
     * @return
     */
    Map selectParticularByUid(Integer id);
}