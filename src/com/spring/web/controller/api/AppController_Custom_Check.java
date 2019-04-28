package com.spring.web.controller.api;


import com.spring.web.model.ZzjgDepartment;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;

import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.CheckCompany.Zzig_departmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 桃红梨白
 * TODO 发起检查功能
 * 状态码类型: 0 成功  1 失败
 * 保留方法:
 *  查询员工信息 private Zzjg_PersonnelService zzjg_personnelService;
 *
 */
@Controller
@RequestMapping(value = "api/custom/check")
public class AppController_Custom_Check  {

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
     * 根据uuid查询pageContext 并进行数据的查询
     * @param request
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A200", method = RequestMethod.POST)
    public AppResult checkCompany(HttpServletRequest request,  String token) {

        AppResult result = new AppResultImpl();

        if (token == null) {
            result.setStatus("1");
            result.setMessage("查询失败");
            return result;
        }

        ZzjgPersonnel zzjg = (ZzjgPersonnel) request.getSession().getServletContext().getAttribute(token);

        if (null == zzjg || !"1".equals(zzjg.getStatus())) {
            result.setStatus("1");
            result.setMessage("还未登陆,请重新登陆");
            return result;
        }

        // 使用总公司 获取这家公司对应的所有的部门
        List<ZzjgDepartment> list = zzig_departmentService.selectDepartmentByCid(zzjg.getCid());

        // 对数据进行判断是否存在
        if (null == list || list.size() == 0) {
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        }

        // 获取所有的部门 使用list集合
        List<String> names = new ArrayList<>();

        for (ZzjgDepartment zzjgDepartment : list) {
            names.add(zzjgDepartment.getName());
        }

        // 根据公司id 和部门获取所有的岗位并进行数据你对比添加
        Map<String, List> stringListMap = checkManual.selectDangerAndManual(zzjg.getUid(), names);

        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(stringListMap);

        return result;
    }

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
    @RequestMapping(value = "A202", method = RequestMethod.POST/*,
            headers = {"Content-type: application/json"}*/)
    public AppResult checkLevel3(HttpServletRequest request, /*@RequestBody*/ CheckLevel checkLevel) {

        AppResult result = new AppResultImpl();
        if (checkLevel == null) {
            result.setStatus("3");
            result.setMessage("请进行选择部门及其岗位");
            return result;
        }

        // 调用方法进行查询
        List<CheckLevel> list = checkManual.selectLevel4AndId(checkLevel);

        if(null == list || list.size()==0){
            result.setStatus("1");
            result.setMessage("未查询到数据");
            return result;
        }

        result.setStatus("0");
        result.setMessage("查询成功");

        //result.setData(list);
        Set<String> set = new HashSet<>();
        for (CheckLevel level : list) {

            set.add(level.level3);
        }
        result.setData(set);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "A203", method = RequestMethod.POST/*,
            headers = {"Content-type: application/json"}*/)
    public AppResult checkLevel4(HttpServletRequest request, /*@RequestBody*/ CheckLevel checkLevel) {
        AppResult result = new AppResultImpl();
        if (checkLevel == null) {
            result.setStatus("1");
            result.setMessage("请进行选择风险点");
            return result;
        }

        // 调用方法进行查询
        List<CheckLevel> list = checkManual.selectLevel5AndId(checkLevel);
        if (null== list || list.size()==0){
            result.setStatus("1");
            result.setMessage("未查询到数据");
            return result;
        }

        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);
        return result;
    }

    /**
     * 传选择参数,然后进行保存,
     * 先保存模版, 然后保存其他的数据
     * 检查类型 => 日常 周  还是 其他的
     * 会传递一个数据结构就是传递的就是一个list集合的形式,和这个token 从这个
     * @return
     */
    @ResponseBody
    @RequestMapping(value="A204",method=RequestMethod.POST)
    public AppResult saveCheck(HttpServletRequest request, CheckItem checkItem){
        AppResult result = new AppResultImpl();

        if(null==checkItem){
            result.setStatus("1");
            result.setMessage("数据格式错误,请从新输入");
            return result;
        }

        ZzjgPersonnel zzjg = (ZzjgPersonnel) request.getSession().getServletContext().getAttribute(checkItem.token);
        if (null == zzjg || !"1".equals(zzjg.getStatus())) {
            result.setStatus("1");
            result.setMessage("还未登陆,请重新登陆");
            return result;
        }

        Integer checkId = checkManual.saveCheck(checkItem,zzjg);

        return result;

    }

}
