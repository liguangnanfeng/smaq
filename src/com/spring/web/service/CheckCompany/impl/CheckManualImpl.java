package com.spring.web.service.CheckCompany.impl;

import com.alibaba.fastjson.JSON;
import com.spring.web.dao.*;
import com.spring.web.model.*;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.service.CheckCompany.ICheckManual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 11:52
 * 1.小程序查询level和风险点
 * <p>
 * check 对用个多个part 和item 现在是 part 和item是一一对应的
 */
@Service
@Transactional
public class CheckManualImpl implements ICheckManual {

    /*检查表信息*/
    @Autowired
    private ACompanyManualMapper companyManualMapper;

    /*模版表信息*/
    @Autowired
    private TModelMapper modelMapper;

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

    /*模版详情*/
    @Autowired
    private TModelPartMapper tModelPartMapper;

    /*行业*/
    @Autowired
    private TIndustryMapper tIndustryMapper;

    /*公司详情*/
    @Autowired
    private TCompanyMapper tCompanyMapper;


    /**
     * TODO 根据公司id和部门id查询部门下面的岗位
     *
     * @param uid   公司id
     * @param names 部门名称
     * @return 岗位信息
     */
    @Override
    public Map<String, List> selectDangerAndManual(Integer uid, List<String> names) {

        Map<String, List> map = new HashMap<>();
        for (String name : names) {
            map.put(name, companyManualMapper.selectDangerAndManual(uid, name));
        }
        return map;
    }

    /**
     * TODO 获取所有的高危检查项
     *
     * @param Uid 公司id
     * @return 所有的高危检查项
     */
    @Override
    public List<Map> checkGaoWei(Integer Uid) {

        try {
            List<Map> list = new ArrayList<>();
            // 首先根据公司id查询部门所需要的
            TCompany tCompany = tCompanyMapper.selectByPrimaryKey(Uid);
            String industry3 = tCompany.getIndustry3();
            String[] split = industry3.split(",");
            for (String s : split) {
                list.addAll(tIndustryMapper.selectType3(Integer.valueOf(s)));
            }

            return list;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ArrayList<>();

        }
    }

    /**
     * TODO 基础检查 获取该公司的基础检查的检查项
     *
     * @param uid 公司id
     * @return 基础检查项
     */
    @Override
    public Map checkJiChu(Integer uid) {
        TCompany tCompany = tCompanyMapper.selectByPrimaryKey(uid);//获取基础检查
        TIndustry industry = tIndustryMapper.selectByPrimaryKey(tCompany.getIndustry1()); //获取他是工贸企业
        Map map = new LinkedHashMap();
        List<String> list = tLevelMapper.selectLevel1ByIndustry(industry.getId());
        // 在根据部门做出统一的查询
        for (String level1 : list) {
            List<Map<String, Object>> maps = tLevelMapper.selectlevel2Bylevel1AndIndustryId(level1, industry.getId());
            map.put(level1, maps);
        }
        return map;

    }

    /**
     * TODO 查询高危level1
     *
     * @param industryId 行业id
     * @return 高危的level1 及其levelId
     */
    @Override
    public List<Map<String, Object>> checkGaoWei2(Integer industryId) {
        List<Map<String, Object>> list = tLevelMapper.checkGaoWei2(industryId);
        return list;
    }

    /**
     * TODO 获取高危的检查选项
     * level1 对应 level2
     *
     * @param industryId  高危行业id
     * @return 高危检查项
     */
    @Override
    public Map checkGaoWeiItem(Integer industryId) {
        Map map = new LinkedHashMap();
        List<String> list = tLevelMapper.selectLevel1ByIndustry(industryId);
        // 在根据部门做出统一的查询
        for (String level1 : list) {
            List<Map<String, Object>> maps = tLevelMapper.selectlevel2Bylevel1AndIndustryId(level1, industryId);
            map.put(level1, maps);
        }
        return map;
    }

    /**
     *TODO 根据基础还是高危
     * @param checkLevel 高危/基础 详情 (基础以废弃,基础已经和现场合并)
     * @return 检查详情
     */
    @Override
    public List<Map> checkGaoWeiAndJiChu(CheckLevel checkLevel) {

        List<Map> list = tLevelMapper.selectGaoweiAndJiChu(checkLevel.getIndustryId(), checkLevel.getLevel1(), checkLevel.getLevel2());

        return list;
    }

    /**
     * TODO 根据uid(企业总id) level2(岗位) 查询level3 及其id
     *
     * @param checkLevel 查询条件
     * @return  查询结果 level3/id
     */
    @Override
    public List<Map> selectLevel3AndId(List<CheckLevel> checkLevel) {

        List<Map> list = new ArrayList<>();

        for (CheckLevel check : checkLevel) {
            list.addAll(companyManualMapper.selectLevel3AndId(check));
        }
        return list;
    }

