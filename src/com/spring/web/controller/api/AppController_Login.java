package com.spring.web.controller.api;

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
            result.setStatus("1");
            result.setMessage("政府登陆还没有做");
            result.setData(null);
        }

        return result;
    }

    private AppResult CommonLogin(HttpServletRequest request, String username, String password) {
        AppResult result = new AppResultImpl();
        ZzjgPersonnel zzjgPersonnel = zzjgPersonnelService.selectPersonnelByNameAndPwd(username, password);

        // 是否有此账号
        if (zzjgPersonnel == null) {
            result.setStatus("1");
            result.setMessage("账号或者密码错误");
            return result;
        }

        if (!"1".equals(zzjgPersonnel.getStatus()) && !"2".equals(zzjgPersonnel.getStatus())  ) {
            result.setStatus("1");
            result.setMessage("没有权限");
            return result;
        }else if(null == zzjgPersonnel.getStatus() || "".equals(zzjgPersonnel.getStatus())){
            result.setStatus("1");
            result.setMessage("没有权限");
            return result;
        }

        String token = String.valueOf(UUID.randomUUID()).replaceAll("-", "");
        System.out.println(token);
        // 登陆组合实体类
        UserItem userItem = new UserItem();
        userItem.setType(5);
        userItem.setZzjgPersonnel(zzjgPersonnel);

        userItem.setToken(token);
        result.setStatus("0");
        result.setMessage("登陆成功");


        result.setData(userItem);

        // 存入session中
        try {
            saveAttribute(request, zzjgPersonnel,token);
        } catch (Exception e) {
            e.printStackTrace();
            saveAttribute(request, zzjgPersonnel,token);
        }

        return result;
    }


    /**
     * 将数据放入session中
     */
    private void saveAttribute(HttpServletRequest request, ZzjgPersonnel zzjgPersonnel,String token) {
        request.getSession().getServletContext().setAttribute(token,zzjgPersonnel );

        //request.getSession().setAttribute(token, zzjgPersonnel);
        //String id = request.getSession().getId();
        //System.out.println(id);
    }

    /**
     * 注销
     * @param token
     * @return AppResult
     */
    @ResponseBody
    @RequestMapping(value= "A251", method= RequestMethod.POST)
    public AppResult LogionOut(String token ,HttpServletRequest request){
        AppResult result =new AppResultImpl();

        Object object = request.getSession().getServletContext().getAttribute(token);
        if(null == object){
            result.setStatus("1");
            result.setMessage("已经退出");
            return result;
        }

        request.getSession().removeAttribute(token);
        result.setStatus("0");
        result.setMessage("退出成功");

        return result;

    }

}
