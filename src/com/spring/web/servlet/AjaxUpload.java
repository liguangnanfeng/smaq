/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.servlet;

import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.util.OutPrintUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/ajaxUpload")
public class AjaxUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static long maxSize = 104857600;
    private static String img = "gif,jpg,jpeg,png,bmp";

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
                result.setMap("message", "请选择图片。");
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
                if (img.indexOf(fileExt) < 0) {
                    result.setStatus("1");
                    result.setMap("message", "请上传gif,jpg,jpeg,png,bmp格式图片。");
                    OutPrintUtil.OutPrint(response, result);
                    return;
                }
                if (item.getSize() > maxSize) {
                    result.setStatus("1");
                    result.setMap("message", "图片不能超出10M大小。");
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
                String pp = realPath + "/" + newFileName;
                if (item.getSize() >  500 * 1024) {
                    // ImageTools.cutImage(300, 300, pp, pp);
                    compressPic(f, pp, item.getSize() >  1024 * 1024 ? "1" : "2", 0.9f);
                    // 压缩文件 大于1m 速度优先 0.9压缩质量 大小为300k左右
                }
                result.setMap("fileName", saveUrl + newFileName);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回结果
        OutPrintUtil.OutPrint(response, result);
    }

    public static void compressPic(File file, String fileName, String flag, Float rat) {
        try {
            Image img = ImageIO.read(file);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                System.out.println(" can't read,retry!" + "<BR>");
            } else {
                int width = img.getWidth(null);
                int height = img.getHeight(null);
                int s = 1;
                if (width > 900 || height > 768) {
                    // 为等比缩放计算输出的图片宽度及高度
                    int rate1 = width / 900;
                    int rate2 = height / 768;
                    // 根据缩放比率大的进行缩放控制
                    s = rate1 > rate2 ? rate1 : rate2;
                }
                width = width / s;
                height = height / s;
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                /*
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
                 */
                if (flag.equals("2")) {
                    tag.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
                } else {
                    tag.getGraphics().drawImage(img, 0, 0, width, height, null);
                }
                FileOutputStream out = new FileOutputStream(fileName);
                // JPEGImageEncoder可适用于其他图片类型的转换
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                // 压缩质量
                JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
                // 压缩质量
                jep.setQuality(rat, true);
                encoder.encode(tag, jep);
                out.close();
            }
            // System.out.println("压缩成功！");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}