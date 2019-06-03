/**  
 * Copyright(c)2017 Wuxi TongXiang Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.Company;
import com.spring.web.model.Detection;
import com.spring.web.model.Evaluation;
import com.spring.web.model.Examination;
import com.spring.web.model.Library;
import com.spring.web.model.LightningProtection;
import com.spring.web.model.Material;
import com.spring.web.model.Mequipment;
import com.spring.web.model.Product;
import com.spring.web.model.ProductionProcessDiagram;
import com.spring.web.model.Regulation;
import com.spring.web.model.Sequipment;
import com.spring.web.model.Sperson;
import com.spring.web.model.Standard;
import com.spring.web.model.TContingencyPlan;
import com.spring.web.model.TDrill;
import com.spring.web.model.Trade;
import com.spring.web.model.TradeClique;
import com.spring.web.model.User;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.SessionUtil;

/**
 * @Title: TradeCliqueController
 * @Description: TODO(行业端——集团型企业)
 * @author WZ
 * @date 2019年1月7日 下午13:`9:42
 * @version V1.0
 */
@Controller
@RequestMapping("/tradeclique")
public class TradeCliqueController extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 2125785439865846208L;
    @Autowired
    private UserService userService;
    @Autowired
    private CgfService cgfService;
    
    /**
     * 行业端——集团企业——所属企业首页，点击——隐藏不必要的信息
     * @param model
     * @param companyId
     */
    public void TradeComapnyHide(Model model,Integer companyId){	
    	if(companyId != null){		
    		model.addAttribute("tradecompanyhide","hide");	
    	}
    }

    /**
     * TODO 风险分布图
     * 集团首页风险分布图
     */
    @RequestMapping(value = "control-list2")
    public String controlList2(Model model, HttpServletRequest request, String flag) throws Exception {
        User user = getLoginUser(request);
        TradeClique clique = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("uid", user.getId());

        model.addAttribute("company", clique);
        model.addAttribute("user", userMapper.selectByPrimaryKey(user.getId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(clique.getVillageId()));
        List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);
        model.addAttribute("list", list);
        m.clear();
        m.put("tradeId", user.getId());
        model.addAttribute("branches", aCompanyManualMapper.selectByMap2(m));
 
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
        model.addAttribute("flag", flag);
        
        /*
         * 添加根据风险等级查询，查询结果可能会受到其他修改风险等级功能的影响	wz_20181116
         */
//        m.put("level", "红色");
//        List<Map<String, Object>> list11 = aCompanyManualMapper.selectByMap(m);
//        model.addAttribute("list11", list11);
//        
//        m.put("level", "橙色");
//        List<Map<String, Object>> list22 = aCompanyManualMapper.selectByMap(m);
//        model.addAttribute("list22", list22);
        
//        if(flag.equals("2")){
        	return "tradeclique/clique/control-list3";
//        }
//        else{
//        	return "company/safety-system/control-list2";
//        }
    }
    /**
     * 处理风险标点
     */
    @RequestMapping(value = "control-list3")
    public String controlList3(Model model, HttpServletRequest request, Integer id, String lnglat) throws Exception {
    	log.error("zhangcl 2018.10.13 controlList3,id="+id+",lnglat="+lnglat);
    	aCompanyManualMapper.updateLnglat(id,lnglat);
    	return controlList2(model, request, "2");
    }
    
    @RequestMapping(value = "area-range-save")
    public String AreaRangeSave(Model model, HttpServletRequest request, String arearange) throws Exception {
        User user = getLoginUser(request);
        TradeClique clique = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        clique.setAreaRange(arearange);
        tradeCliqueMapper.updateByPrimaryKeySelective(clique);
        return controlList2(model, request, "2");
    }
    /**
     * 行业端——集团企业——基本信息页面
     */
	@RequestMapping("clique/basic-information")
    public String basicInformation(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        //Company c = companyMapper.selectByPrimaryKey(user.getId());
        TradeClique tradeClique = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        if(tradeClique == null){
        	TradeClique tc = new TradeClique();
        	Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
        	tc.setUserId(trade.getUserId());
        	tc.setName(trade.getName());
        	tradeCliqueMapper.insertSelective(tc);
        	tradeClique = tc;
        }else{
        	tradeClique.setName(tradeMapper.selectByPrimaryKey(user.getId()).getName());
        	tradeCliqueMapper.updateByPrimaryKeySelective(tradeClique);
        }
        if (null != tradeClique.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(tradeClique.getRegionId()));
        }
        model.addAttribute("c", tradeClique);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "tradeclique/information/clique-information1";
    }
    /**
     * 行业端——集团企业——基本信息修改
     */
    @RequestMapping("clique/basic-information-change")
    public String basicInformationChange(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("TradeCompanyName", tradeMapper.selectByPrimaryKey(user.getId()).getName());
        //Company c = companyMapper.selectByPrimaryKey(user.getId());
        TradeClique c = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        List<Library> list1 = libraryMapper.selectLibraryList(1);
        List<Library> list2 = libraryMapper.selectLibraryList(2);
        model.addAttribute("c", c);
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        user = userMapper.selectByPrimaryKey(user.getId());//定位信息实时
        model.addAttribute("u", user);
        //log.error("user："+user.toString());
        model.addAttribute("ind3L", tIndustryMapper.selectByType(3));// 高危检查类别
        return "tradeclique/information/clique-information1edit";
    }
    /**
     * 行业端——集团企业——基本信息保存
     */
    @RequestMapping(value = "clique/basic-information-save")
    public @ResponseBody Result companyEditDo(TradeClique c, String jw, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        String industry = c.getIndustry();
        //Company company = companyMapper.selectByPrimaryKey(c.getUserId());
        TradeClique tradeClique = tradeCliqueMapper.selectByPrimaryKey(c.getUserId());
        if(tradeClique != null && ! industry.equals(tradeClique.getIndustry())){
            tCompanyMapper.deleteByPrimaryKey(c.getUserId());
        }
        tradeCliqueMapper.updateByPrimaryKeySelective(c);
        User user = null;
        if (StringUtils.isNotBlank(jw)) {
            user = new User();
            user.setId(c.getUserId());
            String[] x = jw.split(",");
            user.setLatitude(x[1]);
            user.setLongitude(x[0]);
            userMapper.updateByPrimaryKeySelective(user);
        }
        //log.error("data："+userMapper.selectByPrimaryKey(user.getId()));
        result.setMap("data", userMapper.selectByPrimaryKey(user.getId()));
        return result;
    }

    /**
     * 集团部门人员汇总
     */
    @RequestMapping(value = "information/user-list")
    public String userList(Model model, HttpServletRequest request, String companyName)throws Exception {
    	User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("tradeId", user.getId());
        m.put("companyName",companyName);
    	List<Map<String, Object>> branches = zzjgPersonnelMapper.selectCountByTrade(m);
    	Map<String, Object> clique = zzjgPersonnelMapper.selectCountForClique(m);
    	branches.add(0, clique);
    	Integer total = 0;
    	for(Map<String, Object> branch : branches){
    		total += Integer.valueOf(branch.get("count").toString());
    	}
    	model.addAttribute("branches",branches);
    	model.addAttribute("total", total);
    	model.addAttribute("companyName", companyName);
    	return "tradeclique/information/user-list";
    }
    /**
     * 集团下属企业部门人员详情
     */
    @RequestMapping(value = "information/user-details")
    public String userDetails(Model model, HttpServletRequest request,Integer userId,
    		Integer cid, Integer dpid, Integer did, String name)
            throws Exception {
        LlHashMap<Object, Object> m = getLlMap();
        m.put("cid", cid);
        m.put("uid", userId);
        m.put("name", name);
        m.put("dpid", dpid);
        m.put("did", did);
        List<LlHashMap<Object, Object>> userList = zzjgPersonnelMapper.selectByMap(m);
        model.addAttribute("list", userList);
        model.addAttribute("m", m);
        model.addAttribute("companyL", zzjgCompanyMapper.selectAll(userId));
        model.addAttribute("userId", userId);
        
        ZzjgDepartment dep = new ZzjgDepartment();
        dep.setUid(userId);
        dep.setLevel(1);
        model.addAttribute("dep1", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 一级部门
        dep.setLevel(2);
        model.addAttribute("dep2", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 二级部门
        return "tradeclique/information/user-details";
    }
    /**
     * 部门人员添加/编辑
     */
    @RequestMapping(value = "information/user-add")
    public String userList(Model model, Integer id, Integer userId, HttpServletRequest request) throws Exception {
        if (null != id) {
            ZzjgPersonnel p = zzjgPersonnelMapper.selectByPrimaryKey(id);
            model.addAttribute("user", p);
        }
        model.addAttribute("companyL", zzjgCompanyMapper.selectAll(userId));
        model.addAttribute("userId", userId);
        ZzjgDepartment dep = new ZzjgDepartment();
        dep.setUid(userId);
        dep.setLevel(1);
        model.addAttribute("dep1", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 一级部门
        dep.setLevel(2);
        model.addAttribute("dep2", gson.toJson(zzjgDepartmentMapper.selectByCompanyId(dep)));// 二级部门
        return "tradeclique/information/user-add";
    }
    /**
     * @param dto
     * @return 员工保存
     * @throws Exception
     */
    @RequestMapping(value = "information/user-save")
    public @ResponseBody Result userSave(ZzjgPersonnel dto, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        Date d = new Date();
        dto.setUtime(d);
        if (null == dto.getId()) {
            dto.setCtime(d);
            dto.setDel(0);
            zzjgPersonnelMapper.insertSelective(dto);
        } else {
            zzjgPersonnelMapper.updateByPrimaryKeySelective(dto);
        }
        return result;
    }
    /**
     * 行业端——集团企业——主要设备设施——页面
     */
    @RequestMapping("product/mequipment-list")
    public String mainEquipmentList(Model model, HttpServletRequest request, String equipmentName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        //setUserId(user, m);
        m.put("tradeId", user.getId());
        List<Integer> userId = null;
        userId = companyMapper.selectByCompany_trade(m);
        userId.add(user.getId());
        m.put("userIds", StringUtils.join(userId, ","));
        //log.error(m.toString());
        
        m.put("equipmentName", equipmentName);
        model.addAttribute("list", mequipmentMapper.selectByUserIdClique(m));
        model.addAttribute("equipmentName", equipmentName);
        return "tradeclique/product/mequipment-list";
    }
    /**
     * 行业端——集团企业——主要设备设施——查看/编辑
     */
    @RequestMapping(value = "product/mequipment-edit")
    public String meqEdit(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("meq", mequipmentMapper.selectByPrimaryKey(id));
        return "tradeclique/product/mequipment-edit";
    }
    /**
     * 行业端——集团企业——主要设备设施——添加(页面)
     */
    @RequestMapping(value = "product/mequipment-add")
    public String addMequipment(HttpServletRequest request, Model model) throws Exception {
    	User user = getLoginUser(request);
    	Map<String, Object> m = new HashMap<String, Object>();
        m.put("tradeId", user.getId());
        List<DynamicParameter<String, Object>> branches = companyMapper.selectCompanyList(m);
        Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", branches);
    	model.addAttribute("clique", clique);
        return "tradeclique/product/mequipment-add";
    }
    /**
     * 行业端——集团企业——主要设备设施——添加/编辑——保存
     */
    @RequestMapping(value = "product/mequipment-save")
    public @ResponseBody Result meqSave(Mequipment mequipment, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (null == mequipment.getId()) {
            mequipmentMapper.insertSelective(mequipment);
        } else {
            mequipmentMapper.updateByPrimaryKeySelective(mequipment);
        }
        return result;
    }
    /**
     * 行业端——集团企业——主要设备设施——删除
     */
    @RequestMapping(value = "product/deleteEquipment")
    public @ResponseBody Result deleteEquipment(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        mequipmentMapper.deleteByPrimaryKey(id);
        return result;
    }
    /**
     * 行业端——集团企业——主要设备设施——页面(打印预览)
     */
    @RequestMapping("product/mequipment-list-print")
    public String mainEquipmentListPrint(Model model, HttpServletRequest request, String equipmentName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        //setUserId(user, m);
        m.put("tradeId", user.getId());
        List<Integer> userId = null;
        userId = companyMapper.selectByCompany_trade(m);
        userId.add(user.getId());
        m.put("userIds", StringUtils.join(userId, ","));
        //log.error(m.toString());
        
        m.put("equipmentName", equipmentName);
        model.addAttribute("list", mequipmentMapper.selectByUserId(m));
        model.addAttribute("equipmentName", equipmentName);
        return "tradeclique/product/mequipment-list-print";
    }
    /**
     * 行业端——集团企业——主要原辅材料——页面
     */
    @RequestMapping("product/material-list")
    public String mainMaterialList(Model model, HttpServletRequest request, String materialName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        m.put("materialName", materialName);
        model.addAttribute("list", materialMapper.selectByUserId(m));
        model.addAttribute("materialName", materialName);
        return "tradeclique/product/material-list";
    }
    /**
     * 主要原辅材料——编辑
     */
    @RequestMapping(value = "product/material-edit")
    public String materialEdit(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("ma", materialMapper.selectByPrimaryKey(id));
        return "tradeclique/product/material-edit";
    }
    /**
     * 主要原辅材料——添加
     */
    @RequestMapping(value = "product/material-add")
    public String addMaterial(HttpServletRequest request) throws Exception {
        return "tradeclique/product/material-add";
    }
    /**
     * 主要原辅材料——添加/编辑保存
     */
    @RequestMapping(value = "product/material-save")
    public @ResponseBody Result materialSave(Material material, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        material.setUserId(user.getId());
        if (null == material.getId()) {
            materialMapper.insertSelective(material);
        } else {
            materialMapper.updateByPrimaryKeySelective(material);
        }
        return result;
    }
    /**
     * 主要原辅材料——删除
     */
    @RequestMapping(value = "product/deleteMaterial")
    public @ResponseBody Result deleteMaterial(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        materialMapper.deleteByPrimaryKey(id);
        return result;
    }
    
    /**
     * 行业端——集团企业——主要生产产品——页面
     */
    @RequestMapping("product/product-list")
    public String mainProductList(Model model, HttpServletRequest request, String proName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        m.put("proName", proName);
        model.addAttribute("list", productMapper.selectByUserId(m));
        model.addAttribute("proName", proName);
        return "tradeclique/product/product-list";
    }
    /**
     * 主要生产产品——编辑
     */
    @RequestMapping(value = "product/product-edit")
    public String proEdit(@RequestParam("id") int id, Model model) throws Exception {
    	//log.error("pr："+ productMapper.selectByPrimaryKey(id));
        model.addAttribute("pr", productMapper.selectByPrimaryKey(id));
        return "tradeclique/product/product-edit";
    }
    /**
     * 主要生产产品——添加
     */
    @RequestMapping(value = "product/product-add")
    public String addProduct(HttpServletRequest request) throws Exception {
        return "tradeclique/product/product-add";
    }
    /**
     * 主要生产产品——添加/编辑——保存
     */
    @RequestMapping(value = "product/product-save")
    public @ResponseBody Result productSave(Product product, Integer code, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        product.setUserId(user.getId());
        if (null == product.getId()) {
            productMapper.insertSelective(product);
        } else {
            productMapper.updateByPrimaryKeySelective(product);
        }
        return result;
    }
    /**
     * 主要生产产品——删除
     */
    @RequestMapping(value = "product/deleteProduct")
    public @ResponseBody Result deleteProduct(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        productMapper.deleteByPrimaryKey(id);
        return result;
    }
    /**
     * 行业端——集团企业——生产工艺流程图——页面
     */
    @RequestMapping("process/process-list")
    public String processList(Model model, HttpServletRequest request, String project) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        m.put("project", project);
        List<Map<String, Object>> list = productionProcessDiagramMapper.selectList1(m);
        //log.error("list："+list);
        model.addAttribute("list", list);
        model.addAttribute("project", project);
        return "tradeclique/process/process-list";
    }
    /**
     * 生产工艺流程图——添加
     */
    @RequestMapping("process/process-edit")
    public String processEdit(Model model, HttpServletRequest request, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("be", productionProcessDiagramMapper.selectByPrimaryKey(id));
        }
        return "tradeclique/process/process-edit";
    }

    /**
     * 生产工艺流程图——保存
     */
    @RequestMapping("process/process-save")
    public @ResponseBody Result processSave(HttpServletRequest request,
            ProductionProcessDiagram productionProcessDiagram) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        productionProcessDiagram.setUserId(user.getId());
        if (null == productionProcessDiagram.getId()) {
            productionProcessDiagramMapper.insertSelective(productionProcessDiagram);
        } else {
            productionProcessDiagramMapper.updateByPrimaryKey(productionProcessDiagram);
        }
        return result;
    }

    /**
     * 生产工艺流程图——删除
     */
    @RequestMapping("process/process-del")
    public @ResponseBody Result processDel(Integer id) {
        Result result = new ResultImpl();
        productionProcessDiagramMapper.deleteByPrimaryKey(id);
        return result;
    }
    
    /**
     * 行业端——集团企业首页——企业数量
     */
    @RequestMapping(value = "company/company-list")
    public String companyList(Model model, HttpServletRequest request, CompanyListReqDTO dto, Integer totalzc, Integer totalwyx) throws Exception {
        User user = getLoginUser(request);
        cgfService.selectCompanyWithPage(dto, user, model);  
        if(user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }    
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("sk", request.getParameter("sk"));   
        model.addAttribute("totalzc", totalzc);
        model.addAttribute("totalwyx", totalwyx);
        model.addAttribute("dto", dto);
        model.addAttribute("title", "全部企业");
        if (StringUtils.isNotBlank(dto.getDoubleDanger())) {
            model.addAttribute("title", "高危作业");
        }
        if (StringUtils.isNotBlank(dto.getCisDanger())) {
            model.addAttribute("title", "重大危险源企业");
        }
        return "tradeclique/company/company-list";
    }
    /**
     * 行业端——集团企业首页——企业数量——跳转到企业页面
     */
	@RequestMapping(value = "move/company")
	public String move(HttpServletRequest request, Model model,Integer uid) throws Exception {
		User user = getLoginUser(request);
		if(user.getUserType()!=5){
			HttpSession session = request.getSession();
			//log.error("moveBefore："+user.toString());
			session.setAttribute("govUser",user); //跳转前user保存
			user = userMapper.selectByPrimaryKey(uid);
			SessionUtil.setUser(request, user);
		}
	    model.addAttribute("userName", companyMapper.selectByPrimaryKey(user.getId()).getName());
	    model.addAttribute("loginUserId", user.getId());
	    return "company/main";
	}
    /**
     * 行业端——集团企业首页——企业数量——跳转返回
     */
	@RequestMapping(value = "back/gov")
	public String back(HttpServletRequest request, Model model) throws Exception {
    	HttpSession session = request.getSession();
    	User user =  (User)session.getAttribute("govUser");
    	if(user == null){//返回后的刷新
    		user = getLoginUser(request);
    	}else{	
    		SessionUtil.setUser(request, user);
    		session.removeAttribute("govUser");//返回时将moveBefore的session删除
    	}
    	if(user.getUserType()==10){//行业端
    		Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
    		model.addAttribute("name_", trade.getName());
    		model.addAttribute("loginUserId", user.getId());
    		if(trade.getIsClique() == 1){//集团型企业
    			//model.addAttribute("list", tradeMapper.selectTradeCompany(user.getId()));
    			//log.error("TradeCompany："+tradeMapper.selectTradeCompany(user.getId()));
    			return "tradeclique/clique-main";	
    		}
    		return "trade/main";	
    	}
    	if(user.getUserType()==6){//区
    		Map<String, Object> m = new HashMap<String, Object>();
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
    		model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
    		model.addAttribute("loginUserId", user.getId());
    		return "area/main";
    	}
    	if(user.getUserType()==3){//镇
    		Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
    		model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
    		model.addAttribute("loginUserId", user.getId());
    		return "town/main";
    	}
    	if(user.getUserType()==4){//乡
    		model.addAttribute("name_", villageMapper.selectByPrimaryKey(user.getId()).getName());
//            if(user.getUserName().equals("港口")) {//港口特殊账号
//                return "gang/main";
//            }
            model.addAttribute("loginUserId", user.getId());
            return "village/main";
    	}
    	if(user.getUserType()==9){//安泰
    		model.addAttribute("list", districtMapper.selectDistrict());
            model.addAttribute("list1", tradeMapper.selectTrade());
            model.addAttribute("name_", user.getUserName());
            model.addAttribute("loginUserId", user.getId());
            return "country/main";
    	}
    	return "";	
	}
    
    /**
     * 行业端——集团企业首页——定期检查：flag=1&status=1 各账号首页定期检查均利用此端口；最好不要修改
     */
    @RequestMapping(value = "check-list")//flag:3 部门抽查
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
            Integer townId, Integer villageId,
            Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);
        Date d = new Date();
        Map<String, Object> m = new HashMap<String, Object>();
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        m.put("type", type);
        m.put("flag", flag);
        m.put("title", title);
        m.put("townId", townId);
        m.put("villageId", villageId);
        m.put("companyName", companyName);
        m.put("status", status);
        if(flag == 1){
        	m.put("now1", DateFormatUtils.format(d, "yyyy-MM-dd"));
        	m.put("extime", 1);
        }
        if(setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList2(m);
            model.addAttribute("list", list);
        }
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        //log.error("user:"+user.toString());
        Integer jcxq = 1;
        model.addAttribute("jcxq",jcxq);//特制：将首页——定期检查——检查详情：显示显示未未检查 wz 0116
        
        Trade trade = tradeMapper.selectByPrimaryKey(user.getId());//行业端集团型企业
        if (trade != null && trade.getIsClique() == 1) {
        	//log.error("flag："+flag);
            return "company/danger/check-list1";
        }
        if (user.getUserType() == 5) {
        	//log.error("flag："+flag);
            return "company/danger/check-list1";
        }
        return "village/danger/check-list1";
    }

    /**
     * 行业端——集团企业——企业自查
     * TODO 企业自查详细信息   url参数 ?flag=1&type=1
     */
	 @RequestMapping(value = "model-list-cx")
	 public String modelList1(Integer type, Integer flag, String title, Integer industryType, HttpServletRequest request,
	         Model model) throws Exception {
	     //  TODO 获取登陆信息
	     User user = getLoginUser(request); 
	     //Company company = companyMapper.selectByPrimaryKey(user.getId());
	     model.addAttribute("type", type);
	     model.addAttribute("flag", flag);
	     model.addAttribute("industryType", industryType);
	     
	     Map<String, Object> m = new HashMap<String, Object>();
	     m.put("type", type);
	     m.put("title", title);
	     m.put("flag", flag);
	     m.put("industryType", industryType);
	     m.put("userId", user.getId());
	     //setUserId(user, m);    
	     model.addAttribute("list", tModelMapper.selectByMap(m));
	
	     return "tradeclique/danger/model-list-cx";
	 }
   /**
     * 行业端——集团企业——企业自查——复查整改
     */
    @RequestMapping(value = "check-list1")
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
            Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);
        m.put("title", title);
        m.put("companyName", companyName);
        m.put("status", status);
