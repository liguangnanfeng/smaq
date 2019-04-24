/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.BaseController;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.ACompanyManual;
import com.spring.web.model.ACompanyManualZp;
import com.spring.web.model.ADangerManual;
import com.spring.web.model.AFxgzp;
import com.spring.web.model.AFxjz;
import com.spring.web.model.AGwyj;
import com.spring.web.model.AGwyjSh;
import com.spring.web.model.AGzk;
import com.spring.web.model.AImplementation;
import com.spring.web.model.AMaterial;
import com.spring.web.model.AWhp;
import com.spring.web.model.AZytjfxcdpj;
import com.spring.web.model.Company;
import com.spring.web.model.Material;
import com.spring.web.model.Product;
import com.spring.web.model.Regulation;
import com.spring.web.model.User;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.trouble.TroubleService;

/**
 * @Title: CompanyController_cd
 * @Description: TODO(用一句话描述该文件做什么)
 * @author CGF
 * @date 2017年7月27日 上午9:49:51
 * @version V1.0
 */
@Controller
@RequestMapping("company/safety-system")
public class CompanyController_safety extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 6784473257577263720L;
    @Autowired
    private TroubleService troubleService;
    @Autowired
    private CgfService cgfService;

    /** 安全风险管控系统 开始 */
    /**
     * 判断安全风险管控 是否存在
     */
    @RequestMapping(value = "check")
    public @ResponseBody Result check(HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            result.setMap("c", -1);
            result.setMap("ind", "");
        } else {
            List<ADangerManual> dL = aDangerManualMapper.selectByIndustry(company.getIndustry());
            result.setMap("c", dL.size());
            result.setMap("ind", company.getIndustry());
        }
        return result;
    }

    /**
     * 风险辨识
     */
    @RequestMapping(value = "risk-list")
    public String riskList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        model.addAttribute("company", company);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", type);
        m.put("flag", "1,3");
        m.put("uid", user.getId());
        List<Map<Object, Object>> zzjg = zzjgDepartmentMapper.selectLevel2ByUid(user.getId());
        if(null == type) {
//        	m.put("order", 1);
            List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
            model.addAttribute("zzjgDep", zzjg);
            model.addAttribute("acL", acL);
            return "company/safety-system/risk-list1";
        }
        
        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMapGroupByLevel1Level2(m);
        Iterator<Map<String, Object>> it = acL.iterator();
        while(it.hasNext()){
            Map<String, Object> ac = it.next();
            String level1 = ac.get("level1").toString();
            String level2 = ac.get("level2").toString();
            boolean has = false;
            for(Map<Object, Object> zz : zzjg) {
                String p = zz.get("parName").toString();
                String name = zz.get("name").toString();
                if(p.equals(level1) && name.equals(level2)) {
                    has = true;
                    break;
                }
            }
            
            if(!has) {
                it.remove();
            }
        }
        
