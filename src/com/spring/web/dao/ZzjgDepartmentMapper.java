package com.spring.web.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.spring.web.model.ZzjgDepartment;

public interface ZzjgDepartmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    int insert(ZzjgDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    int insertSelective(ZzjgDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    ZzjgDepartment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ZzjgDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ZzjgDepartment record);
    
    /**
     * 根据公司名字查询部门
     */
    List<ZzjgDepartment> selectByCompanyId(ZzjgDepartment zzjgDepartment);

    /**
     * 根据公司名称获取部门
     *
     * @param uid
     * @return
     */
    List<Map<Object, Object>> selectByUid(@Param("uid") Integer uid);
    
    List<Map<Object, Object>> selectLevel1ByUid(@Param("uid") Integer uid);
    
    List<Map<Object, Object>> selectLevel2ByUid(@Param("uid") Integer uid);
    
    /**
     * 根据岗位id查询岗位名字和部门名字
     * @param id
     * @return
     */
    List<Map<Object, Object>> selectNameParnameById(@Param("id") Integer id);//zhangcl 2018.10.27

    /**
     * 根据公司id查询所有的部门
     * @param companyId
     * @return
     */
    List<ZzjgDepartment> selectDepartmentByCompidAndLEvel(Integer companyId);

    /**
     * 根据公司id和部门id查询对应的所有岗位
     * @param companyId
     * @return
     */
    List<ZzjgDepartment> selectDepByCompanyIdandSome(Integer companyId, Integer depId);

    /**
     * 根据总公司id查询所有的部门 level
     * @param id
     * @return
     */
    List<String> selectNameByUid(Integer id);


    List<ZzjgDepartment> selectOnes(@Param("pid") Integer pid);


}