    /**
     * TODO 根据 部门 岗位检查详细信息  根据levle1 level2 检查类型  查询level3
     *
     * @param checkLevel  查询条件
     * @return   查询结果
     */
    @Override
    public List<Map<String, Object>> selectLevel4AndId(CheckLevel checkLevel) {
        List<Map<String, Object>> list = new ArrayList<>();

        list.addAll(companyManualMapper.selectLevel3AndId(checkLevel));

        return list;
    }

    /**
     * TODO 根据level3 查询level4及其危险点
     * @param checkLevel  查询条件
     * @return  检查的详细信息
     */
    @Override
    public List<Map> selectLevel5AndId(CheckLevel checkLevel) {
        List<Map> list = new ArrayList<>();
        list.addAll(companyManualMapper.selectLevel4AndId(checkLevel));
        return list;
    }

    /**
     * TODO 小程序企业端 保存自定义模版的信息
     * 按照步骤进行保存
     * 按照事务流的顺序一步一步来的,
     *
     * @param checkItem 用户选择的信息
     * @param zzjg      用户保存在域中的信息
     * @return 检查表的信息
     */
    @Override
    public Integer saveCheck(CheckItem checkItem, ZzjgPersonnel zzjg) {

        try {

            // 1.获取行业id industryId 行业id
            Integer industryId = checkItem.getCheckLevels().get(0).getIndustryId();

            // 2. 获取检查等级id
            List<Integer> levels = null;

            // 3. 保存model信息并返回id
            Integer modelId = saveTmodel(checkItem, zzjg, industryId);

            // 4. 保存check表数据并返回id
            Integer checkId = saveCheckTbl(checkItem, zzjg, modelId, industryId);

            // 5. 保存  TODO  和checkpart表数据 Check_item 表数据并返回id
            saveCheckTtem(checkItem, zzjg, checkId);

            // 6. 保存model_part() 计划检查模块表
            saveTmodelPath(modelId, checkItem, levels);

            System.out.println(checkId);
            return modelId; // 数据返回 TODO 添加功能结束

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("保存失败");

        }

    }

    /**
     * TODO 保存检查模板表
     * @param checkItem 查询条件
     * @param zzjg      人员信息
     * @param industryId 行业id
     * @return  modelId
     */
    private Integer saveTmodel(CheckItem checkItem, ZzjgPersonnel zzjg, Integer industryId) {

        // 获取部门 id
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
            list.add(Integer.toString(checkLevel.getId()));
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        // 开始保存
        TModel tModel = new TModel();
        tModel.setTitle(checkItem.getTemplate()); // 检查名称
        tModel.setUserId(zzjg.getUid()); // 公司id
        tModel.setFlag(1); // 自查
        tModel.setPart(checkLevels.get(0).level1);  // 被检查的部门
        if (industryId != null) {
            tModel.setIndustryId(industryId);         // 被检查的行业id
        }

        if (checkItem.getCheckType() == null) {
            checkItem.setCheckType(2);                // 为空就表示现场检查
        }
        if (-1 == checkItem.getCheckType()) {
            tModel.setIndustryType(1);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
        } else if (-2 == checkItem.getCheckType()) {
            tModel.setIndustryType(2);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
        } else {
            tModel.setIndustryType(3);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
        }

        tModel.setType(checkItem.getTitle());    //  1. 日常 2. 定期 3. 临时
        tModel.setCreateTime(new Date()); // 模版的创建时间

        // TODO 2 表示是定期检查 要确定检查时间
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

        int i = modelMapper.insertSelective(tModel);

        return tModel.getId();
    }

    /**
     * 检查表
     *
     * @param checkItem 用户返回数据
     * @param zzjg      用户登陆信息
     * @param modelId   模版表id
     * @return checkId  检查记录id
     */
    private Integer saveCheckTbl(CheckItem checkItem, ZzjgPersonnel zzjg, Integer modelId, Integer industryId) {

        // 获取部门 id
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();

        TCheck tCheck = new TCheck();
        tCheck.setFlag(1);                             //企业自查
        tCheck.setTitle(checkItem.getTemplate());      //被检查的标题
        tCheck.setDepart(checkLevels.get(0).level1);   // 被检查的部门
        tCheck.setUserId(zzjg.getUid());              // 被检查的企业
        tCheck.setCreateUser(zzjg.getId());           //创建人(检查人员的id)
        tCheck.setModelId(modelId);                   // 模版id
        tCheck.setType(checkItem.getTitle());  // 1. 日常 2 定期  3 临时
        if (industryId != null) {
            tCheck.setIndustryId(industryId); // 检查行业的id
        }
        if (-1 == checkItem.getCheckType()) {
            tCheck.setIndustryType(1); // 1. 基础 2. 现场 3. 高危
        } else if (-2 == checkItem.getCheckType()) {
            tCheck.setIndustryType(2); // 1. 基础 2. 现场 3. 高危
        } else {
            tCheck.setIndustryType(3); // 1. 基础 2. 现场 3. 高危
        }

        tCheck.setExpectTime(new Date()); // 预计检查时间
        tCheck.setRealTime(new Date());  // 实际检查时间
        tCheck.setCheker(zzjg.getName());            // 检查人
        tCheck.setContact(zzjg.getMobile());            // 检查人的联系方式
        tCheck.setDapartContact(String.valueOf(checkItem.getDepartmentId()));      // 被检查人的部门的id
        tCheck.setStatus(0);              //  表示未检查
        tCheck.setCreateTime(new Date()); // 创建时间
        int i = tCheckMapper.insertSelective(tCheck);
        return tCheck.getId();

    }

