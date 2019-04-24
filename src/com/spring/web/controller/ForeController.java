/**  
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicDBValues;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Company;
import com.spring.web.model.Notice;
import com.spring.web.model.User;
import com.spring.web.model.Village;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.user.LoginReqDTO;
import com.spring.web.util.ConstantsUtil;
import com.spring.web.util.SessionUtil;
import com.spring.web.util.WeixinUtil;

/**
 * @Title: ForeController
 * @Description: 前台控制器
 * @author FL
 * @date 2016年1月25日 下午3:40:57
 * @version V1.0
 */
@Controller
@RequestMapping("/fore")
public class ForeController extends BaseController {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = -9202435271301177005L;
    public static final String VALIDATE_CODE = "validateCode";
    @Autowired
    private UserService userService;

    /**
     * 登录页面
     */
    @RequestMapping(value = "town")
    public String safety(Model model, HttpServletRequest request) throws Exception {
        return "village/blogin2";
    }

    /**
     * 登录页面
     */
    @RequestMapping(value = "login-smaq")
    public String smaq(Model model, HttpServletRequest request) throws Exception {
        return "village/blogin-shuang";
    }

    /**
     * 登录页面
     */
    @RequestMapping(value = "company-smaq")
    public String companySmaq(Model model, HttpServletRequest request) throws Exception {
        return "company/blogin-shuang";
    }

    /**
     * 注册页面
     */
    @RequestMapping(value = "/toRegister-smaq")
    public String toRegisterSmaq(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("list", villageMapper.selectAllVillage());
        return "company/register-shuang";
    }

    /**
     * 注册页面
     */
    @RequestMapping(value = "/toRegister")
    public String toRegister(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("list", villageMapper.selectAllVillage());
        return "company/register";
    }

    /**
     * 注册三级联动菜单
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getDistrict")
    public @ResponseBody Result getDistrict(Model model, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        List<DynamicParameter<String, Object>> districtList = districtMapper.selectDistrict();
        List<Map<String, Object>> townList = townMapper.selectTown();
        List<DynamicParameter<String, Object>> villageList = villageMapper.selectVillage();
        for (Map<String, Object> town : townList) {
            List<Map<String, Object>> childs = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> village : villageList) {
                if (village.get("pid").toString().equals(town.get("code").toString())) {
                    childs.add(village);
                }
            }
            town.put("childs", childs);
        }
        for (DynamicParameter<String, Object> district : districtList) {
            List<Map<String, Object>> childs = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> town : townList) {
                if (town.get("pid").toString().equals(district.get("code").toString())) {
                    childs.add(town);
                }
            }
            district.put("childs", childs);
        }
        result.setMap("districtList", districtList);
        return result;
    }

    /**
     * 企业注册
     */
    @RequestMapping(value = "register")
    public @ResponseBody Result register(HttpServletRequest request, String userName, String psw, String village,
            String validateCode) throws Exception {
        Result result = new ResultImpl();
        if (StringUtils.isBlank(userName)) {
            result.setStatus("1");
            result.setMap("message", "企业名称不能为空。");
            return result;
        }
        if (StringUtils.isBlank(psw)) {
            result.setStatus("1");
            result.setMap("message", "密码不能为空。");
            return result;
        }
        User u = userMapper.selectUserByUserName(userName);
        if (null != u) {
            result.setStatus("1");
            result.setMap("message", "此企业已存在");
            return result;
        }
        Village v = villageMapper.selectVillageByName(village);
        if (null == v) {
            result.setStatus("1");
            result.setMap("message", "该村子不存在。");
            return result;
        }
        User vu = userMapper.selectByPrimaryKey(v.getUserId());
        if ("1".equals(vu.getIsFreeze())) {
            result.setStatus("1");
            result.setMap("message", "该村子已被冻结，无法注册账号。");
            return result;
        }
        User ux = new User();
        ux.setUserName(userName);
        ux.setPsw(psw);
        ux.setIsFreeze("0");
        userService.registar(ux, result, null, v.getUserId());
        // UsernamePasswordToken token = new UsernamePasswordToken(userName, psw);
        // token.setRememberMe(true);
        // Subject currentUser = SecurityUtils.getSubject();
        // currentUser.login(token);
        // SessionUtil.setUser(request, ux);
        return result;
    }

    /**
     * 企业端前台登录页
     */
    @RequestMapping("company")
    public String login(Model model, String un) {
        if(StringUtils.isNotBlank(un)) {
            un = utf8Str(un);
            model.addAttribute("un", un);
        }
        return "company/blogin";
    }

