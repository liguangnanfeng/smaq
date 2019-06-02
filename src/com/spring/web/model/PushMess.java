package com.spring.web.model;

public class PushMess {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column push_mess_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column push_mess_tbl.district_id
     *
     * @mbggenerated
     */
    private String districtId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column push_mess_tbl.notice_id
     *
     * @mbggenerated
     */
    private Integer noticeId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table push_mess_tbl
     *
     * @mbggenerated
     */
    public PushMess(Integer id, String districtId, Integer noticeId) {
        this.id = id;
        this.districtId = districtId;
        this.noticeId = noticeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table push_mess_tbl
     *
     * @mbggenerated
     */
    public PushMess() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column push_mess_tbl.id
     *
     * @return the value of push_mess_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column push_mess_tbl.id
     *
     * @param id the value for push_mess_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column push_mess_tbl.district_id
     *
     * @return the value of push_mess_tbl.district_id
     *
     * @mbggenerated
     */
    public String getDistrictId() {
        return districtId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column push_mess_tbl.district_id
     *
     * @param districtId the value for push_mess_tbl.district_id
     *
     * @mbggenerated
     */
    public void setDistrictId(String districtId) {
        this.districtId = districtId == null ? null : districtId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column push_mess_tbl.notice_id
     *
     * @return the value of push_mess_tbl.notice_id
     *
     * @mbggenerated
     */
    public Integer getNoticeId() {
        return noticeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column push_mess_tbl.notice_id
     *
     * @param noticeId the value for push_mess_tbl.notice_id
     *
     * @mbggenerated
     */
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table push_mess_tbl
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
        PushMess other = (PushMess) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDistrictId() == null ? other.getDistrictId() == null : this.getDistrictId().equals(other.getDistrictId()))
            && (this.getNoticeId() == null ? other.getNoticeId() == null : this.getNoticeId().equals(other.getNoticeId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table push_mess_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDistrictId() == null) ? 0 : getDistrictId().hashCode());
        result = prime * result + ((getNoticeId() == null) ? 0 : getNoticeId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table push_mess_tbl
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
        sb.append(", districtId=").append(districtId);
        sb.append(", noticeId=").append(noticeId);
        sb.append("]");
        return sb.toString();
    }
}