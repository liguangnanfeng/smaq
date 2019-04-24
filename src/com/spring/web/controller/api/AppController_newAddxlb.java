/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.controller.api;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.dao.MessageToUserMapper;
import com.spring.web.dao.MessagesMapper;
import com.spring.web.dao.UserGroupMapper;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.AppToken;
import com.spring.web.model.Company;
import com.spring.web.model.HarmfulFactor;
import com.spring.web.model.LawDoc;
import com.spring.web.model.MessageToUser;
import com.spring.web.model.Messages;
import com.spring.web.model.Notice;
import com.spring.web.model.TCheck;
import com.spring.web.model.TCheckItem;
import com.spring.web.model.TCheckPart;
import com.spring.web.model.TCleanWarnSetting;
import com.spring.web.model.TCompany;
import com.spring.web.model.TIndustry;
import com.spring.web.model.TLevel;
import com.spring.web.model.TModel;
import com.spring.web.model.TRectification;
import com.spring.web.model.User;
import com.spring.web.model.Village;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.tobject.cgf.CheckSaveReqDTO;
import com.spring.web.util.DateConvertUtil;
import com.spring.web.util.EncryptUtil;
import com.spring.web.util.RandomUtil;

/**
 * @Title: AppController
 * @Description: TODO(用一句话描述该文件做什么)
 * @author FL
 * @date 2017年9月21日 下午3:56:10
 * @version V1.0
 */
@Controller
@RequestMapping(value = "api/newadd/xlb")
public class AppController_newAddxlb extends BaseController {
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    CgfService cgfService;
    @Autowired
    MessagesMapper messagesMapper;
    @Autowired
    MessageToUserMapper messageToUserMapper;
    @Autowired
    UserGroupMapper userGroupMapper;

    /**
     * 获取企业检查类型列表
     */
    @RequestMapping(value = "A100"/*, method = RequestMethod.POST*/)
    public @ResponseBody
    AppResult getCheckTypeList(HttpServletRequest request,Integer userId) {
        AppResult result = new AppResultImpl();
        //Integer userId = getAppUserId(request);
        TCompany tc = tCompanyMapper.selectByPrimaryKey(userId);
        JSONArray array = new JSONArray();
        
        if (null != tc) {
            JSONObject object = new JSONObject();
            TIndustry industry = tIndustryMapper.selectByPrimaryKey(tc.getIndustry1());// 基础检查类别
            if(null != industry){
                object.put("id", industry.getId());
                object.put("name", "基础管理");
                List<TLevel> lL = tLevelMapper.selectByIndustry(industry.getId());
                object.put("checkParts", lL);
                array.add(object);
                
            }
            if (StringUtils.isNotBlank(tc.getIndustry2())) {
                List<TIndustry> ind2Ls = tIndustryMapper.selectByIds(tc.getIndustry2());// 现场检查类别
                if(null != ind2Ls && ind2Ls.size() > 0){
                    for(int i =0;i < ind2Ls.size();i++){
                        object = new JSONObject();
                        object.put("id", ind2Ls.get(i).getId());
                        object.put("name", "现场管理-"+ind2Ls.get(i).getName());
                        List<TLevel> lL = tLevelMapper.selectByIndustry(ind2Ls.get(i).getId());
                        object.put("checkParts", lL);
                        array.add(object);
                    }
                }
            }
            if (StringUtils.isNotBlank(tc.getIndustry3())) {
                List<TIndustry> ind3Ls = tIndustryMapper.selectByIds(tc.getIndustry3());// 现场检查类别
                if(null != ind3Ls && ind3Ls.size() > 0){
                    for(int i =0;i < ind3Ls.size();i++){
                        object = new JSONObject();
                        object.put("id", ind3Ls.get(i).getId());
                        object.put("name", "高危作业-"+ind3Ls.get(i).getName());
                        List<TLevel> lL = tLevelMapper.selectByIndustry(ind3Ls.get(i).getId());
                        object.put("checkParts", lL);
                        array.add(object);
                    }
                }
            }
        }
        result.setData(array);
        
        return result;
    }
    
    /**
     * 获取检查部位
     */
    @RequestMapping(value = "A101", method = RequestMethod.POST)
    public @ResponseBody
    AppResult getCheckPartList(HttpServletRequest request,Integer industryId) {
        AppResult result = new AppResultImpl();
        List<TLevel> lL = tLevelMapper.selectByIndustry(industryId);
        result.setData(lL);
        return result;
    }
    