//        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
//        for (Map<String, Object> ad : acL) {
//            String l1 = String.valueOf(ad.get("level1"));
//            String l2 = String.valueOf(ad.get("level2"));
//            if (null == list.get(l1)) {
//                list.put(l1, new LinkedHashSet<String>());
//            }
//            Set<String> s = list.get(l1);
//            s.add(l2);
//        }
//        model.addAttribute("list", list);
        model.addAttribute("dL", acL);
        model.addAttribute("type", type);
        return "company/safety-system/risk-list1";
    }
    
    /**
     * 风险辨识确定危害因素
     */
    @RequestMapping(value = "risk-list-load2")
    public String riskListLoad2(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("flag", "1");
        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMapGroupByLevel1Level2(m);
//        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
//        for (Map<String, Object> ad : acL) {
//            String l1 = String.valueOf(ad.get("level1"));
//            String l2 = String.valueOf(ad.get("level2"));
//            if (null == list.get(l1)) {
//                list.put(l1, new LinkedHashSet<String>());
//            }
//            Set<String> s = list.get(l1);
//            s.add(l2);
//        }
//        model.addAttribute("list", list);
        model.addAttribute("dL", acL);
        model.addAttribute("type", type);
        return "company/safety-system/risk-list1-load2";
    }
    
    /**
     * 一般和较小
     */
    @RequestMapping(value = "assess-list")
    public String assessList(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        model.addAttribute("company", company);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("flag", "3");
        List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list", acL);
        model.addAttribute("depL", zzjgDepartmentMapper.selectLevel2ByUid(user.getId()));
        model.addAttribute("shL", aGwyjShMapper.selectAll());
        return "company/safety-system/assess-list";
    }
    
    /**
     * 设置风险因素
     */
    @RequestMapping(value = "set-risk")
    public String setRisk(Model model, Integer id, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url",  request.getRequestURI() + "?id=" + id);
            return "company/safety-system/type";
        }
        model.addAttribute("company", company);
        model.addAttribute("ac", aCompanyManualMapper.selectByPrimaryKey(id));
        return "company/safety-system/set-risk";
    }
    
    /**
     * 设置风险因素-保存
     */
    @RequestMapping(value = "set-risk-edit")
    public @ResponseBody Result setRiskEdit(Model model, ACompanyManual ac, 
            HttpServletRequest request,
            String gzkhxys, String gzkwlys) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ACompanyManual a = aCompanyManualMapper.selectByPrimaryKey(ac.getId());
        
        ac.setUid(a.getUid());
        ac.setLevel1(a.getLevel1());
        ac.setLevel2(a.getLevel2());
        aCompanyManualMapper.updateByL1L2(ac);
        
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        
        if(null != gzkhxys || null != gzkwlys) {
            AGzk gzk = aGzkMapper.selectByPrimaryKey(ac.getId());
            if(null == gzk) {
                gzk = new AGzk(ac.getId(), gzkhxys, gzkwlys);
                aGzkMapper.insertSelective(gzk);
            } else {
                gzk.setHxys(gzkhxys);
                gzk.setWlys(gzkwlys);
                aGzkMapper.updateByPrimaryKeySelective(gzk);
            }
        }
        
        //处理两重点一重大的
        if(null != ac.getGy()) {
            Regulation r = regulationGet(user.getId());
            if(null == r) {
                r = new Regulation();
                r.setUserId(user.getId());
                if("".equals(ac.getGy())) {
                    r.setProcess(0);
                } else {
                    r.setProcess(1);
                }
                r.setProcessName(ac.getGy());
                regulationMapper.insertSelective(r);
            } else {
                if(!"".equals(ac.getGy())) {
                    r.setProcess(1);
                    String process_name = r.getProcessName();
                    if(StringUtils.isBlank(process_name)) {
                        r.setProcessName(ac.getGy());
                        regulationMapper.updateByPrimaryKeySelective(r);
                    } else {
                        String[] gys = ac.getGy().split("!@#");
                        for(String gy : gys) {
                            if(process_name.indexOf(gy) == -1) {
                                process_name = process_name + "," + gy;
                            }
                        }
                        r.setProcessName(process_name);
                        regulationMapper.updateByPrimaryKeySelective(r);
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * 设置风险因素
     */
    @RequestMapping(value = "set-choose")
    public String setChoose(Model model, Integer id, Integer type, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("ac", aCompanyManualMapper.selectByPrimaryKey(id));
        model.addAttribute("aGzk", aGzkMapper.selectByPrimaryKey(id));
        model.addAttribute("type", type);
        if(type.intValue() == 1) {//物理因素
            model.addAttribute("list", aHxysgzkMapper.selectAll());
        }
        if(type.intValue() == 2) {//化学因素
            List<AMaterial> list = aMaterialMapper.selectAll();//化学因素
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("userId", user.getId());
            List<Material> mL = materialMapper.selectByUserId(m);//原辅材料
            List<Product> pL = productMapper.selectByUserId(m);//生产产品
            
            List<AMaterial> list2 = new ArrayList<AMaterial>();
            for(AMaterial ama : list) {
                Boolean x = false;
                for(Material ma : mL) {
                    if(ma.getMaterial().indexOf(ama.getName()) > -1 || 
                            ama.getName().indexOf(ma.getMaterial()) > -1) {
                        list2.add(ama);
                        x = true;
                        break;
                    }
                }
                if(x) {
                    continue;
                }
                for(Product p : pL) {
                    if(p.getProductName().indexOf(ama.getName()) > -1 || 
                            ama.getName().indexOf(p.getProductName()) > -1) {
                        list2.add(ama);
                        break;
                    }
                }
            }
            
            model.addAttribute("list", list2);
        }
        if(type.intValue() == 3) {//工艺
            model.addAttribute("list", aGyMapper.selectAll());
        }
        if(type.intValue() == 4) {//物料
            Set<String> list = new HashSet<String>();
            
            List<AMaterial> amL = aMaterialMapper.selectAll();//化学因素
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("userId", getLoginUser(request).getId());
            List<Material> mL = materialMapper.selectByUserId(m);//原辅材料
            List<Product> pL = productMapper.selectByUserId(m);//生产产品
            
            for(Material ma : mL) {
                for(AMaterial ama : amL) {
                    if(ma.getMaterial().indexOf(ama.getName()) > -1 || 
                            ama.getName().indexOf(ma.getMaterial()) > -1) {
                        list.add(ma.getMaterial());
                        break;
                    }
                }
            }
            for(Product p : pL) {
                for(AMaterial ama : amL) {
                    if(p.getProductName().indexOf(ama.getName()) > -1 || 
                            ama.getName().indexOf(p.getProductName()) > -1) {
                        list.add(p.getProductName());
                        break;
                    }
                }
            }
            
            model.addAttribute("list", list);
        }
        if(type.intValue() == 5) {//高危作业
            Company company = companyMapper.selectByPrimaryKey(user.getId());
            if(StringUtils.isBlank(company.getDangers())) {
                model.addAttribute("list", new ArrayList<String>());
            } else {
                model.addAttribute("list", company.getDangers().split(","));
            }
        }
        return "company/safety-system/set-risk-load";
    }
    
    /**
     * 职业危害物理因素分布表
     */
    @RequestMapping(value = "risk-list2")
    public String riskList2(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url",  request.getRequestURI());
            return "company/safety-system/type";
        }
        AGzk gzk = aGzkMapper.selectByPrimaryKey(user.getId());
        if(null != gzk) {
            if(StringUtils.isNotBlank(gzk.getWlys())) {//物理因素
                model.addAttribute("list", aHxysgzkMapper.selectByIds(gzk.getWlys()));
            }
        }
        model.addAttribute("wlysL", aHxysgzkMapper.selectAll());
        model.addAttribute("hxysL", aMaterialMapper.selectAll());
        model.addAttribute("gzk", gzk);
        return "company/safety-system/risk-list2";
    }
    
    /**
     * 职业危害化学因素分布表
     */
    @RequestMapping(value = "risk-list3")
    public String riskList3(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        AGzk gzk = aGzkMapper.selectByPrimaryKey(user.getId());
        if(null != gzk) {
            if(StringUtils.isNotBlank(gzk.getHxys())) {//化学因素
                model.addAttribute("list", aMaterialMapper.selectByIds(gzk.getHxys()));
            }
        }
        model.addAttribute("hxysL", aMaterialMapper.selectAll());
        model.addAttribute("gzk", gzk);
        return "company/safety-system/risk-list3";
    }
    
    /**
     * 告知卡
     */
    @RequestMapping(value = "harm-list")
    public String gzk(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<AGzk> gzk = aGzkMapper.selectByUid(user.getId());
        Set<String> s = new HashSet<String>();
        for(AGzk a : gzk) {
            if(StringUtils.isNotBlank(a.getWlys())) {
                s.addAll(Arrays.asList(a.getWlys().split(",")));
            }
        }
        if(s.size() > 0) {//物理因素
            model.addAttribute("wlysL", aHxysgzkMapper.selectByIds(StringUtils.join(s, ",")));
        }
        return "company/safety-system/harm-list";
    }
    
    /**
     * 主要物质理化、危险、有害特性表
     */
    @RequestMapping(value = "risk-information-list2")
    public String riskInformationList2(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<AGzk> gzk = aGzkMapper.selectByUid(user.getId());
        Set<String> s = new HashSet<String>();
        for(AGzk a : gzk) {
            if(StringUtils.isNotBlank(a.getHxys())) {
                s.addAll(Arrays.asList(a.getHxys().split(",")));
            }
        }
        if(s.size() > 0) {//物理因素
            model.addAttribute("list", aMaterialMapper.selectByIds(StringUtils.join(s, ",")));
        }
        return "company/safety-system/risk-information-list2";
    }
    

    /**
     * 主要物质理化、危险、有害特性表
     */
    @RequestMapping(value = "risk-information-detail2")
    public String riskInformationDetail2(Model model, Integer id) throws Exception {
        model.addAttribute("be", aMaterialMapper.selectByPrimaryKey(id));
        return "company/safety-system/risk-information-detail2";
    }
    
    /**
     * 实施方案
     */
    @RequestMapping(value = "assess6")
    public String assess6(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        model.addAttribute("company", company);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("flag", 1);
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
        model.addAttribute("list", list);
        model.addAttribute("dL", acL);
        return "company/safety-system/assess6";
    }
    
    /**
     * 风险告知牌
     */
    @RequestMapping(value = "risk-information-list")
    public String riskInformationList(Model model, HttpServletRequest request, Integer flag) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI() + "flag=" + flag);
            return "company/safety-system/type";
        }
        
        //所有风险
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        //m.put("flag2", 1);
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
            model.addAttribute("flag", flag);
            return "company/safety-system/risk-information-list_0";
        }
        
        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> shList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();
        
        //公司级公告牌
        if(flag.intValue() == 1) {
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
            return "company/safety-system/risk-information-list_1";
        }
        
        //车间级
        if(flag.intValue() == 2) {
            Set<String> nameL = new HashSet<String>();//二级分类作为名称
            for(Map<String, Object> ac : acL_f) {
                String level1 = getStringFromMap(ac, "level1");
//                String level2 = getStringFromMap(ac, "level2");
//                String gkzt = getStringFromMap(ac, "gkzt");
//                if(StringUtils.isBlank(gkzt)) {
//                    continue;
//                }
//                nameL.add(gkzt);
                if(StringUtils.isBlank(level1)) {
                    continue;
                }
                nameL.add(level1);
            }
            
            if(nameL.size() == 0) {//没有重大较大隐患
                model.addAttribute("flag", flag);
                return "company/safety-system/risk-information-list_0";
            }
            model.addAttribute("nameL", nameL);
            return "company/safety-system/risk-information-list_2";
        }
        
        //岗位级
        if(flag.intValue() == 3) {
            Set<String> nameL = new HashSet<String>();//二级分类作为名称
            for(Map<String, Object> ac : acL_f) {
                String level1 = getStringFromMap(ac, "level1");
                String level2 = getStringFromMap(ac, "level2");
//                String level3 = getStringFromMap(ac, "level3");
                if(StringUtils.isBlank(level1)) {
                    continue;
                }
                nameL.add(level1 + "/" + level2);
//                if(StringUtils.isBlank(level1)) {
//                    nameL.add(level3);
//                } else {
//                    nameL.add(level1 + "/" + level2 + "/" + level3);
//                }
            }
            
            if(nameL.size() == 0) {//没有重大较大隐患
                model.addAttribute("flag", flag);
                return "company/safety-system/risk-information-list_0";
            }
            model.addAttribute("nameL", nameL);
            return "company/safety-system/risk-information-list_3";
        }
        
        m.put("flag", flag);
        model.addAttribute("flag", flag);
        model.addAttribute("list", aFxgzpMapper.selectByMap(m));
        return "company/safety-system/risk-information-list1";
    }
    
    //设置风险等级，事故类型，风险因素，措施
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
    
    /**
     * 风险告知牌
     */
    @RequestMapping(value = "fxggp-load")
    public String fxggpLoad(Model model, HttpServletRequest request, String name) throws Exception {
        User user = getLoginUser(request);
        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> adanerTypeList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("name", name);
        //m.put("flag2", 1);
        AFxgzp be = aFxgzpMapper.selectByName(m);
        if(null == be || be.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
            if(null == be) {
                be = new AFxgzp();
            }
            be.setFlag(2);
            be.setUid(user.getId());
            be.setGlzrr(company.getSafetyM());//责任人默认为安全管理员
            be.setCtime(new Date());
            be.setDel(0);
            be.setIsedit(0);
            be.setName(name);
            
            
            m.put("uid", user.getId());
            m.put("levels", new String[]{"红色","橙色"});
            List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
            //所有重大较大风险
            List<Map<String, Object>> acL_f = new ArrayList<Map<String,Object>>();
            for(Map<String, Object> ac : acL) {
                String level1 = getStringFromMap(ac, "level1");
                if(StringUtils.isBlank(level1)) {
                  continue;
                }
                if((level1).equals(name)) {
                  acL_f.add(ac);
                }
//                String level1 = getStringFromMap(ac, "level1");
//                String level2 = getStringFromMap(ac, "level2");
//                if(StringUtils.isBlank(level1)) {
//                    continue;
//                }
//                if((level1 + "/" + level2).equals(name)) {
//                    acL_f.add(ac);
//                }
//                String gkzt = getStringFromMap(ac, "gkzt");
//                if(StringUtils.isBlank(gkzt)) {
//                    continue;
//                }
//                if(gkzt.equals(name)) {
//                    acL_f.add(ac);
//                }
            }
            
            setGgpXx(acL_f, be, adanerTypeList);
            
            if(null == be.getId()) {
                aFxgzpMapper.insertSelective(be);
            } else {
                aFxgzpMapper.updateByPrimaryKeySelective(be);
            }
            
        }
        model.addAttribute("be", be);
        return "company/safety-system/risk-information-list-load";
    }
    
    /**
     * 风险告知牌
     */
    @RequestMapping(value = "fxggp-load3")
    public String fxggpLoad3(Model model, HttpServletRequest request, String name) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("name", name);
        //m.put("flag2", 1);
        AFxgzp be = aFxgzpMapper.selectByName(m);
        //事故类型列表，用于处理其中的风险
        List<AGwyjSh> adanerTypeList = aGwyjShMapper.selectAll();
        //List<String> adanerTypeList = aDangerTypeMapper.selectAll();
        if(null == be || be.getIsedit().intValue() == 0) {//没有公告牌或没有修改过，重新生成
            if(null == be) {
                be = new AFxgzp();
            }
            be.setFlag(3);
            be.setUid(user.getId());
            be.setCtime(new Date());
            be.setDel(0);
            be.setIsedit(0);
            be.setName(name);
            
            m.put("uid", user.getId());
            m.put("levels", new String[]{"红色","橙色"});
            List<Map<String, Object>> acL = aCompanyManualMapper.selectByMap(m);
            //所有重大较大风险
            List<Map<String, Object>> acL_f = new ArrayList<Map<String,Object>>();
            for(Map<String, Object> ac : acL) {
                String level1 = getStringFromMap(ac, "level1");
                String level2 = getStringFromMap(ac, "level2");
//                String level3 = getStringFromMap(ac, "level3");
                if(StringUtils.isBlank(level1)) {
                    continue;
                }
                if((level1 + "/" + level2).equals(name)) {
                    acL_f.add(ac);
                    continue;
                }
//                if(StringUtils.isBlank(level1)) {
//                    if(level3.equals(name)) {
//                        acL_f.add(ac);
//                    }
//                } else {
//                    if((level1 + "/" + level2 + "/" + level3).equals(name)) {
//                        acL_f.add(ac);
//                        break;
//                    }
//                }
               
            }
            
            be.setGlzrr((String)acL_f.get(0).get("fjgkfzr"));//责任人
            
            setGgpXx(acL_f, be, adanerTypeList);
            if(null == be.getId()) {
                aFxgzpMapper.insertSelective(be);
            } else {
                aFxgzpMapper.updateByPrimaryKeySelective(be);
            }
            
        }
        model.addAttribute("be", be);
        return "company/safety-system/risk-information-list-load";
    }
    
    
    String getStringFromMap(Map<String, Object> m, String key) {
        Object o = m.get(key);
        if(null != o) {
            return o.toString();
        }
        return null;
    }
    
    /**
     * 风险告知牌
     */
    @RequestMapping(value = "risk-information-detail")
    public String riskInformationDetail(Model model, HttpServletRequest request, Integer id) throws Exception {
        model.addAttribute("be", aFxgzpMapper.selectByPrimaryKey(id));
        return "company/safety-system/risk-information-detail";
    }
    
    /**
     * 风险告知牌
     */
    @RequestMapping(value = "risk-information-add")
    public String riskInformationAdd(Model model, HttpServletRequest request, Integer id) throws Exception {
        model.addAttribute("be", aFxgzpMapper.selectByPrimaryKey(id));
        return "company/safety-system/risk-information-add";
    }

    /**
     * 实施方案
     */
    @RequestMapping(value = "risk-list-load")//确认风险操作
    public String riskListLoad(Model model, HttpServletRequest request, String industry,
           Integer depId) throws Exception {
        User user = getLoginUser(request);
        if(StringUtils.isNotBlank(industry)) {
            industry = utf8Str(industry);
        }
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("company", company);
        if(StringUtils.isBlank(industry)) {
            industry = company.getIndustry();
            if(industry.equals("化工企业（危险化学品生产、经营、使用）、加油站")) {
                if(company.getName().contains("油")) {
                    industry = "加油站";
                }
            }
        }
        model.addAttribute("industry", industry);
        List<ADangerManual> dL = aDangerManualMapper.selectByIndustry(industry);
        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
        for (ADangerManual ad : dL) {
            String l1 = ad.getLevel1();
            String l2 = ad.getLevel2();
            if (null == list.get(l1)) {
                list.put(l1, new LinkedHashSet<String>());
            }
            Set<String> s = list.get(l1);
            s.add(l2);
        }
        model.addAttribute("list", list);
        model.addAttribute("dL", dL);
        model.addAttribute("depId", depId);
        log.error("risk-list-load depId:"+depId);
        return "company/safety-system/risk-list1-load";
    }

    /**
     * 实施方案
     */
    @RequestMapping(value = "plan-add")
    public String companyList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        if(null != im) {
            model.addAttribute("im", gson.toJson(im));
        } else {
            model.addAttribute("im", gson.toJson(new AImplementation()));
        }
        model.addAttribute("type", type);
        return "company/safety-system/plan-add";
    }

    /**
     * 化工企业-危险化学品生产储存企业安全风险评估诊断分级指南
     */
    @RequestMapping(value = "assess1")
    public String assess1(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        model.addAttribute("bean", aWhpMapper.selectByPrimaryKey(user.getId()));
        return "company/safety-system/assess1";
    }

    /**
     * 作业条件风险程度评价
     */
    @RequestMapping(value = "assess2")
    public String assess2(Model model, Integer id) throws Exception {
//        model.addAttribute("bean", aZytjfxcdpjMapper.selectByPrimaryKey(id));
//        model.addAttribute("id", id);
        return "company/safety-system/assess2";
    }

    /**
     * 风险矩阵法
     */
    @RequestMapping(value = "assess3")
    public String assess3(Model model, Integer id) throws Exception {
//        model.addAttribute("bean", aFxjzMapper.selectByPrimaryKey(id));
//        model.addAttribute("id", id);
        return "company/safety-system/assess3";
    }

    /**
     * 危险化学品生产储存企业安全风险评估诊断分级指南
     */
    @RequestMapping(value = "assess-bei")
    public String assessBei(Model model, String industry, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        if (StringUtils.isEmpty(industry)) {
            industry = company.getIndustry();
        }

        //industry = utf8Str(industry);
        List<ADangerManual> dL = aDangerManualMapper.selectByIndustry(industry);
        Map<String, Set<String>> list = new LinkedHashMap<String, Set<String>>();
        for (ADangerManual ad : dL) {
            String l1 = ad.getLevel1();
            String l2 = ad.getLevel2();
            if (null == list.get(l1)) {
                list.put(l1, new LinkedHashSet<String>());
            }
            Set<String> s = list.get(l1);
            s.add(l2);
        }
        model.addAttribute("list", list);
        model.addAttribute("industry", industry);
        return "company/safety-system/assess-bei";
    }

    /**
     * 风险矩阵法
     */
    @RequestMapping(value = "assess4")
    public String assess4(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        model.addAttribute("list", aCompanyManualMapper.selectByMap(m));
        return "company/safety-system/assess4";
    }
    
    /**
     * 重大风险
     */
    @RequestMapping(value = "assess5")
    public String assess5(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (StringUtils.isEmpty(company.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("flag", 2);
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);
        
        if("化工企业（危险化学品生产、经营、使用）、加油站".equals(company.getIndustry())) {
            model.addAttribute("list", list);
            return "company/safety-system/assess5-2";
        }
        model.addAttribute("company", company);
        model.addAttribute("list", gson.toJson(list));
        return "company/safety-system/assess5";
    }
    
    /**
     * 重大风险 化工企业选择
     */
    @RequestMapping(value = "assess5-2-choose")
    public String assess52Choose(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("flag", 2);
        model.addAttribute("list", gson.toJson(aCompanyManualMapper.selectByMap(m)));
        return "company/safety-system/assess5-2-choose";
    }

    /**
     * 分级管控
     */
    @RequestMapping(value = "control-list")
    public String controlList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("order", 1);
        
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list", list);
        Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>();
        
        for(Map<String, Object> m1 : list) {
            String level1 = null == m1.get("level1") ? "" : m1.get("level1").toString();
            String level2 = null == m1.get("level2") ? "" : m1.get("level2").toString();
            LinkedHashSet<String> l2s = levmap.get(level1);
            if(null == l2s) {
                l2s = new LinkedHashSet<String>();
                levmap.put(level1, l2s);
            }
            l2s.add(level2);
        }
        model.addAttribute("treeMap", levmap);
        
        model.addAttribute("type", type);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
        
        model.addAttribute("departL", zzjgDepartmentMapper.selectLevel1ByUid(user.getId()));//组织架构部门班组
        
        LlHashMap<Object, Object> llm = getLlMap();
        llm.put("uid", user.getId());
        model.addAttribute("perL", zzjgPersonnelMapper.selectByMap(llm));//组织架构部门班组
        return "company/safety-system/control-list";
    }
    
    /**
     * 体系文件 组织机构及职责
     */
    @RequestMapping(value = "plan-add2")
    public String planAdd2(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("im", im);
        model.addAttribute("now", new Date());
        if(null != im && StringUtils.isNotBlank(im.getTextz1())) {
            model.addAttribute("per", im.getTextz1().split("\\|"));
        } else {
            model.addAttribute("per", new String[]{"","",""});
        }
        return "company/safety-system/plan-add2";
    }
    
    /**
     * 体系文件 组织机构及职责
     */
    @RequestMapping(value = "plan-add1")
    public String planAdd1(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        LlHashMap<Object, Object> llm = getLlMap();
        llm.put("uid", user.getId());
        model.addAttribute("perL", zzjgPersonnelMapper.selectByMap(llm));//组织架构部门班组
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("im", im);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
        model.addAttribute("now", new Date());
        if(null != im && StringUtils.isNotBlank(im.getTextz1())) {
            model.addAttribute("per", im.getTextz1().split("\\|"));
        } else {
            model.addAttribute("per", new String[]{"","","",""});
        }
        return "company/safety-system/plan-add1";
    }
    
    /**
     * 体系文件 颁布令
     */
    @RequestMapping(value = "plan-add0")
    public String planAdd0(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        LlHashMap<Object, Object> llm = getLlMap();
        llm.put("uid", user.getId());
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("im", im);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
        model.addAttribute("now", new Date());
        return "company/safety-system/plan-add0";
    }
    
    /**
     * 评估结果
     */
