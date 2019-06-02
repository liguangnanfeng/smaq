/**  
 * Copyright(c)2013 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.tag;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: ChooseTag
 * @Description: html样式输出文本
 * @author SEA
 * @date 2013-9-4 下午2:59:51
 * @version V1.0
 */
public class OutputTextTag extends SimpleTagSupport {

	/**
	 *截取的长度
	 */
	private String length;

	/**
	 * 要截取日内容
	 */
	private String value;




	/**
	 * @return value
	 */

	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            要设置的 value
	 */
	public void setValue(String value) {
		this.value = value;
	}


	/**
	 * @return length 
	 */
	
	public String getLength() {
		return length;
	}

	/** 
	 * @param length 要设置的 length 
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * (非 Javadoc) 解析xml文件,解析标签jsp引擎
	 * 
	 * @throws JspException
	 * @throws IOException
	 * @see SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspWriter out = getJspContext().getOut();
		
		String htmlStr = "";
		if (StringUtils.isNotBlank(value)) {
			htmlStr = this.delHTMLTag(value);
			if (StringUtils.length(htmlStr) > Integer.parseInt(length)) {
				htmlStr = StringUtils.substring(htmlStr, 0, Integer.parseInt(length));
				htmlStr = htmlStr + " ...";
			}
		}
		try {
			// 输出到页面
			out.write(htmlStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    } 
}
