package com.spring.web.service.CheckCompany.impl;

import com.spring.web.dao.*;
import com.spring.web.model.*;
import com.spring.web.model.request.SaveDataMessage;
import com.spring.web.model.request.SaveDataMessageItem;
import com.spring.web.service.CheckCompany.SaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 18:33
 */
@Service
public class SaveDataImpl implements SaveMessageService {

    /**
     * 检查 检查项
     */
    @Autowired
    private TCheckItemMapper tCheckItemMapper;

    /**
     * 整改记录表
     */
    @Autowired
    private TRectificationMapper tRectificationMapper;

    /**
     * 检查主表
     */
    @Autowired
    private TCheckMapper tCheckMapper;

    /**
     * 复查意见表主表
     */
    @Autowired
    private TRecheckMapper tRecheckMapper;

    /**
     * 复查意见表
     */
    @Autowired
    private TRecheckItemMapper tRecheckItemMapper;

    /**
     * 主账号
     */
    @Autowired
    private UserMapper userMapper;
    /**
     * 用户表
     */
    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;


    /**
     * 保存检查信息,并进行返回结果消息
     * valve   1 :合格  2 : 不合格
     * @param
     */
    @Override
    public String saveCheckMessage(SaveDataMessageItem saveDataMessageItem, ZzjgPersonnel zzjg) {

        try {

            for (SaveDataMessage saveDataMessage : saveDataMessageItem.getList()) {

                // id查询CheckItem
                TCheckItem item = tCheckItemMapper.selectAllById(saveDataMessage.getId());

                if (item == null) {
                    // 表示没有数据 直接返回
                    return null;
                }


                // 对前端用户状态进行判断 1 :合格 2 :不合格
                if ("1".equals(saveDataMessage.getValue())) {
                    item.setStatus(1); // 状态 合格

                } else if ("2".equals(saveDataMessage.getValue())) {

                    // 不合格下达检查结果整改意见表
                    TRectification tRectification = new TRectification();
                    tRectification.setCheckId(item.getCheckId()); // 检查表id
                    tRectification.setUserId(zzjg.getUid()); // 企业id
                    tRectification.setCreateUser(zzjg.getId()); // 创建人的id
                    tRectification.setCreateTime(new Date()); //生成时间

                    item.setStatus(2); // 状态 不合格

                    if (saveDataMessageItem.getType() == null) { // 立即整改

                        long time = new Date().getTime();
                        long i = 24 * 60 * 60; //一天
                        long l = time + i; //
                        Date date = new Date(l); // 一天后的时间
                        item.setSuggest(1);  // 1.立即整改 2. 限期整改
                        item.setPlanTime(date); //预期检查时间
                        tRectification.setDeadline(date); // 限期时间
                        tRectification.setPlanTime(date); // 计划复查时间
                        tRectification.setItem1(item.getContent()); // 立即整改项

                    } else {
                        //表示限期整改
                        int i = Integer.parseInt(saveDataMessageItem.getType()) * 24 * 60 * 60; // 限期时间
                        long time = new Date().getTime();
                        long l = time + i; //
                        Date date = new Date(l);

                        item.setDeadline(date); // 限期整改期限
                        item.setSuggest(2);  // 1.立即整改 2. 限期整改
                        item.setPlanTime(date); // 预期检查时间
                        tRectification.setDeadline(date); // 限期时间
                        tRectification.setPlanTime(date); // 计划复查时间
                        tRectification.setItem2(item.getContent()); //限期整改项

                    }
                    // 保存检查结果整改意见表
                    tRectificationMapper.insertSelective(tRectification);

                } else {
                    // 第三种情况
                    return null;
                }

                tCheckItemMapper.updateByPrimaryKey(item);
                // 直接将check表数据进行更新
                TCheck tCheck = tCheckMapper.selectByPrimaryKey(item.getCheckId());
                tCheck.setStatus(2); // 已检查
                tCheckMapper.updateByPrimaryKey(tCheck);

            }

            return "插入成功";

        } catch (Exception e) {
            // 出现异常就表示插入失败
            e.printStackTrace(); //打印异常
            return null;

        }

    }

    /**
     * 对表数据进行复查
     * 传递checkId查询数据
     *
     * @param checkId
     * @return
     */
    @Override
    public List findItemByCheckId(Integer checkId) {
        // List tCheckItemMapper.findDangerByCheckId(checkId,2);
        List<Map<String, Object>> maps = tCheckItemMapper.selectDangerByCheckId(checkId, 2);
        if (maps == null) {
            return null;
        }
        return maps;
    }

