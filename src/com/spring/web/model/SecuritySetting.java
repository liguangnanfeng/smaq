package com.spring.web.model;

public class SecuritySetting {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column security_setting_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column security_setting_tbl.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column security_setting_tbl.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column security_setting_tbl.creattime
     *
     * @mbggenerated
     */
    private String creattime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_setting_tbl
     *
     * @mbggenerated
     */
    public SecuritySetting(Integer id, Integer userId, String name, String creattime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.creattime = creattime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_setting_tbl
     *
     * @mbggenerated
     */
    public SecuritySetting() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column security_setting_tbl.id
     *
     * @return the value of security_setting_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column security_setting_tbl.id
     *
     * @param id the value for security_setting_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column security_setting_tbl.user_id
     *
     * @return the value of security_setting_tbl.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column security_setting_tbl.user_id
     *
     * @param userId the value for security_setting_tbl.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column security_setting_tbl.name
     *
     * @return the value of security_setting_tbl.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column security_setting_tbl.name
     *
     * @param name the value for security_setting_tbl.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column security_setting_tbl.creattime
     *
     * @return the value of security_setting_tbl.creattime
     *
     * @mbggenerated
     */
    public String getCreattime() {
        return creattime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column security_setting_tbl.creattime
     *
     * @param creattime the value for security_setting_tbl.creattime
     *
     * @mbggenerated
     */
    public void setCreattime(String creattime) {
        this.creattime = creattime == null ? null : creattime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_setting_tbl
     *
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
        SecuritySetting other = (SecuritySetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCreattime() == null ? other.getCreattime() == null : this.getCreattime().equals(other.getCreattime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_setting_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreattime() == null) ? 0 : getCreattime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table security_setting_tbl
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", creattime=").append(creattime);
        sb.append("]");
        return sb.toString();
    }
}