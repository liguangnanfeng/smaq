
package com.spring.web.controller.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.TCleanWarnSetting;
import com.spring.web.model.User;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.util.DateConvertUtil;

/** 
* @Title: AppController_newAddVillage_xlb 
* @Description: TODO(用一句话描述该文件做什么) 
* @date 2018年5月29日 上午10:07:33 
* @version V1.0   
*/
@Controller
@RequestMapping("api/newadd/village/xlb")
public class AppController_newAddVillage_xlb extends BaseController{

    private static final long serialVersionUID = 1L;
    
    void setUserId(Integer userId, Map<String, Object> m) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getUserType() == 4) {
            m.put("villageId", user.getId());
        }
        if (user.getUserType() == 5) {
            m.put("userId", user.getId());
        }
        if (user.getUserType() == 3) {
            m.put("townId", user.getId());
        }
        if (user.getUserType() == 6) {
            m.put("districtId", user.getId());
        }
        if (user.getUserType() == 10) {
            m.put("tradeId", user.getId());
        }
    }
    
    /**
     * 企业列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "A001", method = RequestMethod.POST)
    public @ResponseBody AppResult modelList2(HttpServletRequest request, String companyName,String onlyProblemCompany,String flag,Integer page,Integer limit) {
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        setUserId(userId, m);
        if(!StringUtils.isEmpty(companyName)){
            try {
                companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(),e);
            }
        }
        m.put("companyName", companyName);
        if(!StringUtils.isEmpty(onlyProblemCompany)){
            m.put("showProblemCompany", onlyProblemCompany);
        }
        if(!StringUtils.isEmpty(flag)){
            m.put("flag", flag);
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if(null == page){
            page = 1;
        }
        if(null == limit){
            limit = 10;
        }
        m.put("page", (page-1)*limit);
        if(page == 1){
            m.put("limit", Integer.MAX_VALUE);
            List<DynamicParameter<String, Object>> list2 = companyMapper.selectNeedCompanyList(m);
            map.put("total", list2.size());
        }
        m.put("limit", limit);
        List<DynamicParameter<String, Object>> list = companyMapper.selectNeedCompanyList(m);
        for (int i = 0; i < list.size(); i++) {
            if (user.getUserType() == 3) {
                list.get(i).put("isKey", list.get(i).get("key1"));
            }
            if (user.getUserType() == 6) {
                list.get(i).put("isKey", list.get(i).get("key2"));
            }
            if (user.getUserType() == 9) {// 安泰没有重点企业
                list.get(i).put("isKey", 0);
            }
            if (user.getUserType() == 10) {
                list.get(i).put("isKey", list.get(i).get("tradeKey"));
            }
        }
        map.put("list", list);
        result.setData(map);
        return result;
    }
    
    /**
     * 隐患数据库
     */
    @RequestMapping(value = "A015")
    public @ResponseBody AppResult modelList(HttpServletRequest request, Integer d, Integer status, Integer userId,
            String flag, String companyName,String memo) {
        AppResult result = new AppResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        
        Integer _userId = getAppUserId(request);
        if (null == _userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        
        
        if(null == userId){
            setUserId(_userId, m);
        }else{
            m.put("userId", userId);
        }
        //m.put("userId", 26);//debug
        
        
        m.put("flag", flag);
        m.put("status", status);
        m.put("d", d);
        try {
            if(!StringUtils.isEmpty(companyName)){
                companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                m.put("companyName", companyName);
            }
            if(!StringUtils.isEmpty(memo)){
                memo = URLDecoder.decode(URLDecoder.decode(memo, "utf-8"),"utf-8");
                m.put("memo", memo);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> list = tCheckItemMapper.selectNeedDangerIndexList(m);
        log.error("list:"+list);
        result.setData(list);
        return result;
    }
    
}
