package com.spring.web.controller.api;

import com.spring.web.BaseController;
import com.spring.web.listener.MySessionContext;
import com.spring.web.model.Messages;
import com.spring.web.model.TCheck;
import com.spring.web.model.TRectificationConfirm;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.AppMessage.AppMessageService;
import com.spring.web.service.Personnel.PersonnerService;
import com.spring.web.service.TRectiflcationConfirm.TRectificationConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * TODO 企业自查controller
 */
@Controller
@RequestMapping(value = "api/message")
@SuppressWarnings("All")
public class AppController_message extends BaseController {

    /*消息服务*/
    @Autowired
    private AppMessageService appMessageService;
    /*企业人员服务*/
    @Autowired
    private PersonnerService personnerService;
    /*消息通知服务*/
    @Autowired
    private TRectificationConfirmService tRectificationConfirmService;

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "findMessageList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findMessageList(@RequestBody Map<String, Object> params, HttpServletRequest request) {

        String userId = String.valueOf(params.get("userId"));
        // 当前页shi
        Integer pageNo = (Integer.valueOf(String.valueOf(params.get("page"))) - 1) * 10;
        // 1.企业消息能看到   2.部门消息能看到   3.个人消息能看到
        //企业自查，传进来的是个人id，先通过个人id找到企业id，定位到企业,
//        ZzjgPersonnel person = appMessageService.findPersonnel(userId);
//        List<Messages> list = appMessageService.findMessageList(String.valueOf(person.getCid()),pageNo,"10");
        System.out.println("获取通知列表==============");
        // 测试用
        List<Messages> list = appMessageService.findMessageList(userId, pageNo, 10);

        AppResult result = new AppResultImpl();

        if (null == list) {
            result.setStatus("0");
            result.setMessage("没有数据");
            result.setData(new ArrayList<>());
        }

        result.setData(list);