    /**
     * 查询该企业所有的安全责任人
     * 先使用cid ,不行在使用uid
     *
     * @param zzjg
     * @return
     */
    @Override
    public List<Map<Integer, String>> findUserByIdAndStatus(ZzjgPersonnel zzjg) {

        Integer uid = zzjg.getCid();
        List<Map<Integer, String>> list = zzjgPersonnelMapper.findUserByIdAndSTatus(uid);

        return list;
    }

    /**
     * TODO  企业端 根据公司id查询所有的模版信息
     *
     * @param uid    企业id
     * @param dpName 部门名称
     * @return
     */
    @Override
    public List<Map<Integer, String>> findModelByUid(Integer uid, String dpName) {
        List<Map<Integer, String>> list = modelMapper.selectModelByUid(uid, dpName);
        return list;
    }

    /**
     * TODO 政府端 根据公司id查询所有的模版信息
     *
     * @param uid
     * @return
     */
    @Override
    public List<Map<Integer, String>> findCountryModelByUid(Integer uid) {
        List<Map<Integer, String>> list = modelMapper.selectCountryModelByUid(uid);
        return list;
    }

    /**
     * 根据安全责任人的id,查询对应的部门,以及岗位
     *
     * @param personnelId
     * @return
     */
    @Override
    public Map<String, List> findLevel2ByPersonnelId(Integer personnelId, Integer Uid) {

        // 首先查询所在的部门
        String name = zzjgPersonnelMapper.selectdpidById(personnelId);
        // 根据部门和 公司总id查询所有level2
        List<String> list = companyManualMapper.findLevel2ByPersonelId(name, Uid);

        Map<String, List> map = new HashMap<>();
        map.put(name, list);

        return map;
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
    private TCheckItem saveCheckTtem(CheckItem checkItem, ZzjgPersonnel zzjg, Integer CheckId) {

        // 获取检查标准详情
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();

        for (CheckLevel checkLevel : checkLevels) {
            List<TCheckItem> list = new LinkedList<>();
            TCheckPart tCheckPart = new TCheckPart(); // 检查岗位/部位表

            tCheckPart.setCheckId(CheckId);         // 检查表id
            tCheckPart.setName(checkLevel.level2);    // 岗位或部位名称
            int i = tCheckPartMapper.insertSelective(tCheckPart);

            TCheckItem tCheckItem = new TCheckItem();
            tCheckItem.setMemo(checkLevel.getFactors());//不合格描述
            tCheckItem.setLevelId(checkLevel.getId());  // conpanyManul_tbl的id
            tCheckItem.setContent(checkLevel.getLevel4()); //检查标准详情
            tCheckItem.setLevelId(checkItem.getTitle());   //检查分类

            if (null == checkLevel.getLevel3()) {
                tCheckItem.setLevels(checkLevel.getLevel1());   // 检查等级
            } else {
                tCheckItem.setLevels(checkLevel.getLevel3());   // 检查等级
            }

            tCheckItem.setReference(checkLevel.getReference());//检查参照

            // tCheckItem.setPartId(tCheckPart.getId());    // 装置与设施id
            tCheckItem.setCheckId(CheckId);   // 检查表id

            list.add(tCheckItem);
            // 表示数据写反了
            tCheckItemMapper.insertThreeBath(list, CheckId, tCheckPart.getId());
        }

        return null;

    }

    /**
     * 检查计划模板 模块表
     * 使用循环保存数据
     * name     varchar(30)   null comment '部门 或装置',
     * model_id int           null comment '模板id',
     * levels   varchar(2000) null comment '检查分类s',
     */
    private void saveTmodelPath(Integer Tmodel, CheckItem checkItem, List<Integer> list) {
        // 获取部门名称list的str
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        String str = new String();
        for (int i = 0; i < checkLevels.size(); i++) {
            if (i < checkLevels.size() - 1) {
                str += checkLevels.get(i).getId() + ",";
            } else {
                str += checkLevels.get(i).getId() + "";
            }
        }

        for (CheckLevel checkLevel : checkItem.getCheckLevels()) {
            TModelPart tModelPart = new TModelPart();
            tModelPart.setModelId(Tmodel);
            tModelPart.setLevels(str); //检查分类
            tModelPart.setName(checkLevel.getLevel1());   // 检查的部门
            tModelPartMapper.insertSelective(tModelPart);
        }

    }
}





