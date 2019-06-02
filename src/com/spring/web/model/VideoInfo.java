package com.spring.web.model;

public class VideoInfo {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.id
	 * @mbggenerated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.name
	 * @mbggenerated
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.ip
	 * @mbggenerated
	 */
	private String ip;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.port
	 * @mbggenerated
	 */
	private Integer port;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.hport
	 * @mbggenerated
	 */
	private Integer hport;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.uid
	 * @mbggenerated
	 */
	private String uid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.pwd
	 * @mbggenerated
	 */
	private String pwd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.epid
	 * @mbggenerated
	 */
	private String epid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.bfix
	 * @mbggenerated
	 */
	private String bfix;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column video_info_t.memo
	 * @mbggenerated
	 */
	private String memo;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table video_info_t
	 * @mbggenerated
	 */
	public VideoInfo(Integer id, String name, String ip, Integer port,
			Integer hport, String uid, String pwd, String epid, String bfix,
			String memo) {
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.hport = hport;
		this.uid = uid;
		this.pwd = pwd;
		this.epid = epid;
		this.bfix = bfix;
		this.memo = memo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table video_info_t
	 * @mbggenerated
	 */
	public VideoInfo() {
		super();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.id
	 * @return  the value of video_info_t.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.id
	 * @param id  the value for video_info_t.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.name
	 * @return  the value of video_info_t.name
	 * @mbggenerated
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.name
	 * @param name  the value for video_info_t.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.ip
	 * @return  the value of video_info_t.ip
	 * @mbggenerated
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.ip
	 * @param ip  the value for video_info_t.ip
	 * @mbggenerated
	 */
	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.port
	 * @return  the value of video_info_t.port
	 * @mbggenerated
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.port
	 * @param port  the value for video_info_t.port
	 * @mbggenerated
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.hport
	 * @return  the value of video_info_t.hport
	 * @mbggenerated
	 */
	public Integer getHport() {
		return hport;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.hport
	 * @param hport  the value for video_info_t.hport
	 * @mbggenerated
	 */
	public void setHport(Integer hport) {
		this.hport = hport;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.uid
	 * @return  the value of video_info_t.uid
	 * @mbggenerated
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.uid
	 * @param uid  the value for video_info_t.uid
	 * @mbggenerated
	 */
	public void setUid(String uid) {
		this.uid = uid == null ? null : uid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.pwd
	 * @return  the value of video_info_t.pwd
	 * @mbggenerated
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.pwd
	 * @param pwd  the value for video_info_t.pwd
	 * @mbggenerated
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.epid
	 * @return  the value of video_info_t.epid
	 * @mbggenerated
	 */
	public String getEpid() {
		return epid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.epid
	 * @param epid  the value for video_info_t.epid
	 * @mbggenerated
	 */
	public void setEpid(String epid) {
		this.epid = epid == null ? null : epid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.bfix
	 * @return  the value of video_info_t.bfix
	 * @mbggenerated
	 */
	public String getBfix() {
		return bfix;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.bfix
	 * @param bfix  the value for video_info_t.bfix
	 * @mbggenerated
	 */
	public void setBfix(String bfix) {
		this.bfix = bfix == null ? null : bfix.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column video_info_t.memo
	 * @return  the value of video_info_t.memo
	 * @mbggenerated
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column video_info_t.memo
	 * @param memo  the value for video_info_t.memo
	 * @mbggenerated
	 */
	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table video_info_t
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
		VideoInfo other = (VideoInfo) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getName() == null ? other.getName() == null : this
						.getName().equals(other.getName()))
				&& (this.getIp() == null ? other.getIp() == null : this.getIp()
						.equals(other.getIp()))
				&& (this.getPort() == null ? other.getPort() == null : this
						.getPort().equals(other.getPort()))
				&& (this.getHport() == null ? other.getHport() == null : this
						.getHport().equals(other.getHport()))
				&& (this.getUid() == null ? other.getUid() == null : this
						.getUid().equals(other.getUid()))
				&& (this.getPwd() == null ? other.getPwd() == null : this
						.getPwd().equals(other.getPwd()))
				&& (this.getEpid() == null ? other.getEpid() == null : this
						.getEpid().equals(other.getEpid()))
				&& (this.getBfix() == null ? other.getBfix() == null : this
						.getBfix().equals(other.getBfix()))
				&& (this.getMemo() == null ? other.getMemo() == null : this
						.getMemo().equals(other.getMemo()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table video_info_t
	 * @mbggenerated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
		result = prime * result
				+ ((getPort() == null) ? 0 : getPort().hashCode());
		result = prime * result
				+ ((getHport() == null) ? 0 : getHport().hashCode());
		result = prime * result
				+ ((getUid() == null) ? 0 : getUid().hashCode());
		result = prime * result
				+ ((getPwd() == null) ? 0 : getPwd().hashCode());
		result = prime * result
				+ ((getEpid() == null) ? 0 : getEpid().hashCode());
		result = prime * result
				+ ((getBfix() == null) ? 0 : getBfix().hashCode());
		result = prime * result
				+ ((getMemo() == null) ? 0 : getMemo().hashCode());
		return result;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table video_info_t
	 * @mbggenerated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", name=").append(name);
		sb.append(", ip=").append(ip);
		sb.append(", port=").append(port);
		sb.append(", hport=").append(hport);
		sb.append(", uid=").append(uid);
		sb.append(", pwd=").append(pwd);
		sb.append(", epid=").append(epid);
		sb.append(", bfix=").append(bfix);
		sb.append(", memo=").append(memo);
		sb.append("]");
		return sb.toString();
	}
}