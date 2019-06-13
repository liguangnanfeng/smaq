package com.spring.web.controller.api;

import com.spring.web.dao.AppTokenMapper;
import com.spring.web.listener.MySessionContext;
import com.spring.web.model.AppToken;
import com.spring.web.util.DateConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/07 16:42
 * 验证token
 */
@Component
@SuppressWarnings("all")
public class AppTokenData {

    @Autowired
    private AppTokenMapper appTokenMapper;


    /**
     * 登陆使用的方法
     * @param request
     * @return
     */
    public Object getAppUser(HttpServletRequest request){
        String access_token = request.getParameter("access_token");
        String sessionId = request.getParameter("sessionId");
      /*  if (StringUtils.isEmpty(access_token)) {
            return null;
        }

        AppToken at = appTokenMapper.selectByPrimaryKey(access_token);
        Date date = new Date();
        Date end = DateConvertUtil.addDays(at.getLastLoginTime(), at.getExpires());
        if (null == at || end.before(date)) {// 超时 或 不存在
            return null;
        }

        String x = DateFormatUtils.format(date, "yyyy-MM-dd");
        String y = DateFormatUtils.format(at.getLastLoginTime(), "yyyy-MM-dd");
        if (!x.equals(y)) {
            // 一天登陆多次的，只记录第一次登陆时间
            at.setLastLoginTime(date);
            appTokenMapper.updateByPrimaryKeySelective(at);
        }
*/
        //到域中获取数据
        MySessionContext sess = MySessionContext.getInstance();
        HttpSession session = sess.getSession(sessionId);
        Object attribute = null;
        if (null != access_token){
            attribute = session.getAttribute(access_token);// 获取session域中的信息
        }
        return attribute;
    }

    /**
     * 注销使用的方法
     */
    public Object delectUserId(HttpServletRequest request){

        String access_token = request.getParameter("access_token");
        String sessionId = request.getParameter("sessionId");
        // 没有传递数据
        if (StringUtils.isEmpty(access_token)||StringUtils.isEmpty(sessionId)) {
            return null;
        }
        // 登陆已经超时
        AppToken at = appTokenMapper.selectByPrimaryKey(access_token);
        Date date = new Date();
        Date end = DateConvertUtil.addDays(at.getLastLoginTime(), at.getExpires());
        if (null == at || end.before(date)) {// 超时 或 不存在
            return null;
        }

        MySessionContext myc= MySessionContext.getInstance();
        HttpSession sess = myc.getSession(sessionId);
        sess.removeAttribute(access_token);  //删除session
        myc.delSession(sess); //删除session

        request.getSession().removeAttribute(access_token);
        return "删除成功";
    }



}