    /**
     * 获取企业列表
     */
    @RequestMapping(value = "A102")
    public @ResponseBody
    AppResult getCompanyList(HttpServletRequest request,String companyName,Integer page,Integer limit) {
        AppResult result = new AppResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(companyName)){
            try {
                companyName = URLDecoder.decode(URLDecoder.decode(companyName,"utf-8"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(),e);
            }
            m.put("companyName", companyName);
        }
        if(null == page){
            page = 1;
        }
        if(null == limit){
            limit = 10;
        }
        JSONObject object = new JSONObject();
        m.put("page", (page-1)*limit);
        Integer userId = getAppUserId(request);
        setUserId(userId, m);
        if(page == 1){
            m.put("limit", Integer.MAX_VALUE);
            List<DynamicParameter<String, Object>> list2 = companyMapper.selectCompanyList(m);
            object.put("total", list2.size());
        }
        m.put("limit", limit);
        object.put("list", companyMapper.selectCompanyList(m));
        result.setData(object);
        return result;
    }
    
    /**
     * 保存检查
    * @param request
    * @param dto
    * @return
     */
    @RequestMapping(value = "A103")
    public @ResponseBody
    AppResult add(HttpServletRequest request,Integer flag,String title,Integer checkCompanyId,String checkCompany,String checkPart,String checker,Integer industryId,Integer industryType, 
           String dapartContact ,String partList ){
        AppResult result = new AppResultImpl();
        JSONObject object = new JSONObject();
        try {
            CheckSaveReqDTO dto = new CheckSaveReqDTO();
            TCheck tCheck = new TCheck();
            if(StringUtils.isNotBlank(title)){
                title = URLDecoder.decode(URLDecoder.decode(title, "utf-8"), "utf-8");
            }
            if(StringUtils.isNotBlank(checkCompany)){
                checkCompany =URLDecoder.decode( URLDecoder.decode(checkCompany, "utf-8"), "utf-8");
            }
            if(StringUtils.isNotBlank(checkPart)){
                checkPart = URLDecoder.decode(URLDecoder.decode(checkPart, "utf-8"), "utf-8");
            }
            if(StringUtils.isNotBlank(checker)){
                checker = URLDecoder.decode(URLDecoder.decode(checker, "utf-8"), "utf-8");
            }
            if(StringUtils.isNotBlank(dapartContact)){
                dapartContact = URLDecoder.decode(URLDecoder.decode(dapartContact, "utf-8"), "utf-8");
            }
            if(StringUtils.isNotBlank(partList)){
                partList = URLDecoder.decode(URLDecoder.decode(partList, "utf-8"), "utf-8");
            }
            if(null == flag){
                flag = 4;
            }
            tCheck.setTitle(title);
            tCheck.setUserId(checkCompanyId);
            tCheck.setFlag(flag);
            tCheck.setCheckCompany(checkCompany);
            tCheck.setDepart(checkPart);
            tCheck.setCheker(checker);
            tCheck.setIndustryId(industryId);
            tCheck.setIndustryType(industryType);
            tCheck.setDapartContact(dapartContact);
            tCheck.setType(3);
            tCheck.setDel(0);
            dto.setCheck(tCheck);
            List<TCheckPart> list = new ArrayList<>();
            
            JSONArray array = JSONArray.parseArray(partList);
            if(null != array && array.size() >0){
                for(Object o:array){
                    JSONObject object2 = (JSONObject)o;
                    TCheckPart part = new TCheckPart();
                    part.setName(object2.getString("partName"));
                    part.setLevels(object2.getString("levels"));
                    list.add(part);
                }
            }
            dto.setPartList(list);
            Integer userId = getAppUserId(request);
            //User user = getLoginUser(request);
            dto.getCheck().setCreateUser(userId);
            cgfService.checkSaveDo(dto);
            //object.put("status", 0);
            //object.put("checkId", dto.getCheck().getId());
            Integer checkId = dto.getCheck().getId();
            TCheck tc = tCheckMapper.selectByPrimaryKey(checkId);
            List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(checkId);
            JSONObject object2 = new JSONObject();
            object2.put("check", tc);
            object2.put("partL", partL);
            object2.put("itemL", tCheckItemMapper.selectByCheckId(checkId));
            object2.put("company", companyMapper.selectByPrimaryKey(tc.getUserId()));
            result.setData(object2);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("status", 1);
            object.put("message", "保存失败");
            result.setData(object);
        }
        return result;
    }
    
    /**
     * 获取检查plan数据
    * @param request
    * @param dto
    * @return
     */
    @RequestMapping(value = "A104")
    public @ResponseBody
    AppResult toCheckPlan(HttpServletRequest request,Integer checkId){
        AppResult result = new AppResultImpl();
        TCheck tc = tCheckMapper.selectByPrimaryKey(checkId);
        List<TCheckPart> partL = tCheckPartMapper.selectByCheckId(checkId);
        JSONObject object = new JSONObject();
        object.put("check", tc);
        object.put("partL", partL);
        object.put("itemL", tCheckItemMapper.selectByCheckId(checkId));
        object.put("company", companyMapper.selectByPrimaryKey(tc.getUserId()));
        result.setData(object);
        return result;
    }
    
