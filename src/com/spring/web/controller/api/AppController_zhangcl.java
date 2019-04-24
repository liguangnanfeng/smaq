
package com.spring.web.controller.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.AppToken;
import com.spring.web.model.TCheck;
import com.spring.web.model.TCheckDocument;
import com.spring.web.model.TCleanWarnSetting;
import com.spring.web.model.TRectification;
import com.spring.web.model.User;
import com.spring.web.result.AppResult;
import com.spring.web.result.AppResultImpl;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.cgf.CgfService;
import com.spring.web.tobject.cgf.RecheckSaveReqDTO;
import com.spring.web.util.DateConvertUtil;

/** 
* @Title: AppController_zhangcl
* @Description: TODO(用一句话描述该文件做什么) 
* @date 2018年5月29日 上午10:07:33 
* @version V1.0   
*/
@Controller
@RequestMapping("api/tongx")
public class AppController_zhangcl extends BaseController{

    private static final long serialVersionUID = 1L;
    @Autowired
    private CgfService cgfService;
    
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
    
//    /**
//     * 根据checkId获取检查表数据
//     */
//    @RequestMapping(value = "A001")
//    public  @ResponseBody AppResult recheckAdd(HttpServletRequest request,Integer checkId) throws Exception {
//    	
//    	AppResult result = new AppResultImpl();
//        Map<String, Object> m = new HashMap<String, Object>();
//        
////        Integer _userId = getAppUserId(request);
////        if (null == _userId) {
////            result.setStatus("2");
////            result.setMessage("登录超时");
////            return result;
////        }
////        if(null == userId){
////            setUserId(_userId, m);
////        }else{
////            m.put("userId", userId);
////        }
////        m.put("userId", 26);//debug
//        
//        List<Map<String, Object>> list = tCheckItemMapper.selectDangerByCheckId(checkId, 2);//status 2:不合格
//        log.error("list:"+list);
//        result.setData(list);
//        
//        return result;
//    }
    
