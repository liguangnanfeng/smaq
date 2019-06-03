/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.*;

//import javax.mail.Address;

/**
 * @Title: MailUtil
 * @Description:邮箱工具类
 * @author FL
 * @date 2017年7月10日 下午1:32:36
 * @version V1.0
 */
public class MailUtil {
    /**
     * 发件人邮箱服务器
     */
    private String emailHost;
    /**
     * 发件人邮箱
     */
    private String emailFrom;
    /**
     * 发件人用户名
     */
    private String emailUserName;
    /**
     * 发件人密码
     */
    private String emailPassword;
    /**
     * 收件人邮箱，多个邮箱以“;”分隔
     */
    private String toEmails;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 邮件中的图片，为空时无图片。map中的key为图片ID，value为图片地址
     */
    private Map<String, String> pictures;
    /**
     * 邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址
     */
    private Map<String, String> attachments;

    public String getEmailHost() {
        if (StringUtils.isBlank(emailHost)) {
            emailHost = ConstantsUtil.EMAIL_HOST;
        }
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getEmailFrom() {
        if (StringUtils.isBlank(emailFrom)) {
            emailFrom = ConstantsUtil.EMAIL_FROM;
        }
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailUserName() {
        if (StringUtils.isBlank(emailUserName)) {
            emailUserName = ConstantsUtil.EMAIL_USERNAME;
        }
        return emailUserName;
    }

    public void setEmailUserName(String emailUserName) {
        this.emailUserName = emailUserName;
    }

    public String getEmailPassword() {
        if (StringUtils.isBlank(emailPassword)) {
            emailPassword = ConstantsUtil.EMAIL_PASSWORD;
        }
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getToEmails() {
        return this.toEmails;
    }

    public void setToEmails(String toEmails) {
        this.toEmails = toEmails;
    }

    public String getSubject() {
        if (StringUtils.isBlank(subject)) {
            subject = "无主题";
        }
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getPictures() {
        return pictures;
    }

    public void setPictures(Map<String, String> pictures) {
        this.pictures = pictures;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }

    /**
     * @throws Exception
     * @throws Exception
     */
    public void sendEmail() throws Exception {
        // Address[] validMailAddress = null; //返回未向其发送此消息的有效地址
        // String[] validMailStr = null;
        if (this.getEmailHost().equals("") || this.getEmailFrom().equals("") || this.getEmailUserName().equals("")
                || this.getEmailPassword().equals("")) {
            throw new RuntimeException("发件人信息不完全，请确认发件人信息！");
        }
        // 设置收件人邮箱
        String[] toEmailArray = toEmails.split(";");
        List<String> toEmailList = new ArrayList<String>();
        if (null == toEmailArray || toEmailArray.length <= 0) {
            throw new RuntimeException("收件人邮箱不得为空！");
        } else {
            for (String s : toEmailArray) {
                if (!s.equals("")) {
                    // 屏蔽收件人列表中重复的收件人
                    if (!toEmailList.contains(s))
                        toEmailList.add(s);
                }
            }
            if (null == toEmailList || toEmailList.size() <= 0) {
                throw new RuntimeException("收件人邮箱不得为空！");
            } else {
                toEmailArray = new String[toEmailList.size()];
                for (int i = 0; i < toEmailList.size(); i++) {
                    toEmailArray[i] = toEmailList.get(i);
                }
            }
        }
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost(emailHost);
        // 建立邮件消息
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try {
            // 设置发件人邮箱
            messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
            messageHelper.setFrom(emailFrom);
            // 邮件主题
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件
            messageHelper.setText(content, true);
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
            prop.put("mail.smtp.timeout", "25000");
            // 添加验证
            MyAuthenticatorUtil auth = new MyAuthenticatorUtil(emailUserName, emailPassword);
            Session session = Session.getDefaultInstance(prop, auth);
            senderImpl.setSession(session);
            StringBuffer ss = new StringBuffer();
            for (int i = 0; i < toEmailArray.length; i++) {
                if (!ss.toString().equals(""))
                    ss.append(",");
                ss.append(toEmailArray[i]);
                if ((i + 1) % 50 == 0 || (i + 1) == toEmailArray.length) {
                    // 每50个收件人一组发送邮件
                    messageHelper.setTo(ss.toString().split(","));
                    senderImpl.send(mailMessage);
                    ss.setLength(0);
                }
            }
        } catch (SendFailedException e) {
            LoggerUtil.cLog.info(StackTraceUtil.getStackTrace(e));
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            LoggerUtil.cLog.info(StackTraceUtil.getStackTrace(e));
        }
        /*
         * // 添加图片 if (null != pictures) { for (Iterator<Map.Entry<String, String>> it = pictures.entrySet() .iterator(); it.hasNext();) { Map.Entry<String, String> entry =
         * it.next(); String cid = entry.getKey(); String filePath = entry.getValue(); if (null == cid || null == filePath) { throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！"); }
         * File file = new File(filePath); if (!file.exists()) { throw new RuntimeException("图片" + filePath + "不存在！"); } FileSystemResource img = new FileSystemResource(file);
         * messageHelper.addInline(cid, img); } } // 添加附件 if (null != attachments) { for (Iterator<Map.Entry<String, String>> it = attachments .entrySet().iterator();
         * it.hasNext();) { Map.Entry<String, String> entry = it.next(); String cid = entry.getKey(); String filePath = entry.getValue(); if (null == cid || null == filePath) {
         * throw new RuntimeException("请确认每个附件的ID和地址是否齐全！"); } File file = new File(filePath); if (!file.exists()) { throw new RuntimeException("附件" + filePath + "不存在！"); }
         * FileSystemResource fileResource = new FileSystemResource(file); messageHelper.addAttachment(cid, fileResource); } }
         */
    }

    public void sendEmailByException(String[] validMailStr) throws Exception {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost(emailHost);
        // 建立邮件消息
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
        // 设置发件人邮箱
        messageHelper.setFrom(emailFrom);
        messageHelper.setTo(validMailStr);
        // 邮件主题
        messageHelper.setSubject(subject);
        // true 表示启动HTML格式的邮件
        messageHelper.setText(content, true);
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        // 添加验证
        MyAuthenticatorUtil auth = new MyAuthenticatorUtil(emailUserName, emailPassword);
        Session session = Session.getDefaultInstance(prop, auth);
        senderImpl.setSession(session);
        // 发送邮件
        senderImpl.send(mailMessage);
    }

    public static void main(String[] args) throws Exception {
        MailUtil mu = new MailUtil();
        test1(mu);
        // test2(mu);
        // test3(mu);
        // test4(mu);
        // test5(mu);
        // test6(mu);
    }

    public static void test1(MailUtil mu) throws Exception {
        String toEmails = "17553367@qq.com;13706180517@163.com";
        String subject = "第一封，简单文本邮件";
        StringBuilder builder = new StringBuilder();
        builder.append("我相信天上不会掉馅饼");
        String content = builder.toString();
        mu.setToEmails(toEmails);
        mu.setSubject(subject);
        mu.setContent(content);
        mu.sendEmail();
    }

    public static void test2(MailUtil mu) throws Exception {
        String toEmails = "17553367@qq.com";
        String subject = "第二封，HTML邮件";
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>老婆：<br />我是你的老公吗？<br />是的，是很久了。<br /></body></html>");
        String content = builder.toString();
        mu.setToEmails(toEmails);
        mu.setSubject(subject);
        mu.setContent(content);
        mu.sendEmail();
    }

    public static void test3(MailUtil mu) throws Exception {
        String toEmails = "17553367@qq.com";
        String subject = "第三封，图片邮件";
        Map<String, String> pictures = new HashMap<String, String>();
        pictures.put("d1", "D:/work/download/d1.jpg");
        pictures.put("d2", "D:/work/download/测试图片2.jpg");
        pictures.put("d3", "D:/work/download/d3.jpg");
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>看看下面的图，你会知道花儿为什么是这样红的：<br />");
        builder.append("<img src=\"cid:d1\" /><br />");
        builder.append("<img src=\"cid:d2\" /><br />");
        builder.append("<img src=\"cid:d3\" /><br />");
        builder.append("</body></html>");
        String content = builder.toString();
        mu.setToEmails(toEmails);
        mu.setSubject(subject);
        mu.setContent(content);
        mu.setPictures(pictures);
        mu.sendEmail();
    }

    public static void test4(MailUtil mu) throws Exception {
        String toEmails = "17553367@qq.com";
        String subject = "第四封，附件邮件";
        Map<String, String> attachments = new HashMap<String, String>();
        attachments.put("d1.jar", "D:/work/download/activation.jar");
        attachments.put("d2.doc", "C:/Documents and Settings/Administrator/桌面/Java设计模式.doc");
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。</body></html>");
        String content = builder.toString();
        mu.setToEmails(toEmails);
        mu.setSubject(subject);
        mu.setContent(content);
        mu.setAttachments(attachments);
        mu.sendEmail();
    }

    public static void test5(MailUtil mu) throws Exception {
        String toEmails = "17553367@qq.com";
        String subject = "第五封，综合邮件";
        Map<String, String> attachments = new HashMap<String, String>();
        attachments.put("d1.jar", "D:/work/download/activation.jar");
        attachments.put("d2.doc", "C:/Documents and Settings/Administrator/桌面/Java设计模式.doc");
        Map<String, String> pictures = new HashMap<String, String>();
        pictures.put("d1", "D:/work/download/d1.jpg");
        pictures.put("d2", "D:/work/download/测试图片2.jpg");
        pictures.put("d3", "D:/work/download/d3.jpg");
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。<br />");
        builder.append("看看下面的图，你会知道花儿为什么是这样红的：<br />");
        builder.append("<img src=\"cid:d1\" /><br />");
        builder.append("<img src=\"cid:d2\" /><br />");
        builder.append("<img src=\"cid:d3\" /><br />");
        builder.append("</body></html>");
        String content = builder.toString();
        mu.setToEmails(toEmails);
        mu.setSubject(subject);
        mu.setContent(content);
        mu.setPictures(pictures);
        mu.setAttachments(attachments);
        mu.sendEmail();
    }

    public static void test6(MailUtil mu) throws Exception {
        String toEmails = "17553367@qq.com;13706180517@163.com";
        String subject = "第五封，群发邮件";
        Map<String, String> attachments = new HashMap<String, String>();
        attachments.put("d1.jar", "D:/work/download/activation.jar");
        attachments.put("d2.doc", "C:/Documents and Settings/Administrator/桌面/Java设计模式.doc");
        Map<String, String> pictures = new HashMap<String, String>();
        pictures.put("d1", "D:/work/download/d1.jpg");
        pictures.put("d2", "D:/work/download/测试图片2.jpg");
        pictures.put("d3", "D:/work/download/d3.jpg");
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。<br />");
        builder.append("看看下面的图，你会知道花儿为什么是这样红的：<br />");
        builder.append("<img src=\"cid:d1\" /><br />");
        builder.append("<img src=\"cid:d2\" /><br />");
        builder.append("<img src=\"cid:d3\" /><br />");
        builder.append("</body></html>");
        String content = builder.toString();
        mu.setToEmails(toEmails);
        mu.setSubject(subject);
        mu.setContent(content);
        mu.setPictures(pictures);
        mu.setAttachments(attachments);
        mu.sendEmail();
    }
}
