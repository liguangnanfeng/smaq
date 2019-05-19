package com.spring.web.ibatis;

import com.spring.web.exception.LlSystemException;
import com.spring.web.util.MessageConstantsUtil;
import com.spring.web.util.StackTraceUtil;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Title: DynamicDBExecuter
 * @Description:动态DB实施
 * @author FL
 * @date 2016年1月26日 上午10:20:21
 * @version V1.0
 */
@Repository("baseDao")
public class DynamicDBExecuter implements BaseDao {
    private static Logger log = Logger.getLogger(DynamicDBExecuter.class);
    private SqlSessionTemplate sqlSessionTemplate;
    private PropertyUtilsBean propertyUtils;
    private ConvertUtilsBean convertUtilsBean;

    @Override
    public DynamicDBValues createDBValues() {
        propertyUtils = new PropertyUtilsBean();
        convertUtilsBean = new ConvertUtilsBean();
        return new DynamicParameter<Object, Object>() {
            /**
             * @Fields serialVersionUID : 序列ID
             */
            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
            public Object put(String s, Object obj) {
                return super.put(s, obj);
            }

            public Object put(Object obj, Object obj1) {
                return this.put((String) obj, obj1);
            }

            /**
             * 移除已在map中对象。
             * 
             * @param s
             */
            @SuppressWarnings("unused")
            public Object remvoe(Object s) {
                return super.remove(s);
            }
        };
    }

    @Override
    public DynamicDBValues createDBPageValues() {
        DynamicDBValues dbValues = this.createDBValues();
        dbValues.put("index", 0);
        dbValues.put("page", 500);
        return dbValues;
    }