    /**
     * 企业端前台注销登录
     */
    @RequestMapping("companyForeLogout")
    public String companyForeLogout(HttpServletRequest request) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        String y = request.getServerName();
        if(y.indexOf("kfq") > -1) {
            return "redirect:/fore/company-smaq";
        }
        return "redirect:/fore/company";
    }

    /**
     * 危化品页面
     */
    @RequestMapping("danger/danger-list")
    public String dangerList(Model model, HttpServletRequest request, String dangerName) throws Exception {
        if (StringUtils.isEmpty(dangerName)) {
            return "danger/danger-list";
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("dangerName", dangerName);
        model.addAttribute("list", dangerLibMapper.selectDangerList(m));
        model.addAttribute("dangerName", dangerName);
        return "danger/danger-list";
    }

    /**
     * 危险源信息展示
     */
    @RequestMapping("danger/danger-show")
    public String dangerShow(Model model, HttpServletRequest request) throws Exception {
        return "danger/danger-show";
    }

    /**
     * 易制爆品页面
     */
    @RequestMapping("danger/bao-list")
    public String baoList(Model model, HttpServletRequest request) throws Exception {
        return "danger/bao-list";
    }

    /**
     * 易制毒化学品页面
     */
    @RequestMapping("danger/du-list")
    public String duList(Model model, HttpServletRequest request) throws Exception {
        return "danger/du-list";
    }

    /**
     * 重大危险源页面
     */
    @RequestMapping("danger/wei-list")
    public String weiList(Model model, HttpServletRequest request) throws Exception {
        return "danger/wei-list";
    }

    /**
     * 职业病危害页面
     */
    @RequestMapping("danger/zhi-list")
    public String zhiList(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("list", fengMapper.selectFengList());
        model.addAttribute("list2", huaMapper.selectHuaList());
        return "danger/zhi-list";
    }

    /**
     * 后台用户登录
     */
    @RequestMapping("/blogin")
    public @ResponseBody Result login(LoginReqDTO dto, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = dto.getUserName();
        String password = dto.getPassword();
        // 获取HttpSession中的验证码
        //String verifyCode = (String) request.getSession().getAttribute(VALIDATE_CODE);
        // 获取用户请求表单中输入的验证码
        //String submitCode = dto.getValidateCode();
        // 清除验证码，确保验证码只能用一次
        //SessionUtil.removeValidateCode(request);
        Result result = new ResultImpl();
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
//        if (StringUtils.isBlank(verifyCode)) {
//            result.setStatus("1");
//            result.setMap("message", "验证码不能为空。");
//            return result;
//        }
//        if (StringUtils.isEmpty(submitCode) || !StringUtils.equalsIgnoreCase(verifyCode, submitCode)) {
//            result.setStatus("1");
//            result.setMap("message", "验证码错误。");
//            return result;
//        }
        try {
            User user = userMapper.selectUserByUserName(username);
            // 判断前后台登录
            if (user == null) {
                result.setStatus("1");
                result.setMap("message", "该账号不存在。");
                return result;
            }
            // 判断前后台登录
            if (!"0".equals(user.getIsFreeze())) {
                result.setStatus("1");
                result.setMap("message", "该账号被冻结。");
                return result;
            }
            
            // 判断前后台登录
            if (!user.getUserName().equals("惠山区港口")) {
                // 判断前后台登录
                if (user.getUserType() == 1 || user.getUserType() == 2 || user.getUserType() == 5) {
                    result.setStatus("1");
                    result.setMap("message", "请用政府账号登录。");
                    return result;
                }
            }
            
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
     * 企业端前台登录
     */
    @RequestMapping("companyLogin")
    public @ResponseBody Result companyLogin(LoginReqDTO dto, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = dto.getUserName();
        String password = dto.getPassword();
        // 获取HttpSession中的验证码
        //String verifyCode = (String) request.getSession().getAttribute(VALIDATE_CODE);
        // 获取用户请求表单中输入的验证码
        //String submitCode = dto.getValidateCode();
        // 清除验证码，确保验证码只能用一次
        //SessionUtil.removeValidateCode(request);
        Result result = new ResultImpl();
        //账号为空 return
        if (StringUtils.isBlank(username)) {
            result.setStatus("1");
            result.setMap("message", "账号不能为空。");
            return result;
        }
        // 密码为空 return
        if (StringUtils.isBlank(password)) {
            result.setStatus("1");
            result.setMap("message", "密码不能为空。");
            return result;
        }

/*       验证码
        if (StringUtils.isBlank(verifyCode)) {
            result.setStatus("1");
            result.setMap("message", "验证码不能为空。");
            return result;
        }
        if (StringUtils.isEmpty(submitCode) || !StringUtils.equalsIgnoreCase(verifyCode, submitCode)) {
            result.setStatus("1");
            result.setMap("message", "验证码错误。");
            return result;
        }*/

        try {
            //TODO controller直接和dao做交互
            User user = userMapper.selectUserByUserName(username);
            // 判断前后台登录
            if (user == null) {
                result.setStatus("1");
                result.setMap("message", "该账号不存在。");
                return result;
            }
            // 判断前后台登录
            if (!"0".equals(user.getIsFreeze())) {
                result.setStatus("1");
                result.setMap("message", "该账号被冻结。");
                return result;
            }
            // 判断前后台登录
            if (user.getUserType() != 5) {
                result.setStatus("1");
                result.setMap("message", "请用企业账号登录。");
                return result;
            }
            // TODO 防止表单重复提交和进行身份验证
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            SessionUtil.setUser(request, user);
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
     * 安全之家详情
     */
    @RequestMapping(value = "notice-show2")
    public String noticeShow2(Model model, Integer id, Integer type) throws Exception {
        Notice n = noticeMapper.selectByPrimaryKey(id);
        model.addAttribute("n", n);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", type);
        model.addAttribute("list", noticeMapper.selectNoticeList(m));
        model.addAttribute("type", type);
        return "village/notice/notice-show2";
    }

    /**
     * 公告页面
     */
    @RequestMapping("notice-list")
    public String noticeList(Model model, HttpServletRequest request) throws Exception {
        // User user = getLoginUser(request);
        // if(null == user) {
        // return "redirect:/common/timedout";
        // }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", 1);
        // if(user.getUserType().intValue() == 5) {//企业
        // m.put("userId", companyMapper.selectByPrimaryKey(user.getId()).getVillageId());
        // } else {//企业
        // m.put("userId", user.getId());
        // }
        model.addAttribute("list", noticeMapper.selectNoticeList(m));
        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("type", "2,3,4");
        model.addAttribute("list2", noticeMapper.selectNoticeList(m2));
        model.addAttribute("type", 1);
        return "company/notice/notice-list";
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
     * 安全员之家页面
     */
    @RequestMapping("notice-list2")
    public String noticeList2(Model model, HttpServletRequest request, String type) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", 2);
        model.addAttribute("list", noticeMapper.selectNoticeList(m));
        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("type", "2,3,4");
        model.addAttribute("list2", noticeMapper.selectNoticeList(m2));
        Map<String, Object> m3 = new HashMap<String, Object>();
        m3.put("type", 1);
        model.addAttribute("list3", noticeMapper.selectNoticeList(m3));
        model.addAttribute("type", type);
        return "company/notice/notice-list2";
    }

    /**
     * 公告详情
     */
    @RequestMapping(value = "notice-show")
    public String noticeShow(Integer id, HttpServletRequest request, Model model, Integer type) throws Exception {
        model.addAttribute("n", noticeMapper.selectByPrimaryKey(id));
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", type);
        model.addAttribute("list", noticeMapper.selectNoticeList(m));
        model.addAttribute("type", type);
        return "company/notice/notice-show";
    }
    
    /**
     * 主要物质理化、危险、有害特性表
     */
    @RequestMapping(value = "safety-system/risk-information-list2")
    public String riskInformationList2(Model model) throws Exception {
        model.addAttribute("list", aMaterialMapper.selectAll());
        return "company/safety-system/risk-hua";
    }
    
    /**
     * 主要物质理化、危险、有害特性表
     */
    @RequestMapping(value = "safety-system/risk-information-detail2")
    public String riskInformationDetail2(Model model, Integer id) throws Exception {
        model.addAttribute("be", aMaterialMapper.selectByPrimaryKey(id));
        return "company/safety-system/risk-hua-detail";
    }

    /**
     * 企业注册机构
     */
    @RequestMapping("regL")
    public @ResponseBody Result regL(Model model, Integer regionId) throws Exception {
        Result result = new ResultImpl();
        result.setMap("list", companyRegMapper.selectByRegionId(regionId));
        return result;
    }
    
    /**
     * 处理行业与将官行业对应
     */
    @RequestMapping("indestry2Industry")
    public @ResponseBody Result indestry2Industry(Model model) throws Exception {
        Result result = new ResultImpl();
        String[][] industry2_ = ConstantsUtil.industry2_;
        DynamicDBValues dy = baseDao.createDBValues();
        for(String[] s : industry2_) {
            dy.put("sql", "update company_tbl set industry = '" + s[1] + "' WHERE industry2 like '%" + s[0] + "%'");
            baseDao.update("company.update_demo", dy);
        }
        return result;
    }
    
    /**
     * 企业经纬度处理
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("jwd")
    public @ResponseBody Result jwd(Model model) throws Exception {
        Result result = new ResultImpl();
        List<User> userL = userMapper.selectJwd();
        for(User u : userL) {
            Company c = companyMapper.selectByPrimaryKey(u.getId());
            String key = u.getUserName();
            if(StringUtils.isNotBlank(c.getAddress())) {
                key = c.getAddress();
            }
            String json = WeixinUtil.doGet("http://restapi.amap.com/v3/geocode/geo", 
                    "address=" + key + "&output=JSON&key=51d38bbdd51aaf526aca2db34f0fe075", 
                    "utf8", false);
            Map<String, Object> j = gson.fromJson(json, LinkedMap.class);
            String status = j.get("status").toString();
            if("1".equals(status)) {
                String count = j.get("count").toString();
                if(!"0".equals(count)) {//有数据
                    ArrayList<Map<String, Object>> geocodes = (ArrayList<Map<String, Object>>)j.get("geocodes");
                    String location = geocodes.get(0).get("location").toString();
                    u.setLongitude(location.split(",")[0]);
                    u.setLatitude(location.split(",")[1]);
                    userMapper.updateByPrimaryKeySelective(u);
                }
            } else {//解析异常
                
            }
        }
        return result;
    }
    
}
