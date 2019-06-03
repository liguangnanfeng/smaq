/**  
 * Copyright(c)2017 Wuxi Tongx Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.write.DateTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.FcPromise;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.tobject.user.CompanyMapReqDTO;
import com.spring.web.util.DateConvertUtil;

/**
 * @Title: VillageController_AddNew
 * @Description: 用于同翔方在项目政府端新加功能的主要文件
 * @author Wangz
 * @date 2018年12月12日 上午09:15:33
 * @version V1.0
 */
@Controller
@RequestMapping("/villageAdd")
public class VillageController_AddNew extends BaseController {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = 3L;
    @Autowired
    private UserService userService;
    @Autowired
    private CgfService cgfService;
    
    
    /**
     * 风险研判与承诺公告————安全承诺公汇总
     */
    @RequestMapping(value = "study/promise-collect")
    public String promiseCollect(HttpServletRequest request,Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        if (user.getUserType() == 10) {//行业端
            m.put("tid", user.getId());
            list = fcPromiseMapper.selectCurrentMonthByTradeMap(m);
        }else{
        	//村级账号
            if (user.getUserType() == 4) {
                m.put("villageId", user.getId());
            }
            //镇级账号
            if (user.getUserType() == 3) {//
                m.put("townId", user.getId());
            }
            //区级账号
            if (user.getUserType() == 6) {
                m.put("districtId", user.getId());
            }
            //总账号查询所有的
            if (user.getUserType() == 9) {
                
            }
            list = fcPromiseMapper.selectCurrentMonthByVTDMap(m);
        }
        model.addAttribute("list", list);
        return "village/study/promise-collect";
    }
    /**
     * 风险研判与承诺公告————安全承诺公告-公告列表
     */
    @RequestMapping(value = "study/promise-list")
    public String promiseList(HttpServletRequest request,Integer uid, Integer type,Model model) throws Exception {
    	List<FcPromise> list = new ArrayList<FcPromise>();
    	if(type == 0){
        	list = fcPromiseMapper.selectByUid(uid);
        }else{//type == 1 查询当月承诺公告
        	list = fcPromiseMapper.selectCurrentMonthByUid(uid);
        }
    	model.addAttribute("type",type);
        model.addAttribute("list",list);
        return "village/study/promise-list";
    }
    /**
     * 风险研判与承诺公告————安全承诺公告详情
     */
    @RequestMapping(value = "study/promise-detail")
    public String promiseDetail(HttpServletRequest request,Integer uid,Integer id,Integer type, Model model) throws Exception {
        model.addAttribute("CompanyName", companyMapper.selectByPrimaryKey(uid).getName());
        FcPromise pro = fcPromiseMapper.selectByPrimaryKey(id);
        model.addAttribute("pro",pro);
        model.addAttribute("type",type);
        model.addAttribute("action","read");
        model.addAttribute("action1","read1");
        return "company/study/promise"; 
    }
    /**
     * 源长制全部企业
     * @author hejy 2019.1.14
     */
    @RequestMapping(value = "source/company-list")
    public String companyList(Model model, HttpServletRequest request, CompanyListReqDTO dto, Integer totalzc, Integer totalwyx) throws Exception {
        User user = getLoginUser(request);
        //设置查询条件
        setUserId2(user, dto);
        dto.setCisDanger("1");
        String industry2 = null;
        if (StringUtils.isNotBlank(dto.getIndustry2_())) {
            industry2 = dto.getIndustry2_();
            if (StringUtils.isNotBlank(dto.getIndustry2_2())) {
                industry2 = industry2 + " > " + dto.getIndustry2_2();
            }
        }
        dto.setIndustry2(industry2);
        String freeze = null;//isFreeze属性用来判断运行状态（当日是否已填写每日检查表）而非是否冻结
        if(StringUtils.isNotBlank(dto.getIsFreeze())){
            freeze = dto.getIsFreeze();
            dto.setIsFreeze(null);
        }
        //获取所有符合条件的user_id
        if(StringUtils.isBlank(dto.getUserIds()) || "-1".equals(dto.getUserIds())){
            List<Integer> allUserIds = companyMapper.selectIdsByCompany_view(dto);
            if (allUserIds.size() == 0) {
                dto.setUserIds("-1");
            } else {
                dto.setUserIds(StringUtils.join(allUserIds, ","));
            }
        }
        //全部企业列表
        List<Map<String,Object>> comps = new ArrayList<Map<String,Object>>();
        //判断每日检查表是否填写 运行状态列表operatingStatusList
        List<Boolean> operatingStatusList = new ArrayList<Boolean>();
        if( ! dto.getUserIds().equals("-1")){
            comps = companyMapper.selectByIds(dto);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            boolean operatingStatus = false;
            String title = "重大危险源每日检查表";
            Map<String, Object> c = new HashMap<String, Object>();
            c.put("flag", 1);
            c.put("title", title);
            for(int i=0; i < comps.size(); i++){
                c.put("userIds", comps.get(i).get("userId"));
                List<Map<String, Object>> lists = tCheckMapper.selectList(c);
                for (Map<String, Object> list : lists) {
                    String rt = list.get("realTime").toString();
                    if(date.equals(rt.substring(0,10))){
                        operatingStatus = true;
                        break;
                    }
                }
                operatingStatusList.add(operatingStatus);
                operatingStatus = false;
            }
            //运行状态筛选
            List<Integer> removeList = new ArrayList<Integer>();
            if(freeze != null){
                dto.setIsFreeze(freeze);
                if(freeze.equals("1")){
                    for(int i = 0; i < comps.size(); i++){
                        if(operatingStatusList.get(i) == false){
                            removeList.add(i);
                        }
                    }
                }else{//freeze为"0"
                    for(int i = 0; i < comps.size(); i++){
                        if(operatingStatusList.get(i) == true){
                            removeList.add(i);
                        }
                    }
                }
                if(removeList.size() > 0){
                    for(int i = removeList.size()-1; i >= 0; i--){
                        comps.remove(removeList.get(i).intValue());
                        operatingStatusList.remove(removeList.get(i).intValue());
                    }
                }
            }
            //分页
            model.addAttribute("total", comps.size());
            Integer from = dto.getPage() * dto.getRows();
            Integer end = null;
            end = Math.min(from + dto.getRows(), comps.size());
            comps = comps.subList(from, end);
            operatingStatusList = operatingStatusList.subList(from, end);
        }else{
        	 model.addAttribute("total", 0);
        }
        model.addAttribute("list",comps);
        model.addAttribute("osl", operatingStatusList);
        
        if(user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }    
//        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("sk", request.getParameter("sk"));      
//        model.addAttribute("totalzc", totalzc);
//        model.addAttribute("totalwyx", totalwyx);
        model.addAttribute("dto", dto);

        return "village/source/company-list";
    }
    
