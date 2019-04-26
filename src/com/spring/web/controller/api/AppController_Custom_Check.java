package com.spring.web.controller.api;


import com.spring.web.BaseController;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;


import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.CheckCompany.Zzig_departmentService;
import com.spring.web.service.CheckCompany.Zzjg_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 桃红梨白
 * TODO 发起检查功能
 * 状态码类型: 0 成功  1 检查人员  2 安全责任人  3 查询失败
 * 保留方法:
 *  查询员工信息 private Zzjg_PersonnelService zzjg_personnelService;
 *
 */
@Controller
@RequestMapping(value = "api/custom/check")
public class AppController_Custom_Check extends BaseController {

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
     * 根据用户id查询部门及岗位
     * @param request
     * @param id
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A200", method = RequestMethod.POST)
    public AppResult checkCompany(HttpServletRequest request, Integer id, Integer uid) {

        AppResult result = new AppResultImpl();

        if (id == null || uid == null) {
            result.setStatus("3");
            result.setMessage("查询失败");
            return result;
        }

        ZzjgPersonnel zzjg = (ZzjgPersonnel) request.getSession().getAttribute(Integer.toString(id));

        if (null == zzjg || !"1".equals(zzjg.getStatus()) || !uid.equals(zzjg.getUid())) {
            result.setStatus("3");
            result.setMessage("权限不足,或者查询失败");
            return result;
        }

        // 使用分公司 获取这家公司对应的所有的部门
        List<ZzjgDepartment> list = zzig_departmentService.selectDepartmentByCid(zzjg.getCid());

        // 对数据进行判断是否存在
        if (null == list || list.size() == 0) {
            result.setStatus("3");
            result.setMessage("未查询出数据");
            return result;
        }

        // 获取所有的部门 使用list集合
        List<String> names = new ArrayList<>();

        for (ZzjgDepartment zzjgDepartment : list) {
            names.add(zzjgDepartment.getName());
        }

        // 根据公司id 和部门获取所有的岗位并进行数据你对比添加
        Map<String, List> stringListMap = checkManual.selectDangerAndManual(uid, names);

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
//    @ResponseBody
//    @RequestMapping(value="A201", method = RequestMethod.POST )
//    public AppResult checkLevel2(HttpServletRequest request,@RequestBody Department department){
//        AppResult result = new AppResultImpl( );
//        // 对前端请求的参数进行判断
//        if(null ==department){
//            result.setStatus("3");
//            result.setMessage("请进行选择部门及其岗位");
//            return result;
//        }
//        if(null==department.getUid()||null==department.getNames()){
//            result.setStatus("3");
//            result.setMessage("请进行选择部门及其岗位");
//            return result;
//        }
//        List<MeasuresBean> list = checkManual.selectmeasures(department.getUid(),department.getNames());
//
//        result.setStatus("1");
//        result.setMessage("查询成功");
//        result.setData(list);
//
//        return result;
//    }


    /**
     * TODO 根据传递的公司的uid 和 岗位 ,获取对应的level3 检查点
     * 并进行返回level3和对应的id
     * @param request
     * @param
     * @return
     */
   /* @RequestMapping(value = "A202",method=RequestMethod.POST)
    @ResponseBody
    public AppResult checkLevel3(HttpServletRequest request, @RequestBody List<CheckLevel> checkLevel){
        AppResult result =new AppResultImpl();
        if(checkLevel==null || checkLevel.size()==0){
            result.setStatus("3");
            result.setMessage("请进行选择部门及其岗位");
            return result;
        }

        // 调用方法进行查询
        List<CheckLevel> list = checkManual.selectLevel3AndId(checkLevel);
        result.setStatus("1");
        result.setMessage("查询成功");
        result.setData(list);
        return result;

    }*/

    /**
     * 根据部门岗位查询风险点
     * @param request
     * @param checkLevel
     * @return AppResult
     * 保留方法:
     * @RequestParam(value="checkLevel", required=true)
     * headers = {"Content-type: application/json"}
     */
    @ResponseBody
    @RequestMapping(value = "A202", method = RequestMethod.POST)
    public AppResult checkLevel3(HttpServletRequest request, @RequestBody CheckLevel checkLevel) {

        AppResult result = new AppResultImpl();
        if (checkLevel == null) {
            result.setStatus("3");
            result.setMessage("请进行选择部门及其岗位");
            return result;
        }

        // 调用方法进行查询
        List<CheckLevel> list = checkManual.selectLevel4AndId(checkLevel);
        result.setStatus("1");
        result.setMessage("查询成功");
        //result.setData(list);
        return result;

    }

}
