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
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: AppController
 * @Description: TODO(登陆信息)
 * @author FL
 * @date 2017年9月21日 下午3:56:10
 * @version V1.0
 */
@Controller
@RequestMapping(value = "api/village/xlb")
public class AppController_village_xlb extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(这个是可序列化变量)
     */
    private static final long serialVersionUID = 3074066448546610745L;
    @Autowired
    private CgfService cgfService;
    @Autowired
    private AppTokenData appTokenData;

    void setUserId(Integer userId, Map<String, Object> m) {
        User user = userMapper.selectByPrimaryKey(userId);
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
        if (user.getUserType() == 10) {
            m.put("tradeId", user.getId());
        }
    }

    /**
     * 企业列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "A001", method = RequestMethod.POST)
    public @ResponseBody AppResult modelList(HttpServletRequest request, String companyName) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        // 返回userId
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        setUserId(userId, m);

        m.put("companyName", companyName);
        User user = userMapper.selectByPrimaryKey(userId);
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
        for (int i = 0; i < list.size(); i++) {
            if (user.getUserType() == 3) {
                list.get(i).put("isKey", list.get(i).get("key1"));
            }
            if (user.getUserType() == 6) {
                list.get(i).put("isKey", list.get(i).get("key2"));
            }
            if (user.getUserType() == 9) {// 安泰没有重点企业
                list.get(i).put("isKey", 0);
            }
            if (user.getUserType() == 10) {
                list.get(i).put("isKey", list.get(i).get("tradeKey"));
            }
        }
        map.put("list", list);
        result.setData(map);
        return result;
    }

    /**
     * 企业分类统计
     */
    @RequestMapping(value = "A002", method = RequestMethod.POST)
    public @ResponseBody AppResult industryList(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        List<Library> lib = libraryMapper.selectLibraryList(1);
        List<Library> da = libraryMapper.selectLibraryList(2);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(userId, m);
        List<DynamicParameter<String, Object>> l = companyMapper.selectIndustry(m);
        List<DynamicParameter<String, Object>> d = companyMapper.selectDlevel(m);
        List<List<Object>> dataL = new ArrayList<List<Object>>();
        for (Library li : lib) {
            List<Object> lll = new ArrayList<Object>();
            lll.add(li.getName());
            lll.add(0);
            dataL.add(lll);
        }
        for (DynamicParameter<String, Object> dy : l) {
            String industry = dy.getString("industry");
            Integer c = dy.getInteger("c");
            if (StringUtils.isBlank(industry)) {
                continue;
            }
            for (int i = 0; i < lib.size(); i++) {
                if (industry.equals(lib.get(i).getName())) {
                    List<Object> x = dataL.get(i);
                    x.set(1, c);
                    x.set(0, (String) x.get(0));
                    break;
                }
            }
        }
        List<List<Object>> dataD = new ArrayList<List<Object>>();
        for (Library lb : da) {
            List<Object> ddd = new ArrayList<Object>();
            ddd.add(lb.getName());
            ddd.add(0);
            dataD.add(ddd);
        }
        for (DynamicParameter<String, Object> dp : d) {
            String dlevel = dp.getString("dlevel");
            Integer amount = dp.getInteger("amount");
            if (StringUtils.isBlank(dlevel)) {
                continue;
            }
            for (int i = 0; i < da.size(); i++) {
                if (dlevel.equals(da.get(i).getName())) {
                    List<Object> obj = dataD.get(i);
                    obj.set(1, amount);
                    obj.set(0, (String) obj.get(0));
                    break;
                }
            }
        }
        map.put("industry", dataL);
        map.put("danger", dataD);
        result.setData(map);
        return result;
    }

    /**
     * 特种作业人员
     */
    @RequestMapping(value = "A003", method = RequestMethod.POST)
    public @ResponseBody AppResult spersonList(HttpServletRequest request, String spFlag, Integer userId, Integer type) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = null;
        if(type==5){
             ZzjgPersonnel zzjgPersonnel = (ZzjgPersonnel) appTokenData.getAppUser(request);
             _userId = zzjgPersonnel.getUid();
        }else{
            Officials officials = (Officials) appTokenData.getAppUser(request);
            _userId = officials.getUid();
        }

        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        m.put("spFlag", spFlag);
        map.put("sperson", spersonMapper.selectSpersonList(m));
        result.setData(map);
        return result;
    }

    /**
     * 安全评价档案
     */
    @RequestMapping(value = "A004", method = RequestMethod.POST)
    public @ResponseBody AppResult evaluation(HttpServletRequest request, String flag, Integer userId,Integer type) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = null;
        if(type==5){
            ZzjgPersonnel zzjgPersonnel = (ZzjgPersonnel) appTokenData.getAppUser(request);
            _userId = zzjgPersonnel.getUid();
        }else{
            Officials officials = (Officials) appTokenData.getAppUser(request);
            _userId = officials.getUid();
        }
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        m.put("flag", flag);
        map.put("evaluation", evaluationMapper.selectEvaluationList(m));
        result.setData(map);
        return result;
    }

    /**
     * 基本信息
     */
    @RequestMapping(value = "A005", method = RequestMethod.POST)
    public @ResponseBody AppResult basicInformation(HttpServletRequest request, Integer userId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
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
     * 劳动防护用品
     */
    @RequestMapping(value = "A006", method = RequestMethod.POST)
    public @ResponseBody AppResult pequipmentList(HttpServletRequest request, String flag, Integer userId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        map.put("pequipment", pequipmentMapper.selectByMap(m));
        result.setData(map);
        return result;
    }

    /**
     * 特种设备清单
     *
     */
    @RequestMapping(value = "A007", method = RequestMethod.POST)
    public @ResponseBody AppResult sequipmentList(HttpServletRequest request, Integer userId,Integer type) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = null;
        if(type==5){
            ZzjgPersonnel zzjgPersonnel = (ZzjgPersonnel) appTokenData.getAppUser(request);
            _userId = zzjgPersonnel.getUid();
        }else{
            Officials officials = (Officials) appTokenData.getAppUser(request);
            _userId = officials.getUid();
        }
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        map.put("sequipment", sequipmentMapper.selectSequipmentList(m));
        result.setData(map);
        return result;
    }

    /**
     * 消防应急设施
     */
    @RequestMapping(value = "A008", method = RequestMethod.POST)
    public @ResponseBody AppResult facList(HttpServletRequest request, Integer userId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        map.put("facilities", facilitiesMapper.selectFacilitiesList(m));
        result.setData(map);
        return result;
    }

    /**
     * TODO    预警栏
     */
    @RequestMapping(value = "A009", method = RequestMethod.POST)
    public @ResponseBody AppResult yjl(HttpServletRequest request, Integer userId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        User user = userMapper.selectByPrimaryKey(_userId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        
        CompanyListReqDTO dto = new CompanyListReqDTO();
        List<Integer> count = userService.selectCount(dto, user);
        
        map.put("count", count);
        result.setData(map);
        return result;
    }
    
    /**
     * 职业卫生检测
     */
    @RequestMapping(value = "A010", method = RequestMethod.POST)
    public @ResponseBody AppResult detList(HttpServletRequest request, Integer userId,Integer type) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = null;
        if(type==5){
            ZzjgPersonnel zzjgPersonnel = (ZzjgPersonnel) appTokenData.getAppUser(request);
            _userId = zzjgPersonnel.getUid();
        }else{
            Officials officials = (Officials) appTokenData.getAppUser(request);
            _userId = officials.getUid();
        }
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        map.put("detection", detectionMapper.selectDetectionList(m));
        result.setData(map);
        return result;
    }

    /**
     * 职业健康检查
     */
    @RequestMapping(value = "A011", method = RequestMethod.POST)
    public @ResponseBody AppResult hetList(HttpServletRequest request, Integer userId,Integer type) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = null;
        if(type==5){
            ZzjgPersonnel zzjgPersonnel = (ZzjgPersonnel) appTokenData.getAppUser(request);
            _userId = zzjgPersonnel.getUid();
        }else{
            Officials officials = (Officials) appTokenData.getAppUser(request);
            _userId = officials.getUid();
        }
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        map.put("examination", examinationMapper.selectExaminationList(m));
        result.setData(map);
        return result;
    }

    /**
     * 主要原辅材料
     */
    @RequestMapping(value = "A012", method = RequestMethod.POST)
    public @ResponseBody AppResult mainMaterialList(HttpServletRequest request, Integer userId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        map.put("material", materialMapper.selectByUserId(m));
        result.setData(map);
        return result;
    }

    /**
     * 主要产品
     */
    @RequestMapping(value = "A013", method = RequestMethod.POST)
    public @ResponseBody AppResult mainProductList(HttpServletRequest request, Integer userId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        map.put("product", productMapper.selectByUserId(m));
        result.setData(map);
        return result;
    }

    /**
     * 主要设备清单
     */
    @RequestMapping(value = "A014", method = RequestMethod.POST)
    public @ResponseBody AppResult mequipmentList(HttpServletRequest request, Integer userId) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        map.put("mequipment", mequipmentMapper.selectByUserId(m));
        result.setData(map);
        return result;
    }

    /**
     * 隐患数据库
     */
    @RequestMapping(value = "A015", method = RequestMethod.POST)
    public @ResponseBody AppResult modelList(HttpServletRequest request, Integer d, Integer status, Integer userId,
            String flag, String companyName,Integer type) {
        AppResult result = new AppResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = null;
        if(type==5){
            ZzjgPersonnel zzjgPersonnel = (ZzjgPersonnel) appTokenData.getAppUser(request);
            _userId = zzjgPersonnel.getUid();
        }else{
            Officials officials = (Officials) appTokenData.getAppUser(request);
            _userId = officials.getUid();
        }
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        m.put("flag", flag);
        m.put("status", status);
        m.put("d", d);
        m.put("companyName", companyName);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerIndexList(m);
        result.setData(list);
        return result;
    }

    /**
     * 各类许可证
     */
    @RequestMapping(value = "A016", method = RequestMethod.POST)
    public @ResponseBody AppResult zhengList(HttpServletRequest request, Integer userId,Integer type) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = null;
        if(type==5){
            ZzjgPersonnel zzjgPersonnel = (ZzjgPersonnel) appTokenData.getAppUser(request);
            _userId = zzjgPersonnel.getUid();
        }else{
            Officials officials = (Officials) appTokenData.getAppUser(request);
            _userId = officials.getUid();
        }
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        m.put("userId", userId);
        setUserId(_userId, m);
        map.put("standard", standardMapper.selectList(m));
        result.setData(map);
        return result;
    }

    /**
     * 首页角标数量
     */
    @RequestMapping(value = "A017", method = RequestMethod.POST)
    public @ResponseBody AppResult countModel(HttpServletRequest request, String flag) {
        AppResult result = new AppResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        setUserId(_userId, m);
        m.put("flag", flag);
        m.put("status", 2);
        result.setData(tCheckItemMapper.selectDangerIndexListCount(m));
        return result;
    }

    /**
     * 行政执法列表
     */
    @RequestMapping(value = "A018", method = RequestMethod.POST)
    public @ResponseBody AppResult opinionShowList(HttpServletRequest request, String flag, String companyName) {
        AppResult result = new AppResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        setUserId(_userId, m);
        m.put("flag", flag);
        m.put("companyName", companyName);
        List<DynamicParameter<String, Object>> list = tCheckDocumentMapper.selectTable(m);
        result.setData(list);
        return result;
    }

    /**
     * 行政执法详情
     */
    @RequestMapping(value = "A019", method = RequestMethod.POST)
    public @ResponseBody AppResult opinionDetailList(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        TCheckDocument document = tCheckDocumentMapper.selectByPrimaryKey(id);
        result.setData(document);
        return result;
    }

    /**
     * 隐患汇总
     */
    @RequestMapping(value = "A020", method = RequestMethod.POST)
    public @ResponseBody AppResult A020(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(_userId, m);
        Integer[] c = new Integer[20];
        for (int i = 0; i < c.length; i++) {
            c[i] = 0;
        }
        List<Map<String, Object>> lf = tCheckItemMapper.selectCount2(m);
        for (Map<String, Object> s : lf) {
            Integer f = Integer.valueOf(s.get("flag").toString());
            Integer status = Integer.valueOf(s.get("status").toString());
            Integer d = Integer.valueOf(s.get("d").toString());
            Integer cx = Integer.valueOf(s.get("c").toString());
            c[f - 1] = c[f - 1] + cx;// 自查隐患数
            if (status == 2) {
                c[5] = c[5] + cx;
            } else {
                c[6] = c[6] + cx;
            }
            if (d == 1) {
                c[19] = c[19] + d;// 重大
            }
        }
        c[4] = c[5] + c[6];// 全部隐患
        m.clear();
        setUserId(_userId, m);
        List<Map<String, Object>> lll = tCheckItemMapper.selectDangerCollectGroup(m);
        for (Map<String, Object> l : lll) {
            Object x = l.get("industry_id");
            Integer cx = Integer.valueOf(l.get("c").toString());
            if (null == x) {
                c[17] = cx;
                continue;
            }
            Integer indu = Integer.valueOf(x.toString());
            if (indu == 76) {
                c[7] += cx;
            } else if (indu == 77) {
                c[8] += cx;
            } else if (indu == 78) {
                c[9] += cx;
            } else if (indu == 79) {
                c[10] += cx;
            } else if (indu == 80) {
                c[11] += cx;
            } else if (indu == 81) {
                c[12] += cx;
            } else if (indu == 82) {
                c[13] += cx;
            } else if (indu == 83) {
                c[14] += cx;
            } else if (indu == 84) {
                c[15] += cx;
            } else if (indu == 96) {
                c[16] += cx;
            } else if (indu == 102) {
                c[17] += cx;
            } else {
                c[18] += cx;
            }
        }
        result.setData(c);
        return result;
    }

    /**
     * 排查治理记录
     */
    @RequestMapping(value = "A021", method = RequestMethod.POST)
    public @ResponseBody AppResult checkList(HttpServletRequest request, String companyName, Integer status,
            Integer flag) {
        AppResult result = new AppResultImpl();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);
        m.put("companyName", companyName);
        m.put("status", status);
        setUserId(_userId, m);
        clearVillageTown(m);
        List<Map<String, Object>> list = tCheckMapper.selectList(m);
        result.setData(list);
        return result;
    }

    /**
     * 排查治理记录删除
     */
    @RequestMapping(value = "A022", method = RequestMethod.POST)
    public @ResponseBody AppResult checkListDel(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        TCheck t = new TCheck();
        t.setId(id);
        t.setDel(1);
        tCheckMapper.updateByPrimaryKeySelective(t);
        return result;
    }

    /**
     * 排查治理记录添加
     */
    @RequestMapping(value = "A023", method = RequestMethod.POST)
    public @ResponseBody AppResult checkListAdd(HttpServletRequest request, TCheck tCheck, String items) {
        AppResult result = new AppResultImpl();
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        try {
            cgfService.checkListAdd(_userId, tCheck, items, result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("添加失败！");
            return result;
        }
        return result;
    }
    
    /**
     * 整改指令书
     */
    @RequestMapping(value = "A024")
    public String checkDocument(Integer checkId, Integer flag, Model model) throws Exception {
        if (null == flag) {
            flag = 8;// 现场检查记录
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("checkId", checkId);
        m.put("flag", flag);
        TCheckDocument doc = tCheckDocumentMapper.selectByCheckId(m);
        TCheck check = tCheckMapper.selectByPrimaryKey(checkId);
        if (null == doc) {// 第一次
            TRectification tr = tRectificationMapper.selectByCheckId(checkId);
            model.addAttribute("check", check);
            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
            model.addAttribute("rectification", tr);
            model.addAttribute("company", companyMapper.selectByPrimaryKey(check.getUserId()));
        } else {
            model.addAttribute("document", doc);
            return "wechat/opinion-detail";
        }
        model.addAttribute("userId", check.getUserId());
        model.addAttribute("timenow", new Date());
        model.addAttribute("checkId", checkId);
        return "wechat/opinion-add" + flag + check.getFlag();
    }
    
    /**
     * 行政文书保存
     */
    @RequestMapping("danger/opinion-save")
    public @ResponseBody
    AppResult opinionSave(Model model,String access_token, Integer userId, HttpServletRequest request, TCheckDocument tCheckDocument)
            throws Exception {
        AppResult result = new AppResultImpl();
        AppToken at = appTokenMapper.selectByPrimaryKey(access_token);
        tCheckDocument.setUserId(userId);
        tCheckDocument.setCreateUser(at.getUserId());
        tCheckDocument.setDel(0);
        tCheckDocument.setCreateTime(new Date());
        tCheckDocumentMapper.insertSelective(tCheckDocument);
        result.setStatus("0");
        result.setData(tCheckDocument);
        return result;
    }
    
    /**
     * @param
     */
    @RequestMapping(value = "check-document-save8")
    public @ResponseBody
    Result checkDocumentSave8(String access_token, Integer checkId, Integer userId, String copy, Integer flag2, String itms,
            HttpServletRequest request) {
        Result result = new ResultImpl();
        AppToken at = appTokenMapper.selectByPrimaryKey(access_token);
        Date d = new Date();
        if (null == checkId) {// 直接添加现场记录的 自动补充检查表结构
            TCheck check = new TCheck();
            TCheckPart part = new TCheckPart();
            List<TCheckItem> itemL = new ArrayList<TCheckItem>();
            String[] iL = itms.split("!@#");
            for (String x : iL) {
                itemL.add(new TCheckItem(null, null, null, "现场管理", null, null, null, 2, x, null, null, null, null,
                        null, null, null,null,null));
            }
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
            check.setCreateUser(at.getUserId());
            check.setType(1);
            check.setCreateTime(d);
            check.setStatus(2);
            check.setDel(0);
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
        doc.setCreateUser(at.getUserId());
        doc.setUserId(userId);
        doc.setDel(0);
        tCheckDocumentMapper.insertSelective(doc);
        result.setMap("checkId", checkId);
        return result;
    }
    
    /**
     * 行政文书详情
     */
    @RequestMapping("danger/opinion-detail")
    public String opinionDetail(Model model, HttpServletRequest request,String access_token, Integer id) throws Exception {
        AppToken at = appTokenMapper.selectByPrimaryKey(access_token);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", at.getUserId());
        model.addAttribute("document", tCheckDocumentMapper.selectByPrimaryKey(id));
        return "wechat/opinion-detail2";
    }

    @InitBinder
    public void initData(WebDataBinder wdb) {
        wdb.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    /**
     * 自查项目
     */
    @RequestMapping(value = "t-industry/{id}")
    public String tIndustry(@PathVariable Integer id, Model model) throws Exception {
        List<TLevel> lL = tLevelMapper.selectByIndustry(id);// 检查级别
        List<TItem> iL = tItemMapper.selectByIndustry(id);// 检查项目
        model.addAttribute("levelL", gson.toJson(lL));
        model.addAttribute("itemL", gson.toJson(iL));
        if (StringUtils.isEmpty(lL.get(0).getLevel3())) {// 只有两级目录
        }
        return "back/danger/danger-list-m";
    }
    
    /**
     * 隐患列表
     */
    @RequestMapping(value = "A025")
    public String appDangerList(Model model, HttpServletRequest request, Integer checkId) throws Exception {
        model.addAttribute("list", tCheckItemMapper.selectDangerByCheckId(checkId, null));
        return "wechat/danger-list";
    }
    
    /**
     * 企业添加保存
     */
    @RequestMapping(value = "company-save")
    public @ResponseBody Result companySave(User user, Integer duserId, HttpServletRequest request, Integer villageId)
            throws Exception {
        Result result = new ResultImpl();
        User xx = userMapper.selectUserByUserName(user.getUserName());
        User d_user = userMapper.selectByPrimaryKey(duserId);
        if (null != xx) {
            result.setStatus("1");
            result.setMap("message", "该企业已存在");
            return result;
        }
        userService.registar(user, result, d_user, villageId);
        return result;
    }
    
    /**
     * 添加企业
     */
    @RequestMapping(value = "A026")
    public String companyAdd(Model model, HttpServletRequest request, String access_token) throws Exception {
        AppToken at = appTokenMapper.selectByPrimaryKey(access_token);
        User user = userMapper.selectByPrimaryKey(at.getUserId());
        model.addAttribute("user", user);
        List<DynamicParameter<String, Object>> list = districtMapper.selectDistrict();
        model.addAttribute("list", list);
        return "wechat/company-add";
    }
}