    void setUserId2(User user, CompanyListReqDTO dto) {
        if (user.getUserType() == 4) {
            dto.setVillageId(user.getId());
        }
        if (user.getUserType() == 5) {
            dto.setUserId(user.getId());
        }
        if (user.getUserType() == 3) {
            dto.setTownId(user.getId());
        }
        if (user.getUserType() == 6) {
            dto.setDistrictId(user.getId());
        }
        if (user.getUserType() == 9) {
        }
        if (user.getUserType() == 10) {
            dto.setTradeId(user.getId());
         }
     }
    /**
     * 重大危险源源长制————在线监测
     */
    @RequestMapping(value = "source/video")
    public String monitorListSupervise(HttpServletRequest request, String companyName, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("cisDanger", 1);
        m.put("chemic", 1);
        m.put("companyName", companyName);
        List<Integer> uidList = companyMapper.selectUseridsForSource(m);
        if(uidList.size()==0){
        	m.put("userIds", -1);
        }else{
        	String[] userIds = new String[uidList.size()];
            for(int i = 0; i < uidList.size(); i++){
            	userIds[i] = uidList.get(i).toString();
            }
            m.put("userIds", StringUtils.join(userIds, ","));
        }
        model.addAttribute("list", monitorMapper.selectGroupByMap(m));
        model.addAttribute("companyName", companyName);
        return "village/system/monitor-list-supervise";
    }
    /**
     * 重大危险源源长制————企业分布
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("source/company-map")
    public String coMap(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("page", 0);
//        m.put("limit", 20);//此参数无意义，实际是1000
        m.put("cisDanger", 1);
        m.put("chemic", 1);
        List<Map<String, Object>> mList = monitorMapper.selectGroupByMap(m);
        if(mList.size()==0){
        	m.put("userIds", -1);
        }else{
        	String[] userIds = new String[mList.size()];
            for(int i = 0; i < mList.size(); i++){
            	userIds[i] = mList.get(i).get("userId").toString();
            }
            m.put("userIds", StringUtils.join(userIds, ","));
        }
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
        for (int i = 0; i < list.size(); i++) {
            DynamicParameter<String, Object> item = list.get(i);
            item.put("id", i + 1 + "");
        }
        Integer count = companyMapper.selectCountMap(m);
        String listStr = JSONArray.toJSONString(list);
        String userStr = JSONObject.toJSONString(user);
        model.addAttribute("count", count);
        model.addAttribute("companyList", listStr);
        model.addAttribute("user", userStr);
        return "village/source/company-map";
    }
    
    /**
     * 根据条件信息查询重大危险源企业列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "source/getCompanyByMap")
    public @ResponseBody Result getCompanyByMap(HttpServletRequest request, @RequestBody CompanyMapReqDTO dto) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", dto.getCompanyName());
        if(m.get("districtId") == null){
            m.put("districtId", dto.getDistrictId());
        }
        if(m.get("townId") == null){
            m.put("townId", dto.getTownId());
        }
        if(m.get("villageId") == null){
            m.put("villageId", dto.getVillageId());
        }
        m.put("dlevel", dto.getDlevel());
        m.put("page", (dto.getPage()- 1) * dto.getLimit());
        m.put("limit", dto.getLimit());
        m.put("industry", dto.getIndustry());
        m.put("cisDanger", 1);
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
        //log.error("zhangcl 2018.12.12 listsize:"+list.size());
        Integer count = companyMapper.selectCountMap(m);
        for (int i = 0; i < list.size(); i++) {
            DynamicParameter<String, Object> item = list.get(i);
            item.put("id", i + 1 + "");
        }
        result.setMap("list", list);
        result.setMap("count", count);
        return result;
    }
    
    
    /**
     * 重大危险源源长制————隐患排查治理
     */
    @RequestMapping(value = "source/danger-collect")
    public String modelList(HttpServletRequest request, Integer industryType, Integer townId, Integer villageId, String companyName, Model model, String startTime, String endTime) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        m.put("townId", townId);
        m.put("villageId", villageId);
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        m.put("industryType", industryType);
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerCollectByCompany(m);
        if (user.getUserType() == 6) {
            m.put("districtId", user.getId());
        }
        // userId, companyName,
        // 企业自查未整改, 已整改, 严重未整改,严重已整改
        // 行政执法未整改, 已整改, 严重未整改,严重已整改
        // 委托检查未整改, 已整改, 严重未整改,严重已整改
        // 行政检查未整改, 已整改, 严重未整改,严重已整改
        // Object[] a = new Object[18];
        List<Object[]> ll = new ArrayList<Object[]>();
        for (Map<String, Object> l : list) {
            Integer userId = Integer.valueOf(l.get("userId").toString());
            Integer c = Integer.valueOf(l.get("c").toString());// 数量
            Integer flag = Integer.valueOf(l.get("flag").toString());// 1 自查 2执法 3委托5检查
            Integer status = Integer.valueOf(l.get("status").toString());// 2未改 3已改
            Integer d = Integer.valueOf(l.get("d").toString());// 0 一般隐患 1重大隐患
            Object[] a = null;
            Boolean has = false;
            for (Object[] o : ll) {
                if (((Integer) o[0]).compareTo(userId) == 0) {
                    a = o;
                    has = true;
                    break;
                }
            }
            if (null == a) {
                String name = (String) l.get("name");// 企业名称
                a = new Object[]{userId, name, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            }
            //处理数据
            int index = (flag -1) * 4 + 2;
            if(status == 2) {
                a[index] = (Integer)a[index] + c;
            } else {
                a[index + 1] = (Integer)a[index + 1] + c;
            }
            //重大隐患
            if(d == 1) {
                if(status == 2) {
                    a[index + 2] = (Integer)a[index + 2] + c;
                } else {
                    a[index + 3] = (Integer)a[index + 3] + c;
                } 
            }
            if(!has) {
                ll.add(a);
            }
        }
        model.addAttribute("m", m);
        model.addAttribute("list", ll);
        
        m.put("d", 1);
        Integer d = tCheckItemMapper.selectDangerIndexListCount(m);
        List<Map<String, Object>> lll = tCheckItemMapper.selectDangerCollectGroup(m);
        model.addAttribute("groupIndu", lll);
        model.addAttribute("d", d);
        model.addAttribute("libL", libraryMapper.selectLibraryList(1));// 行业
        return "village/source/danger-collect";
    }
    /**
     * 重大危险源源长制————分类统计
     */
    @RequestMapping("source/danger-chart")
    public String dangerChart() {
        return "village/source/danger-chart";
    }
    
