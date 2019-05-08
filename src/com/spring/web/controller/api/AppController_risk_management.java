package com.spring.web.controller.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.spring.web.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;

@Controller
@RequestMapping(value = "api/risk/manage")
public class AppController_risk_management extends BaseController{

    /**
     * (获取体系文件)
     * TODO 登陆
     */
    @RequestMapping("A001")
    public @ResponseBody AppResult getTixiwenjian(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        // baseController
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }

        Company company = companyMapper.selectByPrimaryKey(userId);
        if (null == company || StringUtils.isBlank(company.getIndustry())) {
            result.setData(null);
            return result;
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        JSONArray array = new JSONArray();
        AImplementation im = aImplementationMapper.selectByPrimaryKey(userId);
        if(null != im) {
            JSONObject object = new JSONObject();
            
             object.put("title", "颁布令");
             object.put("text", this.getBanbuling(company, format.format(date)));
            object.put("type", 1);
            array.add(object);
            object = new JSONObject();
            if(StringUtils.isNotBlank(im.getTextz1())){
                try
                {
                    object.put("text", this.getRenMingText(company,im.getTextz1().split("\\|")));
                }
                catch (Exception e)
                {
                    object.put("text", this.getRenMingText(company,new String[]{"","",""}));
                    log.error(e.getMessage(),e);
                }
            }else{
                object.put("text", this.getRenMingText(company,new String[]{"","",""}));
            }
            object.put("type", 2);
            object.put("title", "任命书");
            array.add(object);
            object = new JSONObject();
            
            
            String zuzhijiagou = "";
            if(StringUtils.isNotBlank(im.getTextz1())) {
                try
                {
                    zuzhijiagou = this.getZuzhiText(im.getTextz1().split("\\|"));
                }
                catch (Exception e)
                {
                    zuzhijiagou =  this.getZuzhiText(new String[]{"","",""});
                    log.error(e.getMessage(),e);
                }
            } else {
                zuzhijiagou =  this.getZuzhiText(new String[]{"","",""});
            }
            object.put("type", 3);
            if(StringUtils.isNotBlank(im.getText1())){
                object.put("title", "一、组织机构及职责");
                object.put("text", zuzhijiagou + im.getText1());
            }else{
                object.put("title", "一、组织机构及职责");
                object.put("text", zuzhijiagou + this.getWenjian(request, "editor1.html"));
            }
            array.add(object);
            object = new JSONObject();
            object.put("type", 4);
            if(StringUtils.isNotBlank(im.getText2())){
                object.put("title", "二、《双控机制》运行管理责任制");
                object.put("text", im.getText2());
            }else{
                object.put("title", "二、《双控机制》运行管理责任制");
                object.put("text", this.getWenjian(request, "editor2.html"));
            }
            array.add(object);
            object = new JSONObject();
            object.put("type", 5);
            if(StringUtils.isNotBlank(im.getText3())){
                object.put("title", "三、风险分级管控及作业指导书");
                object.put("text", im.getText3());
            }else{
                object.put("title", "三、风险分级管控及作业指导书");
                object.put("text", this.getWenjian(request, "editor3.html"));
            }
            array.add(object);
            object = new JSONObject();
            object.put("type", 6);
            if(StringUtils.isNotBlank(im.getText4())){
                object.put("title", "四、隐患排查治理及作业指导书");
                object.put("text", im.getText4());
            }else{
                object.put("title", "四、隐患排查治理及作业指导书");
                object.put("text", this.getWenjian(request, "editor4.html"));
            }
            array.add(object);
            object = new JSONObject();
            object.put("type", 7);
            if(StringUtils.isNotBlank(im.getText5())){
                object.put("title", "五、考核");
                object.put("text", im.getText5());
            }else{
                object.put("title", "五、考核");
                object.put("text", this.getWenjian(request, "editor5.html"));
            }
            array.add(object);
            object = new JSONObject();
            object.put("type", 8);
            if(StringUtils.isNotBlank(im.getText6())){
                object.put("title", "六、持续改进");
                object.put("text", im.getText6());
            }else{
                object.put("title", "六、持续改进");
                object.put("text", this.getWenjian(request, "editor6.html"));
            }
            array.add(object);
        } else {
            JSONObject object = new JSONObject();
            object.put("type", 1);
            object.put("title", "颁布令");
            object.put("text", this.getBanbuling(company, format.format(date)));
            array.add(object);
            object = new JSONObject();
            object.put("type", 2);
            object.put("text", this.getRenMingText(company,new String[]{"","",""}));
            object.put("title", "任命书");
            array.add(object);
            object = new JSONObject();
            object.put("type", 3);
            object.put("title", "一、组织机构及职责");
            String zuzhijiagou = "";
            zuzhijiagou = this.getZuzhiText(new String[]{"","",""});
            object.put("text", zuzhijiagou + this.getWenjian(request, "editor1.html"));
            array.add(object);
            object = new JSONObject();
            object.put("type", 4);
            object.put("title", "二、《双控机制》运行管理责任制");
            object.put("text", this.getWenjian(request, "editor2.html"));
            array.add(object);
            object = new JSONObject();
            object.put("type", 5);
            object.put("title", "三、风险分级管控及作业指导书");
            object.put("text", this.getWenjian(request, "editor3.html"));
            array.add(object);
            object = new JSONObject();
            object.put("type", 6);
            object.put("title", "四、隐患排查治理及作业指导书");
            object.put("text", this.getWenjian(request, "editor4.html"));
            array.add(object);
            object = new JSONObject();
            object.put("type", 7);
            object.put("title", "五、考核");
            object.put("text", this.getWenjian(request, "editor5.html"));
            array.add(object);
            object = new JSONObject();
            object.put("type", 8);
            object.put("title", "六、持续改进");
            object.put("text", this.getWenjian(request, "editor6.html"));
            array.add(object);
        }
        result.setData(array);
        return result;
    }
    
