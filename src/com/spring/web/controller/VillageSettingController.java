/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.dao.TDeviceMapper;
import com.spring.web.dao.TSensorsMapper;
import com.spring.web.dao.TWarnmsgMapper;
import com.spring.web.dao.UserGroupMapper;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Company;
import com.spring.web.model.Monitor;
import com.spring.web.model.TCheck;
import com.spring.web.model.TCheckPart;
import com.spring.web.model.TModel;
import com.spring.web.model.TSensors;
import com.spring.web.model.TWaterparamter;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.envir_params_setting.EnvirParamsSettingService;
import com.spring.web.service.user.UserService;
import com.spring.web.utils.SocketClient;

/** 
* @Title: VillageSettingController 
* @Description: TODO(用一句话描述该文件做什么) 
* @date 2018年5月28日 下午8:04:58 
* @version V1.0   
*/
@Controller
@RequestMapping("/village")
public class VillageSettingController extends BaseController{
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private UserService userService;
    @Autowired
    private EnvirParamsSettingService envirParamsSettingService;
    @Autowired 
    private TSensorsMapper tSensorsMapper;
    @Autowired
    private TWarnmsgMapper tWarnmsgMapper;
    @Autowired
    private TDeviceMapper tDeviceMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    
    @Autowired
    private CgfService cgfService;

    @RequestMapping("/system/toUpdateWaterParams")
    public String toUpdateWaterParams(HttpServletRequest request,Model model){
        model.addAttribute("waterParams", envirParamsSettingService.geTWaterparamters());
        return "village/paramssetting/watersetting";
    }
    
