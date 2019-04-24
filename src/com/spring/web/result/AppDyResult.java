/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.result;

import com.spring.web.ibatis.DynamicParameter;

/**
 * @Title: AppDyResult
 * @Description: app结果（DynamicParameter）接口类
 * @author FL
 * @date 2014年6月22日 上午4:00:36
 * @version V1.0
 */
public interface AppDyResult {
    public String getStatus();

    public void setStatus(String status);

    public DynamicParameter<String, Object> getDy();

    public void setDy(DynamicParameter<String, Object> dy);

    public void setMessage(String message);

    public String getMessage();
}