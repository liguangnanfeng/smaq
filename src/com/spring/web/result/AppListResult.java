/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.result;

import java.util.List;
import com.spring.web.ibatis.DynamicParameter;

/**
 * @Title: AppListResult
 * @Description: app结果（List）接口类
 * @author FL
 * @date 2014年6月22日 上午4:00:36
 * @version V1.0
 */
public interface AppListResult {
    public String getStatus();

    public void setStatus(String status);

    public void setMessage(String message);

    public String getMessage();

    public List<DynamicParameter<String, Object>> getList();

    public void setList(List<DynamicParameter<String, Object>> list);
}