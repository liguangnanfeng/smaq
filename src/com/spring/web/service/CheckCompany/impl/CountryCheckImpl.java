package com.spring.web.service.CheckCompany.impl;

import com.alibaba.fastjson.JSON;
import com.spring.web.dao.*;
import com.spring.web.model.*;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.request.SaveDataMessage;
import com.spring.web.model.request.SaveDataMessageItem;
import com.spring.web.service.CheckCompany.CountryCheck;
import com.spring.web.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/08 18:02
 * <p>
 * 小程序政府端进行检查,获取数据
 * 现在是check 对应第一个item 和part part 和item是意义对应的关系
 *
 */
@Service
@Transactional
@SuppressWarnings("all")
public class CountryCheckImpl implements CountryCheck {

    /* 企业数据*/
    @Autowired
    private CompanyMapper companyMapper;

    /* 村级数据*/
    @Autowired
    private VillageMapper villageMapper;

    /*镇级数据*/
    @Autowired
    private TownMapper townMapper;


    /*区数据*/
    @Autowired
    private DistrictMapper districtMapper;

    /*检查表信息*/
    @Autowired
    private ACompanyManualMapper companyManualMapper;

    /*模版表信息*/
    @Autowired
    private TModelMapper modelMapper;

    /*危险警示*/
    @Autowired
    private ADangerManualMapper dangerManualMapper;

    /*检查总表*/
    @Autowired
    private TCheckMapper tCheckMapper;

    /*装置或设施表*/
    @Autowired
    private TCheckPartMapper tCheckPartMapper;

    /*自查分类级别表*/
    @Autowired
    private TLevelMapper tLevelMapper;

    /*检查检查项表*/
    @Autowired
    private TCheckItemMapper tCheckItemMapper;

    /*企业人员表*/
    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;

    /*模版模块表*/
    @Autowired
    private TModelPartMapper tModelPartMapper;

    /*企业等级表*/
    @Autowired
    private TIndustryMapper tIndustryMapper;

    @Autowired
    private TItemMapper tItemMapper;

    @Autowired
    private TItemSeriousMapper tItemSeriousMapper;

    /*短信服务*/
    @Autowired
    private SmsUtil smsUtil;

    /**总人员表*/
    @Autowired
    private UserMapper userMapper ;

    /**复查记录*/
    @Autowired
    private TRecheckMapper tRecheckMapper;

    /**复查消息记录表*/
    @Autowired
    private TRectificationConfirmMapper tRectificationConfirmMapper;

    /**复查详情表*/
    @Autowired
    private TRecheckItemMapper tRecheckItemMapper;
    /**
     * 村级查询企业
     *
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCompanyByVillageId(Integer id) {
        List<Map<String, Object>> list = companyMapper.selectByVillageId(id);

        return list;
    }

    /**
     * 根据镇级查询所对应的村
     *
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectVillageBytownId(Integer id) {
        List<Map<String, Object>> list = villageMapper.selectByTownId(id);

        return list;
    }

    /**
     * 根据区级id查询所有的镇
     * <p>
     * List<Map<String,Object>>  list =districtMapper.selectByDistrivtId(id);
     *
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectTownByDistrictId(Integer id) {
        List<Map<String, Object>> list = townMapper.selectByDistrictId(id);
        return list;
    }

    /**
     * 查询所有的区
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectDistrict() {
        List<Map<String, Object>> list = districtMapper.selectByDistrivtId();
        return list;
    }

    /**
     * 查询政府端登录用户的详细信息
     * @param id
     * @param flag
     * @return
     */
    @Override
    public Map selectParticular(Integer id, Integer flag) {
        Map map = new HashMap();
        if(flag==4){ //村级

           map = villageMapper.selectParticularByUid(id);

        }else if(flag==3){  //镇级

            map = townMapper.selectParticularByUid(id);
        }else if(flag==6){   //区级
            map = districtMapper.selectParticularByUid(id);
        }else if(flag==7){   //市级
            map.put("name", "无锡市");
        }else{
            map.put("name", "怎么进来的");

        }
        return map;
    }

    /**
     * TODO 政府端查询检查记录
     * @param id
     * @return
     */
    @Override
    public List findRecordByCreateUser(Integer id) {
        List<Map> list = tCheckMapper.selectRecordByCreateUser(id);
        return list;
    }



