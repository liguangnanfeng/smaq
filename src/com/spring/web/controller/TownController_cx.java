/**  
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Library;
import com.spring.web.model.Standard;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.SessionUtil;

/** 
* @Title: TownController_cx 
* @Description: TODO(用一句话描述该文件做什么) 
* @author CX   
* @date 2017年11月2日 上午10:41:51 
* @version V1.0   
*/
@Controller
@RequestMapping("/town/cx")
public class TownController_cx extends BaseController {
    
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = 1L;

//    /**
//     * 各地企业-政府首页-中间
//     */
//    @RequestMapping(value = "wel")
//    public String wel(Model model, CompanyListReqDTO dto, HttpServletRequest request) throws Exception {
//        User user = getLoginUser(request);
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("villageId", dto.getVillageId());
//        m.put("townId", dto.getTownId());
//        m.put("districtId", dto.getDistrictId());
//        
//        model.addAttribute("number", companyMapper.selectIdsByCompany_view_count(dto));//企业数量
//
//        if(null != dto.getVillageId()) {
//            model.addAttribute("name", villageMapper.selectByPrimaryKey(dto.getVillageId()).getName());
//        }
//        
//        if(null != dto.getTownId()) {
//            model.addAttribute("name", townMapper.selectByPrimaryKey(dto.getTownId()).getName());
//        }
//        
//        if(null != dto.getDistrictId()) {
//            model.addAttribute("name", districtMapper.selectByPrimaryKey(dto.getDistrictId()).getName());
//        }
//        
//        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
//        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
//        
//        model.addAttribute("count", userService.selectCount(dto, user));
//        
//        model.addAttribute("mc", monitorMapper.selectCount(m));
//        model.addAttribute("villageId", dto.getVillageId());
//        model.addAttribute("townId", dto.getTownId());
//        model.addAttribute("districtId", dto.getDistrictId());
//        return "pcList/welcome-list";
//    }
    /**
     * 各地企业-政府首页-中间     update wz 2019-02-21
     */
    @RequestMapping(value = "wel")
    public String wel(Model model, CompanyListReqDTO dto, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", dto.getVillageId());
        m.put("townId", dto.getTownId());
        m.put("districtId", dto.getDistrictId());
        
        model.addAttribute("number", companyMapper.selectIdsByCompany_view_count(dto));//企业数量
        
        HttpSession session = request.getSession();
        User moveBeforeUser =  (User)session.getAttribute("moveBefore");
        if(moveBeforeUser != null){
            model.addAttribute("moveD",1);
        }
        if(null != dto.getVillageId()) {
            model.addAttribute("name", villageMapper.selectByPrimaryKey(dto.getVillageId()).getName());
//            if(moveBeforeUser != null){
//                model.addAttribute("moveD",1);
//                model.addAttribute("nameBefore", townMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName());
//            }
            log.error("村级账号："+villageMapper.selectByPrimaryKey(dto.getVillageId()).toString());
            model.addAttribute("movUserId", villageMapper.selectByPrimaryKey(dto.getVillageId()).getUserId());
        }
        
        if(null != dto.getTownId()) {
            model.addAttribute("name", townMapper.selectByPrimaryKey(dto.getTownId()).getName());
//            if(moveBeforeUser != null){
//                model.addAttribute("moveD",1);
//                model.addAttribute("nameBefore", districtMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName());
//            }
            log.error("镇级账号："+townMapper.selectByPrimaryKey(dto.getTownId()).toString());
            model.addAttribute("movUserId", townMapper.selectByPrimaryKey(dto.getTownId()).getUserId());
        }

        if(null != dto.getDistrictId()) {
            model.addAttribute("name", districtMapper.selectByPrimaryKey(dto.getDistrictId()).getName());
//            if(moveBeforeUser != null){
//                model.addAttribute("moveD",1);
//                model.addAttribute("nameBefore",userMapper.selectByPrimaryKey(moveBeforeUser.getId()).getUserName());
//            }
            log.error("区级账号："+districtMapper.selectByPrimaryKey(dto.getDistrictId()).toString());
            model.addAttribute("movUserId", districtMapper.selectByPrimaryKey(dto.getDistrictId()).getUserId());
        }
        
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        
        model.addAttribute("count", userService.selectCount(dto, user));
        
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("villageId", dto.getVillageId());
        model.addAttribute("townId", dto.getTownId());
        model.addAttribute("districtId", dto.getDistrictId());
        return "pcList/welcome-list";
    }
    /**
     * 政府端——上一级免密跳转到下一级，只跳转一次 wz add 2019-02-21
     */
    @RequestMapping(value = "move/next")
    public String move(HttpServletRequest request, Model model,Integer uid) throws Exception {
        //log.error("move/company");
        User user = getLoginUser(request);
        User userM = userMapper.selectByPrimaryKey(uid);
        if(user.getUserType()!= userM.getUserType()){
            HttpSession session = request.getSession();
            //log.error("moveBefore："+user.toString());
            session.setAttribute("moveBefore",user); //跳转前user保存
            //log.error("unMove："+uid);
            user = userMapper.selectByPrimaryKey(uid);
            //log.error("moveOld："+user.toString());
            SessionUtil.setUser(request, user);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        if(user.getUserType() == 6){//区
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
            return "area/main";
        }
        if(user.getUserType() == 3){//镇
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
            return "town/main";  
        }
        if(user.getUserType() == 4){//村
            model.addAttribute("name_", villageMapper.selectByPrimaryKey(user.getId()).getName());
//            if(user.getUserName().equals("港口")) {//港口特殊账号
//                return "gang/main";
//            }
            model.addAttribute("loginUserId", user.getId());
            return "village/main";
        }
        return "";  
    }
    /**
     * 政府端——上一级免密跳转到下一级，只跳转一次  返回  wz add 2019-02-21
     */
    @RequestMapping(value = "back/gov")
    public String back(HttpServletRequest request, Model model) throws Exception {
        //log.error("back/gov");
        HttpSession session = request.getSession();
        User user =  (User)session.getAttribute("moveBefore");
        //log.error("moveBefore："+user.toString());
        if(user == null){//返回后的刷新
            user = getLoginUser(request);
            //log.error("moveBefore："+user.toString());
        }else{  
            SessionUtil.setUser(request, user);
            session.removeAttribute("moveBefore");//返回时将moveBefore的session删除
        }
        Map<String, Object> m = new HashMap<String, Object>();
        if(user.getUserType() == 9){//安泰
            model.addAttribute("list", districtMapper.selectDistrict());
            model.addAttribute("list1", tradeMapper.selectTrade());
            model.addAttribute("name_", user.getUserName());
            model.addAttribute("loginUserId", user.getId());
            return "country/main";
        }
        if(user.getUserType() == 6){//区
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
            return "area/main";
        }
        if(user.getUserType() == 3){//镇
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());
            return "town/main";  
        }
        return "";  
    }  
    
