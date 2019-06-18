/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller;

import com.spring.web.BaseController;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.User;
import com.spring.web.model.ZzjgCompany;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.trouble.TroubleService;
import com.spring.web.service.zzjgCompany.IzzjgCompanyService;
import com.spring.web.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Title: CompanyController_cd
 * @Description: TODO(公司内部系统)
 * @author CGF
 * @date 2017年7月27日 上午9:49:51
 * @version V1.0
 */
@Controller
@RequestMapping("company/system")
@SuppressWarnings("all")
public class CompanyController_system extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(连续版本uid)
     */
    private static final long serialVersionUID = 6784473257577263720L;
    /**
     * TODO 故障表没有使用
     */
    @Autowired
    private TroubleService troubleService;
    /**
     *
     */
    @Autowired
    private CgfService cgfService;

    //部门人员接口
    @Autowired
    private IzzjgCompanyService zzjgCompanyService;


    /** 组织架构 开始 */

    /**
     * 组织架构
     * TODO 进行根据登陆的信息进行公司查询 然后将数据返回给jsp页面
     * TODO  zzjg_company_tbl : 公司表,显示的是公司的基本信息 id 公司名称 是否删除
     *
     * Integer type: 本身就是 null ,根本就没有传递这个值
     */
    @RequestMapping(value = "department-list")
    public String riskList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("list", zzjgCompanyMapper.selectAll(user.getId()));
        return "company/system/department-list";
    }
    
    /**
     * 组织架构-部门
     * TODO 查询部门
     *  包括部门的创建时间,是否删除,部门号,公司编号
     */
    @RequestMapping(value = "department-list-bumen")
    public String departmentListBumen(Model model , ZzjgDepartment zzjgDepartment) throws Exception {
        model.addAttribute("listbm", zzjgDepartmentMapper.selectByCompanyId(zzjgDepartment));
        // TODO 为什么要返回这个页面呢
        return "company/system/department-listLoad1";
    }
    
    /**
     * 组织架构-班组
     */
    @RequestMapping(value = "department-list-banzu")
    public String departmentListBanzu(Model model,ZzjgDepartment zzjgDepartment) throws Exception {
        model.addAttribute("listbz", zzjgDepartmentMapper.selectByCompanyIds(zzjgDepartment));
        return "company/system/department-listLoad2";
    }
    
    @RequestMapping(value = "company-save")
    public @ResponseBody Result ComapnySave(ZzjgCompany dto, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        Date d = new Date();
        dto.setUtime(d);
        dto.setUid(getLoginUser(request).getId());
        if(null == dto.getId()) {
            dto.setCtime(d);
            dto.setDel(0);
            zzjgCompanyMapper.insertSelective(dto);
            
            result.setMap("obj", dto);
        } else {
            zzjgCompanyMapper.updateByPrimaryKeySelective(dto);
        }
        return result;
    }

    @RequestMapping(value = "depart-save")
    public @ResponseBody Result DepartSave(ZzjgDepartment dto, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        Date d = new Date();
        dto.setUtime(d);
        dto.setUid(getLoginUser(request).getId());
        if(null == dto.getId()) {
            dto.setCtime(d);
            dto.setDel(0);
            dto.setFlag(1);
            zzjgDepartmentMapper.insertSelective(dto);
            
            result.setMap("obj", dto);
        } else {
            zzjgDepartmentMapper.updateByPrimaryKeySelective(dto);
        }
        return result;
    }
    

    /**
     *  查询部门人员方法
     *  TODO 第一次修改
     * @param cid 企业编号
     * @param freeze 0 正常 1冻结
     * @param dpid 部门编号
     * @param did 班组编号
     * @param fullName 名称
     */
    @RequestMapping(value = "user-list")
    public String userList(Model model, Integer cid, HttpServletRequest request, Integer dpid, Integer did, String name)
            throws Exception {
        User user = getLoginUser(request);

        LlHashMap<Object, Object> m = getLlMap();
        m.put("cid", cid);
        m.put("uid", user.getId());
        m.put("name", name);
        m.put("dpid", dpid);
        m.put("did", did);

        //List<LlHashMap<Object, Object>> userList = zzjgPersonnelMapper.selectByMap(m);  TODO 1
        List<LlHashMap<Object, Object>> userList=zzjgCompanyService.selectByMap(m);

        model.addAttribute("list", userList);
        System.out.println(userList);
        model.addAttribute("m", m);
        //model.addAttribute("companyL", zzjgCompanyMapper.selectAll(user.getId()));    TODO 2
        model.addAttribute("companyL", zzjgCompanyService.selectAll(user.getId()));
        ZzjgDepartment dep = new ZzjgDepartment();
        dep.setUid(user.getId());
        dep.setLevel(1);

        //model.addAttribute("dep1", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 一级部门  TODO 根据公司名称查询部门
        model.addAttribute("dep1", gson.toJson(zzjgCompanyService.selectByCompanyId(dep)));// 一级部门
        dep.setLevel(2);
        //model.addAttribute("dep2", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 二级部门   TODO 查询二级部门
        model.addAttribute("dep2", gson.toJson(zzjgCompanyService.selectByCompanyId(dep)));
        return "company/system/user-list";
    }
    

    /**
     * 部门人员
     */
    @RequestMapping(value = "user-add")
    public String userList(Model model, Integer id, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        if (null != id) {
            ZzjgPersonnel p = zzjgPersonnelMapper.selectByPrimaryKey(id);

            model.addAttribute("user", p);
        }
        model.addAttribute("companyL", zzjgCompanyMapper.selectAll(user.getId()));
        ZzjgDepartment dep = new ZzjgDepartment();
        dep.setLevel(1);
        model.addAttribute("dep1", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 一级部门
        dep.setLevel(2);
        model.addAttribute("dep2", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 二级部门
        return "company/system/user-add";
    }

    /**
     * @param dto
     * @return 员工保存 当没有数据的时候,也要进行存储
     * @throws Exception
     */
    @RequestMapping(value = "user-save")
    public @ResponseBody Result userSave(ZzjgPersonnel dto, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        Date d = new Date();
        dto.setUtime(d);
        // 对前端页面进行判断
        if(dto.getStatus()==null){
            dto.setStatus("0");
        }
        dto.setUid(user.getId());
        // TODO 调用工具类生成密码 微信小程序端进行用户登陆密码请勿删除

        if (dto.getPassword() != null){
            if(dto.getPassword().length()<30){
                String encryptedPwd = EncryptUtil.encrypt(dto.getPassword());
                dto.setPassword(encryptedPwd);
            }
        }

        dto.setCtime(d);
        dto.setDel(0);

        if (null == dto.getId()) {
            zzjgPersonnelMapper.insertSelective(dto);
        } else {
            zzjgPersonnelMapper.updateByPrimaryKeySelective(dto);

        }
        return result;
    }

    /**
     * 员工删除
     */
    @RequestMapping(value = "user-del")
    public
    @ResponseBody Result userDel(Integer id, Integer del ){
        Result result = new ResultImpl();
        ZzjgPersonnel zzjgPersonnel = zzjgPersonnelMapper.selectByPrimaryKey(id);
        zzjgPersonnel.setDel(del);
        zzjgPersonnelMapper.updateByPrimaryKey(zzjgPersonnel);
        return result;
    }

    /** 组织架构  结束 */
}
