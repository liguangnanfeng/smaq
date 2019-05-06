package com.spring.web.controller.api;


import com.spring.web.listener.MySessionContext;
import com.spring.web.model.TCheckItem;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.request.*;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;

import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.CheckCompany.SaveMessageService;
import com.spring.web.service.CheckCompany.Zzig_departmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 桃红梨白
 * TODO 发起检查功能
 * 状态码类型: 0 成功  1 失败
 * 保留方法:
 * 查询员工信息 private Zzjg_PersonnelService zzjg_personnelService;
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api/custom/check")
public class AppController_Custom_Check {

    /**
     * 查询部门
     */
    @Autowired
    private Zzig_departmentService zzig_departmentService;

    /**
     * 查询风险点
     */
    @Autowired
    private ICheckManual checkManual;

    /**
     * 检查以及复查信息
     */
    @Autowired
    private SaveMessageService saveMessageService;

    /**
     * 根据sessionId查询对应的session,并通过token获取响应的对象
     *
     * @param request
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A200", method = RequestMethod.POST)
    public AppResult checkCompany(HttpServletRequest request, String sessionId, String token) {

        AppResult result = new AppResultImpl();

        if (token == null || sessionId == null) {
            result.setStatus("1");
            result.setMessage("查询失败");
            return result;
        }

        // 从session中获取
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession sess = myc.getSession(sessionId);
        if (null == sess) {
            result.setStatus("1");
            result.setMessage("登陆时间过长");
            return result;
        }

        ZzjgPersonnel zzjg = (ZzjgPersonnel) sess.getAttribute(token);

        if (null == zzjg || !"1".equals(zzjg.getStatus())) {
            result.setStatus("1");
            result.setMessage("还未登陆,请重新登陆");
            return result;
        }

        // 使用总公司 获取这家公司对应的所有的部门
        List<ZzjgDepartment> list = zzig_departmentService.selectDepartmentByCid(zzjg.getCid());

        // 对数据进行判断是否存在
        if (null == list || list.size() == 0) {
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        }

        // 获取所有的部门 使用list集合
        List<String> names = new ArrayList<>();

        for (ZzjgDepartment zzjgDepartment : list) {
            names.add(zzjgDepartment.getName());
        }

        // 根据公司id 和部门获取所有的岗位并进行数据你对比添加
        Map<String, List> stringListMap = checkManual.selectDangerAndManual(zzjg.getUid(), names);

        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(stringListMap);

        return result;
    }

    /**
     * 查询所有的安全责任人 对象数据
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A201", method = RequestMethod.POST)
    public AppResult checkLevel2(HttpServletRequest request, String sessionId, String token) {

        AppResult result = new AppResultImpl();
        if (sessionId == null || token == null) {
            result.setMessage("未成功请求,请重新选择");
            result.setStatus("1");
            return result;
        }

        // 获取session集合中的域对象
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession sess = myc.getSession(sessionId);
        if (sess == null) {
            result.setMessage("未登陆");
            result.setStatus("1");
            return result;
        }

        // 获取域中的用户信息
        ZzjgPersonnel zzjg = (ZzjgPersonnel) sess.getAttribute(token);
        if (zzjg == null) {
            result.setMessage("未登陆");
            result.setStatus("1");
            return result;
        }

        // 调用service层数据返回安全责任人
        List<Map<Integer, String>> list = checkManual.findUserByIdAndStatus(zzjg);

        if (list == null) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }
        result.setData(list);

        return result;
    }


    /**
     * 根据部门岗位查询风险点  直接查询 并不需要session id和 token
     *
     * @param request
     * @param checkLevel
     * @return AppResult
     * 保留方法:
     * @RequestParam(value="checkLevel", required=true)
     * headers = {"Content-type: application/json"}
     */
    @ResponseBody
    @RequestMapping(value = "A202", method = RequestMethod.POST/*,
            headers = {"Content-type: application/json"}*/)
    public AppResult checkLevel3(HttpServletRequest request, /*@RequestBody*/ CheckLevel checkLevel) {

        AppResult result = new AppResultImpl();
        if (checkLevel == null) {
            result.setStatus("3");
            result.setMessage("请进行选择部门及其岗位");
            return result;
        }

        // 调用方法进行查询
        List<CheckLevel> list = checkManual.selectLevel4AndId(checkLevel);

        if (null == list || list.size() == 0) {
            result.setStatus("1");
            result.setMessage("未查询到数据");
            return result;
        }

        result.setStatus("0");
        result.setMessage("查询成功");

        //result.setData(list);
        Set<String> set = new HashSet<>();
        for (CheckLevel level : list) {

            set.add(level.level3);
        }
        result.setData(set);
        return result;
    }

