package com.spring.web.service.CheckCompany.impl;

import com.alibaba.fastjson.JSON;
import com.spring.web.dao.*;
import com.spring.web.model.*;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.PCSaveModel;
import com.spring.web.service.SaveModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 11:52
 * 1.小程序查询level和风险点
 */
@Service
@Transactional
@SuppressWarnings("all")
public class CheckManualImpl implements ICheckManual {

    /**
     * 检查表信息
     */
    @Autowired
    private ACompanyManualMapper companyManualMapper;

    /**
     * 模版表信息
     */
    @Autowired
    private TModelMapper modelMapper;

    /**
     * 危险警示
     */
    @Autowired
    private ADangerManualMapper dangerManualMapper;

    /**
     * 检查总表
     */
    @Autowired
    private TCheckMapper tCheckMapper;

    /**
     * 装置或设施表
     */
    @Autowired
    private TCheckPartMapper tCheckPartMapper;

    /**
     * 自查分类级别表
     */
    @Autowired
    private TLevelMapper tLevelMapper;

    /**
     * 检查检查项表
     */
    @Autowired
    private TCheckItemMapper tCheckItemMapper;

    /**
     * 企业人员表
     */
    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;

    @Autowired
    private TModelPartMapper tModelPartMapper;

    @Autowired
    private PCSaveModel saveModelService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private TIndustryMapper tIndustryMapper;

    @Autowired
    private TItemSeriousMapper tItemSeriousMapper;

    @Autowired
    private TItemMapper tItemMapper;

    @Autowired
    private TCompanyMapper tCompanyMapper;


    /**
     * TODO 根据公司id和部门id查询部门下面的岗位
     * 根据可以进行传递
     *
     * @param uid
     * @param names
     * @return
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
     * 获取所有的高危检查项
     * @return
     */
    @Override
    public List<Map> checkGaoWei(Integer Uid) {

        List<Map> list = new ArrayList<>();
        // 首先根据公司id查询部门所需要的
        TCompany tCompany = tCompanyMapper.selectByPrimaryKey(Uid);
        String industry3 = tCompany.getIndustry3();
        String[] split = industry3.split(",");
        for (String s : split) {
           list.addAll(tIndustryMapper.selectType3(Integer.valueOf(s)));
        }



        return list;
    }

    /**
     * 基础检查 获取该公司的基础检查的检查项
     * @param zzjg
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
            map.put(level1,maps);
        }
        return map;

    }

    /**
     * 获取高危的检查选项
     * level1 对应 level2
     * @param industryId
     */
    @Override
    public Map checkGaoWeiItem(Integer industryId) {
        Map map = new LinkedHashMap();
        List<String> list = tLevelMapper.selectLevel1ByIndustry(industryId);
        // 在根据部门做出统一的查询
        for (String level1 : list) {
            List<Map<String, Object>> maps = tLevelMapper.selectlevel2Bylevel1AndIndustryId(level1, industryId);
            map.put(level1,maps);
        }
        return map;
    }

    /**
     * 根据基础还是高危
     * 根据一条sql获取多条数据
     * 不管这条sql 的查询数据
     * 获取详细的数据
     * @param checkLevel
     */
    @Override
    public List<Map> checkGaoWeiAndJiChu(CheckLevel checkLevel) {

       List<Map> list =  tLevelMapper.selectGaoweiAndJiChu(checkLevel.getIndustryId(),checkLevel.getLevel1(),checkLevel.getLevel2());

        return list;
    }

    /**
     * TODO 根据uid(企业总id) level2(岗位) 查询level3 及其id
     *
     * @param checkLevel
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
     * @param checkLevel
     * @return
     */
    @Override
    public List<Map<String, Object>> selectLevel4AndId(CheckLevel checkLevel) {
        List<Map<String,Object>> list = new ArrayList<>();

        list.addAll(companyManualMapper.selectLevel3AndId(checkLevel));

        return list;
    }

    /**
     * TODO 根据level3 查询level4及其危险点
     */
    @Override
    public List<Map> selectLevel5AndId(CheckLevel checkLevel) {
        List<Map> list = new ArrayList<>();
        list.addAll(companyManualMapper.selectLevel4AndId(checkLevel));
        return list;
    }

