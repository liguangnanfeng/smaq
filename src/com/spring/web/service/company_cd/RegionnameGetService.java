/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.service.company_cd;

import java.io.Serializable;
import com.spring.web.ibatis.DynamicParameter;

/** 
 * @Title: BuyService 
 * @Description: 获取地区名称接口类
 * @author FL   
 * @date 2017年7月10日 上午10:58:57 
 * @version V1.0   
 */
public interface RegionnameGetService extends Serializable{
    public DynamicParameter<String, Object> getRegionname(Integer regionId);
}