    /**
     * 柱状图数据 企业运行率
     */
    @RequestMapping(value = "source/zhuChartData")//hejy 2019.1.17
    public @ResponseBody Result zhuChartData(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("cisDanger", 1);
        m.put("chemic", 1);
        List<Map<String, Object>> mList = monitorMapper.selectGroupByMap(m);
        if(mList.size()==0){
        	m.put("userIds", -1);
        }else{
        	String[] userIds = new String[mList.size()];
            for(int i = 0; i < mList.size(); i++){
            	userIds[i] = mList.get(i).get("userId").toString();
            }
            m.put("userIds", StringUtils.join(userIds, ","));
        }
        List<DynamicParameter<String, Object>> comps = companyMapper.selectCompanyList(m);
        
        List<Boolean> operatingStatusList = new ArrayList<Boolean>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        boolean operatingStatus = false;
        String title = "重大危险源每日检查表";
        Map<String, Object> c = new HashMap<String, Object>();
        c.put("flag", 1);
        c.put("title", title);
        for(int i=0; i < comps.size(); i++){
            c.put("userIds", comps.get(i).get("userId"));
            List<Map<String, Object>> lists = tCheckMapper.selectList(c);
            for (Map<String, Object> list : lists) {
                String rt = list.get("realTime").toString();
                if(date.equals(rt.substring(0,10))){
                    operatingStatus = true;
                    break;
                }
            }
            operatingStatusList.add(operatingStatus);
            operatingStatus = false;
        }
        //运行状态筛选
        List<Integer> removeList = new ArrayList<Integer>();
        for(int i = 0; i < comps.size(); i++){
        	if(operatingStatusList.get(i) == true){
            	removeList.add(i);
            }
        }
        if(removeList.size() > 0){
           for(int i = removeList.size()-1; i >= 0; i--){
//                comps.remove(removeList.get(i).intValue());
                operatingStatusList.remove(removeList.get(i).intValue());
            }
        }
        

        String[] names = new String[]{"全部企业", "未运行企业"};
        Integer[] d = {comps.size()};
        Integer[] d2 = {operatingStatusList.size()};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        m1.put("name", names[0]);
        m2.put("name", names[1]);
        m1.put("data", d);
        m2.put("data", d2);
        mm.add(m1);
        mm.add(m2);
        String[] categories = {"企业运行率"};
        result.setMap("categories", categories);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    /**
     * 柱状图数据 根据隐患来源 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "source/zhuChartData2")//hejy 2019.1.17
    public @ResponseBody Result zhuChartData2(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();

        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        List<Integer> list = companyMapper.selectByCompany_view(m);
        
        String[] names = new String[]{"非化工液氨使用企业", "粉尘涉爆企业", "高温熔融金属现场运输", "工贸行业有限空间安全作业","冶金企业煤气"};
        Integer[] d1 = new Integer[]{yzLiquidAmmoniaMapper.selectCount(list)};
        Integer[] d2 = new Integer[]{yzStiveExplosionMapper.selectCount(list)};
        Integer[] d3 = new Integer[]{yzHighTemperatureMapper.selectCount(list)};
        Integer[] d4 = new Integer[]{yzTradeArderMapper.selectCount(list)};
        Integer[] d5 = new Integer[]{yzMetallurgyMapper.selectCount(list)};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();
        Map<String, Object> m4 = new HashMap<String, Object>();
        Map<String, Object> m5 = new HashMap<String, Object>();
        m1.put("name", names[0]);
        m2.put("name", names[1]);
        m3.put("name", names[2]);
        m4.put("name", names[3]);
        m5.put("name", names[4]);
        m1.put("data", d1);
        m2.put("data", d2);
        m3.put("data", d3);
        m4.put("data", d4);
        m5.put("data", d5);
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        mm.add(m4);
        mm.add(m5);
        String[] categories = {"特殊作业"};
        result.setMap("categories", categories);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }
    /**
     * 重大危险源源长制————分类统计——查看企业列表
     */
    @RequestMapping(value = "source/danger-chart-list")//hejy 2019.1.18
    public String companyList(Model model, HttpServletRequest request, CompanyListReqDTO dto, String status, String type) throws Exception {
        User user = getLoginUser(request);
        //设置查询条件
        setUserId2(user, dto);
        dto.setCisDanger("1");
        String industry2 = null;
        if (StringUtils.isNotBlank(dto.getIndustry2_())) {
            industry2 = dto.getIndustry2_();
            if (StringUtils.isNotBlank(dto.getIndustry2_2())) {
                industry2 = industry2 + " > " + dto.getIndustry2_2();
            }
        }
        dto.setIndustry2(industry2);
        String freeze = status;//isFreeze属性用来判断运行状态（当日是否已填写每日检查表）而非是否冻结
        if(StringUtils.isNotBlank(dto.getIsFreeze())){
            freeze = dto.getIsFreeze();
            dto.setIsFreeze(null);
        }
        //获取所有符合条件的user_id
        if(StringUtils.isBlank(dto.getUserIds()) || "-1".equals(dto.getUserIds())){
        	Map<String, Object> m = new HashMap<String, Object>();
            setUserId(user, m);
            if(type!=null){
            	List<Integer> list = companyMapper.selectByCompany_view(m);
            	List<Integer> clist = new ArrayList<Integer>();
            	if(type.equals("1")){//非化工液氨使用企业
            		clist = yzLiquidAmmoniaMapper.selectUidsByList(list);
            	}else if(type.equals("2")){//粉尘涉爆企业
            		clist = yzStiveExplosionMapper.selectUidsByList(list);
            	}else if(type.equals("3")){//高温熔融金属现场运输
            		clist = yzHighTemperatureMapper.selectUidsByList(list);
            	}else if(type.equals("4")){//工贸行业有限空间安全作业
            		clist = yzTradeArderMapper.selectUidsByList(list);
            	}else{//冶金企业煤气
            		clist = yzMetallurgyMapper.selectUidsByList(list);
            	}
            	if(clist.size()==0){
            		dto.setUserIds("-1");
            	}else{
            		dto.setUserIds(StringUtils.join(clist,","));
            	}
            }else{
        		m.put("cisDanger", 1);
                List<Map<String, Object>> mList = monitorMapper.selectGroupByMap(m);
                if(mList.size()==0){
                	dto.setUserIds("-1");
                }else{
                	String[] userIds = new String[mList.size()];
                    for(int i = 0; i < mList.size(); i++){
                    	userIds[i] = mList.get(i).get("userId").toString();
                    }
                    dto.setUserIds(StringUtils.join(userIds, ","));
                }
        	}
        }
        //全部企业列表
        List<Map<String,Object>> comps = new ArrayList<Map<String,Object>>();
        //判断每日检查表是否填写 运行状态列表operatingStatusList
        List<Boolean> operatingStatusList = new ArrayList<Boolean>();
        if( ! dto.getUserIds().equals("-1")){
            comps = companyMapper.selectByDTO(dto);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            boolean operatingStatus = false;
            String title = "重大危险源每日检查表";
            Map<String, Object> c = new HashMap<String, Object>();
            c.put("flag", 1);
            c.put("title", title);
            for(int i=0; i < comps.size(); i++){
                c.put("userIds", comps.get(i).get("userId"));
                List<Map<String, Object>> lists = tCheckMapper.selectList(c);
                for (Map<String, Object> list : lists) {
                    String rt = list.get("realTime").toString();
                    if(date.equals(rt.substring(0,10))){
                        operatingStatus = true;
                        break;
                    }
                }
                operatingStatusList.add(operatingStatus);
                operatingStatus = false;
            }
            //运行状态筛选
            List<Integer> removeList = new ArrayList<Integer>();
            if(freeze != null){
                dto.setIsFreeze(freeze);
                if(freeze.equals("1")){
                    for(int i = 0; i < comps.size(); i++){
                        if(operatingStatusList.get(i) == false){
                            removeList.add(i);
                        }
                    }
                }else{//freeze为"0"
                    for(int i = 0; i < comps.size(); i++){
                        if(operatingStatusList.get(i) == true){
                            removeList.add(i);
                        }
                    }
                }
                if(removeList.size() > 0){
                    for(int i = removeList.size()-1; i >= 0; i--){
                        comps.remove(removeList.get(i).intValue());
                        operatingStatusList.remove(removeList.get(i).intValue());
                    }
                }
            }
            //分页
            model.addAttribute("total", comps.size());
            Integer from = dto.getPage() * dto.getRows();
            Integer end = null;
            end = Math.min(from + dto.getRows(), comps.size());
            comps = comps.subList(from, end);
            operatingStatusList = operatingStatusList.subList(from, end);
        }else{
        	 model.addAttribute("total", 0);
        }
        model.addAttribute("list",comps);
        model.addAttribute("osl", operatingStatusList);
        
        if(user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }    
        model.addAttribute("sk", request.getParameter("sk"));      
        model.addAttribute("dto", dto);

        return "village/source/danger-chart-list";
    }

}