package com.spring.web.dao;

import com.spring.web.model.AImplementation;

public interface AImplementationMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table a_implementation_tbl
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer uid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table a_implementation_tbl
	 * @mbggenerated
	 */
	int insert(AImplementation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table a_implementation_tbl
	 * @mbggenerated
	 */
	int insertSelective(AImplementation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table a_implementation_tbl
	 * @mbggenerated
	 */
	AImplementation selectByPrimaryKey(Integer uid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table a_implementation_tbl
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(AImplementation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table a_implementation_tbl
	 * @mbggenerated
	 */
	int updateByPrimaryKeyWithBLOBs(AImplementation record);
}