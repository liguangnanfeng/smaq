package com.spring.web.controller.api;

import com.spring.web.dao.ZzjgDepartmentMapper;
import com.spring.web.dao.ZzjgPersonnelMapper;
import com.spring.web.listener.MySessionContext;
import com.spring.web.model.Officials;
import com.spring.web.model.request.CheckItem;
import com.spring.web.model.request.CheckLevel;
import com.spring.web.model.request.SaveDataMessageItem;
import com.spring.web.model.response.CheckItemS;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.service.CheckCompany.CountryCheck;
import com.spring.web.service.CheckCompany.ICheckManual;
import com.spring.web.service.CheckCompany.SaveMessageService;
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
 * TODO 政府端
 */
@Controller
@RequestMapping(value = "api/country/check")
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
     * TODO 根据村级账号的id查询所有的企业
     *
     * @param request 请求
     * @param id      村级账号id
     * @return list 该账号所管辖的企业
     */
    @RequestMapping(value = "A220", method = RequestMethod.POST)
    public @ResponseBody
    AppResult CompanyByVillageId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);

            if (null == officials) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            List<Map<String, Object>> list = countryCheck.selectCompanyByVillageId(id);

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未成功查询");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;

        }
    }

    /**
     * TODO 根据镇级查询所有对应的村
     *
     * @param request 请求
     * @param id      镇级id
     * @return list   镇附属的村集合
     */
    @RequestMapping(value = "A221", method = RequestMethod.POST)
    public @ResponseBody
    AppResult villageBytownId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();

        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            List<Map<String, Object>> list = countryCheck.selectVillageBytownId(id);

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO 根据区级id查询所有的镇
     *
     * @param request 请求
     * @param id      区级id
     * @return list 区级附属的镇
     */
    @RequestMapping(value = "A222", method = RequestMethod.POST)
    public @ResponseBody
    AppResult townByDistrictId(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();
        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            List<Map<String, Object>> list = countryCheck.selectTownByDistrictId(id);

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO  无锡市直接查询所有的区
     *
     * @param request 请求
     * @return list 无锡市所有的区
     */
    @RequestMapping(value = "A223", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findDistrict(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            List<Map<String, Object>> list = countryCheck.selectDistrict();

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO 总绝式, 来啥查啥
     *
     * @param request 请求
     * @return list   根据登陆的账号id 判断区域内的下级(最小的部门 村级)
     */
    @RequestMapping(value = "A224", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findAll(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

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
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO  查询高危检查项
     *
     * @param request 请求
     * @param uid     企业id
     * @return 根据企业id查询企业对应的高危选项
     */
    @RequestMapping(value = "B212", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkGaoWei(HttpServletRequest request, Integer uid) {

        AppResult result = new AppResultImpl();
        try {

            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            List<Map> list = checkManual.checkGaoWei(uid);

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }

    }

    /**
     * TODO 基础检查, 获取基础检查的id
     *
     * @param request 请求
     * @param uid     企业id
     * @return 根据企业id获取基础查询的细则
     */
    @RequestMapping(value = "B213", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkJiChu(HttpServletRequest request, Integer uid) {

        AppResult result = new AppResultImpl();
        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);

            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            Map map = checkManual.checkJiChu(uid);

            result.setMessage("查询成功");
            result.setStatus("0");
            result.setData(map);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO 高危检查选项
     *
     * @param request       请求
     * @param industryId    行业
     * @return 根据行业id    查询高危检查选项 level1 level2 level 3
     */
    @RequestMapping(value = "B214")
    public @ResponseBody
    AppResult checkGaoWeiItem(HttpServletRequest request, Integer industryId) {
        AppResult result = new AppResultImpl();
        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);

            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            Map map = checkManual.checkGaoWeiItem(industryId);
            result.setStatus("0");
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
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO 高危/基础 检查细则
     *
     * @param request          请求
     * @param checkLevel       查询条件
     * @return checkLevel集合  根据原有的数据进一步查询更详细的数据
     */
    @RequestMapping(value = "B215", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkJiChuAndGaoWei(HttpServletRequest request, CheckLevel checkLevel) {
        AppResult result = new AppResultImpl();

        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);

            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }

            List<Map> list = checkManual.checkGaoWeiAndJiChu(checkLevel);

            if (null == list) {
                result.setStatus("1");
                result.setMessage("没有相关数据");
                return result;
            }

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(list);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO  现场检查  根据企业id获取部门,以及对应的岗位 level1 levle2
     *
     * @param request 请求
     * @param id      企业id
     * @return 根据企业id查询部门(level1) 及部位(level2)
     */
    @RequestMapping(value = "A225", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkCompany(HttpServletRequest request, Integer id) {
        AppResult result = new AppResultImpl();

        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;
            }
            List<String> list = zzjgDepartmentMapper.selectNameByUid(id);
            Map<String, List> stringListMap = checkManual.selectDangerAndManual(id, list);

            result.setStatus("0");
            result.setMessage("查询成功");
            result.setData(stringListMap);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO 查询企业的安全责任人(安全责任人是相对的,政府端对应的安全责任人就是企业端的检查人员)(废弃)
     *
     * @param id 企业id
     * @return 企业所有的安全责任人
     */
    @RequestMapping(value = "A226", method = RequestMethod.POST)
    public @ResponseBody
    AppResult checkPerson(HttpServletRequest request, Integer id) {

        AppResult result = new AppResultImpl();

        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (officials == null || id == null) {
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
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }

    }

    /**
     * TODO 政府端保存检查模版
     *
     * @param checkItem 模板数据
     * @return modelId    生成的模板id
     */
    @RequestMapping(value = "A227", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveModel(@RequestBody CheckItem checkItem) {
        AppResult result = new AppResultImpl();

        try {
            // 从session中获取数据
            MySessionContext sess = MySessionContext.getInstance();
            HttpSession session = sess.getSession(checkItem.getSessionId());
            Officials officials = (Officials) session.getAttribute(checkItem.getAccess_token());// 获取session域中用户登陆的信息

            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;

            }
            Integer modelId = countryCheck.saveCheck(checkItem, officials, checkItem.getUid());

            result.setMessage("查询成功");
            result.setStatus("0");
            result.setData(modelId);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }

    }

    /**
     * TODO 政府端获取该企业所有的模版表(企业端的所有模版)
     *
     * @param  request   请求
     * @param  uid       企业id
     * @return list      获取对应企业所有的模板表
     */
    @ResponseBody
    @RequestMapping(value = "A228", method = RequestMethod.POST)
    public AppResult CheckModelByUid(HttpServletRequest request, Integer uid) {

        AppResult result = new AppResultImpl();

        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (officials == null) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;

            }
            List<Map<Integer, String>> list = checkManual.findCountryModelByUid(uid);

            result.setMessage("查询成功");
            result.setData(list);
            result.setStatus("0");

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }

    }

    /**
     * TODO 根据模版id查询所有的详细信息 就重新生成一系列的数据
     *
     * @param request 请求
     * @param modelId 模版id
     * @return checkItemByModelId  模版包含的详细信息
     */
    @ResponseBody
    @RequestMapping(value = "A229", method = RequestMethod.POST)
    public AppResult checkItemtById(HttpServletRequest request, Integer modelId) {
        AppResult result = new AppResultImpl();

        try {
            Officials officials = (Officials) appTokenData.getAppUser(request);
            if (null == officials) {
                result.setMessage("登陆失败");
                result.setStatus("1");
                return result;

            }
            CheckItemS checkItemByModelId = saveMessageService.findCheckItemByModelId(modelId);

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
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

    /**
     * TODO 保存检查记录，并发送整改意见
     *
     * @param  saveDataMessageItem 检查数据
     * @return message             成功返回消息
     */
    @RequestMapping(value = "A230", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveIdea(@RequestBody SaveDataMessageItem saveDataMessageItem) {
        AppResult result = new AppResultImpl();

        try {
            //到域中获取数据
            MySessionContext sess = MySessionContext.getInstance();
            HttpSession session = sess.getSession(saveDataMessageItem.getSessionId());
            Officials officials = (Officials) session.getAttribute(saveDataMessageItem.getAccess_token());

            if (officials == null) {
                result.setMessage("账号出现问题,请重新登陆");
                result.setStatus("1");
                return result;

            }

            String message = countryCheck.saveCheckMessage(saveDataMessageItem, officials, saveDataMessageItem.getUid());

            result.setMessage("保存成功");
            result.setStatus("0");
            result.setData(message);

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }

    }

    /**
     * 保存政府端复查的信息
     * @param  saveDataMessageItem  保存数据
     * @return message              保存成功返回消息
     */
    @RequestMapping(value = "A231", method = RequestMethod.POST)
    public @ResponseBody
    AppResult saveReviewData( @RequestBody SaveDataMessageItem saveDataMessageItem) {
        AppResult result = new AppResultImpl();
        try {

            // 获取session域中的信息
            MySessionContext sess = MySessionContext.getInstance();
            HttpSession session = sess.getSession(saveDataMessageItem.getSessionId());
            Officials officials = (Officials) session.getAttribute(saveDataMessageItem.getAccess_token());
            if (officials == null) {
                result.setStatus("1");
                result.setMessage("未登陆");
                return result;
            }

            String message = countryCheck.saveReviewData(saveDataMessageItem, officials);

            result.setStatus("0");
            result.setMessage("保存成功");
            result.setData(message);
            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }

    }


    /**
     * TODO 政府端查询检查记录(只有政府端登陆人员获取的数据)
     *
     * @param request 请求
     * @return list   政府端的检查记录
     */
    @RequestMapping(value = "A232", method = RequestMethod.POST)
    public @ResponseBody
    AppResult findCheckItem(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        try {

            Officials officials = (Officials) appTokenData.getAppUser(request);

            List<Map> list = countryCheck.findRecordByCreateUser(officials.getId());
            result.setData(list);
            result.setStatus("0");
            result.setMessage("查询成功");

            return result;
        } catch (NullPointerException e) {

            e.printStackTrace();
            result.setMessage("未查询出数据");
            result.setStatus("1");
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            result.setMessage("网络错误");
            result.setStatus("1");
            return result;
        }
    }

}
