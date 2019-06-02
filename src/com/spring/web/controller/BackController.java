package com.spring.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.spring.web.BaseController;
import com.spring.web.dao.TDeviceMapper;
import com.spring.web.dao.TWarnmsgMapper;
import com.spring.web.dao.UserGroupMapper;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.Company;
import com.spring.web.model.District;
import com.spring.web.model.Library;
import com.spring.web.model.Notice;
import com.spring.web.model.PushMess;
import com.spring.web.model.Regulation;
import com.spring.web.model.Standard;
import com.spring.web.model.TCleanWarnSetting;
import com.spring.web.model.TDevice;
import com.spring.web.model.TIndustry;
import com.spring.web.model.TItem;
import com.spring.web.model.TItemSerious;
import com.spring.web.model.TLevel;
import com.spring.web.model.Town;
import com.spring.web.model.Trade;
import com.spring.web.model.User;
import com.spring.web.model.Village;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.tobject.user.ModifyPwdReqDTO;
import com.spring.web.util.ConstantsUtil;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.EncryptUtil;

/**
 * @Title: backController
 * @Description: 后台控制器 TODO back(后面)
 * @author FL
 * @date 2017年7月10日 上午10:22:52
 * @version V1.0
 */
@Controller
@RequestMapping("/back")
public class BackController extends BaseController {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = 4143650732284392589L;
    @Autowired
    private CgfService cgfService;
    @Autowired
    private TDeviceMapper tDeviceMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private TWarnmsgMapper tWarnmsgMapper;

    /**
     * 后台登录
     */
    @RequestMapping(value = "")
    public String index() throws Exception {
        return "back/blogin";
    }

    /**
     * 后台首页
     */
    @RequestMapping(value = "main")
    public String main(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        if (null != user) {
            model.addAttribute("loginType", "0");
        } else {
            model.addAttribute("loginType", "1");
        }
        return "back/main";
    }

    /**
     * TO添加村
     */
    @RequestMapping(value = "village/village-add")
    public String addVillage(HttpServletRequest request) throws Exception {
        return "back/village/village-add";
    }

