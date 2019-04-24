package com.spring.web.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.commons.lang3.StringUtils;

/**
 * @Title: LlLoopTag
 * @Description: 自定义循环输出
 * @author FL
 * @date 2016年1月26日 上午11:56:29
 * @version V1.0
 */
public class LlLoopTag extends SimpleTagSupport {
    /**
     * 带有间隔符的字符串
     */
    private String original;
    /**
     * 间隔字符
     */
    private String seperate;
    /**
     * 匹配字符串
     */
    private String matchString;
    private String single;

    /**
     * @return original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original 要设置的 original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * @return seperate
     */
    public String getSeperate() {
        return seperate;
    }

    /**
     * @param seperate 要设置的 seperate
     */
    public void setSeperate(String seperate) {
        this.seperate = seperate;
    }

    /**
     * @return matchString
     */
    public String getMatchString() {
        return matchString;
    }

    /**
     * @param matchString 要设置的 matchString
     */
    public void setMatchString(String matchString) {
        this.matchString = matchString;
    }

    /**
     * @return single
     */
    public String getSingle() {
        return single;
    }

    /**
     * @param single 要设置的 single
     */
    public void setSingle(String single) {
        this.single = single;
    }

    public void doTag() throws JspException, IOException {
        String[] arr = StringUtils.split(original, seperate);
        for (String s : arr) {
            getJspContext().setAttribute(single, s);
            this.getJspBody().invoke(null);
        }
    }
}
