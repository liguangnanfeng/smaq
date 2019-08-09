package com.spring.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.dao.*;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.*;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.envir_params_setting.EnvirParamsSettingService;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.tobject.user.LoginReqDTO;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.EncryptUtil;
import com.spring.web.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/global")
public class GlobalController extends BaseController {
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CgfService cgfService;
    @Autowired
    private EnvirParamsSettingService envirParamsSettingService;
    @Autowired
    private MessagesMapper messagesMapper;
    @Autowired
    private MessageToUserMapper messageToUserMapper;
    @Autowired
    private UserGroupListMapper userGroupListMapper;
    @Autowired
    private HiddenPlanMapper hiddenPlanMapper;
    /**
     * 政府端登陆页面
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fore")
    public String safety(Model model, HttpServletRequest request) throws Exception {
        return "global/blogin2";
    }
    /**
     * 政府端登陆验证
     * @param dto  登录的dto
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/glogin")
    public @ResponseBody
    Result login(LoginReqDTO dto, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = dto.getUserName();
        String password = dto.getPassword();
        Result result = new ResultImpl();
        //基本验证
        if (StringUtils.isBlank(username)) {
            result.setStatus("1");
            result.setMap("message", "账号不能为空。");
            return result;
        }
        if (StringUtils.isBlank(password)) {
            result.setStatus("1");
            result.setMap("message", "密码不能为空。");
            return result;
        }

        try {
            User user = userMapper.selectUserByUserName(username);
            // 判断账号是否存在
            if (user == null) {
                result.setStatus("1");
                result.setMap("message", "该账号不存在。");
                return result;
            }
            // 判断账号是否被冻结
            if (!"0".equals(user.getIsFreeze())) {
                result.setStatus("1");
                result.setMap("message", "该账号被冻结。");
                return result;
            }

            //处理惠山区港口的特殊账号
            if (!user.getUserName().equals("惠山区港口")) {
                // 判断当用户类型不是10时,提示错误
                if (user.getUserType() == 1 || user.getUserType() == 2 || user.getUserType() == 5) {
                    result.setStatus("1");
                    result.setMap("message", "请用政府账号登录。");
                    return result;
                }
            }
            //使用shiro进行登录
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            SessionUtil.setUser(request, user);
            result.setMap("userType", user.getUserType());
        } catch (Exception uae) {
            uae.printStackTrace();
            log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            result.setStatus("1");
            result.setMap("message", "用户名或密码不正确。");
            return result;
        }
        String msg = "用户登录日志:";
        log.debug(msg + "[" + username + "]" + "登录成功");
        return result;
    }
    /**
     * 主页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request, Model model) {
        User user = getLoginUser(request);
        Integer userType = user.getUserType(); //获取用户类型
        model.addAttribute("loginUserId", user.getId());
        model.addAttribute("userType", userType);
        if (userType == 9) {  //地市级
            model.addAttribute("list", districtMapper.selectDistrict());
            model.addAttribute("list1", tradeMapper.selectTrade());
            model.addAttribute("name_", user.getUserName());
        }
        if (userType == 6) { //县级市
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
        }
        if (userType == 3) { //镇级
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
        }
        if (userType == 4) { //村级
            model.addAttribute("name_", villageMapper.selectByPrimaryKey(user.getId()).getName());
            if (user.getUserName().equals("港口")) {//港口特殊账号
                return "gang/main";
            }
        }
        if (userType == 10) { //行业级
            Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
            //log.error(trade.toString());
            model.addAttribute("userType", userType);
            model.addAttribute("name_", trade.getName());
            model.addAttribute("userId", trade.getUserId());
            if (trade.getIsClique() == 1) { //跳转到总部企业
                //model.addAttribute("list", tradeMapper.selectTradeCompany(user.getId()));//行业端所属企业
                //log.error("TradeComopany："+tradeMapper.selectTradeCompany(user.getId()));
                return "tradeclique/clique-main";
            }

        }
        return "global/main";
    }

    /**
     * 政府首页-欢迎页
     */
    @RequestMapping(value = "/welcome")
    public String welcome(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        if (user.getUserType() == 9) { //地级市
            Map<String, Object> m = new HashMap<String, Object>();
            setUserId(user, m);

            CompanyListReqDTO dto = new CompanyListReqDTO();
            List<Integer> count = userService.selectCount(dto, user);

            model.addAttribute("number", companyMapper.selectIdsByCompany_view_count(dto));//企业数量

            model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
            model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总

            model.addAttribute("mc", monitorMapper.selectCount(m));
            model.addAttribute("count", count);

            model.addAttribute("loginUserId", user.getId());
        }
        if (user.getUserType() == 6) { //县级市
            Map<String, Object> m = new HashMap<String, Object>();
            setUserId(user, m);
            CompanyListReqDTO dto = new CompanyListReqDTO();
            List<Integer> count = userService.selectCount(dto, user);

            model.addAttribute("number", companyMapper.selectIdsByCompany_view_count(dto));//企业数量

            model.addAttribute("district", districtMapper.selectByPrimaryKey(user.getId()));

            m.clear();
            setUserId(user, m);
            model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
            model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总

            model.addAttribute("mc", monitorMapper.selectCount(m));
            model.addAttribute("count", count);

            model.addAttribute("loginUserId", user.getId());

            //add wz 2019-02-21
            HttpSession session = request.getSession();
            User moveBeforeUser = (User) session.getAttribute("moveBefore");
            if (moveBeforeUser != null) {
                model.addAttribute("moveD", 1);
                model.addAttribute("nameBefore", userMapper.selectByPrimaryKey(moveBeforeUser.getId()).getUserName());
            }

        }
        if (user.getUserType() == 3) { //镇级
            Map<String, Object> m = new HashMap<String, Object>();
            setUserId(user, m);
            CompanyListReqDTO dto = new CompanyListReqDTO();
            List<Integer> count = userService.selectCount(dto, user);

            model.addAttribute("number", companyMapper.selectIdsByCompany_view_count(dto));//企业数量

            model.addAttribute("town", townMapper.selectByPrimaryKey(user.getId()));

            m.clear();
            setUserId(user, m);
            model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
            model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总

            model.addAttribute("count", count);
            model.addAttribute("mc", monitorMapper.selectCount(m));
            model.addAttribute("loginUserId", user.getId());

            //add wz 2019-02-21
            HttpSession session = request.getSession();
            User moveBeforeUser = (User) session.getAttribute("moveBefore");
            if (moveBeforeUser != null) {
                model.addAttribute("moveD", 1);
                model.addAttribute("nameBefore", districtMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName());
            }
        }
        if (user.getUserType() == 4) { //村级
            Map<String, Object> m = new HashMap<String, Object>();
            setUserId(user, m);

            String userIds = (String) m.get("userIds");

            CompanyListReqDTO dto = new CompanyListReqDTO();
            dto.setUserIds(userIds);
            List<Integer> count = userService.selectCount(dto, user); //查询所有管辖企业的id

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

        }
        if (user.getUserType() == 10) { //行业级
            Map<String, Object> m = new HashMap<String, Object>();
            setUserId(user, m);
            CompanyListReqDTO dto = new CompanyListReqDTO();
            List<Integer> count = userService.selectCount(dto, user);

            //log.error("count："+count.toString());

            String userIds = (String) m.get("userIds");
            if (userIds.equals("-1")) {
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
            model.addAttribute("userId", user.getId());
            Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
            if (trade.getIsClique() == 1) {//行业端集团型企业
                return "tradeclique/clique-welcome";
            }
        }
        return "global/welcome";
    }

    /**
     * 企业数据库分类-全部企业-查询
     * 首页-企业数量跳转页
     * @param model
     * @param request
     * @param dto
     * @param totalzc
     * @param totalwyx
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "company/company-list")
    public String companyList(Model model, HttpServletRequest request, CompanyListReqDTO dto, Integer totalzc, Integer totalwyx) throws Exception {
        User user = getLoginUser(request);
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
        return "global/company/company-list";
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
        }

        if (user.getUserType().intValue() == 6) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
        }
        model.addAttribute("userType", user.getUserType().intValue());
        return "global/area/company/company-add";
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
        return "global/company/company-add-trade";
    }
    /**
     * to企业行业添加页面
     */
    @RequestMapping(value = "vilage-list")
    public @ResponseBody Result vilageList(Integer townId) throws Exception {
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("townId", townId);
        List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
        result.setMap("list", list);
        return result;
    }
    /**
     * 行业端——集团企业首页——企业数量——跳转到企业页面
     */
    @RequestMapping(value = "move/company")
    public String move(HttpServletRequest request, Model model,Integer uid) throws Exception {
        User user = getLoginUser(request);
        if(user.getUserType()!=5){
            HttpSession session = request.getSession();
            //log.error("moveBefore："+user.toString());
            session.setAttribute("govUser",user); //跳转前user保存
            user = userMapper.selectByPrimaryKey(uid);
            SessionUtil.setUser(request, user);
        }
        model.addAttribute("userName", companyMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("loginUserId", user.getId());
        return "company/main";
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
        return "global/company/company-show";
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
        return "global/company/information-edit";
    }
    /**
     * 是否村级重点企业
     */
    @RequestMapping(value = "user-isKey")
    public @ResponseBody
    Result userisKey(Company company, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        companyMapper.updateByPrimaryKeySelective(company);
        return result;
    }
    /**
     * TODO 是否重点企业   修改为镇重点
     */
    @RequestMapping(value = "user-key")
    public @ResponseBody Result userisKey(Integer userId, Integer key1) throws Exception {
        Result result = new ResultImpl();
        CompanyIskey key = companyIskeyMapper.selectByPrimaryKey(userId);
        if(null == key) {
            key = new CompanyIskey(userId, key1, 0, 0, 0);
            companyIskeyMapper.insertSelective(key);
        } else {
            key.setKey1(key1);
            companyIskeyMapper.updateByPrimaryKeySelective(key);
        }
        return result;
    }
    /**
     * TODO 是否重点企业   修改为区重点
     */
    @RequestMapping(value = "user-key2")
    public @ResponseBody Result userisKey2(Integer userId, Integer key2) throws Exception {
        Result result = new ResultImpl();
        CompanyIskey key = companyIskeyMapper.selectByPrimaryKey(userId);
        if(null == key) {
            key = new CompanyIskey(userId, 0, key2, 0, 0);
            companyIskeyMapper.insertSelective(key);
        } else {
            key.setKey2(key2);
            companyIskeyMapper.updateByPrimaryKeySelective(key);
        }
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
     * 企业分布页面
     * 首页-企业分布图
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
        return "global/company/company-map1";
    }
    /**
     * 摄像头监管段
     * 首页-重点部位/装置实时监控
     */
    @RequestMapping(value = "monitor-list-supervise")
    public String monitorListSupervise(HttpServletRequest request, String companyName, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        model.addAttribute("list", monitorMapper.selectGroupByMap(m));
        model.addAttribute("companyName", companyName);
        return "global/system/monitor-list-supervise";
    }
    /**
     * 特种人员列表
     * 首页-持证上岗
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
        return "global/warning/sperson-list";
    }
    /**
     * 特种设备列表
     * 首页-特种设备检测
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
        return "global/warning/sequipment-list";
    }
    /**
     * 职业卫生检测
     * 首页-职卫检测,体检
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
        return "global/warning/detection-list";
    }
    /**
     * 企业添加页面
     */
    @RequestMapping(value = "company-add")
    public String toAddCompany(HttpServletRequest request, Model model) throws Exception {
        List<DynamicParameter<String, Object>> list = districtMapper.selectDistrict();
        model.addAttribute("list", list);
        return "global/other/company-add";
    }
    /**
     * 隐患列表 整改复查列表
     * c 0 表示过滤未整改的 1已整改的
     * 首页-行政执法
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
        return "global/danger/check-fulist";
    }
    /**
     * 重大隐患列表
     * 首页-重大事故隐患
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
        return "global/company/danger-index3";
    }
    /**
     * TODO 隐患汇总 cId 企业id  隐患排查记录
     * 首页-企业自查
     * 首页->部门抽查
     * 首页-行政执法
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
        return "global/company/danger-index-list";
    }
    /**
     * 法律法规标准规范页面
     */
    @RequestMapping("notice-list1")
    public String noticeList1(Model model, HttpServletRequest request, String type) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", 3);
        model.addAttribute("list", noticeMapper.selectNoticeList(m));
        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("type", "2,3,4");
        model.addAttribute("list2", noticeMapper.selectNoticeList(m2));
        Map<String, Object> m3 = new HashMap<String, Object>();
        m3.put("type", 1);
        model.addAttribute("list3", noticeMapper.selectNoticeList(m3));
        model.addAttribute("type", type);
        return "company/notice/notice-list1";
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
        return "global/company/company-tab";
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
        if (StringUtils.isNotBlank(level)) {
            level = utf8Str(level);
        }
        m.put("companyName", companyName);
        m.put("level", level);
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
        if (level.equals("红色")) {
            return "global/safety-system/control-listmenu";
        } else {
            return "global/safety-system/control-listmenu1";
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
        for (int i = 0; i < ll.size(); i++) {
            industrys[i] = ll.get(i).getName();
            gg1[i] = 0;
            gg2[i] = 0;
            gg3[i] = 0;
            gg4[i] = 0;
        }

        Integer[] flag1 = new Integer[]{0, 0, 0, 0};//部位 数值顺序对应 红橙黄蓝
        for (Map<String, Object> l : list) {
            String level = "";
            if (null != l.get("level")) {
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
        return "global/safety-system/statistics-list";
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
     * 风险研判与承诺公告————安全承诺公汇总
     */
    @RequestMapping(value = "study/promise-collect")
    public String promiseCollect(HttpServletRequest request,Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        if (user.getUserType() == 10) {//行业端
            m.put("tid", user.getId());
            list = fcPromiseMapper.selectCurrentMonthByTradeMap(m);
        }else{
            //村级账号
            if (user.getUserType() == 4) {
                m.put("villageId", user.getId());
            }
            //镇级账号
            if (user.getUserType() == 3) {//
                m.put("townId", user.getId());
            }
            //区级账号
            if (user.getUserType() == 6) {
                m.put("districtId", user.getId());
            }
            //总账号查询所有的
            if (user.getUserType() == 9) {

            }
            list = fcPromiseMapper.selectCurrentMonthByVTDMap(m);
        }
        model.addAttribute("list", list);
        return "global/study/promise-collect";
    }
    /**
     * 源长制全部企业
     * @author hejy 2019.1.14
     */
    @RequestMapping(value = "source/company-list")
    public String companyListz(Model model, HttpServletRequest request, CompanyListReqDTO dto, Integer totalzc, Integer totalwyx) throws Exception {
        User user = getLoginUser(request);
        //设置查询条件
        setUserId2(user, dto);
        dto.setCisDanger("1");
        String industry2 = null;
        if (StringUtils.isNotBlank(dto.getIndustry2_())) {
            industry2 = dto.getIndustry2_();
            if (StringUtils.isNotBlank(dto.getIndustry2_2())) {
                industry2 = industry2 + " > " + dto.getIndustry2_2();
            }
        }
        dto.setIndustry2(industry2);
        String freeze = null;//isFreeze属性用来判断运行状态（当日是否已填写每日检查表）而非是否冻结
        if(StringUtils.isNotBlank(dto.getIsFreeze())){
            freeze = dto.getIsFreeze();
            dto.setIsFreeze(null);
        }
        //获取所有符合条件的user_id
        if(StringUtils.isBlank(dto.getUserIds()) || "-1".equals(dto.getUserIds())){
            List<Integer> allUserIds = companyMapper.selectIdsByCompany_view(dto);
            if (allUserIds.size() == 0) {
                dto.setUserIds("-1");
            } else {
                dto.setUserIds(StringUtils.join(allUserIds, ","));
            }
        }
        //全部企业列表
        List<Map<String,Object>> comps = new ArrayList<Map<String,Object>>();
        //判断每日检查表是否填写 运行状态列表operatingStatusList
        List<Boolean> operatingStatusList = new ArrayList<Boolean>();
        if( ! dto.getUserIds().equals("-1")){
            comps = companyMapper.selectByIds(dto);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            boolean operatingStatus = false;
            String title = "重大危险源每日检查表";
            Map<String, Object> c = new HashMap<String, Object>();
            c.put("flag", 1);
            c.put("title", title);
            for(int i=0; i < comps.size(); i++){
                c.put("userIds", comps.get(i).get("userId"));
                List<Map<String, Object>> lists = tCheckMapper.selectList(c);
                for (Map<String, Object> list : lists) {
                    String rt = list.get("realTime").toString();
                    if(date.equals(rt.substring(0,10))){
                        operatingStatus = true;
                        break;
                    }
                }
                operatingStatusList.add(operatingStatus);
                operatingStatus = false;
            }
            //运行状态筛选
            List<Integer> removeList = new ArrayList<Integer>();
            if(freeze != null){
                dto.setIsFreeze(freeze);
                if(freeze.equals("1")){
                    for(int i = 0; i < comps.size(); i++){
                        if(operatingStatusList.get(i) == false){
                            removeList.add(i);
                        }
                    }
                }else{//freeze为"0"
                    for(int i = 0; i < comps.size(); i++){
                        if(operatingStatusList.get(i) == true){
                            removeList.add(i);
                        }
                    }
                }
                if(removeList.size() > 0){
                    for(int i = removeList.size()-1; i >= 0; i--){
                        comps.remove(removeList.get(i).intValue());
                        operatingStatusList.remove(removeList.get(i).intValue());
                    }
                }
            }
            //分页
            model.addAttribute("total", comps.size());
            Integer from = dto.getPage() * dto.getRows();
            Integer end = null;
            end = Math.min(from + dto.getRows(), comps.size());
            comps = comps.subList(from, end);
            operatingStatusList = operatingStatusList.subList(from, end);
        }else{
            model.addAttribute("total", 0);
        }
        model.addAttribute("list",comps);
        model.addAttribute("osl", operatingStatusList);

        if(user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }
//        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("sk", request.getParameter("sk"));
//        model.addAttribute("totalzc", totalzc);
//        model.addAttribute("totalwyx", totalwyx);
        model.addAttribute("dto", dto);

        return "global/source/company-list";
    }
    void setUserId2(User user, CompanyListReqDTO dto) {
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
     * 重大危险源源长制————在线监测
     */
    @RequestMapping(value = "source/video")
    public String monitorListSupervisez(HttpServletRequest request, String companyName, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("cisDanger", 1);
        m.put("chemic", 1);
        m.put("companyName", companyName);
        List<Integer> uidList = companyMapper.selectUseridsForSource(m);
        if(uidList.size()==0){
            m.put("userIds", -1);
        }else{
            String[] userIds = new String[uidList.size()];
            for(int i = 0; i < uidList.size(); i++){
                userIds[i] = uidList.get(i).toString();
            }
            m.put("userIds", StringUtils.join(userIds, ","));
        }
        model.addAttribute("list", monitorMapper.selectGroupByMap(m));
        model.addAttribute("companyName", companyName);
        return "global/system/monitor-list-supervise";
    }
    /**
     * 重大危险源源长制————企业分布
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("source/company-map")
    public String coMapz(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("page", 0);
//        m.put("limit", 20);//此参数无意义，实际是1000
        m.put("cisDanger", 1);
        m.put("chemic", 1);
        List<Map<String, Object>> mList = monitorMapper.selectGroupByMap(m);
        if(mList.size()==0){
            m.put("userIds", -1);
        }else{
            String[] userIds = new String[mList.size()];
            for(int i = 0; i < mList.size(); i++){
                userIds[i] = mList.get(i).get("userId").toString();
            }
            m.put("userIds", StringUtils.join(userIds, ","));
        }
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
        for (int i = 0; i < list.size(); i++) {
            DynamicParameter<String, Object> item = list.get(i);
            item.put("id", i + 1 + "");
        }
        Integer count = companyMapper.selectCountMap(m);
        String listStr = JSONArray.toJSONString(list);
        String userStr = JSONObject.toJSONString(user);
        model.addAttribute("count", count);
        model.addAttribute("companyList", listStr);
        model.addAttribute("user", userStr);
        return "global/source/company-map";
    }
    /**
     * 重大危险源源长制————隐患排查治理
     */
    @RequestMapping(value = "source/danger-collect")
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
        return "global/source/danger-collect";
    }
    /**
     * 重大危险源源长制————分类统计
     */
    @RequestMapping("source/danger-chart")
    public String dangerChart() {
        return "global/source/danger-chart";
    }
    /**
     * 安全监察监管——高危作业/重大危险源
     * 首页-高危行业
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
            return "global/warning/company-list-gwzy";
        }
        if (cisDanger != null) {//重大危险源
            return "global/warning/company-list-zdwxy";
        }
        return "global/company/company-list";
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
        return "global/warning/lightning-list";
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
        return "global/warning/evaluation-list";
    }
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
        return "global/evaluate/zheng-list";
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
        return "global/notice-list";
    }
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
        return "global/danger/danger-collect-guanzhu";
    }
    /**
     * to修改密码
     */
    @RequestMapping(value = "system/change-psw")
    public String toChangePsw(HttpServletRequest request, Model model) throws Exception {
        return "global/system/passwordSet";
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
        return "global/danger/check-list-cx2";
    }
    /**
     * 高危企业列表
     * 首页-重大危险源
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("company/toZdCompanyList")
    public String toZdCompanyList(HttpServletRequest request,Model model){
        String districtId = request.getParameter("districtId");
        String townId = request.getParameter("townId");
        String villageId = request.getParameter("villageId");
        model.addAttribute("districtId", districtId);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        return "global/newWarn/zdCompanyList";
    }
    /**
     * 高危企业列表
     * 首页-有限（受限）空间作业
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("company/toGwCompanyList")
    public String toGwCompanyList(HttpServletRequest request,Model model){
        String districtId = request.getParameter("districtId");
        String townId = request.getParameter("townId");
        String villageId = request.getParameter("villageId");
        model.addAttribute("districtId", districtId);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        return "global/newWarn/gwCompanyList";
    }
    @RequestMapping("/system/toUpdateWaterParams")
    public String toUpdateWaterParams(HttpServletRequest request,Model model){
        model.addAttribute("waterParams", envirParamsSettingService.geTWaterparamters());
        return "global/paramssetting/watersetting";
    }
    /**
     * 页面调试用
     */
    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, Integer industryId) throws Exception {
        String url = request.getParameter("url");
        //log.error("zhangcl 2019.1.6 /back/test url:"+url);
        //return "back/welcome";
        return url;
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
        return "global/danger/opinion-show";
    }
    /**
     * 执法人员 查询
     */
    @RequestMapping(value = "off-list")
    public String offList(HttpServletRequest request, Model model, String name) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        m.put("name", name);
        List<Map<String, Object>> list = officialsMapper.selectList(m);
        model.addAttribute("list", list);
        model.addAttribute("map", m);
        return "global/other/officials/officials-list";
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
        return "global/evaluate/lawDoc-list";
    }
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
            return "global/danger/opinion-list2";
        }
        return "global/danger/opinion-list";
    }
    /**
     * 政府端隐患排查信息查询 ！！！
     */
    @RequestMapping(value = "check-list3")//flag:3 部门抽查
    public String troubleList2(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer townId, Integer villageId,
                               Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);

        // 根据登录 ID 查询所有属于这个地区中的所有公司信息
        List<Company> companyList = companyMapper.selectAllList(user.getId());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < companyList.size() ; i++) {
            if (i == companyList.size()-1){
                sb.append(companyList.get(i).getUserId());
            }else {
                sb.append(companyList.get(i).getUserId()).append(",");
            }

        }
        String userIds = sb.toString();