    /**
     * 政府端保存模版
     *
     * @param checkItem
     * @param officials
     * @return
     */
    @Override
    public Integer saveCheck(CheckItem checkItem, Officials officials, Integer uid) {
        try {

            // 1. 保存t_industry_tbl表 返回id
            Integer industryId = checkItem.getCheckLevels().get(0).getIndustryId();

            // 2. 保存t_level_tbl AND t_item_tbl 返回id集合
            List<Integer> levels = null;

            // 3. 保存model信息并返回id
            Integer modelId = saveTmodel(checkItem, officials,industryId,uid);

            // 2. 保存check表数据并返回id
            Integer checkId = saveCheckTbl(checkItem, officials, modelId, industryId,uid);

          /*  // 4. 保存checkPart数据并返回id
            TCheckPart tCheckPart = saveCheckPart(checkItem, levels, checkId);
            tCheckPartMapper.insertSelective(tCheckPart);
            int tCheckPartId = tCheckPart.getId(); // 获取checkPartId*/

            // 5. 保存Check_item 表数据并返回id TODO
            saveCheckTtem(checkItem, officials, checkId);

            // 6. 保存model_part() 计划检查模块表
            saveTmodelPath(modelId, checkItem, levels);

            return modelId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存失败");

        }

    }

    /**
     * 检查计划模板表
     * 传递的模块信息进行保存括其他的数据进行保存
     * 保存数据到检查表,并获取检查表id信息,
     * 检查类型 1: 自查 2: 第三方
     * 危险类型 1  基础 2  现场  3 高危检查
     * 行业id 就是根据保存的数据
     *
     * @param
     * @return
     */
    private Integer saveTmodel(CheckItem checkItem, Officials officials,Integer industryId,Integer uid) {

        // 获取部门 id
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        TModel tModel = new TModel();
        tModel.setTitle(checkItem.getTemplate()); // 检查名称
        tModel.setUserId(uid); // 检查人员所属部门的id
        tModel.setFlag(3); // 检查类型 第三方
        tModel.setPart(checkLevels.get(0).level1);              // 被检查的部门
        tModel.setIndustryId(industryId);         // 被检查的行业id
        if (checkItem.getCheckType() == null) {
            checkItem.setCheckType(1);
        }

        if(-2==checkItem.getCheckType()){
            tModel.setIndustryType(2);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
        }else if(-1==checkItem.getCheckType()){
            tModel.setIndustryType(1);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
        }else{
            tModel.setIndustryType(3);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
        }

        tModel.setType(checkItem.getTitle());    //  1. 日常 2. 定期 3. 临时
        tModel.setCreateTime(new Date()); // 模版的创建时间

        // TODO 表示是定期检查 要确定检查时间
        if (2 == checkItem.getTitle()) {
            if (checkItem.cycle == null) {
                checkItem.cycle = 7;
            }
            tModel.setCycle(checkItem.getCycle()); //定期时间
            Date d = new Date();
            long time = d.getTime();

            int i = checkItem.getCycle() * 24 * 60 * 60 * 1000;
            long l = time + i;
            Date t = new Date(l);

            int a = (checkItem.getCycle() - 1) * 24 * 60 * 60 * 1000;
            long b = time + a;
            Date c = new Date(b);

            tModel.setNextTime(c);     // 下次生成的时间
            tModel.setNextCheckTime(t); // 定期检查的时间
            tModel.setOpen(1);          // 定期生成
        }
        modelMapper.insertSelective(tModel);
        Integer modelId = tModel.getId(); //获取模版id

        return modelId;
    }

