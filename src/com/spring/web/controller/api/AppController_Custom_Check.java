package com.spring.web.controller.api;


import com.spring.web.BaseController;
import com.spring.web.model.ACompanyManual;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.model.request.Department;
import com.spring.web.model.response.MeasuresBean;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;


import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.CheckCompany.Zzig_departmentService;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  桃红梨白
 * TODO 自定义检查模块
 * 1. 根据传递的用户id查询企业
 * 2. 根据企业查询相应的部门
 * 3. 根据部门查询相应的岗位
 * 4. 根据岗位查询响应的风险
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
     * 查询部门
     */
    @Autowired
    private Zzig_departmentService zzig_departmentService;

    /**
     * 查询风险点
     */
    @Autowired
    private ICheckManual checkManual;


    /**
     * 根据用户id 查询 => 公司的Uid 和部门和岗位
     * zppResult 状态  1: 检查人员  2. 被检查人员  3. 表示查询失败
     *
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value="A200", method = RequestMethod.POST )
    public AppResult checkCompany(HttpServletRequest request, Integer id){
        AppResult result = new AppResultImpl();

        if(id == null){
            result.setStatus("3");
            result.setMessage("查询失败");
            return result;
        }

        // 根据用户id查询所属公司 获取该员工对应的分公司的名称
        Integer companyId = zzjg_personnelService.selectCompanyIdByuserId(id);
        //Integer companyId = zzjg_personnelService.selectCompanyIdByuserId(847);

        // 获取这家公司对应的所有的部门
        List <ZzjgDepartment> list = zzig_departmentService.selectDepartmentByCid(companyId);

        if (null == list){
            result.setStatus("3");
            result.setMessage("未查询出数据");
            return result;
        }

        // 获取uid :总公司的id
        Integer uid = list.get(0).getUid();

        // 获取所有的部门 使用list集合
        List<String> names = new ArrayList<>();
        for (ZzjgDepartment zzjgDepartment : list) {
             names.add(zzjgDepartment.getName());
        }
        // 根据公司id 和部门获取所有的岗位并进行数据你对比添加
        checkManual.selectDangerAndManual(uid,names);

        // 获取所有的岗位及其风险点名称
        Map<String, List> stringListMap = checkManual.selectDangerAndManual(uid, names);

        if(null == stringListMap){
            result.setStatus("3");
            result.setMessage("未查询出数据");
            return result;
        }
        result.setStatus("1");
        result.setMessage("查询成功");
        result.setData(stringListMap);

        return result;

    }

    /**
     * 查询level3和measures 进行组装
     * 进行组装的时候,对数据进行覆盖重写
     *  通过岗位名称和id信息获取对应的
     *  cid: 表示的是总公司的id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="A201", method = RequestMethod.POST )
    public AppResult checkLevel2(HttpServletRequest request,@RequestBody Department department){
        AppResult result = new AppResultImpl( );
        // 对前端请求的参数进行判断
        if(null ==department){
            result.setStatus("3");
            result.setMessage("请进行选择部门及其岗位");
            return result;
        }
        if(null==department.getUid()||null==department.getNames()){
            result.setStatus("3");
            result.setMessage("请进行选择部门及其岗位");
            return result;
        }
        List<MeasuresBean> list = checkManual.selectmeasures(department.getUid(),department.getNames());

        result.setStatus("1");
        result.setMessage("查询成功");
        result.setData(list);


        return result;
    }


    /**
     * TODO 根据传递的公司的uid 和 岗位 ,获取对应的level3 检查点
     * 并进行返回level3和对应的id
     * @param request
     * @param departments
     * @return
     */
    @RequestMapping(value = "A202",method=RequestMethod.POST)
    @ResponseBody
    public AppResult checkLevel3(HttpServletRequest request,@RequestBody List<Department> departments){
        AppResult result =new AppResultImpl();
        if( departments.size()==0 || departments==null){
            result.setStatus("3");
            result.setMessage("查询失败");
            return result;
        }

        // 调用方法进行查询





        return result;



    }


}
