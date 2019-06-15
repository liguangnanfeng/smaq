/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller.api;

import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.*;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.util.EncryptUtil;
import com.spring.web.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: AppController
 * @Description: TODO(用一句话描述该文件做什么)
 * @author FL
 * @date 2017年9月21日 下午3:56:10
 * @version V1.0
 */
@Controller
@RequestMapping(value = "api/xlb")
public class AppController_xlb extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 6419171568555467868L;
    @Autowired
    private CgfService cgfService;

    @Autowired
    private AppTokenData appTokenData;

    /**
     * 用户登录
     */
    @RequestMapping(value = "A001", method = RequestMethod.POST)
    public @ResponseBody
    AppResult login(HttpServletRequest request, User user) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        User db_user = userMapper.selectUserByUserName(user.getUserName());
        if (db_user == null) {
            result.setStatus("1");
            result.setMessage("用户不存在");
            return result;
        }

        if (!EncryptUtil.match(db_user.getPsw(), user.getPsw())) {
            result.setStatus("1");
            result.setMessage("密码不正确");
            return result;
        }

        map.put("user", db_user);
        String indexName = "";
        String charge = "";
        String dlevel = "";
        if (db_user.getUserType() == 5) {
            Company company = companyMapper.selectByPrimaryKey(db_user.getId());
            indexName = company.getName();
            charge = company.getCharge();
            dlevel = company.getDlevel();
        }
        if (db_user.getUserType() == 4) {
            Village village = villageMapper.selectByPrimaryKey(db_user.getId());
            indexName = village.getName();
        }
        if (db_user.getUserType() == 3) {
            indexName = townMapper.selectByPrimaryKey(db_user.getId()).getName();
        }
        if (db_user.getUserType() == 6) {
            indexName = districtMapper.selectByPrimaryKey(db_user.getId()).getName();
        }
        if (db_user.getUserType() == 9) {
            indexName = "安泰";
        }
        if (db_user.getUserType() == 10) {
            indexName = tradeMapper.selectByPrimaryKey(db_user.getId()).getName();
        }
        map.put("indexName", indexName);
        map.put("charge", charge);
        map.put("dlevel", dlevel);
        AppToken db_appToken = appTokenMapper.selectByUserId(String.valueOf(db_user.getId()));
        if (db_appToken == null) {
            AppToken appToken = new AppToken();
            appToken.setAccessToken(RandomUtil.generateUpperString(10));
            appToken.setLastLoginTime(new Date());
            appToken.setExpires(7);
            appToken.setUserId(db_user.getId());
            if (appTokenMapper.insertSelective(appToken) == 1) {
                map.put("appToken", appToken);
                result.setData(map);
                return result;
            }
        } else {
            db_appToken.setLastLoginTime((new Date()));
            db_appToken.setExpires(7);
            if (appTokenMapper.updateByPrimaryKeySelective(db_appToken) == 1) {
                map.put("appToken", db_appToken);
                result.setData(map);
            }
        }
        return result;
    }

    /**
     * 用户注销
     */
    @RequestMapping(value = "A002", method = RequestMethod.POST)
    public @ResponseBody
    AppResult logout(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        String access_token = request.getParameter("access_token");
        AppToken appToken = new AppToken();
        appToken.setAccessToken(access_token);
        appToken.setExpires(0);
        appTokenMapper.updateByPrimaryKeySelective(appToken);
        return result;
    }

    /**
     * 用户修改密码
     */
    @RequestMapping(value = "A003", method = RequestMethod.POST)
    public @ResponseBody
    AppResult updatePsw(HttpServletRequest request, String oldPsw, String newPsw) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (!EncryptUtil.match(user.getPsw(), oldPsw)) {
            result.setStatus("1");
            result.setMessage("原密码不正确");
            return result;
        }
        user.setPsw(EncryptUtil.encrypt(newPsw));
        if (userMapper.updateByPrimaryKey(user) == 1) {
            return result;
        }
        result.setStatus("1");
        result.setMessage("修改失败");
        return result;
    }

    /**
     * 小程序用户修改密码
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public @ResponseBody
    AppResult updatePassword(HttpServletRequest request,String userId,String oldPsw, String newPsw,@RequestBody Map<String,Object> param) {

        String uid = String.valueOf(param.get("userId"));
        String oldPassword = String.valueOf(param.get("oldPsw"));
        String newPassword = String.valueOf(param.get("newPsw"));
        AppResult result = new AppResultImpl();
            ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectByPrimaryKey(Integer.valueOf(uid));
            if(!EncryptUtil.match(zzjgPersonnel.getPassword(), oldPassword)){
                result.setStatus("1");
                result.setMessage("原密码不正确");
                return result;
            }
            zzjgPersonnel.setPassword(EncryptUtil.encrypt(newPassword));
            zzjgPersonnelMapper.updateByPrimaryKey(zzjgPersonnel);
        return result;
    }



    /**
     * 基本信息
     */
    @RequestMapping(value = "A004", method = RequestMethod.POST)
    public @ResponseBody
    AppResult basicInformation(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Company company = companyMapper.selectByPrimaryKey(userId);
        if (null != company.getRegionId()) {
            map.put("regionName", globalRegionMapper.selectRegionName(company.getRegionId()));
        }
        map.put("company", company);
        map.put("regulation", regulationGet(userId));
        result.setData(map);
        return result;
    }

    /**
     * 企业地图，经纬度信息
     */
    @RequestMapping(value = "A037", method = RequestMethod.POST)
    public @ResponseBody
    AppResult jwd(HttpServletRequest request, Integer companyId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Company company = companyMapper.selectByPrimaryKey(null == companyId ? userId : companyId);
        if (null != company.getRegionId()) {
            map.put("regionName", globalRegionMapper.selectRegionName(company.getRegionId()));
        }
        User u = userMapper.selectByPrimaryKey(null == companyId ? userId : companyId);
        map.put("company", company);
        map.put("longitude", u.getLongitude());
        map.put("latitude", u.getLatitude());
        result.setData(map);
        return result;
    }

    /**
     * 企业信息编辑
     */
    @RequestMapping(value = "A038")
    public @ResponseBody
    AppResult companyEditDo(Company c, String jw, HttpServletRequest request) throws Exception {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        companyMapper.updateByPrimaryKeySelective(c);
        User user = null;
        if (StringUtils.isNotBlank(jw)) {
            user = new User();
            user.setId(c.getUserId());
            String[] x = jw.split(",");
            user.setLatitude(x[1]);
            user.setLongitude(x[0]);
            userMapper.updateByPrimaryKeySelective(user);
        }
        return result;
    }

    /**
     * 主要原辅材料
     */
    @RequestMapping(value = "A005", method = RequestMethod.POST)
    public @ResponseBody
    AppResult mainMaterialList(HttpServletRequest request, String materialName) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        m.put("materialName", materialName);
        map.put("material", materialMapper.selectByUserId(m));
        result.setData(map);
        return result;
    }

    /**
     * 主要产品
     */
    @RequestMapping(value = "A006", method = RequestMethod.POST)
    public @ResponseBody
    AppResult mainProductList(HttpServletRequest request, String proName) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        m.put("proName", proName);
        map.put("product", productMapper.selectByUserId(m));
        result.setData(map);
        return result;
    }

    /**
     * 特种作业人员
     */
    @RequestMapping(value = "A007", method = RequestMethod.POST)
    public @ResponseBody
    AppResult spersonList(HttpServletRequest request, String spFlag) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        m.put("spFlag", spFlag);
        map.put("sperson", spersonMapper.selectSpersonList(m));
        result.setData(map);
        return result;
    }

    /**
     * 特种设备清单
     */
    @RequestMapping(value = "A008", method = RequestMethod.POST)
    public @ResponseBody
    AppResult sequipmentList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        map.put("sequipment", sequipmentMapper.selectSequipmentList(m));
        result.setData(map);
        return result;
    }

    /**
     * 消防应急设施
     */
    @RequestMapping(value = "A009", method = RequestMethod.POST)
    public @ResponseBody
    AppResult facList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        map.put("facilities", facilitiesMapper.selectFacilitiesList(m));
        result.setData(map);
        return result;
    }

    /**
     * 职业卫生检测
     */
    @RequestMapping(value = "A010", method = RequestMethod.POST)
    public @ResponseBody
    AppResult detList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        map.put("detection", detectionMapper.selectDetectionList(m));
        result.setData(map);
        return result;
    }

    /**
     * 职业健康检查
     */
    @RequestMapping(value = "A011", method = RequestMethod.POST)
    public @ResponseBody
    AppResult hetList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        map.put("examination", examinationMapper.selectByUserId(m));
        result.setData(map);
        return result;
    }

    /**
     * 安全评价档案
     */
    @RequestMapping(value = "A012", method = RequestMethod.POST)
    public @ResponseBody
    AppResult evaluation(HttpServletRequest request, String flag) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        m.put("flag", flag);
        map.put("evaluation", evaluationMapper.selectEvaluationList(m));
        result.setData(map);
        return result;
    }

    /**
     * 各类许可证
     */
    @RequestMapping(value = "A013", method = RequestMethod.POST)
    public @ResponseBody
    AppResult zhengList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        map.put("standard", standardMapper.selectList(m));
        result.setData(map);
        return result;
    }

    /**
     * 各类公告
     */
    @RequestMapping(value = "A014", method = RequestMethod.POST)
    public @ResponseBody
    AppResult noticeList(HttpServletRequest request, Notice notice) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", notice.getType());
        m.put("type2", notice.getType2());
        if(null != notice && null != notice.getType() && null != notice.getType2() && notice.getType().equals("2") && notice.getType2().equals(4)){
            m.clear();
            m.put("type", "1");
        }
        map.put("notice", noticeMapper.selectNoticeAppList(m));
        result.setData(map);
        return result;
    }

    /**
     * 公告内容
     */
    @RequestMapping(value = "A015", method = RequestMethod.POST)
    public @ResponseBody
    AppResult noticeDetail(HttpServletRequest request,@RequestBody Map<String, Object> params) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        String basic = String.valueOf(params.get("basic"));
        String id = (String) params.get("id");
        if(null == basic||"null".equals(basic)){
            map.put("notice", noticeMapper.selectByPrimaryKey(Integer.parseInt(id)));
        }else{
            map.put("notice", noticeMapper.selectByPrimaryKey(Integer.valueOf(basic)));
        }

        result.setData(map);
        return result;
    }

    /**
     * 企业自查
     */
    @RequestMapping(value = "A016", method = RequestMethod.POST)
    public @ResponseBody
    AppResult troubleList(HttpServletRequest request, Integer status) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        m.put("status", status);
        m.put("flag", 1);
        setUserId(userId, m);
        clearVillageTown(m);
        map.put("tCheck", tCheckMapper.selectList1(m));//wz 2019.01.02;由于政府端中隐患排查治理记录，userIds如果被注释会查出不属于从属关系的记录，所有复制查询一个带userIds一个不带userIds
        map.put("time", System.currentTimeMillis());
        result.setData(map);
        return result;
    }

    /**
     * 企业自查删除
     */
    @RequestMapping(value = "A017", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkDel(HttpServletRequest request, TCheck tCheck) {
        AppResult result = new AppResultImpl();
        tCheck.setDel(1);
        if (tCheckMapper.updateByPrimaryKeySelective(tCheck) != 1) {
            result.setStatus("1");
            result.setMessage("删除失败");
            return result;
        }
        return result;
    }

    /**
     * 录入装置
     */
    @RequestMapping(value = "A018", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkPartSave(HttpServletRequest request, TCheckPart tci) {
        AppResult result = new AppResultImpl();
        if (tCheckPartMapper.updateByPrimaryKeySelective(tci) != 1) {
            result.setStatus("1");
            result.setMessage("修改失败");
            return result;
        }
        return result;
    }

    /**
     * 录入检查部位
     */
    @RequestMapping(value = "A019", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkItemGet(HttpServletRequest request, Integer type, Integer checkId) {//zhangcl 2018.12.28
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tCheckPart", tCheckPartMapper.selectByCheckId(checkId));
        //map.put("tCheckItem", tCheckItemMapper.selectByAppCheckId(checkId));
        List<Map<String, Object>> iteml = tCheckItemMapper.selectByAppCheckId(checkId);
        
        if(type!=null&&type==9){
        	for(Map<String,Object> a: iteml){
            	log.error("checkNext:"+1);
            	Integer[] ids = new Integer[1];
            	ids[0] = (Integer) a.get("levelId");
            	log.error("ids:"+ids[0]);
            	log.error("a:"+a.toString());
            	List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
            	String dangertype="";
            	String factors="";
            	String measures="";
            	String level1="";
            	String level2="";
            	String level3="";
            	for(ACompanyManual aa: rets){
            		log.error("checkNext:"+2);
            		dangertype = aa.getType();
            		factors = aa.getFactors();
            		measures = aa.getMeasures();
            		level1 = aa.getLevel1();
            		level2 = aa.getLevel2();
            		level3 = aa.getLevel3();
            		log.error("type:"+dangertype);
            		break;
            	}
            	a.put("dangerType",dangertype);
            	a.put("factors",factors);
            	a.put("measures",measures);
            	a.put("level1",level1);
            	a.put("level2",level2);
            	a.put("level3",level3);
            }
        }
        map.put("tCheckItem", iteml);
        result.setData(map);
        return result;
    }

    /**
     * 录入检查项目列表
     */
//    @RequestMapping(value = "A020", method = RequestMethod.POST)
//    public @ResponseBody
//    AppResult checkItemList(HttpServletRequest request, TCheckItem tCheckItem) {
//        AppResult result = new AppResultImpl();
//        Map<String, Object> map = new HashMap<String, Object>();
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("partId", tCheckItem.getPartId());
//        m.put("levelId", tCheckItem.getLevelId());
//        map.put("tCheckItem", tCheckItemMapper.selectByAppList(m));
//        result.setData(map);
//        return result;
//    }
    @RequestMapping(value = "A020", method = RequestMethod.POST)//zhangcl 2018.12.29
    public @ResponseBody AppResult checkItemList(HttpServletRequest request, Integer type, TCheckItem tCheckItem) {
        AppResult result = new AppResultImpl();
        
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        map.put("userId", userId);
        //map.put("userId", 26);
        m.put("partId", tCheckItem.getPartId());
        m.put("levelId", tCheckItem.getLevelId());
        
        List<TCheckItem> iteml;
        if(type!=null&&type==9){
        	m.remove("levelId");//忽略levelId
        	iteml = tCheckItemMapper.selectByMap1(m);
        }
        else{
        	iteml = tCheckItemMapper.selectByAppList(m);
        }
        map.put("tCheckItem", iteml);
        
        result.setData(map);
        return result;
    }

    /**
     * 录入检查保存
     */
    @RequestMapping(value = "A021", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkItemList(HttpServletRequest request, TCheck tCheck) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        try {
            cgfService.checkNestSave(tCheck/* , partIds, statusp, suggest, time, */);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("更新失败");
            return result;
        }
        return result;
    }

    /**
     * 检查项目保存
     */
    @RequestMapping(value = "A022", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkItemSave(HttpServletRequest request, TCheckItem tCheckItem) {
        AppResult result = new AppResultImpl();
        if (tCheckItemMapper.updateByPrimaryKeySelective(tCheckItem) != 1) {
            result.setStatus("1");
            result.setMessage("更新失败");
            return result;
        }
        return result;
    }

    /**
     * 隐患列表
     */
    @RequestMapping(value = "A023", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkDangerList(HttpServletRequest request, TCheckItem tCheckItem) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tCheckItem", tCheckItemMapper.selectDangerByCheckId(tCheckItem.getCheckId(), null));
        result.setData(map);
        return result;
    }

    /**
     * 隐患详情
     */
    @RequestMapping(value = "A024", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkDangerDetail(HttpServletRequest request, TCheckItem tCheckItem) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tCheckItem", tCheckItemMapper.selectByPrimaryKey(tCheckItem.getId()));
        result.setData(map);
        return result;
    }

    /**
     * 治理意见
     */
//    @RequestMapping(value = "A025", method = RequestMethod.POST)
//    public @ResponseBody
//    AppResult dangerByCheckId(HttpServletRequest request, Integer checkId) {
//        AppResult result = new AppResultImpl();
//        Map<String, Object> map_r = new HashMap<String, Object>();
//        TCheck tCheck = tCheckMapper.selectByPrimaryKey(checkId);
//        // 获取所有治理意见
//        List<Map<String, Object>> list = tCheckItemMapper.selectDangerByCheckId(checkId, null);
//        if (list.size() == 0) {
//            return result;
//        }
//        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
//        for (Map<String, Object> x : list) {
//            Integer partId = (Integer) x.get("partId");
//            String partName = x.get("partName").toString();
//            boolean has = false;
//            for (Map<String, Object> m : list1) {
//                if (((Integer) m.get("partId")).compareTo(partId) == 0) {
//                    has = true;
//                    break;
//                }
//            }
//            if (has) {
//                continue;
//            }
//            Map<String, Object> a = new HashMap<String, Object>();
//            a.put("partId", partId);
//            a.put("partName", partName);
//            // 处理检查项目
//            List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
//            String level1 = x.get("level1").toString();
//            has = false;
//            for (Map<String, Object> l2 : list2) {
//                if (l2.get("name").toString().equals(level1)) {
//                    has = false;
//                    break;
//                }
//            }
//            if (!has) {
//                Map<String, Object> l2 = new HashMap<String, Object>();
//                l2.put("name", level1);
//                List<Map<String, Object>> l3 = new ArrayList<Map<String, Object>>();
//                for (Map<String, Object> y : list) {
//                    String l1 = y.get("level1").toString();
//                    if (l1.equals(level1)) {
//                        l3.add(y);
//                    }
//                }
//                l2.put("list", l3);
//                list2.add(l2);
//            }
//            a.put("list", list2);
//            list1.add(a);
//        }
//        map_r.put("cheker", tCheck.getCheker());
//        map_r.put("dapartContact", tCheck.getDapartContact());
//        map_r.put("list1", list1);
//        result.setData(map_r);
//        return result;
//    }
    
    @RequestMapping(value = "A025", method = RequestMethod.POST)//zhangcl 2018.12.30
    public @ResponseBody AppResult dangerByCheckId(HttpServletRequest request, Integer type, Integer checkId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map_r = new HashMap<String, Object>();
        TCheck tCheck = tCheckMapper.selectByPrimaryKey(checkId);
        // 获取所有治理意见
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerByCheckId(checkId, null);
        if (list.size() == 0) {
            return result;
        }
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> x : list) {
            Integer partId = (Integer) x.get("partId");
            String partName = x.get("partName").toString();
            boolean has = false;
            for (Map<String, Object> m : list1) {
                if (((Integer) m.get("partId")).compareTo(partId) == 0) {
                    has = true;
                    break;
                }
            }
            if (has) {
                continue;
            }
            Map<String, Object> a = new HashMap<String, Object>();
            a.put("partId", partId);
            a.put("partName", partName);
            // 处理检查项目
            List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
            String level1;
            if(type!=null&&type==9){
            	ADangerManual m = aDangerManualMapper.selectByPrimaryKey((Integer)x.get("levelId"));
            	level1 = m.getLevel1();
            }
            else{
            	level1 = x.get("level1").toString();
            }
            has = false;
            for (Map<String, Object> l2 : list2) {
                if (l2.get("name").toString().equals(level1)) {
                    has = false;
                    break;
                }
            }
            if (!has) {
                Map<String, Object> l2 = new HashMap<String, Object>();
                l2.put("name", level1);
                List<Map<String, Object>> l3 = new ArrayList<Map<String, Object>>();
                for (Map<String, Object> y : list) {
                    //String l1 = y.get("level1").toString();
                    String l1;
                    if(type!=null&&type==9){
                    	ADangerManual m = aDangerManualMapper.selectByPrimaryKey((Integer)x.get("levelId"));
                    	l1 = m.getLevel1();
                    	y.put("content", m.getMeasures());
                    }
                    else{
                    	l1 = y.get("level1").toString();
                    }
                    
                    if (l1.equals(level1)) {
                        l3.add(y);
                    }
                }
                l2.put("list", l3);
                list2.add(l2);
            }
            a.put("list", list2);
            list1.add(a);
        }
        map_r.put("cheker", tCheck.getCheker());
        map_r.put("dapartContact", tCheck.getDapartContact());
        map_r.put("list1", list1);
        result.setData(map_r);
        return result;
    }
    

    /**
     * 治理意见展示
     */
    @RequestMapping(value = "A026", method = RequestMethod.POST)
    public @ResponseBody
    AppResult dangerDisplay(HttpServletRequest request, Integer checkId) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        TRectification rectification = tRectificationMapper.selectByCheckId(checkId);
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(userId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("check", check);
        map.put("tCheckItem", tCheckItemMapper.selectDangerByCheckId(checkId, null));
        map.put("rectification", rectification);
        result.setData(map);
        return result;
    }

    /**
     * 治理意见保存
     */
    @RequestMapping(value = "B026", method = RequestMethod.POST)
    public @ResponseBody
    AppResult dangerSave(HttpServletRequest request, TRectification tRectification) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        tRectification.setCreateUser(userId);
        try {
            cgfService.rectificationSave(tRectification);
        } catch (Exception e) {
            result.setStatus("1");
            result.setMessage("系统异常");
        }
        return result;
    }

    /**
     * 职业病危害
     */
    @RequestMapping(value = "A027", method = RequestMethod.POST)
    public @ResponseBody
    AppResult zhiList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fen", fengMapper.selectFengList());
        map.put("hua", huaMapper.selectHuaList());
        // 物理
        List<HarmfulFactor> list_wu = new ArrayList<HarmfulFactor>();
        list_wu.add(new HarmfulFactor("噪声", ""));
        list_wu.add(new HarmfulFactor("高温", ""));
        list_wu.add(new HarmfulFactor("低气压", ""));
        list_wu.add(new HarmfulFactor("高企业", ""));
        list_wu.add(new HarmfulFactor("高原低氧", ""));
        list_wu.add(new HarmfulFactor("振动", ""));
        list_wu.add(new HarmfulFactor("激光", ""));
        list_wu.add(new HarmfulFactor("低温", ""));
        list_wu.add(new HarmfulFactor("微波", ""));
        list_wu.add(new HarmfulFactor("紫外线", ""));
        list_wu.add(new HarmfulFactor("红外线", ""));
        list_wu.add(new HarmfulFactor("工频电磁场", ""));
        list_wu.add(new HarmfulFactor("高频电磁场", ""));
        list_wu.add(new HarmfulFactor("超高频电磁场", ""));
        map.put("wu", list_wu);
        // 放射性
        List<HarmfulFactor> list_fang = new ArrayList<HarmfulFactor>();
        list_fang.add(new HarmfulFactor("密封放射源产生的电离辐射", "主要产生γ、中子等射线"));
        list_fang.add(new HarmfulFactor("非密封放射性物质", "可产生α、β、γ射线或中子"));
        list_fang.add(new HarmfulFactor("X射线装置（含CT机）产生的电离辐射", "X射线"));
        list_fang.add(new HarmfulFactor("加速器产生的电离辐射", "可产生电子射线、X射线、质子、重离子、中子以及感生放射性等"));
        list_fang.add(new HarmfulFactor("中子发生器产生的电离辐射", "主要是中子、γ射线等"));
        list_fang.add(new HarmfulFactor("氡及其短寿命子体", "限于矿工高氡暴露"));
        list_fang.add(new HarmfulFactor("铀及其化合物", ""));
        map.put("fang", list_fang);
        // 生物
        List<HarmfulFactor> list_sheng = new ArrayList<HarmfulFactor>();
        list_sheng.add(new HarmfulFactor("艾滋病病毒", "限于医疗卫生人员及人民警察"));
        list_sheng.add(new HarmfulFactor("布鲁氏菌", ""));
        list_sheng.add(new HarmfulFactor("伯氏疏螺旋体", ""));
        list_sheng.add(new HarmfulFactor("森林脑炎病毒", ""));
        list_sheng.add(new HarmfulFactor("炭疽芽孢杆菌", ""));
        map.put("sheng", list_sheng);
        // 其他
        List<HarmfulFactor> list_qi = new ArrayList<HarmfulFactor>();
        list_qi.add(new HarmfulFactor("金属烟", ""));
        list_qi.add(new HarmfulFactor("井下不良作业条件", "限于井下工人"));
        list_qi.add(new HarmfulFactor("刮研作业", "限于手工刮研作业人员"));
        map.put("qi", list_qi);
        result.setData(map);
        return result;
    }

    /**
     * 获取所有隐患
     */
    @RequestMapping(value = "A028", method = RequestMethod.POST)
    public @ResponseBody
    AppResult getYhByCheckId(HttpServletRequest request, Integer checkId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取所有治理意见
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerByCheckId(checkId, null);
        map.put("tCheckItem", list);
        result.setData(map);
        return result;
    }

    /**
     * 获取下载危化品目录
     */
    @RequestMapping(value = "A029", method = RequestMethod.POST)
    public @ResponseBody
    AppResult dangerdown(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url",
                "/common/download?filename=%E5%8D%B1%E9%99%A9%E5%8C%96%E5%AD%A6%E5%93%81%E7%9B%AE%E5%BD%95.pdf&fileurl=/smaq/upload/trouble/25.pdf");
        result.setData(map);
        return result;
    }

    /**
     * 获取危化品目录
     */
    @RequestMapping(value = "A030", method = RequestMethod.POST)
    public @ResponseBody
    AppResult dangerList(HttpServletRequest request, String dangerName, Integer page, Integer limit) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("page", (page - 1) * limit);
        m.put("limit", limit);
        m.put("dangerName", dangerName);
        map.put("list", dangerLibMapper.selectDangerList(m));
        result.setData(map);
        return result;
    }

    /**
     * 重大危险源页面
     */
    @RequestMapping(value = "A031", method = RequestMethod.GET)
    public String weiList(Model model, HttpServletRequest request) throws Exception {
        return "danger/wei-list2";
    }

    /**
     * 预警栏
     */
    @RequestMapping(value = "A032", method = RequestMethod.POST)
    public @ResponseBody
    AppResult yjl(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        
        CompanyListReqDTO dto = new CompanyListReqDTO();
        List<Integer> count = userService.selectCount(dto, userMapper.selectByPrimaryKey(userId));
        
        map.put("count", count);
        result.setData(map);
        return result;
    }

    /**
     * 各类许可证
     */
    @RequestMapping(value = "A033", method = RequestMethod.POST)
    public @ResponseBody
    AppResult xkList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        map.put("list", standardMapper.selectList(m));
        result.setData(map);
        return result;
    }

    /**
     * 企业自查表
     */
    @RequestMapping(value = "A034", method = RequestMethod.POST)
    public @ResponseBody
    AppResult modelList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("flag", 1);
        m.put("userId", userId);
        map.put("list", tModelMapper.selectByMap(m));
        result.setData(map);
        return result;
    }

    /**
     * 计划生成检查表
     */
    @RequestMapping(value = "A035", method = RequestMethod.POST)
    public @ResponseBody
    AppResult planSave(HttpServletRequest request, Integer type, Integer modelId) {
        AppResult result = new AppResultImpl();
        try {
            TModel model = tModelMapper.selectByPrimaryKey(modelId);
            TCheck check = new TCheck();
            check.setFlag(model.getFlag());
            check.setTitle(model.getTitle());
            check.setDepart(model.getPart());
            check.setModelId(modelId);
            check.setType(model.getType());
            check.setIndustryId(model.getIndustryId());
            check.setIndustryType(model.getIndustryType());
            check.setStatus(1);
            check.setDel(1);
            check.setCreateTime(new Date());
            Integer userId = getAppUserId(request);
            check.setUserId(userId);
            check.setCreateUser(userId);
            //cgfService.checkSave(check);
            
            log.error("A035 modelId: "+modelId+", type : "+type);//zhangcl 2018.12.24
            if(type!=null&&type==9)
            	cgfService.checkSave1(check);
            else
            	cgfService.checkSave(check);
            log.error("A035 : "+6);//zhangcl 2018.12.24
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("checkId", check.getId());
            result.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("更新失败");
        }
        return result;
    }

    /**
     * 重大生产安全事故
     */
    @RequestMapping(value = "A036", method = RequestMethod.POST)
    public @ResponseBody
    AppResult opinionDetail(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        result.setData("/html/danger.html");
        return result;
    }
    
    /**
     * 监控列表
     */
    @RequestMapping(value = "A043", method = RequestMethod.POST)
    public @ResponseBody
    AppResult monitorL(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(userId, m);
        result.setData(monitorMapper.selectByMap(m));
        return result;
    }

    /**
     * 应急预案列表
     */
    @RequestMapping(value = "A044", method = RequestMethod.POST)
    public @ResponseBody AppResult yuan(HttpServletRequest request,Integer type) {
        AppResult result = new AppResultImpl();
        Integer userId =null;
        if(null != type && type==5){
            ZzjgPersonnel zzjgPersonnel = (ZzjgPersonnel) appTokenData.getAppUser(request);
            userId = zzjgPersonnel.getUid();
        }else{
            Officials officials = (Officials) appTokenData.getAppUser(request);
            userId = officials.getUid();
        }
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(userId, m);
        result.setData(tContingencyPlanMapper.selectTable(m));
        return result;
    }

    /**
     * TODO  执法复查
     */
    @RequestMapping(value = "A045", method = RequestMethod.POST)
    public @ResponseBody AppResult fucha(HttpServletRequest request) {
        AppResult result = new AppResultImpl();

        Officials zzjg = (Officials) appTokenData.getAppUser(request);
        Integer userId = getAppUserId(request);
        if (null == zzjg) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(zzjg.getUid(), m);
        m.put("flag", 2);
        result.setData(tRectificationMapper.selectRectificationList(m));
        return result;
    }

    /**
     * 执法文档页面列表
     */
    @RequestMapping("A040")
    public @ResponseBody
    AppResult lawDocList(HttpServletRequest request, String companyName, Integer flag) {
        AppResult result = new AppResultImpl();
        try {
            Map<String, Object> m = new HashMap<String, Object>();
            Integer userId = getAppUserId(request);
            setUserId(userId, m);
            m.put("companyName", companyName);
            m.put("flag", flag);
            List<Map<String, Object>> list = lawDocMapper.selectList(m);
            m.clear();
            m.put("list", list);
            result.setData(m);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("系统异常");
        }
        return result;
    }

    /**
     * 执法文档页面保存
     */
    @RequestMapping("A041")
    public @ResponseBody
    AppResult lawDocSave(@RequestParam MultipartFile file, HttpServletRequest request, Integer cId, Integer flag) {
        AppResult result = new AppResultImpl();
        if (null != file) {
        } else {
            result.setStatus("1");
            result.setMessage("请选择文件。");
            return result;
        }
        LawDoc lawDoc = new LawDoc();
        lawDoc.setFlag(flag);
        
        Integer userId = getAppUserId(request);
        lawDoc.setUserId(null == cId ? userId : cId);
        lawDoc.setCreateTime(new Date());
        lawDoc.setCreateUserId(userId);
        try {
            // 文件保存目录路径
            String savePath = request.getServletContext().getRealPath("/") + "upload/txt/";
            // 文件保存目录URL
            String saveUrl = request.getContextPath() + "/upload/txt/";
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String oldname = file.getOriginalFilename();
            String fileExt = oldname.substring(oldname.lastIndexOf(".") + 1).toLowerCase();
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            file.transferTo(new File(savePath + newFileName));
            lawDoc.setName(file.getOriginalFilename());
            lawDoc.setDocName(file.getOriginalFilename());
            lawDoc.setDocUrl(saveUrl + newFileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setStatus("1");
            result.setMessage("文件上传异常，请重试");
        }
        try {
            lawDocMapper.insertSelective(lawDoc);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setStatus("1");
            result.setMessage("系统异常");
        }
        return result;
    }
    
    /**
     * 执法文档删除
     */
    @RequestMapping("A042")
    public @ResponseBody AppResult opinionDel(Integer id) {
        AppResult result = new AppResultImpl();
        lawDocMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 企业隐患汇总
     */
    @RequestMapping(value = "A039", method = RequestMethod.POST)
    public @ResponseBody
    AppResult dangerCollect(HttpServletRequest request, String companyName, Integer companyId) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("companyName", companyName);
        if (null != companyId) {
            m.put("userId", companyId);
        } else {
            setUserId(userId, m);
        }
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerCollectByCompany(m);
        Integer all = 0;// 总数
        Integer c1 = 0;// 自查
        Integer c2 = 0;// 执法
        Integer c3 = 0;// 委托
        Integer c4 = 0;// 检查
        for (Map<String, Object> l : list) {
            Integer c = Integer.valueOf(l.get("c").toString());// 数量
            Integer flag = Integer.valueOf(l.get("flag").toString());// 1 自查 2执法 3委托5检查
            if (flag == 1) {
                c1 += c;
            }
            if (flag == 2) {
                c2 += c;
            }
            if (flag == 3) {
                c3 += c;
            }
            if (flag == 4) {
                c4 += c;
            }
        }
        all = c1 + c2 + c3 + c4;
        m.clear();
        m.put("all", all);
        m.put("c1", c1);
        m.put("c2", c2);
        m.put("c3", c3);
        m.put("c4", c4);
        result.setData(m);
        return result;
    }

    /**
     * 判断用户的身份, 企业 政府
     * @param userId
     * @param m
     */
    void setUserId(Integer userId, Map<String, Object> m) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getUserType() == 4) {
            m.put("villageId", user.getId());
        }
        // 政府
        if (user.getUserType() == 5) {
            m.put("userId", user.getId());
        }
        if (user.getUserType() == 3) {
            m.put("townId", user.getId());
        }
        if (user.getUserType() == 6) {
            m.put("districtId", user.getId());
        }
        if (user.getUserType() == 10) {
            m.put("tradeId", user.getId());
        }
    }

    @InitBinder
    public void initData(WebDataBinder wdb) {
        wdb.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
