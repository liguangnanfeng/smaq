package com.spring.web.model;

public class AGzk {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_gzk_tbl.uid
     *
     * @mbggenerated
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_gzk_tbl.hxys
     *
     * @mbggenerated
     */
    private String hxys;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_gzk_tbl.wlys
     *
     * @mbggenerated
     */
    private String wlys;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gzk_tbl
     *
     * @mbggenerated
     */
    public AGzk(Integer uid, String hxys, String wlys) {
        this.uid = uid;
        this.hxys = hxys;
        this.wlys = wlys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gzk_tbl
     *
     * @mbggenerated
     */
    public AGzk() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_gzk_tbl.uid
     *
     * @return the value of a_gzk_tbl.uid
     *
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_gzk_tbl.uid
     *
     * @param uid the value for a_gzk_tbl.uid
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_gzk_tbl.hxys
     *
     * @return the value of a_gzk_tbl.hxys
     *
     * @mbggenerated
     */
    public String getHxys() {
        return hxys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_gzk_tbl.hxys
     *
     * @param hxys the value for a_gzk_tbl.hxys
     *
     * @mbggenerated
     */
    public void setHxys(String hxys) {
        this.hxys = hxys == null ? null : hxys.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_gzk_tbl.wlys
     *
     * @return the value of a_gzk_tbl.wlys
     *
     * @mbggenerated
     */
    public String getWlys() {
        return wlys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_gzk_tbl.wlys
     *
     * @param wlys the value for a_gzk_tbl.wlys
     *
     * @mbggenerated
     */
    public void setWlys(String wlys) {
        this.wlys = wlys == null ? null : wlys.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gzk_tbl
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
        AGzk other = (AGzk) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getHxys() == null ? other.getHxys() == null : this.getHxys().equals(other.getHxys()))
            && (this.getWlys() == null ? other.getWlys() == null : this.getWlys().equals(other.getWlys()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gzk_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getHxys() == null) ? 0 : getHxys().hashCode());
        result = prime * result + ((getWlys() == null) ? 0 : getWlys().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_gzk_tbl
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", hxys=").append(hxys);
        sb.append(", wlys=").append(wlys);
        sb.append("]");
        return sb.toString();
    }
}