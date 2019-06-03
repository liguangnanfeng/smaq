/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.service.envir_params_setting;

import com.spring.web.dao.TWaterparamterMapper;
import com.spring.web.model.TWaterparamter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 
* @Title: EnvirParamsSettingServiceImpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @date 2018年5月28日 下午10:19:15 
* @version V1.0   
*/
@Service
public class EnvirParamsSettingServiceImpl implements EnvirParamsSettingService {

    @Autowired
    private TWaterparamterMapper tWaterparamterMapper;
    @Override
    public List<TWaterparamter> geTWaterparamters() {
        return tWaterparamterMapper.selectWaterParamters();
    }

    @Override
    public boolean updateWaterParameter(TWaterparamter paramter) {
        return tWaterparamterMapper.updateByPrimaryKeySelective(paramter)>0?true:false;
    }
    
}
