/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spring.web.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.invoke.empty.Empty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.cgf.CheckSaveReqDTO;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.util.ConstantsUtil;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.OutPrintUtil;

/**
 * @Title: VillageController
 * @Description: TODO(village:  /'vɪlɪdʒ/ 村庄)
 * @author CGF
 * @date 2017年7月21日 下午12:42:42
 * @version V1.0
 */
@Controller
@RequestMapping("/village")
public class VillageController extends BaseController {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = 3L;
    @Autowired
    private UserService userService;
    @Autowired
    private CgfService cgfService;


    /**
     * 政府登录
     */
    @RequestMapping(value = "")
    public String index(HttpServletRequest request) throws Exception {
        String server = request.getServerName();
        if (server.startsWith("smaq.safety365.com.cn")) {
            return "redirect:/fore/login-smaq";
        }
        return "village/blogin2";
    }

    /**
     * 政府首页
     */
    @RequestMapping(value = "main")
    public String main(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("name_", villageMapper.selectByPrimaryKey(user.getId()).getName());
        if(user.getUserName().equals("港口")) {//港口特殊账号
            return "gang/main";
        }
        model.addAttribute("loginUserId", user.getId());
        return "village/main";
    }

    /**
     * 政府首页-中间
     */
    @RequestMapping(value = "welcome")
    public String welcome(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        
        String userIds = (String)m.get("userIds");
        
        CompanyListReqDTO dto = new CompanyListReqDTO();
        dto.setUserIds(userIds);
        List<Integer> count = userService.selectCount(dto, user);

        log.error("countVillage："+count.toString());
        
        model.addAttribute("number", "-1".equals(userIds) ? 0 : userIds.split(",").length);//企业数量
        
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        
        model.addAttribute("count", count);
        model.addAttribute("mc", monitorMapper.selectCount(m));
        if(user.getUserType() == 10){
            model.addAttribute("h", tradeMapper.selectByPrimaryKey(user.getId()));
        } else {
            model.addAttribute("v", villageMapper.selectByPrimaryKey(user.getId()));
        }
        model.addAttribute("loginUserId", user.getId());
        
        //add wz 2019-02-22
        HttpSession session = request.getSession();
        User moveBeforeUser =  (User)session.getAttribute("moveBefore");
        if(moveBeforeUser != null){
            model.addAttribute("moveD",1);
            model.addAttribute("nameBefore", townMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName());
        }       
        
        return "village/welcome";
    }
    
    /**
     * 企业行业汇总
     */
    @RequestMapping("industry2")
    public @ResponseBody
    Result industry2(HttpServletRequest request, Integer villageId, Integer townId, Integer districtId) {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("districtId", districtId);
        m.put("townId", townId);
        m.put("villageId", villageId);
        setUserId(user, m);
        result.setMap("list", companyMapper.selectIndustry2(m));
        result.setMap("list5", companyMapper.selectIndustry2AndScore(m));
        
        //危险等级汇总
        List<Library> da = libraryMapper.selectLibraryList(2);
        List<DynamicParameter<String, Object>> d = companyMapper.selectDlevel(m);
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
        for (List<Object> dL : dataD) {
            Integer x = (Integer) dL.get(1);
            if (x > 0) {
                dL.set(0, (String) dL.get(0) + "(" + x + ")");
            }
        }
        
        result.setMap("dataD", dataD);
        
        //监管行业汇总
        List<Library> lib = libraryMapper.selectLibraryList(1);
        List<DynamicParameter<String, Object>> l = companyMapper.selectIndustry(m);
        List<DynamicParameter<String, Object>> l_scale = companyMapper.selectIndustryByScale(m);
        List<List<Object>> dataL = new ArrayList<List<Object>>();
        for (Library li : lib) {
            List<Object> lll = new ArrayList<Object>();
            lll.add(li.getName());
            lll.add(0);
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
                    
                    for (DynamicParameter<String, Object> d2 : l_scale) {
                        String i2 = d2.getString("industry");
                        Integer c2 = d2.getInteger("c");
                        if (industry.equals(i2)) {
                            x.set(2, c2);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        for (List<Object> dL : dataL) {
            Integer x = (Integer) dL.get(1);
            Integer x2 = (Integer) dL.get(2);
            if (x > 0) {
                dL.set(0, (String) dL.get(0) + "(" + x + ",规上企业" + x2 + "家)");
            }
        }
        result.setMap("dataL", dataL);
        return result;
    }

    /**
     * 企业列表
     */
    @RequestMapping(value = "company/company-list")
    public String companyList(Model model, HttpServletRequest request, CompanyListReqDTO dto, Integer totalzc, Integer totalwyx) throws Exception {
        User user = getLoginUser(request);
        //log.error("zhangcl 2018.11.8 companyList start1 userid:"+user.getId());
        cgfService.selectCompanyWithPage(dto, user, model);
        //log.error("zhangcl 2018.11.8 companyList end1:"+ System.currentTimeMillis());    
        if(user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }    
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("sk", request.getParameter("sk"));
        

//        CompanyListReqDTO dto2 = new CompanyListReqDTO();
//        dto2.setDistrictId(dto.getDistrictId());
//        dto2.setTownId(dto.getTownId());
//        dto2.setTradeId(dto.getTradeId());
//        dto2.setVillageId(dto.getVillageId());
//        dto2.setIsFreeze("0");
//        //log.error("zhangcl 2018.11.8 companyList end2:"+ System.currentTimeMillis());
//        model.addAttribute("totalwyx", companyMapper.selectIdsByCompany_view_count(dto2));
//        //model.addAttribute("totalwyx", companyMapper.selectIdsByCompany_view_count_1(dto2));//已优化 zhangcl 2018.11.8
//        dto2.setIsFreeze("1");
//        //log.error("zhangcl 2018.11.8 companyList end3:"+ System.currentTimeMillis());
//        model.addAttribute("totalzc", companyMapper.selectIdsByCompany_view_count(dto2));
//        //model.addAttribute("totalzc", companyMapper.selectIdsByCompany_view_count_1(dto2));//已优化 zhangcl 2018.11.8
//        //log.error("zhangcl 2018.11.8 companyList end4:"+ System.currentTimeMillis());

        /*if(null == totalzc) {
            CompanyListReqDTO dto2 = new CompanyListReqDTO();
            if("-1".equals(dto.getUserIds())) {
                totalwyx = 0;
                totalzc = 0;
            } else {
                dto2.setUserIds(dto.getUserIds());
                dto2.setDistrictId(dto.getDistrictId());
                dto2.setTownId(dto.getTownId());
                dto2.setTradeId(dto.getTradeId());
                dto2.setVillageId(dto.getVillageId());
                dto2.setIsFreeze("0");
                totalwyx = companyMapper.selectIdsByCompany_view_count(dto2);
                dto2.setIsFreeze("1");
                totalzc = companyMapper.selectIdsByCompany_view_count(dto2);
            }
        }*/
        
//        CompanyListReqDTO dto2 = new CompanyListReqDTO();
//        dto2.setDistrictId(dto.getDistrictId());
//        dto2.setTownId(dto.getTownId());
//        dto2.setTradeId(dto.getTradeId());
//        dto2.setVillageId(dto.getVillageId());
//        dto2.setIsFreeze("0");
//        model.addAttribute("totalwyx", companyMapper.selectIdsByCompany_view_count(dto2));
//        dto2.setIsFreeze("1");
//        model.addAttribute("totalzc", companyMapper.selectIdsByCompany_view_count(dto2));
        
        model.addAttribute("totalzc", totalzc);
        model.addAttribute("totalwyx", totalwyx);
        model.addAttribute("dto", dto);
        model.addAttribute("title", "全部企业");
        if (StringUtils.isNotBlank(dto.getDoubleDanger())) {
            model.addAttribute("title", "高危作业");
        }
        if (StringUtils.isNotBlank(dto.getCisDanger())) {
            model.addAttribute("title", "重大危险源企业");
        }
        //log.error("zhangcl 2018.11.8 companyList end5:"+ System.currentTimeMillis());
        return "village/company/company-list";
    }
    
    /**
     * 企业列表 正常与未运行企业数量统计
     */
    @RequestMapping(value = "company-list-tj")
    public @ResponseBody Result companylisttj(HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        CompanyListReqDTO dto2 = new CompanyListReqDTO();
        setUserId2(user, dto2);
        dto2.setIsFreeze("0");
        result.setMap("totalwyx", companyMapper.selectIdsByCompany_view_count(dto2));
        dto2.setIsFreeze("1");
        result.setMap("totalzc", companyMapper.selectIdsByCompany_view_count(dto2));
        return result;
    }

    /**
     * 判断user是什么用户
     * @param user
     * @param dto
     */
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
     * to企业导入页面
     */
    @RequestMapping(value = "company/company-leadin")
    public String toLeadin(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("list", importRecordMapper.selectRecordList());
        return "village/company/company-leadin";
    }

    /**
     * to企业添加页面
     */
    @RequestMapping(value = "company/company-add")
    public String toAddCompany(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        if(user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            return "town/company/company-add";
        }
        
        if(user.getUserType().intValue() == 6) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
            return "area/company/company-add";
        }
        return "village/company/company-add";
    }
    
    /**
     * to企业行业添加页面
     */
    @RequestMapping(value = "company/company-add-trade")
    public String toAddCompanyTrade(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Integer userId = user.getId();
        
        //获取选中企业
        if (tradeCompanyMapper.selectByUserId(userId).size() == 0) {
            model.addAttribute("tradeCompany", "[]");
        } else {
            model.addAttribute("tradeCompany", JSONArray.toJSONString(tradeCompanyMapper.selectByUserId(userId)));
        }
        return "village/company/company-add-trade";
    }

    /**
     * to企业编辑页面
     */
    @RequestMapping(value = "company-edit")
    public String toAddCompany(Model model, Integer userId) {
        model.addAttribute("c", companyMapper.selectByPrimaryKey(userId));
        model.addAttribute("u", userMapper.selectByPrimaryKey(userId));
        model.addAttribute("list1", libraryMapper.selectLibraryList(1));
        model.addAttribute("list2", libraryMapper.selectLibraryList(2));
        model.addAttribute("ind3L", tIndustryMapper.selectByType(3));// 高危检查类别
        return "village/company/information-edit";
    }

    /**
     * 企业添加保存
     */
    @RequestMapping(value = "company/company-save")
    public @ResponseBody
    Result companySave(User user, HttpServletRequest request, Integer villageId) throws Exception {
        Result result = new ResultImpl();
        User xx = userMapper.selectUserByUserName(user.getUserName());
        if (null != xx) {
            result.setStatus("1");
            result.setMap("message", "该企业已存在");
            return result;
        }
        userService.registar(user, result, null, null == villageId ? getLoginUser(request).getId() : villageId);
        return result;
    }

    /**
     * 冻结 解冻
     */
    @RequestMapping(value = "user-freeze")
    public @ResponseBody
    Result userFreeze(User user, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        userMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    /**
     * 是否重点企业
     */
    @RequestMapping(value = "user-isKey")
    public @ResponseBody
    Result userisKey(Company company, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        companyMapper.updateByPrimaryKeySelective(company);
        return result;
    }
    
    /**
     * 是否行业重点企业
     */
    @RequestMapping(value = "user-tradeKey")
    public @ResponseBody
    Result userTradeKey(TradeCompany tradeCompany, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        tradeCompanyMapper.updateByPrimaryKeySelective(tradeCompany);
        return result;
    }

    /**
     * 企业详细资料
     */
    @RequestMapping(value = "company/company-show")
    public String recomany(Model model, @RequestParam("userId") int userId) throws Exception {
        Company c = companyMapper.selectByPrimaryKey(userId);
        if (null != c.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(c.getRegionId()));
        }
        model.addAttribute("c", c);
        Regulation r = regulationGet(userId);
        model.addAttribute("r", r);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", userId);
        model.addAttribute("peq", pequipmentMapper.selectByMap(m));
        model.addAttribute("melist", mequipmentMapper.selectByUserId(m));
        model.addAttribute("malist", materialMapper.selectByUserId(m));
        model.addAttribute("prlist", productMapper.selectByUserId(m));
        List<DynamicParameter<String, Object>> spelist = spersonMapper.selectSpersonList(m);
        model.addAttribute("spelist", spelist);
        model.addAttribute("seqlist", sequipmentMapper.selectSequipmentList(m));
        List<DynamicParameter<String, Object>> pjlist = evaluationMapper.selectEvaluationList(m);
        model.addAttribute("pjlist", pjlist);
        model.addAttribute("jclist", detectionMapper.selectDetectionList(m));
        List<DynamicParameter<String, Object>> exalist = examinationMapper.selectExaminationList(m);
        model.addAttribute("exalist", exalist);
        List<Map<String, Object>> standard = standardMapper.selectList(m);
        model.addAttribute("standard", standard);
        List<Map<String, Object>> danger1 = tCheckItemMapper.selectDangerIndexList(m);
        model.addAttribute("danger1", danger1);
        List<Map<String, Object>> productionProcessDiagram = productionProcessDiagramMapper.selectList(m);
        model.addAttribute("productionProcessDiagram", productionProcessDiagram);
        m.put("type", "1");
        // model.addAttribute("trolist", troubleMapper.selectByMap(m));
        // Map<String, Object> m6 = new HashMap<String, Object>();
        // m6.put("userId", userId);
        List<DynamicParameter<String, Object>> facilities = facilitiesMapper.selectFacilitiesList(m);
        model.addAttribute("faclist", facilities);
        m.clear();
        m.put("isType", 1);
        m.put("userId", userId);
        model.addAttribute("glzd_list", table2mapper.selectTable(m));// 管理制度
        m.put("isType", 0);
        model.addAttribute("zrz_list", table2mapper.selectTable(m));// 安全生产责任制
        return "village/company/company-show";
    }

    /**
     * 企业导入
     */
    @RequestMapping(value = "company/company-import", method = RequestMethod.POST)
    public void companyLeadin(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Result result = exportService.companyImport(file, getLoginUser(request).getId(), request);
        OutPrintUtil.OutPrint(response, result);
    }

    /**
     * 职业卫生检测
     */
    @RequestMapping(value = "warning/detection-list")
    public String detectionList(Model model, HttpServletRequest request, Integer isTime, String companyName,
            String deType) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("deType", deType);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", detectionMapper.selectDetectionList(m));
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("deType", deType);
        return "village/warning/detection-list";
    }

    /**
     * 职业健康体检
     */
    @RequestMapping(value = "warning/examination-list")
    public String examinationList(Model model, HttpServletRequest request, String companyName, Integer isTime)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("list", examinationMapper.selectExaminationList(m));
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        return "village/warning/examination-list";
    }