    /**
     * 获取待整改信息
     */
    @RequestMapping(value = "A002")//参照pc端villagecontrol的checkRectification
    public @ResponseBody AppResult getRectification(HttpServletRequest request,Integer id) {
    	log.error("A002");
    	AppResult result = new AppResultImpl();
    	Map<String, Object> map = new HashMap<String, Object>();
//    	Integer _userId = getAppUserId(request);
//    	if (null == _userId) {
//    		result.setStatus("2");
//    		result.setMessage("登录超时");
//    		return result;
//    	}
    	try{
    	int flag = 1;//企业自查
        TRectification rectification = tRectificationMapper.selectByCheckId(id);
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        map.put("check", check);
        map.put("itemL", tCheckItemMapper.selectDangerByCheckId(id, null));
        map.put("rectification", rectification);
        map.put("company", companyMapper.selectByPrimaryKey(check.getInteger("userId")));
        map.put("flag", flag);
        map.put("serList", gson.toJson(tItemSeriousMapper.selectbylid(null)));
        map.put("listM", tCheckMapper.selectCompany(id));
        result.setData(map);
    	}catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		result.setStatus("1");
    		result.setMessage("获取失败");
    		return result;
    	}
        return result;
    }
    
    /**
     * 检查表隐患 整改计划保存
     */
    @RequestMapping(value = "A003")//参照companycontroller_cd的rectificationSave
    public @ResponseBody AppResult saveRectification(TRectification tr, HttpServletRequest request){
        log.error("A003");
    	AppResult result = new AppResultImpl();
    	
//    	Integer _userId = getAppUserId(request);
//    	if (null == _userId) {
//    		result.setStatus("2");
//    		result.setMessage("登录超时");
//    		return result;
//    	}
//    	tr.setCreateUser(_userId);
    	tr.setCreateUser(26);//debug
    	try{
        
    	log.error(tr.toString());
        cgfService.rectificationSave(tr);
    }catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		result.setStatus("1");
		result.setMessage("更新失败");
		return result;
	}
        return result;
    }
    
    /**
     * 隐患列表 获取复查内容
     */
    @RequestMapping(value = "A004")//参照pc端companycontroller_cd的recheckAdd
    public @ResponseBody AppResult recheckAdd(HttpServletRequest request,Integer checkId) {
    	log.error("A004");
    	AppResult result = new AppResultImpl();
//      Integer _userId = getAppUserId(request);
//      if (null == _userId) {
//          result.setStatus("2");
//          result.setMessage("登录超时");
//          return result;
//      }
    	try{
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, 2));//status 2:不合格
        Integer id = checkId;
        DynamicParameter<String, Object> check = tCheckMapper.selectCompany(id);
        map.put("check", check);
        map.put("company", companyMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(check.get("userId")))));
        map.put("now", new Date());
        result.setData(map);
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus("1");
    		result.setMessage("获取失败");
    		return result;
		}
    	
        return result;
    }
    
    /**
     * 企业端保存复查内容，政府端保存复查内容
     */
    @RequestMapping(value = "A005")//参照pc端companycontroller_cd的recheckSave
    public @ResponseBody AppResult recheckSave(@RequestBody RecheckSaveReqDTO dto, HttpServletRequest request) {
    	log.error("A005");
    	AppResult result = new AppResultImpl();
        
//    	Integer _userId = getAppUserId(request);
//    	if (null == _userId) {
//    		result.setStatus("2");
//    		result.setMessage("登录超时");
//    		return result;
//    	}
//    	dto.getRecheck().setCreateUser(_userId);

//    	log.error("appuserid:"+_userId);
    	
    	dto.getRecheck().setCreateUser(26);//debug 1116下午接口出错，将上面注释打开，这句给注释


        try {
			cgfService.recheckSave(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus("1");
    		result.setMessage("更新失败");
    		return result;
		}

        return result;
    }
    
    
    /**
     * 政府端整改指令书获取内容flag=1，复查意见书获取内容flag=2
     */
    @RequestMapping(value = "A006")//villagecontroller
    public @ResponseBody AppResult checkDocument(HttpServletRequest request,Integer checkId, Integer flag) {
    	log.error("A006");
    	AppResult result = new AppResultImpl();
    	Map<String, Object> map = new HashMap<String, Object>();
        
//    	Integer _userId = getAppUserId(request);
//    	if (null == _userId) {
//    		result.setStatus("2");
//    		result.setMessage("登录超时");
//    		return result;
//    	}
//    	log.error("appuserid:"+_userId);
    	
    	if (null == flag) {
            flag = 8;// 现场检查记录
        }
    	
    	try{
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("checkId", checkId);
        m.put("flag", flag);
        TCheckDocument doc = tCheckDocumentMapper.selectByCheckId(m);
        TCheck check = tCheckMapper.selectByPrimaryKey(checkId);
        map.put("timenow", new Date());
        int count = tRecheckMapper.selectByCheckId(checkId).size();
        map.put("count",count);
        if (null == doc) {// 第一次
            /*if(check.getFlag() == 3){
            cgfService.rectificationSave(tr);
            }*/
        	TRectification tr = tRectificationMapper.selectByCheckId(checkId);
            map.put("check", check);
            map.put("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
            map.put("itemL1", tCheckItemMapper.selectDangerByCheckId(checkId, 1));
            map.put("rectification", tr);
            map.put("company", companyMapper.selectByPrimaryKey(check.getUserId()));
            map.put("serList", gson.toJson(tItemSeriousMapper.selectbylid(null)));
            map.put("userId", check.getUserId());
            map.put("checkId", checkId);
        } else {
        	map.put("itemL", tCheckItemMapper.selectDangerByCheckId(checkId, null));
        	map.put("itemL1", tCheckItemMapper.selectDangerByCheckId(checkId, 1));
        	map.put("document", doc);
        }
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus("1");
    		result.setMessage("获取失败");
    		return result;
		}
    	result.setData(map);
        return result;
    }
    
    /**
     * @return 政府端整改指令书保存
     */
//    @RequestMapping(value = "A007")
//    public @ResponseBody AppResult checkDocumentSave(Integer checkId, Integer flag, String copy, HttpServletRequest request) {
//    	log.error("A007");
//    	AppResult result = new AppResultImpl();
//    	Map<String, Object> map = new HashMap<String, Object>();
//        
////    	Integer _userId = getAppUserId(request);
////    	if (null == _userId) {
////    		result.setStatus("2");
////    		result.setMessage("登录超时");
////    		return result;
////    	}
////    	log.error("appuserid:"+_userId);
//try{
//        TCheck c = tCheckMapper.selectByPrimaryKey(checkId);
//        Map<String, Object> m = new HashMap<String, Object>();
//        m.put("checkId", checkId);
//        m.put("flag", flag);
//        TCheckDocument doc = tCheckDocumentMapper.selectByCheckId(m);
//        if (null == doc) {// 第一次
//            User user = getLoginUser(request);
//            doc = new TCheckDocument();
//            doc.setCheckId(checkId);
//            doc.setFlag(flag);
//            doc.setCopy(copy);
//            doc.setCreateTime(new Date());
//            doc.setCreateUser(user.getId());
//            doc.setUserId(c.getUserId());
//            doc.setDel(0);
//            tCheckDocumentMapper.insertSelective(doc);
//        } else {
//            doc.setCopy(copy);
//            tCheckDocumentMapper.updateByPrimaryKeySelective(doc);
//        }
//    } catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		result.setStatus("1");
//		result.setMessage("更新失败");
//		return result;
//	}
//        return result;
//    }
    
    /**
     * 行政文书保存,整改意见书
     */
    @RequestMapping("A007")
    public @ResponseBody AppResult opinionSave(Integer userId, HttpServletRequest request, TCheckDocument tCheckDocument){
        AppResult result = new AppResultImpl();
        //User user = getLoginUser(request);
	Integer _userId = getAppUserId(request);
	if (null == _userId) {
		result.setStatus("2");
		result.setMessage("登录超时");
		return result;
	}
	log.error("appuserid:"+_userId);
	try{
        tCheckDocument.setUserId(userId);
        tCheckDocument.setCreateUser(_userId);
        tCheckDocument.setDel(0);
        tCheckDocument.setCreateTime(new Date());
        tCheckDocumentMapper.insertSelective(tCheckDocument);
        result.setStatus("0");
        result.setData(tCheckDocument);
      } catch (Exception e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		result.setStatus("1");
  		result.setMessage("更新失败");
  		return result;
  	}
        return result;
    }
    
    
    /**
     * APP 视频连接接口
     */
    @RequestMapping("A008")
    public @ResponseBody AppResult videoInfo(HttpServletRequest request,String access_token){
        AppResult result = new AppResultImpl();
        Map<String, Object> map = new HashMap<String, Object>();
        
        AppToken at = appTokenMapper.selectByPrimaryKey(access_token);
        Integer userId = at.getUserId();
        //Integer userId = 529;
        
        User user = userMapper.selectByPrimaryKey(userId);
        map.put("videoinfo",videoInfoMapper.selectByPrimaryKey(1));
        Map<String, Object> m = new HashMap<String, Object>();
        if (user.getUserType() == 5) {//企业
        	m.clear();
        	m.put("userId",userId);
//        	map.put("companyName", user.getUserName());//添加企业名称字段  wz add 190116
//          map.put("list", monitorMapper.selectByMap(m));
        	JSONArray array = new JSONArray();//将返回值字段调成一致
        	JSONObject obj = new JSONObject();
        	List<Map<String, Object>> list1 = monitorMapper.selectByMap(m);
        	String companyName = companyMapper.selectByPrimaryKey(userId).getName();
        	for(int i =0 ;i < list1.size();i++){
        		obj = new JSONObject();
        		obj.put("companyName",companyName);
            	obj.put("list",list1.get(i));
            	array.add(obj);	
        	}
        	map.put("list", array);
        }
        else {//政府端
        	m.clear();
        	m.put("companyName", user.getUserName());
            List<DynamicParameter<String, Object>> mlist = companyMapper.selectCompanyList(m);
            List<Integer> ulist = new ArrayList<Integer>();
            for(DynamicParameter<String, Object> l : mlist){
            	ulist.add(l.getInteger("userId"));
            }
            m.clear();
            m.put("userIds", ulist);
            //map.put("list", monitorMapper.selectByMap(m));
            
            //添加企业名称字段 wz add190116
            JSONArray array = new JSONArray();
            JSONObject object = new JSONObject();
            List<Map<String, Object>> list1 = monitorMapper.selectByMap(m);
            //log.error("list1："+list1.toString());
            for(int i =0 ;i < list1.size();i++){
            	object = new JSONObject();
            	Integer companyId = (Integer)list1.get(i).get("userId");
            	//log.error("companyId："+companyId);
            	object.put("companyName",companyMapper.selectByPrimaryKey(companyId).getName());
            	object.put("list",list1.get(i));
            	array.add(object);
            }
            map.put("list", array);
        }
        //log.error("map："+map.toString());
        result.setData(map);

        return result;
    } 
}
