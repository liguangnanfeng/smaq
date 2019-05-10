package com.spring.web.controller.api;

import com.spring.web.dao.ZzjgDepartmentMapper;
import com.spring.web.dao.ZzjgPersonnelMapper;
import com.spring.web.listener.MySessionContext;
import com.spring.web.model.Officials;
import com.spring.web.model.ZzjgDepartment;
import com.spring.web.model.ZzjgPersonnel;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.SaveDataMessageItem;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.CheckCompany.CountryCheck;
import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.CheckCompany.SaveMessageService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author: 桃红梨白
 * @Date: 2019/05/05 19:26
 * 行政检查,
 * 编号从220开始
 * 首先要判断政府人员的权限 获取所在的usertype : 然后根据usertype根据数据进行保存
 * 获取所在的地区和地区所有的企业,获取企业的风险点
 * <p>
 * 根据行业,获取该行业所有的风险点
 */

@Controller
@RequestMapping(value = "api/country/check")
@SuppressWarnings("all")
public class AppController_Country_Check {

    /**
     * token验证
     */
    @Autowired
    private AppTokenData appTokenData;

    /**
     * 政府端查询数据
     */
    @Autowired
    private CountryCheck countryCheck;

    /**
     * 查询所有的部门
     */
    @Autowired
    private ZzjgDepartmentMapper zzjgDepartmentMapper;

    /**
     * 查询的方法
     */
    @Autowired
    private ICheckManual checkManual;

    /**
     * 安全责任人
     */
    @Autowired
    private ZzjgPersonnelMapper zzjgPersonnelMapper;

    /**
     * 查询模版的详细信息
     */
    @Autowired
    private SaveMessageService saveMessageService;



