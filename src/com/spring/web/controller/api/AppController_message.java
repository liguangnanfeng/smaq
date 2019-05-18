package com.spring.web.controller.api;

import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.listener.MySessionContext;
import com.spring.web.model.*;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.AppMessage.AppMessageService;
import com.spring.web.service.Personnel.PersonnerService;
import com.spring.web.service.TRectiflcationConfirm.TRectificationConfirmService;
import com.spring.web.util.DateConvertUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 企业自查controller
 */

@Controller
@RequestMapping(value = "api/message")
public class AppController_message extends BaseController {

    @Autowired
    private AppMessageService appMessageService;

    @Autowired
    private PersonnerService personnerService;

    @Autowired
    private TRectificationConfirmService tRectificationConfirmService;

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "findMessageList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findMessageList(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        String userId  = String.valueOf(params.get("userId"));
        // 当前页shi
        Integer pageNo = (Integer.valueOf(String.valueOf(params.get("page")))-1)*10;
        // 1.企业消息能看到   2.部门消息能看到   3.个人消息能看到
        //企业自查，传进来的是个人id，先通过个人id找到企业id，定位到企业,
//        ZzjgPersonnel person = appMessageService.findPersonnel(userId);
//        List<Messages> list = appMessageService.findMessageList(String.valueOf(person.getCid()),pageNo,"10");
        System.out.println("获取通知列表==============");
        // 测试用
        List<Messages> list = appMessageService.findMessageList(userId,pageNo,10);

        AppResult result = new AppResultImpl();
        result.setData(list);

