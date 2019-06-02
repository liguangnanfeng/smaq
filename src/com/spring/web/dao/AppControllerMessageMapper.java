package com.spring.web.dao;

import com.spring.web.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppControllerMessageMapper {

    List<Messages>  findMessageList(@Param("userId") String userId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    TCheck findCheckById(String checkId);

    Messages findMessageDetail(String id);

    /**
     * 获取已检查的检查记录 所有人都能看到
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Map>  findTCheckList(@Param("uid") Integer userId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    List<Map> findTCheckListByStatus(@Param("cid") String userId, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    ZzjgPersonnel findPersonnel(String userId);

    /**
     * 根据检查记录详情获取部门的整改意见
     * @param checkId
     * @return
     */
    List<Map> findRectificationList(String checkId);

    TRectification findTRectification(String checkId);

    // 复查
    List<Map<String, Object>> selectRecheckByCheckId(@Param("checkId") Integer checkId);

    // 复查 （主界面进入）
    List<Map<String, Object>> selectRecheckByCheckId2(@Param("userId") Integer userId);

    // 政府端检查表
    List<Map> selectCheckByFlag();


    /**
     * 企业端被检查人员 获取去不和个的项
     * @param uid     企业id
     * @param bm      部门
     * @param pageNo  页码
     * @param i       每页显示的条数
     */
    List<Map> findTCheckListByStatusAndBJC(@Param("uid") Integer uid, @Param("bm")String bm, @Param("pageNo")Integer pageNo, @Param("pageSize")int i);

    /**
     * 被检查人员获取自己部门的不合格记录
     * @param uid 公司id
     * @param bm  部门
     * @param name 岗位  TODO 保留
     * @param pageNo 页码
     * @param i     条目
     * @return
     */
    List<Map> findTCheckItemByBJC(@Param("uid") Integer uid, @Param("bm")String bm, @Param("gw")String name, @Param("pageNo")Integer pageNo,  @Param("pageSize")int i);

    /**
     * 政府端检查人员获取复查记录(合格不合格都要有)
     * @param userId 自己的id
     * @param uid    公司id
     * @return
     */
    List<Map<String, Object>> selectRecheckByCheckId2ZF(@Param("userId")Integer userId, @Param("uid") Integer uid);
    /**
     * 企业端的检查人员获取复查记录
     * @param userId
     * @param uid
     * @return
     */
    List<Map<String, Object>> selectRecheckByCheckId2JCR(@Param("userId")Integer userId,@Param("uid") Integer uid);

    /**
     * 企业端被检查人获取复查记录
     * @param dpid    部门id
     * @param uid     公司id
     * @return
     */
    List<Map<String, Object>> selectRecheckByCheckId2BJCR(@Param("dpid")Integer dpid, @Param("uid")String uid);
}
