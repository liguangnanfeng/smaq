package com.spring.web.service.CheckCompany.impl;

import com.spring.web.dao.*;
import com.spring.web.model.*;
import com.spring.web.model.request.SaveDataMessage;
import com.spring.web.model.request.SaveDataMessageItem;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.service.CheckCompany.SaveMessageService;
import com.spring.web.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 18:33
 */
@Service
@Transactional
@SuppressWarnings("all")
public class SaveDataImpl implements SaveMessageService {

    /**
     * 检查 检查项
     */
    @Autowired
    private TCheckItemMapper tCheckItemMapper;

    /**
     * 检查部位
     */
    @Autowired
    private TCheckPartMapper tCheckPartMapper;


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
     * 模版表
     */
    @Autowired
    private TModelMapper modelMapper;

    /**
     * 复查记录表
     */
    @Autowired
    private TRectificationConfirmMapper tRectificationConfirmMapper;
    /**
     * 短信功能
     */
    @Autowired
    private SmsUtil smsUtil;
    /**
     * 查询部门短信单独发送
     */
    @Autowired
    private ACompanyManualMapper aCompanyManualMapper;

    /**
     * 保存检查信息,并进行返回结果消息
     * valve   1 :合格  2 : 不合格
     *
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
                    item.setMemo(saveDataMessage.getMemo()); //不合格描述
                    item.setFiles(saveDataMessage.getFile()); //不合规照片

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
                    return null;
                }

                // 保存checkItem表数据
                tCheckItemMapper.updateByPrimaryKey(item);
                // 直接将check表数据进行更新 被检查的负责人也可以进行导入
                TCheck tCheck = tCheckMapper.selectByPrimaryKey(item.getCheckId());
                tCheck.setStatus(2); // 已检查
                tCheck.setExpectTime(new Date()); //预计的检查时间
                tCheck.setRealTime(new Date()); //实际的检查时间
                tCheck.setLongitude(saveDataMessageItem.getLongitude()); //经度
                tCheck.setLatitude(saveDataMessageItem.getLatitude());  //维度
                tCheckMapper.updateByPrimaryKey(tCheck); // 更新到数据库

            }
            // 内容发送短信内容
            Sms(saveDataMessageItem.getList());
            return "插入成功";

        } catch (Exception e) {
            // 出现异常就表示插入失败
            e.printStackTrace(); //打印异常
            return null;
        }

    }

    /**
     * 发送短信
     * 每一个岗位出现不合格，就给这个岗位被检查人员发送短信
     */
    private void Sms(List<SaveDataMessage> list) {
        List<String> personnelList = null;

        for (SaveDataMessage saveDataMessage : list) {
            if ("2".equals(saveDataMessage.getValue())) {
                Integer id = saveDataMessage.getId();
                ACompanyManual companyManual = aCompanyManualMapper.selectByPrimaryKey(id);
                personnelList = zzjgPersonnelMapper.selectByDpid(companyManual.getDmid());
            }
        }
        if (null != personnelList && personnelList.size() > 0) {
            for (String mobile : personnelList) {
                System.out.println("发送短信==============");
                smsUtil.sendSMS(mobile, "112221");
                System.out.println("发送短信==============");

            }
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

        List<Map<String, Object>> maps = tCheckItemMapper.selectDangerByCheckId(checkId, 2);

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
            return null;
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
     * 根据复查信息进行保存
     * TODO 保存item 的时候会item有多个,但是在消息中的回复可能只有一条,所以会有一个空值
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

            boolean flag = false;

            // 循环遍历是否有未检查项
            for (SaveDataMessage saveDataMessage : saveDataMessageItem.getList()) {
                TCheckItem item = tCheckItemMapper.selectAllById(saveDataMessage.getId());
                if ("2".equals(saveDataMessage.getValue())) {
                    tRecheck.setStatus(1);       // 1 未全部整改  2 全部整改
                    flag = false;
                    break;
                } else if ("1".equals(saveDataMessage.getValue())) {
                    flag = true;
                    tRecheck.setStatus(2);       // 1 未全部整改  2 全部整改

                }
            }

            TCheck tCheck = tCheckMapper.selectByPrimaryKey(item1.getCheckId());
            tRecheck.setChecker(zzjg.getName());       // 检查人员名称
            tRecheck.setDapartContact(tCheck.getDapartContact());   // 被检查部门的负责人
            if (!flag) {
                int i = 7 * 24 * 60 * 60; // 限期时间
                long time = new Date().getTime();
                long l = time + i; //
                Date date = new Date(l);
                tRecheck.setNextTime(date);      // 未合格项 限期检查时间
            }
            tRecheckMapper.insertSelective(tRecheck);
            Integer id = tRecheck.getId(); // 获取到主表id
// -----------------------------------------------------------------------------------------------------------------------------

            List<SaveDataMessage> list = saveDataMessageItem.getList();
            // 在进行便利进行保存 t_recheck_item_tbl  t_rectifiction_confirm 表
            for (SaveDataMessage saveDataMessage : list) {

                // 修改 t_check_item_tbl 数据为合格不合格;
                TCheckItem checkItem = tCheckItemMapper.selectAllById(saveDataMessage.getId());

                // TODO 添加t_recheck_item_tbl表数据
                TRecheckItem tRecheckItem = new TRecheckItem();

                if ("1".equals(saveDataMessage.getValue())) {
                    checkItem.setStatus(3); // 复查成功
                    tRecheckItem.setStatus(2); //表示复查成功
                    //tRectificationConfirm.setStatus(1);
                } else if ("2".equals(saveDataMessage.getValue())) {

                    checkItem.setStatus(2); //复查不合格
                    checkItem.setRecheckFile(saveDataMessage.getFile());//复查照片
                    checkItem.setPlanTime(new Date());                  // 实际的复查时间
                    checkItem.setMemo(saveDataMessage.getMemo());      // 复查描述

                    tRecheckItem.setStatus(3); //表示复查不合格
                    tRecheckItem.setFile(saveDataMessage.getFile());    //图片
                    tRecheckItem.setDeadline(new Date());
                    tRecheckItem.setMemo(saveDataMessage.getMemo());  // 复查描述

                }

                tRecheckItem.setCheckItemId(checkItem.getCheckId()); // 检查项目表id
                tRecheckItem.setRecheckId(id);                  // 复查主表id
                tRecheckItem.setDeadline(new Date());           // 创建时间
                tCheckItemMapper.updateByPrimaryKey(checkItem);
                tRecheckItemMapper.insertSelective(tRecheckItem);


            }
            saveTRectificationConfirm(saveDataMessageItem); // 修改数据
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据模版id查询检查信息并进行返回
     * 注: 5/6 还是单部门进行插入数据
     * ------------------------------------------------
     * 一个模版对应一个部门信息,就是相当于查询一条记录
     * 在进行模版查询的时候,有两种情况,
     * 一种是自定义检查==> 肯定是空的 ==> 一种是本来就存在数据
     *
     * @param modelId
     */
    @Override
    public CheckItemS findCheckItemByModelId(Integer modelId) {

        CheckItemS checkItemS = new CheckItemS();

        // 通过modelId
        TModel tModel = modelMapper.selectByPrimaryKey(modelId);
        tModel.setUseTime(new Date()); // 模版的使用时间
        modelMapper.updateByPrimaryKey(tModel);

        // 每一次都是查询最开始的那一条检查记录然后进行复制保存
        // 这时候按照时间的进行检查，找到最早的那一个存储的模板，然后进行修改保存
        TCheck tCheck = tCheckMapper.selectOldByModelId(tModel.getId());

        Integer checkId = insertCheck(tCheck.getId());  //表示是新的数据,然后将新的数据进行传递

        checkItemS.setLevle1(tCheck.getDepart()); //  部门信息
        checkItemS.setType(tModel.getIndustryType());              // 检查类型
        // 查询风险点数据
        List<Map> list = tCheckItemMapper.selectAllByCheckId(checkId);
        checkItemS.setItems(list);
        checkItemS.setCheckId(checkId);

        return checkItemS;

    }

    /**
     * TODO 根据最早插入的check数据,从新生成一条新的数据,并进行返回
     * 根据检查记录id获取数据,part item 进行新的数据的修改
     * part和item一一对应
     *
     * @param checkId
     * @return
     */
    private Integer insertCheck(Integer checkId) {

        // 获取对应的检查表的数据
        TCheck tCheck = tCheckMapper.selectByPrimaryKey(checkId); //获取主表
        List<TCheckPart> tCheckParts = tCheckPartMapper.findAllByCheckId(checkId);  //获取岗位
        List<TCheckItem> tCheckItems = tCheckItemMapper.selectItemByCheckId(checkId); //获取详情

        // 新增 tCheck
        tCheck.setStatus(1); //表示未检查
        tCheck.setId(null);
        tCheck.setExpectTime(new Date());// 预计的检查时间
        tCheck.setCreateTime(new Date()); //创建时间
        tCheck.setRealTime(new Date()); //实际检查时间

        tCheckMapper.insertSelective(tCheck);
        Integer tCheckId = tCheck.getId(); //获取检查的id

        // 现在唯一能够确定的就是part和item一一对应
        for (int i = 0; i < tCheckParts.size(); i++) {
            TCheckPart tCheckPart = tCheckParts.get(i);

            tCheckPart.setCheckId(tCheckId);
            tCheckPart.setId(null);
            tCheckPartMapper.insertSelective(tCheckPart);
            Integer checkPartId = tCheckPart.getId();
            TCheckItem tCheckItem = tCheckItems.get(i);
            tCheckItem.setId(null);
            tCheckItem.setCheckId(tCheckId);
            tCheckItem.setPartId(checkPartId);
            tCheckItem.setStatus(null);
            tCheckItem.setSuggest(null);
            tCheckItem.setDeadline(null);
            tCheckItem.setPlanTime(null);
            tCheckItem.setRecheckTime(null);

            tCheckItemMapper.insertSelective(tCheckItem);
        }
        // 新增tCheckPart 能确定只有一条记录所有现在出现的就是新的记录数据
        return tCheckId;
    }


    /**
     * TODO 修改tRectificationConfirm数据
     * 当为空的时候就不进行检查
     */
    private void saveTRectificationConfirm(SaveDataMessageItem saveDataMessageItem) {
        List<SaveDataMessage> list = saveDataMessageItem.getList();
        for (SaveDataMessage saveDataMessage : list) {
            List<TRectificationConfirm> tRectificationConfirms = tRectificationConfirmMapper.selectByCheckItemId(saveDataMessage.id);

            if (null != tRectificationConfirms && tRectificationConfirms.size() > 0) {
                for (TRectificationConfirm tRectificationConfirm : tRectificationConfirms) {
                    if ("2".equals(saveDataMessage.getValue())) {
                        tRectificationConfirm.setStatus(0); //表示未合格
                    } else {
                        tRectificationConfirm.setStatus(1); //表示合格
                    }
                    tRectificationConfirmMapper.updateByTRectificationConfirm(tRectificationConfirm);
                }


            }

        }

    }

}
