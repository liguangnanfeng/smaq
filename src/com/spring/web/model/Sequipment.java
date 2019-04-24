package com.spring.web.model;

public class Sequipment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.se_name
     *
     * @mbggenerated
     */
    private String seName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.file_number
     *
     * @mbggenerated
     */
    private String fileNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.amount
     *
     * @mbggenerated
     */
    private Integer amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.detection_time
     *
     * @mbggenerated
     */
    private String detectionTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.expiration_time
     *
     * @mbggenerated
     */
    private String expirationTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequipment_tbl.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    public Sequipment(Integer id, Integer userId, String seName, String type, String fileNumber, Integer amount, String detectionTime, String expirationTime, String remark) {
        this.id = id;
        this.userId = userId;
        this.seName = seName;
        this.type = type;
        this.fileNumber = fileNumber;
        this.amount = amount;
        this.detectionTime = detectionTime;
        this.expirationTime = expirationTime;
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    public Sequipment() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.id
     *
     * @return the value of sequipment_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.id
     *
     * @param id the value for sequipment_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.user_id
     *
     * @return the value of sequipment_tbl.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.user_id
     *
     * @param userId the value for sequipment_tbl.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.se_name
     *
     * @return the value of sequipment_tbl.se_name
     *
     * @mbggenerated
     */
    public String getSeName() {
        return seName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.se_name
     *
     * @param seName the value for sequipment_tbl.se_name
     *
     * @mbggenerated
     */
    public void setSeName(String seName) {
        this.seName = seName == null ? null : seName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.type
     *
     * @return the value of sequipment_tbl.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.type
     *
     * @param type the value for sequipment_tbl.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.file_number
     *
     * @return the value of sequipment_tbl.file_number
     *
     * @mbggenerated
     */
    public String getFileNumber() {
        return fileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.file_number
     *
     * @param fileNumber the value for sequipment_tbl.file_number
     *
     * @mbggenerated
     */
    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber == null ? null : fileNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.amount
     *
     * @return the value of sequipment_tbl.amount
     *
     * @mbggenerated
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.amount
     *
     * @param amount the value for sequipment_tbl.amount
     *
     * @mbggenerated
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.detection_time
     *
     * @return the value of sequipment_tbl.detection_time
     *
     * @mbggenerated
     */
    public String getDetectionTime() {
        return detectionTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.detection_time
     *
     * @param detectionTime the value for sequipment_tbl.detection_time
     *
     * @mbggenerated
     */
    public void setDetectionTime(String detectionTime) {
        this.detectionTime = detectionTime == null ? null : detectionTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.expiration_time
     *
     * @return the value of sequipment_tbl.expiration_time
     *
     * @mbggenerated
     */
    public String getExpirationTime() {
        return expirationTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.expiration_time
     *
     * @param expirationTime the value for sequipment_tbl.expiration_time
     *
     * @mbggenerated
     */
    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime == null ? null : expirationTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequipment_tbl.remark
     *
     * @return the value of sequipment_tbl.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequipment_tbl.remark
     *
     * @param remark the value for sequipment_tbl.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
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
        Sequipment other = (Sequipment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSeName() == null ? other.getSeName() == null : this.getSeName().equals(other.getSeName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getFileNumber() == null ? other.getFileNumber() == null : this.getFileNumber().equals(other.getFileNumber()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getDetectionTime() == null ? other.getDetectionTime() == null : this.getDetectionTime().equals(other.getDetectionTime()))
            && (this.getExpirationTime() == null ? other.getExpirationTime() == null : this.getExpirationTime().equals(other.getExpirationTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSeName() == null) ? 0 : getSeName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getFileNumber() == null) ? 0 : getFileNumber().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getDetectionTime() == null) ? 0 : getDetectionTime().hashCode());
        result = prime * result + ((getExpirationTime() == null) ? 0 : getExpirationTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sequipment_tbl
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
        sb.append(", seName=").append(seName);
        sb.append(", type=").append(type);
        sb.append(", fileNumber=").append(fileNumber);
        sb.append(", amount=").append(amount);
        sb.append(", detectionTime=").append(detectionTime);
        sb.append(", expirationTime=").append(expirationTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}