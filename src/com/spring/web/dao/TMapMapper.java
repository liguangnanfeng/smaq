package com.spring.web.dao;

import com.spring.web.model.request.TMap;
import org.springframework.stereotype.Service;

/**
 * @Author: 桃红梨白
 * @Date: 2019/05/15 10:15
 * 图片dao
 */
@Service
public interface TMapMapper {
    /**保存图片*/
    void  insertTMap(TMap tMap);

    /**查询图片*/
    TMap selectByUserId(Integer id);

    /**
     * 删除图片
     * @param id
     */
    void dropByUserId(Integer id);

    /**
     * 修改公司对应的图片
     * @param tMap
     */
    void updateMap(TMap tMap);

}