    /**
     * 摄像头监管段
     */
    @RequestMapping(value = "monitor-list-supervise")
    public String monitorListSupervise(HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, String companyName, Model model) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("companyName", companyName);
        model.addAttribute("list", monitorMapper.selectGroupByMap(m));
        model.addAttribute("companyName", companyName);
        return "pcList/monitor-list-supervise";
    }
    
    /**
     * 企业分布页面
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("company/company-maps")
    public String coMaps(Model model,Integer villageId ,Integer townId, Integer districtId,HttpServletRequest request) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
        for (int i = 0; i < list.size(); i++) {
            DynamicParameter<String, Object> item = list.get(i);
            item.put("id", i + 1 + "");
        }
        String listStr = JSONArray.toJSONString(list);
        model.addAttribute("companyList", listStr);
        return "pcList/company-map1";
    }
    
    /**
     * 企业列表
     */
    @RequestMapping(value = "company/company-lists")
    public String companyList(Integer townId, Integer villageId , Integer districtId, Integer userId, Model model,
            HttpServletRequest request, String companyName, String state, String isFreeze,
            String industry, String industry2_, String industry2_2,
            Integer cisDanger, String dlevel, Integer isKey, String doubleDanger) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("companyName", companyName);
        m.put("isFreeze", isFreeze);
        m.put("cisDanger", cisDanger);
        if(null != dlevel) {
            dlevel = utf8Str(dlevel);
        }
        m.put("dlevel", dlevel);
        m.put("isKey", isKey);
        m.put("doubleDanger", doubleDanger);
        String industry2 = null;
        if(StringUtils.isNotBlank(industry2_)) {
            industry2 = industry2_;
            if(StringUtils.isNotBlank(industry2_2)) {
                industry2 = industry2 + " > " + industry2_2;
            }
        }
        m.put("industry2", industry2);
        m.put("industry", industry);
        model.addAttribute("industry2", industry2);
        model.addAttribute("industry", industry);
        List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyList(m);
        model.addAttribute("list", mlist);
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("companyName", companyName);
        model.addAttribute("state", state);
        model.addAttribute("isFreeze", isFreeze);
        model.addAttribute("isKey", isKey);
        model.addAttribute("sk", request.getParameter("sk"));
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        if (null != cisDanger) {
            return "village/company/company-list3";
        }
        if (StringUtils.isNotBlank(dlevel)) {
            return "village/company/company-list2";
        }
        return "pcList/company-list";
    }
    
    /**
     * 特种人员列表
     */
    @RequestMapping(value = "warning/sperson-list")
    public String spersonList(HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, Model model, Integer isTime, Integer spFlag,
            String companyName) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("spFlag", spFlag);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        List<DynamicParameter<String, Object>> list = spersonMapper.selectSpersonList(m);
        model.addAttribute("list", list);
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/sperson-list";
    }

    /**
     * 职业卫生检测
     */
    @RequestMapping(value = "warning/detection-list")
    public String detectionList(Model model, HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, Integer isTime, String companyName,
            String deType) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("deType", deType);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", detectionMapper.selectDetectionList(m));
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("deType", deType);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/detection-list";
    }

    /**
     * 职业健康体检
     */
    @RequestMapping(value = "warning/examination-list")
    public String examinationList(Model model, HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, String companyName, Integer isTime)
            throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("list", examinationMapper.selectExaminationList(m));
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/examination-list";
    }
    
    /**
     * 隐患排查 检查历史
     */
    @RequestMapping(value = "check-list")
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
            Integer villageId ,Integer townId, Integer districtId,
            Integer status, Integer flag, Model model) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        if (null != townId) {//区
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (null != districtId) {//全国
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        m.put("type", type);
        m.put("flag", flag);
        m.put("title", title);
        m.put("townId", townId);
        m.put("villageId", villageId);
        m.put("companyName", companyName);
        m.put("status", status);
        m.put("villageId", villageId);
        if(setUserId(getLoginUser(request), m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            model.addAttribute("list", list);
        }
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        return "pcList/check-list";
    }
    
    /**
     * 重大隐患列表
     */
    @RequestMapping(value = "check-item3")
    public String checkItem3(HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, Model model, Integer flag, String companyName, Integer status)
            throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("flag", flag);
        m.put("status", status);
        m.put("companyName", companyName);
        m.put("d", 1);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerIndexList(m);
        m.put("status", 2);
        Integer list_s2 = tCheckItemMapper.selectDangerIndexListCount(m);
        m.put("status", 3);
        Integer list_s3 = tCheckItemMapper.selectDangerIndexListCount(m);
        model.addAttribute("list_s2", list_s2);
        model.addAttribute("list_s3", list_s3);
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("companyName", companyName);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/danger-index3";
    }
    
    /**
     * 重大危险源和高危作业
     */
    @RequestMapping(value = "company/company-list-zg")
    public String companyListZg(Integer villageId ,Integer townId, Integer districtId, Integer userId, Model model,
            HttpServletRequest request, String companyName, String state, String isFreeze,
            String industry, String industry2_, String industry2_2,
            Integer cisDanger, String dlevel, Integer isKey, String doubleDanger) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("companyName", companyName);
        m.put("isFreeze", isFreeze);
        m.put("cisDanger", cisDanger);
        if(null != dlevel) {
            dlevel = utf8Str(dlevel);
        }
        m.put("dlevel", dlevel);
        m.put("isKey", isKey);
        m.put("doubleDanger", doubleDanger);
        String industry2 = null;
        if(StringUtils.isNotBlank(industry2_)) {
            industry2 = industry2_;
            if(StringUtils.isNotBlank(industry2_2)) {
                industry2 = industry2 + " > " + industry2_2;
            }
        }
        m.put("industry2", industry2);
        m.put("industry", industry);
        model.addAttribute("industry2", industry2);
        model.addAttribute("industry", industry);
        List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyList(m);
        model.addAttribute("list", mlist);
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("companyName", companyName);
        model.addAttribute("state", state);
        model.addAttribute("isFreeze", isFreeze);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        model.addAttribute("isKey", isKey);
        model.addAttribute("sk", request.getParameter("sk"));
        if (null != cisDanger) {
            return "village/company/company-list3";
        }
        if (StringUtils.isNotBlank(dlevel)) {
            return "village/company/company-list2";
        }
        return "pcList/company-list";
    }
    
    /**
     * 安全评价档案页面
     */
    @RequestMapping("evaluate/evaluation-list")
    public String evatList(Model model, HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, String reportName, String evaType, Integer isTime,
            String companyName, String flag) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("flag", flag);
        m.put("reportName", reportName);
        m.put("companyName", companyName);
        m.put("evaType", evaType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        List<DynamicParameter<String, Object>> list = evaluationMapper.selectEvaluationList(m);
        model.addAttribute("list", list);
        model.addAttribute("reportName", reportName);
        model.addAttribute("evaType", evaType);
        model.addAttribute("flag", flag);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/evaluation-list";
    }
    
    /**
     * 各类许可证页面
     */
    @RequestMapping("evaluate/zheng-list")
    public String zhengList(Model model, HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, Standard standard, String companyName, Integer isTime)
            throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("type", standard.getType());
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", standardMapper.selectList(m));
        model.addAttribute("type", standard.getType());
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/zheng-list";
    }
    
    /**
     * 应急救援页面
     */
    @RequestMapping(value = "tables/tab-yjlist")
    public String yjjyList(HttpServletRequest request, Model model,Integer villageId ,Integer townId, Integer districtId, String companyName, String name, Integer isTime, Integer c) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        if(null != c && c.compareTo(1) == 0) {
            m.put("ownerId", user.getId());
        } else {
            m.put("villageId", villageId);
            m.put("townId", townId);
            m.put("districtId", districtId);
        }
        m.put("name", name);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("list", tContingencyPlanMapper.selectTable(m));
        model.addAttribute("name", name);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("c", c);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/tab-yjlist";
    }
    
    /**
     * 应急预案演练页面
     */
    @RequestMapping(value = "tables/tab-yllist")
    public String yljyList(HttpServletRequest request, Model model,Integer villageId ,Integer townId, Integer districtId, String name, Integer c) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        if(null != c && c.compareTo(1) == 0) {
            m.put("ownerId", user.getId());
        } else {
            m.put("villageId", villageId);
            m.put("townId", townId);
            m.put("districtId", districtId);
        }
        m.put("name", name);
        model.addAttribute("list", tDrillMapper.selectTable(m));
        model.addAttribute("name", name);
        model.addAttribute("c", c);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/tab-yllist";
    }

    
    /**
     * 隐患汇总 cId 企业id
     */
    @RequestMapping(value = "danger-index-list")
    public String modelList(HttpServletRequest request, Model model,Integer villageId ,Integer townId, Integer districtId, Integer flag, Integer cId, Integer d,
            String partName, String companyName, Integer status,  
            String startTime, String endTime) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("flag", flag);
        m.put("status", status);
        m.put("d", d);
        m.put("companyName", companyName);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        if(null != cId) {
            m.put("userId", cId);
        }
        m.put("partName", partName);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerIndexList(m);
        m.put("status", 2);
        Integer list_s2 = tCheckItemMapper.selectDangerIndexListCount(m);
        m.put("status", 3);
        Integer list_s3 = tCheckItemMapper.selectDangerIndexListCount(m);
        model.addAttribute("list_s2", list_s2);
        model.addAttribute("list_s3", list_s3);
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("companyName", companyName);
        model.addAttribute("partName", partName);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("d", d);
        model.addAttribute("cId", cId);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/danger-index-list";
    }
    
    /**
     * 特种作业人员页面
     */
    @RequestMapping("special-print")
    public String spersonPrint(Model model, HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, String spFlag, String spName, String spType,
            Integer isTime) throws Exception {
        if (spFlag == null || "".equals(spFlag)) {
            spFlag = "0";
        }
        spName = utf8Str(spName);
        int libraryType = 10;
        if ("0".equals(spFlag)) {
            libraryType += 1; 
        } else {
            libraryType += Integer.parseInt(spFlag);
        }
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        switch (user.getUserType()) {
            case 3:
                m.put("villageId", villageId);
                break;
            case 6:
                m.put("townId", townId);
                break;
            case 9:
                m.put("districtId", districtId);
                break;
        }
        m.put("spName", spName);
        m.put("spFlag", spFlag);
        m.put("spType", spType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", spersonMapper.selectSpersonList(m));
        model.addAttribute("lib", libraryMapper.selectLibraryList(libraryType));
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("spName", spName);
        model.addAttribute("spType", spType);
        model.addAttribute("isTime", isTime);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/special-print";
    }
    
    /**
     * 特种设备列表
     */
    @RequestMapping(value = "warning/sequipment-list")
    public String sequipmentList(HttpServletRequest request,Integer villageId ,Integer townId, Integer districtId, Model model, Integer isTime, String companyName)
            throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", sequipmentMapper.selectSequipmentList(m));
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/sequipment-list";
    }
    /**
     * 隐患列表 整改复查列表
     * c 0 表示过滤未整改的 1已整改的
     */
    @RequestMapping(value = "rectification-list")
    public String checkDangerList(Integer flag, String title, Integer c,String companyName, Integer villageId ,Integer townId, Integer districtId,Model model,
            HttpServletRequest request) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);
        m.put("title", title);
        m.put("companyName", companyName);
        if(null != c) {
            m.put("c", c);
        }
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        model.addAttribute("list", tRectificationMapper.selectRectificationList(m));
        model.addAttribute("flag", flag);
        model.addAttribute("title", title);
        model.addAttribute("c", c);
        model.addAttribute("companyName", companyName);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        return "pcList/check-fulist";
    }
    
    static String PP = "yyyy-MM";

    /**
     * 折线图数据 根据隐患类型 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "lineChartData")
    public @ResponseBody Result lineChartData(String sT, String eT,Integer villageId ,Integer townId, Integer districtId, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectGroupIndustryType(m);
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }
        String[] xx = new String[]{"基础管理", "现场管理", "高危作业"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        for (DynamicParameter<String, Object> dy : ll) {
            String time = dy.getString("time");
            Integer t = dy.getInteger("t");// 1 基础 2现场 3高危
            Integer c = dy.getInteger("c");
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    if (t.intValue() == 1) {
                        Integer[] x = (Integer[]) m1.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 2) {
                        Integer[] x = (Integer[]) m2.get("data");
                        x[i] = c;
                        break;
                    } else {
                        Integer[] x = (Integer[]) m3.get("data");
                        x[i] = c;
                        break;
                    }
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    /**
     * 折线图数据 根据隐患等级 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "lineChartData2")
    public @ResponseBody Result lineChartData2(String sT, String eT, Integer villageId ,Integer townId, Integer districtId, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectGroupItem3(m);
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }
        String[] xx = new String[]{"一般", "重大"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        for (DynamicParameter<String, Object> dy : ll) {
            String time = dy.getString("time");
            Integer t = dy.getInteger("d");// 1 一般 0 重大
            Integer c = dy.getInteger("c");
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    if (t.intValue() == 0) {
                        Integer[] x = (Integer[]) m1.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 1) {
                        Integer[] x = (Integer[]) m2.get("data");
                        x[i] = c;
                        break;
                    }
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    /**
     * 柱状图数据 根据隐患整改 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "zhuChartData")
    public @ResponseBody Result zhuChartData(String sT, String eT, Integer villageId ,Integer townId, Integer districtId, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -4), PP);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -4), PP);
        }
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectGroupItemStatus(m);
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }
        String[] xx = new String[]{"隐患条数", "整改项数"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        for (DynamicParameter<String, Object> dy : ll) {
            String time = dy.getString("time");
            Integer t = dy.getInteger("status");// 2 未整改 3已整改
            Integer c = dy.getInteger("c");
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    if (t.intValue() == 2) {
                        Integer[] x = (Integer[]) m1.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 3) {
                        Integer[] x = (Integer[]) m2.get("data");
                        x[i] = c;
                        break;
                    }
                }
            }
        }
        Integer[] x = (Integer[]) m1.get("data");
        Integer[] x2 = (Integer[]) m2.get("data");
        for (int i = 0; i < x.length; i++) {
            x[i] = x2[i] + x[i];
        }
        mm.add(m1);
        mm.add(m2);
        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    /**
     * 柱状图数据 根据隐患来源 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "zhuChartData2")
    public @ResponseBody Result zhuChartData2(String sT, String eT, Integer villageId ,Integer townId, Integer districtId, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectGroupFlag(m);
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }
        String[] xx = new String[]{"企业自查", "委托检查", "行政执法", "行政检查"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();
        Map<String, Object> m4 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);
        m4.put("name", xx[3]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        m4.put("data", d.clone());
        for (DynamicParameter<String, Object> dy : ll) {
            String time = dy.getString("time");
            Integer t = dy.getInteger("flag");// 1 自查 2行政 3第三番
            Integer c = dy.getInteger("c");
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    if (t.intValue() == 1) {
                        Integer[] x = (Integer[]) m1.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 2) {
                        Integer[] x = (Integer[]) m3.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 3) {
                        Integer[] x = (Integer[]) m2.get("data");
                        x[i] = c;
                        break;
                    } else {
                        Integer[] x = (Integer[]) m4.get("data");
                        x[i] = c;
                        break;
                    }
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        mm.add(m4);
        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    List<String> monthB(String sT, String eT) throws Exception {
        List<String> l = new LinkedList<String>();
        Date s = DateConvertUtil.formateDate(sT, PP);
        Date e = DateConvertUtil.formateDate(eT, PP);
        while (!s.after(e)) {
            l.add(DateFormatUtils.format(s, PP));
            s = DateConvertUtil.addMonths(s, 1);
        }
        return l;
    }
    
    /**
     * 企业列表
     */
    @RequestMapping(value = "company/company-list")
    public String companyList( Model model, HttpServletRequest request, 
            String companyName,Integer villageId,Integer townId,Integer districtId,
            Integer danger) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("companyName", companyName);
        m.put("villageId", villageId);
        m.put("townId", townId);
        m.put("districtId", districtId);
        m.put("danger", danger);
        List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyList(m);
        model.addAttribute("list", mlist);
        model.addAttribute("villageId", villageId);
        model.addAttribute("townId", townId);
        model.addAttribute("districtId", districtId);
        if(danger != null){
            return "pcList/company-list-gwzy";
        }
        return "pcList/company-list-zdwxy";
    }

}
