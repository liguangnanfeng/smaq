package com.spring.web.model;

import java.io.Serializable;
import java.util.Date;

public class TCheckDocument implements Serializable {
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -3296670363147734082L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.check_id
     *
     * @mbggenerated
     */
    private Integer checkId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.flag2
     *
     * @mbggenerated
     */
    private Integer flag2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.flag
     *
     * @mbggenerated
     */
    private Integer flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.create_user
     *
     * @mbggenerated
     */
    private Integer createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.del
     *
     * @mbggenerated
     */
    private Integer del;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_check_document_tbl.copy
     *
     * @mbggenerated
     */
    private String copy;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_document_tbl
     *
     * @mbggenerated
     */
    public TCheckDocument(Integer id, Integer checkId, Integer flag2, Integer flag, Integer createUser, Integer userId, Integer del, Date createTime, String copy) {
        this.id = id;
        this.checkId = checkId;
        this.flag2 = flag2;
        this.flag = flag;
        this.createUser = createUser;
        this.userId = userId;
        this.del = del;
        this.createTime = createTime;
        this.copy = copy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_document_tbl
     *
     * @mbggenerated
     */
    public TCheckDocument() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.id
     *
     * @return the value of t_check_document_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.id
     *
     * @param id the value for t_check_document_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.check_id
     *
     * @return the value of t_check_document_tbl.check_id
     *
     * @mbggenerated
     */
    public Integer getCheckId() {
        return checkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.check_id
     *
     * @param checkId the value for t_check_document_tbl.check_id
     *
     * @mbggenerated
     */
    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.flag2
     *
     * @return the value of t_check_document_tbl.flag2
     *
     * @mbggenerated
     */
    public Integer getFlag2() {
        return flag2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.flag2
     *
     * @param flag2 the value for t_check_document_tbl.flag2
     *
     * @mbggenerated
     */
    public void setFlag2(Integer flag2) {
        this.flag2 = flag2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.flag
     *
     * @return the value of t_check_document_tbl.flag
     *
     * @mbggenerated
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.flag
     *
     * @param flag the value for t_check_document_tbl.flag
     *
     * @mbggenerated
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.create_user
     *
     * @return the value of t_check_document_tbl.create_user
     *
     * @mbggenerated
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.create_user
     *
     * @param createUser the value for t_check_document_tbl.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.user_id
     *
     * @return the value of t_check_document_tbl.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.user_id
     *
     * @param userId the value for t_check_document_tbl.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.del
     *
     * @return the value of t_check_document_tbl.del
     *
     * @mbggenerated
     */
    public Integer getDel() {
        return del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.del
     *
     * @param del the value for t_check_document_tbl.del
     *
     * @mbggenerated
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.create_time
     *
     * @return the value of t_check_document_tbl.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.create_time
     *
     * @param createTime the value for t_check_document_tbl.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_check_document_tbl.copy
     *
     * @return the value of t_check_document_tbl.copy
     *
     * @mbggenerated
     */
    public String getCopy() {
        return copy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_check_document_tbl.copy
     *
     * @param copy the value for t_check_document_tbl.copy
     *
     * @mbggenerated
     */
    public void setCopy(String copy) {
        this.copy = copy == null ? null : copy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_document_tbl
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
        TCheckDocument other = (TCheckDocument) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCheckId() == null ? other.getCheckId() == null : this.getCheckId().equals(other.getCheckId()))
            && (this.getFlag2() == null ? other.getFlag2() == null : this.getFlag2().equals(other.getFlag2()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDel() == null ? other.getDel() == null : this.getDel().equals(other.getDel()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCopy() == null ? other.getCopy() == null : this.getCopy().equals(other.getCopy()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_document_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCheckId() == null) ? 0 : getCheckId().hashCode());
        result = prime * result + ((getFlag2() == null) ? 0 : getFlag2().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDel() == null) ? 0 : getDel().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCopy() == null) ? 0 : getCopy().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_check_document_tbl
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
        sb.append(", checkId=").append(checkId);
        sb.append(", flag2=").append(flag2);
        sb.append(", flag=").append(flag);
        sb.append(", createUser=").append(createUser);
        sb.append(", userId=").append(userId);
        sb.append(", del=").append(del);
        sb.append(", createTime=").append(createTime);
        sb.append(", copy=").append(copy);
        sb.append("]");
        return sb.toString();
    }
}