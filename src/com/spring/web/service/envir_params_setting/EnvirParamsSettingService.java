/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.service.envir_params_setting;

import com.spring.web.model.TWaterparamter;

import java.util.List;

/** 
* @Title: EnvirParamsSetting 
* @Description: TODO(用一句话描述该文件做什么) 
* @date 2018年5月28日 下午10:18:15 
* @version V1.0   
*/
public interface EnvirParamsSettingService {
    
    /**
     * 获取所有的水参数
    * @return
     */
    public List<TWaterparamter> geTWaterparamters();
    
    /**
     * 修改水参数
    * @param paramter
    * @return
     */
    public boolean updateWaterParameter(TWaterparamter paramter);
}
