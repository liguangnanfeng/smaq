/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.service.trouble;

import com.spring.web.model.TCheck;
import com.spring.web.result.Result;

import java.io.Serializable;

/** 
 * @Title: TroubleService 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author CGF   
 * @date 2017年9月4日 下午2:43:18 
 * @version V1.0   
 */
public interface TroubleService extends Serializable {
    
    public Result tCheckSave(TCheck tCheck, Result result) throws Exception;
    
}