    @RequestMapping("/system/doUpdateWaterParams")
    @ResponseBody
    public JSONObject dUpdateWaterParams(HttpServletRequest request,Model model){
        JSONObject result = new JSONObject();
        result.put("status", false);
        try {
            String id = request.getParameter("id");
            String maxv = request.getParameter("maxv");
            String minv = request.getParameter("minv");
            TWaterparamter paramter = new TWaterparamter();
            paramter.setId(Integer.parseInt(id));
            paramter.setMaxVal(Double.parseDouble(maxv));
            paramter.setMinVal(Double.parseDouble(minv));
            result.put("status",envirParamsSettingService.updateWaterParameter(paramter));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    
    
    
    /*
     * 首页——重点部位关键装置实时监控
     */
//    @RequestMapping(value = "to_monitor-list")
//    public String videoMonitor(HttpServletRequest request,Model model,Integer userId) {
//
//    	log.error("userId："+userId);
//    	if(userId == null){
//    		User user = getLoginUser(request);
//    		model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
//    	}else{
//    		model.addAttribute("company", companyMapper.selectByPrimaryKey(userId));
//    	}
//    	return "company/system/video-monitor";
//    }
    
    /**
     * 实时监控,zhangcl 2018.12.26
     * @param request
     * @param before -- the fore page flag
     * @param flag -- Integer, 1: config page; 2: all video; 3: one video with config
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "to_monitor-list")
    public String videoMonitor(HttpServletRequest request,Integer before,Integer flag, Model model,Integer userId,String name) throws Exception{

//    	log.error("userId paramin:"+userId);
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
    	
//    	log.error("userId actual:"+userId);
    	
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
        	log.error("monitorList name1 : "+name.toString());
        	log.error("monitorList before : "+before);
        	List<Map<String, Object>> list = monitorMapper.selectByMap(m);
        	
        	for (Map<String, Object> map : list) {
            	model.addAttribute("monitor", map);
            	log.error("monitorList name2: "+map.get("name"));
            	break;
            }
        	
        	model.addAttribute("name", name);
        	model.addAttribute("before", before);
    	}
    	
    	if(flag==2){//显示缩略图,如果还是要显示视频就把这段去掉//zhangcl 2019.1.13
    		Map<String, Object> m = new HashMap<String, Object>();
            m.put("userId", userId);
            model.addAttribute("list", monitorMapper.selectByMap(m));
    		return "company/system/video-monitor-img";
    	} 
    	
    	return "company/system/video-monitor";
    }

    /**
     * TODO  摄像头add,张草六 2018.12.26
     * @param request
     * @param userId
     * @param name
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "monitor-add")
    public String monitorList(HttpServletRequest request,Integer userId, String name, Model model) throws Exception {
    	if(userId == null){
    		User user = getLoginUser(request);
    		userId = user.getId();
    	}
    	Map<String, Object> m = new HashMap<String, Object>();
    	
    	//log.error("utf8Str(name):"+utf8Str(name));
    	//log.error("URLDecoder.decode(name,UTF-8):"+URLDecoder.decode(name,"UTF-8"));
    	name = URLDecoder.decode(name,"UTF-8");//utf8Str(name);
    	m.put("name", name);
    	m.put("userId", userId);
    	log.error("monitorList name1 : "+name);
    	List<Map<String, Object>> list = monitorMapper.selectByMap(m);
    	
    	if(list==null){
    		Monitor monit = new Monitor();
    		monit.setName(name);
    		monit.setUserId(userId);
    		monitorMapper.insertSelective(monit);
    		list = monitorMapper.selectByMap(m);
    	}
    	
    	for (Map<String, Object> map : list) {
        	model.addAttribute("m", map);
        	log.error("monitorList name2: "+map.get("name"));
        	break;
        }
    	

    	model.addAttribute("camera", name);
    	//log.error("monitorList name2: "+m.get("name"));
        
        return "company/system/monitor-add";
    }
    
    /**
     * 更新视频源,zhangcl 2019.1.9
     */
    @RequestMapping(value = "monitor-update")
    public @ResponseBody Result monitorUpdate(String puids, String ids, String names,Integer userId) throws Exception {
    	log.error("puids:"+puids);
    	log.error("ids:"+ids);
    	log.error("userId:"+userId);
    	
    	names = URLDecoder.decode(names, "utf-8");
    	log.error("names:"+names);
    	Result result = new ResultImpl();
        try {
        	
        	cgfService.monitorUpdate(puids,ids,names,userId);
            //result.setMap("id", check.getId());
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "保存失败");
        }
        return result;
    }
    
    /**
     * 保存视频封面
     */
    @RequestMapping(value = "monitor-img-save")
    public @ResponseBody Result monitorImgSave(Monitor mo, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        monitorMapper.updateByPrimaryKeySelective(mo);
        return result;
    }
    
    
    //旧 ——首页——重点部位关键装置实时监控		wz 18.12.21
    @RequestMapping("to_monitor-list-old")
    public String to_monitorlist(HttpServletRequest request,Model model,String companyName){
        String page = request.getParameter("page");
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (user.getUserType() == 5) {
            m.put("companyName", companyName);
            Integer pagenum = 1;
            if(!StringUtils.isEmpty(page)){
                pagenum = Integer.parseInt(page);
            }
            m.put("page", 0);
            m.put("limit", Integer.MAX_VALUE);
            List<Map<String, Object>> list = monitorMapper.selectMonitorByMap(m);
            model.addAttribute("total", null != list ? list.size():0);
            m.put("page", (pagenum-1)*9);
            m.put("limit", 9);
            List<Map<String, Object>> mList = monitorMapper.selectMonitorByMap(m);
            model.addAttribute("list", mList);
            model.addAttribute("currLimit", mList.size()); 
            model.addAttribute("page", pagenum);
            //return "company/system/cn/monitors";
            return "company/system/new-monitor-list";
        }
        String userIds = request.getParameter("userId");
        
        if(!StringUtils.isEmpty(userIds)) {
            m.put("userId", Integer.parseInt(userIds));
            Integer pagenum = 1;
            if(!StringUtils.isEmpty(page)){
                pagenum = Integer.parseInt(page);
            }
            m.put("page", 0);
            m.put("limit", Integer.MAX_VALUE);
            List<Map<String, Object>> list = monitorMapper.selectMonitorByMap(m);
            model.addAttribute("total", null != list?list.size():0);
            m.put("page", (pagenum-1)*9);
            m.put("limit", 9);
            List<Map<String, Object>> mList = monitorMapper.selectMonitorByMap(m);
            model.addAttribute("list", mList);
            model.addAttribute("currLimit", mList.size()); 
            model.addAttribute("page", pagenum);
            model.addAttribute("companyName", companyName);
            model.addAttribute("userId", userIds);
        }
        return "company/system/new-monitor-list";
    }
    
    /*@RequestMapping("/toUpdateMonitorThumbnail")
    @ResponseBody
    public JSONObject toUpdateMonitorThumbnail(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            String id = request.getParameter("id");
            Monitor monitor = monitorMapper.selectByPrimaryKey(Integer.parseInt(id));
            if(null != monitor){
                try {
                    monitor.setThumbnail(VideoUtils.getInstance().getThumbnail(request, monitor.getCode()));
                } catch (Exception e) {
                    log.error(e.getMessage(),e);
                }
                monitorMapper.updateByPrimaryKeySelective(monitor);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }*/
    
    /**
     * 添加传感器
    * @param request
    * @param model
    * @return
     */
    @RequestMapping("/toAddSensor")
    public String toAddSensor(HttpServletRequest request,Model model){
        User user = getLoginUser(request);
        if(null != user && user.getUserType().equals(5)){
            model.addAttribute("device", tSensorsMapper.getCompanyDevice(user.getId()));
            String sensorId = request.getParameter("sensorId");
            model.addAttribute("sensorTypes", tSensorsMapper.getSensorType());
            if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorId)){
                model.addAttribute("sensor", tSensorsMapper.selectByPrimaryKey(Integer.parseInt(sensorId)));
            }
                return "company/sensor/addSensor";
        }
        return "";
    }
    
    /**
     * 检查传感器系列号是否存在
    * @param request
    * @return
     */
    @RequestMapping("checkSensorExsits")
    @ResponseBody
    public JSONObject checkSensorExsits(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            String sensorSn = request.getParameter("sensorSn");
            User user = getLoginUser(request);
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("sensorSn", sensorSn);
            params.put("companyId", user.getId());
            result.put("id", tSensorsMapper.checkSensorSnExsist(params));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 添加传感器
    * @param request
    * @return
     */
    @RequestMapping("/addSensor")
    @ResponseBody
    public JSONObject addSensor(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            result.put("status", false);
            User user = getLoginUser(request);
            String sensorId = request.getParameter("sensorId");
            String sensorName = request.getParameter("sensorName");
            String sensorSn = request.getParameter("sensorSn");
            String deviceSn = request.getParameter("deviceSn");
            String site = request.getParameter("site");
            String sensorType = request.getParameter("sensorType");
            TSensors sensors = new TSensors();
            if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorName)){
                sensors.setSensorname(sensorName);
            }
            if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorSn)){
                sensors.setSensorSn(sensorSn);
            }
            if(org.apache.commons.lang3.StringUtils.isNotBlank(deviceSn)){
                sensors.setDeviceSn(deviceSn);;
            }
            if(org.apache.commons.lang3.StringUtils.isNotBlank(site)){
                sensors.setSite(site);;
            }
            if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorType)){
                sensors.setSensorType(Integer.parseInt(sensorType));
            }
            sensors.setCompanyId(user.getId());
            sensors.setUpdateTime(new Date());
            if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorId)){
                sensors.setId(Integer.parseInt(sensorId));
                result.put("status", tSensorsMapper.updateByPrimaryKeySelective(sensors)>0?true:false);
            }else{
                sensors.setCreateTime(new Date());
                result.put("status", tSensorsMapper.insertSelective(sensors)>0?true:false);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 传感器列表页
    * @param request
    * @return
     */
    @RequestMapping("toSensorList")
    public String toSensorList(HttpServletRequest request,Model model){
        User user =getLoginUser(request);
        if(null != user){
            model.addAttribute("device", tSensorsMapper.getDeviceListBySensor(user.getId()));
            return "company/sensor/sensorList";
        }
        return "";
    }
    
    /**
     * 获取传感器列表
    * @param request
    * @return
     */
    @RequestMapping("/getSensorList")
    @ResponseBody
    public JSONObject getSensorList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user && user.getUserType().equals(5)){
                String pages = request.getParameter("page");
                String limits = request.getParameter("limit");
                Integer page = 1;
                Integer limit = 10;
                String deviceSn = request.getParameter("deviceSn");
                String sensorName = request.getParameter("sensorName");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(pages)){
                    page = Integer.parseInt(pages);
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(limits)){
                    limit = Integer.parseInt(limits);
                }
                Map<String, Object> params = new HashMap<String,Object>();
                if(org.apache.commons.lang3.StringUtils.isNotBlank(deviceSn)){
                    params.put("deviceSn", deviceSn);
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorName)){
                    params.put("sensorName", URLDecoder.decode(URLDecoder.decode(sensorName,"utf-8"),"utf-8"));
                }
                params.put("companyId", user.getId());
                result.put("total", tSensorsMapper.getSensorsListCount(params));
                params.put("page", (page-1)*limit);
                params.put("limit", limit);
                List<Map<String, Object>> list = tSensorsMapper.getSensorsList(params);
                if(null != list && list.size() > 0){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    for(Map<String, Object> map:list){
                        if(null != map.get("createTime")){
                            map.put("createTime", format.format(map.get("createTime")));
                        }else{
                            map.put("createTime","--");
                        }
                    }
                }
                result.put("list", list);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 删除传感器
    * @param request
    * @return
     */
    @RequestMapping("/toDeleteSensor")
    @ResponseBody
    public JSONObject toDeleteSensor(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            result.put("status", false);
           String id = request.getParameter("id");
           User user = getLoginUser(request);
           if(org.apache.commons.lang3.StringUtils.isNotBlank(id)){
               TSensors sensors = tSensorsMapper.selectByPrimaryKey(Integer.parseInt(id));
               if(null != sensors && sensors.getCompanyId().equals(user.getId())){
                   result.put("status", tSensorsMapper.deleteByPrimaryKey(Integer.parseInt(id)));
               }
           }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("company/zdlist")
    public String zdlist(HttpServletRequest request,Model model,Integer yzz){
    	model.addAttribute("yzz", yzz);
        String companyId = request.getParameter("companyId");
        model.addAttribute("companyId", companyId);
        model.addAttribute("typeList", tWarnmsgMapper.getSensorTypes(3));
        model.addAttribute("from", request.getParameter("from"));
        return "village/newWarn/zdlist";
    }
    
    @RequestMapping("company/gwlist")
    public String gwlist(HttpServletRequest request,Model model){
        String companyId = request.getParameter("companyId");
        model.addAttribute("companyId", companyId);
        model.addAttribute("typeList", tWarnmsgMapper.getSensorTypes(2));
        model.addAttribute("from", request.getParameter("from"));
        return "village/newWarn/gwlist";
    }
    
    @RequestMapping("company/wwlist")
    public String wwlist(HttpServletRequest request,Model model){
        String companyId = request.getParameter("companyId");
        model.addAttribute("companyId", companyId);
        model.addAttribute("typeList", tWarnmsgMapper.getSensorTypes(1));
        model.addAttribute("from", request.getParameter("from"));
        return "village/newWarn/wwlist";
    }
    
    @RequestMapping("company/getWwList")
    @ResponseBody
    public JSONObject getWwList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user){
                Integer page = 1;
                Integer limit = 10;
                Map<String, Object> params = new HashMap<String,Object>();
                //String companyName = request.getParameter("companyName");
                /*if(org.apache.commons.lang3.StringUtils.isNotBlank(companyName)){
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                    params.put("companyName", companyName);
                }*/
                String companyId = request.getParameter("companyId");
                String pages = request.getParameter("page");
                String limits = request.getParameter("limit");
                String sensorType = request.getParameter("sensorType");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(pages)){
                    page = Integer.parseInt(pages);
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(limits)){
                    limit = Integer.parseInt(limits);
                }
                //setUserId(user, params);
                if (user.getUserType() == 5) {
                    params.put("userId", user.getId());
                }else{
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(companyId)){
                        params.put("userId", companyId);
                    }
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorType)){
                    params.put("sensorType", Integer.parseInt(sensorType));
                }
                if(user.getUserType() == 9){
                    params.put("utp", 9);
                }
                //params.put("type", 2);
                params.put("page", (page-1)*limit);
                params.put("limit", limit);
                List<Map<String, Object>> list = tWarnmsgMapper.getWaterWarnMsgList(params);
                if(null != list && !list.isEmpty()){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    for(int i = 0;i<list.size();i++){
                        Map<String, Object> map = list.get(i);
                        map.put("index", (page-1)*limit+i+1);
                        if(null != map.get("collectTime")){
                            map.put("collectTime",format.format(map.get("collectTime")));
                        }else{
                            map.put("collectTime","--");
                        }
                    }
                }
                result.put("list", list);
                result.put("total", tWarnmsgMapper.getWaterWarnListCount(params));
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("company/getGwList")
    @ResponseBody
    public JSONObject getGwList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user){
                Integer page = 1;
                Integer limit = 10;
                Map<String, Object> params = new HashMap<String,Object>();
                //String companyName = request.getParameter("companyName");
                /*if(org.apache.commons.lang3.StringUtils.isNotBlank(companyName)){
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                    params.put("companyName", companyName);
                }*/
                String companyId = request.getParameter("companyId");
                String pages = request.getParameter("page");
                String limits = request.getParameter("limit");
                String sensorType = request.getParameter("sensorType");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(pages)){
                    page = Integer.parseInt(pages);
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(limits)){
                    limit = Integer.parseInt(limits);
                }
                //setUserId(user, params);
                if (user.getUserType() == 5) {
                    params.put("userId", user.getId());
                }else{
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(companyId)){
                        params.put("userId", companyId);
                    }
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorType)){
                    params.put("sensorType", Integer.parseInt(sensorType));
                }
                if(user.getUserType() == 9){
                    params.put("utp", 9);
                }
                params.put("type", 2);
                params.put("page", (page-1)*limit);
                params.put("limit", limit);
                List<Map<String, Object>> list = tWarnmsgMapper.getWarnMsgList(params);
                if(null != list && !list.isEmpty()){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    for(int i = 0;i<list.size();i++){
                        Map<String, Object> map = list.get(i);
                        map.put("index", (page-1)*limit+i+1);
                        if(null != map.get("warnTime")){
                            map.put("warnTime",format.format(map.get("warnTime")));
                        }else{
                            map.put("warnTime","");
                        }
                    }
                }
                result.put("list", list);
                result.put("total", tWarnmsgMapper.getWarnMsgListCount(params));
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("company/getZdList")
    @ResponseBody
    public JSONObject getZdList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user){
                Integer page = 1;
                Integer limit = 10;
                Map<String, Object> params = new HashMap<String,Object>();
                String companyId = request.getParameter("companyId");
                String sensorType = request.getParameter("sensorType");
                /*if(org.apache.commons.lang3.StringUtils.isNotBlank(companyName)){
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                    params.put("companyName", companyName);
                }*/
                String pages = request.getParameter("page");
                String limits = request.getParameter("limit");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(pages)){
                    page = Integer.parseInt(pages);
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(limits)){
                    limit = Integer.parseInt(limits);
                }
                //setUserId(user, params);
                if (user.getUserType() == 5) {
                    params.put("userId", user.getId());
                }else{
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(companyId)){
                        params.put("userId", Integer.parseInt(companyId));
                    }
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(sensorType)){
                    params.put("sensorType", Integer.parseInt(sensorType));
                }
                if(user.getUserType() == 9){
                    params.put("utp", 9);
                }
                params.put("type", 3);
                params.put("page", (page-1)*limit);
                params.put("limit", limit);
                List<Map<String, Object>> list = tWarnmsgMapper.getWarnMsgList(params);
                if(null != list && !list.isEmpty()){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    for(int i = 0;i<list.size();i++){
                        Map<String, Object> map = list.get(i);
                        map.put("index", (page-1)*limit+i+1);
                        if(null != map.get("warnTime")){
                            map.put("warnTime",format.format(map.get("warnTime")));
                        }else{
                            map.put("warnTime","");
                        }
                    }
                }
                result.put("list", list);
                result.put("total", tWarnmsgMapper.getWarnMsgListCount(params));
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 高危企业列表
    * @param request
    * @param model
    * @return
     */
    @RequestMapping("company/toGwCompanyList")
    public String toGwCompanyList(HttpServletRequest request,Model model){
        String districtId = request.getParameter("districtId");
        String townId = request.getParameter("townId");
        String villageId = request.getParameter("villageId");
        model.addAttribute("districtId", districtId);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        return "village/newWarn/gwCompanyList";
    }
    
    @RequestMapping("company/toWaterWarnCompanyList")
    public String toWaterWarnCompanyList(HttpServletRequest request,Model model){
        String districtId = request.getParameter("districtId");
        String townId = request.getParameter("townId");
        String villageId = request.getParameter("villageId");
        model.addAttribute("districtId", districtId);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        return "village/newWarn/wwCompanyList";
    }
    
    /**
     * 高危企业列表
    * @param request
    * @param model
    * @return
     */
    @RequestMapping("company/toZdCompanyList")
    public String toZdCompanyList(HttpServletRequest request,Model model){
        String districtId = request.getParameter("districtId");
        String townId = request.getParameter("townId");
        String villageId = request.getParameter("villageId");
        model.addAttribute("districtId", districtId);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        return "village/newWarn/zdCompanyList";
    }
    
    @RequestMapping("company/getWaterWarnCompanyList")
    @ResponseBody
    public JSONObject getWaterWarnCompanyList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user){
                Integer page = 1;
                Integer limit = 10;
                Map<String, Object> params = new HashMap<String,Object>();
                String companyName = request.getParameter("companyName");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(companyName)){
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                    params.put("companyName", companyName);
                }
                String pages = request.getParameter("page");
                String limits = request.getParameter("limit");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(pages)){
                    page = Integer.parseInt(pages);
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(limits)){
                    limit = Integer.parseInt(limits);
                }
                String districtId = request.getParameter("districtId");
                String townId = request.getParameter("townId");
                String villageId = request.getParameter("villageId");
                if(org.apache.commons.lang3.StringUtils.isBlank(districtId) && org.apache.commons.lang3.StringUtils.isBlank(townId) 
                        && org.apache.commons.lang3.StringUtils.isBlank(villageId)){
                    setUserId(user, params);
                    if(user.getUserType() == 9){
                        params.put("utp", 9);
                    }
                }else{
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(villageId)){
                        params.put("villageId", villageId);
                    }else if(org.apache.commons.lang3.StringUtils.isNotBlank(townId)){
                        params.put("townId", townId);
                    }else if(org.apache.commons.lang3.StringUtils.isNotBlank(districtId)){
                        params.put("districtId", districtId);
                    }
                }
                //params.put("type", 2);
                params.put("page", (page-1)*limit);
                params.put("limit", limit);
                List<Map<String, Object>> list = tWarnmsgMapper.getWaterWarnCompanyList(params);
                if(null != list && !list.isEmpty()){
                    for(int i = 0;i<list.size();i++){
                        Map<String, Object> map = list.get(i);
                        map.put("index", (page-1)*limit+i+1);
                       // map.put("companyName",userGroupMapper.getCompanyNameByUserId((Integer)map.get("companyId")));
                    }
                }
                result.put("list", list);
                result.put("total", tWarnmsgMapper.getWaterWarnCompanyListCount(params).size());
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("company/getGwCompanyList")
    @ResponseBody
    public JSONObject getGwCompanyList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user){
                Integer page = 1;
                Integer limit = 10;
                Map<String, Object> params = new HashMap<String,Object>();
                String companyName = request.getParameter("companyName");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(companyName)){
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                    params.put("companyName", companyName);
                }
                String pages = request.getParameter("page");
                String limits = request.getParameter("limit");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(pages)){
                    page = Integer.parseInt(pages);
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(limits)){
                    limit = Integer.parseInt(limits);
                }
                String districtId = request.getParameter("districtId");
                String townId = request.getParameter("townId");
                String villageId = request.getParameter("villageId");
                if(org.apache.commons.lang3.StringUtils.isBlank(districtId) && org.apache.commons.lang3.StringUtils.isBlank(townId) 
                        && org.apache.commons.lang3.StringUtils.isBlank(villageId)){
                    setUserId(user, params);
                    if(user.getUserType() == 9){
                        params.put("utp", 9);
                    }
                }else{
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(villageId)){
                        params.put("villageId", villageId);
                    }else if(org.apache.commons.lang3.StringUtils.isNotBlank(townId)){
                        params.put("townId", townId);
                    }else if(org.apache.commons.lang3.StringUtils.isNotBlank(districtId)){
                        params.put("districtId", districtId);
                    }
                }
                params.put("type", 2);
                params.put("page", (page-1)*limit);
                params.put("limit", limit);
                List<Map<String, Object>> list = tWarnmsgMapper.getWarnMsgCompanyList(params);
                if(null != list && !list.isEmpty()){
                    for(int i = 0;i<list.size();i++){
                        Map<String, Object> map = list.get(i);
                       // map.put("companyName",userGroupMapper.getCompanyNameByUserId((Integer)map.get("companyId")));
                        map.put("index", (page-1)*limit+i+1);
                    }
                }
                result.put("list", list);
                result.put("total", tWarnmsgMapper.getWarnMsgCompanyListCount(params).size());
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("company/getZdCompanyList")
    @ResponseBody
    public JSONObject getZdCompanyList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user){
                Integer page = 1;
                Integer limit = 10;
                Map<String, Object> params = new HashMap<String,Object>();
                String companyName = request.getParameter("companyName");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(companyName)){
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                    params.put("companyName", companyName);
                }
                String pages = request.getParameter("page");
                String limits = request.getParameter("limit");
                if(org.apache.commons.lang3.StringUtils.isNotBlank(pages)){
                    page = Integer.parseInt(pages);
                }
                if(org.apache.commons.lang3.StringUtils.isNotBlank(limits)){
                    limit = Integer.parseInt(limits);
                }
                String districtId = request.getParameter("districtId");
                String townId = request.getParameter("townId");
                String villageId = request.getParameter("villageId");
                if(org.apache.commons.lang3.StringUtils.isBlank(districtId) && org.apache.commons.lang3.StringUtils.isBlank(townId) 
                        && org.apache.commons.lang3.StringUtils.isBlank(villageId)){
                    setUserId(user, params);
                    if(user.getUserType() == 9){
                        params.put("utp", 9);
                    }
                }else{
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(villageId)){
                        params.put("villageId", villageId);
                    }else if(org.apache.commons.lang3.StringUtils.isNotBlank(townId)){
                        params.put("townId", townId);
                    }else if(org.apache.commons.lang3.StringUtils.isNotBlank(districtId)){
                        params.put("districtId", districtId);
                    }
                }
                params.put("type", 3);
                params.put("page", (page-1)*limit);
                params.put("limit", limit);
                List<Map<String, Object>> list = tWarnmsgMapper.getWarnMsgCompanyList(params);
                if(null != list && !list.isEmpty()){
                    for(int i = 0;i<list.size();i++){
                        Map<String, Object> map = list.get(i);
                        //map.put("companyName",userGroupMapper.getCompanyNameByUserId((Integer)map.get("companyId")));
                        map.put("index", (page-1)*limit+i+1);
                    }
                }
                result.put("list", list);
                result.put("total", tWarnmsgMapper.getWarnMsgCompanyListCount(params).size());
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("getDangerCount")
    @ResponseBody
    public JSONObject getDangerCount(HttpServletRequest request){
        JSONObject result = new JSONObject();
        User user = getLoginUser(request);
        Map<String, Object> mm = new HashMap<String,Object>();
        String districtId = request.getParameter("districtId");
        String townId = request.getParameter("townId");
        String villageId = request.getParameter("villageId");
        if(org.apache.commons.lang3.StringUtils.isBlank(districtId) && org.apache.commons.lang3.StringUtils.isBlank(townId) 
                && org.apache.commons.lang3.StringUtils.isBlank(villageId)){
            setUserId(user, mm);
            if(user.getUserType() == 9){
                mm.put("utp", 9);
            }
        }else{
            if(org.apache.commons.lang3.StringUtils.isNotBlank(villageId)){
                mm.put("villageId", villageId);
            }else if(org.apache.commons.lang3.StringUtils.isNotBlank(townId)){
                mm.put("townId", townId);
            }else if(org.apache.commons.lang3.StringUtils.isNotBlank(districtId)){
                mm.put("districtId", districtId);
            }
        }
        
      //model.addAttribute("totalCount", tWarnmsgMapper.getZdAndGwTotal(mm));
        mm.put("type", 2);
        result.put("gwTotal", tWarnmsgMapper.getWarnMsgListCount(mm));
        mm.put("type", 3);
        result.put("zdTotal", tWarnmsgMapper.getWarnMsgListCount(mm));
        return result;
    }
    
    @RequestMapping("syncWarnMsg")
    @ResponseBody
    public JSONObject syncWarnMsg(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String deviceSn = request.getParameter("deviceSn");
        if(org.apache.commons.lang3.StringUtils.isNotBlank(deviceSn)){
            final Integer companyId = tDeviceMapper.getCompanyIdByDeviceSn(deviceSn);
            if(null != companyId){
                final Map<String, Object> map = userGroupMapper.getCompanyInfoById(companyId);
                result.put("status", "success");
                final SocketClient client = new SocketClient();
                //String host = request.getServerName()+":"+request.getServerPort();
                //String uri = "ws://"+host+"/village/websocket/"+companyId;
                //client.start(uri);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client.sendMessage("|"+companyId+"|"+" ");
                            if(null != map){
                                if(map.get("villageId") != null){
                                    client.sendMessage("|"+map.get("villageId")+"|"+" ");
                                }
                                if(map.get("townId") != null){
                                    client.sendMessage("|"+map.get("townId")+"|"+" ");
                                }
                                if(map.get("districtId") != null){
                                    client.sendMessage("|"+map.get("districtId")+"|"+" ");
                                }
                            }
                        } catch (IOException e) {
                            log.error(e.getMessage(),e);
                        }
                    }
                }).start();;

            }
        }
        return result;
    }
}