    /**
     * 安全评价档案页面
     */
    @RequestMapping("evaluate/evaluation-list")
    public String evatList(Model model, HttpServletRequest request, String reportName, String evaType, Integer isTime,
            String companyName, String flag) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("flag", flag);
        m.put("reportName", reportName);
        m.put("companyName", companyName);
        m.put("evaType", evaType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        List<DynamicParameter<String, Object>> list = evaluationMapper.selectEvaluationList(m);
        model.addAttribute("list", list);
        model.addAttribute("reportName", reportName);
        model.addAttribute("evaType", evaType);
        model.addAttribute("flag", flag);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        return "village/warning/evaluation-list";
    }

    /**
     * 消防应急设备列表
     */
    @RequestMapping(value = "warning/facilities-list")
    public String facilitiesList(HttpServletRequest request, Model model, String companyName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        model.addAttribute("list", facilitiesMapper.selectFacilitiesList(m));
        model.addAttribute("companyName", companyName);
        return "village/warning/facilities-list";
    }

    /**
     * 主要设备列表
     */
    @RequestMapping(value = "company/mequipment-list")
    public String mequipmentList(HttpServletRequest request, Model model, Integer isTime, String companyName)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", mequipmentMapper.selectMequipmentList(m));
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        return "village/company/mequipment-list";
    }

    /**
     * 特种设备列表
     */
    @RequestMapping(value = "warning/sequipment-list")
    public String sequipmentList(HttpServletRequest request, Model model, Integer isTime, Integer home, String companyName)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", sequipmentMapper.selectSequipmentList(m));
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("home",home);
        return "village/warning/sequipment-list";
    }

    /**
     * 特种人员列表
     */
    @RequestMapping(value = "warning/sperson-list")
    public String spersonList(HttpServletRequest request, Model model, Integer isTime, Integer spFlag, Integer home,
            String companyName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("spFlag", spFlag);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        List<DynamicParameter<String, Object>> list = spersonMapper.selectSpersonList(m);
        model.addAttribute("list", list);
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("home",home);
        return "village/warning/sperson-list";
    }

    /**
     * 公告列表
     */
    @RequestMapping(value = "notice/notice-list")
    public String NoticeList(HttpServletRequest request, Model model) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", getLoginUser(request).getId());
        m.put("type", "1");
        model.addAttribute("list", noticeMapper.selectNoticeList(m));
        return "village/notice/notice-list";
    }

    /**
     * to添加公告列表
     */
    @RequestMapping(value = "notice/notice-add")
    public String ToAddNotice(Integer id, Model model) throws Exception {
        if (null != id) {
            model.addAttribute("notice", noticeMapper.selectByPrimaryKey(id));
        }
        return "village/notice/notice-add";
    }

    /**
     * 公告添加/编辑保存
     */
    @RequestMapping(value = "notice/notice-save")
    public @ResponseBody
    Result villageSave(Notice notice, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        notice.setTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        notice.setUserId(getLoginUser(request).getId());
        if (null == notice.getId()) {
            noticeMapper.insertSelective(notice);
        } else {
            noticeMapper.updateByPrimaryKeySelective(notice);
        }
        return result;
    }

    /**
     * 删除公告
     */
    @RequestMapping(value = "notice/notice-del")
    public @ResponseBody
    Result noticeDel(Integer id) throws Exception {
        Result result = new ResultImpl();
        noticeMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * to修改密码
     */
    @RequestMapping(value = "system/change-psw")
    public String toChangePsw(HttpServletRequest request, Model model) throws Exception {
        return "village/system/passwordSet";
    }

    /**
     * 重置企业登录密码
     */
    @RequestMapping(value = "system/user-psw")
    public @ResponseBody
    Result userPsw(Integer id, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = new User();
        user.setId(id);
        user.setPsw(ConstantsUtil.ENCRYPT_PASSWORD_123456);
        userMapper.updateByPrimaryKeySelective(user);
        return result;
    }
//    /**
//     * 企业分布页面
//     */
//    @SuppressWarnings("unchecked")
//    @RequestMapping("company/company-map")
//    public String coMap(Model model, HttpServletRequest request) throws Exception {
//        User user = getLoginUser(request);
//        Map<String, Object> m = new HashMap<String, Object>();
//        setUserId(user, m);
//        m.put("page", 0);
//        m.put("limit", 20);
//        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
//        
//        for (int i = 0; i < list.size(); i++) {
//            DynamicParameter<String, Object> item = list.get(i);
//            item.put("id", i + 1 + "");
//        }
//        Integer count = companyMapper.selectCountMap(m);      
//        String listStr = JSONArray.toJSONString(list);       
//        String userStr = JSONObject.toJSONString(user);     
//        model.addAttribute("count", count);
//        model.addAttribute("companyList", listStr);
//        model.addAttribute("user", userStr);
//        return "village/company/company-map1";
//    }
    /**
     * 企业分布页面
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("company/company-map")
    public String coMap(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("page", 0);
        m.put("limit", 20);
        
        Integer count = companyMapper.selectCountMap(m);
        //log.error("count:"+count);
        
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
        //log.error("list:"+list.toString());
        //
		Trade trade = tradeMapper.selectByPrimaryKey(user.getId());//行业端集团型企业 add 190131
        if (trade != null && trade.getIsClique() == 1) {
        	TradeClique tc = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        	 if (tc != null) {
        		 m.clear();
        		 m.put("userId",user.getId());
        		 List<DynamicParameter<String, Object>> list1 = companyMapper.selectCompanyMapTC(m);
        		 //log.error("list1:"+list1.toString());
        		 list.addAll(list1);
        	 }
        }
    	//log.error("list:"+list.toString());
    	//
        for (int i = 0; i < list.size(); i++) {
            DynamicParameter<String, Object> item = list.get(i);
            item.put("id", i + 1 + "");
        }

        String listStr = JSONArray.toJSONString(list);
        //log.error("listStr:"+listStr.toString());
        
        String userStr = JSONObject.toJSONString(user);
        //log.error("userStr:"+userStr.toString());
        
        model.addAttribute("count", count);
        model.addAttribute("companyList", listStr);
        model.addAttribute("user", userStr);
        return "village/company/company-map1";
    }

    /**
     * 重大危险源页面
     */
    @RequestMapping(value = "company/company-list4")
    public String coList4(Model model, HttpServletRequest request, String companyName, String dangerlvl)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        if (StringUtils.isNotBlank(dangerlvl)) {
            m.put("dangerlvl", dangerlvl);
        }
        model.addAttribute("companyName", companyName);
        model.addAttribute("dangerlvl", dangerlvl);
        model.addAttribute("list", regulationMapper.selectRegulationList(m));
        return "village/company/company-list4";
    }

    /**
     * 重大危险源 展示页面
     */
    @RequestMapping(value = "company/company-show4")
    public String coList4(Model model, HttpServletRequest request, Integer id) throws Exception {
        model.addAttribute("r", regulationMapper.selectByPrimaryKey(id));
        return "village/company/company-show4";
    }

    /**
     * 劳动防护用品配备页面
     */
    @RequestMapping(value = "company/company-list5")
    public String coList5(Model model, HttpServletRequest request, String companyName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        model.addAttribute("companyName", companyName);
        model.addAttribute("list", pequipmentMapper.selectByMap(m));
        return "village/company/company-list5";
    }

    /**
     * 劳动防护用品页面详细资料
     */
    @RequestMapping(value = "company/company-show5")
    public String coList5(Model model, HttpServletRequest request, Integer userId) throws Exception {
        model.addAttribute("p", pequipmentMapper.selectByPrimaryKey(userId));
        return "village/company/company-show5";
    }

    /**
     * 职业健康检查页面
     */
    @RequestMapping(value = "company/company-list6")
    public String coList6(Model model, HttpServletRequest request, String companyName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        model.addAttribute("companyName", companyName);
        model.addAttribute("list", examinationMapper.selectExaminationList(m));
        return "village/company/company-list6";
    }

    /**
     * 职业健康检查详细资料
     */
    @RequestMapping(value = "company/company-show6")
    public String coList6(Model model, HttpServletRequest request, Integer userId) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("userId", userId);
        model.addAttribute("list", examinationMapper.selectByUserId(m));
        return "village/company/company-show6";
    }

    /**
     * 下载资料
     */
    @RequestMapping(value = "tables/he-download")
    public void hedownload(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 获得请求文件名
        Integer fileId = Integer.parseInt(request.getParameter("id"));
        Table2 info = table2mapper.selectByPrimaryKey(fileId);
        File realPath = new File(request.getServletContext().getRealPath("/"), info.getUrl());
        String filename = URLEncoder.encode(info.getName(), "UTF-8");
        response.setContentType(request.getServletContext().getMimeType(filename));
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        InputStream in = new FileInputStream(realPath);
        OutputStream out = response.getOutputStream();
        try {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } finally {
            in.close();
            out.close();
        }
    }

    /**
     * 安全设施“三同时”
     */
    @RequestMapping("evaluate/tab-she")
    public String zhengTabShe(Model model, String seName, String companyName, HttpServletRequest request)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("isType", "50");
        m.put("seName", seName);
        m.put("companyName", companyName);
        model.addAttribute("list", table3mapper.selectTable(m));
        model.addAttribute("seName", seName);
        model.addAttribute("companyName", companyName);
        return "village/evaluate/tab-she";
    }

    /**
     * 各类许可证页面
     */
    @RequestMapping("evaluate/zheng-list")
    public String zhengList(Model model, HttpServletRequest request, Standard standard, String companyName, Integer isTime)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("type", standard.getType());
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", standardMapper.selectList(m));
        model.addAttribute("type", standard.getType());
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        return "village/evaluate/zheng-list";
    }

    /**
     * 安全生产管理网络图
     */
    @RequestMapping(value = "information/information6")
    public String information6(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("user", companyMapper.selectByPrimaryKey(user.getId()));
        return "company/information/information6";
    }

    /**
     * 特种作业人员页面
     */
    @RequestMapping("train/special-list")
    public String spersonList(Model model, HttpServletRequest request, String companyName, String spFlag,
            String spName, String spType, Integer isTime) throws Exception {
        if (StringUtils.isBlank(spFlag)) {
            spFlag = "1";
        }
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("spName", spName);
        m.put("spFlag", spFlag);
        m.put("spType", spType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", spersonMapper.selectSpersonList(m));
        model.addAttribute("lib", libraryMapper.selectLibraryList(Integer.parseInt(spFlag) + 10));
        model.addAttribute("spName", spName);
        model.addAttribute("spType", spType);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        return "village/train/special-list";
    }

    /**
     * 安全生产教育台账页面
     */
    @RequestMapping(value = "tables/tab-anlist")
    public String anList(HttpServletRequest request, Model model, Integer isType, Integer isTab) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("isType", null == isType ? "2,3,4,5,6,7" : isType);
        model.addAttribute("list", table2mapper.selectTable(m));
        model.addAttribute("isType", isType);
        return "village/tables/tab-anlist";
    }

    /**
     * 企业统计
     */
    @RequestMapping(value = "company/company-tab")
    public String companyTab(Model model, HttpServletRequest request) throws Exception {
        /*User user = getLoginUser(request);
        List<Library> lib = libraryMapper.selectLibraryList(1);
        List<Library> da = libraryMapper.selectLibraryList(2);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        List<DynamicParameter<String, Object>> l = companyMapper.selectIndustry(m);
        List<DynamicParameter<String, Object>> d = companyMapper.selectDlevel(m);
        List<List<Object>> dataL = new ArrayList<List<Object>>();
        for (Library li : lib) {
            List<Object> lll = new ArrayList<Object>();
            lll.add(li.getName());
            lll.add(0);
            dataL.add(lll);
        }
        // List<Object> lll = new ArrayList<Object>();
        // lll.add("未知");
        // lll.add(0);
        // dataL.add(lll);
        for (DynamicParameter<String, Object> dy : l) {
            String industry = dy.getString("industry");
            Integer c = dy.getInteger("c");
            if (StringUtils.isBlank(industry)) {
                // List<Object> x = dataL.get(lib.size() - 1);
                // x.set(1, (Integer) x.get(1) + c);
                // x.set(0, (String) x.get(0));
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
        // List<Object> ddd = new ArrayList<Object>();
        // ddd.add("未知");
        // ddd.add(0);
        // dataD.add(ddd);
        for (DynamicParameter<String, Object> dp : d) {
            String dlevel = dp.getString("dlevel");
            Integer amount = dp.getInteger("amount");
            if (StringUtils.isBlank(dlevel)) {
                // List<Object> obj = dataD.get(da.size() - 1);
                // obj.set(1, (Integer) obj.get(1) + amount);
                // obj.set(0, (String) obj.get(0));
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
        for (List<Object> dL : dataL) {
            Integer x = (Integer) dL.get(1);
            if (x > 0) {
                dL.set(0, (String) dL.get(0) + "(" + x + ")");
            }
        }
        for (List<Object> dL : dataD) {
            Integer x = (Integer) dL.get(1);
            if (x > 0) {
                dL.set(0, (String) dL.get(0) + "(" + x + ")");
            }
        }
        String in = JSONArray.toJSONString(dataL);
        String danger = JSONArray.toJSONString(dataD);
        model.addAttribute("d", in);
        model.addAttribute("danger", danger);*/
        return "village/company/company-tab";
    }
    
    /**
     * 企业统计详情
     */
    @RequestMapping(value = "company/company-tab-detail")
    public String companyTab(Model model, HttpServletRequest request, CompanyListReqDTO dto,
            String scale) throws Exception {
        User user = getLoginUser(request);
        /*Map<String, Object> m = new HashMap<String, Object>();
        m.put("districtId", districtId);
        m.put("townId", townId);
        m.put("villageId", villageId);
        setUserId(user, m);
        if(StringUtils.isNotBlank(industry)) {
            m.put("industry", utf8Str(industry));
        }
        if(StringUtils.isNotBlank(dlevel)) {
            m.put("dlevel", "'" + utf8Str(dlevel) + "'");
        }
        if(StringUtils.isNotBlank(industry2)) {
            m.put("industry2", utf8Str(industry2));
        }
        m.put("scale", scale);
        List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyList(m);
        model.addAttribute("list", mlist);
        model.addAttribute("m", m);*/
        dto.setIndustry2(utf8Str(dto.getIndustry2()));
        dto.setDlevel(utf8Str(dto.getDlevel()));
        dto.setIndustry(utf8Str(dto.getIndustry()));
        cgfService.selectCompanyWithPage(dto, user, model);
        
        if(user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }
        
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("sk", request.getParameter("sk"));
        
        return "village/company/company-tab-detail";
    }

    /**
     * 隐患排查 检查历史
     * TODO 排查治理记录
     * user. userType : 管理类型  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     *
     *
     */
    @RequestMapping(value = "check-list")//flag:3 部门抽查
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
            Integer townId, Integer villageId,
            Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        // 向map集合进行存储
        m.put("type", type);  //
        m.put("flag", flag);  // 1
        m.put("title", title); // 1
        m.put("townId", townId);   // null
        m.put("villageId", villageId);  //1
        m.put("companyName", companyName); // null
        m.put("status", status); //状态  null
        // 进行判断
        if(setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            model.addAttribute("list", list);
        }
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        if (user.getUserType() == 5) {
            // 表示等于5的话就将页面进行跳转
            return "company/danger/check-list";
        }
        // TODO 找到这个界面
        return "village/danger/check-list";
    }

    /**
     * 村级账号 隐患排查 检查历史
     */
    @RequestMapping(value = "check-list-cx")
    public String troubleList2(HttpServletRequest request, String title, Integer type, String companyName,
            Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", type);
        m.put("flag", flag);
        m.put("title", title);
        m.put("companyName", companyName);
        m.put("status", status);
        if(setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            model.addAttribute("list", list);
        }
        
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        if (user.getUserType() == 5) {
            return "company/danger/check-list";
        }
        return "village/danger/check-list-cx";
    }
    
    /**
     * 添加行政、委托检查表 flag 2 行政执法 3 委托 4 行政检查 userId 企业id
     */
    @RequestMapping(value = "check-add")
    public String checkAdd(Integer flag, Integer industryId, Integer userId, Model model, HttpServletRequest request)
            throws Exception {
        User user = getLoginUser(request);
        if(user.getUserType().intValue() == 5) {
            userId = user.getId();
        }
        model.addAttribute("type", 3);// 默认临时检查
        model.addAttribute("flag", flag);
        if (null == userId) {// 先选择企业
            return "village/danger/check-add";
        }
        TCompany tc = tCompanyMapper.selectByPrimaryKey(userId);
        if (null == tc) {
            return "redirect:/village/trouble-set?userId=" + userId + "&url="
                    + URLEncoder.encode("/village/check-add?flag=" + flag + "&userId=" + userId, "utf-8");
        }
        model.addAttribute("company", companyMapper.selectByPrimaryKey(userId));
//        if (null == industryId) {// 确认检查标准
            model.addAttribute("ind1", tIndustryMapper.selectByPrimaryKey(tc.getIndustry1()));// 基础检查类别
            if(StringUtils.isNotEmpty(tc.getIndustry2())) {
                model.addAttribute("ind2L", tIndustryMapper.selectByIds(tc.getIndustry2()));// 现场检查类别
            }
            if (StringUtils.isNotBlank(tc.getIndustry3())) {
                model.addAttribute("ind3L", tIndustryMapper.selectByIds(tc.getIndustry3()));// 高危检查类别
            }
//            return "village/danger/check-add1";
//        }
        // 确定标题等
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("userId", user.getId());
//        model.addAttribute("jcL", officialsMapper.selectList(m));// 执法人员
        return "village/danger/check-add2";
    }
    
    /**
     * 选择企业
     */
    @RequestMapping(value = "companyLoad")
    public String companyLoad(Model model, String companyName, HttpServletRequest request)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        model.addAttribute("list", companyMapper.selectCompanyList(m));
        return "village/danger/companyLoad";
    }

    /**
     * 企业自查 填写不合格项
     */
