package com.spring.web.controller.api;

import com.spring.web.BaseController;
import com.spring.web.model.Messages;
import com.spring.web.model.TLevel;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.AppMessage.AppMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 通知消息controller
 */

@Controller
@RequestMapping(value = "api/message")
public class AppController_message extends BaseController {

    @Autowired
    private AppMessageService appMessageService;

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "findMessageList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findMessageList(HttpServletRequest request,String userId) {

//        Integer page = 1;
//        Integer limit = 10;
        List<Messages> list = appMessageService.findMessageList(userId);

        AppResult result = new AppResultImpl();
        result.setData(list);

        return result;
    }



    /**
     * 获取检查记录列表
     */
    @RequestMapping(value = "findMessageList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckList(HttpServletRequest request,String userId) {


        // 1.获取userId
        // 2. 查询此用户属于哪个部门，哪个企业id
        // 3. 展示此企业，此部门，的检查数据(check表有企业id,被检查部门id(根据这两个条件查询))

        return null;
    }



}
