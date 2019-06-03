/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.BaseController;
import com.spring.web.model.Company;
import com.spring.web.model.FcBlindplatePermit;
import com.spring.web.model.FcBrokenlandPermit;
import com.spring.web.model.FcContractorOperation;
import com.spring.web.model.FcFireworkPermit;
import com.spring.web.model.FcGreatDangerStudy;
import com.spring.web.model.FcHighsafetyPermit;
import com.spring.web.model.FcHoistingPermit;
import com.spring.web.model.FcLandworkPermit;
import com.spring.web.model.FcLimitspacePermit;
import com.spring.web.model.FcNaturalDangerItem;
import com.spring.web.model.FcNaturalDangerStudy;
import com.spring.web.model.FcOverhaulOperation;
import com.spring.web.model.FcPowersafetyPermit;
import com.spring.web.model.FcPromise;
import com.spring.web.model.FcTargetAssessment;
import com.spring.web.model.FcTemporaryDangerCensus;
import com.spring.web.model.FcTemporaryDangerStudy;
import com.spring.web.model.User;
import com.spring.web.model.ZzjgCompany;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.trouble.TroubleService;
import com.spring.web.tobject.cgf.GreatdangerSaveReqDTO;
import com.spring.web.tobject.cgf.ModelSaveReqDTO;
import com.spring.web.tobject.cgf.NaturaldangerSaveReqDTO;
import com.spring.web.tobject.cgf.TemporaryDangerSaveReqDTO;

/**
 * @Title: CompanyController_cd
 * @Description: TODO(风险研判与承诺公告)
 * @author zhangcl
 * @date 2018年11月1日 上午22:49:51
 * @version V1.0
 */
@Controller
@RequestMapping("company/study")
public class CompanyController_study extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
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
     * 管理手册
     */
    @RequestMapping(value = "management-guide")
    public String managementGuide(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
        
        return "company/study/management-guide";
    }
    
    
    /**
     * 获取体系架构
     */
    @RequestMapping(value = "department-list")
    public String departmentList(Model model, HttpServletRequest request, Integer type,Integer sign) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("signjgt", sign);
        List<ZzjgCompany> list = zzjgCompanyMapper.selectAll(user.getId());
        List<ZzjgDepartment> listbm = new ArrayList<ZzjgDepartment>();
        for(ZzjgCompany a : list) {
        	ZzjgDepartment zzjgDepartment = new ZzjgDepartment();
        	zzjgDepartment.setCid(a.getId());
        	listbm.addAll(zzjgDepartmentMapper.selectByCompanyId(zzjgDepartment));
        }
        model.addAttribute("username", user.getUserName());
        model.addAttribute("list", list.get(0));
        model.addAttribute("listbm", listbm);
        
