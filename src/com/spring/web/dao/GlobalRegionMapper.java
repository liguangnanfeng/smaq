package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.GlobalRegion;

public interface GlobalRegionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_region_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_region_tbl
     *
     * @mbggenerated
     */
    int insert(GlobalRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_region_tbl
     *
     * @mbggenerated
     */
    int insertSelective(GlobalRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_region_tbl
     *
     * @mbggenerated
     */
    GlobalRegion selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_region_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GlobalRegion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_region_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GlobalRegion record);
    
    GlobalRegion selectById(Integer id);
    
    List<DynamicParameter<String, Object>> selectByType(String regionType);
    
    GlobalRegion selectByCityCode(String cityCode);
    
    List<GlobalRegion> selectByParentId(Integer parentId);

    /** 
    * @param region
    * @return 
    */
    int selectParentId(Integer id);

    /** 
    * @return 
    */
    String selectRegionName(Integer id);

    /** 
    * @param regionId
    * @return 
    */
    GlobalRegion selectGlobalRegionById(Integer regionId);
    
    /** 
    * @param m 根据区镇村 获取区域id
    * @return 
    */
    int selectByName(Map<String, Object> m);    
}