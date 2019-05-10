package com.spring.web.controller.api;


import com.spring.web.BaseController;
import com.spring.web.model.User;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.AppMessage.AppMessageService;
import com.spring.web.service.Personnel.PersonnerService;
import com.spring.web.service.TRectiflcationConfirm.TRectificationConfirmService;
import com.spring.web.util.DateConvertUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 政府检查controller
 */

@Controller
@RequestMapping(value = "api/message/government")
public class AppController_message_government extends BaseController {

    @Autowired
    private AppMessageService appMessageService;

    @Autowired
    private PersonnerService personnerService;

    @Autowired
    private TRectificationConfirmService tRectificationConfirmService;



    /**
     *  政府端检查表
     * user. userType : 管理类型  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     *
     *
     */
    @RequestMapping(value = "check-list")
    @ResponseBody
    public AppResult findCheckList(HttpServletRequest request, String title, Integer type, String companyName,
                                   Integer townId, Integer villageId,
                                   Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);
//        Map<String, Object> m = new HashMap<String, Object>();
//        if (user.getUserType() == 3) {//镇
//            model.addAttribute("villageL", villageMapper.selectListByTown(m));
//        }
//        if (user.getUserType() == 6) {//区
//            model.addAttribute("townL", townMapper.selectListByDistrict(m));
//        }

        Map<String,Object> map = new HashMap<>();
        List<Map> list =  appMessageService.selectCheckByFlag();
        map.put("list",list);
        AppResult result = new AppResultImpl();
        result.setData(map);
        return result;
    }




}
