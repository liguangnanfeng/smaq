package com.spring.web.dao;

import com.spring.web.model.TSafety;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO (安全生产标准化的引用数据)
 *
 * @author 桃红梨白
 * @Date 2019/6/27 10:08
 */
@Repository
public interface TSafetyMapper {
    /**
     * 插入数据,只能一条的插入,不然会出现无法进行对接的情况
     * @return
     */
    Integer insertTSafety(TSafety tSafety);

    List<TSafety> selectAByIndustryType(Integer industryType);
}
