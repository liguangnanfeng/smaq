package com.spring.web.controller.api;

import com.mchange.lang.IntegerUtils;
import com.spring.web.BaseController;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;


import com.spring.web.service.CheckCompany.Zzjg_CompanyService;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author  桃红梨白
 * TODO 自定义检查模块
 * 1. 根据传递的用户id查询企业
 * 2. 根据企业查询相应的部门
 * 3. 根据部门查询相应的风险
 *
 *
 */
@Controller
@RequestMapping(value = "api/custom/check")
public class AppController_Custom_Check  extends BaseController {


    /**
     * 查询员工
     */
    @Autowired
    private Zzjg_PersonnelService zzjg_personnelService;


    /**
     * 根据用户查询用户表 => 获取企业信息 => 获取企业所属部门信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="A200")
    public AppResult checkCompany(HttpServletRequest request){

        AppResult result = new AppResultImpl();
        // 获取到用户ID
       // Integer userId = getAppUserId(request);
//        if (null == userId) {
//            result.setStatus("2");
//            result.setMessage("登录超时");
//            return result;
//        }

        // 根据用户id查询所属公司
        //Integer companyId = zzjg_personnelService.selectCompanyIdByuserId(userId);
        Integer companyId = zzjg_personnelService.selectCompanyIdByuserId(844);

        System.out.println(companyId);
        return result;

    }








}