    /**
     * 根据当前登陆用户的id查询所有的检查记录
     *
     * @param zzjg
     */

    @Override
    public List<Map> findCheckItemById(ZzjgPersonnel zzjg) {
        Integer id = zzjg.getId();  //当前登陆用户的id
        Integer uid = zzjg.getUid(); //当前登陆用户的所属公司的id

        // 根据id 和Uid 查询所有的检查表

        List<TCheck> list = tCheckMapper.findAllByUidAndUserId(id, uid);
        if (list == null || list.size() == 0) {
            // 没有数据,直接返回

        }
        List<Map> lists = new ArrayList<>();

        for (TCheck tCheck : list) {
            Map<String, Object> map = new HashMap<>();
            // 去查询数据,并进行返回,但是太消耗数据库的性能,但是为了提交完成需求

            Integer num = tCheckItemMapper.selectNumBytCheckId(tCheck.getId());// 获取不合格的数量
            map.put("level1", tCheck.getDepart()); // 部门
            Date createTime = tCheck.getCreateTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(createTime);
            map.put("createTime", format); //创建时间
            map.put("checkId", tCheck.getId()); //检查表id
            map.put("title", tCheck.getTitle());//检查表标题
            map.put("number", num); //不合格项的数量
            map.put("type", tCheck.getType()); // 检查类型
            lists.add(map);

        }

        return lists;

    }

    /**
     * 根据复查信息进行保存 只要里面有一条不合格就表示这次检查不合格
     *
     * @param saveDataMessageItem
     * @param zzjg
     * @return
     */
    @Override
    public String saveReviewData(SaveDataMessageItem saveDataMessageItem, ZzjgPersonnel zzjg) {
        try {

            // id查询CheckItem
            TCheckItem item1 = tCheckItemMapper.selectAllById(saveDataMessageItem.getList().get(0).getId());

            // --------------------------------------------------------------------------------------------------
            // 复查意见表=> 主表
            TRecheck tRecheck = new TRecheck();
            tRecheck.setCheckId(item1.getCheckId()); // 检查表id
            tRecheck.setUserId(zzjg.getUid()); // 总公司id
            tRecheck.setCreateUser(zzjg.getId());  //检查人员id
            tRecheck.setCreateTime(new Date());    // 创建时间

            User user = userMapper.selectByPrimaryKey(zzjg.getUid());
            tRecheck.setCheckCompany(user.getUserName()); //检查的公司名称

            for (SaveDataMessage saveDataMessage :saveDataMessageItem.getList()) {
                TCheckItem item = tCheckItemMapper.selectAllById(saveDataMessage.getId());
                if ("1".equals(saveDataMessage.getValue())) {
                    tRecheck.setStatus(1);       // 1 未全部整改  2 全部整改
                } else if ("2".equals(saveDataMessage.getValue())) {

                    tRecheck.setStatus(2);       // 1 未全部整改  2 全部整改
                    break;
                }

            }
            //表示限期整改
            int i = 7 * 24 * 60 * 60; // 限期时间
            long time = new Date().getTime();
            long l = time + i; //
            Date date = new Date(l);
            tRecheck.setNextTime(date);      // 未合格项 限期检查时间
            tRecheck.setChecker(zzjg.getName());       // 检查人员名称

            //ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectByPrimaryKey(saveDataMessageItem.zrrId);
           // tRecheck.setDapartContact(zzjgPersonnel.getName());             // 被检查部门的负责人
            int i1 = tRecheckMapper.insertSelective(tRecheck);
            Integer id = tRecheck.getId(); // 获取到主表id

            // --------------------------------------------------------------------------------------------------

            List<SaveDataMessage> list = saveDataMessageItem.getList();
            for (SaveDataMessage saveDataMessage : list) {
                TCheckItem item = tCheckItemMapper.selectAllById(saveDataMessage.getId());
                if ("1".equals(saveDataMessage.getValue())) {
                    item.setStatus(3); // 复查成功
                } else if ("2".equals(saveDataMessage.getValue())) {

                    item.setStatus(2);

                }

                TRecheckItem tRecheckItem =new TRecheckItem();
                tRecheckItem.setCheckItemId(item.getCheckId()); // 检查项目表id
                tRecheckItem.setRecheckId(id);                  // 复查主表id
                tRecheckItem.setDeadline(new Date());           // 创建时间

                tRecheckItemMapper.insertSelective(tRecheckItem);

                tCheckItemMapper.updateByPrimaryKey(item);// 进行更新

            }
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
