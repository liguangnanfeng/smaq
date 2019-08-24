package com.spring.web.dao;

import java.util.List;
import java.util.Map;

import com.spring.web.ibatis.DynamicParameter;
import com.spring.web.model.TradeClique;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;

public interface TradeCliqueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_clique_tbl
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_clique_tbl
     *
     * @mbggenerated
     */
    int insert(TradeClique record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_clique_tbl
     *
     * @mbggenerated
     */
    int insertSelective(TradeClique record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_clique_tbl
     *
     * @mbggenerated
     */
    TradeClique selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_clique_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TradeClique record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_clique_tbl
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TradeClique record);
    
    DynamicParameter<String, Object> selectForCliqueByUserId(Map<String, Object> m);

    List<Map<String, Object>> selectCompanyByCqlib(@Param("tid") Integer tid);
}