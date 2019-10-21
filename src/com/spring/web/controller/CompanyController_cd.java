/**
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd.
 * All right reserved.
 */
package com.spring.web.controller;

import com.alibaba.fastjson.JSON;
import com.spring.web.BaseController;
import com.spring.web.dao.HiddenPlanMapper;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.*;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.service.trouble.TroubleService;
import com.spring.web.tobject.cgf.CompanyListReqDTO;
import com.spring.web.tobject.cgf.ModelSaveReqDTO;
import com.spring.web.tobject.cgf.RecheckSaveReqDTO;
import com.spring.web.tobject.cgf.ThreeSaveReqDTO;
import com.spring.web.tobject.user.ModifyPwdReqDTO;
import com.spring.web.util.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.*;
import java.util.*;

/**
 * @author CGF
 * @version V1.0
 * @Title: CompanyController_cd
 * @Description: TODO()
 * @date 2017年7月27日 上午9:49:51
 */
@Controller
@RequestMapping("company")
public class CompanyController_cd extends BaseController {
    /**
     * @Fields serialVersionUID : TODO()
     */
    private static final long serialVersionUID = 6784473257577263720L;
    @Autowired
    private TroubleService troubleService;
    @Autowired
    private CgfService cgfService;
    @Autowired
    private HiddenPlanMapper hiddenPlanMapper;

    /**
     * 修改风险等级
     */
    @RequestMapping("getCompany")
    public @ResponseBody
    Result getCompany(HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company c = companyMapper.selectByPrimaryKey(user.getId());
        result.setMap("com", c);
        return result;
    }

//    /**
//     * 前台中间页
//     * 
//     * @throws Exception
//     */
//    @RequestMapping("welcome")
//    public String welcome(Model model, HttpServletRequest request) throws Exception {
//        User user = getLoginUser(request);
//        Company c = companyMapper.selectByPrimaryKey(user.getId());
//        model.addAttribute("c", c);
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("type", "1");
//        m.clear();
//        setUserId(user, m);
//        
//        List<Integer> count = userService.selectCount(new CompanyListReqDTO(), user);
//        
//        log.error("count："+count.toString());
//        
//        model.addAttribute("lib", libraryMapper.selectLibraryList(2));
//        m.clear();
//        setUserId(user, m);
//        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
//        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总
//        
//        model.addAttribute("mc", monitorMapper.selectCount(m));
//        model.addAttribute("count", count);
//        
//        model.addAttribute("loginUserId", user.getId());
//        return "company/welcome";
//    }

    /**
     * 前台中间页
     *
     * @throws Exception
     */
    @RequestMapping("welcome")
    public String welcome(Model model, HttpServletRequest request,Integer flage) throws Exception {
        User user = getLoginUser(request);
        Company c = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("c", c);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", "1");
        m.clear();
        setUserId(user, m);
        // TODO 判断该公司的行业是否为化工行业,为化工行业就显示内容
        if (c.getIndustry().contains("化工")) {
            model.addAttribute("industry", 5);
        }

        Map<String, Object> m1 = new HashMap<String, Object>();
        setUserId(user, m1);
        m1.put("d", 1);
        m.put("status", 2);
        List<Map<String, Object>> list11 = tCheckItemMapper.findACompanyAllList(m);
        List<Map<String, Object>> list22 = tCheckItemMapper.findDangerAllList(m);
        List<Map<String, Object>> list33 = tCheckItemMapper.findLevelAllList(m);
        model.addAttribute("dangerCount", list11.size() + list22.size() + list33.size()); // 未整改

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = df.format(new Date().getTime()-15*24*60*60*1000);

        Date startTime1 = df.parse(startTime);
        Date endTime = new Date();

        Integer counts = tCheckMapper.findAllCounte(String.valueOf(user.getId()),startTime1,endTime); // 排查数据
        Integer counts1 = tCheckItemMapper.findAllCounte(String.valueOf(user.getId())); // 治理数据

        model.addAttribute("counts",counts);
        model.addAttribute("counts1",counts1);

        List<Integer> count = userService.selectCount(new CompanyListReqDTO(), user);

        model.addAttribute("lib", libraryMapper.selectLibraryList(2));
        m.clear();
        setUserId(user, m);
        model.addAttribute("dangerC", tCheckItemMapper.selectCount(m));// 隐患情况汇总
        model.addAttribute("dangerC2", tCheckItemMapper.selectZhongCount(m));// 隐患情况汇总

        model.addAttribute("mc", monitorMapper.selectCount(m));
        model.addAttribute("count", count);
        model.addAttribute("loginUserId", user.getId());

        //add 190123
        HttpSession session = request.getSession();
        User moveBeforeUser = (User) session.getAttribute("govUser");
        if (moveBeforeUser != null) {
            //log.error("moveBeforeUser："+moveBeforeUser.toString());
            model.addAttribute("moveCompany", 1);
            String nameBefore = null;
            switch (moveBeforeUser.getUserType()) {
                case 10://行业端
                    nameBefore = tradeMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName();
                    break;
                case 9://安泰
                    nameBefore = moveBeforeUser.getUserName();
                    break;
                case 6://区
                    nameBefore = districtMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName();
                    break;
                case 3://镇
                    nameBefore = townMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName();
                    break;
                case 4://村
                    nameBefore = villageMapper.selectByPrimaryKey(moveBeforeUser.getId()).getName();
                    break;

            }
            model.addAttribute("nameBefore", nameBefore);
            model.addAttribute("flage", flage);

        }

        return "company/welcome";
    }


    @RequestMapping("main")
    public String main(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company c = companyMapper.selectByPrimaryKey(user.getId());

        if (c.getIndustry().contains("化工")){
            model.addAttribute("flag", 1);
        }else {
            model.addAttribute("flag", 2);
        }

        model.addAttribute("c", c);
        model.addAttribute("userName", companyMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("loginUserId", user.getId());

        return "company/main";
    }



    /**
     * 修改风险等级
     *
     * @throws Exception
     */
    @RequestMapping("changedlvl")
    public @ResponseBody
    Result dlevel(Model model, HttpServletRequest request, Company c) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        c.setUserId(user.getId());
        companyMapper.updateByPrimaryKeySelective(c);
        return result;
    }


    /**
     * create by  : 小明！！！
     * description: TODO 页面添加
     * create time: 2019/8/5 13:54
     */
    @RequestMapping("/tables/dajiangtang")
    public String dajiangtang(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);

        model.addAttribute("userName", companyMapper.selectByPrimaryKey(user.getId()).getName());

        return "company/tables/dajiangtang";
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

