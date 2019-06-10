package com.spring.web.ibatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: DynamicParameter
 * @Description: 动态参数
 * @author FL
 * @date 2016年1月26日 上午10:22:36
 * @version V1.0
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("rawtypes")
public class DynamicParameter<K, V> extends HashMap implements DynamicDBValues {
    /**
     * @Fields serialVersionUID : 序列ID
     */
    private static final long serialVersionUID = 7815356654851236563L;

    @Override
    public Object getObject(String s) {
        return super.get(s);
    }

    @Override
    public String getString(String s) {
        Object ss = super.get(s);
        if(null == ss) {
            return null;
        }
        return String.valueOf(ss);
    }

    @Override
    public Boolean getBoolean(String s) {
        if (super.get(s) != null) {
            return (Boolean.valueOf(super.get(s).toString()));
        } else {
            return null;
        }
    }

    @Override
    public Integer getInteger(String s) {
        if (super.get(s) != null) {
            return (Integer.valueOf(super.get(s).toString()));
        } else {
            return null;
        }
    }

    @Override
    public Map<?, ?> getMap(String s) {
        if (super.get(s) != null) {
            return (Map<?, ?>) (super.get(s));
        } else {
            return null;
        }
    }

    @Override
    public List<?> getList(String s) {
        if (super.get(s) != null) {
            return (List<?>) (super.get(s));
        } else {
            return null;
        }
    }

    /**
     * (非 Javadoc)
     * 
     * @param s
     * @return
     * @see com.spring.web.ibatis.DynamicDBValues#getBigDecimal(String)
     */
    @Override
    public BigDecimal getBigDecimal(String s) {
        if (super.get(s) != null) {
            return (new BigDecimal(super.get(s).toString()));
        } else {
            return null;
        }
    }

    @Override
    public Integer getBigDecimalToInteger(String s) {
        if (super.get(s) != null) {
            return (new BigDecimal(super.get(s).toString())).intValue();
        } else {
            return null;
        }
    }
}