    /**
     * 
     * getRiskFenbu(获取风险分布表)
     */
    @RequestMapping("A002")
    public @ResponseBody AppResult getRiskFenbu(HttpServletRequest request, Integer type) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        JSONObject object = new JSONObject();
        Company company = companyMapper.selectByPrimaryKey(userId);
        object.put("company", company);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", type);
        m.put("uid", userId);
        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
        for (Map<String, Object> ad : acL) {
            String l1 = String.valueOf(ad.get("level1"));
            String l2 = String.valueOf(ad.get("level2"));
            if (null == list.get(l1)) {
                list.put(l1, new LinkedHashSet<String>());
            }
            Set<String> s = list.get(l1);
            s.add(l2);
        }
        object.put("rows", list);
        if(!acL.isEmpty()){
            for(Map<String, Object> map : acL){
                if(null != map.get("hxys")){
                    map.put("wlys", map.get("hxys").toString().replaceAll("!@#", ","));
                    map.remove("hxys");
                }
                if(null != map.get("material")){
                    map.put("material", map.get("material").toString().replaceAll("!@#", ","));
                }
                if(null != map.get("gy")){
                    map.put("gy", map.get("gy").toString().replaceAll("!@#", ","));
                }
                if(null != map.get("wlbs")){
                    map.put("wlbs", map.get("wlbs").toString().replaceAll("!@#", ","));
                }
            }
        }
        object.put("list", acL);
        object.put("type", type);
        
