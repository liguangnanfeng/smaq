package com.spring.web;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.spring.web.service.CheckCompany.SaveMessageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.web.bean.GlobalRegionNameBean;
import com.spring.web.dao.*;
import com.spring.web.ibatis.BaseDao;
import com.spring.web.ibatis.DynamicDBValues;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.AppToken;
import com.spring.web.model.Regulation;
import com.spring.web.model.User;
import com.spring.web.service.export.ExportService;
import com.spring.web.service.user.UserService;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.EncacheUtil;
import com.spring.web.util.SessionUtil;

/**
 * @Title: BaseController
 * @Description: 基础控制器
 * @author FL
 * @date 2016年1月19日 下午2:21:49
 * @version V1.0
 * @param
 *
 */
public class BaseController implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8423236445501128350L;
    protected static final Logger log = Logger.getLogger("R");
    @Autowired
    protected MessageSource messageSource;
    /** Mapper类引入开始 */
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected VillageMapper villageMapper;
    @Autowired
    protected GlobalRegionMapper globalRegionMapper;
    @Autowired
    protected TownMapper townMapper;
    @Autowired
    protected LibraryMapper libraryMapper;
    @Autowired
    protected CompanyMapper companyMapper;
    @Autowired
    protected DetectionMapper detectionMapper;
    @Autowired
    protected EvaluationMapper evaluationMapper;
    @Autowired
    protected FacilitiesMapper facilitiesMapper;
    @Autowired
    protected SequipmentMapper sequipmentMapper;
    @Autowired
    protected SpersonMapper spersonMapper;
    @Autowired
    protected NoticeMapper noticeMapper;
    @Autowired
    protected RegulationMapper regulationMapper;
    @Autowired
    protected PequipmentMapper pequipmentMapper;
    @Autowired
    protected MequipmentMapper mequipmentMapper;
    @Autowired
    protected MaterialMapper materialMapper;
    @Autowired
    protected ProductMapper productMapper;
    @Autowired
    protected ExaminationMapper examinationMapper;
    @Autowired
    protected DangerLibMapper dangerLibMapper;
    @Autowired
    protected ImportRecordMapper importRecordMapper;
    @Autowired
    protected Table2Mapper table2mapper;
    @Autowired
    protected TableMapper tablemapper;
    @Autowired
    protected Table3Mapper table3mapper;
    @Autowired
    protected Table4Mapper table4mapper;
    @Autowired
    protected TIndustryMapper tIndustryMapper;
    @Autowired
    protected TLevelMapper tLevelMapper;
    @Autowired
    protected TItemMapper tItemMapper;
    @Autowired
    protected TCompanyMapper tCompanyMapper;
    @Autowired
    protected TTindustryMapper tTindustryMapper;
    @Autowired
    protected TCheckMapper tCheckMapper;
    @Autowired
    protected TCheckItemMapper tCheckItemMapper;
    @Autowired
    protected StandardMapper standardMapper;
    @Autowired
    protected PingMapper pingMapper;
    @Autowired
    protected Table5Mapper table5Mapper;
    @Autowired
    protected Table6Mapper table6Mapper;
    @Autowired
    protected FengMapper fengMapper;
    @Autowired
    protected HuaMapper huaMapper;
    @Autowired
    protected TModelMapper tModelMapper;
    @Autowired
    protected TModelPartMapper tModelPartMapper;
    @Autowired
    protected TCheckPartMapper tCheckPartMapper;
    @Autowired
    protected TRectificationMapper tRectificationMapper;
    @Autowired
    protected TRecheckMapper tRecheckMapper;
    @Autowired
    protected TRecheckItemMapper tRecheckItemMapper;
    @Autowired
    protected AppTokenMapper appTokenMapper;
    @Autowired
    protected TCheckDocumentMapper tCheckDocumentMapper;
    @Autowired
    protected CompanyRegMapper companyRegMapper;
    @Autowired
    protected TContingencyPlanMapper tContingencyPlanMapper;
    @Autowired
    protected TDrillMapper tDrillMapper;
    @Autowired
    protected TAccidentMapper tAccidentMapper;
    @Autowired
    protected MonitorMapper monitorMapper;
    @Autowired
    protected CompanyIskeyMapper companyIskeyMapper;
    @Autowired
    protected LawDocMapper lawDocMapper;
    @Autowired
    protected ProductionProcessDiagramMapper productionProcessDiagramMapper;
    @Autowired
    protected LightningProtectionMapper lightningProtectionMapper;
    @Autowired
    protected DistrictMapper districtMapper;
    @Autowired
    protected OfficialsMapper officialsMapper;
    @Autowired
    protected SafetyFacilitiesMapper safetyFacilitiesMapper;
    @Autowired
    protected CyryjkjhMapper cyryjkjhMapper;
    @Autowired
    protected TradeCompanyMapper tradeCompanyMapper;
    @Autowired
    protected TradeMapper tradeMapper;
    @Autowired
    protected TItemSeriousMapper tItemSeriousMapper;
    @Autowired
    protected QccDataMapper qccDataMapper;
    @Autowired
    protected PushMessageMapper pushMessageMapper;
    @Autowired
    protected PushMessageUserMapper pushMessageUserMapper;
    @Autowired
    protected AImplementationMapper aImplementationMapper;
    @Autowired
    protected ADangerManualMapper aDangerManualMapper;
    @Autowired
    protected ACompanyManualMapper aCompanyManualMapper;
    @Autowired
    protected AZytjfxcdpjMapper aZytjfxcdpjMapper;
    @Autowired
    protected AWhpMapper aWhpMapper;
    @Autowired
    protected AFxjzMapper aFxjzMapper;
    @Autowired
    protected AFxgzpMapper aFxgzpMapper;
    @Autowired
    protected AMaterialMapper aMaterialMapper;
    @Autowired
    protected AGzkMapper aGzkMapper;
    @Autowired
    protected AHxysgzkMapper aHxysgzkMapper;
    @Autowired
    protected ADangerTypeMapper aDangerTypeMapper;
    @Autowired
    protected AGyMapper aGyMapper;
    @Autowired
    protected ZzjgCompanyMapper zzjgCompanyMapper;
    @Autowired
    protected ZzjgDepartmentMapper zzjgDepartmentMapper;
    @Autowired
    protected ZzjgPersonnelMapper zzjgPersonnelMapper;
    @Autowired
    protected PushMessMapper pushMessMapper;
    @Autowired
    protected ACompanyManualZpMapper aCompanyManualZpMapper;
    @Autowired
    protected FcFireworkPermitMapper fcFireworkPermitMapper;
    @Autowired
    protected FcLimitspacePermitMapper fcLimitspacePermitMapper;
    @Autowired
    protected FcBlindplatePermitMapper fcBlindplatePermitMapper;
    @Autowired
    protected FcHighsafetyPermitMapper fcHighsafetyPermitMapper;
    @Autowired
    protected FcHoistingPermitMapper fcHoistingPermitMapper;
    @Autowired
    protected FcPowersafetyPermitMapper fcPowersafetyPermitMapper;
    @Autowired
    protected FcBrokenlandPermitMapper fcBrokenlandPermitMapper;
    @Autowired
    protected FcLandworkPermitMapper fcLandworkPermitMapper;
    @Autowired
    protected FcOverhaulOperationMapper fcOverhaulOperationMapper;
    @Autowired
    protected FcContractorOperationMapper fcContractorOperationMapper;
    @Autowired
    protected FcPromiseMapper fcPromiseMapper;
    @Autowired
    protected FcNaturalDangerStudyMapper fcNaturalDangerStudyMapper;
    @Autowired
    protected FcNaturalDangerItemMapper fcNaturalDangerItemMapper;
    @Autowired
    protected FcTargetAssessmentMapper fcTargetAssessmentMapper;
    @Autowired
    protected FcGreatDangerStudyMapper fcGreatDangerStudyMapper;
    @Autowired
    protected AGwyjMapper aGwyjMapper;
    @Autowired
    protected AGwyjShMapper aGwyjShMapper;
    @Autowired
    protected FcTemporaryDangerCensusMapper fcTemporaryDangerCensusMapper;
    @Autowired
    protected FcTemporaryDangerMapper fcTemporaryDangerMapper;
    @Autowired
    protected FcTemporaryDangerStudyMapper fcTemporaryDangerStudyMapper;
    @Autowired
    protected FcTemporaryDangerItemMapper fcTemporaryDangerItemMapper;
    @Autowired
    protected YzHighTemperatureMapper yzHighTemperatureMapper;
    @Autowired
    protected YzLiquidAmmoniaMapper yzLiquidAmmoniaMapper;
    @Autowired
    protected YzMetallurgyMapper yzMetallurgyMapper;
    @Autowired
    protected YzStiveExplosionMapper yzStiveExplosionMapper;
    @Autowired
    protected YzTradeArderMapper yzTradeArderMapper;
    @Autowired
    protected YzStaffMapper yzStaffMapper;
    @Autowired
    protected YzMajorEquipmentMapper yzMajorEquipmentMapper;
    @Autowired
    protected YzSpecialEquipmentMapper yzSpecialEquipmentMapper;
    @Autowired
    protected YzSafetyFacilitiesMapper yzSafetyFacilitiesMapper;
    @Autowired
    protected VideoInfoMapper videoInfoMapper;
    @Autowired
    protected TradeCliqueMapper tradeCliqueMapper;

    @Autowired
    protected SaveMessageService saveMessageService;


    @Autowired
    protected TSafetyStandardMapper tSafetyStandardMapper;

    /** Mapper类引入结束 */

    /** Service类引入开始 */
    @Autowired
    protected UserService userService;
    @Autowired
    protected ExportService exportService;
    /** Service类引入结束 */
    /**
     * 基础DAO
     */
    @Autowired
    protected BaseDao baseDao;

    protected Gson gson = new GsonBuilder().create();

    /**
     * 根据区域ID查询所在区域名称
     */

    protected final static String SQL_SELECT_GLOBAL_REGION_NAME_BY_KEY = "globalRegion.selectGlobalRegionNameByKey";

    /**
     * 获取登录用户信息
     * TODO使用工具类获取session中的用户信息
     * @param request
     * @return user  两种情况
     * @throws Exception
     */

    protected User getLoginUser(HttpServletRequest request) {
        User user = SessionUtil.getUser(request);
        if(null == user) {
            Subject currentUser = SecurityUtils.getSubject();
            if(null != currentUser) {
                Object name = currentUser.getPrincipal();
                if(null != name) {
                    user = userMapper.selectUserByUserName((String)name);
                    SessionUtil.setUser(request, user);
                    return user;
                }
            }
        }
        return user;
    }
    //TODO 权限?
    protected String getUserAuthority(HttpServletRequest request) {
        return null;
    }

    // TODO 获取用户ip
    protected String getUserIp(HttpServletRequest request) {
        // SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
        // .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        // WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl
        // .getAuthentication().getDetails();
        // // 获得访问地址
        // return details.getRemoteAddress();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 取得上传绝对路径
     *
     * @param request
     * @return
     */
    protected String getUploadPath(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/");
    }

    /**
     * 根据区域ID查询所在区域名称
     *
     * @param globalRegionId
     * @return
     */
    protected GlobalRegionNameBean getGlobalRegionNameByKey(Integer globalRegionId) {
        DynamicDBValues dy = baseDao.createDBPageValues();
        dy.put("id", globalRegionId);
        GlobalRegionNameBean globalRegionNameBean = baseDao.queryForOneObject(SQL_SELECT_GLOBAL_REGION_NAME_BY_KEY, dy);
        return globalRegionNameBean;
    }

    /**
     * TODO 获取app登录用户信息
     */
    protected Integer getAppUserId(HttpServletRequest request) {
        String access_token = request.getParameter("access_token");
        if (StringUtils.isEmpty(access_token)) {
            access_token =  request.getHeader("access_token");
            if (StringUtils.isEmpty(access_token)) {
                return null ;
            }
        }
        AppToken at = appTokenMapper.selectByPrimaryKey(access_token);
        Date date = new Date();
        Date end = DateConvertUtil.addDays(at.getLastLoginTime(), at.getExpires());
        if (null == at || end.before(date)) {// 超时 或 不存在
            return null;
        }
        String x = DateFormatUtils.format(date, "yyyy-MM-dd");
        String y = DateFormatUtils.format(at.getLastLoginTime(), "yyyy-MM-dd");
        if (!x.equals(y)) {
            // 一天登陆多次的，只记录第一次登陆时间
            at.setLastLoginTime(date);
            appTokenMapper.updateByPrimaryKeySelective(at);
        }
        return at.getUserId();
    }

    /**
     * @return 转码
     */
    protected String utf8Str(String val) {
        if (StringUtils.isBlank(val)) {
            return "";
        }
        try {
            return new String(val.getBytes("ISO8859_1"), "utf-8").replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param user
     * @param m 获取企业user_ids
     * @return
     */
    //TODO 对登陆用户进行判定,查询出不同角色,执行不同的方法
    EncacheUtil encacheUtil = new EncacheUtil();

    //     @SuppressWarnings({})
    protected Boolean setUserId(User user, Map<String, Object> m) {
        setUserId2(user, m);
        if (user.getUserType() == 9) {//国家
            if(null == m.get("companyName") && null == m.get("townId") && null == m.get("villageId")
                    && null == m.get("districtId")) {
                return true;
            }
        }
        // TODO 进行判断，是企业，再将企业的id存储到map集合
        if (user.getUserType() == 5) {//企业
            m.put("userIds", user.getId());
            return true;
        }
        List<Integer> userId = null;
        if (user.getUserType() == 10) {//行业
            userId = companyMapper.selectByCompany_trade(m);
        } else {
            userId = companyMapper.selectByCompany_view(m);
        }
/*         String key = gson.toJson(m);
         Element el = encacheUtil.getData(key);

         if(null == el) {
             if (user.getUserType() == 10) {//行业
                 userId = companyMapper.selectByCompany_trade(m);
             } else {
                 userId = companyMapper.selectByCompany_view(m);
             }
             encacheUtil.ehcachePut(key, userId);
         } else {
             userId = (List<Integer>) el.getObjectValue();
         }*/

//         m.put("villageId", null);
//         m.put("townId", null);
//         m.put("districtId", null);
//         m.put("tradeId", null);
        if(userId.size() == 0) {
            m.put("userIds", "-1");
            return false;
        } else {
            m.put("userIds", StringUtils.join(userId, ","));
            return true;
        }
    }

    protected void clearVillageTown(Map<String, Object> m) {
        m.put("villageId", null);
        m.put("townId", null);
        m.put("districtId", null);
        m.put("tradeId", null);
        m.put("companyName", null);
    }

    protected void setUserId2(User user, Map<String, Object> m) {
        if (user.getUserType() == 4) {
            m.put("villageId", user.getId());
        }
        if (user.getUserType() == 5) {
            m.put("userId", user.getId());
        }
        if (user.getUserType() == 3) {
            m.put("townId", user.getId());
        }
        if (user.getUserType() == 6) {
            m.put("districtId", user.getId());
        }
        if (user.getUserType() == 9) {
        }
        if (user.getUserType() == 10) {
            m.put("tradeId", user.getId());
        }
    }

    public LlHashMap<Object, Object> getLlMap() {
        return new LlHashMap<Object, Object>();
    }

    protected Regulation regulationGet(Integer userId) {
        Regulation rg = regulationMapper.selectByUserId(userId);
        if(null == rg) {
            rg = new Regulation();
            rg.setUserId(userId);
            regulationMapper.insertSelective(rg);
        }
        return rg;
    }

}
