/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.bean;

import java.io.Serializable;

/**
 * @Title: GlobalRegionNameBean
 * @Description: 获取区域信息Bean
 * @author FL
 * @date 2014年7月28日 下午8:21:30
 * @version V1.0
 */
public class GlobalRegionNameBean implements Serializable {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = -2751656354448648067L;
    /**
     * 省份ID
     */
    private Integer provinceId;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 城市ID
     */
    private Integer cityId;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 区县ID
     */
    private Integer countyId;
    /**
     * 区县名称
     */
    private String countyName;

    /**
     * @return provinceId
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId 要设置的 provinceId
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName 要设置的 provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId 要设置的 cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName 要设置的 cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return countyId
     */
    public Integer getCountyId() {
        return countyId;
    }

    /**
     * @param countyId 要设置的 countyId
     */
    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    /**
     * @return countyName
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * @param countyName 要设置的 countyName
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
