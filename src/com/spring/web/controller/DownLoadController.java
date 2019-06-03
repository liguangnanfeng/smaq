
package com.spring.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.druid.util.StringUtils;
import com.spring.web.BaseController;

@RequestMapping("download")
@Controller
public class DownLoadController extends BaseController{
    
    @RequestMapping("do")
    public String downLoad(HttpServletRequest request,HttpServletResponse resp){
         FileInputStream input = null;
        try {
            String filename = request.getParameter("fileName");
            String url = request.getParameter("url");
            String path = request.getSession().getServletContext().getRealPath("/newUpload");
            if(!StringUtils.isEmpty(filename)){
                filename = URLDecoder.decode(URLDecoder.decode(filename, "utf-8"),"utf-8");
            }
            //根据文件名获取 MIME 类型
            String contentType = request.getServletContext().getMimeType(filename);

            String contentDisposition = "attachment;filename="+filename;

            // 输入流
            input = new FileInputStream(path+url);

            // 设置头
            resp.setHeader("Content-Type",contentType);
            resp.setHeader("Content-Disposition",contentDisposition);

            // 获取绑定了客户端的流
            ServletOutputStream output = resp.getOutputStream();

            // 把输入流中的数据写入到输出流中
            IOUtils.copy(input,output);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            
        }finally {
            try {
                if(null != input){
                    input.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return "";
    }
}
