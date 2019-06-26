package com.spring.web.dao;

import com.spring.web.model.TSafetyStandard;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * TODO (安全生产标准化的dao层)
 *
 * @author 桃红梨白
 * @Date 2019/6/26 13:43
 */
@Repository
public interface TSafetyStandardMapper {


    /**
     * 插入数据
     * @param tSafetyStandard
     * @return 返回值为1 表示插入成功
     */
    int insertSelective(TSafetyStandard tSafetyStandard );

    /**
     * 修改数据
     */
    void updateTSafetyStandard(TSafetyStandard tSafetyStandard);

    /**
     * 根据条件查询所有的安全生产标准化数据
     * 可以进行排序,如果有sort值,的话就表示倒序没有就是 正序
     * @return
     */
    List<TSafetyStandard> findAll(Map<String,Object> map);



}
