package com.spring.web.controller.api;

import com.spring.web.dao.AppTokenMapper;
import com.spring.web.dao.OfficialsMapper;
import com.spring.web.dao.UserMapper;
import com.spring.web.dao.ZzjgPersonnelMapper;
import com.spring.web.listener.MySessionContext;
import com.spring.web.model.*;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.CheckCompany.CountryCheck;
import com.spring.web.service.CheckCompany.LoginService;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.EncryptUtil;
import com.spring.web.util.RandomUtil;
import com.spring.web.util.SessionUtil;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: 桃红梨白
 * TODO 企业端登陆
 * 状态码类型: 0 成功  1 失败
 */

@Controller
@RequestMapping(value = "api/custom/login")
@SuppressWarnings("all")
public class AppController_Login {

    /**
     * 企业端
     */
    @Autowired
    private Zzjg_PersonnelService zzjgPersonnelService;

    /**
     * 政府端
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * token验证
     */
    @Autowired
    private AppTokenMapper appTokenMapper;

    /**
     * 验证apptoken
     */
    @Autowired
    private AppTokenData appTokenData;

    /**
     * 直接去查询
     */
    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;

    /**
     * 政府检查人员进行登陆
     */
    @Autowired
    private OfficialsMapper officialsMapper;

    /**
     * 政府端查询数据
     */
    @Autowired
    private CountryCheck countryCheck;


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
    public AppResult userLogin(HttpServletRequest request, String username, String password, Integer type) {

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
     * 政府端
     */
    private AppResult countryLogin(HttpServletRequest request, String username, String password) {
        AppResult result = new AppResultImpl();

        try {
            // 根据编号进行查询
            Officials user = officialsMapper.selectByCode(username);
            // 判断前后台登录
            if (user == null) {
                result.setStatus("1");
                result.setMessage("该账号不存在");
                return result;
            }
            // 判断前后台登录
          /*  if (!"1".equals(user.getDel())) {
                result.setStatus("1");
                result.setMessage("该账号被冻结。");
                return result;
            }*/
            // 判断密码是否错误
            if (!EncryptUtil.match(user.getPassword(), password)) {
                result.setStatus("1");
                result.setMessage("密码不正确");
                return result;
            }
            // 之后就表示登陆成功
            result.setStatus("0");
            result.setMessage("登陆成功");
            Map<String, Object> map = new HashMap<String, Object>();
            System.out.println(user);
            // TODO 查询详细的信息发送给前端进行展示  判断等级,查询不同的表数据
            Map map1 = countryCheck.selectParticular(user.getId(),user.getFlag());
            System.out.println(map1);
            map.put("user", map1);
            AppToken db_appToken = appTokenMapper.selectByUserId(String.valueOf(user.getId()));
            if (db_appToken == null) {
                AppToken appToken = new AppToken();
                appToken.setAccessToken(RandomUtil.generateUpperString(10));
                appToken.setLastLoginTime(new Date());
                appToken.setExpires(7);
                appToken.setUserId(user.getId());
                String sessionId = saveAttribute(request, user, appToken.getAccessToken());
                if (appTokenMapper.insertSelective(appToken) == 1) {
                    map.put("appToken", appToken);
                    map.put("type", user.getFlag());
                    map.put("sessionId", sessionId);
                    result.setData(map);

                }
            } else {
                db_appToken.setLastLoginTime((new Date()));
                db_appToken.setExpires(7);
                String sessionId = saveAttribute(request, user, db_appToken.getAccessToken());
                if (appTokenMapper.updateByPrimaryKeySelective(db_appToken) == 1) {
                    map.put("type", user.getFlag());
                    map.put("appToken",db_appToken );
                    map.put("sessionId", sessionId);
                    result.setData(map);
                }

            }
            return result;

        } catch (Exception uae) {
            uae.printStackTrace();
            result.setStatus("1");
            result.setMessage("用户名或密码不正确。");
            return result;

        }

    }

    /**
     * 企业端
     *
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

            Map<String, Object> map = new HashMap<String, Object>();
            Integer id = zzjgPersonnel.getId();
            Map stringObjectMap = zzjgPersonnelMapper.selectAll1( id );
            map.put("user", stringObjectMap);

            AppToken db_appToken = appTokenMapper.selectByUserId(String.valueOf(zzjgPersonnel.getId()));
            if (db_appToken == null) {
                AppToken appToken = new AppToken();
                appToken.setAccessToken(RandomUtil.generateUpperString(10));
                appToken.setLastLoginTime(new Date());
                appToken.setExpires(7);
                appToken.setUserId(zzjgPersonnel.getId());
                String sessionId = saveAttribute(request, zzjgPersonnel, appToken.getAccessToken());
                if (appTokenMapper.insertSelective(appToken) == 1) {
                    map.put("appToken", appToken);
                    map.put("type", 5);
                    map.put("sessionId", sessionId);
                    result.setData(map);

                }

            } else {
                db_appToken.setLastLoginTime((new Date()));
                db_appToken.setExpires(7);
                String sessionId = saveAttribute(request, zzjgPersonnel, db_appToken.getAccessToken());
                if (appTokenMapper.updateByPrimaryKeySelective(db_appToken) == 1) {
                    map.put("type", 5);
                    map.put("appToken", db_appToken);
                    map.put("sessionId", sessionId);
                    result.setData(map);
                }
            }

            return result;

        } catch (Exception e) {
            // 表示密码不正确
            result.setStatus("1");
            e.printStackTrace();
            result.setMessage("密码不正确");
            return result;
        }

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
        MySessionContext myc = MySessionContext.getInstance();
        myc.addSession(session);

        String sessionId = request.getSession().getId();
        return sessionId;
    }

    /**
     * 注销
     *
     * @param token
     * @return AppResult
     */
    @ResponseBody
    @RequestMapping(value = "A251", method = RequestMethod.POST)
    public AppResult LogionOut(HttpServletRequest request) {
        AppResult result = new AppResultImpl();

        try {
            // Object o = appTokenData.delectUserId(request);
            String access_token = request.getParameter("access_token");
            String sessionId = request.getParameter("sessionId");
            // 没有传递数据
            if (StringUtils.isEmpty(access_token)||StringUtils.isEmpty(sessionId)) {

                result.setStatus("1");
                result.setMessage("未正常退出!");
                return result;
            }
            MySessionContext myc = MySessionContext.getInstance();

            HttpSession session = myc.getSession(sessionId);
            myc.delSession(session);
            result.setStatus("0");
            result.setMessage("退出成功");

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("0");
            result.setMessage("退出成功");
            return result;
        }

    }

}
