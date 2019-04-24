package com.spring.web.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
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
import org.json.simple.JSONObject;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @Title: UploadServlet
 * @Description: 上传服务
 * @author FL
 * @date 2017年7月10日 下午3:08:29
 * @version V1.0
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        /**
         * KindEditor JSP 本JSP程序是演示程序，建议不要直接在实际项目中使用。 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
         */
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 文件保存目录路径
        String savePath = request.getServletContext().getRealPath("/") + "upload/";
        // 文件保存目录URL
        String saveUrl = request.getContextPath() + "/upload/";
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "gif,jpg,jpeg,png,bmp,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,mp4,mp3,pdf");
        // 最大文件大小
        long maxSize = 500000000;
        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println(getError("请选择文件。"));
            return;
        }
        // 检查目录
        File uploadDir = new File(savePath);
        if (!uploadDir.isDirectory()) {
            out.println(getError("上传目录不存在。"));
            return;
        }
        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            out.println(getError("上传目录没有写权限。"));
            return;
        }
        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            out.println(getError("目录名不正确。"));
            return;
        }
        // 创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> items;
        try {
            items = upload.parseRequest(request);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                String fileName = item.getName();
                if (!item.isFormField()) {
                    // 检查文件大小
                    if (item.getSize() > maxSize) {
                        out.println(getError("上传文件大小超过限制。"));
                        return;
                    }
                    // 检查扩展名
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                        out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                        return;
                    }
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                    try {
                        File uploadedFile = new File(savePath, newFileName);
                        if (dirName.equals("image")) {
                            if (item.getSize() > 500 * 1024) {
                                item.write(uploadedFile); // 源文件
                                compressPic(uploadedFile, savePath + newFileName, item.getSize() > 1024l * 1024l ? "1"
                                        : "2", 0.9f);
                                // 压缩文件 大于1m 速度优先 0.9压缩质量 大小为300k左右
                            } else {
                                item.write(uploadedFile); // 源文件
                            }
                        } else {
                            item.write(uploadedFile); // 源文件
                        }
                    } catch (Exception e) {
                        out.println(getError("上传文件失败。"));
                        e.printStackTrace();
                        return;
                    }
                    JSONObject obj = new JSONObject();
                    obj.put("error", 0);
                    obj.put("url", saveUrl + newFileName);
                    obj.put("fileName", fileName);
                    out.println(obj.toString());
                }
            }
        } catch (FileUploadException e1) {
            out.println(getError("上传文件失败。"));
            e1.printStackTrace();
            return;
        }
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

    @SuppressWarnings("unchecked")
    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toString();
    }

    public void copy(File file, String fileName) {
        try {
            FileInputStream in = new java.io.FileInputStream(file);
            FileOutputStream out = new FileOutputStream(fileName);
            byte[] bt = new byte[1024];
            int count;
            while ((count = in.read(bt)) > 0) {
                out.write(bt, 0, count);
            }
            in.close();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