//        if(setUserId(user, m)) {
//            clearVillageTown(m);
//            List<Map<String, Object>> list = tCheckMapper.selectList(m);
//            model.addAttribute("list", list);
//        }
        m.put("userId", user.getId());
        clearVillageTown(m);
        List<Map<String, Object>> list = tCheckMapper.selectList(m);
        model.addAttribute("list", list);
 
        
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        
        Trade trade = tradeMapper.selectByPrimaryKey(user.getId());//行业端集团型企业
        if (trade != null && trade.getIsClique() == 1) {
        	return "tradeclique/danger/model-list-cx";
        }
        
        if (user.getUserType() == 5) {
            return "tradeclique/danger/model-list-cx";
        }
        return "village/danger/check-list";
    }
    
    
    
    
    /**
     * 行业端——所属企业——Company首页功能开始
     * 
     * @throws Exception
     */
    @RequestMapping("welcomecompany")
    public String welcomecompany(Model model, HttpServletRequest request,Integer companyId) throws Exception {
        User user = userMapper.selectByPrimaryKey(companyId);
        Company c = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("c", c);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", "1");
        m.clear();
        setUserId(user, m);
        
        List<Integer> count = userService.selectCount(new CompanyListReqDTO(), user);
        
        //log.error("count："+count.toString());
        
        model.addAttribute("lib", libraryMapper.selectLibraryList(2));
        m.clear();
        setUserId(user, m);
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("count", count);
        
        model.addAttribute("loginUserId", user.getId());
        return "tradeclique/company/clique-companywelcome";
    }    
    
    /**
     * Company首页——企业基本信息
     */
    @RequestMapping("company/basic-information")
    public String basicInformation(Model model, HttpServletRequest request,Integer companyId) throws Exception {
        User user = userMapper.selectByPrimaryKey(companyId);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (null != company.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(company.getRegionId()));
        }
        model.addAttribute("c", company);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "tradeclique/company/information1";
    }
    /**
     * Company首页——地图定位
     */
    @RequestMapping("information/information4")
    public String information4(Model model, HttpServletRequest request, Integer userId,Integer companyId) throws Exception {
        User user = userMapper.selectByPrimaryKey(companyId);
        Company company = companyMapper.selectByPrimaryKey(null == userId ? user.getId() : userId);
        if (null != company.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(company.getRegionId()));
        }
        model.addAttribute("company", company);
        model.addAttribute("user", userMapper.selectByPrimaryKey(company.getUserId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(company.getVillageId()));
        return "tradeclique/company/information4";
    }
    /**
     * Company首页——预警栏——持证上岗
     */
    @RequestMapping("train/special-list")
    public String spersonList(Model model, HttpServletRequest request, String spFlag, String spName, String spType,Integer companyId,
            Integer isTime) throws Exception {
    	TradeComapnyHide(model,companyId);
    	
        if (spFlag == null || "".equals(spFlag)) {
            spFlag = "0";
        }
        int libraryType = 10;
        if ("0".equals(spFlag)) {
            libraryType += 1; 
        } else {
            libraryType += Integer.parseInt(spFlag);
        }
        User user = userMapper.selectByPrimaryKey(companyId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
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
        return "tradeclique/company/special-list";
    }
//    /**
//     * Company首页——预警栏——特种设备检测
//     */
//    @RequestMapping("sequipment/sequipment-list")
//    public String sequipmentList(Model model, HttpServletRequest request, Integer companyId, String seName, Integer isTime)
//            throws Exception {
//    	//TradeComapnyHide(model,companyId);
//    	
//        User user = userMapper.selectByPrimaryKey(companyId);
//        Map<String, Object> m = new HashMap<String, Object>();
//        setUserId(user, m);
//        m.put("seName", seName);
//        if (null != isTime) {
//            if (isTime > 0) {
//                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
//            }
//            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
//        }
//        model.addAttribute("list", sequipmentMapper.selectSequipmentList(m));
//        model.addAttribute("seName", seName);
//        model.addAttribute("isTime", isTime);
//        return "tradeclique/company/sequipment-list";
//    }
    /**
     * Company首页——预警栏——职位检测、体检
     */
    @RequestMapping("evaluate/detection-list")
    public String detList(Model model, HttpServletRequest request, String deType, Integer companyId,Integer isTime) throws Exception {
    	//TradeComapnyHide(model,companyId);
    	
        User user = userMapper.selectByPrimaryKey(companyId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("deType", deType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", detectionMapper.selectDetectionList(m));
        model.addAttribute("lib", libraryMapper.selectLibraryList(6));
        model.addAttribute("deType", deType);
        model.addAttribute("isTime", isTime);
        return "tradeclique/company/detection-list";
    }
    /**
     * Company首页——预警栏——评价评估——评价评估
     */
    @RequestMapping("evaluate/evaluation-list")
    public String evatList(Model model, HttpServletRequest request, Integer companyId, String reportName, String evaType, Integer isTime,
            String flag) throws Exception {
    	//TradeComapnyHide(model,companyId);
    	
        if (flag == null || "".equals(flag)) {
            flag = "0";
        }
        User user = userMapper.selectByPrimaryKey(companyId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("flag", flag);
        m.put("reportName", reportName);
        m.put("evaType", evaType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("list", evaluationMapper.selectEvaluationList(m));
        model.addAttribute("reportName", reportName);
        model.addAttribute("evaType", evaType);
        model.addAttribute("flag", flag);
        model.addAttribute("isTime", isTime);
        return "company/evaluate/evaluation-list";
    }
    /**
     * Company首页——预警栏——评价评估——各类许可证
     */
    @RequestMapping("evaluate/zheng-list")
    public String zhengList(Model model, HttpServletRequest request, Integer companyId, Standard standard, Integer isTime) throws Exception {
    	//TradeComapnyHide(model,companyId);
    	
        User user = userMapper.selectByPrimaryKey(companyId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("type", standard.getType());
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", standardMapper.selectList(m));
        model.addAttribute("type", standard.getType());
        model.addAttribute("isTime", isTime);
        return "company/evaluate/zheng-list";
    }
    /**
     * Company首页——预警栏——评价评估——应急预案
     */
    @RequestMapping(value = "tables/tab-yjlist")
    public String yjjyList(HttpServletRequest request, Model model, Integer companyId, String companyName, String name, Integer isTime, Integer c) throws Exception {
    	//TradeComapnyHide(model,companyId);
    	
    	User user = userMapper.selectByPrimaryKey(companyId);
        Map<String, Object> m = new HashMap<String, Object>();
        if(null != c && c.compareTo(1) == 0) {
            m.put("ownerId", user.getId());
        } else {
            setUserId(user, m);
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
        return "tradeclique/company/tab-yjlist";
    }
    
    
    /**
     * Company首页——预警栏——定期检查
     */
    @RequestMapping(value = "tradecheck-list")//flag:3 部门抽查
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
            Integer townId, Integer villageId,Integer companyId,
            Integer status, Integer flag, Model model) throws Exception {
    	//TradeComapnyHide(model,companyId);
    	model.addAttribute("companyId",companyId);
    	User user = userMapper.selectByPrimaryKey(companyId);
        Date d = new Date();
        Map<String, Object> m = new HashMap<String, Object>();
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        m.put("type", type);
        m.put("flag", flag);
        m.put("title", title);
        m.put("townId", townId);
        m.put("villageId", villageId);
        m.put("companyName", companyName);
        m.put("status", status);
        if(flag == 1){
        	m.put("now1", DateFormatUtils.format(d, "yyyy-MM-dd"));
        	m.put("extime", 1);
        }
        if(setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList2(m);
            model.addAttribute("list", list);
        }
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        
        Integer jcxq = 1;
        model.addAttribute("jcxq",jcxq);//特制：将首页——定期检查——检查详情：显示显示未未检查 wz 0116
        
        if (user.getUserType() == 5) {
            return "tradeclique/company/check-list";
        }
        return "village/danger/check-list";
    }
    /**
     * Company首页——预警栏——重大事故隐患
     */
    @RequestMapping(value = "check-item3")
    public String checkItem3(HttpServletRequest request, Model model, Integer companyId,Integer flag, String companyName, Integer status)
            throws Exception {
    	//TradeComapnyHide(model,companyId);
    	
        User user = userMapper.selectByPrimaryKey(companyId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
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
        return "village/company/danger-index3";
    }
    /**
     * Company首页——排查治理统计分析
     */
    static String PP = "yyyy-MM";
    
    /**
     * 折线图数据 根据隐患类型 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "company/lineChartData")
    public @ResponseBody Result lineChartData(String sT, String eT, HttpServletRequest request,Integer companyId) throws Exception {
        User user = userMapper.selectByPrimaryKey(companyId);
        //log.error("lineChartDatacompanyId："+companyId);
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
        setUserId(user, m);
        //log.error("m："+m.toString());
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
    @RequestMapping(value = "company/lineChartData2")
    public @ResponseBody Result lineChartData2(String sT, String eT, HttpServletRequest request,Integer companyId) throws Exception {
        User user = userMapper.selectByPrimaryKey(companyId);
        //log.error("lineChartData2companyId："+companyId);
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
        setUserId(user, m);
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
    @RequestMapping(value = "company/zhuChartData")
    public @ResponseBody Result zhuChartData(String sT, String eT, HttpServletRequest request,Integer companyId) throws Exception {
        User user = userMapper.selectByPrimaryKey(companyId);
        //log.error("zhuChartDatacompanyId："+companyId);
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
        setUserId(user, m);
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
    @RequestMapping(value = "company/zhuChartData2")
    public @ResponseBody Result zhuChartData2(String sT, String eT, HttpServletRequest request,Integer companyId) throws Exception {
        User user = userMapper.selectByPrimaryKey(companyId);
        //log.error("zhuChartData2companyId："+companyId);
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
        setUserId(user, m);
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
     * 安全监察监管——高危作业
     */
    @RequestMapping(value = "dangerouswork-list")
    public String dangerousWorkList(Model model, HttpServletRequest request, Integer userId, String companyName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("danger", 1);
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
        DynamicParameter<String, Object> clique = tradeCliqueMapper.selectForCliqueByUserId(m);
        if(clique != null && clique.get("danger")!=null){
        	if(clique.get("danger").toString().equals("1") && StringUtils.isNotBlank(clique.get("dangers").toString())){
            	list.add(0 , clique);
            }
        }
        model.addAttribute("list", list);
        model.addAttribute("companyName", companyName);
        
        return "tradeclique/clique/dangerouswork-list";
    }
    /**
     * 添加高危作业
     */
    @RequestMapping(value = "dangerouswork-add")
    public String dangerousWorkAdd(Model model, HttpServletRequest request) throws Exception {
    	User user = getLoginUser(request);
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("tradeId", user.getId());
    	List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
    	list.add(0, tradeCliqueMapper.selectForCliqueByUserId(m));
    	model.addAttribute("branches", list);
    	model.addAttribute("ind3L", tIndustryMapper.selectByType(3));// 高危检查类别
    	
        return "tradeclique/clique/dangerouswork-add";
    }
    /**
     * 高危作业保存
     */
    @RequestMapping(value = "dangerouswork-save")
    public @ResponseBody Result dangerousWorkSave(Company c, String jw, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        if(c.getUserId().equals(user.getId())){
        	TradeClique t = new TradeClique();
        	t.setUserId(c.getUserId());
            if(StringUtils.isBlank(c.getDangers())){
            	t.setDanger(0);
            	t.setDangers("");
            }else{
            	t.setDanger(1);
            	t.setDangers(c.getDangers());
            }
            tradeCliqueMapper.updateByPrimaryKeySelective(t);
        }else{
            if(StringUtils.isBlank(c.getDangers())){
            	c.setDanger(0);
            }else{
            	c.setDanger(1);
            }
            companyMapper.updateByPrimaryKeySelective(c);
        }
        return result;
    }
    
    /**
     * 持证上岗人员列表
     */
    @RequestMapping(value = "certified")
    public String spersonList(HttpServletRequest request, Model model, Integer isTime, Integer spFlag,
            String companyName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("spFlag", spFlag);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        List<DynamicParameter<String, Object>> list = spersonMapper.selectSpersonList(m);
        String cliqueName = tradeMapper.selectByPrimaryKey(user.getId()).getName();
        if(!(companyName != null && !cliqueName.contains(companyName))){
        	model.addAttribute("cliqueList", spersonMapper.selectClique(m));
            model.addAttribute("cliqueName", cliqueName);
        }
        model.addAttribute("list", list);
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        return "tradeclique/clique/certified";
    }
    /**
     * 添加持证上岗人员
     */
    @RequestMapping(value = "certified-add")
    public String spersonAdd(HttpServletRequest request, Model model, Integer spFlag, Integer id) throws Exception {
    	User user = getLoginUser(request);
    	if(id!=null){
    		Sperson sp = spersonMapper.selectByPrimaryKey(id);
    		model.addAttribute("sp", sp);
    		System.out.println(sp);
    	}
        if (spFlag == null) {
            spFlag = (Integer) 0;
        }
        int libraryType = 10;
        if (spFlag == 0) {
            libraryType += 1; 
        } else {
            libraryType += spFlag;
        }
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("tradeId", user.getId());
    	List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
    	Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", list);
    	model.addAttribute("clique", clique);
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("lib", libraryMapper.selectLibraryList(libraryType));
        return "tradeclique/clique/certified-add";
    }
    /**
     * 持证上岗人员添加保存
     */
    @RequestMapping(value = "certified-save")
    public @ResponseBody Result spersonSave(Sperson sperson, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (null == sperson.getId()) {
        	spersonMapper.insertSelective(sperson);
        } else {//编辑
            spersonMapper.updateByPrimaryKeySelective(sperson);
        }
        return result;
    }
    /**
     * 删除持证上岗人员
     */
    @RequestMapping(value = "certified-del")
    public @ResponseBody Result spersonDel(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        spersonMapper.deleteByPrimaryKey(id);
        return result;
    }
    /**
     * 安全监管监察——特种设备检测列表
     */
    @RequestMapping(value = "sequipment/sequipment-list")
    public String sequipmentList(HttpServletRequest request, Model model, Integer isTime, String companyName)
            throws Exception {
    	log.error("sequipmentList: zhangcl 2019.2.27");
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        String cliqueName = tradeMapper.selectByPrimaryKey(user.getId()).getName();
        if(!(companyName != null && !cliqueName.contains(companyName))){
        	model.addAttribute("cliqueList", sequipmentMapper.selectClique(m));
            model.addAttribute("cliqueName", cliqueName);
        }
        model.addAttribute("list", sequipmentMapper.selectSequipmentList(m));
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        return "tradeclique/clique/sequipment-list";
    }
    /**
     * 安全监管监察——特种设备检测——添加特种设备
     */
    @RequestMapping(value = "sequipment/sequipment-add")
    public String addSequipment(HttpServletRequest request, Model model, Integer id) throws Exception {
    	User user = getLoginUser(request);
    	if(id != null){
    		Sequipment edit = sequipmentMapper.selectByPrimaryKey(id);
    		model.addAttribute("editEquip", edit);
    	}
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("tradeId", user.getId());
    	List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
    	Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", list);
    	model.addAttribute("clique", clique);
        return "tradeclique/clique/sequipment-add";
    }
    /**
     * 特种设备添加保存
     */
    @RequestMapping(value = "sequipment/sequipment-save")
    public @ResponseBody Result seqSave(Sequipment sequipment) throws Exception {
        Result result = new ResultImpl();
        if (null == sequipment.getId()) {
            sequipmentMapper.insertSelective(sequipment);
        } else {
            sequipmentMapper.updateByPrimaryKeySelective(sequipment);
        }
        return result;
    }
    /**
     * 删除特种设备
     */
    @RequestMapping(value = "sequipment/sequipment-del")
    public @ResponseBody Result deleteSequipment(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        sequipmentMapper.deleteByPrimaryKey(id);
        return result;
    }
    /**
     * 批量导入特种设备
     */
    @RequestMapping(value = "importSequipmentExcel", produces = "text/html;charset=utf-8")
    public void importSequipmentExcel(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        log.error("importSequipmentExcel: zhangcl 2019.2.27");
    	Result result = new ResultImpl();
        System.out.println("receive");//未输出
        userService.sequipmentImportSaveForClique(result, file);
        writeResponse(result, response);//该方法调用如下
    }
    /**
     * 职业卫生检测
     */
    @RequestMapping(value = "detection-list")
    public String detectionList(Model model, HttpServletRequest request, Integer isTime, String companyName,
            String deType) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("deType", deType);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        String cliqueName = tradeMapper.selectByPrimaryKey(user.getId()).getName();
        if(!(companyName != null && !cliqueName.contains(companyName))){
        	model.addAttribute("cliqueList", detectionMapper.selectClique(m));
            model.addAttribute("cliqueName", cliqueName);
        }
        model.addAttribute("list", detectionMapper.selectDetectionList(m));
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("deType", deType);
        return "tradeclique/clique/detection-list";
    }
    /**
     * 职业卫生检测添加
     */
    @RequestMapping(value = "detection-edit")
    public String detectionEdit(Integer id, Model model, HttpServletRequest request) throws Exception {
        if(null != id) {
            Detection de = detectionMapper.selectByPrimaryKey(id);
            model.addAttribute("d", de);
        }
        User user = getLoginUser(request);
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("tradeId", user.getId());
    	List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
    	Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", list);
    	model.addAttribute("clique", clique);
        model.addAttribute("list", libraryMapper.selectLibraryList(6));
        return "tradeclique/clique/detection-edit";
    }
    /**
     * 职业卫生检测添加/编辑保存
     */
    @RequestMapping(value = "detection-save")
    public @ResponseBody Result deSave(Detection detection, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (null == detection.getId()) {
            detectionMapper.insertSelective(detection);
        } else {
            detectionMapper.updateByPrimaryKeySelective(detection);
        }
        return result;
    }
    /**
     * 删除职业卫生检测
     */
    @RequestMapping(value = "detection-del")
    public @ResponseBody Result deleteDe(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        detectionMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 职业健康体检
     */
    @RequestMapping(value = "health-list")
    public String examinationList(Model model, HttpServletRequest request, String companyName, Integer isTime)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        String cliqueName = tradeMapper.selectByPrimaryKey(user.getId()).getName();
        if(!(companyName != null && !cliqueName.contains(companyName))){
        	model.addAttribute("cliqueList", examinationMapper.selectClique(m));
            model.addAttribute("cliqueName", cliqueName);
        }
        model.addAttribute("list", examinationMapper.selectExaminationList(m));
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        return "tradeclique/clique/health-list";
    }
    /**
     * 健康体检添加
     */
    @RequestMapping(value = "health-add")
    public String healthEdit(Model model, Integer id, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("tradeId", user.getId());
    	List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
    	Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", list);
    	model.addAttribute("clique", clique);
        if (null != id) {
            model.addAttribute("e", examinationMapper.selectByPrimaryKey(id));
        }
        return "tradeclique/clique/health-add";
    }
    /**
     * 健康体检保存
     */
    @RequestMapping(value = "health-save")
    public @ResponseBody Result healthSave(HttpServletRequest request, Examination e) throws Exception {
        Result result = new ResultImpl();
        if (null == e.getId()) {
            examinationMapper.insertSelective(e);
        } else {
            examinationMapper.updateByPrimaryKeySelective(e);
        }
        return result;
    }
    /**
     * 健康体检删除
     */
    @RequestMapping(value = "health-del")
    public @ResponseBody Result healthDel(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        examinationMapper.deleteByPrimaryKey(id);
        return result;
    }
    
    /**
     * 防雷防静电设施页面列表
     */
    @RequestMapping("clique/lightning-list")
    public String lightningList(Model model, HttpServletRequest request, String companyName, String project)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", companyName);
        m.put("project", project);
        List<Map<String, Object>> list = lightningProtectionMapper.selectList(m);
        String cliqueName = tradeMapper.selectByPrimaryKey(user.getId()).getName();
        if(!(companyName != null && !cliqueName.contains(companyName))){
        	model.addAttribute("cliqueList", lightningProtectionMapper.selectClique(m));
            model.addAttribute("cliqueName", cliqueName);
        }
        model.addAttribute("list", list);
        model.addAttribute("companyName", companyName);
        model.addAttribute("project", project);
        return "tradeclique/clique/lightning-list";
    }
    /**
     * 防雷防静电设施页面添加
     */
    @RequestMapping("clique/lightning-edit")
    public String lightningEdit(Model model, HttpServletRequest request, Integer id) throws Exception {
    	User user = getLoginUser(request);
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("tradeId", user.getId());
    	List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
    	Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", list);
    	model.addAttribute("clique", clique);
        if (null != id) {
            model.addAttribute("be", lightningProtectionMapper.selectByPrimaryKey(id));
        }
        return "tradeclique/clique/lightning-edit";
    }
    /**
     * 防雷防静电设施保存
     */
    @RequestMapping("clique/lightning-save")
    public @ResponseBody Result lightningSave(HttpServletRequest request, LightningProtection lightningProtection) {
        Result result = new ResultImpl();
        if (null == lightningProtection.getId()) {
            lightningProtectionMapper.insertSelective(lightningProtection);
        } else {
            lightningProtectionMapper.updateByPrimaryKey(lightningProtection);
        }
        return result;
    }
    /**
     * 防雷防静电设施删除
     */
    @RequestMapping("clique/lightning-del")
    public @ResponseBody Result lightningDel(Integer id) {
        Result result = new ResultImpl();
        lightningProtectionMapper.deleteByPrimaryKey(id);
        return result;
    }
    
    /**
     * 评价评估
     */
    @RequestMapping("clique/evaluation-list")
    public String evatList(Model model, HttpServletRequest request, String reportName, String evaType, Integer isTime,
            String companyName, String flag) throws Exception {
        User user = getLoginUser(request);
        String cliqueName = tradeMapper.selectByPrimaryKey(user.getId()).getName();
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
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
        if(!(companyName != null && !cliqueName.contains(companyName))){
        	model.addAttribute("cliqueList", evaluationMapper.selectClique(m));
            model.addAttribute("cliqueName", cliqueName);
        }
        model.addAttribute("reportName", reportName);
        model.addAttribute("evaType", evaType);
        model.addAttribute("flag", flag);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        return "tradeclique/clique/evaluation-list";
    }
    /**
     * 添加评价评估
     */
    @RequestMapping(value = "clique/evaluation-add")
    public String addEva(HttpServletRequest request, Model model, Integer id) throws Exception {
    	User user = getLoginUser(request);
    	if(null != id){
    		model.addAttribute("ev", evaluationMapper.selectByPrimaryKey(id));
    	}
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("tradeId", user.getId());
    	List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
    	Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", list);
    	model.addAttribute("clique", clique);
        return "tradeclique/clique/evaluation-add";
    }
    /**
     * 评价评估添加/编辑保存
     */
    @RequestMapping(value = "clique/evaluation-save")
    public @ResponseBody Result evaSave(Evaluation evaluation, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (null == evaluation.getId()) {
            evaluationMapper.insertSelective(evaluation);
        } else {
            evaluationMapper.updateByPrimaryKeySelective(evaluation);
        }
        return result;
    }
    /**
     * 删除安全评价
     */
    @RequestMapping(value = "clique/evaluation-del")
    public @ResponseBody Result deleteEva(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        evaluationMapper.deleteByPrimaryKey(id);
        return result;
    }
    /**
     * 各类许可证页面
     */
    @RequestMapping("clique/license-list")
    public String licenseList(Model model, HttpServletRequest request, Standard standard, String companyName, Integer isTime)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("type", standard.getType());
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("cliqueName", tradeMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("cliqueList", standardMapper.selectByUserId(user.getId()));
        model.addAttribute("list", standardMapper.selectList(m));
        model.addAttribute("type", standard.getType());
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        return "tradeclique/clique/license-list";
    }
    /**
     * 添加各类许可证页面
     */
    @RequestMapping("clique/license-add")
    public String licenseAdd(Model model, HttpServletRequest request, Integer id) throws Exception {
    	User user = getLoginUser(request);
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("tradeId", user.getId());
    	List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyList(m);
    	Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", list);
    	model.addAttribute("clique", clique);
        if (null != id) {
            model.addAttribute("standard", standardMapper.selectByPrimaryKey(id));
        }
        return "tradeclique/clique/license-add";
    }

    /**
     * 保存许可证页面
     */
    @RequestMapping("clique/license-save")
    public @ResponseBody Result licenseSave(Model model, HttpServletRequest request, Standard standard) throws Exception {
        Result result = new ResultImpl();
        if(null == standard.getId()) {
            standardMapper.insertSelective(standard);
        } else {
            standardMapper.updateByPrimaryKeySelective(standard);
        }
        return result;
    }
    /**
     * 删除许可证页面
     */
    @RequestMapping("clique/license-del")
    public String licenseDel(Model model, HttpServletRequest request, Standard standard) throws Exception {
        standardMapper.deleteByPrimaryKey(standard.getId());
        return "tradeclique/clique/license-list";
    }
    
    /**
     * 应急救援页面
     */
    @RequestMapping(value = "clique/emergency-plan")
    public String emergencyPlan(HttpServletRequest request, Model model, String companyName, String name, Integer isTime) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("tradeId", user.getId());
        List<DynamicParameter<String, Object>> branches = companyMapper.selectCompanyList(m);
        Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", branches);
    	model.addAttribute("clique", clique);
    	
        m.put("name", name);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("cliqueList", tContingencyPlanMapper.selectByUserId(user.getId()));
        model.addAttribute("list", tContingencyPlanMapper.selectTable(m));
        model.addAttribute("name", name);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        return "tradeclique/clique/emergency-plan";
    }

    /**
     * 应急救援添加
     */
    @RequestMapping(value = "clique/emergency-plan-add")
    public @ResponseBody Result emergencyPlanAdd(HttpServletRequest request, Model model, TContingencyPlan tContingencyPlan) throws Exception {
        Result result = new ResultImpl();
        tContingencyPlanMapper.insertSelective(tContingencyPlan);
        return result;
    }

    /**
     * 应急救援删除
     */
    @RequestMapping(value = "clique/emergency-plan-del")
    public String emergencyPlanDel(HttpServletRequest request, Model model, Integer id) throws Exception {
        tContingencyPlanMapper.deleteByPrimaryKey(id);
        return "tradeclique/clique/emergency-plan";
    }

    /**
     * 应急预案演练页面
     */
    @RequestMapping(value = "clique/emergency-drill")
    public String emergencyDrillList(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("tradeId", user.getId());
        List<DynamicParameter<String, Object>> branches = companyMapper.selectCompanyList(m);
        Trade clique = tradeMapper.selectByPrimaryKey(user.getId());
    	model.addAttribute("branches", branches);
    	model.addAttribute("clique", clique);
    	model.addAttribute("cliqueList", tDrillMapper.selectByUserId(user.getId()));
        model.addAttribute("list", tDrillMapper.selectTable(m));
        if(user.getUserType()==5){
        	model.addAttribute("CompanyName", companyMapper.selectByPrimaryKey(user.getId()).getName());//应急预案演练所属企业显示企业名
        }
        return "tradeclique/clique/emergency-drill";
    }

    /**
     * 应急预案演练添加
     */
    @RequestMapping(value = "clique/emergency-drill-add")
    public @ResponseBody Result emergencyDrillAdd(HttpServletRequest request, Model model, TDrill tDrill) throws Exception {
    	Result result = new ResultImpl();
        tDrillMapper.insertSelective(tDrill);
        return result;
    }

    /**
     * 应急预案演练删除
     */
    @RequestMapping(value = "clique/emergency-drill-del")
    public String emergencyDrillDel(HttpServletRequest request, Model model, Integer id) throws Exception {
        tDrillMapper.deleteByPrimaryKey(id);
        return "tradeclique/clique/emergency-drill";
    }
    
    /**
     * 下载资料
     */
    @RequestMapping(value = "download")
    public void hedownload(String filename, String fileurl, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        File realPath = new File(request.getServletContext().getRealPath("/"), fileurl.replace("/smaq", ""));
        if(!realPath.exists()) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("该文件不存在");
            out.flush();
            out.close();
            return;
        }
        
        filename = URLEncoder.encode(new String(filename.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8");
        response.setContentType(request.getServletContext().getMimeType(filename));
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        
        InputStream in = new FileInputStream(realPath);
        OutputStream out = response.getOutputStream();
        try {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } finally {
            in.close();
            out.close();
        }
    }
    
    /**
     * 批量导入持证上岗人员
     */
    @RequestMapping(value = "importSpersonExcelForClique", produces = "text/html;charset=utf-8")
    public void importSpersonExcelForClique(Model model, Integer flag,HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
    	log.error("importSpersonExcelForClique: zhangcl 2019.2.27");
        Result result = new ResultImpl();
        userService.spersonImportSaveForClique(result, flag, file);
        writeResponse(result, response);//该方法调用如下
    }

    /**
     *  为response提供Json格式的返回数据
     */
    public void writeResponse(Object obj, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");//设置编码
            String str = JSON.toJSONString(obj);
            PrintWriter writer = response.getWriter();
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
