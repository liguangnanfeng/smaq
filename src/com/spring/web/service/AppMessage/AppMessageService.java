package com.spring.web.service.AppMessage;

import com.spring.web.model.Messages;
import com.spring.web.model.TCheck;
import com.spring.web.model.TRectification;
import com.spring.web.model.ZzjgPersonnel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppMessageService {

    List<Messages> findMessageList(String userId,Integer pageNo,Integer pageSize);

    Messages findMessageDetail(String id);

    // 检查表，同一个企业可见
    List<Map>  findTCheckList(Integer userId,Integer pageNo,Integer pageSize);

    // 根据checkId获取单条检查记录
    TCheck findCheckById(String checkId);

    // 根据员工id查询所属部门，公司
    ZzjgPersonnel findPersonnel(String userId);

    // 根据checkId获取整改意见详情列表
    List<Map> findRectificationList(String checkId);


    // 根据checkId获取整改意见时限
    TRectification findTRectification(String checkId);

    // 复查 （检查表里进入）
    List<Map<String, Object>> selectRecheckByCheckId(@Param("checkId")Integer checkId);

    // 复查 （主界面进入）
    List<Map<String, Object>> selectRecheckByCheckId2(@Param("userId")Integer userId);

    // 政府端检查表
    List<Map> selectCheckByFlag();

    List<Map> findTCheckListByStatus(String userId, Integer pageNo, Integer pageSize);

    /**
     *
     *   被检查人员获取自己部门的检查记录
     * @param uid 公司id
     * @param userId 人员id
     * @param pageNo 页码
     * @param i      条目
     * @return
     */
    List<Map> findTCheckListByStatusAndBJC(Integer uid, Integer userId, Integer pageNo, int i);

    /**
     * 被检查人员获取自己部门的不合格记录
     * @param uid 公司id
     * @param bm  部门
     * @param name gangwei
     * @param pageNo 页码
     * @param i     条目
     * @return
     */
    List<Map> findTCheckItemByBJC(Integer uid, String bm, String name, Integer pageNo, int i);

    /**
     * 政府端的复查记录(主页面进入)
     * @param userId
     * @param uid
     * @return
     */
    List<Map<String, Object>> selectRecheckByCheckId2ZF(String userId, String uid);

    /**
     * 企业端的检查人员
     * @param userId
     * @param uid
     */
    List<Map<String, Object>> selectRecheckByCheckId2JCR(String userId, String uid);

    /**
     * 企业端的检查人员
     * @param userId
     * @param uid
     */
    List<Map<String, Object>> selectRecheckByCheckId2BJCR(String userId, String uid);
}