//        for(ZzjgDepartment z : listbm) {
//        	log.error("id:"+z.getId()+", pid:"+z.getPid());
//        }
        
        return "company/study/department-list";
    }

    /**
     * 流程管理：确认有无流程
     * 在判断流程的同时向目标履职考核表相应字段插入数据 wz
     */
    @RequestMapping(value = "select")
    public String select(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
    	
    	/*
    	 * 插入之前，先用   khsj考核时间  字段判断当天的的记录是否存在，
    	 * 如果存在则根据其id，向此条记录中插入字段；
    	 * 如果不存在则需要先插入带日期字段的一条记录；保证下面执行update时记录存在
    	 */
//   	Integer id = 1;//数据库中手动加入了1   	
    	FcTargetAssessment ta = new FcTargetAssessment();
//    	ta.setId(id);
//    	ta.setUid(user.getId());
        if(type==1){
        	ta.setKhd5("1");	
        }
        else if(type == 2){
        	ta.setKhd6("1");
        }
        else if(type == 3){
        	ta.setKhd7("1");
        }
        //fcTargetAssessmentMapper.updateByPrimaryKeySelective(ta);
        cgfService.targetAssessmentSave(ta, user);
        model.addAttribute("type",type);
        return "company/study/select";
    }
    
    /**
     * 选择是
     */
    @RequestMapping(value = "selectyes")
    public String selectYes(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        //log.error("user:"+user.getId());
        //save中判断当天是否存在临时风险研判表格数量统计表——将判断、保存封装
        FcTemporaryDangerCensus fcTemporaryDangerCensus = new FcTemporaryDangerCensus();
        cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus, user);
        //log.error("2018.12.5 bug wz .....");
        if(type==1){
        	return "company/study/special-operation";	//特殊作业流程
        	
        }
        else if(type == 2){
        	return "company/study/overhaul-operation";	//检修作业流程
        }
        else if(type == 3){
        	return "company/study/contractor-operation";	//承包商作业流程
        }
        
        else
        	return "company/study/select";
        
    }
    /**
     * 特殊作业流程
     */
    /**
     * 动火作业
     */
    @RequestMapping(value = "firework")
    public String fireWork(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
    	model.addAttribute("action","write");
    	log.error("firework");
        return "company/study/special-firework";

    }  
    /**
     * 动火作业证保存
     */
    @RequestMapping(value = "firework-save")
    public @ResponseBody Result fireworkSave(HttpServletRequest request, FcFireworkPermit fw) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        fw.setUid(user.getId());
        //log.error("dhsj_s:"+fw.getDhsjS());
        //log.error("dhsj_e:"+fw.getDhsjE());
        if (null == fw.getId()) {
            fw.setDel(0);
            fw.setCtime(new Date());
            fcFireworkPermitMapper.insertSelective(fw);
            //进行临时风险保存表格填写数量统计         
            FcTemporaryDangerCensus fcTemporaryDangerCensus1 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer dh = fcTemporaryDangerCensus1.getTslc1Dh()+1;
            fcTemporaryDangerCensus1.setTslc1Dh(dh);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus1, user);
            
        } else {
        	fcFireworkPermitMapper.updateByPrimaryKeySelective(fw);
        }
        return result;
    }    
    /**
     * 受限空间安全作业证
     */
    @RequestMapping(value = "limitspace")
    public String limitSpace(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
        return "company/study/special-limitspace";

    }
    /**
     * 受限空间安全作业证保存
     */
    @RequestMapping(value = "limitspace-save")
    public @ResponseBody Result limitSpaceSave(HttpServletRequest request, FcLimitspacePermit ls) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ls.setUid(user.getId());
        log.error("zysj_s:"+ls.getZysjS());
        log.error("zysj_e:"+ls.getZysjE());
        if (null == ls.getId()) {
            ls.setDel(0);
            ls.setCtime(new Date());
            fcLimitspacePermitMapper.insertSelective(ls);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus2 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer sx = fcTemporaryDangerCensus2.getTslc2Sx()+1;
            fcTemporaryDangerCensus2.setTslc2Sx(sx);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus2, user);
        } else {
        	fcLimitspacePermitMapper.updateByPrimaryKeySelective(ls);
        }
        return result;
    }

    /**
     * 盲板抽堵安全作业证
     */
    @RequestMapping(value = "blindplate")
    public String blindplate(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
        return "company/study/special-blindplate";

    }
    /**
     * 盲板抽堵安全作业证保存
     */
    @RequestMapping(value = "blindplate-save")
    public @ResponseBody Result blindplateSave(HttpServletRequest request, FcBlindplatePermit bp) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        bp.setUid(user.getId());
        if (null == bp.getId()) {
            bp.setDel(0);
            bp.setCtime(new Date());
            fcBlindplatePermitMapper.insertSelective(bp);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus3 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer mb = fcTemporaryDangerCensus3.getTslc3Mb()+1;
            fcTemporaryDangerCensus3.setTslc3Mb(mb);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus3, user);
        } else {
        	fcBlindplatePermitMapper.updateByPrimaryKeySelective(bp);
        }
        return result;
    }
    
    /**
     * 高处安全作业证
     */
    @RequestMapping(value = "highsafety")
    public String highsafety(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
        return "company/study/special-highsafety";

    }
    /**
     * 高处安全作业证保存
     */
    @RequestMapping(value = "highsafety-save")
    public @ResponseBody Result highsafetySave(HttpServletRequest request, FcHighsafetyPermit hs) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        hs.setUid(user.getId());
        if (null == hs.getId()) {
            hs.setDel(0);
            hs.setCtime(new Date());
            fcHighsafetyPermitMapper.insertSelective(hs);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus4 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer gc = fcTemporaryDangerCensus4.getTslc4Gc()+1;
            fcTemporaryDangerCensus4.setTslc4Gc(gc);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus4, user);
        } else {
        	fcHighsafetyPermitMapper.updateByPrimaryKeySelective(hs);
        }
        return result;
    }  
    
    /**
     * 吊装安全作业证
     */
    @RequestMapping(value = "hoisting")
    public String hoisting(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
        return "company/study/special-hoisting";

    }
    /**
     * 吊装安全作业证保存
     */
    @RequestMapping(value = "hoisting-save")
    public @ResponseBody Result hoistingSave(HttpServletRequest request, FcHoistingPermit hi) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        hi.setUid(user.getId());
        if (null == hi.getId()) {
            hi.setDel(0);
            hi.setCtime(new Date());
            fcHoistingPermitMapper.insertSelective(hi);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus5 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer dz = fcTemporaryDangerCensus5.getTslc5Dz()+1;
            fcTemporaryDangerCensus5.setTslc5Dz(dz);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus5, user);
        } else {
        	fcHoistingPermitMapper.updateByPrimaryKeySelective(hi);
        }
        return result;
    } 
    
    /**
     * 临时用电安全作业证
     */
    @RequestMapping(value = "powersafety")
    public String powersafety(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
        return "company/study/special-powersafety";

    }
    /**
     * 临时用电安全作业证保存
     */
    @RequestMapping(value = "powersafety-save")
    public @ResponseBody Result powersafetySave(HttpServletRequest request, FcPowersafetyPermit ps) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        ps.setUid(user.getId());
        if (null == ps.getId()) {
            ps.setDel(0);
            ps.setCtime(new Date());
            fcPowersafetyPermitMapper.insertSelective(ps);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus6 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer yd = fcTemporaryDangerCensus6.getTslc6Yd()+1;
            fcTemporaryDangerCensus6.setTslc6Yd(yd);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus6, user);
        } else {
        	fcPowersafetyPermitMapper.updateByPrimaryKeySelective(ps);
        }
        return result;
    } 
    
    /**
     * 动土安全作业证
     */
    @RequestMapping(value = "landwork")
    public String earthwork(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
        return "company/study/special-landwork";

    }
    /**
     * 动土安全作业证保存
     */
    @RequestMapping(value = "landwork-save")
    public @ResponseBody Result landworklandSave(HttpServletRequest request, FcLandworkPermit lw) throws Exception {
    	Result result = new ResultImpl();
    	User user = getLoginUser(request);
    	lw.setUid(user.getId());
    	if (null == lw.getId()) {
    		lw.setDel(0);
    		lw.setCtime(new Date());
    		fcLandworkPermitMapper.insertSelective(lw);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus7 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer dt = fcTemporaryDangerCensus7.getTslc7Dt()+1;
            fcTemporaryDangerCensus7.setTslc7Dt(dt);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus7, user);
    	} else {
    		fcLandworkPermitMapper.updateByPrimaryKeySelective(lw);
    	}
    	return result;
    } 
    
    /**
     * 断路安全作业证
     */
    @RequestMapping(value = "brokenland")
    public String brokenland(Model model, HttpServletRequest request) throws Exception {
        //User user = getLoginUser(request);
        return "company/study/special-brokenland";

    }
    /**
     * 断路安全作业证保存
     */
    @RequestMapping(value = "brokenland-save")
    public @ResponseBody Result brokenlandSave(HttpServletRequest request, FcBrokenlandPermit bl) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        bl.setUid(user.getId());
        if (null == bl.getId()) {
            bl.setDel(0);
            bl.setCtime(new Date());
            fcBrokenlandPermitMapper.insertSelective(bl);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus8 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer dl = fcTemporaryDangerCensus8.getTslc8Dl()+1;
            fcTemporaryDangerCensus8.setTslc8Dl(dl);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus8, user);
        } else {
        	fcBrokenlandPermitMapper.updateByPrimaryKeySelective(bl);
        }
        return result;
    } 
    /**
     * 对检修作业现场进行安全检查的记录————保存
     */
    @RequestMapping(value = "overhaul-save")
    public @ResponseBody Result overhaulSave(HttpServletRequest request, FcOverhaulOperation oh) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        oh.setUid(user.getId());
        if (null == oh.getId()) {
            oh.setDel(0);
            oh.setCtime(new Date());
            fcOverhaulOperationMapper.insertSelective(oh);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus9 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer jwx = fcTemporaryDangerCensus9.getZylc9Jwx()+1;
            fcTemporaryDangerCensus9.setZylc9Jwx(jwx);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus9, user);
        } else {
        	fcOverhaulOperationMapper.updateByPrimaryKeySelective(oh);
        }
        return result;
    }
    
    /**
     * 承包商评价表————保存
     */
    @RequestMapping(value = "contractor-save")
    public @ResponseBody Result contractorSave(HttpServletRequest request, FcContractorOperation co) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        co.setUid(user.getId());
        if (null == co.getId()) {
            co.setDel(0);
            co.setCtime(new Date());
            fcContractorOperationMapper.insertSelective(co);
            //进行临时风险保存表格填写数量统计
            FcTemporaryDangerCensus fcTemporaryDangerCensus10 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            Integer cbs = fcTemporaryDangerCensus10.getZylc10Cbs()+1;
            fcTemporaryDangerCensus10.setZylc10Cbs(cbs);
            cgfService.fcTemporaryDangerCensusSave(fcTemporaryDangerCensus10, user);
        } else {
        	fcContractorOperationMapper.updateByPrimaryKeySelective(co);
        }
        return result;
    } 
    
    /**
     * 目标履职考核
     */
    @RequestMapping(value = "targetassessment")
    public String targetAssessment(Model model, HttpServletRequest request) throws Exception {
    	
    	/**
    	 * 增加时间限制-每天早上10点后不可操作
    	 */
//    	Calendar c = new GregorianCalendar();
//    	//log.error(c);
//    	c.set(Calendar.HOUR_OF_DAY,10);
//    	c.set(Calendar.MINUTE,0);
//    	c.set(Calendar.SECOND,0);
//    	c.set(Calendar.MILLISECOND, 0);
//    	Date time10 = c.getTime();
//    	//log.error("time10:"+time10);
//    	Date now = new Date();
//    	//log.error("now:"+now);
//    	if(now.after(time10)){
//    		return "company/study/prompt-message5";
//    	}
    	
        User user = getLoginUser(request);
        model.addAttribute("CompanyName", companyMapper.selectByPrimaryKey(user.getId()).getName());
        //log.error("user:"+user.toString()); 
        //当天是否存在目标履职考核表
        FcTargetAssessment fcTargetAssessment= fcTargetAssessmentMapper.selectByUidToday(user.getId());
        if(fcTargetAssessment==null){ 
        	fcTargetAssessment = new FcTargetAssessment();
        	fcTargetAssessment.setUid(user.getId());
        	fcTargetAssessment.setKhsj(new Date());
        	fcTargetAssessment.setDel(0);
        	fcTargetAssessment.setCtime(new Date());
        	fcTargetAssessmentMapper.insertSelective(fcTargetAssessment);
        	fcTargetAssessment = fcTargetAssessmentMapper.selectByUidToday(user.getId());
        }
        //先将1234设为1，如后续条件不符合则改为0
    		fcTargetAssessment.setKhd1("1");
    		fcTargetAssessment.setKhd2("1");
    		fcTargetAssessment.setKhd3("1");
    		fcTargetAssessment.setKhd4("1");
        
        //当天是否进行固有风险研判
        FcNaturalDangerStudy Nd = fcNaturalDangerStudyMapper.selectByUidToday(user.getId());
        //当天是否保存过重大/较大风险研判
//        FcGreatDangerStudy gd = fcGreatDangerStudyMapper.selectByUidToday(user.getId());
        //当固有、重大/较大研判都未做，则1234为0
        if(Nd == null){ 
        	fcTargetAssessment.setKhd1("0");
        	fcTargetAssessment.setKhd2("0");
        	fcTargetAssessment.setKhd3("0");
        	fcTargetAssessment.setKhd4("0");
//        }
//        else if(gd == null){
//        	fcTargetAssessment.setKhd1("0");
//        	fcTargetAssessment.setKhd2("0");
//        	fcTargetAssessment.setKhd3("0");
//        	fcTargetAssessment.setKhd4("0");
        }else{
        	//当固有、重大/较大有不合格的地方，则1234为0；由于固有不合格会在重大处重新判断，所以只需要判断重大是否有不合格
        	//查询当天风险研判条目
        	Map<String, Object> m = new HashMap<String, Object>();
//        	m.put("id", gd.getDangerstudyid());
        	m.put("id", Nd.getId());
        	m.put("uid", user.getId());
        	m.put("checkret", 0);
        	m.put("order", 1);   
        	//List<Map<String, Object>> list = fcNaturalDangerStudyMapper.selectByMap(m);
            m.put("level", "红色");
            List<Map<String, Object>> list = fcNaturalDangerStudyMapper.selectByMap(m);//查询重大隐患风险
            m.put("level", "橙色");
            List<Map<String, Object>> list22 = fcNaturalDangerStudyMapper.selectByMap(m);//查询较大隐患风险
            list.addAll(list22);
        	/*
        	 * checkret=0进行查询，如果Nd不为null，那么以其id进行判断重大的结果，查询为空则还不需要判断重大都为合格；
        	 * 不为空则先判断当天是否进行重大研判，如无则为0，如有遍历是否有不合格现象
        	 */
        	log.error("list："+list.toString());
        	if(list == null || list.isEmpty()){
                fcTargetAssessment.setKhd1("1");
                fcTargetAssessment.setKhd2("1");
                fcTargetAssessment.setKhd3("1");
                fcTargetAssessment.setKhd4("1");
        	}else{
                //当天是否保存过重大/较大风险研判
        		FcGreatDangerStudy gd = fcGreatDangerStudyMapper.selectBydangerstudyidToday(Nd.getId());
        		if(gd == null){
    				fcTargetAssessment.setKhd1("0");
    				fcTargetAssessment.setKhd2("0");
    				fcTargetAssessment.setKhd3("0");
    				fcTargetAssessment.setKhd4("0");
    				
        		}else{	
        			Integer cb =1;
        			for (int i = 0; i < list.size(); i++) {
        				log.error("checkret_bigd:"+ list.get(i).get("checkretBigd"));
        				cb = Integer.parseInt(list.get(i).get("checkretBigd").toString());
        				if(cb == 0 ){
        					fcTargetAssessment.setKhd1("0");
        					fcTargetAssessment.setKhd2("0");
        					fcTargetAssessment.setKhd3("0");
        					fcTargetAssessment.setKhd4("0");
        				}
        			}
        		}
        	}
        	//log.error("list："+list.toString());
        }
        cgfService.targetAssessmentSave(fcTargetAssessment, user);
        
        model.addAttribute("ta",fcTargetAssessment);
        
        return "company/study/target-assessment";
    }
    
    /**
     * 目标履职考核-保存
     */
    @RequestMapping(value = "targetassessment-save")
    public @ResponseBody Result targetAssessmentSave(FcTargetAssessment fcTargetAssessment, HttpServletRequest request)throws Exception {
        log.error("targetAssessmentSave:"+fcTargetAssessment.toString());
    	User user = getLoginUser(request);
        Result result = new ResultImpl();
        
        cgfService.targetAssessmentSave(fcTargetAssessment, user);
        
        return result;
    }
    
    /**
     * 运行管理——固有风险研判
     */
    @RequestMapping(value = "naturaldanger-list")
    public String naturaldangerList(Model model, HttpServletRequest request) throws Exception {
    	 User user = getLoginUser(request);
    	 //log.error("user："+user.toString());
    	 
    	 model.addAttribute("uid", user.getId());
    	 
         Company company = companyMapper.selectByPrimaryKey(user.getId());
        // log.error("company："+company.toString());
         model.addAttribute("company", company);
         
         Map<String, Object> m = new HashMap<String, Object>();
         m.put("uid", user.getId());
         m.put("order", 1);
         //log.error("m："+m.toString());
         
         //List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);  
         m.put("level", "红色");
         List<Map<String, Object>> list = aCompanyManualMapper.selectByMap(m);//查询重大隐患风险
         m.put("level", "橙色");
         List<Map<String, Object>> list22 = aCompanyManualMapper.selectByMap(m);//查询较大隐患风险
         list.addAll(list22);
         
         log.error("list："+list.toString());
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
         log.error("treeMap："+levmap.toString());
         model.addAttribute("treeMap", levmap);  
         model.addAttribute("action","write");
         return "company/study/naturaldanger-list";
    }
    /**
     * 运行管理——固有风险研判——保存
     */
    @RequestMapping(value = "naturaldanger-save")
    public @ResponseBody Result naturaldangerSave(HttpServletRequest request,@RequestBody NaturaldangerSaveReqDTO dto) {
    	
    	log.error("naturaldangerSave");
        Result result = new ResultImpl();
        User user = getLoginUser(request);     
        try {
            //当天是否进行固有风险研判
            FcNaturalDangerStudy nd = fcNaturalDangerStudyMapper.selectByUidToday(user.getId());
            if(nd== null){ 
            	cgfService.naturaldangerSave(dto);
            	
            } else{	
            	
            	nd.setDel(1);
            	fcNaturalDangerStudyMapper.updateByPrimaryKeySelective(nd);
            	
            	cgfService.naturaldangerSave(dto);
            	
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("保存失败");
			e.printStackTrace();
			result.setStatus("1");
    		result.setMess("保存失败");
    		return result;
		}
        
        return result;
    } 
    /**
     * 运行管理——档案管理——固有风险研判——回显
     */
    @RequestMapping(value = "naturaldanger-show")
    public String naturaldangerShow(Model model, HttpServletRequest request,Integer id) throws Exception {
    	 User user = getLoginUser(request);  
         Company company = companyMapper.selectByPrimaryKey(user.getId());
         model.addAttribute("company", company);
         
         Map<String, Object> m = new HashMap<String, Object>();
         m.put("id", id);
         m.put("uid", user.getId());
         m.put("order", 1);
         //log.error("m："+m.toString());
         
         List<Map<String, Object>> list = fcNaturalDangerStudyMapper.selectByMap(m);
         //log.error("list："+list.toString());
         //log.error("checker3:"+ list.get(1).get("checker"));
         model.addAttribute("checker",list.get(1).get("checker"));
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
         //log.error("treeMap："+levmap.toString());
         model.addAttribute("treeMap", levmap); 
         
         model.addAttribute("action","read");
         return "company/study/naturaldanger-list";
    }
    
    
    /**
     * 运行管理——临时风险研判
     */
    @RequestMapping(value = "temporaryrisk-list")
    public String temporaryriskList(Model model, HttpServletRequest request) throws Exception {
    	 User user = getLoginUser(request); 
    	 model.addAttribute("uid", user.getId());
         Company company = companyMapper.selectByPrimaryKey(user.getId());
         model.addAttribute("company", company);
         /*
          * 根据临时风险填表统计表，判断当日是否填写流程作业证，有则将安全措施抓取，作为临时风险进行判断
          */
         FcTemporaryDangerCensus tdc = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
         Map<String, Object> m = new HashMap<String, Object>();
         List<String> listZyz = new ArrayList<String>();
         if(tdc == null){
        	 log.error("未存在检查表！");
        	 return "company/study/prompt-message2";
         }
         //动火作业证
         if(tdc.getTslc1Dh()>0){
        	 listZyz.add("动火作业证");    	 
         }
         //受限空间安全作业证
         if(tdc.getTslc2Sx()>0){
        	 listZyz.add("受限空间安全作业证");
         }
         //盲板抽堵安全作业证
         if(tdc.getTslc3Mb()>0){
        	 listZyz.add("盲板抽堵安全作业证"); 	 
         }
         //高处安全作业证
         if(tdc.getTslc4Gc()>0){
        	 listZyz.add("高处安全作业证");	 
         }
         //吊装安全作业证
         if(tdc.getTslc5Dz()>0){
        	 listZyz.add("吊装安全作业证");
         }
         //临时用电安全作业证
         if(tdc.getTslc6Yd()>0){
        	 listZyz.add("临时用电安全作业证"); 
         }
         //动土安全作业证
         if(tdc.getTslc7Dt()>0){
        	 listZyz.add("动土安全作业证");
         }
         //断路安全作业证
         if(tdc.getTslc8Dl()>0){
        	 listZyz.add("断路安全作业证");
         }
         //对检修作业现场进行安全检查的记录
         if(tdc.getZylc9Jwx()>0){
        	 listZyz.add("对检修作业现场进行安全检查的记录");
         }
         //承包商评价表
         if(tdc.getZylc10Cbs()>0){
        	 listZyz.add("承包商评价表");
         }
         if(listZyz == null || listZyz.isEmpty()){
        	 log.error("以存在检查表，填写表格数目都为0！");
        	 return "company/study/prompt-message2";
         }
         m.put("ZyzMc", listZyz);
         List<Map<String, Object>> list = fcTemporaryDangerMapper.selectByForeachMap(m);
         //log.error("list："+list.toString());
         model.addAttribute("list", list);
         
         Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>(); 
         for(Map<String, Object> m1 : list) {
             String sslcmc = null == m1.get("sslcmc") ? "" : m1.get("sslcmc").toString();
             String zyzmc = null == m1.get("zyzmc") ? "" : m1.get("zyzmc").toString();
             LinkedHashSet<String> l2s = levmap.get(sslcmc);
             if(null == l2s) {
                 l2s = new LinkedHashSet<String>();
                 levmap.put(sslcmc, l2s);
             }
             l2s.add(zyzmc);
         }
         log.error("treeMap："+levmap.toString());
         model.addAttribute("treeMap", levmap);  
         
         return "company/study/temporaryrisk-list";
    }
    /**
     * 运行管理——临时风险研判——保存
     */
    @RequestMapping(value = "temporaryrisk-save")
    public @ResponseBody Result temporaryriskSave(HttpServletRequest request, @RequestBody TemporaryDangerSaveReqDTO dto) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        //log.error("study："+dto.getStudy().toString());
        try {
            //当天是否保存过临时风险研判
        	FcTemporaryDangerStudy tds = fcTemporaryDangerStudyMapper.selectByUidToday(user.getId());
            if(tds== null){ 
            	
            	 cgfService.temporarydangerSave(dto);
            	
            } else{		
            	tds.setDel(1);
            	log.equals("tds："+tds.toString());
            	fcTemporaryDangerStudyMapper.updateByPrimaryKeySelective(tds);
            	cgfService.temporarydangerSave(dto);
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("保存失败");
			e.printStackTrace();
			result.setStatus("1");
    		result.setMess("保存失败");
    		return result;
		}
        return result;
    } 
    /**
     * 运行管理——档案管理——临时风险研判——回显
     */
    @RequestMapping(value = "temporaryrisk-show")
    public String temporaryriskShow(Model model, HttpServletRequest request,Integer id) throws Exception {
    	 User user = getLoginUser(request);  
         Company company = companyMapper.selectByPrimaryKey(user.getId());
         model.addAttribute("company", company);
         
         Map<String, Object> m = new HashMap<String, Object>();
         m.put("id", id);
         m.put("uid", user.getId());
         
         List<Map<String, Object>> list = fcTemporaryDangerStudyMapper.selectByMap(m);
         log.error("list："+list.toString());
         
         log.error("checker3:"+ list.get(1).get("checker"));
         model.addAttribute("checker",list.get(1).get("checker"));
         model.addAttribute("list", list);
         
         Map<String, LinkedHashSet<String>> levmap = new HashMap<String, LinkedHashSet<String>>(); 
         for(Map<String, Object> m1 : list) {
             String sslcmc = null == m1.get("sslcmc") ? "" : m1.get("sslcmc").toString();
             String zyzmc = null == m1.get("zyzmc") ? "" : m1.get("zyzmc").toString();
             LinkedHashSet<String> l2s = levmap.get(sslcmc);
             if(null == l2s) {
                 l2s = new LinkedHashSet<String>();
                 levmap.put(sslcmc, l2s);
             }
             l2s.add(zyzmc);
         }
         log.error("treeMap："+levmap.toString());
         model.addAttribute("treeMap", levmap);  
		 model.addAttribute("action","read");
         return "company/study/temporaryrisk-list";
    }
    /**
     * 运行管理——临时风险研判——调整字段
     * 当用户选择不需要进行临时风险研判时，将临时风险表的判断字段Tresult改为1·
     */
    @RequestMapping(value = "temporaryrisk-alter")
    public @ResponseBody Result temporaryriskAlter(HttpServletRequest request,Model model) throws Exception {
    	Result result = new ResultImpl();
    	User user = getLoginUser(request);
    	try{	
    		//当天是否保存过临时风险研判
    		FcTemporaryDangerStudy tds = fcTemporaryDangerStudyMapper.selectByUidToday(user.getId());
    		if(tds == null){
    			tds = new FcTemporaryDangerStudy();
    			Integer id = 1;
    			FcTemporaryDangerStudy lastrecord = fcTemporaryDangerStudyMapper.selectLastRecord();
    			if(lastrecord!=null){
    				id = lastrecord.getId()+1;
    			}
    			Date date = new Date();
    			log.error("Tdangerstudyid:"+id);
    			tds.setId(id);
    			tds.setUid(user.getId());
    			tds.setTresult(1);
    			tds.setCtime(date);
    			fcTemporaryDangerStudyMapper.insertSelective(tds);
    		}else{
    			tds.setTresult(1);
    			fcTemporaryDangerStudyMapper.updateByPrimaryKeySelective(tds);
    		}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("保存失败");
			e.printStackTrace();
			result.setStatus("1");
    		result.setMess("保存失败");
    		return result;
		}
        return result;     	   	
    } 
    /**
     * 运行管理——重大/较大隐患治理风险研判
     */
    @RequestMapping(value = "greatdanger-list")
    public String greatdangerList(Model model, HttpServletRequest request) throws Exception {
    	 User user = getLoginUser(request);
    	 model.addAttribute("uid", user.getId());
         Company company = companyMapper.selectByPrimaryKey(user.getId());
         model.addAttribute("company", company);
         
         Map<String, Object> m = new HashMap<String, Object>(); 
         
         //当天是否进行固有风险研判
         FcNaturalDangerStudy nd = fcNaturalDangerStudyMapper.selectByUidToday(user.getId());
         if(nd== null){ 
        	 return "company/study/prompt-message1";
         } else{
        	 model.addAttribute("dangerstudyid", nd.getId());
        	 m.put("id", nd.getId());       	 
         } 
         m.put("uid", user.getId());
         m.put("order", 1);
         m.put("checkret", 0);
         /*
          * 将重大/较大隐患分别查询
          */
         m.put("level", "红色");
         List<Map<String, Object>> list = fcNaturalDangerStudyMapper.selectByMap(m);//查询重大隐患风险
         m.put("level", "橙色");
         List<Map<String, Object>> list22 = fcNaturalDangerStudyMapper.selectByMap(m);//查询较大隐患风险
         list.addAll(list22);
         //log.error("list:"+list.toString());
         for (int i = 0; i < list.size(); i++) {
			//list.get(i).put("checkretBigd", 1);
			 //log.error("asas:"+list.get(i).get("checkretBigd"));
			 if(list.get(i).get("checkretBigd") != null){
				 list.get(i).put("checkret", list.get(i).get("checkretBigd"));
			 }
		}
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
        return "company/study/greatdanger-list";
    }
    /**
     * 运行管理——重大/较大隐患治理风险研判——图片插入（跳转页面）
     */
    @RequestMapping(value="greatdanger-imgadd")
    public String zhengAdd(Model model, HttpServletRequest request, Integer dangerid) throws Exception {
        User user = getLoginUser(request);
        //log.error("dangerid:"+dangerid);
        Map<String, Object> m = new HashMap<String, Object>(); 
        FcNaturalDangerStudy nds = fcNaturalDangerStudyMapper.selectByUidToday(user.getId());
        m.put("dangerstudyid",nds.getId());
        m.put("dangerid",dangerid);
        FcNaturalDangerItem ndi = fcNaturalDangerItemMapper.selectByMapDanger(m);
    	//log.error("fcNaturalDangerItem:"+ndi.toString());
    	model.addAttribute("ndi",ndi);
        return "company/study/greatdanger-imgadd";
    }
    /**
     * 运行管理——重大/较大隐患治理风险研判——图片插入（保存）
     */
    @RequestMapping(value="greatdanger-imgsave")
    public @ResponseBody Result zhengSave(Model model, HttpServletRequest request, FcNaturalDangerItem ndi) throws Exception {
        Result result = new ResultImpl();
        //log.error("ndi："+ndi.toString());
        if(null == ndi.getId()) {
        	fcNaturalDangerItemMapper.insertSelective(ndi);
        } else {
        	fcNaturalDangerItemMapper.updateByPrimaryKeySelective(ndi);
        }
        return result;
    }
    /**
     * 运行管理——重大/较大隐患治理风险研判——保存
     */
    @RequestMapping(value = "greatdanger-save")
    public @ResponseBody Result greatdangerSave(HttpServletRequest request, @RequestBody GreatdangerSaveReqDTO dto) throws Exception {
    	//log.error("naturaldangerSave");
        Result result = new ResultImpl();
        User user = getLoginUser(request);   
        try {
            //当天是否保存过重大/较大风险研判
            FcGreatDangerStudy gd = fcGreatDangerStudyMapper.selectByUidToday(user.getId());
            if(gd== null){ 
            	
            	cgfService.greatdangerSave(dto);
            	
            } else{		
            	gd.setDel(1);
            	fcGreatDangerStudyMapper.updateByPrimaryKeySelective(gd);
            	
            	cgfService.greatdangerSave(dto);
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("保存失败");
			e.printStackTrace();
			result.setStatus("1");
    		result.setMess("保存失败");
    		return result;
		}
        
        return result;
    } 
    /**
     * 运行管理——档案管理——重大/较大风险研判——回显
     */
    @RequestMapping(value = "greatdanger-show")
    public String greatdangerShow(Model model, HttpServletRequest request,Integer id) throws Exception {
    	 User user = getLoginUser(request);  	  
         Company company = companyMapper.selectByPrimaryKey(user.getId());
         model.addAttribute("company", company);
         
         FcGreatDangerStudy gd = fcGreatDangerStudyMapper.selectByDangerStudyId(id);
         model.addAttribute("checker",gd.getChecker());
         
         Map<String, Object> m = new HashMap<String, Object>();
         m.put("id", id);
         m.put("uid", user.getId());
         m.put("order", 1);
         m.put("checkret", 0); 
         /*
          * 将重大/较大隐患分别查询
          */
         m.put("level", "红色");
         List<Map<String, Object>> list = fcNaturalDangerStudyMapper.selectByMap(m);//查询重大隐患风险
         m.put("level", "橙色");
         List<Map<String, Object>> list22 = fcNaturalDangerStudyMapper.selectByMap(m);//查询较大隐患风险
         list.addAll(list22);
         for (int i = 0; i < list.size(); i++) {
			list.get(i).put("checkret", list.get(i).get("checkretBigd"));
		}
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
         //log.error("treeMap："+levmap.toString());
         model.addAttribute("treeMap", levmap); 
         
         model.addAttribute("action","read");
         return "company/study/greatdanger-list";
    }
    
    /**
     * 运行管理——档案管理
     */
    @RequestMapping(value = "files-select")
    public String filesList(HttpServletRequest request, Model model) throws Exception {
       return "company/study/files-select";

    }
    /**
     * 档案管理——list
     */
    @RequestMapping(value = "files-list")
    public String specialList(Model model, HttpServletRequest request, Integer type) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("type",type);
        if(type==1){//动火作业证
         	List<FcFireworkPermit> list = fcFireworkPermitMapper.selectByUid(user.getId());
         	//log.error("动火作业证list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 2){//受限空间安全作业证
         	List<FcLimitspacePermit> list = fcLimitspacePermitMapper.selectByUid(user.getId());
         	//log.error("受限空间安全作业证list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 3){//盲板抽堵安全作业证
         	List<FcBlindplatePermit> list = fcBlindplatePermitMapper.selectByUid(user.getId());
         	log.error("盲板抽堵安全作业证list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 4){//高处安全作业证
         	List<FcHighsafetyPermit> list = fcHighsafetyPermitMapper.selectByUid(user.getId());
         	log.error("高处安全作业证list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 5){//吊装安全作业证
         	List<FcHoistingPermit> list = fcHoistingPermitMapper.selectByUid(user.getId());
         	log.error("吊装安全作业证list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 6){//临时用电安全作业证
         	List<FcPowersafetyPermit> list = fcPowersafetyPermitMapper.selectByUid(user.getId());
         	log.error("临时用电安全作业证list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 7){//动土安全作业证
         	List<FcLandworkPermit> list = fcLandworkPermitMapper.selectByUid(user.getId());
         	log.error("动土安全作业证list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 8){//断路安全作业证
         	List<FcBrokenlandPermit> list = fcBrokenlandPermitMapper.selectByUid(user.getId());
         	log.error("断路安全作业证list："+list.toString());
         	model.addAttribute("list",list);
        } 
        else if(type == 9){//对检修作业现场进行安全检查的记录
         	List<FcOverhaulOperation> list = fcOverhaulOperationMapper.selectByUid(user.getId());
         	log.error("断路安全作业证list："+list.toString());
         	model.addAttribute("list",list);
        } 
        else if(type == 10){//承包商评价表
         	List<FcContractorOperation> list = fcContractorOperationMapper.selectByUid(user.getId());
         	log.error("承包商评价表list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 11){//目标履职考核
         	List<FcTargetAssessment> list = fcTargetAssessmentMapper.selectByUid(user.getId());
         	log.error("目标履职考核list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 12){//固有风险研判
         	List<FcNaturalDangerStudy> list = fcNaturalDangerStudyMapper.selectByUid(user.getId());
         	log.error("固有风险研判list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 13){//临时风险研判
//         	List<FcTemporaryDangerStudy> list1 = fcTemporaryDangerStudyMapper.selectByUid(user.getId());
//         	log.error("临时风险研判list1："+list1.toString());
         	List<FcTemporaryDangerStudy> list = fcTemporaryDangerStudyMapper.selectByMapUid(user.getId());
         	log.error("临时风险研判list："+list.toString());
         	model.addAttribute("list",list);
        }
      	else if(type == 14){//重大、较大隐患治理风险研
         	List<FcGreatDangerStudy> list = fcGreatDangerStudyMapper.selectByUid(user.getId());
         	log.error("重大、较大隐患治理风险研list："+list.toString());
         	model.addAttribute("list",list);
        }
        else if(type == 15){//安全承诺公告牌
         	List<FcPromise> list = fcPromiseMapper.selectByUid(user.getId());
         	log.error("安全承诺公告牌list："+list.toString());
         	model.addAttribute("list",list);
        }    
        else{
        	return "company/study/files-select";
        } 
        
        return "company/study/files-list";
       
    }
    /**
     * 档案管理——详情
     */
    @RequestMapping(value = "files-detail")
    public String specialDetail(HttpServletRequest request,Integer id,Model model,Integer type) throws Exception {
            if(type==1){//动火作业证
        		FcFireworkPermit fw = fcFireworkPermitMapper.selectByPrimaryKey(id);
        		//log.error("动火作业证fw："+fw.toString());
        		model.addAttribute("fw",fw);
        		model.addAttribute("action","read");
        		//log.error("动火作业证fw end");
        		return "company/study/special-firework";
            }
            else if(type == 2){//受限空间安全作业证
            	FcLimitspacePermit ls = fcLimitspacePermitMapper.selectByPrimaryKey(id);
        		//log.error("受限空间安全作业证："+ls.toString());
        		model.addAttribute("ls",ls);
        		model.addAttribute("action","read");
        		//log.error("受限空间安全作业证ls end");
        		return "company/study/special-limitspace";
            }
            else if(type == 3){//盲板抽堵安全作业证
            	FcBlindplatePermit bp = fcBlindplatePermitMapper.selectByPrimaryKey(id);
        		log.error("盲板抽堵安全作业证："+bp.toString());
        		model.addAttribute("bp",bp);
        		model.addAttribute("action","read");
        		log.error("盲板抽堵安全作业证bp end");
        		return "company/study/special-blindplate";  
            }
            else if(type == 4){//高处安全作业证
            	FcHighsafetyPermit hs = fcHighsafetyPermitMapper.selectByPrimaryKey(id);
        		log.error("高处安全作业证："+hs.toString());
        		model.addAttribute("hsw1",hs);
        		model.addAttribute("action","read");
        		log.error("高处安全作业证hsw1 end");
        		return "company/study/special-highsafety";  
            }
            else if(type == 5){//吊装安全作业证
            	FcHoistingPermit hi = fcHoistingPermitMapper.selectByPrimaryKey(id);
        		log.error("吊装安全作业证："+hi.toString());
        		model.addAttribute("hi",hi);
        		model.addAttribute("action","read");
        		log.error("吊装安全作业证hi end");
        		return "company/study/special-hoisting";  
            }
            else if(type == 6){//临时用电安全作业证
            	FcPowersafetyPermit ps = fcPowersafetyPermitMapper.selectByPrimaryKey(id);
        		log.error("临时用电安全作业证："+ps.toString());
        		model.addAttribute("hi",ps);
        		model.addAttribute("action","read");
        		log.error("临时用电安全作业证ps end");
        		return "company/study/special-powersafety";  
            }
            else if(type == 7){//动土安全作业证
            	FcLandworkPermit lw = fcLandworkPermitMapper.selectByPrimaryKey(id);
        		log.error("动土安全作业证："+lw.toString());
        		model.addAttribute("lw",lw);
        		model.addAttribute("action","read");
        		log.error("动土安全作业证bp end");
        		return "company/study/special-landwork";  
            }
            else if(type == 8){//断路安全作业证
            	FcBrokenlandPermit bl = fcBrokenlandPermitMapper.selectByPrimaryKey(id);
        		log.error("断路安全作业证："+bl.toString());
        		model.addAttribute("bl",bl);
        		model.addAttribute("action","read");
        		log.error("断路安全作业证bl end");
        		return "company/study/special-brokenland";  
            }
            else if(type == 9){//对检修作业现场进行安全检查的记录
            	FcOverhaulOperation oh = fcOverhaulOperationMapper.selectByPrimaryKey(id);
        		log.error("对检修作业现场进行安全检查的记录："+oh.toString());
        		model.addAttribute("oh",oh);
        		model.addAttribute("action","read");
        		log.error("对检修作业现场进行安全检查的记录oh end");
        		return "company/study/overhaul-operation";  
            }
            else if(type == 10){//承包商评价表
            	FcContractorOperation con = fcContractorOperationMapper.selectByPrimaryKey(id);
        		log.error("承包商评价表："+con.toString());
        		model.addAttribute("con",con);
        		model.addAttribute("action","read");
        		log.error("承包商评价表con end");
        		return "company/study/contractor-operation";  
            }
            else if(type == 11){//目标履职考核
            	FcTargetAssessment ta = fcTargetAssessmentMapper.selectByPrimaryKey(id);
        		log.error("目标履职考核："+ta.toString());
        		model.addAttribute("ta",ta);
        		model.addAttribute("action","read");
        		log.error("目标履职考核con end");
        		return "company/study/target-assessment";  
            }
            else if(type == 15){//安全承诺公告牌
                User user = getLoginUser(request);
                model.addAttribute("CompanyName", companyMapper.selectByPrimaryKey(user.getId()).getName());
//                log.error("id"+id);
//                log.error("type"+type);
                FcPromise pro = fcPromiseMapper.selectByPrimaryKey(id);
        		log.error("安全承诺公告牌："+pro.toString());
        		model.addAttribute("pro",pro);
        		model.addAttribute("action","read");
        		log.error("安全承诺公告牌pr end");
        		return "company/study/promise";  
            }
            else{
            	return "company/study/files-select";
            }
    }
    
    
    /**
     * 承诺公告
     */
    @RequestMapping(value = "promise")
    public String promise(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
   
        //验证目标考核比表信息
        FcTargetAssessment fcTargetAssessment= fcTargetAssessmentMapper.selectByUidToday(user.getId());
        
        if(fcTargetAssessment==null){ 
        	fcTargetAssessment = new FcTargetAssessment();
        	fcTargetAssessment.setUid(user.getId());
        	fcTargetAssessment.setKhsj(new Date());
        	fcTargetAssessment.setDel(0);
        	fcTargetAssessment.setCtime(new Date());
        	fcTargetAssessmentMapper.insertSelective(fcTargetAssessment);
        	fcTargetAssessment = fcTargetAssessmentMapper.selectByUidToday(user.getId());
        }

//        log.error("fcTargetAssessment.getBkhbm():"+fcTargetAssessment.getBkhbm());
//        log.error("fcTargetAssessment.getBkhbm() == null："+fcTargetAssessment.getBkhbm() == null);
//        log.error("fcTargetAssessment.getBkhbm()："+"".equals(fcTargetAssessment.getBkhbm().trim()));  
//        log.error("_______________________________________________________________");     
//        log.error("fcTargetAssessment.getFzrbkhbm():"+fcTargetAssessment.getFzrbkhbm());
//        log.error("fcTargetAssessment.getFzrbkhbm() == null："+fcTargetAssessment.getFzrbkhbm() == null);
        //log.error("fcTargetAssessment.getFzrbkhbm() == null："+"".equals(fcTargetAssessment.getFzrbkhbm()));
//        log.error("："+);

        if(fcTargetAssessment.getBkhgw() == null || fcTargetAssessment.getBkhgw().isEmpty() ||
        	fcTargetAssessment.getKhd1() == null || fcTargetAssessment.getKhd1().trim().equals("0") ||
        	fcTargetAssessment.getKhd2() == null || fcTargetAssessment.getKhd2().trim().equals("0") ||
        	fcTargetAssessment.getKhd3() == null || fcTargetAssessment.getKhd3().trim().equals("0") ||
        	fcTargetAssessment.getKhd4() == null || fcTargetAssessment.getKhd4().trim().equals("0") ||
        	fcTargetAssessment.getKhd5() == null || fcTargetAssessment.getKhd5().trim().equals("0") ||
        	fcTargetAssessment.getKhd6() == null || fcTargetAssessment.getKhd6().trim().equals("0") ||
        	fcTargetAssessment.getKhd7() == null || fcTargetAssessment.getKhd7().trim().equals("0") ||
        	fcTargetAssessment.getKhbm() == null || fcTargetAssessment.getKhbm().isEmpty() ||
        	fcTargetAssessment.getBkhbm() == null || fcTargetAssessment.getBkhbm().isEmpty() ||
        	fcTargetAssessment.getKhrkhbm() == null || fcTargetAssessment.getKhrkhbm().isEmpty() ||
        	fcTargetAssessment.getFzrbkhbm() == null || fcTargetAssessment.getFzrbkhbm().isEmpty() ||
        	fcTargetAssessment.getCjzrkhbm() == null || fcTargetAssessment.getCjzrkhbm().isEmpty() ||
        	fcTargetAssessment.getCjzrbkhbm() == null || fcTargetAssessment.getCjzrbkhbm().isEmpty() ||
        	fcTargetAssessment.getBmjlkhbm() == null || fcTargetAssessment.getBmjlkhbm().isEmpty() ||
        	fcTargetAssessment.getBmjlbkhbm() == null || fcTargetAssessment.getBmjlbkhbm().isEmpty())
        	{
        	
        		return "company/study/prompt-message";
        	
        	}
        
        //验证临时风险研判结果
        FcTemporaryDangerStudy tds = fcTemporaryDangerStudyMapper.selectByUidToday(user.getId());
        if(tds == null){
            /*
             * 根据临时风险填表统计表，判断当日是否填写流程作业证，有则将安全措施抓取，作为临时风险进行判断
             */
            FcTemporaryDangerCensus tdc = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
            log.error("临时风险研判结果不存在，临时风险填表统计表");
//            if(tdc == null){
//            	return "company/study/prompt-message3";
//            }
//            return "company/study/prompt-message4";  
            	return "company/study/prompt-message3";//当流程管理中当选择有流程却不填写表时bug
        }
        if(tds.getTresult() == 0){
        	 log.error("临时风险研判结果存在不合格现象");
        	return "company/study/prompt-message4";
        }
          
        //当天是否进行固有风险研判
        FcNaturalDangerStudy Nd = fcNaturalDangerStudyMapper.selectByUidToday(user.getId());
        if(Nd == null){ 
        	return "company/study/prompt-message";
        	
        } else{	
        	/*
        	 * 承诺公告数据查询与生成   	
        	 */
         	Company company = companyMapper.selectByPrimaryKey(user.getId());
        	//log.error("company："+company.toString());
        	model.addAttribute("CompanyName", company.getName());//企业名称
        	//判断当天是否有承诺公告
        	FcPromise pro = fcPromiseMapper.selectByUidToday(user.getId());
        	if(pro == null){
        		pro = new FcPromise();
        		pro.setUid(user.getId());
//        		/*
//        		 * 生产设备
//        		 */
//        		pro.setNumSc(10);
//        		pro.setNumYx(5);
//        		pro.setNumTc(3);
        		
        		/*
                 * 根据临时风险填表统计表，判断当日是否填写流程作业证
                 */
        		//检修、特殊+动火、动火
        		 FcTemporaryDangerCensus tdc1 = fcTemporaryDangerCensusMapper.selectByUidToday(user.getId());
                 if(tdc1 == null){
                	pro.setNumJx(0);
                 	pro.setNumDh(0);
                 	pro.setNumSx(0);
                 }else{
                	 pro.setNumJx(tdc1.getZylc9Jwx());
                	 Integer dh = tdc1.getTslc1Dh()+tdc1.getTslc3Mb()+tdc1.getTslc4Gc()+tdc1.getTslc5Dz()+tdc1.getTslc6Yd()+tdc1.getTslc7Dt()+tdc1.getTslc8Dl();
                	 pro.setNumDh(dh);
                	 pro.setNumSx(tdc1.getTslc2Sx());
                 }
                 /*
                  * 试生产、开停车
                  */
                 pro.setSsc(0);
                 pro.setKtc(0);
                 pro.setAqzt(1);
                 /*
                  * 主要负责人、承诺公告生成时间
                  */
                 pro.setZyfzr(company.getLegal());//企业负责人
                 Date date = new Date();
                 pro.setAqcnsj(date);
                 pro.setCtime(date);

                 fcPromiseMapper.insertSelective(pro);
        		
        	}
        	
        	log.error("pro："+pro.toString());
        	
        	model.addAttribute("pro", pro);
        	return "company/study/promise";
        }   
    }
    /**
     * 承诺公告————保存
     */
    @RequestMapping(value = "promise-save")
    public @ResponseBody Result promiseSave(HttpServletRequest request, FcPromise pro) throws Exception {
        Result result = new ResultImpl();
        log.error("Pro："+pro.toString());
        User user = getLoginUser(request);
        try {
        	FcPromise pro1 = fcPromiseMapper.selectByUidToday(user.getId());
        	pro1.setNumSc(pro.getNumSc());
        	pro1.setNumYx(pro.getNumYx());
        	pro1.setNumTc(pro.getNumTc());        

        	fcPromiseMapper.updateByPrimaryKeySelective(pro1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("保存失败");
			e.printStackTrace();
			result.setStatus("1");
    		result.setMess("保存失败");
    		return result;
		}
        return result;
    } 
  
}
