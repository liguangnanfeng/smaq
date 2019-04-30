package com.spring.web.ibatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @Title: LlHashMap 
* @Description: TODO(用一句话描述该文件做什么) 
* @author CGF
* @date 2018年5月30日 上午9:57:26 
* @version V1.0  
* @param <K>
* @param <V> 
*/
public class LlHashMap<K, V> extends HashMap<K, V> {
    /**
     * @Fields serialVersionUID : 序列ID
     */
    private static final long serialVersionUID = 7815356654851236563L;

    public Object getObject(Object s) {
        return super.get(s);
    }

    public String getString(Object s) {
        return String.valueOf(super.get(s));
    }

    public Boolean getBoolean(Object s) {
        if (super.get(s) != null) {
            return (Boolean.valueOf(super.get(s).toString()));
        } else {
            return null;
        }
    }

    public Integer getInteger(Object s) {
        if (null != super.get(s)) {
            return (Integer.valueOf(super.get(s).toString()));
        } else {
            return null;
        }
    }
    
    public Long getLong(Object s) {
        if (null != super.get(s)) {
            return (Long.valueOf(super.get(s).toString()));
        } else {
            return null;
        }
    }
    
    public Double getDouble(Object s) {
        if (null != super.get(s)) {
            return (Double.valueOf(super.get(s).toString()));
        } else {
            return null;
        }
    }

    public Map<?, ?> getMap(Object s) {
        if (super.get(s) != null) {
            return (Map<?, ?>) (super.get(s));
        } else {
            return null;
        }
    }

    public List<?> getList(Object s) {
        if (super.get(s) != null) {
            return (List<?>) (super.get(s));
        } else {
            return null;
        }
    }

    public BigDecimal getBigDecimal(Object s) {
        if (super.get(s) != null) {
            return (new BigDecimal(super.get(s).toString()));
        } else {
            return null;
        }
    }
    
    public <T> T getObject(Object s, Class<T> t) {
        if (super.get(s) != null) {
            return t.cast(super.get(s));
        } else {
            return null;
        }
    }

}
