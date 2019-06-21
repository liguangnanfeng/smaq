/**
 * Copyright(c)2013 Wuxi Lanlin Co.,Ltd.
 * All right reserved.
 */
package com.spring.web.service.cgf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.spring.web.dao.*;
import com.spring.web.ibatis.BaseDao;
import com.spring.web.model.*;
import com.spring.web.result.AppResult;
import com.spring.web.result.Result;
import com.spring.web.tobject.cgf.*;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.*;

/**
 * @author CGF
 * @version V1.0
 * @Title: CommonServiceImpl
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2017年2月17日 上午9:02:18
 */
@Service("cgfService")
public class CgfServiceImpl implements CgfService {
    /**
     * @Fields serialVersionUID : 序列化
     */
    private static final long serialVersionUID = 8252510697823573991L;
    protected static final Logger log = Logger.getLogger(CgfServiceImpl.class);
    @Autowired
    private BaseDao baseDao;
    @Autowired
    private TModelPartMapper tModelPartMapper;
    @Autowired
    private TModelMapper tModelMapper;
    @Autowired
    private TCheckMapper tCheckMapper;
    @Autowired
    private TCheckPartMapper tCheckPartMapper;
    @Autowired
    private TItemMapper tItemMapper;
    @Autowired
    private TCheckItemMapper tCheckItemMapper;
    @Autowired
    private TRectificationMapper tRectificationMapper;
    @Autowired
    private TRecheckMapper tRecheckMapper;
    @Autowired
    private TRecheckItemMapper tRecheckItemMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VillageMapper villageMapper;
    @Autowired
    private TownMapper townMapper;
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private TradeMapper tradeMapper;
    @Autowired
    private TradeCompanyMapper tradeCompanyMapper;
    @Autowired
    private TCheckDocumentMapper tCheckDocumentMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private PushMessageMapper pushMessageMapper;
    @Autowired
    private PushMessageUserMapper pushMessageUserMapper;
    @Autowired
    private FcNaturalDangerStudyMapper fcNaturalDangerStudyMapper;
    @Autowired
    private FcNaturalDangerItemMapper fcNaturalDangerItemMapper;
    @Autowired
    private FcTargetAssessmentMapper fcTargetAssessmentMapper;
    @Autowired
    private FcGreatDangerStudyMapper fcGreatDangerStudyMapper;
    @Autowired
    private FcTemporaryDangerCensusMapper fcTemporaryDangerCensusMapper;
    @Autowired
    private FcTemporaryDangerStudyMapper fcTemporaryDangerStudyMapper;
    @Autowired
    private FcTemporaryDangerItemMapper fcTemporaryDangerItemMapper;
    @Autowired
    private MonitorMapper monitorMapper;

    protected Gson gson = new GsonBuilder().create();