//    @RequestMapping(value = "plan-next")
//    public String checkNext(Integer id, Model model, HttpServletRequest request) throws Exception {
//        User user = getLoginUser(request);
//        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
//        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(id);
//        model.addAttribute("check", tc);
//        model.addAttribute("partL", partL);
//        model.addAttribute("itemL", tCheckItemMapper.selectByCheckId(id));
//        if (tc.getStatus() == 2) {// 已检查
//            return "company/danger/plan-detail";
//        }
//        model.addAttribute("now", new Date());
//        model.addAttribute("company", companyMapper.selectByPrimaryKey(tc.getUserId()));
//        
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("userId", user.getId());
//        model.addAttribute("jcL", officialsMapper.selectList(m));// 执法人员
//        return "village/danger/plan-next";
//    }

    /**
     * TODO 生成检查表数据
     * @param id
     * @param type
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "plan-next")//生成检查表，modify by zhangcl 2018.10.27
    public String checkNext(Integer id,Integer type, Model model, HttpServletRequest request) throws Exception {
    	//log.error("checkNext checkid : "+id);
    	User user = getLoginUser(request);
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        
        //log.error("tCheckMapper检查表信息:"+tc.toString());
        type = tc.getType();// add wz 190110
        
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(id);
        //log.error("tCheckPartMapper条目信息:"+partL.toString());
        for(TCheckPart a : partL) {
            String levels = a.getLevels();
            //log.error("levels:"+levels);
        }
          
        model.addAttribute("check", tc);
        model.addAttribute("partL", partL);
        //model.addAttribute("itemL", tCheckItemMapper.selectByCheckId(id));
        List<Map<String, Object>> iteml = tCheckItemMapper.selectByCheckId(id);
        //log.error("tCheckItemMapper条目结果信息:"+iteml.toString());
        
        if(type!=null&&type==9){
        	for(Map<String,Object> a: iteml){
            	//log.error("checkNext:"+1);
            	Integer[] ids = new Integer[1];
            	ids[0] = (Integer) a.get("levelId");
            	//log.error("ids:"+ids[0]);
            	//log.error("a:"+a.toString());
            	List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
            	String dangertype="";
            	String factors="";
            	String measures="";
            	String level1="";
            	String level2="";
            	String level3="";
            	for(ACompanyManual aa: rets){
            		//log.error("checkNext:"+2);
            		dangertype = aa.getType();
            		factors = aa.getFactors();
            		measures = aa.getMeasures();
            		level1 = aa.getLevel1();
            		level2 = aa.getLevel2();
            		level3 = aa.getLevel3();
            		//log.error("type:"+dangertype);
            		break;
            	}
            	a.put("dangerType",dangertype);
            	a.put("factors",factors);
            	a.put("measures",measures);
            	a.put("level1",level1);
            	a.put("level2",level2);
            	a.put("level3",level3);
            	//log.error("level1/2/3 : "+level1+"/"+level2+"/"+level3);
            }
        }
        //log.error("tCheckItemMapper条目结果信息2:"+iteml.toString());
        model.addAttribute("itemL", iteml);
        //log.error("checkNext:"+33);
        if (tc.getStatus() == 2) {// 已检查
            return "company/danger/plan-detail";
        }
        model.addAttribute("now", new Date());
        model.addAttribute("company", companyMapper.selectByPrimaryKey(tc.getUserId()));
        
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        model.addAttribute("jcL", officialsMapper.selectList(m));// 执法人员
        log.error("plan-next："+6);
        if(type==9)
        	return "village/danger/plan-next1";
        else
        	return "village/danger/plan-next";
    }

//    /**
//     * 检查表隐患 整改
//     */
//    @RequestMapping(value = "check-rectification")
//    public String checkRectification(Integer id, Model model, Integer flag) throws Exception {
//        TRectification rectification = tRectificationMapper.selectByCheckId(id);
//        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
//        model.addAttribute("check", check);
//        model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(id, null));
//        model.addAttribute("rectification", rectification);
//        model.addAttribute("company", companyMapper.selectByPrimaryKey(check.getInteger("userId")));
//        model.addAttribute("flag", flag);
//        model.addAttribute("serList", gson.toJson(tItemSeriousMapper.selectbylid(null)));
//        model.addAttribute("listM", tCheckMapper.selectCompany(id));
//        return "village/danger/opinion-detail";
//    }
    
    /**
     * 检查表隐患 整改  update wz 190111
     */
    @RequestMapping(value = "check-rectification")
    public String checkRectification(Integer id, Model model, Integer flag) throws Exception {
    	//log.error("checkId："+id);
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        Integer type = tc.getType();
        //log.error("检查表type："+type);

        TRectification rectification = tRectificationMapper.selectByCheckId(id);
        //log.error(rectification.toString());
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);
        //model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(id, null));
        
        List<Map<String, Object>> iteml = tCheckItemMapper.selectDangerByCheckId(id, null);
        //log.error("tCheckItemMapper条目结果信息:"+iteml.toString());
        if(type!=null&&type==9){
        	for(Map<String,Object> a: iteml){
            	//log.error("checkNext:"+1);
            	Integer[] ids = new Integer[1];
            	ids[0] = (Integer) a.get("levelId");
            	//log.error("ids:"+ids[0]);
            	//log.error("a:"+a.toString());
            	List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
            	String dangertype="";
            	String factors="";
            	String measures="";
            	String level1="";
            	String level2="";
            	String level3="";
            	for(ACompanyManual aa: rets){
            		//log.error("checkNext:"+2);
            		dangertype = aa.getType();
            		factors = aa.getFactors();
            		measures = aa.getMeasures();
            		level1 = aa.getLevel1();
            		level2 = aa.getLevel2();
            		level3 = aa.getLevel3();
            		//log.error("type:"+dangertype);
            		break;
            	}
            	a.put("dangerType",dangertype);
            	a.put("factors",factors);
            	a.put("measures",measures);
            	a.put("level1",level1);
            	a.put("level2",level2);
            	a.put("level3",level3);
            	//log.error("level1/2/3 : "+level1+"/"+level2+"/"+level3);
            }
        }
        //log.error("tCheckItemMapper条目结果信息2:"+iteml.toString());
        model.addAttribute("itemL", iteml);
        
        model.addAttribute("rectification", rectification);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(check.getInteger("userId")));
        model.addAttribute("flag", flag);
        model.addAttribute("serList", gson.toJson(tItemSeriousMapper.selectbylid(null)));
        model.addAttribute("listM", tCheckMapper.selectCompany(id));
        log.error("check-rectification："+6);
        if(type==9){
        	return "village/danger/opinion-detailrjcb";
    	}else{
    		return "village/danger/opinion-detail";
    	}	
    }

    /**
     * 整改指令书
     */
    @RequestMapping(value = "check-document")
    public String checkDocument(Integer checkId, Integer flag, Model model,TRectification tr) throws Exception {
        if (null == flag) {
            flag = 8;// 现场检查记录
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("checkId", checkId);
        m.put("flag", flag);
        TCheckDocument doc = tCheckDocumentMapper.selectByCheckId(m);
        TCheck check = tCheckMapper.selectByPrimaryKey(checkId);
        model.addAttribute("timenow", new Date());
        int count = tRecheckMapper.selectByCheckId(checkId).size();
        model.addAttribute("count",count);
        if (null == doc) {// 第一次
            /*if(check.getFlag() == 3){
            cgfService.rectificationSave(tr);
            }*/
            tr = tRectificationMapper.selectByCheckId(checkId);
            model.addAttribute("check", check);
            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
            model.addAttribute("itemL1", tCheckItemMapper.selectDangerByCheckId(checkId, 1));
            model.addAttribute("rectification", tr);
            model.addAttribute("company", companyMapper.selectByPrimaryKey(check.getUserId()));
        } else {
            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
            model.addAttribute("itemL1", tCheckItemMapper.selectDangerByCheckId(checkId, 1));
            model.addAttribute("document", doc);
            return "village/danger/opinion-detail2";
        }
        model.addAttribute("serList", gson.toJson(tItemSeriousMapper.selectbylid(null)));
        model.addAttribute("userId", check.getUserId());
        model.addAttribute("checkId", checkId);
        log.error("checkid:"+checkId+",flag:"+flag+",checkflag:"+check.getFlag());
        return "village/danger/opinion-add" + flag + check.getFlag();
    }

    /**
     * @return 整改指令书保存
     */
    @RequestMapping(value = "check-document-save")
    public @ResponseBody
    Result checkDocumentSave(Integer checkId, Integer flag, String copy, HttpServletRequest request) {
        Result result = new ResultImpl();
        TCheck c = tCheckMapper.selectByPrimaryKey(checkId);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("checkId", checkId);
        m.put("flag", flag);
        TCheckDocument doc = tCheckDocumentMapper.selectByCheckId(m);
        if (null == doc) {// 第一次
            User user = getLoginUser(request);
            doc = new TCheckDocument();
            doc.setCheckId(checkId);
            doc.setFlag(flag);
            doc.setCopy(copy);
            doc.setCreateTime(new Date());
            doc.setCreateUser(user.getId());
            doc.setUserId(c.getUserId());
            doc.setDel(0);
            tCheckDocumentMapper.insertSelective(doc);
        } else {
            doc.setCopy(copy);
            tCheckDocumentMapper.updateByPrimaryKeySelective(doc);
        }
        return result;
    }

    /**
     * 现场记录保存
     * @param checkId
     * @param userId
     * @param copy
     * @param flag2
     * @param itms
     * @param checkCompany
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "check-document-save8")
    public @ResponseBody
    Result checkDocumentSave8(Integer checkId, Integer userId, String copy, Integer flag2, String itms, String checkCompany,
            HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        checkId = cgfService.checkDocumentSave8(checkId, userId, copy, flag2, itms, getLoginUser(request).getId(), checkCompany);
        result.setMap("checkId", checkId);
        return result;
    }

    /**
     * 企业自查 删除
     */
    @RequestMapping(value = "check-del")
    public @ResponseBody
    Result checkDel(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
//        TCheck t = new TCheck();
//        t.setId(id);
//        t.setDel(1);
//        tCheckMapper.updateByPrimaryKeySelective(t);
        cgfService.checkDel(id);
        return result;
    }

    /**
     * 隐患列表 整改复查列表
     * c 0 表示过滤未整改的 1已整改的
     */
    @RequestMapping(value = "rectification-list")
    public String checkDangerList(Integer flag, String title, Integer c,String companyName, Model model,
            HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);
        m.put("title", title);
        m.put("companyName", companyName);
        if(null != c) {
            m.put("c", c);
        }
        if(setUserId(user, m)) {
            clearVillageTown(m);
            model.addAttribute("list", tRectificationMapper.selectRectificationList(m));
        }
        model.addAttribute("flag", flag);
        model.addAttribute("title", title);
        model.addAttribute("c", c);
        model.addAttribute("companyName", companyName);
        if (user.getUserType() == 5) {
            return "company/danger/check-fulist";
        }
        return "village/danger/check-fulist";
    }

    /**
     * 企业自查 侧边栏点击企业自查的
     * TODO 点击行政检查 /village/trouble-set?url=/company/check-list?flag=4%26type=3
     */
    @RequestMapping(value = "trouble-set")
    public String troubleSet(Integer userId, String url, Model model, HttpServletRequest request) throws Exception {
        // TODO 获取登陆的用户的信息
        User user = getLoginUser(request);
        // 判断是否为企业
        if(user.getUserType().intValue() == 5) {
            userId = user.getId();
        }
        // 查询产业表的所有字段
        List<TIndustry> list = tIndustryMapper.selectAll();
        // 查询公司所属的id并查询  现场检查所属行业
        TCompany tc = tCompanyMapper.selectByPrimaryKey(userId);
        if (null == tc) {
            // 企业的详细信息
            Company c = companyMapper.selectByPrimaryKey(userId);
            if (null != c) {
                // 判断行业
                if (StringUtils.isNotBlank(c.getIndustry())) {
                    // 存储到session
                    model.addAttribute("industry", c.getIndustry());
                }
            }
        } else {
        }
        model.addAttribute("tc", tc);
        model.addAttribute("url", url);
        List<TTindustry> tL = tTindustryMapper.selectAll();
        model.addAttribute("list", list);
        model.addAttribute("userId", userId);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(userId));
        model.addAttribute("ttindustry", gson.toJson(tL));// 企业行业与检查行业对应
        model.addAttribute("libL", libraryMapper.selectLibraryList(1));// 行业
        return "village/danger/danger-list3";
    }

    /**
     * 企业自查 设置
     */
    @RequestMapping(value = "trouble-set-do")
    public @ResponseBody
    Result troubleSet(HttpServletRequest request, TCompany tc) throws Exception {
        Result result = new ResultImpl();
        if (null == tCompanyMapper.selectByPrimaryKey(tc.getUserId())) {
            tCompanyMapper.insertSelective(tc);
        } else {
            tCompanyMapper.updateByPrimaryKeySelective(tc);
        }
        return result;
    }

    /**
     * 行政检查表，委托检查 保存
     */
    @RequestMapping(value = "check-add-do")
    public @ResponseBody
    Result checkAddDo(@RequestBody CheckSaveReqDTO dto, HttpServletRequest request) {
        Result result = new ResultImpl();
        try {
            User user = getLoginUser(request);
            dto.getCheck().setCreateUser(user.getId());
            cgfService.checkSaveDo(dto);
            result.setMap("checkId", dto.getCheck().getId());
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "保存失败");
        }
        return result;
    }

    /**
     * 行政文书
     */
    @RequestMapping("opinion-show")
    public String opinionShow(Model model, String companyName, HttpServletRequest request, Integer flag, Integer flag2)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("flag2", flag2);
        model.addAttribute("list", tCheckDocumentMapper.selectTable(m));
        model.addAttribute("flag", null == flag ? 8 : flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("flag2", flag2);
        return "village/danger/opinion-show";
    }

    /**
     * 行政文书
     */
    @RequestMapping("danger/opinion-list")
    public String opinionList(Model model, String companyName, HttpServletRequest request, Integer type, Integer flag2)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("type", type);
        m.put("flag2", flag2);
        model.addAttribute("list", tCheckDocumentMapper.selectTable(m));
        model.addAttribute("type", type);
        model.addAttribute("flag2", flag2);
        model.addAttribute("companyName", companyName);
        if (type == 1) {
            return "village/danger/opinion-list2";
        }
        return "village/danger/opinion-list";
    }

    /**
     * 行政文书添加
     */
    @RequestMapping("danger/opinion-add")
    public String opinionAdd(Model model, Integer userId, HttpServletRequest request, Integer flag, Integer flag2)
            throws Exception {
        User user = getLoginUser(request);
        if (null == userId && user.getUserType() == 5) {
            userId = user.getId();
        }
        model.addAttribute("flag", flag);
        model.addAttribute("flag2", flag2);
        if (null == userId) {// 先选择企业
            return "village/danger/opinion-add";
        }
        Company company = companyMapper.selectByPrimaryKey(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("company", company);
        model.addAttribute("timenow", new Date());
        return "village/danger/opinion-add" + flag + flag2;
    }

    /**
     * 行政文书保存
     */
    @RequestMapping("danger/opinion-save")
    public @ResponseBody
    AppResult opinionSave(Model model, Integer userId, HttpServletRequest request, TCheckDocument tCheckDocument)
            throws Exception {
        AppResult result = new AppResultImpl();
        User user = getLoginUser(request);
        tCheckDocument.setUserId(userId);
        tCheckDocument.setCreateUser(user.getId());
        tCheckDocument.setDel(0);
        tCheckDocument.setCreateTime(new Date());
        tCheckDocumentMapper.insertSelective(tCheckDocument);
        result.setStatus("0");
        result.setData(tCheckDocument);
        return result;
    }
    
    /**
     * 行政文书保存
     */
    @RequestMapping("getVillageByTown")
    public @ResponseBody Result getVillageByTown(Model model, Integer townId) {
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("townId", townId);
        result.setMap("list", villageMapper.selectListByTown(m));
        return result;
    }

    /**
     * 行政文书详情
     */
    @RequestMapping("danger/opinion-detail")
    public String opinionDetail(Model model, HttpServletRequest request, Integer id) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        TCheckDocument d = tCheckDocumentMapper.selectByPrimaryKey(id);
        if(null != d.getCheckId()) {
            model.addAttribute("check", tCheckMapper.selectByPrimaryKey(d.getCheckId()));
            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(d.getCheckId(), null));
            model.addAttribute("itemL1", tCheckItemMapper.selectDangerByCheckId(d.getCheckId(), 1));
            model.addAttribute("rectification", tRectificationMapper.selectByCheckId(d.getCheckId()));
        }
        model.addAttribute("document", tCheckDocumentMapper.selectByPrimaryKey(id));
        return "village/danger/opinion-detail2";
    }

    /**
     * 隐患汇总 cId 企业id  隐患排查记录
     */
    @RequestMapping(value = "danger-index-list")
    public String modelList(HttpServletRequest request, Model model, Integer flag, Integer cId, Integer d,
            String partName, String companyName, Integer status,  
            String startTime, String endTime) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        //企业自查显示——未整改	wz 18/12/20
        if(status == null){
        	status =2;  	
        }else if(status == 1){
        	status =null;
        }
        m.put("flag", flag);
        m.put("status", status);
        m.put("d", d);
        m.put("companyName", companyName);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        if(null != cId) {
            m.put("userId", cId);
        }
        m.put("partName", partName);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerIndexList(m);
        m.put("status", 2);
        Integer list_s2 = tCheckItemMapper.selectDangerIndexListCount(m);
        m.put("status", 3);
        Integer list_s3 = tCheckItemMapper.selectDangerIndexListCount(m);
        model.addAttribute("list_s2", list_s2);
        model.addAttribute("list_s3", list_s3);
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("companyName", companyName);
        model.addAttribute("partName", partName);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("d", d);
        if (user.getUserType() == 5) {
            return "company/danger/danger-index-list";
        }
        model.addAttribute("cId", cId);
        return "village/company/danger-index-list";
    }

    @RequestMapping(value = "recheck-list")
    public String modelList(HttpServletRequest request, Model model,Integer flag) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("flag", flag);


        // 企业登录
