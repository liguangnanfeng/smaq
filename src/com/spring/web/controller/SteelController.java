package com.spring.web.controller;

import com.alibaba.fastjson.JSON;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     *   特岗登陆首页
     */
    @RequestMapping("/fore")
    public  String blogin(){
        return "steel/blogin2";
    }
    /**
     * 特岗登录验证
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
        if(trade.getIsClique() == 1){ //跳转到总部企业
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
        if(userIds.equals("-1")) {
            model.addAttribute("number", 0);
        } else {
            model.addAttribute("number", userIds.split(",").length);//企业数量
        }

        m.put("tradeId", null);
        model.addAttribute("name_", tradeMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("count", count);
        System.out.println(user.getId());
        model.addAttribute("userId", user.getId());
        Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
        if(trade.getIsClique() == 1){//行业端集团型企业
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
        if(tradeClique == null){
            TradeClique tc = new TradeClique();
            Trade trade = tradeMapper.selectByPrimaryKey(user.getId());
            tc.setUserId(trade.getUserId());
            tc.setName(trade.getName());
            tradeCliqueMapper.insertSelective(tc);
            tradeClique = tc;
        }else{
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
        System.out.println("add");
        return "steel/product/material-add";
    }
    /**
     * 原辅材料——添加/编辑保存
     */
    @RequestMapping(value = "product/material-save")
    public @ResponseBody Result materialSave(Material material, HttpServletRequest request) throws Exception {
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
     *原辅材料-编辑展示
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
    public @ResponseBody Result deleteMaterial(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        materialMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 主要产品-列表展示
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
    public @ResponseBody Result deleteProduct(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        productMapper.deleteByPrimaryKey(id);
        return result;
    }
    /**
     * 主要产品——添加/编辑——保存
     */
    @RequestMapping(value = "product/product-save")
    public @ResponseBody Result productSave(Product product, Integer code, HttpServletRequest request) throws Exception {
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
     * @param id
     * @return
     */
    @RequestMapping("process/process-del")
    public @ResponseBody Result processDel(Integer id) {
        Result result = new ResultImpl();
        productionProcessDiagramMapper.deleteByPrimaryKey(id);
        return result;
    }
    /**
     * 生产工艺——保存
     */
    @RequestMapping("process/process-save")
    public @ResponseBody Result processSave(HttpServletRequest request,
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
        cgfService.selectCompanyWithPage(dto, user, model);
        if(user.getUserType().intValue() == 3) {
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
    public String riskList(Model model, HttpServletRequest request, Integer type, Integer number,Integer dmid,
                           Integer flag,String types,String id, String buttons,String count1) throws Exception {
        User user = this.getLoginUser(request);
        //Company company = this.companyMapper.selectByPrimaryKey(user.getId());
        TradeClique tradeClique = tradeCliqueMapper.selectByPrimaryKey(user.getId());
        if (null == count1 || Integer.parseInt(count1) == 0){
            count1 = "0";
        }else if(Integer.parseInt(count1) != 0){
            count1 = "1";
        }

        if (null == buttons){
            buttons = "2";
        }
        if (null != buttons && Integer.parseInt(buttons) == 1){
            // 根据 uid 修改 dangerId 为 null
            /*Integer a = zzjgDepartmentMapper.deleteAll(null,user.getId(),new Date());*/
            // 根据 uid 查询所有的数据信息
            List<ZzjgDepartment> zzjgDeparList = zzjgDepartmentMapper.selectLevel1DangerIds(user.getId());
            model.addAttribute("buttons", 1);
            model.addAttribute("zzjgDeparList",zzjgDeparList);
        }
        if (null != buttons && Integer.parseInt(buttons) == 2){
            model.addAttribute("buttons", 2);
        }
        if (StringUtils.isEmpty(tradeClique.getIndustry())) {
            model.addAttribute("url", request.getRequestURI());
            return "company/safety-system/type";
        } else {
            model.addAttribute("company", tradeClique);
            Map<String, Object> m = new HashMap();
            if (null != types){
                m.put("type", Integer.parseInt(types));
            }
            m.put("flag", "1,2,3,4,5");
            m.put("uid", user.getId());
            if (null != dmid){
                m.put("dmid", Integer.parseInt(id));
            }
            List<Map<Object, Object>> zzjg = null;
            List<Map<Object, Object>> zzjg1 = null;
            List<ACompanyManual> list = null;
            List acL = null;
            if (null == types || Integer.parseInt(types) == 1 || Integer.parseInt(types) == 2 || Integer.parseInt(types) == 3) {
                if (null == number && null == types){
                    number = 3;
                }else if (null != types && types.equals("3")){ // 现场
                    number = 1;
                }else if (null != types && types.equals("2")){ // 基础
                    number = 2;
                }
                if (number == 1) { // 现场
                    String dangerIds = "1";

                    zzjg1 = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                    list = aCompanyManualMapper.findDangerOne(user.getId(),id);
                    acL = this.aCompanyManualMapper.selectByAll(m);
                    if (null == id){
                        zzjg = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                    }else {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1One(user.getId(),dangerIds,Integer.parseInt(id));
                    }
                } else if (number == 2) { // 基础
                    String dangerIds = "2";
                    acL = this.aCompanyManualMapper.selectBase(m);
                    list = aCompanyManualMapper.findOne("基础管理",user.getId(),id);
                    zzjg1 = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                    if (null == id){
                        zzjg = this.zzjgDepartmentMapper.selectLevel1All(user.getId(),dangerIds);
                    }else {
                        zzjg = this.zzjgDepartmentMapper.selectLevel1One(user.getId(),dangerIds,Integer.parseInt(id));
                    }
                }else if (number == 3) { // 设置
                    if (null != buttons && Integer.parseInt(buttons) == 1){
                        zzjg = this.zzjgDepartmentMapper.selectLevel1ByUid(user.getId());
                    }else if (Integer.parseInt(buttons) == 2){
                        zzjg = this.zzjgDepartmentMapper.selectEmpyDangerIds(user.getId());
                    }
                }

                Integer indus = 0;
                if (tradeClique.getIndustry().contains("化工")){
                    indus = 1;
                }else {
                    indus = 2;
                }
                String parName = null;
                if (null != zzjg && zzjg.size() == 1){
                    for (Map<Object, Object> zzjgList : zzjg) {
                        parName = String.valueOf(zzjgList.get("name"));
                    }
                }else {
                    parName = "";
                }

                // 根据公司 ID 查询所有的现场风险信息
                Integer nowCount = aCompanyManualMapper.selectCountNow(user.getId(),"现场管理");


                model.addAttribute("nowCount",nowCount);
                model.addAttribute("count1",count1);
                model.addAttribute("parName",parName);
                model.addAttribute("buttons",buttons);
                model.addAttribute("indus",indus);
                model.addAttribute("ids",id);
                model.addAttribute("list",list);
                model.addAttribute("zzjgDep1", zzjg1);
                model.addAttribute("number", number);
                model.addAttribute("zzjgDep", zzjg);
                model.addAttribute("acL", acL);

                return "company/safety-system/risk-list1";
            } else {
                String dangerId = null;
                if (Integer.parseInt(types) == 4){ // 职业病风险物理因素
                    dangerId = "4";
                    type = 1;
                }else if(Integer.parseInt(types) == 5){ // 职业病风险化学因素
                    dangerId = "5";
                    type = 2;
                }else if(Integer.parseInt(types) == 6){ // 高危工艺辨识
                    dangerId = "6";
                    type = 3;
                }else if(Integer.parseInt(types) == 7){ // 物料风险辨识
                    dangerId = "7";
                    type = 4;
                }else if(Integer.parseInt(types) == 8){ // 高危作业辨识
                    dangerId = "8";
                    type = 5;
                }
                List<ZzjgDepartment> zzjgDepartment = zzjgDepartmentMapper.selectDangerIds(user.getId(),dangerId);
                List<ZzjgDepartment> zzjgDepartments = zzjgDepartmentMapper.selectOneName(user.getId(),dangerId,Integer.parseInt(id));
                if (null != zzjgDepartment && zzjgDepartment.size() != 0){
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < zzjgDepartment.size(); i++) {
                        if (i == zzjgDepartment.size()-1){
                            sb.append(zzjgDepartment.get(i).getId());
                        }else {
                            sb.append(zzjgDepartment.get(i).getId()).append(",");
                        }
                    }
                    if (sb.toString().length() != 0){
                        zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(),type,sb.toString());
                        zzjg1 = this.zzjgDepartmentMapper.selectAllName(user.getId(),type,sb.toString());
                        if (null == id){
                            zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(),type,sb.toString());
                        }else {
                            zzjg = this.zzjgDepartmentMapper.selectAllName(user.getId(),type,id);
                        }
                    }
                }
                Integer indus = 0;
                if (tradeClique.getIndustry().contains("化工")){
                    indus = 1;
                }else {
                    indus = 2;
                }
                model.addAttribute("count1",count1);
                if(zzjgDepartments.size()>0){
                    model.addAttribute("parName",zzjgDepartments.get(0).getName());
                }
                model.addAttribute("indus",indus);
                model.addAttribute("zzjgDep1", zzjgDepartment);
                model.addAttribute("zzjg",zzjg);
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
     *主要设备
     *
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "importMequipmentExcel", produces = "text/html;charset=utf-8")
    public void importMequipmentExcel(Model model, HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        System.out.println("123");
        writeResponse(result, response);//该方法调用如下
    }

    /**
     * 产品
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "importProductExcel", produces = "text/html;charset=utf-8")
    public void importtProductExcel(Model model, HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        System.out.println("456");
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
    public void importMarerialExcel(Model model, HttpServletResponse response, @RequestParam MultipartFile file, Integer userId) throws Exception {
        Result result = new ResultImpl();
        System.out.println("999");
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
     * 安全生产标准化
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list1")
    public String selectCompanyByCqlib1(Model model, HttpServletRequest request){
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list1";
    }

    /**
     * 检查设置设施
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list2")
    public String selectCompanyByCqlib2(Model model, HttpServletRequest request){
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list2";
    }

    /**
     *隐患排查记录
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list3")
    public String selectCompanyByCqlib3(Model model, HttpServletRequest request){
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list3";
    }

    /**
     *隐患治理记录
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list4")
    public String selectCompanyByCqlib4(Model model, HttpServletRequest request){
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list4";
    }

    /**
     *排查数据分析
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list5")
    public String selectCompanyByCqlib5(Model model, HttpServletRequest request){
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list5";
    }

    /**
     *隐患数据分析
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list6")
    public String selectCompanyByCqlib6(Model model, HttpServletRequest request){
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list6";
    }

    /**
     *治理数据分析
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("company-list7")
    public String selectCompanyByCqlib7(Model model, HttpServletRequest request){
        User user = getLoginUser(request);
        List<Map<String, Object>> list1 = tradeCliqueMapper.selectCompanyByCqlib(user.getId());
        model.addAttribute("list", list1);
        return "steel/other/company-list7";
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
        Company company = companyMapper.selectByPrimaryKey(userId);

        Map<String, Object> map1 = new LinkedHashMap<String, Object>();
        map1.put("level1",company.getName());

        List<Map<String, Object>> jiChuItem = new ArrayList<>();
        jiChuItem.add(map1);
        jiChuItem.addAll(aCompanyManualMapper.findJiChuItem(company.getUserId(), "基础管理"));

        Map<String, Object> map2 = new LinkedHashMap<String, Object>();
        map2.put("level1", company.getName());
        List<Map<String, Object>> XianChangItem = new ArrayList<>();
        XianChangItem.add(map2);
        XianChangItem.addAll(aCompanyManualMapper.findJiChuItem(company.getUserId(), "现场管理"));

        for (Map<String, Object> XianChangMap : XianChangItem) {
            Map<Integer, Integer> Xianmap = new LinkedHashMap<Integer, Integer>();
            Xianmap.put(5, 0);
            Xianmap.put(1, 0);
            Xianmap.put(2, 0);
            /*Xianmap.put(3, 0);*/
            Xianmap.put(4, 0);
            String level1 = (String) XianChangMap.get("level1");
            List<Integer> Xiantypes = tModelMapper.selecttype(level1, company.getUserId(), 2, flag);
            for (Integer integer : Xiantypes) {
                Xianmap.put(integer, 1);
            }
            XianChangMap.put("array", Xianmap);
            Integer count =  tModelMapper.selectDangerCountByDepartAndUserId(level1, company.getUserId(),2);
            if(null==count){
                XianChangMap.put("count", 0);
            }else{
                XianChangMap.put("count", count);
            }

        }

        for (Map<String, Object> jiChuMap: jiChuItem) {
            Map<Integer, Integer> Jimap = new LinkedHashMap<Integer, Integer>();
            Jimap.put(5, 0);
            Jimap.put(1, 0);
            Jimap.put(2, 0);
            /*Jimap.put(3, 0);*/
            Jimap.put(4, 0);
            String level1 = (String) jiChuMap.get("level1");
            List<Integer> Jitypes = tModelMapper.selecttype(level1, company.getUserId(), 1, flag);
            for (Integer integer : Jitypes) {
                Jimap.put(integer, 1);
            }
            Integer count =  tModelMapper.selectDangerCountByDepartAndUserId(level1, company.getUserId(),1);
            if(null==count){
                jiChuMap.put("count", 0);
            }else{
                jiChuMap.put("count", count);
            }

            jiChuMap.put("array", Jimap);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> list = zzjgDepartmentMapper.selectHiddenPlan(company.getUserId());

        List<Map<String, Object>> list1 = hiddenPlanMapper.selectCountAll(company.getUserId());

        List<Map<String, Object>> hiddenPlanList = hiddenPlanMapper.findDpid(0,company.getUserId());

        if (hiddenPlanList.size() == 0){
            HiddenPlan hiddenPlan = new HiddenPlan();
            hiddenPlan.setUid(company.getUserId());
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

            List<Map<String, Object>> hiddenPlanList1 = hiddenPlanMapper.findDpid(0,company.getUserId());

            model.addAttribute("hiddenPlanList",hiddenPlanList1);
        }else {

            model.addAttribute("hiddenPlanList",hiddenPlanList);
        }

        model.addAttribute("data",sdf.format(new Date()));
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("jiChuItem", jiChuItem);
        model.addAttribute("xianChangItem", XianChangItem);
        model.addAttribute("companyName", company.getName());
        model.addAttribute("plan", plan);
        model.addAttribute("list1",list1);
        model.addAttribute("name","合计");
        model.addAttribute("userId", userId);
        return "steel/check/model-list-main";

    }
    /**
     * TODO 检查设置与实施中行政检查/部门抽查 显示检查记录(2019/7/5)
     *
     * @return
     */
    @RequestMapping(value = "model-list-cx2")
    public String modelListCX2(Integer flag, Integer type, Integer template, HttpServletRequest request, Model model, Integer status, Integer userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("flag", flag);
        model.addAttribute("template", template);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);

        status = status == null ? 2 : status;
        m.put("status", status);
        if (setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);

            model.addAttribute("list", list);

            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));
            }
            model.addAttribute("sum", sum);
        }
        model.addAttribute("userId", userId);
        return "steel/check/model-list-cx";
    }
    /**
     * 检查历史
     * TODO 排查治理记录 隐患排查记录(只需要已经检查过的,没有不合格记录的)
     * user. userType : 管理类型  1 超管 2普管 3镇 4 村 5 企业 6区县 7市 8省
     */
    @RequestMapping(value = "check-list")//flag:3 部门抽查
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer townId, Integer villageId,
                               Integer status, Integer flag, Model model,String dmName, Integer userId) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        Map<String, Object> m = new HashMap<String, Object>();

        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
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
        if(Objects.equals("",dmName)){

        }else{
            m.put("dmName",dmName);
        }

        // 进行判断

        if (setUserId(user, m)) {
            clearVillageTown(m);

            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            //List<Map<String, Object>> list = tCheckMapper.selectList3(m);

            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {
                DynamicParameter<String, Object> id = tCheckMapper.selectCompany((Integer) list.get(i).get("id"));
                list.get(i).put("listM",id);
                sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));

            }
            model.addAttribute("sum", sum);
            model.addAttribute("list", list);


            m.put("dmName",null);
            List<Map<String, Object>> list2 = tCheckMapper.selectList(m);
            for (int i = 0; i < list2.size(); i++) {
                set.add(list2.get(i).get("depart"));
            }

        }
        model.addAttribute("set",set);
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("townId", townId);
        model.addAttribute("villageId", villageId);
        model.addAttribute("dmName",dmName);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        model.addAttribute("userId", userId);
        if (user.getUserType() == 5) {
            // 表示等于5的话就将页面进行跳转
            return "steel/check/check-list";
        }
        // TODO 找到这个界面
        return "village/danger/check-list";
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
    public String hiddenDangerList(HttpServletRequest request, Model model, Integer flag, Integer status, Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Company company = companyMapper.selectByPrimaryKey(userId);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("userId", user.getId());

        List<Map> list = new ArrayList<>();
        if (flag == 1) {

            list = tCheckItemMapper.selectListBystatus(user.getId(), flag);

            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
                String level = (String) map.get("level");
                if (null != level && "红色".equals(level)) {
                    map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                }
                map.put("realTimeStr", format);

                if("全公司".equals(map.get("depart"))){
                    Integer checkId = (Integer) map.get("checkId");
                    map.put("fjgkfzr", tCheckMapper.selectByPrimaryKey(checkId).getDapartContact());
                    Integer industryType = (Integer) map.get("industryType");
                    if(null!=industryType&&1==industryType){
                        map.put("level2",tLevelMapper.selectByPrimaryKey((Integer)map.get("levelId")).getLevel2());
                    }else if (null!=map.get("industryType")&&2==map.get("industryType")){
                        map.put("level2",aDangerManualMapper.selectByPrimaryKey((Integer)map.get("levelId")).getLevel2());
                    }
                }

                if(null==map.get("fjgkfzr")||"".equals(map.get("fjgkfzr"))){
                    String name = "";
                    TCheck tc = tCheckMapper.selectByPrimaryKey((Integer) map.get("checkId"));
                    // 表示没有被检查人员 根据部门名称获取这个部门的被检查人员然后随便抓一个
                    List<Integer> integers = tCheckItemMapper.selectLevelIdByCheckId((Integer)map.get("checkItemId"));
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
            list = tCheckItemMapper.selectXZListBystatus(user.getId(), flag);
            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
                String levelId ="";
                if(null!=map.get("industryType")&&1==map.get("industryType")){
                    Integer levelId1 = (Integer) map.get("levelId");
                    if(null!=levelId1){
                        levelId=  tLevelMapper.selectByPrimaryKey(levelId1).getLevel2();
                    }

                }else if (null!=map.get("industryType")&&2==map.get("industryType")){
                    levelId = aDangerManualMapper.selectByPrimaryKey((Integer) map.get("levelId")).getLevel2();
                }

                if(StringUtils.isBlank(levelId)){
                    map.put("level2",levelId);
                }

                map.put("realTimeStr", format);
                map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                // 获取
            }
        } else if (flag == 3) {
            // 表示的是部门抽查
            list = tCheckItemMapper.selectBMCCListBystatus(user.getId(), flag);
            for (Map map : list) {
                Date realTime = (Date) map.get("realTime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String format = sdf.format(realTime);
                //  String level = (String) map.get("level");
                if(null!=map.get("industryType")&&1==map.get("industryType")){
                    map.put("level2",tLevelMapper.selectByPrimaryKey((Integer)map.get("levelId")).getLevel2());
                }else if (null!=map.get("industryType")&&2==map.get("industryType")){
                    map.put("level2",aDangerManualMapper.selectByPrimaryKey((Integer)map.get("levelId")).getLevel2());
                }
                map.put("fjgkfzr", company.getCharge() + company.getChargeContact());
                map.put("realTimeStr", format);
            }
        }
        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("host", host);
        model.addAttribute("list", list);
        model.addAttribute("userId", user.getId());
        return "steel/check/hidden-danger-list";
    }

    /**
     * create by  : 小明！！！
     * description: TODO 排查数据分析页面
     * create time: 2019/8/13 11:02
     */
    @RequestMapping(value = "jx-analysis")
    public String jxAnalysis(HttpServletRequest request,Model model,Integer flag, Integer userId){

        User user = userMapper.selectByPrimaryKey(userId);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        List<Map<String,Object>> list =  hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <list.size() ; i++) {
            if(i == list.size()-1){
                if (null == list.get(i).get("name")){
                    sb.append("'").append(company.getName()).append("'");
                }else {
                    sb.append("'").append(list.get(i).get("name")).append("'");
                }
            }else {
                if (null == list.get(i).get("name")){
                    sb.append("'").append(company.getName()).append("',");
                }else {
                    sb.append("'").append(list.get(i).get("name")).append("',");
                }
            }

        }

        if (null == flag){
            flag = 1;
        }

        Integer number1 = tCheckItemMapper.selectHiddenSources(1,String.valueOf(user.getId())); // 企业自查

        Integer number2 = tCheckItemMapper.selectHiddenSources(2,String.valueOf(user.getId())); // 行政检查

        Integer number3 = tCheckItemMapper.selectHiddenSources(3,String.valueOf(user.getId())); // 第三方检查

        Integer count = 0;
        Integer a = 0;
        Integer b = 0;
        Integer c = 0;
        Integer d = 0;
        Integer e = 0;
        Integer f = 0;
        Integer g = 0;
        Integer h = 0;
        Integer i = 0;
        Integer j = 0;
        Integer k = 0;
        Integer l = 0;
        Integer m = 0;
        Integer n = 0;

        if (flag == 1){
            a = tCheckItemMapper.zhuChartData78("生产工艺",flag,String.valueOf(user.getId()),sb.toString()); // 生产工艺 隐患数据

            b = tCheckItemMapper.zhuChartData78("设备设施",flag,String.valueOf(user.getId()),sb.toString()); // 设备设施 隐患数据

            c = tCheckItemMapper.zhuChartData78("特种设备",flag,String.valueOf(user.getId()),sb.toString()); // 特种设备 隐患数据

            d = tCheckItemMapper.zhuChartData78("消防安全",flag,String.valueOf(user.getId()),sb.toString()); // 消防安全 隐患数据

            e = tCheckItemMapper.zhuChartData78("用电安全",flag,String.valueOf(user.getId()),sb.toString()); // 用电安全 隐患数据

            f = tCheckItemMapper.zhuChartData78("行为环境",flag,String.valueOf(user.getId()),sb.toString()); // 行为环境 隐患数据

            g = tCheckItemMapper.zhuChartData78("公辅设备",flag,String.valueOf(user.getId()),sb.toString()); // 公辅设备 隐患数据

            h = tCheckItemMapper.zhuChartData78("危化管理",flag,String.valueOf(user.getId()),sb.toString()); // 危化管理 隐患数据

            i = tCheckItemMapper.zhuChartData78("基础管理",flag,String.valueOf(user.getId()),sb.toString()); // 基础管理 隐患数据

            j = tCheckItemMapper.zhuChartData78("防雷静电",flag,String.valueOf(user.getId()),sb.toString()); // 防雷静电 隐患数据

            k = tCheckItemMapper.zhuChartData78("安全设施",flag,String.valueOf(user.getId()),sb.toString()); // 安全设施 隐患数据

            l = tCheckItemMapper.zhuChartData78("职业卫生",flag,String.valueOf(user.getId()),sb.toString()); // 职业卫生 隐患数据

            m = tCheckItemMapper.zhuChartData78("生产现场",flag,String.valueOf(user.getId()),sb.toString()); // 生产现场 隐患数据

            n = tCheckItemMapper.zhuChartData781(flag,String.valueOf(user.getId()),sb.toString()); // 其他 隐患数据

        }else {

            a = tCheckItemMapper.zhuChartData77("生产工艺",flag,String.valueOf(user.getId())); // 生产工艺 隐患数据

            b = tCheckItemMapper.zhuChartData77("设备设施",flag,String.valueOf(user.getId())); // 设备设施 隐患数据

            c = tCheckItemMapper.zhuChartData77("特种设备",flag,String.valueOf(user.getId())); // 特种设备 隐患数据

            d = tCheckItemMapper.zhuChartData77("消防安全",flag,String.valueOf(user.getId())); // 消防安全 隐患数据

            e = tCheckItemMapper.zhuChartData77("用电安全",flag,String.valueOf(user.getId())); // 用电安全 隐患数据

            f = tCheckItemMapper.zhuChartData77("行为环境",flag,String.valueOf(user.getId())); // 行为环境 隐患数据

            g = tCheckItemMapper.zhuChartData77("公辅设备",flag,String.valueOf(user.getId())); // 公辅设备 隐患数据

            h = tCheckItemMapper.zhuChartData77("危化管理",flag,String.valueOf(user.getId())); // 危化管理 隐患数据

            i = tCheckItemMapper.zhuChartData77("基础管理",flag,String.valueOf(user.getId())); // 基础管理 隐患数据

            j = tCheckItemMapper.zhuChartData77("防雷静电",flag,String.valueOf(user.getId())); // 防雷静电 隐患数据

            k = tCheckItemMapper.zhuChartData77("安全设施",flag,String.valueOf(user.getId())); // 安全设施 隐患数据

            l = tCheckItemMapper.zhuChartData77("职业卫生",flag,String.valueOf(user.getId())); // 职业卫生 隐患数据

            m = tCheckItemMapper.zhuChartData77("生产现场",flag,String.valueOf(user.getId())); // 生产现场 隐患数据

            n = tCheckItemMapper.zhuChartData771(flag,String.valueOf(user.getId())); // 其他 隐患数据
        }

        model.addAttribute("a",a);
        model.addAttribute("b",b);
        model.addAttribute("c",c);
        model.addAttribute("d",d);
        model.addAttribute("e",e);
        model.addAttribute("f",f);
        model.addAttribute("g",g);
        model.addAttribute("h",h);
        model.addAttribute("i",i);
        model.addAttribute("j",j);
        model.addAttribute("k",k);
        model.addAttribute("l",l);
        model.addAttribute("m",m);
        model.addAttribute("n",n);

        count = a + b + c + d + e + f + g + h + i + j + k + l + m + n;

        model.addAttribute("count",count);

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != count && 0 != count) {

            if (null != a && 0 != a) {
                String str = df.format((float) a / count);
                model.addAttribute("a1",str);

            } else {
                model.addAttribute("a1","0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != b && 0 != b) {
                String str = df.format((float) b / count);
                model.addAttribute("b1",str);

            } else {
                model.addAttribute("b1","0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != c && 0 != c) {
                String str = df.format((float) c / count);
                model.addAttribute("c1",str);

            } else {
                model.addAttribute("c1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != d && 0 != d) {
                String str = df.format((float) d / count);
                model.addAttribute("d1",str);

            } else {
                model.addAttribute("d1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != e && 0 != e) {
                String str = df.format((float) e / count);
                model.addAttribute("e1",str);

            } else {
                model.addAttribute("e1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != f && 0 != f) {
                String str = df.format((float) f / count);
                model.addAttribute("f1",str);

            } else {
                model.addAttribute("f1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != g && 0 != g) {
                String str = df.format((float) g / count);
                model.addAttribute("g1",str);

            } else {
                model.addAttribute("g1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != h && 0 != h) {
                String str = df.format((float) h / count);
                model.addAttribute("h1",str);

            } else {
                model.addAttribute("h1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != i && 0 != i) {
                String str = df.format((float) i / count);
                model.addAttribute("i1",str);

            } else {
                model.addAttribute("i1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != j && 0 != j) {
                String str = df.format((float) j / count);
                model.addAttribute("j1",str);

            } else {
                model.addAttribute("j1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != k && 0 != k) {
                String str = df.format((float) k / count);
                model.addAttribute("k1",str);

            } else {
                model.addAttribute("k1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != l && 0 != l) {
                String str = df.format((float) l / count);
                model.addAttribute("l1",str);

            } else {
                model.addAttribute("l1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != m && 0 != m) {
                String str = df.format((float) m / count);
                model.addAttribute("m1",str);

            } else {
                model.addAttribute("m1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != n && 0 != n) {
                String str = df.format((float) n / count);
                model.addAttribute("n1",str);

            } else {
                model.addAttribute("n1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

        }else {
            model.addAttribute("a1","0.00");
            model.addAttribute("b1","0.00");
            model.addAttribute("c1","0.00");
            model.addAttribute("d1","0.00");
            model.addAttribute("e1","0.00");
            model.addAttribute("f1","0.00");
            model.addAttribute("g1","0.00");
            model.addAttribute("h1","0.00");
            model.addAttribute("i1","0.00");
            model.addAttribute("j1","0.00");
            model.addAttribute("k1","0.00");
            model.addAttribute("l1","0.00");
            model.addAttribute("m1","0.00");
            model.addAttribute("n1","0.00");

        }

        model.addAttribute("number1",number1);
        model.addAttribute("number2",number2);
        model.addAttribute("number3",number3);
        model.addAttribute("flag",flag);

        return "company/danger/jx-analysis";
    }

    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析
     * create time: 2019/8/13 14:34
     */
    @RequestMapping(value = "yh-analysis")
    public String yhAnalysis(HttpServletRequest request, Model model, Integer flag, Integer userId){

        User user = userMapper.selectByPrimaryKey(userId);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        List<Map<String,Object>> list =  hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <list.size() ; i++) {
            if(i == list.size()-1){
                if (null == list.get(i).get("name")){
                    sb.append("'").append(company.getName()).append("'");
                }else {
                    sb.append("'").append(list.get(i).get("name")).append("'");
                }
            }else {
                if (null == list.get(i).get("name")){
                    sb.append("'").append(company.getName()).append("',");
                }else {
                    sb.append("'").append(list.get(i).get("name")).append("',");
                }
            }

        }

        if (null == flag){
            flag = 1;
        }

        Integer number3 = tCheckItemMapper.findTypeByMap(String.valueOf(user.getId()),3); // 一般隐患
        Integer number2 = tCheckItemMapper.findTypeByMap(String.valueOf(user.getId()),2); // 重大隐患
        Integer number1 = tCheckItemMapper.findTypeByMap(String.valueOf(user.getId()),1); // 较大隐患

        model.addAttribute("number1",number1);
        model.addAttribute("number2",number2);
        model.addAttribute("number3",number3);

        Integer count = 0;
        Integer a = 0;
        Integer b = 0;
        Integer c = 0;
        Integer d = 0;
        Integer e = 0;
        Integer f = 0;
        Integer g = 0;
        Integer h = 0;
        Integer i = 0;
        Integer j = 0;
        Integer k = 0;
        Integer l = 0;
        Integer m = 0;
        Integer n = 0;
        if (flag == 1){

            a = tCheckItemMapper.zhuChartData88("生产工艺",flag,String.valueOf(user.getId()),sb.toString()); // 生产工艺 隐患数据

            b = tCheckItemMapper.zhuChartData88("设备设施",flag,String.valueOf(user.getId()),sb.toString()); // 设备设施 隐患数据

            c = tCheckItemMapper.zhuChartData88("特种设备",flag,String.valueOf(user.getId()),sb.toString()); // 特种设备 隐患数据

            d = tCheckItemMapper.zhuChartData88("消防安全",flag,String.valueOf(user.getId()),sb.toString()); // 消防安全 隐患数据

            e = tCheckItemMapper.zhuChartData88("用电安全",flag,String.valueOf(user.getId()),sb.toString()); // 用电安全 隐患数据

            f = tCheckItemMapper.zhuChartData88("行为环境",flag,String.valueOf(user.getId()),sb.toString()); // 行为环境 隐患数据

            g = tCheckItemMapper.zhuChartData88("公辅设备",flag,String.valueOf(user.getId()),sb.toString()); // 公辅设备 隐患数据

            h = tCheckItemMapper.zhuChartData88("危化管理",flag,String.valueOf(user.getId()),sb.toString()); // 危化管理 隐患数据

            i = tCheckItemMapper.zhuChartData88("基础管理",flag,String.valueOf(user.getId()),sb.toString()); // 基础管理 隐患数据

            j = tCheckItemMapper.zhuChartData88("防雷静电",flag,String.valueOf(user.getId()),sb.toString()); // 防雷静电 隐患数据

            k = tCheckItemMapper.zhuChartData88("安全设施",flag,String.valueOf(user.getId()),sb.toString()); // 安全设施 隐患数据

            l = tCheckItemMapper.zhuChartData88("职业卫生",flag,String.valueOf(user.getId()),sb.toString()); // 职业卫生 隐患数据

            m = tCheckItemMapper.zhuChartData88("生产现场",flag,String.valueOf(user.getId()),sb.toString()); // 生产现场 隐患数据

            n = tCheckItemMapper.zhuChartData881(flag,String.valueOf(user.getId()),sb.toString()); // 其他 隐患数据

        }else {

            a = tCheckItemMapper.zhuChartData89("生产工艺",flag,String.valueOf(user.getId())); // 生产工艺 隐患数据

            b = tCheckItemMapper.zhuChartData89("设备设施",flag,String.valueOf(user.getId())); // 设备设施 隐患数据

            c = tCheckItemMapper.zhuChartData89("特种设备",flag,String.valueOf(user.getId())); // 特种设备 隐患数据

            d = tCheckItemMapper.zhuChartData89("消防安全",flag,String.valueOf(user.getId())); // 消防安全 隐患数据

            e = tCheckItemMapper.zhuChartData89("用电安全",flag,String.valueOf(user.getId())); // 用电安全 隐患数据

            f = tCheckItemMapper.zhuChartData89("行为环境",flag,String.valueOf(user.getId())); // 行为环境 隐患数据

            g = tCheckItemMapper.zhuChartData89("公辅设备",flag,String.valueOf(user.getId())); // 公辅设备 隐患数据

            h = tCheckItemMapper.zhuChartData89("危化管理",flag,String.valueOf(user.getId())); // 危化管理 隐患数据

            i = tCheckItemMapper.zhuChartData89("基础管理",flag,String.valueOf(user.getId())); // 基础管理 隐患数据

            j = tCheckItemMapper.zhuChartData89("防雷静电",flag,String.valueOf(user.getId())); // 防雷静电 隐患数据

            k = tCheckItemMapper.zhuChartData89("安全设施",flag,String.valueOf(user.getId())); // 安全设施 隐患数据

            l = tCheckItemMapper.zhuChartData89("职业卫生",flag,String.valueOf(user.getId())); // 职业卫生 隐患数据

            m = tCheckItemMapper.zhuChartData89("生产现场",flag,String.valueOf(user.getId())); // 生产现场 隐患数据

            n = tCheckItemMapper.zhuChartData891(flag,String.valueOf(user.getId())); // 其他 隐患数据
        }

        model.addAttribute("a",a);
        model.addAttribute("b",b);
        model.addAttribute("c",c);
        model.addAttribute("d",d);
        model.addAttribute("e",e);
        model.addAttribute("f",f);
        model.addAttribute("g",g);
        model.addAttribute("h",h);
        model.addAttribute("i",i);
        model.addAttribute("j",j);
        model.addAttribute("k",k);
        model.addAttribute("l",l);
        model.addAttribute("m",m);
        model.addAttribute("n",n);

        count = a + b + c + d + e + f + g + h + i + j + k + l + m + n;

        model.addAttribute("count",count);

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != count && 0 != count) {

            if (null != a && 0 != a) {
                String str = df.format((float) a / count);
                model.addAttribute("a1",str);

            } else {
                model.addAttribute("a1","0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != b && 0 != b) {
                String str = df.format((float) b / count);
                model.addAttribute("b1",str);

            } else {
                model.addAttribute("b1","0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != c && 0 != c) {
                String str = df.format((float) c / count);
                model.addAttribute("c1",str);

            } else {
                model.addAttribute("c1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != d && 0 != d) {
                String str = df.format((float) d / count);
                model.addAttribute("d1",str);

            } else {
                model.addAttribute("d1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != e && 0 != e) {
                String str = df.format((float) e / count);
                model.addAttribute("e1",str);

            } else {
                model.addAttribute("e1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != f && 0 != f) {
                String str = df.format((float) f / count);
                model.addAttribute("f1",str);

            } else {
                model.addAttribute("f1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != g && 0 != g) {
                String str = df.format((float) g / count);
                model.addAttribute("g1",str);

            } else {
                model.addAttribute("g1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != h && 0 != h) {
                String str = df.format((float) h / count);
                model.addAttribute("h1",str);

            } else {
                model.addAttribute("h1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != i && 0 != i) {
                String str = df.format((float) i / count);
                model.addAttribute("i1",str);

            } else {
                model.addAttribute("i1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != j && 0 != j) {
                String str = df.format((float) j / count);
                model.addAttribute("j1",str);

            } else {
                model.addAttribute("j1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != k && 0 != k) {
                String str = df.format((float) k / count);
                model.addAttribute("k1",str);

            } else {
                model.addAttribute("k1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != l && 0 != l) {
                String str = df.format((float) l / count);
                model.addAttribute("l1",str);

            } else {
                model.addAttribute("l1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != m && 0 != m) {
                String str = df.format((float) m / count);
                model.addAttribute("m1",str);

            } else {
                model.addAttribute("m1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != n && 0 != n) {
                String str = df.format((float) n / count);
                model.addAttribute("n1",str);

            } else {
                model.addAttribute("n1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

        }else {
            model.addAttribute("a1","0.00");
            model.addAttribute("b1","0.00");
            model.addAttribute("c1","0.00");
            model.addAttribute("d1","0.00");
            model.addAttribute("e1","0.00");
            model.addAttribute("f1","0.00");
            model.addAttribute("g1","0.00");
            model.addAttribute("h1","0.00");
            model.addAttribute("i1","0.00");
            model.addAttribute("j1","0.00");
            model.addAttribute("k1","0.00");
            model.addAttribute("l1","0.00");
            model.addAttribute("m1","0.00");
            model.addAttribute("n1","0.00");

        }
        model.addAttribute("flag",flag);
        return "company/danger/yh-analysis";
    }
    /**
     * create by  : 小明！！！
     * description: TODO  治理数据分析
     * create time: 2019/8/13 11:17
     */
    @RequestMapping(value = "zl-analysis")
    public String zlAnalysis(HttpServletRequest request, Model model, Integer userId){

        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Integer number4 = tCheckItemMapper.findFileByMap(user.getId(),3); // 一般隐患
        Integer number5 = tCheckItemMapper.findFileByMap(user.getId(),2); // 重大隐患
        Integer number6 = tCheckItemMapper.findFileByMap(user.getId(),1); // 较大隐患

        model.addAttribute("number4",number4);
        model.addAttribute("number5",number5);
        model.addAttribute("number6",number6);


        Integer a = tCheckItemMapper.zhuChartData124("生产工艺",user.getId()); // 生产工艺 隐患数据
        model.addAttribute("a",a);

        Integer b = tCheckItemMapper.zhuChartData124("设备设施",user.getId()); // 设备设施 隐患数据
        model.addAttribute("b",b);

        Integer c = tCheckItemMapper.zhuChartData124("特种设备",user.getId()); // 特种设备 隐患数据
        model.addAttribute("c",c);

        Integer d = tCheckItemMapper.zhuChartData124("消防安全",user.getId()); // 消防安全 隐患数据
        model.addAttribute("d",d);

        Integer e = tCheckItemMapper.zhuChartData124("用电安全",user.getId()); // 用电安全 隐患数据
        model.addAttribute("e",e);

        Integer f = tCheckItemMapper.zhuChartData124("行为环境",user.getId()); // 行为环境 隐患数据
        model.addAttribute("f",f);

        Integer g = tCheckItemMapper.zhuChartData124("公辅设备",user.getId()); // 公辅设备 隐患数据
        model.addAttribute("g",g);

        Integer h = tCheckItemMapper.zhuChartData124("危化管理",user.getId()); // 危化管理 隐患数据
        model.addAttribute("h",h);

        Integer i = tCheckItemMapper.zhuChartData124("基础管理",user.getId()); // 基础管理 隐患数据
        model.addAttribute("i",i);

        Integer j = tCheckItemMapper.zhuChartData124("防雷静电",user.getId()); // 防雷静电 隐患数据
        model.addAttribute("j",j);

        Integer k = tCheckItemMapper.zhuChartData124("安全设施",user.getId()); // 安全设施 隐患数据
        model.addAttribute("k",k);

        Integer l = tCheckItemMapper.zhuChartData124("职业卫生",user.getId()); // 职业卫生 隐患数据
        model.addAttribute("l",l);

        Integer m = tCheckItemMapper.zhuChartData124("生产现场",user.getId()); // 生产现场 隐患数据
        model.addAttribute("m",m);

        Integer n = tCheckItemMapper.zhuChartData1241(user.getId(),company.getName()); // 其他 隐患数据
        model.addAttribute("n",n);

        Integer count = a + b + c + d + e + f + g + h + i + j + k + l + m + n;

        model.addAttribute("count",count);

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != count && 0 != count) {

            if (null != a && 0 != a) {
                String str = df.format((float) a / count);
                model.addAttribute("a1",str);

            } else {
                model.addAttribute("a1","0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != b && 0 != b) {
                String str = df.format((float) b / count);
                model.addAttribute("b1",str);

            } else {
                model.addAttribute("b1","0.00");  // 重大隐患 未治理 占比数据 竖
            }

            if (null != c && 0 != c) {
                String str = df.format((float) c / count);
                model.addAttribute("c1",str);

            } else {
                model.addAttribute("c1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != d && 0 != d) {
                String str = df.format((float) d / count);
                model.addAttribute("d1",str);

            } else {
                model.addAttribute("d1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != e && 0 != e) {
                String str = df.format((float) e / count);
                model.addAttribute("e1",str);

            } else {
                model.addAttribute("e1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != f && 0 != f) {
                String str = df.format((float) f / count);
                model.addAttribute("f1",str);

            } else {
                model.addAttribute("f1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != g && 0 != g) {
                String str = df.format((float) g / count);
                model.addAttribute("g1",str);

            } else {
                model.addAttribute("g1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != h && 0 != h) {
                String str = df.format((float) h / count);
                model.addAttribute("h1",str);

            } else {
                model.addAttribute("h1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != i && 0 != i) {
                String str = df.format((float) i / count);
                model.addAttribute("i1",str);

            } else {
                model.addAttribute("i1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != j && 0 != j) {
                String str = df.format((float) j / count);
                model.addAttribute("j1",str);

            } else {
                model.addAttribute("j1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != k && 0 != k) {
                String str = df.format((float) k / count);
                model.addAttribute("k1",str);

            } else {
                model.addAttribute("k1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != l && 0 != l) {
                String str = df.format((float) l / count);
                model.addAttribute("l1",str);

            } else {
                model.addAttribute("l1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != m && 0 != m) {
                String str = df.format((float) m / count);
                model.addAttribute("m1",str);

            } else {
                model.addAttribute("m1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != n && 0 != n) {
                String str = df.format((float) n / count);
                model.addAttribute("n1",str);

            } else {
                model.addAttribute("n1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

        }else {
            model.addAttribute("a1","0.00");
            model.addAttribute("b1","0.00");
            model.addAttribute("c1","0.00");
            model.addAttribute("d1","0.00");
            model.addAttribute("e1","0.00");
            model.addAttribute("f1","0.00");
            model.addAttribute("g1","0.00");
            model.addAttribute("h1","0.00");
            model.addAttribute("i1","0.00");
            model.addAttribute("j1","0.00");
            model.addAttribute("k1","0.00");
            model.addAttribute("l1","0.00");
            model.addAttribute("m1","0.00");
            model.addAttribute("n1","0.00");

        }
        return "company/danger/zl-analysis";
    }

}