    /**
     * 村添加/编辑保存
     */
    @RequestMapping(value = "village/village-save")
    public @ResponseBody Result villageSave(Integer region, Village village, String vName, String vPsw, String jw)
            throws Exception {
        Result result = new ResultImpl();
        try {
            cgfService.villageSave(region, village, vName, vPsw, jw, result);
        } catch (Exception e) {
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
        return result;
    }

    /**
     * 村列表
     */
    @RequestMapping(value = "village/village-list")
    public String VillageList(String name, Model model) throws Exception {
        model.addAttribute("list", villageMapper.selectVillageList(name));
        model.addAttribute("name", name);
        return "back/village/village-list";
    }

    /**
     * 编辑村
     */
    @RequestMapping(value = "village/village-edit")
    public String VillageEdit(@RequestParam("userId") int userId, Model model) throws Exception {
        Village vi = villageMapper.selectByPrimaryKey(userId);
        model.addAttribute("vi", vi);
        model.addAttribute("user", userMapper.selectByPrimaryKey(userId));
        return "back/village/village-edit";
    }

    /**
     * TO添加镇页面
     */
    @RequestMapping(value = "town/town-add")
    public String TownAdd(HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("list", villageMapper.selectVillageList(null));
        return "back/town/town-add";
    }

    /**
     * 镇添加/编辑保存
     */
    @RequestMapping(value = "town/town-save")
    public @ResponseBody Result townSave(Town town, String tName, String tPsw, String jw, String villageids)
            throws Exception {
        Result result = new ResultImpl();
        try {
            cgfService.townSave(town, tName, tPsw, jw, villageids, result);
        } catch (Exception e) {
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
        return result;
    }

    /**
     * TO编辑镇页面
     */
    @RequestMapping(value = "town/town-edit")
    public String TownEdit(Integer userId, Model model) throws Exception {
        model.addAttribute("town", townMapper.selectByPrimaryKey(userId));
        model.addAttribute("user", userMapper.selectByPrimaryKey(userId));
        model.addAttribute("list", villageMapper.selectVillageList(null));
        return "back/town/town-edit";
    }

    /**
     * 镇列表
     */
    @RequestMapping(value = "town/town-list")
    public String TownList(Model model, String userName, String name, String isFreeze) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userName", userName);
        m.put("name", name);
        m.put("isFreeze", isFreeze);
        model.addAttribute("list", townMapper.selectTownList(m));
        model.addAttribute("userName", userName);
        model.addAttribute("name", name);
        model.addAttribute("isFreeze", isFreeze);
        return "back/town/town-list";
    }

    /**
     * 基库列表
     */
    @RequestMapping(value = "system/base-list")
    public String BaseList(Model model, Integer type) throws Exception {
        model.addAttribute("list", libraryMapper.selectLibraryList(type));
        model.addAttribute("type", type);
        return "back/system/base-list";
    }

    /**
     * 添加基库数据
     */
    @RequestMapping(value = "system/addToLibrary")
    public @ResponseBody Result addToLibrary(HttpServletRequest request, Library library) throws Exception {
        Result result = new ResultImpl();
        Library l = libraryMapper.selectByName(library);
        if (null != l) {
            result.setStatus("1");
            result.setMap("message", "该记录已存在");
            return result;
        }
        if (null == library.getId()) {
            libraryMapper.insertSelective(library);
        } else {
            libraryMapper.updateByPrimaryKeySelective(library);
        }
        return result;
    }

    /**
     * 企业登录冻结、解冻
     */
    @RequestMapping(value = "com-upd")
    public @ResponseBody Result comUpd(HttpServletRequest request, User user) throws Exception {
        Result result = new ResultImpl();
        userMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    /**
     * 删除基库数据
     */
    @RequestMapping(value = "system/deleteFromLibrary")
    public @ResponseBody Result deleteFromeLibrary(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        libraryMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 后台首页-中间
     */
    @RequestMapping(value = "welcome")
    public String welcome(Model model) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("time", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), 3), "yyyy-MM"));
        m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
        // List<Integer> count = troubleMapper.selectCount(m);
        // model.addAttribute("count", count);
        m.clear();
        m.put("type", "1");
        model.addAttribute("list", noticeMapper.selectNoticeList(m));
        m.clear();
        m.put("type", "2,3,4");
        model.addAttribute("list2", noticeMapper.selectNoticeList(m));
        model.addAttribute("c", villageMapper.selectCount());
        return "back/welcome";
    }

    /**
     * 页面调试用
     */
    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, Integer industryId) throws Exception {
        String url = request.getParameter("url");
        //log.error("zhangcl 2019.1.6 /back/test url:"+url);
        //return "back/welcome";
        return url;
    }

    TItem x(String content, Integer tid) {
        return new TItem(null, content, tid, "");
    }

    /**
     * TO管理员修改密码页面
     */
    @RequestMapping(value = "system/passwordSet")
    public String changePsw() throws Exception {
        return "back/system/passwordSet";
    }

    /**
     * 管理员修改密码
     */
    @RequestMapping(value = "/modify-pwd")
    public @ResponseBody Result modifyPwd(ModifyPwdReqDTO dto, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = userMapper.selectByPrimaryKey(getLoginUser(request).getId());
        String code = user.getPsw();
        String psw = dto.getPassword();
        if (!EncryptUtil.match(code, psw)) {
            result.setStatus("1");
            result.setMap("message", "旧密码错误");
        } else {
            // 新密码
            String xmm = EncryptUtil.encrypt(dto.getNewPassword());
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setPsw(xmm);
            userMapper.updateByPrimaryKeySelective(newUser);
        }
        return result;
    }

    /**
     * 冻结 解冻
     */
    @RequestMapping(value = "user-freeze")
    public @ResponseBody Result userFreeze(User user, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        userMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    /**
     * 总部企业 设置|取消
     */
    @RequestMapping(value = "user-clique")
    public @ResponseBody Result userClique(Trade trade, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        tradeMapper.updateByPrimaryKeySelective(trade);
        return result;
    }
    
    /**
     * 重置密码
     */
    @RequestMapping(value = "user-psw")
    public @ResponseBody Result userPsw(User user, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        user.setPsw(ConstantsUtil.ENCRYPT_PASSWORD_123456);
        userMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    /**
     * 公告列表
     */
    @RequestMapping(value = "notice/notice-zlist")
    public String NoticeList(HttpServletRequest request, Model model, Integer type) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", type);
        model.addAttribute("list", noticeMapper.selectNoticeList(m));
        m.clear();
        m.put("type", "2,3,4");
        model.addAttribute("list2", noticeMapper.selectNoticeList(m));
        return "back/notice/notice-zlist";
    }

    /**
     * 公告列表
     */
    @RequestMapping(value = "notice-list")
    public String NoticeList(Integer userId, Model model, String type, Integer type2, Integer type3, Integer type4)
            throws Exception {
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("type", type);
        if (null != type3) {
            if (type2 == 2)
                m1.put("type3", type3);
            if (type2 == 3) {
                m1.put("type3", type3 + 10);
            }
        } else {
            if (null != type2) {
                if (type2 == 2)
                    m1.put("types", "21,22,23,24,25");
                if (type2 == 3) {
                    m1.put("types", "31,32,33,34,35");
                }
            }
        }
        if (null != type4) {
            m1.put("type3", type4);
        }
        if (type2 != null && 1 == type2) {
            m1.put("type3", 10);
        }
        model.addAttribute("list", noticeMapper.selectNoticeList(m1));
        model.addAttribute("listL", districtMapper.selectDistrict());
        model.addAttribute("type", type);
        model.addAttribute("type2", type2);
        model.addAttribute("type3", type3);
        model.addAttribute("type4", type4);
        return "back/notice/notice-list";
    }
    
    /**
     * 公告列表推送信息显示
     */
    @RequestMapping(value = "notice-push-show")
    public @ResponseBody Result noticePushShow(Integer noticeId) throws Exception {
        Result result = new ResultImpl();
        PushMess pushMess = pushMessMapper.selectByNoticeId(noticeId);
        result.setMap("list", pushMess.getDistrictId());
        return result;
    }
    
    /**
     * 公告列表推送
     */
    @RequestMapping(value = "notice-push")
    public @ResponseBody Result noticePush(PushMess push) throws Exception {
        Result result = new ResultImpl();
        PushMess pushMess = pushMessMapper.selectByNoticeId(push.getNoticeId());
        if(null == pushMess){
            pushMessMapper.insertSelective(push);
        }else{
            push.setId(pushMess.getId());
            pushMessMapper.updateByPrimaryKeySelective(push);
        }
        return result;
    }
    

    /**
     * to添加安全之家
     */
    @RequestMapping(value = "notice-add")
    public String ToAddNotice(Integer userId, Model model) throws Exception {
        return "back/notice/notice-add";
    }

    /**
     * 安全之家添加/编辑保存
     */
    @RequestMapping(value = "notice-save")
    public String villageSave(Notice notice, HttpServletRequest request) throws Exception {
        notice.setUserId(getLoginUser(request).getId());
        if (null == notice.getId()) {
            String TimeNow = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            notice.setTime(TimeNow);
            noticeMapper.insertSelective(notice);
        } else {
            noticeMapper.updateByPrimaryKeySelective(notice);
        }
        return "back/notice/notice-list";
    }

    /**
     * 安全之家编辑
     */
    @RequestMapping(value = "notice-edit")
    public String villageEdit(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("notice", noticeMapper.selectByPrimaryKey(id));
        return "back/notice/notice-edit";
    }

    /**
     * 删除公告
     */
    @RequestMapping(value = "notice-del")
    public @ResponseBody Result noticeDel(Integer id) throws Exception {
        Result result = new ResultImpl();
        noticeMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 企业列表
     */
    @RequestMapping(value = "company/company-list")
    public String companyList(Model model, HttpServletRequest request,CompanyListReqDTO dto) throws Exception {
        User user = getLoginUser(request);
        cgfService.selectCompanyWithPage(dto, user, model);
        /*Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageName", villageName);
        m.put("companyName", companyName);
        m.put("isFreeze", isFreeze);
        model.addAttribute("list", companyMapper.selectCompanyList(m));*/
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        /*model.addAttribute("companyName", companyName);
        model.addAttribute("villageName", villageName);
        model.addAttribute("state", state);
        model.addAttribute("isFreeze", isFreeze);*/
        List<Map<String, Object>> listL = townMapper.selectListByDistrict(null);
        model.addAttribute("listL", listL);
        return "back/company/company-list";
    }
    
    /**
     * 企业添加页面
     */
    @RequestMapping(value = "company-add")
    public String toAddCompany(HttpServletRequest request, Model model) throws Exception {
        List<DynamicParameter<String, Object>> list = districtMapper.selectDistrict();
        model.addAttribute("list", list);
        return "back/company/company-add";
    }
    
    /**
     * 企业添加保存
     */
    @RequestMapping(value = "company/company-save")
    public @ResponseBody Result companySave(User user, HttpServletRequest request, Integer villageId, Integer tradeId)
            throws Exception {
        Result result = new ResultImpl();
        User xx = userMapper.selectUserByUserName(user.getUserName());
        if (null != xx) {
            result.setStatus("1");
            result.setMap("message", "该企业已存在");
            return result;
        }
        User user_d = getLoginUser(request);
        userService.registar(user, result, user_d, null == villageId ? getLoginUser(request).getId() : villageId);
        return result;
    }
    
    /**
     * 更改企业名称显示
     */
    @RequestMapping(value = "company-name-show")
    public @ResponseBody Result companyNameShow(Integer id) throws Exception {
        Result result = new ResultImpl();
        User user = userMapper.selectByPrimaryKey(id);
        result.setMap("list", user.getUserName());
        return result;
    }
    
    /**
     * 更改企业名称保存
     */
    @RequestMapping(value = "edit-name-save")
    public @ResponseBody Result editNameSave(Integer id , String userName) throws Exception {
        Result result = new ResultImpl();
        User user = userMapper.selectByPrimaryKey(id);
        User user2 = userMapper.selectUserByUserName(userName);
        userService.editName(user, result, user2, id, userName);
        return result;
    }

    /**
     * to企业添加页面
     */
    @RequestMapping(value = "vilage-list")
    public @ResponseBody Result vilageList(Integer townId) throws Exception {
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("townId", townId);
        List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
        result.setMap("list", list);
        return result;
    }
    
    /**
     * 企业详细资料
     */
    @RequestMapping(value = "company/company-show")
    public String recomany(Model model, @RequestParam("userId") int userId) throws Exception {
        Company c = companyMapper.selectByPrimaryKey(userId);
        if (null != c.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(c.getRegionId()));
        }
        model.addAttribute("c", c);
        Regulation r = regulationGet(userId);
        model.addAttribute("r", r);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", userId);
        model.addAttribute("peq", pequipmentMapper.selectByMap(m));
        model.addAttribute("melist", mequipmentMapper.selectByUserId(m));
        model.addAttribute("malist", materialMapper.selectByUserId(m));
        model.addAttribute("prlist", productMapper.selectByUserId(m));
        List<DynamicParameter<String, Object>> spelist = spersonMapper.selectSpersonList(m);
        model.addAttribute("spelist", spelist);
        model.addAttribute("seqlist", sequipmentMapper.selectSequipmentList(m));
        List<DynamicParameter<String, Object>> pjlist = evaluationMapper.selectEvaluationList(m);
        model.addAttribute("pjlist", pjlist);
        model.addAttribute("jclist", detectionMapper.selectDetectionList(m));
        List<DynamicParameter<String, Object>> exalist = examinationMapper.selectExaminationList(m);
        model.addAttribute("exalist", exalist);
        List<Map<String, Object>> standard = standardMapper.selectList(m);
        model.addAttribute("standard", standard);
        List<Map<String, Object>> danger1 = tCheckItemMapper.selectDangerIndexList(m);
        model.addAttribute("danger1", danger1);
        List<Map<String, Object>> productionProcessDiagram = productionProcessDiagramMapper.selectList(m);
        model.addAttribute("productionProcessDiagram", productionProcessDiagram);
        m.put("type", "1");
        // model.addAttribute("trolist", troubleMapper.selectByMap(m));
        // Map<String, Object> m6 = new HashMap<String, Object>();
        // m6.put("userId", userId);
        List<DynamicParameter<String, Object>> facilities = facilitiesMapper.selectFacilitiesList(m);
        model.addAttribute("faclist", facilities);
        m.clear();
        m.put("isType", 1);
        m.put("userId", userId);
        model.addAttribute("glzd_list", table2mapper.selectTable(m));// 管理制度
        m.put("isType", 0);
        model.addAttribute("zrz_list", table2mapper.selectTable(m));// 安全生产责任制
        return "village/company/company-show";
    }
   
    /**
     * 特种人员列表
     */
    @RequestMapping(value = "warning/sperson-list")
    public String spersonList(String villageName, String companyName, Model model, Integer isTime) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageName", villageName);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", spersonMapper.selectSpersonList(m));
        model.addAttribute("villageName", villageName);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        return "back/warning/sperson-list";
    }

    /**
     * 安全评价列表
     */
    @RequestMapping(value = "warning/evaluation-list")
    public String EvaluationList(Model model, String villageName, String companyName, Integer isTime, String evaType,
            String flag, String reportName) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageName", villageName);
        m.put("flag", flag);
        m.put("reportName", reportName);
        m.put("companyName", companyName);
        m.put("evaType", evaType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("list", evaluationMapper.selectEvaluationList(m));
        model.addAttribute("villageName", villageName);
        model.addAttribute("reportName", reportName);
        model.addAttribute("evaType", evaType);
        model.addAttribute("flag", flag);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        return "back/warning/evaluation-list";
    }

    /**
     * 各类许可证页面
     */
    @RequestMapping("evaluate/zheng-list")
    public String zhengList(Model model, HttpServletRequest request, String villageName, Standard standard,
            String companyName) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageName", villageName);
        m.put("type", standard.getType());
        m.put("companyName", companyName);
        model.addAttribute("list", standardMapper.selectList(m));
        model.addAttribute("type", standard.getType());
        model.addAttribute("companyName", companyName);
        model.addAttribute("villageName", villageName);
        return "back/evaluate/zheng-list";
    }

    /**
     * 安全检测列表
     */
    @RequestMapping(value = "warning/detection-list")
    public String detectionList(Model model, String villageName, String companyName, Integer isTime, String deType)
            throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageName", villageName);
        m.put("companyName", companyName);
        m.put("deType", deType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", detectionMapper.selectDetectionList(m));
        model.addAttribute("villageName", villageName);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        model.addAttribute("deType", deType);
        return "back/warning/detection-list";
    }

    /**
     * 特种设备列表
     */
    @RequestMapping(value = "warning/sequipment-list")
    public String sequipmentList(String villageName, String companyName, Model model, Integer isTime) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageName", villageName);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", sequipmentMapper.selectSequipmentList(m));
        model.addAttribute("villageName", villageName);
        model.addAttribute("companyName", companyName);
        model.addAttribute("isTime", isTime);
        return "back/warning/sequipment-list";
    }

    /**
     * 消防应急设备列表
     */
    @RequestMapping(value = "warning/facilities-list")
    public String facilitiesList(String villageName, String companyName, Model model) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("villageName", villageName);
        m.put("companyName", companyName);
        model.addAttribute("list", facilitiesMapper.selectFacilitiesList(m));
        model.addAttribute("villageName", villageName);
        model.addAttribute("companyName", companyName);
        return "back/warning/facilities-list";
    }

    /**
     * TO编辑检查内容/重大隐患
     */
    @RequestMapping(value = "danger-editor")
    public String dangerEditor(HttpServletRequest request, Model model) throws Exception {
        List<TIndustry> tI = tIndustryMapper.selectAll();
        model.addAttribute("TIndustry", tI);
        return "back/danger/danger-editor";
    }
    
    /**
     * 根据行业id获取检查分类
     */
    @RequestMapping(value = "tlevel-get")
    public @ResponseBody Result tlevelGet(Integer industryId) throws Exception {
        Result result = new ResultImpl();
        TIndustry t = tIndustryMapper.selectByPrimaryKey(industryId);
        if(t.getType().intValue() == 2) {//现场检查需要加上特种设备
            result.setMap("list", tLevelMapper.selectByIndustryAndTz(industryId));
        } else {
            result.setMap("list", tLevelMapper.selectByIndustry(industryId));
        }
        return result;
    }
    
    /**
     * 保存检查内容
     */
    @RequestMapping(value = "titem-save")
    public @ResponseBody Result titemSave(TItem i) throws Exception {
        Result result = new ResultImpl();
        if(null == i.getId()) {
            tItemMapper.insertSelective(i);
        } else {
            tItemMapper.updateByPrimaryKeySelective(i);
        }
        result.setMap("id", i.getId());
        return result;
    }
    
    /**
     * 删除检查内容
     */
    @RequestMapping(value = "titem-del")
    public @ResponseBody Result titemdel(Integer id) throws Exception {
        Result result = new ResultImpl();
        tItemMapper.deleteByPrimaryKey(id);
        return result;
    }
    
    /**
     * 删除重大隐患关键词
     */
    @RequestMapping(value = "titems-del")
    public @ResponseBody Result titemsdel(Integer id) throws Exception {
        Result result = new ResultImpl();
        tItemSeriousMapper.deleteByPrimaryKey(id);
        return result;
    }
    
    /**
     * 保存重大隐患关键词
     */
    @RequestMapping(value = "titems-save")
    public @ResponseBody Result titemSSave(TItemSerious i) throws Exception {
        Result result = new ResultImpl();
        if(null == i.getId()) {
            tItemSeriousMapper.insertSelective(i);
        } else {
            tItemSeriousMapper.updateByPrimaryKeySelective(i);
        }
        result.setMap("id", i.getId());
        return result;
    }
    
    /**
     * 根据levelid 获取检查内容与重大隐患
     */
    @RequestMapping(value = "tlevelitem")
    public String tlevelitem(Integer levelid, Model model) throws Exception {
        model.addAttribute("list", tItemMapper.selectByLevelIds(levelid.toString()));
        model.addAttribute("sList", tItemSeriousMapper.selectbylid(levelid));
        return "back/danger/danger-editor-load";
    }
    
    /**
     * 自查项目
     */
    @RequestMapping(value = "system/t-industry/{id}")
    public String tIndustry(@PathVariable Integer id, Model model) throws Exception {
        List<TLevel> lL = tLevelMapper.selectByIndustry(id);// 检查级别
        List<TItem> iL = tItemMapper.selectByIndustry(id);// 检查项目
        model.addAttribute("levelL", gson.toJson(lL));
        model.addAttribute("itemL", gson.toJson(iL));
        if (StringUtils.isEmpty(lL.get(0).getLevel3())) {// 只有两级目录
        }
        return "back/danger/danger-list";
    }

    /**
     * TO企业自查/重大隐患
     */
    @RequestMapping(value = "key-list")
    public String keyList(HttpServletRequest request, Model model) throws Exception {
        List<TIndustry> tI = tIndustryMapper.selectAll();
        model.addAttribute("TIndustry", tI);
        model.addAttribute("level", tItemSeriousMapper.selectTLevel());
        model.addAttribute("keywords", tItemSeriousMapper.selectKeywords());
        return "back/danger/key-list";
    }

    /**
     * 公告详情
     */
    @RequestMapping(value = "notice/notice-show")
    public String noticeShow(Model model, Integer id, Integer type) throws Exception {
        Notice n = noticeMapper.selectByPrimaryKey(id);
        model.addAttribute("n", n);
        if (null != type) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("type", type);
            model.addAttribute("list", noticeMapper.selectNoticeList(m));
        }
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("type", "2,3,4");
        model.addAttribute("list2", noticeMapper.selectNoticeList(m1));
        m1.clear();
        m1.put("type", "1");
        model.addAttribute("list3", noticeMapper.selectNoticeList(m1));
        model.addAttribute("type", type);
        return "back/notice/notice-show";
    }

    /**
     * TO添加区县页面
     */
    @RequestMapping(value = "district-add")
    public String districtAdd(HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("list", townMapper.selectTownList(null));
        return "back/district/district-add";
    }

    /**
     * TO编辑区县页面
     */
    @RequestMapping(value = "district-edit")
    public String districtEdit(Integer userId, Model model) throws Exception {
        model.addAttribute("district", districtMapper.selectByPrimaryKey(userId));
        model.addAttribute("user", userMapper.selectByPrimaryKey(userId));
        model.addAttribute("list", townMapper.selectTownList(null));
        return "back/district/district-edit";
    }

    /**
     * 区县列表
     */
    @RequestMapping(value = "district-list")
    public String districtList(Model model, String userName, String name, String isFreeze) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userName", userName);
        m.put("name", name);
        m.put("isFreeze", isFreeze);
        model.addAttribute("list", districtMapper.selectList(m));
        model.addAttribute("userName", userName);
        model.addAttribute("name", name);
        model.addAttribute("isFreeze", isFreeze);
        return "back/district/district-list";
    }

    /**
     * 区县添加/编辑保存
     */
    @RequestMapping(value = "district-save")
    public @ResponseBody Result districtSave(District d, String tName, String tPsw, String jw, String villageids)
            throws Exception {
        Result result = new ResultImpl();
        try {
            cgfService.districtSave(d, tName, tPsw, jw, villageids, result);
        } catch (Exception e) {
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
        return result;
    }

    /**
     * TO添加行业页面
     */
    @RequestMapping(value = "trade-add")
    public String tradeAdd(HttpServletRequest request, Model model, Integer userId) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        if (userId != null) {
            model.addAttribute("user", userMapper.selectByPrimaryKey(userId));
            model.addAttribute("trade", tradeMapper.selectByPrimaryKey(userId));
            if (tradeCompanyMapper.selectByUserId(userId).size() == 0) {
                model.addAttribute("tradeCompany", "[]");
            } else {
                model.addAttribute("tradeCompany", JSONArray.toJSONString(tradeCompanyMapper.selectByUserId(userId)));
            }
        } else {
            model.addAttribute("tradeCompany", "[]");
        }
        if (companyMapper.selectCompanyList(m).size() == 0) {
            model.addAttribute("companylist", "[]");
        } else {
            model.addAttribute("companylist", JSONArray.toJSONString(companyMapper.selectCompanyList(m)));
        }
        if (libraryMapper.selectLibraryList(1).size() == 0) {
            model.addAttribute("tradeType", "[]");
        } else {
            model.addAttribute("tradeType", new Gson().toJson(libraryMapper.selectLibraryList(1)));
        }
        //获取区列表
        List<DynamicParameter<String, Object>> list = districtMapper.selectDistrict();
        model.addAttribute("arealist", list);
        return "back/trade/trade-add";
    }

    /**
     * 行业列表
     */
    @RequestMapping(value = "trade-list")
    public String tradeList(Model model, String userName, String name, String isFreeze) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userName", userName);
        m.put("name", name);
        m.put("isFreeze", isFreeze);
        model.addAttribute("list", tradeMapper.selectList1(m));
        //log.error(" admin_trade："+ tradeMapper.selectList1(m));
        model.addAttribute("userName", userName);
        model.addAttribute("name", name);
        model.addAttribute("isFreeze", isFreeze);
        return "back/trade/trade-list";
    }

    /**
     * 行业添加/编辑保存
     */
    @RequestMapping(value = "trade-save")
    public @ResponseBody Result tradeSave(Integer userId, String userName, String tradeName, String userPsw,
            String companyArea) throws Exception {
        Result result = new ResultImpl();
        try {
            cgfService.tradeSave(userId, userName, tradeName, userPsw, companyArea, result);
        } catch (Exception e) {
            result.setStatus("1");
            result.setMap("message", "系统异常");
        }
        return result;
    }
    
    /**
     * 下载资料
     */
    @RequestMapping(value = "download")
    public void hedownload(String filename, String fileurl, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        File realPath = new File(request.getServletContext().getRealPath("/"), fileurl.replace("/smaq", ""));
        if(!realPath.exists()) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("该文件不存在");
            out.flush();
            out.close();
            return;
        }
        
        filename = URLEncoder.encode(new String(filename.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8");
        response.setContentType(request.getServletContext().getMimeType(filename));
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        
        InputStream in = new FileInputStream(realPath);
        OutputStream out = response.getOutputStream();
        try {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } finally {
            in.close();
            out.close();
        }
    }
    
    
    /**
     * 资源导入功能
     * 
     * @param file
     * @param contractCode
     * @throws Exception
     */
    @RequestMapping(value = "importExcel", produces = "text/html;charset=utf-8")
    public void importCompanyExcel(Model model,Integer villageId, HttpServletRequest request,HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        userService.companyImportSave(result, file ,villageId);
        writeResponse(result, response);//该方法调用如下
    }
    
    /**
     * 资源导入持证上岗人员名单
     * 
     * @param file
     * @param contractCode
     * @throws Exception
     */
    @RequestMapping(value = "importSpersonExcel", produces = "text/html;charset=utf-8")
    public void importSpersonExcel(Model model, Integer flag,HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        userService.spersonImportSave(result, flag, file);
        writeResponse(result, response);//该方法调用如下
    }
    
    /**
     * 资源导入 特种设备
     * 
     * @param file
     * @param contractCode
     * @throws Exception
     */
    @RequestMapping(value = "importSequipmentExcel", produces = "text/html;charset=utf-8")
    public void importSequipmentExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam MultipartFile file2) throws Exception {
        Result result = new ResultImpl();
        userService.sequipmentImportSave(result, file2);
        writeResponse(result, response);//该方法调用如下
    }
    
    /**
     *  为response提供Json格式的返回数据
     */
    public void writeResponse(Object obj, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=utf-8");//设置编码
            String str = JSON.toJSONString(obj);
            PrintWriter writer = response.getWriter();
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 添加或修改设备
    * @param request
    * @param model
    * @return
     */
    @RequestMapping("/toAddEquipment")
    public String toAddEquipment(HttpServletRequest request,Model model){
        User user = getLoginUser(request);
        String deviceId = request.getParameter("deviceId");
        if(StringUtils.isNotBlank(deviceId)){
            TDevice device = tDeviceMapper.selectByPrimaryKey(Integer.parseInt(deviceId));
            if(null != device){
                model.addAttribute("companyName", userGroupMapper.getCompanyNameByUserId(device.getCompanyId()));
            }
            model.addAttribute("device",device);
        }
        if(user.getUserType().equals(1)){
            return "back/device/addEquipment";
        }else{
            return "";
        }
    }
    
    /**
     * 检查设备是否存在
    * @param request
    * @return
     */
    @RequestMapping("checkDeviceSnExsist")
    @ResponseBody
    public JSONObject checkDeviceSnExsist(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
          String deviceSn = request.getParameter("deviceSn");
          result.put("id", tDeviceMapper.checkDeviceSnExsits(deviceSn));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("/addDevice")
    @ResponseBody
    public JSONObject addDevice(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            result.put("status", false);
            User user = getLoginUser(request);
            if(null != user && user.getUserType().equals(1)){
                String deviceId = request.getParameter("deviceId");
                String deviceName = request.getParameter("deviceName");
                String deviceSn = request.getParameter("deviceSn");
                String companyId = request.getParameter("companyId");
                String site = request.getParameter("site");
                String phonename = request.getParameter("phonename");
                if(StringUtils.isNotBlank(deviceId)){
                    TDevice tDevice = new TDevice();
                    tDevice.setId(Integer.parseInt(deviceId));
                    if(StringUtils.isNotEmpty(deviceName)){
                        tDevice.setName(deviceName);
                    }
                    if(StringUtils.isNotEmpty(deviceSn)){
                        tDevice.setDeviceSn(deviceSn);
                    }
                    if(StringUtils.isNotEmpty(companyId)){
                        tDevice.setCompanyId(Integer.parseInt(companyId));
                    }
                    if(StringUtils.isNotEmpty(site)){
                        tDevice.setSite(site);
                    }
                    if(StringUtils.isNotEmpty(phonename)){
                        tDevice.setPhonename(phonename);
                    }
                    tDevice.setUpdateTime(new Date());
                    result.put("status", tDeviceMapper.updateByPrimaryKeySelective(tDevice)>0?true:false);
                }else{
                    TDevice tDevice = new TDevice();
                    if(StringUtils.isNotEmpty(deviceName)){
                        tDevice.setName(deviceName);
                    }
                    if(StringUtils.isNotEmpty(deviceSn)){
                        tDevice.setDeviceSn(deviceSn);
                    }
                    if(StringUtils.isNotEmpty(companyId)){
                        tDevice.setCompanyId(Integer.parseInt(companyId));
                    }
                    if(StringUtils.isNotEmpty(site)){
                        tDevice.setSite(site);
                    }
                    if(StringUtils.isNotEmpty(phonename)){
                        tDevice.setPhonename(phonename);
                    }
                    tDevice.setUpdateTime(new Date());
                    tDevice.setCreateTime(new Date());
                    result.put("status", tDeviceMapper.insertSelective(tDevice)>0?true:false);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 查询企业
    * @param request
    * @return
     */
    @RequestMapping("/getCompanyByCondition")
    @ResponseBody
    public JSONObject getCompanyByCondition(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String companyName = request.getParameter("companyName");
        User user = getLoginUser(request);
        if(null != user){
            Map<String, Object> params = new HashMap<String,Object>();
            if(!StringUtils.isEmpty(companyName)){
                try {
                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage(),e);
                }
                params.put("companyName", companyName);
            }
            result.put("list", userGroupMapper.searchCompanyByCondition(params));
        }
        return result;
    }
    
    /**
     * 去设备列表页
    * @param reques
    * @param model
    * @return
     */
    @RequestMapping("/toDeviceList")
    public String getDeviceList(HttpServletRequest reques,Model model){
        model.addAttribute("list", tDeviceMapper.getDeviceCompanyList());
        return "back/device/deviceList";
    }
    
    /**
     * 获取设备列表数据
     */
    @RequestMapping("/getDeviceList")
    @ResponseBody
    public JSONObject getDeviceList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            String pages = request.getParameter("page");
            String limits = request.getParameter("limit");
            String companyId = request.getParameter("companyId");
            String deviceName = request.getParameter("deviceName");
            Integer page = 1;
            Integer limit = 10;
            if(StringUtils.isNotBlank(pages)){
                page = Integer.parseInt(pages);
            }
            if(StringUtils.isNotBlank(limits)){
                limit = Integer.parseInt(limits);
            }
            Map<String, Object> map = new HashMap<String,Object>();
            if(StringUtils.isNotBlank(companyId)){
                map.put("companyId", Integer.parseInt(companyId));
            }
            if(StringUtils.isNotBlank(deviceName)){
                map.put("deviceName", URLDecoder.decode(URLDecoder.decode(deviceName, "utf-8"),"utf-8"));
            }
            Integer count = tDeviceMapper.getDeviceCountByCondition(map);
            result.put("total", count);
            map.put("page", (page-1)*limit);
            map.put("limit", limit);
            List<Map<String, Object>> list = tDeviceMapper.getDeviceListByCondition(map);
            if(null != list && list.size() > 0){
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                for(Map<String, Object> maps : list){
                    if(null != maps.get("createTime")){
                        maps.put("createTime", format.format(maps.get("createTime")));
                    }
                    if(null == maps.get("lastTime")){
                        maps.put("status", 0);
                    }else{
                        if(now.getTime() - ((Date)maps.get("lastTime")).getTime() > 60 * 60 * 1000){
                            maps.put("status", 0);
                        }else{
                            maps.put("status", 1);
                        }
                    }
                }
            }
            result.put("list", list);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("/toDeleteDevice")
    @ResponseBody
    public JSONObject toDeleteDevice(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            result.put("status", false);
            String deviceSn = request.getParameter("deviceSn");
            result.put("status", tDeviceMapper.deleteDeviceWithSensors(deviceSn)>0?true:false);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * to设置清除时间间隔
     */
    @RequestMapping(value = "change-timesetting")
    public String toChangeTimeSetting(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        TCleanWarnSetting setting = userService.geTCleanWarnSetting(user.getId());
        if(null != setting){
            model.addAttribute("setting", setting);
        }
        return "back/timesetting/timeSetting";
    }
    
    @RequestMapping(value = "doTtimesetting")
    @ResponseBody
    public JSONObject doTtimesetting(HttpServletRequest request, Model model) throws Exception {
        JSONObject result = new JSONObject();
        try {
            result.put("status", false);
            String times = request.getParameter("times");
            if(!StringUtils.isEmpty(times) && "1,3,6,12".contains(times)){
                TCleanWarnSetting setting = new TCleanWarnSetting();
                User user = getLoginUser(request);
                setting.setCreateTime(new Date());
                setting.setCreator(user.getId());
                setting.setMaxTimes(Integer.parseInt(times));
                setting.setUpdateName(user.getId());
                setting.setUpdateTime(new Date());
                result.put("status", userService.updateCleanWarnSetting(setting));
                Integer time = Integer.parseInt(times);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(calendar.MONTH, -time);
                tWarnmsgMapper.updateCleanTimeByMonth(calendar.getTime());
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
}