        return "company/tables/yjmanage_center2";
    }



    /**
     * 前台首页
     *
     * @throws Exception
     */
    @RequestMapping("threeLeft")
    public String threeLeft(Model model, HttpServletRequest request, String leftBasic) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());

        // TODO 判断该公司的行业是否为化工行业,为化工行业就显示内容
        if (company.getIndustry().contains("化工")) {
            model.addAttribute("industry", 5);
        }

        model.addAttribute("leftBasic", leftBasic);
        model.addAttribute("userName", user.getUserName());

        return "company/threeLeft";
    }

    /**
     * TODO 基本信息页面
     */
    @RequestMapping("basic-information")
    public String basicInformation(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());

        Regulation regulationComp = regulationGet(user.getId());
        if (company.getHazard() == null) {
            if (regulationComp.getCisDanger() != null) {
                //company.setHazard(regulationComp.getCisDanger());
                //companyMapper.updateByPrimaryKey(company);
            }
        } else {
            if (regulationComp.getCisDanger() == null) {
                 //company.setHazard(0);
            } else {
                //company.setHazard(regulationComp.getCisDanger());
            }
            //companyMapper.updateByPrimaryKey(company);
        }

        if (null != company.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(company.getRegionId()));
        }

        model.addAttribute("c", company);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "company/information/information1";
    }

    /**
     * TODO 企业添加保存
     */
    @RequestMapping(value = "company-edit-do")
    public @ResponseBody
    Result companyEditDo(Company c, String jw, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        String industry = c.getIndustry();
        Company company = companyMapper.selectByPrimaryKey(c.getUserId());
        if (company != null && !industry.equals(company.getIndustry())) {
            tCompanyMapper.deleteByPrimaryKey(c.getUserId());
        }
        companyMapper.updateByPrimaryKeySelective(c);
        User user = null;
        if (StringUtils.isNotBlank(jw)) {
            user = new User();
            user.setId(c.getUserId());
            String[] x = jw.split(",");
            user.setLatitude(x[1]);
            user.setLongitude(x[0]);
            userMapper.updateByPrimaryKeySelective(user);
        }
        result.setMap("data", userMapper.selectByPrimaryKey(user.getId()));
        return result;
    }

    /**
     * 修改基本信息页面
     */
    @RequestMapping("basic-information-change")
    public String basicInformationChange(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Company c = companyMapper.selectByPrimaryKey(user.getId());
        List<Library> list1 = libraryMapper.selectLibraryList(1);
        List<Library> list2 = libraryMapper.selectLibraryList(2);
        model.addAttribute("c", c);
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        model.addAttribute("u", user);
        model.addAttribute("ind3L", tIndustryMapper.selectByType(3));// 高危检查类别
        return "company/information/information-edit1";
    }

    /**
     * 获取基本信息页面
     */
    @RequestMapping(value = "getBasicInformation-qcc", method = RequestMethod.POST)
    public @ResponseBody
    Result getBasicInformationQcc(Model model, HttpServletRequest request, String companyName)
            throws Exception {
        Result result = new ResultImpl();
        QccData qccData = qccDataMapper.selectByCompanyName(companyName);
        if (null != qccData) {
            result.setStatus("0");
            result.setMap("item", qccData.getData());
            return result;
        }
        String url = "http://api.qichacha.com/ECIV4/GetDetailsByName";
        String key = "8539cb3a475a485ca5e19a51dcc54420";
        String secretKey = "6F8E5EBC97EB2D950B68D760A3C3FFC8";
        String timestamp = String.valueOf((System.currentTimeMillis()/1000));//精确到秒的Unix时间戳
        Map<String, String> map = new HashMap<>();
        map.put("Timespan", timestamp);
        map.put("Token", WeixinUtil.MD5(key + timestamp + secretKey));
        Map<String, String> map1 = new HashMap<>();
        map1.put("key","8539cb3a475a485ca5e19a51dcc54420");
        map1.put("keyword", companyName);
        String data = HttpClientUtils.doGet(url, map, map1).getContent();
        qccData = new QccData();
        qccData.setCompanyName(companyName);
        qccData.setStatus(0);
        qccData.setData(data);
        qccDataMapper.insertSelective(qccData);
         //Gson gosn = new Gson();
        // QccJson qccJson = gosn.fromJson(data, QccJson.class);
        result.setStatus("0");
        result.setMap("item", data);
        return result;
    }

    /**
     * 修改基本信息页面保存
     */
    @RequestMapping("basic-information-change-save")
    public @ResponseBody
    Result basicInformationChangeSave(Model model, Company c, String jw, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        if (StringUtils.isNotBlank(jw)) {
            User user = new User();
            user.setId(c.getUserId());
            user.setLongitude(jw.split(",")[0]);
            user.setLatitude(jw.split(",")[1]);
            userMapper.updateByPrimaryKeySelective(user);

            User u = getLoginUser(request);
            u.setLongitude(jw.split(",")[0]);
            u.setLatitude(jw.split(",")[1]);
            SessionUtil.setUser(request, u);
        }
        companyMapper.updateByPrimaryKeySelective(c);
        return result;
    }

    /**
     * 企业位置页面
     */
    @RequestMapping("information/information4")
    public String information4(Model model, HttpServletRequest request, Integer userId) throws Exception {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(null == userId ? user.getId() : userId);
        if (null != company.getRegionId()) {
            model.addAttribute("regionName", globalRegionMapper.selectRegionName(company.getRegionId()));
        }
        model.addAttribute("company", company);
        model.addAttribute("user", userMapper.selectByPrimaryKey(company.getUserId()));
        model.addAttribute("v", userMapper.selectByPrimaryKey(company.getVillageId()));
        return "company/information/information4";
    }

    /**
     * 规章制度页面
     */
    @RequestMapping("rules-institution")
    public String rulesInstitution(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "company/information/information2";
    }

    /**
     * 规章制度编辑页面
     */
    @RequestMapping("rules-institution-change")
    public String rulesInstitutionChange(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "company/information/information-edit2";
    }

    /**
     * 重大危险源编辑页面
     */
    @RequestMapping("information/information-edit3")
    public String information3(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "company/information/information-edit3";
    }

    /**
     * 重大危险源编辑保存
     */
    @RequestMapping("information/information3-save")
    public @ResponseBody
    Result information3Change(Model model, HttpServletRequest request, Regulation regulation) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        regulation.setUserId(user.getId());
        regulationMapper.updateByPrimaryKeySelective(regulation);
        return result;
    }

    /**
     * 规章制度编辑保存
     */
    @RequestMapping("rules-institution-change-save")
    public @ResponseBody
    Result rulesInstitutionChangeSave(Regulation r) throws Exception {
        Result result = new ResultImpl();
        regulationMapper.updateByPrimaryKeySelective(r);
        return result;
    }

    /**
     * 重大危险源页面
     */
    @RequestMapping("information/information3")
    public String danger(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Regulation r = regulationGet(user.getId());
        model.addAttribute("r", r);
        return "company/information/information3";
    }

    /**
     * TODO (设备设施管理)  主要设备清单页面
     */
    @RequestMapping("product/mequipment-list")
    public String mainEquipmentList(Model model, HttpServletRequest request, String equipmentName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("equipmentName", equipmentName);
        model.addAttribute("list", mequipmentMapper.selectByUserId(m));
        model.addAttribute("equipmentName", equipmentName);
        return "company/product/mequipment-list";
    }

    /**
     * 主要设备清单页面(打印)
     */
    @RequestMapping("product/mequipment-list-print")
    public String mainEquipmentListPrint(Model model, HttpServletRequest request, String equipmentName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("equipmentName", equipmentName);
        model.addAttribute("list", mequipmentMapper.selectByUserId(m));
        model.addAttribute("equipmentName", equipmentName);
        return "company/product/mequipment-list-print";
    }

    /**
     * 安全设施登记台账
     */
    @RequestMapping("safety-facilities")
    public String name(Model model, HttpServletRequest request, String name) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("name", name);
        model.addAttribute("list", safetyFacilitiesMapper.selectByMap(m));
        model.addAttribute("name", name);
        return "company/product/sf-list";
    }

    /**
     * 设备编辑
     */
    @RequestMapping(value = "product/mequipment-edit")
    public String meqEdit(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("meq", mequipmentMapper.selectByPrimaryKey(id));
        return "company/product/mequipment-edit";
    }

    /**
     * TO添加设备
     */
    @RequestMapping(value = "product/mequipment-add")
    public String addMequipment(HttpServletRequest request) throws Exception {
        return "company/product/mequipment-add";
    }

    /**
     * TO添加安全设施登记台账
     */
    @RequestMapping(value = "sf-add")
    public String sfAdd(Model model, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("sf", safetyFacilitiesMapper.selectByPrimaryKey(id));
        }
        return "company/product/sf-add";
    }

    /**
     * 设备添加/编辑保存
     */
    @RequestMapping(value = "product/mequipment-save")
    public @ResponseBody
    Result meqSave(Mequipment mequipment, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        mequipment.setUserId(user.getId());
        if (null == mequipment.getId()) {
            mequipmentMapper.insertSelective(mequipment);
        } else {
            mequipmentMapper.updateByPrimaryKeySelective(mequipment);
        }
        return result;
    }

    /**
     * 安全设施登记台账保存
     */
    @RequestMapping(value = "sf-save")
    public @ResponseBody
    Result meqSave(SafetyFacilities mequipment, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        mequipment.setUserId(user.getId());
        if (null == mequipment.getId()) {
            safetyFacilitiesMapper.insertSelective(mequipment);
        } else {
            safetyFacilitiesMapper.updateByPrimaryKeySelective(mequipment);
        }
        return result;
    }

    /**
     * 删除主要设备
     */
    @RequestMapping(value = "product/deleteEquipment")
    public @ResponseBody
    Result deleteEquipment(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        mequipmentMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 删除安全设施登记台账
     */
    @RequestMapping(value = "sf-del")
    public @ResponseBody
    Result sfDel(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        safetyFacilitiesMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 主要原辅材料页面
     */
    @RequestMapping("product/material-list")
    public String mainMaterialList(Model model, HttpServletRequest request, String materialName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("materialName", materialName);
        model.addAttribute("list", materialMapper.selectByUserId(m));
        model.addAttribute("materialName", materialName);
        return "company/product/material-list";
    }



    /**
     * 下载资料
     */
    @RequestMapping(value = "downloadall")
    public void hedownloadll(String filename, String fileurl, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        File realPath = new File(request.getServletContext().getRealPath("/"), fileurl.replace("/smaq", ""));
        if (!realPath.exists()) {
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
     * 材料编辑
     */
    @RequestMapping(value = "product/material-edit")
    public String materialEdit(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("ma", materialMapper.selectByPrimaryKey(id));
        return "company/product/material-edit";
    }

    /**
     * TO添加材料
     */
    @RequestMapping(value = "product/material-add")
    public String addMaterial(HttpServletRequest request) throws Exception {
        return "company/product/material-add";
    }

    /**
     * 材料添加/编辑保存
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
     * 删除材料
     */
    @RequestMapping(value = "product/deleteMaterial")
    public @ResponseBody
    Result deleteMaterial(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        materialMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 主要产品页面
     */
    @RequestMapping("product/product-list")
    public String mainProductList(Model model, HttpServletRequest request, String proName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("proName", proName);
        model.addAttribute("list", productMapper.selectByUserId(m));
        model.addAttribute("proName", proName);
        return "company/product/product-list";
    }

    /**
     * 产品编辑
     */
    @RequestMapping(value = "product/product-edit")
    public String proEdit(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("pr", productMapper.selectByPrimaryKey(id));
        return "company/product/product-edit";
    }

    /**
     * TO添加产品
     */
    @RequestMapping(value = "product/product-add")
    public String addProduct(HttpServletRequest request) throws Exception {
        return "company/product/product-add";
    }

    /**
     * 产品添加/编辑保存
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
     * 删除主要产品
     */
    @RequestMapping(value = "product/deleteProduct")
    public @ResponseBody
    Result deleteProduct(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        productMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * TO添加安管人员
     */
    @RequestMapping(value = "train/safety-add")
    public String addSecurity(HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("list", libraryMapper.selectLibraryList(3));
        return "company/train/safety-add";
    }

    /**
     * 特种作业人员页面
     */
    @RequestMapping("train/special-list")
    public String spersonList(Model model, HttpServletRequest request, String spFlag, String spName, String spType,
                              Integer isTime) throws Exception {
        if (spFlag == null || "".equals(spFlag)) {
            spFlag = "0";
        }
        int libraryType = 10;
        if ("0".equals(spFlag)) {
            libraryType += 1;
        } else {
            libraryType += Integer.parseInt(spFlag);
        }
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("spName", spName);
        m.put("spFlag", spFlag);
        m.put("spType", spType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", spersonMapper.selectSpersonList(m));
        model.addAttribute("lib", libraryMapper.selectLibraryList(libraryType));
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("spName", spName);
        model.addAttribute("spType", spType);
        model.addAttribute("isTime", isTime);
        return "company/train/special-list";
    }

    /**
     * 特种作业人员页面
     */
    @RequestMapping("special-print")
    public String spersonPrint(Model model, HttpServletRequest request, String spFlag, String spName, String spType,
                               Integer isTime) throws Exception {
        if (spFlag == null || "".equals(spFlag)) {
            spFlag = "0";
        }
        spName = utf8Str(spName);
        int libraryType = 10;
        if ("0".equals(spFlag)) {
            libraryType += 1;
        } else {
            libraryType += Integer.parseInt(spFlag);
        }
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("spName", spName);
        m.put("spFlag", spFlag);
        m.put("spType", spType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", spersonMapper.selectSpersonList(m));
        model.addAttribute("lib", libraryMapper.selectLibraryList(libraryType));
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("spName", spName);
        model.addAttribute("spType", spType);
        model.addAttribute("isTime", isTime);
        return "company/train/special-print";
    }

    /**
     * 特种作业人员编辑
     */
    @RequestMapping(value = "train/special-edit")
    public String speEdit(@RequestParam("id") int id, Model model, String spFlag) throws Exception {
        if (spFlag == null || "".equals(spFlag)) {
            spFlag = "0";
        }
        Sperson spe = spersonMapper.selectByPrimaryKey(id);
        model.addAttribute("spe", spe);
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("lib", libraryMapper.selectLibraryList(spe.getFlag().intValue() + 10));
        return "company/train/special-edit";
    }

    /**
     * TO添加特种作业人员
     */
    @RequestMapping(value = "train/special-add")
    public String addSpe(HttpServletRequest request, Model model, String spFlag) throws Exception {
        if (spFlag == null || "".equals(spFlag)) {
            spFlag = "0";
        }
        int libraryType = 10;
        if ("0".equals(spFlag)) {
            libraryType += 1;
        } else {
            libraryType += Integer.parseInt(spFlag);
        }
        model.addAttribute("spFlag", spFlag);
        model.addAttribute("lib", libraryMapper.selectLibraryList(libraryType));
        return "company/train/special-add";
    }

    /**
     * 特种作业人员添加/编辑保存
     */
    @RequestMapping(value = "train/special-save")
    public @ResponseBody
    Result speSave(Sperson sperson, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        sperson.setUserId(user.getId());
        if (null == sperson.getId()) {
            spersonMapper.insertSelective(sperson);
        } else {
            spersonMapper.updateByPrimaryKeySelective(sperson);
        }
        return result;
    }

    /**
     * 删除特种作业人员
     */
    @RequestMapping(value = "train/deleteSpecial")
    public @ResponseBody
    Result deleteSpecial(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        spersonMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 特种设备清单页面
     */
    @RequestMapping("sequipment/sequipment-list")
    public String sequipmentList(Model model, HttpServletRequest request, String seName, Integer isTime)
            throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("seName", seName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", sequipmentMapper.selectSequipmentList(m));
        model.addAttribute("seName", seName);
        model.addAttribute("isTime", isTime);
        return "company/sequipment/sequipment-list";
    }

    /**
     * 特种设备编辑
     */
    @RequestMapping(value = "sequipment/sequipment-edit")
    public String seqEdit(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("se", sequipmentMapper.selectByPrimaryKey(id));
        return "company/sequipment/sequipment-edit";
    }

    /**
     * TO添加特种设备
     */
    @RequestMapping(value = "sequipment/sequipment-add")
    public String addSequipment(HttpServletRequest request) throws Exception {
        return "company/sequipment/sequipment-add";
    }

    /**
     * 特种设备添加/编辑保存
     */
    @RequestMapping(value = "sequipment/sequipment-save")
    public @ResponseBody
    Result seqSave(Sequipment sequipment, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        sequipment.setUserId(user.getId());
        if (null == sequipment.getId()) {
            sequipmentMapper.insertSelective(sequipment);
        } else {
            sequipmentMapper.updateByPrimaryKeySelective(sequipment);
        }
        return result;
    }

    /**
     * 删除特种设备
     */
    @RequestMapping(value = "sequipment/deleteSequipment")
    public @ResponseBody
    Result deleteSequipment(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        sequipmentMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 批量导入特种设备
     *
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "importSequipmentExcel", produces = "text/html;charset=utf-8")
    public void importSequipmentExcel(/*@RequestParam*/ MultipartFile file,Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        userService.importSequipmentExcel(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }

    /**
     * create by  : 小明！！！
     * description: TODO 批量导入原辅材料
     * create time: 2019/8/23 15:53
     */
    @RequestMapping(value = "materialsExcel", produces = "text/html;charset=utf-8")
    public void materialsExcel(/*@RequestParam*/ MultipartFile file,Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        userService.importMaterialsExcel(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }
    
    /**
     * create by  : 小明！！！
     * description: TODO  批量导入主要产品
     * create time: 2019/8/23 16:17
     */
    @RequestMapping(value = "mainProductExcel", produces = "text/html;charset=utf-8")
    public void mainProductExcel(/*@RequestParam*/ MultipartFile file,Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        userService.importMainProductExcel(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }
    
    



    /**
     * TODO 批量导入主要设备
     *
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "importMajorequipmentExcel", produces = "text/html;charset=utf-8")
    public void importMajorequipmentExcel(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        userService.importMajorequipmentExcel(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }


    /*
     * 批量导入人员！！！
     * */
    @RequestMapping(value = "importPersonExcel", produces = "text/html;charset=utf-8")
    public void importPersonExcel(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        userService.importPersonExcel(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }


    /**
     * 安全评价档案页面
     */
    @RequestMapping("evaluate/evaluation-list")
    public String evatList(Model model, HttpServletRequest request, String reportName, String evaType, Integer isTime,
                           String flag) throws Exception {
        if (flag == null || "".equals(flag)) {
            flag = "0";
        }
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("flag", flag);
        m.put("reportName", reportName);
        m.put("evaType", evaType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("list", evaluationMapper.selectEvaluationList(m));
        model.addAttribute("reportName", reportName);
        model.addAttribute("evaType", evaType);
        model.addAttribute("flag", flag);
        model.addAttribute("isTime", isTime);
        return "company/evaluate/evaluation-list";
    }

    /**
     * 安全评价编辑
     */
    @RequestMapping(value = "evaluate/evaluation-edit")
    public String evaEdit(@RequestParam("id") int id, Model model, String flag) throws Exception {
        Evaluation eva = evaluationMapper.selectByPrimaryKey(id);
        model.addAttribute("e", eva);
        model.addAttribute("flag", flag);
        return "company/evaluate/evaluation-edit";
    }

    /**
     * TO添加安全评价
     */
    @RequestMapping(value = "evaluate/evaluation-add")
    public String addEva(HttpServletRequest request, Model model, String flag) throws Exception {
        model.addAttribute("flag", flag);
        return "company/evaluate/evaluation-add";
    }

    /**
     * 从业人员健康监护
     */
    @RequestMapping(value = "cyryjkjh-add")
    public String evaEdit(Integer id, Model model) throws Exception {
        if (null != id) {
            model.addAttribute("c", cyryjkjhMapper.selectByPrimaryKey(id));
        }
        return "company/evaluate/cyryjkjh-add";
    }

    /**
     * 从业人员健康监护 删除
     */
    @RequestMapping(value = "cyryjkjh-del")
    public @ResponseBody
    Result cyryjkjhDel(Integer id, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        cyryjkjhMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 从业人员健康监护  保存
     */
    @RequestMapping(value = "cyryjkjh-save")
    public @ResponseBody
    Result cyryjkjhSave(Cyryjkjh c, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        c.setUid(user.getId());
        if (null == c.getId()) {
            cyryjkjhMapper.insertSelective(c);
        } else {
            cyryjkjhMapper.updateByPrimaryKeySelective(c);
        }
        return result;
    }

    /**
     * 安全评价添加/编辑保存
     *
     * @throws Exception
     */
    @RequestMapping(value = "evaluate/evaluation-save")
    public @ResponseBody
    Result evaSave(Evaluation evaluation, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        evaluation.setUserId(user.getId());
        if (0 == evaluation.getFlag().intValue()) {
            evaluation.setFlag(1);
        }
        if (null == evaluation.getId()) {
            evaluationMapper.insertSelective(evaluation);
        } else {
            evaluationMapper.updateByPrimaryKeySelective(evaluation);
        }
        return result;
    }

    /**
     * 删除安全评价
     */
    @RequestMapping(value = "evaluate/deleteEva")
    public @ResponseBody
    Result deleteEva(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        evaluationMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 检测页面
     */
    @RequestMapping("evaluate/detection-list")
    public String detList(Model model, HttpServletRequest request, String deType, Integer isTime) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("deType", deType);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", detectionMapper.selectDetectionList(m));
        model.addAttribute("lib", libraryMapper.selectLibraryList(6));
        model.addAttribute("deType", deType);
        model.addAttribute("isTime", isTime);
        return "company/evaluate/detection-list";
    }

    /**
     * 职业健康体检
     */
    @RequestMapping(value = "warning/examination-list")
    public String examinationList(Model model, HttpServletRequest request, Integer isTime) {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", examinationMapper.selectExaminationList(m));
        model.addAttribute("isTime", isTime);
        return "company/evaluate/examination-list";
    }

    /**
     * 安全检测编辑
     */
    @RequestMapping(value = "evaluate/detection-edit")
    public String deEdit(Integer id, Model model) throws Exception {
        if (null != id) {
            Detection de = detectionMapper.selectByPrimaryKey(id);
            model.addAttribute("d", de);
        }
        model.addAttribute("list", libraryMapper.selectLibraryList(6));
        return "company/evaluate/detection-edit";
    }

    /**
     * 安全检测添加/编辑保存
     */
    @RequestMapping(value = "evaluate/detection-save")
    public @ResponseBody
    Result deSave(Detection detection, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        detection.setUserId(user.getId());
        if (null == detection.getId()) {
            detectionMapper.insertSelective(detection);
        } else {
            detectionMapper.updateByPrimaryKeySelective(detection);
        }
        return result;
    }

    /**
     * 删除安全检测
     */
    @RequestMapping(value = "evaluate/deleteDe")
    public @ResponseBody
    Result deleteDe(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        detectionMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 职业健康检查页面
     */
    @RequestMapping("evaluate/health-list")
    public String hetList(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        model.addAttribute("list", examinationMapper.selectByUserId(m));
        return "company/evaluate/health-list";
    }

    /**
     * 健康检查编辑
     */
    @RequestMapping(value = "evaluate/health-edit")
    public String heEdit(Model model, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("e", examinationMapper.selectByPrimaryKey(id));
        }
        return "company/evaluate/health-add";
    }

    /**
     * 健康检查编辑保存
     */
    @RequestMapping(value = "evaluate/health-save")
    public @ResponseBody
    Result heSave(HttpServletRequest request, Examination e) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (null == e.getId()) {
            e.setUserId(user.getId());
            examinationMapper.insertSelective(e);
        } else {
            examinationMapper.updateByPrimaryKeySelective(e);
        }
        return result;
    }

    /**
     * 健康检查编辑sc
     */
    @RequestMapping(value = "health-del")
    public @ResponseBody
    Result heSaveDel(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        examinationMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 劳动防护用品页面
     */
    @RequestMapping("labor/labor-list")
    public String labtList(Model model, HttpServletRequest request, String name, Integer libId) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("name", name);
        m.put("libId", libId);
        model.addAttribute("list", pequipmentMapper.selectByMap(m));
        model.addAttribute("name", name);
        model.addAttribute("libId", libId);
        return "company/labor/labor-list";
    }

    /**
     * 防护用品编辑保存
     */
    @RequestMapping(value = "labor/labor-save")
    public @ResponseBody
    Result laSave(Pequipment pequipment, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        if (null == pequipment.getId()) {
            pequipment.setUserId(user.getId());
            pequipmentMapper.insertSelective(pequipment);
        } else {
            pequipmentMapper.updateByPrimaryKeySelective(pequipment);
        }
        return result;
    }

    /**
     * 防护用品 添加
     */
    @RequestMapping("pequipment-add")
    public String pequipmentAdd(Model model, HttpServletRequest request, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("p", pequipmentMapper.selectByPrimaryKey(id));
        }
        return "company/labor/labor-add";
    }

    /**
     * 防护用品编辑删除
     */
    @RequestMapping(value = "pequipment-del")
    public @ResponseBody
    Result pequipmentDel(Integer id, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        pequipmentMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 消防应急设施页面
     */
    @RequestMapping("facilities/facilities-list")
    public String facList(Model model, HttpServletRequest request, String faType, Integer flag) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("faType", faType);
        m.put("flag", flag);
        model.addAttribute("list", facilitiesMapper.selectFacilitiesList(m));
        model.addAttribute("faType", faType);
        model.addAttribute("flag", flag);
        return "company/facilities/facilities-list";
    }

    /**
     * 消防应急设施编辑
     */
    @RequestMapping(value = "facilities/facilities-edit")
    public String facilitiesEdit(Integer id, Model model, Integer flag) throws Exception {
        if (null != id) {
            model.addAttribute("f", facilitiesMapper.selectByPrimaryKey(id));
        }
        model.addAttribute("flag", flag);
        return "company/facilities/facilities-edit";
    }

    /**
     * 消防应急设备添加/编辑保存
     */
    @RequestMapping(value = "facilities/facilities-save")
    public @ResponseBody
    Result facilitiesSave(Facilities facilities, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        facilities.setUserId(user.getId());
        if (null == facilities.getId()) {
            facilitiesMapper.insertSelective(facilities);
        } else {
            facilitiesMapper.updateByPrimaryKeySelective(facilities);
        }
        return result;
    }

    /**
     * 删除特种设备
     */
    @RequestMapping(value = "facilities/deleteFa")
    public @ResponseBody
    Result deleteFacilities(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        facilitiesMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * to修改密码页面
     */
    @RequestMapping("system/change-psw")
    public String changePsw(Model model, HttpServletRequest request) throws Exception {
        return "company/system/passwordSet";
    }

    /**
     * to修改登录名页面
     */
    @RequestMapping("system/change-userName")
    public String changeUserName(Model model, HttpServletRequest request) throws Exception {
        User user = userMapper.selectByPrimaryKey(getLoginUser(request).getId());
        model.addAttribute("user", user);
        return "company/system/userNameSet";
    }

    /**
     * 验证修改登录名时的密码
     */
    @RequestMapping(value = "renzheng-psw")
    public @ResponseBody
    Result renzheng(ModifyPwdReqDTO dto, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = userMapper.selectByPrimaryKey(getLoginUser(request).getId());
        String code = user.getPsw();
        String psw = dto.getPassword();
        if (!EncryptUtil.match(code, psw)) {
            result.setStatus("1");
            result.setMap("message", "密码错误");
        } else {
            // 验证密码
            result.setStatus("0");
            result.setMap("message", "验证成功");
        }
        return result;
    }

    /**
     * 修改登录名
     */
    @RequestMapping(value = "modify-userName")
    public @ResponseBody
    Result modifyUserName(ModifyPwdReqDTO dto, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = userMapper.selectByPrimaryKey(getLoginUser(request).getId());
        String code = user.getPsw();
        String psw = dto.getPassword();
        String name = user.getUserName();
        String oldUserName = dto.getOldUserName();
        User newUserName = userMapper.selectUserByUserName(dto.getNewUserName());
        if (!EncryptUtil.match(code, psw)) {
            result.setStatus("1");
            result.setMap("message", "密码错误");
        } else if (!name.equals(oldUserName)) {
            result.setStatus("1");
            result.setMap("message", "原登录名错误");
        } else if (newUserName != null) {
            result.setStatus("1");
            result.setMap("message", "登录名已存在");
        } else {
            // 新登录名
            String xmm = dto.getNewUserName();
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setUserName(xmm);
            userMapper.updateByPrimaryKeySelective(newUser);
        }
        return result;
    }

    /**
     * 跳转页面(排查数据分析页面)
     *
     * @return
     */
    @RequestMapping("danger/danger-chart-px")
    public String dangerChartPx(Model model ,HttpServletRequest request) {
        User user = getLoginUser(request);

        List<ZzjgDepartment> list = zzjgDepartmentMapper.selectLevel1DangerIds(user.getId());

        model.addAttribute("zzjg",list);
        return "company/danger/danger-chart-px";
    }

    /**
     * 跳转页面(排查数据分析页面)
     *
     * @return
     */
    @RequestMapping("danger/danger-chart-zl")
    public String dangerChartZl(Model model ,HttpServletRequest request) {
        User user = getLoginUser(request);

        List<ZzjgDepartment> list = zzjgDepartmentMapper.selectLevel1DangerIds(user.getId());

        model.addAttribute("zzjg",list);

        return "company/danger/danger-chart-zl";
    }

    static String PP = "yyyy-MM";

    /**
     * TODO 折线图数据 根据隐患类型 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "lineChartData")
    public @ResponseBody
    Result lineChartData(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        // 传递的时间都为空
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);

        }
        // 截止时间为空
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        // 截至时间为空
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        setUserId(user, m);
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectGroupIndustryType(m);
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }
        String[] xx = new String[]{"基础管理", "现场管理", "高危作业"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        for (DynamicParameter<String, Object> dy : ll) {
            String time = dy.getString("time");
            Integer t = dy.getInteger("t");// 1 基础 2现场 3高危
            Integer c = dy.getInteger("c");
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    if (t.intValue() == 1) {
                        Integer[] x = (Integer[]) m1.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 2) {
                        Integer[] x = (Integer[]) m2.get("data");
                        x[i] = c;
                        break;
                    } else {
                        Integer[] x = (Integer[]) m3.get("data");
                        x[i] = c;
                        break;
                    }
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    /**
     * 折线图数据 根据隐患等级 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "lineChartData2")
    public @ResponseBody
    Result lineChartData2(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        setUserId(user, m);
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectGroupItem3(m);
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }
        String[] xx = new String[]{"较大", "重大", "一般和较小"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        for (DynamicParameter<String, Object> dy : ll) {
            String time = dy.getString("time");
            Integer t = dy.getInteger("d");// 1 一般 0 重大
            Integer c = dy.getInteger("c");
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    if (t.intValue() == 0) {
                        Integer[] x = (Integer[]) m1.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 1) {
                        Integer[] x = (Integer[]) m2.get("data");
                        x[i] = c;
                        break;
                    }
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    /**
     * TODO 柱状图数据 根据隐患整改 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "zhuChartData")
    public @ResponseBody
    Result zhuChartData(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        setUserId(user, m);
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectGroupItemStatus(m);
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }

        String[] xx = new String[]{"隐患条数", "已整改项数"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        for (DynamicParameter<String, Object> dy : ll) {
            String time = dy.getString("time");
            Integer t = dy.getInteger("status");// 2 未整改 3已整改
            Integer c = dy.getInteger("c");
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    if (t.intValue() == 2) {
                        Integer[] x = (Integer[]) m1.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 3) {
                        Integer[] x = (Integer[]) m2.get("data");
                        x[i] = c;
                        break;
                    }
                }
            }
        }
        Integer[] x = (Integer[]) m1.get("data");
        Integer[] x2 = (Integer[]) m2.get("data");
        for (int i = 0; i < x.length; i++) {
            x[i] = x2[i] + x[i];
        }
        mm.add(m1);
        mm.add(m2);

        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    /**
     * 柱状图数据 根据隐患来源 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "zhuChartData2")
    public @ResponseBody
    Result zhuChartData2(String sT, String eT, HttpServletRequest request, Integer status, Integer flag, String control) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);// 当前时间
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }

        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("del", 0);
        m.put("status", 2);
        if (null != control){
            m.put("control", control);
        }else {
            m.put("control", null);
        }
        m.put("uid", user.getId());
        // 根据合格不合格查询出数据.然后进行封装
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectMapzhuChartData2(m);


        // 进行循环使用一个月的天数
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }

        String[] xx = new String[]{"企业自查", "行政检查", "部门抽查"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();

        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);

        // 数据表示的是,选中的时间段内
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        // 这次循环的就是每一天合格的信息
        Integer counts = 0;
        for (DynamicParameter<String, Object> dy : ll) {
            String time = (String) dy.get("useTime"); // 每一天的时间
            Integer a = dy.getBigDecimalToInteger("a");
            Integer b = dy.getBigDecimalToInteger("b");
            Integer c = dy.getBigDecimalToInteger("c");
            Integer count = dy.getLongToInteger("count");
            counts += count;
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    // 基础
                    Integer[] a2 = (Integer[]) m1.get("data");
                    a2[i] = a;
                    // 现场
                    Integer[] b2 = (Integer[]) m2.get("data");
                    b2[i] = b;
                    // 高危
                    Integer[] c2 = (Integer[]) m3.get("data");
                    c2[i] = c;
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        log.error(m1);
        log.error(m2);

        result.setMap("categories", monthL);//时间段内所有的天
        result.setMap("count", counts);//时间段内所有的天
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data

        return result;
    }
    /*@RequestMapping(value = "zhuChartData2")
    public @ResponseBody
    Result zhuChartData2(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);// 当前时间
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        setUserId(user, m);
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectGroupFlag(m);
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }
        String[] xx = new String[]{"企业自查", "行政检查", "部门抽查", "行政检查"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();
        Map<String, Object> m4 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);
        m4.put("name", xx[3]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        m4.put("data", d.clone());
        for (DynamicParameter<String, Object> dy : ll) {
            String time = dy.getString("time");
            Integer t = dy.getInteger("flag");// 1 自查 2行政 3第三番
            Integer c = dy.getInteger("c");
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    if (t.intValue() == 1) {
                        Integer[] x = (Integer[]) m1.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 2) {
                        Integer[] x = (Integer[]) m3.get("data");
                        x[i] = c;
                        break;
                    } else if (t.intValue() == 3) {
                        Integer[] x = (Integer[]) m2.get("data");
                        x[i] = c;
                        break;
                    } else {
                        Integer[] x = (Integer[]) m4.get("data");
                        x[i] = c;
                        break;
                    }
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        result.setMap("categories", monthL);//时间段内所有的月
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }*/

    static final String TIME_STR = "yyyy-MM-dd";
    public Integer count = 0;

    /**
     * * TODO 排查数据分析图表数据(单位: 天)
     * * 一次性把检查合格/不合格/复查合格的数据全部查询出来然后生成图表
     *
     * @param sT      起始
     * @param eT      结束
     * @param request 请求
     * @param flag    检查方式 企业自查 行政检查  第三方检查
     * @param type    基础  现场 高危
     * @return 图表
     * @throws Exception
     */
    @RequestMapping(value = "zhuChartData3")
    public @ResponseBody
    Result zhuChartData3(String sT, String eT, HttpServletRequest request, Integer flag, Integer type) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);// 当前时间
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date date = new Date();
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            if (d.after(date)) { //就表示大于当前的时间
                d = date;
            }
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }

        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("flag", flag);
        m.put("type", type);
        m.put("uid", user.getId());

        // 将 合格 不合格 复查合格的数据一次性的查询出来
        List<Map<String, Object>> maps = chartDataCheck(monthL, m);
        result.setMap("categories", monthL);//时间段内所有的天
        result.setMap("series", maps);// List<Data{String name; Integer[] data}> Data
        result.setMap("count", this.count);
        return result;
    }

    private List<Map<String, Object>> chartDataCheck(List<String> monthL, Map<String, Object> m) {
        // 根据合格不合格查询出数据.然后进行封装
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectFailedByMap(m);

        // 进行循环使用一个月的天数
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }

        String[] xx = new String[]{"合格", "不合格", "已复查合格"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();


        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);

        // 数据表示的是,选中的时间段内
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        this.count = 0;
        // 这次循环的就是每一天合格的信息
        for (DynamicParameter<String, Object> dy : ll) {
            String time = (String) dy.get("time"); // 每一天的时间
            Integer t = (Integer) dy.get("flag");//
            Integer a = dy.getBigDecimalToInteger("a");
            Integer b = dy.getBigDecimalToInteger("b");
            Integer c = dy.getBigDecimalToInteger("c");
            this.count += a + b + c;
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    // 合格
                    Integer[] a2 = (Integer[]) m1.get("data");
                    a2[i] = a;
                    // 不合格
                    Integer[] b2 = (Integer[]) m2.get("data");
                    b2[i] = b;
                    // 已复查合格
                    Integer[] c2 = (Integer[]) m3.get("data");
                    c2[i] = c;
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);

        return mm;
    }

    /**
     * TODO 治理数据分析<已复查合格/复查不合格的参数>(单位: 天)
     * 治理数据分析
     * 复查合格和复查不合格的参数统一进行查询
     *
     * @param sT      起始时间
     * @param eT      结束时间
     * @param request 请求
     * @param status  状态 复查合格/不合格(保留字段)
     * @param flag    1 重大  2 一般  3 自定义
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "zhuChartData4")
    public @ResponseBody
    Result zhuChartData4(String sT, String eT, HttpServletRequest request, Integer status, Integer flag,Integer depart) throws Exception {

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);// 当前时间
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }

        ZzjgDepartment zzjgDepartment = zzjgDepartmentMapper.selectByPrimaryKey(depart);

        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("flag", flag);
        if (null == status) {
            m.put("status", 1);
        } else {
            m.put("status", status);
        }

        if (null != depart && null != zzjgDepartment ){
            m.put("depart", zzjgDepartment.getName());
        }else {
            m.put("depart",null);
        }

        m.put("uid", user.getId());
        // 根据合格不合格查询出数据.然后进行封装
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectRecheckFileByMap(m);

        // 进行循环使用一个月的天数
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }

        String[] xx = new String[]{"已治理", "未治理"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();

        m1.put("name", xx[0]);
        m2.put("name", xx[1]);

        // 数据表示的是,选中的时间段内
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        // 这次循环的就是每一天合格的信息
        for (DynamicParameter<String, Object> dy : ll) {
            String time = (String) dy.get("time"); // 每一天的时间
            Integer a = dy.getBigDecimalToInteger("a");
            Integer b = dy.getBigDecimalToInteger("b");
            log.error(a + "咋回事呀" + b);
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    // 复查合格
                    Integer[] a2 = (Integer[]) m1.get("data");
                    a2[i] = a;
                    // 复查不合格
                    Integer[] b2 = (Integer[]) m2.get("data");
                    b2[i] = b;

                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        log.error(m1);
        log.error(m2);

        result.setMap("categories", monthL);//时间段内所有的天
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data

        return result;
    }


    /**
     * 排查数据分析之隐患类型
     *
     * @param sT      起始时间
     * @param eT      截止时间
     * @param request 请求
     * @param status  不重要
     * @param flag    不重要
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "zhuChartData5")
    public @ResponseBody
    Result zhuChartData5(String sT, String eT, HttpServletRequest request, Integer status, Integer flag, Integer flags,Integer depart) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);// 当前时间
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }

        ZzjgDepartment zzjgDepartment = zzjgDepartmentMapper.selectByPrimaryKey(depart);

        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("del", 0);
        m.put("status", 2);
        m.put("uid", user.getId());
        if (null == flags){
            flags = 1;
        }
        m.put("flag", flags);
        if (null != zzjgDepartment){
            m.put("depart", zzjgDepartment.getName());
        }else {
            m.put("depart", null);
        }

        // 根据合格不合格查询出数据.然后进行封装
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectHiddenDangerTypeByMap(m);


        // 进行循环使用一个月的天数
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }

        String[] xx = new String[]{"基础", "现场", "高危"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();

        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);

        // 数据表示的是,选中的时间段内
        m1.put("data", d.clone());

        m2.put("data", d.clone());
        m3.put("data", d.clone());
        // 这次循环的就是每一天合格的信息
        Integer counts = 0;
        for (DynamicParameter<String, Object> dy : ll) {
            String time = (String) dy.get("useTime"); // 每一天的时间
            Integer a = dy.getBigDecimalToInteger("a");
            Integer b = dy.getBigDecimalToInteger("b");
            Integer c = dy.getBigDecimalToInteger("c");
            Integer count = dy.getLongToInteger("count");
            counts += count;
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    // 基础
                    Integer[] a2 = (Integer[]) m1.get("data");
                    a2[i] = a;
                    // 现场
                    Integer[] b2 = (Integer[]) m2.get("data");
                    b2[i] = b;
                    // 高危
                    Integer[] c2 = (Integer[]) m3.get("data");
                    c2[i] = c;
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        log.error(m1);
        log.error(m2);
        result.setMap("categories", monthL);//时间段内所有的天

        result.setMap("count", counts);//时间段内所有的天
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data

        return result;
    }

    /**
     * 排查数据分析之隐患来源
     *
     * @param sT      起始时间
     * @param eT      截止时间
     * @param request 请求
     * @param status  不重要
     * @param flag    不重要
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "zhuChartData6")
    public @ResponseBody
    Result zhuChartData6(String sT, String eT, HttpServletRequest request, Integer status, Integer flag, String control) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);// 当前时间
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }

        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("del", 0);
        m.put("status", 2);
        if (null != control){
            m.put("control", control);
        }else {
            m.put("control", null);
        }
        m.put("uid", user.getId());
        // 根据合格不合格查询出数据.然后进行封装
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectHiddenSourceTypeByMap(m);


        // 进行循环使用一个月的天数
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }

        String[] xx = new String[]{"企业自查", "行政检查", "部门抽查"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();

        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);

        // 数据表示的是,选中的时间段内
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        // 这次循环的就是每一天合格的信息
        Integer counts = 0;
        for (DynamicParameter<String, Object> dy : ll) {
            String time = (String) dy.get("useTime"); // 每一天的时间
            Integer a = dy.getBigDecimalToInteger("a");
            Integer b = dy.getBigDecimalToInteger("b");
            Integer c = dy.getBigDecimalToInteger("c");
            Integer count = dy.getLongToInteger("count");
            counts += count;
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    // 基础
                    Integer[] a2 = (Integer[]) m1.get("data");
                    a2[i] = a;
                    // 现场
                    Integer[] b2 = (Integer[]) m2.get("data");
                    b2[i] = b;
                    // 高危
                    Integer[] c2 = (Integer[]) m3.get("data");
                    c2[i] = c;
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        log.error(m1);
        log.error(m2);

        result.setMap("categories", monthL);//时间段内所有的天
        result.setMap("count", counts);//时间段内所有的天
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data

        return result;
    }

    /**
     * 排查数据分析之隐患等级
     *
     * @param sT      起始时间
     * @param eT      截止时间
     * @param request 请求
     * @param status  不重要
     * @param flag    不重要
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "zhuChartData7")
    public @ResponseBody
    Result zhuChartData7(String sT, String eT, HttpServletRequest request, Integer status, Integer flag, Integer flags,Integer depart) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);// 当前时间
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, TIME_STR);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, TIME_STR);
            sT = DateFormatUtils.format(DateConvertUtil.addDays(d, -30), TIME_STR);
        }
        ZzjgDepartment zzjgDepartment = zzjgDepartmentMapper.selectByPrimaryKey(depart);
        List<String> monthL = monthB(sT, eT);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("startTime1", sT);
        m.put("endTime1", eT);
        m.put("del", 0);
        m.put("status", 2);
        m.put("uid", user.getId());

        if (null == flags){
            flags = 1;
        }
        m.put("flag", flags);
        if (null != zzjgDepartment){
            m.put("depart", zzjgDepartment.getName());
        }else {
            m.put("depart", null);
        }
        //m.put("uid", 37097);
        // 根据合格不合格查询出数据.然后进行封装
        List<DynamicParameter<String, Object>> ll = tCheckItemMapper.selectHiddenLevelTypeByMap(m);


        // 进行循环使用一个月的天数
        Integer[] d = new Integer[monthL.size()];
        for (int l = 0; l < d.length; l++) {
            d[l] = 0;
        }

        String[] xx = new String[]{"较大", "重大", "一般和较小"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        Map<String, Object> m3 = new HashMap<String, Object>();

        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m3.put("name", xx[2]);

        // 数据表示的是,选中的时间段内
        m1.put("data", d.clone());
        m2.put("data", d.clone());
        m3.put("data", d.clone());
        Integer count = 0;
        // 这次循环的就是每一天合格的信息
        for (DynamicParameter<String, Object> dy : ll) {
            String time = (String) dy.get("useTime"); // 每一天的时间
            Integer a = dy.getBigDecimalToInteger("a");
            Integer b = dy.getBigDecimalToInteger("b");
            Integer c = dy.getBigDecimalToInteger("c");
            count += a + b + c;
            for (int i = 0; i < monthL.size(); i++) {
                if (time.equals(monthL.get(i))) {
                    // 基础
                    Integer[] a2 = (Integer[]) m1.get("data");
                    a2[i] = a;
                    // 现场
                    Integer[] b2 = (Integer[]) m2.get("data");
                    b2[i] = b;
                    // 高危
                    Integer[] c2 = (Integer[]) m3.get("data");
                    c2[i] = c;
                }
            }
        }
        mm.add(m1);
        mm.add(m2);
        mm.add(m3);
        log.error(m1);
        log.error(m2);

        result.setMap("categories", monthL);//时间段内所有的天
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        result.setMap("count", count);
        return result;
    }


    /**
     * 治理数据分析
     * 主要是复查结果是否合格
     *
     * @param sT
     * @param eT
     * @return
     * @throws Exception
     */

    // 进行解析 - 天
    List<String> monthC(String sT, String eT) throws Exception {
        List<String> l = new LinkedList<String>();
        Date s = DateConvertUtil.formateDate(sT, TIME_STR);
        Date e = DateConvertUtil.formateDate(eT, TIME_STR);
        // 起始时间 比当前时间
        while (!s.after(e)) {
            l.add(DateFormatUtils.format(s, TIME_STR));
            s = DateConvertUtil.addDays(s, 1);
        }
        return l;
    }

    // 进行解析 - 月

    List<String> monthB(String sT, String eT) throws Exception {
        List<String> l = new LinkedList<String>();
        Date s = DateConvertUtil.formateDate(sT, PP);
        Date e = DateConvertUtil.formateDate(eT, PP);
        // 起始时间 比当前时间
        while (!s.after(e)) {
            l.add(DateFormatUtils.format(s, PP));
            s = DateConvertUtil.addMonths(s, 1);
        }
        return l;
    }

    /**
     * 安全生产管理 评价评估系统
     */
    @RequestMapping(value = "tables/tab-list")
    public String tabList(Integer isType, HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("isType", isType);
        setUserId(user, m);
        model.addAttribute("isType", isType);
        model.addAttribute("list", table2mapper.selectTable(m));
        return "company/tables/tab-list";
    }


    /**
     * 表导入
     */
    @RequestMapping(value = "tables/tab-leadin", method = RequestMethod.POST)
    public void companyLeadin(@RequestParam MultipartFile file, @RequestParam("isType") Integer isType,
                              @RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = exportService.tableImport(file, getLoginUser(request).getId(), id, isType, request);
        OutPrintUtil.OutPrint(response, result);
    }

    /**
     * 删除表
     */
    @RequestMapping(value = "tables/tab-del")
    public @ResponseBody
    Result deleteTab(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        table2mapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 表2页面
     */
    @RequestMapping(value = "tables/tab-list2")
    public String tab2List(HttpServletRequest request, Model model, Integer isType, Integer isTab) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (null != isType) {
            m.put("isType", isType);
        } else {
            m.put("code", 1);
        }
        model.addAttribute("list", table2mapper.selectTable(m));
        model.addAttribute("isType", isType);
        return "company/tables/tab-list2";
    }

    /**
     * 安全生产教育台账页面
     */
    @RequestMapping(value = "tables/tab-anlist")
    public String anList(HttpServletRequest request, Model model, Integer isType, Integer isTab) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (null != isType) {
            m.put("isType", isType);
        } else {
            m.put("code", 2);
        }
        model.addAttribute("list", table2mapper.selectTable(m));
        model.addAttribute("isType", isType);
        return "company/tables/tab-anlist";
    }

    /**
     * 安全奖惩页面
     */
    @RequestMapping(value = "tables/tab-jianglist")
    public String jiangList(HttpServletRequest request, Model model, Integer isType, Integer isTab) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (null != isType) {
            m.put("isType", isType);
        } else {
            m.put("code", 3);
        }
        model.addAttribute("list", table2mapper.selectTable(m));
        model.addAttribute("isType", isType);
        return "company/tables/tab-jianglist";
    }

    /**
     * 危险作业证页面
     */
    @RequestMapping(value = "tables/tab-weilist")
    public String weiList(HttpServletRequest request, Model model, Integer isType, Integer isTab) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (null != isType) {
            m.put("isType", isType);
        } else {
            m.put("code", 5);
        }
        model.addAttribute("list", table2mapper.selectTable(m));
        model.addAttribute("isType", isType);
        return "company/tables/tab-weilist";
    }

    /**
     * 安全标准化
     */
    @RequestMapping(value = "tables/tab-biaozhun")
    public String biaozhun(HttpServletRequest request, Model model, Integer isType, Integer isTab) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (null != isType) {
            m.put("isType", isType);
        } else {
            m.put("code", 10);
        }
        model.addAttribute("list", table2mapper.selectBiaoZhun(m));
        model.addAttribute("isType", isType);
        return "company/tables/tab-biaozhun";
    }

    /**
     * 8:安全工作台账    9:安全档案    10：安全标准化
     * 安全工作台账
     */
    @RequestMapping(value = "tables/tab-taizhang")
    public String taizhang(HttpServletRequest request, Model model, Integer isType, Integer isTab) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (null != isType) {
            m.put("isType", isType);
        } else {
            m.put("code", 8);
        }

        List<DynamicParameter<String, Object>> list = table2mapper.selectTaiZhang(m);

        model.addAttribute("list", list);
        model.addAttribute("isType", isType);
        return "company/tables/tab-taizhang";
    }

    /**
     * 安全档案
     */
    @RequestMapping(value = "tables/tab-dangan")
    public String dangan(HttpServletRequest request, Model model, Integer isType, Integer isTab) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        if (null != isType) {
            m.put("isType", isType);
        } else {
            m.put("code", 9);
        }
        model.addAttribute("list", table2mapper.selectDangAn(m));
        model.addAttribute("isType", isType);
        return "company/tables/tab-dangan";
    }

    /**
     * 模板下载
     */
    @RequestMapping(value = "tables/templetDownload")
    public void temDownload(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 获得请求文件名
        Integer isType = Integer.parseInt(request.getParameter("isType"));
        Table info = tablemapper.selectByIsType(isType);
        File realPath = new File(request.getServletContext().getRealPath("/"), info.getUrl());
        String filename = URLEncoder.encode(info.getName(), "UTF-8");
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
     * 安全生产管理网络图
     */
    @RequestMapping(value = "information/information6")
    public String information6(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("user", companyMapper.selectByPrimaryKey(user.getId()));
        return "company/information/information6";
    }

    /**
     * 安全（生产）许可证
     */
    @RequestMapping(value = "information/information7")
    public String information7(HttpServletRequest request, Model model) throws Exception {
        User user = getLoginUser(request);
        model.addAttribute("user", companyMapper.selectByPrimaryKey(user.getId()));
        return "company/information/information7";
    }

    /**
     * 删除自评
     */
    @RequestMapping(value = "information/h8-del")
    public @ResponseBody
    Result deleteh8(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        pingMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 安全生产标准化编辑保存
     */
    @RequestMapping(value = "information/save-info8")
    public @ResponseBody
    Result infoSave(HttpServletRequest request, Model model, Standard standard) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        standard.setUserId(user.getId());
        if (null == standard.getId()) {
            standardMapper.insertSelective(standard);
        } else {
            standardMapper.updateByPrimaryKeySelective(standard);
        }
        return result;
    }

    /**
     * 自评导入
     */
    @RequestMapping(value = "ping", method = RequestMethod.POST)
    public void pLeadin(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Result result = exportService.pImport(file, getLoginUser(request).getId(), request);
        OutPrintUtil.OutPrint(response, result);
    }

    /**
     * pic保存
     */
    @RequestMapping(value = "information/save-pic")
    public @ResponseBody
    Result picSave(HttpServletRequest request, Model model, Integer code, String pic) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company c = companyMapper.selectByPrimaryKey(user.getId());
        switch (code) {
            case 1:
                c.setPic1(pic);
                break;
            case 2:
                c.setPic2(pic);
                break;
            default:
                break;
        }
        companyMapper.updateByPrimaryKeySelective(c);
        return result;
    }

    /**
     * c 1 : 建设项目职业卫生“三同时” 2:职业卫生管理台账 3：职业卫生宣传培训 4 ：职业病危害因素监测与检测评价 5：用人单位职业健康监护管理 6 ：劳动者个人职业健康监护 7:职业卫生管理台账-其他
     * <p>
     * 8:安全工作台账    9:安全档案    10：安全标准化
     */
    @RequestMapping(value = "tables/tab-health/{c}")
    public String heath1(HttpServletRequest request, @PathVariable Integer c, Model model, String seName, String isType)
            throws Exception {
        User user = getLoginUser(request);
        String[] xx = new String[]{"1,2,3,4,5,6,7,8,9", "10,11,12,13,14,15,16,17,18,19,21,22", "23,24,25,26",
                "27,28,29,30,31,32,33,34,35", "36,37,38,39,40,41,42", "43,44,45,46,47", "48"};
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("seName", seName);
        m.put("isType", StringUtils.isEmpty(isType) ? xx[c - 1] : isType);
        model.addAttribute("list", table3mapper.selectTable(m));
        model.addAttribute("isType", isType);
        model.addAttribute("seName", seName);

        return "company/tables/tab-health" + c;
    }

    /**
     * 删除h1表
     */
    @RequestMapping(value = "tables/h1-del")
    public @ResponseBody
    Result deleteh1(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        table3mapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 表导入
     */
    @RequestMapping(value = "tables/h-leadin", method = RequestMethod.POST)
    public void hLeadin(@RequestParam MultipartFile file, @RequestParam("isType") Integer isType,
                        @RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = exportService.hImport(file, getLoginUser(request).getId(), id, isType, request);
        OutPrintUtil.OutPrint(response, result);
    }

    /**
     * health表模板下载
     */
    @RequestMapping(value = "tables/hDownload")
    public void hDownload(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 获得请求文件名
        Integer isType = Integer.parseInt(request.getParameter("isType"));
        Table4 info = table4mapper.selectByIsType(isType);
        File realPath = new File(request.getServletContext().getRealPath("/"), info.getUrl());
        String filename = URLEncoder.encode(info.getName(), "UTF-8");
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
     * 隐患库页面
     */
    @RequestMapping(value = "trouble/trouble-information")
    public String trInfo(HttpServletRequest request, Model model) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("code", 1);
        model.addAttribute("list", table5Mapper.selectTable(m));
        m.clear();
        m.put("code", 2);
        model.addAttribute("list2", table5Mapper.selectTable(m));
        m.clear();
        m.put("code", 3);
        model.addAttribute("list3", table5Mapper.selectTable(m));
        m.clear();
        return "company/trouble/trouble-information";
    }

    /**
     * 企业自查 删除
     */
    @RequestMapping(value = "check-del")
    public @ResponseBody
    Result checkDel(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        TCheck t = new TCheck();
        t.setId(id);
        t.setDel(1);
        tCheckMapper.updateByPrimaryKeySelective(t);
        return result;
    }

    /**
     * 企业自查 保存检查项
     */
    @RequestMapping(value = "t-check-save")
    public @ResponseBody
    Result tCheckSave(TCheck tc, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        tc.setUserId(user.getId());
        tc.setCreateTime(new Date());
        tc.setStatus(1);
        tc.setDel(0);
        try {
            troubleService.tCheckSave(tc, result);
        } catch (Exception e) {
            result.setStatus("1");
            result.setMap("message", "操作失败");
        }
        return result;
    }

    /**
     * 企业自查 保存检查项 获取
     */
    @RequestMapping(value = "check-item-get")
    public @ResponseBody
    Result checkItemGet(Integer id, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        result.setMap("item", tCheckItemMapper.selectByPrimaryKey(id));
        return result;
    }

    /**
     * 企业自查 保存检查项
     */
    @RequestMapping(value = "check-item-save")
    public @ResponseBody
    Result checkItemSave(Integer id, HttpServletRequest request,Integer status,String files) throws Exception {
        Result result = new ResultImpl();

        TCheckItem tCheckItem = new TCheckItem();

        String file = null;
        if (null == files){
            file = null;
        }else {
            file = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + files;
        }

        tCheckItem.setRecheckFile(file);
        tCheckItem.setId(id);
        tCheckItem.setStatus(status);
        tCheckItemMapper.updateByPrimaryKeySelective(tCheckItem);
        return result;
    }

    /**
     * 企业自查 保存部门照片
     */
    @RequestMapping(value = "check-part-save")
    public @ResponseBody
    Result checkPartSave(TCheckPart tci, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        tCheckPartMapper.updateByPrimaryKeySelective(tci);
        return result;
    }

    /**
     * 企业自查 保存检查
     */
    @RequestMapping(value = "check-save2")
    public @ResponseBody
    Result checkSave2(TCheck t) {
        Result result = new ResultImpl();

        log.error(t);

        if(null != t){
            try {
                cgfService.checkNestSave(t);
            } catch (Exception e) {
                e.printStackTrace();
                result.setStatus("1");
                result.setMap("message", "保存失败");
            }
        }
        return result;
    }

    /**
     * 企业自查 填写不合格项
     */
    @RequestMapping(value = "plan-next-print")
    public String checkNextPrint(Integer id, Model model) throws Exception {
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(id);
        model.addAttribute("check", tc);
        model.addAttribute("partL", partL);
        model.addAttribute("itemL", tCheckItemMapper.selectByCheckId(id));
        model.addAttribute("now", new Date());
        model.addAttribute("company", companyMapper.selectByPrimaryKey(tc.getUserId()));
        if (tc.getStatus() == 2) {
            return "company/danger/print-plan-detail";
        }
        return "company/danger/print-plan-next";
    }


//    @RequestMapping(value = "model-show/{id}")
//    public String modelShow(@PathVariable Integer id, Model model,Integer flag) throws Exception {
//        TModel tc = tModelMapper.selectByPrimaryKey(id);
//        List<TModelPart> partL = tModelPartMapper.selectByModelId(id);
//        model.addAttribute("model", tc);
//        model.addAttribute("partL", partL);
//        StringBuffer levelIds = new StringBuffer();
//        for (TModelPart p : partL) {
//            if (!"".equals(levelIds.toString())) {
//                levelIds = levelIds.append(",");
//            }
//            levelIds = levelIds.append(p.getLevels());
//        }
//        model.addAttribute("itemL", tItemMapper.selectByLevelIdsModel(levelIds.toString()));
//        model.addAttribute("now", new Date());
//        model.addAttribute("flag", flag);
//        return "company/danger/model-show";
//    }

    /* *//**
     * TODO 查询检查表详情
     * @param id    modelId
     * @param model 前端model
     * @param flag  类型
     * @param type   数据
     * @return url地址
     * 可以从ite表中获取的详情
     * @throws Exception
     *//*
    @RequestMapping(value = "model-show/{id}")//modify by zhangcl 2018.10.28
    public String modelShow(@PathVariable Integer id, Model model, Integer flag, Integer type) throws Exception {
        log.error("type:" + type);
        TModel tc = tModelMapper.selectByPrimaryKey(id);
        List<TModelPart> partL = tModelPartMapper.selectByModelId(id);
        model.addAttribute("model", tc);
        model.addAttribute("partL", partL);
        StringBuffer levelIds = new StringBuffer();
        for (TModelPart p : partL) {
            if (!"".equals(levelIds.toString())) {
                levelIds = levelIds.append(",");
            }
            levelIds = levelIds.append(p.getLevels());
        }
        if (type != null && type == 9) {
            List<Map<String, Object>> iteml = new ArrayList<Map<String, Object>>();
            String[] levelsArr = levelIds.toString().split(",");
            for (int i = 0; i < levelsArr.length; i++) {
                Map<String, Object> a = new HashMap<String, Object>();
                Integer[] ids = new Integer[1];
                ids[0] = Integer.parseInt(levelsArr[i]);
                log.error("ids:" + ids[0]);

                List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
                String dangertype = "";
                String factors = "";
                String measures = "";
                for (ACompanyManual aa : rets) {
                    dangertype = aa.getType();
                    factors = aa.getFactors();
                    measures = aa.getMeasures();
                    log.error("type:" + dangertype);
                    break;
                }
                a.put("levelId", Integer.parseInt(levelsArr[i]));
                a.put("dangerType", dangertype);
                a.put("factors", factors);
                a.put("measures", measures);
                log.error("a:" + a.toString());
                iteml.add(a);
            }
            model.addAttribute("itemL", iteml);
        } else {
            model.addAttribute("itemL", tItemMapper.selectByLevelIdsModel(levelIds.toString()));
        }

        model.addAttribute("now", new Date());
        model.addAttribute("flag", flag);

        if (type != null && type == 9) {
            log.error("company/danger/model-show1");
            return "company/danger/model-show1";
        } else {
            return "company/danger/model-show";
        }
    }*/


    /**
     * TODO 查询检查表详情(已修改)(有两种情况)一种是基础检查  一种是现场检查, 是从不同的表中查询出字段的
     * 只有行政检查和部门抽查才会走这个方法, 就是只有两种情况
     * 判断是基础还是现场,
     *
     * @param id    modelId
     * @param model 前端model
     * @param flag  类型
     * @param type  数据
     * @return url地址
     * @throws Exception
     */
    @RequestMapping(value = "model-show/{id}")
    public String modelShow(@PathVariable Integer id, Model model, Integer flag, Integer type, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        TModel tc = tModelMapper.selectByPrimaryKey(id);
        List<TModelPart> partL = tModelPartMapper.selectByModelId(id);
        model.addAttribute("model", tc);
        model.addAttribute("partL", partL);
        model.addAttribute("user", user);
        StringBuffer levelIds = new StringBuffer();
        Set<String> set = new LinkedHashSet<String>();
        for (TModelPart tModelPart : partL) {
            set.add(tModelPart.getLevels());
        }
        for (String s : set) {
            if (!"".equals(levelIds.toString())) {
                levelIds = levelIds.append(",");
            }
            levelIds = levelIds.append(s);
        }

        //判断是基础还是现场
        if (type != null && tc.getIndustryType() == 1) {          // 基础检查
            //保存的就是这个
            List<Map<String, Object>> iteml = new ArrayList<Map<String, Object>>();
            String[] levelsArr = levelIds.toString().split(",");

            for (int i = 0; i < levelsArr.length; i++) {
                Map<String, Object> a = new HashMap<String, Object>();
                if (!"null".equals(levelsArr[i]) && null != levelsArr[i] && !"".equals(levelsArr[i])) {
                    int i1 = Integer.parseInt(levelsArr[i]);
                    TLevel tLevel = tLevelMapper.selectByPrimaryKey(i1);
                    if (null == tLevel) {
                        break;
                    }
                    a.put("levels", tLevel.getLevel1() + tLevel.getLevel2() + tLevel.getLevel3());
                    a.put("levelId", Integer.parseInt(levelsArr[i]));
                    a.put("dangerType", tLevel.getType());
                    a.put("factors", tLevel.getFactors());
                    a.put("measures", tLevel.getMeasures());
                    iteml.add(a);
                }
            }
            // 为空 表示从别的表中进行查询
            if (iteml.size() == 0) {
                TCheck tCheck = tCheckMapper.selectByModelId(id);
                List<Map<String, Object>> maps = tCheckItemMapper.selectByCheckId(tCheck.getId());
                for (Map<String, Object> map : maps) {
                    Map<String, Object> a = new HashMap<String, Object>();
                    a.put("levelId", map.get("level_id"));
                    a.put("dangerType", tCheck.getType());
                    a.put("factors", map.get("content"));
                    a.put("measures", map.get("reference"));
                    a.put("levels", map.get("levels"));
                    if (null == a.get("measures") || "".equals(a.get("measures"))) {
                        a.put("measures", map.get("content"));

                    }
                    log.info("a:" + a.toString());
                    iteml.add(a);
                }
            }
            model.addAttribute("itemL", iteml);
        } else if (type != null && tc.getIndustryType() == 2) { //现场检查
            List<Map<String, Object>> iteml = new ArrayList<Map<String, Object>>();
            String[] levelsArr = levelIds.toString().split(",");

            for (int i = 0; i < levelsArr.length; i++) {
                Map<String, Object> a = new HashMap<String, Object>();
                if (!"null".equals(levelsArr[i]) && null != levelsArr[i] && !"".equals(levelsArr[i])) {
                    int i1 = Integer.parseInt(levelsArr[i]);
                    ACompanyManual companyManual = aCompanyManualMapper.selectByPrimaryKey(i1);
                    if (null == companyManual) {
                        break;
                    }
                    a.put("levels", companyManual.getLevel1() + companyManual.getLevel2() + companyManual.getLevel3());
                    a.put("levelId", Integer.parseInt(levelsArr[i]));
                    a.put("dangerType", companyManual.getType());
                    a.put("factors", companyManual.getFactors());
                    a.put("measures", companyManual.getMeasures());
                    iteml.add(a);
                }
            }
            // 为空 表示从别的表中进行查询
            if (iteml.size() == 0) {
                TCheck tCheck = tCheckMapper.selectByModelId(id);
                List<Map<String, Object>> maps = tCheckItemMapper.selectByCheckId(tCheck.getId());
                for (Map<String, Object> map : maps) {
                    Map<String, Object> a = new HashMap<String, Object>();
                    a.put("levels", map.get("levels"));
                    a.put("levelId", map.get("level_id"));
                    a.put("dangerType", tCheck.getType());
                    a.put("factors", map.get("content"));
                    a.put("measures", map.get("reference"));
                    if (null == a.get("measures") || "".equals(a.get("measures"))) {
                        a.put("measures", map.get("content"));

                    }
                    log.info("a:" + a.toString());
                    iteml.add(a);
                }

            }

            model.addAttribute("itemL", iteml);
        } else {
            // 高危检查
            model.addAttribute("itemL", tItemMapper.selectByLevelIdsModel(levelIds.toString()));
        }
        model.addAttribute("now", new Date());
        model.addAttribute("flag", flag);

        if (type != null && tc.getIndustryType() <= 2) {
            return "company/danger/model-show1";
        } else {
            return "company/danger/model-show";
        }
    }

    /**
     * 企业自查模板
     */
    @RequestMapping(value = "model-list")
    public String modelList(Integer type, Integer flag, String title, Integer industryType, HttpServletRequest request,
                            Model model) throws Exception {
        User user = getLoginUser(request);
        //log.error("type: "+type);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("type", type);
        m.put("title", title);
        m.put("flag", flag);
        m.put("industryType", industryType);
        setUserId(user, m);
        model.addAttribute("list", tModelMapper.selectByMap(m));
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("industryType", industryType);
        return "company/danger/model-list";
    }


    /**
     * TODO 检查设置实施中实施整改按钮中,返回查询记录,可以进行实施复查(2019/7/2 10添加)
     * 现在只显示,只有不合格的,全部合格,和全部是复查合格就不要进行显示了
     *
     * @param dmName       部门名称
     * @param flag         1. 企业自查  2 部门抽查  3 行政检查
     * @param industryType 1 基础  2 现场
     * @param template     前端传递字符
     * @return
     */
    @RequestMapping("check-list-szss")
    public String checkListSzss(String dmName, Integer flag, Integer industryType, Integer template, HttpServletRequest request, Model model, Integer status) {

        User user = getLoginUser(request);

        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("dmName", dmName);
        model.addAttribute("flag", flag);
        model.addAttribute("industryType", industryType);
        model.addAttribute("template", template);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);
        if (null != industryType) {
            industryType = Math.abs(industryType);
            m.put("industryType", industryType);
        }

        // 表示是企业自查,根据名称进行查询
        if (flag == 1) {
            m.put("dmName", dmName);
        }
        status = status == null ? 2 : status;
        m.put("status", status);
        if (user.getId() != 35346) {
            if (setUserId(user, m)) {
                clearVillageTown(m);
                List<Map<String, Object>> list = tCheckMapper.selectListTwo(m);
                if (flag == 1) {
                    List list1 = new ArrayList();
                    for (Map<String, Object> stringObjectMap : list) {
                        int industry = (Integer) stringObjectMap.get("industryType");
                        if (industryType == industry) {
                            list1.add(stringObjectMap);
                        }
                    }
                    model.addAttribute("list", list1);

                } else {
                    model.addAttribute("list", list);
                }

                Integer sum = 0;
                for (int i = 0; i < list.size(); i++) {
                    sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));
                }
                model.addAttribute("sum", sum);
            }
        }else{
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectListTwo(m);
            if (flag == 1) {
                List list1 = new ArrayList();
                for (Map<String, Object> stringObjectMap : list) {
                    int industry = (Integer) stringObjectMap.get("industryType");
                    if (industryType == industry) {
                        list1.add(stringObjectMap);
                    }
                }
                model.addAttribute("list", list1);

            } else {
                model.addAttribute("list", list);
            }

            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));
            }
            model.addAttribute("sum", sum);
        }


        return "company/danger/check-list-szss";
    }

    /**
     * TODO 检查设置与实施中行政检查/部门抽查 显示检查记录(2019/7/5)
     *
     * @return
     */
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
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            model.addAttribute("list", list);
            Integer sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += Integer.parseInt(String.valueOf(list.get(i).get("c")));
            }
            model.addAttribute("sum", sum);
        }
        return "company/danger/model-list-cx";
    }

    /**
     * TODO 检查设置与实施
     * 行政检查/部门抽查
     * 综合检查表(Template 1)
     * 日检查表(Template 2)
     * 临时检查表(template 4)
     * type  =1(日常)  flag=1()自查
     */
    @RequestMapping(value = "model-list-cx")
    public String modelList1(Integer type, Integer flag, String title, Integer industryType, HttpServletRequest request,
                             Model model, Integer template, Integer status) throws Exception {
        User user = getLoginUser(request);

        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("industryType", industryType);
        model.addAttribute("template", template);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("title", title);
        m.put("flag", flag);
        m.put("industryType", industryType);
        setUserId(user, m);
        List<Map<String, Object>> list = null;
        if (null != template && template == 1) { //综合检查表
            m.remove("type");
            list = tModelMapper.selectByMap(m);
        } else if (null != template && template == 2) { //日检查表
            list = tModelMapper.selectByMap(m);
        } else {
            list = tModelMapper.selectByMap(m);
        }

        //企业类型为 化工 或 构成重大危险源 则企业自查处 日检查表显示 wz 190108
        Company company = companyMapper.selectByPrimaryKey(user.getId());

        /*
         * 监管行业不能为空
         */
        if (null != company.getHazard()) {
            if (company.getHazard() == 1 || company.getIndustry().trim().equals("化工企业（危化生产、使用）")) {//显示效果需要
                model.addAttribute("rjcbxs", 1);

            }
        }
        model.addAttribute("list", list);

        // -------------------------------检查记录,(已废弃不需要)-----------------------------------
       /* Map<String, Object> m1 = new HashMap<String, Object>();
        if (user.getUserType() == 3) {//镇
            model.addAttribute("villageL", villageMapper.selectListByTown(m));
        }
        if (user.getUserType() == 6) {//区
            model.addAttribute("townL", townMapper.selectListByDistrict(m));
        }
        // 向map集合进行存储
        m1.put("flag", flag);
        m1.put("title", title);
        if(null==status){
            m1.put("status", 2);
        }else{
            m1.put("status", status);
        }
        // 进行判断
        if (setUserId(user, m1)) {
            clearVillageTown(m1);
            List<Map<String, Object>> list1 = tCheckMapper.selectList(m1);
            model.addAttribute("checkList", list1);

            Integer sum = 0;
            for (int i = 0; i < list1.size(); i++) {
                sum += Integer.parseInt(String.valueOf(list1.get(i).get("c")));
            }
            model.addAttribute("sum", sum);
        }*/

        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());

        return "company/danger/model-list-cx";
    }


    /**
     * 检查设置与实施-企业自查1-日检查表 wz 190109
     * never
     * until restart
     * for session
     * forever
     */
    @RequestMapping(value = "model-list-cx1r")
    public String modelList2(Integer type, Integer flag, String title, Integer industryType, HttpServletRequest request,
                             Model model) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        //log.error("type数据类型："+type.getClass());
        m.put("type", type);
        m.put("title", title);
        m.put("flag", flag);
        m.put("industryType", industryType);
        m.put("cycle", 1);
        setUserId(user, m);
        //log.error("m："+m.toString());
        //log.error("日检查表type=9："+tModelMapper.selectByMap(m).get(0));
        List<Map<String, Object>> list = tModelMapper.selectByMap1(m);

        //查找定期检查表中检查周期为1的表
        m.put("type", 2);
        List<Map<String, Object>> list1 = tModelMapper.selectByMap1(m);
        list.addAll(list1);

        model.addAttribute("list", list);
        //model.addAttribute("list", tModelMapper.selectByMap(m));
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("industryType", industryType);
        return "company/danger/model-list-cx1r";
    }
