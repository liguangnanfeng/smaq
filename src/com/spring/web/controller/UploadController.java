
package com.spring.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.spring.web.BaseController;

/**
 * 上传文件
 */
@Controller
@RequestMapping("village")
public class UploadController extends BaseController{
    
    
    @RequestMapping(value="new/ueditorUpload",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file,HttpServletRequest request)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datePath = format.format(new Date());
        Map<String, Object> res= new HashMap<String,Object>();
        if (null != file && !file.isEmpty())
        {
            String path = request.getSession().getServletContext().getRealPath("/newUpload");
            File file2 = new File(path+File.separator+datePath);
            if(!file2.exists()){
                file2.mkdirs();
            }
            String url = "http://" + request.getServerName() + ":"+ request.getServerPort();
            String fileName = UUID.randomUUID().toString().replaceAll("-", "")+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            //使用输入流读取前台的file文件              
            try {
                file.transferTo(new File(path+File.separator+datePath+File.separator+fileName));
                res.put("url", url+"/newUpload/"+datePath+"/"+fileName);
                res.put("title", file.getOriginalFilename());
                res.put("original", file.getOriginalFilename());
                res.put("state", "SUCCESS");
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }

        }
        return res;
    }
    
    @RequestMapping(value="new/upload")
    @ResponseBody
    public Map<String, Object> newUpload(MultipartFile file,HttpServletRequest request)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datePath = format.format(new Date());
        Map<String, Object> res= new HashMap<String,Object>();
        if (null != file && !file.isEmpty())
        {
            String path = request.getSession().getServletContext().getRealPath("/newUpload");
            File file2 = new File(path+File.separator+datePath);
            if(!file2.exists()){
                file2.mkdirs();
            }
            String url = "http://" + request.getServerName() + ":"+ request.getServerPort()+"/download/do?url=";
            String fileName = UUID.randomUUID().toString().replaceAll("-", "")+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            //使用输入流读取前台的file文件              
            try {
                file.transferTo(new File(path+File.separator+datePath+File.separator+fileName));
                res.put("url", url+"/"+datePath+"/"+fileName+"&fileName="+ URLEncoder.encode(URLEncoder.encode(file.getOriginalFilename(),"utf-8"),"utf-8"));
                res.put("title", file.getOriginalFilename());
                res.put("original", file.getOriginalFilename());
                res.put("state", "SUCCESS");
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
        return res;
    }
    
    @RequestMapping(value="new/ueditorUpload",method=RequestMethod.GET)
    public String ueditorUpload(HttpServletRequest request){
        return "village/userGroup/ueditor/controller";
    }
    
}
