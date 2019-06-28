package com.spring.web.controller;

import com.spring.web.BaseController;
import com.spring.web.dao.TSafetyMapper;
import com.spring.web.model.Company;
import com.spring.web.model.TSafety;
import com.spring.web.model.TSafetyStandard;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * TODO (安全生产标准话实体类)
 *
 * @author 桃红梨白
 * @Date 2019/6/26 16:02
 */
@Controller
@RequestMapping("api/safety_Standard")
public class TSafetyStandardController extends BaseController {

    private static final long serialVersionUID = 6784473267577273720L;

    @Autowired
    private TSafetyMapper tSafetyMapper;

    /**
     * 根据条件查询安全生产标准化数据
     *
     * @param parendId 父级id
     * @param flag     1: A级  2: B级
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String findAll(Integer parendId, Integer flag, HttpServletRequest request, Model model, Integer sort) {
        User user = getLoginUser(request);
        if (null == sort) {
            sort = 1;
        }
        HashMap<String, Object> map = new HashMap<>();

        map.put("userId", user.getId());
        map.put("parentId", parendId);
        map.put("flag", flag);
        map.put("sort", sort);

        List<TSafetyStandard> TSafetyStandardlist = tSafetyStandardMapper.findAll(map);

        // 判断是否有顺序,有书序就按照顺序来,没有就是倒序
        model.addAttribute("sort", sort);
        model.addAttribute("list", TSafetyStandardlist);
        return "company/tables/tab-biaozhun2";

    }

    /**
     * 根据A级元素id查询B级元素
     *
     * @return
     */
    @RequestMapping(value = "/findByParentId")
    public String findByParentId(Integer safetyStandardlistId, Model model, Integer sort) {
        if (null == sort) {
            sort = 1;
        }
        List<TSafetyStandard> TSafetyStandard = tSafetyStandardMapper.findByparentId(safetyStandardlistId, sort);
        model.addAttribute("list", TSafetyStandard);
        model.addAttribute("parentId", safetyStandardlistId);
        model.addAttribute("sort", sort);
        return "company/tables/tab-biaozhunB";
    }

    /**
     * 根据 id查询项目信息 并调转动修改的页面
     *  普通的跳转页面,没有请求
     * @param safetyStandardlistId
     * @return
     */
    @RequestMapping(value = "/findOne")
    public String findOne(Integer safetyStandardlistId,Model model) {

        TSafetyStandard TSafetyStandard = tSafetyStandardMapper.findOne(safetyStandardlistId);
        model.addAttribute("item",TSafetyStandard);
        return "company/tables/tab-detail";
    }

