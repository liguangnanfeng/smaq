

package com.spring.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.*;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.cgf.CheckSaveReqDTO;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.util.ConstantsUtil;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.OutPrintUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jnlp.IntegrationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author CGF
 * @version V1.0
 * @Title: VillageController
 * @Description: TODO(village : / ' vɪlɪdʒ / 村庄)
 * @date 2017年7月21日 下午12:42:42
 */

@Controller
@RequestMapping("/village")
@Transactional
public class VillageController extends BaseController {
    /**
     * 序列化编号
     */
    private static final long serialVersionUID = 3L;
    @Autowired
    private UserService userService;
    @Autowired
    private CgfService cgfService;

    /**
     * 查询风险点
     */
    @Autowired
    private ICheckManual checkManual;


    /**
     * 政府登录
     */
    @RequestMapping(value = "")
    public String index(HttpServletRequest request) throws Exception {
        String server = request.getServerName();
        if (server.startsWith("smaq.safety365.com.cn")) {
            return "redirect:/fore/login-smaq";
        }
        return "redirect:/fore/company";
    }

    /**
     * 政府首页
     */
    @RequestMapping(value = "main")
    public String main(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("name_", villageMapper.selectByPrimaryKey(user.getId()).getName());
        if (user.getUserName().equals("港口")) {//港口特殊账号
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

        String userIds = (String) m.get("userIds");

        CompanyListReqDTO dto = new CompanyListReqDTO();
        dto.setUserIds(userIds);
        List<Integer> count = userService.selectCount(dto, user);

        log.error("countVillage：" + count.toString());

        model.addAttribute("number", "-1".equals(userIds) ? 0 : userIds.split(",").length);//企业数量

        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总

        model.addAttribute("count", count);
        model.addAttribute("mc", monitorMapper.selectCount(m));
        if (user.getUserType() == 10) {
            model.addAttribute("h", tradeMapper.selectByPrimaryKey(user.getId()));
        } else {
            model.addAttribute("v", villageMapper.selectByPrimaryKey(user.getId()));
        }
        model.addAttribute("loginUserId", user.getId());

        //add wz 2019-02-22
        HttpSession session = request.getSession();
        User moveBeforeUser = (User) session.getAttribute("moveBefore");
        if (moveBeforeUser != null) {
            model.addAttribute("moveD", 1);
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
        if (user.getUserType().intValue() == 3) {
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
    public @ResponseBody
    Result companylisttj(HttpServletRequest request) throws Exception {
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
        if (user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            return "town/company/company-add";
        }

        if (user.getUserType().intValue() == 6) {
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
        model.addAttribute("home", home);
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
        model.addAttribute("home", home);
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
                m.put("userId", user.getId());
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

        dto.setIndustry2(utf8Str(dto.getIndustry2()));
        dto.setDlevel(utf8Str(dto.getDlevel()));
        dto.setIndustry(utf8Str(dto.getIndustry()));
        cgfService.selectCompanyWithPage(dto, user, model);

        if (user.getUserType().intValue() == 3) {
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
     * TODO 隐患排查记录 2019/6/5 10:26修改(企业端查询)
     *
     * @param request 请求
     * @param title   标题
     * @param type    未知
     * @param status  状态 1 未检查  2 已检查
     * @param flag    1 自查  4 行政  3 部门抽查
     * @return 该公司所有的检查的记录
     */
    @RequestMapping(value = "check-list-two")
    public String checkListItem(HttpServletRequest request, String title, Integer type, Integer status, Integer flag, Model model) {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userid", user.getId());    //公司id
        m.put("title", title);
        m.put("type", type);
        m.put("status", status);
        m.put("flag", flag);
        // 进行判断,将该公司所有的检查记录进行查询
        if (setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectListByMap(m);

            Integer sum = 0;

            for (int i = 0; i < list.size(); i++) {

                sum += Integer.parseInt(String.valueOf((Long) list.get(i).get("c")));
            }
            model.addAttribute("sum", sum);
            model.addAttribute("list", list);
        }

        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        return "company/danger/check-list";
    }

    /**
     * 检查历史
     * TODO 排查治理记录 隐患排查记录(只需要已经检查过的,没有不合格记录的)
     * user. userType : 管理类型  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     */
    @RequestMapping(value = "check-list")//flag:3 部门抽查
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer townId, Integer villageId,
                               Integer status, Integer flag, Model model,String dmName,Integer button) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();

        if (null == button){
            button = 1;
        }

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
        Set set = new LinkedHashSet();
        if(Objects.equals("",dmName)){

        }else{
            m.put("dmName",dmName);
        }
        // 进行判断
        if (setUserId(user, m)) {
            System.out.println(m);
            clearVillageTown(m);
            List<Map<String, Object>> list = null;

            if (button == 1){
                list = tCheckMapper.selectList(m);
            }else if (button != 1) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String startTime = df.format(new Date().getTime()-15*24*60*60*1000);

                Date startTime1 = df.parse(startTime);
                Date endTime = new Date();
                m.put("startTime",startTime);
                m.put("endTime",endTime);
                m.put("type",2);
                list = tCheckMapper.findSelectList(m);
            }

            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {

                DynamicParameter<String, Object> id = tCheckMapper.selectCompany((Integer) list.get(i).get("id"));
                list.get(i).put("listM",id);
                sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));
            }

            Integer number1 = tCheckMapper.findDataCounts(user.getId(),flag);
            Integer number2 = tCheckMapper.findDataCountSum(user.getId(),1,flag);
            Integer number3 = tCheckMapper.findDataCountSum(user.getId(),2,flag);
            Integer number4 = tCheckMapper.findDataCountSum(user.getId(),3,flag);

            if (null == number1 || number1 == 0){
                model.addAttribute("sum",0); // 总条数
            }else if (null != number1 || number1 != 0){
                model.addAttribute("sum",number1); // 总条数
            }

            if (null == number2 || number2 == 0){
                model.addAttribute("sum1",0); // 合格数据
            }else if (null != number2 || number2 != 0){
                model.addAttribute("sum1",number2); // 合格数据
            }

            if (null == number3 || number3 == 0){
                model.addAttribute("sum2",0); // 不合格数据
            }else if (null != number3 || number3 != 0){
                model.addAttribute("sum2",number3); // 不合格数据
            }

            if (null == number4 || number4 == 0){
                model.addAttribute("sum3",0); // 复查数据
            }else if (null != number4 || number4 != 0){
                model.addAttribute("sum3",number4); // 复查数据
            }
            model.addAttribute("list", list);
            m.put("dmName",null);
            List<Map<String, Object>> list2 = tCheckMapper.selectList(m);
            for (int i = 0; i < list2.size(); i++) {
                set.add(list2.get(i).get("depart"));
            }

        }
        model.addAttribute("set",set);
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        model.addAttribute("dmName",dmName);
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
        if (setUserId(user, m)) {
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
        if (user.getUserType().intValue() == 5) {
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
        if (StringUtils.isNotEmpty(tc.getIndustry2())) {
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
     * loadMap
     * TODO 显示小程序端检查记录的检查定位
     */
    @RequestMapping("loadMap")
    public String loadMap(Model model, String latitude, String longitude, Integer checkId, HttpServletRequest request) {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("areaRange", company.getAreaRange());
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        return "company/danger/xcxMap";
    }


    /**
     * TODO 查询检查表数据
     */
    @RequestMapping(value = "plan-next2")//生成检查表，modify by zhangcl 2018.10.27
    public String checkNext2(Integer id, Integer type, Model model, HttpServletRequest request) throws Exception {
        //log.error("checkNext checkid : "+id);
        User user = getLoginUser(request);

        TCheck tc = tCheckMapper.selectCheckBymodelIdAndStatus(id, 0);

        //log.error("tCheckMapper检查表信息:"+tc.toString());
        type = tc.getType();// add wz 190110

        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(tc.getId());
        //log.error("tCheckPartMapper条目信息:"+partL.toString());
        for (TCheckPart a : partL) {
            String levels = a.getLevels();
            //log.error("levels:"+levels);
        }

        model.addAttribute("check", tc);
        model.addAttribute("partL", partL);
        //model.addAttribute("itemL", tCheckItemMapper.selectByCheckId(id));
        List<Map<String, Object>> iteml = tCheckItemMapper.selectByCheckId(tc.getId());
        //log.error("tCheckItemMapper条目结果信息:"+iteml.toString());

        if (type != null && type == 9) {
            for (Map<String, Object> a : iteml) {
                //log.error("checkNext:"+1);
                Integer[] ids = new Integer[1];
                ids[0] = (Integer) a.get("levelId");
                //log.error("ids:"+ids[0]);
                //log.error("a:"+a.toString());
                List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
                String dangertype = "";
                String factors = "";
                String measures = "";
                String level1 = "";
                String level2 = "";
                String level3 = "";
                for (ACompanyManual aa : rets) {
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
                a.put("dangerType", dangertype);
                a.put("factors", factors);
                a.put("measures", measures);
                a.put("level1", level1);
                a.put("level2", level2);
                a.put("level3", level3);
                log.error("level1/2/3 : " + level1 + "/" + level2 + "/" + level3);
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
        return "village/danger/plan-next2";
    }

    /**
     * TODO 生成检查表数据
     *
     * @param id      model表的id
     * @param type
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "plan-next")//生成检查表，modify by zhangcl 2018.10.27
    public String checkNext(Integer id, Integer type, Model model, HttpServletRequest request) throws Exception {
        //log.error("checkNext checkid : "+id);
        User user = getLoginUser(request);

        CheckItemS checkItemByModelId = saveMessageService.findCheckItemByModelId(id);
        if (null == checkItemByModelId) {
            return "village/danger/plan-next";
        }

        TCheck tc = tCheckMapper.selectByPrimaryKey(checkItemByModelId.getCheckId());

        //log.error("tCheckMapper检查表信息:"+tc.toString());
        type = tc.getType();// add wz 190110

        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(checkItemByModelId.getCheckId());
        //log.error("tCheckPartMapper条目信息:"+partL.toString());

        model.addAttribute("check", tc);
        model.addAttribute("partL", partL);
        //model.addAttribute("itemL", tCheckItemMapper.selectByCheckId(id));
        List<Map<String, Object>> iteml = tCheckItemMapper.selectByCheckId(checkItemByModelId.getCheckId());
        //log.error("tCheckItemMapper条目结果信息:"+iteml.toString());

        if (type != null && type == 9) {
            for (Map<String, Object> a : iteml) {
                //log.error("checkNext:"+1);
                Integer[] ids = new Integer[1];
                ids[0] = (Integer) a.get("levelId");
                //log.error("ids:"+ids[0]);
                //log.error("a:"+a.toString());
                List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
                String dangertype = "";
                String factors = "";
                String measures = "";
                String level1 = "";
                String level2 = "";
                String level3 = "";
                for (ACompanyManual aa : rets) {
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
                a.put("dangerType", dangertype);
                a.put("factors", factors);
                a.put("measures", measures);
                a.put("level1", level1);
                a.put("level2", level2);
                a.put("level3", level3);
                log.error("level1/2/3 : " + level1 + "/" + level2 + "/" + level3);
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

        Set<String> set = new LinkedHashSet<String>();

        for (Map<String, Object> stringObjectMap : iteml) {

            String levels = (String) stringObjectMap.get("levels");
            if(levels.contains("/")){
                String[] split = levels.split("/");
                set.add(split[1]);
            }
        }
        //String departName = "";
        /*for (String s : set) {
            departName+=s+"/";
        }*/
        Object[] objects =  set.toArray();

       /* String departName = Arrays.toString(objects);*/
        String departName = null;
        for (int i = 0; i < objects.length; i++) {
            departName = (String) objects[i];
        }

        model.addAttribute("departName",departName);
        model.addAttribute("jcL", officialsMapper.selectList(m));// 执法人员
        log.error("plan-next：" + 6);
        if (type == 9) {
            return "village/danger/plan-next1";
        } else {
            return "village/danger/plan-next";
        }

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
     * TODO 检查表隐患 整改详情,
     * 保存检查记录的时候，会走这个一下，但是没有进行保存所有没有发送
     * 但是在查询记录的时候，点击一个整改详情的时候，会
     */
    @RequestMapping(value = "check-rectification")
    public String checkRectification(Integer id, Model model, Integer flag, Integer number,HttpServletRequest request) throws Exception {

        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        Integer type = tc.getType();

        User user = getLoginUser(request);
        // TODO 这里只保存一条数据,并返回到前端
        List<TRectification> list = tRectificationMapper.selectAlls(id);


       if(null != list && list.size() > 0){
           model.addAttribute("rectification", list.get(0));
       }

        if((null != list && list.size() > 0)){
            model.addAttribute("rectification", list.get(0));
        }

        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);

        List<Map<String, Object>> iteml = tCheckItemMapper.selectDangerByCheckId(id, null);
        // 判断他是什么类型的然后再添加数据
        Integer industryType = tc.getIndustryType();
        for (int i = 0; i < iteml.size(); i++) {
            Integer levelId = (Integer) iteml.get(i).get("levelId");
                if(null!=levelId){
                    String DangerFlag="";
                    if(flag==1&&!Objects.equals(user.getUserName(),tc.getDepart())){
                        if (null == levelId || levelId == 0){
                            continue;
                        }else if (null != levelId && levelId != 0){
                            DangerFlag = aCompanyManualMapper.selectByPrimaryKey(levelId).getFlag();
                        }
                    }else{
                        DangerFlag = queryDangerFlag(levelId, industryType);
                    }
                    iteml.get(i).put("dangerFlag",DangerFlag);
                }
        }
        // 这个是没有用的
        if (type != null && type == 9) {
            for (Map<String, Object> a : iteml) {
                Integer[] ids = new Integer[1];
                ids[0] = (Integer) a.get("levelId");
                List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
                String dangertype = "";
                String factors = "";
                String measures = "";
                String level1 = "";
                String level2 = "";
                String level3 = "";
                for (ACompanyManual aa : rets) {
                    dangertype = aa.getType();
                    factors = aa.getFactors();
                    measures = aa.getMeasures();
                    level1 = aa.getLevel1();
                    level2 = aa.getLevel2();
                    level3 = aa.getLevel3();
                    break;
                }
                a.put("dangerType", dangertype);
                a.put("factors", factors);
                a.put("measures", measures);
                a.put("level1", level1);
                a.put("level2", level2);
                a.put("level3", level3);
            }
        }
        model.addAttribute("itemL", iteml);

        model.addAttribute("number", number);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
        model.addAttribute("flag", flag);
        model.addAttribute("serList", gson.toJson(tItemSeriousMapper.selectbylid(null)));
        model.addAttribute("listM", tCheckMapper.selectCompany(id));
        return "village/danger/opinion-detail";

    }


    /**
     *  从id 获取这条记录的风险等级
     * @param levelId     level_tbl/danger_manual_tbl的id
     * @param industryType 1/2  基础/现场
     * @return 风险等级
     */
    private  String  queryDangerFlag(int levelId,int industryType){
        String level = "";

        if(industryType==1){
            Integer flag = tLevelMapper.selectByPrimaryKey(levelId).getFlag();
            level=flag+"";
        }else if(industryType==2) {
             level = aDangerManualMapper.selectByPrimaryKey(levelId).getFlag();
        }
        return level;
    }

    /**
     * TODO 整改指令书
     * 获取到了整改指令书
     * 都不传递就是 现场检查记录
     * 1 责令限期整改意见书
     * 2 整改意见复查书
     * 8 现场检查记录
     */
    @RequestMapping(value = "check-document")
    public String checkDocument(HttpServletRequest request, Integer checkId, Integer flag, Model model, TRectification tr) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (null == flag) {
            flag = 8;// 现场检查记录
        }
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("checkId", checkId);
        m.put("flag", flag);
        TCheckDocument doc = tCheckDocumentMapper.selectByCheckId(m);
        TCheck check = tCheckMapper.selectByPrimaryKey(checkId);
        model.addAttribute("timenow", new Date());
        int count = tRecheckMapper.selectByCheckId(checkId).size();
        model.addAttribute("count", count);
        if (null == doc) {// 第一次
            tr = tRectificationMapper.selectByCheckId(checkId);
            model.addAttribute("check", check);
            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
            model.addAttribute("itemL1", tCheckItemMapper.selectDangerByCheckId(checkId, 1));
            model.addAttribute("rectification", tr);
        } else {

            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
            model.addAttribute("itemL1", tCheckItemMapper.selectDangerByCheckId(checkId, 1));
            model.addAttribute("document", doc);
            System.out.println("village/danger/opinion-detail2");
            return "village/danger/opinion-detail2";
        }
        List<TCheckItem> tCheckItems = tCheckItemMapper.selectItemByCheckId(checkId);

        model.addAttribute("is_fu",0);
        for (TCheckItem tCheckItem : tCheckItems) {
            if(tCheckItem.getStatus()==3){
                model.addAttribute("is_fu",1);
                break;
            }else{
                if(tCheckItem.getRecheckFile()!=null&&!"".equals(tCheckItem.getRecheckFile())){
                    model.addAttribute("is_fu",1);
                    break;
                }
            }
        }
        TRectification tRectification = tRectificationMapper.selectByCheckId(checkId);
        if (null != tRectification) {

        } else {
            TRectification tRectification1 = new TRectification();
            tRectification1.setDeadline(new Date());
            tRectification1.setPlanTime(new Date());
        }
        model.addAttribute("flag2", check.getFlag());
        model.addAttribute("check", check);
        model.addAttribute("checkId", checkId);
        model.addAttribute("userId", check.getUserId());
        model.addAttribute("rectification", tRectification);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
        model.addAttribute("serList", gson.toJson(tItemSeriousMapper.selectbylid(null)));

        // 判断是否整改复查过
        if(null==tRectification){
            model.addAttribute("is_re",0);
        }else{
            model.addAttribute("is_re",1);
        }
        for (TCheckItem tCheckItem : tCheckItems) {

            if (null != tCheckItem.getDeadline()) {
                model.addAttribute("deadline", simpleDateFormat.format(tCheckItem.getDeadline()).toString());
            }
            if (null != tCheckItem.getRecheckTime()) {
                model.addAttribute("planTime", simpleDateFormat.format(tCheckItem.getRecheckTime()).toString());
            }
            break;

        }
        if(check.getFlag()==2){
            // 行政检查
            System.out.println("village/danger/opinion-add" + flag + check.getFlag());
            return "village/danger/opinion-add" + flag + check.getFlag();
        }else {
            // 部门抽查
            if(flag==8){
                flag=1;
            }
            System.out.println("village/danger/opinion-add" + flag + check.getFlag());
            return "village/danger/opinion-add" + flag + check.getFlag();
        }

    }

    /**
     * TODO  @return 整改指令书保存 在保存时间点
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
     *
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
    public String checkDangerList(Integer flag, String title, Integer c, String companyName, Model model,
                                  HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);
        m.put("title", title);
        m.put("companyName", companyName);
        if (null != c) {
            m.put("c", c);
        }
        if (setUserId(user, m)) {
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
        if (user.getUserType().intValue() == 5) {
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
    public @ResponseBody
    Result getVillageByTown(Model model, Integer townId) {
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
        if (null != d.getCheckId()) {
            model.addAttribute("check", tCheckMapper.selectByPrimaryKey(d.getCheckId()));
            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(d.getCheckId(), null));
            model.addAttribute("itemL1", tCheckItemMapper.selectDangerByCheckId(d.getCheckId(), 1));
            model.addAttribute("rectification", tRectificationMapper.selectByCheckId(d.getCheckId()));
        }
        model.addAttribute("document", tCheckDocumentMapper.selectByPrimaryKey(id));
        return "village/danger/opinion-detail2";
    }

    /**
     * TODO 隐患汇总 cId 企业id  隐患排查记录
     */
    @RequestMapping(value = "danger-index-list")
    public String modelList(HttpServletRequest request, Model model, Integer flag, Integer cId, Integer d,
                            String partName, String companyName, Integer status,
                            String startTime, String endTime) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        //企业自查显示——未整改	wz 18/12/20
        if (status == null) {
            status = 2;
        } else if (status == 1) {
            status = null;
        }
        m.put("flag", flag);
        m.put("status", status);
        m.put("d", d);
        m.put("companyName", companyName);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        if (null != cId) {
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

    /**
     * TODO 隐患治理记录, 整改不合格的 新建立的页面
     * 有三种情况,企业自查 行政检查  部门抽查
     *
     * @param request 请求
     * @param flag    方式
     * @param status  状态
     * @return
     */
    @RequestMapping(value = "hidden-danger-list")
    public String hiddenDangerList(HttpServletRequest request, Model model, Integer flag, Integer status,Integer breaken) {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("userId", user.getId());

        if (null == breaken){
            breaken = 1;
        }else {
            breaken = 2;
        }

        List<Map> list = new ArrayList<>();
        if (flag == 1) {

            list = tCheckItemMapper.selectListBystatus(user.getId(), flag, breaken);

            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
                String level = (String) map.get("level");
                if (null != level && "红色".equals(level)) {
                    map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                }
                map.put("realTimeStr", format);

                if("全公司".equals(map.get("depart"))){
                    Integer checkId = (Integer) map.get("checkId");
                    map.put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
                    Integer industryType = (Integer) map.get("industryType");
                    if(null!=industryType&&1==industryType){
                        map.put("level2",tLevelMapper.selectByPrimaryKey((Integer)map.get("levelId")).getLevel2());
                    }else if (null!=map.get("industryType")&&2==map.get("industryType")){
                        map.put("level2",aDangerManualMapper.selectByPrimaryKey((Integer)map.get("levelId")).getLevel2());
                    }
                }

                if(null==map.get("fjgkfzr")||"".equals(map.get("fjgkfzr"))){
                    String name = "";
                    TCheck tc = tCheckMapper.selectByPrimaryKey((Integer) map.get("checkId"));
                    // 表示没有被检查人员 根据部门名称获取这个部门的被检查人员然后随便抓一个
                    List<Integer> integers = tCheckItemMapper.selectLevelIdByCheckId((Integer)map.get("checkItemId"));
                    if (null != integers && integers.size() > 0) {
                        // 这里进行名称的获取,进行全部循环,获取数据的方式,在数据库中进行查询
                        List<String> list1 = new ArrayList<String>();
                        for (Integer integer : integers) {
                            if (null != integer) {
                                ACompanyManual aCompanyManual = aCompanyManualMapper.selectByPrimaryKey(integer);
                                if (null != aCompanyManual && null != aCompanyManual.getFjgkfzr()) {
                                    list1.add(aCompanyManual.getFjgkfzr());
                                }
                            }
                        }
                        if (list1.size() == 0) {
                            name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                            tc.setCheckCompany(name);
                        } else {
                            name = list1.get(0);
                            tc.setCheckCompany(name);
                        }
                    } else {
                        name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                        tc.setCheckCompany(name);
                    }
                    map.put("fjgkfzr", name);
                }
            }
        } else if (flag == 2) {
            // 表示的是行政检查
            list = tCheckItemMapper.selectXZListBystatus(user.getId(), flag, breaken);
            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
                String levelId ="";
                if(null!=map.get("industryType")&&1==map.get("industryType")){
                    Integer levelId1 = (Integer) map.get("levelId");
                    if(null!=levelId1){
                        levelId=  tLevelMapper.selectByPrimaryKey(levelId1).getLevel2();
                    }
                }else if (null!=map.get("industryType")&&2==map.get("industryType")){
                    if (null == (Integer) map.get("levelId")){
                        continue;
                    }else {
                        levelId = aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2();
                    }
                }
                if(StringUtils.isBlank(levelId)){
                    map.put("level2",levelId);
                }

                map.put("realTimeStr", format);
                map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                // 获取
            }
        } else if (flag == 3) {
            // 表示的是部门抽查
            list = tCheckItemMapper.selectBMCCListBystatus(user.getId(), flag, breaken);
            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
              //  String level = (String) map.get("level");
                if(null!=map.get("industryType")&&1==map.get("industryType")){
                    map.put("level2",tLevelMapper.selectByPrimaryKey((Integer)map.get("levelId")).getLevel2());
                }else if (null!=map.get("industryType")&&2==map.get("industryType")){
                    map.put("level2",aDangerManualMapper.selectByPrimaryKey((Integer)map.get("levelId")).getLevel2());
                }
                map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                map.put("realTimeStr", format);
            }
        }
        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("host", host);
        model.addAttribute("list", list);
        return "company/danger/hidden-danger-list";
    }

    /**
     * 保存治理投入金额
     *
     * @return
     */
    @RequestMapping(value = "save-money")
    public @ResponseBody
    Result saveMoney(Integer itemId, Integer money) {
        try {
            TCheckItem tCheckItem = new TCheckItem();
            BigDecimal big = new BigDecimal(money);
            tCheckItem.setId(itemId);
            tCheckItem.setMoney(big);
            tCheckItemMapper.updateByPrimaryKeySelective(tCheckItem);
            Result result = new ResultImpl();
            result.setStatus("0");
            result.setMess("保存成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new ResultImpl();
            result.setStatus("1");
            result.setMess("保存失败");
            return result;
        }
    }

    /**
     * @return ！！！
     */
    @RequestMapping(value = "process-see")
    public String processSee(Model model, String url, HttpServletRequest request) throws UnknownHostException {
        String url3 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + url;
        /*String url3 = request.getScheme() + "://" + request.getServerName() + url;*/
        model.addAttribute("list", url3);
        return "company/process/process-see";

    }

    /**
     * TODO 隐患治理记录,只要整改合格的
     */
    @RequestMapping(value = "recheck-list")
    public String modelList(HttpServletRequest request, Model model, Integer flag, Integer status) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        // 只判断是否以检查出过合格的

        List<Map> list = null;

        if (null != status && 1 == status) { //表示的是要查询整改合格的
            list = tCheckItemMapper.selectRecheckListByRecheckStatus(user.getId(), status);
        } else {
            list = tCheckItemMapper.selectRecheckList(user.getId());
        }

        model.addAttribute("list", list);
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
            int index = (flag - 1) * 4 + 2;
            if (status == 2) {
                a[index] = (Integer) a[index] + c;
            } else {
                a[index + 1] = (Integer) a[index + 1] + c;
            }
            //重大隐患
            if (d == 1) {
                if (status == 2) {
                    a[index + 2] = (Integer) a[index + 2] + c;
                } else {
                    a[index + 3] = (Integer) a[index + 3] + c;
                }
            }
            if (!has) {
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
            int index = (flag - 1) * 4 + 2;
            if (status == 2) {
                a[index] = (Integer) a[index] + c;
            } else {
                a[index + 1] = (Integer) a[index + 1] + c;
            }
            //重大隐患
            if (d == 1) {
                if (status == 2) {
                    a[index + 2] = (Integer) a[index + 2] + c;
                } else {
                    a[index + 3] = (Integer) a[index + 3] + c;
                }
            }
            if (!has) {
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
     * TODO (隐患数据分析)设置检查表
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
                                Integer cisDanger, Integer chemic, String dlevel, Integer isKey, String doubleDanger, Integer danger) throws Exception {
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
        if (null != dlevel) {
            dlevel = utf8Str(dlevel);
        }
        m.put("dlevel", dlevel);
        if (user.getUserType() == 10) {
            m.put("isTradeKey", isKey);
        } else {
            m.put("isKey", isKey);
        }
        m.put("doubleDanger", doubleDanger);
        m.put("danger", danger);
        List<DynamicParameter<String, Object>> list = new ArrayList<DynamicParameter<String, Object>>();
        if (cisDanger != null) {//重大危险源
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
            for (DynamicParameter<String, Object> l : list) {
                String dangerMaterial = l.getString("dangerMaterial");
                if (StringUtils.isNotBlank(dangerMaterial)) {
                    l.put("dangerMaterials", dangerMaterial.split(","));
                }
            }
        } else {
            list = companyMapper.selectCompanyList(m);
        }
        model.addAttribute("list", list);
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("companyName", companyName);
        model.addAttribute("state", state);
        model.addAttribute("isFreeze", isFreeze);
        if (user.getUserType() == 10) {
            model.addAttribute("isTradeKey", isKey);
        } else {
            model.addAttribute("isKey", isKey);
        }
        model.addAttribute("sk", request.getParameter("sk"));

        if (danger != null) {//高危作业
            return "village/warning/company-list-gwzy";
        }
        if (cisDanger != null) {//重大危险源
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
        //setUserId(user, m);
        if (StringUtils.isNotBlank(level)) {
            level = utf8Str(level);
        }
        if(user.getId()==35346){
            model.addAttribute("tradeId",user.getId());
        }
        if(null != companyName) {
            m.put("companyName", companyName);
        }
        if(null!=level) {
            if(level.equals("red")) {
                m.put("level", "红色");
            }else{
                m.put("level", "橙色");
            }
        }
        model.addAttribute("list", aCompanyManualMapper.selectByMap2(m));
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        model.addAttribute("m", m);
        //log.error("2018.12.4 debug 10 zhangcl");
        //log.error("level：" + level.toString());
        if (level.equals("red")) {
            return "village/safety-system/control-list";
        } else {
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
        //setUserId(user, m);
        if (StringUtils.isNotBlank(level)) {
            level = utf8Str(level);
        }
        if(level.equals("red")){
            m.put("level", "红色");
        }else{
            m.put("level", "橙色");
        }
        if(user.getId()==35346){
            m.put("tradeId", user.getId());
        }
        if(null != companyName) {
            m.put("companyName", companyName);
        }
        System.out.println(m);
        //model.addAttribute("list", aCompanyManualMapper.selectByMap3(m));
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap3(m);
        model.addAttribute("list", list);
        //log.error("list："+list.toString());
        //统计隐患总数
        Long totalyh = (long) 0;
        for (int i = 0; i < list.size(); i++) {
            Long num = (Long) list.get(i).get("companyNum");
            totalyh = totalyh + num;
        }
        //log.error("totalyh："+totalyh);
        model.addAttribute("totalyh", totalyh);

        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        model.addAttribute("m", m);
        //log.error("list："+aCompanyManualMapper.selectByMap3(m));
        if (level.equals("red")) {
            return "village/safety-system/control-listmenu";
        } else {
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

        List<Library> ll = libraryMapper.selectLibraryList(1);//查询所有行业

        String[] industrys = new String[ll.size()];
        Integer[] gg1 = new Integer[ll.size()];//红
        Integer[] gg2 = new Integer[ll.size()];//橙
        Integer[] gg3 = new Integer[ll.size()];//黄
        Integer[] gg4 = new Integer[ll.size()];//蓝
        for (int i = 0; i < ll.size(); i++) {
            industrys[i] = ll.get(i).getName();
            gg1[i] = 0;
            gg2[i] = 0;
            gg3[i] = 0;
            gg4[i] = 0;
        }

        Integer[] flag1 = new Integer[]{0, 0, 0, 0};//部位 数值顺序对应 红橙黄蓝
        for (Map<String, Object> l : list) {
            String level = ""; //获取风险等级
            if (null != l.get("level")) {//保证所获取的分险的不为空
                level = (String) l.get("level");
            }
            setcompany_manualCount(level, flag1);
            String industry = null;
            if (null != l.get("industry")) {
                industry = l.get("industry").toString();
            }
            if (null != industry && !"".equals(industry)) {
                for (int i = 0; i < industrys.length; i++) {
                    if (industry.equals(industrys[i])) {
                        if ("红色".equals(level)) {
                            gg1[i] = gg1[i] + 1;
                        } else if ("橙色".equals(level)) {
                            gg2[i] = gg2[i] + 1;
                        } else if ("黄色".equals(level)) {
                            gg3[i] = gg3[i] + 1;
                        } else if ("蓝色".equals(level)) {
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
        if ("红色".equals(level)) {
            arr[0] = arr[0] + 1;
        } else if ("橙色".equals(level)) {
            arr[1] = arr[1] + 1;
        } else if ("黄色".equals(level)) {
            arr[2] = arr[2] + 1;
        } else if ("蓝色".equals(level)) {
            arr[3] = arr[3] + 1;
        }
    }


    /**
     * 领导关注系统 隐患汇总 cId 企业id
     */
    @RequestMapping(value = "danger-collect-guanzhu")
    public String modelLists(HttpServletRequest request, Integer industryType, Integer townId, Integer villageId, String companyName, Model model, String startTime, String endTime, Integer isZhongDa) throws Exception {
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
            int index = (flag - 1) * 4 + 2;
            if (status == 2) {
                a[index] = (Integer) a[index] + c;
            } else {
                a[index + 1] = (Integer) a[index + 1] + c;
            }
            //重大隐患
            if (d == 1) {
                if (status == 2) {
                    a[index + 2] = (Integer) a[index + 2] + c;
                } else {
                    a[index + 3] = (Integer) a[index + 3] + c;
                }
            } else {
                if (isZhongDa == 2) {
                    continue;
                }
            }
            if (!has) {
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
    public String noticeList(HttpServletRequest request, Model model) {
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
        if (!realPath.exists()) {
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
    public void importSpersonExcel(Model model, Integer flag, HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        userService.spersonImportSave(result, flag, file);
        writeResponse(result, response);//该方法调用如下
    }

    /**
     * 为response提供Json格式的返回数据
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
     * TODO PC端标准检查模版
     * 直接获取部门信息(该公司所有的部门,一级目录)和高危风险(暂时没有,做出一个判断)
     */
    @RequestMapping(value = "addCheckModel")
    public String addCheckModel(Model model, HttpServletRequest request, Integer flag) {
        User user = getLoginUser(request);

        /**
         * 里面包含部门的id  部门的信息 部门的等级  获取这个公司对应的所有的部门
         */
        List<Map<Object, Object>> maps = zzjgDepartmentMapper.selectByUid(user.getId());

        // 获取所有的部门 使用list集合
        Map<String, Integer> names = new HashMap<>();
        for (Map<Object, Object> map : maps) {
            if (1 == (Integer) map.get("level")) { //表示是一级目录
                String name = (String) map.get("name");//部门名称
                Integer id = (Integer) map.get("id");//部门id
                names.put(name, id);
            }
        }
        // 查询高危风险
        List<Map> list = checkManual.checkGaoWei(user.getId());
        if (list.size() == 0) {
            list = null;
        }
        model.addAttribute("flag", flag);
        model.addAttribute("danger", list);
        model.addAttribute("map", names);
        return "company/checkModel/model-add2";
    }


    /**
     * TODO model-add.jsp 页面跳转 由(model-addOld改写)
     *
     * @return
     */
    @RequestMapping(value = "getCheckModelBasic")
    public String getCheckModelBasic(Integer flag, Model model) {
        model.addAttribute("flag", flag);
        /* return "company/checkModel/model-add";*/
        model.addAttribute("list", null);
        model.addAttribute("dL", null);
        return "company/checkModel/model-add6";
    }

    /**
     * TODO 跳转中转页面==> 跳转到自定义/标准保存模版
     *
     * @return
     */
    @RequestMapping(value = "model-add4")
    public String modelAdd4(HttpServletRequest request, Model model, String dmname, Integer dmid, Integer checkType,
                            Integer industryType, Integer flag ) {
        model.addAttribute("dmname", dmname);
        model.addAttribute("dmid", dmid);
        model.addAttribute("checkType", checkType);
        model.addAttribute("industryType", industryType);
        model.addAttribute("flag", flag);
        return "company/checkModel/model-add5";
    }

    /**
     * TODO model-add.jsp 获取高危属性的检查
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addCheckModel2")
    public Map addCheckModel2(HttpServletRequest request) {
        User user = getLoginUser(request);

        // 里面包含部门的id  部门的信息 部门的等级  获取这个公司对应的所有的部门
        List<Map<Object, Object>> maps = zzjgDepartmentMapper.selectByUid(user.getId());

        Map<String, Integer> names = new HashMap<>();
        for (Map<Object, Object> map : maps) {
            if (1 == (Integer) map.get("level")) { //表示是一级目录
                String name = (String) map.get("name");//部门名称
                Integer id = (Integer) map.get("id");//部门id
                names.put(name, id);
            }
        }
        // 查询高危风险
        List<Map> list = checkManual.checkGaoWei(user.getId());
        if (list.size() == 0) {
            list = null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("danger", list);
        map.put("map", names);

        return map;
    }

    /**
     * TODO 跳转到添加基础检查的页面显示
     *
     * @return
     */
    @RequestMapping("addCheckModel3")
    public String addCheckModel3(Model model, Integer dmid, String dmname, Integer checkType, Integer industryType) {
        model.addAttribute("dmid", dmid);
        model.addAttribute("dmname", dmname);
        model.addAttribute("checkType", checkType);
        model.addAttribute("industryType", industryType);

        return "company/checkModel/model-add3";

    }

    /**
     * TODO 根据页面进行选择的基础/现场,查询基础/现场对应的部门
     *
     * @param request   请求
     * @param checkType 基础/现场
     * @return 部门名称 id
     */
    @RequestMapping(value = "findItemAll", method = RequestMethod.POST)
    public @ResponseBody
    List findItemAll(HttpServletRequest request, Integer checkType, Model model) {
        User user = getLoginUser(request);
        List<Map<String, Object>> list = new ArrayList<>();
        if (checkType == -1) {
            // 基础检查 保存基础检查的数据
            list = aCompanyManualMapper.findJiChuItem(user.getId(), "基础管理");
        } else if (checkType == -2) {
            // 高危检查
            list = aCompanyManualMapper.findJiChuItem(user.getId(), "现场管理");
        }
        return list;
    }

    /**
     * TODO (基础/现场) 根据公司和部门获取对应岗位
     *
     * @param depId     部门id
     * @param dpName    部门名称
     * @param checkType 基础/现场
     * @param request   请求
     * @return 该部门下面所有的检查项
     */
    @RequestMapping(value = "selectDep")
    @ResponseBody
    public List findStation(Integer depId, String dpName, Integer checkType, HttpServletRequest request) {
        User user = getLoginUser(request);
        List<String> list = new ArrayList<String>();

        if (-2 == checkType) {                                     //现场
            list = aCompanyManualMapper.selectlevel3BydmName(user.getId(), dpName);
        } else if (-1 == checkType) {                                //基础
            list = aCompanyManualMapper.selectlevel3BydmNameAndLevel3(user.getId(), dpName, "基础");
        } else {

        }
        ArrayList linkedList = new ArrayList();
        if (null != list && list.size() > 0) {
            for (String level3 : list) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                List<ACompanyManual> aCompanyManuals = aCompanyManualMapper.selectAllByLevel3(user.getId(), dpName, level3);
                map.put("name", level3);
                map.put("list", aCompanyManuals);

                linkedList.add(map);
            }
        }

        return linkedList;
    }


    /**
     * TODO (基础/现场) 根据公司和部门获取对应岗位
     *
     * @param depId     部门id
     * @param dpName    部门名称
     * @param checkType 基础/现场
     * @param request   请求
     * @return 该部门下面所有的检查项
     */
    @RequestMapping(value = "selectDep3")
    @ResponseBody
    public List selectDep3(Integer depId, String dpName, Integer checkType, HttpServletRequest request) {
        User user = getLoginUser(request);
        List<String> list = new ArrayList<String>();

        if (-2 == checkType) {                                     //现场
            /*list = aCompanyManualMapper.selectlevel3BydmName(user.getId(), dpName);*/
            list = aDangerManualMapper.selectAll();
        } else if (-1 == checkType) {                                //基础
            /* list = aCompanyManualMapper.selectlevel3BydmNameAndLevel3(user.getId(), dpName, "基础");*/
            list = tLevelMapper.selectAllDep3();
        } else {

        }
        ArrayList linkedList = new ArrayList();
        if (null != list && list.size() > 0) {
            for (String level3 : list) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                /*List<ACompanyManual> aCompanyManuals = aCompanyManualMapper.selectAllByLevel3(user.getId(), dpName, level3);*/
                if (-2 == checkType) {                                     //现场
                    List<ADangerManual> aDangerManuals = aDangerManualMapper.selectAllDep3();
                    map.put("name", level3);
                    map.put("list", aDangerManuals);
                } else if (-1 == checkType) {                                //基础
                    List<TLevel> levels = tLevelMapper.selectAll();
                    map.put("name", level3);
                    map.put("list", levels);
                }
                linkedList.add(map);
            }
        }
        return linkedList;
    }


    /*
     * TODO 根据 checkType 查询所有的 level2 的信息(废弃)
     *
     */
    @RequestMapping(value = "select-all-level1")
    public String selectAllLevel1(Integer checkType, String industry,Integer lxType, HttpServletRequest request, Model model, String tableName, Integer flag) {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isNotBlank(industry)) {
            industry = utf8Str(industry);
        }
        if (StringUtils.isBlank(industry)) {
            industry = company.getIndustry();
        }
        model.addAttribute("company", company);
        model.addAttribute("checkType", checkType);
        model.addAttribute("industry", industry);
        model.addAttribute("flag", flag);
        model.addAttribute("tableName", tableName);
        model.addAttribute("lxType", lxType);


        StringBuffer sb = new StringBuffer();

        String[] str = company.getDangers().split(",");

        if (null != str &&  str.length != 0){

            for (int i = 0; i < str.length; i++) {
                if (i == str.length-1){
                    sb.append("'").append(str[i]).append("'");
                }else {
                    sb.append("'").append(str[i]).append("'").append(",");
                }
            }
            if (null != industry && industry != ""){
                sb.append(",").append("'").append(industry).append("'");
            }
        }
        // 根据 checkType 查询对应的 level1 信息
        if (-2 == checkType) {
            List<ADangerManual> dL = aDangerManualMapper.selectByIndustryAll(sb.toString());
            Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
            for (ADangerManual ad : dL) {
                String l1 = ad.getLevel1();
                String l2 = ad.getLevel2();
                if (null == list.get(l1)) {
                    list.put(l1, new LinkedHashSet<String>());
                }
                Set<String> s = list.get(l1);
                s.add(l2);
            }
            model.addAttribute("list", list);
            model.addAttribute("dL", dL);

        } else if (-1 == checkType) {
            List<TLevel> dL = tLevelMapper.selectAll();
            Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
            for (TLevel ad : dL) {
                String l1 = ad.getLevel1();
                String l2 = ad.getLevel2();
                if (null == list.get(l1)) {
                    list.put(l1, new LinkedHashSet<String>());
                }
                Set<String> s = list.get(l1);
                s.add(l2);
            }
            model.addAttribute("list", list);
            model.addAttribute("dL", dL);
        }
        return "company/checkModel/model-add6";
    }

    /*
     * TODO 根据 checkType 和 level2  查询所有的 level3 的信息(废弃)
     */
    @RequestMapping(value = "select-all-level3")
    @ResponseBody
    public List<Map<String, Object>> selectAllLevel3(Integer checkType, String level2, HttpServletRequest request) {
        User user = getLoginUser(request);
        List<Map<String, Object>> list = null;
        // 根据 checkType 查询对应的 level1 信息
        if (-2 == checkType) {
            list = aDangerManualMapper.selectLevel3("现场管理", level2);
        } else if (-1 == checkType) {
            list = tLevelMapper.selectLevel3("基础管理", level2);
        }
        return list;
    }


    /*
     * TODO 根据 checkType , level2 , level3  查询所有的 Measures 的信息 (废弃)
     */
    @RequestMapping(value = "select-all-measures")
    @ResponseBody
    public List<Map<String, Object>> selectAllMeasures(Integer checkType, String level2, String level3, HttpServletRequest request) {
        User user = getLoginUser(request);
        List<Map<String, Object>> list = null;
        // 根据 checkType 查询对应的 level1 信息
        if (-2 == checkType) {
            list = aDangerManualMapper.selectMeasures("现场管理", level2, level3);
        } else if (-1 == checkType) {
            list = tLevelMapper.selectMeasures("基础管理", level2, level3);
        }
        return list;
    }


    /**
     * TODO (基础/现场) 根据公司和部门获取对应岗位
     * 出现的问题就是,现在使用部门检查/行政检查 出现的问题两张表里面没有对应的部门的字段
     *
     * @param depId     部门id
     * @param dpName    部门名称
     * @param checkType 基础/现场
     * @param request   请求
     * @return 该部门下面所有的检查项
     */
    @RequestMapping(value = "selectDep2")
    @ResponseBody
    public List findStation2(Integer depId, String dpName, Integer checkType, HttpServletRequest request) {
        User user = getLoginUser(request);
        List<String> list = new ArrayList<String>();
        if (-2 == checkType) {                                     //现场
            list = aCompanyManualMapper.selectlevel3BydmName(user.getId(), dpName);
        } else if (-1 == checkType) {                                //基础
            list = aCompanyManualMapper.selectlevel3BydmNameAndLevel3(user.getId(), dpName, "基础");
        } else {

        }

        ArrayList<ACompanyManual> linkedList = new ArrayList();
        if (null != list && list.size() > 0) {
            for (String level3 : list) {
                List<ACompanyManual> aCompanyManuals = aCompanyManualMapper.selectAllByLevel3(user.getId(), dpName, level3);
                for (ACompanyManual aCompanyManual : aCompanyManuals) {
                    linkedList.add(aCompanyManual);
                }
            }
        }

        return linkedList;
    }

    /**
     * TODO 根据公司和部门,岗位获取检查项
     * 根据部门和岗位,获取风险点和对应的风险因素和数据
     * 根据部门id 和岗位的名称获取level3和其他属性
     *
     * @return
     */
    @RequestMapping(value = "findInspection")
    public @ResponseBody
    List findInspection(Integer depId, String sName, HttpServletRequest request) {
        User user = getLoginUser(request);

        String dpName = zzjgDepartmentMapper.selectByPrimaryKey(depId).getName();// 部门名称
        //根据公司的id 部门名称和岗位信息获取该部门所有的风险点
        List<String> level3s = aCompanyManualMapper.findInspection(String.valueOf(user.getId()), dpName, sName); //获取level3
        List list = new ArrayList();
        for (String level3 : level3s) { //根据level3获取level4和数据
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
            CheckLevel checkLevel = new CheckLevel();
            checkLevel.setUid(user.getId());
            checkLevel.setLevel1(dpName);
            checkLevel.setLevel2(sName);
            checkLevel.setLevel3(level3);
            List<Map> listMap = aCompanyManualMapper.selectLevel4AndId(checkLevel);
            map.put("name", level3);
            map.put("list", listMap);
            list.add(map);
        }
        return list;
    }

    /**
     * TODO 根据高危的行业id查询检查项(废弃)
     */
    @RequestMapping("findGaoWeiAndJiChu")
    public @ResponseBody
    List findGaoWeiAndJiChu(Integer type, HttpServletRequest request) {
        List list1 = new ArrayList();
        List<String> list = tLevelMapper.selectLevel1ByIndustry(type);
        // 在根据部门做出统一的查询
        for (String level1 : list) {
            Map map = new LinkedHashMap();
            List<Map<String, Object>> maps = tLevelMapper.selectlevel2Bylevel1AndIndustryId(level1, type);
            map.put("name", list1);
            map.put("list", maps);
            list1.add(map);
        }
        return list1;
    }

    /**
     * TODO PC 自定义检查模板
     * 并进行设置标题的设置
     * flag 1.企业自查  2.行政检查  3 第三方检查
     *
     * @param request   请求
     * @param checkItem 用户选择的检查项
     * @return
     */
    @RequestMapping(value = "saveCheckMenu2")
    public @ResponseBody
    AppResult saveCheckMenu2(HttpServletRequest request, @RequestBody CheckItem checkItem) {
        User user = getLoginUser(request);
        ACompanyManual companyManual = aCompanyManualMapper.selectByPrimaryKey(checkItem.getCheckLevels().get(0).getId());
        Integer checkType = checkItem.title; // 获取检查方式
        String[] str = {"日常", "定期", "季节", "其他", "综合"};
        if (checkItem.getFlag() == 1) {  //表示企业自查
            if (checkType == 5) {
                checkItem.setTemplate(companyManual.getLevel1() + "综合检查表");
            } else {
                checkItem.setTemplate(companyManual.getLevel1() + str[checkType - 1] + "检查表");
            }
        } else {
            // 行政检查/部门抽查
            checkItem.setTemplate(companyManual.getLevel1() + "安全检查表");
        }
        return savemodel(request, checkItem);
    }

    /**
     * TODO PC端标准检查模版
     *
     * @param request   请求
     * @param dmid      部门名称(level1)
     * @param checkType 检查类型   -1 基础   -2 现场   其他高危
     * @param dptitle   检查方式   1 日常   2 定期   3 临时
     * @param times     定期检查的天数
     * @return message
     */
    @RequestMapping(value = "saveCheckMenu3")
    public @ResponseBody
    AppResult saveCheckMenu3(HttpServletRequest request, String dmid, Integer checkType, Integer dptitle, Integer times, Integer flag) {
        // 首先根据公司id查询部门的和其他的
        try {
            User user = getLoginUser(request);
            List<String> level3s = null;

            //做出判断基础还是现场还是高危
            if (checkType == -1) { //基础
                level3s = aCompanyManualMapper.selectlevel3BydmNameAndLevel3(user.getId(), dmid, "基础");
            } else if (checkType == -2) { //现场
                level3s = aCompanyManualMapper.selectlevel3BydmName(user.getId(), dmid);
            } else {             // 高危
                level3s = aCompanyManualMapper.selectlevel3BydmName(user.getId(), dmid);
            }

            List<ACompanyManual> list = new ArrayList<ACompanyManual>();

            for (String level3 : level3s) {
                List<ACompanyManual> list1 = aCompanyManualMapper.selectAllByLevel3(user.getId(), dmid, level3);
                if (null != list1) {
                    for (ACompanyManual companyManual : list1) {
                        list.add(companyManual);
                    }
                }
            }

            List<CheckLevel> checkLevels = new ArrayList<>();
            for (ACompanyManual companyManual : list) {
                CheckLevel checkLevel = new CheckLevel();

                checkLevel.setUid(companyManual.getUid());
                checkLevel.setId(companyManual.getId());
                checkLevel.setLevel1(companyManual.getLevel1());
                checkLevel.setLevel2(companyManual.getLevel2());
                checkLevel.setLevel3(companyManual.getLevel3());
                checkLevel.setLevel4(companyManual.getFactors());
                checkLevel.setFactors(companyManual.getFactors());
                checkLevel.setGkcs(companyManual.getGkcs());
                checkLevel.setGkzt(companyManual.getGkzt());
                checkLevels.add(checkLevel);
            }

            CheckItem checkItem = new CheckItem();
            checkItem.setCheckLevels(checkLevels);
            if (checkType == -1) { //基础
                checkItem.setTemplate(dmid + "基础标准检查表");
            } else if (checkType == -2) {
                checkItem.setTemplate(dmid + "现场标准检查表");
            } else {
                checkItem.setTemplate(dmid + "高危标准检查表");
            }
            checkItem.setFlag(flag);
            checkItem.setCheckType(checkType);      // 检查类型
            checkItem.setTitle(dptitle);       // 检查方式
            checkItem.setUid(user.getId());
            if (null != times) {
                checkItem.setCycle(times);
            }
            return savemodel(request, checkItem);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // 保存失败
            AppResult result = new AppResultImpl();
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO PC端行政检查/部门抽查模板保存
     * 检查类型就是固定的 都是综合检查表
     *
     * @return message
     */
    @RequestMapping(value = "save-administrative", method = RequestMethod.POST)
    @ResponseBody
    public Result saveAdministrative(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        Result result = new ResultImpl();

        try {
            // String tableName = (String) map.get("tableName"); //表名
            Integer flag = (Integer) map.get("flag"); //检查方式
            Integer checkType = (Integer) map.get("checkType"); //检查类型 -1 现场 -2 就是基础
            String a = (String) map.get("selectItems");
            Integer type = (Integer) map.get("lxType");
            Integer cycle = (Integer) map.get("cycle");
            String [] str = {"日常","定期","季节","其他","综合"};
            if (null == a || "".equals(a)) {
                result.setStatus("1");
                result.setMess("页面保存数据为空");
                return result;
            }

            String[] split = a.split(",");

            List<Map<String, String>> inputItems = (List<Map<String, String>>) map.get("cusCheckItemList");

            User user = getLoginUser(request);
            // 保存model表数据
            TModel model = new TModel();
            if (flag==1){
                model.setTitle("全公司"+str[type-1]+"检查表");
                if(null!=cycle){
                    Date d = new Date();
                    long time1 = d.getTime();
                    int i = cycle * 24 * 60 * 60 * 1000;
                    long l = time1 + i;
                    Date t = new Date(l);
                    model.setCycle(cycle); //定期周期天数
                    model.setNextTime(t);     // 下次生成的时间
                    model.setNextCheckTime(t); // 定期检查的时间
                    model.setOpen(1);          // 定期生成
                }

            }else if (flag == 2) {
                model.setTitle("行政检查表"); // 计划检查名
            } else {
                model.setTitle("部门检查表"); // 计划检查名
            }
            model.setUserId(user.getId());    // 企业id
            model.setFlag(flag);     //检查方式 1. 企业自查  2 行政检查  3 部门检查
            if(null!=type){
                model.setType(type);//  检查类型  日常, 定期, 临时
            }else{
                model.setType(5);//  检查类型  日常, 定期, 临时
            }

            if(flag==1){
                model.setPart(user.getUserName()); // 被检查的部门
            }else{
                model.setPart(user.getUserName()); // 被检查的部门
            }

            model.setIndustryType(Math.abs(checkType));//基础, 现场, 高危
            model.setCreateTime(new Date()); //创建时间
            tModelMapper.insertSelective(model);

            TModelPart modelPart = new TModelPart();
            modelPart.setModelId(model.getId());
            modelPart.setName(user.getUserName());
            modelPart.setLevels(a);
            tModelPartMapper.insertSelective(modelPart);

            TCheck tCheck = new TCheck();

            tCheck.setFlag(flag);                                                   // 1. 企业自查  2 行政检查  3 第三方

            if (flag==1){
                tCheck.setTitle(user.getUserName()+str[type-1]+"检查表");
                if(null!=cycle){
                    Date d = new Date();
                    long time1 = d.getTime();
                    int i = cycle * 24 * 60 * 60 * 1000;
                    long l = time1 + i;
                    Date t = new Date(l);
                    tCheck.setExpectTime(t);
                }
            }else if (flag == 2) {
                tCheck.setTitle("行政检查表"); // 计划检查名
            } else {
                tCheck.setTitle("部门检查表"); // 计划检查名
            }

            tCheck.setDepart(user.getUserName());                                              // 被检查的部门
            tCheck.setUserId(user.getId());                                         // 企业公司id
            tCheck.setCreateUser(user.getId());                                     // 创建人的id
            tCheck.setModelId(model.getId());                                       // 模版id
            if(null!=type){
                tCheck.setType(type);//  检查类型  日常, 定期, 临时
            }else{
                tCheck.setType(5);                                                      // 1. 日常 ,2. 定期 3 临时 5 综合
            }
            tCheck.setIndustryType(Math.abs(checkType));                            //  2. 现场
            //tCheck.setCheker(user.getUserName());                                   // 检查人 当前的公司名称
            tCheck.setContact(companyMapper.selectByPrimaryKey(user.getId()).getChargeContact());  // 检查人的联系方式无
            tCheck.setStatus(0);                                                    // 表示最开始的检查模版
            tCheck.setCreateTime(new Date());                                       // 创建时间
            //tCheck.setCheckCompany(user.getUserName());                             // 检查公司

            tCheckMapper.insertSelective(tCheck);
            Integer tCheckId = tCheck.getId();

            if (-2 == checkType) {
                // 表示现场
                for (String id : split) {
                    if (null != id && !"".equals(id)) {
                        ADangerManual aDangerManual = aDangerManualMapper.selectByPrimaryKey(Integer.parseInt(id));
                        TCheckPart tCheckPart = new TCheckPart();
                        tCheckPart.setCheckId(tCheckId);
                        if (null != aDangerManual.getName()) {
                            tCheckPart.setName(aDangerManual.getName());  //岗位/部位信息
                        }
                        tCheckPartMapper.insertSelective(tCheckPart);
                        TCheckItem tCheckItem = new TCheckItem();
                        tCheckItem.setLevelId(aDangerManual.getId());     // companyManualTbl的id
                        tCheckItem.setContent(aDangerManual.getFactors()); //检查内容
                        tCheckItem.setLevels(aDangerManual.getLevel1() + "/" + aDangerManual.getLevel2() + "/" + aDangerManual.getLevel3());   // 场所/环节/部门 三级
                        tCheckItem.setReference(aDangerManual.getReference()); // 依据
                        tCheckItem.setMemo(aDangerManual.getFactors());   // 较大危险因素
                        tCheckItem.setPartId(tCheckPart.getId());
                        tCheckItem.setCheckId(tCheckId);
                        tCheckItemMapper.insertSelective(tCheckItem);
                    }
                }
            } else {
                // 表示是基础
                for (String id : split) {
                    if (null != id && !"".equals(id)) {
                        TLevel tLevel = tLevelMapper.selectByPrimaryKey(Integer.parseInt(id));
                        TCheckPart tCheckPart = new TCheckPart();
                        tCheckPart.setCheckId(tCheckId);
                        tCheckPart.setName(tLevel.getName());  //岗位/部位信息
                        tCheckPartMapper.insertSelective(tCheckPart);
                        TCheckItem tCheckItem = new TCheckItem();
                        tCheckItem.setLevelId(Integer.parseInt(id));     // companyManualTbl的id
                        tCheckItem.setContent(tLevel.getFactors()); //检查内容
                        tCheckItem.setLevels(tLevel.getLevel1() + "/" + tLevel.getLevel2() + "/" + tLevel.getLevel3());   // 场所/环节/部门 三级
                        tCheckItem.setReference(tLevel.getReference()); // 依据
                        tCheckItem.setMemo(tLevel.getFactors());   // 较大危险因素
                        tCheckItem.setPartId(tCheckPart.getId());
                        tCheckItem.setCheckId(tCheckId);
                        tCheckItemMapper.insertSelective(tCheckItem);
                    }
                }
            }

            if (null != inputItems && inputItems.size() > 0) {
                // 保存自定义内容
                for (Map<String, String> mapStr : inputItems) {
                    TCheckPart tCheckPart = new TCheckPart();
                    tCheckPart.setCheckId(tCheckId);
                    tCheckPart.setName("全公司");  //岗位/部位信息
                    tCheckPartMapper.insertSelective(tCheckPart);
                    TCheckItem tCheckItem = new TCheckItem();
                    //tCheckItem.setLevelId(aDangerManual.getId());     // companyManualTbl的id
                    tCheckItem.setContent(mapStr.get("level4")); //检查内容
                    tCheckItem.setLevels(mapStr.get("level3"));   // 场所/环节/部门 三级
                    // tCheckItem.setReference(aDangerManual.getReference()); // 依据
                    //tCheckItem.setMemo();   // 较大危险因素
                    tCheckItem.setPartId(tCheckPart.getId());
                    tCheckItem.setCheckId(tCheckId);
                    tCheckItemMapper.insertSelective(tCheckItem);
                }

            }
            result.setStatus("0");
            result.setMess("保存成功");
            result.setObject(model.getId());
            return result;
        } catch (NullPointerException e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("页面保存数据为空");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("保存失败");
            return result;
        }
    }

    private AppResult savemodel(HttpServletRequest request, CheckItem checkItem) {
        AppResult result = new AppResultImpl();
        User user = getLoginUser(request); // 主账号登陆
        ACompanyManual companyManual = aCompanyManualMapper.selectByPrimaryKey(checkItem.getCheckLevels().get(0).getId());
        try {
            if (null == checkItem) {
                result.setStatus("1");
                return result;
            }
            TModel model = new TModel();
            model.setTitle(checkItem.getTemplate()); // 计划检查名
            model.setUserId(user.getId());    // 企业id
            model.setFlag(checkItem.getFlag());     //检查方式 1. 企业自查  2 行政检查  3 部门检查
            System.out.println(checkItem.getFlag());
            model.setType(checkItem.getTitle());//  检查类型  日常, 定期, 临时
            if (-2 == checkItem.getCheckType() || -1 == checkItem.getCheckType()) { //只有现场才会存储部门
                model.setPart(checkItem.getCheckLevels().get(0).getLevel1()); // 被检查的部门
            }
            //基础, 现场, 高危
            if (-2 == checkItem.getCheckType()) { //现场
                model.setIndustryType(2);
            } else if (-1 == checkItem.getCheckType()) {
                model.setIndustryType(1); // 基础
            } else {
                model.setIndustryType(3); // 高危
            }

            model.setCreateTime(new Date()); //创建时间
            if (null != checkItem.getCycle()) {  // 保存定期生成时间
                Date d = new Date();
                long time1 = d.getTime();
                int i = checkItem.getCycle() * 24 * 60 * 60 * 1000;
                long l = time1 + i;
                Date t = new Date(l);
                Integer integer = Integer.valueOf(checkItem.getCycle());
                model.setCycle(integer); //定期周期天数
                model.setNextTime(t);     // 下次生成的时间
                model.setNextCheckTime(t); // 定期检查的时间
                model.setOpen(1);          // 定期生成
            } else {
            }

            tModelMapper.insertSelective(model);
            // 存储part表数据

            if (-2 == checkItem.getCheckType() || -1 == checkItem.getCheckType()) { // 现场和基础
                List<CheckLevel> checkLevels1 = checkItem.getCheckLevels();
                Set<String> set = new LinkedHashSet<>();
                String str = new String();
                for (int i = 0; i < checkLevels1.size(); i++) {
                    set.add(checkLevels1.get(i).getLevel2());
                    if (i < checkLevels1.size() - 1) {
                        str += checkLevels1.get(i).getId() + ",";
                    } else {
                        str += checkLevels1.get(i).getId() + "";
                    }
                }
                for (String s : set) {
                    TModelPart modelPart = new TModelPart();
                    modelPart.setModelId(model.getId());
                    modelPart.setName(s);
                    modelPart.setLevels(str);
                    tModelPartMapper.insertSelective(modelPart);
                }
            } else {   // 基础和高危
                TModelPart modelPart = new TModelPart();
                modelPart.setModelId(model.getId());
                tModelPartMapper.insertSelective(modelPart);
            }

            // 保存检查记录总表
            TCheck tCheck = new TCheck();

            tCheck.setFlag(checkItem.getFlag());                                      // 1. 企业自查  2 行政检查  3 第三方
            tCheck.setTitle(checkItem.getTemplate());                               // 被检查的标题
            if (-2 == checkItem.getCheckType() || -1 == checkItem.getCheckType()) { //只有现场才会存储部门
                tCheck.setDepart(checkItem.getCheckLevels().get(0).getLevel1());        // 被检查的部门
                //但是会出现现场检查什么都没有的情况.
                if (null != checkItem.getCheckLevels().get(0).getLevel3() && "".equals(checkItem.getCheckLevels().get(0).getLevel3())) {
                    tCheck.setDapartContact(companyManual.getDmid() + "");                 // 被检查部门的id
                }
            }
            tCheck.setUserId(user.getId());                                         // 企业公司id
            tCheck.setCreateUser(user.getId());                                     // 被创建人的id
            tCheck.setModelId(model.getId());                                       // 模版id
            tCheck.setType(checkItem.getTitle());                                   // 1. 日常 ,2. 定期 3 临时
            if (-2 == checkItem.getCheckType()) {
                tCheck.setIndustryType(2);                                          //  2. 现场
            } else if (-1 == checkItem.getCheckType()) {
                tCheck.setIndustryType(1);                                          // 1. 基础
            } else {
                tCheck.setIndustryType(3);                                          // 3 高危
            }
            tCheck.setExpectTime(new Date());                                      // 预计检查时间
            //tCheck.setCheker(user.getUserName());                                  // 检查人 当前的公司名称
            //tCheck.setContact(user.getUserName());                               // 检查人的联系方式无

            tCheck.setStatus(0);                                                   // 最开始的检查模版
            tCheck.setCreateTime(new Date());                                      // 创建时间
            tCheck.setCheckCompany(user.getUserName());                            // 检查公司

            tCheckMapper.insertSelective(tCheck);
            Integer tCheckId = tCheck.getId();

            if (-2 == checkItem.getCheckType() || -1 == checkItem.getCheckType()) {          //表示现场/基础
                if (null != checkItem.getCheckLevels() && checkItem.getCheckLevels().size() > 0) {
                    for (CheckLevel checkLevels : checkItem.getCheckLevels()) {

                        TCheckPart tCheckPart = new TCheckPart();
                        tCheckPart.setCheckId(tCheckId);
                        tCheckPart.setName(checkLevels.getLevel2());  //岗位/部位信息
                        tCheckPartMapper.insertSelective(tCheckPart);
                        TCheckItem tCheckItem = new TCheckItem();
                        tCheckItem.setLevelId(checkLevels.getId());     // companyManualTbl的id
                        tCheckItem.setContent(checkLevels.getFactors()); //检查内容
                        tCheckItem.setLevels(checkLevels.getLevel3());
                        tCheckItem.setReference(checkLevels.getReference());
                        tCheckItem.setMemo(checkLevels.getFactors());
                        tCheckItem.setPartId(tCheckPart.getId());
                        tCheckItem.setCheckId(tCheckId);
                        tCheckItemMapper.insertSelective(tCheckItem);
                    }
                }
            } else {
                // 高危检查  没有部门和岗位
                if (null != checkItem.getCheckLevels() && checkItem.getCheckLevels().size() > 0) {
                    for (CheckLevel checkLevels : checkItem.getCheckLevels()) {

                        TCheckPart tCheckPart = new TCheckPart();
                        tCheckPart.setCheckId(tCheckId);
                        tCheckPartMapper.insertSelective(tCheckPart);
                        TCheckItem tCheckItem = new TCheckItem();
                        tCheckItem.setContent(checkLevels.getLevel1()); //检查内容
                        tCheckItem.setLevels(checkLevels.getLevel2());
                        tCheckItem.setPartId(tCheckPart.getId());
                        tCheckItem.setCheckId(tCheckId);
                        tCheckItemMapper.insertSelective(tCheckItem);
                    }
                }
            }
            result.setStatus("0");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            return result;
        }
    }

}