//    @RequestMapping(value = "control-list2")
//    public String controlList2(Model model, HttpServletRequest request, String flag) throws Exception {
//        User user = getLoginUser(request);
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("uid", user.getId());
//        m.put("order", 1);
//        model.addAttribute("list", aCompanyManualMapper.selectByMap(m));
//        model.addAttribute("company", companyMapper.selectByPrimaryKey(user.getId()));
//        model.addAttribute("flag", flag);
//        return "company/safety-system/control-list2";
//    }
    @RequestMapping(value = "control-list2")//zhangcl 2018.10.13
    public String controlList2(Model model, HttpServletRequest request, String flag) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
//        m.put("order", 1);

        model.addAttribute("company", company);
        model.addAttribute("user", userMapper.selectByPrimaryKey(company.getUserId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(company.getVillageId()));
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list", list);
 
        Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>();    
        for(Map<String, Object> m1 : list) {
            String level1 = null == m1.get("level1") ? "" : m1.get("level1").toString();
            //log.error("level1：-----------start------------"+level1);
            String level2 = null == m1.get("level2") ? "" : m1.get("level2").toString();
            //log.error("level2："+level2);
            LinkedHashSet<String> l2s = levmap.get(level1);
            if(null == l2s) {
                l2s = new LinkedHashSet<String>();
                //log.error("level1："+level1);
                levmap.put(level1, l2s);
                //log.error("levmap："+levmap.toString());
            }
            l2s.add(level2);
            //log.error("l2s：------------end-----------"+l2s.toString());
        }
        //log.error("levmap："+levmap.toString());  
        model.addAttribute("treeMap", levmap);
        model.addAttribute("flag", flag);
        
        /*
         * 添加根据风险等级查询，查询结果可能会受到其他修改风险等级功能的影响	wz_20181116
         */
        m.put("level", "红色");
        List<Map<String, Object>> list11 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list11", list11);
        
        m.put("level", "橙色");
        List<Map<String, Object>> list22 = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list22", list22);
        
        if(flag.equals("2")){
        	//log.error("zhangcl 2018.10.18 controlList3,area_range="+company.getAreaRange());
        	return "company/safety-system/control-list3";
        }
        else{
        	return "company/safety-system/control-list2";
        }
        
    }
    
    /**
     * 处理风险标点
     */
    @RequestMapping(value = "control-list3")//zhangcl 2018.10.13
    public String controlList3(Model model, HttpServletRequest request, Integer id, String lnglat) throws Exception {
    	//log.error("zhangcl 2018.10.13 controlList3,id="+id+",lnglat="+lnglat);
    	aCompanyManualMapper.updateLnglat(id,lnglat);
    	return controlList2(model, request, "2");
    }
    
    @RequestMapping(value = "area-range-save")
    public String AreaRangeSave(Model model, HttpServletRequest request, String arearange) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        company.setAreaRange(arearange);
        companyMapper.updateByPrimaryKeySelective(company);
        return controlList2(model, request, "2");
    }

	/**
     * 实施方案-保存
     */
    @RequestMapping(value = "plan-save")
    public @ResponseBody Result companySave(HttpServletRequest request, AImplementation i) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        AImplementation im = aImplementationMapper.selectByPrimaryKey(user.getId());
        i.setUid(user.getId());
        if (null == im) {
            aImplementationMapper.insertSelective(i);
        } else {
            aImplementationMapper.updateByPrimaryKeySelective(i);
        }
        return result;
    }

    /**
     * 企业所属行业-保存
     */
    @RequestMapping(value = "company-industry")
    public @ResponseBody Result companyIndustry(HttpServletRequest request, String industry) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company c = new Company();
        c.setUserId(user.getId());
        c.setIndustry(industry);
        companyMapper.updateByPrimaryKeySelective(c);
        return result;
    }

    /**
     * 企业风险辨识-保存
     */
    @RequestMapping(value = "aCompanyManual-save")
    public @ResponseBody Result aCompanyManualSave(HttpServletRequest request, ACompanyManual be) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        if (null == be.getId()) {
            be.setDel(0);
            be.setCtime(new Date());
            aCompanyManualMapper.insertSelective(be);
        } else {
            aCompanyManualMapper.updateByPrimaryKeySelective(be);
        }
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }
    
    /**
     * 企业风险辨识-删除
     */
    @RequestMapping(value = "aCompanyManual-del")
    public @ResponseBody Result aCompanyManualDel(HttpServletRequest request, String ids) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        log.error("ids:"+ids.toString());
        aCompanyManualMapper.updateDelByIds(ids);
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }

    /**
     * 企业风险辨识-保存,从指导书中选择的
     */
    @RequestMapping(value = "aCompanyManual-save1")
    public @ResponseBody Result aCompanyManualSave1(HttpServletRequest request, Integer[] ids,
            Integer depId) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ZzjgDepartment dep = zzjgDepartmentMapper.selectByPrimaryKey(depId);
        ZzjgDepartment parDep = zzjgDepartmentMapper.selectByPrimaryKey(dep.getPid());
        String level1 = parDep.getName();
        String level2 = dep.getName();
        
        LlHashMap<Object, Object> lm = new LlHashMap<Object, Object>();
        lm.put("dpid", dep.getPid());
        List<LlHashMap<Object, Object>> personL = zzjgPersonnelMapper.selectByMap(lm);
        String fjgkfzr = "";//分级管控负责人
        if(personL.size() > 0) {
            fjgkfzr = personL.get(0).getString("name");
        }
        List<ACompanyManual> list = aDangerManualMapper.selectByIds(ids);
        for(ACompanyManual a : list) {
            a.setLevel3(a.getLevel1() + "/" + a.getLevel2() + "/" + a.getLevel3());
            a.setLevel1(level1);
            a.setLevel2(level2);
            a.setGkzt(level1);//管控主体
            a.setFjgkfzr(fjgkfzr);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("issys", 0);
        m.put("del", 0);
        m.put("ctime", new Date());
        m.put("del", 0);
        m.put("list", list);
        //m.put("flag", 1);
        aCompanyManualMapper.insertBath(m);
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }

    /**
     * 危险化学品生产储存企业安全风险评估诊断分级指南（试行）-保存
     */
    @RequestMapping(value = "assess1-save")
    public @ResponseBody Result assess1Save(HttpServletRequest request, AWhp be) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        AWhp a = aWhpMapper.selectByPrimaryKey(user.getId());
        if (null != a) {
            aWhpMapper.updateByPrimaryKeySelective(be);
        } else {
            aWhpMapper.insertSelective(be);
        }
        return result;
    }

    /**
     * 企业风险辨识-保存
     */
    @RequestMapping(value = "assess2-save")
    public @ResponseBody Result assess2Save(HttpServletRequest request, AZytjfxcdpj be) throws Exception {
        Result result = new ResultImpl();
        AZytjfxcdpj a = aZytjfxcdpjMapper.selectByPrimaryKey(be.getUid());
        if (null != a) {
            aZytjfxcdpjMapper.updateByPrimaryKeySelective(be);
        } else {
            aZytjfxcdpjMapper.insertSelective(be);
        }
        
        String level = be.getLev();
        Integer id = be.getUid();
        AFxjz af = aFxjzMapper.selectByPrimaryKey(id);
        if(null != af) {
            level = getDanger(af.getLev(), level);
        }
        ACompanyManualZp zp = aCompanyManualZpMapper.selectByPrimaryKey(id);
        if(null != zp) {
            level = getDanger(zp.getLevel(), level);
        }
        
        ACompanyManual acm = new ACompanyManual();
        acm.setId(id);
        acm.setLevel(level);
        aCompanyManualMapper.updateByPrimaryKeySelective(acm);
        aCompanyManualMapper.updateCompanyDlevel(getLoginUser(request).getId());
        return result;
    }
    
    String getDanger(String danger, String danger1) {
        if(StringUtils.isBlank(danger)) {
            return danger1;
        }
        if(StringUtils.isBlank(danger1)) {
            return danger;
        }
        Map<String, Integer> mm = new HashMap<String, Integer>();
        mm.put("红色", 4);
        mm.put("橙色", 3);
        mm.put("黄色", 2);
        mm.put("蓝色", 1);
        if(mm.get(danger1).compareTo(mm.get(danger)) > 0) {
            return danger1;
        }
        return danger;
    }

    /**
     * 风险举证-保存
     */
    @RequestMapping(value = "assess3-save")
    public @ResponseBody Result assess3Save(HttpServletRequest request, AFxjz be) throws Exception {
        Result result = new ResultImpl();
        AFxjz a = aFxjzMapper.selectByPrimaryKey(be.getUid());
        if (null != a) {
            aFxjzMapper.updateByPrimaryKeySelective(be);
        } else {
            aFxjzMapper.insertSelective(be);
        }
        String level = be.getLev();
        Integer id = be.getUid();
        AZytjfxcdpj aZytjfxcdpj = aZytjfxcdpjMapper.selectByPrimaryKey(id);
        if(null != aZytjfxcdpj) {
            level = getDanger(aZytjfxcdpj.getLev(), level);
        }
        ACompanyManualZp zp = aCompanyManualZpMapper.selectByPrimaryKey(id);
        if(null != zp) {
            level = getDanger(zp.getLevel(), level);
        }
        ACompanyManual acm = new ACompanyManual();
        acm.setId(id);
        acm.setLevel(level);
        aCompanyManualMapper.updateByPrimaryKeySelective(acm);
        aCompanyManualMapper.updateCompanyDlevel(getLoginUser(request).getId());
        return result;
    }
    
    /**
     * 一般与较小自评
     */
    @RequestMapping(value = "zp-save")
    public @ResponseBody Result zpSave(Integer id, String level, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
//        AFxjz af = aFxjzMapper.selectByPrimaryKey(id);
//        if(null != af) {
//            level = getDanger(af.getLev(), level);
//        }
//        AZytjfxcdpj aZytjfxcdpj = aZytjfxcdpjMapper.selectByPrimaryKey(id);
//        if(null != aZytjfxcdpj) {
//            level = getDanger(aZytjfxcdpj.getLev(), level);
//        }
        
        ACompanyManual acm = new ACompanyManual();
        acm.setId(id);
        acm.setLevel(level);
        aCompanyManualMapper.updateByPrimaryKeySelective(acm);
        aCompanyManualMapper.updateCompanyDlevel(getLoginUser(request).getId());

//        ACompanyManualZp zp = aCompanyManualZpMapper.selectByPrimaryKey(id);
//        if(null == zp) {
//            zp = new ACompanyManualZp(id, level);
//            aCompanyManualZpMapper.insertSelective(zp);
//        } else {
//            zp.setLevel(level);
//            aCompanyManualZpMapper.updateByPrimaryKeySelective(zp);
//        }
        return result;
    }
    
    /**
     * 告知牌-保存
     */
    @RequestMapping(value = "risk-information-save")
    public @ResponseBody Result riskInformationSave(HttpServletRequest request, AFxgzp be) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        if (null != be.getId()) {
            aFxgzpMapper.updateByPrimaryKeySelective(be);
        } else {
            be.setCtime(new Date());
            be.setDel(0);
            aFxgzpMapper.insertSelective(be);
        }
        return result;
    }
    
    /**
     * 告知卡-保存
     */
    @RequestMapping(value = "gzk-save")
    public @ResponseBody Result gzkSave(HttpServletRequest request, AGzk be) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        be.setUid(user.getId());
        AGzk g = aGzkMapper.selectByPrimaryKey(user.getId());
        if(null == g) {
            aGzkMapper.insertSelective(be);
        } else {
            aGzkMapper.updateByPrimaryKeySelective(be);
        }
        return result;
    }
    

    /**
     * 重大风险-保存
     */
    @RequestMapping(value = "a-zdfxys-save2")
    public @ResponseBody Result assess5Save2(HttpServletRequest request, String json) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        aCompanyManualMapper.deleteFlag2(user.getId());
        if(StringUtils.isBlank(json)) {
            return result;
        }
        List<LinkedHashMap<String, Object>> l = gson.fromJson(json, List.class);
        for(Map<String, Object> l1 : l) {
            l1.put("level", "红色");
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("issys", 0);
        m.put("del", 0);
        m.put("ctime", new Date());
        m.put("del", 0);
        m.put("list", l);
        m.put("flag", 2);
        aCompanyManualMapper.insertBath(m);
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }
    
    /**
     * 重大风险-保存
     */
    @RequestMapping(value = "a-zdfxys-save")
    public @ResponseBody Result assess5Save(HttpServletRequest request, String zdfx) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        List<ACompanyManual> list = new ArrayList<ACompanyManual>();
        aCompanyManualMapper.deleteFlag2(user.getId());
        
        if(StringUtils.isBlank(zdfx)) {
            return result;
        }
        String[] ll = zdfx.split("!@#");
        for(String l : ll) {
            ACompanyManual c = new ACompanyManual();
            //c.setLevel3(l);
            c.setFactors(l);
            list.add(c);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());
        m.put("issys", 0);
        m.put("del", 0);
        m.put("ctime", new Date());
        m.put("del", 0);
        for(ACompanyManual l1 : list) {
            l1.setLevel("红色");
        }
        m.put("list", list);
        m.put("flag", 2);
        aCompanyManualMapper.insertBath(m);
        aCompanyManualMapper.updateCompanyDlevel(user.getId());
        return result;
    }
    /** 安全风险管控系统 结束 */
    
    /**
     * 岗位应急处置卡
     */
    @RequestMapping(value = "ying-add")
    public String yingAdd(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        List<Map<Object, Object>> depL = zzjgDepartmentMapper.selectLevel2ByUid(user.getId());
        model.addAttribute("depL", depL);
        LlHashMap<Object, Object> llm = getLlMap();
        llm.put("uid", user.getId());
        model.addAttribute("perL", zzjgPersonnelMapper.selectByMap(llm));//组织架构部门班组
        model.addAttribute("shL", aGwyjShMapper.selectAll());//组织架构部门班组
        return "company/safety-system/ying-add";
    }
    
    /**
     * 岗位应急处置卡
     */
    @RequestMapping(value = "ying-load")
    public String yingLoad(Model model, HttpServletRequest request, Integer departId) throws Exception {
        ZzjgDepartment depat = zzjgDepartmentMapper.selectByPrimaryKey(departId);
        AGwyj yj = aGwyjMapper.selectByDepartId(departId);
        if(null == yj) {
            yj = new AGwyj(null, getLoginUser(request).getId(), departId, "", "", "", "");
            aGwyjMapper.insertSelective(yj);
        }
        model.addAttribute("yj", yj);
        model.addAttribute("depat", depat);
        return "company/safety-system/ying-load";
    }
    
    /**
     * 岗位应急处置卡-更新
     */
    @RequestMapping(value = "ying-update")
    public @ResponseBody Result yingUpdate(HttpServletRequest request, AGwyj be) throws Exception {
        Result result = new ResultImpl();
        aGwyjMapper.updateByPrimaryKeySelective(be);
        return result;
    }
}
