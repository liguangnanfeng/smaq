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
 * TODO 企业端登陆
 * 状态码类型: 0 成功  1 检查人员  2 安全责任人  3 查询失败
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

        // 空数据
        if ( username == null||"".equals(username) || password == null || "".equals(password)){
            result.setStatus("3");
            result.setMessage("请输入账号或密码");
            return  result;
        }

       ZzjgPersonnel zzjgPersonnel = zzjgPersonnelService.selectPersonnelByNameAndPwd(username,password);

        // 是否有此账号
        if(zzjgPersonnel==null){
            result.setMessage("账号或者密码错误");
            return result;
        }


        if(!"1".equals(zzjgPersonnel.getStatus())&& !"2".equals(zzjgPersonnel.getStatus())){
            result.setStatus("3");
            result.setMessage("没有权限");
            return  result;
        }

        // 是否具有响应的权限
        if("1".equals(zzjgPersonnel.getStatus())){
            result.setStatus("1");
            result.setMessage("检查人员");
            result.setData(zzjgPersonnel);

        }
        if("2".equals(zzjgPersonnel.getStatus())){
            result.setStatus("2");
            result.setMessage("安全责任人");
            result.setData(zzjgPersonnel);

        }

        // 存入session中
        try{
            saveAttribute(request,zzjgPersonnel);
        }catch (Exception e){
            e.printStackTrace();
            saveAttribute(request,zzjgPersonnel);
        }

       return  result;
    }


    /**
     * 将数据放入session中
     */
    private void saveAttribute(HttpServletRequest request ,ZzjgPersonnel zzjgPersonnel ){
        // 使用用户的id作为key
        Integer id = zzjgPersonnel.getId();
        request.getSession().setAttribute(Integer.toString(id), zzjgPersonnel);

    }




}