        List<Map<String, Object>> list = tCheckMapper.selectAll(userIds);

        if (list.size() != 0){
            model.addAttribute("list", list);
        }

        return "global/danger/check-list1";
    }
    /**
     * 政府端隐患治理信息查询 ！！！
     * */
    @RequestMapping(value = "check-list4")//flag:3 部门抽查
    public String troubleList3(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer townId, Integer villageId,
                               Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);
        // 根据登录 ID 查询所有属于这个地区中的所有公司信息
        List<Company> companyList = companyMapper.selectAllList(user.getId());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < companyList.size() ; i++) {
            if (i == companyList.size()-1){
                sb.append(companyList.get(i).getUserId());
            }else {
                sb.append(companyList.get(i).getUserId()).append(",");
            }

        }
        String userIds = sb.toString();

        List<Map> list = tCheckItemMapper.selectAll(userIds);

        model.addAttribute("list", list);

        return "global/danger/recheck-list";

       /* model.addAttribute("flag", flag);
        model.addAttribute("status", 1);*/
        // 只判断是否以检查出过合格的



        /*for (int i = 0; i < companyList.size(); i++) {

            if (null != status && 1 == status) { //表示的是要查询整改合格的
                list = tCheckItemMapper.selectRecheckListByRecheckStatus(companyList.get(i).getUserId(), 1);
            } else {
                list = tCheckItemMapper.selectRecheckList(companyList.get(i).getUserId());
            }
        }*/


    }
    @RequestMapping(value = "danger-collect")
    public String modelListz(HttpServletRequest request, Integer industryType, Integer townId, Integer villageId, String companyName, Model model, String startTime, String endTime) throws Exception {
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
        return "global/danger/danger-collect";
    }
    /**
     * TODO (隐患数据分析)设置检查表
     */
    @RequestMapping("danger-chart")
    public String dangerChartz() {
        return "company/danger/danger-chart";
    }
    /**
     * 各地企业-政府首页-中间     update wz 2019-02-21
     */
    @RequestMapping(value = "wel")
    public String wel(Model model, CompanyListReqDTO dto, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", dto.getVillageId());
        m.put("townId", dto.getTownId());
        m.put("districtId", dto.getDistrictId());

        model.addAttribute("number", companyMapper.selectIdsByCompany_view_count(dto));//企业数量

        HttpSession session = request.getSession();
        User moveBeforeUser =  (User)session.getAttribute("moveBefore");
        if(moveBeforeUser != null){
            model.addAttribute("moveD",1);
        }
        if(null != dto.getVillageId()) {
            model.addAttribute("name", villageMapper.selectByPrimaryKey(dto.getVillageId()).getName());
//            if(moveBeforeUser != null){
//                model.addAttribute("moveD",1);
//                model.addAttribute("nameBefore", townMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName());
//            }
            log.error("村级账号："+villageMapper.selectByPrimaryKey(dto.getVillageId()).toString());
            model.addAttribute("movUserId", villageMapper.selectByPrimaryKey(dto.getVillageId()).getUserId());
        }

        if(null != dto.getTownId()) {
            model.addAttribute("name", townMapper.selectByPrimaryKey(dto.getTownId()).getName());
//            if(moveBeforeUser != null){
//                model.addAttribute("moveD",1);
//                model.addAttribute("nameBefore", districtMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName());
//            }
            log.error("镇级账号："+townMapper.selectByPrimaryKey(dto.getTownId()).toString());
            model.addAttribute("movUserId", townMapper.selectByPrimaryKey(dto.getTownId()).getUserId());
        }

        if(null != dto.getDistrictId()) {
            model.addAttribute("name", districtMapper.selectByPrimaryKey(dto.getDistrictId()).getName());
//            if(moveBeforeUser != null){
//                model.addAttribute("moveD",1);
//                model.addAttribute("nameBefore",userMapper.selectByPrimaryKey(moveBeforeUser.getId()).getUserName());
//            }
            log.error("区级账号："+districtMapper.selectByPrimaryKey(dto.getDistrictId()).toString());
            model.addAttribute("movUserId", districtMapper.selectByPrimaryKey(dto.getDistrictId()).getUserId());
        }

        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总

        model.addAttribute("count", userService.selectCount(dto, user));

        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("villageId", dto.getVillageId());
        model.addAttribute("townId", dto.getTownId());
        model.addAttribute("districtId", dto.getDistrictId());
        return "global/other/welcome-list";
    }
    /**
     *
     */
    @RequestMapping(value = "welcometrade")
    public String welcometrade(Model model, HttpServletRequest request,Integer tradeId) throws Exception {
        User user = userMapper.selectByPrimaryKey(tradeId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        CompanyListReqDTO dto = new CompanyListReqDTO();
        List<Integer> count = userService.selectCount(dto, user);

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
        return "global/other/welcome";
    }
    /**
     * 发送的消息列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/sendMessageList")
    public String sendMessageList(HttpServletRequest request,Model model){

        return "global/userGroup/sendList";
    }
    /**
     * 收到的消息列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/reviceMessageList")
    public String reviceMessageList(HttpServletRequest request,Model model){

        return "global/userGroup/reviceList";
    }
    /**
     * 群组列表页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/groupIndex")
    public String groupIndex(HttpServletRequest request,Model model){
        User user = getLoginUser(request);
        if(null != user){
            String groupName = request.getParameter("groupName");
            Map<String, Object> params = new HashMap<String,Object>();
            if(!com.alibaba.druid.util.StringUtils.isEmpty(groupName)){
                try {
                    groupName = URLDecoder.decode(URLDecoder.decode(groupName, "utf-8"),"utf-8");
                    params.put("groupName", groupName);
                    model.addAttribute("groupName", groupName);
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage(),e);
                }
            }
            params.put("userId", user.getId());
            List<UserGroup> list = userGroupMapper.getUserGroupList(params);
            model.addAttribute("list", list);
            return "global/userGroup/groupList";
        }
        return "";
    }
    @RequestMapping("addMessages")
    public String addMessages(HttpServletRequest request,Model model){
        User user = getLoginUser(request);
        if(null != user){
            if(user.getUserType() == 5){
                model.addAttribute("propelcompany", 1);
            }
            model.addAttribute("userType", user.getUserType());
            model.addAttribute("loginUserId", user.getId());
            return "global/userGroup/addMessages";
        }
        return "";
    }
    /**
     * 政府端——上一级免密跳转到下一级，只跳转一次 wz add 2019-02-21
     */
    @RequestMapping(value = "move/next")
    public String movez(HttpServletRequest request, Model model,Integer uid) throws Exception {
        //log.error("move/company");
        User user = getLoginUser(request);
        User userM = userMapper.selectByPrimaryKey(uid);
        if(user.getUserType()!= userM.getUserType()){
            HttpSession session = request.getSession();
            //log.error("moveBefore："+user.toString());
            session.setAttribute("moveBefore",user); //跳转前user保存
            //log.error("unMove："+uid);
            user = userMapper.selectByPrimaryKey(uid);
            //log.error("moveOld："+user.toString());
            SessionUtil.setUser(request, user);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        if(user.getUserType() == 6){//区
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
            model.addAttribute("userType", user.getUserType());

        }
        if(user.getUserType() == 3){//镇
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
            model.addAttribute("userType", user.getUserType());

        }
        if(user.getUserType() == 4){//村
            model.addAttribute("name_", villageMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("userType", user.getUserType());
//            if(user.getUserName().equals("港口")) {//港口特殊账号
//                return "gang/main";
//            }
            model.addAttribute("loginUserId", user.getId());

        }
        return "global/main";
    }
    /**
     * 政府端——上一级免密跳转到下一级，只跳转一次  返回  wz add 2019-02-21
     */
    @RequestMapping(value = "back/gov")
    public String back(HttpServletRequest request, Model model) throws Exception {
        //log.error("back/gov");
        HttpSession session = request.getSession();
        User user =  (User)session.getAttribute("moveBefore");
        //log.error("moveBefore："+user.toString());
        if(user == null){//返回后的刷新
            user = getLoginUser(request);
            //log.error("moveBefore："+user.toString());
        }else{
            SessionUtil.setUser(request, user);
            session.removeAttribute("moveBefore");//返回时将moveBefore的session删除
        }
        Map<String, Object> m = new HashMap<String, Object>();
        if(user.getUserType() == 9){//安泰
            model.addAttribute("list", districtMapper.selectDistrict());
            model.addAttribute("list1", tradeMapper.selectTrade());
            model.addAttribute("name_", user.getUserName());
            model.addAttribute("loginUserId", user.getId());
            return "country/main";
        }
        if(user.getUserType() == 6){//区
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
            return "area/main";
        }
        if(user.getUserType() == 3){//镇
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
            return "town/main";
        }
        return "";
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
                               Integer status, Integer flag, Model model,String dmName) throws Exception {

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
        Set set = new LinkedHashSet();
        if(Objects.equals("",dmName)){

        }else{
            m.put("dmName",dmName);
        }

        // 进行判断

        if (setUserId(user, m)) {
            clearVillageTown(m);

            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            //List<Map<String, Object>> list = tCheckMapper.selectList3(m);

            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {
                DynamicParameter<String, Object> id = tCheckMapper.selectCompany((Integer) list.get(i).get("id"));
                list.get(i).put("listM",id);
                sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));

            }
            model.addAttribute("sum", sum);
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
        return "global/danger/check-list";
    }
    /**
     * 重大危险源页面
     */
    @RequestMapping("danger/wei-list")
    public String weiList(Model model, HttpServletRequest request) throws Exception {
        return "global/other/wei-list";
    }
    /**
     * 危化品页面
     */
    @RequestMapping("danger/danger-list")
    public String dangerList(Model model, HttpServletRequest request, String dangerName) throws Exception {
        if (StringUtils.isEmpty(dangerName)) {
            return "global/other/danger-list";
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("dangerName", dangerName);
        model.addAttribute("list", dangerLibMapper.selectDangerList(m));
        model.addAttribute("dangerName", dangerName);
        return "global/other/danger-list";
    }
    /**
     * 易制爆品页面
     */
    @RequestMapping("danger/bao-list")
    public String baoList(Model model, HttpServletRequest request) throws Exception {
        return "global/other/bao-list";
    }
    /**
     * 易制毒化学品页面
     */
    @RequestMapping("danger/du-list")
    public String duList(Model model, HttpServletRequest request) throws Exception {
        return "global/other/du-list";
    }
    /**
     * 职业病危害页面
     */
    @RequestMapping("danger/zhi-list")
    public String zhiList(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("list", fengMapper.selectFengList());
        model.addAttribute("list2", huaMapper.selectHuaList());
        return "global/other/zhi-list";
    }
    /**
     * 危险源信息展示
     */
    @RequestMapping("danger/danger-show")
    public String dangerShow(Model model, HttpServletRequest request) throws Exception {
        return "global/other/danger-show";
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
        return "global/danger/print-danger-collect";
    }
    /**
     * 重大危险源源长制————分类统计——查看企业列表
     */
    @RequestMapping(value = "source/danger-chart-list")//hejy 2019.1.18
    public String companyList(Model model, HttpServletRequest request, CompanyListReqDTO dto, String status, String type) throws Exception {
        User user = getLoginUser(request);
        //设置查询条件
        setUserId2(user, dto);
        dto.setCisDanger("1");
        String industry2 = null;
        if (StringUtils.isNotBlank(dto.getIndustry2_())) {
            industry2 = dto.getIndustry2_();
            if (StringUtils.isNotBlank(dto.getIndustry2_2())) {
                industry2 = industry2 + " > " + dto.getIndustry2_2();
            }
        }
        dto.setIndustry2(industry2);
        String freeze = status;//isFreeze属性用来判断运行状态（当日是否已填写每日检查表）而非是否冻结
        if(StringUtils.isNotBlank(dto.getIsFreeze())){
            freeze = dto.getIsFreeze();
            dto.setIsFreeze(null);
        }
        //获取所有符合条件的user_id
        if(StringUtils.isBlank(dto.getUserIds()) || "-1".equals(dto.getUserIds())){
            Map<String, Object> m = new HashMap<String, Object>();
            setUserId(user, m);
            if(type!=null){
                List<Integer> list = companyMapper.selectByCompany_view(m);
                List<Integer> clist = new ArrayList<Integer>();
                if(type.equals("1")){//非化工液氨使用企业
                    clist = yzLiquidAmmoniaMapper.selectUidsByList(list);
                }else if(type.equals("2")){//粉尘涉爆企业
                    clist = yzStiveExplosionMapper.selectUidsByList(list);
                }else if(type.equals("3")){//高温熔融金属现场运输
                    clist = yzHighTemperatureMapper.selectUidsByList(list);
                }else if(type.equals("4")){//工贸行业有限空间安全作业
                    clist = yzTradeArderMapper.selectUidsByList(list);
                }else{//冶金企业煤气
                    clist = yzMetallurgyMapper.selectUidsByList(list);
                }
                if(clist.size()==0){
                    dto.setUserIds("-1");
                }else{
                    dto.setUserIds(StringUtils.join(clist,","));
                }
            }else{
                m.put("cisDanger", 1);
                List<Map<String, Object>> mList = monitorMapper.selectGroupByMap(m);
                if(mList.size()==0){
                    dto.setUserIds("-1");
                }else{
                    String[] userIds = new String[mList.size()];
                    for(int i = 0; i < mList.size(); i++){
                        userIds[i] = mList.get(i).get("userId").toString();
                    }
                    dto.setUserIds(StringUtils.join(userIds, ","));
                }
            }
        }
        //全部企业列表
        List<Map<String,Object>> comps = new ArrayList<Map<String,Object>>();
        //判断每日检查表是否填写 运行状态列表operatingStatusList
        List<Boolean> operatingStatusList = new ArrayList<Boolean>();
        if( ! dto.getUserIds().equals("-1")){
            comps = companyMapper.selectByDTO(dto);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            boolean operatingStatus = false;
            String title = "重大危险源每日检查表";
            Map<String, Object> c = new HashMap<String, Object>();
            c.put("flag", 1);
            c.put("title", title);
            for(int i=0; i < comps.size(); i++){
                c.put("userIds", comps.get(i).get("userId"));
                List<Map<String, Object>> lists = tCheckMapper.selectList(c);
                for (Map<String, Object> list : lists) {
                    String rt = list.get("realTime").toString();
                    if(date.equals(rt.substring(0,10))){
                        operatingStatus = true;
                        break;
                    }
                }
                operatingStatusList.add(operatingStatus);
                operatingStatus = false;
            }
            //运行状态筛选
            List<Integer> removeList = new ArrayList<Integer>();
            if(freeze != null){
                dto.setIsFreeze(freeze);
                if(freeze.equals("1")){
                    for(int i = 0; i < comps.size(); i++){
                        if(operatingStatusList.get(i) == false){
                            removeList.add(i);
                        }
                    }
                }else{//freeze为"0"
                    for(int i = 0; i < comps.size(); i++){
                        if(operatingStatusList.get(i) == true){
                            removeList.add(i);
                        }
                    }
                }
                if(removeList.size() > 0){
                    for(int i = removeList.size()-1; i >= 0; i--){
                        comps.remove(removeList.get(i).intValue());
                        operatingStatusList.remove(removeList.get(i).intValue());
                    }
                }
            }
            //分页
            model.addAttribute("total", comps.size());
            Integer from = dto.getPage() * dto.getRows();
            Integer end = null;
            end = Math.min(from + dto.getRows(), comps.size());
            comps = comps.subList(from, end);
            operatingStatusList = operatingStatusList.subList(from, end);
        }else{
            model.addAttribute("total", 0);
        }
        model.addAttribute("list",comps);
        model.addAttribute("osl", operatingStatusList);

        if(user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }
        model.addAttribute("sk", request.getParameter("sk"));
        model.addAttribute("dto", dto);

        return "global/source/danger-chart-list";
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
        return "global/warning/examination-list";
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
            return "global/danger/opinion-add";
        }
        Company company = companyMapper.selectByPrimaryKey(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("company", company);
        model.addAttribute("timenow", new Date());
        System.out.println("global/danger/opinion-add" + flag + flag2);
        return "global/danger/opinion-add" + flag + flag2;
    }
    @RequestMapping(value = "off-add")
    public String offAdd(Integer id, Model model) throws Exception {

        if(null != id) {
            model.addAttribute("p", officialsMapper.selectByPrimaryKey(id));
        }
        return "global/other/officials/officials-add";
    }
    /**
     * 执法人员保存
     * 当密码小于三十位数的话就表示是新增/修改密码
     */
    @RequestMapping(value = "off-save")
    public @ResponseBody Result offSave(Officials off, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
      /*  if(off.getPassword().length()<30){ //表示是新密码
            String encryptedPwd = EncryptUtil.encrypt(off.getPassword());
            off.setPassword(encryptedPwd);
        }*/
        if(null == off.getId()) {
            User user = getLoginUser(request);
            off.setDel(0);
            off.setFlag(user.getUserType());
            off.setTime(new Date());
            off.setUid(user.getId());
            officialsMapper.insertSelective(off);
        } else {
            officialsMapper.updateByPrimaryKeySelective(off);
        }
        return result;
    }
    @RequestMapping("/tpAddGroup")
    public String toAddGroup(HttpServletRequest request,Model model){
        User user = getLoginUser(request);
        if(null != user){
            model.addAttribute("utp", user.getUserType());
            String groupId = request.getParameter("groupId");
            if(!com.alibaba.druid.util.StringUtils.isEmpty(groupId)){
                UserGroup group = userGroupMapper.selectByPrimaryKey(groupId);
                if(null != group){
                    model.addAttribute("groupId", group.getId());
                    model.addAttribute("groupName", group.getGroupName());
                }
            }
            return "global/userGroup/addUserGroup";
        }else{
            return "";
        }
    }
    /**
     * 政府用户注销登录
     */
    @RequestMapping("/villageLogout")
    public String villageLogout(HttpServletRequest request) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        String y = request.getServerName();
        if(y.indexOf("kfq") > -1) {
            return "redirect:/fore/login-smaq";
        }
        return "redirect:/global/fore";
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
        if (StringUtils.isNotBlank(level)) {
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
        log.error("level：" + level.toString());
        if (level.equals("红色")) {
            return "global/safety-system/control-list";
        } else {
            return "global/safety-system/control-list1";
        }
    }
    /**
     * 风险研判与承诺公告————安全承诺公告-公告列表
     */
    @RequestMapping(value = "study/promise-list")
    public String promiseList(HttpServletRequest request,Integer uid, Integer type,Model model) throws Exception {
        List<FcPromise> list = new ArrayList<FcPromise>();
        if(type == 0){
            list = fcPromiseMapper.selectByUid(uid);
        }else{//type == 1 查询当月承诺公告
            list = fcPromiseMapper.selectCurrentMonthByUid(uid);
        }
        model.addAttribute("type",type);
        model.addAttribute("list",list);
        return "global/study/promise-list";
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
            return "global/danger/check-add";
        }
        TCompany tc = tCompanyMapper.selectByPrimaryKey(userId);
        if (null == tc) {
            return "redirect:/global/trouble-set?userId=" + userId + "&url="
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
        return "global/danger/check-add2";
    }
    /**
     * 企业自查 侧边栏点击企业自查的
     * TODO 点击行政检查 /village/trouble-set?url=/company/check-list?flag=4%26type=3
     */
    @RequestMapping(value = "trouble-set")
    public String troubleSet(Integer userId, String url, Model model, HttpServletRequest request) throws Exception {
        System.out.println("userId:"+userId);
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
        return "global/danger/danger-list3";
    }
    /**
     * 消息详情页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/messageDetail")
    public String messageDetail(HttpServletRequest request,Model model){
        String messageId = request.getParameter("messageId");
        String from = request.getParameter("from");
        User user = getLoginUser(request);
        if(null != user){
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("messageId", messageId);
            params.put("reviceUserId", user.getId());
            Messages messages = null;
            if("0".equals(from)){
                messages = messagesMapper.selectByPrimaryKey(messageId);
            }else{
                messages = messagesMapper.getMessageByCondition(params);
            }
            if(null != messages){
                MessageToUser toUser = messageToUserMapper.getMessageStatus(params);
                if(null != toUser && toUser.getReadFlag().equals((byte)0)){
                    toUser.setReadFlag((byte)1);
                    toUser.setReadTime(new Date());
                    toUser.setReviceFlag((byte)1);
                    messageToUserMapper.updateInfoByMessageAndUserId(toUser);
                }
                model.addAttribute("message", messages);
                if(!com.alibaba.druid.util.StringUtils.isEmpty(messages.getFiles())){
                    model.addAttribute("files", com.alibaba.fastjson.JSONArray.parseArray(messages.getFiles()));
                }
                model.addAttribute("from", from);
                return "global/userGroup/messageDetail";
            }
        }
        return "";
    }
    /**
     * 获取群组用户信息
     * @param request
     * @return
     */
    @RequestMapping("/getGroupDetail")
    @ResponseBody
    public JSONObject getGroupDetail(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            String groupId = request.getParameter("groupId");
            result.put("list", userGroupListMapper.getGroupUserList(groupId));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
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
        return "global/danger/companyLoad";
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
        return "global/danger/opinion-detail2";
    }

    /**
     * 查询公司
     * @param request
     * @return
     */
    @RequestMapping("/getCompanyByCondition")
    @ResponseBody
    public JSONObject getCompanyByCondition(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String companyName = request.getParameter("companyName"); //获取公司名称
        String villageId = request.getParameter("villageId");  //获取村级id
        String districtId = request.getParameter("districtId"); //获取城市id
        String townId = request.getParameter("townId"); //获取村级id
        User user = getLoginUser(request); //用户登陆
        if(null != user){ //用户不为空
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("start", 0);
            params.put("page", 10);
            if(!com.alibaba.druid.util.StringUtils.isEmpty(companyName)){ //用户名不为空
                try {
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8"); //对用户名进行utf-8编码
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage(),e);
                }
                params.put("companyName", companyName);
            }
            if(!com.alibaba.druid.util.StringUtils.isEmpty(villageId)){ //村级id不为空
                params.put("villageId", villageId);
            }
            if(!com.alibaba.druid.util.StringUtils.isEmpty(districtId)){ //城市id不为空
                params.put("districtId", districtId);
            }
            if(!com.alibaba.druid.util.StringUtils.isEmpty(townId)){ //城镇id不为空
                params.put("townId", townId);
            }
            if (user.getUserType() == 4) { //
                params.put("villageId", user.getId());
            }
            if (user.getUserType() == 3) {
                params.put("townId", user.getId());
            }
            if (user.getUserType() == 6) {
                params.put("districtId", user.getId());
            }
            //result.put("list", userGroupMapper.searchCompanyByCondition(params));
            //在行业端添加信息推送功能 wz add190119
            log.error("params："+params.toString()+"，Type："+ user.getUserType());
            if (user.getUserType() == 10) { //如果是行业级
                params.put("tradeId", user.getId()); //存入id
                result.put("list", userGroupMapper.searchCompanyByConditionTrade(params)); //根据用户ID或者公司名称查询
                //log.error("行业端下传企业名称list："+userGroupMapper.searchCompanyByConditionTrade(params));
            }else if(user.getUserType() == 5){ //如果企业端
                params.put("companyId", user.getId());
                List<Map<String, Object>> list = userGroupMapper.searchCompanyByConditionCompany(params);
                List<Map<String, Object>> list1 = userGroupMapper.searchCompanyByConditionCompanyTrade(params);//如果
                if(list1 != null && list1.size() != 0){
                    list.addAll(list1);
                }
                //log.error("list："+list.toString());
                result.put("list", list);
            }else{
                result.put("list", userGroupMapper.searchCompanyByCondition(params));
            }
        }
        //log.error("result："+result.toString());
        return result;
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/getCompanyByConditionPage")
    @ResponseBody
    public JSONObject getCompanyNames(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String companyName = request.getParameter("companyName"); //获取公司名称
        String villageId = request.getParameter("villageId");  //获取村级id
        String districtId = request.getParameter("districtId"); //获取城市id
        String townId = request.getParameter("townId"); //获取村级id
        String page = request.getParameter("page"); //获取村级i
        System.out.println("Page:"+page);
        User user = getLoginUser(request); //用户登陆
        if (null != user) { //用户不为空
            Map<String, Object> params = new HashMap<String, Object>();
            if (!com.alibaba.druid.util.StringUtils.isEmpty(companyName)) { //用户名不为空
                try {
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"), "utf-8"); //对用户名进行utf-8编码
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage(), e);
                }
                params.put("companyName", companyName);
            }
            if (!com.alibaba.druid.util.StringUtils.isEmpty(villageId)) { //村级id不为空
                params.put("villageId", villageId);
            }
            if (!com.alibaba.druid.util.StringUtils.isEmpty(districtId)) { //城市id不为空
                params.put("districtId", districtId);
            }
            if (!com.alibaba.druid.util.StringUtils.isEmpty(townId)) { //城镇id不为空
                params.put("townId", townId);
            }
            if (user.getUserType() == 4) { //
                params.put("villageId", user.getId());
            }
            if (user.getUserType() == 3) {
                params.put("townId", user.getId());
            }
            if (user.getUserType() == 6) {
                params.put("districtId", user.getId());
            }
            //result.put("list", userGroupMapper.searchCompanyByCondition(params));
            //在行业端添加信息推送功能 wz add190119
            log.error("params："+params.toString()+"，Type："+ user.getUserType());
            if (user.getUserType() == 10) { //如果是行业级
                params.put("tradeId", user.getId()); //存入id
                result.put("list", userGroupMapper.searchCompanyByConditionTrade(params)); //根据用户ID或者公司名称查询
                //log.error("行业端下传企业名称list："+userGroupMapper.searchCompanyByConditionTrade(params));
            }else if(user.getUserType() == 5){ //如果企业端
                params.put("companyId", user.getId());
                List<Map<String, Object>> list = userGroupMapper.searchCompanyByConditionCompany(params);
                List<Map<String, Object>> list1 = userGroupMapper.searchCompanyByConditionCompanyTrade(params);//如果
                if(list1 != null && list1.size() != 0){
                    list.addAll(list1);
                }
                //log.error("list："+list.toString());
                result.put("list", list);
            }else{
                params.put("page", Integer.parseInt(page)*10);
                result.put("list", userGroupMapper.searchCompanyByConditionPage(params));
            }
        }
        //log.error("result："+result.toString());
        return result;
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
        return "global/danger/plan-next2";
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
            /*if(check.getFlag() == 3){
            cgfService.rectificationSave(tr);
            }*/
            tr = tRectificationMapper.selectByCheckId(checkId);
            model.addAttribute("check", check);
            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
            model.addAttribute("itemL1", tCheckItemMapper.selectDangerByCheckId(checkId, 1));
            model.addAttribute("rectification", tr);
        } else {

            model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
            model.addAttribute("itemL1", tCheckItemMapper.selectDangerByCheckId(checkId, 1));
            model.addAttribute("document", doc);
            return "global/danger/opinion-detail2";
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

        log.error("checkid:" + checkId + ",flag:" + flag + ",checkflag:" + check.getFlag());

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
            return "global/danger/opinion-add" + flag + check.getFlag();
        }else {
            // 部门抽查
            if(flag==8){
                flag=1;
            }
            return "global/danger/opinion-add" + flag + check.getFlag();
        }

    }
    /**
     * TODO 检查表隐患 整改详情,
     * 保存检查记录的时候，会走这个一下，但是没有进行保存所有没有发送
     * 但是在查询记录的时候，点击一个整改详情的时候，会
     */
    @RequestMapping(value = "check-rectification")
    public String checkRectification(Integer id, Model model, Integer flag, Integer number,HttpServletRequest request) throws Exception {
        //log.error("checkId："+id);
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        Integer type = tc.getType();
        //log.error("检查表type："+type);

        User user = getLoginUser(request);
        // TODO 这里只保存一条数据,并返回到前端
        List<TRectification> list = tRectificationMapper.selectAlls(id);
        //List<Map> list = tRectificationMapper.selectAlls(id);
       /* if (null != list && list.size() > 0) {
            TRectification rectification = new TRectification();
            rectification.setId(list.get(0).getId());
            rectification.setCheckId(list.get(0).getCheckId());
            rectification.setItem1(list.get(0).getItem1());
            rectification.setItem2(list.get(0).getItem2());
            rectification.setItem3(list.get(0).getItem3());
            rectification.setDeadline(list.get(0).getDeadline());
            rectification.setPlanTime(list.get(0).getPlanTime());
            rectification.setUserId(list.get(0).getUserId());
            rectification.setCreateUser(list.get(0).getCreateUser());
            rectification.setCreateTime(list.get(0).getCreateTime());
            rectification.setDel(list.get(0).getDel());

        }*/

        if(null != list && list.size() > 0){
            model.addAttribute("rectification", list.get(0));
        }

        if((null != list && list.size() > 0)){
            model.addAttribute("rectification", list.get(0));
        }

        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);
        //model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(id, null));

        List<Map<String, Object>> iteml = tCheckItemMapper.selectDangerByCheckId(id, null);
        // 判断他是什么类型的然后再添加数据
        Integer industryType = tc.getIndustryType();
        for (int i = 0; i < iteml.size(); i++) {
            Integer levelId = (Integer) iteml.get(i).get("levelId");
            if(null!=levelId){
                String DangerFlag="";
                if(flag==1&&!Objects.equals(user.getUserName(),tc.getDepart())){
                    DangerFlag = aCompanyManualMapper.selectByPrimaryKey(levelId).getFlag();
                }else{
                    DangerFlag = queryDangerFlag(levelId, industryType);
                }
                iteml.get(i).put("dangerFlag",DangerFlag);
            }
        }
        // 这个是没有用的
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
                //log.error("level1/2/3 : "+level1+"/"+level2+"/"+level3);
            }
        }
        //log.error("tCheckItemMapper条目结果信息2:"+iteml.toString());
        model.addAttribute("itemL", iteml);

        model.addAttribute("number", number);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
        model.addAttribute("flag", flag);
        model.addAttribute("serList", gson.toJson(tItemSeriousMapper.selectbylid(null)));
        model.addAttribute("listM", tCheckMapper.selectCompany(id));
        return "village/danger/opinion-detail";
        /*if (type == 9) {
            return "village/danger/opinion-detailrjcb";
        } else {
            return "village/danger/opinion-detail";
        }*/

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

        if (user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }

        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("sk", request.getParameter("sk"));

        return "global/company/company-tab-detail";
    }
    @RequestMapping("getCompanyByPageCount")
    public int getPageCount(HttpServletRequest request){

        return 0;
    }
    /**
     * 添加行政、委托检查表 flag 2 行政执法 3 委托 4 行政检查 userId 企业id
     * 查询企业
     */
    @RequestMapping(value = "check-add2")
    public String checkAdd2(Integer flag, Integer industryId, Integer userId, Model model, HttpServletRequest request)
            throws Exception {
        User user = getLoginUser(request);
        if (user.getUserType().intValue() == 5) {
            userId = user.getId();
        }
        model.addAttribute("type", 3);// 默认临时检查
        model.addAttribute("flag", flag);
        if (null == userId) {// 先选择企业
            return "global/danger/check-add3";
        }
        model.addAttribute("userId", userId);
        TCompany tc = tCompanyMapper.selectByPrimaryKey(userId);
        if (null == tc) {
            return "redirect:/global/trouble-set?userId=" + userId + "&url="
                    + URLEncoder.encode("/global/check-add2?flag=" + flag + "&userId=" + userId, "utf-8");
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
        return "global/checkModel/model-add6";
    }
    /**
     * TODO model-add.jsp 页面跳转 由(model-addOld改写)
     *
     * @return
     */
    @RequestMapping(value = "getCheckModelBasic")
    public String getCheckModelBasic(Integer flag,Integer userId, Model model) {
        model.addAttribute("flag", flag);
        /* return "company/checkModel/model-add";*/
        model.addAttribute("list", null);
        model.addAttribute("dL", null);
        model.addAttribute("userId", userId);
        return "global/checkModel/model-add6";
    }

    /*
     * TODO 根据 checkType 查询所有的 level2 的信息(废弃)
     *
     */
    @RequestMapping(value = "select-all-level1")
    public String selectAllLevel1(Integer userId, Integer checkType, String industry,Integer lxType, HttpServletRequest request, Model model, String tableName, Integer flag) {
        User user = userMapper.selectByPrimaryKey(userId);
        System.out.println("---------------");
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
        model.addAttribute("userId", userId);

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
        return "global/checkModel/model-add6";
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
            Integer userId = (Integer)map.get("userId");
            String [] str = {"日常","定期","季节","其他","综合"};
            if (null == a || "".equals(a)) {
                result.setStatus("1");
                result.setMess("页面保存数据为空");
                return result;
            }

            String[] split = a.split(",");

            List<Map<String, String>> inputItems = (List<Map<String, String>>) map.get("cusCheckItemList");

            User user = userMapper.selectByPrimaryKey(userId);
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
    public String checkNext(Integer id, Integer type, Model model, HttpServletRequest request, Integer userId) throws Exception {
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



        //
        model.addAttribute("userId",userId);
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
        String departName = Arrays.toString(objects);

        model.addAttribute("departName",departName);
        model.addAttribute("jcL", officialsMapper.selectList(m));// 执法人员
        log.error("plan-next：" + 6);
        if (type == 9) {
            return "village/danger/plan-next1";
        } else {
            return "village/danger/plan-next";
        }

    }

    @RequestMapping(value = "model-list-main")
    public String modelListMain(HttpServletRequest request, Integer userId, Model model, Integer flag, Integer status, Integer plan) throws ParseException {

        User user = userMapper.selectByPrimaryKey(userId);

        Map<String, Object> map1 = new LinkedHashMap<String, Object>();
        map1.put("level1",user.getUserName());

        List<Map<String, Object>> jiChuItem = new ArrayList<>();
        jiChuItem.add(map1);
        jiChuItem.addAll(aCompanyManualMapper.findJiChuItem(user.getId(), "基础管理"));

        Map<String, Object> map2 = new LinkedHashMap<String, Object>();
        map2.put("level1", user.getUserName());
        List<Map<String, Object>> XianChangItem = new ArrayList<>();
        XianChangItem.add(map2);
        XianChangItem.addAll(aCompanyManualMapper.findJiChuItem(user.getId(), "现场管理"));

        for (Map<String, Object> XianChangMap : XianChangItem) {
            Map<Integer, Integer> Xianmap = new LinkedHashMap<Integer, Integer>();
            Xianmap.put(5, 0);
            Xianmap.put(1, 0);
            Xianmap.put(2, 0);
            /*Xianmap.put(3, 0);*/
            Xianmap.put(4, 0);
            String level1 = (String) XianChangMap.get("level1");
            List<Integer> Xiantypes = tModelMapper.selecttype(level1, user.getId(), 2, flag);
            for (Integer integer : Xiantypes) {
                Xianmap.put(integer, 1);
            }
            XianChangMap.put("array", Xianmap);
            Integer count =  tModelMapper.selectDangerCountByDepartAndUserId(level1, user.getId(),2);
            if(null==count){
                XianChangMap.put("count", 0);
            }else{
                XianChangMap.put("count", count);
            }

        }

        for (Map<String, Object> jiChuMap: jiChuItem) {
            Map<Integer, Integer> Jimap = new LinkedHashMap<Integer, Integer>();
            Jimap.put(5, 0);
            Jimap.put(1, 0);
            Jimap.put(2, 0);
            /*Jimap.put(3, 0);*/
            Jimap.put(4, 0);
            String level1 = (String) jiChuMap.get("level1");
            List<Integer> Jitypes = tModelMapper.selecttype(level1, user.getId(), 1, flag);
            for (Integer integer : Jitypes) {
                Jimap.put(integer, 1);
            }
            Integer count =  tModelMapper.selectDangerCountByDepartAndUserId(level1, user.getId(),1);
            if(null==count){
                jiChuMap.put("count", 0);
            }else{
                jiChuMap.put("count", count);
            }

            jiChuMap.put("array", Jimap);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> list = zzjgDepartmentMapper.selectHiddenPlan(user.getId());

        List<Map<String, Object>> list1 = hiddenPlanMapper.selectCountAll(user.getId());

        List<Map<String, Object>> hiddenPlanList = hiddenPlanMapper.findDpid(0,user.getId());

        if (hiddenPlanList.size() == 0){
            HiddenPlan hiddenPlan = new HiddenPlan();
            hiddenPlan.setUid(user.getId());
            hiddenPlan.setDpid(0);
            hiddenPlan.setUpdate_time(new Date());
            hiddenPlan.setCreate_time(new Date());
            hiddenPlan.setSyn_month(0);
            hiddenPlan.setSyn_year(0);
            hiddenPlan.setSyn_ratio("0:0");
            hiddenPlan.setEve_month(0);
            hiddenPlan.setEve_year(0);
            hiddenPlan.setEve_ratio("0:0");
            hiddenPlan.setReg_month(0);
            hiddenPlan.setReg_year(0);
            hiddenPlan.setReg_ratio("0:0");
            hiddenPlan.setSea_month(0);
            hiddenPlan.setSea_year(0);
            hiddenPlan.setSea_ratio("0:0");
            hiddenPlan.setEls_month(0);
            hiddenPlan.setEls_year(0);
            hiddenPlan.setEls_ratio("0:0");
            hiddenPlan.setBas_month(0);
            hiddenPlan.setBas_year(0);
            hiddenPlan.setBas_ratio("0:0");
            hiddenPlan.setTotal_count(0);
            hiddenPlan.setTotal_ratio("0:0");

            Integer a = hiddenPlanMapper.insert(hiddenPlan);

            List<Map<String, Object>> hiddenPlanList1 = hiddenPlanMapper.findDpid(0,user.getId());

            model.addAttribute("hiddenPlanList",hiddenPlanList1);
        }else {

            model.addAttribute("hiddenPlanList",hiddenPlanList);
        }

        model.addAttribute("data",sdf.format(new Date()));
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("jiChuItem", jiChuItem);
        model.addAttribute("xianChangItem", XianChangItem);
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("plan", plan);
        model.addAttribute("list1",list1);
        model.addAttribute("name","合计");
        return "company/danger/model-list-main";

    }


}
