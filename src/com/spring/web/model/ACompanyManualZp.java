package com.spring.web.model;

public class ACompanyManualZp {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_zp_tbl.acm_id
     *
     * @mbggenerated
     */
    private Integer acmId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_company_manual_zp_tbl.level
     *
     * @mbggenerated
     */
    private String level;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_zp_tbl
     *
     * @mbggenerated
     */
    public ACompanyManualZp(Integer acmId, String level) {
        this.acmId = acmId;
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_zp_tbl
     *
     * @mbggenerated
     */
    public ACompanyManualZp() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_zp_tbl.acm_id
     *
     * @return the value of a_company_manual_zp_tbl.acm_id
     *
     * @mbggenerated
     */
    public Integer getAcmId() {
        return acmId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_zp_tbl.acm_id
     *
     * @param acmId the value for a_company_manual_zp_tbl.acm_id
     *
     * @mbggenerated
     */
    public void setAcmId(Integer acmId) {
        this.acmId = acmId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_company_manual_zp_tbl.level
     *
     * @return the value of a_company_manual_zp_tbl.level
     *
     * @mbggenerated
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_company_manual_zp_tbl.level
     *
     * @param level the value for a_company_manual_zp_tbl.level
     *
     * @mbggenerated
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_zp_tbl
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
        ACompanyManualZp other = (ACompanyManualZp) that;
        return (this.getAcmId() == null ? other.getAcmId() == null : this.getAcmId().equals(other.getAcmId()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_zp_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAcmId() == null) ? 0 : getAcmId().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_company_manual_zp_tbl
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", acmId=").append(acmId);
        sb.append(", level=").append(level);
        sb.append("]");
        return sb.toString();
    }
}