//        if(1==flag){
            List<Map> list = tCheckItemMapper.selectRecheckList(user.getId());
            model.addAttribute("list", list);
//        }



        return "company/danger/recheck-list";
    }


    /**
     * 隐患汇总 cId 企业id
     */
    @RequestMapping(value = "danger-collect")
    public String modelList(HttpServletRequest request, Integer industryType, Integer townId, Integer villageId, String companyName, Model model, String startTime, String endTime) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        m.put("townId", townId);
        m.put("villageId", villageId);
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        m.put("industryType", industryType);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerCollectByCompany(m);
        if (user.getUserType() == 6) {
            m.put("districtId", user.getId());
        }
        // userId, companyName,
        // 企业自查未整改, 已整改, 严重未整改,严重已整改
        // 行政执法未整改, 已整改, 严重未整改,严重已整改
        // 委托检查未整改, 已整改, 严重未整改,严重已整改
        // 行政检查未整改, 已整改, 严重未整改,严重已整改
        // Object[] a = new Object[18];
        List<Object[]> ll = new ArrayList<Object[]>();
        for (Map<String, Object> l : list) {
            Integer userId = Integer.valueOf(l.get("userId").toString());
            Integer c = Integer.valueOf(l.get("c").toString());// 数量
            Integer flag = Integer.valueOf(l.get("flag").toString());// 1 自查 2执法 3委托5检查
            Integer status = Integer.valueOf(l.get("status").toString());// 2未改 3已改
            Integer d = Integer.valueOf(l.get("d").toString());// 0 一般隐患 1重大隐患
            Object[] a = null;
            Boolean has = false;
            for (Object[] o : ll) {
                if (((Integer) o[0]).compareTo(userId) == 0) {
                    a = o;
                    has = true;
                    break;
                }
            }
            if (null == a) {
                String name = (String) l.get("name");// 企业名称
                a = new Object[]{userId, name, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            }
            //处理数据
            int index = (flag -1) * 4 + 2;
            if(status == 2) {
                a[index] = (Integer)a[index] + c;
            } else {
                a[index + 1] = (Integer)a[index + 1] + c;
            }
            //重大隐患
            if(d == 1) {
                if(status == 2) {
                    a[index + 2] = (Integer)a[index + 2] + c;
                } else {
                    a[index + 3] = (Integer)a[index + 3] + c;
                } 
            }
            if(!has) {
                ll.add(a);
            }
        }
        model.addAttribute("m", m);
        model.addAttribute("list", ll);
        
        m.put("d", 1);
        Integer d = tCheckItemMapper.selectDangerIndexListCount(m);
        List<Map<String, Object>> lll = tCheckItemMapper.selectDangerCollectGroup(m);
        model.addAttribute("groupIndu", lll);
        model.addAttribute("d", d);
        model.addAttribute("libL", libraryMapper.selectLibraryList(1));// 行业
        return "village/danger/danger-collect";
    }
    
    /**
     * 隐患汇总 cId 企业id
     */
    @RequestMapping(value = "danger-collect-print")
    public String collectPrint(HttpServletRequest request, Integer industryType, Integer townId, Integer villageId, String companyName, Model model, String startTime, String endTime) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        m.put("townId", townId);
        m.put("villageId", villageId);
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        m.put("industryType", industryType);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerCollectByCompany(m);
        if (user.getUserType() == 6) {
            m.put("districtId", user.getId());
        }
        // userId, companyName,
        // 企业自查未整改, 已整改, 严重未整改,严重已整改
        // 行政执法未整改, 已整改, 严重未整改,严重已整改
        // 委托检查未整改, 已整改, 严重未整改,严重已整改
        // 行政检查未整改, 已整改, 严重未整改,严重已整改
        // Object[] a = new Object[18];
        List<Object[]> ll = new ArrayList<Object[]>();
        for (Map<String, Object> l : list) {
            Integer userId = Integer.valueOf(l.get("userId").toString());
            Integer c = Integer.valueOf(l.get("c").toString());// 数量
            Integer flag = Integer.valueOf(l.get("flag").toString());// 1 自查 2执法 3委托5检查
            Integer status = Integer.valueOf(l.get("status").toString());// 2未改 3已改
            Integer d = Integer.valueOf(l.get("d").toString());// 0 一般隐患 1重大隐患
            Object[] a = null;
            Boolean has = false;
            for (Object[] o : ll) {
                if (((Integer) o[0]).compareTo(userId) == 0) {
                    a = o;
                    has = true;
                    break;
                }
            }
            if (null == a) {
                String name = (String) l.get("name");// 企业名称
                a = new Object[]{userId, name, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            }
            //处理数据
            int index = (flag -1) * 4 + 2;
            if(status == 2) {
                a[index] = (Integer)a[index] + c;
            } else {
                a[index + 1] = (Integer)a[index + 1] + c;
            }
            //重大隐患
            if(d == 1) {
                if(status == 2) {
                    a[index + 2] = (Integer)a[index + 2] + c;
                } else {
                    a[index + 3] = (Integer)a[index + 3] + c;
                } 
            }
            if(!has) {
                ll.add(a);
            }
        }
        model.addAttribute("m", m);
        model.addAttribute("list", ll);
        
        m.put("d", 1);
        Integer d = tCheckItemMapper.selectDangerIndexListCount(m);
        List<Map<String, Object>> lll = tCheckItemMapper.selectDangerCollectGroup(m);
        model.addAttribute("groupIndu", lll);
        model.addAttribute("d", d);
        model.addAttribute("libL", libraryMapper.selectLibraryList(1));// 行业
        return "village/danger/print-danger-collect";
    }

    /**
     * 重大隐患列表
     */
    @RequestMapping(value = "check-item3")
    public String checkItem3(HttpServletRequest request, Model model, Integer flag, String companyName, Integer status)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("flag", flag);
        m.put("status", status);
        m.put("companyName", companyName);
        m.put("d", 1);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerIndexList(m);
        m.put("status", 2);
        Integer list_s2 = tCheckItemMapper.selectDangerIndexListCount(m);
        m.put("status", 3);
        Integer list_s3 = tCheckItemMapper.selectDangerIndexListCount(m);
        model.addAttribute("list_s2", list_s2);
        model.addAttribute("list_s3", list_s3);
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("companyName", companyName);
        return "village/company/danger-index3";
    }
    
    /**
     * 摄像头监管段
     */
    @RequestMapping(value = "monitor-list-supervise")
    public String monitorListSupervise(HttpServletRequest request, String companyName, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        model.addAttribute("list", monitorMapper.selectGroupByMap(m));
        model.addAttribute("companyName", companyName);
        return "village/system/monitor-list-supervise";
    }
    
    /**
     * 摄像头企业端
     */
    @RequestMapping(value = "monitor-list-company")
    public String monitorListSupervise(HttpServletRequest request, Integer userId, Model model) throws Exception {
        //User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        //setUserId(user, m);
        m.put("userId", userId);
        model.addAttribute("list", monitorMapper.selectByMap(m));
        model.addAttribute("user", userMapper.selectByPrimaryKey(userId));
        return "village/system/monitor-list-company";
    }

    /**
     * 摄像头list
     */
    @RequestMapping(value = "monitor-list")
    public String monitorList(HttpServletRequest request, String companyName, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        model.addAttribute("list", monitorMapper.selectByMap(m));
        if (user.getUserType() == 5) {
            return "company/system/monitor-list";
        }
        model.addAttribute("companyName", companyName);
        return "village/system/monitor-list";
    }

    /**
     * 执法文档页面列表
     * 
     * @throws Exception
     */
    @RequestMapping("evaluate/lawDoc-list")
    public String lawDocList(Model model, HttpServletRequest request, String companyName, Integer flag)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        //List<DynamicParameter<String, Object>> companylist = companyMapper.selectCompanyList(m);
        m.put("companyName", companyName);
        m.put("flag", flag);
        List<Map<String, Object>> list = lawDocMapper.selectList(m);
        model.addAttribute("list", list);
        //model.addAttribute("companylist", companylist);
        model.addAttribute("companyName", companyName);
        model.addAttribute("flag", flag);
        if (user.getUserType() == 5) {
            return "company/evaluate/lawDoc-list";
        }
        return "village/evaluate/lawDoc-list";
    }

    /**
     * 执法文档页面删除
     */
    @RequestMapping("evaluate/lawDoc-del")
    public @ResponseBody
    Result lawDocDel(Model model, HttpServletRequest request, LawDoc lawDoc) throws Exception {
        Result result = new ResultImpl();
        lawDocMapper.deleteByPrimaryKey(lawDoc.getId());
        return result;
    }

    /**
     * 执法文档页面保存
     */
    @RequestMapping("evaluate/lawDoc-add")
    public @ResponseBody
    Result lawDocSave(HttpServletRequest request, LawDoc lawDoc) {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        if (user.getUserType() == 5) {
            lawDoc.setUserId(user.getId());
        }
        lawDoc.setCreateTime(new Date());
        lawDoc.setCreateUserId(user.getId());
        lawDocMapper.insertSelective(lawDoc);
        return result;
    }

    /**
     * 执法文档删除
     */
    @RequestMapping("danger/opinion-del")
    public @ResponseBody
    Result opinionDel(HttpServletRequest request, TCheckDocument doc) {
        Result result = new ResultImpl();
        doc.setDel(1);
        tCheckDocumentMapper.updateByPrimaryKeySelective(doc);
        return result;
    }

    /**
     * 防雷防静电设施页面列表
     * 
     * @throws Exception
     */
    @RequestMapping("warning/lightning-list")
    public String lightningList(Model model, HttpServletRequest request, String companyName, String project)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("project", project);
        List<Map<String, Object>> list = lightningProtectionMapper.selectList(m);
        model.addAttribute("list", list);
        model.addAttribute("companyName", companyName);
        model.addAttribute("project", project);
        return "village/warning/lightning-list";
    }

    /**
     * 设置检查表
     */
    @RequestMapping("check-add-cho")
    public String checkAddCho(Integer flag, Model model) {
        model.addAttribute("flag", flag);
        return "village/danger/check-add-cho";
    }

    /**
     * 设置检查表
     */
    @RequestMapping("danger-chart")
    public String dangerChart() {
        return "company/danger/danger-chart";
    }
    /**
     * 监管端行政检查添加
     * 
     * @throws Exception
     */
    // @RequestMapping("company/model-add")
    // public String model(Model model, HttpServletRequest request,Integer userId, Integer flag, String id) throws Exception {
    // model.addAttribute("flag", flag);
    // model.addAttribute("id", id);
    // if(null == userId) {//先选择企业
    // Map<String, Object> m = new HashMap<String, Object>();
    // m.put("villageId", getLoginUser(request).getId());
    // model.addAttribute("list", companyMapper.selectCompanyList(m));
    // return "village/company/model-add";
    // }
    // Company company = companyMapper.selectByPrimaryKey(userId);
    // model.addAttribute("userId", userId);
    // model.addAttribute("company", company);
    // model.addAttribute("timenow", new Date());
    // return "village/company/model-add";
    // }
    
    /**
     * 安全监察监管——高危作业/重大危险源
     */
    @RequestMapping(value = "company/company-list-zg")
    public String companyListZg(Integer townId, Integer villageId, Integer userId, Model model,
            HttpServletRequest request, String companyName, String state, String isFreeze,
            Integer cisDanger,Integer chemic, String dlevel, Integer isKey, String doubleDanger, Integer danger) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        if (null != townId) {
            m.put("townId", townId);
        }
        if (null != villageId) {
            m.put("villageId", villageId);
        }
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("isFreeze", isFreeze);
        m.put("cisDanger", cisDanger);//1 含有重大危险源
        m.put("chemic", chemic);// 含有危险化学品
        if(null != dlevel) {
            dlevel = utf8Str(dlevel);
        }
        m.put("dlevel", dlevel);
        if (user.getUserType() == 10) {
            m.put("isTradeKey", isKey);
        }else{
            m.put("isKey", isKey);
        }
        m.put("doubleDanger", doubleDanger);
        m.put("danger", danger);
        List<DynamicParameter<String, Object>> list = new ArrayList<DynamicParameter<String,Object>>();
        if(cisDanger != null){//重大危险源
//	        List<Map<String, Object>> mList = monitorMapper.selectGroupByMap(m);//在线监控有内容的企业
//	        if(mList.size()==0){
//	        	m.put("userIds", -1);
//	        }else{
//	        	String[] userIds = new String[mList.size()];
//	            for(int i = 0; i < mList.size(); i++){
//	            	userIds[i] = mList.get(i).get("userId").toString();
//	            }
//	            m.put("userIds", StringUtils.join(userIds, ","));
//	        }
	        list = companyMapper.selectCompanyList(m);
            for(DynamicParameter<String, Object> l : list) {
                String dangerMaterial = l.getString("dangerMaterial");
                if(StringUtils.isNotBlank(dangerMaterial)) {
                    l.put("dangerMaterials", dangerMaterial.split(","));
                }
            }
        }else{
        	list = companyMapper.selectCompanyList(m);
        }
        model.addAttribute("list", list);
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("companyName", companyName);
        model.addAttribute("state", state);
        model.addAttribute("isFreeze", isFreeze);
        if (user.getUserType() == 10) {
            model.addAttribute("isTradeKey", isKey);
        }else{
            model.addAttribute("isKey", isKey);
        }
        model.addAttribute("sk", request.getParameter("sk"));
        
        if(danger != null){//高危作业
            return "village/warning/company-list-gwzy";
        }
        if(cisDanger != null){//重大危险源
            return "village/warning/company-list-zdwxy";
        }
        return "village/company/company-list";
    }
    
    /**
     * 安全风险管控
     */
    @RequestMapping(value = "safety-system/control-list")
    public String controlList(HttpServletRequest request, 
            Integer townId, Integer villageId, Integer userId, Model model,
            String companyName, String level) throws Exception {
    	//log.error("2018.12.4 debug 0 zhangcl");
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        if (null != townId) {
            m.put("townId", townId);
        }
        if (null != villageId) {
            m.put("villageId", villageId);
        }
        setUserId(user, m);
        if(StringUtils.isNotBlank(level)) {
            level = utf8Str(level);
        }
        m.put("companyName", companyName);
        m.put("level", level);
        model.addAttribute("list", aCompanyManualMapper.selectByMap2(m));
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        model.addAttribute("m", m);
        //log.error("2018.12.4 debug 10 zhangcl");
        log.error("level："+level.toString());
        if(level.equals("红色")){
        	return "village/safety-system/control-list";
        }else{
        	return "village/safety-system/control-list1";
        }
    }
    /**
     * 安全风险管控-企业列表 
     */
    @RequestMapping(value = "safety-system/control-listmenu")
    public String controlListmenu(HttpServletRequest request, 
            Integer townId, Integer villageId, Integer userId, Model model,
            String companyName, String level) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        if (null != townId) {
            m.put("townId", townId);
        }
        if (null != villageId) {
            m.put("villageId", villageId);
        }
        setUserId(user, m);
        if(StringUtils.isNotBlank(level)) {
            level = utf8Str(level);
        }
        m.put("companyName", companyName);
        m.put("level", level);
        //model.addAttribute("list", aCompanyManualMapper.selectByMap3(m));
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap3(m);
        model.addAttribute("list",list);
        //log.error("list："+list.toString());
        //统计隐患总数
        Long totalyh = (long) 0;
        for(int i = 0 ; i < list.size() ; i++ ){
        	Long num = (Long)list.get(i).get("companyNum");
        	totalyh = totalyh + num;
        }
        //log.error("totalyh："+totalyh);
        model.addAttribute("totalyh",totalyh);
        
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        model.addAttribute("m", m);
        //log.error("list："+aCompanyManualMapper.selectByMap3(m));  
        if(level.equals("红色")){
        	return "village/safety-system/control-listmenu";
        }else{
        	return "village/safety-system/control-listmenu1";
        }  
    }

    /**
     * 安全风险管控
     */
    @RequestMapping(value = "safety-system/statistics-list")
    public String statisticsList(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap2(m);
        
        List<Library> ll = libraryMapper.selectLibraryList(1);//行业
        
        String[] industrys = new String[ll.size()];
        Integer[] gg1 = new Integer[ll.size()];//红
        Integer[] gg2 = new Integer[ll.size()];//橙
        Integer[] gg3 = new Integer[ll.size()];//黄
        Integer[] gg4 = new Integer[ll.size()];//蓝
        for(int i=0;i<ll.size();i++) {
            industrys[i] = ll.get(i).getName();
            gg1[i] = 0;
            gg2[i] = 0;
            gg3[i] = 0;
            gg4[i] = 0;
        }
        
        Integer[] flag1 = new Integer[]{0,0,0,0};//部位 数值顺序对应 红橙黄蓝
        for(Map<String, Object> l : list) {
            String level = "";
            if(null != l.get("level")) {
                level = (String)l.get("level");
            }
            setcompany_manualCount(level, flag1);
            String industry = null;
            if(null != l.get("industry")) {
                industry = l.get("industry").toString();
            }
            if(null != industry && !"".equals(industry)) {
                for(int i=0;i<industrys.length;i++) {
                    if(industry.equals(industrys[i])) {
                        if("红色".equals(level)) {
                            gg1[i] = gg1[i] + 1;
                        } else if("橙色".equals(level)) {
                            gg2[i] = gg2[i] + 1;
                        } else if("黄色".equals(level)) {
                            gg3[i] = gg3[i] + 1;
                        } else if("蓝色".equals(level)) {
                            gg4[i] = gg4[i] + 1;
                        }
                        break;
                    }
                }
            }
        }
        model.addAttribute("flag1", flag1);
        model.addAttribute("industrys", gson.toJson(industrys));
        model.addAttribute("gg1", gson.toJson(gg1));
        model.addAttribute("gg2", gson.toJson(gg2));
        model.addAttribute("gg3", gson.toJson(gg3));
        model.addAttribute("gg4", gson.toJson(gg4));
        return "village/safety-system/statistics-list";
    }
    
    void setcompany_manualCount(String level, Integer[] arr) {
        if("红色".equals(level)) {
            arr[0] = arr[0] + 1;
        } else if("橙色".equals(level)) {
            arr[1] = arr[1] + 1;
        } else if("黄色".equals(level)) {
            arr[2] = arr[2] + 1;
        } else if("蓝色".equals(level)) {
            arr[3] = arr[3] + 1;
        }
    }
    
    
    
    /**
     * 领导关注系统 隐患汇总 cId 企业id
     */
    @RequestMapping(value = "danger-collect-guanzhu")
    public String modelLists(HttpServletRequest request, Integer industryType, Integer townId, Integer villageId, String companyName, Model model, String startTime, String endTime , Integer isZhongDa) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        m.put("townId", townId);
        m.put("villageId", villageId);
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        m.put("industryType", industryType);
        m.put("isZhongDa", isZhongDa);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerCollectByCompany(m);
        if (user.getUserType() == 6) {
            m.put("districtId", user.getId());
        }
        // userId, companyName,
        // 企业自查未整改, 已整改, 严重未整改,严重已整改
        // 行政执法未整改, 已整改, 严重未整改,严重已整改
        // 委托检查未整改, 已整改, 严重未整改,严重已整改
        // 行政检查未整改, 已整改, 严重未整改,严重已整改
        // Object[] a = new Object[18];
        List<Object[]> ll = new ArrayList<Object[]>();
        for (Map<String, Object> l : list) {
            Integer userId = Integer.valueOf(l.get("userId").toString());
            Integer c = Integer.valueOf(l.get("c").toString());// 数量
            Integer flag = Integer.valueOf(l.get("flag").toString());// 1 自查 2执法 3委托5检查
            Integer status = Integer.valueOf(l.get("status").toString());// 2未改 3已改
            Integer d = Integer.valueOf(l.get("d").toString());// 0 一般隐患 1重大隐患
            Object[] a = null;
            Boolean has = false;
            for (Object[] o : ll) {
                if (((Integer) o[0]).compareTo(userId) == 0) {
                    a = o;
                    has = true;
                    break;
                }
            }
            if (null == a) {
                String name = (String) l.get("name");// 企业名称
                a = new Object[]{userId, name, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            }
            //处理数据
            int index = (flag -1) * 4 + 2;
            if(status == 2) {
                a[index] = (Integer)a[index] + c;
            } else {
                a[index + 1] = (Integer)a[index + 1] + c;
            }
            //重大隐患
            if(d == 1) {
                if(status == 2) {
                    a[index + 2] = (Integer)a[index + 2] + c;
                } else {
                    a[index + 3] = (Integer)a[index + 3] + c;
                } 
            }else{
                if(isZhongDa == 2){
                    continue;
                }
            }
            if(!has) {
                ll.add(a);
            }
        }
        model.addAttribute("m", m);
        model.addAttribute("list", ll);
        
        m.put("d", 1);
        Integer d = tCheckItemMapper.selectDangerIndexListCount(m);
        List<Map<String, Object>> lll = tCheckItemMapper.selectDangerCollectGroup(m);
        model.addAttribute("groupIndu", lll);
        model.addAttribute("d", d);
        model.addAttribute("libL", libraryMapper.selectLibraryList(1));// 行业
        return "village/danger/danger-collect-guanzhu";
    }
    
    /**
     * 一周监管动态
     */
    @RequestMapping("notice-list")
    public String noticeList(HttpServletRequest request,Model model) {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("districtId", user.getId());
        model.addAttribute("list", pushMessMapper.selectNotice(m));
        return "village/notice-list";
    }
    
    /**
     * 公告详情
     */
    @RequestMapping(value = "notice/notice-show")
    public String noticeShow(Model model, Integer id) throws Exception {
        Notice n = noticeMapper.selectByPrimaryKey(id);
        model.addAttribute("n", n);
        return "village/notice-show";
    }
    
    /**
     * 下载资料
     */
    @RequestMapping(value = "download")
    public void hedownload(String filename, String fileurl, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        File realPath = new File(request.getServletContext().getRealPath("/"), fileurl.replace("/smaq", ""));
        if(!realPath.exists()) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("该文件不存在");
            out.flush();
            out.close();
            return;
        }
        
        filename = URLEncoder.encode(new String(filename.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8");
        response.setContentType(request.getServletContext().getMimeType(filename));
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        
        InputStream in = new FileInputStream(realPath);
        OutputStream out = response.getOutputStream();
        try {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } finally {
            in.close();
            out.close();
        }
    }
    
    /**
     * 资源导入持证上岗人员名单
     * 
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "importSpersonExcel", produces = "text/html;charset=utf-8")
    public void importSpersonExcel(Model model, Integer flag,HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        userService.spersonImportSave(result, flag, file);
        writeResponse(result, response);//该方法调用如下
    }
    
    /**
     *  为response提供Json格式的返回数据
     */
    public void writeResponse(Object obj, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");//设置编码
            String str = JSON.toJSONString(obj);
            PrintWriter writer = response.getWriter();
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO 添加检查模版
     * 添加检查模版 ==> 就是要进行数据的传递
     * 1. 根据总公司的id查询所有的部门
     * 2. 根据 总Id 和部门查询所有的岗位
     *
     */
    @RequestMapping(value = "addCheckModel")
    public String addCheckModel( Model model, HttpServletRequest request){
        User user = getLoginUser(request);

        /**
         * 里面包含部门的id  部门的信息部门的等级
         */
        List<Map<Object, Object>> maps = zzjgDepartmentMapper.selectByUid(user.getId());

        // 获取所有的部门 使用list集合
        List<String> names = new ArrayList<>();
        for (Map<Object, Object> map : maps) {
            if(1==map.get("level")){ //表示是一级目录
                String name = (String) map.get("name");
                names.add(name);

            }
        }

        Map<String,Object> map = new HashMap<>();

        for (String name : names) {
            List<String> list = aCompanyManualMapper.selectDangerAndManual(user.getId(), name);
            map.put(name,list );
        }

        model.addAttribute("map", map);

        return "company/checkModel/model-add";
    }

}