    /**
     * @param dto    行政 委托检查表保存
     * @param result
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void checkSaveDo(CheckSaveReqDTO dto) throws Exception {
        TCheck check = dto.getCheck();
        Date d = new Date();
        check.setDel(0);
        check.setStatus(1);
        if (null == check.getId()) {
            check.setCreateTime(d);
            tCheckMapper.insertSelective(check);
        } else {
            tCheckMapper.updateByPrimaryKeySelective(check);
            tCheckItemMapper.deleteByCheckId(check.getId());
            tCheckPartMapper.deleteByCheckId(check.getId());
        }
        for (TCheckPart cp : dto.getPartList()) {
            cp.setCheckId(check.getId());
            tCheckPartMapper.insertSelective(cp);
            List<Map<String, Object>> itemL = tItemMapper.selectByLevelIdsModel(cp.getLevels());
            tCheckItemMapper.insertBath(itemL, check.getId(), cp.getId());
        }
    }

    /**
     * @param dto    自查计划保存
     * @param result
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void modelSave(ModelSaveReqDTO dto, Result result) throws Exception {
        TModel model = dto.getModel();
        Date d = new Date();
        // 开启自动时，判断报表大于当前时间
        if (1 == model.getOpen().intValue()) {
            if (model.getNextTime().compareTo(d) < 1) {
                result.setStatus("1");
                result.setMap("message", "检查开始日期应大于当前时间");
                return;
            }
        }
        // Boolean creat = 1 == model.getOpen().intValue();// 自动创建一封报表
        // // 下次报表创建时检查时间
        if (null != model.getNextTime() && null != model.getCycle()) {
            model.setNextCheckTime(DateConvertUtil.addDays(model.getNextTime(), model.getCycle()));
        }
        // if (creat) {
        // model.setUseTime(d);// 模板最新使用时间
        // }
        // 新增
        if (null == model.getId()) {
            model.setCreateTime(d);
            tModelMapper.insertSelective(model);
        } else {
            // if (creat) {
            // TModel t = tModelMapper.selectByPrimaryKey(model.getId());
            // if(null != t.getNextTime() && 0 == t.getNextCheckTime().compareTo(model.getNextTime())) {
            // //计划时间没变 不创建报表
            //
            // }
            // }
            tModelMapper.updateByPrimaryKeySelective(model);
            tModelPartMapper.deleteByModelId(model.getId());
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("modelId", model.getId());
        m.put("list", dto.getPartList());
        tModelPartMapper.insertBath(m);
        // // 自动创建一封报表
        // if (creat) {
        // TCheck check = new TCheck();
        // check.setTitle(model.getTitle());
        // check.setDepart(model.getPart());
        // check.setUserId(model.getUserId());
        // check.setCreateUser(model.getUserId());
        // check.setModelId(model.getId());
        // check.setType(model.getType());
        // check.setIndustryId(model.getIndustryId());
        // check.setIndustryType(model.getIndustryType());
        // check.setExpectTime(model.getNextTime());
        // check.setFlag(model.getFlag());
        // check.setDel(0);
        // checkSave(check);
        // }
    }

    /**
     * @param check  检查表保存
     * @param result
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void checkSave(TCheck check) throws Exception {
        check.setCreateTime(new Date());
        check.setStatus(1);
        tCheckMapper.insertSelective(check);
        List<TModelPart> partL = tModelPartMapper.selectByModelId(check.getModelId());
        for (TModelPart p : partL) {
            TCheckPart cp = new TCheckPart();
            cp.setCheckId(check.getId());
            cp.setLevels(p.getLevels());
            cp.setName(p.getName());
            // cp.setStatus(0);
            tCheckPartMapper.insertSelective(cp);
            //log.error("checkSave:"+7);//zhangcl 2018.10.28
            //根据风险因素id查找检查内容，可能1对多
            List<Map<String, Object>> itemL = tItemMapper.selectByLevelIdsModel(cp.getLevels());
            //log.error("checkSave itemL.size:"+itemL.size());//zhangcl 2018.10.28
            tCheckItemMapper.insertBath(itemL, check.getId(), cp.getId());
            //log.error("checkSave:"+9);//zhangcl 2018.10.28
        }
        // 跟新最新使用时间
        // TModel t = new TModel();
        // t.setId(check.getModelId());
        // t.setUseTime(check.getCreateTime());
        // tModelMapper.updateByPrimaryKeySelective(t);
    }

    /**
     * @param check  检查表保存
     * @param result
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void checkSave1(TCheck check) throws Exception {//add by zhangcl 2018.10.28
        check.setCreateTime(new Date());
        check.setStatus(1);
        tCheckMapper.insertSelective(check);
        //log.error("checkSave1:"+5);//zhangcl 2018.10.28
        List<TModelPart> partL = tModelPartMapper.selectByModelId(check.getModelId());
        //log.error("checkSave1:"+6);//zhangcl 2018.10.28
        for (TModelPart p : partL) {
            TCheckPart cp = new TCheckPart();
            cp.setCheckId(check.getId());
            cp.setLevels(p.getLevels());
            cp.setName(p.getName());
            // cp.setStatus(0);
            tCheckPartMapper.insertSelective(cp);
            //log.error("checkSave1:"+7);//zhangcl 2018.10.28
            //根据风险因素id查找检查内容，可能1对多,可以找不到,所以如果是根据风险因素来自动生成的检查表会出bug
            //这里的解决方法就是不查找tItem表，而是只保存了风险因素的id，后期生成检查表的时候根据id去查danger表,获取所要信息
            //List<Map<String, Object>> itemL = tItemMapper.selectByLevelIdsModel(cp.getLevels());
            List<Map<String, Object>> itemL = new ArrayList<Map<String, Object>>();
            //log.error("checkSave cp.getLevels:"+cp.getLevels());//zhangcl 2018.10.28
            String[] levelsArr = cp.getLevels().split(",");
            //log.error("checkSave1 levelsArr:"+cp.getLevels());//zhangcl 2018.10.28
            for (int i = 0; i < levelsArr.length; i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("levelId", Integer.parseInt(levelsArr[i]));
                item.put("content", "");
                item.put("levels", "");
                item.put("reference", "");
                itemL.add(item);
            }
            //log.error("checkSave1 itemL.size:"+itemL.size());//zhangcl 2018.10.28
            tCheckItemMapper.insertBath(itemL, check.getId(), cp.getId());
            //log.error("checkSave1:"+9);//zhangcl 2018.10.28
        }
        // 跟新最新使用时间
        // TModel t = new TModel();
        // t.setId(check.getModelId());
        // t.setUseTime(check.getCreateTime());
        // tModelMapper.updateByPrimaryKeySelective(t);
    }

    /**
     * @param check  检查表自动化设置保存
     * @param result
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void modelAuto(TModel model, Result result) throws Exception {
        // 开启自动时，判断报表大于当前时间
        Date d = new Date();
        if (1 == model.getOpen().intValue()) {
            if (model.getNextTime().compareTo(d) < 1) {
                result.setStatus("1");
                result.setMap("message", "计划时间应大于当前时间");
                return;
            }
        }
        // Boolean creat = 1 == model.getOpen().intValue();// 自动创建一封报表
        // 下次报表创建时检查时间
        if (null != model.getNextTime() && null != model.getCycle()) {
            model.setNextCheckTime(DateConvertUtil.addDays(model.getNextTime(), model.getCycle()));
        }
        tModelMapper.updateByPrimaryKeySelective(model);
        // 自动创建一封报表
        // if (creat) {
        // model = tModelMapper.selectByPrimaryKey(model.getId());
        // TCheck check = new TCheck();
        // check.setTitle(model.getTitle());
        // check.setDepart(model.getPart());
        // check.setUserId(model.getUserId());
        // check.setModelId(model.getId());
        // check.setType(model.getType());
        // check.setIndustryId(model.getIndustryId());
        // check.setIndustryType(model.getIndustryType());
        // check.setExpectTime(model.getNextTime());
        // checkSave(check);
        // }
    }

    // /**
    // * @param check 检查检查保存
    // * @param result
    // * @throws Exception
    // */
    // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    // public void checkNestSave(TCheck t, Integer[] partIds, Integer[] statusp, Integer[] suggest, String[] time,
    // Result result) throws Exception {
    // t.setStatus(2);
    // t.setRealTime(new Date());
    // tCheckMapper.updateByPrimaryKeySelective(t);
    // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // for (int i = 0; i < partIds.length; i++) {
    // TCheckPart p = new TCheckPart();
    // p.setId(partIds[i]);
    // p.setSuggest(suggest[i]);
    // p.setStatus(statusp[i]);
    // if (!"-".equals(time[i])) {
    // p.setDangerTime(dateFormat.parse(time[i]));
    // }
    // tCheckPartMapper.updateByPrimaryKeySelective(p);
    // }
    // }

