/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.result;

import com.spring.web.ibatis.DynamicParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: AppListResult
 * @Description: app结果（List）接口实现类
 * @author FL
 * @date 2014年6月22日 上午4:00:36
 * @version V1.0
 */
public class AppListResultImpl implements AppListResult {
    private String status = "0";
    private String message;
    private List<DynamicParameter<String, Object>> list = new ArrayList<DynamicParameter<String, Object>>();

    /**
     * (非 Javadoc)
     * 
     * @return
     * @see com.spring.web.result.AppResult#getStatus()
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /**
     * (非 Javadoc)
     *
     * @param status
     * @see com.spring.web.result.AppResult#setStatus(String)
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message 要设置的 message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return
     * @see com.spring.web.result.AppResult#getList()
     */
    @Override
    public List<DynamicParameter<String, Object>> getList() {
        return this.list;
    }

    /**
     * (非 Javadoc)
     *
     * @param list
     * @see com.spring.web.result.AppResult#setList(List)
     */
    @Override
    public void setList(List<DynamicParameter<String, Object>> list) {
        this.list = list;
    }
}