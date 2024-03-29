package com.spring.web.model;

import java.util.Date;

public class FcGreatDangerStudy {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column fc_greatdanger_study_t.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column fc_greatdanger_study_t.uid
	 * @mbggenerated
	 */
	private Integer uid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column fc_greatdanger_study_t.dangerstudyid
	 * @mbggenerated
	 */
	private Integer dangerstudyid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column fc_greatdanger_study_t.checker
	 * @mbggenerated
	 */
	private String checker;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column fc_greatdanger_study_t.del
	 * @mbggenerated
	 */
	private Integer del;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column fc_greatdanger_study_t.ctime
	 * @mbggenerated
	 */
	private Date ctime;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_greatdanger_study_t
	 * @mbggenerated
	 */
	public FcGreatDangerStudy(Integer id, Integer uid, Integer dangerstudyid,
			String checker, Integer del, Date ctime) {
		this.id = id;
		this.uid = uid;
		this.dangerstudyid = dangerstudyid;
		this.checker = checker;
		this.del = del;
		this.ctime = ctime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_greatdanger_study_t
	 * @mbggenerated
	 */
	public FcGreatDangerStudy() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column fc_greatdanger_study_t.id
	 * @return  the value of fc_greatdanger_study_t.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column fc_greatdanger_study_t.id
	 * @param id  the value for fc_greatdanger_study_t.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column fc_greatdanger_study_t.uid
	 * @return  the value of fc_greatdanger_study_t.uid
	 * @mbggenerated
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column fc_greatdanger_study_t.uid
	 * @param uid  the value for fc_greatdanger_study_t.uid
	 * @mbggenerated
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column fc_greatdanger_study_t.dangerstudyid
	 * @return  the value of fc_greatdanger_study_t.dangerstudyid
	 * @mbggenerated
	 */
	public Integer getDangerstudyid() {
		return dangerstudyid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column fc_greatdanger_study_t.dangerstudyid
	 * @param dangerstudyid  the value for fc_greatdanger_study_t.dangerstudyid
	 * @mbggenerated
	 */
	public void setDangerstudyid(Integer dangerstudyid) {
		this.dangerstudyid = dangerstudyid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column fc_greatdanger_study_t.checker
	 * @return  the value of fc_greatdanger_study_t.checker
	 * @mbggenerated
	 */
	public String getChecker() {
		return checker;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column fc_greatdanger_study_t.checker
	 * @param checker  the value for fc_greatdanger_study_t.checker
	 * @mbggenerated
	 */
	public void setChecker(String checker) {
		this.checker = checker == null ? null : checker.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column fc_greatdanger_study_t.del
	 * @return  the value of fc_greatdanger_study_t.del
	 * @mbggenerated
	 */
	public Integer getDel() {
		return del;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column fc_greatdanger_study_t.del
	 * @param del  the value for fc_greatdanger_study_t.del
	 * @mbggenerated
	 */
	public void setDel(Integer del) {
		this.del = del;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column fc_greatdanger_study_t.ctime
	 * @return  the value of fc_greatdanger_study_t.ctime
	 * @mbggenerated
	 */
	public Date getCtime() {
		return ctime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column fc_greatdanger_study_t.ctime
	 * @param ctime  the value for fc_greatdanger_study_t.ctime
	 * @mbggenerated
	 */
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_greatdanger_study_t
	 * @mbggenerated
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		FcGreatDangerStudy other = (FcGreatDangerStudy) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getUid() == null ? other.getUid() == null : this
						.getUid().equals(other.getUid()))
				&& (this.getDangerstudyid() == null ? other.getDangerstudyid() == null
						: this.getDangerstudyid().equals(
								other.getDangerstudyid()))
				&& (this.getChecker() == null ? other.getChecker() == null
						: this.getChecker().equals(other.getChecker()))
				&& (this.getDel() == null ? other.getDel() == null : this
						.getDel().equals(other.getDel()))
				&& (this.getCtime() == null ? other.getCtime() == null : this
						.getCtime().equals(other.getCtime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_greatdanger_study_t
	 * @mbggenerated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getUid() == null) ? 0 : getUid().hashCode());
		result = prime
				* result
				+ ((getDangerstudyid() == null) ? 0 : getDangerstudyid()
						.hashCode());
		result = prime * result
				+ ((getChecker() == null) ? 0 : getChecker().hashCode());
		result = prime * result
				+ ((getDel() == null) ? 0 : getDel().hashCode());
		result = prime * result
				+ ((getCtime() == null) ? 0 : getCtime().hashCode());
		return result;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table fc_greatdanger_study_t
	 * @mbggenerated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", uid=").append(uid);
		sb.append(", dangerstudyid=").append(dangerstudyid);
		sb.append(", checker=").append(checker);
		sb.append(", del=").append(del);
		sb.append(", ctime=").append(ctime);
		sb.append("]");
		return sb.toString();
	}
}