    /**
     * 保存插入数据
     *
     * @param tSafetyStandard
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody
    Result save(@RequestBody TSafetyStandard tSafetyStandard, HttpServletRequest request) {
        Result result = new ResultImpl();
        User loginUser = getLoginUser(request);
        Company company = companyMapper.selectByPrimaryKey(loginUser.getId());
        try {

            String industry = company.getIndustry();
            if (industry.indexOf("化工") != -1) {
                tSafetyStandard.setIndustryId(1);
            } else {
                tSafetyStandard.setIndustryId(2);
            }
            tSafetyStandard.setUserId(loginUser.getId());
            if (tSafetyStandard.getParentId() == 0) {
                tSafetyStandard.setFlag(1);
            } else {
                tSafetyStandard.setFlag(2);
            }

            tSafetyStandard.setDel(0);
            tSafetyStandardMapper.insertSelective(tSafetyStandard);

            result.setMess("保存成功");
            result.setStatus("0");
            result.setObject(tSafetyStandard.getId());
            return result;
        } catch (Exception e) {
            result.setMess("保存失败");
            result.setStatus("1");
            result.setObject(tSafetyStandard.getId());
            return result;
        }

    }

    /**
     * 修改数据
     *
     * @param tSafetyStandard
     * @return
     */
    @RequestMapping(value = "/update-tSafetyStandard", method = RequestMethod.POST)
    public @ResponseBody
    Result updateTSafetyStandard(@RequestBody TSafetyStandard tSafetyStandard) {
        Result result = new ResultImpl();

        try {
            tSafetyStandardMapper.updateTSafetyStandard(tSafetyStandard);
            result.setStatus("0");
            result.setMess("修改成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("修改失败");
            return result;
        }
    }

    /**
     * 删除要素,要判断是否有这个要素点,第二这个要素点的子id有没有数据,有的话让其将子要素删除,然后才能删除
     *
     * @param safetyStandardlistId
     * @return
     */
    @RequestMapping(value = "/delete-tSafetyStandard", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteTSafetyStandard(Integer safetyStandardlistId) {
        Result result = new ResultImpl();
        try {
            List<TSafetyStandard> TSafetyStandard = tSafetyStandardMapper.findByparentId(safetyStandardlistId, null);
            if (null != TSafetyStandard && TSafetyStandard.size() > 0) {
                result.setStatus("1");
                result.setMess("该数据存在下级要素点,请删除完后在进行删除");
                return result;
            }
            tSafetyStandardMapper.deleteTSafetyStandard(safetyStandardlistId);
            result.setStatus("0");
            result.setMess("删除成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("未成功删除,请重试!");
            return result;
        }
    }

    /**
     * 自动导入功能
     * 首先判断这个公司类型
     * <p>
     * 先根据名称进行判断,是否包含化工企业,
     * 两种企业, 工贸/化工
     *
     * @return
     */
    @RequestMapping("/Automatic-import")
    @SuppressWarnings("all")
    @ResponseBody
    public Result automaticImport(HttpServletRequest request) {
        Result result = new ResultImpl();
        try {
            User user = getLoginUser(request);// 获取公司名称
            Company company = companyMapper.selectByPrimaryKey(user.getId());

            String industry = company.getIndustry();
            Integer industryType = null;
            if (industry.indexOf("化工") != -1) {
                industryType = 1; // 危化企业
            } else {
                industryType = 2; // 工贸企业
            }
            // 1. 查询关于该行业的A级要素
            List<TSafety> tSafetyList = tSafetyMapper.selectAByIndustryType(industryType, 0);
            for (TSafety tSafety : tSafetyList) {
                //先插入A级要素
                TSafetyStandard tSafetyStandard = new TSafetyStandard();
                tSafetyStandard.setUserId(user.getId());   // 公司id
                tSafetyStandard.setName(tSafety.getName());// 要素名称
                tSafetyStandard.setDel(0); //表示未删除
                tSafetyStandard.setIndustryId(industryType); // 行业类型 1. 危化  2. 工贸
                tSafetyStandard.setFlag(1); // A级要素
                tSafetyStandard.setParentId(0);// 表示未删除
                tSafetyStandardMapper.insertSelective(tSafetyStandard);
                Integer tSafetyStandardId = tSafetyStandard.getId(); // 获取插入的A级要素id
                List<TSafety> list = tSafetyMapper.selectBBytSafetyStandardId(tSafety.getId());
                for (TSafety safety : list) {
                    TSafetyStandard tSafetyStandard1 = new TSafetyStandard();
                    tSafetyStandard1.setUserId(user.getId());   // 公司id
                    tSafetyStandard1.setName(safety.getName()); // 要素名称
                    tSafetyStandard1.setIndustryId(industryType); // 行业类型 1. 危化 2 工贸
                    tSafetyStandard1.setParentId(tSafetyStandardId); // 对应的A类要素id
                    tSafetyStandard1.setFlag(2); // b级要素
                    tSafetyStandard1.setContent(safety.getContent()); // 内容
                    tSafetyStandard1.setDel(0); //表示未删除
                    tSafetyStandardMapper.insertSelective(tSafetyStandard1);
                }

            }
            result.setStatus("0");
            result.setMess("插入成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("1");
            result.setMess("插入失败");
            return result;
        }
    }

    /**
     * 自动导入文本文件数据进行保存
     */
    @RequestMapping("/import-table")
    @ResponseBody
    public Result importTable(@RequestBody TSafety tSafety) {
        Result result = new ResultImpl();
        tSafetyMapper.insertTSafety(tSafety);
        //判断该公司类型
        result.setObject(tSafety.getId());
        return result;
    }


}
