package com.spring.web.controller.api;

import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 19:26
 * 行政检查,
 * 编号从220开始
 * 首先要判断政府人员的权限
 * 获取所在的地区和地区所有的企业,获取企业的风险点
 *
 * 根据行业,获取该行业所有的风险点
 *
 */
@Controller
@RequestMapping(value = "api/country/check")
public class AppController_Country_Check {

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping(value="A220", method= RequestMethod.POST)
    public AppResult findTemplate(){
        AppResult result =new AppResultImpl();

       return  result ;

    }



}
