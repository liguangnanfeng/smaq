package com.spring.web.dao;

import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.ZzjgPersonnel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZzjgPersonnelMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(ZzjgPersonnel record);

    /**
     * @mbggenerated
     */
    int insertSelective(ZzjgPersonnel record);

    /**
     *
     * @mbggenerated
     */
    ZzjgPersonnel selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ZzjgPersonnel record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ZzjgPersonnel record);
    
    List<LlHashMap<Object, Object>> selectByMap(LlHashMap<Object, Object> m);
    
    List<LlHashMap<Object, Object>>selectByMapLimit(LlHashMap<Object, Object> m);

    Integer selectByMapCount(LlHashMap<Object, Object> m);
    
    List<Map<String, Object>> selectCountByTrade(Map<String, Object> m);
    
    Map<String, Object> selectCountForClique(Map<String, Object> m);

    ZzjgPersonnel selectById(Integer userId);

    /**
     * 登陆验证
     * @param name
     * @return
     */
    ZzjgPersonnel  selectPersonnelByNameAndPwd(String name);

    /**
     * 根据id查询公司的详细信息
     * @param id
     * @return
     */
    Map selectAll1(Integer id);

    /**
     *
     * @param uid
     * @return
     */
    List<Map<Integer, String>> findUserByIdAndSTatus(Integer uid);

    /**
     * 根据安全责任人的id查询部门和对应的岗位
     * @param personnelId
     * @return
     */
    String selectdpidById(Integer personnelId);

    /**
     * 政府端
     * @param id
     * @return
     */
    List<Map<Integer, String>> findUserByIdAndStatus1(Integer id);

    /**
     * 根据部门查询该部门所有被检查人员的id
     * @return
     */
    //List<String> selectByDpid(Integer dpid);

    void save(ZzjgPersonnel zzjgPersonnel);


    List<Map<String,Object>> selectHashMap(@Param("uid") Integer uid, @Param("dpid")Integer dpid);

    List<ZzjgPersonnel> selectName(@Param("dpid")Integer dmid, @Param("uid")Integer uid);

    boolean updateIds(Integer id);

    List<ZzjgPersonnel> selectNameDid(@Param("did") Integer did,@Param("uid") Integer uid);

    List<ZzjgPersonnel> selectDpidAndDid(@Param("did") Integer did,@Param("dpid") Integer dpid);
}