    /**
     * (非 Javadoc) 检查检查保存
     *
     * @param t
     * @param result
     * @throws Exception
     * @see com.spring.web.service.cgf.CgfService#checkNestSave(com.spring.web.model.TCheck, com.spring.web.result.Result)
     */
    public void checkNestSave(TCheck t) throws Exception {
        t.setStatus(2);
        t.setDel(0);
        t.setRealTime(new Date());
        tCheckMapper.updateByPrimaryKeySelective(t);
        TModel tm = new TModel();
        tm.setId(t.getModelId());
        tm.setUseTime(t.getRealTime());
        tModelMapper.updateByPrimaryKeySelective(tm);
    }

    /**
     * (非 Javadoc) 整改意见保存
     *
     * @param tr
     * @throws Exception
     * @see com.spring.web.service.cgf.CgfService#rectificationSave(com.spring.web.model.TRectification)
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void rectificationSave(TRectification tr) throws Exception {
        TRectification rectification = tRectificationMapper.selectByCheckId(tr.getCheckId());
        if (null == rectification) {
            TCheck c = tCheckMapper.selectByPrimaryKey(tr.getCheckId());
            tr.setCreateTime(new Date());
            tr.setUserId(c.getUserId());
            tr.setDel(0);
            tRectificationMapper.insertSelective(tr);
        } else {
            tr.setId(rectification.getId());
            tRectificationMapper.updateByPrimaryKeySelective(tr);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(tr.getItem1())) {
            m.put("ids", tr.getItem1());
            m.put("suggest", 1);
            m.put("deadline", null);
            m.put("planTime", tr.getPlanTime());
            tCheckItemMapper.updSuggest(m);
        }
        if (!StringUtils.isEmpty(tr.getItem2())) {
            m.put("ids", tr.getItem2());
            m.put("suggest", 2);
            m.put("deadline", tr.getDeadline());
            m.put("planTime", tr.getPlanTime());
            tCheckItemMapper.updSuggest(m);
        }
    }

    /**
     * TODO 保存复查意见
     * 但是要修改的是checkItem中的数据进行保存才行
     *
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void recheckSave(RecheckSaveReqDTO dto) throws Exception {
        Date date = new Date();
        TRecheck r = dto.getRecheck();
        TCheck c = tCheckMapper.selectByPrimaryKey(r.getCheckId());
        r.setUserId(c.getUserId());
        r.setCreateTime(date);
        tRecheckMapper.insertSelective(r);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("list", dto.getList());
        m.put("recheckId", r.getId());
        tRecheckItemMapper.insertBath(m);
        // 保存check_Item表中的数据

        List<TRecheckItem> list = dto.getList();
        if (list.size() > 0) {
            for (TRecheckItem tRecheckItem : list) {
                if (null != tRecheckItem) {
                    TCheckItem tCheckItem = tCheckItemMapper.selectByPrimaryKey(tRecheckItem.getCheckItemId());
                    tCheckItem.setStatus(tRecheckItem.getStatus());
                    if (null != tRecheckItem.getFile() && !"".equals(tRecheckItem.getFile())) {
                        tCheckItem.setFiles(tRecheckItem.getFile());
                        tCheckItem.setRecheckFile(tRecheckItem.getFile());
                    } else {
                        tCheckItem.setRecheckFile(tCheckItem.getFiles());
                    }
                    if (null != tRecheckItem.getMemo() && !"".equals(tRecheckItem.getMemo())) {
                        tCheckItem.setMemo(tRecheckItem.getMemo());
                        tCheckItem.setRecheckMemo(tRecheckItem.getMemo());
                    }
                    tCheckItem.setRecheckTime(tRecheckItem.getDeadline());
                    tCheckItemMapper.updateByPrimaryKeySelective(tCheckItem);
                }
            }
        }

     /*   for (TRecheckItem i : dto.getList()) {
            m.clear();
            m.put("id", i.getCheckItemId());
            m.put("status", i.getStatus());
            m.put("recheckTime", date);
            if (2 == i.getStatus().intValue()) {
                m.put("deadline", r.getNextTime());
                m.put("planTime", r.getNextTime());
            }
            tCheckItemMapper.updRecheck(m);
        }*/
    }

    /**
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void threeSave(ThreeSaveReqDTO dto) throws Exception {
        TCheck check = dto.getCheck();
        Date d = new Date();
        if (null == check.getId()) {// 新增
            // check.setFlag(3);
            check.setType(1);
            check.setDel(0);
            check.setCreateTime(d);
            tCheckMapper.insertSelective(check);
        } else {
            tCheckItemMapper.deleteByCheckId(check.getId());
            tCheckPartMapper.deleteByCheckId(check.getId());
        }
        for (ThreePartReqDTO cpr : dto.getPartList()) {
            TCheckPart cp = new TCheckPart();
            cp.setName(cpr.getName());
            cp.setCheckId(check.getId());
            tCheckPartMapper.insertSelective(cp);
            tCheckItemMapper.insertThreeBath(cpr.getList(), check.getId(), cp.getId());
        }
    }

    /**
     * @param 保存村
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void villageSave(Integer region, Village village, String vName, String vPsw, String jw, Result result)
            throws Exception {
        if (null == village.getUserId()) {// 新增
            Village v = villageMapper.selectByName(village);
            if (null != v) {
                result.setStatus("1");
                result.setMap("message", "该村已存在");
                return;
            }
            User uu = userMapper.selectUserByUserName(vName);
            if (null != uu) {
                result.setStatus("1");
                result.setMap("message", "该登录名已存在");
                return;
            }
            User user = new User();
            user.setUserName(vName);
            user.setPsw(EncryptUtil.encrypt(vPsw));
            user.setUserType(4);
            user.setIsFreeze("0");
            user.setIsDel("0");
            user.setCreateTime(new Date());
            if (StringUtils.isNotBlank(jw)) {
                user.setLongitude(jw.split(",")[0]);
                user.setLatitude(jw.split(",")[1]);
            }
            userMapper.insertSelective(user);
            village.setUserId(user.getId());
            village.setAccountnum(vName);
            ;
            villageMapper.insertSelective(village);
        } else {// 编辑
            if (StringUtils.isNotBlank(jw)) {
                User user = new User();
                user.setId(village.getUserId());
                user.setLongitude(jw.split(",")[0]);
                user.setLatitude(jw.split(",")[1]);
                userMapper.updateByPrimaryKeySelective(user);
            }
            villageMapper.updateByPrimaryKeySelective(village);
        }
    }

    /**
     * @param 保存镇
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void townSave(Town town, String tName, String tPsw, String jw, String villageids, Result result)
            throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        if (null == town.getUserId()) {// 新增
            Town v = townMapper.selectByName(town);
            if (null != v) {
                result.setStatus("1");
                result.setMap("message", "该镇已存在");
                return;
            }
            User uu = userMapper.selectUserByUserName(tName);
            if (null != uu) {
                result.setStatus("1");
                result.setMap("message", "该登录名已存在");
                return;
            }
            User user = new User();
            user.setUserName(tName);
            user.setPsw(EncryptUtil.encrypt(tPsw));
            user.setUserType(3);
            user.setIsFreeze("0");
            user.setIsDel("0");
            user.setCreateTime(new Date());
            if (StringUtils.isNotBlank(jw)) {
                user.setLongitude(jw.split(",")[0]);
                user.setLatitude(jw.split(",")[1]);
            }
            userMapper.insertSelective(user);
            town.setUserId(user.getId());
            townMapper.insertSelective(town);
        } else {// 编辑
            if (StringUtils.isNotBlank(jw)) {
                User user = new User();
                user.setId(town.getUserId());
                user.setLongitude(jw.split(",")[0]);
                user.setLatitude(jw.split(",")[1]);
                userMapper.updateByPrimaryKeySelective(user);
            }
            townMapper.updateByPrimaryKeySelective(town);
            // 将之前的村设置为null
            m.put("townId", town.getUserId());
            villageMapper.updTownNull(m);
        }
        // 跟新村townid
        if (StringUtils.isNotBlank(villageids)) {
            m.put("townId", town.getUserId());
            m.put("ids", villageids);
            villageMapper.updTown(m);
        }
    }

    /**
     * @param 保存区县
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void districtSave(District d, String tName, String tPsw, String jw, String villageids, Result result)
            throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        if (null == d.getUserId()) {// 新增
            District v = districtMapper.selectByName(d);
            if (null != v) {
                result.setStatus("1");
                result.setMap("message", "该区县已存在");
                return;
            }
            User uu = userMapper.selectUserByUserName(tName);
            if (null != uu) {
                result.setStatus("1");
                result.setMap("message", "该登录名已存在");
                return;
            }
            User user = new User();
            user.setUserName(tName);
            user.setPsw(EncryptUtil.encrypt(tPsw));
            user.setUserType(6);
            user.setIsFreeze("0");
            user.setIsDel("0");
            user.setCreateTime(new Date());
            if (StringUtils.isNotBlank(jw)) {
                user.setLongitude(jw.split(",")[0]);
                user.setLatitude(jw.split(",")[1]);
            }
            userMapper.insertSelective(user);
            d.setUserId(user.getId());
            districtMapper.insertSelective(d);
        } else {// 编辑
            if (StringUtils.isNotBlank(jw)) {
                User user = new User();
                user.setId(d.getUserId());
                user.setLongitude(jw.split(",")[0]);
                user.setLatitude(jw.split(",")[1]);
                userMapper.updateByPrimaryKeySelective(user);
            }
            districtMapper.updateByPrimaryKeySelective(d);
            // 将之前的村设置为null
            m.put("districtId", d.getUserId());
            townMapper.updTownNull(m);
        }
        // 跟新村townid
        if (StringUtils.isNotBlank(villageids)) {
            m.put("districtId", d.getUserId());
            m.put("ids", villageids);
            townMapper.updTown(m);
        }
    }

    /**
     * @param 保存行业
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void tradeSave(Integer userId, String userName, String tradeName, String userPsw, String companyArea,
                          Result result) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        if (null == userId) {// 新增
            Trade v = tradeMapper.selectByName(tradeName);
            if (null != v) {
                result.setStatus("1");
                result.setMap("message", "该区县已存在");
                return;
            }
            User uu = userMapper.selectUserByUserName(userName);
            if (null != uu) {
                result.setStatus("1");
                result.setMap("message", "该登录名已存在");
                return;
            }
            User user = new User();
            user.setUserName(userName);
            user.setPsw(EncryptUtil.encrypt(userPsw));
            user.setUserType(10);
            user.setIsFreeze("0");
            user.setIsDel("0");
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);
            userId = user.getId();
            Trade trade = new Trade();
            trade.setUserId(user.getId());
            trade.setName(tradeName);
            tradeMapper.insertSelective(trade);
        } else {// 编辑
            User user = new User();
            user.setId(userId);
            user.setUserName(userName);
            userMapper.updateByPrimaryKeySelective(user);
            Trade trade = new Trade();
            trade.setUserId(userId);
            trade.setName(tradeName);
            tradeMapper.updateByPrimaryKeySelective(trade);
            // 将之前的企业删除
            m.put("tid", userId);
            tradeCompanyMapper.deleteByUserId(m);
        }
        // 更新企业
        if (StringUtils.isNotBlank(companyArea)) {
            String[] str = companyArea.split("\\,");
            TradeCompany tradeCompany = null;
            for (int i = 0; i < str.length; i++) {
                tradeCompany = new TradeCompany();
                tradeCompany.setUid(Integer.valueOf(str[i]));
                tradeCompany.setTid(userId);
                tradeCompany.setIskey(0);
                tradeCompanyMapper.insertSelective(tradeCompany);
            }
        }
    }

    /**
     * @param 排查治理记录添加
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void checkListAdd(Integer _userId, TCheck tCheck, String items, AppResult result) throws Exception {
        String title = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String[] type = new String[]{"企业自查", "行政执法", "第三方委托排查", "行政检查"};
        tCheck.setTitle(title + type[tCheck.getFlag() - 1]);
        tCheck.setCreateUser(_userId);
        tCheck.setType(1);
        tCheck.setIndustryType(2);
        tCheck.setStatus(2);
        tCheck.setCreateTime(new Date());
        tCheck.setRealTime(new Date());
        tCheck.setDel(0);
        tCheckMapper.insertSelective(tCheck);
        TCheckPart TCheckPart = new TCheckPart();
        TCheckPart.setCheckId(tCheck.getId());
        tCheckPartMapper.insertSelective(TCheckPart);
        List<TCheckItem> list = gson.fromJson(items, new TypeToken<List<TCheckItem>>() {
        }.getType());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setPartId(TCheckPart.getId());
            list.get(i).setCheckId(tCheck.getId());
            list.get(i).setStatus(2);
            tCheckItemMapper.insertSelective(list.get(i));
        }
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("checkId", tCheck.getId());
        result.setData(m);
    }

    /**
     * (非 Javadoc) 现场记录保存
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Integer checkDocumentSave8(Integer checkId, Integer userId, String copy, Integer flag2, String itms,
                                      Integer loginUserId, String checkCompany) throws Exception {
        Date d = new Date();
        if (null == checkId) {// 直接添加现场记录的 自动补充检查表结构
            TCheck check = new TCheck();
            TCheckPart part = new TCheckPart();
            List<TCheckItem> itemL = gson.fromJson(itms, new TypeToken<List<TCheckItem>>() {
            }.getType());
            check.setRealTime(d);
            check.setFlag(flag2);
            if (flag2 == 2) {
                check.setTitle("行政执法");
            }
            if (flag2 == 3) {
                check.setTitle("委托检查");
            }
            if (flag2 == 4) {
                check.setTitle("行政检查");
            }
            Company c = companyMapper.selectByPrimaryKey(userId);
            check.setUserId(userId);
            check.setDepart(c.getName());
            check.setCreateUser(loginUserId);
            check.setType(1);
            check.setCreateTime(d);
            check.setStatus(2);
            check.setDel(0);
            check.setCheckCompany(checkCompany);
            tCheckMapper.insertSelective(check);
            part.setName("");
            part.setCheckId(check.getId());
            tCheckPartMapper.insertSelective(part);
            tCheckItemMapper.insertThreeBath(itemL, check.getId(), part.getId());
            checkId = check.getId();
        }
        TCheckDocument doc = new TCheckDocument();
        doc.setCheckId(checkId);
        doc.setFlag(8);
        doc.setFlag2(flag2);
        doc.setCopy(copy);
        doc.setCreateTime(d);
        doc.setCreateUser(loginUserId);
        doc.setUserId(userId);
        doc.setDel(0);
        tCheckDocumentMapper.insertSelective(doc);
        return checkId;
    }

    /**
     * (非 Javadoc)
     *
     * @param p
     * @param ids
     * @throws Exception
     * @see com.spring.web.service.cgf.CgfService#pushMessageSave(com.spring.web.model.PushMessage, String)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void pushMessageSave(PushMessage p, String ids) throws Exception {
        pushMessageMapper.insertSelective(p);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", p.getUid());
        m.put("pushId", p.getId());
        m.put("ids", ids.split(","));
        pushMessageUserMapper.insertBath(m);
    }

    /**
     * @param id
     * @throws Exception 检查表删除
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void checkDel(Integer id) throws Exception {
        String[] sql = new String[]{
                "delete from t_recheck_item_tbl WHERE recheck_id in (select id from t_recheck_tbl where check_id = "
                        + id + ")", "delete from t_recheck_tbl WHERE check_id = " + id,
                "delete from t_check_item_tbl WHERE check_id = " + id,
                "delete from t_check_part_tbl WHERE check_id = " + id,
                "delete from t_check_admin_tbl WHERE check_id = " + id,
                "delete from t_check_document_tbl WHERE check_id = " + id,
                "delete from t_rectification_tbl WHERE check_id = " + id, "delete from t_check_tbl WHERE id = " + id,};
        for (String s : sql) {
            tCheckMapper.deleteById(s);
        }
    }

    /**
     * @param dto 企业查询分页
     * @param model
     */
