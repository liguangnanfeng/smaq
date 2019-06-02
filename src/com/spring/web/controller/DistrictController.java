/**  
 * All right reserved. 
 */
package com.spring.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.web.util.EncryptUtil;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.CompanyIskey;
import com.spring.web.model.Officials;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.tobject.cgf.CompanyListReqDTO;

/** 
* @Title: DistrictController 
* @Description: TODO(用一句话描述该文件做什么) 
* @author CGF   
* @date 2017年11月20日 下午4:53:28 
* @version V1.0   
*/
@Controller
@RequestMapping("/district")
public class DistrictController extends BaseController {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = -9202435271301177005L;

    /**
     * 区县首页
     */
    @RequestMapping(value = "main")
    public String main(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("districtId", user.getId());
        List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
        model.addAttribute("list", list);
        model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("loginUserId", user.getId());
        return "area/main";
    }
    
    /**
     * to企业添加页面
     */
    @RequestMapping(value = "vilage-list")
    public @ResponseBody Result vilageList(Integer townId) throws Exception {
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("townId", townId);
        List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
        result.setMap("list", list);
        return result;
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
        
        model.addAttribute("district", districtMapper.selectByPrimaryKey(user.getId()));
        
        m.clear();
        setUserId(user, m);
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("count", count);
        
        model.addAttribute("loginUserId", user.getId());
        
        //add wz 2019-02-21
        HttpSession session = request.getSession();
        User moveBeforeUser =  (User)session.getAttribute("moveBefore");
        if(moveBeforeUser != null){
            model.addAttribute("moveD",1);
            model.addAttribute("nameBefore",userMapper.selectByPrimaryKey(moveBeforeUser.getId()).getUserName());
        }
        
        return "area/welcome";
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
     * 执法人员保存
     * 当密码小于三十位数的话就表示是新增/修改密码
     */
    @RequestMapping(value = "off-save")
    public @ResponseBody Result offSave(Officials off, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();

        if(off.getPassword().length()<30){ //表示是新密码
            String encryptedPwd = EncryptUtil.encrypt(off.getPassword());
            off.setPassword(encryptedPwd);
        }
        if(null == off.getId()) {
            User user = getLoginUser(request);
            off.setDel(0);
            off.setFlag(user.getUserType());
            off.setTime(new Date());
            off.setUid(user.getId());
            officialsMapper.insertSelective(off);
        } else {
            officialsMapper.updateByPrimaryKeySelective(off); 
        }
        return result;
    }
    
    /**
     * 执法人员添加
     * 要将密码进行加密
     */
    @RequestMapping(value = "off-add")
    public String offAdd(Integer id, Model model) throws Exception {

        if(null != id) {
            model.addAttribute("p", officialsMapper.selectByPrimaryKey(id));
        }
        return "area/officials/officials-add";
    }
    /**
     * 执法人员删除
     */
    @RequestMapping(value = "off-del")
    public @ResponseBody Result offdel(Integer id, Integer del) throws Exception {
        Result result =  new ResultImpl();

        if(null == id) {
            return null;
        }
        Officials officials = officialsMapper.selectByPrimaryKey(id);
        officials.setDel(del);
        officialsMapper.updateByPrimaryKey(officials);
        return result;
    }
    
    /**
     * 执法人员 查询
     */
    @RequestMapping(value = "off-list")
    public String offList(HttpServletRequest request, Model model, String name) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        m.put("name", name);
        List<Map<String, Object>> list = officialsMapper.selectList(m);
        model.addAttribute("list", list);
        model.addAttribute("map", m);
        return "area/officials/officials-list";
    }
    
    /**
     * 企业分布页面
     */
    @SuppressWarnings("unchecked")
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

        List<Map<String, Object>> townList = townMapper.selectListByDistrict(m);
        for (int i = 0; i < townList.size(); i++) {
            Map<String, Object> item = townList.get(i);
            item.put("townId", item.get("userId"));
        }
        String userStr = JSONObject.toJSONString(user);
        model.addAttribute("user", userStr);
        String townLists = JSONArray.toJSONString(townList);
        model.addAttribute("townList", townLists);
        return "area/company/company-map1";
    } 
    
}