//    /**
//     * 检查设置与实施-整改实施
//     */
//    @RequestMapping(value = "check-list")
//    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
//            Integer status, Integer flag, Model model) throws Exception {
//        User user = getLoginUser(request);
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("flag", flag);
//        m.put("title", title);
//        m.put("companyName", companyName);
//        m.put("status", status);
//        if(setUserId(user, m)) {
//            clearVillageTown(m);
//            List<Map<String, Object>> list = tCheckMapper.selectList(m);
//            model.addAttribute("list", list);
//        }
//        model.addAttribute("type", type);
//        model.addAttribute("flag", flag);
//        model.addAttribute("companyName", companyName);
//        model.addAttribute("title", title);
//        model.addAttribute("status", status);
//        Date d = new Date();
//        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
//        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
//        model.addAttribute("t", d.getTime());
//        if (user.getUserType() == 5) {//企业用户
//            return "company/danger/model-list-cx";
//        }
//        return "village/danger/check-list";
//    }

    /**
     * TODO 临时检查表(已废弃)
     * 在model表查询获取数据
     */
    @RequestMapping(value = "model-list-bm")
    public String modelLinShi(HttpServletRequest request, Integer flag, Integer type, Integer template,
                              Integer status, String title, String companyName,
                              Model model
    ) throws ParseException {
        // 获取用户信息
        User user = getLoginUser(request);
        //企业类型为 化工 或 构成重大危险源 则企业自查处 日检查表显示 wz 190108
        Company company = companyMapper.selectByPrimaryKey(user.getId());
       
       /* if (company.getHazard() == 1 || company.getIndustry().trim().equals("化工企业（危化生产、使用）")) {
            //log.error("监管行业："+company.getIndustry());
            //log.error("是否构成重大危险源、1是："+company.getHazard());
            model.addAttribute("rjcbxs", 1);
        }*/

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);
        m.put("title", title);
        m.put("companyName", companyName);
        m.put("status", status);
        //m.put("userId",user.getId());
        if (setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            model.addAttribute("list", list);
        }
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        Date d = new Date();

        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());

        List<Map<String, Object>> jiChuItem = aCompanyManualMapper.findJiChuItem(user.getId(), "基础管理");

        List<Map<String, Object>> XianChangItem = aCompanyManualMapper.findJiChuItem(user.getId(), "现场管理");

        model.addAttribute("jiChuItem", jiChuItem);
        model.addAttribute("xianChangItem", XianChangItem);


        if (user.getUserType() == 5) {
            //企业用户
            return "company/danger/model-list-cx";
        }
        return "village/danger/check-list";

    }

    /**
     * TODO 跳转中转页面==> 跳转到自定义/标准保存模版
     *
     * @return
     */
    @RequestMapping(value = "model-add-main")
    public String modelAddMain(HttpServletRequest request, Model model, String dmname, Integer dmid, Integer checkType,
                               Integer industryType) {
        model.addAttribute("dmname", dmname);
        model.addAttribute("dmid", dmid);
        model.addAttribute("checkType", checkType);
        model.addAttribute("industryType", industryType);

        return "company/checkModel/model-add-main";
    }


    /**
     * @author ：小明！！！
     * @description：隐患排查计划 数据添加
     */
    @RequestMapping(value = "model-list-save")
    @ResponseBody
    public Result modelListMain(HttpServletRequest request, Model model, Integer danger1, Integer danger2, String danger3, Integer danger4, Integer danger5,
                                String danger6, Integer danger7, Integer danger8, String danger9, Integer danger10, Integer danger11, String danger12, Integer danger13,
                                Integer danger14, String danger15, Integer danger16, Integer danger17, String danger18, Integer danger19, String danger20, Integer id, Integer dpid) {

        User user = getLoginUser(request);
        Result result = new ResultImpl();
        Integer a = null;
        Integer b = null;
        danger2 = 0;
        danger5 = 0;
        danger8 = 0;
        if (null == danger11){
            danger11 = 0;
        }
        if (null == danger14){
            danger14 = 0;
        }
        if (null == danger17){
            danger17 = 0;
        }
        if (null == danger10){
            danger10 = 0;
        }
        if (null == danger13){
            danger13 = 0;
        }
        if (null == danger16){
            danger16 = 0;
        }
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        Integer year = 12 - month + 1;
        // 根据 ID 查询数据信息
        List<HiddenPlan> hiddenPlan2 = hiddenPlanMapper.selectDpid(dpid,user.getId());
        HiddenPlan hiddenPlan1 = new HiddenPlan();
        sumDatase1(danger1, danger2, danger3, danger4, danger5, danger6, danger7, danger8, danger9, danger10, danger11, danger12, danger13, danger14, danger15, danger16, danger17, danger18, danger20, dpid, user, result, a, b, year, hiddenPlan2, hiddenPlan1);
        return result;
    }

    private void sumDatase1(Integer danger1, Integer danger2, String danger3, Integer danger4, Integer danger5, String danger6, Integer danger7, Integer danger8, String danger9, Integer danger10, Integer danger11, String danger12, Integer danger13, Integer danger14, String danger15, Integer danger16, Integer danger17, String danger18, String danger20, Integer dpid, User user, Result result, Integer a, Integer b, Integer year, List<HiddenPlan> hiddenPlan2, HiddenPlan hiddenPlan1) {
        Integer danger19;
        if (null == hiddenPlan2 || hiddenPlan2.size() == 0) {
            hiddenPlan1.setUid(user.getId());
            hiddenPlan1.setDpid(dpid);
            if (null != danger1 && danger1 != 0) {
                hiddenPlan1.setSyn_month(danger1);
                danger2 = danger1 * year;
                hiddenPlan1.setSyn_year(danger2);
            } else {
                hiddenPlan1.setSyn_month(0);
                hiddenPlan1.setSyn_year(0);
            }
            /*if (null != danger2 && danger2 != 0) {
                hiddenPlan1.setSyn_year(danger2);
            } else {
                hiddenPlan1.setSyn_year(0);
            }*/
            if (null != danger3) {
                hiddenPlan1.setSyn_ratio(danger3);
            } else {
                hiddenPlan1.setSyn_ratio("0:0");
            }
            if (null != danger4 && danger4 != 0) {
                hiddenPlan1.setEve_month(danger4);
                danger5 = danger4 * year;
                hiddenPlan1.setEve_year(danger5);
            } else {
                hiddenPlan1.setEve_month(0);
                hiddenPlan1.setEve_year(0);
            }
           /* if (null != danger5 && danger5 != 0) {
                hiddenPlan1.setEve_year(danger5);
            } else {
                hiddenPlan1.setEve_year(0);
            }*/
            if (null != danger6) {
                hiddenPlan1.setEve_ratio(danger6);
            } else {
                hiddenPlan1.setEve_ratio("0:0");
            }
            if (null != danger7 && danger7 != 0) {
                hiddenPlan1.setReg_month(danger7);
                danger8 = danger7 * year;
                hiddenPlan1.setReg_year(danger8);
            } else {
                hiddenPlan1.setReg_month(0);
                hiddenPlan1.setReg_year(0);
            }
            /*if (null != danger8 && danger8 != 0) {
                hiddenPlan1.setReg_year(danger8);
            } else {
                hiddenPlan1.setReg_year(0);
            }*/
            if (null != danger9) {
                hiddenPlan1.setReg_ratio(danger9);
            } else {
                hiddenPlan1.setReg_ratio("0:0");
            }
            if (null != danger10 && danger10 != 0) {
                hiddenPlan1.setSea_month(danger10);
                danger11 = danger10 * year;
                hiddenPlan1.setSea_year(danger11);
            } else {
                hiddenPlan1.setSea_month(0);
                hiddenPlan1.setSea_year(0);
            }
            /*if (null != danger11 && danger11 != 0) {
                hiddenPlan1.setSea_year(danger11);
            } else {
                hiddenPlan1.setSea_year(0);
            }*/
            if (null != danger12) {
                hiddenPlan1.setSea_ratio(danger12);
            } else {
                hiddenPlan1.setSea_ratio("0:0");
            }
            if (null != danger13 && danger13 != 0) {
                hiddenPlan1.setEls_month(danger13);
                danger14 = danger13 * year;
                hiddenPlan1.setEls_year(danger14);
            } else {
                hiddenPlan1.setEls_month(0);
                hiddenPlan1.setEls_year(0);
            }
            /*if (null != danger14 && danger14 != 0) {
                hiddenPlan1.setEls_year(danger14);
            } else {
                hiddenPlan1.setEls_year(0);
            }*/
            if (null != danger15) {
                hiddenPlan1.setEls_ratio(danger15);
            } else {
                hiddenPlan1.setEls_ratio("0:0");
            }
            if (null != danger16 && danger16 != 0) {
                hiddenPlan1.setBas_month(danger16);
                danger17 = danger16 * year;
                hiddenPlan1.setBas_year(danger17);
            } else {
                hiddenPlan1.setBas_month(0);
                hiddenPlan1.setBas_year(0);
            }
            /*if (null != danger17 && danger17 != 0) {
                hiddenPlan1.setBas_year(danger17);
            } else {
                hiddenPlan1.setBas_year(0);
            }*/
            if (null != danger18) {
                hiddenPlan1.setBas_ratio(danger18);
            } else {
                hiddenPlan1.setBas_ratio("0:0");
            }
            danger19 = danger2 + danger5 + danger8 + danger11 + danger14 + danger17;
            if (null != danger19 && danger19 != 0) {
                hiddenPlan1.setTotal_count(danger19);
            } else {
                hiddenPlan1.setTotal_count(0);
            }
            if (null != danger20) {
                hiddenPlan1.setTotal_ratio(danger20);
            } else {
                hiddenPlan1.setTotal_ratio("0:0");
            }
            hiddenPlan1.setCreate_time(new Date());
            hiddenPlan1.setUpdate_time(new Date());
            a = hiddenPlanMapper.insert(hiddenPlan1);
        } else {
            hiddenPlan1.setUid(user.getId());
            hiddenPlan1.setDpid(dpid);
            Integer count = 0;
            Integer number1 = 0;
            if (null != danger1 && danger1 != 0) {
                hiddenPlan1.setSyn_month(danger1);
                count = danger2 = danger1 * year;
                hiddenPlan1.setSyn_year(danger2);
            } else {
                hiddenPlan1.setSyn_month(hiddenPlan2.get(0).getSyn_month());
                hiddenPlan1.setSyn_year(hiddenPlan2.get(0).getSyn_year());
            }
            /*if (null != danger2 && danger2 != 0) {
                hiddenPlan1.setSyn_year(danger2);
            } else {
                hiddenPlan1.setSyn_year(hiddenPlan2.get(0).getSyn_year());
            }*/
            if (null != danger3) {
                hiddenPlan1.setSyn_ratio(danger3);
            } else {
                hiddenPlan1.setSyn_ratio(hiddenPlan2.get(0).getSyn_ratio());
            }
            if (null != danger4 && danger4 != 0) {
                hiddenPlan1.setEve_month(danger4);
                count = danger5 = danger4 * year;
                hiddenPlan1.setEve_year(danger5);
            } else {
                hiddenPlan1.setEve_month(hiddenPlan2.get(0).getEve_month());
                hiddenPlan1.setEve_year(hiddenPlan2.get(0).getEve_year());
            }
            /*if (null != danger5 && danger5 != 0) {
                hiddenPlan1.setEve_year(danger5);
            } else {
                hiddenPlan1.setEve_year(hiddenPlan2.get(0).getEve_year());
            }*/
            if (null != danger6) {
                hiddenPlan1.setEve_ratio(danger6);
            } else {
                hiddenPlan1.setEve_ratio(hiddenPlan2.get(0).getEve_ratio());
            }
            if (null != danger7 && danger7 != 0) {
                hiddenPlan1.setReg_month(danger7);
                count = danger8 = danger7 * year;
                hiddenPlan1.setReg_year(danger8);
            } else {
                hiddenPlan1.setReg_month(hiddenPlan2.get(0).getReg_month());
                hiddenPlan1.setReg_year(hiddenPlan2.get(0).getReg_year());
            }
            /*if (null != danger8 && danger8 != 0) {
                hiddenPlan1.setReg_year(danger8);
            } else {
                hiddenPlan1.setReg_year(hiddenPlan2.get(0).getReg_year());
            }*/
            if (null != danger9) {
                hiddenPlan1.setReg_ratio(danger9);
            } else {
                hiddenPlan1.setReg_ratio(hiddenPlan2.get(0).getReg_ratio());
            }
            if (null != danger10 && danger10 != 0) {
                hiddenPlan1.setSea_month(danger10);
            } else {
                hiddenPlan1.setSea_month(hiddenPlan2.get(0).getSea_month());
            }
            if (null != danger11 && danger11 != 0) {
                hiddenPlan1.setSea_year(danger11);
                count = danger11;
            } else {
                hiddenPlan1.setSea_year(hiddenPlan2.get(0).getSea_year());
            }
            if (null != danger12) {
                hiddenPlan1.setSea_ratio(danger12);
            } else {
                hiddenPlan1.setSea_ratio(hiddenPlan2.get(0).getSea_ratio());
            }
            if (null != danger13 && danger13 != 0) {
                hiddenPlan1.setEls_month(danger13);
            } else {
                hiddenPlan1.setEls_month(hiddenPlan2.get(0).getEls_month());
            }
            if (null != danger14 && danger14 != 0) {
                hiddenPlan1.setEls_year(danger14);
                count = danger14;
            } else {
                hiddenPlan1.setEls_year(hiddenPlan2.get(0).getEls_year());
            }
            if (null != danger15) {
                hiddenPlan1.setEls_ratio(danger15);
            } else {
                hiddenPlan1.setEls_ratio(hiddenPlan2.get(0).getEls_ratio());
            }
            if (null != danger16 && danger16 != 0) {
                hiddenPlan1.setBas_month(danger16);
            } else {
                hiddenPlan1.setBas_month(hiddenPlan2.get(0).getBas_month());
            }
            if (null != danger17 && danger17 != 0) {
                hiddenPlan1.setBas_year(danger17);
                count = danger17;
            } else {
                hiddenPlan1.setBas_year(hiddenPlan2.get(0).getBas_year());
            }
            if (null != danger18) {
                hiddenPlan1.setBas_ratio(danger18);
            } else {
                hiddenPlan1.setBas_ratio(hiddenPlan2.get(0).getBas_ratio());
            }
            if (null != danger1 && danger1 != 0 && null != hiddenPlan2.get(0).getSyn_month() && hiddenPlan2.get(0).getSyn_month() != 0) {
                number1 = hiddenPlan2.get(0).getSyn_month();
            } else if (null != danger4 && danger4 != 0 && null != hiddenPlan2.get(0).getEve_month() && hiddenPlan2.get(0).getEve_month() != 0) {
                number1 = hiddenPlan2.get(0).getEve_month();
            } else if (null != danger7 && danger7 != 0 && null != hiddenPlan2.get(0).getReg_month() && hiddenPlan2.get(0).getReg_month() != 0) {
                number1 = hiddenPlan2.get(0).getReg_month();
            } else if (null != danger11 && danger11 != 0 && null != hiddenPlan2.get(0).getSea_year() && hiddenPlan2.get(0).getSea_year() != 0) {
                number1 = hiddenPlan2.get(0).getSea_month() / year;
            } else if (null != danger14 && danger14 != 0 && null != hiddenPlan2.get(0).getEls_year() && hiddenPlan2.get(0).getEls_year() != 0) {
                number1 = hiddenPlan2.get(0).getEls_month() / year;
            } else if (null != danger17 && danger17 != 0 && null != hiddenPlan2.get(0).getBas_year() && hiddenPlan2.get(0).getBas_year() != 0) {
                number1 = hiddenPlan2.get(0).getBas_month() / year;
            }
            danger19 = hiddenPlan2.get(0).getSyn_year() + hiddenPlan2.get(0).getEve_year() + hiddenPlan2.get(0).getReg_year() +
                    hiddenPlan2.get(0).getSea_year() + hiddenPlan2.get(0).getEls_year() + hiddenPlan2.get(0).getBas_year() + count - (number1 * year);
            if (null != danger19 && danger19 != 0) {
                hiddenPlan1.setTotal_count(danger19);
            } else {
                hiddenPlan1.setTotal_count(hiddenPlan2.get(0).getTotal_count());
            }
            if (null != danger20) {
                hiddenPlan1.setTotal_ratio(danger20);
            } else {
                hiddenPlan1.setTotal_ratio(hiddenPlan2.get(0).getTotal_ratio());
            }
            hiddenPlan1.setCreate_time(new Date());
            hiddenPlan1.setUpdate_time(new Date());
            hiddenPlan1.setId(hiddenPlan2.get(0).getId());
            b = hiddenPlanMapper.updateByPrimaryKey(hiddenPlan1);
        }

        if ((null != b && b != 0) || null != a && a != 0) {
            result.setStatus("0");
            result.setMess("保存成功");
        } else {
            result.setStatus("1");
            result.setMess("保存失败，请检查数据！！！");
        }
    }

    /**
     * @author ：小明！！！
     * @description：隐患排查计划 公司级数据添加
     */
    @RequestMapping(value = "model-list-save-company")
    @ResponseBody
    public Result modelListMainCompany(HttpServletRequest request, Model model, Integer danger1, Integer danger2, String danger3, Integer danger4, Integer danger5,
                                String danger6, Integer danger7, Integer danger8, String danger9, Integer danger10, Integer danger11, String danger12, Integer danger13,
                                Integer danger14, String danger15, Integer danger16, Integer danger17, String danger18, Integer danger19, String danger20, Integer id, Integer dpid) {

        User user = getLoginUser(request);
        Result result = new ResultImpl();
        Integer a = null;
        Integer b = null;
        dpid = 0;
        danger2 = 0;
        danger5 = 0;

        danger8 = 0;
        if (null == danger11){
            danger11 = 0;
        }
        if (null == danger14){
            danger14 = 0;
        }
        if (null == danger17){
            danger17 = 0;
        }
        if (null == danger10){
            danger10 = 0;
        }
        if (null == danger13){
            danger13 = 0;
        }
        if (null == danger16){
            danger16 = 0;
        }

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        Integer year = 12 - month + 1;

        // 根据 ID 查询数据信息
        List<HiddenPlan> hiddenPlan2 = hiddenPlanMapper.selectDpid(dpid,user.getId());
        HiddenPlan hiddenPlan1 = new HiddenPlan();
        sumDatase1(danger1, danger2, danger3, danger4, danger5, danger6, danger7, danger8, danger9, danger10, danger11, danger12, danger13, danger14, danger15, danger16, danger17, danger18, danger20, dpid, user, result, a, b, year, hiddenPlan2, hiddenPlan1);
        return result;
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
    public String modelListMain(HttpServletRequest request, Model model, Integer flag, Integer status, Integer plan) throws ParseException {

        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String, Object> map1 = new LinkedHashMap<String, Object>();
        map1.put("level1",user.getUserName());

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
            Integer count =  tModelMapper.selectDangerCountByDepartAndUserId(level1, user.getId(),2);
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
            List<Integer> Jitypes = tModelMapper.selecttype(level1, user.getId(), 1, flag);
            for (Integer integer : Jitypes) {
                Jimap.put(integer, 1);
            }
            Integer count =  tModelMapper.selectDangerCountByDepartAndUserId(level1, user.getId(),1);
            if(null==count){
                jiChuMap.put("count", 0);
            }else{
                jiChuMap.put("count", count);
            }

            jiChuMap.put("array", Jimap);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> list = zzjgDepartmentMapper.selectHiddenPlan(user.getId());

        List<Map<String, Object>> list1 = hiddenPlanMapper.selectCountAll(user.getId());

        List<Map<String, Object>> hiddenPlanList = hiddenPlanMapper.findDpid(0,user.getId());

        if (hiddenPlanList.size() == 0){
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

            List<Map<String, Object>> hiddenPlanList1 = hiddenPlanMapper.findDpid(0,user.getId());//公司级

            model.addAttribute("hiddenPlanList",hiddenPlanList1);
        }else {

            model.addAttribute("hiddenPlanList",hiddenPlanList);
        }
        model.addAttribute("company",company.getName());
        model.addAttribute("data",sdf.format(new Date()));
        model.addAttribute("list", list);
        model.addAttribute("flag", flag);
        model.addAttribute("status", status);
        model.addAttribute("jiChuItem", jiChuItem);
        model.addAttribute("xianChangItem", XianChangItem);
        model.addAttribute("companyName", user.getUserName());
        model.addAttribute("plan", plan);
        model.addAttribute("list1",list1);
        model.addAttribute("name","合计");
        return "company/danger/model-list-main";

    }



    /**
     * create by  : 小明！！！
     * description: TODO 绩效分析  flag 1.企业自查；2.行政检查；3.第三方排查
     * create time: 2019/8/3 11:49
     */
    @RequestMapping(value = "zhuChartData55")
    public String zhuChartData55 (HttpServletRequest request, Model model,Integer flag){
        User user = getLoginUser(request);
        /*List<Map<String,Object>> list = zzjgDepartmentMapper.findAllLevel1(user.getId());*/
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = null;
        if (flag ==1){
            list = hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));
        }else if (flag == 2){
            list = tCheckMapper.findCheckCompany(String.valueOf(user.getId()),2);
        }else if (flag == 3){
            list = tCheckMapper.findCheckCompany(String.valueOf(user.getId()),3);
        }

        Map<String,Object> map = new HashMap<>();

        Integer count1 = 0;
        Integer count2 = 0;
        Integer  a = 0;
        Integer  b = 0;

        if (null == list || list.size() == 0){

        }else if (null != list && list.size() != 0){

            for (int i = 0; i < list.size(); i++) {

                if (flag == 1){
                    if (null == list.get(i).get("dpid") || list.get(i).get("dpid") == 0){
                        list1 = tCheckItemMapper.findHiddenSourceCount(flag, company.getName(),String.valueOf(user.getId()),2); // 现场
                        a = list1.size();
                        list.get(i).put("danger1",a);

                        list1 = tCheckItemMapper.findHiddenSourceCount(flag, company.getName(),String.valueOf(user.getId()),1); // 基础
                        b = list1.size();
                        list.get(i).put("danger2",b);

                    }else if (null != list.get(i).get("dpid") && list.get(i).get("dpid") == 0){
                        list1 = tCheckItemMapper.findHiddenSourceCount(flag, (String) list.get(i).get("name"),String.valueOf(user.getId()),2); // 现场
                        a = list1.size();
                        list.get(i).put("danger1",a);

                        list1 = tCheckItemMapper.findHiddenSourceCount(flag, (String) list.get(i).get("name"),String.valueOf(user.getId()),1); // 基础
                        b = list1.size();
                        list.get(i).put("danger2",b);
                    }
                }else {
                    list1 = tCheckItemMapper.lookHiddenSourceCount(flag, (String) list.get(i).get("name"),String.valueOf(user.getId()),2); // 现场
                    a = list1.size();
                    list.get(i).put("danger1",a);

                    list1 = tCheckItemMapper.lookHiddenSourceCount(flag, (String) list.get(i).get("name"),String.valueOf(user.getId()),1); // 基础
                    b = list1.size();
                    list.get(i).put("danger2",b);
                }

                count1 += a;
                count2 += b;

                Integer sum = a + b;

                list.get(i).put("sum",sum); // 某车间 现场 和 基础 的合计总数

            }
        }

        Integer sum   = count1 + count2;

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != sum && 0 != sum){

            if (0 != count1){
                String s = df.format((float)count1 / sum * 100);
                map.put("result1",s + "%"); // 所有车间 现场风险 占比总数
            }else {
                map.put("result1","0.00%"); // 所有车间 现场风险 占比总数
            }

            if (0 != count2){
                String s = df.format((float)count2 / sum * 100);
                map.put("result2",s + "%"); // 所有车间 基础风险 占比总数
            }else {
                map.put("result2","0.00%"); // 所有车间 基础风险 占比总数
            }

        }else {
            map.put("result1","0.00%"); // 所有车间 现场风险 占比总数
            map.put("result2","0.00%"); // 所有车间 基础风险 占比总数
        }

        map.put("count1",count1); // 所有车间 现场风险 合计总数
        map.put("count2",count2); // 所有车间 基础风险 合计总数

        list.add(map);

        model.addAttribute("data",new Date());
        model.addAttribute("list",list);

        return "company/danger/zhuChartData55";
    }


    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析  flag : 1.较大; 2.重大; 3.一般
     * create time: 2019/8/3 16:18
     */
    @RequestMapping(value = "zhuChartData77")
    public String zhuChartData77 (HttpServletRequest request, Model model){
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        /*List<Map<String,Object>> list = zzjgDepartmentMapper.findAllLevel1(user.getId());*/
        List<Map<String,Object>> list =  hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));
        System.out.println(list);
        List<Map<String,Object>> list1 = new ArrayList<Map<String, Object>>();
        DecimalFormat df = new DecimalFormat("0.00");
        Map<String,Object> map = new HashMap<>();

        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;
        Integer sum = 0;
        Integer a = 0;
        Integer b = 0;
        Integer c = 0;
        if (null == list || list.size() == 0){

        }else if (null != list && list.size() != 0){

            for (int i = 0; i < list.size(); i++) {

                if (null == list.get(i).get("name") && (Integer)list.get(i).get("dpid") == 0) {
                    list.get(i).put("name",company.getName());

                    Integer a1 = tCheckItemMapper.findHiddenLevelNews((String) list.get(i).get("name"),user.getId(),3); //一般 现场检查
                    Integer a2 = tCheckItemMapper.findHiddenLevelBasics((String) list.get(i).get("name"),user.getId(),3); // 一般 基础检查
                    a = a1 + a2;
                    list.get(i).put("danger1",a);

                    Integer b1 = tCheckItemMapper.findHiddenLevelNews((String) list.get(i).get("name"),user.getId(),1); // 较大   现场检查
                    Integer b2 = tCheckItemMapper.findHiddenLevelBasics((String) list.get(i).get("name"),user.getId(),1); // 较大 基础检查
                    b = b1 + b2;
                    list.get(i).put("danger2",b);
                    System.out.println((String) list.get(i).get("name"));
                    Integer c1 = tCheckItemMapper.findHiddenLevelNews((String) list.get(i).get("name"),user.getId(),2); // 重大  现场检查
                    Integer c2 = tCheckItemMapper.findHiddenLevelBasics((String) list.get(i).get("name"),user.getId(),2); // 重大 基础检查
                    c = c1 + c2;
                    System.out.println(c1+":"+c2);
                    list.get(i).put("danger3",c);
                }else if (null != list.get(i).get("name") && (Integer)list.get(i).get("dpid") != 0){

                    a = tCheckItemMapper.findHiddenLevelTypeByMap((String) list.get(i).get("name"),user.getId(),3); // 一般
                    list.get(i).put("danger1",a);
                    b = tCheckItemMapper.findHiddenLevelTypeByMap((String) list.get(i).get("name"),user.getId(),1); // 较大
                    list.get(i).put("danger2",b);
                    c = tCheckItemMapper.findHiddenLevelTypeByMap((String) list.get(i).get("name"),user.getId(),2); // 重大
                    list.get(i).put("danger3",c);

                }

                count1 += a;
                count2 += b;
                count3 += c;

                sum = a + b + c;

                list.get(i).put("sum", sum); // 某个车间所有隐患的 合计

                if (null != sum && sum != 0){
                    String str = df.format((float)a / sum * 100);// 一般 横占比
                    list.get(i).put("result11",str + "%");

                    String str1 = df.format((float)b / sum * 100); // 较大 横占比

                    list.get(i).put("result22",str + "%");

                    String str2 = df.format((float)c / sum * 100); // 重大 横占比

                    list.get(i).put("result33",str + "%");
                }else {

                    list.get(i).put("result11", "0.00%");
                    list.get(i).put("result22", "0.00%");
                    list.get(i).put("result33", "0.00%");
                }

                Map<String,Object> map1 = new HashMap<>();

                map1.put("name",list.get(i).get("name"));
                map1.put("sum",sum);

                list1.add(map1);
            }

            Collections.sort(list1, new Comparator<Map<String, Object>>() {

                public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                    Integer name1 = Integer.valueOf(o1.get("sum").toString()) ;

                    Integer name2 = Integer.valueOf(o2.get("sum").toString()) ;

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

        for (int i = 0; i < list.size(); i++) {
            Integer sums = count1 + count2 + count3;
            if (null != sums && 0 != sums){
                if (null != list.get(i).get("sum") && 0 != list.get(i).get("sum")){
                    String s = df.format((float)(Integer)list.get(i).get("sum") / sums * 100);
                    list.get(i).put("result44",s + "%");
                }else {
                    list.get(i).put("result44","0.00%");
                }
            }
        }

        Integer sums = count1 + count2 + count3;

        if (null != sums && 0 != sums){

            if (null != count1 && 0 != count1){
                String s = df.format((float)count1 / sums * 100);
                map.put("result1",s + "%"); // 一般 竖占比

            }else {
                map.put("result1","0.00%"); // 一般 竖占比
            }

            if (null != count2 && 0 != count2){
                String s = df.format((float)count2 / sums * 100);
                map.put("result2",s + "%"); // 较大 竖占比
            }else {
                map.put("result2","0.00%"); // 较大 竖占比
            }

            if (null != count3 && 0 != count3){
                String s = df.format((float)count3 / sums * 100);
                map.put("result3",s + "%"); // 重大 竖占比
            }else {
                map.put("result3","0.00%"); // 重大 竖占比
            }

        }else {
            map.put("result1","0.00%"); // 一般 竖占比
            map.put("result2","0.00%"); // 较大 竖占比
            map.put("result3","0.00%"); // 重大 竖占比
        }

        map.put("count1",count1); // 所有 一般隐患数据
        map.put("count2",count2); // 所有 较大隐患数据
        map.put("count3",count3); // 所有 重大隐患数据

        list.add(map);

        model.addAttribute("sums",sums);
        model.addAttribute("data",new Date());
        model.addAttribute("list",list);

        return "company/danger/zhuChartData77";
    }

    
    /**
     * create by  : 小明！！！
     * description: TODO 隐患治理数据分析  status : 1.已治理；2.未治理  flag : 1.较大；2.重大；3.一般和较小
     * create time: 2019/8/3 16:58
     */
    @RequestMapping(value = "zhuChartData44")
    public String zhuChartData44 (HttpServletRequest request, Model model){
        User user = getLoginUser(request);
        List<Map<String,Object>> list = zzjgDepartmentMapper.findAllLevel1(user.getId());
        List<Map<String,Object>> list1 = new ArrayList<Map<String, Object>>();
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String,Object> map = new HashMap<>();

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

        Integer numbers1 = 0;
        Integer numbers11 = 0;
        Integer numbers2 = 0;
        Integer numbers22 = 0;
        Integer numbers3 = 0;
        Integer numbers33 = 0;

        Map<String,Object> map1 = new HashMap<>();
        model.addAttribute("company",company.getName());

        Integer numbers41 = tCheckItemMapper.findRecheckNews(3,user.getId(),3,company.getName()); // 一般和较小 合格 已治理
        Integer numbers4 = tCheckItemMapper.findRecheckBasics(3,user.getId(),3,company.getName()); // 一般和较小 合格 已治理
        numbers1 = numbers41 + numbers4;
        model.addAttribute("numbers1",numbers1);
        sign1 = numbers1;


        Integer numbers51 = tCheckItemMapper.findRecheckNews(2,user.getId(),3,company.getName()); // 一般和较小 不合格 未治理
        Integer numbers5 = tCheckItemMapper.findRecheckBasics(2,user.getId(),3,company.getName()); // 一般和较小 不合格 未治理
        numbers11 =  numbers51+ numbers5;
        model.addAttribute("numbers11",numbers11);
        sign11 = numbers11;

        Integer add1 = numbers1 + numbers11;


        Integer numbers61 = tCheckItemMapper.findRecheckNews(3,user.getId(),1,company.getName()); // 较大 合格 已治理
        Integer numbers6 = tCheckItemMapper.findRecheckBasics(3,user.getId(),1,company.getName()); // 较大 合格 已治理
        numbers2 = numbers6+ numbers61;
        model.addAttribute("numbers2",numbers2);
        sign2 = numbers2;


        Integer numbers71 = tCheckItemMapper.findRecheckNews(2,user.getId(),1,company.getName()); // 较大 不合格 未治理
        Integer numbers7 = tCheckItemMapper.findRecheckBasics(2,user.getId(),1,company.getName()); // 较大 不合格 未治理
        numbers22 = numbers7 + numbers71;
        model.addAttribute("numbers22",numbers22);
        sign22 = numbers22;

        Integer add2 = numbers2 + numbers22;


        Integer numbers81 = tCheckItemMapper.findRecheckNews(3,user.getId(),2,company.getName()); // 重大 合格 已治理
        Integer numbers8 = tCheckItemMapper.findRecheckBasics(3,user.getId(),2,company.getName()); // 重大 合格 已治理
        numbers3 = numbers8 + numbers81;
        model.addAttribute("numbers3",numbers3);
        sign3 = numbers3;

        Integer numbers91 = tCheckItemMapper.findRecheckNews(2,user.getId(),2,company.getName()); // 重大 不合格 未治理
        Integer numbers9 = tCheckItemMapper.findRecheckBasics(2,user.getId(),2,company.getName()); // 重大 不合格 未治理
        numbers33 = numbers9 + numbers91;
        model.addAttribute("numbers33",numbers33);
        sign33 = numbers33;

        Integer add3 = numbers3 + numbers33;

        DecimalFormat df = new DecimalFormat("0.00");

        if (null != add1 && add1 != 0){  // 一般和较小 治理率
            String str = df.format((float)numbers1 / add1 * 100);
            model.addAttribute("rate1",str + "%");
        }else {
            model.addAttribute("rate1","0.00%");
        }

        if (null != add2 && add2 != 0){ // 较大 治理率
            String str = df.format((float)numbers2 / add2 * 100);
            model.addAttribute("rate2",str + "%");
        }else {
            model.addAttribute("rate2","0.00%");
        }

        if (null != add3 && add3 != 0){ // 重大 治理率
            String str = df.format((float)numbers3 / add3  * 100);
            model.addAttribute("rate3",str + "%");
        }else {
            model.addAttribute("rate3","0.00%");
        }

        Integer flag1 = numbers1 + numbers2 + numbers3; // 已治理 合计
        model.addAttribute("flag1",flag1);

        Integer flag2 = numbers11 + numbers22 + numbers33;  // 未治理 合计
        model.addAttribute("flag2",flag2);

        Integer flag = flag1 + flag2;

        Map<String,Object> map3 = new HashMap<>();

        map3.put("name",company.getName());
        map3.put("sum",flag1);

        list.add(map3);
        if (null != flag && flag != 0){
            String str = df.format((float)flag1 / flag * 100);
            model.addAttribute("number66",str + "%");  // 治理率 合计
        }else {
            model.addAttribute("number66","0.00%");
        }

        if (null == list || list.size() == 0){

        }else if (null != list || list.size() != 0){

            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).get("name").equals(company.getName())){
                    continue;
                }else {
                    Integer  a = tCheckItemMapper.findRecheckFileByMap(3,user.getId(),3,(String) list.get(i).get("name")); // 一般和较小 合格 已治理
                    list.get(i).put("danger1",a);

                    Integer  a1 = tCheckItemMapper.findRecheckFileByMap(2,user.getId(),3,(String) list.get(i).get("name")); // 一般和较小 不合格 未治理
                    list.get(i).put("danger11",a1);

                    count1 = a + a1 ;

                    Integer  b = tCheckItemMapper.findRecheckFileByMap(3,user.getId(),1,(String) list.get(i).get("name")); // 较大 合格 已治理
                    list.get(i).put("danger2",b);

                    Integer  b1 = tCheckItemMapper.findRecheckFileByMap(2,user.getId(),1,(String) list.get(i).get("name")); // 较大 不合格 未治理
                    list.get(i).put("danger22",b1);

                    count2  = b + b1;

                    Integer  c = tCheckItemMapper.findRecheckFileByMap(3,user.getId(),2,(String) list.get(i).get("name")); // 重大 合格 已治理
                    list.get(i).put("danger3",c);

                    Integer  c1 = tCheckItemMapper.findRecheckFileByMap(2,user.getId(),2,(String) list.get(i).get("name")); // 重大 不合格 未治理
                    list.get(i).put("danger33",c1);

                    count3  = c + c1;

                    sign1 += a;
                    sign2 += b;
                    sign3 += c;

                    sign11 += a1;
                    sign22 += b1;
                    sign33 += c1;

                    Integer sum = count1 + count2 + count3;

                    if (null != count1 && count1 != 0){  // 一般和较小 治理率
                        String str = df.format((float)a / count1 * 100);
                        list.get(i).put("result11",str + "%");
                    }else {
                        list.get(i).put("result11","0.00%");
                    }

                    if (null != count2 && count2 != 0){ // 较大 治理率
                        String str = df.format((float)b / count2 * 100);
                        list.get(i).put("result22",str + "%");
                    }else {
                        list.get(i).put("result22","0.00%");
                    }

                    if (null != count3 && count3 != 0){ // 重大 治理率
                        String str = df.format((float)c / count3 * 100);
                        list.get(i).put("result33",str + "%");
                    }else {
                        list.get(i).put("result33","0.00%");
                    }

                    Integer number1 = a + b + c; // 已治理 合计
                    list.get(i).put("number1",number1);

                    Integer number2 = a1 + b1 + c1;  // 未治理 合计
                    list.get(i).put("number2",number2);

                    Integer number = number1 + number2;

                    if (null != number && number != 0){
                        String str = df.format((float)number1 / number * 100);
                        list.get(i).put("number",str + "%");  // 治理率 合计
                    }else {
                        list.get(i).put("number","0.00%");
                    }

                    Map<String,Object> map2 = new HashMap<>();

                    if (list.get(i).get("name").equals(company.getName())){

                        map2.put("name",company.getName());
                        map2.put("sum",sum);
                    }else {
                        map2.put("name",list.get(i).get("name"));
                        map2.put("sum",sum);

                    }
                    list1.add(map2);
                }

            }

            Collections.sort(list1, new Comparator<Map<String, Object>>() {

                public int compare(Map<String, Object> o1, Map<String, Object> o2) {

                    Integer name1 = Integer.valueOf(o1.get("sum").toString()) ;

                    Integer name2 = Integer.valueOf(o2.get("sum").toString()) ;

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
            Integer datas = 0;
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).get("name").equals(company.getName())){
                    datas = (Integer) list1.get(i).get("data");
                }
            }
           model.addAttribute("datas",datas);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("name").equals(company.getName())){
                list.remove(i);
            }
        }

        Integer sub = sign1 + sign2 + sign3;

        Integer sub1 = sign11 + sign22 + sign33;

        model.addAttribute("sub",sub);
        model.addAttribute("sub1",sub1);

        Integer sum1 = sign1 + sign11;
        Integer sum2 = sign2 + sign22;
        Integer sum3 = sign3 + sign33;

        findCounts7(map, sign1, sign2, sign3, df, sum1, sum2, sum3);

        Integer proportion1 = sign1 + sign2 + sign3;

        Integer proportion2 = sign11 + sign22 + sign33;

        if (null != proportion1 && 0 != proportion1){

            if (null != sign1 && 0 != sign1){
                String str = df.format((float)sign1 / proportion1 * 100);
                map.put("proportion11",str + "%");  // 一般隐患 已治理 占比数据 竖
            }else {
                map.put("proportion11","0.00%"); // 一般隐患的治理率 竖
            }

            if (null != sign2 && 0 != sign2){
                String str = df.format((float)sign2 / proportion1 * 100);
                map.put("proportion22",str + "%");  // 较大隐患 已治理 占比数据 竖
            }else {
                map.put("proportion22","0.00%"); // 较大隐患 已治理 占比数据 竖
            }

            if (null != sign3 && 0 != sign3){
                String str = df.format((float)sign3 / proportion1 * 100);
                map.put("proportion33",str + "%");  // 重大隐患 已治理 占比数据 竖

            }else {
                map.put("proportion33","0.00%"); // 重大隐患 已治理 占比数据 竖
            }

        }else {
            map.put("proportion11","0.00%"); // 一般隐患的治理率 竖
            map.put("proportion22","0.00%"); // 较大隐患 已治理 占比数据 竖
            map.put("proportion33","0.00%"); // 重大隐患 已治理 占比数据 竖
        }

        if (null != proportion2 && 0 != proportion2){

            if (null != sign11 && 0 != sign11){
                String str = df.format((float)sign11 / proportion2 * 100);
                map.put("proportion44",str + "%");  // 一般隐患 未治理 占比数据 竖
            }else {
                map.put("proportion44","0.00%"); // 一般隐患 未治理 占比数据 竖
            }

            if (null != sign22 && 0 != sign22){
                String str = df.format((float)sign22 / proportion2 * 100);
                map.put("proportion55",str + "%");  // 较大隐患 未治理 占比数据 竖
            }else {
                map.put("proportion55","0.00%"); // 较大隐患 未治理 占比数据 竖
            }

            if (null != sign33 && 0 != sign33){
                Double sign = Double.valueOf(sign33 / proportion2);
                String str = df.format((float)sign3/proportion2 * 100);
                map.put("proportion66",str + "%");  // 重大隐患 未治理 占比数据 竖

            }else {
                map.put("proportion66","0.00%"); // 重大隐患 未治理 占比数据 竖
            }

        }else {
            map.put("proportion44","0.00%"); // 一般隐患的治理率 竖
            map.put("proportion55","0.00%"); // 较大隐患 已治理 占比数据 竖
            map.put("proportion66","0.00%"); // 重大隐患 已治理 占比数据 竖
        }

        map.put("sign1",sign1); // 一般隐患 已治理 合计 竖
        map.put("sign2",sign2); // 较大隐患 已治理 合计 竖
        map.put("sign3",sign3); // 重大隐患 已治理 合计 竖
        map.put("sign11",sign11); // 一般隐患 未治理 合计 竖
        map.put("sign22",sign22); // 较大隐患 未治理 合计 竖
        map.put("sign33",sign33); // 重大隐患 未治理 合计 竖

        list.add(map);

        model.addAttribute("data",new Date());
        model.addAttribute("list",list);

        return "company/danger/zhuChartData44";
    }

    private void findCounts7(Map<String, Object> map, Integer sign1, Integer sign2, Integer sign3, DecimalFormat df, Integer sum1, Integer sum2, Integer sum3) {
        if (null != sum1 && 0 != sum1){

            if (null != sign1 && 0 != sign1){
                String str = df.format((float)sign1 / sum1 * 100);
                map.put("result1",str + "%"); // 一般隐患的治理率 竖

            }else {
                map.put("result1","0.00%"); // 一般隐患的治理率 竖
            }
        }else {
            map.put("result1","0.00%"); // 一般隐患的治理率 竖
        }

        if (null != sum2 && 0 != sum2){

            if (null != sign2 && 0 != sign2){
                String str = df.format((float)sign2 / sum2 * 100);
                map.put("result2",str + "%"); // 较大隐患的治理率 竖

            }else {
                map.put("result2","0.00%"); // 较大隐患的治理率 竖
            }

        }else {
            map.put("result2","0.00%"); // 一般隐患的治理率 竖
        }

        if (null != sum3 && 0 != sum3){

            if (null != sign3 && 0 != sign3){
                String str = df.format((float)sign3/sum3 * 100);
                map.put("result3",str + "%"); // 重大隐患的治理率 竖

            }else {
                map.put("result3","0.00%"); // 一般隐患的治理率 竖
            }

        }else {
            map.put("result3","0.00%"); // 一般隐患的治理率 竖
        }
    }


    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析   所有未治理的数据  flag : 1.企业自查; 2.行政检查; 3.第三方排查;
     * create time: 2019/8/5 14:29
     */
    @RequestMapping(value = "zhuChartData22")
    public String zhuChartData22 (HttpServletRequest request, Model model, Integer flag){
        User user = getLoginUser(request);
        /*List<Map<String,Object>> list = zzjgDepartmentMapper.findAllLevel1(user.getId());*/
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = new ArrayList<Map<String, Object>>();

        if (flag ==1){
            list = hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));
        }else if (flag == 2){
            list = tCheckMapper.findCheckCompany(String.valueOf(user.getId()),2);
        }else if (flag == 3){
            list = tCheckMapper.findCheckCompany(String.valueOf(user.getId()),3);
        }

        Map<String,Object> map = new HashMap<>();

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

        if (null == list || list.size() == 0){

        }else if (null != list && list.size() != 0){
            for (int i = 0; i < list.size(); i++) {

                Integer a = 0;
                Integer b = 0;
                Integer c = 0;
                Integer d = 0;
                Integer e = 0;
                Integer f = 0;
                Integer g = 0;
                Integer h = 0;
                Integer i1 = 0;
                Integer j = 0;
                Integer k = 0;
                Integer l = 0;
                Integer m = 0;
                Integer n = 0;
                Integer o = 0;
                Integer p = 0;
                Integer q = 0;
                Integer r = 0;
                Integer s = 0;
                Integer s1 = 0;

                if (flag == 1){

                    if (null == list.get(i).get("dpid") || list.get(i).get("dpid") == 0){

                        list.get(i).put("name",company.getName());

                        s1 = tCheckItemMapper.zhu22ChartDataBasic(company.getName(),flag,user.getId()); // 生产工艺 隐患数据

                        s = tCheckItemMapper.zhu22ChartDataNow("基础管理",company.getName(),flag,user.getId()); // 生产工艺 隐患数据
                        list.get(i).put("danger19",s + s1);
                        sum19 += s;

                        a = tCheckItemMapper.zhu22ChartDataNow("设计总图",company.getName(),flag,user.getId()); // 生产工艺 隐患数据
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        b = tCheckItemMapper.zhu22ChartDataNow("试生产",company.getName(),flag,user.getId()); // 设备设施 隐患数据
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        c = tCheckItemMapper.zhu22ChartDataNow("装置运行",company.getName(),flag,user.getId()); // 特种设备 隐患数据
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        d = tCheckItemMapper.zhu22ChartDataNow("设备安全",company.getName(),flag,user.getId()); // 消防安全 隐患数据
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        e = tCheckItemMapper.zhu22ChartDataNow("仪表安全",company.getName(),flag,user.getId()); // 用电安全 隐患数据
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        f = tCheckItemMapper.zhu22ChartDataNow("电气安全",company.getName(),flag,user.getId()); // 行为环境 隐患数据
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        g = tCheckItemMapper.zhu22ChartDataNow("应急消防",company.getName(),flag,user.getId()); // 公辅设备 隐患数据
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        h = tCheckItemMapper.zhu22ChartDataNow("特殊管控",company.getName(),flag,user.getId()); // 危化管理 隐患数据
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        i1 = tCheckItemMapper.zhu22ChartDataNow("行为环境",company.getName(),flag,user.getId()); // 基础管理 隐患数据
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        j = tCheckItemMapper.zhu22ChartDataNow("生产现场",company.getName(),flag,user.getId()); // 防雷静电 隐患数据
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        k = tCheckItemMapper.zhu22ChartDataNow("公辅工程",company.getName(),flag,user.getId()); // 安全设施 隐患数据
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        l = tCheckItemMapper.zhu22ChartDataNow("特种设备",company.getName(),flag,user.getId()); // 职业卫生 隐患数据
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        m = tCheckItemMapper.zhu22ChartDataNow("专项行业",company.getName(),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        n = tCheckItemMapper.zhu22ChartDataNow("生产工艺",company.getName(),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        o = tCheckItemMapper.zhu22ChartDataNow("设备设施",company.getName(),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        p = tCheckItemMapper.zhu22ChartDataNow("危化管理",company.getName(),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        q = tCheckItemMapper.zhu22ChartDataNow("安全设施",company.getName(),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        r = tCheckItemMapper.zhu22ChartDataNow("其他",company.getName(),flag,user.getId()); // 其他 隐患数据
                        list.get(i).put("danger18",r);
                        sum18 += r;

                    }else if (null != list.get(i).get("dpid") && list.get(i).get("dpid") != 0){

                        s = tCheckItemMapper.zhuChartData22("基础管理",(String)list.get(i).get("name"),flag,user.getId()); // 生产工艺 隐患数据
                        list.get(i).put("danger19",s);
                        sum19 += s;

                        a = tCheckItemMapper.zhuChartData22("设计总图",(String)list.get(i).get("name"),flag,user.getId()); // 生产工艺 隐患数据
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        b = tCheckItemMapper.zhuChartData22("试生产",(String)list.get(i).get("name"),flag,user.getId()); // 设备设施 隐患数据
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        c = tCheckItemMapper.zhuChartData22("装置运行",(String)list.get(i).get("name"),flag,user.getId()); // 特种设备 隐患数据
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        d = tCheckItemMapper.zhuChartData22("设备安全",(String)list.get(i).get("name"),flag,user.getId()); // 消防安全 隐患数据
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        e = tCheckItemMapper.zhuChartData22("仪表安全",(String)list.get(i).get("name"),flag,user.getId()); // 用电安全 隐患数据
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        f = tCheckItemMapper.zhuChartData22("电气安全",(String)list.get(i).get("name"),flag,user.getId()); // 行为环境 隐患数据
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        g = tCheckItemMapper.zhuChartData22("应急消防",(String)list.get(i).get("name"),flag,user.getId()); // 公辅设备 隐患数据
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        h = tCheckItemMapper.zhuChartData22("特殊管控",(String)list.get(i).get("name"),flag,user.getId()); // 危化管理 隐患数据
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        i1 = tCheckItemMapper.zhuChartData22("行为环境",(String)list.get(i).get("name"),flag,user.getId()); // 基础管理 隐患数据
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        j = tCheckItemMapper.zhuChartData22("生产现场",(String)list.get(i).get("name"),flag,user.getId()); // 防雷静电 隐患数据
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        k = tCheckItemMapper.zhuChartData22("公辅工程",(String)list.get(i).get("name"),flag,user.getId()); // 安全设施 隐患数据
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        l = tCheckItemMapper.zhuChartData22("特种设备",(String)list.get(i).get("name"),flag,user.getId()); // 职业卫生 隐患数据
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        m = tCheckItemMapper.zhuChartData22("专项行业",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        n = tCheckItemMapper.zhuChartData22("生产工艺",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        o = tCheckItemMapper.zhuChartData22("设备设施",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        p = tCheckItemMapper.zhuChartData22("危化管理",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        q = tCheckItemMapper.zhuChartData22("安全设施",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        r = tCheckItemMapper.zhuChartData22("其他",(String)list.get(i).get("name"),flag,user.getId()); // 其他 隐患数据
                        list.get(i).put("danger18",r);
                        sum18 += r;
                    }

                }else {

                    if (null != list.get(i).get("depart") && list.get(i).get("depart").equals(company.getName())){

                        s1 = tCheckItemMapper.zhu23ChartDataBasics((String)list.get(i).get("name"),flag,user.getId()); // 生产工艺 隐患数据

                        s = tCheckItemMapper.zhu23ChartDataNows("基础管理",(String)list.get(i).get("name"),flag,user.getId()); // 生产工艺 隐患数据
                        list.get(i).put("danger19",s + s1);
                        sum19 += s;

                        a = tCheckItemMapper.zhu23ChartDataNows("设计总图",(String)list.get(i).get("name"),flag,user.getId()); // 生产工艺 隐患数据
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        b = tCheckItemMapper.zhu23ChartDataNows("试生产",(String)list.get(i).get("name"),flag,user.getId()); // 设备设施 隐患数据
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        c = tCheckItemMapper.zhu23ChartDataNows("装置运行",(String)list.get(i).get("name"),flag,user.getId()); // 特种设备 隐患数据
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        d = tCheckItemMapper.zhu23ChartDataNows("设备安全",(String)list.get(i).get("name"),flag,user.getId()); // 消防安全 隐患数据
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        e = tCheckItemMapper.zhu23ChartDataNows("仪表安全",(String)list.get(i).get("name"),flag,user.getId()); // 用电安全 隐患数据
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        f = tCheckItemMapper.zhu23ChartDataNows("电气安全",(String)list.get(i).get("name"),flag,user.getId()); // 行为环境 隐患数据
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        g = tCheckItemMapper.zhu23ChartDataNows("应急消防",(String)list.get(i).get("name"),flag,user.getId()); // 公辅设备 隐患数据
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        h = tCheckItemMapper.zhu23ChartDataNows("特殊管控",(String)list.get(i).get("name"),flag,user.getId()); // 危化管理 隐患数据
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        i1 = tCheckItemMapper.zhu23ChartDataNows("行为环境",(String)list.get(i).get("name"),flag,user.getId()); // 基础管理 隐患数据
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        j = tCheckItemMapper.zhu23ChartDataNows("生产现场",(String)list.get(i).get("name"),flag,user.getId()); // 防雷静电 隐患数据
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        k = tCheckItemMapper.zhu23ChartDataNows("公辅工程",(String)list.get(i).get("name"),flag,user.getId()); // 安全设施 隐患数据
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        l = tCheckItemMapper.zhu23ChartDataNows("特种设备",(String)list.get(i).get("name"),flag,user.getId()); // 职业卫生 隐患数据
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        m = tCheckItemMapper.zhu23ChartDataNows("专项行业",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        n = tCheckItemMapper.zhu23ChartDataNows("生产工艺",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        o = tCheckItemMapper.zhu23ChartDataNows("设备设施",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        p = tCheckItemMapper.zhu23ChartDataNows("危化管理",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        q = tCheckItemMapper.zhu23ChartDataNows("安全设施",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        r = tCheckItemMapper.zhu23ChartDataNows("其他",(String)list.get(i).get("name"),flag,user.getId()); // 其他 隐患数据
                        list.get(i).put("danger18",r);
                        sum18 += r;

                    }else if (null != list.get(i).get("depart")){

                        s = tCheckItemMapper.zhuChartData23("基础管理",(String)list.get(i).get("name"),flag,user.getId()); // 生产工艺 隐患数据
                        list.get(i).put("danger19",s);
                        sum19 += s;

                        a = tCheckItemMapper.zhuChartData23("设计总图",(String)list.get(i).get("name"),flag,user.getId()); // 生产工艺 隐患数据
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        b = tCheckItemMapper.zhuChartData23("试生产",(String)list.get(i).get("name"),flag,user.getId()); // 设备设施 隐患数据
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        c = tCheckItemMapper.zhuChartData23("装置运行",(String)list.get(i).get("name"),flag,user.getId()); // 特种设备 隐患数据
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        d = tCheckItemMapper.zhuChartData23("设备安全",(String)list.get(i).get("name"),flag,user.getId()); // 消防安全 隐患数据
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        e = tCheckItemMapper.zhuChartData23("仪表安全",(String)list.get(i).get("name"),flag,user.getId()); // 用电安全 隐患数据
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        f = tCheckItemMapper.zhuChartData23("电气安全",(String)list.get(i).get("name"),flag,user.getId()); // 行为环境 隐患数据
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        g = tCheckItemMapper.zhuChartData23("应急消防",(String)list.get(i).get("name"),flag,user.getId()); // 公辅设备 隐患数据
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        h = tCheckItemMapper.zhuChartData23("特殊管控",(String)list.get(i).get("name"),flag,user.getId()); // 危化管理 隐患数据
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        i1 = tCheckItemMapper.zhuChartData23("行为环境",(String)list.get(i).get("name"),flag,user.getId()); // 基础管理 隐患数据
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        j = tCheckItemMapper.zhuChartData23("生产现场",(String)list.get(i).get("name"), flag,user.getId()); // 防雷静电 隐患数据
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        k = tCheckItemMapper.zhuChartData23("公辅工程",(String)list.get(i).get("name"),flag,user.getId()); // 安全设施 隐患数据
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        l = tCheckItemMapper.zhuChartData23("特种设备",(String)list.get(i).get("name"),flag,user.getId()); // 职业卫生 隐患数据
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        m = tCheckItemMapper.zhuChartData23("专项行业",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        n = tCheckItemMapper.zhuChartData23("生产工艺",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        o = tCheckItemMapper.zhuChartData23("设备设施",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        p = tCheckItemMapper.zhuChartData23("危化管理",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        q = tCheckItemMapper.zhuChartData23("安全设施",(String)list.get(i).get("name"),flag,user.getId()); // 生产现场 隐患数据
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        r = tCheckItemMapper.zhuChartData23("其他",(String)list.get(i).get("name"),flag,user.getId()); // 其他 隐患数据
                        list.get(i).put("danger18",r);
                        sum18 += r;

                    }

                }

                Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                list.get(i).put("danger20",count);  // 某个车间的所有种类隐患的合计

                findSort2(flag, company, list, list1, i, count);
            }
        }

        findSort1(flag, list, list1);


        Integer count = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 + sum11 + sum12 + sum13 + sum14 + sum15 + sum16 + sum17 + sum18 + sum19;

        DecimalFormat df = new DecimalFormat("0.00");

        selectCount1(map, sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, sum11, sum12, sum13, sum14, sum15, sum16, sum17, sum18, sum19, count, df);

        // 合计 坚
        map.put("sum1",sum1);
        map.put("sum2",sum2);
        map.put("sum3",sum3);
        map.put("sum4",sum4);
        map.put("sum5",sum5);
        map.put("sum6",sum6);
        map.put("sum7",sum7);
        map.put("sum8",sum8);
        map.put("sum9",sum9);
        map.put("sum10",sum10);
        map.put("sum11",sum11);
        map.put("sum12",sum12);
        map.put("sum13",sum13);
        map.put("sum14",sum14);
        map.put("sum15",sum15);
        map.put("sum16",sum16);
        map.put("sum17",sum17);
        map.put("sum18",sum18);
        map.put("sum19",sum19);

        list.add(map);

        model.addAttribute("data",new Date());
        model.addAttribute("list",list);

        return "company/danger/zhuChartData22";
    }

    private void findSort2(Integer flag, Company company, List<Map<String, Object>> list, List<Map<String, Object>> list1, int i, Integer count) {
        if (null != flag){

            Map<String,Object> map1 = new HashMap<>();

            if (null == list.get(i).get("name")){
                map1.put("name",company.getName());
                map1.put("danger20",count);
            }else {
                map1.put("name",list.get(i).get("name"));
                map1.put("danger20",count);
            }
            list1.add(map1);
        }
    }


    /**
     * create by  : 小明！！！
     * description: TODO 隐患排查对象分析    flag : 1.企业自查; 2.行政检查; 3.第三方排查;
     * create time: 2019/8/5 14:29
     */
    @RequestMapping(value = "zhuChartData66")
    public String zhuChartData66 (HttpServletRequest request, Model model, Integer flag){
        User user = getLoginUser(request);
       /* List<Map<String,Object>> list = zzjgDepartmentMapper.findAllLevel1(user.getId());*/
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        List<Map<String,Object>> list = null;
        List<Map<String,Object>> list1 = new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> list2 = null;
        if (flag ==1){
            list = hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));
        }else if (flag == 2){
            list = tCheckMapper.findCheckCompany(String.valueOf(user.getId()),2);
        }else if (flag == 3){
            list = tCheckMapper.findCheckCompany(String.valueOf(user.getId()),3);
        }

        Map<String,Object> map = new HashMap<>();

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

        if (null == list || list.size() == 0){

        }else if (null != list && list.size() != 0){

            for (int i = 0; i < list.size(); i++) {

                Integer a = 0;
                Integer b = 0;
                Integer c = 0;
                Integer d = 0;
                Integer e = 0;
                Integer f = 0;
                Integer g = 0;
                Integer h = 0;
                Integer i1 = 0;
                Integer j = 0;
                Integer k = 0;
                Integer l = 0;
                Integer m = 0;
                Integer n = 0;
                Integer o = 0;
                Integer p = 0;
                Integer q = 0;
                Integer r = 0;
                Integer s = 0;
                Integer s1 = 0;
                if (flag == 1){

                    if (null == list.get(i).get("dpid") || list.get(i).get("dpid") == 0){

                        list.get(i).put("name",company.getName());

                        list2 = tCheckItemMapper.zhuFinichChartDataBasics(company.getName(),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        s1 = list2.size();

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("基础管理",company.getName(),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        s = list2.size() + s1;

                        list.get(i).put("danger19",s);
                        sum19 += s;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("设计总图",company.getName(),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        a = list2.size();
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("试生产",company.getName(),flag,String.valueOf(user.getId())); // 设备设施 隐患数据
                        b = list2.size();
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("装置运行",company.getName(),flag,String.valueOf(user.getId())); // 特种设备 隐患数据
                        c = list2.size();
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("设备安全",company.getName(),flag,String.valueOf(user.getId())); // 消防安全 隐患数据
                        d = list2.size();
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("仪表安全",company.getName(),flag,String.valueOf(user.getId())); // 用电安全 隐患数据
                        e = list2.size();
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("电气安全",company.getName(),flag,String.valueOf(user.getId())); // 行为环境 隐患数据
                        f = list2.size();
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("应急消防",company.getName(),flag,String.valueOf(user.getId())); // 公辅设备 隐患数据
                        g = list2.size();
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("特殊管控",company.getName(),flag,String.valueOf(user.getId())); // 危化管理 隐患数据
                        h = list2.size();
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("行为环境",company.getName(),flag,String.valueOf(user.getId())); // 基础管理 隐患数据
                        i1 = list2.size();
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("生产现场",company.getName(),flag,String.valueOf(user.getId())); // 防雷静电 隐患数据
                        j = list2.size();
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("公辅工程",company.getName(),flag,String.valueOf(user.getId())); // 安全设施 隐患数据
                        k = list2.size();
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("特种设备",company.getName(),flag,String.valueOf(user.getId())); // 职业卫生 隐患数据
                        l = list2.size();
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("专项行业",company.getName(),flag,String.valueOf(user.getId())); // 生产现场 隐患数据
                        m = list2.size();
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("生产工艺",company.getName(),flag,String.valueOf(user.getId())); // 防雷静电 隐患数据
                        n = list2.size();
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("设备设施",company.getName(),flag,String.valueOf(user.getId())); // 安全设施 隐患数据
                        o = list2.size();
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("危化管理",company.getName(),flag,String.valueOf(user.getId())); // 职业卫生 隐患数据
                        p = list2.size();
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("安全设施",company.getName(),flag,String.valueOf(user.getId())); // 生产现场 隐患数据
                        q = list2.size();
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        list2 = tCheckItemMapper.zhuFinichChartDataNow("其他",company.getName(),flag,String.valueOf(user.getId())); // 其他 隐患数据
                        r = list2.size();
                        list.get(i).put("danger18",r);
                        sum18 += r;


                    }else if (null != list.get(i).get("dpid") && list.get(i).get("dpid") != 0){

                        list2 = tCheckItemMapper.zhuChartData661("基础管理",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        s = list2.size();
                        list.get(i).put("danger19",s);
                        sum19 += s;

                        list2 = tCheckItemMapper.zhuChartData661("设计总图",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        a = list2.size();
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        list2 = tCheckItemMapper.zhuChartData661("试生产",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 设备设施 隐患数据
                        b = list2.size();
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        list2 = tCheckItemMapper.zhuChartData661("装置运行",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 特种设备 隐患数据
                        c = list2.size();
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        list2 = tCheckItemMapper.zhuChartData661("设备安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 消防安全 隐患数据
                        d = list2.size();
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        list2 = tCheckItemMapper.zhuChartData661("仪表安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 用电安全 隐患数据
                        e = list2.size();
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        list2 = tCheckItemMapper.zhuChartData661("电气安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 行为环境 隐患数据
                        f = list2.size();
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        list2 = tCheckItemMapper.zhuChartData661("应急消防",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 公辅设备 隐患数据
                        g = list2.size();
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        list2 = tCheckItemMapper.zhuChartData661("特殊管控",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 危化管理 隐患数据
                        h = list2.size();
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        list2 = tCheckItemMapper.zhuChartData661("行为环境",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 基础管理 隐患数据
                        i1 = list2.size();
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        list2 = tCheckItemMapper.zhuChartData661("生产现场",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 防雷静电 隐患数据
                        j = list2.size();
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        list2 = tCheckItemMapper.zhuChartData661("公辅工程",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 安全设施 隐患数据
                        k = list2.size();
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        list2 = tCheckItemMapper.zhuChartData661("特种设备",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 职业卫生 隐患数据
                        l = list2.size();
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        list2 = tCheckItemMapper.zhuChartData661("专项行业",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产现场 隐患数据
                        m = list2.size();
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        list2 = tCheckItemMapper.zhuChartData661("生产工艺",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 防雷静电 隐患数据
                        n = list2.size();
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        list2 = tCheckItemMapper.zhuChartData661("设备设施",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 安全设施 隐患数据
                        o = list2.size();
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        list2 = tCheckItemMapper.zhuChartData661("危化管理",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 职业卫生 隐患数据
                        p = list2.size();
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        list2 = tCheckItemMapper.zhuChartData661("安全设施",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产现场 隐患数据
                        q = list2.size();
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        list2 = tCheckItemMapper.zhuChartData661("其他",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 其他 隐患数据
                        r = list2.size();
                        list.get(i).put("danger18",r);
                        sum18 += r;
                    }

                }else {

                    if (null != list.get(i).get("depart") && list.get(i).get("depart").equals(company.getName())){

                        list2 = tCheckItemMapper.zhu671ChartDataBasics((String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        s1 = list2.size();

                        list2 = tCheckItemMapper.zhu671ChartDataNow("基础管理",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        s = list2.size() + s1;

                        list.get(i).put("danger19",s);
                        sum19 += s;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("设计总图",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        a = list2.size();
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("试生产",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 设备设施 隐患数据
                        b = list2.size();
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("装置运行",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 特种设备 隐患数据
                        c = list2.size();
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("设备安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 消防安全 隐患数据
                        d = list2.size();
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("仪表安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 用电安全 隐患数据
                        e = list2.size();
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("电气安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 行为环境 隐患数据
                        f = list2.size();
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("应急消防",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 公辅设备 隐患数据
                        g = list2.size();
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("特殊管控",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 危化管理 隐患数据
                        h = list2.size();
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("行为环境",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 基础管理 隐患数据
                        i1 = list2.size();
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("生产现场",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 防雷静电 隐患数据
                        j = list2.size();
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("公辅工程",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 安全设施 隐患数据
                        k = list2.size();
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("特种设备",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 职业卫生 隐患数据
                        l = list2.size();
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("专项行业",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产现场 隐患数据
                        m = list2.size();
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("生产工艺",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 防雷静电 隐患数据
                        n = list2.size();
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("设备设施",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 安全设施 隐患数据
                        o = list2.size();
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("危化管理",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 职业卫生 隐患数据
                        p = list2.size();
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("安全设施",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产现场 隐患数据
                        q = list2.size();
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        list2 = tCheckItemMapper.zhu671ChartDataNow("其他",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 其他 隐患数据
                        r = list2.size();
                        list.get(i).put("danger18",r);
                        sum18 += r;


                    }else if (null != list.get(i).get("depart")){

                        list2 = tCheckItemMapper.zhuChartData671("基础管理",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        s = list2.size();
                        list.get(i).put("danger19",s);
                        sum19 += s;

                        list2 = tCheckItemMapper.zhuChartData671("设计总图",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产工艺 隐患数据
                        a = list2.size();
                        list.get(i).put("danger1",a);
                        sum1 += a;

                        list2 = tCheckItemMapper.zhuChartData671("试生产",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 设备设施 隐患数据
                        b = list2.size();
                        list.get(i).put("danger2",b);
                        sum2 += b;

                        list2 = tCheckItemMapper.zhuChartData671("装置运行",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 特种设备 隐患数据
                        c = list2.size();
                        list.get(i).put("danger3",c);
                        sum3 += c;

                        list2 = tCheckItemMapper.zhuChartData671("设备安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 消防安全 隐患数据
                        d = list2.size();
                        list.get(i).put("danger4",d);
                        sum4 += d;

                        list2 = tCheckItemMapper.zhuChartData671("仪表安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 用电安全 隐患数据
                        e = list2.size();
                        list.get(i).put("danger5",e);
                        sum5 += e;

                        list2 = tCheckItemMapper.zhuChartData671("电气安全",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 行为环境 隐患数据
                        f = list2.size();
                        list.get(i).put("danger6",f);
                        sum6 += f;

                        list2 = tCheckItemMapper.zhuChartData671("应急消防",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 公辅设备 隐患数据
                        g = list2.size();
                        list.get(i).put("danger7",g);
                        sum7 += g;

                        list2 = tCheckItemMapper.zhuChartData671("特殊管控",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 危化管理 隐患数据
                        h = list2.size();
                        list.get(i).put("danger8",h);
                        sum8 += h;

                        list2 = tCheckItemMapper.zhuChartData671("行为环境",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 基础管理 隐患数据
                        i1 = list2.size();
                        list.get(i).put("danger9",i1);
                        sum9 += i1;

                        list2 = tCheckItemMapper.zhuChartData671("生产现场",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 防雷静电 隐患数据
                        j = list2.size();
                        list.get(i).put("danger10",j);
                        sum10 += j;

                        list2 = tCheckItemMapper.zhuChartData671("公辅工程",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 安全设施 隐患数据
                        k = list2.size();
                        list.get(i).put("danger11",k);
                        sum11 += k;

                        list2 = tCheckItemMapper.zhuChartData671("特种设备",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 职业卫生 隐患数据
                        l = list2.size();
                        list.get(i).put("danger12",l);
                        sum12 += l;

                        list2 = tCheckItemMapper.zhuChartData671("专项行业",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产现场 隐患数据
                        m = list2.size();
                        list.get(i).put("danger13",m);
                        sum13 += m;

                        list2 = tCheckItemMapper.zhuChartData671("生产工艺",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 防雷静电 隐患数据
                        n = list2.size();
                        list.get(i).put("danger14",n);
                        sum14 += n;

                        list2 = tCheckItemMapper.zhuChartData671("设备设施",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 安全设施 隐患数据
                        o = list2.size();
                        list.get(i).put("danger15",o);
                        sum15 += o;

                        list2 = tCheckItemMapper.zhuChartData671("危化管理",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 职业卫生 隐患数据
                        p = list2.size();
                        list.get(i).put("danger16",p);
                        sum16 += p;

                        list2 = tCheckItemMapper.zhuChartData671("安全设施",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 生产现场 隐患数据
                        q = list2.size();
                        list.get(i).put("danger17",q);
                        sum17 += q;

                        list2 = tCheckItemMapper.zhuChartData671("其他",(String) list.get(i).get("name"),flag,String.valueOf(user.getId())); // 其他 隐患数据
                        r = list2.size();
                        list.get(i).put("danger18",r);
                        sum18 += r;
                    }
                }

                Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                list.get(i).put("danger20",count);  // 某个车间的所有种类隐患的合计

                findSort2(flag, company, list, list1, i, count);
            }

        }

        findSort1(flag, list, list1);

        Integer count = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 + sum11 + sum12 + sum13 + sum14 + sum15 + sum16 + sum17 + sum18 + sum19;

        DecimalFormat df = new DecimalFormat("0.00");

        selectCount1(map, sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, sum11, sum12, sum13, sum14, sum15, sum16, sum17, sum18, sum19, count, df);
        // 合计 坚
        map.put("sum1",sum1);
        map.put("sum2",sum2);
        map.put("sum3",sum3);
        map.put("sum4",sum4);
        map.put("sum5",sum5);
        map.put("sum6",sum6);
        map.put("sum7",sum7);
        map.put("sum8",sum8);
        map.put("sum9",sum9);
        map.put("sum10",sum10);
        map.put("sum11",sum11);
        map.put("sum12",sum12);
        map.put("sum13",sum13);
        map.put("sum14",sum14);
        map.put("sum15",sum15);
        map.put("sum16",sum16);
        map.put("sum17",sum17);
        map.put("sum18",sum18);
        map.put("sum19",sum19);

        list.add(map);

        model.addAttribute("data",new Date());
        model.addAttribute("list",list);

        model.addAttribute("list1",list1);

        return "company/danger/zhuChartData66";
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


    /**
     * create by  : 小明！！！
     * description: TODO 隐患治理数据分析    status = 1 已治理的数据
     * create time: 2019/8/5 14:29
     */
    @RequestMapping(value = "zhuChartData123")
    public String zhuChartData123 (HttpServletRequest request, Model model){
        User user = getLoginUser(request);
        List<Map<String,Object>> list = zzjgDepartmentMapper.findAllLevel1(user.getId());
        List<Map<String,Object>> list1 = new ArrayList<Map<String, Object>>();
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        Map<String,Object> map = new HashMap<>();

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

        Integer aa = 0;
        Integer bb = 0;
        Integer cc = 0;
        Integer dd = 0;
        Integer ee = 0;
        Integer ff = 0;
        Integer gg = 0;
        Integer hh = 0;
        Integer ii = 0;
        Integer jj = 0;
        Integer kk = 0;
        Integer ll = 0;
        Integer mm = 0;
        Integer nn = 0;
        Integer oo = 0;
        Integer pp = 0;
        Integer qq = 0;
        Integer rr = 0;
        Integer ss = 0;
        Integer ss1 = 0;

        Integer count1 = 0;

        Map<String,Object> map1 = new HashMap<>();

        model.addAttribute("company",company.getName());
        ss1 = tCheckItemMapper.zhu123ChartDataBasics(company.getName(),user.getId()); // 生产工艺 隐患数据

        ss = tCheckItemMapper.zhu123ChartDataNews("基础管理",company.getName(),user.getId()) + ss1; // 生产工艺 隐患数据
        model.addAttribute("ss",ss);

        aa = tCheckItemMapper.zhu123ChartDataNews("设计总图",company.getName(),user.getId()); // 生产工艺 隐患数据
        model.addAttribute("aa",aa);

        bb = tCheckItemMapper.zhu123ChartDataNews("试生产",company.getName(),user.getId()); // 设备设施 隐患数据
        model.addAttribute("bb",bb);

        cc = tCheckItemMapper.zhu123ChartDataNews("装置运行",company.getName(),user.getId()); // 特种设备 隐患数据
        model.addAttribute("cc",cc);

        dd = tCheckItemMapper.zhu123ChartDataNews("设备安全",company.getName(),user.getId()); // 消防安全 隐患数据
        model.addAttribute("dd",dd);

        ee = tCheckItemMapper.zhu123ChartDataNews("仪表安全",company.getName(),user.getId()); // 用电安全 隐患数据
        model.addAttribute("ee",ee);

        ff = tCheckItemMapper.zhu123ChartDataNews("电气安全",company.getName(),user.getId()); // 行为环境 隐患数据
        model.addAttribute("ff",ff);

        gg = tCheckItemMapper.zhu123ChartDataNews("应急消防",company.getName(),user.getId()); // 公辅设备 隐患数据
        model.addAttribute("gg",gg);

        hh = tCheckItemMapper.zhu123ChartDataNews("特殊管控",company.getName(),user.getId()); // 危化管理 隐患数据
        model.addAttribute("hh",hh);

        ii = tCheckItemMapper.zhu123ChartDataNews("行为环境",company.getName(),user.getId()); // 基础管理 隐患数据
        model.addAttribute("ii",ii);

        jj = tCheckItemMapper.zhu123ChartDataNews("生产现场",company.getName(),user.getId()); // 防雷静电 隐患数据
        model.addAttribute("jj",jj);

        kk = tCheckItemMapper.zhu123ChartDataNews("公辅工程",company.getName(),user.getId()); // 安全设施 隐患数据
        model.addAttribute("kk",kk);

        ll = tCheckItemMapper.zhu123ChartDataNews("特种设备",company.getName(),user.getId()); // 职业卫生 隐患数据
        model.addAttribute("ll",ll);

        mm = tCheckItemMapper.zhu123ChartDataNews("专项行业",company.getName(),user.getId()); // 生产现场 隐患数据
        model.addAttribute("mm",mm);

        nn = tCheckItemMapper.zhu123ChartDataNews("生产工艺",company.getName(),user.getId()); // 防雷静电 隐患数据
        model.addAttribute("nn",nn);

        oo = tCheckItemMapper.zhu123ChartDataNews("设备设施",company.getName(),user.getId()); // 安全设施 隐患数据
        model.addAttribute("oo",oo);

        pp = tCheckItemMapper.zhu123ChartDataNews("危化管理",company.getName(),user.getId()); // 职业卫生 隐患数据
        model.addAttribute("pp",pp);

        qq = tCheckItemMapper.zhu123ChartDataNews("安全设施",company.getName(),user.getId()); // 生产现场 隐患数据
        model.addAttribute("qq",qq);

        rr = tCheckItemMapper.zhu123ChartDataNews("其他",company.getName(),user.getId()); // 其他 隐患数据
        model.addAttribute("rr",rr);

        count1 = aa + bb + cc + dd + ee + ff + gg + hh + ii + jj + kk + ll + mm + nn + oo + pp + qq + rr + ss;

        model.addAttribute("count1",count1);

        Map<String,Object> map3 = new HashMap<>();

        map3.put("name",company.getName());
        map3.put("danger20",count1);

        list.add(map3);

        if (null == list || list.size() == 0){

        }else if (null != list && list.size() != 0){

            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).get("name").equals(user.getUserName())){
                    continue;
                }else {

                    Integer  s = tCheckItemMapper.zhuChartData123("基础管理",(String) list.get(i).get("name"),user.getId()); // 生产工艺 隐患数据
                    list.get(i).put("danger19",s);
                    sum19 += s;

                    Integer  a = tCheckItemMapper.zhuChartData123("设计总图",(String) list.get(i).get("name"),user.getId()); // 生产工艺 隐患数据
                    list.get(i).put("danger1",a);
                    sum1 += a;

                    Integer  b = tCheckItemMapper.zhuChartData123("试生产",(String) list.get(i).get("name"),user.getId()); // 设备设施 隐患数据
                    list.get(i).put("danger2",b);
                    sum2 += b;

                    Integer  c = tCheckItemMapper.zhuChartData123("装置运行",(String) list.get(i).get("name"),user.getId()); // 特种设备 隐患数据
                    list.get(i).put("danger3",c);
                    sum3 += c;

                    Integer  d = tCheckItemMapper.zhuChartData123("设备安全",(String) list.get(i).get("name"),user.getId()); // 消防安全 隐患数据
                    list.get(i).put("danger4",d);
                    sum4 += d;

                    Integer  e = tCheckItemMapper.zhuChartData123("仪表安全",(String) list.get(i).get("name"),user.getId()); // 用电安全 隐患数据
                    list.get(i).put("danger5",e);
                    sum5 += e;

                    Integer  f = tCheckItemMapper.zhuChartData123("电气安全",(String) list.get(i).get("name"),user.getId()); // 行为环境 隐患数据
                    list.get(i).put("danger6",f);
                    sum6 += f;

                    Integer  g = tCheckItemMapper.zhuChartData123("应急消防",(String) list.get(i).get("name"),user.getId()); // 公辅设备 隐患数据
                    list.get(i).put("danger7",g);
                    sum7 += g;

                    Integer  h = tCheckItemMapper.zhuChartData123("特殊管控",(String) list.get(i).get("name"),user.getId()); // 危化管理 隐患数据
                    list.get(i).put("danger8",h);
                    sum8 += h;

                    Integer  i1 = tCheckItemMapper.zhuChartData123("行为环境",(String) list.get(i).get("name"),user.getId()); // 基础管理 隐患数据
                    list.get(i).put("danger9",i1);
                    sum9 += i1;

                    Integer  j = tCheckItemMapper.zhuChartData123("生产现场",(String) list.get(i).get("name"),user.getId()); // 防雷静电 隐患数据
                    list.get(i).put("danger10",j);
                    sum10 += j;

                    Integer  k = tCheckItemMapper.zhuChartData123("公辅工程",(String) list.get(i).get("name"),user.getId()); // 安全设施 隐患数据
                    list.get(i).put("danger11",k);
                    sum11 += k;

                    Integer  l = tCheckItemMapper.zhuChartData123("特种设备",(String) list.get(i).get("name"),user.getId()); // 职业卫生 隐患数据
                    list.get(i).put("danger12",l);
                    sum12 += l;

                    Integer  m = tCheckItemMapper.zhuChartData123("专项行业",(String) list.get(i).get("name"),user.getId()); // 生产现场 隐患数据
                    list.get(i).put("danger13",m);
                    sum13 += m;

                    Integer  n = tCheckItemMapper.zhuChartData123("生产工艺",(String) list.get(i).get("name"),user.getId()); // 防雷静电 隐患数据
                    list.get(i).put("danger14",n);
                    sum14 += n;

                    Integer  o = tCheckItemMapper.zhuChartData123("设备设施",(String) list.get(i).get("name"),user.getId()); // 安全设施 隐患数据
                    list.get(i).put("danger15",o);
                    sum15 += o;

                    Integer  p = tCheckItemMapper.zhuChartData123("危化管理",(String) list.get(i).get("name"),user.getId()); // 职业卫生 隐患数据
                    list.get(i).put("danger16",p);
                    sum16 += p;

                    Integer  q = tCheckItemMapper.zhuChartData123("安全设施",(String) list.get(i).get("name"),user.getId()); // 生产现场 隐患数据
                    list.get(i).put("danger17",q);
                    sum17 += q;

                    Integer  r = tCheckItemMapper.zhuChartData123("其他",(String) list.get(i).get("name"),user.getId()); // 其他 隐患数据
                    list.get(i).put("danger18",r);
                    sum18 += r;

                    Integer count = a + b + c + d + e + f + g + h + i1 + j + k + l + m + n + o + p + q + r + s;

                    list.get(i).put("danger20",count);  // 某个车间的所有种类隐患的合计

                    Map<String,Object> map2 = new HashMap<>();

                    if (list.get(i).get("name").equals(company.getName())){

                        map2.put("name",company.getName());
                        map2.put("danger20",count);
                    }else {
                        map2.put("name",list.get(i).get("name"));
                        map2.put("danger20",count);

                    }
                    list1.add(map2);
                }
            }
        }

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

        Integer datas = 0;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).get("name").equals(company.getName())){
                datas = (Integer) list1.get(i).get("data");
            }
        }

        model.addAttribute("datas",datas);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("name").equals(company.getName())){
                list.remove(i);
            }
        }

        sum1 = sum1 + aa;
        sum2 = sum2 + bb;
        sum3 = sum3 + cc;
        sum4 = sum4 + dd;
        sum5 = sum5 + ee;
        sum6 = sum6 + ff;
        sum7 = sum7 + gg;
        sum8 = sum8 + hh;
        sum9 = sum9 + ii;
        sum10 = sum10 + jj;
        sum11 = sum11 + kk;
        sum12 = sum12 + ll;
        sum13 = sum13 + mm;
        sum14 = sum14 + nn;
        sum15 = sum15 + oo;
        sum16 = sum16 + pp;
        sum17 = sum17 + qq;
        sum18 = sum18 + rr;
        sum19 = sum19 + ss;

        Integer count = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 + sum11 + sum12 + sum13 + sum14 + sum15 + sum16 + sum17 + sum18 + sum19;

        model.addAttribute("counts",count);

        DecimalFormat df = new DecimalFormat("0.00");

        selectCount1(map, sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, sum11, sum12, sum13, sum14, sum15, sum16, sum17, sum18, sum19, count, df);

        // 合计 坚sum18,
        map.put("sum1",sum1);
        map.put("sum2",sum2);
        map.put("sum3",sum3);
        map.put("sum4",sum4);
        map.put("sum5",sum5);
        map.put("sum6",sum6);
        map.put("sum7",sum7);
        map.put("sum8",sum8);
        map.put("sum9",sum9);
        map.put("sum10",sum10);
        map.put("sum11",sum11);
        map.put("sum12",sum12);
        map.put("sum13",sum13);
        map.put("sum14",sum14);
        map.put("sum15",sum15);
        map.put("sum16",sum16);
        map.put("sum17",sum17);
        map.put("sum18",sum18);
        map.put("sum19",sum19);

        list.add(map);

        model.addAttribute("data",new Date());
        model.addAttribute("list",list);

        return "company/danger/zhuChartData123";
    }




    /**
     * create by  : 小明！！！
     * description: 隐患排查绩效分析
     * create time: 2019/7/30 14:26
     */
    @RequestMapping(value = "danger-chart-jx")
    public String dangerCharJx(HttpServletRequest request, Model model) throws ParseException {

        User user = getLoginUser(request);
        Map map = new HashMap();
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("company",company.getName());
        List<Map<String,Object>> list =  hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));
        DecimalFormat df = new DecimalFormat("0.00");
        /*List<ZzjgDepartment> zList = zzjgDepartmentMapper.selectLevel1DangerIds(user.getId());*/
        if (null == list || list.size() == 0){

        }else if (null != list && list.size() != 0){

            for (int i = 0; i < list.size(); i++) {

                Integer a = tCheckMapper.findCountAll((String)list.get(i).get("name"),1,String.valueOf(user.getId()));

                list.get(i).put("danger1",a);

                Integer b = tCheckMapper.findCountAll((String)list.get(i).get("name"),2,String.valueOf(user.getId()));

                list.get(i).put("danger2",b);

                Integer c = tCheckMapper.findCountAll((String)list.get(i).get("name"),3,String.valueOf(user.getId()));

                list.get(i).put("danger3",c);

                Integer d = tCheckMapper.findCountAll((String)list.get(i).get("name"),4,String.valueOf(user.getId()));

                list.get(i).put("danger4",d);

                Integer e = tCheckMapper.findCountAll((String)list.get(i).get("name"),5,String.valueOf(user.getId()));

                list.get(i).put("danger5",e);

                Integer count = a + b + c + d + e;

                list.get(i).put("count",count);

                Integer counts = (Integer) list.get(i).get("syn_year") + (Integer)list.get(i).get("eve_year") + (Integer)list.get(i).get("reg_year") + (Integer)list.get(i).get("sea_year") + (Integer)list.get(i).get("els_year") + (Integer)list.get(i).get("bas_year");

                if (null != counts && 0 != counts) {

                    if (null != count && 0 != count) {
                        String str = df.format((float) count / counts * 100);

                        list.get(i).put("counts",str + "%");

                    } else {
                        list.get(i).put("counts","0.00%");
                    }
                }
            }
        }

        Integer count1 = 0;
        Integer count2 = 0;
        Integer count3 = 0;
        Integer count4 = 0;
        Integer count5 = 0;
        Integer number1 = 0;
        Integer number2 = 0;
        Integer number3 = 0;
        Integer number4 = 0;
        Integer number5 = 0;
        Integer number6 = 0;
        /*Integer sum = 0;*/

        for (int i = 0; i < list.size(); i++) {
            count1 += (Integer) list.get(i).get("danger1");
            count2 += (Integer) list.get(i).get("danger2");
            count3 += (Integer) list.get(i).get("danger3");
            count4 += (Integer) list.get(i).get("danger4");
            count5 += (Integer) list.get(i).get("danger5");

            if (null == list.get(i).get("syn_year")){
                number1 += 0 ;
            }else {
                number1 += (Integer)list.get(i).get("syn_year") ;
            }

            if (null == list.get(i).get("eve_year")){
                number2 += 0;
            }else {
                number2 += (Integer) list.get(i).get("eve_year");
            }

            if (null == list.get(i).get("reg_year")){
                number3 += 0;
            }else {
                number3 += (Integer) list.get(i).get("reg_year");
            }

            if (null == list.get(i).get("sea_year")){
                number4 += 0;
            }else {
                number4 += (Integer) list.get(i).get("sea_year");
            }

            if (null == list.get(i).get("els_year")){
                number5 += 0;
            }else {
                number5 += (Integer) list.get(i).get("els_year");
            }

            if (null == list.get(i).get("bas_year")){
                number6 += 0;
            }else {
                number6 += (Integer) list.get(i).get("bas_year");
            }
        }

        Integer sums = count1 + count2 + count3 + count4 + count5;

        Integer sums1 = number1 + number2 + number3 + number4 + number5 + number6;

        model.addAttribute("sums",sums);

        if (null != sums1 && 0 != sums1) {

            if (null != sums && 0 != sums) {
                String str = df.format((float) sums / sums1 * 100);
                model.addAttribute("sums1",str + "%");

            } else {
                model.addAttribute("sums1","0.00%");
            }
        }

        Map map1 = new HashMap();
        Integer sum = count1 + count2 + count3 + count4 + count5;

        Integer sum1 = number1 + number2 + number3 + number4 + number5 + number6;

        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result1 = numberFormat.format((float)count1 / (float)sum * 100);
        String result2 = numberFormat.format((float)count2 / (float)sum * 100);
        String result3 = numberFormat.format((float)count3 / (float)sum * 100);
        String result4 = numberFormat.format((float)count4 / (float)sum * 100);
        String result5 = numberFormat.format((float)count5 / (float)sum * 100);

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

        map1.put("result11",result11+"%");
        map1.put("result22",result22+"%");
        map1.put("result33",result33+"%");
        map1.put("result44",result44+"%");
        map1.put("result55",result55+"%");
        map1.put("result66",result66+"%");

        map.put("count1",count1);
        map.put("count2",count2);
        map.put("count3",count3);
        map.put("count4",count4);
        map.put("count5",count5);
        /*map.put("count6",count6);
        map.put("count7",count7);
        map.put("count8",count8);
        map.put("count9",count9);
        map.put("count10",count10);*/
        map.put("number1",number1);
        map.put("number2",number2);
        map.put("number3",number3);
        map.put("number4",number4);
        map.put("number5",number5);
        map.put("number6",number6);

        list.add(map);
        list.add(map1);
        model.addAttribute("list",list);
        return "company/danger/danger-chart-jx";

    }


    /**
     * create by  : 小明！！！
     * description: TODO 排查数据分析页面
     * create time: 2019/8/13 11:02
     */
    @RequestMapping(value = "jx-analysis")
    public String jxAnalysis(HttpServletRequest request,Model model){

        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        List<Map<String,Object>> list =  hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));
        List<Map<String,Object>> list1 = null;
        StringBuilder sb = new StringBuilder();
        Integer totalCounts = tCheckMapper.dataTotalCounts(user.getId());
        model.addAttribute("total",totalCounts);
        findComment1(company, list, sb);

        /*if (null == flag){
            flag = 1;
        }*/

        list1 = tCheckItemMapper.selectHiddenSources11(1,String.valueOf(user.getId())); // 企业自查
        Integer number1 = list1.size();

        list1 = tCheckItemMapper.selectHiddenSources11(2,String.valueOf(user.getId())); // 行政检查
        Integer number2 = list1.size();

        list1 = tCheckItemMapper.selectHiddenSources11(3,String.valueOf(user.getId())); // 第三方检查
        Integer number3 = list1.size();

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
        Integer o = 0;
        Integer p = 0;
        Integer q = 0;
        Integer r = 0;
        Integer s = 0;

        Integer a1 = 0;
        Integer b1 = 0;
        Integer c1 = 0;
        Integer d1 = 0;
        Integer e1 = 0;
        Integer f1 = 0;
        Integer g1 = 0;
        Integer h1 = 0;
        Integer i1 = 0;
        Integer j1 = 0;
        Integer k1 = 0;
        Integer l1 = 0;
        Integer m1 = 0;
        Integer n1 = 0;
        Integer o1 = 0;
        Integer p1 = 0;
        Integer q1 = 0;
        Integer r1 = 0;
        Integer s1 = 0;
        Integer s2 = 0;
        List<Map<String,Object>> list2 = null;
        /*if (flag == 1){*/
        if (null == sb.toString() || sb.toString().length() == 0){

        }else if (null != sb.toString() && sb.toString().length() != 0){

            for (int t = 0; t < list.size(); t++) {

                if (null == list.get(t).get("name") && (Integer)list.get(t).get("dpid") == 0){

                    list2 = tCheckItemMapper.zhuChartData521("基础管理",String.valueOf(user.getId()),company.getName()); // 生产工艺 隐患数据
                    s2 = list2.size();

                    list2 = tCheckItemMapper.zhuChartDataBasic(String.valueOf(user.getId()),company.getName()); // 生产工艺 隐患数据

                    s1 = list2.size() + s2;

                    list2 = tCheckItemMapper.zhuChartData521("设计总图",String.valueOf(user.getId()),company.getName()); // 生产工艺 隐患数据
                    a1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("试生产",String.valueOf(user.getId()),company.getName()); // 设备设施 隐患数据
                    b1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("装置运行",String.valueOf(user.getId()),company.getName()); // 特种设备 隐患数据
                    c1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("设备安全",String.valueOf(user.getId()),company.getName()); // 消防安全 隐患数据
                    d1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("仪表安全",String.valueOf(user.getId()),company.getName()); // 用电安全 隐患数据
                    e1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("电气安全",String.valueOf(user.getId()),company.getName()); // 行为环境 隐患数据
                    f1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("应急消防",String.valueOf(user.getId()),company.getName()); // 公辅设备 隐患数据
                    g1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("特殊管控",String.valueOf(user.getId()),company.getName()); // 危化管理 隐患数据
                    h1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("行为环境",String.valueOf(user.getId()),company.getName()); // 基础管理 隐患数据
                    i1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("生产现场",String.valueOf(user.getId()),company.getName()); // 防雷静电 隐患数据
                    j1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("公辅工程",String.valueOf(user.getId()),company.getName()); // 安全设施 隐患数据
                    k1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("特种设备",String.valueOf(user.getId()),company.getName()); // 职业卫生 隐患数据
                    l1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("专项行业",String.valueOf(user.getId()),company.getName()); // 生产现场 隐患数据
                    m1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("生产工艺",String.valueOf(user.getId()),company.getName()); // 防雷静电 隐患数据
                    n1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("设备设施",String.valueOf(user.getId()),company.getName()); // 安全设施 隐患数据
                    o1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("危化管理",String.valueOf(user.getId()),company.getName()); // 职业卫生 隐患数据
                    p1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("安全设施",String.valueOf(user.getId()),company.getName()); // 生产现场 隐患数据
                    q1 = list2.size();

                    list2 = tCheckItemMapper.zhuChartData521("其他",String.valueOf(user.getId()),company.getName()); // 其他 隐患数据
                    r1 = list2.size();

                }else {

                }
            }

            list2 = tCheckItemMapper.zhuChartData781("基础管理",String.valueOf(user.getId()),sb.toString()); // 生产工艺 隐患数据
            s = list2.size() + s1;

            list2 = tCheckItemMapper.zhuChartData781("设计总图",String.valueOf(user.getId()),sb.toString()); // 生产工艺 隐患数据
            a = list2.size() + a1;

            list2 = tCheckItemMapper.zhuChartData781("试生产",String.valueOf(user.getId()),sb.toString()); // 设备设施 隐患数据
            b = list2.size() + b1;

            list2 = tCheckItemMapper.zhuChartData781("装置运行",String.valueOf(user.getId()),sb.toString()); // 特种设备 隐患数据
            c = list2.size() + c1;

            list2 = tCheckItemMapper.zhuChartData781("设备安全",String.valueOf(user.getId()),sb.toString()); // 消防安全 隐患数据
            d = list2.size() + d1;

            list2 = tCheckItemMapper.zhuChartData781("仪表安全",String.valueOf(user.getId()),sb.toString()); // 用电安全 隐患数据
            e = list2.size() + e1;

            list2 = tCheckItemMapper.zhuChartData781("电气安全",String.valueOf(user.getId()),sb.toString()); // 行为环境 隐患数据
            f = list2.size() + f1;

            list2 = tCheckItemMapper.zhuChartData781("应急消防",String.valueOf(user.getId()),sb.toString()); // 公辅设备 隐患数据
            g = list2.size() + g1;

            list2 = tCheckItemMapper.zhuChartData781("特殊管控",String.valueOf(user.getId()),sb.toString()); // 危化管理 隐患数据
            h = list2.size() + h1;

            list2 = tCheckItemMapper.zhuChartData781("行为环境",String.valueOf(user.getId()),sb.toString()); // 基础管理 隐患数据
            i = list2.size() + i1;

            list2 = tCheckItemMapper.zhuChartData781("生产现场",String.valueOf(user.getId()),sb.toString()); // 防雷静电 隐患数据
            j = list2.size() + j1;

            list2 = tCheckItemMapper.zhuChartData781("公辅工程",String.valueOf(user.getId()),sb.toString()); // 安全设施 隐患数据
            k = list2.size() + k1;

            list2 = tCheckItemMapper.zhuChartData781("特种设备",String.valueOf(user.getId()),sb.toString()); // 职业卫生 隐患数据
            l = list2.size() + l1;

            list2 = tCheckItemMapper.zhuChartData781("专项行业",String.valueOf(user.getId()),sb.toString()); // 生产现场 隐患数据
            m = list2.size() + m1;

            list2 = tCheckItemMapper.zhuChartData781("生产工艺",String.valueOf(user.getId()),sb.toString()); // 防雷静电 隐患数据
            n = list2.size() + n1;

            list2 = tCheckItemMapper.zhuChartData781("设备设施",String.valueOf(user.getId()),sb.toString()); // 安全设施 隐患数据
            o = list2.size() + o1;

            list2 = tCheckItemMapper.zhuChartData781("危化管理",String.valueOf(user.getId()),sb.toString()); // 职业卫生 隐患数据
            p = list2.size() + p1;

            list2 = tCheckItemMapper.zhuChartData781("安全设施",String.valueOf(user.getId()),sb.toString()); // 生产现场 隐患数据
            q = list2.size() + q1;

            list2 = tCheckItemMapper.zhuChartData781("其他",String.valueOf(user.getId()),sb.toString()); // 其他 隐患数据
            r = list2.size() + r1;

        }
       /* }else {

            s = tCheckItemMapper.zhuChartData77("基础管理",flag,String.valueOf(user.getId())); // 生产工艺 隐患数据

            a = tCheckItemMapper.zhuChartData77("设计总图",flag,String.valueOf(user.getId())); // 生产工艺 隐患数据

            b = tCheckItemMapper.zhuChartData77("试生产",flag,String.valueOf(user.getId())); // 设备设施 隐患数据

            c = tCheckItemMapper.zhuChartData77("装置运行",flag,String.valueOf(user.getId())); // 特种设备 隐患数据

            d = tCheckItemMapper.zhuChartData77("设备安全",flag,String.valueOf(user.getId())); // 消防安全 隐患数据

            e = tCheckItemMapper.zhuChartData77("仪表安全",flag,String.valueOf(user.getId())); // 用电安全 隐患数据

            f = tCheckItemMapper.zhuChartData77("电气安全",flag,String.valueOf(user.getId())); // 行为环境 隐患数据

            g = tCheckItemMapper.zhuChartData77("应急消防",flag,String.valueOf(user.getId())); // 公辅设备 隐患数据

            h = tCheckItemMapper.zhuChartData77("特殊管控",flag,String.valueOf(user.getId())); // 危化管理 隐患数据

            i = tCheckItemMapper.zhuChartData77("行为环境",flag,String.valueOf(user.getId())); // 基础管理 隐患数据

            j = tCheckItemMapper.zhuChartData77("生产现场",flag,String.valueOf(user.getId())); // 防雷静电 隐患数据

            k = tCheckItemMapper.zhuChartData77("公辅工程",flag,String.valueOf(user.getId())); // 安全设施 隐患数据

            l = tCheckItemMapper.zhuChartData77("特种设备",flag,String.valueOf(user.getId())); // 职业卫生 隐患数据

            m = tCheckItemMapper.zhuChartData77("专项行业",flag,String.valueOf(user.getId())); // 生产现场 隐患数据

            n = tCheckItemMapper.zhuChartData77("生产工艺",flag,String.valueOf(user.getId())); // 防雷静电 隐患数据

            o = tCheckItemMapper.zhuChartData77("设备设施",flag,String.valueOf(user.getId())); // 安全设施 隐患数据

            p = tCheckItemMapper.zhuChartData77("危化管理",flag,String.valueOf(user.getId())); // 职业卫生 隐患数据

            q = tCheckItemMapper.zhuChartData77("安全设施",flag,String.valueOf(user.getId())); // 生产现场 隐患数据

            r = tCheckItemMapper.zhuChartData771(flag,String.valueOf(user.getId())); // 其他 隐患数据
        }*/
        model.addAttribute("s",s);
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
        model.addAttribute("o",o);
        model.addAttribute("p",p);
        model.addAttribute("q",q);
        model.addAttribute("r",r);

        count = a + b + c + d + e + f + g + h + i + j + k + l + m + n + o + p + q + r + s;

        model.addAttribute("count",count);

        DecimalFormat df = new DecimalFormat("0.00");

        selectCounts2(model, count, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, df);

        model.addAttribute("number1",number1);
        model.addAttribute("number2",number2);
        model.addAttribute("number3",number3);
       /* model.addAttribute("flag",flag);*/

        return "company/danger/jx-analysis";
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
                model.addAttribute("s1",str);

            } else {
                model.addAttribute("s1","0.00");  // 重大隐患 未治理 占比数据 竖
            }

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

            if (null != o && 0 != o) {
                String str = df.format((float) o / count);
                model.addAttribute("o1",str);

            } else {
                model.addAttribute("o1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != p && 0 != p) {
                String str = df.format((float) p / count);
                model.addAttribute("p1",str);

            } else {
                model.addAttribute("p1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != q && 0 != q) {
                String str = df.format((float) q / count);
                model.addAttribute("q1",str);

            } else {
                model.addAttribute("q1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != r && 0 != r) {
                String str = df.format((float) r / count);
                model.addAttribute("r1",str);

            } else {
                model.addAttribute("r1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

        }else {
            model.addAttribute("s1","0.00");
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
            model.addAttribute("o1","0.00");
            model.addAttribute("p1","0.00");
            model.addAttribute("q1","0.00");
            model.addAttribute("r1","0.00");
        }
    }


    /**
     * create by  : 小明！！！
     * description: TODO 隐患数据分析
     * create time: 2019/8/13 14:34
     */
    @RequestMapping(value = "yh-analysis")
    public String yhAnalysis(HttpServletRequest request, Model model){

        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        List<Map<String,Object>> list =  hiddenPlanMapper.selectDpids(String.valueOf(user.getId()));

        StringBuilder sb = new StringBuilder();

        findComment1(company, list, sb);

        /*if (null == flag){
            flag = 1;
        }*/
        Integer numbers1 = 0;
        Integer numbers2 = 0;
        Integer numbers3 = 0;
        Integer numbers4 = 0;
        Integer numbers5 = 0;
        Integer numbers6 = 0;
        for (int t = 0; t < list.size(); t++) {

            if (null == list.get(t).get("name") && (Integer) list.get(t).get("dpid") == 0) {

                numbers1 = tCheckItemMapper.findTypeByCompany(String.valueOf(user.getId()),3,company.getName()); // 一般隐患
                numbers2 = tCheckItemMapper.findTypeByBasic(String.valueOf(user.getId()),3,company.getName()); // 一般隐患

                numbers3 = tCheckItemMapper.findTypeByCompany(String.valueOf(user.getId()),2,company.getName()); // 一般隐患
                numbers4 = tCheckItemMapper.findTypeByBasic(String.valueOf(user.getId()),2,company.getName()); // 一般隐患

                numbers5 = tCheckItemMapper.findTypeByCompany(String.valueOf(user.getId()),1,company.getName()); // 一般隐患
                numbers6 = tCheckItemMapper.findTypeByBasic(String.valueOf(user.getId()),1,company.getName()); // 一般隐患

            }

        }
        Integer number3 = tCheckItemMapper.findTypeByMap(String.valueOf(user.getId()),3,company.getName()); // 一般隐患
        Integer number2 = tCheckItemMapper.findTypeByMap(String.valueOf(user.getId()),2,company.getName()); // 重大隐患
        Integer number1 = tCheckItemMapper.findTypeByMap(String.valueOf(user.getId()),1,company.getName()); // 较大隐患

        model.addAttribute("number1",number1 + numbers1 + numbers2);
        model.addAttribute("number2",number2 + numbers3 + numbers4);
        model.addAttribute("number3",number3 + numbers5 + numbers6);

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
        Integer o = 0;
        Integer p = 0;
        Integer q = 0;
        Integer r = 0;
        Integer s = 0;

        Integer a1 = 0;
        Integer b1 = 0;
        Integer c1 = 0;
        Integer d1 = 0;
        Integer e1 = 0;
        Integer f1 = 0;
        Integer g1 = 0;
        Integer h1 = 0;
        Integer i1 = 0;
        Integer j1 = 0;
        Integer k1 = 0;
        Integer l1 = 0;
        Integer m1 = 0;
        Integer n1 = 0;
        Integer o1 = 0;
        Integer p1 = 0;
        Integer q1 = 0;
        Integer r1 = 0;
        Integer s1 = 0;
        Integer s2 = 0;

        /*if (flag == 1){*/

        if (null == sb.toString() || sb.toString().length() == 0){

        }else if (null != sb.toString() || sb.toString().length() != 0){

            for (int t = 0; t < list.size(); t++) {

                if (null == list.get(t).get("name") && (Integer)list.get(t).get("dpid") == 0){

                    s2 = tCheckItemMapper.zhu521ChartData("基础管理",String.valueOf(user.getId()),company.getName()); // 生产工艺 隐患数据

                    Integer list2 = tCheckItemMapper.zhu5211ChartDataBasic(String.valueOf(user.getId()),company.getName()); // 生产工艺 隐患数据

                    s1 = list2 + s2;

                    a1 = tCheckItemMapper.zhu521ChartData("设计总图",String.valueOf(user.getId()),company.getName()); // 生产工艺 隐患数据

                    b1 = tCheckItemMapper.zhu521ChartData("试生产",String.valueOf(user.getId()),company.getName()); // 设备设施 隐患数据

                    c1 = tCheckItemMapper.zhu521ChartData("装置运行",String.valueOf(user.getId()),company.getName()); // 特种设备 隐患数据

                    d1 = tCheckItemMapper.zhu521ChartData("设备安全",String.valueOf(user.getId()),company.getName()); // 消防安全 隐患数据

                    e1 = tCheckItemMapper.zhu521ChartData("仪表安全",String.valueOf(user.getId()),company.getName()); // 用电安全 隐患数据

                    f1 = tCheckItemMapper.zhu521ChartData("电气安全",String.valueOf(user.getId()),company.getName()); // 行为环境 隐患数据

                    g1 = tCheckItemMapper.zhu521ChartData("应急消防",String.valueOf(user.getId()),company.getName()); // 公辅设备 隐患数据

                    h1 = tCheckItemMapper.zhu521ChartData("特殊管控",String.valueOf(user.getId()),company.getName()); // 危化管理 隐患数据

                    i1 = tCheckItemMapper.zhu521ChartData("行为环境",String.valueOf(user.getId()),company.getName()); // 基础管理 隐患数据

                    j1 = tCheckItemMapper.zhu521ChartData("生产现场",String.valueOf(user.getId()),company.getName()); // 防雷静电 隐患数据

                    k1 = tCheckItemMapper.zhu521ChartData("公辅工程",String.valueOf(user.getId()),company.getName()); // 安全设施 隐患数据

                    l1 = tCheckItemMapper.zhu521ChartData("特种设备",String.valueOf(user.getId()),company.getName()); // 职业卫生 隐患数据

                    m1 = tCheckItemMapper.zhu521ChartData("专项行业",String.valueOf(user.getId()),company.getName()); // 生产现场 隐患数据

                    n1 = tCheckItemMapper.zhu521ChartData("生产工艺",String.valueOf(user.getId()),company.getName()); // 防雷静电 隐患数据

                    o1 = tCheckItemMapper.zhu521ChartData("设备设施",String.valueOf(user.getId()),company.getName()); // 安全设施 隐患数据

                    p1 = tCheckItemMapper.zhu521ChartData("危化管理",String.valueOf(user.getId()),company.getName()); // 职业卫生 隐患数据

                    q1 = tCheckItemMapper.zhu521ChartData("安全设施",String.valueOf(user.getId()),company.getName()); // 生产现场 隐患数据

                    r1 = tCheckItemMapper.zhu521ChartData("其他",String.valueOf(user.getId()),company.getName()); // 其他 隐患数据

                }else {

                }
            }

            s = tCheckItemMapper.zhuChartData88("基础管理",String.valueOf(user.getId()),sb.toString()) + s1; // 生产工艺 隐患数据

            a = tCheckItemMapper.zhuChartData88("设计总图",String.valueOf(user.getId()),sb.toString()) + a1; // 生产工艺 隐患数据

            b = tCheckItemMapper.zhuChartData88("试生产",String.valueOf(user.getId()),sb.toString()) + b1; // 设备设施 隐患数据

            c = tCheckItemMapper.zhuChartData88("装置运行",String.valueOf(user.getId()),sb.toString()) + c1; // 特种设备 隐患数据

            d = tCheckItemMapper.zhuChartData88("设备安全",String.valueOf(user.getId()),sb.toString()) + d1; // 消防安全 隐患数据

            e = tCheckItemMapper.zhuChartData88("仪表安全",String.valueOf(user.getId()),sb.toString()) + e1; // 用电安全 隐患数据

            f = tCheckItemMapper.zhuChartData88("电气安全",String.valueOf(user.getId()),sb.toString()) + f1; // 行为环境 隐患数据

            g = tCheckItemMapper.zhuChartData88("应急消防",String.valueOf(user.getId()),sb.toString()) + g1; // 公辅设备 隐患数据

            h = tCheckItemMapper.zhuChartData88("特殊管控",String.valueOf(user.getId()),sb.toString()) + h1; // 危化管理 隐患数据

            i = tCheckItemMapper.zhuChartData88("行为环境",String.valueOf(user.getId()),sb.toString()) + i1; // 基础管理 隐患数据

            j = tCheckItemMapper.zhuChartData88("生产现场",String.valueOf(user.getId()),sb.toString()) + j1; // 防雷静电 隐患数据

            k = tCheckItemMapper.zhuChartData88("公辅工程",String.valueOf(user.getId()),sb.toString()) + k1; // 安全设施 隐患数据

            l = tCheckItemMapper.zhuChartData88("特种设备",String.valueOf(user.getId()),sb.toString()) + l1; // 职业卫生 隐患数据

            m = tCheckItemMapper.zhuChartData88("专项行业",String.valueOf(user.getId()),sb.toString()) + m1; // 生产现场 隐患数据

            n = tCheckItemMapper.zhuChartData88("生产工艺",String.valueOf(user.getId()),sb.toString()) + n1; // 防雷静电 隐患数据

            o = tCheckItemMapper.zhuChartData88("设备设施",String.valueOf(user.getId()),sb.toString()) + o1; // 安全设施 隐患数据

            p = tCheckItemMapper.zhuChartData88("危化管理",String.valueOf(user.getId()),sb.toString()) + p1; // 职业卫生 隐患数据

            q = tCheckItemMapper.zhuChartData88("安全设施",String.valueOf(user.getId()),sb.toString()) + q1; // 生产现场 隐患数据

            r = tCheckItemMapper.zhuChartData88("其他",String.valueOf(user.getId()),sb.toString()) + r1; // 其他 隐患数据
        }

        /*}else {

            s = tCheckItemMapper.zhuChartData89("基础管理",flag,String.valueOf(user.getId())); // 生产工艺 隐患数据

            a = tCheckItemMapper.zhuChartData89("设计总图",flag,String.valueOf(user.getId())); // 生产工艺 隐患数据

            b = tCheckItemMapper.zhuChartData89("试生产",flag,String.valueOf(user.getId())); // 设备设施 隐患数据

            c = tCheckItemMapper.zhuChartData89("装置运行",flag,String.valueOf(user.getId())); // 特种设备 隐患数据

            d = tCheckItemMapper.zhuChartData89("设备安全",flag,String.valueOf(user.getId())); // 消防安全 隐患数据

            e = tCheckItemMapper.zhuChartData89("仪表安全",flag,String.valueOf(user.getId())); // 用电安全 隐患数据

            f = tCheckItemMapper.zhuChartData89("电气安全",flag,String.valueOf(user.getId())); // 行为环境 隐患数据

            g = tCheckItemMapper.zhuChartData89("应急消防",flag,String.valueOf(user.getId())); // 公辅设备 隐患数据

            h = tCheckItemMapper.zhuChartData89("特殊管控",flag,String.valueOf(user.getId())); // 危化管理 隐患数据

            i = tCheckItemMapper.zhuChartData89("行为环境",flag,String.valueOf(user.getId())); // 基础管理 隐患数据

            j = tCheckItemMapper.zhuChartData89("生产现场",flag,String.valueOf(user.getId())); // 防雷静电 隐患数据

            k = tCheckItemMapper.zhuChartData89("公辅工程",flag,String.valueOf(user.getId())); // 安全设施 隐患数据

            l = tCheckItemMapper.zhuChartData89("特种设备",flag,String.valueOf(user.getId())); // 职业卫生 隐患数据

            m = tCheckItemMapper.zhuChartData89("专项行业",flag,String.valueOf(user.getId())); // 生产现场 隐患数据

            n = tCheckItemMapper.zhuChartData89("生产工艺",flag,String.valueOf(user.getId())); // 防雷静电 隐患数据

            o = tCheckItemMapper.zhuChartData89("设备设施",flag,String.valueOf(user.getId())); // 安全设施 隐患数据

            p = tCheckItemMapper.zhuChartData89("危化管理",flag,String.valueOf(user.getId())); // 职业卫生 隐患数据

            q = tCheckItemMapper.zhuChartData89("安全设施",flag,String.valueOf(user.getId())); // 生产现场 隐患数据

            r = tCheckItemMapper.zhuChartData891(flag,String.valueOf(user.getId())); // 其他 隐患数据
        }*/

        model.addAttribute("s",s);
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
        model.addAttribute("o",o);
        model.addAttribute("p",p);
        model.addAttribute("q",q);
        model.addAttribute("r",r);

        count = a + b + c + d + e + f + g + h + i + j + k + l + m + n + o + p + q + r + s;

        model.addAttribute("count",count);

        DecimalFormat df = new DecimalFormat("0.00");

        selectCounts3(model, s, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, count, df);

        /*model.addAttribute("flag",flag);*/
        return "company/danger/yh-analysis";
    }


    /**
     * create by  : 小明！！！
     * description: TODO  治理数据分析
     * create time: 2019/8/13 11:17
     */
    @RequestMapping(value = "zl-analysis")
    public String zlAnalysis(HttpServletRequest request, Model model){

        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());


        Integer numbers1 = tCheckItemMapper.findFinishCount(user.getId(),3,company.getName()); // 一般隐患 现场管理
        Integer numbers2 = tCheckItemMapper.findFinishBasic(user.getId(),3,company.getName()); // 一般隐患 基础管理

        Integer numbers3 = tCheckItemMapper.findFinishCount(user.getId(),2,company.getName()); // 一般隐患  现场管理
        Integer numbers4 = tCheckItemMapper.findFinishBasic(user.getId(),2,company.getName()); // 一般隐患  基础管理

        Integer numbers5 = tCheckItemMapper.findFinishCount(user.getId(),1,company.getName()); // 一般隐患  现场管理
        Integer numbers6 = tCheckItemMapper.findFinishBasic(user.getId(),1,company.getName()); // 一般隐患  基础管理

        Integer number4 = tCheckItemMapper.findFileByMap(user.getId(),3,company.getName()); // 一般隐患
        Integer number5 = tCheckItemMapper.findFileByMap(user.getId(),2,company.getName()); // 重大隐患
        Integer number6 = tCheckItemMapper.findFileByMap(user.getId(),1,company.getName()); // 较大隐患

        model.addAttribute("number4",number4 + numbers1 + numbers2);
        model.addAttribute("number5",number5 + numbers3 + numbers4);
        model.addAttribute("number6",number6 + numbers5 + numbers6);

        Integer s1 = tCheckItemMapper.zhu124ChartData("基础管理",user.getId(),company.getName()); // 生产工艺 隐患数据
        Integer s2 = tCheckItemMapper.zhu124ChartDataBasic(user.getId(),company.getName()); // 生产工艺 隐患数据
        Integer s = tCheckItemMapper.zhuChartData124("基础管理",user.getId(),company.getName()) + s1 + s2; // 生产工艺 隐患数据
        model.addAttribute("s",s);

        Integer a1 = tCheckItemMapper.zhu124ChartData("设计总图",user.getId(),company.getName()); // 生产工艺 隐患数据
        Integer a = tCheckItemMapper.zhuChartData124("设计总图",user.getId(),company.getName()) + a1; // 生产工艺 隐患数据
        model.addAttribute("a",a);

        Integer b1 = tCheckItemMapper.zhu124ChartData("试生产",user.getId(),company.getName()); // 设备设施 隐患数据
        Integer b = tCheckItemMapper.zhuChartData124("试生产",user.getId(),company.getName()) + b1; // 设备设施 隐患数据
        model.addAttribute("b",b);

        Integer c1 = tCheckItemMapper.zhu124ChartData("装置运行",user.getId(),company.getName()); // 特种设备 隐患数据
        Integer c = tCheckItemMapper.zhuChartData124("装置运行",user.getId(),company.getName()) + c1; // 特种设备 隐患数据
        model.addAttribute("c",c);

        Integer d1 = tCheckItemMapper.zhu124ChartData("设备安全",user.getId(),company.getName()); // 消防安全 隐患数据
        Integer d = tCheckItemMapper.zhuChartData124("设备安全",user.getId(),company.getName()) + d1; // 消防安全 隐患数据
        model.addAttribute("d",d);

        Integer e1 = tCheckItemMapper.zhu124ChartData("仪表安全",user.getId(),company.getName()); // 用电安全 隐患数据
        Integer e = tCheckItemMapper.zhuChartData124("仪表安全",user.getId(),company.getName()) + e1; // 用电安全 隐患数据
        model.addAttribute("e",e);

        Integer f1 = tCheckItemMapper.zhu124ChartData("电气安全",user.getId(),company.getName()); // 行为环境 隐患数据
        Integer f = tCheckItemMapper.zhuChartData124("电气安全",user.getId(),company.getName()) + f1; // 行为环境 隐患数据
        model.addAttribute("f",f);

        Integer g1 = tCheckItemMapper.zhu124ChartData("应急消防",user.getId(),company.getName()); // 公辅设备 隐患数据
        Integer g = tCheckItemMapper.zhuChartData124("应急消防",user.getId(),company.getName()) + g1; // 公辅设备 隐患数据
        model.addAttribute("g",g);

        Integer h1 = tCheckItemMapper.zhu124ChartData("特殊管控",user.getId(),company.getName()); // 危化管理 隐患数据
        Integer h = tCheckItemMapper.zhuChartData124("特殊管控",user.getId(),company.getName()) + h1; // 危化管理 隐患数据
        model.addAttribute("h",h);

        Integer i1 = tCheckItemMapper.zhu124ChartData("行为环境",user.getId(),company.getName()); // 基础管理 隐患数据
        Integer i = tCheckItemMapper.zhuChartData124("行为环境",user.getId(),company.getName()) + i1; // 基础管理 隐患数据
        model.addAttribute("i",i);

        Integer j1 = tCheckItemMapper.zhu124ChartData("生产现场",user.getId(),company.getName()); // 防雷静电 隐患数据
        Integer j = tCheckItemMapper.zhuChartData124("生产现场",user.getId(),company.getName()) + j1; // 防雷静电 隐患数据
        model.addAttribute("j",j);

        Integer k1 = tCheckItemMapper.zhu124ChartData("公辅工程",user.getId(),company.getName()); // 安全设施 隐患数据
        Integer k = tCheckItemMapper.zhuChartData124("公辅工程",user.getId(),company.getName()) + k1; // 安全设施 隐患数据
        model.addAttribute("k",k);

        Integer l1 = tCheckItemMapper.zhu124ChartData("特种设备",user.getId(),company.getName()); // 职业卫生 隐患数据
        Integer l = tCheckItemMapper.zhuChartData124("特种设备",user.getId(),company.getName()) + l1; // 职业卫生 隐患数据
        model.addAttribute("l",l);

        Integer m1 = tCheckItemMapper.zhu124ChartData("专项行业",user.getId(),company.getName()); // 生产现场 隐患数据
        Integer m = tCheckItemMapper.zhuChartData124("专项行业",user.getId(),company.getName()) + m1; // 生产现场 隐患数据
        model.addAttribute("m",m);

        Integer n1 = tCheckItemMapper.zhu124ChartData("生产工艺",user.getId(),company.getName()); // 防雷静电 隐患数据
        Integer n = tCheckItemMapper.zhuChartData124("生产工艺",user.getId(),company.getName()) + n1; // 防雷静电 隐患数据
        model.addAttribute("n",n);

        Integer o1 = tCheckItemMapper.zhu124ChartData("设备设施",user.getId(),company.getName()); // 安全设施 隐患数据
        Integer o = tCheckItemMapper.zhuChartData124("设备设施",user.getId(),company.getName()) + o1; // 安全设施 隐患数据
        model.addAttribute("o",o);

        Integer p1 = tCheckItemMapper.zhu124ChartData("危化管理",user.getId(),company.getName()); // 职业卫生 隐患数据
        Integer p = tCheckItemMapper.zhuChartData124("危化管理",user.getId(),company.getName()) + p1; // 职业卫生 隐患数据
        model.addAttribute("p",p);

        Integer q1 = tCheckItemMapper.zhu124ChartData("安全设施",user.getId(),company.getName()); // 生产现场 隐患数据
        Integer q = tCheckItemMapper.zhuChartData124("安全设施",user.getId(),company.getName()) + q1; // 生产现场 隐患数据
        model.addAttribute("q",q);

        Integer r1 = tCheckItemMapper.zhu124ChartData("其他",user.getId(),company.getName()); // 其他 隐患数据
        Integer r = tCheckItemMapper.zhuChartData124("其他",user.getId(),company.getName()) + r1; // 其他 隐患数据
        model.addAttribute("r",r);

        Integer count = a + b + c + d + e + f + g + h + i + j + k + l + m + n + o + p + q + r + s;

        model.addAttribute("count",count);

        DecimalFormat df = new DecimalFormat("0.00");

        selectCounts3(model, s, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, count, df);

        return "company/danger/zl-analysis";
    }

    private void selectCounts3(Model model, Integer s, Integer a, Integer b, Integer c, Integer d, Integer e, Integer f, Integer g, Integer h, Integer i, Integer j, Integer k, Integer l, Integer m, Integer n, Integer o, Integer p, Integer q, Integer r, Integer count, DecimalFormat df) {
        if (null != count && 0 != count) {

            if (null != s && 0 != s) {
                String str = df.format((float) s / count);
                model.addAttribute("s1",str);

            } else {
                model.addAttribute("s1","0.00");  // 重大隐患 未治理 占比数据 竖
            }

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

            if (null != o && 0 != o) {
                String str = df.format((float) o / count);
                model.addAttribute("o1",str);

            } else {
                model.addAttribute("o1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != p && 0 != p) {
                String str = df.format((float) p / count);
                model.addAttribute("p1",str);

            } else {
                model.addAttribute("p1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != q && 0 != q) {
                String str = df.format((float) q / count);
                model.addAttribute("q1",str);

            } else {
                model.addAttribute("q1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

            if (null != r && 0 != r) {
                String str = df.format((float) r / count);
                model.addAttribute("r1",str);

            } else {
                model.addAttribute("r1","0.00"); // 重大隐患 未治理 占比数据 竖
            }

        }else {

            model.addAttribute("s1","0.00");
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
            model.addAttribute("o1","0.00");
            model.addAttribute("p1","0.00");
            model.addAttribute("q1","0.00");
            model.addAttribute("r1","0.00");
        }
    }


    /**
     * TODO 用户点击检查设置实施=> 实施 =>点击执行的时候获取这个方法的检查模版的最新的一条记录,
     * 没有就显示为null
     * 每一次获取的就是最新的
     * 没有就表示数据库没有这个部门的模版,让用户直接添加
     */
    @ResponseBody
    @RequestMapping(value = "model-list-ss")
    public AppResult modelListss(HttpServletRequest request, Model model, String dmname, Integer dmid, Integer checkType,
                                 Integer industryType, Integer template, Integer flag) {
        AppResult result = new AppResultImpl();
        User user = getLoginUser(request);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("userId", user.getId());    // 公司id,必要
        map.put("dmname", dmname);          // 部门名称
        map.put("type", checkType);         // 检查方式
        map.put("flag", flag);
        if (industryType == -1) {
            map.put("industryType", 1);  // 基础类型
        } else if (industryType == -2) {
            map.put("industryType", 2);  // 现场类型
        } else {
            map.put("industryType", 3);  // 高危类型
        }

        TModel tModel = tModelMapper.selectModelByMapAndLimit1(map);
        if (null == tModel) {
            result.setStatus("1");
            return result;
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("modelId", tModel.getId());
        resultMap.put("flag", flag);
        result.setStatus("0");
        result.setData(resultMap);
        return result;

    }

    /**
     * create by  : 小明！！！
     * description: TODO 检查设置实施  定期检查数据展示
     * create time: 2019/8/1 9:24
     */
    @RequestMapping(value = "model-list-showAll")
    public String modelListShow(HttpServletRequest request, Model model, String dmname, Integer dmid, Integer checkType,
                                 Integer industryType, Integer template, Integer flag,Integer status) {

        User user = getLoginUser(request);
        // 根据查检类型，检查车间名称查询数据信息
        List<Map<String,Object>> list = tCheckMapper.selectShowAll(dmname,user.getId(),status);

        model.addAttribute("list",list);
        model.addAttribute("dmname",dmname);
        model.addAttribute("dmid",dmid);
        model.addAttribute("checkType",checkType);
        model.addAttribute("industryType",industryType);
        model.addAttribute("template",template);
        model.addAttribute("flag",flag);

        return "company/danger/model-list-main1";

    }




    /**
     * TODO 用户点击检查设置实施=> 实施 => 查询对应的model模版
     *
     * @param request      前端请求
     * @param model        mvc数据存储
     * @param dmname       部门名称
     * @param dmid         部门id
     * @param checkType    检查方式
     * @param industryType 检查类型 基础/现场
     * @return
     */
    @RequestMapping(value = "model-list-tj")
    public String modelListtj(HttpServletRequest request, Model model, String dmname, Integer dmid, Integer checkType,
                              Integer industryType, Integer template, Integer flag) {
        User user = getLoginUser(request);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("userId", user.getId());    // 公司id,必要
        map.put("dmname", dmname);          // 部门名称
        map.put("dmid", dmid);              // 部门id
        map.put("type", checkType);         // 检查方式
        if (industryType == -1) {
            map.put("industryType", 1);  // 基础类型
        } else if (industryType == -2) {
            map.put("industryType", 2);  // 现场类型
        } else {
            map.put("industryType", 3);  // 高危类型
        }

        map.put("flag", flag);

        List<Map<String, Object>> list = tModelMapper.selectByMap4(map);
        model.addAttribute("list", list);
        model.addAttribute("dmname", dmname);
        model.addAttribute("dmid", dmid);
        model.addAttribute("type", checkType);
        model.addAttribute("industryType", industryType);
        model.addAttribute("userId", user.getId());
        model.addAttribute("template", template);
        model.addAttribute("flag", flag);

        //企业类型为 化工 或 构成重大危险源 则企业自查处 日检查表显示 wz 190108
        Company company = companyMapper.selectByPrimaryKey(user.getId());

        /*
         * 监管行业不能为空
         */
        //log.error("监管行业："+ company.getIndustry());
        if (null != company.getHazard()) {
            if (company.getHazard() == 1 || company.getIndustry().trim().equals("化工企业（危化生产、使用）")) {//显示效果需要
                //log.error("监管行业："+company.getIndustry());
                //log.error("是否构成重大危险源、1是："+company.getHazard());
                model.addAttribute("rjcbxs", 1);

            }
        }
        return "company/danger/model-list-cx";
    }

    /**
     * TODO   整改复查
     * TODO   title  type  compantName status 的传递
     * 检查设置与实施-整改实施
     */
    @RequestMapping(value = "check-list")
    public String troubleList1(HttpServletRequest request, String title, Integer type, String companyName,
                               Integer status, Integer flag, Model model) throws Exception {
        User user = getLoginUser(request);
        //企业类型为 化工 或 构成重大危险源 则企业自查处 日检查表显示 wz 190108
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (company.getHazard() == 1 || company.getIndustry().trim().equals("化工企业（危化生产、使用）")) {
            //log.error("监管行业："+company.getIndustry());
            //log.error("是否构成重大危险源、1是："+company.getHazard());
            model.addAttribute("rjcbxs", 1);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", flag);
        m.put("title", title);
        m.put("companyName", companyName);
        m.put("status", status);
        if (setUserId(user, m)) {
            clearVillageTown(m);
            List<Map<String, Object>> list = tCheckMapper.selectList(m);
            for (Map<String, Object> stringObjectMap : list) {

            }
            model.addAttribute("list", list);
        }
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("companyName", companyName);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        Date d = new Date();
        String x = DateFormatUtils.format(d, "yyyy-MM-dd");
        d = DateConvertUtil.formateDate(x, "yyyy-MM-dd");
        model.addAttribute("t", d.getTime());
        if (user.getUserType() == 5) {//企业用户
            return "company/danger/model-list-cx";
        }
        return "village/danger/check-list";
    }

    /**
     * 不知道什么方法
     */
    @RequestMapping(value = "model-add")//modify by zhangcl 2018.10.24
    public String modelAdd(Integer type, Integer flag, Integer[] ids, Integer depId,
                           Integer industryId, HttpServletRequest request, Model model)
            throws Exception {
        User user = getLoginUser(request);
        TCompany tc = tCompanyMapper.selectByPrimaryKey(user.getId());
        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        if (null == tc) {
            return "redirect:/village/trouble-set?url="
                    + URLEncoder.encode("/company/model-add?type=" + type + "&flag=" + flag, "utf-8");
        }
        model.addAttribute("ind1", tIndustryMapper.selectByPrimaryKey(tc.getIndustry1()));// 基础检查类别
        if (StringUtils.isNotBlank(tc.getIndustry2())) {
            model.addAttribute("ind2L", tIndustryMapper.selectByIds(tc.getIndustry2()));// 现场检查类别
        }
        if (StringUtils.isNotBlank(tc.getIndustry3())) {
            model.addAttribute("ind3L", tIndustryMapper.selectByIds(tc.getIndustry3()));// 高危检查类别
        }
        if (type != null && type == 9) {//新增检查表类型
            model.addAttribute("companyname", user.getUserName());
            model.addAttribute("items", aDangerManualMapper.selectByIds(ids));
            //log.error("depId:"+depId);
            List<Map<Object, Object>> zzjg = zzjgDepartmentMapper.selectNameParnameById(depId);
            //log.error(zzjg==null?"yes":"no");
            for (Map<Object, Object> p : zzjg) {
                log.error(p.get("id") + "," + p.get("name") + "," + p.get("parName"));
                model.addAttribute("name", p.get("name"));
                model.addAttribute("parName", p.get("parName"));
                break;
            }
            return "company/danger/model-add3";
        }
        return "company/danger/model-add2";
    }

    /**
     * 企业自查 编辑模板
     */
    @RequestMapping(value = "model-edit")
    public String modelEdit(Integer modelId, HttpServletRequest request, Model model) throws Exception {
        TModel m = tModelMapper.selectByPrimaryKey(modelId);
        model.addAttribute("model", m);
        model.addAttribute("type", m.getType());
        model.addAttribute("flag", m.getFlag());
        model.addAttribute("industry", tIndustryMapper.selectByPrimaryKey(m.getIndustryId()));
        List<TModelPart> partL = tModelPartMapper.selectByModelId(modelId);
        StringBuffer ids = new StringBuffer();
        for (TModelPart p : partL) {
            if (!"".equals(ids.toString())) {
                ids = ids.append(",");
            }
            ids = ids.append(p.getLevels());
        }
        model.addAttribute("partL", partL);
        model.addAttribute("levelL", tLevelMapper.selectByIds(ids.toString()));
        return "company/danger/model-add2";
    }

    /**
     * 企业自查添加部位或装置
     */
    @RequestMapping(value = "model-part-add")
    public String modelPartAdd(Integer industryId, Model model, String partN, String levels, Integer index)
            throws Exception {
        List<TLevel> lL = tLevelMapper.selectByIndustry(industryId);// 检查级别
        model.addAttribute("levelL", gson.toJson(lL));
        if (StringUtils.isNotBlank(partN)) {
            model.addAttribute("levelIds", levels);
            model.addAttribute("index", index);
            model.addAttribute("partN", new String(partN.getBytes("ISO8859_1"), "utf-8"));
        }
        model.addAttribute("tIndustry", tIndustryMapper.selectByPrimaryKey(industryId));
        return "company/danger/model-window";
    }

    /**
     * 企业自查模板删除
     */
    @RequestMapping(value = "model-del")
    public @ResponseBody
    Result modelDel(TModel t, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        t.setDel(1);
        tModelMapper.updateByPrimaryKeySelective(t);
        return result;
    }

    /**
     * 企业自查模板保存
     */
    @RequestMapping(value = "model-save")
    public @ResponseBody
    Result modelSave(@RequestBody ModelSaveReqDTO dto, HttpServletRequest request)
            throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        dto.getModel().setUserId(user.getId());
        cgfService.modelSave(dto, result);
        result.setMap("modelId", dto.getModel().getId());
        return result;
    }

    /**
     * 企业自查模板 自动化设置保存
     */
    @RequestMapping(value = "model-auto")
    public @ResponseBody
    Result modelAuto(TModel model, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        log.error("model: " + model.toString());
        cgfService.modelAuto(model, result);
        return result;
    }

    /**
     * 计划 自动生成检查表设置
     */
//    @RequestMapping(value = "plan-auto")
//    public String planAuto(Integer modelId, Model model) throws Exception {
//        TModel tm = tModelMapper.selectByPrimaryKey(modelId);
//        model.addAttribute("model", tm);
//        return "company/danger/model-auto";
//    }
    @RequestMapping(value = "plan-auto")//modi fy by zhangcl 2018.10.27
    public String planAuto(Integer modelId, Model model, Integer type) throws Exception {
        TModel tm = tModelMapper.selectByPrimaryKey(modelId);
        model.addAttribute("model", tm);
        model.addAttribute("type", type);
        return "company/danger/model-auto";
    }

    /**
     * 计划 生成检查表
     */
    @RequestMapping(value = "plan-add")
    public String planAdd(Integer modelId, Model model) throws Exception {
        TModel tm = tModelMapper.selectByPrimaryKey(modelId);
        model.addAttribute("model", tm);
        return "company/danger/plan-add";
    }

    /**
     * 添加委托检查记录、行政检查记录 线下录入线上
     */
    @RequestMapping(value = "check-add3")
    public String checkAdd3(Model model, Integer flag) throws Exception {
        if (null == flag) {
            flag = 3;
        }
        model.addAttribute("flag", flag);
        return "company/danger/three-add";
    }

    /**
     * 添加委托检查记录 线下录入线上
     */
    @RequestMapping(value = "three-window")
    public String checkWindow(Model model, String index) throws Exception {
        model.addAttribute("index", index);
        return "company/danger/three-window";
    }

    /**
     * 添加委托检查记录 线下录入线上
     */
    @RequestMapping(value = "three-save")
    public @ResponseBody
    Result threeSave(@RequestBody ThreeSaveReqDTO dto, HttpServletRequest request)
            throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        dto.getCheck().setUserId(user.getId());
        dto.getCheck().setCreateUser(user.getId());
        try {
            cgfService.threeSave(dto);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "保存失败");
        }
        return result;
    }

    /**
     * 添加行政、委托检查表 flag 2 行政 3 委托
     */
    @RequestMapping(value = "check-edit")
    public String checkEdit(Integer id, HttpServletRequest request, Model model) throws Exception {
        TCheck m = tCheckMapper.selectByPrimaryKey(id);
        Company com = companyMapper.selectByPrimaryKey(getLoginUser(request).getId());
        model.addAttribute("company", com.getLegal());
        model.addAttribute("check", m);
        model.addAttribute("type", m.getType());
        model.addAttribute("flag", m.getFlag());
        model.addAttribute("industry", tIndustryMapper.selectByPrimaryKey(m.getIndustryId()));
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(id);
        StringBuffer ids = new StringBuffer();
        for (TCheckPart p : partL) {
            if (!"".equals(ids.toString())) {
                ids = ids.append(",");
            }
            ids = ids.append(p.getLevels());
        }
        model.addAttribute("partL", partL);
        model.addAttribute("levelL", tLevelMapper.selectByIds(ids.toString()));
        return "company/danger/check-add2";
    }

    /**
     * 计划 编辑检查表
     */
    @RequestMapping(value = "plan-edit")
    public String planEdit(Integer id, Model model) throws Exception {
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        TModel tm = tModelMapper.selectByPrimaryKey(tc.getModelId());
        model.addAttribute("model", tm);
        model.addAttribute("check", tc);
        return "company/danger/plan-edit";
    }

//    /**
//     * 检查表 详情
//     */
//    @RequestMapping(value = "check-detail")
//    public String checkDetail(Integer id, Model model) throws Exception {
//        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
//        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(id);
//        model.addAttribute("check", tc);
//        model.addAttribute("partL", partL);
//        model.addAttribute("itemL", tCheckItemMapper.selectByCheckId(id));
//        model.addAttribute("listM", tCheckMapper.selectCompany(id));
//        return "company/danger/plan-detail";
//    }

    /**
     * 检查表 详情
     * TODO 隐患排查记录/检查详情查看检查记录
     * 没有兼容小程序
     */
    @RequestMapping(value = "check-detail")
    public String checkDetail(Integer id, Model model, Integer jcxq, HttpServletRequest request, Integer flag, Integer number) throws Exception {
        User loginUser = getLoginUser(request);
        // 根据id查询的是检查表信息
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        Integer type = tc.getType();//5
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(id);
        String name = tc.getDapartContact();
        //设置名称
        /*if (null == tc.getDapartContact() || tc.getDapartContact().matches("[0-9]{1,}") || "".equals(name)) {
            // 表示没有被检查人员 根据部门名称获取这个部门的被检查人员然后随便抓一个
            List<Integer> integers = tCheckItemMapper.selectLevelIdByCheckId(id);
            if (null != integers && integers.size() > 0) {
                // 这里进行名称的获取,进行全部循环,获取数据的方式,在数据库中进行查询
                List<String> list = new ArrayList<String>();
                for (Integer integer : integers) {
                    if (null != integer) {
                        ACompanyManual aCompanyManual = aCompanyManualMapper.selectByPrimaryKey(integer);
                        if (null != aCompanyManual && null != aCompanyManual.getFjgkfzr()) {
                            list.add(aCompanyManual.getFjgkfzr());
                        }
                    }
                }
                if (list.size() == 0) {
                    //name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
                } else {
                    name = list.get(0);
                    tc.setCheckCompany(name);
                }
                //name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
            } else {
                //name = companyMapper.selectByPrimaryKey(tc.getUserId()).getSafety();
            }
        }*/
        model.addAttribute("partL", partL);
        List<Map<String, Object>> iteml = null;
        if (flag == 1) {
            iteml = tCheckMapper.selectLevels(id);
            for (int i = 0; i < iteml.size(); i++) {
                if (loginUser.getUserName().equals(iteml.get(i).get("depart"))) {
                    TCheck tCheck = tCheckMapper.selectByPrimaryKey(id);
                    if (tCheck.getIndustryType() == 1) { // 基础
                        iteml = tCheckMapper.selectAllLevel(id);
                        break;
                    } else if (tCheck.getIndustryType() != 1) { // 现场
                        iteml = tCheckMapper.selectAllDanger(id);
                        break;
                    }
                }
            }
        } else if (flag != 1) {
            // 根据 ID 查询对应的数据 是 基础 还是 现场
            TCheck tCheck = tCheckMapper.selectByPrimaryKey(id);
            if (tCheck.getIndustryType() == 1) { // 基础
                iteml = tCheckMapper.selectAllLevel(id);
            } else if (tCheck.getIndustryType() != 1) { // 现场
                iteml = tCheckMapper.selectAllDanger(id);
            }
        }
        if (type != null && type == 9) {
            for (Map<String, Object> a : iteml) {
                Integer[] ids = new Integer[1];
                ids[0] = (Integer) a.get("levelId");
                List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
                String dangertype = "";
                String factors = "";
                String measures = "";
                String level1 = "";
                String level2 = "";
                String level3 = "";
                for (ACompanyManual aa : rets) {
                    dangertype = aa.getType();
                    factors = aa.getFactors();
                    measures = aa.getMeasures();
                    level1 = aa.getLevel1();
                    level2 = aa.getLevel2();
                    level3 = aa.getLevel3();
                    break;
                }
                a.put("dangerType", dangertype);
                a.put("factors", factors);
                a.put("measures", measures);
                a.put("level1", level1);
                a.put("level2", level2);
                a.put("level3", level3);
            }
        }
        model.addAttribute("check", tc);
        model.addAttribute("flag", tc.getFlag());
        model.addAttribute("itemL", iteml);
        model.addAttribute("user", loginUser);
        if(null==number){
            Integer count = tCheckMapper.selectHiddenDangerNumber(id);
            if(null==count){
                count = 0;
            }
            model.addAttribute("number", count);
        }else{
            model.addAttribute("number", number);
        }
        Integer integer = tCheckMapper.selectHiddenDangerNumber2(id);
        /*if (null == name || "".equals(name)) {
            name = companyMapper.selectByPrimaryKey(loginUser.getId()).getSafety();
        }*/
        model.addAttribute("dangerInteger",integer);
        model.addAttribute("name", name);
        // 根据检查记录的id获取详细的信息
        if (null != tc.getLatitude() && null != tc.getLongitude()) {
            model.addAttribute("latitude", tc.getLatitude());
            model.addAttribute("longitude", tc.getLongitude());
        } else {
            model.addAttribute("latitude", null);
            model.addAttribute("longitude", null);
        }
        // 判断是否整改复查过
        TRectification tRectification = tRectificationMapper.selectByCheckId(id);
        if(null==tRectification){
            model.addAttribute("is_re",0);
        }else{
            model.addAttribute("is_re",1);
        }
        System.out.println(tCheckMapper.selectCompany(id));
        model.addAttribute("listM", tCheckMapper.selectCompany(id));
        model.addAttribute("companyName", loginUser.getUserName());

        return "company/danger/plan-detail";

    }

    /**
     * 查看隐患描述和图片
     */
    @RequestMapping(value = "danger-detail")
    public String dangerDetail(Integer id, Model model) throws Exception {
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        model.addAttribute("check", tc);
        model.addAttribute("itemL", tCheckItemMapper.selectByCheckId(id));
        return "company/danger/danger-detail";
    }

    /**
     * 检查表隐患汇总
     */
    @RequestMapping(value = "check-danger")
    public String checkDanger(Integer id, Model model) throws Exception {
        model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(id, null));
        return "company/danger/rectification-detail";
    }

    /**
     * 检查表隐患 整改
     */
    @RequestMapping(value = "check-rectification-print")
    public String checkRectificationPrint(Integer id, Model model, Integer flag) throws Exception {
        TRectification rectification = tRectificationMapper.selectByCheckId(id);
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);
        model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(id, null));
        model.addAttribute("rectification", rectification);
        model.addAttribute("company",
                companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
        model.addAttribute("now", null == rectification ? new Date() : rectification.getCreateTime());
        model.addAttribute("flag", flag);
        return "company/danger/print-opinion-detail";
    }

    /**
     * 检查表隐患 整改计划保存
     */
    @RequestMapping(value = "rectification-save")
    public @ResponseBody
    Result rectificationSave(TRectification tr, HttpServletRequest request) {
        Result result = new ResultImpl();
        try {
            tr.setCreateUser(getLoginUser(request).getId());
            cgfService.rectificationSave(tr);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "系统出错");
        }
        return result;
    }

    /**
     * 计划 生成检查表
     */
    @RequestMapping(value = "plan-save")
    public @ResponseBody
    Result planSave(TCheck check, HttpServletRequest request) {
        Result result = new ResultImpl();
        try {
            User user = getLoginUser(request);
            check.setUserId(user.getId());
            check.setCreateUser(user.getId());
            cgfService.checkSave(check);
            result.setMap("id", check.getId());
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "保存失败");
        }
        return result;
    }

    /**
     * 计划 生成检查表
     */
//    @RequestMapping(value = "plan-save2")
//    public @ResponseBody Result planSave2(Integer modelId,HttpServletRequest request) {
//        Result result = new ResultImpl();
//        try {
//            TModel model = tModelMapper.selectByPrimaryKey(modelId);
//            TCheck check = new TCheck();
//            check.setFlag(model.getFlag());
//            check.setTitle(model.getTitle());
//            check.setDepart(model.getPart());
//            check.setModelId(modelId);
//            check.setType(model.getType());
//            check.setIndustryId(model.getIndustryId());
//            check.setIndustryType(model.getIndustryType());
//            check.setStatus(1);
//            check.setDel(1);
//            check.setCreateTime(new Date());
//            User user = getLoginUser(request);
//            check.setUserId(user.getId());
//            check.setCreateUser(user.getId());
//            cgfService.checkSave(check);
//            result.setMap("id", check.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setStatus("1");
//            result.setMap("message", "保存失败");
//        }
//        return result;
//    }

    /**
     * TODO  实施检查
     *
     * @param modelId
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "plan-save2")
    public @ResponseBody
    Result planSave2(Integer modelId, Integer type, HttpServletRequest request) {
        Result result = new ResultImpl();
        try {
            TModel model = tModelMapper.selectByPrimaryKey(modelId);

            //log.error(model.toString());
            type = model.getType();// add wz 190110

            TCheck check = new TCheck();
            check.setFlag(model.getFlag());
            check.setTitle(model.getTitle());
            check.setDepart(model.getPart());
            check.setModelId(modelId);
            check.setType(model.getType());
            check.setIndustryId(model.getIndustryId());
            check.setIndustryType(model.getIndustryType());
            check.setStatus(1);
            check.setDel(1);
            check.setCreateTime(new Date());
            User user = getLoginUser(request);
            check.setUserId(user.getId());
            check.setCreateUser(user.getId());
            //log.error("planSave2 modelId:"+modelId+", type : "+type);//zhangcl 2018.10.29

            if (type != null && type == 9) {
                cgfService.checkSave1(check);

            } else {
                cgfService.checkSave(check);
            }
            log.error("planSave2:" + 6);//zhangcl 2018.10.29
            result.setMap("id", check.getId());
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "保存失败");
        }
        return result;
    }

    /**
     * 编辑检查表
     */
    @RequestMapping(value = "plan-edit-save")
    public @ResponseBody
    Result planEditSave(TCheck check, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        tCheckMapper.updateByPrimaryKeySelective(check);
        return result;
    }

//    /**
//     * 隐患列表 复查
//     */
//    @RequestMapping(value = "recheck-add")
//    public String recheckAdd(Integer checkId, Model model) throws Exception {
//        model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, 2));
//        Integer id = checkId;
//        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
//        model.addAttribute("check", check);
//        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
//        model.addAttribute("now", new Date());
//        return "company/danger/check-fuadd";
//    }

    /**
     * TODO  隐患列表 复查(实施复查)
     */
    @RequestMapping(value = "recheck-add")//修改 wz0114
    public String recheckAdd(Integer checkId, Model model) throws Exception {
        //model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, 2));
        TCheck tc = tCheckMapper.selectByPrimaryKey(checkId);
        Integer type = tc.getType();
        List<Map<String, Object>> iteml = tCheckItemMapper.selectDangerByCheckId(checkId, 2);
        //log.error("tCheckItemMapper条目结果信息:"+iteml.toString());
        if (type != null && type == 9) {
            for (Map<String, Object> a : iteml) {
                //log.error("checkNext:"+1);
                Integer[] ids = new Integer[1];
                ids[0] = (Integer) a.get("levelId");
                //log.error("ids:"+ids[0]);
                //log.error("a:"+a.toString());
                List<ACompanyManual> rets = aDangerManualMapper.selectByIds(ids);
                String dangertype = "";
                String factors = "";
                String measures = "";
                String level1 = "";
                String level2 = "";
                String level3 = "";
                for (ACompanyManual aa : rets) {
                    //log.error("checkNext:"+2);
                    dangertype = aa.getType();
                    factors = aa.getFactors();
                    measures = aa.getMeasures();
                    level1 = aa.getLevel1();
                    level2 = aa.getLevel2();
                    level3 = aa.getLevel3();
                    //log.error("type:"+dangertype);
                    break;
                }
                a.put("dangerType", dangertype);
                a.put("factors", factors);
                a.put("measures", measures);
                a.put("level1", level1);
                a.put("level2", level2);
                a.put("level3", level3);

            }
        }

        model.addAttribute("itemL", iteml);

        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
        model.addAttribute("now", new Date());

        if (type == 9) {
            return "company/danger/check-fuaddrjcb";
        } else {
            return "company/danger/check-fuadd";
        }
    }

    /**
     * 隐患列表 复查
     */
    @RequestMapping(value = "recheck-add-print")
    public String recheckAddPrint(Integer checkId, Model model) throws Exception {
        model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, 2));
        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
        model.addAttribute("now", new Date());
        return "company/danger/print-check-fudetail";
    }

    /**
     * TODO 隐患列表 复查详情
     * 现在复查有多余的记录
     * 表示的就是整改合格之后的复查记录
     * 表示在这里将数据进行保存到 tRecheckItemMapper
     * 这里也是需要改掉
     * 没有数据的
     */
    @RequestMapping(value = "recheck-detail")
    public String recheckDetail(Integer checkId, Model model, Integer flag, Integer number) throws Exception {
        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        List<Map> maps = tCheckItemMapper.selectAllByCheckId(checkId);
        List<TRecheck> tRechecks = tRecheckMapper.selectByCheckId(checkId);

        Integer id2 = tRechecks.get(0).getId();
        for (int i = 0; i < maps.size(); i++) {
            // 这边就不需要记性保存了 因为如果有数据直接查询然后在进行保存
            TRecheckItem id1 = tRecheckItemMapper.selectByCheckItemId((Integer) maps.get(i).get("id"));
            if (null != id1) {
                // 表示是新添加的数据不需要进行保存
            } else {
                id1 = new TRecheckItem();
                id1.setCheckItemId((Integer) maps.get(i).get("id"));
                id1.setStatus((Integer) maps.get(i).get("status"));
                id1.setFile((String) maps.get(i).get("recheckFile"));
                id1.setRecheckId(id2);
                id1.setMemo((String) maps.get(i).get("memo"));
                id1.setDeadline((Date) maps.get(i).get("deadline"));
                tRecheckItemMapper.insertSelective(id1);
            }
        }
        model.addAttribute("number", number);
        model.addAttribute("flag", flag);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));

        List<Map<String, Object>> maps1 = tRecheckItemMapper.selectByCheckId(checkId);
        model.addAttribute("recheckList", tRechecks);
        //model.addAttribute("recheckList", maps1);
        model.addAttribute("itemList", maps1);
        return "company/danger/check-fudetail";
    }

    /**
     * TODO  隐患列表 复查详情
     */
    @RequestMapping(value = "print-recheck-detail")
    public String precheckDetail(Integer checkId, Model model) throws Exception {
        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
        model.addAttribute("recheckList", tRecheckMapper.selectByCheckId(checkId));
        model.addAttribute("itemList", tRecheckItemMapper.selectByCheckId(checkId));
        return "company/danger/print-fudetail";
    }

    /**
     * 复查删除
     */
    @RequestMapping(value = "fu-del")
    public @ResponseBody
    Result fuDel(Integer id, HttpServletRequest request) {
        Result result = new ResultImpl();
        TRectification tr = new TRectification();
        tr.setId(id);
        tr.setDel(1);
        tRectificationMapper.updateByPrimaryKeySelective(tr);
        return result;
    }

    /**
     * 实施整改
     */
    @RequestMapping(value = "recheck-save")
    public @ResponseBody
    Result recheckSave(@RequestBody RecheckSaveReqDTO dto, HttpServletRequest request) throws ParseException {
        Result result = new ResultImpl();
        User user = getLoginUser(request);

        Date date = null;
        if (null == dto.getNextTime() || "".equals(dto.getNextTime())) {
            date = new Date();
        } else {
            String nextTime = dto.getNextTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = simpleDateFormat.parse(nextTime);
        }

        try {
            cgfService.recheckSave(dto, date);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "保存失败");
        }
        return result;
    }

    @InitBinder
    public void initData(WebDataBinder wdb) {
        wdb.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * 行政检查、委托 添加部位或装置
     */
    @RequestMapping(value = "check-part-add")
    public String checkPartAdd(Integer industryId, Model model, String partN, String levels, Integer index)
            throws Exception {
        List<TLevel> lL = tLevelMapper.selectByIndustry(industryId);// 检查级别
        model.addAttribute("levelL", gson.toJson(lL));
        if (StringUtils.isNotBlank(partN)) {
            model.addAttribute("levelIds", levels);
            model.addAttribute("index", index);
            model.addAttribute("partN", new String(partN.getBytes("ISO8859_1"), "utf-8"));
        }
        model.addAttribute("tIndustry", tIndustryMapper.selectByPrimaryKey(industryId));
        return "company/danger/check-window";
    }

    /**
     * 自查项目
     */
    @RequestMapping(value = "t-industry/{id}")
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
     * 摄像头list
     */
    @RequestMapping(value = "monitor-list")
    public String monitorList(HttpServletRequest request, Model model) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("userId", getLoginUser(request).getId());
        model.addAttribute("list", monitorMapper.selectByMap(m));
        return "company/system/monitor-list";
    }

    /**
     * 摄像头add
     */
    @RequestMapping(value = "monitor-add")
    public String monitorList(Model model) throws Exception {

        return "company/system/monitor-add";
    }
    @RequestMapping(value = "monitor-edit")
    public String monitorList2(Model model, Integer id) throws Exception {
        model.addAttribute("m", monitorMapper.selectByPrimaryKey(id));
        System.out.println("------------");
        return "company/system/monitor-edit";
    }

    /**
     * 执法检查添加
     */
    @RequestMapping(value = "check-add-cho")
    public String checkAddCho() throws Exception {
        return "company/danger/check-add-cho";
    }

    /**
     * TODO 摄像头 添加
     */
    @RequestMapping(value = "monitor-save")
    public @ResponseBody
    Result monitorSave(Monitor m, HttpServletRequest request) {
           Result result = new ResultImpl();

            m.setDel(0);
            m.setUserId(getLoginUser(request).getId());
            monitorMapper.insertSelective(m);
            return result;
    }

    @RequestMapping(value = "monitor-update")
    public @ResponseBody
    Result monitorSave2(Monitor m, HttpServletRequest request) {
        Result result = new ResultImpl();
        monitorMapper.updateByPrimaryKeySelective(m);
        return result;
    }
    /**
     * 摄像头 删除
     */
    @RequestMapping(value = "monitor-del")
    public @ResponseBody
    Result monitorDel(Integer id, HttpServletRequest request) {
        Result result = new ResultImpl();
        Monitor m = new Monitor();
        m.setId(id);
        m.setDel(1);
        monitorMapper.updateByPrimaryKeySelective(m);
        return result;
    }

    /**
     * 各类许可证页面
     */
    @RequestMapping("evaluate/zheng-list")
    public String zhengList(Model model, HttpServletRequest request, Standard standard, Integer isTime) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("type", standard.getType());
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM"));
        }
        model.addAttribute("list", standardMapper.selectList(m));
        model.addAttribute("type", standard.getType());
        model.addAttribute("isTime", isTime);
        return "company/evaluate/zheng-list";
    }

    /**
     * 添加各类许可证页面
     */
    @RequestMapping("evaluate/zheng-add")
    public String zhengAdd(Model model, HttpServletRequest request, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("standard", standardMapper.selectByPrimaryKey(id));
        }
        return "company/evaluate/zheng-add";
    }

    /**
     * 保存许可证页面
     */
    @RequestMapping("evaluate/zheng-save")
    public @ResponseBody
    Result zhengSave(Model model, HttpServletRequest request, Standard standard) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        standard.setUserId(user.getId());
        if (null == standard.getId()) {
            standardMapper.insertSelective(standard);
        } else {
            standardMapper.updateByPrimaryKeySelective(standard);
        }
        return result;
    }

    /**
     * 删除许可证页面
     */
    @RequestMapping("evaluate/zheng-del")
    public String zhengDel(Model model, HttpServletRequest request, Standard standard) throws Exception {
        standardMapper.deleteByPrimaryKey(standard.getId());
        return "company/evaluate/zheng-list";
    }

    /**
     * 安全设施“三同时”
     */
    @RequestMapping("evaluate/tab-she")
    public String zhengTabShe(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("isType", "50");
        model.addAttribute("list", table3mapper.selectTable(m));
        return "company/evaluate/tab-she";
    }

    /**
     * @throws Exception
     */
    @RequestMapping("evaluate/tab-she-add")
    public @ResponseBody
    Result zhengTabSheAdd(Model model, HttpServletRequest request, Table3 table3) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        table3.setUserId(user.getId());
        table3.setCreateTime(new Date());
        table3mapper.insertSelective(table3);
        return result;
    }

    /**
     * 隐患排查计划
     */
    @RequestMapping("danger/tab-plan")
    public String dangerList(Model model, HttpServletRequest request, Table3 table3, String seName) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("isType", "51");
        m.put("seName", seName);
        model.addAttribute("list", table3mapper.selectTable(m));
        model.addAttribute("seName", seName);
        return "company/danger/tab-plan";
    }

    /**
     * 应急救援页面
     */
    @RequestMapping(value = "tables/tab-yjlist")
    public String yjjyList(HttpServletRequest request, Model model, String companyName, String name, Integer isTime, Integer c) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        if (null != c && c.compareTo(1) == 0) {
            m.put("ownerId", user.getId());
        } else {
            setUserId(user, m);
        }
        m.put("name", name);
        m.put("companyName", companyName);
        if (null != isTime) {
            if (isTime > 0) {
                m.put("now", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            }
            m.put("isTime", DateFormatUtils.format(DateConvertUtil.addMonths(new Date(), isTime), "yyyy-MM-dd"));
        }
        model.addAttribute("list", tContingencyPlanMapper.selectTable(m));
        model.addAttribute("name", name);
        model.addAttribute("isTime", isTime);
        model.addAttribute("companyName", companyName);
        model.addAttribute("c", c);
        return "company/tables/tab-yjlist";
    }

    /**
     * 应急救援添加
     */
    @RequestMapping(value = "tables/tab-yjadd")
    public @ResponseBody
    Result yjjyAdd(HttpServletRequest request, Model model, TContingencyPlan tContingencyPlan) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        tContingencyPlan.setUserId(user.getId());
        tContingencyPlanMapper.insertSelective(tContingencyPlan);
        return result;
    }

    /**
     * 应急救援删除
     */
    @RequestMapping(value = "tables/tab-yjdel")
    public String yjjyDel(HttpServletRequest request, Model model, Integer id) throws Exception {
        tContingencyPlanMapper.deleteByPrimaryKey(id);
        return "company/tables/tab-yjlist";
    }

    /**
     * 应急预案演练页面
     */
    @RequestMapping(value = "tables/tab-yllist")
    public String yljyList(HttpServletRequest request, Model model, String name, Integer c) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        if (null != c && c.compareTo(1) == 0) {
            m.put("ownerId", user.getId());
        } else {
            setUserId(user, m);
        }
        m.put("name", name);
        model.addAttribute("list", tDrillMapper.selectTable(m));
        //log.error("list："+ tDrillMapper.selectTable(m));
        model.addAttribute("name", name);
        model.addAttribute("c", c);
        if (user.getUserType() == 5) {
            model.addAttribute("CompanyName", companyMapper.selectByPrimaryKey(user.getId()).getName());//应急预案演练所属企业显示企业名
        }
        return "company/tables/tab-yllist";
    }

    /**
     * 应急预案演练添加
     */
    @RequestMapping(value = "tables/tab-yladd")
    public @ResponseBody
    Result yljyAdd(HttpServletRequest request, Model model, TDrill tDrill) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        tDrill.setUserId(user.getId());
        tDrillMapper.insertSelective(tDrill);
        return result;
    }

    /**
     * 应急预案演练删除
     */
    @RequestMapping(value = "tables/tab-yldel")
    public String yljyDel(HttpServletRequest request, Model model, Integer id) throws Exception {
        tDrillMapper.deleteByPrimaryKey(id);
        return "company/tables/tab-yllist";
    }

    /**
     * 事故报告和处理页面
     */
    @RequestMapping(value = "tables/tab-shilist")
    public String shiList(HttpServletRequest request, Model model, String type) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("type", type);
        model.addAttribute("list", tAccidentMapper.selectTable(m));
        model.addAttribute("type", type);
        return "company/tables/tab-shilist";
    }

    /**
     * 事故报告和处理添加
     */
    @RequestMapping(value = "tables/tabshi-add")
    public String shiAdd(HttpServletRequest request, Model model, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("be", tAccidentMapper.selectByPrimaryKey(id));
        }
        return "company/tables/tabshi-add";
    }

    /**
     * 事故报告和处理保存
     */
    @RequestMapping(value = "tables/tabshi-save")
    public @ResponseBody
    Result shiSave(HttpServletRequest request, Model model, TAccident tAccident) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        tAccident.setUserId(user.getId());
        if (null == tAccident.getId()) {
            tAccidentMapper.insertSelective(tAccident);
        } else {
            tAccidentMapper.updateByPrimaryKey(tAccident);
        }
        return result;
    }

    /**
     * 事故报告和处理保存
     */
    @RequestMapping(value = "tables/tabshi-del")
    public String shiDel(HttpServletRequest request, Model model, Integer id) throws Exception {
        tAccidentMapper.deleteByPrimaryKey(id);
        return "company/tables/tab-shilist";
    }

    /**
     * 生产工艺流程页面列表
     */
    @RequestMapping("process/process-list")
    public String processList(Model model, HttpServletRequest request, String project) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("project", project);
        List<Map<String, Object>> list = productionProcessDiagramMapper.selectList(m);
        model.addAttribute("list", list);
        model.addAttribute("project", project);
        return "company/process/process-list";
    }

    /**
     * 生产工艺流程页面添加
     */
    @RequestMapping("process/process-edit")
    public String processEdit(Model model, HttpServletRequest request, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("be", productionProcessDiagramMapper.selectByPrimaryKey(id));
        }
        return "company/process/process-edit";
    }


    /*
     * 生产工艺流程页面 查看文档
     */
    @RequestMapping("process/lockAll")
    public String lockAll(Model model, HttpServletRequest request, Integer id) throws Exception {
        if (null != id) {

            List<ProductionProcessDiagram> list = productionProcessDiagramMapper.selectAddress(id);

            for (int i = 0; i < list.size(); i++) {

                model.addAttribute("list", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + list.get(i).getDocUrl());

                return "company/process/process-see";
            }
        }
        return "company/process/process-see";
    }


    /**
     * 生产工艺流程页面保存
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
     * 生产工艺流程页面删除
     */
    @RequestMapping("process/process-del")
    public @ResponseBody
    Result processDel(Integer id) {
        Result result = new ResultImpl();
        productionProcessDiagramMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 防雷防静电设施页面列表
     */
    @RequestMapping("product/lightning-list")
    public String lightningList(Model model, HttpServletRequest request, String project) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>(16);
        setUserId(user, m);
        m.put("project", project);
        List<Map<String, Object>> list = lightningProtectionMapper.selectList(m);
        model.addAttribute("list", list);
        model.addAttribute("project", project);
        return "company/product/lightning-list";
    }

    /**
     * 防雷防静电设施页面添加
     */
    @RequestMapping("product/lightning-edit")
    public String lightningEdit(Model model, HttpServletRequest request, Integer id) throws Exception {
        if (null != id) {
            model.addAttribute("be", lightningProtectionMapper.selectByPrimaryKey(id));
        }
        return "company/product/lightning-edit";
    }


    /**
     * 个人职业健康监护
     */
    @RequestMapping("cyryjkjh")
    public String cyryjkjh(Model model, HttpServletRequest request) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        User user = getLoginUser(request);
        setUserId(user, m);
        model.addAttribute("list", cyryjkjhMapper.selectList(m));
        return "company/evaluate/cyryjkjh-list";
    }

    /**
     * 视频监控
     */
    @RequestMapping("monitor-show")
    public String monitorShow(Model model, Integer id) throws Exception {
        model.addAttribute("monitor", monitorMapper.selectByPrimaryKey(id));
        //System.out.println("ss"+monitorMapper.selectByPrimaryKey(id).getPuid());
        model.addAttribute("puid", monitorMapper.selectByPrimaryKey(id).getPuid());
        model.addAttribute("code", monitorMapper.selectByPrimaryKey(id).getCode());
        return "company/system/monitor-show";
    }

    /**
     * 防雷防静电设施页面保存
     */
    @RequestMapping("product/lightning-save")
    public @ResponseBody
    Result lightningSave(HttpServletRequest request, LightningProtection lightningProtection) {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        lightningProtection.setUserId(user.getId());
        if (null == lightningProtection.getId()) {
            lightningProtectionMapper.insertSelective(lightningProtection);
        } else {
            lightningProtectionMapper.updateByPrimaryKey(lightningProtection);
        }
        return result;
    }

    /**
     * 防雷防静电设施页面删除
     */
    @RequestMapping("product/lightning-del")
    public @ResponseBody
    Result lightningDel(Integer id) {
        Result result = new ResultImpl();
        lightningProtectionMapper.deleteByPrimaryKey(id);
        return result;
    }

    /**
     * 防雷防静电文件上传
     * 获取到名称然后保存到线上,然后将将文件导入到数据库
     * @param file  文件
     * @param model  model
     * @param request 请求
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "uoloadLightning", produces = "text/html;charset=utf-8")
    public void uoloadLightning(MultipartFile file,Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        userService.importLightning(result, user.getId(), file);

        writeResponse(result, response);//该方法调用如下
    }

    /**
     * 安全设备登记台账上传
     * 获取到名称然后保存到线上,然后将将文件导入到数据库
     * @param file  文件
     * @param model  model
     * @param request 请求
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "uoloadSafetyfacilities", produces = "text/html;charset=utf-8")
    public void uoloadSafetyfacilities(MultipartFile file,Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        userService.uoloadSafetyfacilities(result, user.getId(), file);
        writeResponse(result, response);//该方法调用如下
    }

    /**
     * 隐患库页面
     */
    @RequestMapping(value = "trouble/trouble-information-list")
    public String trInfoList(HttpServletRequest request, Model model) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        model.addAttribute("list", table6Mapper.selectTable(m));
        return "company/trouble/trouble-information-list";
    }

    /**
     * 企业列表
     */
    @RequestMapping(value = "company/company-list")
    public String companyList(Model model, HttpServletRequest request,
                              Integer danger, Integer hazard) throws Exception {
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("companyName", user.getUserName());
        m.put("danger", danger);
        m.put("hazard", hazard);
        List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyList(m);
        model.addAttribute("list", mlist);
        if (danger != null) {
            return "company/company-list-gwzy";
        }
        return "company/company-list-zdwxy";
    }

    /**
     * 根据name获取企业列表
     */
    @RequestMapping(value = "company-list-name", method = RequestMethod.POST)
    public @ResponseBody
    Result companyListName(String companyName) {
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("companyName", companyName);
        List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyMap(m);
        result.setMap("list", mlist);
        return result;
    }

    /**
     * 视频调试
     */
    @RequestMapping(value = "videodebug")
    public String videoDebug() {
        return "company/videodebug";
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
     * TODO 下载资料
     * 并转换成为utf-8格式的数据
     *
     */
    @RequestMapping(value = "download")
    public void hedownload(String filename, String fileurl, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //log.error("request.getServletContext():"+request.getServletContext());
        //log.error("request.getServletContext().getRealPath('/'):"+request.getServletContext().getRealPath("/"));
        //log.error("fileurl.replace:"+fileurl.replace("/smaq", ""));
        File realPath = new File(request.getServletContext().getRealPath("/"), fileurl.replace("/smaq", ""));
        //log.error("realPath:"+realPath.toString());
        //log.error("realPath:"+realPath);
        if (!realPath.exists()) {
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
     * 判断行业
     */
    @ResponseBody
    @RequestMapping(value = "judgeIndustry")
    public Result judgeIndustry(HttpServletRequest request) {
        User user = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (company.getIndustry().contains("化工")) {
            Result result = new ResultImpl();
            result.setStatus("0");
            result.setMess("化工行业");
            return result;
        } else {
            Result result = new ResultImpl();
            result.setStatus("1");
            return result;
        }

    }
    /**
     * 更新重大危险源
     */
    @ResponseBody
    @RequestMapping("updateHazard")
    public Result updateHazard(HttpServletRequest request, Integer hazard){
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Company company = new Company();
        company.setUserId(user.getId());
        company.setHazard(hazard);
        int flag = companyMapper.updateByPrimaryKeySelective(company);
        if (flag==1){
            result.setStatus("1");
        }else{
            result.setStatus("0");
        }
         return result;
    }

    /**
     * create by  : 小明！！！
     * description: TODO
     * create time: 2019/8/12 10:48
     */
    @RequestMapping(value = "fuchadetail")
    public String fuchadetail(HttpServletRequest request,Model model,Integer checkId, Integer flag, Integer number) {
        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        List<Map> maps = tCheckItemMapper.selectAllByCheckId(checkId);
        List<TRecheck> tRechecks = tRecheckMapper.selectByCheckId(checkId);

        Integer id2 = tRechecks.get(0).getId();
        for (int i = 0; i < maps.size(); i++) {
            // 这边就不需要记性保存了 因为如果有数据直接查询然后在进行保存
            TRecheckItem id1 = tRecheckItemMapper.selectByCheckItemId((Integer) maps.get(i).get("id"));
            if (null != id1) {
                // 表示是新添加的数据不需要进行保存
            } else {
                id1 = new TRecheckItem();
                id1.setCheckItemId((Integer) maps.get(i).get("id"));
                id1.setStatus((Integer) maps.get(i).get("status"));
                id1.setFile((String) maps.get(i).get("recheckFile"));
                id1.setRecheckId(id2);
                id1.setMemo((String) maps.get(i).get("memo"));
                id1.setDeadline((Date) maps.get(i).get("deadline"));
                tRecheckItemMapper.insertSelective(id1);
            }
        }
        model.addAttribute("number", number);
        model.addAttribute("flag", flag);
        model.addAttribute("checkId", checkId);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));

        List<Map<String, Object>> maps1 = tRecheckItemMapper.selectByCheckId(checkId);
        model.addAttribute("recheckList", tRechecks);
        //model.addAttribute("recheckList", maps1);
        model.addAttribute("itemList", maps1);
        return "company/danger/fuchadetail";
    }

}
