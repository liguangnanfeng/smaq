package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Company;
import com.spring.web.model.User;
import com.spring.web.tobject.cgf.CompanyListReqDTO;

public interface CompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_tbl
     *
     * @mbggenerated
     */
    int insert(Company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_tbl
     *
     * @mbggenerated
     */
    int insertSelective(Company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_tbl
     *
     * @mbggenerated
     */
    Company selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Company record);

    /** 
    * @param m
    * @return 
    */
    List<DynamicParameter<String, Object>> selectCompanyList(Map<String, Object> m);
    
    /** 
     * @param m
     * @return 
     */
    Integer selectCompanyList_count(Map<String, Object> m);

    /** 
    * @return 根据 村 查找企业 根据监管行业分组
    */
    List<DynamicParameter<String, Object>> selectIndustry(Map<String, Object> m);
    
    /** 
     * @return 根据 村 查找企业 根据监管行业分组
     */
    List<DynamicParameter<String, Object>> selectIndustryByScale(Map<String, Object> m);
    
    
    /** 
     * @return 根据 村 查找企业 根据企业行业分组
     */
    List<DynamicParameter<String, Object>> selectIndustry2(Map<String, Object> m);

    /** 
    * @param m
    * @return  规上企业 根据企业行业分组
    */
    List<DynamicParameter<String, Object>> selectIndustry2AndScore(Map<String, Object> m);

    /** 
     * @return 根据 村 查找企业 根据危险级别分组
     */
    List<DynamicParameter<String, Object>> selectDlevel(Map<String, Object> m);

    /** 
    * @return 获取 村 企业数量
    */
    List<Integer> selectCount(Map<String, Object> m, User user);
    
    /** 
     * @return 获取 地图 企业数量
     */
    Integer selectCountMap(Map<String, Object> m);
    
    /** 
     * @return 企业首页预警
     */
    List<Integer> selectYuCount(Map<String, Object> m);
    
    /** 
     * @return 企业首页预警
     */
    List<Integer> selectYuCountAll(Map<String, Object> m);
    
    /** 
     * @return 获取村地图信息
     */
    List<DynamicParameter<String, Object>> selectCompanyMap(Map<String, Object> m);
    
    /** 
     * @return 获取行业端集团企业地图信息
     */
    List<DynamicParameter<String, Object>> selectCompanyMapTC(Map<String, Object> m);//add 20190131
    
    /** 
     * @return 获取镇地图信息
     */
    List<DynamicParameter<String, Object>> selectCompanyMapByTown(Map<String, Object> m);
    
    int insertBatch(@Param("list") List<Company> list);
    
    List<DynamicParameter<String, Object>> selectNeedCompanyList(Map<String, Object> m);

    /** 
    * @param m
    * @return 
    */
    List<Integer> selectByCompany_view(Map<String, Object> m);
    
    /** 
     * @param m
     * @return 
     */
    List<Integer> selectByCompany_trade(Map<String, Object> m);
    
    /** 
    * @param m
    * @return 
    */
    List<Integer> selectIdsByCompany_view(CompanyListReqDTO dto);
    
    /** 
     * @param m
     * @return 
     */
    List<Integer> selectIdsByCompany_viewWithPage(CompanyListReqDTO dto);
    
    /** 
     * @param m
     * @return 
     */
    List<Map<String, Object>> selectIdsByCompany_viewWithPage_1(CompanyListReqDTO dto);//add by zhangcl 2018.11.8
    
    /** 
     * @param m
     * @return 
     */
    Integer selectIdsByCompany_view_count(CompanyListReqDTO dto);
    
    /** 
     * @param m
     * @return 
     */
    Integer selectIdsByCompany_view_count_1(CompanyListReqDTO dto);//add by zhangcl 2018.11.8
    
    /** 
     * @param m
     * @return 
     */
    List<Map<String, Object>> selectByIds(CompanyListReqDTO dto);

    List<Map<String, Object>> selectByIds2(CompanyListReqDTO dto);
    
    /**
     * 查询所有企业
     */
    List<Company> selectAllCompany();
    
    List<Map<String, Object>> selectByDTO(CompanyListReqDTO dto);
    
    List<Integer> selectUseridsForSource(Map<String, Object> m);

    /**
     * 根据村级查询所有对应的企业
     * @param id
     * @return
     */
    List<Map<String, Object>> selectByVillageId(Integer id);


    List<Company> selectByPrimaryKeys(Integer userId);


    /*
    * 根据村级ID查询对应的企业信息
    * */
    List<Company> selectAllList(Integer village_id);

    /**
     * 查询三角洲信息
     * @param map
     * @return
     */
    List<Map<String, Object>> selectCompanyCliq(Map<String, Object> map);

    Integer findALL(@Param("userId") Integer userId, @Param("type")Integer type, @Param("isFreeze")Integer isFreeze);
}