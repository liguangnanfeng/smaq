package com.spring.web.tag;

import java.io.IOException;
import java.util.Set;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.commons.lang3.StringUtils;

/**
 * @Title: SetContainsTag
 * @Description: 设置常量标签
 * @author FL
 * @date 2016年1月26日 上午11:55:25
 * @version V1.0
 */
public class SetContainsTag extends SimpleTagSupport {
    private Set<?> set;
    private String ele;
    /**
     * 标志0,为true时执行,1.为false时执行
     */
    private String flag;

    /**
     * @return set
     */
    public Set<?> getSet() {
        return set;
    }

    /**
     * @param set 要设置的 set
     */
    public void setSet(Set<?> set) {
        this.set = set;
    }

    /**
     * @return ele
     */
    public String getEle() {
        return ele;
    }

    /**
     * @return flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag 要设置的 flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @param ele 要设置的 ele
     */
    public void setEle(String ele) {
        this.ele = ele;
    }

    public void doTag() throws JspException, IOException {
        if (set != null && StringUtils.isNotBlank(ele) && StringUtils.isNotBlank(flag)) {
            if (StringUtils.equals(flag, "0")) {
                if (set.contains(Integer.valueOf(ele))) {
                    this.getJspBody().invoke(null);
                }
            } else {
                if (!set.contains(Integer.valueOf(ele))) {
                    this.getJspBody().invoke(null);
                }
            }
        }
    }
}
