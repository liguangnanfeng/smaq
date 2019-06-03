package com.spring.web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Title: OutPrintUtil
 * @Description: 输出工具类
 * @author FL
 * @date 2016年1月13日 下午2:41:32
 * @version V1.0
 */
public class OutPrintUtil {
    public static void OutPrint(HttpServletResponse response, Object obj) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String json = gson.toJson(obj);
        try {
            PrintWriter out = response.getWriter();
            out.println(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}