    @Override
    public DynamicDBValues createDBValuesWithInitialize(Object object) throws Exception {
        DynamicDBValues dbValues = this.createDBValues();
        try {
            if (log.isDebugEnabled()) {
                long startTime = System.currentTimeMillis();
                log.debug("convert start:" + startTime);
                PropertyDescriptor[] descriptors = propertyUtils.getPropertyDescriptors(object);
                for (int i = 0; i < descriptors.length; i++) {
                    String name = descriptors[i].getName();
                    if (!"class".equals(name) && propertyUtils.getReadMethod(descriptors[i]) != null) {
                        if (propertyUtils.getReadMethod(descriptors[i]).getReturnType().equals(String.class)) {
                            dbValues.put(name, StringUtils.trim(convertUtilsBean.convert(propertyUtils
                                    .getNestedProperty(object, name))));
                        } else if (propertyUtils.getReadMethod(descriptors[i]).getReturnType().equals(Date.class)) {
                            if (propertyUtils.getNestedProperty(object, name) == null) {
                                dbValues.put(name, null);
                            } else {
                                dbValues.put(name, convertUtilsBean.convert(
                                        propertyUtils.getNestedProperty(object, name),
                                        propertyUtils.getReadMethod(descriptors[i]).getReturnType()));
                            }
                        } else if (propertyUtils.getReadMethod(descriptors[i]).getReturnType().equals(BigDecimal.class)) {
                            if (propertyUtils.getNestedProperty(object, name) == null) {
                                dbValues.put(name, null);
                            } else {
                                dbValues.put(name, convertUtilsBean.convert(
                                        propertyUtils.getNestedProperty(object, name),
                                        propertyUtils.getReadMethod(descriptors[i]).getReturnType()));
                            }
                        } else {
                            dbValues.put(name, convertUtilsBean.convert(propertyUtils.getNestedProperty(object, name),
                                    propertyUtils.getReadMethod(descriptors[i]).getReturnType()));
                        }
                    }
                }
                long endTime = System.currentTimeMillis();
                log.debug("convert end:" + endTime);
                log.debug("convert total time:" + (endTime - startTime) + "ms");
            } else {
                PropertyDescriptor[] descriptors = propertyUtils.getPropertyDescriptors(object);
                for (int i = 0; i < descriptors.length; i++) {
                    String name = descriptors[i].getName();
                    if (!"class".equals(name) && propertyUtils.getReadMethod(descriptors[i]) != null) {
                        if (propertyUtils.getReadMethod(descriptors[i]).getReturnType().equals(String.class)) {
                            dbValues.put(name, StringUtils.trim(convertUtilsBean.convert(propertyUtils
                                    .getNestedProperty(object, name))));
                        } else if (propertyUtils.getReadMethod(descriptors[i]).getReturnType().equals(Date.class)) {
                            if (propertyUtils.getNestedProperty(object, name) == null) {
                                dbValues.put(name, null);
                            } else {
                                dbValues.put(name, convertUtilsBean.convert(
                                        propertyUtils.getNestedProperty(object, name),
                                        propertyUtils.getReadMethod(descriptors[i]).getReturnType()));
                            }
                        } else if (propertyUtils.getReadMethod(descriptors[i]).getReturnType().equals(BigDecimal.class)) {
                            if (propertyUtils.getNestedProperty(object, name) == null) {
                                dbValues.put(name, null);
                            } else {
                                dbValues.put(name, convertUtilsBean.convert(
                                        propertyUtils.getNestedProperty(object, name),
                                        propertyUtils.getReadMethod(descriptors[i]).getReturnType()));
                            }
                        } else {
                            dbValues.put(name, convertUtilsBean.convert(propertyUtils.getNestedProperty(object, name),
                                    propertyUtils.getReadMethod(descriptors[i]).getReturnType()));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.info(StackTraceUtil.getStackTrace(e));
            throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
        } catch (InvocationTargetException e) {
            log.info(StackTraceUtil.getStackTrace(e));
            throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
        } catch (NoSuchMethodException e) {
            log.info(StackTraceUtil.getStackTrace(e));
            throw new LlSystemException(MessageConstantsUtil.CONVERT_EXCEPTION);
        }
        return dbValues;
    }

    @Override
    public DynamicDBValues createDBValuesWithInitializePage(Object object) throws Exception {
        DynamicDBValues dbValues = this.createDBValues();
        dbValues = this.createDBValuesWithInitialize(object);
        dbValues.put("index", 0);
        dbValues.put("page", 500);
        return dbValues;
    }

    @Override
    public <T extends Serializable> List<T> queryForList(String s, DynamicDBValues dynamicDBValues) {
        List<T> l = null;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("select start,startTime:" + startTime);
            l = sqlSessionTemplate.selectList(s, dynamicDBValues);
            long endTime = System.currentTimeMillis();
            log.debug("select end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            l = sqlSessionTemplate.selectList(s, dynamicDBValues);
        }
        if (dynamicDBValues != null) {
            dynamicDBValues.clear();
        }
        return l;
    }

    public <T extends Serializable> T queryForObject(String s, DynamicDBValues dynamicDBValues) {
        T l = null;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("select start,startTime:" + startTime);
            l = sqlSessionTemplate.selectOne(s, dynamicDBValues);
            long endTime = System.currentTimeMillis();
            log.debug("select end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            l = sqlSessionTemplate.selectOne(s, dynamicDBValues);
        }
        dynamicDBValues.clear();
        return l;
    }

    @Override
    public int insert(String s, DynamicDBValues dynamicDBValues) {
        int count = 0;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("insert start,startTime:" + startTime);
            count = sqlSessionTemplate.insert(s, dynamicDBValues);
            long endTime = System.currentTimeMillis();
            log.debug("insert end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            count = sqlSessionTemplate.insert(s, dynamicDBValues);
        }
        dynamicDBValues.clear();
        return count;
    }

    @Override
    public int insertReturnPrimaryKey(String s, DynamicDBValues dynamicDBValues) {
        int count = 0;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("insert start,startTime:" + startTime);
            count = sqlSessionTemplate.insert(s, dynamicDBValues);
            long endTime = System.currentTimeMillis();
            log.debug("insert end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            count = sqlSessionTemplate.insert(s, dynamicDBValues);
        }
        return count;
    }

    @Override
    public int update(String s, DynamicDBValues dynamicDBValues) {
        int count = 0;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("update start,startTime:" + startTime);
            count = sqlSessionTemplate.update(s, dynamicDBValues);
            long endTime = System.currentTimeMillis();
            log.debug("update end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            count = sqlSessionTemplate.update(s, dynamicDBValues);
        }
        return count;
    }

    @Override
    public int delete(String s, DynamicDBValues dynamicDBValues) {
        int count = 0;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("delete start,startTime:" + startTime);
            count = sqlSessionTemplate.delete(s, dynamicDBValues);
            long endTime = System.currentTimeMillis();
            log.debug("delete end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            count = sqlSessionTemplate.delete(s, dynamicDBValues);
        }
        return count;
    }

    /**
     * @return sqlSessionTemplate
     */
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    /**
     * @param sqlSessionTemplate 要设置的 sqlSessionTemplate
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * (非 Javadoc)
     * 
     * @param <T>
     * @param s
     * @param dynamicDBValues
     * @return
     * @see com.spring.web.ibatis.BaseDao#queryForOneObject(String, com.spring.web.ibatis.DynamicDBValues)
     */
    @Override
    public <T extends Serializable> T queryForOneObject(String s, DynamicDBValues dynamicDBValues) {
        List<T> l = null;
        T t = null;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("select start,startTime:" + startTime);
            l = sqlSessionTemplate.selectList(s, dynamicDBValues);
            long endTime = System.currentTimeMillis();
            log.debug("select end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            l = sqlSessionTemplate.selectList(s, dynamicDBValues);
        }
        if (l != null && l.size() > 0) {
            t = l.get(0);
        }
        if (dynamicDBValues != null) {
            dynamicDBValues.clear();
        }
        return t;
    }

    /**
     * (非 Javadoc) 批量插入数据
     *
     * @param <T> DTO型
     * @param s 插入ID
     * @param list ModelList
     * @return 插入记录数
     * @see com.spring.web.ibatis.BaseDao#insert(String, List)
     */
    @Override
    public <T extends Serializable> int insert(String s, List<T> list) {
        int count = 0;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("batch insert start,startTime:" + startTime);
            count = sqlSessionTemplate.insert(s, list);
            long endTime = System.currentTimeMillis();
            log.debug("batch insert end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            count = sqlSessionTemplate.insert(s, list);
        }
        return count;
    }

    /**
     * (非 Javadoc)
     *
     * @param <T>
     * @param s
     * @param dynamicDBValues
     * @return
     * @see com.spring.web.ibatis.BaseDao#queryMapForList(String, com.spring.web.ibatis.DynamicDBValues)
     */
    @Override
    public <T> List<T> queryForMap(String s, DynamicDBValues dynamicDBValues) {
        List<T> l = null;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("select start,startTime:" + startTime);
            l = sqlSessionTemplate.selectList(s, dynamicDBValues);
            long endTime = System.currentTimeMillis();
            log.debug("select end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            l = sqlSessionTemplate.selectList(s, dynamicDBValues);
        }
        if (dynamicDBValues != null) {
            dynamicDBValues.clear();
        }
        return l;
    }

    /**
     * (非 Javadoc) 数据查询返回map
     *
     * @param s
     * @param dynamicDBValues
     * @return map
     * @see com.spring.web.ibatis.BaseDao#queryForMapObject(String, com.spring.web.ibatis.DynamicDBValues)
     */
    @Override
    public Map<?, ?> queryForMapObject(String s, DynamicDBValues dynamicDBValues, String key) {
        Map<?, ?> l = null;
        if (log.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            log.debug("select start,startTime:" + startTime);
            l = sqlSessionTemplate.selectMap(s, dynamicDBValues, key);
            long endTime = System.currentTimeMillis();
            log.debug("select end,endTime:" + endTime);
            log.debug("total time:" + (endTime - startTime) + "ms");
        } else {
            l = sqlSessionTemplate.selectMap(s, dynamicDBValues, key);
        }
        if (dynamicDBValues != null) {
            dynamicDBValues.clear();
        }
        return l;
    }
}