    /**
     * 为不合格item添加描述
    * @param request
    * @param dto
    * @return
     */
    @RequestMapping(value = "A105")
    public @ResponseBody
    AppResult doAddImpression(HttpServletRequest request,Integer itemId,String url,String memo){
        AppResult result = new AppResultImpl();
        TCheckItem tci = new TCheckItem();
        tci.setId(itemId);
        tci.setFiles(url);
        tci.setMemo(memo);
        result.setData(tCheckItemMapper.updateByPrimaryKeySelective(tci));
        return result;
    }
    
    /**
     * 保存检查信息
    * @param request
    * @param dto
    * @return
     */
    @RequestMapping(value = "A106")
    public @ResponseBody
    AppResult saveCheckInfo(HttpServletRequest request,Integer checkId,String cheker,String checkCompany,String dapartContact){
        AppResult result = new AppResultImpl();
        TCheck t = new TCheck();
        t.setId(checkId);
        t.setCheker(cheker);
        t.setCheckCompany(checkCompany);
        t.setDapartContact(dapartContact);
        t.setStatus(2);
        try {
            cgfService.checkNestSave(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 获取信息详情
    * @param request
    * @param messageId
    * @return
     */
    @RequestMapping(value = "A107"/*, method = RequestMethod.POST*/)
    public @ResponseBody AppResult getMessagesById(HttpServletRequest request, String messageId) {
        AppResult result = new AppResultImpl();
        JSONObject object = new JSONObject();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        if(StringUtils.isBlank(messageId)){
            result.setStatus("2");
            result.setMessage("资讯id不能为空");
            return result;
        }
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("messageId", messageId);
        params.put("reviceUserId", userId);
        MessageToUser toUser = messageToUserMapper.getMessageStatus(params);
        if(null != toUser && toUser.getReadFlag().equals((byte)0)){
            toUser.setReadTime(new Date());
            toUser.setReadFlag((byte)1);
            toUser.setReviceFlag((byte)1);
            messageToUserMapper.updateByPrimaryKeySelective(toUser);
        }
        Messages mapper = messagesMapper.getMessageByCondition(params);
        if(null != mapper){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            object.put("messageId", mapper.getId());
            object.put("title", mapper.getTitle());
            object.put("desc", mapper.getDescr());
            object.put("content", mapper.getContent());
            List<Map<String, Object>> list = userGroupMapper.getVillageNameByUserId(mapper.getUserId());
            if(null != list){
                Map<String, Object> userInfo = list.get(0);
                switch (mapper.getCreateUtp()) {
                    case 6:
                        object.put("createName", userInfo.get("districtName"));
                        break;
                    case 3:
                        object.put("createName", userInfo.get("townName"));
                        break;
                    case 4:
                        object.put("createName", userInfo.get("villageName"));
                        break;
                    default:
                        object.put("createName", userInfo.get("userName"));
                        break;
                }
            }
            object.put("createId", mapper.getUserId());
            object.put("createTime", format.format(mapper.getCreateTime()));
            String files = mapper.getFiles();
            if(StringUtils.isNotBlank(files)){
                object.put("files", JSONArray.parseArray(files));
            }else{
                object.put("files", new ArrayList());
            }
        }
        result.setData(object);
        return result;
    }
    
    /**
     * 获取信息列表
    * @param request
    * @param messageId
    * @return
     */
    @RequestMapping(value = "A108"/*, method = RequestMethod.POST*/)
    public @ResponseBody AppResult getMessageList(HttpServletRequest request, Integer page,Integer limit) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("userId", userId);
        if(null ==page){
            page = 1;
        }
        if(null == limit){
            limit = 10;
        }
        params.put("page", (page-1)*limit);
        params.put("limit", limit);
        List<Map<String, Object>> list = messagesMapper.getRecviceMessages(params);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(null != list && list.size() > 0){
            for(Map<String, Object> map:list){
                if(null != map){
                    map.put("desc", map.get("descr"));
                    map.remove("descr");
                    List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId(Integer.parseInt(map.get("createId").toString()));
                    if(null != namelist){
                        Map<String, Object> userInfo = namelist.get(0);
                        switch (Integer.parseInt(map.get("createUserType").toString())) {
                            case 6:
                                map.put("createName", userInfo.get("districtName"));
                                break;
                            case 3:
                                map.put("createName", userInfo.get("townName"));
                                break;
                            case 4:
                                map.put("createName", userInfo.get("villageName"));
                                break;
                            default:
                                map.put("createName", userInfo.get("userName"));
                                break;
                        }
                    }
                    map.remove("createUserType");
                    map.put("createTime", format.format((Date)map.get("createTime")));
                }
            }
        }
        JSONObject object = new JSONObject();
        object.put("list", list);
        Map<String, Object> params2 = new HashMap<String,Object>();
        params2.put("userId", userId);
        object.put("total", messagesMapper.getRecviceMessagesCount(params2));
        result.setData(object);
        return result;
    }
    
    /**
     * 删除已读信息
    * @param request
    * @param messageId
    * @return
     */
    @RequestMapping(value = "A109"/*, method = RequestMethod.POST*/)
    public @ResponseBody AppResult deleteMessageById(HttpServletRequest request, String messageId) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        MessageToUser toUser = new MessageToUser();
        toUser.setUserId(userId);
        toUser.setMessageId(messageId);
        toUser.setDelFlag((byte)1);
        result.setData(messageToUserMapper.updateInfoByMessageAndUserId(toUser)>0?true:false);
        return result;
    }
    
    @RequestMapping(value = "A110"/*, method = RequestMethod.POST*/)
    public @ResponseBody AppResult getUnreadTotal(HttpServletRequest request) {
        AppResult result = new AppResultImpl();
        Integer userId = getAppUserId(request);
        if (null == userId) {
            result.setStatus("2");
            result.setMessage("登录超时");
            return result;
        }
        result.setData(messageToUserMapper.getUnreadMessageCount(userId));
        return result;
    }
    
    /**
     * 添加检查结果
    * @param request
    * @param dto
    * @return
     */
    @RequestMapping(value = "A111")
    public @ResponseBody
    AppResult doAddCheckResult(HttpServletRequest request,String partImage,Integer partId,String checkItems,Integer checkId,String cheker,String checkCompany,String dapartContact){
        AppResult result = new AppResultImpl();
        TCheckPart checkPart = new TCheckPart();
        int i = 0;
        try {
            if(StringUtils.isNotBlank(partImage)){
                partImage = URLDecoder.decode(URLDecoder.decode(partImage, "utf-8"), "utf-8");
            }
            if(StringUtils.isNotBlank(cheker)){
                cheker = URLDecoder.decode(URLDecoder.decode(cheker, "utf-8"), "utf-8");
            }
            if(StringUtils.isNotBlank(checkCompany)){
                checkCompany = URLDecoder.decode(URLDecoder.decode(checkCompany, "utf-8"), "utf-8");
            }
            if(StringUtils.isNotBlank(dapartContact)){
                dapartContact = URLDecoder.decode(URLDecoder.decode(dapartContact, "utf-8"), "utf-8");
            }
        } catch (UnsupportedEncodingException e1) {
            log.error(e1.getMessage(),e1);
        }
        if(StringUtils.isNotBlank(partImage)){
            checkPart.setId(partId);
            checkPart.setPartImg(partImage);
            i = tCheckPartMapper.updateByPrimaryKeySelective(checkPart);
        }
        
        if(StringUtils.isNotBlank(checkItems)){
            try {
                checkItems = URLDecoder.decode(URLDecoder.decode(checkItems, "utf-8"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(),e);
            }
            JSONArray items = JSONArray.parseArray(checkItems);
            if(null != items && !items.isEmpty()){
                for(Object o:items){
                    JSONObject object = (JSONObject)o;
                    TCheckItem tci = new TCheckItem();
                    tci.setId(object.getInteger("itemId"));
                    tci.setFiles(object.getString("url"));
                    tci.setMemo(object.getString("memo"));
                    tci.setStatus(2);
                    i = tCheckItemMapper.updateByPrimaryKeySelective(tci);
                }
            }
        }
        
        TCheck t = new TCheck();
        t.setId(checkId);
        t.setCheker(cheker);
        t.setCheckCompany(checkCompany);
        t.setDapartContact(dapartContact);
        t.setStatus(2);
        try {
            cgfService.checkNestSave(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        result.setData(i>0?true:false);
        return result;
    }
    
    
    void setUserId(Integer userId, Map<String, Object> m) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getUserType() == 4) {
            m.put("villageId", user.getId());
        }
        if (user.getUserType() == 5) {
            m.put("userId", user.getId());
        }
        if (user.getUserType() == 3) {
            m.put("townId", user.getId());
        }
        if (user.getUserType() == 6) {
            m.put("districtId", user.getId());
        }
        if (user.getUserType() == 10) {
            m.put("tradeId", user.getId());
        }
    }
    

    @InitBinder
    public void initData(WebDataBinder wdb) {
        wdb.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