    /**
     * 检查表
     * flag         '1 企业自查 2行政执法 3第三方排查',
     * title         '检查标题',
     * depart         '被检查部门',
     * depart_img     '部门照片',
     * user_id         '企业id',
     * create_user     '创建人id',
     * model_id        '模板id',
     * type            '1 日常 2定期 3临时',
     * industry_id     '检查行业id',
     * industry_type   '1 基础 2现场 3高危',
     * expect_time     '预计检查时间',
     * real_time       '实际检查时间',
     * cheker          '检查人',
     * contact        '检查人联系方式',
     * dapart_contact  '被检查部门负责人',
     * status          '1 未检查 2 已检查',
     * create_time     '创建时间',
     * del             '1 删除',
     * check_company   '检查单位',
     * <p>
     * t_check_part_tbl
     * levels   varchar(500) null comment '检查分类s',
     * name     varchar(50)  null comment '部位或装置名称',
     * part_img varchar(200) null comment '部门照片'
     *
     * @param checkItem 用户返回数据
     * @param zzjg      用户登陆信息
     * @param modelId   模版表id
     * @return
     */
    private Integer saveCheckTbl(CheckItem checkItem, Officials officials, Integer modelId, Integer industryId,Integer uid) {

        // 获取部门 id
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        TCheck tCheck = new TCheck();
        tCheck.setFlag(2);       //行政检查
        tCheck.setTitle(checkItem.getTemplate());     //被检查的标题
        tCheck.setDepart(checkLevels.get(0).level1);    // 被检查的部门 TODO 政府端理论上是检查多个部门,这里应该是添加多个部门的信息
        tCheck.setUserId(uid);     // 被检查的企业的id
        tCheck.setCreateUser(officials.getId()); //创建人(检查人员的id)
        tCheck.setModelId(modelId);    // 模版id
        tCheck.setType(checkItem.getTitle());       // 1. 日常 2 定期  3 临时
        tCheck.setIndustryId(industryId); // 检查行业的id

        if (checkItem.getCheckType() == null) {
            checkItem.setCheckType(1);
        }
        if(-2==checkItem.getCheckType()){
            tCheck.setIndustryType(2); // 1. 基础 2. 现场 3. 高危
        }else if(-1==checkItem.getCheckType()){
            tCheck.setIndustryType(1); // 1. 基础 2. 现场 3. 高危
        }else{
            tCheck.setIndustryType(3); // 1. 基础 2. 现场 3. 高危
        }

        tCheck.setExpectTime(new Date()); // 预计检查时间
        tCheck.setRealTime(new Date());  // 实际检查时间
        tCheck.setCheker(officials.getName());            // 检查人
        tCheck.setContact(officials.getDepart());            // 检查人的部门
        tCheck.setDapartContact(String.valueOf(checkItem.getDepartmentId()));      // 被检查人的id
        tCheck.setStatus(0);              // 1. 未检查
        tCheck.setCreateTime(new Date()); // 创建时间
        User user = userMapper.selectByPrimaryKey(officials.getUid());
        tCheck.setCheckCompany(user.getUserName());         // 检查单位

        int i = tCheckMapper.insertSelective(tCheck);
        Integer checkId = tCheck.getId();  //获取检查表id

        return checkId;

    }

    /**
     * 装置设施表
     * check_id int          null,
     * levels   varchar(500) null comment '检查分类s',
     * name     varchar(50)  null comment '部位或装置名称',
     * part_img varchar(200) null comment '部门照片'
     * @return
     */
    private TCheckPart saveCheckPart(CheckItem checkItem, List<Integer> list, Integer tCheckId) {

        // 获取部门名称list的str
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        String NameStr = JSON.toJSONString(list);

        TCheckPart tCheckPart = new TCheckPart();
        tCheckPart.setCheckId(tCheckId);         // 检查表id
        tCheckPart.setName(checkLevels.get(0).level1);    // 部门或装置名称
        tCheckPart.setLevels(NameStr);           // 检查分类

        return tCheckPart;

    }

    /**
     * 检查项目表
     * t_check_item_tbl
     * content       '检查标准详情',
     * level_id      '检查分类',
     * levels      ,  检查的参照
     * reference     '检查参照',
     * part_id       '装置或设施id',
     * check_id     ,
     * status        '1 合格 2不合格 3已复查',
     * memo          '不合格描述',
     * files         '检查图片',
     * suggest       '1 立即整改 2限期整改',
     * deadline      '限期整改期限',
     * plan_time     '预计复查时间',
     * recheck_time  '实际复查时间',
     * recheck_file  '复查图片s',
     * recheck_memo  '复查描述'
     * <p>
     *
     * @return
     */
    private void saveCheckTtem(CheckItem checkItem, Officials officials, Integer CheckId) {

        // 获取检查标准详情
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();

        for (CheckLevel checkLevel : checkLevels) {
            List<TCheckItem> list = new LinkedList<>();
            TCheckPart tCheckPart = new TCheckPart(); // 检查岗位/部位表

            tCheckPart.setCheckId(CheckId);         // 检查表id
            tCheckPart.setName(checkLevels.get(0).level2);    // 岗位或部位名称
            int i = tCheckPartMapper.insertSelective(tCheckPart);

            TCheckItem tCheckItem = new TCheckItem();
            tCheckItem.setMemo(checkLevel.getFactors());//不合格描述
            tCheckItem.setLevelId(checkLevel.getId());  // 保存的conpanymanualId
            tCheckItem.setContent(checkLevel.getLevel4()); //检查标准详情
            tCheckItem.setLevelId(checkItem.getTitle());   //检查分类
            if(null==checkLevel.getLevel3()){
                tCheckItem.setLevels(checkLevel.getLevel1());   // 检查等级
            }else{
                tCheckItem.setLevels(checkLevel.getLevel3());   // 检查等级
            }
            tCheckItem.setReference(checkLevel.getReference());//检查参照

            // tCheckItem.setPartId(tCheckPart.getId());    // 装置与设施id
            tCheckItem.setCheckId(CheckId);   // 检查表id

            list.add(tCheckItem);

            tCheckItemMapper.insertThreeBath(list, CheckId, tCheckPart.getId());
        }

    }

