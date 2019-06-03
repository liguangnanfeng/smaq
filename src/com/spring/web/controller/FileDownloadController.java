package com.spring.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.spring.web.exception.LlSystemException;
import com.spring.web.util.MessageConstantsUtil;
import com.spring.web.util.StackTraceUtil;

/**
 * @Title: FileDownloadController
 * @Description: 文件下载控制器
 * @author FL
 * @date 2016年1月19日 下午4:57:01
 * @version V1.0
 */
@Controller
@RequestMapping("/download")
public class FileDownloadController {
    /**
     * 日志记录器
     */
    private static Logger log = Logger.getLogger(FileDownloadController.class);
    /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Method for handling file download request from client
     */
    @RequestMapping(value = "/file/{filePath}/{fileName}/{fileExtends}", method = RequestMethod.GET)
    public void doDownload(@PathVariable("filePath") String filePath, @PathVariable("fileName") String fileName,
            @PathVariable("fileExtends") String fileExtends, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.info("download file start...");
        int type = 0;
        do {
            if (StringUtils.isNotBlank(filePath) && StringUtils.isNotBlank(fileName)) {
                log.info("down...");
                // get absolute path of the application
                ServletContext context = request.getServletContext();
                String appPath = context.getRealPath("/upload/");
                if ("txt".equals(filePath)) {
                    appPath = context.getAttribute("txt_path").toString();
                    appPath = StringUtils.substring(appPath, 0, (appPath.indexOf("txt") - 1));
                }
                log.info("appPath = " + appPath);
                // construct the complete absolute path of the file
                fileName = URLDecoder.decode(fileName, "UTF-8");
                String fullPath = appPath + "/" + filePath + "/" + fileName;
                File downloadFile = new File(fullPath);
                if (!downloadFile.exists()) {
                    type = 1;
                    break;
                }
                FileInputStream inputStream = new FileInputStream(downloadFile);
                // get MIME type of the file
                String mimeType = context.getMimeType(fullPath);
                if (mimeType == null) {
                    // set to binary type if MIME mapping not found
                    mimeType = "application/octet-stream";
                }
                log.info("MIME type: " + mimeType);
                // set content attributes for the response
                response.setContentType(mimeType);
                response.setContentLength((int) downloadFile.length());
                // set headers for the response
                fileName = StringUtils.trim(downloadFile.getName());
                String formatFileName = encodingFileName(fileName);
                // 在后面定义方法encodingFileName(String fileName);
                // response.setHeader("Content-Disposition", "attachment; filename=" + formatFileName );
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", formatFileName);
                response.setHeader(headerKey, headerValue);
                // get output stream of the response
                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                // write bytes read from the input stream into the output stream
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outStream.close();
            }
        } while (false);
        if (type == 1) {
            log.info("文件不存在");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            out.println("<HTML>");
            out.println(" <HEAD><TITLE>错误</TITLE>");
            out.println("<script type=\"text/javascript\" src=\"");
            out.println(request.getContextPath());
            out.println("/js/jquery-1.8.3.min.js\"></script>\r\n");
            out.println("<script type=\"text/javascript\" src=\"");
            out.println(request.getContextPath());
            out.println("/lhgdialog/lhgdialog/lhgdialog.min.js?skin=iblue\"></script>\r\n");
            out.println("</HEAD>");
            out.println(" <BODY>");
            out.println("<script type=\"text/javascript\">");
            out.println("$.dialog.alert('当前文件不存在');");
            out.println("</script>");
            out.println(" </BODY>");
            out.println("</HTML>");
            out.flush();
            out.close();
        }
        log.info("download file end!");
    }

    // 处理文件名中出现的空格
    // 其中%20是空格在UTF-8下的编码
    public static String encodingFileName(String fileName) throws Exception {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            log.error(StackTraceUtil.getStackTrace(e));
            log.info("Don't support this encoding ...");
            throw new LlSystemException(MessageConstantsUtil.DOWNLOAD_FILE_EX);
        }
        return returnFileName;
    }
}
