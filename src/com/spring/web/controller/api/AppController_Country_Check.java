package com.spring.web.controller.api;

import com.spring.web.model.Officials;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.CheckCompany.CountryCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 19:26
 * 行政检查,
 * 编号从220开始
 * 首先要判断政府人员的权限 获取所在的usertype : 然后根据usertype根据数据进行保存
 * 获取所在的地区和地区所有的企业,获取企业的风险点
 * <p>
 * 根据行业,获取该行业所有的风险点
 */

@Controller
@RequestMapping(value = "api/country/check")
public class AppController_Country_Check {

    /**
     * token验证
     */
    @Autowired
    private AppTokenData appTokenData;

    /**
     * 政府端查询数据
     */
    @Autowired
    private CountryCheck countryCheck;


    /**
     * @return TODO 根据村级账号的id查询所有的企业
     */
    @ResponseBody
    @RequestMapping(value = "A220", method = RequestMethod.POST)
    public AppResult CompanyByVillageId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        // 根据村级id查询响应的企业
        List<Map<String, Object>> list = countryCheck.selectCompanyByVillageId(id);
        if (null == list) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }

        return result;
    }

    /**
     * @return TODO 根据镇级查询所有对应的村
     */
    @ResponseBody
    @RequestMapping(value = "A221", method = RequestMethod.POST)
    public AppResult villageBytownId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        // 根据村级id查询响应的企业
        List<Map<String, Object>> list = countryCheck.selectVillageBytownId(id);
        if (null == list) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }

        return result;
    }


/*    *//**
     * @return TODO 根据区级查询所有的镇
     *//*
    @ResponseBody
    @RequestMapping(value = "A222", method = RequestMethod.POST)
    public AppResult villageBytownId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        // 根据村级id查询响应的企业
        List<Map<String, Object>> list = countryCheck.selectVillageBytownId(id);
        if (null == list) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }

        return result;
    }*/

}
