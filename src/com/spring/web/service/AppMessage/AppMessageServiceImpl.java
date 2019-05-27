package com.spring.web.service.AppMessage;

import com.spring.web.dao.AppControllerMessageMapper;
import com.spring.web.dao.ZzjgDepartmentMapper;
import com.spring.web.dao.ZzjgPersonnelMapper;
import com.spring.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("AppMessageService")
public class AppMessageServiceImpl implements AppMessageService {

    @Autowired
    private AppControllerMessageMapper appControllerMessageMapper;
    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;
    @Autowired
    private ZzjgDepartmentMapper zzjgDepartmentMapper;

    /**
     * 查询通知消息
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<Messages> findMessageList(String userId,Integer pageNo,Integer pageSize) {
        return  appControllerMessageMapper.findMessageList(userId,pageNo,pageSize);
    }

    @Override
    public Messages findMessageDetail(String id) {
        return appControllerMessageMapper.findMessageDetail(id);
    }

    /**
     * 获取已检查的检查记录
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<Map> findTCheckList(Integer userId,Integer pageNo,Integer pageSize) {
        return appControllerMessageMapper.findTCheckList(userId,pageNo,pageSize);
    }

    /**
     * 获取不合格项
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<Map> findTCheckListByStatus(String userId, Integer pageNo, Integer pageSize) {
        return appControllerMessageMapper.findTCheckListByStatus(userId,pageNo,pageSize);
    }


    /**
     * 被检查人员获取自己部门的检查记录
     * @param uid    企业id
     * @param userId 人员id
     * @param pageNo 页码
     * @param i      条数
     * @return
     */
    @Override
    public List<Map> findTCheckListByStatusAndBJC(Integer uid, Integer userId, Integer pageNo, int i) {
        // 1. 获取公司
        ZzjgPersonnel zzjg = zzjgPersonnelMapper.selectByPrimaryKey(userId);
        ZzjgDepartment zzjgDepartment = zzjgDepartmentMapper.selectByPrimaryKey(zzjg.getDpid());

        List<Map> list = appControllerMessageMapper.findTCheckListByStatusAndBJC(uid, zzjgDepartment.getName(), pageNo, i);

        return list;
    }
    /**
     * 被检查人员获取自己部门的不合格记录
     * @param uid 公司id
     * @param bm  部门
     * @param name gangwei
     * @param pageNo 页码
     * @param i     条目
     * @return
     */
    @Override
    public List<Map> findTCheckItemByBJC(Integer uid, String bm, String name, Integer pageNo, int i) {
        List<Map> list =appControllerMessageMapper.findTCheckItemByBJC(uid,bm,name,pageNo,i);
        return list;
    }

    /**
     * 政府端检查人员获取复查记录(合格不合格都要有)
     * @param userId 自己的id
     * @param uid    公司id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectRecheckByCheckId2ZF(String userId, String uid) {
        return appControllerMessageMapper.selectRecheckByCheckId2ZF(Integer.valueOf(userId),Integer.valueOf(uid));
    }

    /**
     * 企业端的检查人员获取复查记录
     * @param userId  用户id
     * @param uid     公司id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectRecheckByCheckId2JCR(String userId, String uid) {
        return appControllerMessageMapper.selectRecheckByCheckId2JCR(Integer.valueOf(userId),Integer.valueOf(uid));
    }

    /**
     * 企业端被检查人获取复查记录
     * @param userId    用户id
     * @param uid       公司id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectRecheckByCheckId2BJCR(String userId, String uid) {
        ZzjgPersonnel zzjg = zzjgPersonnelMapper.selectByPrimaryKey(Integer.valueOf(userId));
        return appControllerMessageMapper.selectRecheckByCheckId2BJCR(zzjg.getDpid(),uid);
    }

    @Override
    public TCheck findCheckById(String checkId) {
        return appControllerMessageMapper.findCheckById(checkId);
    }

    @Override
    public ZzjgPersonnel findPersonnel(String userId) {
        return appControllerMessageMapper.findPersonnel(userId);
    }

    /**
     * 根据检查记录id 获取整改的详情信息
     * @param checkId
     * @return
     */
    @Override
    public List<Map> findRectificationList(String checkId) {
        return appControllerMessageMapper.findRectificationList(checkId);
    }

    @Override
    public TRectification findTRectification(String checkId) {
        return appControllerMessageMapper.findTRectification(checkId);
    }

    @Override
    public List<Map<String, Object>> selectRecheckByCheckId(Integer checkId) {
        return appControllerMessageMapper.selectRecheckByCheckId(checkId);
    }

    @Override
    public List<Map<String, Object>> selectRecheckByCheckId2(Integer userId) {
        return appControllerMessageMapper.selectRecheckByCheckId2(userId);
    }

    @Override
    public List<Map> selectCheckByFlag() {
        return appControllerMessageMapper.selectCheckByFlag();
    }




}
