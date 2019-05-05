package com.spring.web.controller.api;

import com.spring.web.dao.UserMapper;
import com.spring.web.listener.MySessionContext;
import com.spring.web.model.User;
import com.spring.web.model.UserItem;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.CheckCompany.LoginService;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import com.spring.web.util.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author: 桃红梨白
 * TODO 企业端登陆
 * 状态码类型: 0 成功  1 失败
 */

@Controller
@RequestMapping(value = "api/custom/login")
public class AppController_Login {

    /**
     * 企业端登陆
     */
    @Autowired
    private Zzjg_PersonnelService zzjgPersonnelService;

    /**
     * 政府端登陆
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登陆功能
     *
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "A250", method = RequestMethod.POST)
    @ResponseBody
    public AppResult userLogin(HttpServletRequest request, String username, String password, Integer type)  {

        AppResult result = new AppResultImpl();

        // 空数据
        if (username == null || "".equals(username) || password == null || "".equals(password) || null == type) {
            result.setStatus("1");
            result.setMessage("请输入账号或密码");
            return result;
        }
        // 企业
        if ("1".equals(Integer.toString(type))) {
            result = CommonLogin(request, username, password);

        }

        // 政府
        if ("2".equals(Integer.toString(type))) {
            //政府端登陆 写一个登陆接口
            result = countryLogin(request, username, password);

        }

        return result;
    }

    /**
     * 政府端登陆
     */
    private AppResult countryLogin(HttpServletRequest request, String username, String password){
        AppResult result = new AppResultImpl();

        try {
            User user = userMapper.selectUserByUserName(username);
            // 判断前后台登录
            if (user == null) {
                result.setStatus("1");
                result.setMessage("该账号不存在");
                return result;
            }
            // 判断前后台登录
/*            if (user.getUserType() > 2) {
                result.setStatus("1");
                result.setMessage("请用管理员账号登录。");
                return result;
            }*/
            // 判断前后台登录
            if (!"0".equals(user.getIsFreeze())) {
                result.setStatus("1");
                result.setMessage("该账号被冻结。");
                return result;
            }
            // 对登陆数据进行验证信息
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
        /*    Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            SessionUtil.setUser(request, user);*/

            // 设置token
            String tokenStr = String.valueOf(UUID.randomUUID()).replaceAll("-", "");

            // 将数据存储Dao组合实体类中
            UserItem userItem = new UserItem();

            userItem.setType(user.getUserType());  //类型进行判断
            userItem.setUser(user);
            userItem.setToken(tokenStr);
            // 将数据存储到返回数据中
            result.setData(userItem);

            //将获取到的用户信息存储到session中
            String sessionId = saveAttribute(request, user, tokenStr);
            userItem.setSessionId(sessionId);

        } catch (Exception uae) {
            uae.printStackTrace();
            result.setStatus("1");
            result.setMessage("用户名或密码不正确。");
            return result;

        }

        return result;
    }


    /**
     * 企业端登陆
     * @param request
     * @param username
     * @param password
     * @return
     */
    private AppResult CommonLogin(HttpServletRequest request, String username, String password) {
        AppResult result = new AppResultImpl();
        try {
            ZzjgPersonnel zzjgPersonnel = zzjgPersonnelService.selectPersonnelByNameAndPwd(username, password);

            // 数据库是否有此账号
            if (zzjgPersonnel == null) {
                result.setStatus("1");
                result.setMessage("没有此用户");
                return result;
            }

            if (!"1".equals(zzjgPersonnel.getStatus()) && !"2".equals(zzjgPersonnel.getStatus())) {
                result.setStatus("1");
                result.setMessage("没有权限");
                return result;
            } else if (null == zzjgPersonnel.getStatus() || "".equals(zzjgPersonnel.getStatus())) {
                result.setStatus("1");
                result.setMessage("没有权限");
                return result;
            }

            // 设置token
            String token = String.valueOf(UUID.randomUUID()).replaceAll("-", "");

            // 登陆组合实体类
            UserItem userItem = new UserItem();

            userItem.setType(5);
            userItem.setZzjgPersonnel(zzjgPersonnel);
            userItem.setToken(token);

            result.setStatus("0");
            result.setMessage("登陆成功");
            result.setData(userItem);

            // 存入域中
            String sessionId = saveAttribute(request, zzjgPersonnel, token);
            userItem.setSessionId(sessionId);

        } catch (Exception e) {
            // 表示密码不正确
            result.setStatus("1");
            result.setMessage("密码不正确");

        }

        return result;
    }

    /**
     * 将数据放入session中
     */
    private String saveAttribute(HttpServletRequest request, Object zzjgPersonnel, String token) {
        //request.getSession().getServletContext().setAttribute(token, zzjgPersonnel);

        // 获取session对象
        HttpSession session = request.getSession();

        session.setAttribute(token, zzjgPersonnel);
        // 并存入map集合中
        MySessionContext myc= MySessionContext.getInstance();
        myc.addSession(session);

        String sessionId = request.getSession().getId();
        return sessionId;
    }

    /**
     *  注销,并删除session
     *  但是session的过期时间,延长到七天是不是有点长
     *
     * @param token
     * @return AppResult
     */
    @ResponseBody
    @RequestMapping(value = "A251", method = RequestMethod.POST)
    public AppResult LogionOut(String sessionId, String token, HttpServletRequest request) {
        AppResult result = new AppResultImpl();

        if(token==null || sessionId==null){
            result.setStatus("0");
            result.setMessage("已退出");

            return result;

        }

        MySessionContext myc= MySessionContext.getInstance();
        HttpSession sess = myc.getSession(sessionId);
        sess.removeAttribute(token);  //删除session
        myc.delSession(sess); //删除session

        request.getSession().removeAttribute(token);
        result.setStatus("0");
        result.setMessage("退出成功");

        return result;

    }

}
