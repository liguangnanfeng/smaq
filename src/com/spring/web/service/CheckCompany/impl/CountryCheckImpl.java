package com.spring.web.service.CheckCompany.impl;

import com.alibaba.fastjson.JSON;
import com.spring.web.dao.*;
import com.spring.web.model.*;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.request.SaveDataMessage;
import com.spring.web.model.request.SaveDataMessageItem;
import com.spring.web.service.CheckCompany.CountryCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/08 18:02
 * <p>
 * 小程序政府端进行检查,获取数据
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

    /**
     * 镇级数据
     */
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
     * 政府端保存模版
     *
     * @param checkItem
     * @param officials
     * @return
     */
    @Override
    public Integer saveCheck(CheckItem checkItem, Officials officials, Integer id) {
        try {
            // 1. 保存model信息并返回id
            TModel tModel = saveTmodel(checkItem, officials);
            modelMapper.insertSelective(tModel);
            Integer modelId = tModel.getId(); //获取模版id
            // 2. 保存check表数据并返回id
            TCheck tCheck = saveCheckTbl(checkItem, officials, modelId, id);
            int i = tCheckMapper.insertSelective(tCheck);
            Integer checkId = tCheck.getId();  //获取检查表id
            // 3. 保存并获取检查分类id
            List<Integer> list = saveTLevel(checkItem);
            // 4. 保存checkPart数据并返回id
            TCheckPart tCheckPart = saveCheckPart(checkItem, list, checkId);
            tCheckPartMapper.insertSelective(tCheckPart);
            int tCheckPartId = tCheckPart.getId(); // 获取checkPartId

            // 5. 保存Check_item 表数据并返回id TODO
            saveCheckTtem(checkItem, officials, checkId, tCheckPartId);

            // 6. 保存model_part() 计划检查模块表
            saveTmodelPath(modelId, checkItem, list);

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
    private TModel saveTmodel(CheckItem checkItem, Officials officials) {

        // 获取部门 id
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        // 获取行业名称
        String name = dangerManualMapper.selectIndustryByid(checkItem.getCheckLevels().get(0).id);

        // 获取行业id
        int IndustryId = companyManualMapper.selectDmidById(name);

        TModel tModel = new TModel();
        tModel.setTitle(checkItem.getTemplate()); // 检查名称
        tModel.setUserId(officials.getUid()); // 检查人员所属部门的id
        tModel.setFlag(2); // 检查类型
        tModel.setPart(checkLevels.get(0).level1);              // 被检查的部门
        tModel.setIndustryId(IndustryId);         // 被检查的行业id
        if (checkItem.getCheckType() == null) {
            checkItem.setCheckType(1);
        }
        tModel.setIndustryType(checkItem.getCheckType());       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
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

        return tModel;
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
    private TCheck saveCheckTbl(CheckItem checkItem, Officials officials, Integer modelId, Integer id) {

        // 获取部门 id
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        // 获取行业名称
        String name = dangerManualMapper.selectIndustryByid(checkItem.getCheckLevels().get(0).id);

        // 获取行业id
        int IndustryId = companyManualMapper.selectDmidById(name);

        TCheck tCheck = new TCheck();
        tCheck.setFlag(2);       //行政检查
        tCheck.setTitle(checkItem.getTemplate());     //被检查的标题
        tCheck.setDepart(checkLevels.get(0).level1);    // 被检查的部门
        tCheck.setUserId(id);     // 被检查的企业
        tCheck.setCreateUser(officials.getId()); //创建人(检查人员的id)
        tCheck.setModelId(modelId);    // 模版id
        tCheck.setType(checkItem.getTitle());       // 1. 日常 2 定期  3 临时
        tCheck.setIndustryId(IndustryId); // 检查行业的id
        tCheck.setIndustryType(checkItem.getCheckType()); // 1. 基础 2. 现场 3. 高危
        tCheck.setExpectTime(new Date()); // 预计检查时间
        tCheck.setRealTime(new Date());  // 实际检查时间
        tCheck.setCheker(officials.getName());            // 检查人
        tCheck.setContact(officials.getDepart());            // 检查人的部门
        tCheck.setDapartContact(String.valueOf(checkItem.getDepartmentId()));      // 被检查人的id
        tCheck.setStatus(1);              // 1. 未检查
        tCheck.setCreateTime(new Date()); // 创建时间

        return tCheck;

    }

    /**
     * 添加检查分类表
     * level1      varchar(50)  null comment 'Ⅱ级隐患自查标准',
     * level2      varchar(50)  null comment 'Ⅲ级隐患自查标准',
     * level3      varchar(100) null,
     * industry_id int          null comment '所属行业',
     * 出现的类型 type 进行判断 1, 按类型进行检查, 2. 自定义进行检查
     * 是自定义检查还是随机检查,
     *
     * @return
     */
    private List<Integer> saveTLevel(CheckItem checkItem) {
        TLevel tLevel = new TLevel();

        // 获取行业名称
        String name = dangerManualMapper.selectIndustryByid(checkItem.getCheckLevels().get(0).id);

        // 获取行业id
        int IndustryId = companyManualMapper.selectDmidById(name);

        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<Integer> list = new ArrayList<>();

        for (CheckLevel checkLevel : checkLevels) {
            // 按照数据库查询进行检查
            if (checkLevel.getType() == "1") {
                String level3 = checkLevel.getLevel3();
                String[] split = level3.split("/");
                tLevel.setLevel1(split[0]);
                tLevel.setLevel2(split[1]);
                tLevel.setLevel3(split[2]);

            } else if (checkLevel.getType() == "2") {
                // 自定义进行检查
                tLevel.setLevel1(checkLevel.getLevel3());
                tLevel.setLevel2(checkLevel.getLevel3());
                tLevel.setLevel3(checkLevel.getLevel3());
            }
            tLevel.setIndustryId(IndustryId);
            tLevelMapper.insertSelective(tLevel);
            Integer tLevelId = tLevel.getId(); //获取检查分类的id
            list.add(tLevelId);
        }

        return list;
    }

    /**
     * 装置设施表
     * check_id int          null,
     * levels   varchar(500) null comment '检查分类s',
     * name     varchar(50)  null comment '部位或装置名称',
     * part_img varchar(200) null comment '部门照片'
     *
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
     * levels      ,
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
     * TODO 出现重复数据,是因为不同的部门上出现这种风险点和风险因素重复问题?????
     *
     * @return
     */
    private TCheckItem saveCheckTtem(CheckItem checkItem, Officials officials, Integer CheckId, Integer CheckPartId) {

        List<TCheckItem> list = new LinkedList<>();
        // 获取检查标准详情
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();

        for (CheckLevel checkLevel : checkLevels) {
            TCheckItem tCheckItem = new TCheckItem();
            tCheckItem.setContent(checkLevel.getLevel4()); //检查标准详情
            tCheckItem.setLevelId(checkItem.getTitle());   //检查分类
            tCheckItem.setLevels(checkLevel.getLevel3());   // 检查等级
            tCheckItem.setReference(checkLevel.getReference());//检查参照
            tCheckItem.setPartId(CheckPartId);    // 装置与设施id
            tCheckItem.setCheckId(CheckId);   // 检查表id
            list.add(tCheckItem);
        }
        tCheckItemMapper.insertThreeBath(list, CheckId, CheckPartId);

        return null;

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
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        String NameStr = JSON.toJSONString(list);

        tModelPart.setModelId(Tmodel);

        tModelPart.setLevels(NameStr); //检查分类
        tModelPart.setName(departmentNametr);   // 检查的部门

        tModelPartMapper.insertSelective(tModelPart);

    }


    /**
     * 政府账号保存检查信息(第一次检查)
     * <p>
     * 1. 保存item数据
     * 2. 保存check数据
     * 3. 保存check_document 行政检查文书
     * 4. 保存
     *
     * @param saveDataMessageItem
     * @param officials
     * @param id
     * @return
     */
    @Override
    public String saveCheckMessage(SaveDataMessageItem saveDataMessageItem, Officials officials, Integer id) {
        try {
            // 1. 修改检查的数据
            TCheck tCheck = tCheckMapper.selectByPrimaryKey(saveDataMessageItem.getCheckId());
            tCheck.setStatus(2); // 表示已经检查
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
                    tRectification.setCreateUser(officials.getUid()); // 创建人的id
                    tRectification.setCreateTime(new Date()); //生成时间
                    //item.setMemo(saveDataMessage.getMemo()); //不合格描述
                    tCheckItem.setStatus(2); //不合格
                    tCheckItem.setMemo(saveDataMessage.getMemo()); // 不合格描述
                    tCheckItem.setFiles(saveDataMessage.getFile()); // 不合格图片 应该是一个图片集合，但是现在没有集合
                    //tCheckItem.set
                }


            }


            return "保存成功";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
