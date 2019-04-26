/**  
 * Copyright(c)2015 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.util;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.web.bean.AccessTokenBean;
import com.spring.web.bean.WebAccessTokenBean;
import com.spring.web.bean.WebWechatMessageBean;
import com.spring.web.bean.WebWechatUserDetailBean;
import com.spring.web.bean.WechatUserDetailBean;

/**
 * 微信验证,通过token
 */
public class AccessTokenUtil {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    /**
     * @param 获取微信公众号access_token ，接口有数目限制，存入全局变量中
     *        并判断是否失效
     * @return
     */
    public static AccessTokenBean getGZHAccessToken(HttpServletRequest request) {
        Boolean isOn = true;
        String access_token_str = "";
        ServletContext sc = request.getSession().getServletContext();
        if (null != sc.getAttribute("gzh_access_token_str")
                && !"".equals((String) sc.getAttribute("gzh_access_token_str"))) {
            Long t = (Long) sc.getAttribute("gzh_time");
            Long now = new Date().getTime();
            if (now > t) { // 失效了
                isOn = false;
            } else {
                access_token_str = (String) sc.getAttribute("gzh_access_token_str");
            }
        }
        AccessTokenBean at = null;
        if (isOn) {
            at = gson.fromJson(access_token_str, AccessTokenBean.class);
            if (null == at) {
                isOn = false;
            }
        }
        // TODO 失效的话在重新查询token,并进行ServletContext域中,并返回
        if (!isOn) {
            access_token_str = WeixinUtil
                    .doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx8f7d4dface233627&secret=c358d925d22da3b8f82a8b45fd89c7aa",
                            "", "UTF8", true);
            sc.setAttribute("gzh_access_token_str", access_token_str);
            sc.setAttribute("gzh_time", DateConvertUtil.addHours(new Date(), 1).getTime());
        }
        return gson.fromJson(access_token_str, AccessTokenBean.class);
    }

    /**
     * 获取微信公众接口 ticket
     */
    public static String getWeixinJSTicket(HttpServletRequest request) throws NoSuchAlgorithmException {
        String jsapi_ticket = "";
        ServletContext sc = request.getSession().getServletContext();
        Boolean isOn = true;
        if (null != sc.getAttribute("jsapi_ticket_time")) {
            Long t = (Long) sc.getAttribute("jsapi_ticket_time");
            Long now = new Date().getTime();
            if (now > t) { // 失效了
                isOn = false;
            } else {
                jsapi_ticket = (String) sc.getAttribute("jsapi_ticket");
            }
        } else {
            isOn = false;
        }
        // 失效后 重新获取access_token ticket
        if (!isOn) {
            String access_token = getGZHAccessToken(request).getAccess_token();
            String ticketJson = WeixinUtil.doGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                    + access_token + "&type=jsapi", "", "UTF8", true);
            @SuppressWarnings("unchecked")
            Map<String, String> mapS = gson.fromJson(ticketJson, Map.class);
            jsapi_ticket = mapS.get("ticket");
            sc.setAttribute("jsapi_ticket", jsapi_ticket);
            sc.setAttribute("jsapi_ticket_time", DateConvertUtil.addHours(new Date(), 1).getTime());
        }
        return jsapi_ticket;
    }

    /**
     * @param 微信公众号access_token ,openId获取用户信息
     * @return
     */
    public static WechatUserDetailBean getGZHUserInfo(String access_token, String openId) {
        String userinfo1 = WeixinUtil.doGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_token
                + "&openid=" + openId + "&lang=zh_CN", "", "UTF8", true);
        return gson.fromJson(userinfo1, WechatUserDetailBean.class);
    }

    /**
     * @param 微信公众号openId获取用户信息
     * @return
     */
    public static WechatUserDetailBean getGZHUserInfo(HttpServletRequest request, String openId) {
        AccessTokenBean at = getGZHAccessToken(request);
        return getGZHUserInfo(at.getAccess_token(), openId);
    }

    /**
     * @param 网页授权code获取openid access_token
     * @return
     */
    public static WebWechatMessageBean getWYSQOpenId(String code) {
        String openidstr = WeixinUtil.doGet(
                "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx8f7d4dface233627&secret=c358d925d22da3b8f82a8b45fd89c7aa"
                        + "&code=" + code + "&grant_type=authorization_code", "", "UTF8", true);
        return gson.fromJson(openidstr, WebWechatMessageBean.class);
    }

    /**
     * @param 微信公众号access_token ,openId获取用户信息
     * @return
     */
    public static WebWechatUserDetailBean getWYSQUserInfo(String access_token, String openId) {
        String userinfo1 = WeixinUtil.doGet("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token
                + "&openid=" + openId + "&lang=zh_CN", "", "UTF8", true);
        return gson.fromJson(userinfo1, WebWechatUserDetailBean.class);
    }

    /**
     * @param 微信公众号code获取用户信息
     * @return
     */
    public static WebWechatUserDetailBean getWYSQUserInfo(String code) {
        WebWechatMessageBean wwm = getWYSQOpenId(code);
        return getWYSQUserInfo(wwm.getAccess_token(), wwm.getOpenid());
    }

    /**
     * @param 网页联合登陆授权code获取openid access_token
     * @return
     */
    public static WebAccessTokenBean getLHDLQOpenId(String code) {
        String access_token_str = WeixinUtil
                .doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxe6d7d57d1d581ccc&secret=2cd5ccf4ea93b0938d79b5233775e451&code="
                        + code + "&grant_type=authorization_code", "", "UTF8", true);
        return gson.fromJson(access_token_str, WebAccessTokenBean.class);
    }

    /**
     * @param 网页联合登陆授权获取用户信息
     * @return
     */
    public static WechatUserDetailBean getLHDLQUserInfo(String access_token, String openId) {
        String userDetail = WeixinUtil.doGet("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token
                + "&openid=" + openId, "", "UTF8", true);
        return gson.fromJson(userDetail, WechatUserDetailBean.class);
    }

    /**
     * @param 网页联合登陆授权code获取用户信息
     * @return
     */
    public static WechatUserDetailBean getLHDLQUserInfo(String code) {
        WebAccessTokenBean wat = getLHDLQOpenId(code);
        return getLHDLQUserInfo(wat.getAccess_token(), wat.getOpenid());
    }

    public static class Xx {
        String content;

        Xx(String content) {
            this.content = content;
        }
    }

    public static void sendWechatMessage(String content, String openId, HttpServletRequest request) {
        AccessTokenBean access = AccessTokenUtil.getGZHAccessToken(request);
        String json = "{\"touser\": \"" + openId + "\",\"msgtype\": \"text\", \"text\": {\"content\": \"" + content
                + "\"}}";
        String xx = WeixinUtil.sendPost(
                "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + access.getAccess_token(), json);
        System.out.println(xx);
    }
}
