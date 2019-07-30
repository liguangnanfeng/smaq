package com.spring.web.dao;

import com.spring.web.model.ZzjgDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

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



    List<Map<Object, Object>> selectLevel2ByUid(@Param("uid") Integer uid);

    /**
     * 根据岗位id查询岗位名字和部门名字
     *
     * @param id
     * @return
     */
    List<Map<Object, Object>> selectNameParnameById(@Param("id") Integer id);//zhangcl 2018.10.27

    /**
     * 根据公司id查询所有的部门
     *
     * @param companyId
     * @return
     */
    List<ZzjgDepartment> selectDepartmentByCompidAndLEvel(Integer companyId);

    /**
     * 根据公司id和部门id查询对应的所有岗位
     *
     * @param companyId
     * @return
     */
    List<ZzjgDepartment> selectDepByCompanyIdandSome(Integer companyId, Integer depId);

    /**
     * 根据总公司id查询所有的部门 level
     *
     * @param id
     * @return
     */
    List<String> selectNameByUid(Integer id);

    List<ZzjgDepartment> selectOnes(@Param("pid") Integer pid);

    ZzjgDepartment selectNameAll(@Param("id") Integer id);

    ZzjgDepartment selectLevel1(@Param("id") Integer id);

    List<ZzjgDepartment> selectCount(@Param("pid") Integer pid, @Param("name") String name);

    void add(ZzjgDepartment zzjgDepartment);

    void updateAll(@Param("utime") Date date, @Param("id") Integer id);

    List<Map<Object, Object>> selectLevel1ByUids(@Param("uid") Integer uid);

    List<ZzjgDepartment> selectByCompanyIds(ZzjgDepartment zzjgDepartment);

    List<Map<Object, Object>> selectLevel1All(@Param("uid")Integer id, @Param("dangerIds")String dangerIds);

    List<Map<Object,Object>> selectLevel1ByUid(@Param("uid")Integer uid);

    boolean updateDangerId(@Param("id")Integer id, @Param("dangerId")String dangerId);

  
    List<Map<Object, Object>> selectAllName(@Param("uid") Integer uid,@Param("flag") Integer flag,@Param("pids") String pids);

    /**
     * 根据公司id查询所有岗位
     * @param id
     * @return
     */
    List<String> selectAllByUserId(Integer id);


    List<Map<Object,Object>> selectLevel1One(@Param("uid")Integer uid, @Param("dangerIds")String dangerIds, @Param("id")Integer id);

    List<Map<Object, Object>> selectNotEmpy(@Param("uid") Integer uid,@Param("flag") Integer flag);

    List<ZzjgDepartment> selectNameLevel2(@Param("uid")Integer uid, @Param("pid")Integer pid, @Param("name")String name, @Param("level")int level);

    List<Map<String,Object>> selectHiddenPlan(@Param("uid")Integer uid);

    List<ZzjgDepartment> selectDangerIds(@Param("uid")Integer uid, @Param("dangerIds")String dangerIds);

    Integer deleteAll(@Param("dangerId")String dangerId, @Param("uid")Integer uid, @Param("utime")Date date);

    List<ZzjgDepartment> selectLevel1DangerIds(@Param("uid")Integer uid);

    List<ZzjgDepartment> selectOneName(@Param("uid")Integer uid, @Param("dangerIds")String dangerIds,@Param("id")Integer id);

    List<Map<Object,Object>> selectEmpyDangerIds(@Param("uid")Integer uid);

    List<ZzjgDepartment> findNameByUid(@Param("uid")Integer uid);

    List<Map<String,Object>> findAllCount(@Param("uid")Integer uid);
}