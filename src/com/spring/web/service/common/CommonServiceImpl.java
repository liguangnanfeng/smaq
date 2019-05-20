/**  
 * Copyright(c)2013 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */

package com.spring.web.service.common;

import com.spring.web.dao.GlobalRegionMapper;
import com.spring.web.ibatis.BaseDao;
import com.spring.web.ibatis.DynamicDBValues;
import com.spring.web.model.GlobalRegion;
import com.spring.web.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
* @Title: CommonServiceImpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author CGF   
* @date 2017年2月17日 上午9:02:18 
* @version V1.0   
*/
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    /**
     * @Fields serialVersionUID : 序列化
     */
    private static final long serialVersionUID = 8252510697823573991L;

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private GlobalRegionMapper globalRegionMapper;

    private final String SQL_CHILD_GLOBAL = "globalRegion.childGlobal";

    /**
     * 下级区域
     */
    public void ChildGlobal(GlobalRegion gr, Result result) throws Exception {

        DynamicDBValues dy = baseDao.createDBValues();

        dy.put("parentId", gr.getId());
        result.setMap("list", baseDao.queryForList(SQL_CHILD_GLOBAL, dy));

        gr = globalRegionMapper.selectByPrimaryKey(gr.getId());
        result.setMap("type", gr.getRegionType()); // 1.省 2市
    }

    /**
     * 区域联动 下级找上及
     */
    public void ParentGlobal(GlobalRegion gr, Result result) throws Exception {

        gr = globalRegionMapper.selectByPrimaryKey(gr.getId());
        result.setMap("type", gr.getRegionType());

        DynamicDBValues dy = baseDao.createDBValues();

        if (gr.getRegionType().equals("3")) {// 区-》市-》省

            dy.put("parentId", gr.getParentId());
            result.setMap("regionList", baseDao.queryForList(SQL_CHILD_GLOBAL, dy));
            result.setMap("regionId", gr.getId());

            GlobalRegion city = globalRegionMapper.selectByPrimaryKey(gr.getParentId());
            result.setMap("cityId", city.getId());
            dy.put("parentId", city.getParentId());
            result.setMap("cityList",baseDao.queryForList(SQL_CHILD_GLOBAL, dy));

            GlobalRegion province = globalRegionMapper.selectByPrimaryKey(city.getParentId());
            result.setMap("provinceId", province.getId());
            dy.put("parentId", province.getParentId());
            result.setMap("provinceList", baseDao.queryForList(SQL_CHILD_GLOBAL, dy));

        } else if (gr.getRegionType().equals("2")) { // 市-》省

            dy.put("parentId", gr.getParentId());
            result.setMap("cityList", baseDao.queryForList(SQL_CHILD_GLOBAL, dy));
            result.setMap("cityId", gr.getId());

            GlobalRegion city = globalRegionMapper.selectByPrimaryKey(gr.getParentId());
            result.setMap("provinceId", city.getId());
            dy.put("parentId", city.getParentId());
            result.setMap("provinceList", baseDao.queryForList(SQL_CHILD_GLOBAL, dy));
        }
    }
    
}
