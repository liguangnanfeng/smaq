/**
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd.
 * All right reserved.
 */
package com.spring.web.controller;

import com.alibaba.fastjson.JSON;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.*;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public String welcome(Model model, HttpServletRequest request) throws Exception {
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

        }

        return "company/welcome";
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
     * TODO 企业端 前台首页 如果是化工行业的内容就显示 研判承诺公告/重大危险源长制
     *
     * @throws Exception
     */
    @RequestMapping("main")
    public String main(Model model, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);

        model.addAttribute("userName", companyMapper.selectByPrimaryKey(user.getId()).getName());
        model.addAttribute("loginUserId", user.getId());
        return "company/main";
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
                company.setHazard(regulationComp.getCisDanger());
                companyMapper.updateByPrimaryKey(company);
            }
        } else {
            if (regulationComp.getCisDanger() == null) {
                company.setHazard(0);
            } else {
                company.setHazard(regulationComp.getCisDanger());
            }
            companyMapper.updateByPrimaryKey(company);
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
        log.error("data：" + userMapper.selectByPrimaryKey(user.getId()));
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
    @RequestMapping("getBasicInformation-qcc")
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
        String url = "http://i.yjapi.com/ECIV4/GetDetailsByName";
        String key = "30871bb9213d45489680e4e7ec48c52e";
        String queryString = "key=" + key + "&keyword=" + companyName;
        String data = WeixinUtil.doGet(url, queryString, "UTF-8", false);
        qccData = new QccData();
        qccData.setCompanyName(companyName);
        qccData.setStatus(0);
        qccData.setData(data);
        qccDataMapper.insertSelective(qccData);
        // Gson gosn = new Gson();
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
    public void importSequipmentExcel(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        userService.importSequipmentExcel(result, user.getId(), file);
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

    static String PP = "yyyy-MM";

    /**
     * 折线图数据 根据隐患类型 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "lineChartData")
    public @ResponseBody
    Result lineChartData(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
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
            eT = DateFormatUtils.format(d, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
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
        String[] xx = new String[]{"一般", "重大"};
        List<Map<String, Object>> mm = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        m1.put("name", xx[0]);
        m2.put("name", xx[1]);
        m1.put("data", d.clone());
        m2.put("data", d.clone());
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
        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    /**
     * 柱状图数据 根据隐患整改 sT 起始时间 eT 终止时间
     */
    @RequestMapping(value = "zhuChartData")
    public @ResponseBody
    Result zhuChartData(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -4), PP);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -4), PP);
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
        String[] xx = new String[]{"隐患条数", "整改项数"};
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
    Result zhuChartData2(String sT, String eT, HttpServletRequest request) throws Exception {
        User user = getLoginUser(request);
        Result result = new ResultImpl();
        if (StringUtils.isEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
        }
        if (StringUtils.isNotEmpty(sT) && StringUtils.isEmpty(eT)) {
            Date d = new Date();
            eT = DateFormatUtils.format(d, PP);
        }
        if (StringUtils.isEmpty(sT) && StringUtils.isNotEmpty(eT)) {
            Date d = DateConvertUtil.formateDate(eT, PP);
            sT = DateFormatUtils.format(DateConvertUtil.addMonths(d, -11), PP);
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
        String[] xx = new String[]{"企业自查", "委托检查", "行政执法", "行政检查"};
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
        mm.add(m4);
        result.setMap("categories", monthL);
        result.setMap("series", mm);// List<Data{String name; Integer[] data}> Data
        return result;
    }

    List<String> monthB(String sT, String eT) throws Exception {
        List<String> l = new LinkedList<String>();
        Date s = DateConvertUtil.formateDate(sT, PP);
        Date e = DateConvertUtil.formateDate(eT, PP);
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
    Result checkItemSave(TCheckItem tci, HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        tCheckItemMapper.updateByPrimaryKeySelective(tci);
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
        try {
            cgfService.checkNestSave(t);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMap("message", "保存失败");
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
     * TODO 查询检查表详情(已修改)
     *
     * @param id    modelId
     * @param model 前端model
     * @param flag  类型
     * @param type  数据
     * @return url地址
     * 可以从ite表中获取的详情
     * @throws Exception
     */
    @RequestMapping(value = "model-show/{id}")//modify by zhangcl 2018.10.28
    public String modelShow(@PathVariable Integer id, Model model, Integer flag, Integer type) throws Exception {
        log.error("type:" + type);
        TModel tc = tModelMapper.selectByPrimaryKey(id);
        List<TModelPart> partL = tModelPartMapper.selectByModelId(id);
        model.addAttribute("model", tc);
        model.addAttribute("partL", partL);
        StringBuffer levelIds = new StringBuffer();
        // 第一次我要将里面的数据便利出来获取唯一的值
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
        //要对其进行判断,是否为现场/基础
        if (type != null && tc.getIndustryType() <= 2) {
            List<Map<String, Object>> iteml = new ArrayList<Map<String, Object>>();
            String[] levelsArr = levelIds.toString().split(",");

            for (int i = 0; i < levelsArr.length; i++) {
                Map<String, Object> a = new HashMap<String, Object>();
                if (!"null".equals(levelsArr[i]) && null != levelsArr[i]) {
                    int i1 = Integer.parseInt(levelsArr[i]);
                    ACompanyManual companyManual = aCompanyManualMapper.selectByPrimaryKey(i1);
                    if (null == companyManual) {
                        break;
                    }
                    a.put("levelId", Integer.parseInt(levelsArr[i]));
                    a.put("dangerType", companyManual.getType());
                    a.put("factors", companyManual.getFactors());
                    a.put("measures", companyManual.getMeasures());
                    log.info("a:" + a.toString());
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
                    log.info("a:" + a.toString());
                    iteml.add(a);
                }

            }

            model.addAttribute("itemL", iteml);
        } else {
            model.addAttribute("itemL", tItemMapper.selectByLevelIdsModel(levelIds.toString()));
        }

        model.addAttribute("now", new Date());
        model.addAttribute("flag", flag);
        if (type != null && tc.getIndustryType() <= 2) {
            log.error("company/danger/model-show1");
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
//    /**
//     * 检查设置与实施-企业自查1
//     */
//    @RequestMapping(value = "model-list-cx")
//    public String modelList1(Integer type, Integer flag, String title, Integer industryType, HttpServletRequest request,
//            Model model) throws Exception {
//        User user = getLoginUser(request); 
//        
//        model.addAttribute("type", type);
//        model.addAttribute("flag", flag);
//        model.addAttribute("industryType", industryType);
//        
//        Map<String, Object> m = new HashMap<String, Object>();
//        //log.error("type数据类型："+type.getClass());
//        m.put("type", type);
//        m.put("title", title);
//        m.put("flag", flag);
//        m.put("industryType", industryType);
//        setUserId(user, m);    
//        model.addAttribute("list", tModelMapper.selectByMap(m));
//
//        return "company/danger/model-list-cx";
//    }

    /**
     * 检查设置与实施-企业自查1
     * TODO
     * 综合检查表(Template 1)
     * 日检查表(Template 2)
     * 临时检查表(template 4)
     * type  =1(日常)  flag=1()自查
     */
    @RequestMapping(value = "model-list-cx")
    public String modelList1(Integer type, Integer flag, String title, Integer industryType, HttpServletRequest request,
                             Model model, Integer template) throws Exception {
        User user = getLoginUser(request);

        model.addAttribute("type", type);
        model.addAttribute("flag", flag);
        model.addAttribute("industryType", industryType);
        model.addAttribute("template", template);
        Map<String, Object> m = new HashMap<String, Object>();
        //log.error("type数据类型："+type.getClass());
        m.put("type", type);
        m.put("title", title);
        m.put("flag", flag);
        m.put("industryType", industryType);
        setUserId(user, m);
        //log.error("m："+m.toString());
        //log.error("定期检查表type=2："+tModelMapper.selectByMap(m).get(0));
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
        //log.error("监管行业："+ company.getIndustry());
        if (null != company.getHazard()) {
            if (company.getHazard() == 1 || company.getIndustry().trim().equals("化工企业（危险化学品生产、经营、使用）、加油站")) {//显示效果需要
                //log.error("监管行业："+company.getIndustry());
                //log.error("是否构成重大危险源、1是："+company.getHazard());
                model.addAttribute("rjcbxs", 1);

            }
        }
        /*if (type == 2) {

            if (company.getHazard() == 1 || company.getIndustry().trim().equals("化工企业（危险化学品生产、经营、使用）、加油站")) {//取数据
                //log.error("查询出日检查表和定期检查表检查周期不为1表单！");
                List<Map<String, Object>> lista = tModelMapper.selectByMap2(m);//定期检查表——检查周期不为1
                Integer type9 = 9;
                m.put("type", type9);
                List<Map<String, Object>> listb = tModelMapper.selectByMap2(m);
                lista.addAll(listb);
                model.addAttribute("list", lista);
                return "company/danger/model-list-cx";

            } else {
                //查询日检查表 wz 190109
                Integer type9 = 9;
                m.put("type", type9);
                List<Map<String, Object>> list1 = tModelMapper.selectByMap(m);
                list.addAll(list1);
            }
        }*/
        model.addAttribute("list", list);

        return "company/danger/model-list-cx";
    }

    /**
     * 检查设置与实施-企业自查1-日检查表 wz 190109
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
     * TODO 临时检查表
     * 在model表查询获取数据
     */
    @RequestMapping(value = "check-LinShi")
    public String modelLinShi(HttpServletRequest request, String title, Integer type, String companyName,
                              Integer status, Integer flag, Model model) throws ParseException {
        User user = getLoginUser(request);
        //企业类型为 化工 或 构成重大危险源 则企业自查处 日检查表显示 wz 190108
        Company company = companyMapper.selectByPrimaryKey(user.getId());
        if (company.getHazard() == 1 || company.getIndustry().trim().equals("化工企业（危险化学品生产、经营、使用）、加油站")) {
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
        if (company.getHazard() == 1 || company.getIndustry().trim().equals("化工企业（危险化学品生产、经营、使用）、加油站")) {
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
     * 企业自查添加模板
     */
//    @RequestMapping(value = "model-add")
//    public String modelAdd(Integer type, Integer flag, Integer industryId, HttpServletRequest request, Model model)
//            throws Exception {
//        User user = getLoginUser(request);
//        TCompany tc = tCompanyMapper.selectByPrimaryKey(user.getId());
//        model.addAttribute("type", type);
//        model.addAttribute("flag", flag);
//        if (null == tc) {
//            return "redirect:/village/trouble-set?url="
//                    + URLEncoder.encode("/company/model-add?type=" + type + "&flag=" + flag, "utf-8");
//        }
//        model.addAttribute("ind1", tIndustryMapper.selectByPrimaryKey(tc.getIndustry1()));// 基础检查类别
//        if (StringUtils.isNotBlank(tc.getIndustry2())) {
//            model.addAttribute("ind2L", tIndustryMapper.selectByIds(tc.getIndustry2()));// 现场检查类别
//        }
//        if (StringUtils.isNotBlank(tc.getIndustry3())) {
//            model.addAttribute("ind3L", tIndustryMapper.selectByIds(tc.getIndustry3()));// 高危检查类别
//        }
//        return "company/danger/model-add2";
//    }
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
     * TODO 查看检查记录
     */
    @RequestMapping(value = "check-detail")
    public String checkDetail(Integer id, Model model, Integer jcxq) throws Exception {
        // 根据id查询的是检查表信息
        TCheck tc = tCheckMapper.selectByPrimaryKey(id);
        Integer type = tc.getType();
        //log.error("检查表type："+type);
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(id);
        model.addAttribute("check", tc);
        model.addAttribute("partL", partL);
        //model.addAttribute("itemL", tCheckItemMapper.selectByCheckId(id));
        List<Map<String, Object>> iteml = tCheckItemMapper.selectByCheckId(id);
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
                //log.error("level1/2/3 : "+level1+"/"+level2+"/"+level3);
            }
        }
        //log.error("tCheckItemMapper条目结果信息2:"+iteml.toString());
        model.addAttribute("itemL", iteml);
        model.addAttribute("listM", tCheckMapper.selectCompany(id));
        log.error("检查表 详情check-detail" + type);

        log.error("检查详情：" + jcxq);//首页——定期检查——检查详情显示为未检查
        if (jcxq == null) {
            if (type == 9) {
                return "company/danger/plan-detailrjcb";
            } else {
                return "company/danger/plan-detail";
            }
        } else {
            if (type == 9) {
                return "company/danger/plan-detailrjcbjcxq";
            } else {
                return "company/danger/plan-detailjcxq";
            }
        }


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
            if (type != null && type == 9)
                cgfService.checkSave1(check);
            else
                cgfService.checkSave(check);
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
     * 隐患列表 复查
     */
    @RequestMapping(value = "recheck-add")//修改 wz0114
    public String recheckAdd(Integer checkId, Model model) throws Exception {
        //model.addAttribute("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, 2));
        TCheck tc = tCheckMapper.selectByPrimaryKey(checkId);
        Integer type = tc.getType();
        //log.error("检查表type："+type);
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
                //log.error("level1/2/3 : "+level1+"/"+level2+"/"+level3);
            }
        }
        //log.error("tCheckItemMapper条目结果信息2:"+iteml.toString());
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
     * 隐患列表 复查详情
     */
    @RequestMapping(value = "recheck-detail")
    public String recheckDetail(Integer checkId, Model model) throws Exception {
        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        model.addAttribute("check", check);
        model.addAttribute("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
        model.addAttribute("recheckList", tRecheckMapper.selectByCheckId(checkId));
        model.addAttribute("itemList", tRecheckItemMapper.selectByCheckId(checkId));
        return "company/danger/check-fudetail";
    }

    /**
     * 隐患列表 复查详情
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
     * 编辑检查表
     */
    @RequestMapping(value = "recheck-save")
    public @ResponseBody
    Result recheckSave(@RequestBody RecheckSaveReqDTO dto, HttpServletRequest request) {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        dto.getRecheck().setCreateUser(user.getId());
        try {
            cgfService.recheckSave(dto);
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
    public String monitorList(Integer id, Model model) throws Exception {
        if (null != id) {
            model.addAttribute("m", monitorMapper.selectByPrimaryKey(id));
        }
        return "company/system/monitor-add";
    }

    /**
     * 执法检查添加
     */
    @RequestMapping(value = "check-add-cho")
    public String checkAddCho() throws Exception {
        return "company/danger/check-add-cho";
    }

    /**
     * 摄像头 删除
     */
    @RequestMapping(value = "monitor-save")
    public @ResponseBody
    Result monitorSave(Monitor m, HttpServletRequest request) {
        Result result = new ResultImpl();
        if (null == m.getId()) {
            m.setDel(0);
            m.setUserId(getLoginUser(request).getId());
            monitorMapper.insertSelective(m);
        } else {
            monitorMapper.updateByPrimaryKeySelective(m);
        }
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
        Map<String, Object> m = new HashMap<String, Object>();
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
     * 下载资料
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
}
