/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Company;
import com.spring.web.model.Library;
import com.spring.web.model.Regulation;
import com.spring.web.model.Trade;
import com.spring.web.model.TradeCompany;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.util.ConstantsUtil;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.SessionUtil;

/**
 * @Title: VillageController
 * @Description: TODO(Trade: 职业)
 * @author FL
 * @date 2017年7月21日 下午12:42:42
 * @version V1.0
 */
@Controller
@RequestMapping("/trade")
public class TradeController extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 2125785439865846208L;
    @Autowired
    private UserService userService;
    @Autowired
    private CgfService cgfService;

    /**
     * 政府首页
     */
    @RequestMapping(value = "main")
    public String main(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
        //log.error(trade.toString());
        model.addAttribute("name_", trade.getName());
        model.addAttribute("userId", trade.getUserId());
        if(trade.getIsClique() == 1){ //跳转到总部企业
        	//model.addAttribute("list", tradeMapper.selectTradeCompany(user.getId()));//行业端所属企业
        	//log.error("TradeComopany："+tradeMapper.selectTradeCompany(user.getId()));
        	return "tradeclique/clique-main";	
        }
        return "trade/main";
    }
//    /**
//     * 政府首页
//     */
//    @RequestMapping(value = "main")
//    public String main(HttpServletRequest request, Model model) throws Exception {
//        User user = getLoginUser(request);
//        if(user.getUserType() == 5){
//        	//log.error("企业登陆后刷新");
//        	HttpSession session = request.getSession();
//        	User user1 =  (User)session.getAttribute("trade");
//        	//log.error("企业登陆后刷新user1："+user1.toString());
//        	SessionUtil.setUser(request, user1);
//        	if(user1.getId()== 35346){//wz 0105 行业端内部仍有区分，目前按要求只修改新三洲账号；后续另谈	
//        		model.addAttribute("list", tradeMapper.selectTradeCompany(user1.getId()));
//        		//log.error("TradeComopany："+tradeMapper.selectTradeCompany(user.getId()));
//        		return "tradeclique/clique-main";	
//        	}
//        }else{
//        	HttpSession session = request.getSession();
//        	session.setAttribute("trade",user);
//        	model.addAttribute("name_", tradeMapper.selectByPrimaryKey(user.getId()).getName());
//        	if(user.getId()== 35346){//wz 0105 行业端内部仍有区分，目前按要求只修改新三洲账号；后续另谈	
//        		model.addAttribute("list", tradeMapper.selectTradeCompany(user.getId()));
//        		//log.error("TradeComopany："+tradeMapper.selectTradeCompany(user.getId()));
//        		return "tradeclique/clique-main";	
//        	}
//        }
//        return "trade/main";
//    }
    /**
     * 政府首页-中间
     */
    @RequestMapping(value = "welcome")
    public String welcome(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        CompanyListReqDTO dto = new CompanyListReqDTO();
        List<Integer> count = userService.selectCount(dto, user);
        
        //log.error("count："+count.toString());
        
        String userIds = (String) m.get("userIds");
        if(userIds.equals("-1")) {
            model.addAttribute("number", 0);
        } else {
            model.addAttribute("number", userIds.split(",").length);//企业数量
        }
        
        m.put("tradeId", null);
        model.addAttribute("name_", tradeMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("count", count);
        System.out.println(user.getId());
        model.addAttribute("userId", user.getId());
        Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
        if(trade.getIsClique() == 1){//行业端集团型企业
        	return "tradeclique/clique-welcome";	
        }
        
        return "trade/welcome";
    }  
    /**
     * 企业列表
     */
    @RequestMapping(value = "company/company-list")
    public String companyList(Integer townId, Integer villageId, Integer userId, Model model,
            HttpServletRequest request, String companyName, String coType, String state, String isFreeze,
            Integer cisDanger, String dlevel, Integer isKey, String doubleDanger, Integer isAll) throws Exception {
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
        m.put("coType", coType);
        m.put("isFreeze", isFreeze);
        m.put("cisDanger", cisDanger);
        m.put("dlevel", dlevel);
        m.put("isKey", isKey);
        m.put("isAll", isAll);
        m.put("doubleDanger", doubleDanger);
        List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyList(m);
        model.addAttribute("list", mlist);
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("companyName", companyName);
        model.addAttribute("coType", coType);
        model.addAttribute("state", state);
        model.addAttribute("isFreeze", isFreeze);
        model.addAttribute("isKey", isKey);
        model.addAttribute("isAll", isAll);
        model.addAttribute("sk", request.getParameter("sk"));
        if (null != cisDanger) {
            return "village/company/company-list3";
        }
        if (StringUtils.isNotBlank(dlevel)) {
            return "village/company/company-list2";
        }
        return "village/company/company-list";
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
    public String toAddCompany(Model model) throws Exception {
        return "village/company/company-add";
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
    @RequestMapping(value = "trade-user-del")
    public @ResponseBody Result tradeUserDel(Integer userId, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", userId);
        m.put("tid", getLoginUser(request).getId());
        tradeCompanyMapper.deleteByUidAndTid(m);
        return result;
    }
    
    /**
     * 企业添加保存
     */
    @RequestMapping(value = "company-edit-do")
    public @ResponseBody Result companyEditDo(Company c, String jw, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        companyMapper.updateByPrimaryKeySelective(c);
        if (StringUtils.isNotBlank(jw)) {
            User user = new User();
            user.setId(c.getUserId());
            String[] x = jw.split(",");
            user.setLatitude(x[1]);
            user.setLongitude(x[0]);
            userMapper.updateByPrimaryKeySelective(user);
        }
        return result;
    }

    /**
     * 企业添加保存
     */
    @RequestMapping(value = "company/company-save")
    public @ResponseBody Result companySave(User user, HttpServletRequest request, Integer villageId, Integer tradeId)
            throws Exception {
        Result result = new ResultImpl();
        User xx = userMapper.selectUserByUserName(user.getUserName());
        if (null != xx) {
            result.setStatus("1");
            result.setMap("message", "该企业已存在");
            return result;
        }
        User user_d = getLoginUser(request);
        userService.registar(user, result, user_d, null == villageId ? getLoginUser(request).getId() : villageId);
        return result;
    }

    /**
     * 冻结 解冻
     */
    @RequestMapping(value = "user-freeze")
    public @ResponseBody Result userFreeze(User user, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        userMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    /**
     * 是否重点企业
     */
    @RequestMapping(value = "user-isKey")
    public @ResponseBody Result userisKey(Company company, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        companyMapper.updateByPrimaryKeySelective(company);
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
     * 重置企业登录密码
     */
    @RequestMapping(value = "system/user-psw")
    public @ResponseBody Result userPsw(Integer id, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = new User();
        user.setId(id);
        user.setPsw(ConstantsUtil.ENCRYPT_PASSWORD_123456);
        userMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    /**
     * 企业分布页面
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("company/company-map")
    public String coMap(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
        for (int i = 0; i < list.size(); i++) {
            DynamicParameter<String, Object> item = list.get(i);
            item.put("id", i + 1 + "");
        }
        String userStr = JSONObject.toJSONString(user);
        model.addAttribute("user", userStr);
        String listStr = JSONArray.toJSONString(list);
        model.addAttribute("companyList", listStr);
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
     * 企业统计
     */
    @RequestMapping(value = "company/company-tab")
    public String companyTab(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
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
        model.addAttribute("danger", danger);
        return "village/company/company-tab";
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
     * 行业添加企业
     */
    @RequestMapping(value = "trade-company-add")
    public @ResponseBody Result tradeCompanyAdd(HttpServletRequest request, Integer companyId) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        TradeCompany tradeCompany = new TradeCompany();
        tradeCompany.setTid(user.getId());
        tradeCompany.setUid(companyId);
        tradeCompany.setIskey(0);
        tradeCompanyMapper.insertSelective(tradeCompany);
        result.setMap("tradeCompany", tradeCompany);
        return result;
    }

    /**
     * 行业添加企业
     */
    @RequestMapping(value = "trade-company-del")
    public @ResponseBody Result tradeCompanyDel(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        tradeCompanyMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 企业添加页面
     */
    @RequestMapping(value = "company-add")
    public String toAddCompany(HttpServletRequest request, Model model) throws Exception {
        List<DynamicParameter<String, Object>> list = districtMapper.selectDistrict();
        model.addAttribute("list", list);
        return "trade/company-add";
    }
    
   
}
