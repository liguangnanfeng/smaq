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
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.simple.JSONArray;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.*;
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
            // 判断账号是否新三州账号
            if(35346==user.getId()){
                result.setStatus("1");
                result.setMap("message", "该账号为特钢集团, 请用特钢登陆页登录");
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
            model.addAttribute("list", districtMapper.selectDistrict());//查询区列表
            model.addAttribute("list1", tradeMapper.selectTrade());//查询港口账号
            model.addAttribute("name_", user.getUserName());//用户名称
        }
        if (userType == 6) { //县级市
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);//设置镇级
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
                return "tradeclique/clique-main";
            }

        }
        return "global/main";
    }

    /**
     * 查询
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("selectBranch")
    public String selectBranch(HttpServletRequest request, Model model){
        User user = getLoginUser(request);
        Integer userType = user.getUserType(); //获取用户类型
        model.addAttribute("loginUserId", user.getId());
        model.addAttribute("userType", userType);
        if (userType == 9) {  //地市级
            model.addAttribute("list", districtMapper.selectDistrict());//查询区列表
            model.addAttribute("list1", tradeMapper.selectTrade());//查询港口账号
            model.addAttribute("name_", user.getUserName());//用户名称
        }
        if (userType == 6) { //县级市
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);//设置镇级
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
            model.addAttribute("userType", userType);
            model.addAttribute("name_", trade.getName());
            model.addAttribute("userId", trade.getUserId());
            if (trade.getIsClique() == 1) { //跳转到总部企业
                return "tradeclique/clique-main";
            }

        }
        return "global/other/tab-biaozhun2";
    }

    /**
     * 政府首页-欢迎页
     */
    @RequestMapping(value = "/welcome")
    public String welcome(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = null;
        ids = basicFindAllIds(user, ids);
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        if (null == ids || ids.size() == 0){
            model.addAttribute("counts",0);
            model.addAttribute("counts1",0);
            model.addAttribute("dangerCount",0);
        }else {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = df.format(new Date().getTime()-15*24*60*60*1000);

            Date startTime1 = df.parse(startTime);
            Date endTime = new Date();

            Integer counts = tCheckMapper.findAllCounte(sb.toString(),startTime1,endTime); // 排查数据
            Integer counts1 = tCheckItemMapper.findAllCounte(sb.toString()); // 治理数据

            Map<String, Object> m1 = new HashMap<String, Object>();
            m1.put("d", 1);
            m1.put("status", 2);
            m1.put("userId", sb.toString());
            List<Map<String, Object>> list11 = tCheckItemMapper.findACompanyAllList(m1);
            List<Map<String, Object>> list22 = tCheckItemMapper.findDangerAllList(m1);
            List<Map<String, Object>> list33 = tCheckItemMapper.findLevelAllList(m1);
            model.addAttribute("dangerCount", list11.size() + list22.size() + list33.size()); // 未整改
            model.addAttribute("counts",counts);
            model.addAttribute("counts1",counts1);
        }

        if (user.getUserType() == 9) { //地级市
            Map<String, Object> m = new HashMap<String, Object>();
            setUserId(user, m);

            CompanyListReqDTO dto = new CompanyListReqDTO();
            List<Integer> count = userService.selectCount(dto, user);
            model.addAttribute("number", companyMapper.selectIdsByCompany_view_count(dto));//企业数量

            model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
            model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总

            model.addAttribute("mc", monitorMapper.selectCount(m));//监控的个数
            model.addAttribute("count", count);

            model.addAttribute("loginUserId", user.getId());

            model.addAttribute("name", user.getUserName());
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
            model.addAttribute("name", user.getUserName());

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
            model.addAttribute("name", user.getUserName());
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
            model.addAttribute("name", user.getUserName());

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
            model.addAttribute("name", user.getUserName());
        }
        model.addAttribute("userType", user.getUserType());
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
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);//查询所属下的所有乡村
            model.addAttribute("villagelist", villagelist);
        }
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));//查询行业列表
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
     *
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
        model.addAttribute("flage", 1);
        //排除特钢
        if(35346 == uid){
            Trade trade = tradeMapper.selectByPrimaryKey(uid);
            //log.error(trade.toString());
            model.addAttribute("name_", trade.getName());
            model.addAttribute("userId", trade.getUserId());
            if (trade.getIsClique() == 1) { //跳转到总部企业
                return "steel/clique-main";
            }
            return "trade/main";
        }
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
        //setUserId(user, m);
        m.put("flag", flag);
        m.put("status", status);
        m.put("companyName", companyName);
        m.put("d", 1);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = null;
        ids = basicFindAllIds(user, ids);
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        m.put("userId", sb.toString());

        List<Map<String, Object>> list1 = tCheckItemMapper.findACompanyAllList(m);
        List<Map<String, Object>> list2 = tCheckItemMapper.findDangerAllList(m);
        for (int i = 0; i < list2.size(); i++) {
            Integer checkId = (Integer) list2.get(i).get("checkId");
            list2.get(i).put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
        }
        List<Map<String, Object>> list3= tCheckItemMapper.findLevelAllList(m);
        for (int i = 0; i < list3.size(); i++) {
            Integer checkId = (Integer) list3.get(i).get("checkId");
            list3.get(i).put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
        }
        m.put("status", 2);
        List<Map<String, Object>> list11 = tCheckItemMapper.findACompanyAllList(m);
        List<Map<String, Object>> list22 = tCheckItemMapper.findDangerAllList(m);
        List<Map<String, Object>> list33 = tCheckItemMapper.findLevelAllList(m);
        model.addAttribute("list_s2", list11.size() + list22.size() + list33.size()); // 未整改

        m.put("status", 3);
        List<Map<String, Object>> list111 = tCheckItemMapper.findACompanyAllList(m);
        List<Map<String, Object>> list222 = tCheckItemMapper.findDangerAllList(m);
        List<Map<String, Object>> list333 = tCheckItemMapper.findLevelAllList(m);
        model.addAttribute("list_s3", list111.size() + list222.size() + list333.size());

        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        //model.addAttribute("list", list);
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        model.addAttribute("flag", flag);
        model.addAttribute("host", host);
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
     * create by  : 小明！！！
     * description: TODO    风险分级管控 —— 风险数据分析 -- 饼状图数据内容表格
     * create time: 2019/9/12 15:30
     */
    @RequestMapping(value = "all-statistics-list")
    public String controlData(HttpServletRequest request,Model model) throws Exception {
        User user = getLoginUser(request);

        List<Map<String,Object>> list = aCompanyManualMapper.findControlData(user.getId(), user.getUserType());

        for (int i = 0; i < list.size(); i++) {

            Integer red = aCompanyManualMapper.totalize((Integer) list.get(i).get("user_id"),"红色");
            list.get(i).put("red",red);

            Integer orange = aCompanyManualMapper.totalize((Integer) list.get(i).get("user_id"),"橙色");
            list.get(i).put("orange",orange);

            Integer yellow = aCompanyManualMapper.totalize((Integer) list.get(i).get("user_id"),"黄色");
            list.get(i).put("yellow",yellow);

            Integer blue = aCompanyManualMapper.totalizeBlue((Integer) list.get(i).get("user_id"));
            list.get(i).put("blue",blue);
        }

        model.addAttribute("list",list);

        return "global/safety-system/all-statistics-list";

    }


    /**
     * create by  : 小明！！！
     * description: TODO    风险分级管控 —— 风险数据分析 -- 数据内容表格
     * create time: 2019/9/12 15:30
     */
    @RequestMapping(value = "all-statistics-industry")
    public String controlDataIndustry(HttpServletRequest request,Model model) throws Exception {
        User user = getLoginUser(request);
        List<Map<String,Object>> list = companyMapper.selectIndustrs();
        List<Integer> ids = null;
        ids = basicFindAllIds(user, ids);
        StringBuilder sb = new StringBuilder();

        if (null == ids || ids.size() == 0){

            return "global/evaluate/globalError";

        }else if (null != ids && ids.size() != 0){

            for (int i = 0; i < ids.size() ; i++) {
                if (i == ids.size()-1){
                    sb.append("'").append(ids.get(i)).append("'");
                }else {
                    sb.append("'").append(ids.get(i)).append("',");
                }
            }

            for (int i = 0; i < list.size(); i++) {

                Integer number1 = companyMapper.selectDangerAll((String)list.get(i).get("name"),"红色",sb.toString());
                list.get(i).put("number1",number1);

                Integer number2 = companyMapper.selectDangerAll((String)list.get(i).get("name"),"橙色",sb.toString());
                list.get(i).put("number2",number2);

                Integer number3 = companyMapper.selectDangerAll((String)list.get(i).get("name"),"黄色",sb.toString());
                list.get(i).put("number3",number3);

                Integer number4 = companyMapper.selectDangerBlue((String)list.get(i).get("name"),sb.toString());
                list.get(i).put("number4",number4);
            }

        }


        model.addAttribute("list",list);

        return "global/safety-system/all-statistics-list";

    }

    private List<Integer> basicFindAllIds(User user, List<Integer> ids) {
        if (user.getUserType() == 4){ // 村级账户
            ids = companyMapper.selectCompanyIdVillage(user.getId());
        }else if (user.getUserType() == 3){ // 镇级账户
            ids = companyMapper.selectTownCompanyId(user.getId());
        }else if (user.getUserType() == 6){ // 区级账户
            ids = companyMapper.selectDistrictCompanyId(user.getId());
        }else if (user.getUserType() == 10){ // 惠山化工账号
            ids = companyMapper.selectTidCompanyId(user.getId());
        }else if(user.getUserType() == 9){
            ids = companyMapper.selectAll();
        }
        return ids;
    }


    /**
     * create by  : 小明！！！
     * description: TODO    风险分级管控 ——  分级管控运行
     * create time: 2019/9/12 15:30
     */
    @RequestMapping(value = "/safety-system/control-operation")

    public String realTimeMonitoring(HttpServletRequest request,Model model,CompanyListReqDTO dto, Integer control) throws Exception {

        User user = getLoginUser(request);

        List<Map<String,Object>> list = tCheckItemMapper.findCompany(user.getId(), user.getUserType(),control);
        // 4 村  3 镇  6 区  7 市
        if (null == list || list.size() == 0){
            
        }else {
            addFlag(user, list);
        }
        model.addAttribute("list",list);
        model.addAttribute("userType",user.getUserType());
        model.addAttribute("control", control);
        return "global/safety-system/check-company";

    }

    private void addFlag(User user, List<Map<String, Object>> list) {
        for (int i = 0; i < list.size(); i++) {  // 1.可升可取; 2.不可升不可取; 3.可升不可取; 4.不可升可取

            if (null == (Integer)list.get(i).get("is_controls")){

                list.get(i).put("flag",3);

            }else if (null != (Integer)list.get(i).get("is_controls") ){
                if (user.getUserType() == 7){ // 如何登录的是 市级 账户
                    if ((Integer)list.get(i).get("is_controls") == 7){
                        list.get(i).put("flag",4);
                    }else if ((Integer)list.get(i).get("is_controls") == 6 || (Integer)list.get(i).get("is_controls") == 3 || (Integer)list.get(i).get("is_controls") == 4) {
                        list.get(i).put("flag",3);
                    }else {
                        list.get(i).put("flag",3);
                    }
                }

                if (user.getUserType() == 6){ // 如何登录的是 区级 账户
                    if ((Integer)list.get(i).get("is_controls") == 7){
                        list.get(i).put("flag",2);
                    }else if ((Integer)list.get(i).get("is_controls") == 6){
                        list.get(i).put("flag",4);
                    }else if ((Integer)list.get(i).get("is_controls") == 3 || (Integer)list.get(i).get("is_controls") == 4){
                        list.get(i).put("flag",3);
                    }else {
                        list.get(i).put("flag",3);
                    }
                }

                if (user.getUserType() == 3){ // 如何登录的是 镇级 账户
                    if ((Integer)list.get(i).get("is_controls") == 7){
                        list.get(i).put("flag",2);
                    }else if ((Integer)list.get(i).get("is_controls") == 6){
                        list.get(i).put("flag",2);
                    }else if ((Integer)list.get(i).get("is_controls") == 3){
                        list.get(i).put("flag",4);
                    }else if ((Integer)list.get(i).get("is_controls") == 4){
                        list.get(i).put("flag",3);
                    }else {
                        list.get(i).put("flag",3);
                    }
                }

                if (user.getUserType() == 4){ // 如何登录的是 村级 账户
                    if ((Integer)list.get(i).get("is_controls") == 7){
                        list.get(i).put("flag",2);
                    }else if ((Integer)list.get(i).get("is_controls") == 6){
                        list.get(i).put("flag",2);
                    }else if ((Integer)list.get(i).get("is_controls") == 3){
                        list.get(i).put("flag",2);
                    }else if ((Integer)list.get(i).get("is_controls") == 4){
                        list.get(i).put("flag",4);
                    }else if ((Integer)list.get(i).get("is_controls") == 0){
                        list.get(i).put("flag",3);
                    }else {
                        list.get(i).put("flag",3);
                    }
                }
            }
        }
    }

    /**
     * create by  : 小明！！！
     * description: TODO  是否是本级管控
     * create time: 2019/9/16 16:23
     */
    @RequestMapping(value = "user-isControls")
    public @ResponseBody
    Result userisControls(HttpServletRequest request, Integer userId, Integer flag) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company company = new Company();
        company.setUserId(userId);

        if (user.getUserType() != 10){
            if (flag == 1){
                company.setIsControls(user.getUserType());
            }else if (flag == 0){
                company.setIsControls(0);
            }
        }else if (user.getUserType() == 10){
            if (flag == 1){
                company.setIsIndustrys(user.getUserType());
            }else if (flag == 0){
                company.setIsIndustrys(0);
            }
        }
        companyMapper.updateByPrimaryKeySelective(company);

        return result;
    }



    /**
     *
     * create by  : 小明！！！
     * description: TODO    风险分级管控 —— 各类风险分布
     * create time: 2019/9/12 15:30
     */
    @RequestMapping(value = "/safety-system/all-risk-map")
    public String controlDistribution(HttpServletRequest request,Model model) throws Exception {
        User user = getLoginUser(request);

        model.addAttribute("longitude",user.getLongitude());
        model.addAttribute("latitude",user.getLatitude());

        List<Map<String,Object>> list1 = aCompanyManualMapper.findCoordinate(user.getId(), user.getUserType(),"红色");

        List<Map<String,Object>> list2 = aCompanyManualMapper.findCoordinate(user.getId(), user.getUserType(),"橙色");

        List<Map<String,Object>> list3 = aCompanyManualMapper.findCoordinate(user.getId(), user.getUserType(),"黄色");

        List<Map<String,Object>> list4 = aCompanyManualMapper.findCoordinateBlue(user.getId(), user.getUserType());

        model.addAttribute("list1",list1);
        model.addAttribute("list2",list2);
        model.addAttribute("list3",list3);
        model.addAttribute("list4",list4);

        return "global/safety-system/all-risk-map";

    }


    /**
     * 安全风险管控-企业列表
     */
    /*@RequestMapping(value = "safety-system/control-listmenu")
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
    }*/

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
            System.out.println(dto);
            List<Integer> allUserIds = companyMapper.selectIdsByCompany_view(dto);
            System.out.println(allUserIds);
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
        //System.out.println(monitorMapper.selectGroupByMap(m));
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
        model.addAttribute("list1",list);
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

        return "global/danger/check-list2";
    }
    /**
     * 政府端隐患治理信息查询 ！！！
     * */
    @RequestMapping(value = "check-list4")//flag:3 部门抽查
    public String troubleList3(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer townId, Integer villageId,
                               Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);

        List<Map<String,Object>> map = tCheckItemMapper.findALL(user.getId(), user.getUserType());
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < map.size() ; i++) {
            if (i == map.size()-1){
                sb.append(map.get(i).get("user_id"));
            }else {
                sb.append(map.get(i).get("user_id")).append(",");
            }

        }

        String userIds = sb.toString();
        List<Map> list = new ArrayList<>();
        if(userIds.length()!=0) {
            list = tCheckItemMapper.selectAll(userIds);
        }

        model.addAttribute("list", list);

        return "global/danger/recheck-list";
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
     * create by  : 小明！！！
     * description: TODO 查询该范围内的所有企业信息
     * create time: 2019/9/9 11:03
     */
    @RequestMapping(value = "check-company")
    public String checkCompany(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer townId, Integer villageId,
                               Integer status, Integer flag, Model model,String dmName,Integer button) throws Exception {

        User user = getLoginUser(request);
        List<Map<String, Object>> list = tCheckItemMapper.findNumbers(user.getId(),user.getUserType());

        model.addAttribute("list",list);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("type",type);
        model.addAttribute("villageId",villageId);
        model.addAttribute("townId",townId);
        model.addAttribute("dmName",dmName);
        model.addAttribute("status",status);
        model.addAttribute("button",button);

        return "global/company/danger/check-company";
    }




    /**
     * create by  : 小明！！！
     * description: TODO  排查治理记录 隐患排查记录(只需要已经检查过的,没有不合格记录的)
     * user. userType : 管理类型  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     * create time: 2019/8/22 16:55
     */
    @RequestMapping(value = "check-list")//flag:3 部门抽查
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer townId, Integer villageId,
                               Integer status, Integer flag, Model model,String dmName,Integer uid,Integer button) throws Exception {

        User user = null;
        if (null == uid){
            user = getLoginUser(request);
        }else if (null != uid){
            user = userMapper.selectByPrimaryKey(uid);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        if (null == button){
            button = 1;
        }
        if (null == title || title.length() == 0){
            title = null;
        }
        if (null == dmName || dmName.length() == 0){
            dmName = null;
        }
        if (null == companyName || companyName.length() == 0){
            companyName = null;
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
        m.put("companyName", dmName); // null
        m.put("status", status); //状态  null
        Set set = new LinkedHashSet();
        if(Objects.equals("",dmName)){

        }else{
            m.put("dmName",dmName);
        }
        // 进行判断
        if (setUserId(user, m)) {
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
                m.put("flag",1);
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
        model.addAttribute("uid",uid);
        model.addAttribute("set",set);
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("button", button);
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
            return "global/company/danger/check-list";
        }
        return "global/company/checkModel/check-list";
    }


    /**
     * create by  : 小明！！！
     * description: TODO 隐患排查 查看详情
     * create time: 2019/9/23 9:22
     */
    @RequestMapping(value = "check-detail")
    public String checkDetail(Integer id, Model model, Integer jcxq, HttpServletRequest request, Integer flag, Integer number, Integer uid) throws Exception {

        User loginUser = userMapper.selectByPrimaryKey(uid);
        // 根据id查询的是检查表信息
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        Integer type = tc.getType();
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(id);
        String name = tc.getDapartContact();
        //设置名称
        if (null == tc.getDapartContact() || tc.getDapartContact().matches("[0-9]{1,}") || "".equals(name)) {
            // 表示没有被检查人员 根据部门名称获取这个部门的被检查人员然后随便抓一个
            List<Integer> integers = tCheckItemMapper.selectLevelIdByCheckId(id);
            if (null != integers && integers.size() > 0) {
                // 这里进行名称的获取,进行全部循环,获取数据的方式,在数据库中进行查询
                List<String> list = new ArrayList<String>();
                for (Integer integer : integers) {
                    if (null != integer) {
                        ACompanyManual aCompanyManual = aCompanyManualMapper.selectByPrimaryKey(integer);
                        if (null != aCompanyManual && null != aCompanyManual.getFjgkfzr()) {
                            list.add(aCompanyManual.getFjgkfzr());
                        }
                    }
                }
                if (list.size() == 0) {
                    name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                } else {
                    name = list.get(0);
                    tc.setCheckCompany(name);
                }
                name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
            } else {
                name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
            }
        }
        model.addAttribute("partL", partL);
        List<Map<String, Object>> iteml = null;
        if (flag == 1) {
            iteml = tCheckMapper.selectLevels(id);
            for (int i = 0; i < iteml.size(); i++) {
                if (loginUser.getUserName().equals(iteml.get(i).get("depart"))) {
                    TCheck tCheck = tCheckMapper.selectByPrimaryKey(id);
                    if (tCheck.getIndustryType() == 1) { // 基础
                        iteml = tCheckMapper.selectAllLevel(id);
                        break;
                    } else if (tCheck.getIndustryType() != 1) { // 现场
                        iteml = tCheckMapper.selectAllDanger(id);
                        break;
                    }
                }
            }

        } else if (flag != 1) {
            // 根据 ID 查询对应的数据 是 基础 还是 现场
            TCheck tCheck = tCheckMapper.selectByPrimaryKey(id);
            if (tCheck.getIndustryType() == 1) { // 基础
                iteml = tCheckMapper.selectAllLevel(id);
            } else if (tCheck.getIndustryType() != 1) { // 现场
                iteml = tCheckMapper.selectAllDanger(id);
            }
        }
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
        model.addAttribute("check", tc);
        model.addAttribute("flag", tc.getFlag());
        model.addAttribute("itemL", iteml);
        model.addAttribute("user", loginUser);

        if(null==number){
            Integer count = tCheckMapper.selectHiddenDangerNumber(id);
            if(null==count){
                count = 0;
            }
            model.addAttribute("number", count);
        }else{
            model.addAttribute("number", number);
        }
        Integer integer = tCheckMapper.selectHiddenDangerNumber2(id);

        if (null == name || "".equals(name)) {
            name = companyMapper.selectByPrimaryKey(loginUser.getId()).getSafety();
        }
        model.addAttribute("dangerInteger",integer);
        model.addAttribute("name", name);
        // 根据检查记录的id获取详细的信息
        if (null != tc.getLatitude() && null != tc.getLongitude()) {
            model.addAttribute("latitude", tc.getLatitude());
            model.addAttribute("longitude", tc.getLongitude());
        } else {
            model.addAttribute("latitude", null);
            model.addAttribute("longitude", null);
        }
        // 判断是否整改复查过
        TRectification tRectification = tRectificationMapper.selectByCheckId(id);
        if(null==tRectification){
            model.addAttribute("is_re",0);
        }else{
            model.addAttribute("is_re",1);
        }
        model.addAttribute("uid",uid);
        model.addAttribute("listM", tCheckMapper.selectCompany(id));

        return "global/company/checkModel/plan-detail";
    }


    /**
     * create by  : 小明！！！
     * description: TODO    行政文书
     * create time: 2019/9/23 11:46
     */
    @RequestMapping("danger/opinion-list")
    public String opinionList(Model model, String companyName, HttpServletRequest request, Integer type, Integer flag2, Integer uid)
            throws Exception {

        User user = null;
        if (null == uid){
            user = getLoginUser(request);
        }else if (null != uid){
            user = userMapper.selectByPrimaryKey(uid);
        }
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
     * create by  : 小明！！！
     * description: TODO    政府端 检查详情 整改意见书
     * create time: 2019/9/23 9:33
     */
    @RequestMapping(value = "check-rectification")
    public String checkRectification(HttpServletRequest request, Integer id, Model model, Integer flag, Integer number,Integer uid) throws Exception {

        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        Integer type = tc.getType();
        User user = userMapper.selectByPrimaryKey(uid);
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

        return "global/company/checkModel/opinion-detail";
    }

    /**
     * create by  : 小明！！！
     * description: TODO    政府端 检查详情 整改意见书(已经有复查)
     * create time: 2019/9/23 9:56
     */
    @RequestMapping(value = "recheck-detail")
    public String recheckDetail(Integer checkId, Model model, Integer flag, Integer number) throws Exception {
        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        List<Map> maps = tCheckItemMapper.selectAllByCheckId(checkId);
        List<TRecheck> tRechecks = tRecheckMapper.selectByCheckId(checkId);
        Integer id2 = tRechecks.get(0).getId();
        for (int i = 0; i < maps.size(); i++) {
            // 这边就不需要记性保存了 因为如果有数据直接查询然后在进行保存
            TRecheckItem id1 = tRecheckItemMapper.selectByCheckItemId((Integer) maps.get(i).get("id"));
            if (null != id1) {
                // 表示是新添加的数据不需要进行保存
            } else {
                id1 = new TRecheckItem();
                id1.setCheckItemId((Integer) maps.get(i).get("id"));
                id1.setStatus((Integer) maps.get(i).get("status"));
                id1.setFile((String) maps.get(i).get("recheckFile"));
                id1.setRecheckId(id2);
                id1.setMemo((String) maps.get(i).get("memo"));
                id1.setDeadline((Date) maps.get(i).get("deadline"));
                tRecheckItemMapper.insertSelective(id1);
            }
        }
        model.addAttribute("number", number);
        model.addAttribute("flag", flag);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));

        List<Map<String, Object>> maps1 = tRecheckItemMapper.selectByCheckId(checkId);
        model.addAttribute("recheckList", tRechecks);
        model.addAttribute("itemList", maps1);
        return "global/company/checkModel/check-fudetail";
    }

    /**
     * create by  : 小明！！！
     * description: TODO    政府端 复查详情
     * create time: 2019/9/23 10:31
     */
    @RequestMapping(value = "fuchadetail")
    public String fuchadetail(HttpServletRequest request,Model model,Integer checkId, Integer flag, Integer uid, Integer number) {
        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        List<Map> maps = tCheckItemMapper.selectAllByCheckId(checkId);
        List<TRecheck> tRechecks = tRecheckMapper.selectByCheckId(checkId);

        Integer id2 = tRechecks.get(0).getId();
        for (int i = 0; i < maps.size(); i++) {
            // 这边就不需要记性保存了 因为如果有数据直接查询然后在进行保存
            TRecheckItem id1 = tRecheckItemMapper.selectByCheckItemId((Integer) maps.get(i).get("id"));
            if (null != id1) {
                // 表示是新添加的数据不需要进行保存
            } else {
                id1 = new TRecheckItem();
                id1.setCheckItemId((Integer) maps.get(i).get("id"));
                id1.setStatus((Integer) maps.get(i).get("status"));
                id1.setFile((String) maps.get(i).get("recheckFile"));
                id1.setRecheckId(id2);
                id1.setMemo((String) maps.get(i).get("memo"));
                id1.setDeadline((Date) maps.get(i).get("deadline"));
                tRecheckItemMapper.insertSelective(id1);
            }
        }
        model.addAttribute("number", number);
        model.addAttribute("flag", flag);
        model.addAttribute("checkId", checkId);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));

        List<Map<String, Object>> maps1 = tRecheckItemMapper.selectByCheckId(checkId);
        model.addAttribute("recheckList", tRechecks);
        model.addAttribute("itemList", maps1);
        return "global/company/checkModel/fuchadetail";
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
        return "redirect:/fore/global";
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
        model.addAttribute("ind1", tIndustryMapper.selectByPrimaryKey(tc.getIndustry1()));// 基础检查类别
        if (StringUtils.isNotEmpty(tc.getIndustry2())) {
            model.addAttribute("ind2L", tIndustryMapper.selectByIds(tc.getIndustry2()));// 现场检查类别
        }
        if (StringUtils.isNotBlank(tc.getIndustry3())) {
            model.addAttribute("ind3L", tIndustryMapper.selectByIds(tc.getIndustry3()));// 高危检查类别
        }

        return "global/danger/check-add2";
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
    public String opinionDetail(Model model, HttpServletRequest request, Integer id, Integer uid) throws Exception {
        User user = null;
        if (null == uid){
            user = getLoginUser(request);
        }else if (null != uid){
            user = userMapper.selectByPrimaryKey(uid);
        }
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
        return "global/company/checkModel/opinion-detail2";
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
        User user = getLoginUser(request);
        TCheck tc = tCheckMapper.selectCheckBymodelIdAndStatus(id, 0);
        type = tc.getType();// add wz 190110
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(tc.getId());
        for (TCheckPart a : partL) {
            String levels = a.getLevels();
        }
        model.addAttribute("check", tc);
        model.addAttribute("partL", partL);
        List<Map<String, Object>> iteml = tCheckItemMapper.selectByCheckId(tc.getId());
        selectMeaths1(type, iteml);
        model.addAttribute("itemL", iteml);
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

    private void selectMeaths1(Integer type, List<Map<String, Object>> iteml) {
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
                log.error("level1/2/3 : " + level1 + "/" + level2 + "/" + level3);
            }
        }
    }


    /**
    * create by  : 小明！！！
    * description: TODO    隐患排查  文书详情
    * create time: 2019/9/23 10:33
    */
    @RequestMapping(value = "check-document")
    public String checkDocument(HttpServletRequest request, Integer checkId, Integer flag, Integer uid, Model model, TRectification tr) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (null == flag) {
            flag = 8;// 现场检查记录
        }
        User user = userMapper.selectByPrimaryKey(uid);
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
            return "global/company/checkModel/opinion-detail2";
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
        model.addAttribute("uid", uid);
        model.addAttribute("checkId", checkId);
        model.addAttribute("userId", check.getUserId());
        model.addAttribute("rectification", tRectification);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(uid));
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
            return "global/company/checkModel/opinion-add" + flag + check.getFlag();
        }else {
            // 部门抽查
            if(flag==8){
                flag=1;
            }
            return "global/company/checkModel/opinion-add" + flag + check.getFlag();
        }

    }


    /**
     * create by  : 小明！！！
     * description: TODO    政府端 行政文书保存
     * create time: 2019/9/23 11:22
     */
    @RequestMapping("danger/opinion-save")
    public @ResponseBody
    AppResult opinionSave(Model model, Integer userId,Integer uid, HttpServletRequest request, TCheckDocument tCheckDocument)
            throws Exception {
        AppResult result = new AppResultImpl();
        User user = userMapper.selectByPrimaryKey(uid);
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
     * TODO  隐患列表 复查(实施复查)
     */
    @RequestMapping(value = "recheck-add")//修改 wz0114
    public String recheckAdd(Integer checkId, Model model, Integer uid) throws Exception {

        TCheck tc = tCheckMapper.selectByPrimaryKey(checkId);
        Integer type = tc.getType();
        List<Map<String, Object>> iteml = tCheckItemMapper.selectDangerByCheckId(checkId, 2);
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

        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
        model.addAttribute("now", new Date());

        if (type == 9) {
            return "company/danger/check-fuaddrjcb";
        } else {
            return "company/danger/check-fuadd";
        }
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
     * create by  : 小明！！！
     * description: TODO    首页 企业分类统计 饼图数据
     * create time: 2019/10/8 14:48
     */
    @RequestMapping(value = "company/company-tab-detail")
    public String companyTab(Model model, HttpServletRequest request, CompanyListReqDTO dto,
                             String scale) throws Exception {
        User user = getLoginUser(request);

        if(dto.getIndustry2()!=null) {
            dto.setIndustry2(URLDecoder.decode(dto.getIndustry2(), "UTF-8"));
        }
        if(dto.getDlevel()!=null) {
            dto.setDlevel(URLDecoder.decode(dto.getDlevel(), "UTF-8"));
        }
        if(dto.getIndustry()!=null) {
            dto.setIndustry(URLDecoder.decode(dto.getIndustry(), "UTF-8"));
        }
        dto.setIsFreeze("1");
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
        model.addAttribute("ind1", tIndustryMapper.selectByPrimaryKey(tc.getIndustry1()));// 基础检查类别
        if (StringUtils.isNotEmpty(tc.getIndustry2())) {
            model.addAttribute("ind2L", tIndustryMapper.selectByIds(tc.getIndustry2()));// 现场检查类别
        }
        if (StringUtils.isNotBlank(tc.getIndustry3())) {
            model.addAttribute("ind3L", tIndustryMapper.selectByIds(tc.getIndustry3()));// 高危检查类别
        }
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
        if (null != company.getDangers()){
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
                int state = 0;
                if(company.getIndustry().contains("化工"))
                {
                    state = 1;
                }
                List<TLevel> dL = tLevelMapper.selectAll(state);
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

        User user = getLoginUser(request);
        CheckItemS checkItemByModelId = saveMessageService.findCheckItemByModelId(id);
        if (null == checkItemByModelId) {
            return "village/danger/plan-next";
        }
        TCheck tc = tCheckMapper.selectByPrimaryKey(checkItemByModelId.getCheckId());
        type = tc.getType();// add wz 190110
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(checkItemByModelId.getCheckId());
        model.addAttribute("userId",userId);
        model.addAttribute("check", tc);
        model.addAttribute("partL", partL);
        List<Map<String, Object>> iteml = tCheckItemMapper.selectByCheckId(checkItemByModelId.getCheckId());
        selectMeaths1(type, iteml);
        model.addAttribute("itemL", iteml);
        if (tc.getStatus() == 2) {// 已检查
            return "company/danger/plan-detail";
        }
        model.addAttribute("now", new Date());
        model.addAttribute("company", companyMapper.selectByPrimaryKey(tc.getUserId()));

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        Set<String> set = new LinkedHashSet<String>();

        selectmeadths2(iteml, set);
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

    private void selectmeadths2(List<Map<String, Object>> iteml, Set<String> set) {
        for (Map<String, Object> stringObjectMap : iteml) {
            String levels = (String) stringObjectMap.get("levels");
            if(levels.contains("/")){
                String[] split = levels.split("/");
                set.add(split[1]);
            }
        }
    }

    /**
     * 检查表隐患 整改计划保存
     */
    @RequestMapping(value = "rectification-save")
    public @ResponseBody
    Result rectificationSave(TRectification tr, HttpServletRequest request, Integer uid) {
        Result result = new ResultImpl();
        try {
            tr.setCreateUser(userMapper.selectByPrimaryKey(uid).getId());
            cgfService.rectificationSave(tr);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "系统出错");
        }
        return result;
    }

    @RequestMapping(value = "model-list-main")
    public String modelListMain(HttpServletRequest request, Integer userId, Integer uid, Model model, Integer flag, Integer status, Integer plan) throws ParseException {

        User user = userMapper.selectByPrimaryKey(uid);

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
        selectMeadths4(flag, user, jiChuItem);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> list = zzjgDepartmentMapper.selectHiddenPlan(user.getId());

        List<Map<String, Object>> list1 = hiddenPlanMapper.selectCountAll(user.getId());

        List<Map<String, Object>> hiddenPlanList = hiddenPlanMapper.findDpid(0,user.getId());

        selectMeadths3(model, user, hiddenPlanList);

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

    private void selectMeadths4(Integer flag, User user, List<Map<String, Object>> jiChuItem) {
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
    }

    private void selectMeadths3(Model model, User user, List<Map<String, Object>> hiddenPlanList) {
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
    }

    /**
     * 检查历史
     * TODO 排查治理记录 隐患排查记录(只需要已经检查过的,没有不合格记录的)
     * user. userType : 管理类型  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     */
    @RequestMapping(value = "check-list2")//flag:3 部门抽查
    public String troubleList2(HttpServletRequest request, String title, Integer type, String companyName,
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
        selectMeadths5(model, user, m, set);
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
        return "global/other/check-list";
    }

    private void selectMeadths5(Model model, User user, Map<String, Object> m, Set set) {
        if (setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);
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
    }


    /**
     * create by  : 小明！！！
     * description: TODO 查询该范围内的所有企业信息
     * create time: 2019/9/9 11:03
     */
    @RequestMapping(value = "hidden-company-list")
    public String hiddenCompanyList(HttpServletRequest request, Model model,Integer flag, Integer status) throws Exception {
        User user = getLoginUser(request);
        /*StringBuilder sb = new StringBuilder();
        List<Integer> ids = tCheckItemMapper.selectCompanyId(user.getId(), user.getUserType());//查询该账户下所有公司id
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        Integer counts1 = tCheckItemMapper.findAllCounte(sb.toString()); // 治理数据*/
        List<Map<String, Object>> list = tCheckItemMapper.findNumbers(user.getId(),user.getUserType());
        model.addAttribute("list",list);
        //model.addAttribute("count", counts1);
        model.addAttribute("flag",flag);
        model.addAttribute("status",status);

        return "global/company/danger/hidden-company-list";
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
    public String hiddenDangerList(HttpServletRequest request, Model model, Integer flag, Integer status, Integer uid,Integer breaken) {

        User user = userMapper.selectByPrimaryKey(uid);
        Company company = companyMapper.selectByPrimaryKey(uid);
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

                if(company.getName().equals(map.get("depart")) && null != map.get("levelId")){
                    Integer checkId = (Integer) map.get("checkId");
                    map.put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
                    Integer industryType = (Integer) map.get("industryType");
                    if(null != industryType && 1 == industryType){
                        map.put("level2",tLevelMapper.selectByPrimaryKey((Integer)map.get("levelId")).getName());
                    }else if (null != map.get("industryType") && 1 != map.get("industryType")){
                        map.put("level2",aDangerManualMapper.selectByPrimaryKey((Integer)map.get("levelId")).getName());
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
                    levelId = aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2();
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
            list = tCheckItemMapper.selectBMCCListBystatus(user.getId(), flag,breaken);
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
        model.addAttribute("breaken", breaken);

        return "global/company/danger/hidden-danger-list";
    }


    /**
     * create by  : 小明！！！
     * description: TODO  排查数据分析 柱状图
     * create time: 2019/8/29 16:35
     */
    @RequestMapping(value = "pc-analysis")
    public String pcAnalysis (HttpServletRequest request, Model model){

        User user = getLoginUser(request);
        List<Map<String, Object>> list = null;
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> lists1 = (List<Map<String, Object>>)request.getSession().getAttribute("list1");
        List<Map<String, Object>> lists2= (List<Map<String, Object>>)request.getSession().getAttribute("list2");
        Integer counts1= (Integer)request.getSession().getAttribute("counts1");
        Integer counts2= (Integer)request.getSession().getAttribute("counts2");
        Integer userId = (Integer)request.getSession().getAttribute("userId2");
        request.getSession().setMaxInactiveInterval(60*60*24);

        if (null == userId || userId != user.getId()){
            lists1 = null;
        }

        if (null == lists1 || lists1.size() == 0 || null == lists2 || lists2.size() == 0){
            List<Integer> ids = null;
            ids = basicFindAllIds(user, ids);

            DecimalFormat df = new DecimalFormat("0.00");

            Integer now = 0;
            Integer now1 = 0;
            if (null == ids || ids.size() == 0){

                return "global/evaluate/globalError";

            }else if (null != ids && ids.size() != 0){

                for (int i = 0; i < ids.size() ; i++) {

                    if (i == ids.size()-1){
                        sb.append("'").append(ids.get(i)).append("'");
                    }else {
                        sb.append("'").append(ids.get(i)).append("',");
                    }
                }

                Integer totalCounts = tCheckMapper.dataTotalZhengfu(sb.toString());
                model.addAttribute("count",totalCounts);

                List<Map<String, Object>> list1 = tCheckMapper.zhengfuCountBasic(sb.toString()); // 化工行业 数据

                model.addAttribute("list",list1);
                request.getSession().setAttribute("list1",list1);

                List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();

                HashMap<String, Object> map1 = new HashMap<>();

                list = tCheckItemMapper.zf11TroubleNews("基础管理",sb.toString()); // 基础管理 隐患数据
                now = list.size();
                list = tCheckItemMapper.zf11TroubleBasics(sb.toString()); // 基础管理 隐患数据
                now1 =  list.size();
                list = tCheckItemMapper.zfTrouble11("基础管理",sb.toString()); // 基础管理 隐患数据
                Integer number19 = list.size() + now + now1;

                map1.put("name19","基础管理");

                map1.put("number19",number19);

                list = tCheckItemMapper.zf11TroubleNews("设计总图",sb.toString()); // 基础管理 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("设计总图",sb.toString()); // 基础管理 隐患数据
                Integer number1 = list.size() + now;
                map1.put("name1","设计总图");
                map1.put("number1",number1);


                list = tCheckItemMapper.zf11TroubleNews("试生产",sb.toString()); // 生产工艺 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("试生产",sb.toString()); // 生产工艺 隐患数据
                Integer number2 = list.size() + now;
                map1.put("name2","试生产");
                map1.put("number2",number2);

                list = tCheckItemMapper.zf11TroubleNews("装置运行",sb.toString()); // 设备设施 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("装置运行",sb.toString()); // 设备设施 隐患数据
                Integer number3 = list.size() + now;
                map1.put("name3","装置运行");
                map1.put("number3",number3);

                list = tCheckItemMapper.zf11TroubleNews("设备安全",sb.toString()); // 公用工程 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("设备安全",sb.toString()); // 公用工程 隐患数据
                Integer number4 = list.size() + now;
                map1.put("name4","设备安全");
                map1.put("number4",number4);

                list = tCheckItemMapper.zf11TroubleNews("仪表安全",sb.toString()); // 特种设备 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("仪表安全",sb.toString()); // 特种设备 隐患数据
                Integer number5 = list.size() + now;
                map1.put("name5","仪表安全");
                map1.put("number5",number5);

                list = tCheckItemMapper.zf11TroubleNews("电气安全",sb.toString()); // 生产现场 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("电气安全",sb.toString()); // 生产现场 隐患数据
                Integer number6 = list.size() + now;
                map1.put("name6","电气安全");
                map1.put("number6",number6);

                list = tCheckItemMapper.zf11TroubleNews("应急消防",sb.toString()); // 行为环境 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("应急消防",sb.toString()); // 行为环境 隐患数据
                Integer number7 = list.size() + now;
                map1.put("name7","应急消防");
                map1.put("number7",number7);

                list = tCheckItemMapper.zf11TroubleNews("特殊管控",sb.toString()); // 危化管理 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("特殊管控",sb.toString()); // 危化管理 隐患数据
                Integer number8 = list.size() + now;
                map1.put("name8","特殊管控");
                map1.put("number8",number8);

                list = tCheckItemMapper.zf11TroubleNews("行为环境",sb.toString()); // 消防安全 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("行为环境",sb.toString()); // 消防安全 隐患数据
                Integer number9 = list.size() + now;
                map1.put("name9","行为环境");
                map1.put("number9",number9);

                list = tCheckItemMapper.zf11TroubleNews("生产现场",sb.toString()); // 用电安全 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("生产现场",sb.toString()); // 用电安全 隐患数据
                Integer number10 = list.size() + now;
                map1.put("name10","生产现场");
                map1.put("number10",number10);

                list = tCheckItemMapper.zf11TroubleNews("公辅工程",sb.toString()); // 安全设施 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("公辅工程",sb.toString()); // 安全设施 隐患数据
                Integer number11 = list.size() + now;
                map1.put("name11","公辅工程");
                map1.put("number11",number11);

                list = tCheckItemMapper.zf11TroubleNews("特种设备",sb.toString()); // 防雷静电 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("特种设备",sb.toString()); // 防雷静电 隐患数据
                Integer number12 = list.size() + now;
                map1.put("name12","特种设备");
                map1.put("number12",number12);

                list = tCheckItemMapper.zf11TroubleNews("专项行业",sb.toString()); // 职业卫生 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("专项行业",sb.toString()); // 职业卫生 隐患数据
                Integer number13 = list.size() + now;
                map1.put("name13","专项行业");
                map1.put("number13",number13);

                list = tCheckItemMapper.zf11TroubleNews("生产工艺",sb.toString()); // 职业卫生 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("生产工艺",sb.toString()); // 职业卫生 隐患数据
                Integer number14 = list.size() + now;
                map1.put("name14","生产工艺");
                map1.put("number14",number14);

                list = tCheckItemMapper.zf11TroubleNews("设备设施",sb.toString()); // 职业卫生 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("设备设施",sb.toString()); // 职业卫生 隐患数据
                Integer number15 = list.size() + now;
                map1.put("name15","设备设施");
                map1.put("number15",number15);

                list = tCheckItemMapper.zf11TroubleNews("危化管理",sb.toString()); // 职业卫生 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("危化管理",sb.toString()); // 职业卫生 隐患数据
                Integer number16 = list.size() + now;
                map1.put("name16","危化管理");
                map1.put("number16",number16);

                list = tCheckItemMapper.zf11TroubleNews("安全设施",sb.toString()); // 职业卫生 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("安全设施",sb.toString()); // 职业卫生 隐患数据
                Integer number17 = list.size() + now;
                map1.put("name17","安全设施");
                map1.put("number17",number17);

                list = tCheckItemMapper.zf11TroubleNews("其他",sb.toString()); // 其他 隐患数据
                now = list.size();
                list = tCheckItemMapper.zfTrouble11("其他",sb.toString()); // 其他 隐患数据
                Integer number18 = list.size() + now;
                map1.put("name18","其他");
                map1.put("number18",number18);

                Integer count1 = number1 + number2 + number3 + number4 + number5 + number6 + number7 + number8 + number9 + number10 + number11 + number12 + number13 + number14 + number15 + number16 + number17 + number18 + number19;

                model.addAttribute("count1",count1);

                findCounts2(model, df, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13, number14, number15, number16, number17, number18, number19, count1, map1);

                lists.add(map1);

                model.addAttribute("lists",lists);
                request.getSession().setAttribute("list2",lists);
                request.getSession().setAttribute("counts1",totalCounts);
                request.getSession().setAttribute("counts2",count1);
                request.getSession().setAttribute("userId2",user.getId());
            }

        }else if (null != lists1 && lists1.size() != 0 && null != lists2 && lists2.size() != 0 ){

            model.addAttribute("count",counts1);
            model.addAttribute("count1",counts2);
            model.addAttribute("list",lists1);
            model.addAttribute("lists",lists2);
            request.getSession().setAttribute("userId2",user.getId());
        }

        return "global/company/evaluate/analysis/pc-analysis";
    }

    private void findCounts3(Model model, DecimalFormat df, Integer a, Integer b, Integer c, Integer d, Integer e, Integer f, Integer g, Integer h, Integer i1, Integer j, Integer count, Map map1) {
        if (null != count && 0 != count) {

            if (null != a && 0 != a) {
                String str = df.format((float)a / count);
                model.addAttribute("result1",str);

            } else {
                model.addAttribute("result1","0.00");  // 化工 占比数据
            }

            if (null != b && 0 != b) {
                String str = df.format((float)b / count);
                model.addAttribute("result2",str);

            } else {
                model.addAttribute("result2","0.00"); // 冶金 占比数据
            }

            if (null != c && 0 != c) {
                String str = df.format((float)c / count);
                model.addAttribute("result3",str);

            } else {
                model.addAttribute("result3","0.00");  // 有色 占比数据 竖
            }

            if (null != d && 0 != d) {
                String str = df.format((float)d / count);
                model.addAttribute("result4",str);

            } else {
                model.addAttribute("result4","0.00"); // 建材 占比数据
            }

            if (null != e && 0 != e) {
                String str = df.format((float)e / count);
                model.addAttribute("result5", str);

            } else {
                model.addAttribute("result5","0.00"); // 机械 占比数据
            }

            if (null != f && 0 != f) {
                String str = df.format((float)f / count);
                model.addAttribute("result6",str);

            } else {
                model.addAttribute("result6","0.00"); // 轻工 占比数据
            }

            if (null != g && 0 != g) {
                String str = df.format((float)g / count);
                model.addAttribute("result7",str);

            } else {
                model.addAttribute("result7","0.00"); // 纺织 占比数据
            }

            if (null != h && 0 != h) {
                String str = df.format((float)h / count);
                model.addAttribute("result8",str);

            } else {
                model.addAttribute("result8","0.00");// 商贸 占比数据
            }

            if (null != i1 && 0 != i1) {
                String str = df.format((float)i1 / count);
                model.addAttribute("result9",str);

            } else {
                model.addAttribute("result9","0.00"); // 烟花 占比数据
            }

            if (null != j && 0 != j) {
                String str = df.format((float)j / count);
                model.addAttribute("result10",str);

            } else {
                model.addAttribute("result10","0.00"); // 其他 占比数据
            }

        }else {
            // 占比 坚
            model.addAttribute("result1", "0.00");
            model.addAttribute("result2", "0.00");
            model.addAttribute("result3", "0.00");
            model.addAttribute("result4", "0.00");
            model.addAttribute("result5", "0.00");
            model.addAttribute("result6", "0.00");
            model.addAttribute("result7", "0.00");
            model.addAttribute("result8", "0.00");
            model.addAttribute("result9", "0.00");
            model.addAttribute("result10", "0.00");
        }
    }


    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析 柱状图
     * create time: 2019/8/29 17:42
     */
    @RequestMapping(value = "yh-all-analysis")
    public String yhAllAnalysiszf (HttpServletRequest request, Model model){

        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> list = null;
        List<Map<String,Object>> list3 = (List<Map<String,Object>>)request.getSession().getAttribute("list3");
        List<Map<String,Object>> list4 = (List<Map<String,Object>>)request.getSession().getAttribute("list4");
        Integer counts3 = (Integer)request.getSession().getAttribute("counts3");
        Integer counts4 = (Integer)request.getSession().getAttribute("counts4");
        Integer userid = (Integer) request.getSession().getAttribute("userId3");
        request.getSession().setMaxInactiveInterval(60*60*24);

        if (null == userid || userid != user.getId()){
            list3 = null;
        }

        if (null == list3 || list3.size() == 0 || list4.size() == 0 || null == list4){

            List<Integer> ids = null;
            ids = basicFindAllIds(user, ids);

            DecimalFormat df = new DecimalFormat("0.00");

            if (null == ids || ids.size() == 0){

                return "global/evaluate/globalError";

            }else if (null != ids && ids.size() != 0){

                for (int i = 0; i < ids.size() ; i++) {
                    if (i == ids.size()-1){
                        sb.append("'").append(ids.get(i)).append("'");
                    }else {
                        sb.append("'").append(ids.get(i)).append("',");
                    }
                }

                list = tCheckItemMapper.zhengFuDangerBasics(sb.toString()); // 化工行业 数据

                model.addAttribute("list",list);

                Integer count = Integer.parseInt(String.valueOf(list.get(0).get("danger1"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger2"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger3"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger4"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger5"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger6"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger7"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger8"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger9"))) + Integer.parseInt(String.valueOf(list.get(0).get("danger10")));
                model.addAttribute("count",count);
                request.getSession().setAttribute("list3",list);
                request.getSession().setAttribute("userId3",user.getId());

                List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();

                HashMap<String, Object> map1 = new HashMap<>();

                Integer number191 = tCheckItemMapper.zf88DataNows("基础管理",sb.toString()); // 基础管理 隐患数据
                Integer number192 = tCheckItemMapper.zf88DataBasics(sb.toString()); // 基础管理 隐患数据
                Integer number193 = tCheckItemMapper.zfData88("基础管理",sb.toString()); // 基础管理 隐患数据
                Integer number19 = number191 + number192 + number193;
                map1.put("name19","基础管理");
                map1.put("number19",number19);
                //model.addAttribute("number19",number19);

                Integer numbers1 = tCheckItemMapper.zf88DataNows("设计总图",sb.toString()); // 基础管理 隐患数据
                Integer numbers2 = tCheckItemMapper.zfData88("设计总图",sb.toString()); // 基础管理 隐患数据
                Integer number1 = numbers1 + numbers2;
                //model.addAttribute("number1",number1);
                map1.put("name1","设计总图");
                map1.put("number1",number1);

                Integer number21 = tCheckItemMapper.zf88DataNows("试生产",sb.toString()); // 生产工艺 隐患数据
                Integer number22 = tCheckItemMapper.zfData88("试生产",sb.toString()); // 生产工艺 隐患数据
                Integer number2 = number21 + number22;
                //model.addAttribute("number2",number2);
                map1.put("name2","试生产");
                map1.put("number2",number2);

                Integer number31 = tCheckItemMapper.zf88DataNows("装置运行",sb.toString()); // 设备设施 隐患数据
                Integer number32 = tCheckItemMapper.zfData88("装置运行",sb.toString()); // 设备设施 隐患数据
                Integer number3 = number31 + number32;
                //model.addAttribute("number3",number3);
                map1.put("name3","装置运行");
                map1.put("number3",number3);

                Integer number41 = tCheckItemMapper.zf88DataNows("设备安全",sb.toString()); // 公用工程 隐患数据
                Integer number42 = tCheckItemMapper.zfData88("设备安全",sb.toString()); // 公用工程 隐患数据
                Integer number4 = number41 + number42;
                //model.addAttribute("number4",number4);
                map1.put("name4","设备安全");
                map1.put("number4",number4);

                Integer number51 = tCheckItemMapper.zf88DataNows("仪表安全",sb.toString()); // 特种设备 隐患数据
                Integer number52 = tCheckItemMapper.zfData88("仪表安全",sb.toString()); // 特种设备 隐患数据
                Integer number5 = number51 + number52;
                //model.addAttribute("number5",number5);
                map1.put("name5","仪表安全");
                map1.put("number5",number5);

                Integer number61 = tCheckItemMapper.zf88DataNows("电气安全",sb.toString()); // 生产现场 隐患数据
                Integer number62 = tCheckItemMapper.zfData88("电气安全",sb.toString()); // 生产现场 隐患数据
                Integer number6 = number61 + number62;
                //model.addAttribute("number6",number6);
                map1.put("name6","电气安全");
                map1.put("number6",number6);

                Integer number71 = tCheckItemMapper.zf88DataNows("应急消防",sb.toString()); // 行为环境 隐患数据
                Integer number72 = tCheckItemMapper.zfData88("应急消防",sb.toString()); // 行为环境 隐患数据
                Integer number7 = number71 + number72;
                //model.addAttribute("number7",number7);
                map1.put("name7","应急消防");
                map1.put("number7",number7);

                Integer number81 = tCheckItemMapper.zf88DataNows("特殊管控",sb.toString()); // 危化管理 隐患数据
                Integer number82 = tCheckItemMapper.zfData88("特殊管控",sb.toString()); // 危化管理 隐患数据
                Integer number8 = number81 + number82;
                //model.addAttribute("number8",number8);
                map1.put("name8","特殊管控");
                map1.put("number8",number8);

                Integer number91 = tCheckItemMapper.zf88DataNows("行为环境",sb.toString()); // 消防安全 隐患数据
                Integer number92 = tCheckItemMapper.zfData88("行为环境",sb.toString()); // 消防安全 隐患数据
                Integer number9 = number91 + number92;
                //model.addAttribute("number9",number9);
                map1.put("name9","行为环境");
                map1.put("number9",number9);

                Integer number101 = tCheckItemMapper.zf88DataNows("生产现场",sb.toString()); // 用电安全 隐患数据
                Integer number102 = tCheckItemMapper.zfData88("生产现场",sb.toString()); // 用电安全 隐患数据
                Integer number10 = number101 + number102;
                //model.addAttribute("number10",number10);
                map1.put("name10","生产现场");
                map1.put("number10",number10);

                Integer number111 = tCheckItemMapper.zf88DataNows("公辅工程",sb.toString()); // 安全设施 隐患数据
                Integer number112 = tCheckItemMapper.zfData88("公辅工程",sb.toString()); // 安全设施 隐患数据
                Integer number11 = number111 + number112;
                //model.addAttribute("number11",number11);
                map1.put("name11","公辅工程");
                map1.put("number11",number11);

                Integer number121 = tCheckItemMapper.zf88DataNows("特种设备",sb.toString()); // 防雷静电 隐患数据
                Integer number122 = tCheckItemMapper.zfData88("特种设备",sb.toString()); // 防雷静电 隐患数据
                Integer number12 = number121 + number122;
                //model.addAttribute("number12",number12);
                map1.put("name12","特种设备");
                map1.put("number12",number12);

                Integer number131 = tCheckItemMapper.zf88DataNows("专项行业",sb.toString()); // 职业卫生 隐患数据
                Integer number132 = tCheckItemMapper.zfData88("专项行业",sb.toString()); // 职业卫生 隐患数据
                Integer number13 = number131 + number132;
                //model.addAttribute("number13",number13);
                map1.put("name13","专项行业");
                map1.put("number13",number13);

                Integer number141 = tCheckItemMapper.zf88DataNows("生产工艺",sb.toString()); // 职业卫生 隐患数据
                Integer number142 = tCheckItemMapper.zfData88("生产工艺",sb.toString()); // 职业卫生 隐患数据
                Integer number14 = number141 + number142;
                //model.addAttribute("number14",number14);
                map1.put("name14","生产工艺");
                map1.put("number14",number14);

                Integer number151 = tCheckItemMapper.zf88DataNows("设备设施",sb.toString()); // 防雷静电 隐患数据
                Integer number152 = tCheckItemMapper.zfData88("设备设施",sb.toString()); // 防雷静电 隐患数据
                Integer number15 = number151 + number152;
                //model.addAttribute("number15",number15);
                map1.put("name15","设备设施");
                map1.put("number15",number15);

                Integer number161 = tCheckItemMapper.zf88DataNows("危化管理",sb.toString()); // 职业卫生 隐患数据
                Integer number162 = tCheckItemMapper.zfData88("危化管理",sb.toString()); // 职业卫生 隐患数据
                Integer number16 = number161 + number162;
                //model.addAttribute("number16",number16);
                map1.put("name16","危化管理");
                map1.put("number16",number16);

                Integer number171 = tCheckItemMapper.zf88DataNows("安全设施",sb.toString()); // 职业卫生 隐患数据
                Integer number172 = tCheckItemMapper.zfData88("安全设施",sb.toString()); // 职业卫生 隐患数据
                Integer number17 = number171 + number172;
                //model.addAttribute("number17",number17);
                map1.put("name17","安全设施");
                map1.put("number17",number17);

                Integer number181 = tCheckItemMapper.zf88DataNows("其他",sb.toString()); // 其他 隐患数据
                Integer number182 = tCheckItemMapper.zfData88("其他",sb.toString()); // 其他 隐患数据
                Integer number18 = number181 + number182;
                //model.addAttribute("number18",number18);
                map1.put("name18","其他");
                map1.put("number18",number18);

                Integer count1 = number1 + number2 + number3 + number4 + number5 + number6 + number7 + number8 + number9 + number10 + number11 + number12 + number13 + number14 + number15 + number16 + number17 + number18 + number19;

                model.addAttribute("count1",count1);

                findCounts2(model, df, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13, number14, number15, number16, number17, number18, number19, count1, map1);

                lists.add(map1);

                model.addAttribute("lists",lists);
                request.getSession().setAttribute("counts3",count);
                request.getSession().setAttribute("counts4",count1);
                request.getSession().setAttribute("list4",lists);
            }
        }else {

            model.addAttribute("count",counts3);
            model.addAttribute("count1",counts4);
            model.addAttribute("list",list3);
            model.addAttribute("lists",list4);
            request.getSession().setAttribute("userId3",user.getId());
        }

        return "global/company/evaluate/analysis/yh-analysis";
    }

    private void findCounts2(Model model, DecimalFormat df, Integer number1, Integer number2, Integer number3, Integer number4, Integer number5, Integer number6, Integer number7, Integer number8, Integer number9, Integer number10, Integer number11, Integer number12, Integer number13, Integer number14, Integer number15, Integer number16, Integer number17, Integer number18, Integer number19, Integer count1, HashMap<String,Object> map1) {
        if (null != count1 && 0 != count1) {

            if (null != number19 && 0 != number19) {
                String str = df.format((float)number19 / count1);
                map1.put("double19",str);

            } else {
                map1.put("double19","0.00");  // 化工 占比数据
            }

            if (null != number1 && 0 != number1) {
                String str = df.format((float)number1 / count1);
                map1.put("double1",str);

            } else {
                map1.put("double1","0.00");  // 化工 占比数据
            }

            if (null != number2 && 0 != number2) {
                String str = df.format((float)number2 / count1);
                map1.put("double2",str);

            } else {
                map1.put("double2","0.00"); // 冶金 占比数据
            }

            if (null != number3 && 0 != number3) {
                String str = df.format((float)number3 / count1);
                map1.put("double3",str);

            } else {
                map1.put("double3","0.00");  // 有色 占比数据 竖
            }

            if (null != number4 && 0 != number4) {
                String str = df.format((float)number4 / count1);
                map1.put("double4",str);

            } else {
                map1.put("double4","0.00"); // 建材 占比数据
            }

            if (null != number5 && 0 != number5) {
                String str = df.format((float)number5 / count1);
                map1.put("double5", str);

            } else {
                map1.put("double5","0.00"); // 机械 占比数据
            }

            if (null != number6 && 0 != number6) {
                String str = df.format((float)number6 / count1);
                map1.put("double6",str);

            } else {
                map1.put("double6","0.00"); // 轻工 占比数据
            }

            if (null != number7 && 0 != number7) {
                String str = df.format((float)number7 / count1);
                map1.put("double7",str);

            } else {
                map1.put("double7","0.00"); // 纺织 占比数据
            }

            if (null != number8 && 0 != number8) {
                String str = df.format((float)number8 / count1);
                map1.put("double8",str);

            } else {
                map1.put("double8","0.00");// 商贸 占比数据
            }

            if (null != number9 && 0 != number9) {
                String str = df.format((float)number9 / count1);
                map1.put("double9",str);

            } else {
                map1.put("double9","0.00"); // 烟花 占比数据
            }

            if (null != number10 && 0 != number10) {
                String str = df.format((float)number10 / count1);
                map1.put("double10",str);

            } else {
                map1.put("double10","0.00"); // 其他 占比数据
            }

            if (null != number11 && 0 != number11) {
                String str = df.format((float)number11 / count1);
                map1.put("double11",str);

            } else {
                map1.put("double11","0.00"); // 其他 占比数据
            }

            if (null != number12 && 0 != number12) {
                String str = df.format((float)number12 / count1);
                map1.put("double12",str);

            } else {
                map1.put("double12","0.00"); // 其他 占比数据
            }

            if (null != number13 && 0 != number13) {
                String str = df.format((float)number13 / count1);
                map1.put("double13",str);

            } else {
                map1.put("double13","0.00"); // 其他 占比数据
            }

            if (null != number14 && 0 != number14) {
                String str = df.format((float)number14 / count1);
                map1.put("double14",str);

            } else {
                map1.put("double14","0.00"); // 其他 占比数据
            }

            if (null != number15 && 0 != number15) {
                String str = df.format((float)number15 / count1);
                map1.put("double15",str);

            } else {
                map1.put("double15","0.00"); // 其他 占比数据
            }

            if (null != number16 && 0 != number16) {
                String str = df.format((float)number15 / count1);
                map1.put("double16",str);

            } else {
                map1.put("double16","0.00"); // 其他 占比数据
            }

            if (null != number17 && 0 != number17) {
                String str = df.format((float)number15 / count1);
                map1.put("double17",str);

            } else {
                map1.put("double17","0.00"); // 其他 占比数据
            }

            if (null != number18 && 0 != number18) {
                String str = df.format((float)number18 / count1);
                map1.put("double18",str);

            } else {
                map1.put("double18","0.00"); // 其他 占比数据
            }

        }else {
            // 占比 坚
            map1.put("double1", "0.00");
            map1.put("double2", "0.00");
            map1.put("double3", "0.00");
            map1.put("double4", "0.00");
            map1.put("double5", "0.00");
            map1.put("double6", "0.00");
            map1.put("double7", "0.00");
            map1.put("double8", "0.00");
            map1.put("double9", "0.00");
            map1.put("double10", "0.00");
            map1.put("double11", "0.00");
            map1.put("double12", "0.00");
            map1.put("double13", "0.00");
            map1.put("double14", "0.00");
            map1.put("double15", "0.00");
            map1.put("double16", "0.00");
            map1.put("double17", "0.00");
            map1.put("double18", "0.00");
            map1.put("double19", "0.00");
        }
    }


    /**
     * create by  : 小明！！！
     * description: TODO    治理数据分析  饼状图
     * create time: 2019/8/29 17:57
     */
    @RequestMapping(value = "zl-all-analysis")
    public String zlAllAnalysiszf (HttpServletRequest request, Model model){

        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();

        List<Map<String,Object>> list5 = (List<Map<String,Object>>) request.getSession().getAttribute("list5");
        Integer userId = (Integer) request.getSession().getAttribute("userId5");
        if (null == userId || userId != user.getId()){
            list5 = null;
        }

        if (null == list5 || list5.size() == 0){

            List<Integer> ids = null;
            ids = basicFindAllIds(user, ids);
            DecimalFormat df = new DecimalFormat("0.00");

            if (null == ids || ids.size() == 0){

                return "global/evaluate/globalError";

            }else if (null != ids && ids.size() != 0){

                for (int i = 0; i < ids.size() ; i++) {
                    if (i == ids.size()-1){
                        sb.append("'").append(ids.get(i)).append("'");
                    }else {
                        sb.append("'").append(ids.get(i)).append("',");
                    }
                }
                List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();

                HashMap<String, Object> map1 = new HashMap<>();

                Integer  a11 = tCheckItemMapper.manageHiddenCompany(3,sb.toString(),3); // 一般和较小 合格 已治理
                Integer  a2 = tCheckItemMapper.manageHiddenNows(3,sb.toString(),3); // 一般和较小 合格 已治理
                Integer  a3 = tCheckItemMapper.manageHiddenBasic(3,sb.toString(),3); // 一般和较小 合格 已治理

                Integer  a4 = tCheckItemMapper.manageHiddenCompany(2,sb.toString(),3); // 一般和较小 不合格 未治理
                Integer  a5 = tCheckItemMapper.manageHiddenNows(2,sb.toString(),3); // 一般和较小 不合格 未治理
                Integer  a6 = tCheckItemMapper.manageHiddenBasic(2,sb.toString(),3); // 一般和较小 不合格 未治理

                Integer a = a11 + a2 + a3;
                Integer a1 = a4 + a5 + a6;
                Integer count1 = a + a1;

                Integer  b11 = tCheckItemMapper.manageHiddenCompany(3,sb.toString(),1); // 较大 合格 已治理
                Integer  b2 = tCheckItemMapper.manageHiddenNows(3,sb.toString(),1); // 较大 合格 已治理
                Integer  b3 = tCheckItemMapper.manageHiddenBasic(3,sb.toString(),1); // 较大 合格 已治理

                Integer  b4 = tCheckItemMapper.manageHiddenCompany(2,sb.toString(),1); // 较大 不合格 未治理
                Integer  b5 = tCheckItemMapper.manageHiddenNows(2,sb.toString(),1); // 较大 不合格 未治理
                Integer  b6 = tCheckItemMapper.manageHiddenBasic(2,sb.toString(),1); // 较大 不合格 未治理

                Integer b = b11 + b2 + b3;
                Integer b1 = b4 + b5 + b6;
                Integer count2  = b + b1;

                Integer  c11 = tCheckItemMapper.manageHiddenCompany(3,sb.toString(),2); // 重大 合格 已治理
                Integer  c2 = tCheckItemMapper.manageHiddenNows(3,sb.toString(),2); // 重大 合格 已治理
                Integer  c3 = tCheckItemMapper.manageHiddenBasic(3,sb.toString(),2); // 重大 合格 已治理

                Integer  c4 = tCheckItemMapper.manageHiddenCompany(2,sb.toString(),2); // 重大 不合格 未治理
                Integer  c5 = tCheckItemMapper.manageHiddenNows(2,sb.toString(),2); // 重大 不合格 未治理
                Integer  c6 = tCheckItemMapper.manageHiddenBasic(2,sb.toString(),2); // 重大 不合格 未治理
                Integer c = c11 + c2 + c3;
                Integer c1 = c4 + c5 + c6;
                Integer count3  = c + c1;

                if (null != count1 && count1 != 0){  // 一般和较小 治理率
                    String str = df.format((float)a/count1);
                    map1.put("result11",str);
                }else {
                    map1.put("result11","0.00");
                }

                if (null != count2 && count2 != 0){ // 较大 治理率
                    String str = df.format((float)b/count2);
                    map1.put("result22",str);
                }else {
                    map1.put("result22","0.00");
                }

                if (null != count3 && count3 != 0){ // 重大 治理率
                    String str = df.format((float)c/count3);
                    map1.put("result33",str);
                }else {
                    map1.put("result33","0.00");
                }

                String string = "'基础管理','设计总图','试生产','装置运行','设备安全','仪表安全','电气安全','应急消防','特殊管控','行为环境','生产现场','公辅工程','特种设备','专项行业','生产工艺','设备设施','危化管理','安全设施','其他'";

                Integer numbers1 = tCheckItemMapper.manage22HiddenNews(3,string,3,sb.toString()); // 一般和较小 合格 已治理
                Integer numbers2 = tCheckItemMapper.manage22HiddenBasics(3,3,sb.toString()); // 一般和较小 合格 已治理
                Integer numbers3 = tCheckItemMapper.manageHiddenDanger22(3,string,3,sb.toString()); // 一般和较小 合格 已治理
                Integer number1 = numbers1 + numbers2 + numbers3;

                Integer numbers11 = tCheckItemMapper.manage22HiddenNews(2,string,3,sb.toString()); // 一般和较小 不合格 未治理
                Integer numbers12 = tCheckItemMapper.manage22HiddenBasics(2,3,sb.toString()); // 一般和较小 不合格 未治理
                Integer numbers13 = tCheckItemMapper.manageHiddenDanger22(2,string,3,sb.toString()); // 一般和较小 不合格 未治理
                Integer number11 = numbers11 + numbers12 + numbers13;

                Integer numbera1 = tCheckItemMapper.manage22HiddenNews(3,string,1,sb.toString()); // 较大 合格 已治理
                Integer numbera2 = tCheckItemMapper.manage22HiddenBasics(3,1,sb.toString()); // 较大 合格 已治理
                Integer numbera3 = tCheckItemMapper.manageHiddenDanger22(3,string,1,sb.toString()); // 较大 合格 已治理
                Integer number2 = numbera1 + numbera2 + numbera3;

                Integer numbers21 = tCheckItemMapper.manage22HiddenNews(2,string,1,sb.toString()); // 较大 不合格 未治理
                Integer numbers22 = tCheckItemMapper.manage22HiddenBasics(2,1,sb.toString()); // 较大 不合格 未治理
                Integer numbers23 = tCheckItemMapper.manageHiddenDanger22(2,string,1,sb.toString()); // 较大 不合格 未治理
                Integer number22 = numbers21 + numbers22 + numbers23;

                Integer numberq1 = tCheckItemMapper.manage22HiddenNews(3,string,2,sb.toString()); // 重大 合格 已治理
                Integer numberq2 = tCheckItemMapper.manage22HiddenBasics(3,2,sb.toString()); // 重大 合格 已治理
                Integer numberq3 = tCheckItemMapper.manageHiddenDanger22(3,string,2,sb.toString()); // 重大 合格 已治理
                Integer number3 = numberq1 + numberq2 + numberq3;

                Integer numbers31 = tCheckItemMapper.manage22HiddenNews(2,string,2,sb.toString()); // 重大 不合格 未治理
                Integer numbers32 = tCheckItemMapper.manage22HiddenBasics(2,2,sb.toString()); // 重大 不合格 未治理
                Integer numbers33 = tCheckItemMapper.manageHiddenDanger22(2,string,2,sb.toString()); // 重大 不合格 未治理
                Integer number33 = numbers31 + numbers32 + numbers33;

                Integer counts1 = number1 + number11;
                Integer counts2 = number2 + number22;
                Integer counts3 = number3 + number33;

                if (null != counts1 && 0 != counts1) {
                    String str = df.format((float) number1 / counts1);// 一般和较小  3
                    map1.put("resusts1",str);
                }else {
                    map1.put("resusts1","0.00");
                }

                if (null != counts2 && 0 != counts2) {
                    String str = df.format((float) number2 / counts2); // 较大   1
                    map1.put("resusts2",str);
                }else {
                    map1.put("resusts2","0.00");
                }

                if (null != counts3 && 0 != counts3) {
                    String str = df.format((float) number3 / counts3);// 重大  2
                    map1.put("resusts3",str);
                }else {
                    map1.put("resusts3","0.00");
                }

                Integer numberz1 = tCheckItemMapper.manage22HiddenIndustryNews(3,3,sb.toString()); // 一般和较小 合格 已治理
                Integer numberz2 = tCheckItemMapper.manage22HiddenIndustryBasics(3,3,sb.toString()); // 一般和较小 合格 已治理
                Integer numberz3 = tCheckItemMapper.manageHiddenIndustry22(3,3,sb.toString()); // 一般和较小 合格 已治理
                Integer number4 = numberz1 + numberz2 + numberz3;

                Integer numberx1 = tCheckItemMapper.manage22HiddenIndustryNews(2,3,sb.toString()); // 一般和较小 不合格 未治理
                Integer numberx2 = tCheckItemMapper.manage22HiddenIndustryBasics(2,3,sb.toString()); // 一般和较小 不合格 未治理
                Integer numberx3 = tCheckItemMapper.manageHiddenIndustry22(2,3,sb.toString()); // 一般和较小 不合格 未治理
                Integer number44 = numberx1 + numberx2 + numberx3;

                Integer numberc1 = tCheckItemMapper.manage22HiddenIndustryNews(3,1,sb.toString()); // 较大 合格 已治理
                Integer numberc2 = tCheckItemMapper.manage22HiddenIndustryBasics(3,1,sb.toString()); // 较大 合格 已治理
                Integer numberc3 = tCheckItemMapper.manageHiddenIndustry22(3,1,sb.toString()); // 较大 合格 已治理
                Integer number5 = numberc1 + numberc2 + numberc3;

                Integer numberf1 = tCheckItemMapper.manage22HiddenIndustryNews(2,1,sb.toString()); // 较大 不合格 未治理
                Integer numberf2 = tCheckItemMapper.manage22HiddenIndustryBasics(2,1,sb.toString()); // 较大 不合格 未治理
                Integer numberf3 = tCheckItemMapper.manageHiddenIndustry22(2,1,sb.toString()); // 较大 不合格 未治理
                Integer number55 = numberf1 + numberf2 + numberf3;

                Integer numberv1 = tCheckItemMapper.manage22HiddenIndustryNews(3,2,sb.toString()); // 重大 合格 已治理
                Integer numberv2 = tCheckItemMapper.manage22HiddenIndustryBasics(3,2,sb.toString()); // 重大 合格 已治理
                Integer numberv3 = tCheckItemMapper.manageHiddenIndustry22(3,2,sb.toString()); // 重大 合格 已治理
                Integer number6 = numberv1 + numberv2 + numberv3;

                Integer numbeb1 = tCheckItemMapper.manage22HiddenIndustryNews(2,2,sb.toString()); // 重大 不合格 未治理
                Integer numbeb2 = tCheckItemMapper.manage22HiddenIndustryBasics(2,2,sb.toString()); // 重大 不合格 未治理
                Integer numbeb3 = tCheckItemMapper.manageHiddenIndustry22(2,2,sb.toString()); // 重大 不合格 未治理
                Integer number66 = numbeb1 + numbeb2 + numbeb3;

                Integer counts4 = number4 + number44;
                Integer counts5 = number5 + number55;
                Integer counts6 = number6 + number66;

                if (null != counts4 && 0 != counts4) {
                    String str = df.format((float) number4 / counts4);
                    map1.put("resusts4",str);
                }else {
                    map1.put("resusts4","0.00");
                }

                if (null != counts5 && 0 != counts5) {
                    String str = df.format((float) number5 / counts5);
                    map1.put("resusts5",str);
                }else {
                    map1.put("resusts5","0.00");
                }

                if (null != counts6 && 0 != counts6) {
                    String str = df.format((float) number6 / counts6);
                    map1.put("resusts6",str);
                }else {
                    map1.put("resusts6","0.00");
                }
                lists.add(map1);

                model.addAttribute("list",lists);
                request.getSession().setAttribute("list5",lists);
                request.getSession().setAttribute("userId5",user.getId());

            }
        }else {
            model.addAttribute("list",list5);
            request.getSession().setAttribute("userId5",user.getId());
        }

        return "global/company/evaluate/analysis/zl-analysis";
    }


    /**
     * create by  : 小明！！！
     * description: TODO 重大危险源分类统计
     * create time: 2019/9/11 11:22
     */
    @RequestMapping(value = "greateDanger")
    public String greateDanger(HttpServletRequest request, Model model){

        User user = getLoginUser(request);

        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = null;
        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;

        if (user.getUserType() == 4){ // 如果登录的账户是村级账号
            // 根据登录账户 ID 查询该区域下的所有公司信息
            list = tCheckItemMapper.findALL(user.getId(), user.getUserType());

            if (null == list || list.size() == 0){// 如果该区域下没有公司

                return "global/evaluate/globalError";// 返回提示页面

            }else if (null != list || list.size() != 0) {

                for (int i = 0; i < list.size(); i++) {

                    Integer a = tCheckMapper.selectGreateDanger("化工",(Integer) list.get(i).get("user_id")); // 化工行业 数据
                    list.get(i).put("danger1",a);
                    sum1 += a;

                    Integer  b = tCheckMapper.selectGreateDanger("冶金",(Integer) list.get(i).get("user_id")); // 冶金行业 数据
                    list.get(i).put("danger2",b);
                    sum2 += b;

                    Integer  c = tCheckMapper.selectGreateDanger("有色",(Integer) list.get(i).get("user_id")); // 有色行业 数据
                    list.get(i).put("danger3",c);
                    sum3 += c;

                    Integer  d = tCheckMapper.selectGreateDanger("建材",(Integer) list.get(i).get("user_id")); // 建材行业 数据
                    list.get(i).put("danger4",d);
                    sum4 += d;

                    Integer  e = tCheckMapper.selectGreateDanger("机械",(Integer) list.get(i).get("user_id")); // 机械行业 数据
                    list.get(i).put("danger5",e);
                    sum5 += e;

                    Integer  f = tCheckMapper.selectGreateDanger("轻工",(Integer) list.get(i).get("user_id")); // 轻工行业 数据
                    list.get(i).put("danger6",f);
                    sum6 += f;

                    Integer  g = tCheckMapper.selectGreateDanger("纺织",(Integer) list.get(i).get("user_id")); // 纺织行业 数据
                    list.get(i).put("danger7",g);
                    sum7 += g;

                    Integer  h = tCheckMapper.selectGreateDanger("商贸",(Integer) list.get(i).get("user_id")); // 商贸行业 数据
                    list.get(i).put("danger8",h);
                    sum8 += h;

                    Integer  i1 = tCheckMapper.selectGreateDanger("烟花",(Integer) list.get(i).get("user_id")); // 烟花行业 数据
                    list.get(i).put("danger9",i1);
                    sum9 += i1;

                    Integer  j = tCheckMapper.selectGreateDanger11((Integer) list.get(i).get("user_id")); // 其他行业 数据
                    list.get(i).put("danger10",j);
                    sum10 += j;

                    Integer count = a + b + c + d + e + f + g + h + i1 + j;

                    list.get(i).put("danger11",count);  // 某个公司的所有行业合计

                }
            }

        }else {

            list1 = new ArrayList<Map<String, Object>>();

            if (findAllIds(user, list1)) return "global/evaluate/globalError";

            if (null == list1 && list1.size() == 0){// 如果该区域下的下属区域没有公司

                return "global/evaluate/globalError";// 返回提示页面

            }else if (null != list1 && list1.size() != 0){

                for (int i = 0; i < list1.size(); i++) {

                    Integer a = tCheckMapper.greateDangerCount("化工",(String) list1.get(i).get("user_id")); // 化工行业 数据
                    list1.get(i).put("danger1",a);
                    sum1 += a;

                    Integer  b = tCheckMapper.greateDangerCount("冶金",(String) list1.get(i).get("user_id")); // 冶金行业 数据
                    list1.get(i).put("danger2",b);
                    sum2 += b;

                    Integer  c = tCheckMapper.greateDangerCount("有色",(String) list1.get(i).get("user_id")); // 有色行业 数据
                    list1.get(i).put("danger3",c);
                    sum3 += c;

                    Integer  d = tCheckMapper.greateDangerCount("建材",(String) list1.get(i).get("user_id")); // 建材行业 数据
                    list1.get(i).put("danger4",d);
                    sum4 += d;

                    Integer  e = tCheckMapper.greateDangerCount("机械",(String) list1.get(i).get("user_id")); // 机械行业 数据
                    list1.get(i).put("danger5",e);
                    sum5 += e;

                    Integer  f = tCheckMapper.greateDangerCount("轻工",(String) list1.get(i).get("user_id")); // 轻工行业 数据
                    list1.get(i).put("danger6",f);
                    sum6 += f;

                    Integer  g = tCheckMapper.greateDangerCount("纺织",(String) list1.get(i).get("user_id")); // 纺织行业 数据
                    list1.get(i).put("danger7",g);
                    sum7 += g;

                    Integer  h = tCheckMapper.greateDangerCount("商贸",(String) list1.get(i).get("user_id")); // 商贸行业 数据
                    list1.get(i).put("danger8",h);
                    sum8 += h;

                    Integer  i1 = tCheckMapper.greateDangerCount("烟花",(String) list1.get(i).get("user_id")); // 烟花行业 数据
                    list1.get(i).put("danger9",i1);
                    sum9 += i1;

                    Integer  j = tCheckMapper.greateDangerCount11((String) list1.get(i).get("user_id")); // 其他行业 数据
                    list1.get(i).put("danger10",j);
                    sum10 += j;

                    Integer count = a + b + c + d + e + f + g + h + i1 + j;

                    list1.get(i).put("danger11",count);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        

                }
            }
        }

        Map<String,Object> map = new HashMap<>();

        Integer counts = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 ;

        DecimalFormat df = new DecimalFormat("0.00");

        findCounts1(sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, map, counts, df);

        // 合计 坚
        map.put("sum1",sum1);
        map.put("sum2",sum2);
        map.put("sum3",sum3);
        map.put("sum4",sum4);
        map.put("sum5",sum5);
        map.put("sum6",sum6);
        map.put("sum7",sum7);
        map.put("sum8",sum8);
        map.put("sum9",sum9);
        map.put("sum10",sum10);
        map.put("counts",counts);

        model.addAttribute("data",new Date());

        if (user.getUserType() == 4){
            list.add(map);
            model.addAttribute("list",list);
            return "global/company/evaluate/sortTj";
        }else {
            list1.add(map);
            model.addAttribute("list",list1);
            return "global/company/evaluate/sortTj-up";
        }

    }



    /**
     * create by  : 小明！！！
     * description: TODO 政府端 绩效分析表格数据 行业  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     * create time: 2019/8/26 9:25
     */
    @RequestMapping(value = "zf-performance-industry")
    public String performance(HttpServletRequest request, Model model, Integer flag){

        User user = getLoginUser(request);
        request.getSession().setMaxInactiveInterval(60*60*34);

        if (null == flag){
            flag = 1;
        }
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = null;
        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;

        List<Map<String,Object>> list11 = (List<Map<String,Object>>)request.getSession().getAttribute("list11");
        Integer userId = (Integer)request.getSession().getAttribute("userId11");
        Integer flag11 = (Integer)request.getSession().getAttribute("flag11");

        if (null == flag11 || flag11 != flag){
            list11 = null;
        }

        if (null == userId || userId != user.getId()){
            list11 = null;
        }

        if ((null == list11 || list11.size() == 0)){
            if (user.getUserType() == 4  || user.getUserType() == 10){ // 如果登录的账户是村级账号

                if (user.getUserType() == 4){ // 村级账户
                    list = companyMapper.selectListsCompanyIdVillage(user.getId());
                }else if (user.getUserType() == 10){ // 惠山化工账号
                    list = companyMapper.selectListsTidCompanyId(user.getId());
                }

                if (null == list || list.size() == 0){// 如果该区域下没有公司

                    return "global/evaluate/globalError";// 返回提示页面

                }else if (null != list || list.size() != 0) {

                    for (int i = 0; i < list.size(); i++) {

                        Integer a = tCheckMapper.zfPerformanceCount22(flag,"化工",(Integer) list.get(i).get("user_id")); // 化工行业 数据
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        Integer  b = tCheckMapper.zfPerformanceCount22(flag,"冶金",(Integer) list.get(i).get("user_id")); // 冶金行业 数据
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        Integer  c = tCheckMapper.zfPerformanceCount22(flag,"有色",(Integer) list.get(i).get("user_id")); // 有色行业 数据
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        Integer  d = tCheckMapper.zfPerformanceCount22(flag,"建材",(Integer) list.get(i).get("user_id")); // 建材行业 数据
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        Integer  e = tCheckMapper.zfPerformanceCount22(flag,"机械",(Integer) list.get(i).get("user_id")); // 机械行业 数据
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        Integer  f = tCheckMapper.zfPerformanceCount22(flag,"轻工",(Integer) list.get(i).get("user_id")); // 轻工行业 数据
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        Integer  g = tCheckMapper.zfPerformanceCount22(flag,"纺织",(Integer) list.get(i).get("user_id")); // 纺织行业 数据
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        Integer  h = tCheckMapper.zfPerformanceCount22(flag,"商贸",(Integer) list.get(i).get("user_id")); // 商贸行业 数据
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        Integer  i1 = tCheckMapper.zfPerformanceCount22(flag,"烟花",(Integer) list.get(i).get("user_id")); // 烟花行业 数据
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        Integer  j = tCheckMapper.zfPerformanceCount22(flag,"其他",(Integer) list.get(i).get("user_id")); // 其他行业 数据
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        Integer count = a + b + c + d + e + f + g + h + i1 + j;

                        list.get(i).put("danger11",count);  // 某个公司的所有行业合计

                    }
                }

            }else {

                list1 = new ArrayList<Map<String, Object>>();

                if (findAllIds(user, list1)) return "global/evaluate/globalError";

                if (null == list1 && list1.size() == 0){// 如果该区域下的下属区域没有公司

                    return "global/evaluate/globalError";// 返回提示页面

                }else if (null != list1 && list1.size() != 0){

                    for (int i = 0; i < list1.size(); i++) {

                        Integer a = tCheckMapper.zfPerformanceCount(flag,"化工",(String) list1.get(i).get("user_id")); // 化工行业 数据
                        list1.get(i).put("danger1",a);
                        sum1 += a;

                        Integer  b = tCheckMapper.zfPerformanceCount(flag,"冶金",(String) list1.get(i).get("user_id")); // 冶金行业 数据
                        list1.get(i).put("danger2",b);
                        sum2 += b;

                        Integer  c = tCheckMapper.zfPerformanceCount(flag,"有色",(String) list1.get(i).get("user_id")); // 有色行业 数据
                        list1.get(i).put("danger3",c);
                        sum3 += c;

                        Integer  d = tCheckMapper.zfPerformanceCount(flag,"建材",(String) list1.get(i).get("user_id")); // 建材行业 数据
                        list1.get(i).put("danger4",d);
                        sum4 += d;

                        Integer  e = tCheckMapper.zfPerformanceCount(flag,"机械",(String) list1.get(i).get("user_id")); // 机械行业 数据
                        list1.get(i).put("danger5",e);
                        sum5 += e;

                        Integer  f = tCheckMapper.zfPerformanceCount(flag,"轻工",(String) list1.get(i).get("user_id")); // 轻工行业 数据
                        list1.get(i).put("danger6",f);
                        sum6 += f;

                        Integer  g = tCheckMapper.zfPerformanceCount(flag,"纺织",(String) list1.get(i).get("user_id")); // 纺织行业 数据
                        list1.get(i).put("danger7",g);
                        sum7 += g;

                        Integer  h = tCheckMapper.zfPerformanceCount(flag,"商贸",(String) list1.get(i).get("user_id")); // 商贸行业 数据
                        list1.get(i).put("danger8",h);
                        sum8 += h;

                        Integer  i1 = tCheckMapper.zfPerformanceCount(flag,"烟花",(String) list1.get(i).get("user_id")); // 烟花行业 数据
                        list1.get(i).put("danger9",i1);
                        sum9 += i1;

                        Integer  j = tCheckMapper.zfPerformanceCount(flag,"其他",(String) list1.get(i).get("user_id")); // 其他行业 数据
                        list1.get(i).put("danger10",j);
                        sum10 += j;

                        Integer count = a + b + c + d + e + f + g + h + i1 + j;

                        list1.get(i).put("danger11",count);

                    }
                }
            }

            Map<String,Object> map = new HashMap<>();

            Integer counts = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 ;

            DecimalFormat df = new DecimalFormat("0.00");

            findCounts1(sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, map, counts, df);

            // 合计 坚
            map.put("sum1",sum1);
            map.put("sum2",sum2);
            map.put("sum3",sum3);
            map.put("sum4",sum4);
            map.put("sum5",sum5);
            map.put("sum6",sum6);
            map.put("sum7",sum7);
            map.put("sum8",sum8);
            map.put("sum9",sum9);
            map.put("sum10",sum10);
            map.put("counts",counts);

            model.addAttribute("data",new Date());
            Integer uerId = (Integer)request.getSession().getAttribute("userId");
            if (user.getUserType() == 4 || user.getUserType() == 10){
                list.add(map);
                model.addAttribute("list",list);
                request.getSession().setAttribute("list11",list);
                request.getSession().setAttribute("userId11",user.getId());
                request.getSession().setAttribute("flag11",flag);
                return "global/company/evaluate/villageDown/zf-performance-industry-charts1";
            }else {
                list1.add(map);
                model.addAttribute("list",list1);
                request.getSession().setAttribute("list11",list1);
                request.getSession().setAttribute("flag11",flag);
                request.getSession().setAttribute("userId11",user.getId());
                return "global/company/evaluate/villageUp/zf-performance-industry-charts1";
            }

        }else {

            if (user.getUserType() == 4 || user.getUserType() == 10){
                model.addAttribute("list",list11);
                model.addAttribute("data",new Date());
                return "global/company/evaluate/villageDown/zf-performance-industry-charts1";
            }else {
                model.addAttribute("list",list11);
                model.addAttribute("data",new Date());
                return "global/company/evaluate/villageUp/zf-performance-industry-charts1";
            }
        }

    }

    private void findCounts1(Integer sum1, Integer sum2, Integer sum3, Integer sum4, Integer sum5, Integer sum6, Integer sum7, Integer sum8, Integer sum9, Integer sum10, Map<String, Object> map, Integer counts, DecimalFormat df) {
        if (null != counts && 0 != counts) {

            if (null != sum1 && 0 != sum1) {
                String str = df.format((float)sum1 / counts * 100);
                map.put("result1",str + "%");

            } else {
                map.put("result1","0.00%");  // 化工 占比数据
            }

            if (null != sum2 && 0 != sum2) {
                String str = df.format((float)sum2 / counts * 100);
                map.put("result2",str + "%");

            } else {
                map.put("result2","0.00%"); // 冶金 占比数据
            }

            if (null != sum3 && 0 != sum3) {
                String str = df.format((float)sum3 / counts * 100);
                map.put("result3",str + "%");

            } else {
                map.put("result3","0.00%");  // 有色 占比数据 竖
            }

            if (null != sum4 && 0 != sum4) {
                String str = df.format((float)sum4 / counts * 100);
                map.put("result4",str + "%");

            } else {
                map.put("result4","0.00%"); // 建材 占比数据
            }

            if (null != sum5 && 0 != sum5) {
                String str = df.format((float)sum5 / counts * 100);
                map.put("result5",str + "%");

            } else {
                map.put("result5","0.00%"); // 机械 占比数据
            }

            if (null != sum6 && 0 != sum6) {
                String str = df.format((float)sum6 / counts * 100);
                map.put("result6",str + "%");

            } else {
                map.put("result6","0.00%"); // 轻工 占比数据
            }

            if (null != sum7 && 0 != sum7) {
                String str = df.format((float)sum7 / counts * 100);
                map.put("result7",str + "%");

            } else {
                map.put("result7","0.00%"); // 纺织 占比数据
            }

            if (null != sum8 && 0 != sum8) {
                String str = df.format((float)sum8 / counts * 100);
                map.put("result8",str + "%");

            } else {
                map.put("result8","0.00%"); // 商贸 占比数据
            }

            if (null != sum9 && 0 != sum9) {
                String str = df.format((float)sum9 / counts * 100);
                map.put("result9",str + "%");

            } else {
                map.put("result9","0.00%"); // 烟花 占比数据
            }

            if (null != sum10 && 0 != sum10) {
                String str = df.format((float)sum10 / counts * 100);
                map.put("result10",str + "%");

            } else {
                map.put("result10","0.00%"); // 其他 占比数据
            }

        }else {
            // 占比 坚
            map.put("result1", "0.00%");
            map.put("result2", "0.00%");
            map.put("result3", "0.00%");
            map.put("result4", "0.00%");
            map.put("result5", "0.00%");
            map.put("result6", "0.00%");
            map.put("result7", "0.00%");
            map.put("result8", "0.00%");
            map.put("result9", "0.00%");
            map.put("result10", "0.00%");
        }
    }


    /**
     * create by  : 小明！！！
     * description: TODO  政府端 隐患排查对象分析 风险
     * create time: 2019/8/26 11:28
     */
    @RequestMapping(value = "zf-check-hidden-trouble")
    public String zfHiddenTrouble(HttpServletRequest request, Model model ,Integer flag){

        User user = getLoginUser(request);

        if (null == flag){
            flag = 1;
        }
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = null;
        List<Map<String,Object>> list2 = null;
        Map<String,Object> map = new HashMap<>();

        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;
        Integer sum11 = 0;
        Integer sum12 = 0;
        Integer sum13 = 0;
        Integer sum14 = 0;
        Integer sum15 = 0;
        Integer sum16 = 0;
        Integer sum17 = 0;
        Integer sum18 = 0;
        Integer sum19 = 0;
        Integer now = 0;
        Integer now1 = 0;
        request.getSession().setMaxInactiveInterval(60*60*24);
        List<Map<String,Object>> list22 = (List<Map<String,Object>>) request.getSession().getAttribute("list22");
        Integer userId = (Integer)request.getSession().getAttribute("userId22");
        Integer flag22 = (Integer)request.getSession().getAttribute("flag22");

        if (null == flag22 || flag22 != flag){
            list22 = null;
        }

        if (null == userId || userId != user.getId()){
            list22 = null;
        }

        if (null == list22 || list22.size() == 0){
            if (user.getUserType() == 4 || user.getUserType() == 10){
                if (user.getUserType() == 4){ // 村级账户
                    list = companyMapper.selectListsCompanyIdVillage(user.getId());
                }else if (user.getUserType() == 10){ // 惠山化工账号
                    list = companyMapper.selectListsTidCompanyId(user.getId());
                }

                if (null == list || list.size() == 0){

                    return "global/evaluate/globalError";

                }else if (null != list && list.size() != 0){

                    for (int i = 0; i < list.size(); i++) {

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("基础管理", flag, (Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleBasics(flag, (Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                        now1 = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("基础管理", flag, (Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer s = list2.size() + now + now1;
                        list.get(i).put("danger19", s);
                        sum19 += s;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("设计总图", flag, (Integer) list.get(i).get("user_id")); // 设计总图 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("设计总图", flag, (Integer) list.get(i).get("user_id")); // 设计总图 隐患数据
                        Integer a = list2.size() + now;
                        list.get(i).put("danger1", a);
                        sum1 += a;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("试生产", flag, (Integer) list.get(i).get("user_id")); // 试生产 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("试生产", flag, (Integer) list.get(i).get("user_id")); // 试生产 隐患数据
                        Integer b = list2.size() + now;
                        list.get(i).put("danger2", b);
                        sum2 += b;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("装置运行", flag, (Integer) list.get(i).get("user_id")); // 装置运行 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("装置运行", flag, (Integer) list.get(i).get("user_id")); // 装置运行 隐患数据
                        Integer c = list2.size() + now;
                        list.get(i).put("danger3", c);
                        sum3 += c;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("设备安全", flag, (Integer) list.get(i).get("user_id")); // 设备安全 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("设备安全", flag, (Integer) list.get(i).get("user_id")); // 设备安全 隐患数据
                        Integer d = list2.size() + now;
                        list.get(i).put("danger4", d);
                        sum4 += d;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("仪表安全", flag, (Integer) list.get(i).get("user_id")); // 仪表安全 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("仪表安全", flag, (Integer) list.get(i).get("user_id")); // 仪表安全 隐患数据
                        Integer e = list2.size() + now;
                        list.get(i).put("danger5", e);
                        sum5 += e;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("电气安全", flag, (Integer) list.get(i).get("user_id")); // 电气安全 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("电气安全", flag, (Integer) list.get(i).get("user_id")); // 电气安全 隐患数据
                        Integer f = list2.size() + now;
                        list.get(i).put("danger6", f);
                        sum6 += f;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("应急消防", flag, (Integer) list.get(i).get("user_id")); // 应急消防 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("应急消防", flag, (Integer) list.get(i).get("user_id")); // 应急消防 隐患数据
                        Integer g = list2.size() + now;
                        list.get(i).put("danger7", g);
                        sum7 += g;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("特殊管控", flag, (Integer) list.get(i).get("user_id")); // 特殊管控 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("特殊管控", flag, (Integer) list.get(i).get("user_id")); // 特殊管控 隐患数据
                        Integer h = list2.size() + now;
                        list.get(i).put("danger8", h);
                        sum8 += h;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("行为环境", flag, (Integer) list.get(i).get("user_id")); // 行为环境 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("行为环境", flag, (Integer) list.get(i).get("user_id")); // 行为环境 隐患数据
                        Integer i1 = list2.size() + now;
                        list.get(i).put("danger9", i1);
                        sum9 += i1;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("生产现场", flag, (Integer) list.get(i).get("user_id")); // 生产现场 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("生产现场", flag, (Integer) list.get(i).get("user_id")); // 生产现场 隐患数据
                        Integer j = list2.size() + now;
                        list.get(i).put("danger10", j);
                        sum10 += j;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("公辅工程", flag, (Integer) list.get(i).get("user_id")); // 公辅工程 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("公辅工程", flag, (Integer) list.get(i).get("user_id")); // 公辅工程 隐患数据
                        Integer k = list2.size() + now;
                        list.get(i).put("danger11", k);
                        sum11 += k;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("特种设备", flag, (Integer) list.get(i).get("user_id")); // 特种设备 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("特种设备", flag, (Integer) list.get(i).get("user_id")); // 特种设备 隐患数据
                        Integer l = list2.size() + now;
                        list.get(i).put("danger12", l);
                        sum12 += l;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("专项行业", flag, (Integer) list.get(i).get("user_id")); // 专项行业 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("专项行业", flag, (Integer) list.get(i).get("user_id")); // 专项行业 隐患数据
                        Integer m = list2.size() + now;
                        list.get(i).put("danger13", m);
                        sum13 += m;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("生产工艺", flag, (Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("生产工艺", flag, (Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                        Integer n = list2.size() + now;
                        list.get(i).put("danger14", n);
                        sum14 += n;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("设备设施", flag, (Integer) list.get(i).get("user_id")); // 特种设备 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("设备设施", flag, (Integer) list.get(i).get("user_id")); // 特种设备 隐患数据
                        Integer o = list2.size() + now;
                        list.get(i).put("danger15", o);
                        sum15 += o;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("危化管理", flag, (Integer) list.get(i).get("user_id")); // 专项行业 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("危化管理", flag, (Integer) list.get(i).get("user_id")); // 专项行业 隐患数据
                        Integer p = list2.size() + now;
                        list.get(i).put("danger16", p);
                        sum16 += p;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("安全设施", flag, (Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("安全设施", flag, (Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                        Integer q = list2.size() + now;
                        list.get(i).put("danger17", q);
                        sum17 += q;

                        list2 = tCheckItemMapper.zfHiddenTroubleNews("其他", flag, (Integer) list.get(i).get("user_id")); // 其他 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTrouble22GroupBy("其他", flag, (Integer) list.get(i).get("user_id")); // 其他 隐患数据
                        Integer r = list2.size() + now;
                        list.get(i).put("danger18", r);
                        sum18 += r;

                        Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                        list.get(i).put("danger20", count);  // 某个车间的所有种类隐患的合计
                    }
                }

            }else {

                list1 = new ArrayList<Map<String, Object>>();

                if (findAllIds(user, list1)) return "global/evaluate/globalError";

                if (null == list1 && list1.size() == 0){// 如果该区域下的下属区域没有公司

                    return "global/evaluate/globalError";// 返回提示页面

                }else if (null != list1 && list1.size() != 0){

                    for (int i = 0; i < list1.size(); i++) {

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("基础管理", flag, (String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeBasics(flag, (String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        now1 = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("基础管理", flag, (String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer s = list2.size() + now + now1;
                        list1.get(i).put("danger19", s);
                        sum19 += s;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("设计总图", flag, (String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("设计总图", flag, (String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer a = list2.size() + now;
                        list1.get(i).put("danger1", a);
                        sum1 += a;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("试生产", flag, (String) list1.get(i).get("user_id")); // 生产工艺 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("试生产", flag, (String) list1.get(i).get("user_id")); // 生产工艺 隐患数据
                        Integer b = list2.size() + now;
                        list1.get(i).put("danger2", b);
                        sum2 += b;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("装置运行", flag, (String) list1.get(i).get("user_id")); // 设备设施 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("装置运行", flag, (String) list1.get(i).get("user_id")); // 设备设施 隐患数据
                        Integer c = list2.size() + now;
                        list1.get(i).put("danger3", c);
                        sum3 += c;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("设备安全", flag, (String) list1.get(i).get("user_id")); // 公用工程 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("设备安全", flag, (String) list1.get(i).get("user_id")); // 公用工程 隐患数据
                        Integer d = list2.size() + now;
                        list1.get(i).put("danger4", d);
                        sum4 += d;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("仪表安全", flag, (String) list1.get(i).get("user_id")); // 特种设备 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("仪表安全", flag, (String) list1.get(i).get("user_id")); // 特种设备 隐患数据
                        Integer e = list2.size() + now;
                        list1.get(i).put("danger5", e);
                        sum5 += e;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("电气安全", flag, (String) list1.get(i).get("user_id")); // 生产现场 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("电气安全", flag, (String) list1.get(i).get("user_id")); // 生产现场 隐患数据
                        Integer f = list2.size() + now;
                        list1.get(i).put("danger6", f);
                        sum6 += f;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("应急消防", flag, (String) list1.get(i).get("user_id")); // 行为环境 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("应急消防", flag, (String) list1.get(i).get("user_id")); // 行为环境 隐患数据
                        Integer g = list2.size() + now;
                        list1.get(i).put("danger7", g);
                        sum7 += g;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("特殊管控", flag, (String) list1.get(i).get("user_id")); // 危化管理 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("特殊管控", flag, (String) list1.get(i).get("user_id")); // 危化管理 隐患数据
                        Integer h = list2.size() + now;
                        list1.get(i).put("danger8", h);
                        sum8 += h;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("行为环境", flag, (String) list1.get(i).get("user_id")); // 消防安全 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("行为环境", flag, (String) list1.get(i).get("user_id")); // 消防安全 隐患数据
                        Integer i1 = list2.size() + now;
                        list1.get(i).put("danger9", i1);
                        sum9 += i1;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("生产现场", flag, (String) list1.get(i).get("user_id")); // 用电安全 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("生产现场", flag, (String) list1.get(i).get("user_id")); // 用电安全 隐患数据
                        Integer j = list2.size() + now;
                        list1.get(i).put("danger10", j);
                        sum10 += j;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("公辅工程", flag, (String) list1.get(i).get("user_id")); // 安全设施 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("公辅工程", flag, (String) list1.get(i).get("user_id")); // 安全设施 隐患数据
                        Integer k = list2.size() + now;
                        list1.get(i).put("danger11", k);
                        sum11 += k;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("特种设备", flag, (String) list1.get(i).get("user_id")); // 防雷静电 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("特种设备", flag, (String) list1.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer l = list2.size() + now;
                        list1.get(i).put("danger12", l);
                        sum12 += l;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("专项行业", flag, (String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("专项行业", flag, (String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer m = list2.size() + now;
                        list1.get(i).put("danger13", m);
                        sum13 += m;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("生产工艺", flag, (String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("生产工艺", flag, (String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer n = list2.size() + now;
                        list1.get(i).put("danger14", n);
                        sum14 += n;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("设备设施", flag, (String) list1.get(i).get("user_id")); // 防雷静电 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("设备设施", flag, (String) list1.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer o = list2.size() + now;
                        list1.get(i).put("danger15", o);
                        sum15 += o;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("危化管理", flag, (String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("危化管理", flag, (String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer p = list2.size() + now;
                        list1.get(i).put("danger16", p);
                        sum16 += p;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("安全设施", flag, (String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("安全设施", flag, (String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer q = list2.size() + now;
                        list1.get(i).put("danger17", q);
                        sum17 += q;

                        list2 = tCheckItemMapper.zfHiddenTroubleTypeNews("其他", flag, (String) list1.get(i).get("user_id")); // 其他 隐患数据
                        now = list2.size();
                        list2 = tCheckItemMapper.zfHiddenTroubleTypeGroupBy("其他", flag, (String) list1.get(i).get("user_id")); // 其他 隐患数据
                        Integer r = list2.size() + now;
                        list1.get(i).put("danger18", r);
                        sum18 += r;

                        Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                        list1.get(i).put("danger20", count);  // 某个车间的所有种类隐患的合计

                    }
                }
            }

            Integer counts = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 + sum11 + sum12 + sum13 + sum14 + sum15 + sum16 + sum17 + sum18 + sum19;

            DecimalFormat df = new DecimalFormat("0.00");

            findCounts(map, sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, sum11, sum12, sum13, sum14, sum15, sum16, sum17, sum18, sum19, counts, df);
            // 合计 坚
            map.put("sum1",sum1);
            map.put("sum2",sum2);
            map.put("sum3",sum3);
            map.put("sum4",sum4);
            map.put("sum5",sum5);
            map.put("sum6",sum6);
            map.put("sum7",sum7);
            map.put("sum8",sum8);
            map.put("sum9",sum9);
            map.put("sum10",sum10);
            map.put("sum11",sum11);
            map.put("sum12",sum12);
            map.put("sum13",sum13);
            map.put("sum14",sum14);
            map.put("sum15",sum15);
            map.put("sum16",sum16);
            map.put("sum17",sum17);
            map.put("sum18",sum18);
            map.put("sum19",sum19);

            map.put("counts",counts);

            model.addAttribute("data",new Date());

            if (user.getUserType() == 4 || user.getUserType() == 10){
                list.add(map);
                model.addAttribute("list",list);
                request.getSession().setAttribute("list22",list);
                request.getSession().setAttribute("flag22",flag);
                request.getSession().setAttribute("userId22",user.getId());
                return "global/company/evaluate/villageDown/zf-check-hidden-trouble-charts2";
            }else {
                list1.add(map);
                model.addAttribute("list",list1);
                request.getSession().setAttribute("list22",list1);
                request.getSession().setAttribute("flag22",flag);
                request.getSession().setAttribute("userId22",user.getId());
                return "global/company/evaluate/villageUp/zf-check-hidden-trouble-charts2";
            }
        }else {

            if (user.getUserType() == 4 || user.getUserType() == 10){

                model.addAttribute("list",list22);
                model.addAttribute("data",new Date());
                request.getSession().setAttribute("userId22",user.getId());
                return "global/company/evaluate/villageDown/zf-check-hidden-trouble-charts2";
            }else {
                model.addAttribute("list",list22);
                model.addAttribute("data",new Date());
                request.getSession().setAttribute("userId22",user.getId());
                return "global/company/evaluate/villageUp/zf-check-hidden-trouble-charts2";
            }
        }

    }


    /**
     * create by  : 小明！！！
     * description: TODO    政府端 隐患数据分析 风险
     * create time: 2019/8/26 11:54
     */
    @RequestMapping(value = "zf-hidden-trouble")
    public String hiddenTrouble(HttpServletRequest request, Model model, Integer flag){

        User user = getLoginUser(request);
        request.getSession().setMaxInactiveInterval(60*60*24);
        if (null == flag){
            flag = 1;
        }
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = null;
        Map<String,Object> map = new HashMap<>();

        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;
        Integer sum11 = 0;
        Integer sum12 = 0;
        Integer sum13 = 0;
        Integer sum14 = 0;
        Integer sum15 = 0;
        Integer sum16 = 0;
        Integer sum17 = 0;
        Integer sum18 = 0;
        Integer sum19 = 0;

        List<Map<String,Object>> list44 = (List<Map<String,Object>>) request.getSession().getAttribute("list44");
        Integer userId = (Integer) request.getSession().getAttribute("userId44");
        Integer flag44 = (Integer) request.getSession().getAttribute("flag44");
        if (null == flag44 || flag44 != flag){
            list44 = null;
        }

        if (null == userId || userId != user.getId()){
            list44 = null;
        }

        if (null == list44 || list44.size() == 0){

            if (user.getUserType() == 4 || user.getUserType() == 10){

                if (user.getUserType() == 4){ // 村级账户
                    list = companyMapper.selectListsCompanyIdVillage(user.getId());
                }else if (user.getUserType() == 10){ // 惠山化工账号
                    list = companyMapper.selectListsTidCompanyId(user.getId());
                }

                if (null == list || list.size() == 0){

                    return "global/evaluate/globalError";

                }else if (null != list && list.size() != 0){

                    for (int i = 0; i < list.size(); i++) {

                        Integer s1 = tCheckItemMapper.zhengFu99ChartDataNews("基础管理",flag,(Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer s2 = tCheckItemMapper.zhengFu99ChartDataBasics(flag,(Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer s3 = tCheckItemMapper.zhengFuChartData99("基础管理",flag,(Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer s = s1 + s2 + s3;
                        list.get(i).put("danger19",s);
                        sum19 += s;

                        Integer a1 = tCheckItemMapper.zhengFu99ChartDataNews("设计总图",flag,(Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer a2 = tCheckItemMapper.zhengFuChartData99("设计总图",flag,(Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer a = a1 + a2;
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        Integer b1 = tCheckItemMapper.zhengFu99ChartDataNews("试生产",flag,(Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                        Integer b2 = tCheckItemMapper.zhengFuChartData99("试生产",flag,(Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                        Integer b = b1 + b2;
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        Integer c1 = tCheckItemMapper.zhengFu99ChartDataNews("装置运行",flag,(Integer) list.get(i).get("user_id")); // 设备设施 隐患数据
                        Integer c2 = tCheckItemMapper.zhengFuChartData99("装置运行",flag,(Integer) list.get(i).get("user_id")); // 设备设施 隐患数据
                        Integer c = c1 + c2;
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        Integer d1 = tCheckItemMapper.zhengFu99ChartDataNews("设备安全",flag,(Integer) list.get(i).get("user_id")); // 公用工程 隐患数据
                        Integer d2 = tCheckItemMapper.zhengFuChartData99("设备安全",flag,(Integer) list.get(i).get("user_id")); // 公用工程 隐患数据
                        Integer d = d1 + d2;
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        Integer e1 = tCheckItemMapper.zhengFu99ChartDataNews("仪表安全",flag,(Integer) list.get(i).get("user_id")); // 特种设备 隐患数据
                        Integer e2 = tCheckItemMapper.zhengFuChartData99("仪表安全",flag,(Integer) list.get(i).get("user_id")); // 特种设备 隐患数据
                        Integer e = e1 + e2;
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        Integer f1 = tCheckItemMapper.zhengFu99ChartDataNews("电气安全",flag,(Integer) list.get(i).get("user_id")); // 生产现场 隐患数据
                        Integer f2 = tCheckItemMapper.zhengFuChartData99("电气安全",flag,(Integer) list.get(i).get("user_id")); // 生产现场 隐患数据
                        Integer f = f1 + f2;
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        Integer g1 = tCheckItemMapper.zhengFu99ChartDataNews("应急消防",flag,(Integer) list.get(i).get("user_id")); // 行为环境 隐患数据
                        Integer g2 = tCheckItemMapper.zhengFuChartData99("应急消防",flag,(Integer) list.get(i).get("user_id")); // 行为环境 隐患数据
                        Integer g = g1 + g2;
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        Integer h1 = tCheckItemMapper.zhengFu99ChartDataNews("特殊管控",flag,(Integer) list.get(i).get("user_id")); // 危化管理 隐患数据
                        Integer h2 = tCheckItemMapper.zhengFuChartData99("特殊管控",flag,(Integer) list.get(i).get("user_id")); // 危化管理 隐患数据
                        Integer h = h1 + h2;
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        Integer i11 = tCheckItemMapper.zhengFu99ChartDataNews("行为环境",flag,(Integer) list.get(i).get("user_id")); // 消防安全 隐患数据
                        Integer i12 = tCheckItemMapper.zhengFuChartData99("行为环境",flag,(Integer) list.get(i).get("user_id")); // 消防安全 隐患数据
                        Integer i1 = i11 + i12;
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        Integer j1 = tCheckItemMapper.zhengFu99ChartDataNews("生产现场",flag,(Integer) list.get(i).get("user_id")); // 用电安全 隐患数据
                        Integer j2 = tCheckItemMapper.zhengFuChartData99("生产现场",flag,(Integer) list.get(i).get("user_id")); // 用电安全 隐患数据
                        Integer j = j1 + j2;
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        Integer k1 = tCheckItemMapper.zhengFu99ChartDataNews("公辅工程",flag,(Integer) list.get(i).get("user_id")); // 安全设施 隐患数据
                        Integer k2 = tCheckItemMapper.zhengFuChartData99("公辅工程",flag,(Integer) list.get(i).get("user_id")); // 安全设施 隐患数据
                        Integer k = k1 + k2;
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        Integer l1 = tCheckItemMapper.zhengFu99ChartDataNews("特种设备",flag,(Integer) list.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer l2 = tCheckItemMapper.zhengFuChartData99("特种设备",flag,(Integer) list.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer l = l1 + l2;
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        Integer m1 = tCheckItemMapper.zhengFu99ChartDataNews("专项行业",flag,(Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer m2 = tCheckItemMapper.zhengFuChartData99("专项行业",flag,(Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer m = m1 + m2;
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        Integer n1 = tCheckItemMapper.zhengFu99ChartDataNews("生产工艺",flag,(Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer n2 = tCheckItemMapper.zhengFuChartData99("生产工艺",flag,(Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer n = n1 + n2;
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        Integer o1 = tCheckItemMapper.zhengFu99ChartDataNews("设备设施",flag,(Integer) list.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer o2 = tCheckItemMapper.zhengFuChartData99("设备设施",flag,(Integer) list.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer o = o1 + o2;
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        Integer p1 = tCheckItemMapper.zhengFu99ChartDataNews("危化管理",flag,(Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer p2 = tCheckItemMapper.zhengFuChartData99("危化管理",flag,(Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer p = p1 + p2;
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        Integer q1 = tCheckItemMapper.zhengFu99ChartDataNews("安全设施",flag,(Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer q2 = tCheckItemMapper.zhengFuChartData99("安全设施",flag,(Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer q = q1 + q2;
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        Integer r1 = tCheckItemMapper.zhengFu99ChartDataNews("其他",flag,(Integer) list.get(i).get("user_id")); // 其他 隐患数据
                        Integer r2 = tCheckItemMapper.zhengFuChartData99("其他",flag,(Integer) list.get(i).get("user_id")); // 其他 隐患数据
                        Integer r = r1 + r2;
                        list.get(i).put("danger18",r);
                        sum18 += r;

                        Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                        list.get(i).put("danger20",count);  // 某个车间的所有种类隐患的合计
                    }
                }

            }else {

                list1 = new ArrayList<Map<String, Object>>();

                if (findAllIds(user, list1)) return "global/evaluate/globalError";

                if (null == list1 && list1.size() == 0){// 如果该区域下的下属区域没有公司

                    return "global/evaluate/globalError";// 返回提示页面

                }else if (null != list1 && list1.size() != 0){

                    for (int i = 0; i < list1.size(); i++) {

                        Integer s1 = tCheckItemMapper.zhengFu88ChartDataNews("基础管理",flag,(String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer s2 = tCheckItemMapper.zhengFu88ChartDataBasics(flag,(String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer s3 = tCheckItemMapper.zhengFuChartData88("基础管理",flag,(String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer s = s1 + s2 + s3;
                        list1.get(i).put("danger19",s);
                        sum19 += s;

                        Integer a1 = tCheckItemMapper.zhengFu88ChartDataNews("设计总图",flag,(String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer a2 = tCheckItemMapper.zhengFuChartData88("设计总图",flag,(String) list1.get(i).get("user_id")); // 基础管理 隐患数据
                        Integer a = a1 + a2;
                        list1.get(i).put("danger1",a);
                        sum1 += a;

                        Integer b1 = tCheckItemMapper.zhengFu88ChartDataNews("试生产",flag,(String) list1.get(i).get("user_id")); // 生产工艺 隐患数据
                        Integer b2 = tCheckItemMapper.zhengFuChartData88("试生产",flag,(String) list1.get(i).get("user_id")); // 生产工艺 隐患数据
                        Integer b = b1 + b2;
                        list1.get(i).put("danger2",b);
                        sum2 += b;

                        Integer c1 = tCheckItemMapper.zhengFu88ChartDataNews("装置运行",flag,(String) list1.get(i).get("user_id")); // 设备设施 隐患数据
                        Integer c2 = tCheckItemMapper.zhengFuChartData88("装置运行",flag,(String) list1.get(i).get("user_id")); // 设备设施 隐患数据
                        Integer c = c1 + c2;
                        list1.get(i).put("danger3",c);
                        sum3 += c;

                        Integer d1 = tCheckItemMapper.zhengFu88ChartDataNews("设备安全",flag,(String) list1.get(i).get("user_id")); // 公用工程 隐患数据
                        Integer d2 = tCheckItemMapper.zhengFuChartData88("设备安全",flag,(String) list1.get(i).get("user_id")); // 公用工程 隐患数据
                        Integer d = d1 + d2;
                        list1.get(i).put("danger4",d);
                        sum4 += d;

                        Integer e1 = tCheckItemMapper.zhengFu88ChartDataNews("仪表安全",flag,(String) list1.get(i).get("user_id")); // 特种设备 隐患数据
                        Integer e2 = tCheckItemMapper.zhengFuChartData88("仪表安全",flag,(String) list1.get(i).get("user_id")); // 特种设备 隐患数据
                        Integer e = e1 + e2;
                        list1.get(i).put("danger5",e);
                        sum5 += e;

                        Integer f1 = tCheckItemMapper.zhengFu88ChartDataNews("电气安全",flag,(String) list1.get(i).get("user_id")); // 生产现场 隐患数据
                        Integer f2 = tCheckItemMapper.zhengFuChartData88("电气安全",flag,(String) list1.get(i).get("user_id")); // 生产现场 隐患数据
                        Integer f = f1 + f2;
                        list1.get(i).put("danger6",f);
                        sum6 += f;

                        Integer g1 = tCheckItemMapper.zhengFu88ChartDataNews("应急消防",flag,(String) list1.get(i).get("user_id")); // 行为环境 隐患数据
                        Integer g2 = tCheckItemMapper.zhengFuChartData88("应急消防",flag,(String) list1.get(i).get("user_id")); // 行为环境 隐患数据
                        Integer g = g1 + g2;
                        list1.get(i).put("danger7",g);
                        sum7 += g;

                        Integer h1 = tCheckItemMapper.zhengFu88ChartDataNews("特殊管控",flag,(String) list1.get(i).get("user_id")); // 危化管理 隐患数据
                        Integer h2 = tCheckItemMapper.zhengFuChartData88("特殊管控",flag,(String) list1.get(i).get("user_id")); // 危化管理 隐患数据
                        Integer h = h1 + h2;
                        list1.get(i).put("danger8",h);
                        sum8 += h;

                        Integer i11 = tCheckItemMapper.zhengFu88ChartDataNews("行为环境",flag,(String) list1.get(i).get("user_id")); // 消防安全 隐患数据
                        Integer i12 = tCheckItemMapper.zhengFuChartData88("行为环境",flag,(String) list1.get(i).get("user_id")); // 消防安全 隐患数据
                        Integer i1 = i11 + i12;
                        list1.get(i).put("danger9",i1);
                        sum9 += i1;

                        Integer j1 = tCheckItemMapper.zhengFu88ChartDataNews("生产现场",flag,(String) list1.get(i).get("user_id")); // 用电安全 隐患数据
                        Integer j2 = tCheckItemMapper.zhengFuChartData88("生产现场",flag,(String) list1.get(i).get("user_id")); // 用电安全 隐患数据
                        Integer j = j1 + j2;
                        list1.get(i).put("danger10",j);
                        sum10 += j;

                        Integer k1 = tCheckItemMapper.zhengFu88ChartDataNews("公辅工程",flag,(String) list1.get(i).get("user_id")); // 安全设施 隐患数据
                        Integer k2 = tCheckItemMapper.zhengFuChartData88("公辅工程",flag,(String) list1.get(i).get("user_id")); // 安全设施 隐患数据
                        Integer k = k1 + k2;
                        list1.get(i).put("danger11",k);
                        sum11 += k;

                        Integer l1 = tCheckItemMapper.zhengFu88ChartDataNews("特种设备",flag,(String) list1.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer l2 = tCheckItemMapper.zhengFuChartData88("特种设备",flag,(String) list1.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer l = l1 + l2;
                        list1.get(i).put("danger12",l);
                        sum12 += l;

                        Integer m1 = tCheckItemMapper.zhengFu88ChartDataNews("专项行业",flag,(String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer m2 = tCheckItemMapper.zhengFuChartData88("专项行业",flag,(String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer m = m1 + m2;
                        list1.get(i).put("danger13",m);
                        sum13 += m;

                        Integer n1 = tCheckItemMapper.zhengFu88ChartDataNews("生产工艺",flag,(String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer n2 = tCheckItemMapper.zhengFuChartData88("生产工艺",flag,(String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer n = n1 + n2;
                        list1.get(i).put("danger14",n);
                        sum14 += n;

                        Integer o1 = tCheckItemMapper.zhengFu88ChartDataNews("设备设施",flag,(String) list1.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer o2 = tCheckItemMapper.zhengFuChartData88("设备设施",flag,(String) list1.get(i).get("user_id")); // 防雷静电 隐患数据
                        Integer o = o1 + o2;
                        list1.get(i).put("danger15",o);
                        sum15 += o;

                        Integer p1 = tCheckItemMapper.zhengFu88ChartDataNews("危化管理",flag,(String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer p2 = tCheckItemMapper.zhengFuChartData88("危化管理",flag,(String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer p = p1 + p2;
                        list1.get(i).put("danger16",p);
                        sum16 += p;

                        Integer q1 = tCheckItemMapper.zhengFu88ChartDataNews("安全设施",flag,(String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer q2 = tCheckItemMapper.zhengFuChartData88("安全设施",flag,(String) list1.get(i).get("user_id")); // 职业卫生 隐患数据
                        Integer q = q1 + q2;
                        list1.get(i).put("danger17",q);
                        sum17 += q;

                        Integer r1 = tCheckItemMapper.zhengFu88ChartDataNews("其他",flag,(String) list1.get(i).get("user_id")); // 其他 隐患数据
                        Integer r2 = tCheckItemMapper.zhengFuChartData88("其他",flag,(String) list1.get(i).get("user_id")); // 其他 隐患数据
                        Integer r = r1 + r2;
                        list1.get(i).put("danger18",r);
                        sum18 += r;

                        Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                        list1.get(i).put("danger20",count);  // 某个车间的所有种类隐患的合计

                    }
                }
            }

            Integer counts = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 + sum11 + sum12 + sum13 + sum14 + sum15 + sum16 + sum17 + sum18 + sum19;

            DecimalFormat df = new DecimalFormat("0.00");

            findCounts(map, sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, sum11, sum12, sum13, sum14, sum15, sum16, sum17, sum18, sum19, counts, df);
            // 合计 坚
            map.put("sum1",sum1);
            map.put("sum2",sum2);
            map.put("sum3",sum3);
            map.put("sum4",sum4);
            map.put("sum5",sum5);
            map.put("sum6",sum6);
            map.put("sum7",sum7);
            map.put("sum8",sum8);
            map.put("sum9",sum9);
            map.put("sum10",sum10);
            map.put("sum11",sum11);
            map.put("sum12",sum12);
            map.put("sum13",sum13);
            map.put("sum14",sum14);
            map.put("sum15",sum15);
            map.put("sum16",sum16);
            map.put("sum17",sum17);
            map.put("sum18",sum18);
            map.put("sum19",sum19);

            map.put("counts",counts);

            model.addAttribute("data",new Date());

            if (user.getUserType() == 4 || user.getUserType() == 10){
                list.add(map);
                model.addAttribute("list",list);
                request.getSession().setAttribute("list44",list);
                request.getSession().setAttribute("flag44",flag);
                request.getSession().setAttribute("userId44",user.getId());
                return "global/company/evaluate/villageDown/zf-hidden-trouble-charts3-2";
            }else {
                list1.add(map);
                model.addAttribute("list",list1);
                request.getSession().setAttribute("list44",list1);
                request.getSession().setAttribute("flag44",flag);
                request.getSession().setAttribute("userId44",user.getId());
                return "global/company/evaluate/villageUp/zf-hidden-trouble-charts3-2";
            }

        }else {

            if (user.getUserType() == 4 || user.getUserType() == 10){
                model.addAttribute("list",list44);
                model.addAttribute("data",new Date());
                request.getSession().setAttribute("userId44",user.getId());
                return "global/company/evaluate/villageDown/zf-hidden-trouble-charts3-2";
            }else {
                model.addAttribute("list",list44);
                model.addAttribute("data",new Date());
                request.getSession().setAttribute("userId44",user.getId());
                return "global/company/evaluate/villageUp/zf-hidden-trouble-charts3-2";
            }
        }



    }

    private void findCounts(Map<String, Object> map, Integer sum1, Integer sum2, Integer sum3, Integer sum4, Integer sum5, Integer sum6, Integer sum7, Integer sum8, Integer sum9, Integer sum10, Integer sum11, Integer sum12, Integer sum13, Integer sum14, Integer sum15, Integer sum16, Integer sum17, Integer sum18, Integer sum19, Integer counts, DecimalFormat df) {
        if (null != counts && 0 != counts) {

            if (null != sum19 && 0 != sum19) {
                String str = df.format((float)sum19 / counts * 100);
                map.put("result19",str + "%");

            } else {
                map.put("result19","0.00%");  // 基础管理 占比数据 竖
            }

            if (null != sum1 && 0 != sum1) {
                String str = df.format((float)sum1 / counts * 100);
                map.put("result1",str + "%");

            } else {
                map.put("result1","0.00%");  // 基础管理 占比数据 竖
            }

            if (null != sum2 && 0 != sum2) {
                String str = df.format((float)sum2 / counts * 100);
                map.put("result2",str + "%");

            } else {
                map.put("result2","0.00%"); // 生产工艺 占比数据 竖
            }

            if (null != sum3 && 0 != sum3) {
                String str = df.format((float)sum3 / counts * 100);
                map.put("result3",str + "%");

            } else {
                map.put("result3","0.00%");  // 设备设施 占比数据 竖
            }

            if (null != sum4 && 0 != sum4) {
                String str = df.format((float)sum4 / counts * 100);
                map.put("result4",str + "%");

            } else {
                map.put("result4","0.00%"); // 公用工程 占比数据 竖
            }

            if (null != sum5 && 0 != sum5) {
                String str = df.format((float)sum5 / counts * 100);
                map.put("result5",str + "%");

            } else {
                map.put("result5","0.00%"); // 特种设备 占比数据 竖
            }

            if (null != sum6 && 0 != sum6) {
                String str = df.format((float)sum6 / counts * 100);
                map.put("result6",str + "%");

            } else {
                map.put("result6","0.00%"); // 生产现场 占比数据 竖
            }

            if (null != sum7 && 0 != sum7) {
                String str = df.format((float)sum7 / counts * 100);
                map.put("result7",str + "%");

            } else {
                map.put("result7","0.00%"); // 行为环境 占比数据 竖
            }

            if (null != sum8 && 0 != sum8) {
                String str = df.format((float)sum8 / counts * 100);
                map.put("result8",str + "%");

            } else {
                map.put("result8","0.00%");// 危化管理 占比数据 竖
            }

            if (null != sum9 && 0 != sum9) {
                String str = df.format((float)sum9 / counts * 100);
                map.put("result9",str + "%");

            } else {
                map.put("result9","0.00%"); // 消防安全 占比数据 竖
            }

            if (null != sum10 && 0 != sum10) {
                String str = df.format((float)sum10 / counts * 100);
                map.put("result10",str + "%");

            } else {
                map.put("result10","0.00%"); // 用电安全 占比数据 竖
            }

            if (null != sum11 && 0 != sum11) {
                String str = df.format((float)sum11 / counts * 100);
                map.put("result11",str + "%");

            } else {
                map.put("result11","0.00%"); // 安全设施 占比数据 竖
            }

            if (null != sum12 && 0 != sum12) {
                String str = df.format((float)sum12 / counts * 100);
                map.put("result12",str + "%");

            } else {
                map.put("result12","0.00%"); // 防雷静电 占比数据 竖
            }

            if (null != sum13 && 0 != sum13) {
                String str = df.format((float)sum13 / counts * 100);
                map.put("result13",str + "%");

            } else {
                map.put("result13","0.00%"); // 职业卫生 占比数据 竖
            }

            if (null != sum14 && 0 != sum14) {
                String str = df.format((float)sum14 / counts * 100);
                map.put("result14",str + "%");

            } else {
                map.put("result14","0.00%"); // 专项行业 占比数据 竖
            }

            if (null != sum15 && 0 != sum15) {
                String str = df.format((float)sum15 / counts * 100);
                map.put("result15",str + "%");

            } else {
                map.put("result15","0.00%"); // 其他 占比数据 竖
            }

            if (null != sum16 && 0 != sum16) {
                String str = df.format((float)sum16 / counts * 100);
                map.put("result16",str + "%");

            } else {
                map.put("result16","0.00%"); // 其他 占比数据 竖
            }

            if (null != sum17 && 0 != sum17) {
                String str = df.format((float)sum17 / counts * 100);
                map.put("result17",str + "%");

            } else {
                map.put("result17","0.00%"); // 其他 占比数据 竖
            }

            if (null != sum18 && 0 != sum18) {
                String str = df.format((float)sum18 / counts * 100);
                map.put("result18",str + "%");

            } else {
                map.put("result18","0.00%"); // 其他 占比数据 竖
            }

        }else {
            // 占比 坚
            map.put("result1", "0.00%");
            map.put("result2", "0.00%");
            map.put("result3", "0.00%");
            map.put("result4", "0.00%");
            map.put("result5", "0.00%");
            map.put("result6", "0.00%");
            map.put("result7", "0.00%");
            map.put("result8", "0.00%");
            map.put("result9", "0.00%");
            map.put("result10", "0.00%");
            map.put("result11", "0.00%");
            map.put("result12", "0.00%");
            map.put("result13", "0.00%");
            map.put("result14", "0.00%");
            map.put("result15", "0.00%");
            map.put("result16", "0.00%");
            map.put("result17", "0.00%");
            map.put("result18", "0.00%");
            map.put("result19", "0.00%");
        }
    }


    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析 行业
     * create time: 2019/8/26 14:18
     */
    @RequestMapping(value = "zf-hidden-industry")
    public String hiddenIndustry(HttpServletRequest request, Model model, Integer flag){

        User user = getLoginUser(request);
        request.getSession().setMaxInactiveInterval(60*60*24);
        if (null == flag){
            flag = 1;
        }

        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = null;

        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;

        List<Map<String,Object>> list33 = (List<Map<String,Object>>) request.getSession().getAttribute("list33");
        Integer userId = (Integer) request.getSession().getAttribute("userId33");
        Integer flag33 = (Integer) request.getSession().getAttribute("flag33");
        if (null == flag33 || flag33 != flag){
            list33 = null;
        }

        if (null == userId || userId != user.getId()){
            list33 = null;
        }

        if (null == list33 || list33.size() == 0){
            if (user.getUserType() == 4 || user.getUserType() == 10){

                if (user.getUserType() == 4){ // 村级账户
                    list = companyMapper.selectListsCompanyIdVillage(user.getId());
                }else if (user.getUserType() == 10){ // 惠山化工账号
                    list = companyMapper.selectListsTidCompanyId(user.getId());
                }

                for (int i = 0; i < list.size(); i++) {

                    Integer a = tCheckItemMapper.zhengFuChartDataDanger22(flag,"化工",(Integer) list.get(i).get("user_id")); // 化工行业 数据
                    list.get(i).put("danger1",a);
                    sum1 += a;

                    Integer  b = tCheckItemMapper.zhengFuChartDataDanger22(flag,"冶金",(Integer) list.get(i).get("user_id")); // 冶金行业 数据
                    list.get(i).put("danger2",b);
                    sum2 += b;

                    Integer  c = tCheckItemMapper.zhengFuChartDataDanger22(flag,"有色",(Integer) list.get(i).get("user_id")); // 有色行业 数据
                    list.get(i).put("danger3",c);
                    sum3 += c;

                    Integer  d = tCheckItemMapper.zhengFuChartDataDanger22(flag,"建材",(Integer) list.get(i).get("user_id")); // 建材行业 数据
                    list.get(i).put("danger4",d);
                    sum4 += d;

                    Integer  e = tCheckItemMapper.zhengFuChartDataDanger22(flag,"机械",(Integer) list.get(i).get("user_id")); // 机械行业 数据
                    list.get(i).put("danger5",e);
                    sum5 += e;

                    Integer  f = tCheckItemMapper.zhengFuChartDataDanger22(flag,"轻工",(Integer) list.get(i).get("user_id")); // 轻工行业 数据
                    list.get(i).put("danger6",f);
                    sum6 += f;

                    Integer  g = tCheckItemMapper.zhengFuChartDataDanger22(flag,"纺织",(Integer) list.get(i).get("user_id")); // 纺织行业 数据
                    list.get(i).put("danger7",g);
                    sum7 += g;

                    Integer  h = tCheckItemMapper.zhengFuChartDataDanger22(flag,"商贸",(Integer) list.get(i).get("user_id")); // 商贸行业 数据
                    list.get(i).put("danger8",h);
                    sum8 += h;

                    Integer  i1 = tCheckItemMapper.zhengFuChartDataDanger22(flag,"烟花",(Integer) list.get(i).get("user_id")); // 烟花行业 数据
                    list.get(i).put("danger9",i1);
                    sum9 += i1;

                    Integer  j = tCheckItemMapper.zhengFuChartDataDanger22(flag,"其他",(Integer) list.get(i).get("user_id")); // 其他行业 数据
                    list.get(i).put("danger10",j);
                    sum10 += j;

                    Integer count = a + b + c + d + e + f + g + h + i1 + j;

                    list.get(i).put("danger11",count);  // 某个公司的所有行业合计

                }
            }else {

                list1 = new ArrayList<Map<String, Object>>();

                if (findAllIds(user, list1)) return "global/evaluate/globalError";

                if (null == list1 && list1.size() == 0){// 如果该区域下的下属区域没有公司

                    return "global/evaluate/globalError";// 返回提示页面

                }else if (null != list1 && list1.size() != 0){

                    for (int i = 0; i < list1.size(); i++) {

                        Integer a = tCheckItemMapper.zhengFuChartDataDanger(flag,"化工",(String) list1.get(i).get("user_id")); // 化工行业 数据
                        list1.get(i).put("danger1",a);
                        sum1 += a;

                        Integer  b = tCheckItemMapper.zhengFuChartDataDanger(flag,"冶金",(String) list1.get(i).get("user_id")); // 冶金行业 数据
                        list1.get(i).put("danger2",b);
                        sum2 += b;

                        Integer  c = tCheckItemMapper.zhengFuChartDataDanger(flag,"有色",(String) list1.get(i).get("user_id")); // 有色行业 数据
                        list1.get(i).put("danger3",c);
                        sum3 += c;

                        Integer  d = tCheckItemMapper.zhengFuChartDataDanger(flag,"建材",(String) list1.get(i).get("user_id")); // 建材行业 数据
                        list1.get(i).put("danger4",d);
                        sum4 += d;

                        Integer  e = tCheckItemMapper.zhengFuChartDataDanger(flag,"机械",(String) list1.get(i).get("user_id")); // 机械行业 数据
                        list1.get(i).put("danger5",e);
                        sum5 += e;

                        Integer  f = tCheckItemMapper.zhengFuChartDataDanger(flag,"轻工",(String) list1.get(i).get("user_id")); // 轻工行业 数据
                        list1.get(i).put("danger6",f);
                        sum6 += f;

                        Integer  g = tCheckItemMapper.zhengFuChartDataDanger(flag,"纺织",(String) list1.get(i).get("user_id")); // 纺织行业 数据
                        list1.get(i).put("danger7",g);
                        sum7 += g;

                        Integer  h = tCheckItemMapper.zhengFuChartDataDanger(flag,"商贸",(String) list1.get(i).get("user_id")); // 商贸行业 数据
                        list1.get(i).put("danger8",h);
                        sum8 += h;

                        Integer  i1 = tCheckItemMapper.zhengFuChartDataDanger(flag,"烟花",(String) list1.get(i).get("user_id")); // 烟花行业 数据
                        list1.get(i).put("danger9",i1);
                        sum9 += i1;

                        Integer  j = tCheckItemMapper.zhengFuChartDataDanger(flag,"其他",(String) list1.get(i).get("user_id")); // 其他行业 数据
                        list1.get(i).put("danger10",j);
                        sum10 += j;

                        Integer count = a + b + c + d + e + f + g + h + i1 + j;

                        list1.get(i).put("danger11",count);  // 某个公司的所有行业合计

                    }
                }
            }

            Map<String,Object> map = new HashMap<>();

            Integer counts = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 ;

            DecimalFormat df = new DecimalFormat("0.00");

            findCounts1(sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, map, counts, df);

            // 合计 坚
            map.put("sum1",sum1);
            map.put("sum2",sum2);
            map.put("sum3",sum3);
            map.put("sum4",sum4);
            map.put("sum5",sum5);
            map.put("sum6",sum6);
            map.put("sum7",sum7);
            map.put("sum8",sum8);
            map.put("sum9",sum9);
            map.put("sum10",sum10);
            map.put("counts",counts);

            model.addAttribute("data",new Date());

            if (user.getUserType() == 4 || user.getUserType() == 10){
                list.add(map);
                model.addAttribute("list",list);
                request.getSession().setAttribute("list33",list);
                request.getSession().setAttribute("flag33",flag);
                request.getSession().setAttribute("userId33",user.getId());
                return "global/company/evaluate/villageDown/zf-hidden-industry-charts3-1";
            }else {
                list1.add(map);
                model.addAttribute("list",list1);
                request.getSession().setAttribute("list33",list1);
                request.getSession().setAttribute("flag33",flag);
                request.getSession().setAttribute("userId33",user.getId());
                return "global/company/evaluate/villageUp/zf-hidden-industry-charts3-1";
            }

        }else {

            if (user.getUserType() == 4 || user.getUserType() == 10){
                model.addAttribute("list",list33);
                model.addAttribute("data",new Date());
                request.getSession().setAttribute("flag33",flag);
                request.getSession().setAttribute("userId33",user.getId());
                return "global/company/evaluate/villageDown/zf-hidden-industry-charts3-1";
            }else {
                model.addAttribute("list",list33);
                model.addAttribute("data",new Date());
                request.getSession().setAttribute("flag33",flag);
                request.getSession().setAttribute("userId33",user.getId());
                return "global/company/evaluate/villageUp/zf-hidden-industry-charts3-1";
            }
        }


    }


    /**
     * create by  : 小明！！！
     * description: TODO    隐患治理数据分析  风险
     * create time: 2019/8/26 15:10
     */
    @RequestMapping(value = "manage-hidden-company")
    public String manegeHiddenConpany(HttpServletRequest request, Model model, Integer flag){

        User user = getLoginUser(request);
        request.getSession().setMaxInactiveInterval(60*60*24);
        if (null == flag){
            flag = 1;
        }

        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = null;
        Map<String,Object> map = new HashMap<>();

        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;

        // 已治理合计
        Integer sign1 = 0;
        Integer sign2 = 0;
        Integer sign3 = 0;
        // 未治理合计
        Integer sign11 = 0;
        Integer sign22 = 0;
        Integer sign33 = 0;

        List<Map<String,Object>> list55 = (List<Map<String,Object>>) request.getSession().getAttribute("list55");
        Integer userId = (Integer) request.getSession().getAttribute("userId55");
        Integer flag55 = (Integer) request.getSession().getAttribute("flag55");

        if (null == flag55 || flag55 != flag){
            list55 = null;
        }

        if (null == userId || userId != user.getId()){
            list55 = null;
        }

        if (null == list55 || list55.size() == 0){

            if (user.getUserType() == 4 || user.getUserType() == 10){

                if (user.getUserType() == 4){ // 村级账户
                    list = companyMapper.selectListsCompanyIdVillage(user.getId());
                }else if (user.getUserType() == 10){ // 惠山化工账号
                    list = companyMapper.selectListsTidCompanyId(user.getId());
                }

                for (int i = 0; i < list.size(); i++) {

                    Integer flags1 = tCheckItemMapper.manageHiddenCompanyNews(3,(Integer)list.get(i).get("user_id"),3); // 一般和较小 合格 已治理
                    Integer flags2 = tCheckItemMapper.manageHiddenCompanyBasics(3,(Integer)list.get(i).get("user_id"),3); // 一般和较小 合格 已治理
                    Integer flags3 = tCheckItemMapper.manageHiddenCompany11(3,(Integer)list.get(i).get("user_id"),3); // 一般和较小 合格 已治理
                    Integer a = flags1 + flags2 + flags3;
                    list.get(i).put("danger1",a);

                    Integer flags1a1 = tCheckItemMapper.manageHiddenCompanyNews(2,(Integer)list.get(i).get("user_id"),3); // 一般和较小 不合格 未治理
                    Integer flags1a2 = tCheckItemMapper.manageHiddenCompanyBasics(2,(Integer)list.get(i).get("user_id"),3); // 一般和较小 不合格 未治理
                    Integer flags1a3 = tCheckItemMapper.manageHiddenCompany11(2,(Integer)list.get(i).get("user_id"),3); // 一般和较小 不合格 未治理
                    Integer a1 = flags1a1 + flags1a2 + flags1a3;
                    list.get(i).put("danger11",a1);

                    count1 = a + a1;

                    Integer flabg1 = tCheckItemMapper.manageHiddenCompanyNews(3,(Integer)list.get(i).get("user_id"),1); // 较大 合格 已治理
                    Integer flabg2 = tCheckItemMapper.manageHiddenCompanyBasics(3,(Integer)list.get(i).get("user_id"),1); // 较大 合格 已治理
                    Integer flabg3 = tCheckItemMapper.manageHiddenCompany11(3,(Integer)list.get(i).get("user_id"),1); // 较大 合格 已治理
                    Integer b = flabg1 + flabg2 + flabg3;
                    list.get(i).put("danger2",b);

                    Integer flab1 = tCheckItemMapper.manageHiddenCompanyNews(2,(Integer)list.get(i).get("user_id"),1); // 较大 不合格 未治理
                    Integer flab2 = tCheckItemMapper.manageHiddenCompanyBasics(2,(Integer)list.get(i).get("user_id"),1); // 较大 不合格 未治理
                    Integer flab3 = tCheckItemMapper.manageHiddenCompany11(2,(Integer)list.get(i).get("user_id"),1); // 较大 不合格 未治理
                    Integer b1 = flab1 + flab2 + flab3;
                    list.get(i).put("danger22",b1);

                    count2  = b + b1;

                    Integer flcg1 = tCheckItemMapper.manageHiddenCompanyNews(3,(Integer)list.get(i).get("user_id"),2); // 重大 合格 已治理
                    Integer flcg2 = tCheckItemMapper.manageHiddenCompanyBasics(3,(Integer)list.get(i).get("user_id"),2); // 重大 合格 已治理
                    Integer flcg3 = tCheckItemMapper.manageHiddenCompany11(3,(Integer)list.get(i).get("user_id"),2); // 重大 合格 已治理
                    Integer c = flcg1 + flcg2 + flcg3;
                    list.get(i).put("danger3",c);

                    Integer flc1 = tCheckItemMapper.manageHiddenCompanyNews(2,(Integer)list.get(i).get("user_id"),2); // 重大 不合格 未治理
                    Integer flc2 = tCheckItemMapper.manageHiddenCompanyBasics(2,(Integer)list.get(i).get("user_id"),2); // 重大 不合格 未治理
                    Integer flc3 = tCheckItemMapper.manageHiddenCompany11(2,(Integer)list.get(i).get("user_id"),2); // 重大 不合格 未治理
                    Integer c1 = flc1 + flc2 + flc3;
                    list.get(i).put("danger33",c1);

                    count3  = c + c1;

                    sign1 += a;
                    sign2 += b;
                    sign3 += c;

                    sign11 += a1;
                    sign22 += b1;
                    sign33 += c1;

                    DecimalFormat df = new DecimalFormat("0.00");

                    Integer sum = count1 + count2 + count3;

                    if (null != count1 && count1 != 0){  // 一般和较小 治理率
                        String str = df.format((float)a / count1 * 100);
                        list.get(i).put("result11",str+"%");
                    }else {
                        list.get(i).put("result11","0.00%");
                    }

                    if (null != count2 && count2 != 0){ // 较大 治理率
                        String str = df.format((float)b / count2 * 100);
                        list.get(i).put("result22",str+"%");
                    }else {
                        list.get(i).put("result22","0.00%");
                    }

                    if (null != count3 && count3 != 0){ // 重大 治理率
                        String str = df.format((float)c / count3 * 100);
                        list.get(i).put("result33",str+"%");
                    }else {
                        list.get(i).put("result33","0.00%");
                    }

                }

            }else {

                list1 = new ArrayList<Map<String, Object>>();

                if (findAllIds(user, list1)) return "global/evaluate/globalError";// 返回提示页面

                if (null == list1 && list1.size() == 0){// 如果该区域下的下属区域没有公司

                    return "global/evaluate/globalError";// 返回提示页面

                }else if (null != list1 && list1.size() != 0){

                    for (int i = 0; i < list1.size(); i++) {

                        Integer numbera1 = tCheckItemMapper.manageNewsHiddenCompany(1,(String)list1.get(i).get("user_id"),3); // 一般和较小 合格 已治理
                        Integer numbera2 = tCheckItemMapper.manageBasicsHiddenCompany(1,(String)list1.get(i).get("user_id"),3); // 一般和较小 合格 已治理
                        Integer numbera3 = tCheckItemMapper.manageHiddenCompany(1,(String)list1.get(i).get("user_id"),3); // 一般和较小 合格 已治理
                        Integer a = numbera1 + numbera2 + numbera3;
                        list1.get(i).put("danger1",a);

                        Integer numbera11 = tCheckItemMapper.manageNewsHiddenCompany(2,(String)list1.get(i).get("user_id"),3); // 一般和较小 不合格 未治理
                        Integer numbera12 = tCheckItemMapper.manageBasicsHiddenCompany(2,(String)list1.get(i).get("user_id"),3); // 一般和较小 不合格 未治理
                        Integer numbera13 = tCheckItemMapper.manageHiddenCompany(2,(String)list1.get(i).get("user_id"),3); // 一般和较小 不合格 未治理
                        Integer a1 = numbera11 + numbera12 + numbera13;
                        list1.get(i).put("danger11",a1);

                        count1 = a + a1;

                        Integer numberb1 = tCheckItemMapper.manageNewsHiddenCompany(1,(String)list1.get(i).get("user_id"),1); // 较大 合格 已治理
                        Integer numberb2 = tCheckItemMapper.manageBasicsHiddenCompany(1,(String)list1.get(i).get("user_id"),1); // 较大 合格 已治理
                        Integer numberb3 = tCheckItemMapper.manageHiddenCompany(1,(String)list1.get(i).get("user_id"),1); // 较大 合格 已治理
                        Integer b = numberb1 + numberb2 + numberb3;
                        list1.get(i).put("danger2",b);

                        Integer numberb11 = tCheckItemMapper.manageNewsHiddenCompany(2,(String)list1.get(i).get("user_id"),1); // 较大 不合格 未治理
                        Integer numberb12 = tCheckItemMapper.manageBasicsHiddenCompany(2,(String)list1.get(i).get("user_id"),1); // 较大 不合格 未治理
                        Integer numberb13 = tCheckItemMapper.manageHiddenCompany(2,(String)list1.get(i).get("user_id"),1); // 较大 不合格 未治理
                        Integer b1 = numberb11 + numberb12 + numberb13;
                        list1.get(i).put("danger22",b1);

                        count2  = b + b1;

                        Integer numberc1 = tCheckItemMapper.manageNewsHiddenCompany(1,(String)list1.get(i).get("user_id"),2); // 重大 合格 已治理
                        Integer numberc2 = tCheckItemMapper.manageBasicsHiddenCompany(1,(String)list1.get(i).get("user_id"),2); // 重大 合格 已治理
                        Integer numberc3 = tCheckItemMapper.manageHiddenCompany(1,(String)list1.get(i).get("user_id"),2); // 重大 合格 已治理
                        Integer c = numberc1 + numberc2 + numberc3;
                        list1.get(i).put("danger3",c);

                        Integer  numberc11 = tCheckItemMapper.manageNewsHiddenCompany(2,(String)list1.get(i).get("user_id"),2); // 重大 不合格 未治理
                        Integer  numberc12 = tCheckItemMapper.manageBasicsHiddenCompany(2,(String)list1.get(i).get("user_id"),2); // 重大 不合格 未治理
                        Integer  numberc13 = tCheckItemMapper.manageHiddenCompany(2,(String)list1.get(i).get("user_id"),2); // 重大 不合格 未治理
                        Integer c1 = numberc11 + numberc12 + numberc13;
                        list1.get(i).put("danger33",c1);

                        count3  = c + c1;

                        sign1 += a;
                        sign2 += b;
                        sign3 += c;

                        sign11 += a1;
                        sign22 += b1;
                        sign33 += c1;

                        DecimalFormat df = new DecimalFormat("0.00");

                        Integer sum = count1 + count2 + count3;

                        if (null != count1 && count1 != 0){  // 一般和较小 治理率
                            String str = df.format((float)a / count1 * 100);
                            list1.get(i).put("result11",str+"%");
                        }else {
                            list1.get(i).put("result11","0.00%");
                        }

                        if (null != count2 && count2 != 0){ // 较大 治理率
                            String str = df.format((float)b / count2 * 100);
                            list1.get(i).put("result22",str+"%");
                        }else {
                            list1.get(i).put("result22","0.00%");
                        }

                        if (null != count3 && count3 != 0){ // 重大 治理率
                            String str = df.format((float)c / count3 * 100);
                            list1.get(i).put("result33",str+"%");
                        }else {
                            list1.get(i).put("result33","0.00%");
                        }
                    }
                }
            }

            DecimalFormat df = new DecimalFormat("0.00");

            Integer sum1 = sign1 + sign11;
            Integer sum2 = sign2 + sign22;
            Integer sum3 = sign3 + sign33;

            findCounts5(map, sign1, sign2, sign3, df, sum1, sum2, sum3);

            map.put("sign1",sign1); // 一般隐患 已治理 合计 竖
            map.put("sign2",sign2); // 较大隐患 已治理 合计 竖
            map.put("sign3",sign3); // 重大隐患 已治理 合计 竖
            map.put("sign11",sign11); // 一般隐患 未治理 合计 竖
            map.put("sign22",sign22); // 较大隐患 未治理 合计 竖
            map.put("sign33",sign33); // 重大隐患 未治理 合计 竖

            model.addAttribute("data",new Date());

            if (user.getUserType() == 4 || user.getUserType() == 10){
                list.add(map);
                model.addAttribute("list",list);
                request.getSession().setAttribute("list55",list);
                request.getSession().setAttribute("flag55",flag);
                request.getSession().setAttribute("userId55",user.getId());
                return "global/company/evaluate/villageDown/manage-hidden-company-charts4-1";
            }else {
                list1.add(map);
                model.addAttribute("list",list1);
                request.getSession().setAttribute("list55",list1);
                request.getSession().setAttribute("flag55",flag);
                request.getSession().setAttribute("userId55",user.getId());
                return "global/company/evaluate/villageUp/manage-hidden-company-charts4-1";
            }

        }else {

            if (user.getUserType() == 4 || user.getUserType() == 10){
                model.addAttribute("list",list55);
                model.addAttribute("data",new Date());
                request.getSession().setAttribute("flag55",flag);
                request.getSession().setAttribute("userId55",user.getId());
                return "global/company/evaluate/villageDown/manage-hidden-company-charts4-1";
            }else {
                model.addAttribute("list",list55);
                model.addAttribute("data",new Date());
                request.getSession().setAttribute("flag55",flag);
                request.getSession().setAttribute("userId55",user.getId());
                return "global/company/evaluate/villageUp/manage-hidden-company-charts4-1";
            }
        }
    }

    private void findCounts5(Map<String, Object> map, Integer sign1, Integer sign2, Integer sign3, DecimalFormat df, Integer sum1, Integer sum2, Integer sum3) {
        if (null != sum1 && 0 != sum1){

            if (null != sign1 && 0 != sign1){
                String str = df.format((float)sign1 / sum1 * 100);
                map.put("result1",str + "%"); // 一般隐患的治理率 竖

            }else {
                map.put("result1","0.00%"); // 一般隐患的治理率 竖
            }
        }else {
            map.put("result1","0.00%"); // 一般隐患的治理率 竖
        }

        if (null != sum2 && 0 != sum2){

            if (null != sign2 && 0 != sign2){
                String str = df.format((float)sign2 / sum2 * 100);
                map.put("result2",str + "%"); // 较大隐患的治理率 竖

            }else {
                map.put("result2","0.00%"); // 较大隐患的治理率 竖
            }

        }else {
            map.put("result2","0.00%"); // 较大隐患的治理率 竖
        }

        if (null != sum3 && 0 != sum3){

            if (null != sign3 && 0 != sign3){
                String str = df.format((float)sign3/sum3 * 100);
                map.put("result3",str + "%"); // 重大隐患的治理率 竖

            }else {
                map.put("result3","0.00%"); // 重大隐患的治理率 竖
            }

        }else {
            map.put("result3","0.00%"); // 重大隐患的治理率 竖
        }
    }

    private boolean findAllIds(User user, List<Map<String, Object>> list1) {
        if (user.getUserType() == 3){// 如果登录的是镇级账号

            // 查询镇下面的所有村
            List<Village> lists = villageMapper.findUserID(user.getId());
            Map<String,Object> map1 = null;
            if (null == lists || lists.size() == 0){// 如果该区域下没有下级区域

                return true;

            }else if (null != lists && lists.size() != 0) {

                for (int i = 0; i < lists.size(); i++) {
                    // 根据区域 ID 查询该区域下所有的企业 ID
                    List<Integer> ids = companyMapper.selectTownCompanyId(user.getId());
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < ids.size(); j++) {
                        if (j == ids.size()-1){
                            sb.append("'").append(ids.get(j)).append("'");
                        }else{
                            sb.append("'").append(ids.get(j)).append("',");
                        }
                    }

                    map1 = new HashMap<>();

                    map1.put("name",lists.get(i).getName());

                    map1.put("user_id",sb.toString());

                    list1.add(map1);

                }
            }

        }else if (user.getUserType() == 6){// 如果登录的是区级账号

            // 查询区下面的所有镇
            List<Town> lists = townMapper.findAll(user.getId());
            Map<String,Object> map1 = null;
            if (null == lists || lists.size() == 0){// 如果该区域下没有下级区域

                return true;

            }else if (null != lists || lists.size() != 0){

                for (int i = 0; i < lists.size(); i++) {
                    // 根据区域 ID 查询该区域下所有的企业 ID
                    List<Integer> ids = companyMapper.selectDistrictCompanyId(user.getId());
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < ids.size(); j++) {
                        if (j == ids.size()-1){
                            sb.append("'").append(ids.get(j)).append("'");
                        }else{
                            sb.append("'").append(ids.get(j)).append("',");
                        }
                    }

                    map1 = new HashMap<>();

                    map1.put("name",lists.get(i).getName());

                    map1.put("user_id",sb.toString());

                    list1.add(map1);

                }
            }
        }
        return false;
    }


    /**
     * create by  : 小明！！！
     * description: TODO    隐患治理数据分析 风险
     * create time: 2019/8/26 15:10
     */
    @RequestMapping(value = "manage-hidden-danger")
    public String manegeHiddenDanger(HttpServletRequest request, Model model, Integer flag){

        User user = getLoginUser(request);
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        String string = "基础管理,设计总图,试生产,装置运行,设备安全,仪表安全,电气安全,应急消防,特殊管控,行为环境,生产现场,公辅工程,特种设备,专项行业,生产工艺,设备设施,危化管理.安全设施,其他";
        DecimalFormat df = new DecimalFormat("0.00");
        List<Integer> ids = null;
        ids = basicFindAllIds(user, ids);

        StringBuilder sb = new StringBuilder();

        if (null == flag){
            flag = 1;
        }

        for (int i = 0; i < ids.size() ; i++) {

            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }

        String[] strs = string.split(",");

        Map<String,Object> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            Map<String,Object> map1 = new HashMap<>();
            map1.put("danger",strs[i]);

            list.add(map1);
        }
        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;
        // 已治理合计
        Integer sign1 = 0;
        Integer sign2 = 0;
        Integer sign3 = 0;
        // 未治理合计
        Integer sign11 = 0;
        Integer sign22 = 0;
        Integer sign33 = 0;

        request.getSession().setMaxInactiveInterval(60*60*24);
        List<Map<String,Object>> list66 = (List<Map<String,Object>>) request.getSession().getAttribute("list66");
        Integer userId = (Integer) request.getSession().getAttribute("userId66");

        if (null == userId || userId != user.getId()){
            list66 = null;
        }

        if (null == list66 || list66.size() == 0){

            if (null == list || list.size() == 0){

                return "global/evaluate/globalError";// 返回提示页面

            }else if (null != list && list.size() != 0){

                for (int i = 0; i < list.size(); i++) {

                    Integer suma1 = tCheckItemMapper.manageNewsHiddenDanger(3,(String)list.get(i).get("danger"),3,sb.toString()); // 一般和较小 合格 已治理
                    Integer suma2 = tCheckItemMapper.manageBasicsHiddenDanger(3,3,sb.toString()); // 一般和较小 合格 已治理
                    Integer suma3 = tCheckItemMapper.manageHiddenDanger(3,(String)list.get(i).get("danger"),3,sb.toString()); // 一般和较小 合格 已治理
                    Integer a = suma1 + suma2 + suma3;
                    list.get(i).put("danger1",a);

                    Integer suma11 = tCheckItemMapper.manageNewsHiddenDanger(2,(String)list.get(i).get("danger"),3,sb.toString()); // 一般和较小 不合格 未治理
                    Integer suma12 = tCheckItemMapper.manageBasicsHiddenDanger(2,3,sb.toString()); // 一般和较小 不合格 未治理
                    Integer suma13 = tCheckItemMapper.manageHiddenDanger(2,(String)list.get(i).get("danger"),3,sb.toString()); // 一般和较小 不合格 未治理
                    Integer a1 = suma11 + suma12 + suma13;
                    list.get(i).put("danger11",a1);

                    Integer sumb1 = tCheckItemMapper.manageNewsHiddenDanger(3,(String)list.get(i).get("danger"),1,sb.toString()); // 较大 合格 已治理
                    Integer sumb2 = tCheckItemMapper.manageBasicsHiddenDanger(3,1,sb.toString()); // 较大 合格 已治理
                    Integer sumb3 = tCheckItemMapper.manageHiddenDanger(3,(String)list.get(i).get("danger"),1,sb.toString()); // 较大 合格 已治理
                    Integer b = sumb1 + sumb2 + sumb3;
                    list.get(i).put("danger2",b);

                    Integer sumb11 = tCheckItemMapper.manageNewsHiddenDanger(2,(String)list.get(i).get("danger"),1,sb.toString()); // 较大 不合格 未治理
                    Integer sumb12 = tCheckItemMapper.manageBasicsHiddenDanger(2,1,sb.toString()); // 较大 不合格 未治理
                    Integer sumb13 = tCheckItemMapper.manageHiddenDanger(2,(String)list.get(i).get("danger"),1,sb.toString()); // 较大 不合格 未治理
                    Integer b1 = sumb11 + sumb12 + sumb13;
                    list.get(i).put("danger22",b1);

                    Integer sumc1 = tCheckItemMapper.manageNewsHiddenDanger(3,(String)list.get(i).get("danger"),2,sb.toString()); // 重大 合格 已治理
                    Integer sumc2 = tCheckItemMapper.manageBasicsHiddenDanger(3,2,sb.toString()); // 重大 合格 已治理
                    Integer sumc3 = tCheckItemMapper.manageHiddenDanger(3,(String)list.get(i).get("danger"),2,sb.toString()); // 重大 合格 已治理
                    Integer c = sumc1 + sumc2 + sumc3;
                    list.get(i).put("danger3",c);

                    Integer sumc11 = tCheckItemMapper.manageNewsHiddenDanger(2,(String)list.get(i).get("danger"),2,sb.toString()); // 重大 不合格 未治理
                    Integer sumc12 = tCheckItemMapper.manageBasicsHiddenDanger(2,2,sb.toString()); // 重大 不合格 未治理
                    Integer sumc13 = tCheckItemMapper.manageHiddenDanger(2,(String)list.get(i).get("danger"),2,sb.toString()); // 重大 不合格 未治理
                    Integer c1 = sumc11 + sumc12 + sumc13;
                    list.get(i).put("danger33",c1);

                    sign1 += a;
                    sign2 += b;
                    sign3 += c;

                    sign11 += a1;
                    sign22 += b1;
                    sign33 += c1;

                    count1 = a + a1;
                    count2  = b + b1;
                    count3  = c + c1;

                    Integer sum = count1 + count2 + count3; // 单个风险所包含的所有隐患

                    if (null != count1 && count1 != 0){  // 一般和较小 治理率
                        String str = df.format((float)a / count1 * 100);
                        list.get(i).put("result11",str + "%");
                    }else {
                        list.get(i).put("result11","0.00%");
                    }

                    if (null != count2 && count2 != 0){ // 较大 治理率
                        String str = df.format((float)b / count2 * 100);
                        list.get(i).put("result22",str + "%");
                    }else {
                        list.get(i).put("result22","0.00%");
                    }

                    if (null != count3 && count3 != 0){ // 重大 治理率
                        String str = df.format((float)c / count3 * 100);
                        list.get(i).put("result33",str + "%");
                    }else {
                        list.get(i).put("result33","0.00%");
                    }
                }
            }

            Integer sum1 = sign1 + sign11;  // 所有风险  一般隐患的总和

            Integer sum2 = sign2 + sign22;  // 所有风险  较大隐患的总和

            Integer sum3 = sign3 + sign33;  // 所有风险  重大隐患的总和

            if (null != sum1 && sum1 != 0){  // 一般和较小 治理率
                String str = df.format((float)sign1 / sum1 * 100);
                map.put("number111",str + "%");
            }else {
                map.put("number111","0.00%");
            }

            if (null != sum2 && sum2 != 0){ // 较大 治理率
                String str = df.format((float)sign2 / sum2 * 100);
                map.put("number222",str + "%");
            }else {
                map.put("number222","0.00%");
            }

            if (null != sum3 && sum3 != 0){ // 重大 治理率
                String str = df.format((float)sign3 / sum3 * 100);
                map.put("number333",str + "%");
            }else {
                map.put("number222","0.00%");
            }

            map.put("sign1",sign1);
            map.put("sign2",sign2);
            map.put("sign3",sign3);
            map.put("sign11",sign11);
            map.put("sign22",sign22);
            map.put("sign33",sign33);

            list.add(map);

            model.addAttribute("data",new Date());
            model.addAttribute("list",list);
            request.getSession().setAttribute("list66",list);
            request.getSession().setAttribute("userId66",user.getId());
            if (user.getUserType() == 4 || user.getUserType() == 10){
                return "global/company/evaluate/villageDown/manage-hidden-danger-charts4-2";
            }else {
                return "global/company/evaluate/villageUp/manage-hidden-danger-charts4-2";
            }

        }else {
            model.addAttribute("data",new Date());
            model.addAttribute("list",list66);
            request.getSession().setAttribute("userId66",user.getId());
            if (user.getUserType() == 4 || user.getUserType() == 10){
                return "global/company/evaluate/villageDown/manage-hidden-danger-charts4-2";
            }else {
                return "global/company/evaluate/villageUp/manage-hidden-danger-charts4-2";
            }
        }

    }


    /**
     * create by  : 小明！！！
     * description: TODO 隐患治理数据分析  行业
     * create time: 2019/8/27 9:04
     */
    @RequestMapping(value = "manage-hidden-industry")
    public String manegeHiddenIndustry(HttpServletRequest request, Model model){

        User user = getLoginUser(request);
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        DecimalFormat df = new DecimalFormat("0.00");
        List<Integer> ids = null;
        ids = basicFindAllIds(user, ids);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ids.size() ; i++) {

            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }
        System.out.println(sb.toString());
        String string = "化工,冶金,有色,建材,机械,轻工,纺织,商贸,烟花,其他";

        String[] strs = string.split(",");

        Map<String,Object> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            Map<String,Object> map1 = new HashMap<>();

            map1.put("industry",strs[i]);

            list.add(map1);
        }

        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;

        // 已治理合计
        Integer sign1 = 0;
        Integer sign2 = 0;
        Integer sign3 = 0;
        // 未治理合计
        Integer sign11 = 0;
        Integer sign22 = 0;
        Integer sign33 = 0;

        request.getSession().setMaxInactiveInterval(60*60*24);

        List<Map<String,Object>> list77 = (List<Map<String,Object>>) request.getSession().getAttribute("list77");
        Integer userId = (Integer) request.getSession().getAttribute("userId77");
        if (null == userId || userId != user.getId()){
            list77 = null;
        }

        if (null == list77 || list77.size() == 0){

            if (null == list || list.size() == 0){

                return "global/evaluate/globalError";// 返回提示页面

            }else if (null != list && list.size() != 0){

                for (int i = 0; i < list.size(); i++) {

                    Integer suma1 = tCheckItemMapper.manageHiddenIndustryNowS(3,(String)list.get(i).get("industry"),3,sb.toString()); // 一般和较小 合格 已治理
                    Integer suma2 = tCheckItemMapper.manageHiddenIndustryBasicS(3,(String)list.get(i).get("industry"),3,sb.toString()); // 一般和较小 合格 已治理
                    Integer suma3 = tCheckItemMapper.manageHiddenIndustry(3,(String)list.get(i).get("industry"),3,sb.toString()); // 一般和较小 合格 已治理
                    Integer a = suma1 + suma2 + suma3;
                    list.get(i).put("industry1",a);

                    Integer suma11 = tCheckItemMapper.manageHiddenIndustryNowS(2,(String)list.get(i).get("industry"),3,sb.toString()); // 一般和较小 不合格 未治理
                    Integer suma12 = tCheckItemMapper.manageHiddenIndustryBasicS(2,(String)list.get(i).get("industry"),3,sb.toString()); // 一般和较小 不合格 未治理
                    Integer suma13 = tCheckItemMapper.manageHiddenIndustry(2,(String)list.get(i).get("industry"),3,sb.toString()); // 一般和较小 不合格 未治理
                    Integer a1 = suma11 + suma12 + suma13;
                    list.get(i).put("industry11",a1);

                    Integer sumb1 = tCheckItemMapper.manageHiddenIndustryNowS(3,(String)list.get(i).get("industry"),1,sb.toString()); // 较大 合格 已治理
                    Integer sumb2 = tCheckItemMapper.manageHiddenIndustryBasicS(3,(String)list.get(i).get("industry"),1,sb.toString()); // 较大 合格 已治理
                    Integer sumb3 = tCheckItemMapper.manageHiddenIndustry(3,(String)list.get(i).get("industry"),1,sb.toString()); // 较大 合格 已治理
                    Integer b = sumb1 + sumb2 + sumb3;
                    list.get(i).put("industry2",b);

                    Integer sumb11 = tCheckItemMapper.manageHiddenIndustryNowS(2,(String)list.get(i).get("industry"),1,sb.toString()); // 较大 不合格 未治理
                    Integer sumb12 = tCheckItemMapper.manageHiddenIndustryBasicS(2,(String)list.get(i).get("industry"),1,sb.toString()); // 较大 不合格 未治理
                    Integer sumb13 = tCheckItemMapper.manageHiddenIndustry(2,(String)list.get(i).get("industry"),1,sb.toString()); // 较大 不合格 未治理
                    Integer b1 = sumb11 + sumb12 + sumb13;
                    list.get(i).put("industry22",b1);

                    Integer sumc1 = tCheckItemMapper.manageHiddenIndustryNowS(3,(String)list.get(i).get("industry"),2,sb.toString()); // 重大 合格 已治理
                    Integer sumc2 = tCheckItemMapper.manageHiddenIndustryBasicS(3,(String)list.get(i).get("industry"),2,sb.toString()); // 重大 合格 已治理
                    Integer sumc3 = tCheckItemMapper.manageHiddenIndustry(3,(String)list.get(i).get("industry"),2,sb.toString()); // 重大 合格 已治理
                    Integer c = sumc1 + sumc2 + sumc3;
                    list.get(i).put("industry3",c);

                    Integer sumc11 = tCheckItemMapper.manageHiddenIndustryNowS(2,(String)list.get(i).get("industry"),2,sb.toString()); // 重大 不合格 未治理
                    Integer sumc12 = tCheckItemMapper.manageHiddenIndustryBasicS(2,(String)list.get(i).get("industry"),2,sb.toString()); // 重大 不合格 未治理
                    Integer sumc13 = tCheckItemMapper.manageHiddenIndustry(2,(String)list.get(i).get("industry"),2,sb.toString()); // 重大 不合格 未治理
                    Integer c1 = sumc11 + sumc12 + sumc13;
                    list.get(i).put("industry33",c1);

                    count1 = a + a1;
                    count2  = b + b1;
                    count3  = c + c1;

                    sign1 += a;
                    sign2 += b;
                    sign3 += c;

                    sign11 += a1;
                    sign22 += b1;
                    sign33 += c1;

                    Integer sum = count1 + count2 + count3; // 单个风险所包含的所有隐患

                    if (null != count1 && count1 != 0){  // 一般和较小 治理率
                        String str = df.format((float)a / count1 * 100);
                        list.get(i).put("result11",str + "%");
                    }else {
                        list.get(i).put("result11","0.00%");
                    }

                    if (null != count2 && count2 != 0){ // 较大 治理率
                        String str = df.format((float)b / count2 * 100);
                        list.get(i).put("result22",str + "%");
                    }else {
                        list.get(i).put("result22","0.00%");
                    }

                    if (null != count3 && count3 != 0){ // 重大 治理率
                        String str = df.format((float)c / count3 * 100);
                        list.get(i).put("result33",str + "%");
                    }else {
                        list.get(i).put("result33","0.00%");
                    }
                }
            }

            Integer sum1 = sign1 + sign11;  // 所有风险  一般隐患的总和

            Integer sum2 = sign2 + sign22;  // 所有风险  较大隐患的总和

            Integer sum3 = sign3 + sign33;  // 所有风险  重大隐患的总和

            if (null != sum1 && sum1 != 0){  // 一般和较小 治理率
                String str = df.format((float)sign1 / sum1 * 100);
                map.put("number111",str + "%");
            }else {
                map.put("number111","0.00%");
            }

            if (null != sum2 && sum2 != 0){ // 较大 治理率
                String str = df.format((float)sign2 / sum2 * 100);
                map.put("number222",str + "%");
            }else {
                map.put("number222","0.00%");
            }

            if (null != sum3 && sum3 != 0){ // 重大 治理率
                String str = df.format((float)sign3 / sum3 * 100);
                map.put("number333",str + "%");
            }else {
                map.put("number333","0.00%");
            }
            map.put("sign1",sign1);
            map.put("sign2",sign2);
            map.put("sign3",sign3);
            map.put("sign11",sign11);
            map.put("sign22",sign22);
            map.put("sign33",sign33);
            list.add(map);
            model.addAttribute("data",new Date());
            model.addAttribute("list",list);
            request.getSession().setAttribute("list77",list);
            request.getSession().setAttribute("userId77",user.getId());
            if (user.getUserType() == 4 || user.getUserType() == 10){
                return "global/company/evaluate/villageDown/manage-hidden-industry-charts4-3";
            }else {
                return "global/company/evaluate/villageUp/manage-hidden-industry-charts4-3";
            }
        }else {
            model.addAttribute("list",list77);
            model.addAttribute("data",new Date());
            request.getSession().setAttribute("userId77",user.getId());
            if (user.getUserType() == 4 || user.getUserType() == 10){
                return "global/company/evaluate/villageDown/manage-hidden-industry-charts4-3";
            }else {
                return "global/company/evaluate/villageUp/manage-hidden-industry-charts4-3";
            }
        }
    }


    /**
     * create by  : 小明！！！
     * description: TODO    清空缓存
     * create time: 2019/9/30 17:54
     */
    @RequestMapping(value = "/clear/session")
    public @ResponseBody Result checkDel(HttpServletRequest request, String sessionFlag) throws Exception {
        Result result = new ResultImpl();

        if (sessionFlag.equals("list1")){
            request.getSession().setAttribute(sessionFlag,null);
            request.getSession().setAttribute("list11",null);
            request.getSession().setAttribute("list22",null);

        }else if (sessionFlag.equals("list3")){
            request.getSession().setAttribute(sessionFlag,null);
            request.getSession().setAttribute("list33",null);
            request.getSession().setAttribute("list44",null);

        }if (sessionFlag.equals("list5")){
            request.getSession().setAttribute(sessionFlag,null);
            request.getSession().setAttribute("list55",null);
            request.getSession().setAttribute("list66",null);
            request.getSession().setAttribute("list77",null);
        }
        result.setStatus("1");
        result.setMess("数据更新成功。");
        return result;
    }



    /**
     * 应急管理中心
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/tables/yjmanage_center")
    public String yjmanage_center(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company c = companyMapper.selectByPrimaryKey(user.getId());
        List<Integer> count = userService.selectCount(new CompanyListReqDTO(), user);

        model.addAttribute("count", count);
        model.addAttribute("c", c);
        model.addAttribute("userName", userMapper.selectByPrimaryKey(user.getId()).getUserName());
        return "global/rescue/yjmanage_center";
    }

    @RequestMapping(value = "/findOne")
    public String findOne(Integer safetyStandardlistId, Model model, HttpServletRequest request) {

        TSafetyStandard TSafetyStandard = tSafetyStandardMapper.findOne(safetyStandardlistId);
        model.addAttribute("item", TSafetyStandard);
        return "global/company/tables/tab-detail";
    }

    /**
     * 查询该账号下基本信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "Standard")
    public String Standard(HttpServletRequest request, Model model){
        return "global/other/standard-list";
    }
    /**
     * 分页获取所公司信息数据
     */
    @RequestMapping(value="/getData", method = RequestMethod.POST)
    public @ResponseBody  Result getData(HttpServletRequest request, Model model, Integer page, String companyName) {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        Integer start = page * 10;
        if ("".equals(companyName.trim())) {//如果为空串则直接
            companyName = null;
        }
        List<Map<String, Object>> list = tCheckItemMapper.getData(user.getId(), user.getUserType(), start, companyName);
        for (Map<String, Object> map : list) {
            String time = tCheckItemMapper.selectLastOperateTimeById((Integer) map.get("userId"));
            if (time == null) {
                map.put("time", "暂无操作时间");
            } else {
                map.put("time", tCheckItemMapper.selectLastOperateTimeById((Integer) map.get("userId")));
            }
        }
        result.setMap("list", list);
        result.setStatus("0");
        return result;
    }
    @RequestMapping(value="/getTotalPage", method = RequestMethod.POST)
    public @ResponseBody  Result getTotalPage(HttpServletRequest request, Model model, Integer page, String companyName) {
        User user = getLoginUser(request);
        if("".equals(companyName)){
            companyName = null;
        }
        Integer total = tCheckItemMapper.getTotalPage(user.getId(), user.getUserType(), companyName);
        Integer totalPage = total/10+1;
        Result result = new ResultImpl();
        result.setMap("totals", total);//总条数
        result.setMap("totalPage", totalPage);
        return result;
    }
    @RequestMapping(value = "/tab-biaozhunC")
    public String findOneTwo(Integer safetyStandardlistId, Integer sort, Model model, HttpServletRequest request, Integer userId) {
        if (null == sort) {
            sort = 1;
        }
        List<TSafetyStandard> tSafetyStandard = tSafetyStandardMapper.findByparentId(safetyStandardlistId, sort);
        TSafetyStandard one = tSafetyStandardMapper.findOne(safetyStandardlistId);
        model.addAttribute("fuId", one.getParentId());
        model.addAttribute("list", tSafetyStandard);
        model.addAttribute("parentId", safetyStandardlistId);
        model.addAttribute("sort", sort);
        model.addAttribute("userId", userId);
        return "global/company/tables/tab-biaozhunC";
    }
    /**
     * 根据条件查询安全生产标准化数据
     *
     * @param parentId 父级id
     * @param flag     1: A级  2: B级
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String findAll(Integer parentId, Integer flag, HttpServletRequest request, Model model, Integer sort, Integer userId) {
        User user = getLoginUser(request);
        if (null == sort) {
            sort = 1;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",  userId);
        map.put("parentId", parentId);
        map.put("flag", flag);
        List<TSafetyStandard> TSafetyStandardlist = tSafetyStandardMapper.findAll(map);
        // 判断是否有顺序,有书序就按照顺序来,没有就是倒序
        model.addAttribute("sort", sort);
        model.addAttribute("companyName", companyMapper.selectByPrimaryKey(userId).getName());
        model.addAttribute("list", TSafetyStandardlist);
        model.addAttribute("userId", userId);
        return "global/company/tables/tab-biaozhun2";

    }

    @RequestMapping(value = "/findByParentId")
    public String findByParentId(Integer safetyStandardlistId, Model model, Integer sort, Integer userId) {
        if (null == sort) {
            sort = 1;
        }
        List<TSafetyStandard> TSafetyStandard = tSafetyStandardMapper.findByparentId(safetyStandardlistId, sort);
        model.addAttribute("list", TSafetyStandard);
        model.addAttribute("parentId", safetyStandardlistId);
        model.addAttribute("sort", sort);
        model.addAttribute("userId", userId);
        return "global/company/tables/tab-biaozhunB";
    }

    /**
     * create by  : 小明！！！
     * description: TODO
     * create time: 2019/8/26 15:56
     */
    @RequestMapping(value = "company-allcompany")
    public String companyAllcompany(HttpServletRequest request, Model model){

        return "/global/company/allcompany";
    }
    
    /**
     * create by  : 小明！！！
     * description: TODO
     * create time: 2019/8/26 15:56
     */
    @RequestMapping(value = "allcompany-district")
    public String allcompanyDistrict(HttpServletRequest request, Model model) {

        return "/global/company/allcompany-district";
    }

    /**
     * 企业搜索页
     * @return
     */
    @RequestMapping("searchPage")
    public String searchPage(HttpServletRequest request, Model model){

        return "global/other/company-search";
    }
    /**
     * 企业应急管理中心
     */
    @RequestMapping("/conManager")
    public String conManager(HttpServletRequest request, Model model,Integer userId){
        model.addAttribute("userId", userId);
        return "global/rescue/threeLeft1";
    }
    /**
     * 企业应急管理中心
     */
    @RequestMapping("/conrespond")
    public String conManager1(HttpServletRequest request, Model model,Integer userId){
        model.addAttribute("userId", userId);
        return "global/rescue/threeLeft2";
    }
    /**企业基本信息
     * TODO 基本信息页面
     */
    @RequestMapping("basic-information")
    public String basicInformation(Model model, HttpServletRequest request, Integer userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Regulation regulationComp = regulationGet(user.getId());
        if (company.getHazard() == null) {
            if (regulationComp.getCisDanger() != null) {
                company.setHazard(regulationComp.getCisDanger());
                companyMapper.updateByPrimaryKey(company);
            }
        } else {
            if (regulationComp.getCisDanger() == null) {
                company.setHazard(0);
            } else {
                company.setHazard(regulationComp.getCisDanger());
            }
            companyMapper.updateByPrimaryKey(company);
        }

        if (null != company.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(company.getRegionId()));
        }

        model.addAttribute("c", company);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "global/other/information1";
    }

    /**
     * 企业定位
     * @param model
     * @param request
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("information/information4")
    public String information4(Model model, HttpServletRequest request, Integer userId) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(null == userId ? user.getId() : userId);
        if (null != company.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(company.getRegionId()));
        }
        model.addAttribute("company", company);
        model.addAttribute("user", userMapper.selectByPrimaryKey(company.getUserId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(company.getVillageId()));
        return "company/information/information4";
    }
    /**
     * TODO 四色图
     * @param model
     * @param request
     * @param flag
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "control-list2")//zhangcl 2018.10.13
    public String controlList2(Model model, HttpServletRequest request, String flag, Integer userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("order", 1);
        Map<String, Object> m2 = new HashMap<String, Object>();
        m.put("uid", user.getId());
        model.addAttribute("company", company);
        model.addAttribute("user", userMapper.selectByPrimaryKey(company.getUserId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(company.getVillageId()));
        List<Map<String, Object>> list2 = aCompanyManualMapper.selectByMapTwo(m);
        List<Map<String, Object>> list = aCompanyManualMapper.selectRed(user.getId());
        model.addAttribute("list", list);
        model.addAttribute("list2", list2);

        Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>();
        selectMeadths33(list, levmap);
        model.addAttribute("treeMap", levmap);
        model.addAttribute("flag", flag);

        /*
         * 添加根据风险等级查询，查询结果可能会受到其他修改风险等级功能的影响	wz_20181116
         */
        m.put("level", "红色");
        List<Map<String, Object>> list11 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list11", list11);

        m.put("level", "橙色");
        List<Map<String, Object>> list22 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list22", list22);

        m.put("level", "黄色");
        List<Map<String, Object>> list33 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list33", list33);

        m.put("level", "蓝色");
        List<Map<String, Object>> list44 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list44", list44);

        m.put("level", "");
        List<Map<String, Object>> list55 = aCompanyManualMapper.selectNum(m);
        model.addAttribute("list55", list55);

        Integer number = list11.size() + list22.size() +  list33.size() + list44.size() + list55.size();

        model.addAttribute("number", number);
        if (flag.equals("2")) {
            //log.error("zhangcl 2018.10.18 controlList3,area_range="+company.getAreaRange());
            return "company/safety-system/control-list3";
        } else {
            return "company/safety-system/control-list2";
        }

    }

    private void selectMeadths33(List<Map<String, Object>> list, Map<String, LinkedHashSet<String>> levmap) {
        for (Map<String, Object> m1 : list) {
            String level1 = null == m1.get("level1") ? "" : m1.get("level1").toString();
            String level2 = null == m1.get("level2") ? "" : m1.get("level2").toString();
            LinkedHashSet<String> l2s = levmap.get(level1);
            if (null == l2s) {
                l2s = new LinkedHashSet<String>();
                levmap.put(level1, l2s);
            }
            l2s.add(level2);
        }
    }

    @RequestMapping(value = "tables/tab-yjlist")
    public String yjjyList(HttpServletRequest request, Model model, String companyName, String name, Integer isTime, Integer c, Integer userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        Map<String, Object> m = new HashMap<String, Object>();
        if (null != c && c.compareTo(1) == 0) {
            m.put("ownerId", user.getId());
        } else {
            setUserId(user, m);
        }
        m.put("name", name);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("list", tContingencyPlanMapper.selectTable(m));
        model.addAttribute("name", name);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("c", c);
        return "company/tables/tab-yjlist";
    }
    /**
     * 前台首页
     *
     * @throws Exception
     */
    @RequestMapping("threeLeft")
    public String threeLeft(Model model, HttpServletRequest request, String leftBasic) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());

//        // TODO 判断该公司的行业是否为化工行业,为化工行业就显示内容
//        if (company.getIndustry().contains("化工")) {
//            model.addAttribute("industry", 5);
//        }

        model.addAttribute("leftBasic", leftBasic);
        model.addAttribute("userName", user.getUserName());

        return "company/threeLeft";
    }
    @RequestMapping("command")
    public String command(Model model, HttpServletRequest request){
        return "global/rescue/threeLeft3";
    }

    /**
     * 企业搜索页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("searchPage2")
    public String searchPage2(HttpServletRequest request, Model model){

        return "global/other/company-search2";
    }
    /**
     * 检查历史
     * TODO 排查治理记录 隐患排查记录(只需要已经检查过的,没有不合格记录的)
     * user. userType : 管理类型  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     */
    @RequestMapping(value = "check-list500")//flag:3 部门抽查
    public String troubleList4(HttpServletRequest request, String title, Integer type, String companyName,
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
        selectMeadths5(model, user, m, set);
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

    @RequestMapping(value = "back/global")
    public String back2(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        User user =  (User)session.getAttribute("govUser");
        if(user == null){//返回后的刷新
            user = getLoginUser(request);
        }else{
            SessionUtil.setUser(request, user);
            session.removeAttribute("govUser");//返回时将moveBefore的session删除
        }
        if(user.getUserType()==10){//行业端
            Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
            model.addAttribute("name_", trade.getName());
            model.addAttribute("loginUserId", user.getId());
            if(trade.getIsClique() == 1){//集团型企业
                return "steel/clique-main";
            }

        }
        selectMeadths11(model, user);
        if(user.getUserType()==4){//乡
            model.addAttribute("name_", villageMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
        }
        selectMeadths22(model, user);
        return "redirect:/global/main";
    }

    private void selectMeadths22(Model model, User user) {
        if(user.getUserType()==9){//安泰
            model.addAttribute("list", districtMapper.selectDistrict());
            model.addAttribute("list1", tradeMapper.selectTrade());
            model.addAttribute("name_", user.getUserName());
            model.addAttribute("loginUserId", user.getId());
        }
    }

    private void selectMeadths11(Model model, User user) {
        if(user.getUserType()==6){//区
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
        }
        if(user.getUserType()==3){//镇
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
        }
    }

}
