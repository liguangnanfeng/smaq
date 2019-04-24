/**  
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.CompanyIskey;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.tobject.cgf.CompanyListReqDTO;

/** 
* @Title: TownController 
* @Description: TODO(用一句话描述该文件做什么) 
* @author CGF   
* @date 2017年11月2日 上午10:41:51 
* @version V1.0   
*/
@Controller
@RequestMapping("/town")
public class TownController extends BaseController {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = -9202435271301177005L;
    
    /**
     * 镇首页
     */
    @RequestMapping(value = "main")
    public String main(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("townId", user.getId());
        List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
        model.addAttribute("list", list);
        model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("loginUserId", user.getId());
        return "town/main";
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
        
        model.addAttribute("town", townMapper.selectByPrimaryKey(user.getId()));
        
        m.clear();
        setUserId(user, m);
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        
        model.addAttribute("count", count);
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("loginUserId", user.getId());
        
        //add wz 2019-02-21
        HttpSession session = request.getSession();
        User moveBeforeUser =  (User)session.getAttribute("moveBefore");
        if(moveBeforeUser != null){
            model.addAttribute("moveD",1);
            model.addAttribute("nameBefore", districtMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName());
        }
        
        return "town/welcome";
    }
    
    /**
     * 是否重点企业
     */
    @RequestMapping(value = "user-key")
    public @ResponseBody Result userisKey(Integer userId, Integer key1) throws Exception {
        Result result = new ResultImpl();
        CompanyIskey key = companyIskeyMapper.selectByPrimaryKey(userId);
        if(null == key) {
            key = new CompanyIskey(userId, key1, 0, 0, 0);
            companyIskeyMapper.insertSelective(key);
        } else {
            key.setKey1(key1);
            companyIskeyMapper.updateByPrimaryKeySelective(key);
        }
        return result;
    }
    
    /**
     * 企业分布页面
     */
    @RequestMapping("company/company-map")
    public String coMap(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
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

        List<DynamicParameter<String, Object>> villageList = villageMapper.selectListByTown(m);
        for (int i = 0; i < villageList.size(); i++) {
            DynamicParameter<String, Object> item = villageList.get(i);
            item.put("villageId", item.get("userId").toString());
        }
        String userStr = JSONObject.toJSONString(user);
        model.addAttribute("user", userStr);
        String villageLists = JSONArray.toJSONString(villageList);
        model.addAttribute("villageList", villageLists);
        return "town/company/company-map1";
    } 
}
