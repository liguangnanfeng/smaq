package com.spring.web.controller.api;

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

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 09:18
 * TODO 企业端进行登陆的方法
 * 1. 第一步在数据库进行用户名称查询然后进行数据判断
 * 2. 判断状态是否为  1, 表示的是检查人员  2. 表示为被检查人员 , 3 表示查询失败
 * 3. 判断用户手机号是否正确
 *
 */
@Controller
@RequestMapping(value = "api/custom/login")
public class AppController_Login {

    @Autowired
    private Zzjg_PersonnelService zzjgPersonnelService;

    /**
     * 企业端用户登陆功能
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "A250",method = RequestMethod.POST  )
    @ResponseBody
    public AppResult userLogin(HttpServletRequest request, String username ,String password){
        AppResult result =new AppResultImpl();
        result.setStatus("3");

        // 1. 用户是否进行输入账号密码
        if ( username == null||"".equals(username) || password == null || "".equals(password)){
            result.setMessage("请输入账号或密码");
            return  result;
        }

        // 2. 判断数据库中是否有该数据,
       ZzjgPersonnel zzjgPersonnel = zzjgPersonnelService.selectPersonnelByNameAndPwd(username,password);

        // 判断是否有此账号
        if(zzjgPersonnel==null){
            result.setMessage("账号或者密码错误");
            return result;
        }

        // 判断是否有检查的权限表示为检查人员
        if("1".equals(zzjgPersonnel.getStatus())){
            result.setStatus("1");
            result.setMessage("检查人员");
            result.setData(zzjgPersonnel);
            return result;
        }
        // 判断是否有检查的权限表示为检查人员
        if("2".equals(zzjgPersonnel.getStatus())){
            result.setStatus("2");
            result.setMessage("被检查人员");
            result.setData(zzjgPersonnel);
            return result;
        }
        result.setMessage("没有权限");

       return  result;
    }







}
