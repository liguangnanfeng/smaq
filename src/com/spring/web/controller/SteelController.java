package com.spring.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.spring.web.BaseController;
import com.spring.web.dao.HiddenPlanMapper;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.*;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.tobject.user.LoginReqDTO;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.*;
import java.util.*;

@Controller
@RequestMapping("/steel")
public class SteelController extends BaseController {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 2125785439865846208L;
    @Autowired
    private UserService userService;
    @Autowired
    private CgfService cgfService;
    @Autowired
    private HiddenPlanMapper hiddenPlanMapper;

    /**
     * 特岗登录验证
     *
     * @param dto
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/glogin")
    public @ResponseBody
    Result login(LoginReqDTO dto, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = dto.getUserName();
        String password = dto.getPassword();
        Result result = new ResultImpl();
        //基本验证
        if (StringUtils.isBlank(username)) {
            result.setStatus("1");
            result.setMap("message", "账号不能为空。");
            return result;
        }
        if (StringUtils.isBlank(password)) {
            result.setStatus("1");
            result.setMap("message", "密码不能为空。");
            return result;
        }

        try {
            User user = userMapper.selectUserByUserName(username);
            // 判断账号是否存在
            if (user == null) {
                result.setStatus("1");
                result.setMap("message", "该账号不存在。");
                return result;
            }
            // 判断账号是否被冻结
            if (!"0".equals(user.getIsFreeze())) {
                result.setStatus("1");
                result.setMap("message", "该账号被冻结。");
                return result;
            }
            //处理惠山区港口的特殊账号
            if (!user.getUserName().equals("惠山区港口")) {
                // 判断当用户类型不是10时,提示错误
                if (user.getUserType() == 1 || user.getUserType() == 2 || user.getUserType() == 5) {
                    result.setStatus("1");
                    result.setMap("message", "请用政府账号登录。");
                    return result;
                }
            }
            if (35346 != user.getId()) {
                result.setStatus("1");
                result.setMap("message", "请用政府账号登录。");
                return result;
            }
            //使用shiro进行登录
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            SessionUtil.setUser(request, user);
            result.setMap("userType", user.getUserType());
            result.setMap("userId", user.getId());
        } catch (Exception uae) {
            uae.printStackTrace();
            log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            result.setStatus("1");
            result.setMap("message", "用户名或密码不正确。");
            return result;
        }
        String msg = "用户登录日志:";
        log.debug(msg + "[" + username + "]" + "登录成功");
        return result;
    }

    /**
     * 特钢首页
     */
    @RequestMapping(value = "main")
    public String main(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
        //log.error(trade.toString());
        model.addAttribute("name_", trade.getName());
        model.addAttribute("userId", trade.getUserId());
        if (trade.getIsClique() == 1) { //跳转到总部企业
            return "steel/clique-main";
        }
        return "trade/main";
    }

    /**
     * 特岗首页-中间
     */
    @RequestMapping(value = "welcome")
    public String welcome(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        CompanyListReqDTO dto = new CompanyListReqDTO();
        List<Integer> count = userService.selectCount(dto, user);

        //log.error("count："+count.toString());

        String userIds = (String) m.get("userIds");
        if (userIds.equals("-1")) {
            model.addAttribute("number", 0);
        } else {
            model.addAttribute("number", userIds.split(",").length);//企业数量
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = df.format(new Date().getTime()-15*24*60*60*1000);

        Date startTime1 = df.parse(startTime);
        Date endTime = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(user.getId()).append("',");
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        Integer counts = tCheckMapper.findAllCounte(sb.toString(),startTime1,endTime); // 排查数据
        Integer counts1 = tCheckItemMapper.findAllCounte(sb.toString()); // 治理数据

        m.put("tradeId", null);
        model.addAttribute("name_", tradeMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("count", count);
        model.addAttribute("userId", user.getId());
        model.addAttribute("counts1", counts1);
        model.addAttribute("counts", counts);
        Trade trade = tradeMapper.selectByPrimaryKey(user.getId());

        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("status", 2);
        m1.put("userId", sb.toString());
        m1.put("d", 1);
        List<Map<String, Object>> list11 = tCheckItemMapper.findACompanyAllList(m1);
        List<Map<String, Object>> list22 = tCheckItemMapper.findDangerAllList(m1);
        List<Map<String, Object>> list33 = tCheckItemMapper.findLevelAllList(m1);
        model.addAttribute("count7", list11.size() + list22.size() + list33.size()); // 未整改
        if (trade.getIsClique() == 1) {//行业端集团型企业
            return "steel/clique-welcome";
        }
        return "trade/welcome";
    }

    /**
     * 特钢企业基本信息
     */
    @RequestMapping("clique/basic-information")
    public String basicInformation(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        //Company c = companyMapper.selectByPrimaryKey(user.getId());
        TradeClique tradeClique = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        if (tradeClique == null) {
            TradeClique tc = new TradeClique();
            Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
            tc.setUserId(trade.getUserId());
            tc.setName(trade.getName());
            tradeCliqueMapper.insertSelective(tc);
            tradeClique = tc;
        } else {
            tradeClique.setName(tradeMapper.selectByPrimaryKey(user.getId()).getName());
            tradeCliqueMapper.updateByPrimaryKeySelective(tradeClique);
        }
        if (null != tradeClique.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(tradeClique.getRegionId()));
        }
        model.addAttribute("c", tradeClique);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "steel/information/clique-information1";
    }

    /**
     * 企业基本信息修改
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("clique/basic-information-change")
    public String basicInformationChange(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("TradeCompanyName", tradeMapper.selectByPrimaryKey(user.getId()).getName());
        //Company c = companyMapper.selectByPrimaryKey(user.getId());
        TradeClique c = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        List<Library> list1 = libraryMapper.selectLibraryList(1);
        List<Library> list2 = libraryMapper.selectLibraryList(2);
        model.addAttribute("c", c);
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        user = userMapper.selectByPrimaryKey(user.getId());//定位信息实时
        model.addAttribute("u", user);
        //log.error("user："+user.toString());
        model.addAttribute("ind3L", tIndustryMapper.selectByType(3));// 高危检查类别
        return "steel/information/clique-information1edit";
    }

    /**
     * 组织架构首页
     *
     * @throws Exception
     */
    @RequestMapping("threeLeft")
    public String threeLeft(Model model, HttpServletRequest request, String leftBasic) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());

//        // TODO 判断该公司的行业是否为化工行业,为化工行业就显示内容
//        if (company.getIndustry().contains("化工")) {
//            model.addAttribute("industry", 5);
//        }
        model.addAttribute("leftBasic", leftBasic);
        model.addAttribute("userName", user.getUserName());
        return "steel/org/threeLeft";
    }

    /**
     * 原辅材料-列表展示
     *
     * @param model
     * @param request
     * @param materialName
     * @return
     * @throws Exception
     */
    @RequestMapping("product/material-list")
    public String mainMaterialList(Model model, HttpServletRequest request, String materialName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        m.put("materialName", materialName);
        model.addAttribute("list", materialMapper.selectByUserId(m));
        model.addAttribute("materialName", materialName);
        return "steel/product/material-list";
    }

    /**
     * 原辅材料——添加
     */
    @RequestMapping(value = "product/material-add")
    public String addMaterial(HttpServletRequest request) throws Exception {
        return "steel/product/material-add";
    }

    /**
     * 原辅材料——添加/编辑保存
     */
    @RequestMapping(value = "product/material-save")
    public @ResponseBody
    Result materialSave(Material material, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        material.setUserId(user.getId());
        if (null == material.getId()) {
            materialMapper.insertSelective(material);
        } else {
            materialMapper.updateByPrimaryKeySelective(material);
        }
        return result;
    }

    /**
     * 原辅材料-编辑展示
     */
    @RequestMapping(value = "product/material-edit")
    public String materialEdit(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("ma", materialMapper.selectByPrimaryKey(id));
        return "tradeclique/product/material-edit";
    }

    /**
     * 原辅材料——删除
     */
    @RequestMapping(value = "product/deleteMaterial")
    public @ResponseBody
    Result deleteMaterial(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        materialMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 主要产品-列表展示
     *
     * @param model
     * @param request
     * @param proName
     * @return
     * @throws Exception
     */
    @RequestMapping("product/product-list")
    public String mainProductList(Model model, HttpServletRequest request, String proName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        m.put("proName", proName);
        model.addAttribute("list", productMapper.selectByUserId(m));
        model.addAttribute("proName", proName);
        return "steel/product/product-list";
    }

    /**
     * 主要产品——添加
     */
    @RequestMapping(value = "product/product-add")
    public String addProduct(HttpServletRequest request) throws Exception {
        return "steel/product/product-add";
    }

    /**
     * 主要产品——编辑
     */
    @RequestMapping(value = "product/product-edit")
    public String proEdit(@RequestParam("id") int id, Model model) throws Exception {
        //log.error("pr："+ productMapper.selectByPrimaryKey(id));
        model.addAttribute("pr", productMapper.selectByPrimaryKey(id));
        return "steel/product/product-edit";
    }

    /**
     * 主要产品——删除
     */
    @RequestMapping(value = "product/deleteProduct")
    public @ResponseBody
    Result deleteProduct(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        productMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 主要产品——添加/编辑——保存
     */
    @RequestMapping(value = "product/product-save")
    public @ResponseBody
    Result productSave(Product product, Integer code, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        product.setUserId(user.getId());
        if (null == product.getId()) {
            productMapper.insertSelective(product);
        } else {
            productMapper.updateByPrimaryKeySelective(product);
        }
        return result;
    }

    /**
     * 生产工艺——列表
     */
    @RequestMapping("process/process-list")
    public String processList(Model model, HttpServletRequest request, String project) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", user.getId());
        m.put("project", project);
        List<Map<String, Object>> list = productionProcessDiagramMapper.selectList1(m);
        //log.error("list："+list);
        model.addAttribute("list", list);
        model.addAttribute("project", project);
        return "steel/process/process-list";
    }

    /***
     * 生产工艺-添加,编辑
     * @param model
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("process/process-edit")
    public String processEdit(Model model, HttpServletRequest request, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("be", productionProcessDiagramMapper.selectByPrimaryKey(id));
        }
        return "steel/process/process-edit";
    }

    /**
     * 生产工艺-删除
     *
     * @param id
     * @return
     */
    @RequestMapping("process/process-del")
    public @ResponseBody
    Result processDel(Integer id) {
        Result result = new ResultImpl();
        productionProcessDiagramMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 生产工艺——保存
     */
    @RequestMapping("process/process-save")
    public @ResponseBody
    Result processSave(HttpServletRequest request,
                       ProductionProcessDiagram productionProcessDiagram) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        productionProcessDiagram.setUserId(user.getId());
        if (null == productionProcessDiagram.getId()) {
            productionProcessDiagramMapper.insertSelective(productionProcessDiagram);
        } else {
            productionProcessDiagramMapper.updateByPrimaryKey(productionProcessDiagram);
        }
        return result;
    }

    /**
     * 特钢的分厂展示
     */
    @RequestMapping(value = "company/company-list")
    public String companyList(Model model, HttpServletRequest request, CompanyListReqDTO dto, Integer totalzc, Integer totalwyx) throws Exception {
        User user = getLoginUser(request);
        cgfService.selectCompanyWithPage2(dto, user, model);
        if (user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }
        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("sk", request.getParameter("sk"));
        model.addAttribute("totalzc", totalzc);
        model.addAttribute("totalwyx", totalwyx);
        model.addAttribute("dto", dto);
        model.addAttribute("title", "全部企业");
        if (StringUtils.isNotBlank(dto.getDoubleDanger())) {
            model.addAttribute("title", "高危作业");
        }
        if (StringUtils.isNotBlank(dto.getCisDanger())) {
            model.addAttribute("title", "重大危险源企业");
        }
        return "steel/company/company-list";
    }

    /*
     * 风险辨识,其他因素 !!!
     * @param model
     * @param request
     * @param type
     * @return
     * @throws Exception
     **/
    @RequestMapping({"risk-list"})
    public String riskList(Model model, HttpServletRequest request, Integer type, Integer number, Integer dmid,
                           Integer flag, String types, String id, String buttons, String count1) throws Exception {
        User user = this.getLoginUser(request);
        //Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        TradeClique tradeClique = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        if (null == count1 || Integer.parseInt(count1) == 0) {
            count1 = "0";
        } else if (Integer.parseInt(count1) != 0) {
            count1 = "1";
        }

        if (null == buttons) {
            buttons = "2";
        }
        if (null != buttons && Integer.parseInt(buttons) == 1) {
            // 根据 uid 修改 dangerId 为 null
            /*Integer a = zzjgDepartmentMapper.deleteAll(null,user.getId(),new Date());*/
            // 根据 uid 查询所有的数据信息
            List<ZzjgDepartment> zzjgDeparList = zzjgDepartmentMapper.selectLevel1DangerIds(user.getId());
            model.addAttribute("buttons", 1);
            model.addAttribute("zzjgDeparList", zzjgDeparList);
        }
        if (null != buttons && Integer.parseInt(buttons) == 2) {
            model.addAttribute("buttons", 2);
        }
        if (StringUtils.isEmpty(tradeClique.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        } else {
            model.addAttribute("company", tradeClique);
            Map<String, Object> m = new HashMap();
            if (null != types) {
                m.put("type", Integer.parseInt(types));
            }
            m.put("flag", "1,2,3,4,5");
            m.put("uid", user.getId());
            if (null != dmid) {
                m.put("dmid", Integer.parseInt(id));
            }
            List<Map<Object, Object>> zzjg = null;
            List<Map<Object, Object>> zzjg1 = null;
            List<ACompanyManual> list = null;
            List acL = null;
            if (null == types || Integer.parseInt(types) == 1 || Integer.parseInt(types) == 2 || Integer.parseInt(types) == 3) {
                if (null == number && null == types) {
                    number = 3;
                } else if (null != types && types.equals("3")) { // 现场
                    number = 1;
                } else if (null != types && types.equals("2")) { // 基础
                    number = 2;
                }
                if (number == 1) { // 现场
                    String dangerIds = "1";

                    zzjg1 = this.zzjgDepartmentMapper.selectLevel1All(user.getId(), dangerIds);
                    list = aCompanyManualMapper.findDangerOne(user.getId(), id);
                    acL = this.aCompanyManualMapper.selectByAll(m);
                    if (null == id) {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1All(user.getId(), dangerIds);
                    } else {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1One(user.getId(), dangerIds, Integer.parseInt(id));
                    }
                } else if (number == 2) { // 基础
                    String dangerIds = "2";
                    acL = this.aCompanyManualMapper.selectBase(m);
                    list = aCompanyManualMapper.findOne("基础管理", user.getId(), id);
                    zzjg1 = this.zzjgDepartmentMapper.selectLevel1All(user.getId(), dangerIds);
                    if (null == id) {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1All(user.getId(), dangerIds);
                    } else {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1One(user.getId(), dangerIds, Integer.parseInt(id));
                    }
                } else if (number == 3) { // 设置
                    if (null != buttons && Integer.parseInt(buttons) == 1) {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1ByUid(user.getId());
                    } else if (Integer.parseInt(buttons) == 2) {
                        zzjg = this.zzjgDepartmentMapper.selectEmpyDangerIds(user.getId());
                    }
                }

                Integer indus = 0;
                if (tradeClique.getIndustry().contains("化工")) {
                    indus = 1;
                } else {
                    indus = 2;
                }
                String parName = null;
                if (null != zzjg && zzjg.size() == 1) {
                    for (Map<Object, Object> zzjgList : zzjg) {
                        parName = String.valueOf(zzjgList.get("name"));
                    }
                } else {
                    parName = "";
                }

                // 根据公司 ID 查询所有的现场风险信息
                Integer nowCount = aCompanyManualMapper.selectCountNow(user.getId(), "现场管理");


                model.addAttribute("nowCount", nowCount);
                model.addAttribute("count1", count1);
                model.addAttribute("parName", parName);
                model.addAttribute("buttons", buttons);
                model.addAttribute("indus", indus);
                model.addAttribute("ids", id);
                model.addAttribute("list", list);
                model.addAttribute("zzjgDep1", zzjg1);
                model.addAttribute("number", number);
                model.addAttribute("zzjgDep", zzjg);
                model.addAttribute("acL", acL);

                return "company/safety-system/risk-list1";
            } else {
                String dangerId = null;
                if (Integer.parseInt(types) == 4) { // 职业病风险物理因素
                    dangerId = "4";
                    type = 1;
                } else if (Integer.parseInt(types) == 5) { // 职业病风险化学因素
                    dangerId = "5";
                    type = 2;
                } else if (Integer.parseInt(types) == 6) { // 高危工艺辨识
                    dangerId = "6";
                    type = 3;
                } else if (Integer.parseInt(types) == 7) { // 物料风险辨识
                    dangerId = "7";
                    type = 4;
                } else if (Integer.parseInt(types) == 8) { // 高危作业辨识
                    dangerId = "8";
                    type = 5;
                }
                List<ZzjgDepartment> zzjgDepartment = zzjgDepartmentMapper.selectDangerIds(user.getId(), dangerId);
                List<ZzjgDepartment> zzjgDepartments = zzjgDepartmentMapper.selectOneName(user.getId(), dangerId, Integer.parseInt(id));
                if (null != zzjgDepartment && zzjgDepartment.size() != 0) {
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < zzjgDepartment.size(); i++) {
                        if (i == zzjgDepartment.size() - 1) {
                            sb.append(zzjgDepartment.get(i).getId());
                        } else {
                            sb.append(zzjgDepartment.get(i).getId()).append(",");
                        }
                    }
                    if (sb.toString().length() != 0) {
                        zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(), type, sb.toString());
                        zzjg1 = this.zzjgDepartmentMapper.selectAllName(user.getId(), type, sb.toString());
                        if (null == id) {
                            zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(), type, sb.toString());
                        } else {
                            zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(), type, id);
                        }
                    }
                }
                Integer indus = 0;
                if (tradeClique.getIndustry().contains("化工")) {
                    indus = 1;
                } else {
                    indus = 2;
                }
                model.addAttribute("count1", count1);
                if (zzjgDepartments.size() > 0) {
                    model.addAttribute("parName", zzjgDepartments.get(0).getName());
                }
                model.addAttribute("indus", indus);
                model.addAttribute("zzjgDep1", zzjgDepartment);
                model.addAttribute("zzjg", zzjg);
                model.addAttribute("type", type);
                return "company/safety-system/risk-list1";
            }
        }
    }

    /**
     * 行业端——集团企业——主要设备设施——页面
     */
    @RequestMapping("product/mequipment-list")
    public String mainEquipmentList(Model model, HttpServletRequest request, String equipmentName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        //setUserId(user, m);
        m.put("tradeId", user.getId());
        List<Integer> userId = null;
        userId = companyMapper.selectByCompany_trade(m);
        userId.add(user.getId());
        m.put("userIds", StringUtils.join(userId, ","));
        //log.error(m.toString());

        m.put("equipmentName", equipmentName);
        model.addAttribute("list", mequipmentMapper.selectByUserIdClique(m));
        model.addAttribute("equipmentName", equipmentName);
        return "steel/product/mequipment-list";
    }

    /**
     * 主要设备
     *
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "importMequipmentExcel", produces = "text/html;charset=utf-8")
    public void importMequipmentExcel(Model model, HttpServletResponse response, @RequestParam MultipartFile file, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        exportService.ImportMequipmentSave(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }

    /**
     * 产品
     *
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "importProductExcel", produces = "text/html;charset=utf-8")
    public void importtProductExcel(Model model, HttpServletResponse response, @RequestParam MultipartFile file, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        exportService.ImportProductSave(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }

    /**
     * 原辅材料
     *
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "importMarerialExcel", produces = "text/html;charset=utf-8")
    public void importMarerialExcel(Model model, HttpServletResponse response, @RequestParam MultipartFile file, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        exportService.ImportMaterialSave(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }

    /**
     * 为response提供Json格式的返回数据
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
     * 隐患数据分析
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list6")
    public String selectCompanyByCqlib6(Model model, HttpServletRequest request) {
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list6";
    }

    /**
     * 治理数据分析
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list7")
    public String selectCompanyByCqlib7(Model model, HttpServletRequest request) {
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list7";
    }

    @RequestMapping(value = "company-list8", method = RequestMethod.GET)
    public @ResponseBody
    Result selectCompanyByCqlib8(Model model, HttpServletRequest request) {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        List<Map<String, Object>> list2 = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", "总部端");
        list2.add(map);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        list2.addAll(list1);
        result.setMap("list", list2);
        return result;
    }

    /***
     * 查询具体企业的标准化
     * @param parentId
     * @param flag
     * @param request
     * @param model
     * @param sort
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String findAll(Integer parentId, Integer flag, HttpServletRequest request, Model model, Integer sort, Integer userId) {
        User user = getLoginUser(request);
        if (null == sort) {
            sort = 1;
        }
        HashMap<String, Object> map = new HashMap<>();

        map.put("userId", user.getId());
        map.put("parentId", parentId);
        map.put("flag", flag);

        List<TSafetyStandard> TSafetyStandardlist = tSafetyStandardMapper.findAll(map);

        // 判断是否有顺序,有书序就按照顺序来,没有就是倒序
        model.addAttribute("sort", sort);
        model.addAttribute("companyName", companyMapper.selectByPrimaryKey(userId).getName());
        model.addAttribute("list", TSafetyStandardlist);
        return "company/tables/tab-biaozhun2";
    }

    /**
     * TODO 隐患排查治理板块 => 检查设置实施中首页表显示车间
     * 是根据conpanyManual这张表中的数据车间数据进行查询
     * 跳转到新一轮的页面进行修改
     *
     * @param request 请求
     * @param model   存储域
     * @param flag    检查方式 1. 企业自查  2 行政检查 3 部门抽查
     * @param status
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "model-list-main")
    public String modelListMain(HttpServletRequest request, Model model, Integer flag, Integer status, Integer plan, Integer userId) throws ParseException {
        User user = getLoginUser(request);

        Map<String, Object> map1 = new LinkedHashMap<String, Object>();
        map1.put("level1", user.getUserName());

        List<Map<String, Object>> jiChuItem = new ArrayList<>();
        jiChuItem.add(map1);
        jiChuItem.addAll(aCompanyManualMapper.findJiChuItem(user.getId(), "基础管理"));

        Map<String, Object> map2 = new LinkedHashMap<String, Object>();
        map2.put("level1", user.getUserName());
        List<Map<String, Object>> XianChangItem = new ArrayList<>();
        XianChangItem.add(map2);
        XianChangItem.addAll(aCompanyManualMapper.findJiChuItem(user.getId(), "现场管理"));

        for (Map<String, Object> XianChangMap : XianChangItem) {
            Map<Integer, Integer> Xianmap = new LinkedHashMap<Integer, Integer>();
            Xianmap.put(5, 0);
            Xianmap.put(1, 0);
            Xianmap.put(2, 0);
            /*Xianmap.put(3, 0);*/
            Xianmap.put(4, 0);
            String level1 = (String) XianChangMap.get("level1");
            List<Integer> Xiantypes = tModelMapper.selecttype(level1, user.getId(), 2, flag);
            for (Integer integer : Xiantypes) {
                Xianmap.put(integer, 1);
            }
            XianChangMap.put("array", Xianmap);
            Integer count = tModelMapper.selectDangerCountByDepartAndUserId(level1, user.getId(), 2);
            if (null == count) {
                XianChangMap.put("count", 0);
            } else {
                XianChangMap.put("count", count);
            }

        }

        for (Map<String, Object> jiChuMap : jiChuItem) {
            Map<Integer, Integer> Jimap = new LinkedHashMap<Integer, Integer>();
            Jimap.put(5, 0);
            Jimap.put(1, 0);
            Jimap.put(2, 0);
            /*Jimap.put(3, 0);*/
            Jimap.put(4, 0);
            String level1 = (String) jiChuMap.get("level1");
            List<Integer> Jitypes = tModelMapper.selecttype(level1, user.getId(), 1, flag);
            for (Integer integer : Jitypes) {
                Jimap.put(integer, 1);
            }
            Integer count = tModelMapper.selectDangerCountByDepartAndUserId(level1, user.getId(), 1);
            if (null == count) {
                jiChuMap.put("count", 0);
            } else {
                jiChuMap.put("count", count);
            }

            jiChuMap.put("array", Jimap);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> list = zzjgDepartmentMapper.selectHiddenPlan(user.getId());
        List<Map<String, Object>> list1 = hiddenPlanMapper.selectCountAll(user.getId());

        List<Map<String, Object>> hiddenPlanList = hiddenPlanMapper.findDpid(0, user.getId());

        if (hiddenPlanList.size() == 0) {
            HiddenPlan hiddenPlan = new HiddenPlan();
            hiddenPlan.setUid(user.getId());
            hiddenPlan.setDpid(0);
            hiddenPlan.setUpdate_time(new Date());
            hiddenPlan.setCreate_time(new Date());
            hiddenPlan.setSyn_month(0);
            hiddenPlan.setSyn_year(0);
            hiddenPlan.setSyn_ratio("0:0");
            hiddenPlan.setEve_month(0);
            hiddenPlan.setEve_year(0);
            hiddenPlan.setEve_ratio("0:0");
            hiddenPlan.setReg_month(0);
            hiddenPlan.setReg_year(0);
            hiddenPlan.setReg_ratio("0:0");
            hiddenPlan.setSea_month(0);
            hiddenPlan.setSea_year(0);
            hiddenPlan.setSea_ratio("0:0");
            hiddenPlan.setEls_month(0);
            hiddenPlan.setEls_year(0);
            hiddenPlan.setEls_ratio("0:0");
            hiddenPlan.setBas_month(0);
            hiddenPlan.setBas_year(0);
            hiddenPlan.setBas_ratio("0:0");
            hiddenPlan.setTotal_count(0);
            hiddenPlan.setTotal_ratio("0:0");

            Integer a = hiddenPlanMapper.insert(hiddenPlan);

            List<Map<String, Object>> hiddenPlanList1 = hiddenPlanMapper.findDpid(0, user.getId());

            model.addAttribute("hiddenPlanList", hiddenPlanList1);
        } else {

            model.addAttribute("hiddenPlanList", hiddenPlanList);
        }
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        model.addAttribute("data", sdf.format(new Date()));
        model.addAttribute("year",  year);
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("jiChuItem", jiChuItem);
        model.addAttribute("xianChangItem", XianChangItem);
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("plan", plan);
        model.addAttribute("list1", list1);
        model.addAttribute("name", "合计");
        return "steel/check/model-list-main1";

    }


    @RequestMapping(value = "model-list-cx2")
    public String modelListCX2(Integer flag, Integer type, Integer template, HttpServletRequest request, Model model, Integer status) {

        User user = getLoginUser(request);
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("flag", flag);
        model.addAttribute("template", template);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);

        status = status == null ? 2 : status;
        m.put("status", status);
        if (setUserId(user, m)) {
            m.remove("tradeId");
            m.put("userId", user.getId());
            m.put("userIds", user.getId());
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);

            model.addAttribute("list", list);

            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));
            }
            model.addAttribute("sum", sum);
        }
        return "steel/danger/model-list-cx";
    }

    /**
     * 检查历史
     * TODO 排查治理记录 隐患排查记录(只需要已经检查过的,没有不合格记录的)
     * user. userType : 管理类型  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     */
    @RequestMapping(value = "check-list")//flag:3 部门抽查
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer townId, Integer villageId,
                               Integer status, Integer flag, Model model, String dmName, Integer button) throws Exception {
        User user=getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId()).append(",");
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        //默认是隐患排查记录
        if(null == button){
            button = 1;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        //信息汇总
        Integer number1 = tCheckMapper.findDataCountsByCliqu(sb.toString(), flag);
        Integer number2 = tCheckMapper.findDataCountSumByCliqu(sb.toString(), 1, flag);
        Integer number3 = tCheckMapper.findDataCountSumByCliqu(sb.toString(), 2, flag);
        Integer number4 = tCheckMapper.findDataCountSumByCliqu(sb.toString(), 3, flag);
        if (null == number1 || number1 == 0) {
            model.addAttribute("sum4", 0); // 总条数
        } else if (null != number1 || number1 != 0) {
            model.addAttribute("sum4", number1); // 总条数
        }

        if (null == number2 || number2 == 0) {
            model.addAttribute("sum1", 0); // 合格数据
        } else if (null != number2 || number2 != 0) {
            model.addAttribute("sum1", number2); // 合格数据
        }

        if (null == number3 || number3 == 0) {
            model.addAttribute("sum2", 0); // 不合格数据
        } else if (null != number3 || number3 != 0) {
            model.addAttribute("sum2", number3); // 不合格数据
        }

        if (null == number4 || number4 == 0) {
            model.addAttribute("sum3", 0); // 复查数据
        } else if (null != number4 || number4 != 0) {
            model.addAttribute("sum3", number4); // 复查数据
        }


        // 向map集合进行存储
        m.put("type", type);  //
        m.put("flag", flag);  // 1
        m.put("title", title); // 1
        m.put("townId", townId);   // null
        m.put("villageId", villageId);  //1
        m.put("companyName", companyName); // null
        m.put("status", status); //状态  null
        Set set = new LinkedHashSet();
        if (Objects.equals("", dmName)) {

        } else {
            m.put("dmName", dmName);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        // 进行判断
        if (setUserId(user, m)) {
            m.remove("tradeId");
            m.put("userIds", sb.toString());
            clearVillageTown(m);
            if(1 == button) {
                list = tCheckMapper.selectList(m);
                System.out.println(list);
                //List<Map<String, Object>> list = tCheckMapper.selectList3(m);
            }else{
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String startTime = df.format(new Date().getTime()-15*24*60*60*1000);

                Date startTime1 = df.parse(startTime);
                Date endTime = new Date();
                m.put("startTime",startTime);
                m.put("endTime",endTime);
                m.put("type",2);
                m.put("status",1);
                list = tCheckMapper.findSelectList(m);
            }
            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {
                DynamicParameter<String, Object> id = tCheckMapper.selectCompany((Integer) list.get(i).get("id"));
                list.get(i).put("listM", id);
                sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));

            }
            model.addAttribute("sum", sum);
            model.addAttribute("list", list);


            m.put("dmName", null);
            List<Map<String, Object>> list2 = tCheckMapper.selectList(m);
            for (int i = 0; i < list2.size(); i++) {
                set.add(list2.get(i).get("depart"));
            }

        }
        model.addAttribute("set", set);
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        model.addAttribute("dmName", dmName);
        model.addAttribute("button", button);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        //model.addAttribute("userId", userId);
        if (user.getUserType() == 5) {
            // 表示等于5的话就将页面进行跳转
            return "steel/check/check-list";
        }

        if(2== button){
            return "steel/check/check-list2";
        }
        // TODO 找到这个界面
        return "steel/check/check-list";
    }

    /**
     * TODO 隐患治理记录, 整改不合格的 新建立的页面
     * 有三种情况,企业自查 行政检查  部门抽查
     *
     * @param request 请求
     * @param flag    方式
     * @param status  状态
     * @return
     */
    @RequestMapping(value = "hidden-danger-list")
    public String hiddenDangerList(HttpServletRequest request, Model model, Integer flag, Integer status, Integer userId, Integer breaken) {
        User user=getLoginUser(request);
        User user1;
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        ids.add(user.getId());
        List<Map> list3 = new ArrayList<>();
        Company company;

        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("userId", user.getId());
        if (null == breaken){
            breaken = 1;
        }else {
            breaken = 2;
        }
        List<Map> list = new ArrayList<>();
        if (flag == 1) {
            /*if(35346 == user.getId()){
                list = tCheckItemMapper.selectListBystatus2(user.getId(), flag, breaken);
            }else {
                list = tCheckItemMapper.selectListBystatus(user.getId(), flag, breaken);
            }*/
            for(Integer id:ids) {
                list = new ArrayList<>();
                user1 = userMapper.selectByPrimaryKey(id);
                company = companyMapper.selectByPrimaryKey(user1.getId());
                list = tCheckItemMapper.selectListBystatus2(user1.getId(), flag, breaken);
                for (Map map : list) {
                    Date realTime = (Date) map.get("realTime");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String format = sdf.format(realTime);
                    String level = (String) map.get("level");
                    if (null != level && "红色".equals(level)) {
                        map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                    }
                    map.put("realTimeStr", format);

                    if ("全公司".equals(map.get("depart"))) {
                        Integer checkId = (Integer) map.get("checkId");
                        map.put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
                        Integer industryType = (Integer) map.get("industryType");
                        if (null != industryType && 1 == industryType) {
                            map.put("level2", tLevelMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2());
                        } else if (null != map.get("industryType") && 2 == map.get("industryType")) {
                            map.put("level2", aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2());
                        }
                    }

                    if (null == map.get("fjgkfzr") || "".equals(map.get("fjgkfzr"))) {
                        String name = "";
                        TCheck tc = tCheckMapper.selectByPrimaryKey((Integer) map.get("checkId"));
                        // 表示没有被检查人员 根据部门名称获取这个部门的被检查人员然后随便抓一个
                        List<Integer> integers = tCheckItemMapper.selectLevelIdByCheckId((Integer) map.get("checkItemId"));
                        if (null != integers && integers.size() > 0) {
                            // 这里进行名称的获取,进行全部循环,获取数据的方式,在数据库中进行查询
                            List<String> list1 = new ArrayList<String>();
                            for (Integer integer : integers) {
                                if (null != integer) {
                                    ACompanyManual aCompanyManual = aCompanyManualMapper.selectByPrimaryKey(integer);
                                    if (null != aCompanyManual && null != aCompanyManual.getFjgkfzr()) {
                                        list1.add(aCompanyManual.getFjgkfzr());
                                    }
                                }
                            }
                            if (list1.size() == 0) {
                                name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                                tc.setCheckCompany(name);
                            } else {
                                name = list1.get(0);
                                tc.setCheckCompany(name);
                            }
                        } else {
                            name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                            tc.setCheckCompany(name);
                        }
                        map.put("fjgkfzr", name);
                    }

                }
                list3.addAll(list);
            }
        } else if (flag == 2) {
            for(Integer id:ids) {
                list = new ArrayList<>();
                user1 = userMapper.selectByPrimaryKey(id);
                company = companyMapper.selectByPrimaryKey(user1.getId());
                // 表示的是行政检查
                list = tCheckItemMapper.selectXZListBystatus(user1.getId(), flag, breaken);
                for (Map map : list) {
                    Date realTime = (Date) map.get("realTime");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String format = sdf.format(realTime);
                    String levelId = "";
                    if (null != map.get("industryType") && 1 == map.get("industryType")) {
                        Integer levelId1 = (Integer) map.get("levelId");
                        if (null != levelId1) {
                            levelId = tLevelMapper.selectByPrimaryKey(levelId1).getLevel2();
                        }

                    } else if (null != map.get("industryType") && 2 == map.get("industryType")) {
                        levelId = aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2();
                    }

                    if (StringUtils.isBlank(levelId)) {
                        map.put("level2", levelId);
                    }

                    map.put("realTimeStr", format);
                    map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                    // 获取
                }
                list3.addAll(list);
            }
        } else if (flag == 3) {
            // 表示的是部门抽查
            for(Integer id:ids) {
                list = new ArrayList<>();
                user1 = userMapper.selectByPrimaryKey(id);
                company = companyMapper.selectByPrimaryKey(user1.getId());
                list = tCheckItemMapper.selectBMCCListBystatus(user1.getId(), flag, breaken);
                for (Map map : list) {
                    Date realTime = (Date) map.get("realTime");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String format = sdf.format(realTime);
                    //  String level = (String) map.get("level");
                    if (null != map.get("industryType") && 1 == map.get("industryType")) {
                        map.put("level2", tLevelMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2());
                    } else if (null != map.get("industryType") && 2 == map.get("industryType")) {
                        map.put("level2", aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2());
                    }
                    map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                    map.put("realTimeStr", format);
                }
                list3.addAll(list);
            }
        }
        model.addAttribute("breaken", breaken);
        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("host", host);
        model.addAttribute("list", list3);
        if(2==breaken){
            return "steel/check/hidden-danger-list2";
        }
        //model.addAttribute("userId", user.getId());
        return "steel/check/hidden-danger-list";
    }
    /**
     * TODO 隐患治理记录, 整改不合格的 新建立的页面
     * 有三种情况,企业自查 行政检查  部门抽查
     *
     * @param request 请求
     * @param flag    方式
     * @param status  状态
     * @return
     */
    @RequestMapping(value = "hidden-danger-list2")
    public String hiddenDangerList2(HttpServletRequest request, Model model, Integer flag, Integer status, Integer userId, Integer breaken) {
        User user;
        if (null == userId) {
            user = getLoginUser(request);
            model.addAttribute("userId", user.getId());
        } else {
            user = userMapper.selectByPrimaryKey(userId);
            model.addAttribute("userId", userId);
        }
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("userId", user.getId());
        if (null == breaken){
            breaken = 1;
        }else {
            breaken = 2;
        }
        List<Map> list = new ArrayList<>();
        if (flag == 1) {
            /*if(35346 == user.getId()){
                list = tCheckItemMapper.selectListBystatus2(user.getId(), flag, breaken);
            }else {
                list = tCheckItemMapper.selectListBystatus(user.getId(), flag, breaken);
            }*/
            list = tCheckItemMapper.selectListBystatus2(user.getId(), flag, breaken);
            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
                String level = (String) map.get("level");
                if (null != level && "红色".equals(level)) {
                    map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                }
                map.put("realTimeStr", format);

                if ("全公司".equals(map.get("depart"))) {
                    Integer checkId = (Integer) map.get("checkId");
                    map.put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
                    Integer industryType = (Integer) map.get("industryType");
                    if (null != industryType && 1 == industryType) {
                        map.put("level2", tLevelMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2());
                    } else if (null != map.get("industryType") && 2 == map.get("industryType")) {
                        map.put("level2", aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2());
                    }
                }

                if (null == map.get("fjgkfzr") || "".equals(map.get("fjgkfzr"))) {
                    String name = "";
                    TCheck tc = tCheckMapper.selectByPrimaryKey((Integer) map.get("checkId"));
                    // 表示没有被检查人员 根据部门名称获取这个部门的被检查人员然后随便抓一个
                    List<Integer> integers = tCheckItemMapper.selectLevelIdByCheckId((Integer) map.get("checkItemId"));
                    if (null != integers && integers.size() > 0) {
                        // 这里进行名称的获取,进行全部循环,获取数据的方式,在数据库中进行查询
                        List<String> list1 = new ArrayList<String>();
                        for (Integer integer : integers) {
                            if (null != integer) {
                                ACompanyManual aCompanyManual = aCompanyManualMapper.selectByPrimaryKey(integer);
                                if (null != aCompanyManual && null != aCompanyManual.getFjgkfzr()) {
                                    list1.add(aCompanyManual.getFjgkfzr());
                                }
                            }
                        }
                        if (list1.size() == 0) {
                            name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                            tc.setCheckCompany(name);
                        } else {
                            name = list1.get(0);
                            tc.setCheckCompany(name);
                        }
                    } else {
                        name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                        tc.setCheckCompany(name);
                    }
                    map.put("fjgkfzr", name);
                }
            }
        } else if (flag == 2) {
            // 表示的是行政检查
            list = tCheckItemMapper.selectXZListBystatus(user.getId(), flag, breaken);
            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
                String levelId = "";
                if (null != map.get("industryType") && 1 == map.get("industryType")) {
                    Integer levelId1 = (Integer) map.get("levelId");
                    if (null != levelId1) {
                        levelId = tLevelMapper.selectByPrimaryKey(levelId1).getLevel2();
                    }

                } else if (null != map.get("industryType") && 2 == map.get("industryType")) {
                    levelId = aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2();
                }

                if (StringUtils.isBlank(levelId)) {
                    map.put("level2", levelId);
                }

                map.put("realTimeStr", format);
                map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                // 获取
            }
        } else if (flag == 3) {
            // 表示的是部门抽查
            list = tCheckItemMapper.selectBMCCListBystatus(user.getId(), flag, breaken);
            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
                //  String level = (String) map.get("level");
                if (null != map.get("industryType") && 1 == map.get("industryType")) {
                    map.put("level2", tLevelMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2());
                } else if (null != map.get("industryType") && 2 == map.get("industryType")) {
                    map.put("level2", aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2());
                }
                map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                map.put("realTimeStr", format);
            }
        }
        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("host", host);
        model.addAttribute("list", list);
        //model.addAttribute("userId", user.getId());
        return "steel/check/hidden-danger-list";
    }

    /**
     * create by  : 小明！！！
     * description: TODO  排查数据分析 柱状图
     * create time: 2019/8/29 16:35
     */
    @RequestMapping(value = "pc-analysis")
    public String pcAnalysis(HttpServletRequest request, Model model) {

        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        List<Map<String, Object>> branchs = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        String[] companyNames = new String[branchs.size()];
        DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }
        //获取各个分公司名称
        for (int i = 0; i < branchs.size(); i++) {
            companyNames[i] = (String) branchs.get(i).get("user_name");
        }

        // Integer a = tCheckMapper.zfPerformanceCount(flag,"化工",sb.toString()); // 化工行业 数据
        Integer a = tCheckMapper.zfPerformanceCount3((Integer) branchs.get(0).get("user_id"));
        model.addAttribute("danger1", a);

        //Integer  b = tCheckMapper.zfPerformanceCount(flag,"冶金",sb.toString()); // 冶金行业 数据
        Integer b = tCheckMapper.zfPerformanceCount3((Integer) branchs.get(1).get("user_id"));
        model.addAttribute("danger2", b);

        //Integer  c = tCheckMapper.zfPerformanceCount(flag,"有色",sb.toString()); // 有色行业 数据
        Integer c = tCheckMapper.zfPerformanceCount3((Integer) branchs.get(2).get("user_id"));
        model.addAttribute("danger3", c);

        //Integer  d = tCheckMapper.zfPerformanceCount(flag,"建材",sb.toString()); // 建材行业 数据
        Integer d = tCheckMapper.zfPerformanceCount3((Integer) branchs.get(3).get("user_id"));
        model.addAttribute("danger4", d);

        //Integer  e = tCheckMapper.zfPerformanceCount(flag,"机械",sb.toString()); // 机械行业 数据
        Integer e = tCheckMapper.zfPerformanceCount3((Integer) branchs.get(4).get("user_ids"));
        model.addAttribute("danger5", e);

        //Integer  f = tCheckMapper.zfPerformanceCount(flag,"轻工",sb.toString()); // 轻工行业 数据
        Integer f = tCheckMapper.zfPerformanceCount3((Integer) branchs.get(5).get("user_id"));
        model.addAttribute("danger6", f);

        //Integer  g = tCheckMapper.zfPerformanceCount(flag,"纺织",sb.toString()); // 纺织行业 数据
        Integer g = tCheckMapper.zfPerformanceCount3((Integer) branchs.get(6).get("user_id"));
        model.addAttribute("danger7", g);

        //Integer  h = tCheckMapper.zfPerformanceCount(flag,"商贸",sb.toString()); // 商贸行业 数据
        Integer h = tCheckMapper.zfPerformanceCount3((Integer) branchs.get(7).get("user_id"));
        model.addAttribute("danger8", h);

