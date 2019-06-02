/**  
 * Copyright(c)2017 Wuxi tongxiang Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jfree.ui.Layer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.BaseController;
import com.spring.web.model.AFxgzp;
import com.spring.web.model.AGwyjSh;
import com.spring.web.model.Company;
import com.spring.web.model.LightningProtection;
import com.spring.web.model.Mequipment;
import com.spring.web.model.Regulation;
import com.spring.web.model.SafetyFacilities;
import com.spring.web.model.Sequipment;
import com.spring.web.model.User;
import com.spring.web.model.YzHighTemperature;
import com.spring.web.model.YzLiquidAmmonia;
import com.spring.web.model.YzMajorEquipment;
import com.spring.web.model.YzMetallurgy;
import com.spring.web.model.YzSafetyFacilities;
import com.spring.web.model.YzSpecialEquipment;
import com.spring.web.model.YzStaff;
import com.spring.web.model.YzStiveExplosion;
import com.spring.web.model.YzTradeArder;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.trouble.TroubleService;
import com.spring.web.tobject.cgf.ModelSaveReqDTO;
import com.spring.web.util.DateConvertUtil;

/**
 * @Title: CompanyController_cd
 * @Description: 重大危险源源长制
 * @author hjy
 * @date 2018年12月28日 下午13:43:13
 * @version V1.0
 */
@Controller
@RequestMapping("company/source")
public class CompanyController_source extends BaseController {
    /**
     * @Fields serialVersionUID : 
     */
    private static final long serialVersionUID = 6784473257577263720L;
    @Autowired
    private TroubleService troubleService;
    @Autowired
    private CgfService cgfService;

