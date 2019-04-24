/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.result;

/**
 * @Title: AppResult
 * @Description: app结果（Map）接口实现类
 * @author FL
 * @date 2017年8月3日 上午4:00:36
 * @version V1.0
 */
public class AppResultImpl implements AppResult {
    /**
     * 状态CODE
     */
    private String status = "0";
    /**
     * 消息
     */
    private String message = "SUCCESS";
    /**
     * 数据
     */
    private Object data;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

}