//        Integer  i1 = tCheckMapper.zfPerformanceCount(flag,"烟花",sb.toString()); // 烟花行业 数据
//        model.addAttribute("danger9",i1);
//
//        Integer  j = tCheckMapper.zfPerformanceCount11(flag,sb.toString()); // 其他行业 数据
//        model.addAttribute("danger10",j);

        Integer count = a + b + c + d + e + f + g + h;

        model.addAttribute("count", count);

        if (null != count && 0 != count) {

            if (null != a && 0 != a) {
                String str = df.format((float) a / count);
                model.addAttribute("result1", str);

            } else {
                model.addAttribute("result1", "0.00");  // 化工 占比数据
            }

            if (null != b && 0 != b) {
                String str = df.format((float) b / count);
                model.addAttribute("result2", str);

            } else {
                model.addAttribute("result2", "0.00"); // 冶金 占比数据
            }

            if (null != c && 0 != c) {
                String str = df.format((float) c / count);
                model.addAttribute("result3", str);

            } else {
                model.addAttribute("result3", "0.00");  // 有色 占比数据 竖
            }

            if (null != d && 0 != d) {
                String str = df.format((float) d / count);
                model.addAttribute("result4", str);

            } else {
                model.addAttribute("result4", "0.00"); // 建材 占比数据
            }

            if (null != e && 0 != e) {
                String str = df.format((float) e / count);
                model.addAttribute("result5", str);

            } else {
                model.addAttribute("result5", "0.00"); // 机械 占比数据
            }

            if (null != f && 0 != f) {
                String str = df.format((float) f / count);
                model.addAttribute("result6", str);

            } else {
                model.addAttribute("result6", "0.00"); // 轻工 占比数据
            }

            if (null != g && 0 != g) {
                String str = df.format((float) g / count);
                model.addAttribute("result7", str);

            } else {
                model.addAttribute("result7", "0.00"); // 纺织 占比数据
            }

            if (null != h && 0 != h) {
                String str = df.format((float) h / count);
                model.addAttribute("result8", str);

            } else {
                model.addAttribute("result8", "0.00");// 商贸 占比数据
            }

            /*if (null != i1 && 0 != i1) {
                String str = df.format((float)i1 / count);
                model.addAttribute("result9",str);

            } else {
                model.addAttribute("result9","0.00"); // 烟花 占比数据
            }

            if (null != j && 0 != j) {
                String str = df.format((float)j / count);
                model.addAttribute("result10",str);

            } else {
                model.addAttribute("result10","0.00"); // 其他 占比数据
            }*/

        } else {
            // 占比 坚
            model.addAttribute("result1", "0.00");
            model.addAttribute("result2", "0.00");
            model.addAttribute("result3", "0.00");
            model.addAttribute("result4", "0.00");
            model.addAttribute("result5", "0.00");
            model.addAttribute("result6", "0.00");
            model.addAttribute("result7", "0.00");
            model.addAttribute("result8", "0.00");
            model.addAttribute("result9", "0.00");
            model.addAttribute("result10", "0.00");
        }


        Integer number1 = tCheckItemMapper.zfHiddenTrouble2("设计总图", sb.toString()); // 基础管理 隐患数据
        model.addAttribute("number1", number1);

        Integer number2 = tCheckItemMapper.zfHiddenTrouble2("试生产", sb.toString()); // 生产工艺 隐患数据
        model.addAttribute("number2", number2);

        Integer number3 = tCheckItemMapper.zfHiddenTrouble2("装置运行", sb.toString()); // 设备设施 隐患数据
        model.addAttribute("number3", number3);

        Integer number4 = tCheckItemMapper.zfHiddenTrouble2("设备安全", sb.toString()); // 公用工程 隐患数据
        model.addAttribute("number4", number4);

        Integer number5 = tCheckItemMapper.zfHiddenTrouble2("仪表安全", sb.toString()); // 特种设备 隐患数据
        model.addAttribute("number5", number5);

        Integer number6 = tCheckItemMapper.zfHiddenTrouble2("电气安全", sb.toString()); // 生产现场 隐患数据
        model.addAttribute("number6", number6);

        Integer number7 = tCheckItemMapper.zfHiddenTrouble2("应急消防", sb.toString()); // 行为环境 隐患数据
        model.addAttribute("number7", number7);

        Integer number8 = tCheckItemMapper.zfHiddenTrouble2("特殊管控", sb.toString()); // 危化管理 隐患数据
        model.addAttribute("number8", number8);

        Integer number9 = tCheckItemMapper.zfHiddenTrouble2("行为环境", sb.toString()); // 消防安全 隐患数据
        model.addAttribute("number9", number9);

        Integer number10 = tCheckItemMapper.zfHiddenTrouble2("生产现场", sb.toString()); // 用电安全 隐患数据
        model.addAttribute("number10", number10);

        Integer number11 = tCheckItemMapper.zfHiddenTrouble2("公辅工程", sb.toString()); // 安全设施 隐患数据
        model.addAttribute("number11", number11);

        Integer number12 = tCheckItemMapper.zfHiddenTrouble2("特种设备", sb.toString()); // 防雷静电 隐患数据
        model.addAttribute("number12", number12);

        Integer number13 = tCheckItemMapper.zfHiddenTrouble2("专项行业", sb.toString()); // 职业卫生 隐患数据
        model.addAttribute("number13", number13);

        Integer number14 = tCheckItemMapper.zfHiddenTrouble2("生产工艺", sb.toString()); // 职业卫生 隐患数据
        model.addAttribute("number14", number14);

        Integer number15 = tCheckItemMapper.zfHiddenTrouble2("设备设施", sb.toString()); // 其他 隐患数据
        model.addAttribute("number15", number15);

        Integer number16 = tCheckItemMapper.zfHiddenTrouble2("危化管理", sb.toString()); // 其他 隐患数据
        model.addAttribute("number16", number16);

        Integer number17 = tCheckItemMapper.zfHiddenTrouble2("安全设施", sb.toString()); // 其他 隐患数据
        model.addAttribute("number17", number17);

        Integer number18 = tCheckItemMapper.zfHiddenTrouble2("其他", sb.toString()); // 其他 隐患数据
        model.addAttribute("number18", number18);

        Integer number19 = tCheckItemMapper.zfHiddenTrouble2("基础管理", sb.toString()); // 其他 隐患数据
        model.addAttribute("number19", number19);


        Integer count1 = number1 + number2 + number3 + number4 + number5 + number6 + number7 + number8 + number9 + number10 + number11 + number12 + number13 + number14 + number15 + number16 + number17 + number18 + number19;

        model.addAttribute("count1", count1);

        if (null != count1 && 0 != count1) {

            if (null != number1 && 0 != number1) {
                String str = df.format((float) number1 / count1);
                model.addAttribute("double1", str);

            } else {
                model.addAttribute("double1", "0.00");  // 化工 占比数据
            }

            if (null != number2 && 0 != number2) {
                String str = df.format((float) number2 / count1);
                model.addAttribute("double2", str);

            } else {
                model.addAttribute("double2", "0.00"); // 冶金 占比数据
            }

            if (null != number3 && 0 != number3) {
                String str = df.format((float) number3 / count1);
                model.addAttribute("double3", str);

            } else {
                model.addAttribute("double3", "0.00");  // 有色 占比数据 竖
            }

            if (null != number4 && 0 != number4) {
                String str = df.format((float) number4 / count1);
                model.addAttribute("double4", str);

            } else {
                model.addAttribute("double4", "0.00"); // 建材 占比数据
            }

            if (null != number5 && 0 != number5) {
                String str = df.format((float) number5 / count1);
                model.addAttribute("double5", str);

            } else {
                model.addAttribute("double5", "0.00"); // 机械 占比数据
            }

            if (null != number6 && 0 != number6) {
                String str = df.format((float) number6 / count1);
                model.addAttribute("double6", str);

            } else {
                model.addAttribute("double6", "0.00"); // 轻工 占比数据
            }

            if (null != number7 && 0 != number7) {
                String str = df.format((float) number7 / count1);
                model.addAttribute("double7", str);

            } else {
                model.addAttribute("double7", "0.00"); // 纺织 占比数据
            }

            if (null != number8 && 0 != number8) {
                String str = df.format((float) number8 / count1);
                model.addAttribute("double8", str);

            } else {
                model.addAttribute("double8", "0.00");// 商贸 占比数据
            }

            if (null != number9 && 0 != number9) {
                String str = df.format((float) number9 / count1);
                model.addAttribute("double9", str);

            } else {
                model.addAttribute("double9", "0.00"); // 烟花 占比数据
            }

            if (null != number10 && 0 != number10) {
                String str = df.format((float) number10 / count1);
                model.addAttribute("double10", str);

            } else {
                model.addAttribute("double10", "0.00"); // 其他 占比数据
            }

            if (null != number11 && 0 != number11) {
                String str = df.format((float) number11 / count1);
                model.addAttribute("double11", str);

            } else {
                model.addAttribute("double11", "0.00"); // 其他 占比数据
            }

            if (null != number12 && 0 != number12) {
                String str = df.format((float) number12 / count1);
                model.addAttribute("double12", str);

            } else {
                model.addAttribute("double12", "0.00"); // 其他 占比数据
            }

            if (null != number13 && 0 != number13) {
                String str = df.format((float) number13 / count1);
                model.addAttribute("double13", str);

            } else {
                model.addAttribute("double13", "0.00"); // 其他 占比数据
            }

            if (null != number14 && 0 != number14) {
                String str = df.format((float) number14 / count1);
                model.addAttribute("double14", str);

            } else {
                model.addAttribute("double14", "0.00"); // 其他 占比数据
            }

            if (null != number15 && 0 != number15) {
                String str = df.format((float) number15 / count1);
                model.addAttribute("double15", str);

            } else {
                model.addAttribute("double15", "0.00"); // 其他 占比数据
            }

            if (null != number16 && 0 != number16) {
                String str = df.format((float) number16 / count1);
                model.addAttribute("double16", str);

            } else {
                model.addAttribute("double16", "0.00"); // 其他 占比数据
            }

            if (null != number17 && 0 != number17) {
                String str = df.format((float) number17 / count1);
                model.addAttribute("double17", str);

            } else {
                model.addAttribute("double17", "0.00"); // 其他 占比数据
            }

            if (null != number18 && 0 != number18) {
                String str = df.format((float) number18 / count1);
                model.addAttribute("double18", str);

            } else {
                model.addAttribute("double18", "0.00"); // 其他 占比数据
            }

            if (null != number19 && 0 != number19) {
                String str = df.format((float) number19 / count1);
                model.addAttribute("double19", str);

            } else {
                model.addAttribute("double19", "0.00"); // 其他 占比数据
            }

        } else {
            // 占比 坚
            model.addAttribute("double1", "0.00");
            model.addAttribute("double2", "0.00");
            model.addAttribute("double3", "0.00");
            model.addAttribute("double4", "0.00");
            model.addAttribute("double5", "0.00");
            model.addAttribute("double6", "0.00");
            model.addAttribute("double7", "0.00");
            model.addAttribute("double8", "0.00");
            model.addAttribute("double9", "0.00");
            model.addAttribute("double10", "0.00");
            model.addAttribute("double11", "0.00");
            model.addAttribute("double12", "0.00");
            model.addAttribute("double13", "0.00");
            model.addAttribute("double14", "0.00");
            model.addAttribute("double15", "0.00");
            model.addAttribute("double16", "0.00");
            model.addAttribute("double17", "0.00");
            model.addAttribute("double18", "0.00");
            model.addAttribute("double19", "0.00");
        }
        Gson gson = new Gson();
        model.addAttribute("companyNames", gson.toJson(companyNames));
        return "steel/company/evaluate/analysis/pc-analysis";
    }

    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析
     * create time: 2019/8/13 14:34
     */
    @RequestMapping(value = "yh-all-analysis")
    public String yhAnalysis(HttpServletRequest request, Model model, Integer flag, Integer sign) {

        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        List<Map<String, Object>> branchs = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        String[] companyNames = new String[branchs.size()];
        DecimalFormat df = new DecimalFormat("0.00");

        if (null == flag) {
            flag = 1;
        }

        if (null == sign) {
            sign = 1;
        }

        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }

        //获取各个分公司名称
        for (int i = 0; i < branchs.size(); i++) {
            companyNames[i] = (String) branchs.get(i).get("user_name");
        }

        //Integer a = tCheckItemMapper.zhengFuChartDataDanger(flag,"化工",sb.toString()); // 化工行业 数据
        Integer a = tCheckItemMapper.zhengFuChartDataDanger3((Integer) branchs.get(0).get("user_id"));
        model.addAttribute("danger1", a);

        //Integer  b = tCheckItemMapper.zhengFuChartDataDanger(flag,"冶金",sb.toString()); // 冶金行业 数据
        Integer b = tCheckItemMapper.zhengFuChartDataDanger3((Integer) branchs.get(1).get("user_id"));
        model.addAttribute("danger2", b);

        //Integer  c = tCheckItemMapper.zhengFuChartDataDanger(flag,"有色",sb.toString()); // 有色行业 数据
        Integer c = tCheckItemMapper.zhengFuChartDataDanger3((Integer) branchs.get(2).get("user_id"));
        model.addAttribute("danger3", c);

        //Integer  d = tCheckItemMapper.zhengFuChartDataDanger(flag,"建材",sb.toString()); // 建材行业 数据
        Integer d = tCheckItemMapper.zhengFuChartDataDanger3((Integer) branchs.get(3).get("user_id"));
        model.addAttribute("danger4", d);

        //Integer  e = tCheckItemMapper.zhengFuChartDataDanger(flag,"机械",sb.toString()); // 机械行业 数据
        Integer e = tCheckItemMapper.zhengFuChartDataDanger3((Integer) branchs.get(4).get("user_id"));
        model.addAttribute("danger5", e);

        //Integer  f = tCheckItemMapper.zhengFuChartDataDanger(flag,"轻工",sb.toString()); // 轻工行业 数据
        Integer f = tCheckItemMapper.zhengFuChartDataDanger3((Integer) branchs.get(5).get("user_id"));
        model.addAttribute("danger6", f);

        //Integer  g = tCheckItemMapper.zhengFuChartDataDanger(flag,"纺织",sb.toString()); // 纺织行业 数据
        Integer g = tCheckItemMapper.zhengFuChartDataDanger3((Integer) branchs.get(6).get("user_id"));
        model.addAttribute("danger7", g);

        //Integer  h = tCheckItemMapper.zhengFuChartDataDanger(flag,"商贸",sb.toString()); // 商贸行业 数据
        Integer h = tCheckItemMapper.zhengFuChartDataDanger3((Integer) branchs.get(7).get("user_id"));
        model.addAttribute("danger8", h);

        /*Integer  i1 = tCheckItemMapper.zhengFuChartDataDanger(flag,"烟花",sb.toString()); // 烟花行业 数据
        model.addAttribute("danger9",i1);

        Integer  j = tCheckItemMapper.zhengFuChartDataDanger11(flag,sb.toString()); // 其他行业 数据
        model.addAttribute("danger10",j);*/

        //Integer count = a + b + c + d + e + f + g + h + i1 + j ;

        Integer count = a + b + c + d + e + f + g + h;

        model.addAttribute("count", count);

        if (null != count && 0 != count) {

            if (null != a && 0 != a) {
                String str = df.format((float) a / count);
                model.addAttribute("result1", str);

            } else {
                model.addAttribute("result1", "0.00");  // 化工 占比数据
            }

            if (null != b && 0 != b) {
                String str = df.format((float) b / count);
                model.addAttribute("result2", str);

            } else {
                model.addAttribute("result2", "0.00"); // 冶金 占比数据
            }

            if (null != c && 0 != c) {
                String str = df.format((float) c / count);
                model.addAttribute("result3", str);

            } else {
                model.addAttribute("result3", "0.00");  // 有色 占比数据 竖
            }

            if (null != d && 0 != d) {
                String str = df.format((float) d / count);
                model.addAttribute("result4", str);

            } else {
                model.addAttribute("result4", "0.00"); // 建材 占比数据
            }

            if (null != e && 0 != e) {
                String str = df.format((float) e / count);
                model.addAttribute("result5", str);

            } else {
                model.addAttribute("result5", "0.00"); // 机械 占比数据
            }

            if (null != f && 0 != f) {
                String str = df.format((float) f / count);
                model.addAttribute("result6", str);

            } else {
                model.addAttribute("result6", "0.00"); // 轻工 占比数据
            }

            if (null != g && 0 != g) {
                String str = df.format((float) g / count);
                model.addAttribute("result7", str);

            } else {
                model.addAttribute("result7", "0.00"); // 纺织 占比数据
            }

            if (null != h && 0 != h) {
                String str = df.format((float) h / count);
                model.addAttribute("result8", str);

            } else {
                model.addAttribute("result8", "0.00");// 商贸 占比数据
            }

            /*if (null != i1 && 0 != i1) {
                String str = df.format((float)i1 / count);
                model.addAttribute("result9",str);

            } else {
                model.addAttribute("result9","0.00"); // 烟花 占比数据
            }

            if (null != j && 0 != j) {
                String str = df.format((float)j / count);
                model.addAttribute("result10",str);

            } else {
                model.addAttribute("result10","0.00"); // 其他 占比数据
            }*/

        } else {
            // 占比 坚
            model.addAttribute("result1", "0.00");
            model.addAttribute("result2", "0.00");
            model.addAttribute("result3", "0.00");
            model.addAttribute("result4", "0.00");
            model.addAttribute("result5", "0.00");
            model.addAttribute("result6", "0.00");
            model.addAttribute("result7", "0.00");
            model.addAttribute("result8", "0.00");
            model.addAttribute("result9", "0.00");
            model.addAttribute("result10", "0.00");
        }

        Integer number1 = tCheckItemMapper.zhengFuChartData882("设计总图", sb.toString()); // 基础管理 隐患数据
        model.addAttribute("number1", number1);

        Integer number2 = tCheckItemMapper.zhengFuChartData882("试生产", sb.toString()); // 生产工艺 隐患数据
        model.addAttribute("number2", number2);

        Integer number3 = tCheckItemMapper.zhengFuChartData882("装置运行", sb.toString()); // 设备设施 隐患数据
        model.addAttribute("number3", number3);

        Integer number4 = tCheckItemMapper.zhengFuChartData882("设备安全", sb.toString()); // 公用工程 隐患数据
        model.addAttribute("number4", number4);

        Integer number5 = tCheckItemMapper.zhengFuChartData882("仪表安全", sb.toString()); // 特种设备 隐患数据
        model.addAttribute("number5", number5);

        Integer number6 = tCheckItemMapper.zhengFuChartData882("电气安全", sb.toString()); // 生产现场 隐患数据
        model.addAttribute("number6", number6);

        Integer number7 = tCheckItemMapper.zhengFuChartData882("应急消防", sb.toString()); // 行为环境 隐患数据
        model.addAttribute("number7", number7);

        Integer number8 = tCheckItemMapper.zhengFuChartData882("特殊管控", sb.toString()); // 危化管理 隐患数据
        model.addAttribute("number8", number8);

        Integer number9 = tCheckItemMapper.zhengFuChartData882("行为环境", sb.toString()); // 消防安全 隐患数据
        model.addAttribute("number9", number9);

        Integer number10 = tCheckItemMapper.zhengFuChartData882("生产现场", sb.toString()); // 用电安全 隐患数据
        model.addAttribute("number10", number10);

        Integer number11 = tCheckItemMapper.zhengFuChartData882("公辅工程", sb.toString()); // 安全设施 隐患数据
        model.addAttribute("number11", number11);

        Integer number12 = tCheckItemMapper.zhengFuChartData882("特种设备", sb.toString()); // 防雷静电 隐患数据
        model.addAttribute("number12", number12);

        Integer number13 = tCheckItemMapper.zhengFuChartData882("专项行业", sb.toString()); // 职业卫生 隐患数据
        model.addAttribute("number13", number13);

        Integer number14 = tCheckItemMapper.zhengFuChartData882("生产工艺", sb.toString()); // 职业卫生 隐患数据
        model.addAttribute("number14", number14);

        Integer number15 = tCheckItemMapper.zhengFuChartData882("设备设施", sb.toString()); // 其他 隐患数据
        model.addAttribute("number15", number15);

        Integer number16 = tCheckItemMapper.zhengFuChartData882("危化管理", sb.toString()); // 其他 隐患数据
        model.addAttribute("number16", number16);

        Integer number17 = tCheckItemMapper.zhengFuChartData882("安全设施", sb.toString()); // 其他 隐患数据
        model.addAttribute("number17", number17);

        Integer number18 = tCheckItemMapper.zhengFuChartData882("其他", sb.toString()); // 其他 隐患数据
        model.addAttribute("number18", number18);

        Integer number19 = tCheckItemMapper.zhengFuChartData882("基础管理", sb.toString()); // 其他 隐患数据
        model.addAttribute("number19", number19);

        Integer count1 = number1 + number2 + number3 + number4 + number5 + number6 + number7 + number8 + number9 + number10 + number11 + number12 + number13 + number14 + number15 + number16 + number17 + number18 + number19;

        model.addAttribute("count1", count1);

        if (null != count1 && 0 != count1) {

            if (null != number1 && 0 != number1) {
                String str = df.format((float) number1 / count1);
                model.addAttribute("double1", str);

            } else {
                model.addAttribute("double1", "0.00");  // 化工 占比数据
            }

            if (null != number2 && 0 != number2) {
                String str = df.format((float) number2 / count1);
                model.addAttribute("double2", str);

            } else {
                model.addAttribute("double2", "0.00"); // 冶金 占比数据
            }

            if (null != number3 && 0 != number3) {
                String str = df.format((float) number3 / count1);
                model.addAttribute("double3", str);

            } else {
                model.addAttribute("double3", "0.00");  // 有色 占比数据 竖
            }

            if (null != number4 && 0 != number4) {
                String str = df.format((float) number4 / count1);
                model.addAttribute("double4", str);

            } else {
                model.addAttribute("double4", "0.00"); // 建材 占比数据
            }

            if (null != number5 && 0 != number5) {
                String str = df.format((float) number5 / count1);
                model.addAttribute("double5", str);

            } else {
                model.addAttribute("double5", "0.00"); // 机械 占比数据
            }

            if (null != number6 && 0 != number6) {
                String str = df.format((float) number6 / count1);
                model.addAttribute("double6", str);

            } else {
                model.addAttribute("double6", "0.00"); // 轻工 占比数据
            }

            if (null != number7 && 0 != number7) {
                String str = df.format((float) number7 / count1);
                model.addAttribute("double7", str);

            } else {
                model.addAttribute("double7", "0.00"); // 纺织 占比数据
            }

            if (null != number8 && 0 != number8) {
                String str = df.format((float) number8 / count1);
                model.addAttribute("double8", str);

            } else {
                model.addAttribute("double8", "0.00");// 商贸 占比数据
            }

            if (null != number9 && 0 != number9) {
                String str = df.format((float) number9 / count1);
                model.addAttribute("double9", str);

            } else {
                model.addAttribute("double9", "0.00"); // 烟花 占比数据
            }

            if (null != number10 && 0 != number10) {
                String str = df.format((float) number10 / count1);
                model.addAttribute("double10", str);

            } else {
                model.addAttribute("double10", "0.00"); // 其他 占比数据
            }

            if (null != number11 && 0 != number11) {
                String str = df.format((float) number11 / count1);
                model.addAttribute("double11", str);

            } else {
                model.addAttribute("double11", "0.00"); // 其他 占比数据
            }

            if (null != number12 && 0 != number12) {
                String str = df.format((float) number12 / count1);
                model.addAttribute("double12", str);

            } else {
                model.addAttribute("double12", "0.00"); // 其他 占比数据
            }

            if (null != number13 && 0 != number13) {
                String str = df.format((float) number13 / count1);
                model.addAttribute("double13", str);

            } else {
                model.addAttribute("double13", "0.00"); // 其他 占比数据
            }

            if (null != number14 && 0 != number14) {
                String str = df.format((float) number14 / count1);
                model.addAttribute("double14", str);

            } else {
                model.addAttribute("double14", "0.00"); // 其他 占比数据
            }

            if (null != number15 && 0 != number15) {
                String str = df.format((float) number15 / count1);
                model.addAttribute("double15", str);

            } else {
                model.addAttribute("double15", "0.00"); // 其他 占比数据
            }

            if (null != number16 && 0 != number16) {
                String str = df.format((float) number16 / count1);
                model.addAttribute("double16", str);

            } else {
                model.addAttribute("double16", "0.00"); // 其他 占比数据
            }
            if (null != number17 && 0 != number17) {
                String str = df.format((float) number17 / count1);
                model.addAttribute("double17", str);

            } else {
                model.addAttribute("double17", "0.00"); // 其他 占比数据
            }
            if (null != number18 && 0 != number18) {
                String str = df.format((float) number18 / count1);
                model.addAttribute("double18", str);

            } else {
                model.addAttribute("double18", "0.00"); // 其他 占比数据
            }
            if (null != number19 && 0 != number19) {
                String str = df.format((float) number19 / count1);
                model.addAttribute("double19", str);

            } else {
                model.addAttribute("double19", "0.00"); // 其他 占比数据
            }

        } else {
            // 占比 坚
            model.addAttribute("double1", "0.00");
            model.addAttribute("double2", "0.00");
            model.addAttribute("double3", "0.00");
            model.addAttribute("double4", "0.00");
            model.addAttribute("double5", "0.00");
            model.addAttribute("double6", "0.00");
            model.addAttribute("double7", "0.00");
            model.addAttribute("double8", "0.00");
            model.addAttribute("double9", "0.00");
            model.addAttribute("double10", "0.00");
            model.addAttribute("double11", "0.00");
            model.addAttribute("double12", "0.00");
            model.addAttribute("double13", "0.00");
            model.addAttribute("double14", "0.00");
            model.addAttribute("double15", "0.00");
            model.addAttribute("double16", "0.00");
            model.addAttribute("double17", "0.00");
            model.addAttribute("double18", "0.00");
            model.addAttribute("double19", "0.00");
        }

        Gson gson = new Gson();
        model.addAttribute("companyNames", gson.toJson(companyNames));
        model.addAttribute("sign", sign);
        model.addAttribute("flag", flag);

        return "steel/company/evaluate/analysis/yh-analysis";
    }

    /**
     * create by  : 小明！！！
     * description: TODO  治理数据分析
     * create time: 2019/8/13 11:17
     */
    @RequestMapping(value = "zl-all-analysis")
    public String zlAnalysis(HttpServletRequest request, Model model) {

        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }


        Integer a = tCheckItemMapper.manageHiddenCompany(1, sb.toString(), 3); // 一般和较小 合格 已治理

        Integer a1 = tCheckItemMapper.manageHiddenCompany(2, sb.toString(), 3); // 一般和较小 不合格 未治理

        Integer count1 = a + a1;

        Integer b = tCheckItemMapper.manageHiddenCompany(1, sb.toString(), 1); // 较大 合格 已治理

        Integer b1 = tCheckItemMapper.manageHiddenCompany(2, sb.toString(), 1); // 较大 不合格 未治理

        Integer count2 = b + b1;

        Integer c = tCheckItemMapper.manageHiddenCompany(1, sb.toString(), 2); // 重大 合格 已治理

        Integer c1 = tCheckItemMapper.manageHiddenCompany(1, sb.toString(), 2); // 重大 不合格 未治理

        Integer count3 = c + c1;

        if (null != count1 && count1 != 0) {  // 一般和较小 治理率
            String str = df.format((float) a / count1);
            model.addAttribute("result11", str);
        } else {
            model.addAttribute("result11", "0.00");
        }

        if (null != count2 && count2 != 0) { // 较大 治理率
            String str = df.format((float) b / count2);
            model.addAttribute("result22", str);
        } else {
            model.addAttribute("result22", "0.00");
        }

        if (null != count3 && count3 != 0) { // 重大 治理率
            String str = df.format((float) c / count3);
            model.addAttribute("result33", str);
        } else {
            model.addAttribute("result33", "0.00");
        }

        String string = "'基础管理','基础管理','基础管理','公用工程','特种设备','生产现场','行为环境','危化管理','消防安全','用电安全','安全设施','防雷静电','职业卫生','专项行业','其他'";

        Integer number1 = tCheckItemMapper.manageHiddenDanger22(1, string, 3, sb.toString()); // 一般和较小 合格 已治理

        Integer number11 = tCheckItemMapper.manageHiddenDanger22(2, string, 3, sb.toString()); // 一般和较小 不合格 未治理

        Integer number2 = tCheckItemMapper.manageHiddenDanger22(1, string, 1, sb.toString()); // 较大 合格 已治理

        Integer number22 = tCheckItemMapper.manageHiddenDanger22(2, string, 1, sb.toString()); // 较大 不合格 未治理

        Integer number3 = tCheckItemMapper.manageHiddenDanger22(1, string, 2, sb.toString()); // 重大 合格 已治理

        Integer number33 = tCheckItemMapper.manageHiddenDanger22(1, string, 2, sb.toString()); // 重大 不合格 未治理

        Integer counts1 = number1 + number11;
        Integer counts2 = number2 + number22;
        Integer counts3 = number3 + number33;

        if (null != counts1 && 0 != counts1) {
            String str = df.format((float) number1 / counts1);
            model.addAttribute("resusts1", str);
        } else {
            model.addAttribute("resusts1", "0.00");
        }

        if (null != counts2 && 0 != counts2) {
            String str = df.format((float) number2 / counts2);
            model.addAttribute("resusts2", str);
        } else {
            model.addAttribute("resusts2", "0.00");
        }

        if (null != counts3 && 0 != counts3) {
            String str = df.format((float) number3 / counts3);
            model.addAttribute("resusts3", str);
        } else {
            model.addAttribute("resusts3", "0.00");
        }

        Integer number4 = tCheckItemMapper.manageHiddenIndustry22(1, 3, sb.toString()); // 一般和较小 合格 已治理

        Integer number44 = tCheckItemMapper.manageHiddenIndustry22(2, 3, sb.toString()); // 一般和较小 不合格 未治理

        Integer number5 = tCheckItemMapper.manageHiddenIndustry22(1, 1, sb.toString()); // 较大 合格 已治理

        Integer number55 = tCheckItemMapper.manageHiddenIndustry22(2, 1, sb.toString()); // 较大 不合格 未治理

        Integer number6 = tCheckItemMapper.manageHiddenIndustry22(1, 2, sb.toString()); // 重大 合格 已治理

        Integer number66 = tCheckItemMapper.manageHiddenIndustry22(1, 2, sb.toString()); // 重大 不合格 未治理

        Integer counts4 = number4 + number44;
        Integer counts5 = number5 + number55;
        Integer counts6 = number6 + number66;

        if (null != counts4 && 0 != counts4) {
            String str = df.format((float) number4 / counts4);
            model.addAttribute("resusts4", str);
        } else {
            model.addAttribute("resusts4", "0.00");
        }

        if (null != counts5 && 0 != counts5) {
            String str = df.format((float) number5 / counts5);
            model.addAttribute("resusts5", str);
        } else {
            model.addAttribute("resusts5", "0.00");
        }

        if (null != counts6 && 0 != counts6) {
            String str = df.format((float) number6 / counts6);
            model.addAttribute("resusts6", str);
        } else {
            model.addAttribute("resusts6", "0.00");
        }

        return "steel/company/evaluate/analysis/zl-analysis";
    }

    @RequestMapping("/villageLogout")
    public String villageLogout(HttpServletRequest request) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        String y = request.getServerName();
        if (y.indexOf("kfq") > -1) {
            return "redirect:/fore/steel";
        }
        return "redirect:/fore/steel";
    }

    /**
     * @param request
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "danger-chart-jx")
    public String dangerCharJx(HttpServletRequest request, Model model, Integer userId) throws ParseException {
        User user = getLoginUser(request);
        //公司实绩统计          //纵向实绩统计        //排查计划统计        //纵向排查计划统计
        Integer danger1 = 0;  Integer count1 = 0; Integer plan1 = 0;  Integer number1 = 0;  Integer danger6 = 0;
        Integer danger2 = 0;  Integer count2 = 0; Integer plan2 = 0;  Integer number2 = 0;  Integer count6 = 0;
        Integer danger3 = 0;  Integer count3 = 0; Integer plan3 = 0;  Integer number3 = 0;
        Integer danger4 = 0;  Integer count4 = 0; Integer plan4 = 0;  Integer number4 = 0;
        Integer danger5 = 0;  Integer count5 = 0; Integer plan5 = 0;  Integer number5 = 0;
        Integer count11 = 0;  Integer count22 = 0;Integer plan6 = 0;  Integer number6 = 0;
        List<Map<String, Object>> companyMaps = new ArrayList<>();
        List<Map<String, Object>> companyMaps1 = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1); //查出所有的公司
        companyMaps.addAll(companyMaps1);
        List<Map<String,Object>> list = null;
        List<Map<String, Object>> list2 = new ArrayList<>();
        List<Map<String, Object>> totalList = new ArrayList<>();
        List<Map<String, Object>> zanlist = new ArrayList<>();
        Company company = null;
        for(Map<String, Object> map:companyMaps){
            danger1 = 0; danger2 = 0; danger3 = 0; danger4 = 0; danger5 = 0; danger6 = 0;
            plan1 = 0; plan2 = 0; plan3 = 0; plan4 = 0; plan5 = 0; plan6 = 0;

            company = companyMapper.selectByPrimaryKey((Integer)map.get("user_id"));//查询对应的公司

            list =  hiddenPlanMapper.selectDpids(String.valueOf((Integer) map.get("user_id")));//查询当前公司的各个车间
           //公司级排查计划
            if (null == list || list.size() == 0){

            }else if (null != list && list.size() != 0){

                for (int i = 0; i < list.size(); i++) {
                    if(null==list.get(i).get("name")){
                        list.get(i).put("name", map.get("user_name"));
                    }
                    if(null !=list.get(i).get("syn_year")){
                        plan1 = plan1 + (Integer) list.get(i).get("syn_year");
                    }

                    danger1 = danger1 + tCheckMapper.findCountAll((String)list.get(i).get("name"),5,String.valueOf((Integer) map.get("user_id")));

                    if(null !=list.get(i).get("eve_year")){
                        plan2 = plan2 + (Integer) list.get(i).get("eve_year");
                    }

                    danger2 = danger2 + tCheckMapper.findCountAll((String)list.get(i).get("name"),1,String.valueOf((Integer) map.get("user_id")));

                    if(null !=list.get(i).get("reg_year")){
                        plan3 = plan3 + (Integer) list.get(i).get("reg_year");
                    }

                    danger3 = danger3 + tCheckMapper.findCountAll((String)list.get(i).get("name"),2,String.valueOf((Integer) map.get("user_id")));

                    if(null !=list.get(i).get("sea_year")){
                        plan4 = plan4 + (Integer) list.get(i).get("sea_year");
                    }

                    danger4 = danger4 + tCheckMapper.findCountAll((String)list.get(i).get("name"),4,String.valueOf((Integer) map.get("user_id")));

                    if(null !=list.get(i).get("els_year")){
                        plan5 = plan5 + (Integer) list.get(i).get("els_year");
                    }

                    danger5 = danger5 + tCheckMapper.findCountAll((String)list.get(i).get("name"),3,String.valueOf((Integer) map.get("user_id")));

                    if(null !=list.get(i).get("bas_year")){
                        plan6 = plan6 + (Integer) list.get(i).get("bas_year");
                    }

                    danger6 = danger6 + tCheckMapper.findCountAll2((String)list.get(i).get("name"), String.valueOf((Integer) map.get("user_id")));
                }
            }
            count11 = danger1 + danger2 + danger3 + danger4 + danger5+danger6;
            count22 = plan1 + plan2 + plan3 + plan4 + plan5 + plan6;
            count1 = count1 + danger1;
            count2 = count2 + danger2;
            count3 = count3 + danger3;
            count4 = count4 + danger4;
            count5 = count5 + danger5;
            count6 = count6 + danger6;
            number1 = number1 + plan1;
            number2 = number2 + plan2;
            number3 = number3 + plan3;
            number4 = number4 + plan4;
            number5 = number5 + plan5;
            number6 = number6 + plan6;
            map1 = new HashMap<>();
            map1.put("name", (String) map.get("user_name"));
            map1.put("syn_year", plan1);
            map1.put("danger1", danger1);
            map1.put("eve_year", plan2);
            map1.put("danger2", danger2);
            map1.put("reg_year", plan3);
            map1.put("danger3", danger3);
            map1.put("sea_year", plan4);
            map1.put("danger4", danger4);
            map1.put("els_year", plan5);
            map1.put("danger5", danger5);
            map1.put("danger6", danger6);
            map1.put("bas_year", plan6);
            map1.put("count", count22);
            map1.put("counts", count11);
            if(count22==0){
                map1.put("wan", "0%");
            }else{
                NumberFormat numberFormat1 = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat1.setMaximumFractionDigits(2);
                map1.put("wan", numberFormat1.format((float)count11 / (float)count22 * 100)+"%");
            }
            list2.add(map1);
        }
        map1 = new HashMap<>();
        map1.put("number1",number1);
        map1.put("number2",number2);
        map1.put("number3",number3);
        map1.put("number4",number4);
        map1.put("number5",number5);
        map1.put("number6",number6);
        map1.put("count1", count1);
        map1.put("count2", count2);
        map1.put("count3", count3);
        map1.put("count4", count4);
        map1.put("count5", count5);
        map1.put("count6", count6);

        DecimalFormat df = new DecimalFormat("0.00");

        Integer sums = count1 + count2 + count3 + count4 + count5 + count6;

        Integer sums1 = number1 + number2 + number3 + number4 + number5 + number6;

        map1.put("sums", sums);
        map1.put("sums1", sums1);
        model.addAttribute("sums",sums);
        totalList.add(map1);
        if (null != sums1 && 0 != sums1) {

            if (null != sums && 0 != sums) {
                String str = df.format((float) sums / sums1 * 100);
                model.addAttribute("sums12",str + "%");

            } else {
                model.addAttribute("sums12","0.00%");
            }
        }
        map1 = new HashMap();
        Integer sum = count1 + count2 + count3 + count4 + count5 + count6;

        Integer sum1 = number1 + number2 + number3 + number4 + number5 + number6;

        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result1 = numberFormat.format((float)count1 / (float)sum * 100);
        String result2 = numberFormat.format((float)count2 / (float)sum * 100);
        String result3 = numberFormat.format((float)count3 / (float)sum * 100);
        String result4 = numberFormat.format((float)count4 / (float)sum * 100);
        String result5 = numberFormat.format((float)count5 / (float)sum * 100);
        String result6 = numberFormat.format((float)count6 / (float)sum * 100);

        String result11 = numberFormat.format((float)number1 / (float)sum1 * 100);
        String result22 = numberFormat.format((float)number2 / (float)sum1 * 100);
        String result33 = numberFormat.format((float)number3 / (float)sum1 * 100);
        String result44 = numberFormat.format((float)number4 / (float)sum1 * 100);
        String result55 = numberFormat.format((float)number5 / (float)sum1 * 100);
        String result66 = numberFormat.format((float)number6 / (float)sum1 * 100);

        map1.put("result1",result1+"%");
        map1.put("result2",result2+"%");
        map1.put("result3",result3+"%");
        map1.put("result4",result4+"%");
        map1.put("result5",result5+"%");
        map1.put("result6",result5+"%");

        map1.put("result11",result11+"%");
        map1.put("result22",result22+"%");
        map1.put("result33",result33+"%");
        map1.put("result44",result44+"%");
        map1.put("result55",result55+"%");
        map1.put("result66",result66+"%");
        zanlist.add(map1);
       //list2.add(map1);
       model.addAttribute("list", list2);
       model.addAttribute("list1", totalList);
       model.addAttribute("list2", zanlist);
       return "steel/danger/danger1/danger-chart-jx";

    }

    /**
     * create by  : 小明！！！
     * description: TODO 绩效分析  flag 1.企业自查；2.行政检查；3.第三方排查
     * create time: 2019/8/3 11:49
     */
    @RequestMapping(value = "zhuChartData55")
    public String zhuChartData55(HttpServletRequest request, Model model, Integer flag, Integer userId) {
        User user = getLoginUser(request);
        Integer danger1 = 0; Integer count1 = 0;
        Integer danger2 = 0; Integer count2 = 0;
        List<Map<String, Object>> companyMaps = new ArrayList<>();
        List<Map<String, Object>> companyMaps1 = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        companyMaps.addAll(companyMaps1);
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = new ArrayList<>();
        List<Map<String,Object>> list2 = new ArrayList<>();
        List<Map<String, Object>> totallist = new ArrayList<>();
        List<Map<String, Object>> zanlist = new ArrayList<>();
        for(Map<String, Object> map:companyMaps){
            danger1 = 0; danger2 = 0;
            if (flag ==1){
                list = hiddenPlanMapper.selectDpids(String.valueOf((Integer) map.get("user_id")));
            }else if (flag == 2){
                list = tCheckMapper.findCheckCompany(String.valueOf((Integer) map.get("user_id")),2);
            }else if (flag == 3){
                list = tCheckMapper.findCheckCompany(String.valueOf((Integer) map.get("user_id")),3);
            }

            if (null == list || list.size() == 0){

            }else if (null != list && list.size() != 0){

                for (int i = 0; i < list.size(); i++) {
                    if (flag == 1){
                        if (null == list.get(i).get("dpid") || list.get(i).get("dpid") == 0){
                            list1 = tCheckItemMapper.findHiddenSourceCount(flag, (String) map.get("user_name"),String.valueOf((Integer) map.get("user_id")),2); // 现场
                            danger1 = danger1 + list1.size();
                            list1 = tCheckItemMapper.findHiddenSourceCount(flag, (String) map.get("user_name"),String.valueOf((Integer) map.get("user_id")),1); // 现场
                            danger2 = danger2 + list1.size();

                        }else if (null != list.get(i).get("dpid") && list.get(i).get("dpid") == 0){
                            list1 = tCheckItemMapper.findHiddenSourceCount(flag, (String) list.get(i).get("name"),String.valueOf((Integer) map.get("user_id")),2); // 现场
                            danger1 = danger1 + list1.size();
                            list1 = tCheckItemMapper.findHiddenSourceCount(flag, (String) list.get(i).get("name"),String.valueOf((Integer) map.get("user_id")),1); // 现场
                            danger2 = danger2 + list1.size();

                        }

                    }else{
                        list1 = tCheckItemMapper.lookHiddenSourceCount(flag, (String) list.get(i).get("name"),String.valueOf((Integer) map.get("user_id")),2); // 现场
                        danger1 = danger1 + list1.size();
                        list1 = tCheckItemMapper.lookHiddenSourceCount(flag, (String) list.get(i).get("name"),String.valueOf((Integer) map.get("user_id")),1); // 基础
                        danger2 = danger2 + list1.size();

                    }
                }
            }
            count1 = count1 + danger1;
            count2 = count2 + danger2;
            map1 = new HashMap<>();
            map1.put("name", (String) map.get("user_name"));
            map1.put("danger1", danger1);
            map1.put("danger2", danger2);
            map1.put("sum", danger1+danger2);
            list2.add(map1);
        }
        map1 = new HashMap<>();
        map1.put("count1", count1);
        map1.put("count2", count2);
        Integer sum   = count1 + count2;
        map1.put("sum", sum);
        totallist.add(map1);
        DecimalFormat df = new DecimalFormat("0.00");
        map1 = new HashMap<>();
        if (null != sum && 0 != sum){

            if (0 != count1){
                String s = df.format((float)count1 / sum * 100);
                map1.put("result1",s + "%"); // 所有车间 现场风险 占比总数
            }else {
                map1.put("result1","0.00%"); // 所有车间 现场风险 占比总数
            }

            if (0 != count2){
                String s = df.format((float)count2 / sum * 100);
                map1.put("result2",s + "%"); // 所有车间 基础风险 占比总数
            }else {
                map1.put("result2","0.00%"); // 所有车间 基础风险 占比总数
            }

        }else {
            map1.put("result1","0.00%"); // 所有车间 现场风险 占比总数
            map1.put("result2","0.00%"); // 所有车间 基础风险 占比总数
        }
        zanlist.add(map1);
        //list2.add(map1);
        model.addAttribute("list", list2);
        model.addAttribute("list1", totallist);
        model.addAttribute("list2", zanlist);
        return "steel/danger/danger1/zhuChartData55";

    }

    /**
     * create by  : 小明！！！
     * description: TODO 隐患排查对象分析    flag : 1.企业自查; 2.行政检查; 3.第三方排查;
     * create time: 2019/8/5 14:29
     */
    @RequestMapping(value = "zhuChartData66")
    public String zhuChartData66(HttpServletRequest request, Model model, Integer flag, Integer userId) {
        User user = getLoginUser(request);
        //存储各个公司分量           //纵向统计
        Integer danger19 = 0;      Integer sumS = 0;
        Integer danger1 = 0;       Integer sumA = 0;
        Integer danger2 = 0;       Integer sumB = 0;
        Integer danger3 = 0;       Integer sumC = 0;
        Integer danger4 = 0;       Integer sumD = 0;
        Integer danger5 = 0;       Integer sumE = 0;
        Integer danger6 = 0;       Integer sumF = 0;
        Integer danger7 = 0;       Integer sumG = 0;
        Integer danger8 = 0;       Integer sumH = 0;
        Integer danger9 = 0;       Integer sumI = 0;
        Integer danger10 = 0;      Integer sumJ = 0;
        Integer danger11 = 0;      Integer sumK = 0;
        Integer danger12 = 0;      Integer sumL = 0;
        Integer danger13 = 0;      Integer sumM = 0;
        Integer danger14 = 0;      Integer sumN = 0;
        Integer danger15 = 0;      Integer sumO = 0;
        Integer danger16 = 0;      Integer sumP = 0;
        Integer danger17 = 0;      Integer sumQ = 0;
        Integer danger18 = 0;      Integer sumR = 0;
        Integer danger20 = 0;
        List<Map<String, Object>> companyMaps = new ArrayList<>();
        List<Map<String, Object>> companyMaps1 = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        companyMaps.addAll(companyMaps1);
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> companylist = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> sortlist = new ArrayList<>();
        List<Map<String,Object>> list2 = null;
        Company company = null;
        Map<String,Object> map2 = null;
        for(Map<String, Object> map:companyMaps){
            //计数初始化
            danger19 = 0; danger1 =0; danger2 =0; danger3 =0; danger4 =0;
            danger5 =0;  danger6 =0; danger7 =0; danger8 =0; danger9 =0;
            danger10 =0; danger11 =0; danger12 =0; danger13 =0; danger14 =0;
            danger15 =0; danger16 =0; danger17 =0; danger18 =0; danger20 =0;
            //统计车间
            if (flag ==1){
                list = hiddenPlanMapper.selectDpids(String.valueOf((Integer) map.get("user_id")));
            }else if (flag == 2){
                list = tCheckMapper.findCheckCompany(String.valueOf((Integer) map.get("user_id")),2);
            }else if (flag == 3){
                list = tCheckMapper.findCheckCompany(String.valueOf((Integer) map.get("user_id")),3);
            }
            if (null == list || list.size() == 0){

            }else if (null != list && list.size() != 0){

                for (int i = 0; i < list.size(); i++) {
                    if (flag == 1){
                        if(null == list.get(i).get("dpid") || list.get(i).get("dpid") == 0){
                            list2 = tCheckItemMapper.zhuFinichChartDataBasics((String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger19 = danger19 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("基础管理",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger19 = danger19 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("设计总图",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger1 = danger1 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("试生产",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger2 = danger2 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("装置运行",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger3 = danger3 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("设备安全",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger4 = danger4 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("仪表安全",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger5 = danger5 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("电气安全",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger6 = danger6 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("应急消防",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger7 = danger7 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("特殊管控",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger8 = danger8 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("行为环境",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger9 = danger9 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("生产现场",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger10 = danger10 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("公辅工程",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger11 = danger11 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("特殊设备",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger12 = danger12 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("专项行业",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger13 = danger13 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("生产工艺",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger14 = danger14 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("设备设施",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger15 = danger15 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("危化管理",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger16 = danger16 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("安全设施",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger17 = danger17 + list2.size();

                            list2 = tCheckItemMapper.zhuFinichChartDataNow("其他",(String) map.get("user_name"),flag,String.valueOf((Integer) map.get("user_id")));
                            danger18 = danger18 + list2.size();

                        }else if (null != list.get(i).get("dpid") && list.get(i).get("dpid") != 0){

                            list2 = tCheckItemMapper.zhuChartData661("基础管理",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger19 = danger19 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("设计总图",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger1 = danger1 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("试生产",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger2 = danger2 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("装置运行",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger3 = danger3 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("设备安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger4 = danger4 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("仪表安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger5 = danger5 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("电气安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger6 = danger6 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("应急消防",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger7 = danger7 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("特殊管控",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger8 = danger8 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("行为环境",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger9 = danger9 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("生产现场",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger10 = danger10 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("公辅工程",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger11 = danger11 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("特种设备",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger12 = danger12 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("专项行业",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger13 = danger13 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("生产工艺",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger14 = danger14 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("设备设施",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger15 = danger15 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("危化管理",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger16 = danger16 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("安全设施",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger17 = danger17 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData661("其他",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); //
                            danger18 = danger18 + list2.size();

                        }
                    }else{
                        if (null != list.get(i).get("depart") && list.get(i).get("depart").equals((String) map.get("user_name"))){

                            list2 = tCheckItemMapper.zhu671ChartDataBasics((String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger19 = danger19 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("基础管理",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger19 = danger19 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("设计总图",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger1 = danger1 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("试生产",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger2 = danger2 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("装置运行",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger3 = danger3 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("设备安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger4 = danger4 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("仪表安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger5 = danger5 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("电气安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger6 = danger6 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("应急消防",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger7 = danger7 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("特殊管控",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger8 = danger8 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("行为环境",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger9 = danger9 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("生产现场",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger10 = danger10 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("公辅工程",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger11 = danger11 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("特种设备",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger12 = danger12 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("专项行业",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger13 = danger13 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("生产工艺",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger14 = danger14 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("设备设施",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger15 = danger15 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("危化管理",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger16 = danger16 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("安全设施",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger17 = danger17 + list2.size();

                            list2 = tCheckItemMapper.zhu671ChartDataNow("其他",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger18 = danger18 + list2.size();

                        }else if (null != list.get(i).get("depart")){

                            list2 = tCheckItemMapper.zhuChartData671("基础管理",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger19 = danger19 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("设计总图",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger1 = danger1 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("试生产",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger2 = danger2 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("装置运行",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger3 = danger3 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("设备安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger4 = danger4 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("仪表安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger5 = danger5 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("电气安全",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger6 = danger6 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("应急消防",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger7 = danger7 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("特殊管控",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger8 = danger8 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("行为环境",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger9 = danger9 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("生产现场",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger10 = danger10 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("公辅工程",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger11 = danger11 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("特殊设备",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger12 = danger12 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("专项行业",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger13 = danger13 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("生产工艺",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger14 = danger14 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("设备设施",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger15 = danger15 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("危化管理",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger16 = danger16 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("安全设施",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger17 = danger17 + list2.size();

                            list2 = tCheckItemMapper.zhuChartData671("其他",(String) list.get(i).get("name"),flag,String.valueOf((Integer) map.get("user_id"))); // 生产工艺 隐患数据
                            danger18 = danger18 + list2.size();

                        }

                    }
                }
            }
            map2 = new HashMap<>();
            map2.put("name", (String) map.get("user_name"));
            map2.put("danger19", danger19);
            map2.put("danger1", danger1);
            map2.put("danger2", danger2);
            map2.put("danger3", danger3);
            map2.put("danger4", danger4);
            map2.put("danger5", danger5);
            map2.put("danger6", danger6);
            map2.put("danger7", danger7);
            map2.put("danger8", danger8);
            map2.put("danger9", danger9);
            map2.put("danger10", danger10);
            map2.put("danger11", danger11);
            map2.put("danger12", danger12);
            map2.put("danger13", danger13);
            map2.put("danger14", danger14);
            map2.put("danger15", danger15);
            map2.put("danger16", danger16);
            map2.put("danger17", danger17);
            map2.put("danger18", danger18);
            sumS = sumS + danger19;
            sumA = sumA + danger1;
            sumB = sumB + danger2;
            sumC = sumC + danger3;
            sumD = sumD + danger4;
            sumE = sumE + danger5;
            sumF = sumF + danger6;
            sumG = sumG + danger7;
            sumH = sumH + danger8;
            sumI = sumI + danger9;
            sumJ = sumJ + danger10;
            sumK = sumK + danger11;
            sumL = sumL + danger12;
            sumM = sumM + danger13;
            sumN = sumN + danger14;
            sumO = sumO + danger15;
            sumP = sumP + danger16;
            sumQ = sumQ + danger17;
            sumR = sumR + danger18;
            danger20 = danger19 + danger1 + danger2 + danger3 + danger4 + danger5 + danger6 + danger7 + danger8 + danger9 + danger10 + danger11 + danger12 + danger13 + danger14 +danger15 + danger16 + danger17 + danger18;
            map2.put("danger20", danger20);
            companylist.add(map2);
            addSortElement(sortlist,(String) map.get("user_name"), danger20);
        }
        findSort1(flag, companylist, sortlist);
        Integer count = sumS + sumA + sumB + sumC + sumD +sumE + sumF + sumG + sumH +sumI + sumJ + sumK + sumL + sumM + sumN + sumO + sumP + sumQ + sumR;
        DecimalFormat df = new DecimalFormat("0.00");
        map2 = new HashMap<>();
        selectCount1(map2, sumA, sumB, sumC, sumD, sumE, sumF, sumG, sumH, sumI, sumJ, sumK, sumL, sumM, sumN, sumO, sumP, sumQ, sumR, sumS, count, df);
        map2.put("sum1",sumA);
        map2.put("sum2",sumB);
        map2.put("sum3",sumC);
        map2.put("sum4",sumD);
        map2.put("sum5",sumE);
        map2.put("sum6",sumF);
        map2.put("sum7",sumG);
        map2.put("sum8",sumH);
        map2.put("sum9",sumI);
        map2.put("sum10",sumJ);
        map2.put("sum11",sumK);
        map2.put("sum12",sumL);
        map2.put("sum13",sumM);
        map2.put("sum14",sumN);
        map2.put("sum15",sumO);
        map2.put("sum16",sumP);
        map2.put("sum17",sumQ);
        map2.put("sum18",sumR);
        map2.put("sum19",sumS);
        companylist.add(map2);
        model.addAttribute("data",new Date());
        model.addAttribute("list",companylist);

        //model.addAttribute("list1",list1);
        return "steel/danger/danger1/zhuChartData66";
    }
    @RequestMapping(value = "zhuChartData22")
    public String zhuChartData22 (HttpServletRequest request, Model model, Integer flag){
        User user = getLoginUser(request);
        //存储各个公司分量           //纵向统计
        Integer danger19 = 0;      Integer sumS = 0;
        Integer danger1 = 0;       Integer sumA = 0;
        Integer danger2 = 0;       Integer sumB = 0;
        Integer danger3 = 0;       Integer sumC = 0;
        Integer danger4 = 0;       Integer sumD = 0;
        Integer danger5 = 0;       Integer sumE = 0;
        Integer danger6 = 0;       Integer sumF = 0;
        Integer danger7 = 0;       Integer sumG = 0;
        Integer danger8 = 0;       Integer sumH = 0;
        Integer danger9 = 0;       Integer sumI = 0;
        Integer danger10 = 0;      Integer sumJ = 0;
        Integer danger11 = 0;      Integer sumK = 0;
        Integer danger12 = 0;      Integer sumL = 0;
        Integer danger13 = 0;      Integer sumM = 0;
        Integer danger14 = 0;      Integer sumN = 0;
        Integer danger15 = 0;      Integer sumO = 0;
        Integer danger16 = 0;      Integer sumP = 0;
        Integer danger17 = 0;      Integer sumQ = 0;
        Integer danger18 = 0;      Integer sumR = 0;
        Integer danger20 = 0;
        List<Map<String, Object>> companyMaps = new ArrayList<>();
        List<Map<String, Object>> companyMaps1 = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        companyMaps.addAll(companyMaps1);
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> companylist = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> sortlist = new ArrayList<>();
        List<Map<String,Object>> list2 = null;
        Company company = null;
        Map<String,Object> map2 = null;
        for(Map<String, Object> map:companyMaps){
            //计数初始化
            danger19 = 0; danger1 =0; danger2 =0; danger3 =0; danger4 =0;
            danger5 =0;  danger6 =0; danger7 =0; danger8 =0; danger9 =0;
            danger10 =0; danger11 =0; danger12 =0; danger13 =0; danger14 =0;
            danger15 =0; danger16 =0; danger17 =0; danger18 =0; danger20 =0;

            if (flag ==1){
                list = hiddenPlanMapper.selectDpids(String.valueOf((Integer) map.get("user_id")));
            }else if (flag == 2){
                list = tCheckMapper.findCheckCompany(String.valueOf((Integer) map.get("user_id")),2);
            }else if (flag == 3){
                list = tCheckMapper.findCheckCompany(String.valueOf((Integer) map.get("user_id")),3);
            }

            if (null == list || list.size() == 0){

            }else if (null != list && list.size() != 0){

                for (int i = 0; i < list.size(); i++) {

                    if (flag == 1){
                        if (null == list.get(i).get("dpid") || list.get(i).get("dpid") == 0){

                            danger19 = danger19 + tCheckItemMapper.zhu22ChartDataBasic((String) map.get("user_name"),flag,user.getId()); // 生产工艺 隐患数据

                            danger19 = danger19 + tCheckItemMapper.zhu22ChartDataNow("基础管理",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger1 = danger1 + tCheckItemMapper.zhu22ChartDataNow("设计总图",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger2 = danger2 + tCheckItemMapper.zhu22ChartDataNow("试生产",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger3 = danger3 + tCheckItemMapper.zhu22ChartDataNow("装置运行",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger4 = danger4 + tCheckItemMapper.zhu22ChartDataNow("设备安全",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger5 = danger5 + tCheckItemMapper.zhu22ChartDataNow("仪表安全",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger6 = danger6 + tCheckItemMapper.zhu22ChartDataNow("电气安全",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger7 = danger7 + tCheckItemMapper.zhu22ChartDataNow("应急消防",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger8 = danger8 + tCheckItemMapper.zhu22ChartDataNow("特殊管控",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger9 = danger9 + tCheckItemMapper.zhu22ChartDataNow("行为环境",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger10 = danger10 + tCheckItemMapper.zhu22ChartDataNow("生产现场",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger11 = danger11 + tCheckItemMapper.zhu22ChartDataNow("公辅工程",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger12 = danger12 + tCheckItemMapper.zhu22ChartDataNow("特种设备",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger13 = danger13 + tCheckItemMapper.zhu22ChartDataNow("专项行业",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger14 = danger14 + tCheckItemMapper.zhu22ChartDataNow("生产工艺",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger15 = danger15 + tCheckItemMapper.zhu22ChartDataNow("设备设施",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger16 = danger16 + tCheckItemMapper.zhu22ChartDataNow("危化管理",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger17 = danger17 + tCheckItemMapper.zhu22ChartDataNow("安全设施",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger18 = danger18 + tCheckItemMapper.zhu22ChartDataNow("其他",(String) map.get("user_name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据


                        }else if (null != list.get(i).get("dpid") && list.get(i).get("dpid") != 0){

                            danger19 = danger19 + tCheckItemMapper.zhuChartData22("基础管理",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger1 = danger1 + tCheckItemMapper.zhuChartData22("设计总图",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger2 = danger2 + tCheckItemMapper.zhuChartData22("试生产",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger3 = danger3 + tCheckItemMapper.zhuChartData22("装置运行",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger4 = danger4 + tCheckItemMapper.zhuChartData22("设备安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger5 = danger5 + tCheckItemMapper.zhuChartData22("仪表安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger6 = danger6 + tCheckItemMapper.zhuChartData22("电气安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger7 = danger7 + tCheckItemMapper.zhuChartData22("应急消防",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger8 = danger8 + tCheckItemMapper.zhuChartData22("特殊管控",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger9 = danger9 + tCheckItemMapper.zhuChartData22("行为环境",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger10 = danger10 + tCheckItemMapper.zhuChartData22("生产现场",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger11 = danger11 + tCheckItemMapper.zhuChartData22("公辅工程",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger12 = danger12 + tCheckItemMapper.zhuChartData22("特种设备",(String) map.get("user_name"),flag,user.getId()); // 生产工艺 隐患数据

                            danger13 = danger13 + tCheckItemMapper.zhuChartData22("专项行业",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger14 = danger14 + tCheckItemMapper.zhuChartData22("生产工艺",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger15 = danger15 + tCheckItemMapper.zhuChartData22("设备设施",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger16 = danger16 + tCheckItemMapper.zhuChartData22("危化管理",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger17 = danger17 + tCheckItemMapper.zhuChartData22("安全设施",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger18 = danger18 + tCheckItemMapper.zhuChartData22("其他",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                        }
                    }else{
                        if (null != list.get(i).get("depart") && list.get(i).get("depart").equals((String) map.get("user_name"))){

                            danger19 = danger19 + tCheckItemMapper.zhu23ChartDataBasics((String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger19 = danger19 + tCheckItemMapper.zhu23ChartDataNows("基础管理",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger1 = danger1 + tCheckItemMapper.zhu23ChartDataNows("设计总图",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger2 = danger2 + tCheckItemMapper.zhu23ChartDataNows("试生产",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger3 = danger3 + tCheckItemMapper.zhu23ChartDataNows("装置运行",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger4 = danger4 + tCheckItemMapper.zhu23ChartDataNows("设备安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger5 = danger5 + tCheckItemMapper.zhu23ChartDataNows("仪表安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger6 = danger6 + tCheckItemMapper.zhu23ChartDataNows("电气安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger7 = danger7 + tCheckItemMapper.zhu23ChartDataNows("应急消防",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger8 = danger8 + tCheckItemMapper.zhu23ChartDataNows("特殊管控",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger9 = danger9 + tCheckItemMapper.zhu23ChartDataNows("行为环境",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger10 = danger10 + tCheckItemMapper.zhu23ChartDataNows("生产现场",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger11 = danger11 + tCheckItemMapper.zhu23ChartDataNows("公辅工程",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger12 = danger12 + tCheckItemMapper.zhu23ChartDataNows("特种设备",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger13 = danger13 + tCheckItemMapper.zhu23ChartDataNows("专项行业",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger14 = danger14 + tCheckItemMapper.zhu23ChartDataNows("生产工艺",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger15 = danger15 + tCheckItemMapper.zhu23ChartDataNows("设备设施",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger16 = danger16 + tCheckItemMapper.zhu23ChartDataNows("危化管理",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger17 = danger17 + tCheckItemMapper.zhu23ChartDataNows("安全设施",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger18 = danger18 + tCheckItemMapper.zhu23ChartDataNows("其他",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                        }else if (null != list.get(i).get("depart")){

                            danger19 = danger19 + tCheckItemMapper.zhuChartData23("基础管理",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger1 = danger1 + tCheckItemMapper.zhuChartData23("设计总图",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger2 = danger2 + tCheckItemMapper.zhuChartData23("试生产",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger3 = danger3 + tCheckItemMapper.zhuChartData23("装置运行",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger4 = danger4 + tCheckItemMapper.zhuChartData23("设备安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger5 = danger5 + tCheckItemMapper.zhuChartData23("仪表安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger6 = danger6 + tCheckItemMapper.zhuChartData23("电气安全",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger7 = danger7 + tCheckItemMapper.zhuChartData23("应急消防",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger8 = danger8 + tCheckItemMapper.zhuChartData23("特殊管控",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger9 = danger9 + tCheckItemMapper.zhuChartData23("行为环境",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger10 = danger10 + tCheckItemMapper.zhuChartData23("生产现场",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger11 = danger11 + tCheckItemMapper.zhuChartData23("公辅工程",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger12 = danger12 + tCheckItemMapper.zhuChartData23("特种设备",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger13 = danger13 + tCheckItemMapper.zhuChartData23("专项行业",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger14 = danger14 + tCheckItemMapper.zhuChartData23("生产工艺",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger15 = danger15 + tCheckItemMapper.zhuChartData23("设备设施",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger16 = danger16 + tCheckItemMapper.zhuChartData23("危化管理",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger17 = danger17 + tCheckItemMapper.zhuChartData23("安全设施",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                            danger18 = danger18 + tCheckItemMapper.zhuChartData23("其他",(String)list.get(i).get("name"),flag,(Integer) map.get("user_id")); // 生产工艺 隐患数据

                        }
                    }
                }
            }
            map2 = new HashMap<>();
            map2.put("name", (String) map.get("user_name"));
            map2.put("danger19", danger19);
            map2.put("danger1", danger1);
            map2.put("danger2", danger2);
            map2.put("danger3", danger3);
            map2.put("danger4", danger4);
            map2.put("danger5", danger5);
            map2.put("danger6", danger6);
            map2.put("danger7", danger7);
            map2.put("danger8", danger8);
            map2.put("danger9", danger9);
            map2.put("danger10", danger10);
            map2.put("danger11", danger11);
            map2.put("danger12", danger12);
            map2.put("danger13", danger13);
            map2.put("danger14", danger14);
            map2.put("danger15", danger15);
            map2.put("danger16", danger16);
            map2.put("danger17", danger17);
            map2.put("danger18", danger18);
            sumS = sumS + danger19;
            sumA = sumA + danger1;
            sumB = sumB + danger2;
            sumC = sumC + danger3;
            sumD = sumD + danger4;
            sumE = sumE + danger5;
            sumF = sumF + danger6;
            sumG = sumG + danger7;
            sumH = sumH + danger8;
            sumI = sumI + danger9;
            sumJ = sumJ + danger10;
            sumK = sumK + danger11;
            sumL = sumL + danger12;
            sumM = sumM + danger13;
            sumN = sumN + danger14;
            sumO = sumO + danger15;
            sumP = sumP + danger16;
            sumQ = sumQ + danger17;
            sumR = sumR + danger18;
            danger20 = danger19 + danger1 + danger2 + danger3 + danger4 + danger5 + danger6 + danger7 + danger8 + danger9 + danger10 + danger11 + danger12 + danger13 + danger14 +danger15 + danger16 + danger17 + danger18;
            map2.put("danger20", danger20);
            companylist.add(map2);
            addSortElement(sortlist,(String) map.get("user_name"), danger20);
        }
        findSort1(flag, companylist, sortlist);
        Integer count = sumS + sumA + sumB + sumC + sumD +sumE + sumF + sumG + sumH +sumI + sumJ + sumK + sumL + sumM + sumN + sumO + sumP + sumQ + sumR;
        DecimalFormat df = new DecimalFormat("0.00");
        map2 = new HashMap<>();
        selectCount1(map2, sumA, sumB, sumC, sumD, sumE, sumF, sumG, sumH, sumI, sumJ, sumK, sumL, sumM, sumN, sumO, sumP, sumQ, sumR, sumS, count, df);
        map2.put("sum1",sumA);
        map2.put("sum2",sumB);
        map2.put("sum3",sumC);
        map2.put("sum4",sumD);
        map2.put("sum5",sumE);
        map2.put("sum6",sumF);
        map2.put("sum7",sumG);
        map2.put("sum8",sumH);
        map2.put("sum9",sumI);
        map2.put("sum10",sumJ);
        map2.put("sum11",sumK);
        map2.put("sum12",sumL);
        map2.put("sum13",sumM);
        map2.put("sum14",sumN);
        map2.put("sum15",sumO);
        map2.put("sum16",sumP);
        map2.put("sum17",sumQ);
        map2.put("sum18",sumR);
        map2.put("sum19",sumS);
        companylist.add(map2);
        model.addAttribute("data",new Date());
        model.addAttribute("list",companylist);
        return "steel/danger/danger1/zhuChartData22";
    }
    private static void addSortElement(List<Map<String,Object>> list, String companyName, int count){
        Map<String, Object> map = new HashMap<>();
        map.put("name", companyName);
        map.put("danger20", count);
        list.add(map);
    }
    private void findSort1(Integer flag, List<Map<String, Object>> list, List<Map<String, Object>> list1) {
        if (null != flag){

            Collections.sort(list1, new Comparator<Map<String, Object>>() {

                public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                    Integer name1 = Integer.valueOf(o1.get("danger20").toString()) ;

                    Integer name2 = Integer.valueOf(o2.get("danger20").toString()) ;

                    return name1.compareTo(name2);

                }
            });

            for (int i = 0; i < list1.size(); i++) {

                list1.get(i).put("data",list1.size()-i);

            }

            for (int i = 0; i < list.size(); i++) {

                for (int j = 0; j < list1.size(); j++) {

                    if (null != list.get(i).get("name") &&  null != list1.get(j).get("name")){

                        if (list.get(i).get("name").equals(list1.get(j).get("name"))){

                            list.get(i).put("data",list1.get(j).get("data"));

                            break;
                        }
                    }
                }
            }
        }
    }

    private void selectCount1(Map<String, Object> map, Integer sum1, Integer sum2, Integer sum3, Integer sum4, Integer sum5, Integer sum6, Integer sum7, Integer sum8, Integer sum9, Integer sum10, Integer sum11, Integer sum12, Integer sum13, Integer sum14, Integer sum15, Integer sum16, Integer sum17, Integer sum18, Integer sum19, Integer count, DecimalFormat df) {
        if (null != count && 0 != count) {

            if (null != sum19 && 0 != sum19) {
                String str = df.format((float)sum19 / count * 100);

                map.put("result19",str+"%");

            } else {
                map.put("result19","0.00%");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum1 && 0 != sum1) {
                String str = df.format((float)sum1 / count * 100);
                map.put("result1",str + "%");

            } else {
                map.put("result1","0.00%");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum2 && 0 != sum2) {
                String str = df.format((float)sum2 / count * 100);
                map.put("result2",str + "%");

            } else {
                map.put("result2","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum3 && 0 != sum3) {
                String str = df.format((float)sum3 / count * 100);
                map.put("result3",str + "%");

            } else {
                map.put("result3","0.00%");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum4 && 0 != sum4) {
                String str = df.format((float)sum4 / count * 100);
                map.put("result4",str + "%");

            } else {
                map.put("result4","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum5 && 0 != sum5) {
                String str = df.format((float)sum5 / count * 100);
                map.put("result5", str + "%");

            } else {
                map.put("result5","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum6 && 0 != sum6) {
                String str = df.format((float)sum6 / count * 100);
                map.put("result6",str + "%");

            } else {
                map.put("result6","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum7 && 0 != sum7) {
                String str = df.format((float)sum7 / count * 100);
                map.put("result7",str + "%");

            } else {
                map.put("result7","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum8 && 0 != sum8) {
                String str = df.format((float)sum8 / count * 100);
                map.put("result8",str + "%");

            } else {
                map.put("result8","0.00%");// 重大隐患 未治理 占比数据 竖
            }

            if (null != sum9 && 0 != sum9) {
                String str = df.format((float)sum9 / count * 100);
                map.put("result9",str + "%");

            } else {
                map.put("result9","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum10 && 0 != sum10) {
                String str = df.format((float)sum10 / count * 100);
                map.put("result10",str + "%");

            } else {
                map.put("result10","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum11 && 0 != sum11) {
                String str = df.format((float)sum11 / count * 100);
                map.put("result11",str + "%");

            } else {
                map.put("result11","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum12 && 0 != sum12) {
                String str = df.format((float)sum12 / count * 100);
                map.put("result12",str + "%");

            } else {
                map.put("result12","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum13 && 0 != sum13) {
                String str = df.format((float)sum13 / count * 100);
                map.put("result13",str + "%");

            } else {
                map.put("result13","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum14 && 0 != sum14) {
                String str = df.format((float)sum14 / count * 100);
                map.put("result14",str + "%");

            } else {
                map.put("result14","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum15 && 0 != sum15) {
                String str = df.format((float)sum15 / count * 100);
                map.put("result15",str + "%");

            } else {
                map.put("result15","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum16 && 0 != sum16) {
                String str = df.format((float)sum16 / count * 100);
                map.put("result16",str + "%");

            } else {
                map.put("result16","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum17 && 0 != sum17) {
                String str = df.format((float)sum17 / count * 100);
                map.put("result17",str + "%");

            } else {
                map.put("result17","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != sum18 && 0 != sum18) {
                String str = df.format((float)sum18 / count * 100);
                map.put("result18",str + "%");

            } else {
                map.put("result18","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

        }else {
            // 占比 坚
            map.put("result1", "0.00%");
            map.put("result2", "0.00%");
            map.put("result3", "0.00%");
            map.put("result4", "0.00%");
            map.put("result5", "0.00%");
            map.put("result6", "0.00%");
            map.put("result7", "0.00%");
            map.put("result8", "0.00%");
            map.put("result9", "0.00%");
            map.put("result10", "0.00%");
            map.put("result11", "0.00%");
            map.put("result12", "0.00%");
            map.put("result13", "0.00%");
            map.put("result14", "0.00%");
            map.put("result15", "0.00%");
            map.put("result16", "0.00%");
            map.put("result17", "0.00%");
            map.put("result18", "0.00%");
            map.put("result19", "0.00%");
        }
    }
    @RequestMapping(value = "zhuChartData123")
    public String zhuChartData123 (HttpServletRequest request, Model model){
        User user = getLoginUser(request);
        //存储各个公司分量           //纵向统计
        Integer danger19 = 0;      Integer sumS = 0;
        Integer danger1 = 0;       Integer sumA = 0;
        Integer danger2 = 0;       Integer sumB = 0;
        Integer danger3 = 0;       Integer sumC = 0;
        Integer danger4 = 0;       Integer sumD = 0;
        Integer danger5 = 0;       Integer sumE = 0;
        Integer danger6 = 0;       Integer sumF = 0;
        Integer danger7 = 0;       Integer sumG = 0;
        Integer danger8 = 0;       Integer sumH = 0;
        Integer danger9 = 0;       Integer sumI = 0;
        Integer danger10 = 0;      Integer sumJ = 0;
        Integer danger11 = 0;      Integer sumK = 0;
        Integer danger12 = 0;      Integer sumL = 0;
        Integer danger13 = 0;      Integer sumM = 0;
        Integer danger14 = 0;      Integer sumN = 0;
        Integer danger15 = 0;      Integer sumO = 0;
        Integer danger16 = 0;      Integer sumP = 0;
        Integer danger17 = 0;      Integer sumQ = 0;
        Integer danger18 = 0;      Integer sumR = 0;
        Integer danger20 = 0;
        List<Map<String, Object>> companyMaps = new ArrayList<>();
        List<Map<String, Object>> companyMaps1 = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        companyMaps.addAll(companyMaps1);
        List<Map<String,Object>> list = null; //查询车间
        List<Map<String,Object>> list1 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map2 = null;
        List<Map<String, Object>> companylist = new ArrayList<>();
        List<Map<String, Object>> sortlist = new ArrayList<>();
        Company company = null;
        for(Map<String, Object> map:companyMaps){
            //计数初始化
            danger19 = 0; danger1 =0; danger2 =0; danger3 =0; danger4 =0;
            danger5 =0;  danger6 =0; danger7 =0; danger8 =0; danger9 =0;
            danger10 =0; danger11 =0; danger12 =0; danger13 =0; danger14 =0;
            danger15 =0; danger16 =0; danger17 =0; danger18 =0; danger20 =0;

             list = zzjgDepartmentMapper.findAllLevel1((Integer) map.get("user_id"));

             company = companyMapper.selectByPrimaryKey((Integer) map.get("user_id"));
            //公司级
            danger19 = danger19 + tCheckItemMapper.zhu123ChartDataBasics((String) map.get("user_name"),(Integer) map.get("user_id"));

            danger19 = danger19 + tCheckItemMapper.zhu123ChartDataNews("基础管理",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger1 = danger1 + tCheckItemMapper.zhu123ChartDataNews("设计总图",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger2 = danger2 + tCheckItemMapper.zhu123ChartDataNews("试生产",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger3 = danger3 + tCheckItemMapper.zhu123ChartDataNews("装置运行",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger4 = danger4 + tCheckItemMapper.zhu123ChartDataNews("设备安全",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger5 = danger5 + tCheckItemMapper.zhu123ChartDataNews("仪表安全",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger6 = danger6 + tCheckItemMapper.zhu123ChartDataNews("电气安全",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger7 = danger7 + tCheckItemMapper.zhu123ChartDataNews("应急消防",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger8 = danger8 + tCheckItemMapper.zhu123ChartDataNews("特殊管控",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger9 = danger9 + tCheckItemMapper.zhu123ChartDataNews("行为环境",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger10 = danger10 + tCheckItemMapper.zhu123ChartDataNews("生产现场",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger11 = danger11 + tCheckItemMapper.zhu123ChartDataNews("公辅工程",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger12 = danger12 + tCheckItemMapper.zhu123ChartDataNews("特种设备",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger13 = danger13 + tCheckItemMapper.zhu123ChartDataNews("专项行业",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger14 = danger14 + tCheckItemMapper.zhu123ChartDataNews("生产工艺",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger15 = danger15 + tCheckItemMapper.zhu123ChartDataNews("设备设施",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger16 = danger16 + tCheckItemMapper.zhu123ChartDataNews("危化管理",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger17 = danger17 + tCheckItemMapper.zhu123ChartDataNews("安全设施",(String) map.get("user_name"),(Integer) map.get("user_id"));

            danger18 = danger18 + tCheckItemMapper.zhu123ChartDataNews("其他",(String) map.get("user_name"),(Integer) map.get("user_id"));

            if (null == list || list.size() == 0){

            }else if (null != list && list.size() != 0){
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).get("name").equals(company.getUserId())){
                        continue;
                    }else {
                        danger19 = danger19 + tCheckItemMapper.zhuChartData123("基础管理", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger1 = danger1 + tCheckItemMapper.zhuChartData123("设计总图", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger2 = danger2 + tCheckItemMapper.zhuChartData123("试生产", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger3 = danger3 + tCheckItemMapper.zhuChartData123("装置运行", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger4 = danger4 + tCheckItemMapper.zhuChartData123("设备安全", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger5 = danger5 + tCheckItemMapper.zhuChartData123("仪表安全", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger6 = danger6 + tCheckItemMapper.zhuChartData123("电气安全", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger7 = danger7 + tCheckItemMapper.zhuChartData123("应急消防", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger8 = danger8 + tCheckItemMapper.zhuChartData123("特殊管控", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger9 = danger9 + tCheckItemMapper.zhuChartData123("行为环境", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger10 = danger10 + tCheckItemMapper.zhuChartData123("生产现场", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger11 = danger11 + tCheckItemMapper.zhuChartData123("公辅工程", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger12 = danger12 + tCheckItemMapper.zhuChartData123("特种设备", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger13 = danger13 + tCheckItemMapper.zhuChartData123("专项行业", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger14 = danger14 + tCheckItemMapper.zhuChartData123("生产工艺", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger15 = danger15 + tCheckItemMapper.zhuChartData123("设备设施", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger16 = danger16 + tCheckItemMapper.zhuChartData123("危化管理", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger17 = danger17 + tCheckItemMapper.zhuChartData123("安全设施", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据

                        danger18 = danger18 + tCheckItemMapper.zhuChartData123("其他", (String) list.get(i).get("name"), (Integer) map.get("user_id")); // 生产工艺 隐患数据
                    }

                }
            }
            map2 = new HashMap<>();
            map2.put("name", (String) map.get("user_name"));
            map2.put("danger19", danger19);
            map2.put("danger1", danger1);
            map2.put("danger2", danger2);
            map2.put("danger3", danger3);
            map2.put("danger4", danger4);
            map2.put("danger5", danger5);
            map2.put("danger6", danger6);
            map2.put("danger7", danger7);
            map2.put("danger8", danger8);
            map2.put("danger9", danger9);
            map2.put("danger10", danger10);
            map2.put("danger11", danger11);
            map2.put("danger12", danger12);
            map2.put("danger13", danger13);
            map2.put("danger14", danger14);
            map2.put("danger15", danger15);
            map2.put("danger16", danger16);
            map2.put("danger17", danger17);
            map2.put("danger18", danger18);
            sumS = sumS + danger19;
            sumA = sumA + danger1;
            sumB = sumB + danger2;
            sumC = sumC + danger3;
            sumD = sumD + danger4;
            sumE = sumE + danger5;
            sumF = sumF + danger6;
            sumG = sumG + danger7;
            sumH = sumH + danger8;
            sumI = sumI + danger9;
            sumJ = sumJ + danger10;
            sumK = sumK + danger11;
            sumL = sumL + danger12;
            sumM = sumM + danger13;
            sumN = sumN + danger14;
            sumO = sumO + danger15;
            sumP = sumP + danger16;
            sumQ = sumQ + danger17;
            sumR = sumR + danger18;
            danger20 = danger19 + danger1 + danger2 + danger3 + danger4 + danger5 + danger6 + danger7 + danger8 + danger9 + danger10 + danger11 + danger12 + danger13 + danger14 +danger15 + danger16 + danger17 + danger18;
            map2.put("danger20", danger20);
            companylist.add(map2);
            addSortElement(sortlist,(String) map.get("user_name"), danger20);
        }
        findSort1(0, companylist, sortlist);
        Integer count = sumS + sumA + sumB + sumC + sumD +sumE + sumF + sumG + sumH +sumI + sumJ + sumK + sumL + sumM + sumN + sumO + sumP + sumQ + sumR;
        DecimalFormat df = new DecimalFormat("0.00");
        map2 = new HashMap<>();
        selectCount1(map2, sumA, sumB, sumC, sumD, sumE, sumF, sumG, sumH, sumI, sumJ, sumK, sumL, sumM, sumN, sumO, sumP, sumQ, sumR, sumS, count, df);
        map2.put("sum1",sumA);
        map2.put("sum2",sumB);
        map2.put("sum3",sumC);
        map2.put("sum4",sumD);
        map2.put("sum5",sumE);
        map2.put("sum6",sumF);
        map2.put("sum7",sumG);
        map2.put("sum8",sumH);
        map2.put("sum9",sumI);
        map2.put("sum10",sumJ);
        map2.put("sum11",sumK);
        map2.put("sum12",sumL);
        map2.put("sum13",sumM);
        map2.put("sum14",sumN);
        map2.put("sum15",sumO);
        map2.put("sum16",sumP);
        map2.put("sum17",sumQ);
        map2.put("sum18",sumR);
        map2.put("sum19",sumS);
        companylist.add(map2);
        model.addAttribute("data",new Date());
        model.addAttribute("list",companylist);
        return "steel/danger/danger1/zhuChartData123";
    }
    /**
     * create by  : 小明！！！
     * description: TODO 应急管理中心页面跳转
     * create time: 2019/8/5 13:54
     */
    @RequestMapping("/tables/yjmanage_center")
    public String yjmanage_center(Model model, HttpServletRequest request) throws Exception {

        User user = getLoginUser(request);
        Company c = companyMapper.selectByPrimaryKey(user.getId());
        List<Integer> count = userService.selectCount(new CompanyListReqDTO(), user);

        model.addAttribute("count", count);
        model.addAttribute("c", c);
        model.addAttribute("userName", userMapper.selectByPrimaryKey(user.getId()).getUserName());

        return "steel/rescue/yjmanage_center";
    }

    /**
     * 跳入应急响应模块
     *
     * @return
     */
    @RequestMapping("/searchPage2")
    public String searchPage2(Model model, HttpServletRequest request) {
        User user = getLoginUser(request);
        model.addAttribute("userId", user.getId());
        return "steel/rescue/threeLeft2";
    }

    /**
     * create by  : 小明！！！
     * description: TODO 政府端 绩效分析表格数据 行业  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     * create time: 2019/8/26 9:25
     */
    @RequestMapping(value = "zf-performance-industry")
    public String performance(HttpServletRequest request, Model model, Integer flag) {
        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        if (null == flag) {
            flag = 1;
        }

        List<Map<String, Object>> list = null;
        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;
        list = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                Company company = companyMapper.selectByPrimaryKey((Integer) list.get(i).get("user_id"));
                list.get(i).put("name", company.getName());
                Integer a = tCheckMapper.zfPerformanceCount22(flag, "化工", (Integer) list.get(i).get("user_id")); // 化工行业 数据
                list.get(i).put("danger1", a);
                sum1 += a;

                Integer b = tCheckMapper.zfPerformanceCount22(flag, "冶金", (Integer) list.get(i).get("user_id")); // 冶金行业 数据
                list.get(i).put("danger2", b);
                sum2 += b;

                Integer c = tCheckMapper.zfPerformanceCount22(flag, "有色", (Integer) list.get(i).get("user_id")); // 有色行业 数据
                list.get(i).put("danger3", c);
                sum3 += c;

                Integer d = tCheckMapper.zfPerformanceCount22(flag, "建材", (Integer) list.get(i).get("user_id")); // 建材行业 数据
                list.get(i).put("danger4", d);
                sum4 += d;

                Integer e = tCheckMapper.zfPerformanceCount22(flag, "机械", (Integer) list.get(i).get("user_id")); // 机械行业 数据
                list.get(i).put("danger5", e);
                sum5 += e;

                Integer f = tCheckMapper.zfPerformanceCount22(flag, "轻工", (Integer) list.get(i).get("user_id")); // 轻工行业 数据
                list.get(i).put("danger6", f);
                sum6 += f;

                Integer g = tCheckMapper.zfPerformanceCount22(flag, "纺织", (Integer) list.get(i).get("user_id")); // 纺织行业 数据
                list.get(i).put("danger7", g);
                sum7 += g;

                Integer h = tCheckMapper.zfPerformanceCount22(flag, "商贸", (Integer) list.get(i).get("user_id")); // 商贸行业 数据
                list.get(i).put("danger8", h);
                sum8 += h;

                Integer i1 = tCheckMapper.zfPerformanceCount22(flag, "烟花", (Integer) list.get(i).get("user_id")); // 烟花行业 数据
                list.get(i).put("danger9", i1);
                sum9 += i1;

                Integer j = tCheckMapper.zfPerformanceCount33(flag, (Integer) list.get(i).get("user_id")); // 其他行业 数据
                list.get(i).put("danger10", j);
                sum10 += j;

                Integer count = a + b + c + d + e + f + g + h + i1 + j;
                list.get(i).put("danger11", count);  // 某个公司的所有行业合计


            }
        }

        Map<String, Object> map = new HashMap<>();

        Integer count = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10;

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != count && 0 != count) {

            if (null != sum1 && 0 != sum1) {
                String str = df.format((float) (sum1 / count * 100));
                map.put("result1", str + "%");

            } else {
                map.put("result1", "0.00%");  // 化工 占比数据
            }

            if (null != sum2 && 0 != sum2) {
                String str = df.format((float) (sum2 / count * 100));
                map.put("result2", str + "%");

            } else {
                map.put("result2", "0.00%"); // 冶金 占比数据
            }

            if (null != sum3 && 0 != sum3) {
                String str = df.format((float) (sum3 / count * 100));
                map.put("result3", str + "%");

            } else {
                map.put("result3", "0.00%");  // 有色 占比数据 竖
            }

            if (null != sum4 && 0 != sum4) {
                String str = df.format((float) (sum4 / count * 100));
                map.put("result4", str + "%");

            } else {
                map.put("result4", "0.00%"); // 建材 占比数据
            }

            if (null != sum5 && 0 != sum5) {
                String str = df.format((float) (sum5 / count * 100));
                map.put("result5", str + "%");

            } else {
                map.put("result5", "0.00%"); // 机械 占比数据
            }

            if (null != sum6 && 0 != sum6) {
                String str = df.format((float) (sum6 / count * 100));
                map.put("result6", str + "%");

            } else {
                map.put("result6", "0.00%"); // 轻工 占比数据
            }

            if (null != sum7 && 0 != sum7) {
                String str = df.format((float) (sum7 / count * 100));
                map.put("result7", str + "%");

            } else {
                map.put("result7", "0.00%"); // 纺织 占比数据
            }

            if (null != sum8 && 0 != sum8) {
                String str = df.format((float) (sum8 / count * 100));
                map.put("result8", str + "%");

            } else {
                map.put("result8", "0.00%");// 商贸 占比数据
            }

            if (null != sum9 && 0 != sum9) {
                String str = df.format((float) (sum9 / count * 100));
                map.put("result9", str + "%");

            } else {
                map.put("result9", "0.00%"); // 烟花 占比数据
            }

            if (null != sum10 && 0 != sum10) {
                String str = df.format((float) (sum10 / count * 100));
                map.put("result10", str + "%");

            } else {
                map.put("result10", "0.00%"); // 其他 占比数据
            }

        } else {
            // 占比 坚
            map.put("result1", "0.00%");
            map.put("result2", "0.00%");
            map.put("result3", "0.00%");
            map.put("result4", "0.00%");
            map.put("result5", "0.00%");
            map.put("result6", "0.00%");
            map.put("result7", "0.00%");
            map.put("result8", "0.00%");
            map.put("result9", "0.00%");
            map.put("result10", "0.00%");
        }

        // 合计 坚
        map.put("sum1", sum1);
        map.put("sum2", sum2);
        map.put("sum3", sum3);
        map.put("sum4", sum4);
        map.put("sum5", sum5);
        map.put("sum6", sum6);
        map.put("sum7", sum7);
        map.put("sum8", sum8);
        map.put("sum9", sum9);
        map.put("sum10", sum10);

        list.add(map);

        model.addAttribute("data", new Date());
        model.addAttribute("list", list);

        return "steel/company/evaluate/villageDown/zf-performance-industry-charts1";
    }

    @RequestMapping(value = "zf-check-hidden-trouble")
    public String zfHiddenTrouble(HttpServletRequest request, Model model, Integer flag) {
        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        if (null == flag) {
            flag = 1;
        }
        List<Map<String, Object>> list = null;
        Map<String, Object> map = new HashMap<>();

        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;
        Integer sum11 = 0;
        Integer sum12 = 0;
        Integer sum13 = 0;
        Integer sum14 = 0;
        Integer sum15 = 0;
        Integer sum16 = 0;
        Integer sum17 = 0;
        Integer sum18 = 0;
        Integer sum19 = 0;

        list = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                Company company = companyMapper.selectByPrimaryKey((Integer) list.get(i).get("user_id"));
                list.get(i).put("name", company.getName());

                Integer s = tCheckItemMapper.zfHiddenTrouble22("基础管理", flag, (Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                list.get(i).put("danger19", s);
                sum19 += s;

                Integer a = tCheckItemMapper.zfHiddenTrouble22("设计总图", flag, (Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                list.get(i).put("danger1", a);
                sum1 += a;

                Integer b = tCheckItemMapper.zfHiddenTrouble22("试生产", flag, (Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                list.get(i).put("danger2", b);
                sum2 += b;

                Integer c = tCheckItemMapper.zfHiddenTrouble22("装置运行", flag, (Integer) list.get(i).get("user_id")); // 设备设施 隐患数据
                list.get(i).put("danger3", c);
                sum3 += c;

                Integer d = tCheckItemMapper.zfHiddenTrouble22("设备安全", flag, (Integer) list.get(i).get("user_id")); // 公用工程 隐患数据
                list.get(i).put("danger4", d);
                sum4 += d;

                Integer e = tCheckItemMapper.zfHiddenTrouble22("仪表安全", flag, (Integer) list.get(i).get("user_id")); // 特种设备 隐患数据
                list.get(i).put("danger5", e);
                sum5 += e;

                Integer f = tCheckItemMapper.zfHiddenTrouble22("电气安全", flag, (Integer) list.get(i).get("user_id")); // 生产现场 隐患数据
                list.get(i).put("danger6", f);
                sum6 += f;

                Integer g = tCheckItemMapper.zfHiddenTrouble22("应急消防", flag, (Integer) list.get(i).get("user_id")); // 行为环境 隐患数据
                list.get(i).put("danger7", g);
                sum7 += g;

                Integer h = tCheckItemMapper.zfHiddenTrouble22("特殊管控", flag, (Integer) list.get(i).get("user_id")); // 危化管理 隐患数据
                list.get(i).put("danger8", h);
                sum8 += h;

                Integer i1 = tCheckItemMapper.zfHiddenTrouble22("行为环境", flag, (Integer) list.get(i).get("user_id")); // 消防安全 隐患数据
                list.get(i).put("danger9", i1);
                sum9 += i1;

                Integer j = tCheckItemMapper.zfHiddenTrouble22("生产安全", flag, (Integer) list.get(i).get("user_id")); // 用电安全 隐患数据
                list.get(i).put("danger10", j);
                sum10 += j;

                Integer k = tCheckItemMapper.zfHiddenTrouble22("公辅工程", flag, (Integer) list.get(i).get("user_id")); // 安全设施 隐患数据
                list.get(i).put("danger11", k);
                sum11 += k;

                Integer l = tCheckItemMapper.zfHiddenTrouble22("特种设备", flag, (Integer) list.get(i).get("user_id")); // 防雷静电 隐患数据
                list.get(i).put("danger12", l);
                sum12 += l;

                Integer m = tCheckItemMapper.zfHiddenTrouble22("专项行业", flag, (Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                list.get(i).put("danger13", m);
                sum13 += m;

                Integer n = tCheckItemMapper.zfHiddenTrouble22("生产工艺", flag, (Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                list.get(i).put("danger14", m);
                sum14 += m;

                Integer o = tCheckItemMapper.zfHiddenTrouble22("设备设施", flag, (Integer) list.get(i).get("user_id")); // 其他 隐患数据
                list.get(i).put("danger15", o);
                sum15 += o;


                Integer p = tCheckItemMapper.zfHiddenTrouble22("危化管理", flag, (Integer) list.get(i).get("user_id")); // 专项行业 隐患数据
                list.get(i).put("danger16", p);
                sum16 += p;

                Integer q = tCheckItemMapper.zfHiddenTrouble22("安全设施", flag, (Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                list.get(i).put("danger17", q);
                sum17 += q;

                Integer r = tCheckItemMapper.zfHiddenTrouble22("其他", flag, (Integer) list.get(i).get("user_id")); // 其他 隐患数据
                list.get(i).put("danger18", r);
                sum18 += r;


                Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                list.get(i).put("danger20", count);  // 某个车间的所有种类隐患的合计
            }
        }

        Integer count = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 + sum11 + sum12 + sum13 + sum14 + sum15 + sum16 + sum17 + sum18 + sum19;

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != count && 0 != count) {

            if (null != sum1 && 0 != sum1) {
                String str = df.format((float) (sum1 / count * 100));
                map.put("result1", str + "%");

            } else {
                map.put("result1", "0.00%");  // 基础管理 占比数据 竖
            }

            if (null != sum2 && 0 != sum2) {
                String str = df.format((float) (sum2 / count * 100));
                map.put("result2", str + "%");

            } else {
                map.put("result2", "0.00%"); // 生产工艺 占比数据 竖
            }

            if (null != sum3 && 0 != sum3) {
                String str = df.format((float) (sum3 / count * 100));
                map.put("result3", str + "%");

            } else {
                map.put("result3", "0.00%");  // 设备设施 占比数据 竖
            }

            if (null != sum4 && 0 != sum4) {
                String str = df.format((float) (sum4 / count * 100));
                map.put("result4", str + "%");

            } else {
                map.put("result4", "0.00%"); // 公用工程 占比数据 竖
            }

            if (null != sum5 && 0 != sum5) {
                String str = df.format((float) (sum5 / count * 100));
                map.put("result5", str + "%");

            } else {
                map.put("result5", "0.00%"); // 特种设备 占比数据 竖
            }

            if (null != sum6 && 0 != sum6) {
                String str = df.format((float) sum6 / count);
                map.put("result6", str + "%");

            } else {
                map.put("result6", "0.00%"); // 生产现场 占比数据 竖
            }

            if (null != sum7 && 0 != sum7) {
                String str = df.format((float) (sum7 / count * 100));
                map.put("result7", str + "%");

            } else {
                map.put("result7", "0.00%"); // 行为环境 占比数据 竖
            }

            if (null != sum8 && 0 != sum8) {
                String str = df.format((float) (sum8 / count * 100));
                map.put("result8", str + "%");

            } else {
                map.put("result8", "0.00%");// 危化管理 占比数据 竖
            }

            if (null != sum9 && 0 != sum9) {
                String str = df.format((float) (sum9 / count * 100));
                map.put("result9", str + "%");

            } else {
                map.put("result9", "0.00%"); // 消防安全 占比数据 竖
            }

            if (null != sum10 && 0 != sum10) {
                String str = df.format((float) (sum10 / count * 100));
                map.put("result10", str + "%");

            } else {
                map.put("result10", "0.00%"); // 用电安全 占比数据 竖
            }

            if (null != sum11 && 0 != sum11) {
                String str = df.format((float) (sum11 / count * 100));
                map.put("result11", str + "%");

            } else {
                map.put("result11", "0.00%"); // 安全设施 占比数据 竖
            }

            if (null != sum12 && 0 != sum12) {
                String str = df.format((float) (sum12 / count * 100));
                map.put("result12", str + "%");

            } else {
                map.put("result12", "0.00%"); // 防雷静电 占比数据 竖
            }

            if (null != sum13 && 0 != sum13) {
                String str = df.format((float) (sum13 / count * 100));
                map.put("result13", str + "%");

            } else {
                map.put("result13", "0.00%"); // 职业卫生 占比数据 竖
            }

            if (null != sum14 && 0 != sum14) {
                String str = df.format((float) (sum14 / count * 100));
                map.put("result14", str + "%");

            } else {
                map.put("result14", "0.00%"); // 专项行业 占比数据 竖
            }

            if (null != sum15 && 0 != sum15) {
                String str = df.format((float) (sum15 / count * 100));
                map.put("result15", str + "%");

            } else {
                map.put("result15", "0.00%"); // 其他 占比数据 竖
            }

            if (null != sum16 && 0 != sum16) {
                String str = df.format((float) (sum16 / count * 100));
                map.put("result16", str + "%");

            } else {
                map.put("result16", "0.00%"); // 其他 占比数据 竖
            }

            if (null != sum17 && 0 != sum17) {
                String str = df.format((float) (sum17 / count * 100));
                map.put("result17", str + "%");

            } else {
                map.put("result17", "0.00%"); // 其他 占比数据 竖
            }

            if (null != sum18 && 0 != sum18) {
                String str = df.format((float) (sum18 / count * 100));
                map.put("result18", str + "%");

            } else {
                map.put("result18", "0.00%"); // 其他 占比数据 竖
            }

            if (null != sum19 && 0 != sum19) {
                String str = df.format((float) (sum19 / count * 100));
                map.put("result19", str + "%");

            } else {
                map.put("result19", "0.00%"); // 其他 占比数据 竖
            }

        } else {
            // 占比 坚
            map.put("result1", "0.00%");
            map.put("result2", "0.00%");
            map.put("result3", "0.00%");
            map.put("result4", "0.00%");
            map.put("result5", "0.00%");
            map.put("result6", "0.00%");
            map.put("result7", "0.00%");
            map.put("result8", "0.00%");
            map.put("result9", "0.00%");
            map.put("result10", "0.00%");
            map.put("result11", "0.00%");
            map.put("result12", "0.00%");
            map.put("result13", "0.00%");
            map.put("result14", "0.00%");
            map.put("result15", "0.00%");
            map.put("result16", "0.00%");
            map.put("result17", "0.00%");
            map.put("result18", "0.00%");
            map.put("result19", "0.00%");

        }
        // 合计 坚
        map.put("sum1", sum1);
        map.put("sum2", sum2);
        map.put("sum3", sum3);
        map.put("sum4", sum4);
        map.put("sum5", sum5);
        map.put("sum6", sum6);
        map.put("sum7", sum7);
        map.put("sum8", sum8);
        map.put("sum9", sum9);
        map.put("sum10", sum10);
        map.put("sum11", sum11);
        map.put("sum12", sum12);
        map.put("sum13", sum13);
        map.put("sum14", sum14);
        map.put("sum15", sum15);
        map.put("sum16", sum12);
        map.put("sum17", sum13);
        map.put("sum18", sum18);
        map.put("sum19", sum19);
        list.add(map);
        model.addAttribute("data", new Date());
        model.addAttribute("list", list);
        return "steel/company/evaluate/villageDown/zf-check-hidden-trouble-charts2";
    }

    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析 行业
     * create time: 2019/8/26 14:18
     */
    @RequestMapping(value = "zf-hidden-industry")
    public String hiddenIndustry(HttpServletRequest request, Model model, Integer flag) {

        User user = getLoginUser(request);

        StringBuilder sb = new StringBuilder();

        if (null == flag) {
            flag = 1;
        }

        List<Map<String, Object>> list = null;

        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;
        list = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                Company company = companyMapper.selectByPrimaryKey((Integer) list.get(i).get("user_id"));
                list.get(i).put("name", company.getName());

                Integer a = tCheckItemMapper.zhengFuChartDataDanger22(flag, "化工", (Integer) list.get(i).get("user_id")); // 化工行业 数据
                list.get(i).put("danger1", a);
                sum1 += a;

                Integer b = tCheckItemMapper.zhengFuChartDataDanger22(flag, "冶金", (Integer) list.get(i).get("user_id")); // 冶金行业 数据
                list.get(i).put("danger2", b);
                sum2 += b;

                Integer c = tCheckItemMapper.zhengFuChartDataDanger22(flag, "有色", (Integer) list.get(i).get("user_id")); // 有色行业 数据
                list.get(i).put("danger3", c);
                sum3 += c;

                Integer d = tCheckItemMapper.zhengFuChartDataDanger22(flag, "建材", (Integer) list.get(i).get("user_id")); // 建材行业 数据
                list.get(i).put("danger4", d);
                sum4 += d;

                Integer e = tCheckItemMapper.zhengFuChartDataDanger22(flag, "机械", (Integer) list.get(i).get("user_id")); // 机械行业 数据
                list.get(i).put("danger5", e);
                sum5 += e;

                Integer f = tCheckItemMapper.zhengFuChartDataDanger22(flag, "轻工", (Integer) list.get(i).get("user_id")); // 轻工行业 数据
                list.get(i).put("danger6", f);
                sum6 += f;

                Integer g = tCheckItemMapper.zhengFuChartDataDanger22(flag, "纺织", (Integer) list.get(i).get("user_id")); // 纺织行业 数据
                list.get(i).put("danger7", g);
                sum7 += g;

                Integer h = tCheckItemMapper.zhengFuChartDataDanger22(flag, "商贸", (Integer) list.get(i).get("user_id")); // 商贸行业 数据
                list.get(i).put("danger8", h);
                sum8 += h;

                Integer i1 = tCheckItemMapper.zhengFuChartDataDanger22(flag, "烟花", (Integer) list.get(i).get("user_id")); // 烟花行业 数据
                list.get(i).put("danger9", i1);
                sum9 += i1;

                Integer j = tCheckItemMapper.zhengFuChartDataDanger33(flag, (Integer) list.get(i).get("user_id")); // 其他行业 数据
                list.get(i).put("danger10", j);
                sum10 += j;

                Integer count = a + b + c + d + e + f + g + h + i1 + j;

                list.get(i).put("danger11", count);  // 某个公司的所有行业合计

            }
        }
        Map<String, Object> map = new HashMap<>();

        Integer count = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10;

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != count && 0 != count) {

            if (null != sum1 && 0 != sum1) {
                String str = df.format((float) (sum1 / count * 100));
                map.put("result1", str + "%");

            } else {
                map.put("result1", "0.00%");  // 化工 占比数据
            }

            if (null != sum2 && 0 != sum2) {
                String str = df.format((float) (sum2 / count * 100));
                map.put("result2", str + "%");

            } else {
                map.put("result2", "0.00%"); // 冶金 占比数据
            }

            if (null != sum3 && 0 != sum3) {
                String str = df.format((float) (sum3 / count * 100));
                map.put("result3", str + "%");

            } else {
                map.put("result3", "0.00%");  // 有色 占比数据 竖
            }

            if (null != sum4 && 0 != sum4) {
                String str = df.format((float) (sum4 / count * 100));
                map.put("result4", str + "%");

            } else {
                map.put("result4", "0.00%"); // 建材 占比数据
            }

            if (null != sum5 && 0 != sum5) {
                String str = df.format((float) (sum5 / count * 100));
                map.put("result5", str + "%");

            } else {
                map.put("result5", "0.00%"); // 机械 占比数据
            }

            if (null != sum6 && 0 != sum6) {
                String str = df.format((float) (sum6 / count * 100));
                map.put("result6", str + "%");

            } else {
                map.put("result6", "0.00%"); // 轻工 占比数据
            }

            if (null != sum7 && 0 != sum7) {
                String str = df.format((float) (sum7 / count * 100));
                map.put("result7", str + "%");

            } else {
                map.put("result7", "0.00%"); // 纺织 占比数据
            }

            if (null != sum8 && 0 != sum8) {
                String str = df.format((float) (sum8 / count * 100));
                map.put("result8", str + "%");

            } else {
                map.put("result8", "0.00%");// 商贸 占比数据
            }

            if (null != sum9 && 0 != sum9) {
                String str = df.format((float) (sum9 / count * 100));
                map.put("result9", str + "%");

            } else {
                map.put("result9", "0.00%"); // 烟花 占比数据
            }

            if (null != sum10 && 0 != sum10) {
                String str = df.format((float) (sum10 / count * 100));
                map.put("result10", str + "%");

            } else {
                map.put("result10", "0.00%"); // 其他 占比数据
            }

        } else {
            // 占比 坚
            map.put("result1", "0.00%");
            map.put("result2", "0.00%");
            map.put("result3", "0.00%");
            map.put("result4", "0.00%");
            map.put("result5", "0.00%");
            map.put("result6", "0.00%");
            map.put("result7", "0.00%");
            map.put("result8", "0.00%");
            map.put("result9", "0.00%");
            map.put("result10", "0.00%");
        }

        // 合计 坚
        map.put("sum1", sum1);
        map.put("sum2", sum2);
        map.put("sum3", sum3);
        map.put("sum4", sum4);
        map.put("sum5", sum5);
        map.put("sum6", sum6);
        map.put("sum7", sum7);
        map.put("sum8", sum8);
        map.put("sum9", sum9);
        map.put("sum10", sum10);

        list.add(map);

        model.addAttribute("data", new Date());
        model.addAttribute("list", list);

        return "steel/company/evaluate/villageDown/zf-hidden-industry-charts3-1";


    }

    /**
     * create by  : 小明！！！
     * description: TODO    政府端 隐患数据分析 风险
     * create time: 2019/8/26 11:54
     */
    @RequestMapping(value = "zf-hidden-trouble")
    public String hiddenTrouble(HttpServletRequest request, Model model, Integer flag) {
        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        if (null == flag) {
            flag = 1;
        }
        List<Map<String, Object>> list = null;

        Map<String, Object> map = new HashMap<>();

        Integer sum1 = 0;
        Integer sum2 = 0;
        Integer sum3 = 0;
        Integer sum4 = 0;
        Integer sum5 = 0;
        Integer sum6 = 0;
        Integer sum7 = 0;
        Integer sum8 = 0;
        Integer sum9 = 0;
        Integer sum10 = 0;
        Integer sum11 = 0;
        Integer sum12 = 0;
        Integer sum13 = 0;
        Integer sum14 = 0;
        Integer sum15 = 0;
        Integer sum16 = 0;
        Integer sum17 = 0;
        Integer sum18 = 0;
        Integer sum19 = 0;
        list = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                Company company = companyMapper.selectByPrimaryKey((Integer) list.get(i).get("user_id"));
                list.get(i).put("name", company.getName());

                Integer s = tCheckItemMapper.zhengFuChartData99("基础管理", flag, (Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                list.get(i).put("danger19", s);
                sum19 += s;

                Integer a = tCheckItemMapper.zhengFuChartData99("设计总图", flag, (Integer) list.get(i).get("user_id")); // 基础管理 隐患数据
                list.get(i).put("danger1", a);
                sum1 += a;

                Integer b = tCheckItemMapper.zhengFuChartData99("试生产", flag, (Integer) list.get(i).get("user_id")); // 生产工艺 隐患数据
                list.get(i).put("danger2", b);
                sum2 += b;

                Integer c = tCheckItemMapper.zhengFuChartData99("装置运行", flag, (Integer) list.get(i).get("user_id")); // 设备设施 隐患数据
                list.get(i).put("danger3", c);
                sum3 += c;

                Integer d = tCheckItemMapper.zhengFuChartData99("设备安全", flag, (Integer) list.get(i).get("user_id")); // 公用工程 隐患数据
                list.get(i).put("danger4", d);
                sum4 += d;

                Integer e = tCheckItemMapper.zhengFuChartData99("仪表安全", flag, (Integer) list.get(i).get("user_id")); // 特种设备 隐患数据
                list.get(i).put("danger5", e);
                sum5 += e;

                Integer f = tCheckItemMapper.zhengFuChartData99("电气安全", flag, (Integer) list.get(i).get("user_id")); // 生产现场 隐患数据
                list.get(i).put("danger6", f);
                sum6 += f;

                Integer g = tCheckItemMapper.zhengFuChartData99("应急消防", flag, (Integer) list.get(i).get("user_id")); // 行为环境 隐患数据
                list.get(i).put("danger7", g);
                sum7 += g;

                Integer h = tCheckItemMapper.zhengFuChartData99("特殊管控", flag, (Integer) list.get(i).get("user_id")); // 危化管理 隐患数据
                list.get(i).put("danger8", h);
                sum8 += h;

                Integer i1 = tCheckItemMapper.zhengFuChartData99("行为环境", flag, (Integer) list.get(i).get("user_id")); // 消防安全 隐患数据
                list.get(i).put("danger9", i1);
                sum9 += i1;

                Integer j = tCheckItemMapper.zhengFuChartData99("生产现场", flag, (Integer) list.get(i).get("user_id")); // 用电安全 隐患数据
                list.get(i).put("danger10", j);
                sum10 += j;

                Integer k = tCheckItemMapper.zhengFuChartData99("公辅工程", flag, (Integer) list.get(i).get("user_id")); // 安全设施 隐患数据
                list.get(i).put("danger11", k);
                sum11 += k;

                Integer l = tCheckItemMapper.zhengFuChartData99("特种设备", flag, (Integer) list.get(i).get("user_id")); // 防雷静电 隐患数据
                list.get(i).put("danger12", l);
                sum12 += l;

                Integer m = tCheckItemMapper.zhengFuChartData99("专项行业", flag, (Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                list.get(i).put("danger13", m);
                sum13 += m;

                Integer n = tCheckItemMapper.zhengFuChartData99("生产工艺", flag, (Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                list.get(i).put("danger14", n);
                sum14 += n;

                Integer o = tCheckItemMapper.zhengFuChartData99("设备设施", flag, (Integer) list.get(i).get("user_id")); // 防雷静电 隐患数据
                list.get(i).put("danger15", o);
                sum15 += o;

                Integer p = tCheckItemMapper.zhengFuChartData99("危化管理", flag, (Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                list.get(i).put("danger16", p);
                sum16 += p;

                Integer q = tCheckItemMapper.zhengFuChartData99("安全设施", flag, (Integer) list.get(i).get("user_id")); // 职业卫生 隐患数据
                list.get(i).put("danger17", q);
                sum17 += q;

                Integer r = tCheckItemMapper.zhengFuChartData99("其他", flag, (Integer) list.get(i).get("user_id")); // 其他 隐患数据
                list.get(i).put("danger18", r);
                sum18 += r;

                Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                list.get(i).put("danger20", count);  // 某个车间的所有种类隐患的合计
            }
        }
        Integer count = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 + sum11 + sum12 + sum13 + sum14 + sum15 + sum16 + sum17 + sum18 + sum19;
        ;

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != count && 0 != count) {

            if (null != sum1 && 0 != sum1) {
                String str = df.format((float) (sum1 / count * 100));
                map.put("result1", str + "%");

            } else {
                map.put("result1", "0.00%");  // 基础管理 占比数据 竖
            }

            if (null != sum2 && 0 != sum2) {
                String str = df.format((float) (sum2 / count * 100));
                map.put("result2", str + "%");

            } else {
                map.put("result2", "0.00%"); // 生产工艺 占比数据 竖
            }

            if (null != sum3 && 0 != sum3) {
                String str = df.format((float) (sum3 / count * 100));
                map.put("result3", str + "%");

            } else {
                map.put("result3", "0.00%");  // 设备设施 占比数据 竖
            }

            if (null != sum4 && 0 != sum4) {
                String str = df.format((float) (sum4 / count * 100));
                map.put("result4", str + "%");

            } else {
                map.put("result4", "0.00%"); // 公用工程 占比数据 竖
            }

            if (null != sum5 && 0 != sum5) {
                String str = df.format((float) (sum5 / count * 100));
                map.put("result5", str + "%");

            } else {
                map.put("result5", "0.00%"); // 特种设备 占比数据 竖
            }

            if (null != sum6 && 0 != sum6) {
                String str = df.format((float) (sum6 / count * 100));
                map.put("result6", str + "%");

            } else {
                map.put("result6", "0.00%"); // 生产现场 占比数据 竖
            }

            if (null != sum7 && 0 != sum7) {
                String str = df.format((float) (sum7 / count * 100));
                map.put("result7", str + "%");

            } else {
                map.put("result7", "0.00%"); // 行为环境 占比数据 竖
            }

            if (null != sum8 && 0 != sum8) {
                String str = df.format((float) (sum8 / count * 100));
                map.put("result8", str + "%");

            } else {
                map.put("result8", "0.00%");// 危化管理 占比数据 竖
            }

            if (null != sum9 && 0 != sum9) {
                String str = df.format((float) (sum9 / count * 100));
                map.put("result9", str + "%");

            } else {
                map.put("result9", "0.00%"); // 消防安全 占比数据 竖
            }

            if (null != sum10 && 0 != sum10) {
                String str = df.format((float) (sum10 / count * 100));
                map.put("result10", str + "%");

            } else {
                map.put("result10", "0.00%"); // 用电安全 占比数据 竖
            }

            if (null != sum11 && 0 != sum11) {
                String str = df.format((float) (sum11 / count * 100));
                map.put("result11", str + "%");

            } else {
                map.put("result11", "0.00%"); // 安全设施 占比数据 竖
            }

            if (null != sum12 && 0 != sum12) {
                String str = df.format((float) (sum12 / count * 100));
                map.put("result12", str + "%");

            } else {
                map.put("result12", "0.00%"); // 防雷静电 占比数据 竖
            }

            if (null != sum13 && 0 != sum13) {
                String str = df.format((float) (sum13 / count * 100));
                map.put("result13", str + "%");

            } else {
                map.put("result13", "0.00%"); // 职业卫生 占比数据 竖
            }

            if (null != sum14 && 0 != sum14) {
                String str = df.format((float) (sum14 / count * 100));
                map.put("result14", str + "%");

            } else {
                map.put("result14", "0.00%"); // 专项行业 占比数据 竖
            }

            if (null != sum15 && 0 != sum15) {
                String str = df.format((float) (sum15 / count * 100));
                map.put("result15", str + "%");

            } else {
                map.put("result15", "0.00%"); // 其他 占比数据 竖
            }

            if (null != sum16 && 0 != sum16) {
                String str = df.format((float) (sum16 / count * 100));
                map.put("result16", str + "%");

            } else {
                map.put("result16", "0.00%"); // 其他 占比数据 竖
            }

            if (null != sum17 && 0 != sum17) {
                String str = df.format((float) (sum17 / count * 100));
                map.put("result17", str + "%");

            } else {
                map.put("result17", "0.00%"); // 其他 占比数据 竖
            }

            if (null != sum18 && 0 != sum18) {
                String str = df.format((float) (sum18 / count * 100));
                map.put("result18", str + "%");

            } else {
                map.put("result18", "0.00%"); // 其他 占比数据 竖
            }

            if (null != sum19 && 0 != sum19) {
                String str = df.format((float) (sum19 / count * 100));
                map.put("result19", str + "%");

            } else {
                map.put("result19", "0.00%"); // 其他 占比数据 竖
            }

        } else {
            // 占比 坚
            map.put("result1", "0.00%");
            map.put("result2", "0.00%");
            map.put("result3", "0.00%");
            map.put("result4", "0.00%");
            map.put("result5", "0.00%");
            map.put("result6", "0.00%");
            map.put("result7", "0.00%");
            map.put("result8", "0.00%");
            map.put("result9", "0.00%");
            map.put("result10", "0.00%");
            map.put("result11", "0.00%");
            map.put("result12", "0.00%");
            map.put("result13", "0.00%");
            map.put("result14", "0.00%");
            map.put("result15", "0.00%");
            map.put("result16", "0.00%");
            map.put("result17", "0.00%");
            map.put("result18", "0.00%");
            map.put("result19", "0.00%");
        }
        // 合计 坚
        map.put("sum1", sum1);
        map.put("sum2", sum2);
        map.put("sum3", sum3);
        map.put("sum4", sum4);
        map.put("sum5", sum5);
        map.put("sum6", sum6);
        map.put("sum7", sum7);
        map.put("sum8", sum8);
        map.put("sum9", sum9);
        map.put("sum10", sum10);
        map.put("sum11", sum11);
        map.put("sum12", sum12);
        map.put("sum13", sum13);
        map.put("sum14", sum14);
        map.put("sum15", sum15);
        map.put("sum16", sum16);
        map.put("sum17", sum17);
        map.put("sum18", sum18);
        map.put("sum19", sum19);

        list.add(map);

        model.addAttribute("data", new Date());
        model.addAttribute("list", list);

        return "steel/company/evaluate/villageDown/zf-hidden-trouble-charts3-2";
    }

    /**
     * create by  : 小明！！！
     * description: TODO    隐患治理数据分析  风险
     * create time: 2019/8/26 15:10
     */
    @RequestMapping(value = "manage-hidden-company")
    public String manegeHiddenConpany(HttpServletRequest request, Model model, Integer flag) {
        User user = getLoginUser(request);

        StringBuilder sb = new StringBuilder();

        if (null == flag) {
            flag = 1;
        }

        List<Map<String, Object>> list = null;

        Map<String, Object> map = new HashMap<>();

        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;

        // 已治理合计
        Integer sign1 = 0;
        Integer sign2 = 0;
        Integer sign3 = 0;
        // 未治理合计
        Integer sign11 = 0;
        Integer sign22 = 0;
        Integer sign33 = 0;
        list = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                Company company = companyMapper.selectByPrimaryKey((Integer) list.get(i).get("user_id"));
                list.get(i).put("name", company.getName());
                Integer a = tCheckItemMapper.manageHiddenCompany11(1, (Integer) list.get(i).get("user_id"), 3); // 一般和较小 合格 已治理
                list.get(i).put("danger1", a);

                Integer a1 = tCheckItemMapper.manageHiddenCompany11(2, (Integer) list.get(i).get("user_id"), 3); // 一般和较小 不合格 未治理
                list.get(i).put("danger11", a1);

                count1 = a + a1;

                Integer b = tCheckItemMapper.manageHiddenCompany11(1, (Integer) list.get(i).get("user_id"), 1); // 较大 合格 已治理
                list.get(i).put("danger2", b);

                Integer b1 = tCheckItemMapper.manageHiddenCompany11(2, (Integer) list.get(i).get("user_id"), 1); // 较大 不合格 未治理
                list.get(i).put("danger22", b1);

                count2 = b + b1;

                Integer c = tCheckItemMapper.manageHiddenCompany11(1, (Integer) list.get(i).get("user_id"), 2); // 重大 合格 已治理
                list.get(i).put("danger3", c);

                Integer c1 = tCheckItemMapper.manageHiddenCompany11(1, (Integer) list.get(i).get("user_id"), 2); // 重大 不合格 未治理
                list.get(i).put("danger33", c1);

                count3 = c + c1;

                sign1 += a;
                sign2 += b;
                sign3 += c;

                sign11 += a1;
                sign22 += b1;
                sign33 += c1;

                DecimalFormat df = new DecimalFormat("0.00");

                Integer sum = count1 + count2 + count3;

                if (null != count1 && count1 != 0) {  // 一般和较小 治理率
                    String str = df.format((float) (a / count1 * 100));
                    list.get(i).put("result11", str + "%");
                } else {
                    list.get(i).put("result11", 0.00);
                }

                if (null != count2 && count2 != 0) { // 较大 治理率
                    String str = df.format((float) (b / count2 * 100));
                    list.get(i).put("result22", str + "%");
                } else {
                    list.get(i).put("result22", 0.00);
                }

                if (null != count3 && count3 != 0) { // 重大 治理率
                    String str = df.format((float) (c / count3 * 100));
                    list.get(i).put("result33", str + "%");
                } else {
                    list.get(i).put("result33", 0.00);
                }

            }
        }

        DecimalFormat df = new DecimalFormat("0.00");

        Integer sum1 = sign1 + sign11;
        Integer sum2 = sign2 + sign22;
        Integer sum3 = sign3 + sign33;

        if (null != sum1 && 0 != sum1) {

            if (null != sign1 && 0 != sign1) {
                String str = df.format((float) (sign1 / sum1 * 100));
                map.put("result1", str + "%"); // 一般隐患的治理率 竖

            } else {
                map.put("result1", "0.00%"); // 一般隐患的治理率 竖
            }
        } else {
            map.put("result1", "0.00%"); // 一般隐患的治理率 竖
        }

        if (null != sum2 && 0 != sum2) {

            if (null != sign2 && 0 != sign2) {
                String str = df.format((float) (sign2 / sum2 * 100));
                map.put("result2", str + "%"); // 较大隐患的治理率 竖

            } else {
                map.put("result2", "0.00%"); // 较大隐患的治理率 竖
            }

        } else {
            map.put("result2", "0.00%"); // 较大隐患的治理率 竖
        }

        if (null != sum3 && 0 != sum3) {

            if (null != sign3 && 0 != sign3) {
                String str = df.format((float) (sign3 / sum3 * 100));
                map.put("result3", str + "%"); // 重大隐患的治理率 竖

            } else {
                map.put("result3", "0.00%"); // 重大隐患的治理率 竖
            }

        } else {
            map.put("result3", "0.00%"); // 重大隐患的治理率 竖
        }

        map.put("sign1", sign1); // 一般隐患 已治理 合计 竖
        map.put("sign2", sign2); // 较大隐患 已治理 合计 竖
        map.put("sign3", sign3); // 重大隐患 已治理 合计 竖
        map.put("sign11", sign11); // 一般隐患 未治理 合计 竖
        map.put("sign22", sign22); // 较大隐患 未治理 合计 竖
        map.put("sign33", sign33); // 重大隐患 未治理 合计 竖


        list.add(map);

        model.addAttribute("data", new Date());
        model.addAttribute("list", list);

        return "steel/company/evaluate/villageDown/manage-hidden-company-charts4-1";

    }

    /**
     * create by  : 小明！！！
     * description: TODO    隐患治理数据分析 风险
     * create time: 2019/8/26 15:10
     */
    @RequestMapping(value = "manage-hidden-danger")
    public String manegeHiddenDanger(HttpServletRequest request, Model model, Integer flag) {

        User user = getLoginUser(request);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        String string = "基础管理,生产工艺,设备设施,公用工程,特种设备,生产现场,行为环境,危化管理,消防安全,用电安全,安全设施,防雷静电,职业卫生,专项行业,其他";

        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());

        StringBuilder sb = new StringBuilder();

        StringBuilder sb1 = new StringBuilder();

        if (null == flag) {
            flag = 1;
        }

        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }

        String[] strs = string.split(",");

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            Map<String, Object> map1 = new HashMap<>();
            map1.put("danger", strs[i]);

            list.add(map1);
        }

        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;

        // 已治理合计
        Integer sign1 = 0;
        Integer sign2 = 0;
        Integer sign3 = 0;
        // 未治理合计
        Integer sign11 = 0;
        Integer sign22 = 0;
        Integer sign33 = 0;
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {

                Integer a = 0;
                Integer a1 = 0;
                Integer b = 0;
                Integer b1 = 0;
                Integer c = 0;
                Integer c1 = 0;

                if (null != list.get(i).get("danger") && list.get(i).get("danger").equals("其他")) {

                    a = tCheckItemMapper.manageHiddenDanger11(1, 3, sb.toString()); // 一般和较小 合格 已治理
                    list.get(i).put("danger1", a);

                    a1 = tCheckItemMapper.manageHiddenDanger11(2, 3, sb.toString()); // 一般和较小 不合格 未治理
                    list.get(i).put("danger11", a1);

                    b = tCheckItemMapper.manageHiddenDanger11(1, 1, sb.toString()); // 较大 合格 已治理
                    list.get(i).put("danger2", b);

                    b1 = tCheckItemMapper.manageHiddenDanger11(2, 1, sb.toString()); // 较大 不合格 未治理
                    list.get(i).put("danger22", b1);

                    c = tCheckItemMapper.manageHiddenDanger11(1, 2, sb.toString()); // 重大 合格 已治理
                    list.get(i).put("danger3", c);

                    c1 = tCheckItemMapper.manageHiddenDanger11(1, 2, sb.toString()); // 重大 不合格 未治理
                    list.get(i).put("danger33", c1);

                } else if (null != list.get(i).get("danger")) {

                    a = tCheckItemMapper.manageHiddenDanger(1, (String) list.get(i).get("danger"), 3, sb.toString()); // 一般和较小 合格 已治理
                    list.get(i).put("danger1", a);

                    a1 = tCheckItemMapper.manageHiddenDanger(2, (String) list.get(i).get("danger"), 3, sb.toString()); // 一般和较小 不合格 未治理
                    list.get(i).put("danger11", a1);

                    b = tCheckItemMapper.manageHiddenDanger(1, (String) list.get(i).get("danger"), 1, sb.toString()); // 较大 合格 已治理
                    list.get(i).put("danger2", b);

                    b1 = tCheckItemMapper.manageHiddenDanger(2, (String) list.get(i).get("danger"), 1, sb.toString()); // 较大 不合格 未治理
                    list.get(i).put("danger22", b1);

                    c = tCheckItemMapper.manageHiddenDanger(1, (String) list.get(i).get("danger"), 2, sb.toString()); // 重大 合格 已治理
                    list.get(i).put("danger3", c);

                    c1 = tCheckItemMapper.manageHiddenDanger(1, (String) list.get(i).get("danger"), 2, sb.toString()); // 重大 不合格 未治理
                    list.get(i).put("danger33", c1);
                }

                count1 = a + a1;
                count2 = b + b1;
                count3 = c + c1;

                sign1 += a;
                sign2 += b;
                sign3 += c;

                sign11 += a1;
                sign22 += b1;
                sign33 += c1;

                DecimalFormat df = new DecimalFormat("0.00");

                Integer sum = count1 + count2 + count3; // 单个风险所包含的所有隐患

                if (null != count1 && count1 != 0) {  // 一般和较小 治理率
                    String str = df.format((float) (a / count1 * 100));
                    list.get(i).put("result11", str + "%");
                } else {
                    list.get(i).put("result11", 0.00);
                }

                if (null != count2 && count2 != 0) { // 较大 治理率
                    String str = df.format((float) (b / count2 * 100));
                    list.get(i).put("result22", str + "%");
                } else {
                    list.get(i).put("result22", 0.00);
                }

                if (null != count3 && count3 != 0) { // 重大 治理率
                    String str = df.format((float) (c / count3 * 100));
                    list.get(i).put("result33", str + "%");
                } else {
                    list.get(i).put("result33", 0.00);
                }

            }
        }
        DecimalFormat df = new DecimalFormat("0.00");

        Integer sum1 = sign1 + sign11;  // 所有风险  一般隐患的总和

        Integer sum2 = sign2 + sign22;  // 所有风险  较大隐患的总和

        Integer sum3 = sign3 + sign33;  // 所有风险  重大隐患的总和

        if (null != sum1 && sum1 != 0) {  // 一般和较小 治理率
            String str = df.format((float) (sign1 / sum1 * 100));
            map.put("number111", str + "%");
        } else {
            map.put("number111", 0.00);
        }

        if (null != sum2 && sum2 != 0) { // 较大 治理率
            String str = df.format((float) (sign2 / sum2 * 100));
            map.put("number222", str + "%");
        } else {
            map.put("number222", 0.00);
        }

        if (null != sum3 && sum3 != 0) { // 重大 治理率
            String str = df.format((float) (sign3 / sum3 * 100));
            map.put("number333", str + "%");
        } else {
            map.put("number222", 0.00);
        }

        map.put("sign1", sign1);
        map.put("sign2", sign2);
        map.put("sign3", sign3);
        map.put("sign11", sign11);
        map.put("sign22", sign22);
        map.put("sign33", sign33);

        list.add(map);

        model.addAttribute("data", new Date());
        model.addAttribute("list", list);
        return "steel/company/evaluate/villageDown/manage-hidden-danger-charts4-2";

    }

    /**
     * create by  : 小明！！！
     * description: TODO 隐患治理数据分析  行业
     * create time: 2019/8/27 9:04
     */
    @RequestMapping(value = "manage-hidden-industry")
    public String manegeHiddenIndustry(HttpServletRequest request, Model model) {
        User user = getLoginUser(request);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());

        StringBuilder sb = new StringBuilder();

        StringBuilder sb1 = new StringBuilder();

        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }

        String string = "化工,冶金,有色,建材,机械,轻工,纺织,商贸,烟花,其他";

        String[] strs = string.split(",");

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            Map<String, Object> map1 = new HashMap<>();

            map1.put("industry", strs[i]);

            list.add(map1);
        }

        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;

        // 已治理合计
        Integer sign1 = 0;
        Integer sign2 = 0;
        Integer sign3 = 0;
        // 未治理合计
        Integer sign11 = 0;
        Integer sign22 = 0;
        Integer sign33 = 0;
        for (int i = 0; i < list.size(); i++) {

            Integer a = 0;
            Integer a1 = 0;
            Integer b = 0;
            Integer b1 = 0;
            Integer c = 0;
            Integer c1 = 0;

            if (null != list.get(i).get("industry") && list.get(i).get("industry").equals("其他")) {

                a = tCheckItemMapper.manageHiddenIndustry11(1, 3, sb.toString()); // 一般和较小 合格 已治理
                list.get(i).put("industry1", a);

                a1 = tCheckItemMapper.manageHiddenIndustry11(2, 3, sb.toString()); // 一般和较小 不合格 未治理
                list.get(i).put("industry11", a1);

                b = tCheckItemMapper.manageHiddenIndustry11(1, 1, sb.toString()); // 较大 合格 已治理
                list.get(i).put("industry2", b);

                b1 = tCheckItemMapper.manageHiddenIndustry11(2, 1, sb.toString()); // 较大 不合格 未治理
                list.get(i).put("industry22", b1);

                c = tCheckItemMapper.manageHiddenIndustry11(1, 2, sb.toString()); // 重大 合格 已治理
                list.get(i).put("industry3", c);

                c1 = tCheckItemMapper.manageHiddenIndustry11(1, 2, sb.toString()); // 重大 不合格 未治理
                list.get(i).put("industry33", c1);

            } else if (null != list.get(i).get("industry")) {

                a = tCheckItemMapper.manageHiddenIndustry(1, (String) list.get(i).get("industry"), 3, sb.toString()); // 一般和较小 合格 已治理
                list.get(i).put("industry1", a);

                a1 = tCheckItemMapper.manageHiddenIndustry(2, (String) list.get(i).get("industry"), 3, sb.toString()); // 一般和较小 不合格 未治理
                list.get(i).put("industry11", a1);

                b = tCheckItemMapper.manageHiddenIndustry(1, (String) list.get(i).get("industry"), 1, sb.toString()); // 较大 合格 已治理
                list.get(i).put("industry2", b);

                b1 = tCheckItemMapper.manageHiddenIndustry(2, (String) list.get(i).get("industry"), 1, sb.toString()); // 较大 不合格 未治理
                list.get(i).put("industry22", b1);

                c = tCheckItemMapper.manageHiddenIndustry(1, (String) list.get(i).get("industry"), 2, sb.toString()); // 重大 合格 已治理
                list.get(i).put("industry3", c);

                c1 = tCheckItemMapper.manageHiddenIndustry(1, (String) list.get(i).get("industry"), 2, sb.toString()); // 重大 不合格 未治理
                list.get(i).put("industry33", c1);
            }

            count1 = a + a1;
            count2 = b + b1;
            count3 = c + c1;

            sign1 += a;
            sign2 += b;
            sign3 += c;

            sign11 += a1;
            sign22 += b1;
            sign33 += c1;

            DecimalFormat df = new DecimalFormat("0.00");

            Integer sum = count1 + count2 + count3; // 单个风险所包含的所有隐患

            if (null != count1 && count1 != 0) {  // 一般和较小 治理率
                String str = df.format((float) (a / count1 * 100));
                list.get(i).put("result11", str + "%");
            } else {
                list.get(i).put("result11", 0.00);
            }

            if (null != count2 && count2 != 0) { // 较大 治理率
                String str = df.format((float) (b / count2 * 100));
                list.get(i).put("result22", str + "%");
            } else {
                list.get(i).put("result22", 0.00);
            }

            if (null != count3 && count3 != 0) { // 重大 治理率
                String str = df.format((float) (c / count3 * 100));
                list.get(i).put("result33", str + "%");
            } else {
                list.get(i).put("result33", 0.00);
            }

        }

        DecimalFormat df = new DecimalFormat("0.00");

        Integer sum1 = sign1 + sign11;  // 所有风险  一般隐患的总和

        Integer sum2 = sign2 + sign22;  // 所有风险  较大隐患的总和

        Integer sum3 = sign3 + sign33;  // 所有风险  重大隐患的总和

        if (null != sum1 && sum1 != 0) {  // 一般和较小 治理率
            String str = df.format((float) (sign1 / sum1 * 100));
            map.put("number111", str + "%");
        } else {
            map.put("number111", 0.00);
        }

        if (null != sum2 && sum2 != 0) { // 较大 治理率
            String str = df.format((float) (sign2 / sum2 * 100));
            map.put("number222", str + "%");
        } else {
            map.put("number222", 0.00);
        }

        if (null != sum3 && sum3 != 0) { // 重大 治理率
            String str = df.format((float) (sign3 / sum3 * 100));
            map.put("number333", str + "%");
        } else {
            map.put("number333", 0.00);
        }

        map.put("sign1", sign1);
        map.put("sign2", sign2);
        map.put("sign3", sign3);
        map.put("sign11", sign11);
        map.put("sign22", sign22);
        map.put("sign33", sign33);

        list.add(map);

        model.addAttribute("data", new Date());
        model.addAttribute("list", list);

        return "steel/company/evaluate/villageDown/manage-hidden-industry-charts4-3";


    }

    /**
     * 行业端——集团企业首页——企业数量——跳转到企业页面
     */
    @RequestMapping(value = "move/company")
    public String move(HttpServletRequest request, Model model, Integer uid) throws Exception {
        User user = getLoginUser(request); //用户登录
        if (user.getUserType() != 5) { //当用户不是企业类型时
            HttpSession session = request.getSession();
            //log.error("moveBefore："+user.toString());
            session.setAttribute("govUser", user); //跳转前user保存
            user = userMapper.selectByPrimaryKey(uid);
            SessionUtil.setUser(request, user);
        }
        model.addAttribute("userName", companyMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("loginUserId", user.getId());
        model.addAttribute("flage", 2);
        return "company/main";
    }

    @RequestMapping(value = "safety-system/statistics-list")
    public String statisticsList(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<Map<String, Object>> branchs = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId()); //获取所有分厂信息
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", "总部");
        branchs.add(map1);
        //将分厂的id变成一个集合
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < branchs.size(); i++) {

            if (i == branchs.size() - 1) {
                sb.append("'").append(branchs.get(i).get("user_id")).append("'");
            } else {
                sb.append("'").append(branchs.get(i).get("user_id")).append("',");
            }

        }
        List<Map<String, Object>> list = aCompanyManualMapper.findManualLevel(sb.toString());
        String[] industrys = new String[branchs.size()];
        Integer[] gg1 = new Integer[branchs.size()];//红
        Integer[] gg2 = new Integer[branchs.size()];//橙
        Integer[] gg3 = new Integer[branchs.size()];//黄
        Integer[] gg4 = new Integer[branchs.size()];//蓝
        Integer[] flag1 = new Integer[]{0, 0, 0, 0};//部位 数值顺序对应 红橙黄蓝
        for (int i = 0; i < branchs.size(); i++) {
            industrys[i] = (String) branchs.get(i).get("user_name");
            gg1[i] = 0;
            gg2[i] = 0;
            gg3[i] = 0;
            gg4[i] = 0;
        }
        Gson gson = new Gson();
        if (0L == list.size()) { //当查询为空时
            model.addAttribute("flag1", flag1);
            model.addAttribute("industrys", gson.toJson(industrys));
            model.addAttribute("gg1", gson.toJson(gg1));
            model.addAttribute("gg2", gson.toJson(gg2));
            model.addAttribute("gg3", gson.toJson(gg3));
            model.addAttribute("gg4", gson.toJson(gg4));
            return "village/safety-system/statistics-list";
        } else {
            for (Map map : list) {
                String level = ""; //获取风险等级
                if (null != map.get("level")) {//保证所获取的分险的不为空
                    level = (String) map.get("level");
                }
                setcompany_manualCount(level, flag1);//统计饼状图数据
                String industry = null;
                if (null != map.get("user_name")) {//保证所获取的分险的不为空
                    industry = (String) map.get("user_name");
                }
                if (null != industry && !"".equals(industry)) {
                    for (int i = 0; i < industrys.length; i++) {
                        if (industry.equals(industrys[i])) {
                            if ("红色".equals(level)) {
                                gg1[i] = gg1[i] + 1;
                            } else if ("橙色".equals(level)) {
                                gg2[i] = gg2[i] + 1;
                            } else if ("黄色".equals(level)) {
                                gg3[i] = gg3[i] + 1;
                            } else if ("蓝色".equals(level)) {
                                gg4[i] = gg4[i] + 1;
                            }
                            break;
                        }
                    }
                }

            }
        }
        List<Map<String, Object>> list1 = aCompanyManualMapper.selectCompanyLevel(sb.toString());
        model.addAttribute("list", list1);
        model.addAttribute("flag1", flag1);
        model.addAttribute("industrys", gson.toJson(industrys));
        model.addAttribute("gg1", gson.toJson(gg1));
        model.addAttribute("gg2", gson.toJson(gg2));
        model.addAttribute("gg3", gson.toJson(gg3));
        model.addAttribute("gg4", gson.toJson(gg4));
        return "steel/safety-system/statistics-list";
    }

    void setcompany_manualCount(String level, Integer[] arr) {
        if ("红色".equals(level)) {
            arr[0] = arr[0] + 1;
        } else if ("橙色".equals(level)) {
            arr[1] = arr[1] + 1;
        } else if ("黄色".equals(level)) {
            arr[2] = arr[2] + 1;
        } else if ("蓝色".equals(level)) {
            arr[3] = arr[3] + 1;
        }
    }

    @RequestMapping(value = "safety-system/statistics-list1")
    public String statisticsList1(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<Map<String, Object>> branchs = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId()); //获取所有分厂信息
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", "总部");
        branchs.add(map1);
        //将分厂的id变成一个集合
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < branchs.size(); i++) {

            if (i == branchs.size() - 1) {
                sb.append("'").append(branchs.get(i).get("user_id")).append("'");
            } else {
                sb.append("'").append(branchs.get(i).get("user_id")).append("',");
            }

        }
        List<Map<String, Object>> list = aCompanyManualMapper.findManualLevel(sb.toString());
        String[] industrys = new String[branchs.size()];
        Integer[] gg1 = new Integer[branchs.size()];//红
        Integer[] gg2 = new Integer[branchs.size()];//橙
        Integer[] gg3 = new Integer[branchs.size()];//黄
        Integer[] gg4 = new Integer[branchs.size()];//蓝
        Integer[] flag1 = new Integer[]{0, 0, 0, 0};//部位 数值顺序对应 红橙黄蓝
        for (int i = 0; i < branchs.size(); i++) {
            industrys[i] = (String) branchs.get(i).get("user_name");
            gg1[i] = 0;
            gg2[i] = 0;
            gg3[i] = 0;
            gg4[i] = 0;
        }
        Gson gson = new Gson();
        if (0L == list.size()) { //当查询为空时
            model.addAttribute("flag1", flag1);
            model.addAttribute("industrys", gson.toJson(industrys));
            model.addAttribute("gg1", gson.toJson(gg1));
            model.addAttribute("gg2", gson.toJson(gg2));
            model.addAttribute("gg3", gson.toJson(gg3));
            model.addAttribute("gg4", gson.toJson(gg4));
            return "village/safety-system/statistics-list";
        } else {
            for (Map map : list) {
                String level = ""; //获取风险等级
                if (null != map.get("level")) {//保证所获取的分险的不为空
                    level = (String) map.get("level");
                }
                setcompany_manualCount1(level, flag1);//统计饼状图数据
                String industry = null;
                if (null != map.get("user_name")) {//保证所获取的分险的不为空
                    industry = (String) map.get("user_name");
                }
                if (null != industry && !"".equals(industry)) {
                    for (int i = 0; i < industrys.length; i++) {
                        if (industry.equals(industrys[i])) {
                            if ("红色".equals(level)) {
                                gg1[i] = gg1[i] + 1;
                            } else if ("橙色".equals(level)) {
                                gg2[i] = gg2[i] + 1;
                            } else if ("黄色".equals(level)) {
                                gg3[i] = gg3[i] + 1;
                            } else if ("蓝色".equals(level)) {
                                gg4[i] = gg4[i] + 1;
                            }
                            break;
                        }
                    }
                }

            }
        }
        List<Map<String, Object>> list1 = aCompanyManualMapper.selectCompanyLevel(sb.toString());
        model.addAttribute("list", list1);
        model.addAttribute("flag1", flag1);
        model.addAttribute("industrys", gson.toJson(industrys));
        model.addAttribute("gg1", gson.toJson(gg1));
        model.addAttribute("gg2", gson.toJson(gg2));
        model.addAttribute("gg3", gson.toJson(gg3));
        model.addAttribute("gg4", gson.toJson(gg4));
        return "steel/safety-system/statistics-list";
    }

    void setcompany_manualCount1(String level, Integer[] arr) {
        if ("红色".equals(level)) {
            arr[0] = arr[0] + 1;
        } else if ("橙色".equals(level)) {
            arr[1] = arr[1] + 1;
        } else if ("黄色".equals(level)) {
            arr[2] = arr[2] + 1;
        } else if ("蓝色".equals(level)) {
            arr[3] = arr[3] + 1;
        }
    }

    @RequestMapping(value = "/selectLevelMessage", method = RequestMethod.POST)
    public @ResponseBody
    Result selectLevelMessage(HttpServletRequest request, String companyName) {
        Result result = new ResultImpl();
        List<Map<String, Object>> list = aCompanyManualMapper.selectLevelMessage(companyName);
        result.setMap("list", list);
        return result;
    }

    /**
     * 行业端——集团企业首页——企业数量——跳转返回
     */
    @RequestMapping(value = "back/steel")
    public String back(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("govUser");
        if (user == null) {//返回后的刷新
            user = getLoginUser(request);
        } else {
            SessionUtil.setUser(request, user);
            session.removeAttribute("govUser");//返回时将moveBefore的session删除
        }
        if (user.getUserType() == 10) {//行业端
            Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
            model.addAttribute("name_", trade.getName());
            model.addAttribute("loginUserId", user.getId());
            if (trade.getIsClique() == 1) {//集团型企业
                //model.addAttribute("list", tradeMapper.selectTradeCompany(user.getId()));
                //log.error("TradeCompany："+tradeMapper.selectTradeCompany(user.getId()));
                return "redirect:/steel/main";
            }

        }
        if (user.getUserType() == 6) {//区
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("districtId", user.getId());
            List<Map<String, Object>> list = townMapper.selectListByDistrict(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", districtMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());

        }
        if (user.getUserType() == 3) {//镇
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", user.getId());
            List<DynamicParameter<String, Object>> list = villageMapper.selectListByTown(m);
            model.addAttribute("list", list);
            model.addAttribute("name_", townMapper.selectByPrimaryKey(user.getId()).getName());
            model.addAttribute("loginUserId", user.getId());

        }
        if (user.getUserType() == 4) {//乡
            model.addAttribute("name_", villageMapper.selectByPrimaryKey(user.getId()).getName());
//            if(user.getUserName().equals("港口")) {//港口特殊账号
//                return "gang/main";
//            }
            model.addAttribute("loginUserId", user.getId());
        }
        if (user.getUserType() == 9) {//安泰
            model.addAttribute("list", districtMapper.selectDistrict());
            model.addAttribute("list1", tradeMapper.selectTrade());
            model.addAttribute("name_", user.getUserName());
            model.addAttribute("loginUserId", user.getId());
        }
        return "redirect:/steel/main";
    }

    @RequestMapping(value = "source/company-list")
    public String companyList2(Model model, HttpServletRequest request, CompanyListReqDTO dto, Integer totalzc, Integer totalwyx) throws Exception {
        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("Ids", sb.toString());
        if (StringUtils.isNotBlank(dto.getIndustry2_())) {
            String industry2 = dto.getIndustry2_();
            if (StringUtils.isNotBlank(dto.getIndustry2_2())) {
                industry2 = industry2 + " > " + dto.getIndustry2_2();
            }
            map.put("industry2", industry2);
        }
        if (StringUtils.isNotBlank(dto.getCompanyName())) {
            map.put("companyName", dto.getCompanyName());
        }
        if (StringUtils.isNotBlank(dto.getIsFreeze())) {
            String freeze = dto.getIsFreeze();
            map.put("isFreeze", dto.getIsFreeze());
        }
        List<Map<String, Object>> list = companyMapper.selectCompanyCliq(map);
        model.addAttribute("list", list);
        return "steel/source/company-list";
    }

    /**
     * 重大危险源源长制————
     */
    @RequestMapping(value = "source/video")
    public String monitorListSupervise(HttpServletRequest request, String companyName, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<>();
        m.put("tradeId", user.getId());
        if(null != companyName){
            m.put("companyName", companyName);
        }
        model.addAttribute("list", monitorMapper.selectGroupByMap(m));
        model.addAttribute("companyName", companyName);
        return "village/system/monitor-list-supervise";
    }

    /**
     * 重大危险源源长制————企业分布
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("source/company-map")
    public String coMap(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        List<Map<String, Object>> list = companyMapper.selectDangerCompanyByCliqu(user.getId());
        return null;
    }

    /**
     * 重大危险源源长制————隐患排查治理
     */
    @RequestMapping(value = "source/danger-collect")
    public String modelList(HttpServletRequest request, Integer industryType, Integer townId, Integer villageId, String companyName, Model model, String startTime, String endTime) throws Exception {
        return null;
    }

    /**
     * 重大危险源源长制————分类统计
     */
    @RequestMapping("source/danger-chart")
    public String dangerChart() {

        return "village/source/danger-chart";
    }

    @RequestMapping(value = "source/company-list2")
    public String companyList22(Model model, HttpServletRequest request, CompanyListReqDTO dto, Integer totalzc, Integer totalwyx) throws Exception {
        User user = getLoginUser(request);
        //设置查询条件
        setUserId22(user, dto);
        dto.setCisDanger("1");
        String industry2 = null;
        if (StringUtils.isNotBlank(dto.getIndustry2_())) {
            industry2 = dto.getIndustry2_();
            if (StringUtils.isNotBlank(dto.getIndustry2_2())) {
                industry2 = industry2 + " > " + dto.getIndustry2_2();
            }
        }
        dto.setIndustry2(industry2);
        String freeze = null;//isFreeze属性用来判断运行状态（当日是否已填写每日检查表）而非是否冻结
        if (StringUtils.isNotBlank(dto.getIsFreeze())) {
            freeze = dto.getIsFreeze();
            dto.setIsFreeze(null);
        }
        //获取所有符合条件的user_id
        if (StringUtils.isBlank(dto.getUserIds()) || "-1".equals(dto.getUserIds())) {
            List<Integer> allUserIds = companyMapper.selectIdsByCompany_view(dto);
            if (allUserIds.size() == 0) {
                dto.setUserIds("-1");
            } else {
                dto.setUserIds(StringUtils.join(allUserIds, ","));
            }
        }
        //全部企业列表
        List<Map<String, Object>> comps = new ArrayList<Map<String, Object>>();
        //判断每日检查表是否填写 运行状态列表operatingStatusList
        List<Boolean> operatingStatusList = new ArrayList<Boolean>();
        if (!dto.getUserIds().equals("-1")) {
            comps = companyMapper.selectByIds2(dto);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            boolean operatingStatus = false;
            String title = "重大危险源每日检查表";
            Map<String, Object> c = new HashMap<String, Object>();
            c.put("flag", 1);
            c.put("title", title);
            for (int i = 0; i < comps.size(); i++) {
                c.put("userIds", comps.get(i).get("userId"));
                List<Map<String, Object>> lists = tCheckMapper.selectList(c);
                for (Map<String, Object> list : lists) {
                    String rt = list.get("realTime").toString();
                    if (date.equals(rt.substring(0, 10))) {
                        operatingStatus = true;
                        break;
                    }
                }
                operatingStatusList.add(operatingStatus);
                operatingStatus = false;
            }
            //运行状态筛选
            List<Integer> removeList = new ArrayList<Integer>();
            if (freeze != null) {
                dto.setIsFreeze(freeze);
                if (freeze.equals("1")) {
                    for (int i = 0; i < comps.size(); i++) {
                        if (operatingStatusList.get(i) == false) {
                            removeList.add(i);
                        }
                    }
                } else {//freeze为"0"
                    for (int i = 0; i < comps.size(); i++) {
                        if (operatingStatusList.get(i) == true) {
                            removeList.add(i);
                        }
                    }
                }
                if (removeList.size() > 0) {
                    for (int i = removeList.size() - 1; i >= 0; i--) {
                        comps.remove(removeList.get(i).intValue());
                        operatingStatusList.remove(removeList.get(i).intValue());
                    }
                }
            }
            //分页
            model.addAttribute("total", comps.size());
            Integer from = dto.getPage() * dto.getRows();
            Integer end = null;
            end = Math.min(from + dto.getRows(), comps.size());
            comps = comps.subList(from, end);
            operatingStatusList = operatingStatusList.subList(from, end);
        } else {
            model.addAttribute("total", 0);
        }
        model.addAttribute("list", comps);
        model.addAttribute("osl", operatingStatusList);

        if (user.getUserType().intValue() == 3) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("townId", dto.getTownId());
            m.put("districtId", dto.getDistrictId());
            List<DynamicParameter<String, Object>> villagelist = villageMapper.selectListByTown(m);
            model.addAttribute("villagelist", villagelist);
        }
//        model.addAttribute("lib", libraryMapper.selectLibraryList(1));
        model.addAttribute("sk", request.getParameter("sk"));
//        model.addAttribute("totalzc", totalzc);
//        model.addAttribute("totalwyx", totalwyx);
        model.addAttribute("dto", dto);

        return "village/source/company-list";
    }

    void setUserId22(User user, CompanyListReqDTO dto) {
        if (user.getUserType() == 4) {
            dto.setVillageId(user.getId());
        }
        if (user.getUserType() == 5) {
            dto.setUserId(user.getId());
        }
        if (user.getUserType() == 3) {
            dto.setTownId(user.getId());
        }
        if (user.getUserType() == 6) {
            dto.setDistrictId(user.getId());
        }
        if (user.getUserType() == 9) {
        }
        if (user.getUserType() == 10) {
            dto.setTradeId(user.getId());
        }
    }

    @ResponseBody
    @RequestMapping("updateHazard")
    public Result updateHazard(HttpServletRequest request, Integer hazard) {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company company = new Company();
        company.setUserId(user.getId());
        company.setHazard(hazard);
        int flag1 = companyMapper.updateByPrimaryKeySelective(company);
        TradeClique tradeClique = new TradeClique();
        tradeClique.setUserId(user.getId());
        tradeClique.setHazard(hazard);
        int flag2 = tradeCliqueMapper.updateByPrimaryKeySelective(tradeClique);
        if (flag1 == 1 && flag2 == 1) {
            result.setStatus("1");
        } else {
            result.setStatus("0");
        }
        return result;
    }

    /**
     * 分级管控运行
     *
     * @param request
     * @param model
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping("safety-system/control-operation")
    public String realTimeMonitoring(HttpServletRequest request, Model model, CompanyListReqDTO dto) throws Exception {
        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }

        return "";
    }

    /**
     * 各类风险分布
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/safety-system/all-risk-map1")
    public String riskDistribution(HttpServletRequest request, Model model) {
        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }

        List<Map<String, Object>> list1 = aCompanyManualMapper.findCoordinateByCliequ(sb.toString(), "红色");

        List<Map<String, Object>> list2 = aCompanyManualMapper.findCoordinateByCliequ(sb.toString(), "橙色");

        List<Map<String, Object>> list3 = aCompanyManualMapper.findCoordinateByCliequ(sb.toString(), "黄色");

        List<Map<String, Object>> list4 = aCompanyManualMapper.findCoordinateBlueByCliequ(sb.toString());

        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        model.addAttribute("list4", list4);

        return "global/safety-system/all-risk-map";
    }

    @RequestMapping(value = "all-statistics-list")
    public String controlData(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        StringBuilder sb = new StringBuilder();
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }
        List<Map<String, Object>> list = aCompanyManualMapper.findControlDataByCliqu(sb.toString());

        for (int i = 0; i < list.size(); i++) {

            Integer red = aCompanyManualMapper.totalize((Integer) list.get(i).get("user_id"), "红色");
            list.get(i).put("red", red);

            Integer orange = aCompanyManualMapper.totalize((Integer) list.get(i).get("user_id"), "橙色");
            list.get(i).put("orange", orange);

            Integer yellow = aCompanyManualMapper.totalize((Integer) list.get(i).get("user_id"), "黄色");
            list.get(i).put("yellow", yellow);

            Integer blue = aCompanyManualMapper.totalizeBlue((Integer) list.get(i).get("user_id"));
            list.get(i).put("blue", blue);
        }

        model.addAttribute("list", list);

        return "global/safety-system/all-statistics-list";
    }

    /**
     * TODO 隐患排查治理板块 => 检查设置实施中首页表显示车间
     * 是根据conpanyManual这张表中的数据车间数据进行查询
     * 跳转到新一轮的页面进行修改
     *
     * @param request 请求
     * @param model   存储域
     * @param flag    检查方式 1. 企业自查  2 行政检查 3 部门抽查
     * @param status
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "model-list-main2")
    public String modelListMain2(HttpServletRequest request, Model model, Integer flag, Integer status, Integer plan, Integer userId) throws ParseException {
        User user = getLoginUser(request);
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        ids.add(user.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(user.getId()).append("',");
        for (int i = 0; i < ids.size(); i++) {

            if (i == ids.size() - 1) {
                sb.append("'").append(ids.get(i)).append("'");
            } else {
                sb.append("'").append(ids.get(i)).append("',");
            }

        }
        List<Map<String, Object>> list1 = hiddenPlanMapper.selectCountAllTraces(sb.toString());//合计的数据
        List<Map<String, Object>> hiddenPlanList2 = new ArrayList<>();//各个分公司合计数据
        for (Integer id : ids) {
            List<Map<String, Object>> map = hiddenPlanMapper.selectCountAllName(id);
            if (id.equals(user.getId())) {
                map.get(0).put("name", "总部");
            }
            hiddenPlanList2.addAll(map);
        }
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("hiddenPlanList2", hiddenPlanList2);
        model.addAttribute("list1", list1);
        return "steel/check/model-list-main";

    }

    @RequestMapping(value = "jx-analysis")
    public String jxAnalysis(HttpServletRequest request, Model model) {
        User user = getLoginUser(request);
        Integer number1 = 0;
        Integer number2 = 0;
        Integer number3 = 0;
        Integer sumA = 0;
        Integer sumB = 0;
        Integer sumC = 0;
        Integer sumD = 0;
        Integer sumE = 0;
        Integer sumF = 0;
        Integer sumG = 0;
        Integer sumH = 0;
        Integer sumI = 0;
        Integer sumJ = 0;
        Integer sumK = 0;
        Integer sumL = 0;
        Integer sumM = 0;
        Integer sumN = 0;
        Integer sumO = 0;
        Integer sumP = 0;
        Integer sumQ = 0;
        Integer sumR = 0;
        Integer sumS = 0;
        Integer total = 0;
        Company company = null;
        List<Map<String,Object>> list = null;
        StringBuilder sb = null;
        List<Map<String,Object>> list1 = null;
        List<Map<String,Object>> list2 = null;
        List<Map<String, Object>> companyMaps = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        for(Map<String, Object> map:companyMaps){
            company = companyMapper.selectByPrimaryKey((Integer) map.get("user_id"));
            list =  hiddenPlanMapper.selectDpids(String.valueOf((Integer) map.get("user_id")));
            sb = new StringBuilder();
            total = total + tCheckMapper.dataTotalCounts((Integer) map.get("user_id"));//统计各个分厂的总数


            //System.out.println("user:" + map.get("user_id"));

            findComment1(company, list, sb);
           // list1 = tCheckItemMapper.selectHiddenSources11(1,String.valueOf((Integer) map.get("user_id"))); // 企业自查
            //number1 = number1 + list1.size();
            //list1 = tCheckItemMapper.selectHiddenSources11(2,String.valueOf((Integer) map.get("user_id"))); // 行政检查
            //number2 = number2 + list.size();
            //list1 = tCheckItemMapper.selectHiddenSources11(3,String.valueOf((Integer) map.get("user_id"))); // 第三方检查
           // number3 = number3 + list.size();
            if (null == sb.toString() || sb.toString().length() == 0){

            }else if (null != sb.toString() && sb.toString().length() != 0){

                for (int t = 0; t < list.size(); t++) {

                    if (null == list.get(t).get("name") && (Integer)list.get(t).get("dpid") == 0){

                        list2 = tCheckItemMapper.zhuChartData521("基础管理",String.valueOf(map.get("user_id")),company.getName()); // 生产工艺 隐患数据
                        sumS = sumS + list2.size();

                        list2 = tCheckItemMapper.zhuChartDataBasic(String.valueOf(map.get("user_id")),company.getName()); // 生产工艺 隐患数据
                        sumS = sumS + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("设计总图",String.valueOf(map.get("user_id")),company.getName()); // 生产工艺 隐患数据
                        sumA = sumA + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("试生产",String.valueOf(map.get("user_id")),company.getName()); // 设备设施 隐患数据
                        sumB = sumB + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("装置运行",String.valueOf(map.get("user_id")),company.getName()); // 特种设备 隐患数据
                        sumC = sumC + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("设备安全",String.valueOf(map.get("user_id")),company.getName()); // 消防安全 隐患数据
                        sumD = sumD + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("仪表安全",String.valueOf(map.get("user_id")),company.getName()); // 用电安全 隐患数据
                        sumE = sumE + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("电气安全",String.valueOf(map.get("user_id")),company.getName()); // 行为环境 隐患数据
                        sumF = sumF + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("应急消防",String.valueOf(map.get("user_id")),company.getName()); // 公辅设备 隐患数据
                        sumG = sumG + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("特殊管控",String.valueOf(map.get("user_id")),company.getName()); // 危化管理 隐患数据
                        sumH = sumH + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("行为环境",String.valueOf(map.get("user_id")),company.getName()); // 基础管理 隐患数据
                        sumI = sumI + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("生产现场",String.valueOf(map.get("user_id")),company.getName()); // 防雷静电 隐患数据
                        sumJ = sumJ + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("公辅工程",String.valueOf(map.get("user_id")),company.getName()); // 安全设施 隐患数据
                        sumK = sumK + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("特种设备",String.valueOf(map.get("user_id")),company.getName()); // 职业卫生 隐患数据
                        sumL = sumL + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("专项行业",String.valueOf(map.get("user_id")),company.getName()); // 生产现场 隐患数据
                        sumM = sumM + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("生产工艺",String.valueOf(map.get("user_id")),company.getName()); // 防雷静电 隐患数据
                        sumN = sumN + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("设备设施",String.valueOf(map.get("user_id")),company.getName()); // 安全设施 隐患数据
                        sumO =  sumO + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("危化管理",String.valueOf(user.getId()),company.getName()); // 职业卫生 隐患数据
                        sumP = sumP + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("安全设施",String.valueOf(map.get("user_id")),company.getName()); // 生产现场 隐患数据
                        sumQ = sumQ + list2.size();

                        list2 = tCheckItemMapper.zhuChartData521("其他",String.valueOf(map.get("user_id")),company.getName()); // 其他 隐患数据
                        sumR = sumR + list2.size();
                    }else{

                    }
                }

/*
                System.out.println(sb.toString());*/


                list2 = tCheckItemMapper.zhuChartData781("基础管理",String.valueOf(map.get("user_id")),sb.toString()); // 生产工艺 隐患数据
                sumS = sumS + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("设计总图",String.valueOf(map.get("user_id")),sb.toString()); // 生产工艺 隐患数据
                sumA = sumA + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("试生产",String.valueOf(map.get("user_id")),sb.toString()); // 设备设施 隐患数据
                sumB = sumB + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("装置运行",String.valueOf(map.get("user_id")),sb.toString()); // 特种设备 隐患数据
                sumC = sumC + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("设备安全",String.valueOf(map.get("user_id")),sb.toString()); // 消防安全 隐患数据
                sumD = sumD + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("仪表安全",String.valueOf(map.get("user_id")),sb.toString()); // 用电安全 隐患数据
                sumE = sumE + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("电气安全",String.valueOf(map.get("user_id")),sb.toString()); // 行为环境 隐患数据
                sumF = sumF + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("应急消防",String.valueOf(map.get("user_id")),sb.toString()); // 公辅设备 隐患数据
                sumG = sumG + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("特殊管控",String.valueOf(map.get("user_id")),sb.toString()); // 危化管理 隐患数据
                sumH = sumH + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("行为环境",String.valueOf(map.get("user_id")),sb.toString()); // 基础管理 隐患数据
                sumI = sumI + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("生产现场",String.valueOf(map.get("user_id")),sb.toString()); // 防雷静电 隐患数据
                sumJ = sumJ + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("公辅工程",String.valueOf(map.get("user_id")),sb.toString()); // 安全设施 隐患数据
                sumK = sumK + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("特种设备",String.valueOf(map.get("user_id")),sb.toString()); // 职业卫生 隐患数据
                sumL = sumL + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("专项行业",String.valueOf(map.get("user_id")),sb.toString()); // 生产现场 隐患数据
                sumM = sumM + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("生产工艺",String.valueOf(map.get("user_id")),sb.toString()); // 防雷静电 隐患数据
                sumN = sumN + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("设备设施",String.valueOf(map.get("user_id")),sb.toString()); // 安全设施 隐患数据
                sumO = sumO + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("危化管理",String.valueOf(map.get("user_id")),sb.toString()); // 职业卫生 隐患数据
                sumP = sumP + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("安全设施",String.valueOf(map.get("user_id")),sb.toString()); // 生产现场 隐患数据
                sumQ = sumQ + list2.size();

                list2 = tCheckItemMapper.zhuChartData781("其他",String.valueOf(map.get("user_id")),sb.toString()); // 其他 隐患数据
                sumR = sumR + list2.size();
            }

        }
        model.addAttribute("s", sumS);
        model.addAttribute("a", sumA);
        model.addAttribute("b", sumB);
        model.addAttribute("c", sumC);
        model.addAttribute("d", sumD);
        model.addAttribute("e", sumE);
        model.addAttribute("f", sumF);
        model.addAttribute("g", sumG);
        model.addAttribute("h", sumH);
        model.addAttribute("i", sumI);
        model.addAttribute("j", sumJ);
        model.addAttribute("k", sumK);
        model.addAttribute("l", sumL);
        model.addAttribute("m", sumM);
        model.addAttribute("n", sumN);
        model.addAttribute("o", sumO);
        model.addAttribute("p", sumP);
        model.addAttribute("q", sumQ);
        model.addAttribute("r", sumR);
        model.addAttribute("total", total);
        Integer count = sumA + sumB + sumC +sumD + sumE + sumF + sumG + sumH + sumI + sumJ + sumK +sumL + sumM + sumN + sumO +sumP + sumQ  + sumR + sumR;
        model.addAttribute("count",count);

        DecimalFormat df = new DecimalFormat("0.00");

        selectCounts2(model, count, sumA, sumB, sumC, sumD, sumE, sumF, sumG, sumH, sumI, sumJ, sumK, sumL, sumM, sumN, sumO, sumP, sumQ, sumR, sumS, df);
         sb = new StringBuilder();
         for(int i =0; i< companyMaps.size(); i++){
             if (i == companyMaps.size() - 1) {
                 sb.append("'").append(companyMaps.get(i).get("user_id")).append("'");
             } else {
                 sb.append("'").append(companyMaps.get(i).get("user_id")).append("',");
             }
         }
        number1 = tCheckItemMapper.selectHiddenSources11(1,sb.toString()).size();// 企业自查
        number2 = tCheckItemMapper.selectHiddenSources11(2,sb.toString()).size();// 企业自查
        number3 = tCheckItemMapper.selectHiddenSources11(3,sb.toString()).size();// 企业自查
        model.addAttribute("number1",number1);
        model.addAttribute("number2",number2);
        model.addAttribute("number3",number3);

        return "steel/danger/danger1/jx-analysis";
    }
    private void findComment1(Company company, List<Map<String, Object>> list, StringBuilder sb) {
        for (int i = 0; i <list.size() ; i++) {
            if(i == list.size()-1){
                if (null == list.get(i).get("name") || list.get(i).get("name").equals(company.getName())){
                    continue;
                }else {
                    sb.append("'").append(list.get(i).get("name")).append("'");
                }
            }else {
                if (null == list.get(i).get("name") || list.get(i).get("name").equals(company.getName())){
                    continue;
                }else {
                    sb.append("'").append(list.get(i).get("name")).append("',");
                }
            }
        }
    }

    private void selectCounts2(Model model, Integer count, Integer a, Integer b, Integer c, Integer d, Integer e, Integer f, Integer g, Integer h, Integer i, Integer j, Integer k, Integer l, Integer m, Integer n, Integer o, Integer p, Integer q, Integer r, Integer s, DecimalFormat df) {
        if (null != count && 0 != count) {

            if (null != s && 0 != s) {
                String str = df.format((float) s / count);
                model.addAttribute("s1", str);

            } else {
                model.addAttribute("s1", "0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != a && 0 != a) {
                String str = df.format((float) a / count);
                model.addAttribute("a1", str);

            } else {
                model.addAttribute("a1", "0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != b && 0 != b) {
                String str = df.format((float) b / count);
                model.addAttribute("b1", str);

            } else {
                model.addAttribute("b1", "0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != c && 0 != c) {
                String str = df.format((float) c / count);
                model.addAttribute("c1", str);

            } else {
                model.addAttribute("c1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != d && 0 != d) {
                String str = df.format((float) d / count);
                model.addAttribute("d1", str);

            } else {
                model.addAttribute("d1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != e && 0 != e) {
                String str = df.format((float) e / count);
                model.addAttribute("e1", str);

            } else {
                model.addAttribute("e1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != f && 0 != f) {
                String str = df.format((float) f / count);
                model.addAttribute("f1", str);

            } else {
                model.addAttribute("f1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != g && 0 != g) {
                String str = df.format((float) g / count);
                model.addAttribute("g1", str);

            } else {
                model.addAttribute("g1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != h && 0 != h) {
                String str = df.format((float) h / count);
                model.addAttribute("h1", str);

            } else {
                model.addAttribute("h1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != i && 0 != i) {
                String str = df.format((float) i / count);
                model.addAttribute("i1", str);

            } else {
                model.addAttribute("i1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != j && 0 != j) {
                String str = df.format((float) j / count);
                model.addAttribute("j1", str);

            } else {
                model.addAttribute("j1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != k && 0 != k) {
                String str = df.format((float) k / count);
                model.addAttribute("k1", str);

            } else {
                model.addAttribute("k1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != l && 0 != l) {
                String str = df.format((float) l / count);
                model.addAttribute("l1", str);

            } else {
                model.addAttribute("l1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != m && 0 != m) {
                String str = df.format((float) m / count);
                model.addAttribute("m1", str);

            } else {
                model.addAttribute("m1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != n && 0 != n) {
                String str = df.format((float) n / count);
                model.addAttribute("n1", str);

            } else {
                model.addAttribute("n1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != o && 0 != o) {
                String str = df.format((float) o / count);
                model.addAttribute("o1", str);

            } else {
                model.addAttribute("o1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != p && 0 != p) {
                String str = df.format((float) p / count);
                model.addAttribute("p1", str);

            } else {
                model.addAttribute("p1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != q && 0 != q) {
                String str = df.format((float) q / count);
                model.addAttribute("q1", str);

            } else {
                model.addAttribute("q1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != r && 0 != r) {
                String str = df.format((float) r / count);
                model.addAttribute("r1", str);

            } else {
                model.addAttribute("r1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

        } else {
            model.addAttribute("s1", "0.00");
            model.addAttribute("a1", "0.00");
            model.addAttribute("b1", "0.00");
            model.addAttribute("c1", "0.00");
            model.addAttribute("d1", "0.00");
            model.addAttribute("e1", "0.00");
            model.addAttribute("f1", "0.00");
            model.addAttribute("g1", "0.00");
            model.addAttribute("h1", "0.00");
            model.addAttribute("i1", "0.00");
            model.addAttribute("j1", "0.00");
            model.addAttribute("k1", "0.00");
            model.addAttribute("l1", "0.00");
            model.addAttribute("m1", "0.00");
            model.addAttribute("n1", "0.00");
            model.addAttribute("o1", "0.00");
            model.addAttribute("p1", "0.00");
            model.addAttribute("q1", "0.00");
            model.addAttribute("r1", "0.00");
        }
    }

    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析
     * create time: 2019/8/13 14:34
     */
    @RequestMapping(value = "yh-analysis")
    public String yhAnalysis(HttpServletRequest request, Model model) {
        User user = getLoginUser(request);
        Integer number1 = 0;
        Integer number2 = 0;
        Integer number3 = 0;
        Integer sumA = 0;
        Integer sumB = 0;
        Integer sumC = 0;
        Integer sumD = 0;
        Integer sumE = 0;
        Integer sumF = 0;
        Integer sumG = 0;
        Integer sumH = 0;
        Integer sumI = 0;
        Integer sumJ = 0;
        Integer sumK = 0;
        Integer sumL = 0;
        Integer sumM = 0;
        Integer sumN = 0;
        Integer sumO = 0;
        Integer sumP = 0;
        Integer sumQ = 0;
        Integer sumR = 0;
        Integer sumS = 0;
        Integer count = 0;
        Company company = null;
        List<Map<String,Object>> list = null;
        StringBuilder sb = null;
        List<Map<String, Object>> companyMaps = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        for(Map<String, Object> map:companyMaps){
            sb = new StringBuilder();
            company = companyMapper.selectByPrimaryKey((Integer) map.get("user_id"));
            list =  hiddenPlanMapper.selectDpids(String.valueOf((Integer) map.get("user_id")));
            findComment1(company, list, sb);
            for (int t = 0; t < list.size(); t++) {
                if (null == list.get(t).get("name") && (Integer) list.get(t).get("dpid") == 0) {
                    number1 = number1 + tCheckItemMapper.findTypeByCompany(String.valueOf((Integer) map.get("user_id")),3,company.getName()); // 一般隐患
                    number1 = number1 + tCheckItemMapper.findTypeByBasic(String.valueOf((Integer) map.get("user_id")),3,company.getName()); // 一般隐患
                    number2 = number2 + tCheckItemMapper.findTypeByCompany(String.valueOf((Integer) map.get("user_id")),2,company.getName()); // 一般隐患
                    number2 = number2 + tCheckItemMapper.findTypeByBasic(String.valueOf((Integer) map.get("user_id")),2,company.getName()); //
                    number3 = number3 + tCheckItemMapper.findTypeByCompany(String.valueOf((Integer) map.get("user_id")),1,company.getName()); // 一般隐患
                    number3 = number3 + tCheckItemMapper.findTypeByBasic(String.valueOf((Integer) map.get("user_id")),1,company.getName()); //
                }
            }
            number1 = number1 + tCheckItemMapper.findTypeByMap(String.valueOf((Integer) map.get("user_id")),3,company.getName()); // 一般隐患
            number2 = number2 + tCheckItemMapper.findTypeByMap(String.valueOf((Integer) map.get("user_id")),2,company.getName()); // 一般隐患
            number3 = number3 + tCheckItemMapper.findTypeByMap(String.valueOf((Integer) map.get("user_id")),1,company.getName()); // 一般隐患

            if (null == sb.toString() || sb.toString().length() == 0){

            }else if (null != sb.toString() || sb.toString().length() != 0){
                for (int t = 0; t < list.size(); t++) {
                    if (null == list.get(t).get("name") && (Integer)list.get(t).get("dpid") == 0){
                        sumS = sumS + tCheckItemMapper.zhu521ChartData("基础管理",String.valueOf((Integer) map.get("user_id")),company.getName()); // 生产工艺 隐患数据

                        sumS = sumS + tCheckItemMapper.zhu5211ChartDataBasic(String.valueOf((Integer) map.get("user_id")),company.getName()); // 生产工艺 隐患数据

                        sumA = sumA + tCheckItemMapper.zhu521ChartData("设计总图",String.valueOf((Integer) map.get("user_id")),company.getName()); // 生产工艺 隐患数据

                        sumB = sumB + tCheckItemMapper.zhu521ChartData("试生产",String.valueOf((Integer) map.get("user_id")),company.getName()); // 设备设施 隐患数据

                        sumC = sumC + tCheckItemMapper.zhu521ChartData("装置运行",String.valueOf((Integer) map.get("user_id")),company.getName()); // 特种设备 隐患数据

                        sumD = sumD + tCheckItemMapper.zhu521ChartData("设备安全",String.valueOf((Integer) map.get("user_id")),company.getName()); // 消防安全 隐患数据

                        sumE = sumE + tCheckItemMapper.zhu521ChartData("仪表安全",String.valueOf((Integer) map.get("user_id")),company.getName()); // 用电安全 隐患数据

                        sumF = sumF + tCheckItemMapper.zhu521ChartData("电气安全",String.valueOf((Integer) map.get("user_id")),company.getName()); // 行为环境 隐患数据

                        sumG = sumG + tCheckItemMapper.zhu521ChartData("应急消防",String.valueOf((Integer) map.get("user_id")),company.getName()); // 公辅设备 隐患数据

                        sumH = sumH + tCheckItemMapper.zhu521ChartData("特殊管控",String.valueOf((Integer) map.get("user_id")),company.getName()); // 危化管理 隐患数据

                        sumI = sumI + tCheckItemMapper.zhu521ChartData("行为环境",String.valueOf((Integer) map.get("user_id")),company.getName()); // 基础管理 隐患数据

                        sumJ = sumJ + tCheckItemMapper.zhu521ChartData("生产现场",String.valueOf((Integer) map.get("user_id")),company.getName()); // 防雷静电 隐患数据

                        sumK = sumK + tCheckItemMapper.zhu521ChartData("公辅工程",String.valueOf((Integer) map.get("user_id")),company.getName()); // 安全设施 隐患数据

                        sumL = sumL + tCheckItemMapper.zhu521ChartData("特种设备",String.valueOf((Integer) map.get("user_id")),company.getName()); // 职业卫生 隐患数据

                        sumM = sumM + tCheckItemMapper.zhu521ChartData("专项行业",String.valueOf((Integer) map.get("user_id")),company.getName()); // 生产现场 隐患数据

                        sumN = sumN + tCheckItemMapper.zhu521ChartData("生产工艺",String.valueOf((Integer) map.get("user_id")),company.getName()); // 防雷静电 隐患数据

                        sumO = sumO + tCheckItemMapper.zhu521ChartData("设备设施",String.valueOf((Integer) map.get("user_id")),company.getName()); // 安全设施 隐患数据

                        sumP = sumP + tCheckItemMapper.zhu521ChartData("危化管理",String.valueOf((Integer) map.get("user_id")),company.getName()); // 职业卫生 隐患数据

                        sumQ = sumQ + tCheckItemMapper.zhu521ChartData("安全设施",String.valueOf((Integer) map.get("user_id")),company.getName()); // 生产现场 隐患数据

                        sumR = sumR + tCheckItemMapper.zhu521ChartData("其他",String.valueOf((Integer) map.get("user_id")),company.getName()); // 其他 隐患数据
                    }else{

                    }
                }
                sumS = sumS + tCheckItemMapper.zhuChartData88("基础管理",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 生产工艺 隐患数据

                sumA = sumA + tCheckItemMapper.zhuChartData88("设计总图",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 生产工艺 隐患数据

                sumB = sumB + tCheckItemMapper.zhuChartData88("试生产",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 设备设施 隐患数据

                sumC = sumC + tCheckItemMapper.zhuChartData88("装置运行",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 特种设备 隐患数据

                sumD = sumD + tCheckItemMapper.zhuChartData88("设备安全",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 消防安全 隐患数据

                sumE = sumE + tCheckItemMapper.zhuChartData88("仪表安全",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 用电安全 隐患数据

                sumF = sumF + tCheckItemMapper.zhuChartData88("电气安全",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 行为环境 隐患数据

                sumG = sumG + tCheckItemMapper.zhuChartData88("应急消防",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 公辅设备 隐患数据

                sumH = sumH + tCheckItemMapper.zhuChartData88("特殊管控",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 危化管理 隐患数据

                sumI = sumI + tCheckItemMapper.zhuChartData88("行为环境",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 基础管理 隐患数据

                sumJ = sumJ + tCheckItemMapper.zhuChartData88("生产现场",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 防雷静电 隐患数据

                sumK = sumK + tCheckItemMapper.zhuChartData88("公辅工程",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 安全设施 隐患数据

                sumL = sumL + tCheckItemMapper.zhuChartData88("特种设备",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 职业卫生 隐患数据

                sumM = sumM + tCheckItemMapper.zhuChartData88("专项行业",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 生产现场 隐患数据

                sumN = sumN + tCheckItemMapper.zhuChartData88("生产工艺",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 防雷静电 隐患数据

                sumO = sumO + tCheckItemMapper.zhuChartData88("设备设施",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 安全设施 隐患数据

                sumP = sumP + tCheckItemMapper.zhuChartData88("危化管理",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 职业卫生 隐患数据

                sumQ = sumQ + tCheckItemMapper.zhuChartData88("安全设施",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 生产现场 隐患数据

                sumR = sumR + tCheckItemMapper.zhuChartData88("其他",String.valueOf((Integer) map.get("user_id")),sb.toString()); // 其他 隐患数据
            }
        }
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        model.addAttribute("number3", number3);
        model.addAttribute("s", sumS);
        model.addAttribute("a", sumA);
        model.addAttribute("b", sumB);
        model.addAttribute("c", sumC);
        model.addAttribute("d", sumD);
        model.addAttribute("e", sumE);
        model.addAttribute("f", sumF);
        model.addAttribute("g", sumG);
        model.addAttribute("h", sumH);
        model.addAttribute("i", sumI);
        model.addAttribute("j", sumJ);
        model.addAttribute("k", sumK);
        model.addAttribute("l", sumL);
        model.addAttribute("m", sumM);
        model.addAttribute("n", sumN);
        model.addAttribute("o", sumO);
        model.addAttribute("p", sumP);
        model.addAttribute("q", sumQ);
        model.addAttribute("r", sumR);
        count = sumA + sumB + sumC +sumD + sumE + sumF + sumG + sumH + sumI + sumJ + sumK +sumL + sumM + sumN + sumO +sumP + sumQ  + sumR + sumS;
        model.addAttribute("count",count);

        DecimalFormat df = new DecimalFormat("0.00");
        selectCounts3(model, sumS, sumA, sumB, sumC, sumD, sumE, sumF, sumG, sumH, sumI, sumJ, sumK, sumL, sumM, sumN, sumO, sumP, sumQ, sumR, count, df);
        return "steel/danger/danger1/yh-analysis";
    }

    private void selectCounts3(Model model, Integer s, Integer a, Integer b, Integer c, Integer d, Integer e, Integer f, Integer g, Integer h, Integer i, Integer j, Integer k, Integer l, Integer m, Integer n, Integer o, Integer p, Integer q, Integer r, Integer count, DecimalFormat df) {
        if (null != count && 0 != count) {

            if (null != s && 0 != s) {
                String str = df.format((float) s / count);
                model.addAttribute("s1", str);

            } else {
                model.addAttribute("s1", "0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != a && 0 != a) {
                String str = df.format((float) a / count);
                model.addAttribute("a1", str);

            } else {
                model.addAttribute("a1", "0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != b && 0 != b) {
                String str = df.format((float) b / count);
                model.addAttribute("b1", str);

            } else {
                model.addAttribute("b1", "0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != c && 0 != c) {
                String str = df.format((float) c / count);
                model.addAttribute("c1", str);

            } else {
                model.addAttribute("c1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != d && 0 != d) {
                String str = df.format((float) d / count);
                model.addAttribute("d1", str);

            } else {
                model.addAttribute("d1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != e && 0 != e) {
                String str = df.format((float) e / count);
                model.addAttribute("e1", str);

            } else {
                model.addAttribute("e1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != f && 0 != f) {
                String str = df.format((float) f / count);
                model.addAttribute("f1", str);

            } else {
                model.addAttribute("f1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != g && 0 != g) {
                String str = df.format((float) g / count);
                model.addAttribute("g1", str);

            } else {
                model.addAttribute("g1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != h && 0 != h) {
                String str = df.format((float) h / count);
                model.addAttribute("h1", str);

            } else {
                model.addAttribute("h1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != i && 0 != i) {
                String str = df.format((float) i / count);
                model.addAttribute("i1", str);

            } else {
                model.addAttribute("i1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != j && 0 != j) {
                String str = df.format((float) j / count);
                model.addAttribute("j1", str);

            } else {
                model.addAttribute("j1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != k && 0 != k) {
                String str = df.format((float) k / count);
                model.addAttribute("k1", str);

            } else {
                model.addAttribute("k1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != l && 0 != l) {
                String str = df.format((float) l / count);
                model.addAttribute("l1", str);

            } else {
                model.addAttribute("l1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != m && 0 != m) {
                String str = df.format((float) m / count);
                model.addAttribute("m1", str);

            } else {
                model.addAttribute("m1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != n && 0 != n) {
                String str = df.format((float) n / count);
                model.addAttribute("n1", str);

            } else {
                model.addAttribute("n1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != o && 0 != o) {
                String str = df.format((float) o / count);
                model.addAttribute("o1", str);

            } else {
                model.addAttribute("o1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != p && 0 != p) {
                String str = df.format((float) p / count);
                model.addAttribute("p1", str);

            } else {
                model.addAttribute("p1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != q && 0 != q) {
                String str = df.format((float) q / count);
                model.addAttribute("q1", str);

            } else {
                model.addAttribute("q1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != r && 0 != r) {
                String str = df.format((float) r / count);
                model.addAttribute("r1", str);

            } else {
                model.addAttribute("r1", "0.00"); // 重大隐患 未治理 占比数据 竖
            }

        } else {

            model.addAttribute("s1", "0.00");
            model.addAttribute("a1", "0.00");
            model.addAttribute("b1", "0.00");
            model.addAttribute("c1", "0.00");
            model.addAttribute("d1", "0.00");
            model.addAttribute("e1", "0.00");
            model.addAttribute("f1", "0.00");
            model.addAttribute("g1", "0.00");
            model.addAttribute("h1", "0.00");
            model.addAttribute("i1", "0.00");
            model.addAttribute("j1", "0.00");
            model.addAttribute("k1", "0.00");
            model.addAttribute("l1", "0.00");
            model.addAttribute("m1", "0.00");
            model.addAttribute("n1", "0.00");
            model.addAttribute("o1", "0.00");
            model.addAttribute("p1", "0.00");
            model.addAttribute("q1", "0.00");
            model.addAttribute("r1", "0.00");
        }
    }

    @RequestMapping(value = "zl-analysis")
    public String zlAnalysis2(HttpServletRequest request, Model model) {
        User user = getLoginUser(request);
        Integer num1 = 0;
        Integer num2 = 0;
        Integer num3 = 0;
        Integer sumA = 0;
        Integer sumB = 0;
        Integer sumC = 0;
        Integer sumD = 0;
        Integer sumE = 0;
        Integer sumF = 0;
        Integer sumG = 0;
        Integer sumH = 0;
        Integer sumI = 0;
        Integer sumJ = 0;
        Integer sumK = 0;
        Integer sumL = 0;
        Integer sumM = 0;
        Integer sumN = 0;
        Integer sumO = 0;
        Integer sumP = 0;
        Integer sumQ = 0;
        Integer sumR = 0;
        Integer sumS = 0;
        List<Map<String, Object>> companyMaps = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        for(Map<String, Object> map:companyMaps){
            num1 = num1 + tCheckItemMapper.findFinishCount((Integer) map.get("user_id"),3, (String) map.get("user_name"));
            num1 = num1 + tCheckItemMapper.findFinishBasic((Integer) map.get("user_id"),3, (String) map.get("user_name"));
            num2 = num2 + tCheckItemMapper.findFinishCount((Integer) map.get("user_id"),2, (String) map.get("user_name"));
            num2 = num2 + tCheckItemMapper.findFinishBasic((Integer) map.get("user_id"),2, (String) map.get("user_name"));
            num3 = num3 + tCheckItemMapper.findFinishCount((Integer) map.get("user_id"),1, (String) map.get("user_name"));
            num3 = num3 + tCheckItemMapper.findFinishBasic((Integer) map.get("user_id"),1, (String) map.get("user_name"));
            num1 = num1 + tCheckItemMapper.findFileByMap((Integer) map.get("user_id"),3, (String) map.get("user_name"));
            num2 = num2 + tCheckItemMapper.findFileByMap((Integer) map.get("user_id"),2, (String) map.get("user_name"));
            num3 = num3 + tCheckItemMapper.findFileByMap((Integer) map.get("user_id"),1, (String) map.get("user_name"));
            sumS = sumS + tCheckItemMapper.zhu124ChartData("基础管理",(Integer) map.get("user_id"),(String) map.get("user_name"));
            sumS = sumS + tCheckItemMapper.zhu124ChartDataBasic((Integer) map.get("user_id"),(String) map.get("user_name"));
            sumS = sumS + tCheckItemMapper.zhuChartData124("基础管理",(Integer) map.get("user_id"),(String) map.get("user_name"));
            sumA = sumA + tCheckItemMapper.zhu124ChartData("设计总图", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumA = sumA + tCheckItemMapper.zhuChartData124("设计总图", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumB = sumB + tCheckItemMapper.zhu124ChartData("试生产",(Integer) map.get("user_id"),(String) map.get("user_name"));
            sumB = sumB + tCheckItemMapper.zhuChartData124("试生产", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumC = sumC + tCheckItemMapper.zhu124ChartData("装置运行",(Integer) map.get("user_id"),(String) map.get("user_name"));
            sumC = sumC + tCheckItemMapper.zhuChartData124("装置运行",(Integer) map.get("user_id"),(String) map.get("user_name"));
            sumD = sumD + tCheckItemMapper.zhu124ChartData("设备安全", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumD = sumD + tCheckItemMapper.zhuChartData124("设备安全", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumE = sumE + tCheckItemMapper.zhu124ChartData("仪表安全", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumE = sumE + tCheckItemMapper.zhuChartData124("仪表安全", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumF = sumF + tCheckItemMapper.zhu124ChartData("电气安全", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumF = sumF + tCheckItemMapper.zhuChartData124("电气安全", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumG = sumG + tCheckItemMapper.zhu124ChartData("应急消防", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumG = sumG + tCheckItemMapper.zhuChartData124("应急消防", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumH = sumH + tCheckItemMapper.zhu124ChartData("特殊管控", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumH = sumH + tCheckItemMapper.zhuChartData124("特殊管控", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumI = sumI + tCheckItemMapper.zhu124ChartData("行为环境", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumI = sumI + tCheckItemMapper.zhuChartData124("行为环境", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumJ = sumJ + tCheckItemMapper.zhu124ChartData("生产现场", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumJ = sumJ + tCheckItemMapper.zhuChartData124("生产现场", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumK = sumK + tCheckItemMapper.zhu124ChartData("公辅工程", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumK = sumK + tCheckItemMapper.zhuChartData124("公辅工程", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumL = sumL + tCheckItemMapper.zhu124ChartData("特种设备", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumL = sumL + tCheckItemMapper.zhuChartData124("特种设备", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumM = sumM + tCheckItemMapper.zhu124ChartData("专项行业", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumM = sumM + tCheckItemMapper.zhuChartData124("专项行业", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumN = sumN + tCheckItemMapper.zhu124ChartData("生产工艺", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumN = sumN + tCheckItemMapper.zhuChartData124("生产工艺", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumO = sumO + tCheckItemMapper.zhu124ChartData("设备设施", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumO = sumO + tCheckItemMapper.zhuChartData124("设备设施", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumP = sumP + tCheckItemMapper.zhu124ChartData("危化管理", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumP = sumP + tCheckItemMapper.zhuChartData124("危化管理", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumQ = sumQ + tCheckItemMapper.zhu124ChartData("安全设施", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumQ = sumQ + tCheckItemMapper.zhuChartData124("安全设施", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumR = sumR + tCheckItemMapper.zhu124ChartData("其他", (Integer) map.get("user_id"),(String) map.get("user_name"));
            sumR = sumR + tCheckItemMapper.zhuChartData124("其他", (Integer) map.get("user_id"),(String) map.get("user_name"));
        }
        Integer count = sumS + sumA + sumB + sumC + sumD + sumE + sumF + sumG + sumH +sumI + sumJ + sumK + sumL + sumM + sumN + sumO + sumP + sumQ + sumR;
        model.addAttribute("count",count);
        model.addAttribute("number4", num1);
        model.addAttribute("number5", num2);
        model.addAttribute("number6", num3);
        model.addAttribute("s", sumS);
        model.addAttribute("a", sumA);
        model.addAttribute("b", sumB);
        model.addAttribute("c", sumC);
        model.addAttribute("d", sumD);
        model.addAttribute("e", sumE);
        model.addAttribute("f", sumF);
        model.addAttribute("g", sumG);
        model.addAttribute("h", sumH);
        model.addAttribute("i", sumI);
        model.addAttribute("j", sumJ);
        model.addAttribute("k", sumK);
        model.addAttribute("l", sumL);
        model.addAttribute("m", sumM);
        model.addAttribute("n", sumN);
        model.addAttribute("o", sumO);
        model.addAttribute("p", sumP);
        model.addAttribute("q", sumQ);
        model.addAttribute("r", sumR);
        //System.out.println(num1+num2+num3);
        DecimalFormat df = new DecimalFormat("0.00");
        selectCounts3(model, sumS, sumA, sumB, sumC, sumD, sumE, sumF, sumG, sumH, sumI, sumJ, sumK, sumL, sumM, sumN, sumO, sumP, sumQ, sumR, count, df);
        return "steel/danger/danger1/zl-analysis";
    }
    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析  flag : 1.较大; 2.重大; 3.一般
     * create time: 2019/8/3 16:18
     */
    @RequestMapping(value = "zhuChartData77")
    public String zhuChartData77 (HttpServletRequest request, Model model){

        User user = getLoginUser(request);
        Integer danger1 = 0; Integer count1 = 0;
        Integer danger2 = 0; Integer count2 = 0;
        Integer danger3 = 0; Integer count3 = 0;
        Integer sum = 0;
        List<Map<String, Object>> companyMaps = new ArrayList<>();
        List<Map<String, Object>> companyMaps1 = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        companyMaps.addAll(companyMaps1);
        List<Map<String, Object>> list1 = new ArrayList<>();
        List<Map<String, Object>> list2 = new ArrayList<>();
        List<Map<String, Object>> list3 = new ArrayList<>();
        List<Map<String, Object>> list4 = new ArrayList<>();
        List<Map<String, Object>> list = null;
        DecimalFormat df = new DecimalFormat("0.00");
        //统计数据
        for(Map<String, Object> map:companyMaps){
            danger1 = 0; danger2 = 0; danger3 = 0;
            list =   hiddenPlanMapper.selectDpids(String.valueOf((Integer) map.get("user_id")));

            if (null == list || list.size() == 0){

            }else if (null != list && list.size() != 0){
                for (int i = 0; i < list.size(); i++) {
                    if (null == list.get(i).get("name") && (Integer)list.get(i).get("dpid") == 0) {
                        list.get(i).put("name",map.get("user_name"));
                        danger1 = danger1 + tCheckItemMapper.findHiddenLevelNews((String) list.get(i).get("name"), (Integer) map.get("user_id"), 3);//一般隐患
                        danger1 = danger1 + tCheckItemMapper.findHiddenLevelBasics((String) list.get(i).get("name"), (Integer) map.get("user_id"), 3);//

                        danger2 = danger2 + tCheckItemMapper.findHiddenLevelNews((String) list.get(i).get("name"), (Integer) map.get("user_id"), 1); // 较大
                        danger2 = danger2 + tCheckItemMapper.findHiddenLevelBasics((String) list.get(i).get("name"), (Integer) map.get("user_id"), 1); //

                        danger3 = danger3 + tCheckItemMapper.findHiddenLevelNews((String) list.get(i).get("name"), (Integer) map.get("user_id"), 2);
                        danger3 = danger3 + tCheckItemMapper.findHiddenLevelBasics((String) list.get(i).get("name"), (Integer) map.get("user_id"), 2);

                    }else if (null != list.get(i).get("name") && (Integer)list.get(i).get("dpid") != 0){

                        danger1 = danger1 + tCheckItemMapper.findHiddenLevelTypeByMap((String) list.get(i).get("name"),(Integer) map.get("user_id"),3);

                        danger2 = danger2 + tCheckItemMapper.findHiddenLevelTypeByMap((String) list.get(i).get("name"),(Integer) map.get("user_id"),1);

                        danger3 = danger3 + tCheckItemMapper.findHiddenLevelTypeByMap((String) list.get(i).get("name"),(Integer) map.get("user_id"),2);

                    }

                }
            }
            map1 = new HashMap<>();
            Integer sum1 = danger1 + danger2 + danger3;
            if (null != sum1 && sum1 != 0){  // 一般和较小 治理率
                String str = df.format((float)danger1 / sum1 * 100);
                map1.put("result11",str + "%");
            }else {
                map1.put("result11","0.00%");
            }
            if (null != sum1 && sum1 != 0){  // 一般和较小 治理率
                String str = df.format((float)danger2 / sum1 * 100);
                map1.put("result22",str + "%");
            }else {
                map1.put("result22","0.00%");
            }
            if (null != sum1 && sum1 != 0){  // 一般和较小 治理率
                String str = df.format((float)danger3 / sum1 * 100);
                map1.put("result33",str + "%");
            }else {
                map1.put("result33","0.00%");
            }
            count1 = count1 +danger1;
            count2 = count2 + danger2;
            count3 = count3 + danger3;
            map1.put("name", map.get("user_name"));
            map1.put("danger1", danger1);
            map1.put("danger2", danger2);
            map1.put("danger3", danger3);
            map1.put("sum", sum1);
            list1.add(map1);
            map1 = new HashMap<>();
            map1.put("name", map.get("user_name"));
            map1.put("sum", sum1);
            list3.add(map1);
        }
        Collections.sort(list3, new Comparator<Map<String, Object>>() {

            public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                Integer name1 = Integer.valueOf(o1.get("sum").toString()) ;

                Integer name2 = Integer.valueOf(o2.get("sum").toString()) ;

                return name1.compareTo(name2);

            }
        });
        for (int i = 0; i < list3.size(); i++) {

            list3.get(i).put("data",list1.size()-i);

        }
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list3.size(); j++) {

                if (null != list1.get(i).get("name") &&  null != list3.get(j).get("name")){

                    if (list1.get(i).get("name").equals(list3.get(j).get("name"))){

                        list1.get(i).put("data",list3.get(j).get("data"));

                        break;
                    }
                }
            }
        }
        //计算比例
        map1 = new HashMap<>();
        map1.put("count1", count1);
        map1.put("count2", count2);
        map1.put("count3", count3);
        sum = count1 + count2 + count3;
        map1.put("sum", sum);
        list2.add(map1);
        map1 = new HashMap<>();
        Integer add14 = count1 + count2 + count3;
        if (null != add14 && add14 != 0){  // 一般和较小 治理率
            String str = df.format((float)count1 / add14 * 100);
            map1.put("result1",str + "%");
        }else {
            map1.put("result1","0.00%");
        }
        if (null != add14 && add14 != 0){  // 一般和较小 治理率
            String str = df.format((float)count2 / add14 * 100);
            map1.put("result2",str + "%");
        }else {
            map1.put("result2","0.00%");
        }
        if (null != add14 && add14 != 0){  // 一般和较小 治理率
            String str = df.format((float)count3 / add14 * 100);
            map1.put("result3",str + "%");
        }else {
            map1.put("result3","0.00%");
        }
        list4.add(map1);
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        model.addAttribute("list4", list4);
        return "steel/danger/danger1/zhuChartData77";
    }

    @RequestMapping(value = "/safety-system/all-risk-map")
    public String controlDistribution(HttpServletRequest request,Model model) throws Exception {
        User user = getLoginUser(request);

        model.addAttribute("longitude",user.getLongitude());
        model.addAttribute("latitude",user.getLatitude());
        List<Map<String, Object>> list1 = aCompanyManualMapper.findCompanyByCliqu(user.getId());
        model.addAttribute("list1",list1);
        return "steel/safety-system/all-risk-map";
    }


    @RequestMapping(value = "zhuChartData44")
    public String zhuChartData44 (HttpServletRequest request, Model model){

        User user = getLoginUser(request);
        Integer danger11 = 0; Integer danger12 = 0; Integer count11 = 0; Integer count12 = 0;//已治理 未治理  一般隐患
        Integer danger21 = 0; Integer danger22 = 0; Integer count21 = 0; Integer count22 = 0;//已治理 未治理  较大隐患
        Integer danger31 = 0; Integer danger32 = 0; Integer count31 = 0; Integer count32 = 0;//已治理 未治理  重大隐患
        List<Map<String, Object>> companyMaps = new ArrayList<>();
        List<Map<String, Object>> companyMaps1 = tradeCliqueMapper.selectCompanyMapByCqlib(user.getId());
        DecimalFormat df = new DecimalFormat("0.00");
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user.getId());
        map1.put("user_name", user.getUserName());
        companyMaps.add(map1);
        companyMaps.addAll(companyMaps1);
        List<Map<String, Object>> list1 = new ArrayList<>();
        List<Map<String, Object>> list2 = new ArrayList<>();
        List<Map<String, Object>> list3 = new ArrayList<>();
        List<Map<String, Object>> list4 = new ArrayList<>();
        List<Map<String, Object>> list = null;

        for(Map<String, Object> map:companyMaps){
            danger11 = 0; danger12 =0;
            danger21 = 0; danger22 = 0;
            danger31 = 0; danger32 = 0;
            list = zzjgDepartmentMapper.findAllLevel1((Integer) map.get("user_id"));
            danger11 = danger11 + tCheckItemMapper.findRecheckNews(3,(Integer) map.get("user_id"),3,(String) map.get("user_name")); //一般 较小 合格
            danger11 = danger11 + tCheckItemMapper.findRecheckBasics(3, (Integer) map.get("user_id"),3,(String) map.get("user_name"));//一般 合格
            danger12 = danger12 + tCheckItemMapper.findRecheckNews(2,(Integer) map.get("user_id"),3,(String) map.get("user_name"));
            danger12 = danger12 + tCheckItemMapper.findRecheckBasics(2,(Integer) map.get("user_id"),3,(String) map.get("user_name"));
            danger21 = danger21 + tCheckItemMapper.findRecheckNews(3,(Integer) map.get("user_id"),1,(String) map.get("user_name"));
            danger21 = danger21 + tCheckItemMapper.findRecheckBasics(3,(Integer) map.get("user_id"),1,(String) map.get("user_name"));
            danger22 = danger22 + tCheckItemMapper.findRecheckNews(2,(Integer) map.get("user_id"),1,(String) map.get("user_name"));
            danger22 = danger22 + tCheckItemMapper.findRecheckBasics(2,(Integer) map.get("user_id"),1,(String) map.get("user_name"));
            danger31 = danger31 + tCheckItemMapper.findRecheckNews(3,(Integer) map.get("user_id"),2,(String) map.get("user_name"));
            danger31 = danger31 + tCheckItemMapper.findRecheckBasics(3,(Integer) map.get("user_id"),2,(String) map.get("user_name")); // 重大 合格 已治理
            danger32 = danger32 + tCheckItemMapper.findRecheckNews(2,(Integer) map.get("user_id"),2,(String) map.get("user_name"));
            danger32 = danger32 + tCheckItemMapper.findRecheckBasics(2,(Integer) map.get("user_id"),2,(String) map.get("user_name"));
             if (null == list || list.size() == 0){

             }else if (null != list || list.size() != 0){

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).get("name").equals((String) map.get("user_name"))){
                        continue;
                    }else {
                        danger11 = danger11 + tCheckItemMapper.findRecheckFileByMap(3,(Integer) map.get("user_id"),3,(String) list.get(i).get("name"));
                        danger12 = danger12 + tCheckItemMapper.findRecheckFileByMap(2,(Integer) map.get("user_id"),3,(String) list.get(i).get("name"));
                        danger21 = danger21 + tCheckItemMapper.findRecheckFileByMap(3,(Integer) map.get("user_id"),1,(String) list.get(i).get("name"));
                        danger22 = danger22 + tCheckItemMapper.findRecheckFileByMap(2,(Integer) map.get("user_id"),1,(String) list.get(i).get("name"));
                        danger31 = danger31 + tCheckItemMapper.findRecheckFileByMap(3,(Integer) map.get("user_id"),2,(String) list.get(i).get("name"));
                        danger32 = danger32 + tCheckItemMapper.findRecheckFileByMap(2,(Integer) map.get("user_id"),2,(String) list.get(i).get("name"));

                    }
                }
            }
            map1 = new HashMap<>();
             //计算百分比
             Integer add11 = danger11 + danger12;
            if (null != add11 && add11 != 0){  // 一般和较小 治理率
                String str = df.format((float)danger11 / add11 * 100);
                map1.put("result11",str + "%");
            }else {
                map1.put("result11","0.00%");
            }
            Integer add22 = danger21 + danger22;
            if (null != add22 && add22 != 0){  // 一般和较小 治理率
                String str = df.format((float)danger21 / add22 * 100);
                map1.put("result22",str + "%");
            }else {
                map1.put("result22","0.00%");
            }
            Integer add33 = danger31 + danger32;
            if (null != add33 && add33 != 0){  // 一般和较小 治理率
                String str = df.format((float)danger31 / add33 * 100);
                map1.put("result33",str + "%");
            }else {
                map1.put("result33","0.00%");
            }
             map1.put("name",(String) map.get("user_name"));
             map1.put("danger11", danger11);
             map1.put("danger12", danger12);
             map1.put("danger21", danger21);
             map1.put("danger22", danger22);
             map1.put("danger31", danger31);
             map1.put("danger32", danger32);
             Integer sum1 = danger11 + danger21 + danger31;
             Integer sum2 = danger12 + danger22 + danger32;
             map1.put("sum1", sum1);
             map1.put("sum2", sum2);
             count11 = count11 + danger11;
             count12 = count12 + danger12;
             count21 = count21 + danger21;
             count22 = count22 + danger22;
             count31 = count31 + danger31;
             count32 = count32 + danger32;
             list1.add(map1);
             map1 = new HashMap<>();
             map1.put("name",(String) map.get("user_name"));
             map1.put("sum", sum1);
             list3.add(map1);

        }
        map1 = new HashMap<>();
        map1.put("count11", count11);
        map1.put("count12", count12);
        map1.put("count21", count21);
        map1.put("count22", count22);
        map1.put("count31", count31);
        map1.put("count32", count32);
        Integer sum1 = count11 + count21 + count31;
        Integer sum2 = count12 + count22 + count32;
        Integer add44 = count11 + danger12;
        if (null != add44 && add44 != 0){  // 一般和较小 治理率
            String str = df.format((float)count11 / add44 * 100);
            map1.put("result1",str + "%");
        }else {
            map1.put("result1", "0.00%");
        }
        Integer add55 = count21 + danger22;
        if (null != add55 && add55 != 0){  // 一般和较小 治理率
            String str = df.format((float)count21 / add55 * 100);
            map1.put("result2",str + "%");
        }else {
            map1.put("result2","0.00%");
        }
        Integer add66 = count31 + danger32;
        if (null != add66 && add66 != 0){  // 一般和较小 治理率
            String str = df.format((float)count31 / add66 * 100);
            map1.put("result3",str + "%");
        }else {
            map1.put("result3","0.00%");
        }
        map1.put("sum1", sum1);
        map1.put("sum2", sum2);
        list2.add(map1);
        Integer add77 = count11 + count21 + count31;
        Integer add88 = count12 + count22 + count32;
        map1 = new HashMap<>();
        if (null != add77 && add77 != 0){  // 一般和较小 治理率
            String str = df.format((float)count11 / add77 * 100);
            map1.put("proportion11",str + "%");
        }else {
            map1.put("proportion11","0.00%");
        }
        if (null != add77 && add77 != 0){  // 一般和较小 治理率
            String str = df.format((float)count21 / add77 * 100);
            map1.put("proportion22",str + "%");
        }else {
            map1.put("proportion22","0.00%");
        }
        if (null != add77 && add77 != 0){  // 一般和较小 治理率
            String str = df.format((float)count31 / add77 * 100);
            map1.put("proportion33",str + "%");
        }else {
            map1.put("proportion33","0.00%");
        }
        if (null != add88 && add88 != 0){  // 一般和较小 治理率
            String str = df.format((float)count12 / add88 * 100);
            map1.put("proportion44",str + "%");
        }else {
            map1.put("proportion44","0.00%");
        }
        if (null != add88 && add88 != 0){  // 一般和较小 治理率
            String str = df.format((float)count22 / add88 * 100);
            map1.put("proportion55",str + "%");
        }else {
            map1.put("proportion55","0.00%");
        }
        if (null != add88 && add88 != 0){  // 一般和较小 治理率
            String str = df.format((float)count32 / add88 * 100);
            map1.put("proportion66",str + "%");
        }else {
            map1.put("proportion66","0.00%");
        }
        Collections.sort(list3, new Comparator<Map<String, Object>>() {

            public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                Integer name1 = Integer.valueOf(o1.get("sum").toString()) ;

                Integer name2 = Integer.valueOf(o2.get("sum").toString()) ;

                return name1.compareTo(name2);

            }
        });
        for (int i = 0; i < list3.size(); i++) {

            list3.get(i).put("data",list1.size()-i);

        }
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list3.size(); j++) {

                if (null != list1.get(i).get("name") &&  null != list3.get(j).get("name")){

                    if (list1.get(i).get("name").equals(list3.get(j).get("name"))){

                        list1.get(i).put("data",list3.get(j).get("data"));

                        break;
                    }
                }
            }
        }
        list4.add(map1);
        model.addAttribute("list3", list4);
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        return "steel/danger/danger1/zhuChartData44";
    }

    @RequestMapping("opinion-show")
    public String opinionShow(Model model, String companyName, HttpServletRequest request, Integer flag, Integer flag2)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.remove("tradeId");
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId()).append(",");
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        m.put("companyName", companyName);
        m.put("flag2", flag2);
        m.put("userIds", sb.toString());
        model.addAttribute("list", tCheckDocumentMapper.selectTable(m));
        model.addAttribute("flag", null == flag ? 8 : flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("flag2", flag2);
        return "village/danger/opinion-show";
    }

    @RequestMapping(value = "check-item3")
    public String checkItem3(HttpServletRequest request, Model model, Integer flag, String companyName, Integer status)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("flag", flag);
        m.put("status", status);
        m.put("companyName", companyName);
        m.put("d", 1);
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(user.getId()).append("',");
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        m.put("userIds", sb.toString());
        List<Map<String, Object>> list = tCheckItemMapper.selectDangerIndexListByCliqu(m);
        for(Map map: list){
            if (null == map.get("fjgkfzr") || "".equals(map.get("fjgkfzr"))) {
                String name = "";
                TCheck tc = tCheckMapper.selectByPrimaryKey((Integer) map.get("checkId"));
                // 表示没有被检查人员 根据部门名称获取这个部门的被检查人员然后随便抓一个
                List<Integer> integers = tCheckItemMapper.selectLevelIdByCheckId((Integer) map.get("checkItemId"));
                if (null != integers && integers.size() > 0) {
                    // 这里进行名称的获取,进行全部循环,获取数据的方式,在数据库中进行查询
                    List<String> list1 = new ArrayList<String>();
                    for (Integer integer : integers) {
                        if (null != integer) {
                            ACompanyManual aCompanyManual = aCompanyManualMapper.selectByPrimaryKey(integer);
                            if (null != aCompanyManual && null != aCompanyManual.getFjgkfzr()) {
                                list1.add(aCompanyManual.getFjgkfzr());
                            }
                        }
                    }
                    if (list1.size() == 0) {
                        name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                        tc.setCheckCompany(name);
                    } else {
                        name = list1.get(0);
                        tc.setCheckCompany(name);
                    }
                } else {
                    name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                    tc.setCheckCompany(name);
                }
                map.put("fjgkfzr", name);
            }
        }
        if (null == flag){
            m.put("status", 2);
            System.out.println(m);
            List<Map<String, Object>> list1 = tCheckItemMapper.selectDangerIndexListByCliqu(m);
            model.addAttribute("list_s2", list1.size()); // 未整改

            m.put("status", 3);

            List<Map<String, Object>> list2 = tCheckItemMapper.selectDangerIndexListByCliqu(m);
            model.addAttribute("list_s3", list2.size()); // 已整改

        }
        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        //model.addAttribute("companyName", user.getUserName());
        model.addAttribute("host", host);
        return "steel/company/danger-index3";
    }
    @RequestMapping(value = "check-item32")
    public String checkItem32(HttpServletRequest request, Model model, Integer flag, String companyName, Integer status)
            throws Exception {
        User user = getLoginUser(request);
        List<Integer> ids = tradeCliqueMapper.selectCompanyIdsByCqlib(user.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(user.getId()).append("',");
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size()-1){
                sb.append("'").append(ids.get(i)).append("'");
            }else {
                sb.append("'").append(ids.get(i)).append("',");
            }
        }
        Map<String, Object> m = new HashMap<>();
        m.put("flag", flag);
        m.put("userId", sb.toString());
        m.put("status", status);
        m.put("companyName", companyName);
        m.put("d", 1);

        m.remove("tradeId");
        m.put("userId", sb.toString());
        List<Map<String, Object>> list1 = tCheckItemMapper.findACompanyAllList(m);
        List<Map<String, Object>> list2 = tCheckItemMapper.findDangerAllList(m);
        for (int i = 0; i < list2.size(); i++) {
            Integer checkId = (Integer) list2.get(i).get("checkId");
            list2.get(i).put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
        }
        List<Map<String, Object>> list3= tCheckItemMapper.findLevelAllList(m);
        for (int i = 0; i < list3.size(); i++) {
            Integer checkId = (Integer) list3.get(i).get("checkId");
            list3.get(i).put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
        }
        m.put("status", 2);
        System.out.println(m);
        List<Map<String, Object>> list11 = tCheckItemMapper.findACompanyAllList(m);
        List<Map<String, Object>> list22 = tCheckItemMapper.findDangerAllList(m);
        List<Map<String, Object>> list33 = tCheckItemMapper.findLevelAllList(m);
        model.addAttribute("list_s2", list11.size() + list22.size() + list33.size()); // 未整改

        m.put("status", 3);
        List<Map<String, Object>> list111 = tCheckItemMapper.findACompanyAllList(m);
        List<Map<String, Object>> list222 = tCheckItemMapper.findDangerAllList(m);
        List<Map<String, Object>> list333 = tCheckItemMapper.findLevelAllList(m);
        model.addAttribute("list_s3", list111.size() + list222.size() + list333.size());
        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("companyName", companyName);
        model.addAttribute("host", host);
        return "steel/company/danger-index3";
    }

    /**
     * 跳转到地图的截图页面
     */
    @RequestMapping(value = "control-list4")
    public String jumpHtml(HttpServletRequest request, Model model) {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("company", company);
        return "steel/safety-system/control-list4";
    }


}