    public @ResponseBody Result modelSave(@RequestBody ModelSaveReqDTO dto, HttpServletRequest request)
            throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        dto.getModel().setUserId(user.getId());
        cgfService.modelSave(dto, result);
        result.setMap("modelId", dto.getModel().getId());
        return result;
    }

    /**
     * 判断重大危险源源长制 是否显示
     */
    @RequestMapping(value = "check")
    public @ResponseBody Result check(HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Regulation company = regulationMapper.selectByUserId(user.getId());
        result.setMap("flag",company.getCisDanger());

        return result;
    }
    /**
     * 管控机制——管控架构
     */
    @RequestMapping(value = "management-guide")
    public String managementGuide(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);

        return "company/source/management-guide";
    }
    /**
     * 管控措施——设备设施管理
     */
    @RequestMapping("equipment")
    public String mainEquipmentList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("remark", "重大危险源");
        boolean flag = false;
        if(type == 1){//主要设备
            List<Mequipment> list1 = mequipmentMapper.selectByUserId(m);
            List<YzMajorEquipment> list2 = yzMajorEquipmentMapper.selectByUserId(m);
            List<YzMajorEquipment> showList = new ArrayList<YzMajorEquipment>();
            int id1,id2;
            for(int i = 0;i < list1.size();i ++){
                id1 = list1.get(i).getId();
                for(int j = 0;j < list2.size();j ++){
                    id2 = list2.get(j).getId();
                    if(id1 == id2){
                        showList.add(list2.get(j));
                        flag = true;
                        break;
                    }
                }
                if(flag == false){
                    YzMajorEquipment e = new YzMajorEquipment();
                    e.setId(id1);
                    e.setName(list1.get(i).getEquipmentName()+list1.get(i).getSize());
                    e.setUserid(user.getId());
                    yzMajorEquipmentMapper.insertSelective(e);
                    showList.add(e);
                }
                flag = false;
            }
            list2.removeAll(showList);
            for(int k = 0; k < list2.size(); k++){
            	yzMajorEquipmentMapper.deleteByPrimaryKey(list2.get(k).getId());
            }
            model.addAttribute("showList", showList);
        }else if(type == 2){//特种设备
            List<Sequipment> list1 = sequipmentMapper.selectByUserId(m);
            List<YzSpecialEquipment> list2 = yzSpecialEquipmentMapper.selectByUserId(m);
            List<YzSpecialEquipment> showList = new ArrayList<YzSpecialEquipment>();
            int id1,id2;
            for(int i = 0;i < list1.size();i ++){
                id1 = list1.get(i).getId();
                for(int j = 0;j < list2.size();j ++){
                    id2 = list2.get(j).getId();

                    if(id1 == id2){
                        showList.add(list2.get(j));
                        
                        flag = true;
                        break;
                    }
                }
                if(flag == false){
                    YzSpecialEquipment e = new YzSpecialEquipment();
                    e.setId(id1);
                    e.setName(list1.get(i).getSeName());
                    e.setDetectiontime(list1.get(i).getDetectionTime()+list1.get(i).getType());
                    e.setUserid(user.getId());
                    yzSpecialEquipmentMapper.insertSelective(e);
                    showList.add(e);
                }
                flag = false;
            }
            list2.removeAll(showList);
            for(int k = 0; k < list2.size(); k++){
            	yzSpecialEquipmentMapper.deleteByPrimaryKey(list2.get(k).getId());
            }
            model.addAttribute("showList", showList);
        }else if(type == 3){//安全设施
            List<SafetyFacilities> list1 = safetyFacilitiesMapper.selectByUserId(m);
            List<Map<String, Object>>list3 = lightningProtectionMapper.selectList(m);
            List<YzSafetyFacilities> list2 = yzSafetyFacilitiesMapper.selectByUserId(m);
            List<YzSafetyFacilities> showList = new ArrayList<YzSafetyFacilities>();
            int id1,id2,id3;
            for(int i = 0;i < list1.size();i ++){
                id1 = list1.get(i).getId();
                for(int j = 0;j < list2.size();j ++){
                    id2 = list2.get(j).getId();
                    if(id1 == id2){
                        showList.add(list2.get(j));
                        flag = true;
                        break;
                    }
                }
                if(flag == false){
                    YzSafetyFacilities e = new YzSafetyFacilities();
                    e.setId(id1);
                    e.setName(list1.get(i).getName()+list1.get(i).getSize());
                    e.setUserid(user.getId());
                    yzSafetyFacilitiesMapper.insertSelective(e);
                    showList.add(e);
                }
                flag = false;
            }
            
            for(int i = 0;i < list1.size();i ++){
                id3 = Integer.parseInt(list3.get(i).get("id").toString());
                for(int j = 0;j < list2.size();j ++){
                    id2 = list2.get(j).getId();
                    if(id3 == id2){
                        showList.add(list2.get(j));
                        flag = true;
                        break;
                    }
                }
                if(flag == false){
                    YzSafetyFacilities e = new YzSafetyFacilities();
                    e.setId(id3);
                    e.setName(list3.get(i).get("project").toString());
                    e.setUserid(user.getId());
                    yzSafetyFacilitiesMapper.insertSelective(e);
                    showList.add(e);
                }
                flag = false;
            }
            list2.removeAll(showList);
            for(int k = 0; k < list2.size(); k++){
            	yzSafetyFacilitiesMapper.deleteByPrimaryKey(list2.get(k).getId());
            }
            model.addAttribute("showList", showList);
        }
        model.addAttribute("type",type);
        return "company/source/equipment";
    }
    /**
     * 管控措施——设备设施管理——查看/编辑
     */
    @RequestMapping(value = "equipment-edit")
    public String equipEdit(@RequestParam("id") int id, Model model, int type) throws Exception {
        model.addAttribute("dpnames",yzStaffMapper.selectDpname());
        model.addAttribute("staffs",gson.toJson(yzStaffMapper.selectByDel(0)));
        if(type == 1){
            model.addAttribute("editEquip", yzMajorEquipmentMapper.selectByPrimaryKey(id));
        }else if(type == 2){
            model.addAttribute("editEquip", yzSpecialEquipmentMapper.selectByPrimaryKey(id));
        }else if(type == 3){
            model.addAttribute("editEquip", yzSafetyFacilitiesMapper.selectByPrimaryKey(id));
        }
        model.addAttribute("type",type);
        return "company/source/equipment-edit";
    }
    /**
     * 设备添加——主要设备编辑保存
     */
    @RequestMapping(value = "equipment-save1")
    public @ResponseBody Result equipSave(YzMajorEquipment majorEquip, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        majorEquip.setUserid(user.getId());
        yzMajorEquipmentMapper.updateByPrimaryKeySelective(majorEquip);

        return result;
    }
    /**
     * 设备添加——特种设备编辑保存
     */
    @RequestMapping(value = "equipment-save2")
    public @ResponseBody Result equipSave(YzSpecialEquipment specialEquip, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        specialEquip.setUserid(user.getId());
        yzSpecialEquipmentMapper.updateByPrimaryKeySelective(specialEquip);

        return result;
    }
    /**
     * 设备添加——安全设施编辑保存
     */
    @RequestMapping(value = "equipment-save3")
    public @ResponseBody Result equipSave(YzSafetyFacilities safetyFacilities, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        safetyFacilities.setUserid(user.getId());
        yzSafetyFacilitiesMapper.updateByPrimaryKeySelective(safetyFacilities);

        return result;
    }

    /**
     * 管控措施——设备设施管理——删除设备
     */
    // @RequestMapping(value = "deleteEquipment")
    // public @ResponseBody Result deleteEquipment(HttpServletRequest request, Integer id, int type) throws Exception {
    //     Result result = new ResultImpl();
    //     if(type == 1){
    //         yzMajorEquipmentMapper.deleteByPrimaryKey(id);
    //     }
        
    //     return result;
    // }

    /**
     * 管控措施——人员管理
     */
    @RequestMapping(value = "staff")
    public String userList(Model model, HttpServletRequest request)
            throws Exception {
        User user = getLoginUser(request);
        List<YzStaff> userList = yzStaffMapper.selectByDel(0);
        model.addAttribute("list", userList);
        model.addAttribute("companyL", zzjgCompanyMapper.selectAll(user.getId()));

        ZzjgDepartment dep = new ZzjgDepartment();
        dep.setUid(user.getId());
        dep.setLevel(1);
        model.addAttribute("dep1", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 一级部门
        dep.setLevel(2);
        model.addAttribute("dep2", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 二级部门
        return "company/source/staff";
    }
    /**
     * 管控措施——人员管理——添加人员
     */
    @RequestMapping(value = "user-add")
    public String userList(Model model, Integer id, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        if (null != id) {
            YzStaff p = yzStaffMapper.selectByPrimaryKey(id);
            model.addAttribute("editUser", p);
        }
        model.addAttribute("companyL", zzjgCompanyMapper.selectAll(user.getId()));
        ZzjgDepartment dep = new ZzjgDepartment();
        dep.setLevel(1);
        model.addAttribute("dep1", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 一级部门
        dep.setLevel(2);
        model.addAttribute("dep2", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 二级部门
        return "company/source/user-add";
    }
    /**
     * 管控措施——人员管理——添加人员——保存
     */
    @RequestMapping(value = "user-save")
    public @ResponseBody Result userSave(YzStaff staff, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        Date d = new Date();
        staff.setUtime(d);
        staff.setUid(user.getId());
        staff.setDpname(zzjgDepartmentMapper.selectByPrimaryKey(staff.getDpid()).getName());
        if (null == staff.getId()) {
            staff.setCtime(d);
            staff.setDel(0);
            yzStaffMapper.insertSelective(staff);
        } else {
            yzStaffMapper.updateByPrimaryKeySelective(staff);
        }
        return result;
    }
    /**
     * 管控措施——在线监测
     */
    @RequestMapping(value = "source-video")
    public String sourceVideo(HttpServletRequest request,Integer before,Integer flag, Model model,Integer userId,String name) throws Exception{
    	Company company;
    	if(userId == null){
    		User user = getLoginUser(request);
    		company = companyMapper.selectByPrimaryKey(user.getId());
    		userId = user.getId();
    	}else{
    		company = companyMapper.selectByPrimaryKey(userId);
    	}
    	model.addAttribute("company", company);
    	model.addAttribute("flag",flag);
    	model.addAttribute("userId",userId);
    	
    	
    	if(flag==1){
    		//User user = getLoginUser(request);
            Map<String, Object> m = new HashMap<String, Object>();
            //setUserId(user, m);
            //m.put("companyName", company.getName());
            m.put("userId", userId);
            model.addAttribute("list", monitorMapper.selectByMap(m));
            //if (user.getUserType() == 5) {
            //    return "company/system/monitor-list";
            //}
            //model.addAttribute("companyName", companyName);
            //return "village/system/monitor-list";
    	}
    	
    	if(flag==3){
    		Map<String, Object> m = new HashMap<String, Object>();
            name = URLDecoder.decode(name,"UTF-8");
        	m.put("name", name);
        	m.put("userId", userId);
//        	log.error("monitorList name1 : "+name.toString());
//        	log.error("monitorList before : "+before);
        	List<Map<String, Object>> list = monitorMapper.selectByMap(m);
        	
        	for (Map<String, Object> map : list) {
            	model.addAttribute("monitor", map);
//            	log.error("monitorList name2: "+map.get("name"));
            	break;
            }
        	
        	model.addAttribute("name", name);
        	model.addAttribute("before", before);
    	}
    	
//    	if(flag==2){//显示缩略图,如果还是要显示视频就把这段去掉//zhangcl 2019.1.13
//    		Map<String, Object> m = new HashMap<String, Object>();
//            m.put("userId", userId);
//            model.addAttribute("list", monitorMapper.selectByMap(m));
//    		return "company/system/video-monitor-img";
//    	}
    	
        return "company/source/source-video";
    }
    /**
     * 管控措施——风险分布图
     */
    @RequestMapping(value = "distribution")
    public String controlList2(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("order", 1);

        model.addAttribute("company", company);
        model.addAttribute("user", userMapper.selectByPrimaryKey(company.getUserId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(company.getVillageId()));
        log.error("m:"+m.toString());
        Regulation r = regulationMapper.selectByUserId(user.getId());
        model.addAttribute("regulation", r);

        //log.error("11");

        return "company/source/distribution";
    }

    /**
     * 管控措施——风险分布图,标示位置
     */
    @RequestMapping(value = "control-list3")
    public @ResponseBody Result controlList3(Model model, HttpServletRequest request, Integer id, String dangerMaterial) throws Exception {
        //log.error("zhangcl 2018.10.13 controlList3,id="+id+",lnglat="+lnglat);
        Result result = new ResultImpl();
        //User user = getLoginUser(request);
        //int userid = user.getId();
        Regulation r = new Regulation();
        //r.setUserId(userid);
        r.setId(id);
        r.setDangerMaterial(dangerMaterial);
        regulationMapper.updateByPrimaryKeySelective(r);
        //log.error(id+","+dangerMaterial);

        return result;
    }
    /**
     * 管控措施——公示
     */
    @RequestMapping(value = "publicity")
    public String publicity(Model model, HttpServletRequest request, Integer flag) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI() + "flag=" + flag);
            return "company/safety-system/type";
        }
        
        //所有风险
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("levels", new String[]{"红色","橙色","黄色","蓝色"});
        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
        //所有重大较大风险
        List<Map<String, Object>> acL_f = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> ac : acL) {
            String level = getStringFromMap(ac, "level");
            if(level.equals("红色") || level.equals("橙色")) {
                acL_f.add(ac);
            }
        }
        
        if(acL_f.size() == 0) {//没有重大较大隐患
            return "company/source/publicity_0";
        }
        
        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> shList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();
        
        AFxgzp ggp = aFxgzpMapper.selectFlag1(user.getId());
        if(null == ggp || ggp.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
            if(null == ggp) {
                ggp = new AFxgzp();
            }
            ggp.setFlag(1);
            ggp.setUid(user.getId());
            ggp.setGlzrr(company.getLegal());//责任人默认为法人
            ggp.setCtime(new Date());
            ggp.setDel(0);
            ggp.setIsedit(0);
            ggp.setName(company.getName());
            List<AGwyjSh> shL = setGgpXx(acL_f, ggp, shList);
            
            //公司的应急处置措施按照危险来
            StringBuilder yjczcs = new StringBuilder();
            for(AGwyjSh s : shL) {
                yjczcs.append("<b>").append(s.getName()).append("：</b>").append(s.getMeasures()).append("<br>");
            }
            ggp.setYjczcs(yjczcs.toString());
            if(null == ggp.getId()) {
                aFxgzpMapper.insertSelective(ggp);
            } else {
                aFxgzpMapper.updateByPrimaryKeySelective(ggp);
            }
            
        }
        model.addAttribute("be", ggp);
        
        Map<String, Object> map = new HashMap<String, Object>();
        setUserId(user, map);
        map.put("remark", "重大危险源");
        List<Mequipment> list1 = mequipmentMapper.selectByUserId(map);
        List<Sequipment> list2 = sequipmentMapper.selectByUserId(map);
        List<SafetyFacilities> list3 = safetyFacilitiesMapper.selectByUserId(map);
        StringBuilder equipList = new StringBuilder();
        for(Mequipment me:list1){
            equipList.append(me.getEquipmentName());
            equipList.append("、");
        }
        for(Sequipment se:list2){
            equipList.append(se.getSeName());
            equipList.append("、");
        }
        for(SafetyFacilities sf:list3){
            equipList.append(sf.getName());
            equipList.append("、");
        }if(equipList.length() > 0){
            equipList.deleteCharAt(equipList.length()-1);
        }else{
            equipList.append("无");
        }
        model.addAttribute("equipList", equipList);
        return "company/source/publicity";
    }
//  设置风险等级，事故类型，风险因素，措施
    List<AGwyjSh> setGgpXx(List<Map<String, Object>> acL, AFxgzp ggp, List<AGwyjSh> shList) {
        StringBuilder fxdj = new StringBuilder();//风险等级
        Set<String> sglx = new HashSet<String>();//事故类型
        List<AGwyjSh> shL = new LinkedList<AGwyjSh>(); 
        Set<String> fxys = new HashSet<String>();//风险因素
        Set<String> fxys_zd = new HashSet<String>();//重大风险因素
        Set<String> ffcs = new HashSet<String>();//防范措施
        for(Map<String, Object> ac : acL) {
            //风险等级
            String level = getStringFromMap(ac, "level"); 
            if(fxdj.toString().indexOf(level.replace("色", "")) == -1) {
                if(!"".equals(fxdj.toString())) {
                    fxdj.append(",");
                }
                fxdj.append(level.replace("色", ""));
            }
            
            //事故类型
            String type = getStringFromMap(ac, "type");
            if(StringUtils.isNotBlank(type)) {
                for(AGwyjSh e : shList) {
                    if(type.indexOf(e.getName()) > -1) {
                        if(sglx.add(e.getName())) {
                            shL.add(e);
                        };
                    }
                }
            }
            
            //风险因素
            String factors = getStringFromMap(ac, "factors");
            if(StringUtils.isNotBlank(factors)) {
                if(level.equals("红色")) {
                    fxys_zd.add(factors);
                } else {
                    fxys.add(factors);
                }
            }
            
            //措施
            String measures = getStringFromMap(ac, "measures");
            if(StringUtils.isNotBlank(measures)) {
                ffcs.add(measures);
            }
        }
        
        ggp.setFxdj(fxdj.toString());//风险等级
        if(sglx.size() > 0) {//事故类型
            ggp.setSglx(StringUtils.join(sglx, "、") + "等");
        }
        ggp.setFxys(StringUtils.join(fxys, "\n"));
        ggp.setFxysZd(StringUtils.join(fxys_zd, "\n"));
        ggp.setYjczcs(StringUtils.join(ffcs, "\n"));
        return shL;
    }
    String getStringFromMap(Map<String, Object> m, String key) {
        Object o = m.get(key);
        if(null != o) {
            return o.toString();
        }
        return null;
    }
    
    
    /**
     * 隐患排查治理——每日检查表
     */
    @RequestMapping(value = "model-list-cx")
    public String modelList1(String title, HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", 2);//定期
        m.put("title", "重大危险源每日检查表");
        //m.put("title", "");
        m.put("flag", 1);//自查
        setUserId(user, m);
        model.addAttribute("list", tModelMapper.selectByMap(m));

        model.addAttribute("type", 2);
        model.addAttribute("flag", 1);
        return "company/source/model-list-cx";
    }

    /**
     * 隐患排查治理——隐患治理
     */
    @RequestMapping(value = "check-list")
    public String troubleList1(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        String title = "重大危险源每日检查表";
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", 1);
        m.put("title", title);
        if(setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            model.addAttribute("list", list);
        }
        model.addAttribute("type", 3);//整改复查
        model.addAttribute("flag", 1);
        model.addAttribute("title", title);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        return "company/source/model-list-cx";
        //        if (user.getUserType() == 5) {//企业用户
        //            return "company/danger/model-list-cx";
        //        }
        //        return "village/danger/check-list";
    }
    /**
     * 特殊作业
     */
    @RequestMapping(value = "specialWork")
    public String liquidAmmonia(int type, HttpServletRequest request) throws Exception {
    	User user = getLoginUser(request);
    	Company company = companyMapper.selectByPrimaryKey(user.getId());
    	String highRisk = company.getDangers();
        if(type == 1 && highRisk.contains("非化工液氨使用企业")){
            return "company/source/tliquid-ammonia";
        }else if(type == 2 && highRisk.contains("粉尘涉爆企业")){
            return "company/source/tstive-explosion";
        }else if(type == 3 && highRisk.contains("高温熔融金属现场运输")){
            return "company/source/thigh-temperature";
        }else if(type == 4 && highRisk.contains("工贸行业有限空间安全作业")){
            return "company/source/ttrade-arder";
        }else if(type == 5 && highRisk.contains("冶金企业煤气")){
            return "company/source/tmetallurgy";
        }else{
            return "company/source/specialwork-unselected";
        }
    }
    /**
     * 特殊作业——非化工液氨使用企业 保存
     */
    @RequestMapping(value = "tliquid-ammonia-save")
    public @ResponseBody Result tliquidSave(HttpServletRequest request, YzLiquidAmmonia la) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        la.setUid(user.getId());
        if (null == la.getId()) {
            la.setDel(0);
            la.setCtime(new Date());
            yzLiquidAmmoniaMapper.insertSelective(la);

        } else {
            yzLiquidAmmoniaMapper.updateByPrimaryKeySelective(la);
        }

        return result;
    }
    //    特殊作业——非化工液氨使用企业 保存(限制del属性)
    //    @RequestMapping(value = "tliquid-ammonia-save")
    //    public @ResponseBody Result tliquidSave(HttpServletRequest request, YzLiquidAmmonia la) throws Exception {
    //        Result result = new ResultImpl();
    //        User user = getLoginUser(request);
    //        la.setUid(user.getId());
    //        la.setDel(0);
    //        la.setCtime(new Date()); 
    //        
    //        try {
    //          YzLiquidAmmonia laDel = yzLiquidAmmoniaMapper.selectByUid(user.getId());
    //          if (laDel.getId() != null) {
    //              laDel.setDel(1);
    //              yzLiquidAmmoniaMapper.updateByPrimaryKeySelective(laDel);
    //          }
    //          yzLiquidAmmoniaMapper.insertSelective(la);
    //        } catch (Exception e) {
    //          log.error("保存失败");
    //          e.printStackTrace();
    //          result.setStatus("1");
    //          result.setMess("保存失败");
    //          return result;
    //      }
    //      
    //        return result;
    //    }
    /**
     * 特殊作业——粉尘涉爆企业 保存
     */
    @RequestMapping(value = "tstive-explosion-save")
    public @ResponseBody Result stiveSave(HttpServletRequest request, YzStiveExplosion se) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        se.setUid(user.getId());
        if (null == se.getId()) {
            se.setDel(0);
            se.setCtime(new Date());
            yzStiveExplosionMapper.insertSelective(se);

        } else {
            yzStiveExplosionMapper.updateByPrimaryKeySelective(se);
        }
        return result;
    }
    /**
     * 特殊作业——高温熔融金属现场运输 保存
     */
    @RequestMapping(value = "thigh-temperature-save")
    public @ResponseBody Result thighSave(HttpServletRequest request, YzHighTemperature ht) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ht.setUid(user.getId());
        if (null == ht.getId()) {
            ht.setDel(0);
            ht.setCtime(new Date());
            yzHighTemperatureMapper.insertSelective(ht);

        } else {
            yzHighTemperatureMapper.updateByPrimaryKeySelective(ht);
        }
        return result;
    }
    /**
     * 特殊作业——工贸行业有限空间安全作业 保存
     */
    @RequestMapping(value = "ttrade-arder-save")
    public @ResponseBody Result tradeSave(HttpServletRequest request, YzTradeArder ta) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ta.setUid(user.getId());
        if (null == ta.getId()) {
            ta.setDel(0);
            ta.setCtime(new Date());
            yzTradeArderMapper.insertSelective(ta);

        } else {
            yzTradeArderMapper.updateByPrimaryKeySelective(ta);
        }
        return result;
    }
    /**
     * 特殊作业——冶金企业煤气 保存
     */
    @RequestMapping(value = "tmetallurgy-save")
    public @ResponseBody Result metallurgySave(HttpServletRequest request, YzMetallurgy me) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        me.setUid(user.getId());
        if (null == me.getId()) {
            me.setDel(0);
            me.setCtime(new Date());
            yzMetallurgyMapper.insertSelective(me);

        } else {
            yzMetallurgyMapper.updateByPrimaryKeySelective(me);
        }
        return result;
    }
    /**
     * 应急救援——已存在
     */
    /**
     * 档案管理
     */
    @RequestMapping(value = "files-select")
    public String filesList(HttpServletRequest request, Model model) throws Exception {
        return "company/source/files-select";
    }
    /**
     * 档案管理——list
     */
    @RequestMapping(value = "files-list")
    public String specialList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("type",type);
        if(type==1){//非化工液氨使用企业
            List<YzLiquidAmmonia> list= yzLiquidAmmoniaMapper.selectByUid(user.getId());
            model.addAttribute("list",list);
        }
        else if(type == 2){//粉尘涉爆企业
            List<YzStiveExplosion> list = yzStiveExplosionMapper.selectByUid(user.getId());
            model.addAttribute("list",list);
        }
        else if(type == 3){//高温熔融金属现场运输
            List<YzHighTemperature> list = yzHighTemperatureMapper.selectByUid(user.getId());
            model.addAttribute("list",list);
        }
        else if(type == 4){//工贸行业有限空间安全作业
            List<YzTradeArder> list = yzTradeArderMapper.selectByUid(user.getId());
            model.addAttribute("list",list);
        }
        else if(type == 5){//冶金企业煤气
            List<YzMetallurgy> list = yzMetallurgyMapper.selectByUid(user.getId());
            model.addAttribute("list",list);
        }
        else{
            return "company/source/files-select";
        } 

        return "company/source/files-list";

    }
    /**
     * 档案管理——详情
     */
    @RequestMapping(value = "files-detail")
    public String specialDetail(HttpServletRequest request,Integer id,Model model,Integer type) throws Exception {
        if(type==1){//非化工液氨使用企业
            YzLiquidAmmonia la = yzLiquidAmmoniaMapper.selectByPrimaryKey(id);
            model.addAttribute("la",la);
            model.addAttribute("action","read");
            return "company/source/tliquid-ammonia";
        }
        else if(type == 2){//粉尘涉爆企业
            YzStiveExplosion se = yzStiveExplosionMapper.selectByPrimaryKey(id);
            model.addAttribute("se",se);
            model.addAttribute("action","read");
            return "company/source/tstive-explosion";
        }
        else if(type == 3){//高温熔融金属现场运输
            YzHighTemperature ht = yzHighTemperatureMapper.selectByPrimaryKey(id);
            model.addAttribute("ht",ht);
            model.addAttribute("action","read");
            return "company/source/thigh-temperature";  
        }
        else if(type == 4){//工贸行业有限空间安全作业
            YzTradeArder ta = yzTradeArderMapper.selectByPrimaryKey(id);
            model.addAttribute("ta",ta);
            model.addAttribute("action","read");
            return "company/source/ttrade-arder";  
        }
        else if(type == 5){//冶金企业煤气
            YzMetallurgy me = yzMetallurgyMapper.selectByPrimaryKey(id);
            model.addAttribute("me",me);
            model.addAttribute("action","read");
            return "company/source/tmetallurgy";  
        }
        else{
            return "company/source/files-select";
        }
    }
}