        return result;
    }


    /**
     * 获取通知详情
     */
    @RequestMapping(value = "findMessageDetail", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findMessageDetail(@RequestBody Map<String, Object> params, HttpServletRequest request) {

        String id = String.valueOf(params.get("id"));
        Messages message = appMessageService.findMessageDetail(id);
        System.out.println("获取通知详情==============");
        AppResult result = new AppResultImpl();
        result.setData(message);

        return result;
    }

    /**
     * TODO 获取已检查记录列表
     * 检查人员是全部,被检查人员是只有自己车间的
     * 所有人都能看到 所有人都能看到
     */
    @RequestMapping(value = "findCheckList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckList(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession sess = myc.getSession((String) params.get("sessionId"));
        if (null == sess) {
            result.setStatus("1");
            result.setMessage("登陆时间过长");
            return result;
        }

        // 1.获取userId
        String userId = String.valueOf(params.get("userId"));  //用户id
        Integer uid = (Integer) params.get("uid");             // 公司id
        String status = (String) params.get("status");         // 状态

        // 当前页
        Integer pageNo = (Integer.valueOf(String.valueOf(params.get("page"))) - 1) * 10;
        // 2. 查询此用户属于哪个部门，哪个企业id
//        ZzjgPersonnel personnel = personnerService.findPersonnel(userId);
        System.out.println("获取检查记录列表==============");
        // 检查人员和被检查人员
        List<Map> list = null;
        if ("1".equals(status)) {
            //检查人员
            list = appMessageService.findTCheckList(uid, pageNo, 10);
        } else {
            //被检查人员
            list = appMessageService.findTCheckListByStatusAndBJC(uid, Integer.parseInt(userId), pageNo, 10);
        }

        result.setData(list);

        return result;
    }

    /**
     * 获取检查不合格记录列表     只有当前检查人和当前责任人能看到 不合格
     * 检查人员能看到所有不合格信息
     * 但是被检查任何员只能看到自己部门
     * 用户id
     * TODO 被检查人       只有自定义检查和部门进行修改时
     * 判断是企业端检查人员  由没有相应的权力，在进行保存的时候
     */
    @RequestMapping(value = "findCheckListBystatus", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckListByStatus(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        // 1.获取userId
        String userId = String.valueOf(params.get("userId"));
        // 当前页
        Integer pageNo = (Integer.valueOf(String.valueOf(params.get("page"))) - 1) * 10;
        // 2. 查询此用户属于哪个部门，哪个企业id
        ZzjgPersonnel zzjg = personnerService.findPersonnel(userId);

        List<Map> list = null;

        // 对用户进行判断,如果是检查人员能看到所有的不合格信息
        if ("1".equals(zzjg.getStatus())) {
            System.out.println("检查人员获取不合格检查记录列表==============");
            list = appMessageService.findTCheckListByStatus(userId, pageNo, 10);
        } else if ("2".equals(zzjg.getStatus())) {
            // 被检查人员只能获取它对应的部门检查和合格项
            String bm = zzjgDepartmentMapper.selectByPrimaryKey(zzjg.getDpid()).getName();  //部门
            String name = zzjgDepartmentMapper.selectByPrimaryKey(zzjg.getDid()).getName(); //岗位
            System.out.println("被检查人员获取不合格检查记录列表==============");
            list = appMessageService.findTCheckItemByBJC(zzjg.getUid(), bm, name, pageNo, 10);

        }
        result.setData(list);

        return result;
    }

    /**
     * 获取检查记录详情  ,String checkId
     * 获取检查记录详情
     */
    @RequestMapping(value = "findCheckDetailList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckDetailList(@RequestBody Map<String, Object> params, HttpServletRequest request) {

        String checkId = String.valueOf(params.get("checkId"));
        List<Map<String, Object>> detailList = tCheckItemMapper.selectByCheckId(Integer.valueOf(checkId));
        System.out.println("获取检查记录详情==============");
        AppResult result = new AppResultImpl();
        result.setData(detailList);
        return result;
    }

    /**
     * 整改意见列表
     * 1.获取checkId 查找t_check_item表，获取当前检查记录的整改意见列表     ,String checkId
     * 整改意见表就是在整改过程中
     *
     * @RequestBody Map<String, Object> params,
     */
    @RequestMapping(value = "findRectificationList", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findRectificationList(@RequestBody Map<String, Object> params, HttpServletRequest request) {

        String checkId = String.valueOf(params.get("checkId"));
//        String checkId = "37437";
        Map<String, Object> map = new HashMap<>();
        // 检查时间
//        TRectification tRectification = appMessageService.findTRectification(checkId);
//        map.put("rectification",tRectification);
//        // 检查部门
        TCheck check = appMessageService.findCheckById(checkId);
        map.put("depart", check.getDepart());

        // 检查部位  t_check_part_tbl表的name字段   TCheckItem新添加name字段
//        List<TCheckPart> checkParts = tCheckPartMapper.selectByCheckId(Integer.valueOf(checkId));
//        map.put("checkParts",checkParts);

        System.out.println("整改意见列表==============");
        // 意见详情
        List<Map> list = appMessageService.findRectificationList(checkId);
        map.put("details", list);
        AppResult result = new AppResultImpl();
        result.setData(map);
        return result;
    }

    /**
     * 整改意见回复(存储)       , String checkId
     */
    @RequestMapping(value = "saveTRectificationConfirm", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveTRectificationConfirm(@RequestBody Map<String, Object> params, HttpServletRequest request) {

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

        if (null == confirmSet) {
            tRectificationConfirmService.saveTRectificationConfirm(confirm);
        } else {
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
    AppResult findTRectificationConfirm(@RequestBody Map<String, Object> params, HttpServletRequest request) {

        String userId = String.valueOf(params.get("userId"));
        String checkId = String.valueOf(params.get("checkId"));

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
    AppResult findTRectificationConfirmByZF(@RequestBody Map<String, Object> params, HttpServletRequest request) {

        String userId = String.valueOf(params.get("userId"));
        String checkId = String.valueOf(params.get("checkId"));

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
    AppResult findReCheckList(@RequestBody Map<String, Object> params, HttpServletRequest request) {

        String checkId = String.valueOf(params.get("checkId"));
        AppResult result = new AppResultImpl();
        if (null == checkId) {
            result.setMessage("检查表id不能为空");
            return result;
        }

        Integer id = Integer.valueOf(checkId);
        LinkedHashMap<String, Object> listMap = new LinkedHashMap<>();
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
     * TODO 复查列表记录 (主界面进入)
     * 合格不合格都要显示
     * 政府端和企业端
     * 分为检查人员和被检查人员
     * 分为企业端和政府端
     * status 政府
     * <p>
     * 政府端 根据部门和存储的检查人员的id获取复查记录
     * 企业端 检查人员和存储的企业id
     * 被检查人员 的部门id和企业的id进行查询,
     */
    @RequestMapping(value = "findReCheckList2", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findReCheckList2(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        String userId = String.valueOf(params.get("userId"));    // 用户id
        String uid = String.valueOf(params.get("uid"));          // 企业id
        String status = String.valueOf(params.get("status"));          // 企业/政府

        List<Map<String, Object>> maps = null;

        if ("1".equals(status)) {   //企业端
            // 根据检查人员和被检查人员进行查询
            ZzjgPersonnel zzjg = zzjgPersonnelMapper.selectByPrimaryKey(Integer.parseInt(userId));
            if ("1".equals(zzjg.getStatus())) {
                // 检查人员
                maps = appMessageService.selectRecheckByCheckId2JCR(userId, uid);

            } else {
                // 被检查人员查询复查记录
                maps = appMessageService.selectRecheckByCheckId2BJCR(userId, uid);
            }

        } else { //政府端
            maps = appMessageService.selectRecheckByCheckId2ZF(userId, uid);

        }

        LinkedHashMap<String, Object> listMap = new LinkedHashMap<>();
        listMap.put("itemList", maps);
        if(null == maps){
            result.setStatus("0");
            result.setMessage("没有数据");
            result.setData(new ArrayList<>());
            return result;
        }

        result.setData(listMap);
        return result;
    }

}
