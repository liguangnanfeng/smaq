package com.spring.web.controller.api;

import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通知消息controller
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
        // 当前页
        String pageNo = String.valueOf(params.get("page"));
        // 1.企业消息能看到   2.部门消息能看到   3.个人消息能看到
        //企业自查，传进来的是个人id，先通过个人id找到企业id，定位到企业,
//        ZzjgPersonnel person = appMessageService.findPersonnel(userId);
//        List<Messages> list = appMessageService.findMessageList(String.valueOf(person.getCid()),pageNo,"10");

        // 测试用
        List<Messages> list = appMessageService.findMessageList(userId,pageNo,"10");

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
        AppResult result = new AppResultImpl();
        result.setData(message);

        return result;
    }


    /**
     * 获取检查记录列表     String userId
     */
    @RequestMapping(value = "findCheckList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckList(@RequestBody Map<String, Object> params,HttpServletRequest request) {


//            User user = getLoginUser(request);
//            Map<String, Object> m = new HashMap<String, Object>();
//            if (user.getUserType() == 3) {//镇
//                model.addAttribute("villageL", villageMapper.selectListByTown(m));
//            }
//            if (user.getUserType() == 6) {//区
//                model.addAttribute("townL", townMapper.selectListByDistrict(m));
//            }
//            // 向map集合进行存储
//            m.put("type", type);  //
//            m.put("flag", flag);  // 1
//            m.put("title", title); // 1
//            m.put("townId", townId);   // null
//            m.put("villageId", villageId);  //1
//            m.put("companyName", companyName); // null
//            m.put("status", status); //状态  null
//            // 进行判断
//            if(setUserId(user, m)) {
//                clearVillageTown(m);
//                List<Map<String, Object>> list = tCheckMapper.selectList(m);
//                model.addAttribute("list", list);
//            }
//            model.addAttribute("type", type);
//            model.addAttribute("flag", flag);
//            model.addAttribute("companyName", companyName);
//            model.addAttribute("title", title);
//            model.addAttribute("status", status);
//            model.addAttribute("townId", townId);
//            model.addAttribute("villageId", villageId);
//            Date d = new Date();
//            String x = DateFormatUtils.format(d, "yyyy-MM-dd");
//            d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
//            model.addAttribute("t", d.getTime());
//            if (user.getUserType() == 5) {
//                // 表示等于5的话就将页面进行跳转
//                return "company/danger/check-list";
//            }
//            // TODO 找到这个界面
//            return "village/danger/check-list";


//        List<TCheck> list = appMessageService.findTCheckList("26");

        // 1.获取userId
        String userId  = String.valueOf(params.get("userId"));
        // 当前页
        String page = String.valueOf(params.get("page"));
        // 2. 查询此用户属于哪个部门，哪个企业id
        ZzjgPersonnel personnel = personnerService.findPersonnel(userId);
//
        System.out.println("11111111111111111111111111");
//        // 3. 展示此企业，此部门，的检查数据(check表有企业id,被检查部门名称(根据这两个条件查询))
        List<TCheck> list = appMessageService.findTCheckList(String.valueOf(personnel.getCid()),page,"10");
        AppResult result = new AppResultImpl();
        result.setData(list);

        return result;
    }


    /**
     * 获取检查记录详情  ,String checkId
     */
    @RequestMapping(value = "findCheckDetailList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckDetailList(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        String checkId  = String.valueOf(params.get("checkId"));
        List<Map<String, Object>> detailList = tCheckItemMapper.selectByCheckId(Integer.valueOf(checkId));
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
    AppResult findRectificationList(HttpServletRequest request) {

//        String checkId  = String.valueOf(params.get("checkId"));
        String checkId = "37437";
        Map<String,Object> map = new HashMap<>();
        // 检查时间
        TRectification tRectification = appMessageService.findTRectification(checkId);
        map.put("rectification",tRectification);
//        // 检查部门
        TCheck check = appMessageService.findCheckById(checkId);
        map.put("depart",check.getDepart());

        // 检查部位  t_check_part_tbl表的name字段   TCheckItem新添加name字段
//        List<TCheckPart> checkParts = tCheckPartMapper.selectByCheckId(Integer.valueOf(checkId));
//        map.put("checkParts",checkParts);

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

        Object obj = params.get("TRectificationConfirm");

        TRectificationConfirm confirm = new TRectificationConfirm();

        // 组装数据
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        confirm.setCreateTime(new Date());

        tRectificationConfirmService.saveTRectificationConfirm(confirm);

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

        List<TRectificationConfirm> confirmList = tRectificationConfirmService.findTRectificationConfirm(userId);

        AppResult result = new AppResultImpl();
        result.setData(confirmList);
        return result;

    }




    /**
     * 复查意见     ,String checkId
     */
    @RequestMapping(value = "findReCheckList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findReCheckList(@RequestBody Map<String, Object> params,HttpServletRequest request) {

        String checkId  = String.valueOf(params.get("checkId"));
        Integer id = Integer.valueOf(checkId);
        LinkedHashMap<String,Object> listMap = new LinkedHashMap<>();
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
//        listMap.put("check", check);
//        listMap.put("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
//        listMap.put("recheckList", tRecheckMapper.selectByCheckId(id));
        listMap.put("itemList", tRecheckItemMapper.selectByCheckId(id));

        AppResult result = new AppResultImpl();
        result.setData(listMap);

        return result;

    }


}
