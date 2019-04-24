package com.spring.web.controller;

import com.spring.web.BaseController;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.tobject.user.LoginReqDTO;
import com.spring.web.util.CookieUtil;
import com.spring.web.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: LoginController
 * @Description: 登录控制器
 * @author FL
 * @date 2016年1月21日 下午2:58:52
 * @version V1.0
 */
@Controller
public class LoginController extends BaseController {
    /**
     * serialId
     */
    private static final long serialVersionUID = 1L;
    /**
     * 日志记录器
     */
    private final static Logger log = Logger.getLogger(LoginController.class);
    // 放到session中的key
    public static final String VALIDATE_CODE = "validateCode";

    /**
     * 后台用户登录
     */
    @RequestMapping("/blogin")
    public @ResponseBody
    Result login(LoginReqDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            if (user.getUserType() > 2) {
                result.setStatus("1");
                result.setMap("message", "请用管理员账号登录。");
                return result;
            }
            // 判断前后台登录
            if (!"0".equals(user.getIsFreeze())) {
                result.setStatus("1");
                result.setMap("message", "该账号被冻结。");
                return result;
            }
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
     * 前台用户注销登录
     */
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        CookieUtil.setCookie(response, "validate", "", -1);
        String y = request.getServerName();
        if(y.indexOf("kfq") > -1) {
            return "redirect:/fore/company-smaq";
        }
        return "redirect:/";
    }
    
    @RequestMapping("")
    public String index() throws Exception {
        return "redirect:/village";
    }

    /**
     * 后台用户注销登录
     */
    @RequestMapping("/backLogout")
    public String backLogout() throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/back";
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
        return "redirect:/village";
    }
    
    @RequestMapping("gang")
    public String indexGang() throws Exception {
        return "/company/blogin-gang";
    }
    
    /**
     * 后台用户登录（惠山区港口）
     */
    @RequestMapping("/blogin-gang")
    public @ResponseBody
    Result loginGang(LoginReqDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            if (user.getUserType() == 1 || user.getUserType() == 2 || user.getUserType() == 5) {
                result.setStatus("1");
                result.setMap("message", "请用政府账号登录。");
                return result;
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
    
    @RequestMapping("gang-list")
    public String gangList(HttpServletRequest request, String companyName, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        model.addAttribute("list", monitorMapper.selectGroupByMap(m));
        return "/company/gang-list";
    }
}
