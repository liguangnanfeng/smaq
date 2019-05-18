package com.spring.web.controller.api;


import com.spring.web.BaseController;
import com.spring.web.dao.TCheckMapper;
import com.spring.web.listener.MySessionContext;
import com.spring.web.model.*;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author 桃红梨白
 * TODO 小程序 企业端
 */
@Controller
@RequestMapping(value = "api/custom/check")
public class AppController_Custom_Check extends BaseController {

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
     * token验证
     */
    @Autowired
    private AppTokenData appTokenData;

    /**
     * 检查记录
     */
    @Autowired
    private TCheckMapper tCheckMapper;

    /**
     * TODO 获取部门,以及对应的岗位 level1 levle2
     *
     * @param request request请求
     * @return result 返回的基础信息
     */
    @RequestMapping(value = "A200", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkCompany(HttpServletRequest request) {

        AppResult result = new AppResultImpl();

        try {
            ZzjgPersonnel zzjg = (ZzjgPersonnel) appTokenData.getAppUser(request);

            // 使用总公司 获取这家公司对应的所有的部门
            List<ZzjgDepartment> list = zzig_departmentService.selectDepartmentByCid(zzjg.getCid());

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
        } catch (NullPointerException n) {
            n.printStackTrace();
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络故障,请稍后再试");
            return result;
        }
    }

    /**
     * TODO 查询安全责任人
     *
     * @param request request请求
     * @return result 安全责任人
     */
    @RequestMapping(value = "A201", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkLevel2(HttpServletRequest request) {

        AppResult result = new AppResultImpl();
        try {
            ZzjgPersonnel zzjg = (ZzjgPersonnel) appTokenData.getAppUser(request);

            // 调用service层数据返回安全责任人
            List<Map<Integer, String>> list = checkManual.findUserByIdAndStatus(zzjg);

            result.setData(list);
            result.setStatus("0");
            result.setMessage("查询成功");

            return result;
        } catch (NullPointerException n) {
            n.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("网络故障,请稍后再试");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO 查询企业对应的高危检查
     *
     * @param request request请求
     * @return result 高危检查条目
     */
    @RequestMapping(value = "A212", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkGaoWei(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        try {
            ZzjgPersonnel zzjg = (ZzjgPersonnel) appTokenData.getAppUser(request);

            // 查询高危风险
            List<Map> list = checkManual.checkGaoWei(zzjg.getUid());
            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (NullPointerException e) {
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        } catch (Exception e) {
            result.setStatus("1");
            result.setMessage("网络故障");
            return result;
        }
    }


    /**
     * TODO 查询基础选项  jsp页面
     *
     * @param request request 请求
     * @return result 基础条目
     */
    @RequestMapping(value = "A2132", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkJiChu2(HttpServletRequest request) {

        User user = getLoginUser(request);

        AppResult result = new AppResultImpl();

        Map map = checkManual.checkJiChu(user.getId());
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(map);

        return result;

    }

    /**
     * TODO pc端查询高危level1
     *
     * @return list  高危levelOne条目
     */
    @RequestMapping(value = "A2133", method = RequestMethod.POST)
    public @ResponseBody
    List checkGaoWei2(@RequestBody Map<String, Object> params) {
        // 获取登陆内容
        try {

            String id = String.valueOf(params.get("id"));

            Integer industryId = Integer.valueOf(id);

            List<Map<String, Object>> GaoWeilist = checkManual.checkGaoWei2(industryId);

            return GaoWeilist;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TODO 查询基础检查 选项
     *
     * @param request request请求
     * @return result 小程序基础检查条目
     */
    @RequestMapping(value = "A213", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkJiChu(HttpServletRequest request) {
        // 获取登陆内容
        AppResult result = new AppResultImpl();
        ZzjgPersonnel zzjg = (ZzjgPersonnel) appTokenData.getAppUser(request);
        if (zzjg == null) {
            result.setStatus("1");
            result.setMessage("未成功登陆,请重新登陆");
            return result;
        }

        // 查询高危风险
        Map map = checkManual.checkJiChu(zzjg.getUid());
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(map);

        return result;
    }

    /**
     * TODO 高危检查选项
     *
     * @param industryId 返回的高危检查的id
     * @return result    小程序 高危检查条目
     */
    @RequestMapping(value = "A214", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkGaoWeiItem( Integer industryId) {
        // 获取登陆内容
        AppResult result = new AppResultImpl();
        try {

            Map map = checkManual.checkGaoWeiItem(industryId);
            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(map);

            return result;
        } catch (NullPointerException e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络故障");
            return result;
        }
    }

    /**
     * TODO  高危/基础  检查项详细信息
     *
     * @param checkLevel 高危/基础 检查条件
     * @return 小程序 检查项详细条目
     */
    @RequestMapping(value = "A215", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkJiChuAndGaoWei(CheckLevel checkLevel) {
        AppResult result = new AppResultImpl();
        try {

            List<Map> list = checkManual.checkGaoWeiAndJiChu(checkLevel);

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络异常");
            return result;
        }
    }

    /**
     * TODO 现场检查/查询level3
     *
     * @param checkLevel 现场检查条件
     * @return AppResult 小程序 现场检查项条目
     */
    @RequestMapping(value = "A202", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkLevel3(@RequestBody CheckLevel checkLevel) {

        AppResult result = new AppResultImpl();
        try {

            // 调用方法进行查询
            List<Map<String, Object>> list = checkManual.selectLevel4AndId(checkLevel);

            Set<String> set = new HashSet<>();
            for (Map level : list) {
                set.add((String) level.get("level3"));
            }
            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(set);

            return result;
        } catch (NullPointerException e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("未查询到数据");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络故障");
            return result;
        }
    }

    /**
     * TODO 查询level4
     *
     * @param checkLevel 前置的查询条件
     * @return list      保存该检查项的level4
     */
    @RequestMapping(value = "A203", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkLevel4(@RequestBody CheckLevel checkLevel) {
        AppResult result = new AppResultImpl();
        try {

            // 调用方法进行查询
            List<Map> list = checkManual.selectLevel5AndId(checkLevel);

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("未查询到数据");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络异常");
            return result;
        }
    }

    /**
     * TODO 保存自定义的检查模版, 并返回模版 Id
     *
     * @param checkItem 查询项信息
     * @return modelId  模版id
     */
    @RequestMapping(value = "A204", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveCheck(@RequestBody CheckItem checkItem) {
        AppResult result = new AppResultImpl();

        try {

            MySessionContext myc = MySessionContext.getInstance();
            HttpSession sess = myc.getSession(checkItem.getSessionId());

            ZzjgPersonnel zzjg = (ZzjgPersonnel) sess.getAttribute(checkItem.getAccess_token());
            // 保存检查项
            Integer modelId = checkManual.saveCheck(checkItem, zzjg);

            result.setStatus("0");
            result.setMessage("保存成功");
            result.setData(modelId);

            System.out.println("保存检查项并返回modelId" + modelId);
            return result;
        } catch (NullPointerException e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("未查询到数据");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络异常");
            return result;
        }

    }

    /**
     * TODO 根据用户点击查询(所有)模版
     *
     * @param sessionId    sessionId
     * @param access_token 令牌
     * @return list  企业关联的模版
     */
    @RequestMapping(value = "A205", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkDepartmentById(String sessionId, String access_token) {
        AppResult result = new AppResultImpl();

        try {
            MySessionContext myc = MySessionContext.getInstance();
            HttpSession sess = myc.getSession(sessionId);

            ZzjgPersonnel zzjg = (ZzjgPersonnel) sess.getAttribute(access_token);

            // 根据公司id获取模版信息
            List<Map<Integer, String>> list = checkManual.findModelByUid(zzjg.getUid());

            result.setStatus("0");
            result.setData(list);
            result.setMessage("查询成功");

            return result;
        } catch (NullPointerException e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络异常");
            return result;
        }

    }

    /**
     * TODO 根据模版id查询详细信息 > 开启检查
     *
     * @param checkModel 包含模版id信息
     * @return checkItemByModelId 检查记录的详细信息
     */
    @RequestMapping(value = "A206", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkItemtById(@RequestBody CheckModel checkModel) {
        AppResult result = new AppResultImpl();
        try {

            // 根据id查询并进行封装数据
            CheckItemS checkItemByModelId = saveMessageService.findCheckItemByModelId(checkModel.getModelId());

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(checkItemByModelId);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络故障");
            result.setStatus("1");
            return result;
        }

    }

    /**
     * TODO 根据安全责任人的id 查询相关的部门和岗位  预留没有使用
     *
     * @param sessionId    sessionID
     * @param access_token 令牌
     * @param personnelId  负责人id
     * @return map 获取相关部门
     */
    @ResponseBody
    @RequestMapping(value = "A207", method = RequestMethod.POST)
    public AppResult checkByStatus(String sessionId, String access_token, Integer personnelId) {
        AppResult result = new AppResultImpl();

        try {
            if (sessionId == null || access_token == null || personnelId == null) {
                result.setStatus("1");
                result.setMessage("查询失败,请重新查询");
                return result;
            }

            MySessionContext sess = MySessionContext.getInstance();
            HttpSession session = sess.getSession(sessionId);
            ZzjgPersonnel zzjg = (ZzjgPersonnel) session.getAttribute(access_token); // 获取session域中的信息

            // 直接 进行查询获取部门的id 和公司的id  根据部门id查询部门名称 ,然后在根据部门名称查询 所对应的风险点
            Map<String, List> map = checkManual.findLevel2ByPersonnelId(personnelId, zzjg.getUid());

            result.setStatus("2");
            result.setMessage("查询成功");
            result.setData(map);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络故障");
            result.setStatus("1");
            return result;
        }

    }

    /**
     * TODO 根据前端传递的合格不合格信息进行数据的存储
     *
     * @param saveDataMessageItem 检查结果信息
     * @return message             保存成功消息
     */
    @RequestMapping(value = "A208", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveMessage(@RequestBody SaveDataMessageItem saveDataMessageItem) {
        AppResult result = new AppResultImpl();

        try {

            MySessionContext sess = MySessionContext.getInstance();
            HttpSession session = sess.getSession(saveDataMessageItem.getSessionId());
            ZzjgPersonnel zzjg = (ZzjgPersonnel) session.getAttribute(saveDataMessageItem.getAccess_token()); // 获取session域中的信息

            String message = saveMessageService.saveCheckMessage(saveDataMessageItem, zzjg);

            result.setStatus("0");
            result.setMessage("检查成功,以向责任人发送消息");
            result.setData(message);

            return result;
        } catch (NullPointerException n) {

            n.printStackTrace();
            result.setStatus("1");
            result.setMessage("未成功保存数据");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络异常");
            return result;
        }
    }

    /**
     * TODO 根据当前用户查询所有的检查记录()  对根据判断求出这个责任人的部门和岗位
     *  首先要判断他是检查人员还是被检查人员
     *  根据状态进行查询,不同的检查详情在company_manul_tbl 获取岗位,来判断这个岗位的检查项是否合格,不合格进行显示
     * @param checkModel access_token信息
     * @return list       关于当前企业的不合格信息
     */
    @RequestMapping(value = "A209", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckItem(@RequestBody CheckModel checkModel) {
        AppResult result = new AppResultImpl();
        try {

            MySessionContext sess = MySessionContext.getInstance();
            HttpSession session = sess.getSession(checkModel.getSessionId());
            ZzjgPersonnel zzjg = (ZzjgPersonnel) session.getAttribute(checkModel.getAccess_token()); // 获取session域中的信息

            // 根据当前用户的id进行查询
            List<Map> list = saveMessageService.findCheckItemById(zzjg);

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;

        } catch (NullPointerException e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络异常");
            return result;

        }
    }

    /**
     * TODO 根据检查表信息 查询检查记录中不合格项查询出来
     * 首先根据用户id 进行判断获取他的部门 ,根据检查表的详细信息表示的是这个
     * @param checkId 检查表id
     * @return map     不合格的复查记录
     */
    @RequestMapping(value = "A210", method = RequestMethod.POST)
    public @ResponseBody
    AppResult reviewData(String checkId) {

        AppResult result = new AppResultImpl();

        try {

            List<TCheckItem> list = saveMessageService.findItemByCheckId(Integer.parseInt(checkId));
            // 查询检查类型
            TCheck tCheck = tCheckMapper.selectByPrimaryKey(Integer.parseInt(checkId));

            Map<String, Object> map = new LinkedHashMap();

            map.put("type", tCheck.getIndustryType());
            map.put("List", list);
            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(map);

            return result;
        } catch (NumberFormatException e) {

            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络异常");
            return result;
        }

    }

    /**
     * TODO 存储复查数据, 只要有一条数据不合格,复查表就存储不合格
     *
     * @param saveDataMessageItem 复查信息
     * @return message             成功消息
     */
    @RequestMapping(value = "A211", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveReviewData(@RequestBody SaveDataMessageItem saveDataMessageItem) {
        AppResult result = new AppResultImpl();

        try {
            //到域中获取数据
            MySessionContext sess = MySessionContext.getInstance();
            HttpSession session = sess.getSession(saveDataMessageItem.getSessionId());
            ZzjgPersonnel zzjg = (ZzjgPersonnel) session.getAttribute(saveDataMessageItem.getAccess_token()); // 获取session域中的信息

            String message = saveMessageService.saveReviewData(saveDataMessageItem, zzjg);

            result.setStatus("0");
            result.setMessage("保存成功");
            result.setData(message);
            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("未成功保存");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("1");
            result.setMessage("网络错误");
            return result;
        }

    }

    /**
     * TODO 企业端复查详情
     *
     * @param request   请求
     * @param reCheckId 复查表id
     * @return 详情信息
     */
    @RequestMapping(value = "B215", method = RequestMethod.POST)
    public @ResponseBody
    AppResult rechekItem(HttpServletRequest request, Integer reCheckId) {


        return null;
    }

    /**
     * TODO 保存图片上传并返回路径
     *
     * @param request 请求
     * @param file    接收的文件
     * @return realPath1  查询的路径
     * @throws IOException
     */
    @RequestMapping(value = "A300", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveImage(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        AppResult result = new AppResultImpl();
        System.out.println("执行文件上传");
        request.setCharacterEncoding("UTF-8");

        String realPath1 = "/images/upload/";
        String path = null;
        if (!file.isEmpty()) {
            System.out.println("成功获取图片");
            String fileName = file.getOriginalFilename();

            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    String realPath2 = realPath.replaceAll("\\\\", "/");
                    // 自定义的文件名称

                    String trueFileName = /*String.valueOf(System.currentTimeMillis()) +*/ fileName;
                    // 设置存放图片文件的路径
                    path = realPath2 + "images/upload/" + trueFileName;

                    // 判断是否存在
                    File file1 =new File(realPath2+"images/upload/");
                    if  (!file1 .exists()  && !file1 .isDirectory())
                    {
                        System.out.println("//不存在");
                        file1 .mkdir();
                    }

                    realPath1 += trueFileName;
                    System.out.println(path);

                    file.transferTo(new File(path));

                } else {
                    result.setStatus("1");
                    result.setMessage("不是我们想要的文件类型,请按要求重新上传");
                    return result;

                }
            } else {
                result.setStatus("1");
                result.setMessage("文件类型为空");
                return result;

            }
        } else {

            result.setStatus("1");
            result.setMessage("没有找到相对应的文件");

            return result;

        }
        result.setStatus("0");
        result.setMessage("保存成功");
        realPath1.replace("\\", "/");
        result.setData(realPath1);
        return result;

    }

}