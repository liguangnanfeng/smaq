package com.spring.web.dao;

import java.util.List;
import java.util.Map;

import com.spring.web.model.FcTemporaryDanger;

public interface FcTemporaryDangerMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_temporarydanger_manual_t
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_temporarydanger_manual_t
	 * @mbggenerated
	 */
	int insert(FcTemporaryDanger record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_temporarydanger_manual_t
	 * @mbggenerated
	 */
	int insertSelective(FcTemporaryDanger record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_temporarydanger_manual_t
	 * @mbggenerated
	 */
	FcTemporaryDanger selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_temporarydanger_manual_t
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(FcTemporaryDanger record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_temporarydanger_manual_t
	 * @mbggenerated
	 */
	int updateByPrimaryKey(FcTemporaryDanger record);
	
	List<Map<String, Object>> selectByForeachMap(Map<String, Object> m);

}