/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.util.OutPrintUtil;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/ajaxUploadFile")
public class AjaxUploadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static long maxSize = 1048576000;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 需要返回的fileName
        String fileName = null;
        Result result = new ResultImpl();
        if (!ServletFileUpload.isMultipartContent(request)) {
            try {
                result.setStatus("1");
                result.setMap("message", "请选择文件。");
                OutPrintUtil.OutPrint(response, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> items = upload.parseRequest(request);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            for (FileItem item : items) {
                // 其他参数
                String type = item.getContentType();
                if (type == null) {
                    continue;
                }
                // 文件参数
                fileName = item.getName();
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (item.getSize() > maxSize) {
                    result.setStatus("1");
                    result.setMap("message", "不能超出100M大小。");
                    OutPrintUtil.OutPrint(response, result);
                    return;
                }
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                // 设置保存文件路径
                String realPath = request.getServletContext().getRealPath("/upload");
                String saveUrl = request.getContextPath() + "/upload/";
                File dir = new File(realPath);
                File f = new File(dir, newFileName);
                item.write(f);
                result.setMap("fileName", saveUrl + newFileName);
                result.setMap("oldName", fileName);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回结果
        OutPrintUtil.OutPrint(response, result);
    }
}