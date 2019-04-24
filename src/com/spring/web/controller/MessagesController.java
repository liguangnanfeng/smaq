
package com.spring.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.dao.MessageToUserMapper;
import com.spring.web.dao.MessagesMapper;
import com.spring.web.dao.UserGroupListMapper;
import com.spring.web.dao.UserGroupMapper;
import com.spring.web.model.MessageToUser;
import com.spring.web.model.Messages;
import com.spring.web.model.User;
import com.spring.web.model.UserGroup;
import com.spring.web.model.UserGroupList;
import com.spring.web.utils.MIPushServerUtils;

/** 
* @Title: MessagesController 
* @Description: TODO(消息相关的请求) 
* @date 2018年6月6日 下午2:46:32 
* @version V1.0   
*/
@Controller
@RequestMapping("/village")
public class MessagesController extends BaseController{

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private MessagesMapper messagesMapper;
    @Autowired
    private MessageToUserMapper messageToUserMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private UserGroupListMapper userGroupListMapper;
    
    
//    @RequestMapping("addMessages")
//    public String addMessages(HttpServletRequest request,Model model){
//        User user = getLoginUser(request);
//        if(null != user){
//        	model.addAttribute("userType", user.getUserType());
//            model.addAttribute("loginUserId", user.getId());
//            return "village/userGroup/addMessages";
//        }
//        return "";
//    }
    @RequestMapping("addMessages")
    public String addMessages(HttpServletRequest request,Model model){
        User user = getLoginUser(request);
        if(null != user){
        	if(user.getUserType() == 5){
        		model.addAttribute("propelcompany", 1);
        	}
        	model.addAttribute("userType", user.getUserType());
            model.addAttribute("loginUserId", user.getId());
            return "village/userGroup/addMessages";
        }
        return "";
    }
    
    
    /**
     * 群组列表页
    * @param request
    * @param model
    * @return
     */
    @RequestMapping("/groupIndex")
    public String groupIndex(HttpServletRequest request,Model model){
        User user = getLoginUser(request);
        if(null != user){
            String groupName = request.getParameter("groupName");
            Map<String, Object> params = new HashMap<String,Object>();
            if(!StringUtils.isEmpty(groupName)){
                try {
                    groupName = URLDecoder.decode(URLDecoder.decode(groupName, "utf-8"),"utf-8");
                    params.put("groupName", groupName);
                    model.addAttribute("groupName", groupName);
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage(),e);
                }
            }
            params.put("userId", user.getId());
            List<UserGroup> list = userGroupMapper.getUserGroupList(params);
            model.addAttribute("list", list);
            return "village/userGroup/groupList";
        }
        return "";
    }
    
    /**
     * 获取所有的分组
     */
    @RequestMapping("/getUserGroupUserList")
    @ResponseBody
    public JSONObject getUserGroupUserList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user){
                String groupName = request.getParameter("groupName");
                Map<String, Object> params = new HashMap<String,Object>();
                if(!StringUtils.isEmpty(groupName)){
                    groupName = URLDecoder.decode(URLDecoder.decode(groupName, "utf-8"),"utf-8");
                    params.put("groupName", groupName);
                }
                params.put("userId", user.getId());
                result.put("list", userGroupMapper.getUserGroupList(params));
            }
            
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 获取群组用户信息
    * @param request
    * @return
     */
    @RequestMapping("/getGroupDetail")
    @ResponseBody
    public JSONObject getGroupDetail(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            String groupId = request.getParameter("groupId");
            result.put("list", userGroupListMapper.getGroupUserList(groupId));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    @RequestMapping("/tpAddGroup")
    public String toAddGroup(HttpServletRequest request,Model model){
        User user = getLoginUser(request);
        if(null != user){
            model.addAttribute("utp", user.getUserType());
            String groupId = request.getParameter("groupId");
            if(!StringUtils.isEmpty(groupId)){
                UserGroup group = userGroupMapper.selectByPrimaryKey(groupId);
                if(null != group){
                    model.addAttribute("groupId", group.getId());
                    model.addAttribute("groupName", group.getGroupName());
                }
            }
            return "village/userGroup/addUserGroup";
        }else{
            return "";
        }
    }
    
    @RequestMapping("/getGroupUserList")
    @ResponseBody
    public JSONObject getGroupUserList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        User user = getLoginUser(request);
        if(null != user){
            String userType = request.getParameter("userType");
            if(!StringUtils.isEmpty(userType)){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Map<String, Object> params = new HashMap<String,Object>();
                String userIds = request.getParameter("userId");
                Integer userId = user.getId();
                if(!StringUtils.isEmpty(userIds)){
                    userId = Integer.parseInt(userIds);
                }
                switch (userType) {
                    case "7":
                        params.put("cityId", userId);
                        list = userGroupMapper.getUsersByOrgId(params);
                        result.put("list", list);
                        result.put("utp", 7);
                        break;
                    case "6":
                        params.put("districtId", userId);
                        list = userGroupMapper.getUsersByOrgId(params);
                        result.put("list", list);
                        result.put("utp", 6);
                        break;
                    case "3":
                        params.put("townId", userId);
                        list = userGroupMapper.getUsersByOrgId(params);
                        result.put("list", list);
                        result.put("utp", 3);
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }
    
//    @RequestMapping("/getCompanyByCondition")
//    @ResponseBody
//    public JSONObject getCompanyByCondition(HttpServletRequest request){
//        JSONObject result = new JSONObject();
//        String companyName = request.getParameter("companyName");
//        String villageId = request.getParameter("villageId");
//        String districtId = request.getParameter("districtId");
//        String townId = request.getParameter("townId");
//        User user = getLoginUser(request);
//        if(null != user){
//            Map<String, Object> params = new HashMap<String,Object>();
//            if(!StringUtils.isEmpty(companyName)){
//                try {
//                    companyName = URLDecoder.decode(URLDecoder.decode(companyName, "utf-8"),"utf-8");
//                } catch (UnsupportedEncodingException e) {
//                    log.error(e.getMessage(),e);
//                }
//                params.put("companyName", companyName);
//            }
//            if(!StringUtils.isEmpty(villageId)){
//                params.put("villageId", villageId);
//            }
//            if(!StringUtils.isEmpty(districtId)){
//                params.put("districtId", districtId);
//            }
//            if(!StringUtils.isEmpty(townId)){
//                params.put("townId", townId);
//            }
//            if (user.getUserType() == 4) {
//                params.put("villageId", user.getId());
//            }
//            if (user.getUserType() == 3) {
//                params.put("townId", user.getId());
//            }
//            if (user.getUserType() == 6) {
//                params.put("districtId", user.getId());
//            }
//            result.put("list", userGroupMapper.searchCompanyByCondition(params));
//        return result;
//    }
    @RequestMapping("/getCompanyByCondition")
    @ResponseBody
    public JSONObject getCompanyByCondition(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String companyName = request.getParameter("companyName");
        String villageId = request.getParameter("villageId");
        String districtId = request.getParameter("districtId");
        String townId = request.getParameter("townId");
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
            if(!StringUtils.isEmpty(villageId)){
                params.put("villageId", villageId);
            }
            if(!StringUtils.isEmpty(districtId)){
                params.put("districtId", districtId);
            }
            if(!StringUtils.isEmpty(townId)){
                params.put("townId", townId);
            }
            if (user.getUserType() == 4) {
                params.put("villageId", user.getId());
            }
            if (user.getUserType() == 3) {
                params.put("townId", user.getId());
            }
            if (user.getUserType() == 6) {
                params.put("districtId", user.getId());
            }
            //result.put("list", userGroupMapper.searchCompanyByCondition(params));
            //在行业端添加信息推送功能 wz add190119
            log.error("params："+params.toString()+"，Type："+ user.getUserType());
            if (user.getUserType() == 10) {
                params.put("tradeId", user.getId());
                result.put("list", userGroupMapper.searchCompanyByConditionTrade(params)); 
                //log.error("行业端下传企业名称list："+userGroupMapper.searchCompanyByConditionTrade(params));
            }else if(user.getUserType() == 5){
            	params.put("companyId", user.getId());
            	List<Map<String, Object>> list = userGroupMapper.searchCompanyByConditionCompany(params);
            	List<Map<String, Object>> list1 = userGroupMapper.searchCompanyByConditionCompanyTrade(params);
            	if(list1 != null && list1.size() != 0){
            		list.addAll(list1);
            	}
            	//log.error("list："+list.toString());
            	result.put("list", list);
            }else{
            	result.put("list", userGroupMapper.searchCompanyByCondition(params));
            }
        }
        //log.error("result："+result.toString());
        return result;
    }
    @RequestMapping("/doAddGroup")
    @ResponseBody
    public JSONObject doAddGroup(HttpServletRequest request){
        JSONObject result = new JSONObject();
        result.put("status", false);
        try {
            User user = getLoginUser(request);
            if(null != user){
                String groupName = request.getParameter("groupName");
                String companyList = request.getParameter("companyList");
                String groupId = request.getParameter("groupId");
                if(!StringUtils.isEmpty(groupName) && !StringUtils.isEmpty(companyList)){
                    groupName = URLDecoder.decode(URLDecoder.decode(groupName, "utf-8"),"utf-8");
                    JSONArray array = JSONArray.parseArray(companyList);
                    if(array.size() > 0){
                        UserGroup newUserGroup = null;
                        if(!StringUtils.isEmpty(groupId)){
                            newUserGroup = userGroupMapper.selectByPrimaryKey(groupId);
                        }
                        if(null == newUserGroup){
                            UserGroup group = new UserGroup();
                            group.setCreateTime(new Date());
                            group.setGroupName(groupName);
                            group.setId(UUID.randomUUID().toString().replaceAll("-","" ));
                            group.setUserId(user.getId());
                            int i = userGroupMapper.insertSelective(group);
                            if(i > 0){
                                List<UserGroupList> userGroups = new ArrayList<UserGroupList>(); 
                                for(Object o:array){
                                    JSONObject object = (JSONObject)o;
                                    UserGroupList userGroup = new UserGroupList();
                                    userGroup.setGroupId(group.getId());
                                    userGroup.setId(UUID.randomUUID().toString().replaceAll("-","" ));
                                    userGroup.setGroupUserId(object.getInteger("userId"));
                                    userGroups.add(userGroup);
                                }
                                result.put("status", userGroupListMapper.insertCompanyListBatch(userGroups)>0?true:false);
                            }
                        }else{
                            newUserGroup.setGroupName(groupName);
                            userGroupMapper.updateByPrimaryKeySelective(newUserGroup);
                            int i = userGroupListMapper.deleteByGroupId(groupId);
                            if(i > 0){
                                List<UserGroupList> userGroups = new ArrayList<UserGroupList>(); 
                                for(Object o:array){
                                    JSONObject object = (JSONObject)o;
                                    UserGroupList userGroup = new UserGroupList();
                                    userGroup.setGroupId(newUserGroup.getId());
                                    userGroup.setId(UUID.randomUUID().toString().replaceAll("-","" ));
                                    userGroup.setGroupUserId(object.getInteger("userId"));
                                    userGroups.add(userGroup);
                                }
                                result.put("status", userGroupListMapper.insertCompanyListBatch(userGroups)>0?true:false);
                            }
                        }
                        
                    }
                }
            }
            
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 获取已选择的企业列表
    * @param request
    * @return
     */
    @RequestMapping("/getHasChooseCompanyList")
    @ResponseBody
    public JSONObject getHasChooseCompanyList(HttpServletRequest request){
        JSONObject result = new JSONObject();
        JSONArray resultArr = new JSONArray();
        try {
            String groupList = request.getParameter("groupList");
            if(!StringUtils.isEmpty(groupList)){
                JSONArray groupArr = JSONArray.parseArray(groupList);
                if(groupArr.size() > 0){
                   for(Object o : groupArr){
                       JSONObject object = (JSONObject)o;
                       String groupId = object.getString("groupId");
                       List<Map<String, Object>> list = userGroupListMapper.getGroupUserList(groupId);
                       if(null != list && list.size() > 0){
                           resultArr.addAll(list);
                       }
                   } 
                }
                result.put("list", resultArr);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
//    /**
//     * 添加推送的消息记录
//    * @param request
//    * @return
//     */
//    @RequestMapping("doAddMessage")
//    @ResponseBody
//    public JSONObject doAddMessage(HttpServletRequest request){
//        JSONObject result = new JSONObject();
//        result.put("status", false);
//        String environment = request.getParameter("envrionment");
//        try {
//            User user = getLoginUser(request);
//            List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId(user.getId());
//            String createName = "";
//            if(null != namelist){
//                Map<String, Object> userInfo = namelist.get(0);
//                switch (user.getUserType()) {
//                    case 6:
//                        createName = (String) userInfo.get("districtName");
//                        break;
//                    case 3:
//                        createName = (String)userInfo.get("townName");
//                        break;
//                    case 4:
//                        createName = (String)userInfo.get("villageName");
//                        break;
//                    default:
//                        createName = (String) userInfo.get("userName");
//                        break;
//                }
//            }
//            if(null != user){
//                String title = request.getParameter("title");
//                String desc = request.getParameter("desc");
//                String content = request.getParameter("content");
//                String companyList = request.getParameter("companyList");
//                String files = request.getParameter("files");
//                JSONArray array = JSONArray.parseArray(companyList);
//                Messages messages = new Messages();
//                if(!StringUtils.isEmpty(content)){
//                    messages.setContent(content);
//                }
//                if(!StringUtils.isEmpty(files)){
//                    messages.setFiles(files);
//                }
//                messages.setTitle(URLDecoder.decode(URLDecoder.decode(title, "utf-8"),"utf-8"));
//                messages.setDescr(URLDecoder.decode(URLDecoder.decode(desc, "utf-8"),"utf-8"));
//                messages.setSendTotal(array.size());
//                messages.setUserId(user.getId());
//                messages.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//                messages.setCreateTime(new Date());
//                messages.setCreateUtp(user.getUserType());
//                int i = messagesMapper.insertSelective(messages);
//                if(i > 0){
//                    List<MessageToUser> users = new ArrayList<MessageToUser>();
//                    for(Object o :array){
//                        JSONObject object = (JSONObject)o;
//                        MessageToUser toUser = new MessageToUser();
//                        toUser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//                        toUser.setMessageId(messages.getId());
//                        toUser.setUserId(object.getInteger("userId"));
//                        users.add(toUser);
//                    }
//                    result.put("status", messageToUserMapper.insertMessagesListBatch(users));
//                    result.put("messageId", messages.getId());
//                    
//                    Integer envri = null;
//                    if(!StringUtils.isEmpty(environment)){
//                        envri = 1;
//                    }
//                    MIPushServerUtils utils = new MIPushServerUtils();
//                    utils.sendMessages(users, createName, 1, messages,envri);
//                    MIPushServerUtils utils2 = new MIPushServerUtils();
//                    utils2.sendMessages(users, createName, 0, messages,null);
//                }
//            }
//            
//        } catch (Exception e) {
//            log.error(e.getMessage(),e);
//        }
//        return result;
//    }
    /**
     * 添加推送的消息记录
    * @param request
    * @return
     */
    @RequestMapping("doAddMessage")
    @ResponseBody
    public JSONObject doAddMessage(HttpServletRequest request){
        JSONObject result = new JSONObject();
        result.put("status", false);
        String environment = request.getParameter("envrionment");
        try {
            User user = getLoginUser(request);
            List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId1(user.getId());//update
            String createName = "";
            if(null != namelist){
                Map<String, Object> userInfo = namelist.get(0);
                switch (user.getUserType()) {
                    case 6:
                        createName = (String) userInfo.get("districtName");
                        break;
                    case 3:
                        createName = (String)userInfo.get("townName");
                        break;
                    case 4:
                        createName = (String)userInfo.get("villageName");
                        break;
                    case 10:
                    	createName = (String)userInfo.get("tradeName");// add 
                        break;
                    case 5:
                    	createName = (String)userInfo.get("companyName");// add 
                        break;
                    default:
                        createName = (String) userInfo.get("userName");
                        break;
                }
            }
            //log.error(createName);
            if(null != user){
                String title = request.getParameter("title");
                String desc = request.getParameter("desc");
                String content = request.getParameter("content");
                String companyList = request.getParameter("companyList");
                String files = request.getParameter("files");
                JSONArray array = JSONArray.parseArray(companyList);
                Messages messages = new Messages();
                if(!StringUtils.isEmpty(content)){
                    messages.setContent(content);
                }
                if(!StringUtils.isEmpty(files)){
                    messages.setFiles(files);
                }
                messages.setTitle(URLDecoder.decode(URLDecoder.decode(title, "utf-8"),"utf-8"));
                messages.setDescr(URLDecoder.decode(URLDecoder.decode(desc, "utf-8"),"utf-8"));
                messages.setSendTotal(array.size());
                messages.setUserId(user.getId());
                messages.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                messages.setCreateTime(new Date());
                messages.setCreateUtp(user.getUserType());
                int i = messagesMapper.insertSelective(messages);
                if(i > 0){
                    List<MessageToUser> users = new ArrayList<MessageToUser>();
                    for(Object o :array){
                        JSONObject object = (JSONObject)o;
                        MessageToUser toUser = new MessageToUser();
                        toUser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                        toUser.setMessageId(messages.getId());
                        toUser.setUserId(object.getInteger("userId"));
                        users.add(toUser);
                    }
                    result.put("status", messageToUserMapper.insertMessagesListBatch(users));
                    result.put("messageId", messages.getId());
                    
                    Integer envri = null;
                    if(!StringUtils.isEmpty(environment)){
                        envri = 1;
                    }
                    MIPushServerUtils utils = new MIPushServerUtils();
                    utils.sendMessages(users, createName, 1, messages,envri);
                    MIPushServerUtils utils2 = new MIPushServerUtils();
                    utils2.sendMessages(users, createName, 0, messages,null);
                }
            }
            
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
//    @RequestMapping("/getMessageById")
//    @ResponseBody
//    public JSONObject getMessageById(HttpServletRequest request){
//        JSONObject result = new JSONObject();
//        try {
//            User user = getLoginUser(request);
//            String messageId = request.getParameter("messageId");
//            Messages messages = messagesMapper.selectByPrimaryKey(messageId);
//            if(null != messages){
//                MessageToUser toUser = new MessageToUser();
//                toUser.setReviceFlag((byte)1);
//                toUser.setMessageId(messageId);
//                //toUser.setReadFlag((byte)1);
//                toUser.setUserId(user.getId());
//                messageToUserMapper.updateInfoByMessageAndUserId(toUser);
//                result.put("title", messages.getTitle());
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                result.put("createTime", format.format(messages.getCreateTime()));
//                result.put("id", messages.getId());
//                List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId(messages.getUserId());
//                if(null != namelist){
//                    Map<String, Object> userInfo = namelist.get(0);
//                    switch (messages.getCreateUtp()) {
//                        case 6:
//                            result.put("createName", userInfo.get("districtName"));
//                            break;
//                        case 3:
//                            result.put("createName", userInfo.get("townName"));
//                            break;
//                        case 4:
//                            result.put("createName", userInfo.get("villageName"));
//                            break;
//                        default:
//                            result.put("createName", userInfo.get("userName"));
//                            break;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(),e);
//        }
//        return result;
//    }
    @RequestMapping("/getMessageById")
    @ResponseBody
    public JSONObject getMessageById(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            String messageId = request.getParameter("messageId");
            Messages messages = messagesMapper.selectByPrimaryKey(messageId);
            if(null != messages){
                MessageToUser toUser = new MessageToUser();
                toUser.setReviceFlag((byte)1);
                toUser.setMessageId(messageId);
                //toUser.setReadFlag((byte)1);
                toUser.setUserId(user.getId());
                messageToUserMapper.updateInfoByMessageAndUserId(toUser);
                result.put("title", messages.getTitle());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                result.put("createTime", format.format(messages.getCreateTime()));
                result.put("id", messages.getId());
                List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId1(messages.getUserId());//update
                if(null != namelist){
                    Map<String, Object> userInfo = namelist.get(0);
                    switch (messages.getCreateUtp()) {
                        case 6:
                            result.put("createName", userInfo.get("districtName"));
                            break;
                        case 3:
                            result.put("createName", userInfo.get("townName"));
                            break;
                        case 4:
                            result.put("createName", userInfo.get("villageName"));
                            break;
                        case 10:
                        	result.put("createName", userInfo.get("tradeName"));// add 
                            break;
                        case 5:
                        	result.put("createName", userInfo.get("companyName"));// add 
                            break;
                        default:
                            result.put("createName", userInfo.get("userName"));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    } 
    /**
     * 消息详情页
    * @param request
    * @param model
    * @return
     */
    @RequestMapping("/messageDetail")
    public String messageDetail(HttpServletRequest request,Model model){
        String messageId = request.getParameter("messageId");
        String from = request.getParameter("from");
        User user = getLoginUser(request);
        if(null != user){
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("messageId", messageId);
            params.put("reviceUserId", user.getId());
            Messages messages = null;
            if("0".equals(from)){
                messages = messagesMapper.selectByPrimaryKey(messageId);
            }else{
                messages = messagesMapper.getMessageByCondition(params);
            }
            if(null != messages){
                MessageToUser toUser = messageToUserMapper.getMessageStatus(params);
                if(null != toUser && toUser.getReadFlag().equals((byte)0)){
                    toUser.setReadFlag((byte)1);
                    toUser.setReadTime(new Date());
                    toUser.setReviceFlag((byte)1);
                    messageToUserMapper.updateInfoByMessageAndUserId(toUser);
                }
                model.addAttribute("message", messages);
                if(!StringUtils.isEmpty(messages.getFiles())){
                    model.addAttribute("files", JSONArray.parseArray(messages.getFiles()));
                }
                model.addAttribute("from", from);
                return "village/userGroup/messageDetail";
            }
        }
        return "";
    }
    
    /**
     * 发送的消息列表
    * @param request
    * @param model
    * @return
     */
    @RequestMapping("/sendMessageList")
    public String sendMessageList(HttpServletRequest request,Model model){
        
        return "village/userGroup/sendList";
    }
    
    /**
     * 获取接收消息的列表
    * @param request
    * @return
     */
    @RequestMapping("/getSendList")
    @ResponseBody
    public JSONObject getSendList(HttpServletRequest request){
        String pages = request.getParameter("page");
        String limits = request.getParameter("limit");
        String title = request.getParameter("title");
        JSONObject result = new JSONObject();
        Integer page = 1;
        Integer limit = 10;
        User user = getLoginUser(request);
        if(!StringUtils.isEmpty(pages)){
            page = Integer.parseInt(pages);
        }
        if(!StringUtils.isEmpty(limits)){
            limit = Integer.parseInt(limits);
        }
        if(null != user){
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("page", (page-1)*limit);
            params.put("limit", limit);
            if(!StringUtils.isEmpty(title)){
                try {
                    params.put("title", URLDecoder.decode(URLDecoder.decode(title, "utf-8"), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage(),e);
                }
            }
            params.put("userId", user.getId());
            result.put("total", messagesMapper.getSendMessagesCount(params));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            List<Map<String, Object>> list = messagesMapper.getSendMessages(params);
            if(null != list && list.size() > 0){
                for(Map<String, Object> map:list){
                    if(null != map){
                        if(null != map.get("createTime")){
                            map.put("createTime", format.format((Date)map.get("createTime")));
                        }else{
                            map.put("createTime", "--");
                        }
                    }
                }
            }
            result.put("list", list);
        }
        
        return result;
    }
    
    /**
     * 收到的消息列表
    * @param request
    * @param model
    * @return
     */
    @RequestMapping("/reviceMessageList")
    public String reviceMessageList(HttpServletRequest request,Model model){
        
        return "village/userGroup/reviceList";
    }
    
//    /**
//     * 获取接收消息的列表
//    * @param request
//    * @return
//     */
//    @RequestMapping("/getReviceList")
//    @ResponseBody
//    public JSONObject getReviceList(HttpServletRequest request){
//        String pages = request.getParameter("page");
//        String limits = request.getParameter("limit");
//        String title = request.getParameter("title");
//        JSONObject result = new JSONObject();
//        Integer page = 1;
//        Integer limit = 10;
//        User user = getLoginUser(request);
//        if(!StringUtils.isEmpty(pages)){
//            page = Integer.parseInt(pages);
//        }
//        if(!StringUtils.isEmpty(limits)){
//            limit = Integer.parseInt(limits);
//        }
//        if(null != user){
//            Map<String, Object> params = new HashMap<String,Object>();
//            params.put("page", (page-1)*limit);
//            params.put("limit", limit);
//            if(!StringUtils.isEmpty(title)){
//                try {
//                    params.put("title", URLDecoder.decode(URLDecoder.decode(title, "utf-8"), "utf-8"));
//                } catch (UnsupportedEncodingException e) {
//                    log.error(e.getMessage(),e);
//                }
//            }
//            params.put("userId", user.getId());
//            result.put("total", messagesMapper.getRecviceMessagesCount(params));
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            List<Map<String, Object>> list = messagesMapper.getRecviceMessages(params);
//            if(null != list && list.size() > 0){
//                for(Map<String, Object> map:list){
//                    if(null != map){
//                        map.put("desc", map.get("descr"));
//                        map.remove("descr");
//                        List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId(Integer.parseInt(map.get("createId").toString()));
//                        if(null != namelist){
//                            Map<String, Object> userInfo = namelist.get(0);
//                            switch (Integer.parseInt(map.get("createUserType").toString())) {
//                                case 6:
//                                    map.put("createName", userInfo.get("districtName"));
//                                    break;
//                                case 3:
//                                    map.put("createName", userInfo.get("townName"));
//                                    break;
//                                case 4:
//                                    map.put("createName", userInfo.get("villageName"));
//                                    break;
//                                default:
//                                    map.put("createName", userInfo.get("userName"));
//                                    break;
//                            }
//                        }
//                        map.remove("createUserType");
//                        map.put("createTime", format.format((Date)map.get("createTime")));
//                    }
//                }
//            }
//            result.put("list", list);
//        }
//        
//        return result;
//    }
    /**
     * 获取接收消息的列表
    * @param request
    * @return
     */
    @RequestMapping("/getReviceList")
    @ResponseBody
    public JSONObject getReviceList(HttpServletRequest request){
        String pages = request.getParameter("page");
        String limits = request.getParameter("limit");
        String title = request.getParameter("title");
        JSONObject result = new JSONObject();
        Integer page = 1;
        Integer limit = 10;
        User user = getLoginUser(request);
        if(!StringUtils.isEmpty(pages)){
            page = Integer.parseInt(pages);
        }
        if(!StringUtils.isEmpty(limits)){
            limit = Integer.parseInt(limits);
        }
        if(null != user){
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("page", (page-1)*limit);
            params.put("limit", limit);
            if(!StringUtils.isEmpty(title)){
                try {
                    params.put("title", URLDecoder.decode(URLDecoder.decode(title, "utf-8"), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage(),e);
                }
            }
            params.put("userId", user.getId());
            result.put("total", messagesMapper.getRecviceMessagesCount(params));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            List<Map<String, Object>> list = messagesMapper.getRecviceMessages(params);
            if(null != list && list.size() > 0){
                for(Map<String, Object> map:list){
                    if(null != map){
                        map.put("desc", map.get("descr"));
                        map.remove("descr");
                        List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId1(Integer.parseInt(map.get("createId").toString()));//update
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
                                case 10:
                                	 map.put("createName", userInfo.get("tradeName"));// add 
                                    break;
                                case 5:
                               	 map.put("createName", userInfo.get("companyName"));// add 
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
            result.put("list", list);
        }
        
        return result;
    }
    /**
     * 删除接收的消息
    * @param request
    * @return
     */
    @RequestMapping("/toDeleteReviceMsg")
    @ResponseBody
    public JSONObject toDeleteReviceMsg(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            result.put("status", false);
            String messageId = request.getParameter("messageId");
            User user = getLoginUser(request);
            if(null != user){
                Map<String, Object> params = new HashMap<String,Object>();
                params.put("messageId", messageId);
                params.put("reviceUserId", user.getId());
                MessageToUser toUser = messageToUserMapper.getMessageStatus(params);
                if(null == toUser || toUser.getDelFlag().equals((byte)1)){
                    result.put("status", true);
                }else{
                    toUser.setDelFlag((byte)1);
                    result.put("status", messageToUserMapper.updateByPrimaryKeySelective(toUser)>0?true:false);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 删除接收的消息
    * @param request
    * @return
     */
    @RequestMapping("/toDeleteSendMsg")
    @ResponseBody
    public JSONObject toDeleteSendMsg(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            result.put("status", false);
            String messageId = request.getParameter("messageId");
            User user = getLoginUser(request);
            if(null != user){
                Messages messages = messagesMapper.selectByPrimaryKey(messageId);
                if(null == messages || messages.getDelFlag().equals((byte)1)){
                    result.put("status", true);
                }else{
                    messages.setDelFlag((byte)1);
                    result.put("status", messagesMapper.updateByPrimaryKeySelective(messages)>0?true:false);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
//    /**
//     * 获取未推送的消息
//    * @param request
//    * @return
//     */
//    @RequestMapping("/getUnReadMessage")
//    @ResponseBody
//    public JSONObject getUnReadMessage(HttpServletRequest request){
//        JSONObject result = new JSONObject();
//        try {
//            User user = getLoginUser(request);
//            if(null != user){
//                List<MessageToUser> list = messageToUserMapper.getUnReviceMessages(user.getId());
//                if(null != list && list.size() > 0){
//                    MessageToUser toUser = list.get(0);
//                    Messages messages = messagesMapper.selectByPrimaryKey(toUser.getMessageId());
//                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                    JSONObject object = new JSONObject();
//                    object.put("createTime", format.format(messages.getCreateTime()));
//                    object.put("id", messages.getId());
//                    object.put("title", messages.getTitle());
//                    List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId(messages.getUserId());
//                    if(null != namelist){
//                        Map<String, Object> userInfo = namelist.get(0);
//                        switch (messages.getCreateUtp()) {
//                            case 6:
//                                object.put("createName", userInfo.get("districtName"));
//                                break;
//                            case 3:
//                                object.put("createName", userInfo.get("townName"));
//                                break;
//                            case 4:
//                                object.put("createName", userInfo.get("villageName"));
//                                break;
//                            default:
//                                object.put("createName", userInfo.get("userName"));
//                                break;
//                        }
//                    }
//                    result.put("message", object);
//                    //for(MessageToUser mtu:list){
//                    toUser.setReviceFlag((byte)1);
//                        messageToUserMapper.updateByPrimaryKeySelective(toUser);
//                    //}
//                }
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(),e);
//        }
//        return result;
//    }
    /**
     * 获取未推送的消息
    * @param request
    * @return
     */
    @RequestMapping("/getUnReadMessage")
    @ResponseBody
    public JSONObject getUnReadMessage(HttpServletRequest request){
        JSONObject result = new JSONObject();
        try {
            User user = getLoginUser(request);
            if(null != user){
                List<MessageToUser> list = messageToUserMapper.getUnReviceMessages(user.getId());
                if(null != list && list.size() > 0){
                    MessageToUser toUser = list.get(0);
                    Messages messages = messagesMapper.selectByPrimaryKey(toUser.getMessageId());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    JSONObject object = new JSONObject();
                    object.put("createTime", format.format(messages.getCreateTime()));
                    object.put("id", messages.getId());
                    object.put("title", messages.getTitle());
                    List<Map<String, Object>> namelist = userGroupMapper.getVillageNameByUserId1(messages.getUserId());//update
                    if(null != namelist){
                        Map<String, Object> userInfo = namelist.get(0);
                        switch (messages.getCreateUtp()) {
                            case 6:
                                object.put("createName", userInfo.get("districtName"));
                                break;
                            case 3:
                                object.put("createName", userInfo.get("townName"));
                                break;
                            case 4:
                                object.put("createName", userInfo.get("villageName"));
                                break;
                            case 10:
                            	 object.put("createName", userInfo.get("tradeName"));// add 
                                break;
                            case 5:
                           	 	object.put("createName", userInfo.get("companyName"));// add 
                               break;
                            default:
                                object.put("createName", userInfo.get("userName"));
                                break;
                        }
                    }
                    result.put("message", object);
                    //for(MessageToUser mtu:list){
                    toUser.setReviceFlag((byte)1);
                        messageToUserMapper.updateByPrimaryKeySelective(toUser);
                    //}
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
}
