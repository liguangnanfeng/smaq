package com.spring.web.controller.api;

import com.spring.web.listener.MySessionContext;
import com.spring.web.model.UserItem;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
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

    @Autowired
    private Zzjg_PersonnelService zzjgPersonnelService;

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
            result.setStatus("1");
            result.setMessage("政府登陆还没有做");
            result.setData(null);
        }

        return result;
    }

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
    private String saveAttribute(HttpServletRequest request, ZzjgPersonnel zzjgPersonnel, String token) {
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
     * 注销,并删除session
     *  但是session的过期时间,延长到七天是不是有点长
     *
     * @param token
     * @return AppResult
     */
    @ResponseBody
    @RequestMapping(value = "A251", method = RequestMethod.POST)
    public AppResult LogionOut(String sessionId, String token, HttpServletRequest request) {

        MySessionContext myc= MySessionContext.getInstance();
        HttpSession sess = myc.getSession(sessionId);
        myc.delSession(sess); //删除session
        sess.removeAttribute(token);  //删除session
        AppResult result = new AppResultImpl();

        request.getSession().removeAttribute(token);
        result.setStatus("0");
        result.setMessage("退出成功");

        return result;

    }

}
