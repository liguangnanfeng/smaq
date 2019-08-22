package com.spring.web.controller;

import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.ibatis.LlHashMap;
import com.spring.web.model.*;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.user.UserService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.tobject.user.LoginReqDTO;
import com.spring.web.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
