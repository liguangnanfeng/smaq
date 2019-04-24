/**  
 * Copyright(c)2013 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */

package com.spring.web.service.common;

import java.io.Serializable;
import com.spring.web.model.GlobalRegion;
import com.spring.web.result.Result;

/**
 * 
* @Title: CommonService 
* @Description: TODO(用一句话描述该文件做什么) 
* @author ZWL   
* @date 2015年3月25日 下午3:35:46 
* @version V1.0
 */
public interface CommonService extends Serializable {

    /**
     * 下级区域
     */
    public void ChildGlobal(GlobalRegion gr, Result result) throws Exception;

    /**
     * 区域联动 下级找上及
     */
    public void ParentGlobal(GlobalRegion gr, Result result) throws Exception;
}
