package com.spring.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.web.BaseController;
import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.GlobalRegion;
import com.spring.web.model.User;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.service.common.CommonService;
import com.spring.web.tobject.user.CompanyMapReqDTO;

/**
 * @Title: CommonController
 * @Description: 共通控制器
 * @author FL
 * @date 2016年1月19日 下午4:51:51
 * @version V1.0
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
    /**
     * @Fields serialVersionUID : 序列ID
     */
    private static final long serialVersionUID = -7196374659492891808L;
    @Autowired
    private CommonService commonService;

    /**
     * 出错
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String toError() throws Exception {
        return "/error";
    }

    /**
     * 超时
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/timedout")
    public String toTimedout() throws Exception {
        return "/timedout";
    }

    @RequestMapping(value = "/denied")
    public String toDenied() throws Exception {
        return "/denied";
    }

    /**
     * 首页面
     */
    @RequestMapping(value = "/index")
    public String index() throws Exception {
        return "/index";
    }
    /**
     * 区域联动 查找下一级
     */
    @RequestMapping(value = "/childGlobal")
    public @ResponseBody
    Result ChildGlobal(GlobalRegion gr) throws Exception {
        Result result = new ResultImpl();
        commonService.ChildGlobal(gr, result);
        return result;
    }

    /**
     * 区域联动 下级找上及
     */
    @RequestMapping(value = "/parentGlobal")
    public @ResponseBody
    Result ParentGlobal(GlobalRegion gr) throws Exception {
        Result result = new ResultImpl();
        commonService.ParentGlobal(gr, result);
        return result;
    }
    
    /**
     * 下载资料
     */
    @RequestMapping(value = "download")
    public void hedownload(String filename, String fileurl, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        File realPath = new File(request.getServletContext().getRealPath("/"), fileurl.replace("/smaq", ""));
        if(!realPath.exists()) {
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
     * 获取区信息
     */
    @RequestMapping(value = "/selectDistrict")
    public @ResponseBody Result selectDistrict(HttpServletRequest request) throws Exception {
        Result result = new ResultImpl();
        List<DynamicParameter<String, Object>> districtList = districtMapper.selectDistrict();
        result.setMap("list", districtList);
        return result;
    }
    
    /**
     * 根据区获取镇信息
     */
    @RequestMapping(value = "/getTownByArea")
    public @ResponseBody Result getTownByArea(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("districtId", id);
        List<Map<String, Object>> townList = townMapper.selectListByDistrict(m);
        result.setMap("list", townList);
        return result;
    }
    
    /**
     * 根据镇获取村信息
     */
    @RequestMapping(value = "/getVillageByTown")
    public @ResponseBody Result getVillageByTown(HttpServletRequest request, Integer id) throws Exception {
        Result result = new ResultImpl();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("townId", id);
        
        List<DynamicParameter<String, Object>> villageList = villageMapper.selectListByTown(m);
        for (int i = 0; i < villageList.size(); i++) {
            DynamicParameter<String, Object> item = villageList.get(i);
            item.put("villageId", item.get("userId").toString());
        }
        result.setMap("list", villageList);
        return result;
    }
    
    /**
     * 根据条件信息查询企业列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getCompanyByMap")
    public @ResponseBody Result getCompanyByMap(HttpServletRequest request, @RequestBody CompanyMapReqDTO dto) throws Exception {
        Result result = new ResultImpl();
        User user = getLoginUser(request);
        Map<String, Object> m = new HashMap<String, Object>();
        setUserId(user, m);
        m.put("companyName", dto.getCompanyName());
        if(m.get("districtId") == null){
            m.put("districtId", dto.getDistrictId());
        }
        if(m.get("townId") == null){
            m.put("townId", dto.getTownId());
        }
        if(m.get("villageId") == null){
            m.put("villageId", dto.getVillageId());
        }
        m.put("dlevel", dto.getDlevel());
        m.put("page", (dto.getPage()- 1) * dto.getLimit());
        m.put("limit", dto.getLimit());
        m.put("industry", dto.getIndustry());
        List<DynamicParameter<String, Object>> list = companyMapper.selectCompanyMap(m);
        Integer count = companyMapper.selectCountMap(m);
        for (int i = 0; i < list.size(); i++) {
            DynamicParameter<String, Object> item = list.get(i);
            item.put("id", i + 1 + "");
        }
        result.setMap("list", list);
        result.setMap("count", count);
        return result;
        
        
    }
}
