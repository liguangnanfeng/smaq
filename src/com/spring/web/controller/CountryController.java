/**  
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.User;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.util.ConstantsUtil;

/** 
* @Title: DistrictController 
* @Description: TODO(用一句话描述该文件做什么) 
* @author CGF   
* @date 2017年11月20日 下午4:53:28 
* @version V1.0   
*/
@Controller
@RequestMapping("/country")
public class CountryController extends BaseController {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = -9202435271301177005L;
    
    /**
     * 区县首页
     */
    @RequestMapping(value = "main")
    public String main(HttpServletRequest request, Model model) throws Exception {
    	//log.error("各地企业："+districtMapper.selectDistrict());
        model.addAttribute("list", districtMapper.selectDistrict());
        //log.error("部门、人员管理："+tradeMapper.selectTrade());
        model.addAttribute("list1", tradeMapper.selectTrade());
        User user = getLoginUser(request);
        model.addAttribute("name_", user.getUserName());
        model.addAttribute("loginUserId", user.getId());
        return "country/main";
    }
    
    /**
     * 政府首页-中间
     */
    @RequestMapping(value = "welcome")
    public String welcome(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        
        CompanyListReqDTO dto = new CompanyListReqDTO();
        List<Integer> count = userService.selectCount(dto, user);

        model.addAttribute("number", companyMapper.selectIdsByCompany_view_count(dto));//企业数量

        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("count", count);
        
        model.addAttribute("loginUserId", user.getId());
        return "country/welcome";
    }
    
    /**
     * 企业列表
     */
    @RequestMapping(value = "company-list")
    public String companyList(Integer userId, Model model, HttpServletRequest request, CompanyListReqDTO dto) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("companyName", dto.getCompanyName());
        m.put("isFreeze", dto.getIsFreeze());
        m.put("cisDanger", dto.getCisDanger());
        m.put("dlevel", dto.getDlevel());
        m.put("isKey", dto.getIsKey());
        m.put("districtId", dto.getDistrictId());
        m.put("doubleDanger", dto.getDoubleDanger());
        m.put("danger", dto.getDanger());
        m.put("hazard", dto.getHazard());
        String industry2 = null;
        if(StringUtils.isNotBlank(dto.getIndustry2_())) {
            industry2 = dto.getIndustry2_();
            if(StringUtils.isNotBlank(dto.getIndustry2_2())) {
                industry2 = industry2 + " > " + dto.getIndustry2_2();
            }
        }
        m.put("industry2", industry2);
        m.put("industry", dto.getIndustry());
        if(null == dto.getPage()) {
            dto.setPage(0);
        }
        if(null == dto.getRows()) {
            dto.setRows(ConstantsUtil.SELECT_COUNT);
        }
        m.put("index", dto.getPage() * dto.getRows());
        m.put("rows", dto.getRows());
        m.put("page", dto.getPage());
        List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyList(m);
        model.addAttribute("list", mlist);
        model.addAttribute("total", companyMapper.selectCompanyList_count(m));
        
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        
        model.addAttribute("m", m);
        if (null != dto.getCisDanger()) {
            return "country/company/company-list3";
        }
        if (StringUtils.isNotBlank(dto.getDlevel())) {
            return "country/company/company-list2";
        }
        if(dto.getDanger() != null){
            return "country/company/company-list-gwzy";
        }
        if(dto.getHazard() != null){
            return "country/company/company-list-zdwxy";
        }
        return "country/company/company-list";
    }
    
    /**
     * 企业分布页面
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("company/company-map")
    public String coMap(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("page", 0);
        m.put("limit", 20);
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
        for (int i = 0; i < list.size(); i++) {
            DynamicParameter<String, Object> item = list.get(i);
            item.put("id", i + 1 + "");
        }
        Integer count = companyMapper.selectCountMap(m);
        String listStr = JSONArray.toJSONString(list);
        model.addAttribute("companyList", listStr);
        model.addAttribute("count", count);

        List<Map<String, Object>> townList = townMapper.selectListByDistrict(m);
        for (int i = 0; i < townList.size(); i++) {
            Map<String, Object> item = townList.get(i);
            item.put("townId", item.get("userId"));
        }
        String userStr = JSONObject.toJSONString(user);
        model.addAttribute("user", userStr);
        String townLists = JSONArray.toJSONString(townList);
        model.addAttribute("townList", townLists);
        return "country/company/company-map1";
    }

    /**
     * 
     */
    @RequestMapping(value = "welcometrade")
    public String welcometrade(Model model, HttpServletRequest request,Integer tradeId) throws Exception {
        User user = userMapper.selectByPrimaryKey(tradeId);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        CompanyListReqDTO dto = new CompanyListReqDTO();
        List<Integer> count = userService.selectCount(dto, user);

        String userIds = (String) m.get("userIds");
        if(userIds.equals("-1")) {
            model.addAttribute("number", 0);
        } else {
            model.addAttribute("number", userIds.split(",").length);//企业数量
        }
        
        m.put("tradeId", null);
        model.addAttribute("name_", tradeMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("count", count);
        return "managetrade/welcome";
    }
    
}
