package com.spring.web.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: WeixinUtil
 * @Description: 微信工具类
 * @author FL
 * @date 2017年7月10日 下午1:39:10
 * @version V1.0
 */
public final class WeixinUtil {
    private static Log log = LogFactory.getLog(WeixinUtil.class);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

    /***
     * 32位MD5加密算法
     * @param sourceStr
     * @return
     */
    public static String MD5(String sourceStr) {
        String result = "";//通过result返回结果值
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");//1.初始化MessageDigest信息摘要对象,并指定为MD5不分大小写都可以
            md.update(sourceStr.getBytes());//2.传入需要计算的字符串更新摘要信息，传入的为字节数组byte[],将字符串转换为字节数组使用getBytes()方法完成
            byte b[] = md.digest();//3.计算信息摘要digest()方法,返回值为字节数组

            int i;//定义整型
            //声明StringBuffer对象
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];//将首个元素赋值给i
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");//前面补0
                buf.append(Integer.toHexString(i));//转换成16进制编码
            }
            result = buf.toString();//转换成字符串
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result.toUpperCase();//返回结果
    }
    /**
     * 执行一个HTTP GET请求，返回请求响应的HTML
     * 
     * @param url 请求的URL地址
     * @param queryString 请求的查询参数,可以为null
     * @param charset 字符集
     * @param pretty 是否美化
     * @return 返回请求响应的HTML
     */
    public static String doGet(String url, String queryString, String charset, boolean pretty) {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        try {
            if (StringUtils.isNotBlank(queryString)) {
                // 对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
                method.setQueryString(URIUtil.encodeQuery(queryString));
            }
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                        charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty)
                        response.append(line).append(System.getProperty("line.separator"));
                    else
                        response.append(line);
                }
                reader.close();
            }
        } catch (URIException e) {
            log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
        } catch (IOException e) {
            log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
        } finally {
            method.releaseConnection();
        }
        return response.toString();
    }

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML
     * 
     * @param url 请求的URL地址
     * @param params 请求的查询参数,可以为null
     * @param charset 字符集
     * @param pretty 是否美化
     * @return 返回请求响应的HTML
     */
    public static String doPost(String url, Map<String, String> params, String charset, boolean pretty) {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        HttpMethod method = new PostMethod(url);
        // 设置Http Post数据
        if (params != null) {
            HttpMethodParams p = new HttpMethodParams();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                p.setParameter(entry.getKey(), entry.getValue());
            }
            method.setParams(p);
        }
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                        charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty)
                        response.append(line).append(System.getProperty("line.separator"));
                    else
                        response.append(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            log.error("执行HTTP Post请求" + url + "时，发生异常！", e);
        } finally {
            method.releaseConnection();
        }
        return response.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            OutputStreamWriter outWriter = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            out = new PrintWriter(outWriter);
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("touser", "openid");
        map.put("msgtype", "普通用户text");
        map.put("content", "文本消息内容");
        // String y = doGet("http://127.0.0.1:8080/AIRP/servletTest", "xx=00", "GBK", true);
        String z = doPost(
                "https://api.weixin.qq.com/cgi-bin/message/custom/send?&body=0&access_token=M72spOY-vguUeD-SvG5d5DZScRF3WxsaIj-91sAKYCtDqJnORfMx3W0v7pfrPqCoif3yHAvL_aQ6mFscJWCTUqSTkd65G0MvGzKoyiOT2Dk",
                map, "GBK", false);
        // System.out.println(y);
        System.out.println(z);
    }

    // sha1加密
    public static String EncryptSHA1(String content) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(content.getBytes());
        byte[] digest = md.digest();
        int len = digest.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(digest[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[digest[j] & 0x0f]);
        }
        return buf.toString();
    }
}