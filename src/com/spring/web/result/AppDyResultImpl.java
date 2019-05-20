/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.result;

import com.spring.web.ibatis.DynamicParameter;

/**
 * @Title: AppDyResult
 * @Description: app结果（DynamicParameter）接口实现类
 * @author FL
 * @date 2014年6月22日 上午4:00:36
 * @version V1.0
 */
public class AppDyResultImpl implements AppDyResult {
    private DynamicParameter<String, Object> dy;
    private String status = "0";
    private String message;

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
     * @see com.spring.web.result.AppDyResult#getDy()
     */
    @Override
    public DynamicParameter<String, Object> getDy() {
        return this.dy;
    }

    /**
     * @param dy
     * @see com.spring.web.result.AppDyResult#setDy(com.spring.web.ibatis.DynamicParameter)
     */
    @Override
    public void setDy(DynamicParameter<String, Object> dy) {
        this.dy = dy;
    }
}