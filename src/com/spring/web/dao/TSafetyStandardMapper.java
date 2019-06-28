package com.spring.web.dao;

import com.spring.web.model.TSafetyStandard;
import org.apache.ibatis.annotations.Param;
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


    /**
     * 根据id查询详细信息
     * @param safetyStandardlistId
     * @return
     */
    TSafetyStandard findOne(Integer safetyStandardlistId);

    /**
     * 判断该要素有没有子要素
     * @param safetyStandardlistId
     * @return
     */
    List<TSafetyStandard> findByparentId(@Param("id") Integer safetyStandardlistId, @Param("sort")Integer sort);

    /**
     * 根据id 删除数据
     * @param safetyStandardlistId
     */
    void deleteTSafetyStandard(Integer safetyStandardlistId);
}