    /**
     * 查询level4
     *
     * @param request
     * @param checkLevel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A203", method = RequestMethod.POST)
    public AppResult checkLevel4(HttpServletRequest request, /*@RequestBody*/ CheckLevel checkLevel) {
        AppResult result = new AppResultImpl();
        if (checkLevel == null) {
            result.setStatus("1");
            result.setMessage("请进行选择风险点");
            return result;
        }

        // 调用方法进行查询
        List<CheckLevel> list = checkManual.selectLevel5AndId(checkLevel);
        if (null == list || list.size() == 0) {
            result.setStatus("1");
            result.setMessage("未查询到数据");
            return result;
        }

        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);
        return result;
    }

    /**
     * 传选择参数,然后进行保存,
     * 先保存模版, 然后保存其他的数据
     * 检查类型 => 日常 周  还是 其他的
     * 会传递一个数据结构就是传递的就是一个list集合的形式,和这个token 从这个
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A204", method = RequestMethod.POST)
    public AppResult saveCheck(HttpServletRequest request, @RequestBody CheckItem checkItem) {
        AppResult result = new AppResultImpl();

        if (null == checkItem) {
            result.setStatus("1");
            result.setMessage("数据格式错误,请从新输入");
            return result;
        }

        MySessionContext myc = MySessionContext.getInstance();
        HttpSession sess = myc.getSession(checkItem.getSessionId());
        if (null == sess) {
            result.setStatus("1");
            result.setMessage("登陆时间过长");
            return result;
        }

        ZzjgPersonnel zzjg = (ZzjgPersonnel) sess.getAttribute(checkItem.getToken());
        if (null == zzjg || !"1".equals(zzjg.getStatus())) {
            result.setStatus("1");
            result.setMessage("还未登陆,请重新登陆");
            return result;
        }

        Integer checkId = checkManual.saveCheck(checkItem, zzjg);
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(checkId);
        System.out.println(checkId);
        return result;

    }

    /**
     * TODO 根据用户点击查询模版
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A205", method = RequestMethod.POST)
    public AppResult checkDepartmentById(HttpServletRequest request, String sessionId, String token) {
        AppResult result = new AppResultImpl();

        if (sessionId == null || token == null) {
            result.setMessage("未成功请求,请重新选择");
            result.setStatus("1");
            return result;
        }

        // 获取session集合中的域对象
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession sess = myc.getSession(sessionId);
        if (sess == null) {
            result.setMessage("未登陆");
            result.setStatus("1");
            return result;
        }

        // 获取域中的用户信息
        ZzjgPersonnel zzjg = (ZzjgPersonnel) sess.getAttribute(token);
        if (zzjg == null) {
            result.setMessage("未登陆");
            result.setStatus("1");
            return result;
        }

        Integer uid = zzjg.getUid(); // 获取总公司id
        // 根据公司id获取模版信息
        List<Map<Integer, String>> list = checkManual.findModelByUid(uid);
        if (list == null || list.size() == 0) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;

        }
        result.setMessage("查询成功");
        result.setData(list);
        result.setStatus("0"); // 状态码成功 0 失败 1

        return result;

    }

    /**
     * 根据模版id查询详细信息
     *
     * @param request
     * @param modelId
     * @param sessionId
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A206", method = RequestMethod.POST)
    public AppResult checkItemtById(HttpServletRequest request,/*Integer modelId */@RequestBody CheckModel checkModel) {
        AppResult result = new AppResultImpl();
        // 判断是否为空
        if (checkModel.getModelId() == null || checkModel.getSessionId() == null || checkModel.getToken() == null) {
            result.setMessage("查询失败");
            result.setStatus("1");
        }

        // 获取session集合中的域对象
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession sess = myc.getSession(checkModel.getSessionId());
        if (sess == null) {
            result.setMessage("未登陆");
            result.setStatus("1");
            return result;
        }

        // 获取域中的用户信息
        ZzjgPersonnel zzjg = (ZzjgPersonnel) sess.getAttribute(checkModel.getToken());
        if (zzjg == null) {
            result.setMessage("未登陆");
            result.setStatus("1");
            return result;
        }

        // 判断完成, 根据id查询并进行封装数据

        CheckItemS checkItemByModelId = checkManual.findCheckItemByModelId(checkModel.getModelId());
        //CheckItemS checkItemByModelId = checkManual.findCheckItemByModelId(modelId);
        if (checkItemByModelId == null) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(checkItemByModelId);
        return result;

    }

    /**
     * TODO 根据安全责任人的id 查询相关的部门和岗位
     *
     * @param request
     * @param sessionId
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A207", method = RequestMethod.POST)
    public AppResult checkByStatus(HttpServletRequest request, String sessionId, String token, Integer personnelId) {
        AppResult result = new AppResultImpl();

        if (sessionId == null || token == null || personnelId == null) {
            result.setStatus("1");
            result.setMessage("查询失败,请重新查询");
            return result;
        }
        MySessionContext sess = MySessionContext.getInstance();
        HttpSession session = sess.getSession(sessionId);
        ZzjgPersonnel zzjg = (ZzjgPersonnel) session.getAttribute(token); // 获取session域中的信息


        if (zzjg == null) {
            result.setStatus("1");
            result.setMessage("登陆失败,请重新登陆");
            return result;

        }

        // 直接 进行查询获取部门的id 和公司的id  根据部门id查询部门名称 ,然后在根据部门名称查询 所对应的风险点
        Map<String, List> map = checkManual.findLevel2ByPersonnelId(personnelId, zzjg.getUid());

        if (map == null) {
            result.setStatus("1");
            result.setMessage("没有数据,请输入正确数据");
            return result;

        }

        result.setStatus("2");
        result.setMessage("查询成功");
        result.setData(map);
        return result;

    }


    /**
     * TODO 根据前端传递的合格不合格信息进行数据的存储
     *
     * @param request
     * @param sessionId
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A208", method = RequestMethod.POST)
    public AppResult saveMessage(HttpServletRequest request, @RequestBody SaveDataMessageItem saveDataMessageItem) {
        AppResult result = new AppResultImpl();

        if (saveDataMessageItem == null) {
            result.setStatus("1");
            result.setMessage("登陆失败,请重新登陆");
            return result;
        }

        //到域中获取数据

        MySessionContext sess = MySessionContext.getInstance();
        HttpSession session = sess.getSession(saveDataMessageItem.getSessionId());
        ZzjgPersonnel zzjg = (ZzjgPersonnel) session.getAttribute(saveDataMessageItem.getToken()); // 获取session域中的信息

        if (zzjg == null) {
            result.setStatus("1");
            result.setMessage("登陆失败,请重新登陆");
            return result;

        }

        String s = saveMessageService.saveCheckMessage(saveDataMessageItem, zzjg);
        if (s == null) {
            result.setStatus("1");
            result.setMessage("检查失败,请从新发起检查");
            return result;
        }

        result.setStatus("0");
        result.setMessage("检查成功,以向责任人发送消息");

        return result;
    }


    /**
     * 复查
     * <p>
     * 根据checkid 去 item查询不合格项返回
     */
    @ResponseBody
    @RequestMapping(value = "A209", method = RequestMethod.POST)
    public AppResult findCheckItem(@RequestBody CheckModel checkModel) {
        AppResult result = new AppResultImpl();
        if (checkModel.getSessionId() == null || checkModel.getToken() == null) {
            result.setStatus("1");
            result.setMessage("查询失败");
            return result;
        }

        //到域中获取数据
        MySessionContext sess = MySessionContext.getInstance();
        HttpSession session = sess.getSession(checkModel.getSessionId());
        ZzjgPersonnel zzjg = (ZzjgPersonnel) session.getAttribute(checkModel.getToken()); // 获取session域中的信息

        if (zzjg == null) {
            result.setStatus("1");
            result.setMessage("登陆失败,请重新登陆");
            return result;

        }
        // 根据当前用户的id进行查询
        List<Map> list = saveMessageService.findCheckItemById(zzjg);
        if (list == null || list.size() == 0) {
            result.setStatus("1");
            result.setMessage("没有数据");
            return result;
        }
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);

        return result;
    }


    /**
     * 复查
     * <p>
     * 根据checkid 去 item查询不合格项返回
     */
    @ResponseBody
    @RequestMapping(value = "A210", method = RequestMethod.POST)
    public AppResult reviewData( /*@RequestBody  Map<String, Object> params*/ String checkId) {
       /*  System.out.println(params);
         String checkId = (String) params.get("checkId");*/
        int i = Integer.parseInt(checkId);

        AppResult result = new AppResultImpl();
        if (checkId == null) {
            result.setStatus("1");
            result.setMessage("查询失败,请重新发起检查");
            return result;
        }
        List<TCheckItem> list = saveMessageService.findItemByCheckId(i);
        if (list == null) {
            result.setStatus("1");
            result.setMessage("查询失败,请重新发起检查");
            return result;
        }

        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);

        return result;

    }

    /**
     * 存储复查数据, 只要有一条数据不合格,复查表就存储不合格
     * t_recheck_tbl
     * t_recheck_item_tbl
     *
     * @param checkId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A211", method = RequestMethod.POST)
    public AppResult saveReviewData(@RequestBody SaveDataMessageItem saveDataMessageItem) {
        AppResult result = new AppResultImpl();

        if (saveDataMessageItem == null) {
            result.setStatus("1");
            result.setMessage("登陆失败,请重新登陆");
            return result;
        }

        //到域中获取数据
        MySessionContext sess = MySessionContext.getInstance();
        HttpSession session = sess.getSession(saveDataMessageItem.getSessionId());
        ZzjgPersonnel zzjg = (ZzjgPersonnel) session.getAttribute(saveDataMessageItem.getToken()); // 获取session域中的信息

        if (zzjg == null) {
            result.setStatus("1");
            result.setMessage("登陆失败,请重新登陆");
            return result;

        }
        String message = saveMessageService.saveReviewData(saveDataMessageItem, zzjg);
        if (message == null) {
            result.setStatus("2");
            result.setMessage("保存失败");
            return result;
        }
        result.setStatus("1");
        result.setMessage("保存成功");
        return result;
    }


}






