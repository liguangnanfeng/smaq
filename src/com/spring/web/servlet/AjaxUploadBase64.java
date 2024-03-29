package com.spring.web.servlet;

import com.spring.web.result.Result;
import com.spring.web.result.ResultImpl;
import com.spring.web.util.OutPrintUtil;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@WebServlet("/ajaxUploadBase64")
public class AjaxUploadBase64 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Result result = new ResultImpl();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String data = request.getParameter("data");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + ".jpeg";
        // 设置保存文件路径
        String realPath = request.getServletContext().getRealPath("/upload");
        String saveUrl = request.getContextPath() + "/upload/";
        if (!GenerateImage(data, realPath + "/" + newFileName)) {
            result.setStatus("1");
            result.setMap("message", "请上传gif,jpg,jpeg,png,bmp格式图片。");
            OutPrintUtil.OutPrint(response, result);
        } else {
            result.setMap("fileName", saveUrl + newFileName);
            OutPrintUtil.OutPrint(response, result);
        }
    }

    public static boolean GenerateImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null || null == imgFilePath) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b, 0, b.length);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}