    /**
     * @return TODO 根据村级账号的id查询所有的企业
     */
    @ResponseBody
    @RequestMapping(value = "A220", method = RequestMethod.POST)
    public AppResult CompanyByVillageId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        // 根据村级id查询响应的企业
        List<Map<String, Object>> list = countryCheck.selectCompanyByVillageId(id);
        if (null == list) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);
        return result;
    }

    /**
     * @return TODO 根据镇级查询所有对应的村
     */
    @ResponseBody
    @RequestMapping(value = "A221", method = RequestMethod.POST)
    public AppResult villageBytownId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        // 根据镇级查询所有对应的村
        List<Map<String, Object>> list = countryCheck.selectVillageBytownId(id);
        if (null == list) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);

        return result;
    }

    /**
     * @return TODO 根据区级id查询所有的镇
     */
    @ResponseBody
    @RequestMapping(value = "A222", method = RequestMethod.POST)
    public AppResult townByDistrictId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        // 根据区级id查询所有的镇
        List<Map<String, Object>> list = countryCheck.selectTownByDistrictId(id);
        if (null == list) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);

        return result;
    }

    /**
     * 市直接查询所有的区
     */
    @ResponseBody
    @RequestMapping(value = "A223", method = RequestMethod.POST)
    public AppResult findDistrict(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        List<Map<String, Object>> list = countryCheck.selectDistrict();
        if (null == list) {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }
        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);

        return result;
    }

    /**
     * 总绝式, 来啥查啥
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "A224", method = RequestMethod.POST)
    public AppResult findAll(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        List<Map<String, Object>> list = null;

        if (7 == officials.getFlag()) {
            //市级查询所有的区
            list = countryCheck.selectDistrict();
        } else if (6 == officials.getFlag()) {
            // 区查询所有的镇
            list = countryCheck.selectTownByDistrictId(officials.getUid());

        } else if (3 == officials.getFlag()) {
            //镇查询所有的村
            list = countryCheck.selectVillageBytownId(officials.getUid());

        } else if (4 == officials.getFlag()) {
            //村查询所有的企业
            list = countryCheck.selectCompanyByVillageId(officials.getUid());

        } else {
            result.setMessage("查询失败");
            result.setStatus("1");
            return result;
        }

        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);

        return result;
    }

    /**
     * 获取部门,以及对应的岗位 level1 levle2
     * @param request
     * @param
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "A225", method = RequestMethod.POST)
    public AppResult checkCompany(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();

        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }

        // 企业对应的所有的部门
       List<String> list = zzjgDepartmentMapper.selectNameByUid(id);

        // 对数据进行判断是否存在
        if (null == list || list.size() == 0) {
            result.setStatus("1");
            result.setMessage("未查询出数据");
            return result;
        }

        // 根据公司id 和部门获取所有的岗位并进行数据你对比添加
        Map<String, List> stringListMap = checkManual.selectDangerAndManual(id, list);

        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(stringListMap);

        return result;
    }

    /**
     * 查询公司的安全责任人
     * id: uid 总公司的id
     */
    @ResponseBody
    @RequestMapping(value = "A226", method = RequestMethod.POST)
    public AppResult checkPerson(HttpServletRequest request, Integer id) {

        AppResult result = new AppResultImpl();

        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null || id==null ) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }

        // 根据id查询安全责任人
        List<Map<Integer, String>> list = zzjgPersonnelMapper.findUserByIdAndStatus1(id);

        result.setStatus("0");
        result.setMessage("查询成功");
        result.setData(list);

        return result;

    }

    /**
     * 保存模版
     * id 表示为公司的id
     */
    @ResponseBody
    @RequestMapping(value="A227" ,method = RequestMethod.POST)
    public AppResult saveModel(HttpServletRequest request, @RequestBody CheckItem checkItem ){
        AppResult result = new AppResultImpl();

        //到域中获取数据
        MySessionContext sess = MySessionContext.getInstance();
        HttpSession session = sess.getSession(checkItem.getSessionId());
        Officials officials = (Officials) session.getAttribute(checkItem.getAccess_token());// 获取session域中的信息
        if (officials == null || checkItem==null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }

        Integer modelId =  countryCheck.saveCheck(checkItem,officials,checkItem.getId());
        result.setMessage("查询成功");
        result.setStatus("0");
        result.setData(modelId);
        return  result;

    }

    /**
     * 政府端获取该企业所有的模版表
     */
    @ResponseBody
    @RequestMapping(value="A228",method = RequestMethod.POST)
    public AppResult CheckModelByUid(HttpServletRequest request,Integer id ){

        AppResult result = new AppResultImpl();

        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        List<Map<Integer, String>> list = checkManual.findModelByUid(id);
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
     * 根据模版id查询所有的详细信息
     */
    @ResponseBody
    @RequestMapping(value="A229",method = RequestMethod.POST)
    public AppResult checkItemtById(HttpServletRequest request,Integer modelId){
        AppResult result = new AppResultImpl();

        Officials officials = (Officials) appTokenData.getAppUser(request);
        if (officials == null) {
            result.setMessage("登陆失败");
            result.setStatus("1");
            return result;

        }
        CheckItemS checkItemByModelId = saveMessageService.findCheckItemByModelId(modelId);
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
     * 保存检查记录，并发送整改意见
     * @param saveDataMessageItem
     * @param id 公司的id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="A230" ,method = RequestMethod.POST)
    public  AppResult saveIdea(@RequestBody SaveDataMessageItem saveDataMessageItem,Integer id){
        AppResult result = new AppResultImpl();
        if (saveDataMessageItem==null){
            result.setStatus("1");
            result.setMessage("保存失败");
            return result;
        }
        //到域中获取数据
        MySessionContext sess = MySessionContext.getInstance();
        HttpSession session = sess.getSession(saveDataMessageItem.getSessionId());
        Officials officials = (Officials) session.getAttribute(saveDataMessageItem.getAccess_token());// 获取session域中的信息
        if (officials == null ) {
            result.setMessage("账号出现问题,请重新登陆");
            result.setStatus("1");
            return result;

        }

        // 对数据进行保存
        String name = countryCheck. saveCheckMessage(saveDataMessageItem, officials,id);
        if(name==null){
            result.setMessage("保存是被请重新发起检查");
            result.setStatus("1");
            return result;

        }

        result.setMessage("保存成功");
        result.setStatus("0");
        result.setData(name);

        return result;

    }


}