        return result;
    }


    /**
     * 获取通知详情
     */
    @RequestMapping(value = "findMessageDetail", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findMessageDetail(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        String id  = String.valueOf(params.get("id"));
        Messages message = appMessageService.findMessageDetail(id);
        System.out.println("获取通知详情==============");
        AppResult result = new AppResultImpl();
        result.setData(message);

        return result;
    }

    /**
     * TODO 获取已检查记录列表     只有当前检查人和当前责任人能看到 对应的检查记录
     */
    @RequestMapping(value = "findCheckList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckList(@RequestBody Map<String, Object> params,HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession sess = myc.getSession((String)params.get("sessionId"));
        if (null == sess) {
            result.setStatus("1");
            result.setMessage("登陆时间过长");
            return result;
        }

        ZzjgPersonnel zzjg = (ZzjgPersonnel) sess.getAttribute((String)params.get("access_token"));

        // 1.获取userId
        String userId  = String.valueOf(params.get("userId"));
        // 当前页
        Integer pageNo = (Integer.valueOf(String.valueOf(params.get("page")))-1)*10;
        // 2. 查询此用户属于哪个部门，哪个企业id
//        ZzjgPersonnel personnel = personnerService.findPersonnel(userId);
        System.out.println("获取检查记录列表==============");
        // 检查人员和被检查人员
        List<Map> list = appMessageService.findTCheckList(userId,pageNo,10);
        result.setData(list);

        return result;
    }

    /**
     * 获取检查记录列表     只有当前检查人和当前责任人能看到 不合格
     */
    @RequestMapping(value = "findCheckListBystatus", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckListByStatus(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        // 1.获取userId
        String userId  = String.valueOf(params.get("userId"));
        // 当前页
        Integer pageNo = (Integer.valueOf(String.valueOf(params.get("page")))-1)*10;
        // 2. 查询此用户属于哪个部门，哪个企业id
//        ZzjgPersonnel personnel = personnerService.findPersonnel(userId);
//
        System.out.println("获取检查记录列表==============");
        List<Map> list = appMessageService.findTCheckListByStatus(userId,pageNo,10);
        AppResult result = new AppResultImpl();
        result.setData(list);

        return result;
    }

    /**
     * 获取检查记录详情  ,String checkId
     * 获取检查记录详情
     *
     */
    @RequestMapping(value = "findCheckDetailList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckDetailList(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        String checkId  = String.valueOf(params.get("checkId"));
        List<Map<String, Object>> detailList = tCheckItemMapper.selectByCheckId(Integer.valueOf(checkId));
        System.out.println("获取检查记录详情==============");
        AppResult result = new AppResultImpl();
        result.setData(detailList);
        return result;
    }



    /**
     * 整改意见列表
     *  1.获取checkId 查找t_check_item表，获取当前检查记录的整改意见列表     ,String checkId
     *
     *      @RequestBody Map<String, Object> params,
     */
    @RequestMapping(value = "findRectificationList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findRectificationList(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        String checkId  = String.valueOf(params.get("checkId"));
//        String checkId = "37437";
        Map<String,Object> map = new HashMap<>();
        // 检查时间
//        TRectification tRectification = appMessageService.findTRectification(checkId);
//        map.put("rectification",tRectification);
//        // 检查部门
        TCheck check = appMessageService.findCheckById(checkId);
        map.put("depart",check.getDepart());

        // 检查部位  t_check_part_tbl表的name字段   TCheckItem新添加name字段
//        List<TCheckPart> checkParts = tCheckPartMapper.selectByCheckId(Integer.valueOf(checkId));
//        map.put("checkParts",checkParts);

        System.out.println("整改意见列表==============");
        // 意见详情
        List<Map> list = appMessageService.findRectificationList(checkId);
        map.put("details",list);
        AppResult result = new AppResultImpl();
        result.setData(map);
        return result;
    }

    /**
     * 整改意见回复(存储)       , String checkId
     */
    @RequestMapping(value = "saveTRectificationConfirm", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveTRectificationConfirm(@RequestBody Map<String, Object> params, HttpServletRequest request){

        String confirmText = String.valueOf(params.get("confirmText"));
        String checkId = String.valueOf(params.get("checkId"));
        String checkItemId = String.valueOf(params.get("checkItemId"));
        String name = String.valueOf(params.get("name"));
        String userId = String.valueOf(params.get("userId"));
        String address = String.valueOf(params.get("address"));

        TRectificationConfirm confirmSet = tRectificationConfirmService.findTRectificationConfirmByItemId(checkItemId);

        TRectificationConfirm confirm = new TRectificationConfirm();
        TCheck check = tCheckMapper.selectByPrimaryKey(Integer.valueOf(checkId));
        // 组装数据  数据要求： checkId, 被检查人Id，检查人id（具体回复给谁），正文，时间，查看状态
        System.out.println("整改意见回复(存储) ==============");
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        confirm.setChecker(check.getCreateUser());
        confirm.setCheckId(Integer.valueOf(checkId));
        confirm.setConfirm(confirmText);
        confirm.setCreateTime(new Date());
        confirm.setSender(name);
        confirm.setStatus(0);
        confirm.setSenderId(Integer.valueOf(userId));
        confirm.setCheckItemId(Integer.valueOf(checkItemId));
        confirm.setPhoto(address);

        if(null == confirmSet){
            tRectificationConfirmService.saveTRectificationConfirm(confirm);
        }else{
            tRectificationConfirmService.updateTRectificationConfirm(confirm);
        }

        AppResult result = new AppResultImpl();
        return result;

    }

    /**
     * 整改意见回复(检查人读取)        , String checkId
     */
    @RequestMapping(value = "findTRectificationConfirm", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findTRectificationConfirm(@RequestBody Map<String, Object> params, HttpServletRequest request){

        String userId  = String.valueOf(params.get("userId"));
        String checkId  = String.valueOf(params.get("checkId"));

        List<Map> confirmList = tRectificationConfirmService.findTRectificationConfirm(userId);

        AppResult result = new AppResultImpl();
        result.setData(confirmList);
        return result;

    }

    /**
     * 整改意见回复(政府端)
     */
    @RequestMapping(value = "findTRectificationConfirmByZF", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findTRectificationConfirmByZF(@RequestBody Map<String, Object> params, HttpServletRequest request){

        String userId  = String.valueOf(params.get("userId"));
        String checkId  = String.valueOf(params.get("checkId"));

        List<Map> confirmList = tRectificationConfirmService.findTRectificationConfirm(userId);

        AppResult result = new AppResultImpl();
        result.setData(confirmList);
        return result;

    }

    /**
     * 复查    ,String checkId   （检查表里进入）
     */
    @RequestMapping(value = "findReCheckList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findReCheckList(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        String checkId  = String.valueOf(params.get("checkId"));
        AppResult result = new AppResultImpl();
        if(null == checkId){
            result.setMessage("检查表id不能为空");
            return result;
        }
        Integer id = Integer.valueOf(checkId);
        LinkedHashMap<String,Object> listMap = new LinkedHashMap<>();
//        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
//        listMap.put("check", check);
//        listMap.put("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
//        listMap.put("recheckList", tRecheckMapper.selectByCheckId(id));
//        listMap.put("itemList", tRecheckItemMapper.selectByCheckId(id));
        listMap.put("itemList", appMessageService.selectRecheckByCheckId(id));
        System.out.println("复查 ==============");
        result.setData(listMap);
        return result;

    }

    /**
     * 复查列表 (主界面进入)
     */
    @RequestMapping(value = "findReCheckList2", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findReCheckList2(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        String userId = String.valueOf(params.get("userId"));
        LinkedHashMap<String,Object> listMap = new LinkedHashMap<>();
        listMap.put("itemList", appMessageService.selectRecheckByCheckId2(Integer.valueOf(userId)));
        AppResult result = new AppResultImpl();
        result.setData(listMap);
        return result;
    }

}
