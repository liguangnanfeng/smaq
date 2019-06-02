/**  
 * Copyright(c)2016 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @Title: EncacheUtil
 * @Description: TODO(用一句话描述该文件做什么) 纯Java的进程内缓存框架，是Hibernate中默认的CacheProvider。
 * @author CGF
 * @date 2017年3月27日 下午5:28:31
 * @version V1.0
 *
 *
 *
 */
public class EncacheUtil {
    
    private static CacheManager cacheManager;
    
    private static String cacheName = "search_product";
    
    private static void ehcacheSetUp() {  
        cacheManager = CacheManager.create();
        cacheManager.addCache(cacheName);  
    }  
  
    public static void ehcachePut(String key, Object value) {  
        if(null == cacheManager) {
            ehcacheSetUp();
        }
        Cache cache = cacheManager.getCache(cacheName);
        cache.put(new Element(key, value));
    }  
  
    public static Element getData(String key) {
        if(null == cacheManager) {
            ehcacheSetUp();
            return null;
        } else {
            Cache cache = cacheManager.getCache(cacheName);
            return cache.get(key);  
        }
    }  
}
