/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.result;

/**
 * @Title: AppResult
 * @Description: app结果（Map）接口类
 * @author FL
 * @date 2014年6月22日 上午4:00:36
 * @version V1.0
 */
public interface AppResult {
    public String getStatus();

    public void setStatus(String status);

    public void setMessage(String message);

    public String getMessage();

    public Object getData();

    public void setData(Object obj);
}