    /**
     *
     * 保存自定义模版的信息
     * 按照步骤进行保存
     * 按照事务流的顺序一步一步来的,
     *  保存  t_model 获取id
     *  保存  t_model_part_tbl 获取id
     *  保存
     * 1. 保存model
     * 2. 保存check
     * 3. 保存
     * 4. 返回表数据
     * @param checkItem 用户选择的信息
     * @param zzjg      用户保存在域中的信息
     * @return 检查表的信息
     */
    @Override
    public Integer saveCheck(CheckItem checkItem, ZzjgPersonnel zzjg) {

        try {

            // 1. 保存t_industry_tbl表 返回id  TODO
            Integer industryId = checkItem.getCheckLevels().get(0).getIndustryId();

            // 2. 保存t_level_tbl AND t_item_tbl 返回id集合
            List<Integer> levels = null;

            // 1. 保存model信息并返回id
            TModel tModel = saveTmodel(checkItem, zzjg,industryId);
            modelMapper.insertSelective(tModel);
            Integer modelId = tModel.getId(); //获取模版id

            // 2. 保存check表数据并返回id
            TCheck tCheck = saveCheckTbl(checkItem, zzjg, modelId,industryId);
            int i = tCheckMapper.insertSelective(tCheck);
            Integer checkId = tCheck.getId();  //获取检查表id


            // 4. 保存checkPart数据并返回id
            TCheckPart tCheckPart = saveCheckPart(checkItem, levels, checkId);
            tCheckPartMapper.insertSelective(tCheckPart);
            int tCheckPartId = tCheckPart.getId(); // 获取checkPartId

            // 5. 保存Check_item 表数据并返回id TODO
            saveCheckTtem(checkItem, zzjg, checkId, tCheckPartId);

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
     * TODO 根据公司id查询所有的模版信息
     *
     * @param uid
     * @return
     */
    @Override
    public List<Map<Integer, String>> findModelByUid(Integer uid) {
        List<Map<Integer, String>> list = modelMapper.selectModelByUid(uid);
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
     * 检查计划模板表
     * 传递的模块信息进行保存括其他的数据进行保存
     * 保存数据到检查表,并获取检查表id信息,
     * 检查类型 1: 自查 2: 第三方
     * 危险类型 1  基础 2  现场  3 高危检查
     * 行业id 就是根据保存的数据
     *
     * @param zzjg
     * @return
     */
    private TModel saveTmodel(CheckItem checkItem, ZzjgPersonnel zzjg, Integer industryId ) {

        // 获取部门 id
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
            list.add(Integer.toString(checkLevel.getId()));
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        TModel tModel = new TModel();
        tModel.setTitle(checkItem.getTemplate()); // 检查名称
        tModel.setUserId(zzjg.getUid()); // 公司id
        tModel.setFlag(1); // 检查类型
        tModel.setPart(checkLevels.get(0).level1);              // 被检查的部门
        if(industryId!= null){
            tModel.setIndustryId(industryId);         // 被检查的行业id
        }

        if (checkItem.getCheckType() == null) {
            checkItem.setCheckType(1);
        }
        if(-1==checkItem.getCheckType()){
            tModel.setIndustryType(1);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
        }else if(-2==checkItem.getCheckType() ){
            tModel.setIndustryType(2);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
        }else{
            tModel.setIndustryType(3);       // 被检查的危险类型 1. 基础  2. 现场  3. 五大高危行业
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
    private TCheck saveCheckTbl(CheckItem checkItem, ZzjgPersonnel zzjg, Integer modelId, Integer industryId) {

        // 获取部门 id
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();
        List<String> departmentName = new ArrayList<>();
        for (CheckLevel checkLevel : checkLevels) {
            departmentName.add(checkLevel.getLevel1());
        }
        String departmentNametr = JSON.toJSONString(departmentName);

        TCheck tCheck = new TCheck();
        tCheck.setFlag(1);       //企业自查
        tCheck.setTitle(checkItem.getTemplate());     //被检查的标题
        tCheck.setDepart(checkLevels.get(0).level1);    // 被检查的部门
        tCheck.setUserId(zzjg.getUid());     // 被检查的企业
        tCheck.setCreateUser(zzjg.getId()); //创建人(检查人员的id)
        tCheck.setModelId(modelId);    // 模版id
        tCheck.setType(checkItem.getTitle());       // 1. 日常 2 定期  3 临时
        if(industryId!= null) {
            tCheck.setIndustryId(industryId); // 检查行业的id
        }
        if(-1==checkItem.getCheckType()){
            tCheck.setIndustryType(1); // 1. 基础 2. 现场 3. 高危
        }else if(-2==checkItem.getCheckType() ){
            tCheck.setIndustryType(2); // 1. 基础 2. 现场 3. 高危
        }else{
            tCheck.setIndustryType(3); // 1. 基础 2. 现场 3. 高危
        }

        tCheck.setExpectTime(new Date()); // 预计检查时间
        tCheck.setRealTime(new Date());  // 实际检查时间
        tCheck.setCheker(zzjg.getName());            // 检查人
        tCheck.setContact(zzjg.getMobile());            // 检查人的联系方式
        tCheck.setDapartContact(String.valueOf(checkItem.getDepartmentId()));      // 被检查人的id
        tCheck.setStatus(4);              //  表示是为初始值模版
        tCheck.setCreateTime(new Date()); // 创建时间

        return tCheck;

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
     * @return
     */
    private TCheckItem saveCheckTtem(CheckItem checkItem, ZzjgPersonnel zzjg, Integer CheckId, Integer CheckPartId) {

        List<TCheckItem> list = new LinkedList<>();
        // 获取检查标准详情
        List<CheckLevel> checkLevels = checkItem.getCheckLevels();

        for (CheckLevel checkLevel : checkLevels) {
            TCheckItem tCheckItem = new TCheckItem();
            tCheckItem.setMemo(checkLevel.getFactors());//不合格描述
            //tCheckItem.setFiles(checkLevel.getFiles());//检查图片
            tCheckItem.setContent(checkLevel.getLevel4()); //检查标准详情
            tCheckItem.setLevelId(checkItem.getTitle());   //检查分类
            tCheckItem.setLevels(checkLevel.getLevel3());   // 检查等级

            tCheckItem.setReference(checkLevel.getReference());//检查参照

            tCheckItem.setPartId(CheckPartId);    // 装置与设施id
            tCheckItem.setCheckId(CheckId);   // 检查表id
           // tCheckItem.setStatus(4); //表示初始记录
            list.add(tCheckItem);
        }
        // 表示数据写反了
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

}