//     public void selectCompanyWithPage(CompanyListReqDTO dto, User user, Model model) {
//         String industry2 = null;
//         if(StringUtils.isNotBlank(dto.getIndustry2_())) {
//             industry2 = dto.getIndustry2_();
//             if(StringUtils.isNotBlank(dto.getIndustry2_2())) {
//                 industry2 = industry2 + " > " + dto.getIndustry2_2();
//             }
//         }
//         log.error("userids sql start:" + System.currentTimeMillis());
//         List<Integer> userIds = getCompanyUserIdsWithPage(dto, user);
//         log.error("userids sql end:" + System.currentTimeMillis());
//         model.addAttribute("total", companyMapper.selectIdsByCompany_view_count(dto));
//         log.error("userids total sql end:" + System.currentTimeMillis());
//         if(userIds.size() > 0) {
//             dto.setUserIds(StringUtils.join(userIds, ","));
////             dto.setUserIds(getUserIdsByPage(userIds, dto));
//             model.addAttribute("list", companyMapper.selectByIds(dto));
//             log.error("userids list sql end:" + System.currentTimeMillis());
//         }
//         model.addAttribute("dto", dto);
//     }


//     String getUserIdsByPage(List<Integer> userIds, CompanyListReqDTO dto) {
//         Integer from = dto.getIndex();
//         Integer end = dto.getIndex() + dto.getRows();
//         StringBuilder ids = new StringBuilder();
//         for(;from<=end && from < userIds.size();from++) {
//             if(!ids.toString().equals("")) {
//                 ids.append(",");
//             }
//             ids.append(userIds.get(from));
//         }
//         return ids.toString();
//     }

    /**
     * @param dto   TODO 企业查询分页 功能进行显示
     *              就是显示出是否为重点企业
     * @param model
     */
    public void selectCompanyWithPage(CompanyListReqDTO dto, User user, Model model) {
        String industry2 = null;
        if (StringUtils.isNotBlank(dto.getIndustry2_())) {
            industry2 = dto.getIndustry2_();
            if (StringUtils.isNotBlank(dto.getIndustry2_2())) {
                industry2 = industry2 + " > " + dto.getIndustry2_2();
            }
        }
        dto.setIndustry2(industry2);
        List<Integer> userIds = getCompanyUserIdsWithPage(dto, user);

        //log.error("userIds："+userIds.toString());

        String all_userIds = dto.getUserIds();
        if ("-1".equals(all_userIds)) {
            model.addAttribute("total", 0);
        } else {
            model.addAttribute("total", all_userIds.split(",").length);
        }
        if (userIds.size() > 0) {
            dto.setUserIds(StringUtils.join(userIds, ","));
//            dto.setUserIds(getUserIdsByPage(userIds, dto));
            model.addAttribute("list", companyMapper.selectByIds(dto));
        }
        dto.setUserIds(all_userIds);
        model.addAttribute("dto", dto);
    }

    // String getUserIdsByPage(List<Integer> userIds, CompanyListReqDTO dto) {
    // Integer from = dto.getIndex();
    // Integer end = dto.getIndex() + dto.getRows();
    // StringBuilder ids = new StringBuilder();
    // for(;from<=end && from < userIds.size();from++) {
    // if(!ids.toString().equals("")) {
    // ids.append(",");
    // }
    // ids.append(userIds.get(from));
    // }
    // return ids.toString();
    // }
    List<Integer> getCompanyUserIdsWithPage(CompanyListReqDTO dto, User user) {
        setUserId2(user, dto);
        List<Integer> ll = new ArrayList<Integer>();
        Integer from = dto.getPage() * dto.getRows();
        Integer end = null;
        if (StringUtils.isBlank(dto.getUserIds()) || "-1".equals(dto.getUserIds())) {
            List<Integer> list = getCompanyUserIds(dto, user);
            end = Math.min(from + dto.getRows(), list.size());
            if (list.size() == 0) {
                dto.setUserIds("-1");
            } else {
                dto.setUserIds(StringUtils.join(list, ","));
                for (; from < end; from++) {
                    ll.add(list.get(from));
                }
            }
            return ll;
        } else {
            String[] userIds = dto.getUserIds().split(",");
            end = Math.min(from + dto.getRows(), userIds.length);
            for (; from < end; from++) {
                ll.add(Integer.valueOf(userIds[from]));
            }
        }
        return companyMapper.selectIdsByCompany_viewWithPage(dto);
    }

    public List<Integer> getCompanyUserIds(CompanyListReqDTO dto, User user) {
        if (null != user) {
            setUserId2(user, dto);
        }
        return companyMapper.selectIdsByCompany_view(dto);
    }

    protected void setUserId2(User user, CompanyListReqDTO dto) {
        if (user.getUserType() == 4) {
            dto.setVillageId(user.getId());
        }
        if (user.getUserType() == 5) {
            dto.setUserId(user.getId());
        }
        if (user.getUserType() == 3) {
            dto.setTownId(user.getId());
        }
        if (user.getUserType() == 6) {
            dto.setDistrictId(user.getId());
        }
        if (user.getUserType() == 9) {
        }
        if (user.getUserType() == 10) {
            dto.setTradeId(user.getId());
        }
    }

    /**
     * @param 固有风险研判表保存
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void naturaldangerSave(NaturaldangerSaveReqDTO dto) throws Exception {
        log.error("cfg naturaldangerSave");
        Date date = new Date();

        Integer id = 1;
        FcNaturalDangerStudy lastrecord = fcNaturalDangerStudyMapper.selectLastRecord();
        //log.error("lastrecord"+lastrecord.getId());
        if (lastrecord != null) {
            log.error("lastrecord==null");
            id = lastrecord.getId() + 1;
        }
        log.error("dangerstudyid:" + id);

        FcNaturalDangerStudy study = dto.getStudy();
        study.setId(id);
        study.setCtime(date);

        fcNaturalDangerStudyMapper.insertSelective(study);

        //log.error("checker:"+study.getChecker());

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("list", dto.getList());
        m.put("dangerstudyid", id);
        fcNaturalDangerItemMapper.insertBath(m);
    }


    /**
     * @param 目标履职考核-保存
     * @throws Exception
     */
    public void targetAssessmentSave(FcTargetAssessment fcTargetAssessment, User user) throws Exception {

        FcTargetAssessment fcTargetAssessment_ret = fcTargetAssessmentMapper.selectByUidToday(user.getId());

        if (fcTargetAssessment_ret == null) {
            fcTargetAssessment.setUid(user.getId());
            fcTargetAssessment.setKhsj(new Date());
            fcTargetAssessment.setDel(0);
            fcTargetAssessment.setCtime(new Date());
            fcTargetAssessmentMapper.insertSelective(fcTargetAssessment);
        } else {
            fcTargetAssessment.setId(fcTargetAssessment_ret.getId());
            fcTargetAssessmentMapper.updateByPrimaryKeySelective(fcTargetAssessment);
        }
    }

    /**
     * @param 临时风险研判表格填写数目-保存
     * @throws Exception
     */
    public void fcTemporaryDangerCensusSave(FcTemporaryDangerCensus fcTemporaryDangerCensus, User user) throws Exception {

        FcTemporaryDangerCensus tdc = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());

        if (tdc == null) {
            fcTemporaryDangerCensus.setUid(user.getId());
            fcTemporaryDangerCensus.setDel(0);
            fcTemporaryDangerCensus.setCtime(new Date());
            fcTemporaryDangerCensusMapper.insertSelective(fcTemporaryDangerCensus);
        } else {
            //log.error("ID："+tdc.getId());
            fcTemporaryDangerCensus.setId(tdc.getId());
            fcTemporaryDangerCensus.setCtime(new Date());
            //fcTemporaryDangerCensusMapper.insert(fcTemporaryDangerCensus);//id为关键字，数据已存在就不可使用insert
            //log.error("fcTemporaryDangerCensus："+fcTemporaryDangerCensus.toString());
            fcTemporaryDangerCensusMapper.updateByPrimaryKeySelective(fcTemporaryDangerCensus);
        }
    }

    /**
     * @param 重大/较大风险研判表保存
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void greatdangerSave(GreatdangerSaveReqDTO dto) throws Exception {
        log.error("cfg greatdangerSave");
        Date date = new Date();
        FcGreatDangerStudy study = dto.getStudy();
//    	 log.error("uid:"+study.getUid());
//    	 log.error("checker:"+study.getDangerstudyid());
//    	 log.error("checker:"+study.getChecker());
        study.setCtime(date);
        fcGreatDangerStudyMapper.insertSelective(study);

        log.error("list:" + dto.getList());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("list", dto.getList());
        fcNaturalDangerItemMapper.updateBatch(m);

    }

    /**
     * @param 临时风险研判表保存
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void temporarydangerSave(TemporaryDangerSaveReqDTO dto) throws Exception {
        log.error("cfg temporarydangerSave");
        Date date = new Date();

        Integer id = 1;
        FcTemporaryDangerStudy lastrecord = fcTemporaryDangerStudyMapper.selectLastRecord();
        if (lastrecord != null) {
            id = lastrecord.getId() + 1;
        }
        log.error("Tdangerstudyid:" + id);

        FcTemporaryDangerStudy study = dto.getStudy();
        study.setId(id);
        study.setTresult(1);
        //判断临时风险研判结果list中是否存在不合格0；存在则将tresult字段改为0
        List<FcTemporaryDangerItem> listCheck = dto.getList();
        log.error("list_size：" + listCheck.size());
        for (int i = 0; i < listCheck.size(); i++) {
            if (listCheck.get(i).getCheckret() == 0) {
                study.setTresult(0);
                break;
            }
        }
        study.setCtime(date);
        //log.error("FcTemporaryDangerStudy:"+study.toString());
        fcTemporaryDangerStudyMapper.insertSelective(study);

        log.error("list:" + dto.getList());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("list", dto.getList());
        m.put("tdangerstudyid", id);
        fcTemporaryDangerItemMapper.insertBath(m);

    }

    /**
     * @param 更新视频源
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void monitorUpdate(String puids, String ids, String names, Integer userId) throws Exception {
//    	 Map<String, Object> m = new HashMap<String, Object>();
//    	 //1、全部删除
//         m.put("del", 1);
//         m.put("userId",userId);
//    	 monitorMapper.updateByUserId(m);
//    	 //2、恢复前后端都有的源;3、新增只有前端有的源
//    	 String[] puid = puids.split(",");
//    	 String[] id = ids.split(",");
//    	 String[] name = names.split(",");
//    	 if(puid.length!=id.length){
//    		 throw new Exception();
//    	 }
//    	 List<Map<String, Object>> list;
//    	 for(int i=0;i<puid.length;i++){
//    		 m.clear();
//    		 m.put("name", name[i]);
//    		 m.put("userId", userId);
//    		 list = monitorMapper.selectWithDelByMap(m);
//    		 //log.error("name:"+name[i]+",userId:"+userId+",list.size():"+list.size());
//    		 if(list.size()>0){
//    			 log.error(name[i]+"is exist");
//    			 m.put("del", 0);
//    			 m.put("puid",puid[i]);
//    			 m.put("channelId",id[i]);
//    			 monitorMapper.updateByUserIdAndName(m);//恢复
//    		 }
//    		 else{
//    			 log.error(name[i]+"is not exist");
//    			 Monitor monitor = new Monitor();
//    			 monitor.setUserId(userId);
//    			 monitor.setName(name[i]);
//    			 monitor.setPuid(puid[i]);
//    			 monitor.setChannelId(Integer.parseInt(id[i]));
//    			 String tname = monitor.getName();
//    			 //log.error("tname:"+tname);
//    			 monitorMapper.insertSelective(monitor); 
//    		 }
//    	 }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", userId);
        List<Map<String, Object>> list = monitorMapper.selectByMap(m);
        //List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        //log.error("list.size 1:"+list.size());
        //删除所有摄像头
        if (names.equals("") && puids.equals("") && ids.equals("")) {
            log.error("remove all video for userId:" + userId);
            m.clear();
            m.put("del", 1);
            m.put("userId", userId);
            monitorMapper.updateByUserId(m);
            return;
        }

        //开始同步
        //1、遍历前端视频源集合，后无insert,后有update
        String[] puid = puids.split(",");
        String[] id = ids.split(",");
        String[] name = names.split(",");
        if (!(puid.length == id.length && puid.length == name.length)) {
            throw new Exception();
        }
        for (int i = 0; i < name.length; i++) {
            boolean has = false;//后端有无此名字的摄像头标志
            Iterator<Map<String, Object>> iter = list.iterator();
            while (iter.hasNext()) {
                Map<String, Object> a = iter.next();
                if (name[i].equals(a.get("name"))) {
                    has = true;
                    iter.remove();
                }
            }
            //log.error("list.size 2:"+list.size());
            if (has) {
                //更新视频源
                log.error(name[i] + "is exist");
                m.clear();
                m.put("name", name[i]);
                m.put("userId", userId);
                m.put("puid", puid[i]);
                m.put("channelId", id[i]);
                monitorMapper.updateByUserIdAndName(m);//更新
            } else {
                log.error(name[i] + "is not exist");
                Monitor monitor = new Monitor();
                monitor.setUserId(userId);
                monitor.setName(name[i]);
                monitor.setPuid(puid[i]);
                monitor.setChannelId(Integer.parseInt(id[i]));
                monitorMapper.insertSelective(monitor);
            }
        }

        //log.error("list.size 3:"+list.size());

        //2、遍历后端仅有的视频源集合
        for (Map<String, Object> a : list) {
            m.clear();
            m.put("name", a.get("name"));
            m.put("userId", a.get("userId"));
            m.put("del", 1);
            log.error("del name:" + a.get("name") + ",userId:" + a.get("userId"));
            monitorMapper.updateByUserIdAndName(m);//删除
        }
    }
}
