/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.service.company_cd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.web.ibatis.BaseDao;
import com.spring.web.ibatis.DynamicDBValues;
import com.spring.web.ibatis.DynamicParameter;

/**
 * @Title: BuyServiceImpl
 * @Description: 获取地区名称接口实现类
 * @author FL
 * @date 2017年7月10日 上午10:59:11
 * @version V1.0
 */
@Service("regionnameGetService")
public class RegionnameGetServiceImpl implements RegionnameGetService {
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = 4267558846455208398L;
    @Autowired
    private BaseDao baseDao;
    private final static String GET_REGION_NAME = "company.queryForListId";
    /** (非 Javadoc)
    * @param regionId
    * @return 
    * @see com.spring.web.service.company_cd.RegionnameGetService#getRegionname(java.lang.Integer) 
    */
    @Override
    public DynamicParameter<String, Object> getRegionname(Integer regionId) {
        DynamicDBValues db=baseDao.createDBValues();
        db.put("regionId", regionId);
        DynamicParameter<String, Object> dy=baseDao.queryForObject(GET_REGION_NAME, db);
        return dy;
    }
}
