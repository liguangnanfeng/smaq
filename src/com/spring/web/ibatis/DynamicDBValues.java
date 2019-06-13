package com.spring.web.ibatis;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @Title: DynamicDBValues
 * @Description: 动态DB属性
 * @author FL
 * @date 2016年1月26日 上午10:22:12
 * @version V1.0
 */
public interface DynamicDBValues extends Serializable {
    /**
     * 清空map
     */
    public abstract void clear();

    /**
     * toString
     * 
     * @return 对应属性字段连接的字符串
     */
    public abstract String toString();

    /**
     * 由键值得到对应的实体
     * 
     * @param 对应键值
     * @return 实体
     */
    public abstract Object getObject(String s);

    /**
     * 由键值得到对应的字符串值
     * 
     * @param 对应键值
     * @return 对应的字符串值
     */
    public abstract String getString(String s);

    public abstract Object put(Object o, Object o1);

    public abstract Boolean getBoolean(String s);

    public abstract Integer getInteger(String s);

    public abstract Map<?, ?> getMap(String s);

    public abstract List<?> getList(String s);

    public abstract BigDecimal getBigDecimal(String s);

    public abstract Integer getBigDecimalToInteger(String s);
    public abstract Integer getLongToInteger(String s);

    public abstract Object remove(Object o);

}