        result.setData(object);
        return result;
    }
    
    /**
     * 
     * getPingguResult(获取评估结果)
     */
    @RequestMapping("A003")
    public @ResponseBody AppResult getPingguResult(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        JSONObject object = new JSONObject();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", userId);
        m.put("order", 1);
        object.put("list", aCompanyManualMapper.selectByMap(m));
        object.put("company", companyMapper.selectByPrimaryKey(userId));
        
        result.setData(object);
        return result;
    }
    
    /**
     * 
     * getSearchCondition(获取村镇列表)
     */
    @RequestMapping("A004")
    public @ResponseBody AppResult getSearchCondition(HttpServletRequest request,Integer townId) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        JSONObject object = new JSONObject();
        if(null != townId){
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", townId);
            object.put("list", villageMapper.selectListByTown(m));
        }else{
            Map<String, Object> m = new HashMap<String, Object>();
            User user = userMapper.selectByPrimaryKey(userId);
            setUserId(user, m);
            if (user.getUserType() == 3) {//镇
                object.put("list", villageMapper.selectListByTown(m));
            }
            if (user.getUserType() == 6) {//区
                object.put("list", townMapper.selectListByDistrict(m));
            }
        }
        result.setData(object);
        return result;
    }
    
    /**
     * 
     * getFengxianHuizong(风险汇总)
     */
    @RequestMapping("A005")
    public @ResponseBody AppResult getFengxianHuizong(HttpServletRequest request,Integer townId, Integer villageId,
            String companyName, String level,Integer page,Integer limit,Integer companyId) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        JSONObject object = new JSONObject();
        try
        {
            if(null == page){
                page = 1;
            }
            if(null == limit){
                limit = 10;
            }
            Map<String, Object> m = new HashMap<String, Object>();
            if(null != companyId){
                m.put("userId", companyId);
            }else{
                if (null != townId) {
                    m.put("townId", townId);
                }
                if (null != villageId) {
                    m.put("villageId", villageId);
                }
                setUserId(user, m);
            }
            
            m.put("page", (page-1)*limit);
            m.put("limit", limit);
            if(StringUtils.isNotBlank(companyName)){
                companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                m.put("companyName", companyName);
            }
            if(StringUtils.isNotBlank(level)){
                level = URLDecoder.decode(URLDecoder.decode(level, "utf-8"),"utf-8");
                m.put("level", level);
            }
            //新增的方法
            object.put("list", aCompanyManualMapper.selectByMap2Limit(m));
            object.put("total", aCompanyManualMapper.selectByMap2LimitCount(m));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
        }
        
        result.setData(object);
        return result;
    }
    
    /**
     * 
     * getStatistics(获取统计数据)
     */
    @RequestMapping("A006")
    public @ResponseBody AppResult getStatistics(HttpServletRequest request,Integer townId) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        JSONObject object = new JSONObject();
        User user = userMapper.selectByPrimaryKey(userId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap2(m);
        
        List<Library> ll = libraryMapper.selectLibraryList(1);//行业
        
        String[] industrys = new String[ll.size()];
        Integer[] gg1 = new Integer[ll.size()];//红
        Integer[] gg2 = new Integer[ll.size()];//橙
        Integer[] gg3 = new Integer[ll.size()];//黄
        Integer[] gg4 = new Integer[ll.size()];//蓝
        for(int i=0;i<ll.size();i++) {
            industrys[i] = ll.get(i).getName();
            gg1[i] = 0;
            gg2[i] = 0;
            gg3[i] = 0;
            gg4[i] = 0;
        }
        
        Integer[] flag1 = new Integer[]{0,0,0,0};//部位 数值顺序对应 红橙黄蓝
        for(Map<String, Object> l : list) {
            String level = "";
            if(null != l.get("level")) {
                level = (String)l.get("level");
            }
            setcompany_manualCount(level, flag1);
            String industry = null;
            if(null != l.get("industry")) {
                industry = l.get("industry").toString();
            }
            if(null != industry && !"".equals(industry)) {
                for(int i=0;i<industrys.length;i++) {
                    if(industry.equals(industrys[i])) {
                        if("红色".equals(level)) {
                            gg1[i] = gg1[i] + 1;
                        } else if("橙色".equals(level)) {
                            gg2[i] = gg2[i] + 1;
                        } else if("黄色".equals(level)) {
                            gg3[i] = gg3[i] + 1;
                        } else if("蓝色".equals(level)) {
                            gg4[i] = gg4[i] + 1;
                        }
                        break;
                    }
                }
            }
        }
        object.put("dangers", flag1);
        object.put("industrys", industrys);
        object.put("gg1", gg1);
        object.put("gg2", gg2);
        object.put("gg3", gg3);
        object.put("gg4", gg4);
        result.setData(object);
        return result;
    }
    
    
    /**
     * 
     * getOrgPart(获取组织架构)
     */
    @RequestMapping("A007")
    public @ResponseBody AppResult getOrgPart(HttpServletRequest request,Integer userId) {
        AppResult result = new AppResultImpl();
        Integer userIds = getAppUserId(request);
        if (null == userIds) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        if(null == userId){
            userId = userIds;
        }
        JSONArray array = new JSONArray();
        List<ZzjgCompany> list = zzjgCompanyMapper.selectAll(userId);
        if(!list.isEmpty()){
            for(ZzjgCompany company : list){
                JSONObject object = new JSONObject();
                ZzjgDepartment zzjgDepartment = new ZzjgDepartment();
                object.put("company", company);
                zzjgDepartment.setCid(company.getId());
                zzjgDepartment.setLevel(1);
                List<ZzjgDepartment> bumenList = zzjgDepartmentMapper.selectByCompanyId(zzjgDepartment);
                if(!bumenList.isEmpty()){
                    JSONArray bumenArr = new JSONArray();
                    for(ZzjgDepartment department : bumenList){
                        JSONObject bObject = new JSONObject();
                        bObject.put("department", department);
                        ZzjgDepartment zzjgDepartment2 = new ZzjgDepartment();
                        zzjgDepartment2.setPid(department.getId());
                        zzjgDepartment2.setLevel(2);
                        List<ZzjgDepartment> banzuList = zzjgDepartmentMapper.selectByCompanyId(zzjgDepartment2);
                        bObject.put("banzuList", banzuList);
                        bumenArr.add(bObject);
                    }
                    object.put("departmentList", bumenArr);
                }
                
                array.add(object);
            }
        }
        result.setData(array);
        return result;
    }



    /**
     *
     * getOrgPart(获取组织架构)
     */
    @RequestMapping("organization")
    public @ResponseBody AppResult organization(HttpServletRequest request,Integer userId) {
        AppResult result = new AppResultImpl();
        Integer userIds = getAppUserId(request);
        if (null == userIds) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        if(null == userId){
            userId = userIds;
        }
        JSONArray array = new JSONArray();
        ZzjgPersonnel   zzjgPersonnel  = zzjgPersonnelMapper.selectByPrimaryKey(userId);
        List<ZzjgCompany> list = zzjgCompanyMapper.selectAll(zzjgPersonnel.getCid());
        if(!list.isEmpty()){
            for(ZzjgCompany company : list){
                JSONObject object = new JSONObject();
                ZzjgDepartment zzjgDepartment = new ZzjgDepartment();
                object.put("company", company);
                zzjgDepartment.setCid(company.getId());
                zzjgDepartment.setLevel(1);
                List<ZzjgDepartment> bumenList = zzjgDepartmentMapper.selectByCompanyId(zzjgDepartment);
                if(!bumenList.isEmpty()){
                    JSONArray bumenArr = new JSONArray();
                    for(ZzjgDepartment department : bumenList){
                        JSONObject bObject = new JSONObject();
                        bObject.put("department", department);
                        ZzjgDepartment zzjgDepartment2 = new ZzjgDepartment();
                        zzjgDepartment2.setPid(department.getId());
                        zzjgDepartment2.setLevel(2);
                        List<ZzjgDepartment> banzuList = zzjgDepartmentMapper.selectByCompanyId(zzjgDepartment2);
                        bObject.put("banzuList", banzuList);
                        bumenArr.add(bObject);
                    }
                    object.put("departmentList", bumenArr);
                }

                array.add(object);
            }
        }
        result.setData(array);
        return result;
    }


    /**
     * 
     * getUserList(获取部门人员列表)
     */
    @RequestMapping("A008")
    public @ResponseBody AppResult getUserList(HttpServletRequest request,Integer userId,Integer cid,Integer dpid, Integer did, String name,Integer page,Integer limit) {
        AppResult result = new AppResultImpl();
        Integer userIds = getAppUserId(request);
        if (null == userIds) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        if(null == userId){
            userId = userIds;
        }
        if(null == page){
            page = 1;
        }
        if(null == limit){
            limit = 10;
        }
        try
        {
            LlHashMap<Object, Object> m = getLlMap();
            m.put("page", (page-1)*limit);
            m.put("limit", limit);
            if(null != cid){
                m.put("cid", cid);
            }
            if(null != dpid){
                m.put("dpid", dpid);
            }
            if(null != did){
                m.put("did", did);
            }
            m.put("uid", userId);
            if(StringUtils.isNotBlank(name)){
                name = URLDecoder.decode(URLDecoder.decode(name, "utf-8"),"utf-8");
                m.put("name", name);
            }
            List<LlHashMap<Object, Object>> userList = zzjgPersonnelMapper.selectByMapLimit(m);
            JSONObject object = new JSONObject();
            object.put("list", userList);
            object.put("total", zzjgPersonnelMapper.selectByMapCount(m));
            result.setData(object);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 
     * getFengxianCompany(获取风险企业)
     */
    @RequestMapping("A009")
    public @ResponseBody AppResult getFengxianCompany(HttpServletRequest request,Integer townId, Integer villageId, String companyName,String level) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        try
        {
            User user = userMapper.selectByPrimaryKey(userId);
            Map map = new HashMap();
            setUserId(user, map);
            if(StringUtils.isNotBlank(companyName)){
                companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                map.put("companyName", companyName);
            }
            if(StringUtils.isNotBlank(level)){
                level = URLDecoder.decode(URLDecoder.decode(level, "utf-8"),"utf-8");
                map.put("level", level);
            }
            if(null != townId){
                map.put("townId", townId);
            }
            if(null != villageId){
                map.put("villageId", villageId);
            }

            result.setData(aCompanyManualMapper.selectDangerCompanyList(map));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    void setcompany_manualCount(String level, Integer[] arr) {
        if("红色".equals(level)) {
            arr[0] = arr[0] + 1;
        } else if("橙色".equals(level)) {
            arr[1] = arr[1] + 1;
        } else if("黄色".equals(level)) {
            arr[2] = arr[2] + 1;
        } else if("蓝色".equals(level)) {
            arr[3] = arr[3] + 1;
        }
    }
    
    public String getWenjian(HttpServletRequest request,String fileName){
        StringBuffer text = new StringBuffer();
        try
        {
            String path = request.getSession().getServletContext().getRealPath("/html/editor");
            File file = new File(path +File.separator+ fileName);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), "utf-8");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    text.append(lineTxt);         
                 }       
                read.close();
            } else {
                log.error("can not find file"+fileName);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return text.toString();
    }
    
    String getRenMingText(Company company,String[] per){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String html = "";
        html += "<div class=\"detail-plan mt-20\">";
        html += "        <p style=\"text-indent: 37px; text-align: center; line-height: 2em;\">";
        html += "            <span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">任</span></span><span style=\";font-family:宋体;font-size:19px\">&nbsp;&nbsp;</span><span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">命</span></span><span style=\";font-family:宋体;font-size:19px\">&nbsp;&nbsp;</span><span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">书</span></span>";
        html += "        </p>";
        html += "        <p style=\"text-indent: 37px; line-height: 2em;\">";
        html += "            <span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">各部门：</span></span>";
        html += "        </p>";
        html += "        <p style=\"text-indent: 37px; line-height: 2em;\">";
        html += "            <span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">为使公司《双控机制》体系有效运行，强化对《双控机制》的管理，现对公司《双控机制》领导小组的组成作出如下任命：</span></span>";
        html += "        </p>";
        html += "        <p style=\"text-indent: 37px; line-height: 2em;\">";
        html += "            <span style=\"font-family:宋体;font-size:19px\">1、</span><span style=\"font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">任命</span> <span class=\"m1\">"+per[0]+"</span><i class=\"Hui-iconfont chosi\" >&#xe60c;</i><span style=\"font-family:宋体\">为公司《双控机制》领导小组组长</span></span>";
        html += "        </p>";
        html += "        <p style=\"margin-left: 0px; text-indent: 37px; line-height: 2em;\">";
        html += "            <span style=\"font-family:宋体;font-size:19px\">2、</span><span style=\"font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">任命</span> <span class=\"m2\">"+per[1]+"</span><i class=\"Hui-iconfont chosi\" >&#xe60c;</i><span style=\"font-family:宋体\">为公司《双控机制》领导小组副组长</span></span>";
        html += "        </p>";
        html += "        <p style=\"margin-left: 0px; text-indent: 37px; line-height: 2em;\">";
        html += "            <span style=\"font-family:宋体;font-size:19px\">3、</span><span style=\"font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">任命</span> <span class=\"m3\">"+per[2]+"</span><i class=\"Hui-iconfont chosi\" >&#xe60c;</i><span style=\"font-family:宋体\">为领导小组成员</span></span>";
        html += "        </p>";
        html += "        <p style=\"text-align: justify; line-height: 2em;\">";
        html += "            <span style=\";font-family:宋体;font-size:19px\">&nbsp;</span>";
        html += "        </p>";
        html += "        <p style=\"text-align: justify; line-height: 2em;text-indent: 37px;\">";
        html += "            <span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">特此任命</span></span>";
        html += "        </p>";
        html += "        <p style=\"text-align: justify; line-height: 2em;\">";
        html += "            <span style=\";font-family:宋体;font-size:19px\">&nbsp;</span>";
        html += "        </p>";
        html += "        <p style=\"text-align:right;\">";
        html += "            <strong><span style=\"font-family: 宋体;font-size: 20px\"><span style=\"font-family:宋体\">"+company.getName()+"</span></span></strong>";
        html += "        </p>";
        html += "        <p style=\"text-align:right;\">";
        html += "            <span style=\";font-family:宋体;font-size:19px\" >"+format.format(new Date())+"</span>";
        html += "        </p>";
        html += "        <p style=\"text-align: justify; line-height: 2em;\">";
        html += "   <span style=\";font-family:宋体;font-size:19px\"><br/></span>";
        html += "  </p>";
        html += "</div>";
            return html;
    }
    
    private  String getBanbuling(Company company,String time){
        String html = "";
        html += "<div class=\"detail-plan mt-20\">";
        html += "        <h1 style=\"text-align:center\">";
        html += "        <span style=\";font-family:宋体;font-weight:normal;font-size:19px\"><span style=\"font-family:宋体\">颁</span></span><span style=\";font-family:宋体;font-weight:normal;font-size:19px\">&nbsp;</span><span style=\";font-family:宋体;font-weight:normal;font-size:19px\"><span style=\"font-family:宋体\">布</span></span><span style=\";font-family:宋体;font-weight:normal;font-size:19px\">&nbsp;</span><span style=\";font-family:宋体;font-weight:normal;font-size:19px\"><span style=\"font-family:宋体\">令</span></span>";
        html += "        </h1>";
        html += "<p>";
        html += "<span style=\";font-family:宋体;font-size:19px\">&nbsp;</span>";
        html += "</p>";
        html += "<p>";
        html += "<span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">各部门：</span></span>";
        html += "</p>";
        html += "<p style=\"text-indent:37px\">";
        html += "<span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">根据《中共中央、国务院关于推荐安全生产领域改革发展的意见》（中发【</span></span><span style=\";font-family:宋体;font-size:19px\">2016】32号）、《国务院安委会办公室关于实施遏制重特大事故工作指南，构建双重预防机制的意见》（安委办【2016】11号）、《省安委办公室关于印发江苏省遏制重特大事故构建双重预防机制实施办法的通知》（苏安办【2016】103号）的规定，按照国家相关标准规范，结合办单位实际，编制了《风险分级管控、隐患排查治理》（以下简称“双控机制”管理体系文件，经公司安全生产领导小组审议并通过，现予颁布，并于颁布之日起生效。</span><span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">。</span></span>";
        html += "</p>";
        html += "<p style=\"text-indent:37px\">";
        html += "<span style=\";font-family:宋体;font-size:19px\">&nbsp;</span>";
        html += "</p>";
        html += "<p style=\"text-indent:37px\">";
        html += "<span style=\";font-family:宋体;font-size:19px\">&nbsp;</span>";
        html += "</p>";
        html += "<p style=\"text-indent:37px\">";
        html += "<span style=\";font-family:宋体;font-size:19px\">&nbsp;</span>";
        html += "</p>";
        html += "<p style=\"text-indent:37px\">";
        html += "<span style=\";font-family:宋体;font-size:19px\">&nbsp;</span>";
        html += "</p>";
        html += "<p style=\"text-align:right;\">";
        html += "<span style=\";font-family:宋体;font-size:19px\"><strong>"+company.getName() +"</strong></span>";
        html += "</p>";
        html += "<p style=\"text-align:right;\">";
        html += "<span style=\";font-family:宋体;font-size:19px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-family:宋体\">总经理："+company.getLegal() +"</span></span>";
        html += "</p>";
        html += "<p style=\"text-align:right;\">";
        html += "<span style=\";font-family:宋体;font-size:19px\" contenteditable=\"true\">"+time+"</span>";
        html += "</p>";
        html += "<p>";
        html += "<br/>";
        html += "</p>";
        html += "</div>";
        return html;
    }
    
    private String getZuzhiText(String[] per){
        String html = "";
        html +=" <div class=\"detail-plan mt-20\">";
        html +="        <p style=\"text-align: center; line-height: 2em;\">";
        html +="            <strong><span style=\"font-family: 宋体;font-size: 19px\"><span style=\"font-family:宋体\">一、组织机构及职责</span></span></strong>";
        html +="        </p>";
        html +="        <p style=\"text-align: justify; text-indent: 37px; line-height: 2em;\">";
        html +="            <span style=\"font-family:宋体;font-size:19px\">1、</span><span style=\"font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">组织机构：为加强</span>“双控”体系建设工作的组织领导，确保取得实效，成立“双控”体系建设工作领导小组，对工作进行全面组织、指导和检查。</span>";
        html +="        </p>";
        html +="        <p style=\"text-indent: 56px; text-align: justify; line-height: 2em;\">";
        html +="            <span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">组长：</span><span style=\"font-family:宋体\" class=\"m1\">"+per[0]+"</span></span>";
        html +="        </p>";
        html +="        <p style=\"text-indent: 56px; text-align: justify; line-height: 2em;\">";
        html +="           <span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">副组长：</span><span style=\"font-family:宋体\" class=\"m2\">"+per[1]+"</span></span>";
        html +="        </p>";
        html +="       <p style=\"text-indent: 56px; text-align: justify; line-height: 2em;\">";
        html +="            <span style=\";font-family:宋体;font-size:19px\"><span style=\"font-family:宋体\">组员：</span><span style=\"font-family:宋体\" class=\"m3\">"+per[2]+"</span></span>";
        html +="        </p>";
        html +="      </div>";
              return html;
    }
}
