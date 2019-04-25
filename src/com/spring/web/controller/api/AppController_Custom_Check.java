package com.spring.web.controller.api;


import com.spring.web.BaseController;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;


import com.spring.web.service.CheckCompany.Zzig_departmentService;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private Zzig_departmentService zzig_departmentService;



    /**
     * 根据用户查询用户表 => 获取企业信息 => 获取该企业所有部门信息
     * zppResult 状态  1: 检查人员  2. 被检查人员  3. 表示查询失败
     *
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value="A200", method = RequestMethod.POST )
    public AppResult checkCompany(HttpServletRequest request, Integer id){

        AppResult result = new AppResultImpl();
        result.setStatus("3");
        result.setMessage("查询失败");

        if(id == null){
            return result;
        }

        // 获取到用户ID
       // Integer userId = getAppUserId(request);
//        if (null == userId) {
//            result.setStatus("2");
//            result.setMessage("登录超时");
//            return result;
//        }

        // 根据用户id查询所属公司 获取该员工对应的分公司的名称
        Integer companyId = zzjg_personnelService.selectCompanyIdByuserId(id);
        //Integer companyId = zzjg_personnelService.selectCompanyIdByuserId(847);

        // 获取这家公司对应的所有的部门
        List <ZzjgDepartment> list = zzig_departmentService.selectDepartmentByCid(companyId);
        if (null != list){
            result.setStatus("1");
            result.setMessage("查询成功");
            result.setData(list);
        }

        System.out.println(companyId);
        return result;

    }

    /**
     * 通过点击的部门查询对应的岗位和对应的风险点 => 可以使用map集合
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value="A201", method = RequestMethod.POST )
    public AppResult checkFengXian(  @RequestBody List<Integer> ids ){

        AppResult result = new AppResultImpl();
        result.setStatus("2");
        result.setMessage("查询失败");

        if(null == ids){
            return result;
        }
        // 表示ids 不为空,然后在service层进行判断,查询响应的岗位


        return result;
    }

}