    /**
     * 检查计划模板 模块表
     * name     varchar(30)   null comment '部门 或装置',
     * model_id int           null comment '模板id',
     * levels   varchar(2000) null comment '检查分类s',
     */
    private void saveTmodelPath(Integer Tmodel, CheckItem checkItem, List<Integer> list) {
        TModelPart tModelPart = new TModelPart();

        // 获取部门名称list的str
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        String str = new  String ();
        for (int i = 0; i < checkLevels.size(); i++) {
            departmentName.add(checkLevels.get(i).getLevel1());
            if(i<checkLevels.size()-1){
                str+=checkLevels.get(i).getId()+",";
            }else{
                str+=checkLevels.get(i).getId()+"";
            }
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        String NameStr = JSON.toJSONString(list);

        tModelPart.setModelId(Tmodel);

        tModelPart.setLevels(str);              //检查分类
        tModelPart.setName(departmentNametr);   // 检查的部门

        tModelPartMapper.insertSelective(tModelPart);

    }

    /**
     *    政府账号保存检查信息(第一次检查)
     * 1. 保存item数据
     * 2. 保存check数据
     * 3. 保存check_document 行政检查文书
     * 4. 发送文书
     * 5. 发送短信
     * @param saveDataMessageItem
     * @param officials
     * @param id
     * @return
     */
    @Override
    public String saveCheckMessage(SaveDataMessageItem saveDataMessageItem, Officials officials, Integer id) {
        try {
            Integer id1 = saveDataMessageItem.getList().get(0).getId();
            TCheckItem tCheckItem1 = tCheckItemMapper.selectByPrimaryKey(id1);
            // 1. 修改检查的数据
            TCheck tCheck = tCheckMapper.selectByPrimaryKey(tCheckItem1.getCheckId());
            tCheck.setStatus(2); // 表示已经检查
            tCheck.setCheker(officials.getName());  // 检查人员的名称
            tCheck.setExpectTime(new Date()); //预计的检查时间
            tCheck.setRealTime(new Date()); //实际的检查时间
            if(null!=saveDataMessageItem.getLongitude()){
                tCheck.setLongitude(saveDataMessageItem.getLongitude()+""); //经度
                tCheck.setLatitude(saveDataMessageItem.getLatitude()+"");  //维度
            }
            tCheckMapper.updateByPrimaryKey(tCheck); // 更新到数据库

            // 将信息进行保存 数据进行更新,数据的结构会更加的混乱
            List<SaveDataMessage> list = saveDataMessageItem.getList();
            for (SaveDataMessage saveDataMessage : list) {
                TCheckItem tCheckItem = tCheckItemMapper.selectByPrimaryKey(saveDataMessage.getId());
                // 对状态进行判断
                if ("1".equals(saveDataMessage.getValue())) {  // 表示检查合格
                    tCheckItem.setStatus(1); //合格
                } else {
                    // 表示不合格
                    TRectification tRectification = new TRectification();
                    tRectification.setCheckId(saveDataMessageItem.getCheckId()); // 检查表id
                    tRectification.setUserId(id); // 企业id
                    tRectification.setCreateUser(officials.getId()); // 创建人的id
                    tRectification.setCreateTime(new Date()); //生成时间
                    //item.setMemo(saveDataMessage.getMemo()); //不合格描述
                    tCheckItem.setStatus(2); //不合格
                    tCheckItem.setMemo(saveDataMessage.getMemo()); // 不合格描述
                    tCheckItem.setFiles(saveDataMessage.getFile()); // 不合格图片 应该是一个图片集合，但是现在没有集合
                    tCheckItem.setSuggest(1);  //   1. 立即整改 2. 限期整改
                    if(tCheckItem.getSuggest()==2){
                        tCheckItem.setDeadline(new Date()); // 限期整改时间

                    }
                    tCheckItem.setRecheckTime(new Date()); // 预计的复查时间
                }
                tCheckItemMapper.updateByPrimaryKey(tCheckItem);

            }
                // TODO 发送检查通知书

           // Sms(saveDataMessageItem.getList()); //短信

            return "保存成功";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 政府端保存复查记录
     *
     * @param saveDataMessageItem
     * @param officials
     * @return
     */
    @Override
    public String saveReviewData(SaveDataMessageItem saveDataMessageItem, Officials officials) {

        try {

            // id查询CheckItem
            TCheckItem item1 = tCheckItemMapper.selectAllById(saveDataMessageItem.getList().get(0).getId());

            // --------------------------------------------------------------------------------------------------
            // 复查意见表=> 主表
            TRecheck tRecheck = new TRecheck();
            tRecheck.setCheckId(item1.getCheckId()); // 检查表id
            tRecheck.setUserId(saveDataMessageItem.getUid()); // 企业id
            tRecheck.setCreateUser(officials.getId());  //检查人员id
            tRecheck.setCreateTime(new Date());    // 创建时间

            // 通过checkId获取公司的id
            TCheck tCheck1 = tCheckMapper.selectByPrimaryKey(saveDataMessageItem.getCheckId());

            User user = userMapper.selectByPrimaryKey(tCheck1.getUserId());
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
            tRecheck.setChecker(officials.getName());       // 检查人员名称
            tRecheck.setUserId(tCheck.getUserId());//被检查的企业id
            tRecheck.setDapartContact(tCheck.getDapartContact());   // 被的检查部门
            if(!flag){
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
            saveTRectificationConfirm(saveDataMessageItem);

            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * TODO 将不合格信息记录插入到 TRectification_tbl表中,一次检查就对应一条记录
     * 政府的检查记录是为了什么???
     * 为了pc显示整改详情内容进行显示每一次只查询一次数据
     */
    private void saveTRectification(Integer checkId,ZzjgPersonnel zzjg,SaveDataMessageItem saveDataMessageItem){
        // 保存检查结果整改意见表 保存的就是一次检查记录里面的数据
        TRectification tRectification = new TRectification();
        tRectification.setCheckId(checkId); // 检查表id
        tRectification.setUserId(zzjg.getUid()); // 企业id
        tRectification.setCreateUser(zzjg.getId()); // 创建人的id
        tRectification.setCreateTime(new Date()); //生成时间

        String str = new String();

        List<SaveDataMessage> list = saveDataMessageItem.getList();
        for (int i = 0; i < list.size(); i++) {
            if("2".equals(list.get(i))){
                // 表示检查不合格
                if(i<list.size()-1){
                    str+=list.get(i).getId()+",";
                }
                str+=list.get(i).getId();
            }

        }
        // 小程序一般要么就是立即整改要么就是限期整改
        if(null==saveDataMessageItem.getType()){
            //立即整改
            tRectification.setItem1(str); // 立即整改项

            long time = new Date().getTime();
            long i = 24 * 60 * 60;
            long l = time + i;
            Date date = new Date(l); // 一天后的时间
            tRectification.setDeadline(date); // 限期时间
            tRectification.setPlanTime(date); // 计划复查时间

        }else{
            //限期整改
            int i = Integer.parseInt(saveDataMessageItem.getType()) * 24 * 60 * 60; // 限期时间
            long time = new Date().getTime();
            long l = time + i; //
            Date date = new Date(l);

            tRectification.setItem2(str); //限期整改项
            tRectification.setDeadline(date); // 限期时间
            tRectification.setPlanTime(date); // 计划复查时间
        }

        //tRectificationMapper.insertSelective(tRectification);
    }


    /**
     * TODO 修改tRectificationConfirm数据
     * 当为空的时候就不进行检查
     */
    private void saveTRectificationConfirm(SaveDataMessageItem saveDataMessageItem){
        List<SaveDataMessage> list = saveDataMessageItem.getList();
        for (SaveDataMessage saveDataMessage : list) {
            List<TRectificationConfirm> tRectificationConfirms = tRectificationConfirmMapper.selectByCheckItemId(saveDataMessage.id);

            if(null !=tRectificationConfirms && tRectificationConfirms.size()>0  ){
                for (TRectificationConfirm tRectificationConfirm : tRectificationConfirms) {
                    if("2".equals(saveDataMessage.getValue())){
                        tRectificationConfirm.setStatus(0); //表示未合格
                    }else{
                        tRectificationConfirm.setStatus(1); //表示合格
                    }
                    tRectificationConfirmMapper.updateByTRectificationConfirm(tRectificationConfirm);
                }
            }
        }
    }
}
