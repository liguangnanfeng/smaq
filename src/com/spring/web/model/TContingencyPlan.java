package com.spring.web.model;

import java.io.Serializable;

public class TContingencyPlan implements Serializable {
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -5411633482643290436L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.create_time
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.filing_time
     *
     * @mbggenerated
     */
    private String filingTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.active_time
     *
     * @mbggenerated
     */
    private String activeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.doc_name
     *
     * @mbggenerated
     */
    private String docName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.doc_url
     *
     * @mbggenerated
     */
    private String docUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.djb_name
     *
     * @mbggenerated
     */
    private String djbName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_contingency_plan_tbl.djb_url
     *
     * @mbggenerated
     */
    private String djbUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contingency_plan_tbl
     *
     * @mbggenerated
     */
    public TContingencyPlan(Integer id, Integer userId, String name, String createTime, String filingTime, String activeTime, String docName, String docUrl, String djbName, String djbUrl) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.createTime = createTime;
        this.filingTime = filingTime;
        this.activeTime = activeTime;
        this.docName = docName;
        this.docUrl = docUrl;
        this.djbName = djbName;
        this.djbUrl = djbUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contingency_plan_tbl
     *
     * @mbggenerated
     */
    public TContingencyPlan() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.id
     *
     * @return the value of t_contingency_plan_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.id
     *
     * @param id the value for t_contingency_plan_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.user_id
     *
     * @return the value of t_contingency_plan_tbl.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.user_id
     *
     * @param userId the value for t_contingency_plan_tbl.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.name
     *
     * @return the value of t_contingency_plan_tbl.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.name
     *
     * @param name the value for t_contingency_plan_tbl.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.create_time
     *
     * @return the value of t_contingency_plan_tbl.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.create_time
     *
     * @param createTime the value for t_contingency_plan_tbl.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.filing_time
     *
     * @return the value of t_contingency_plan_tbl.filing_time
     *
     * @mbggenerated
     */
    public String getFilingTime() {
        return filingTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.filing_time
     *
     * @param filingTime the value for t_contingency_plan_tbl.filing_time
     *
     * @mbggenerated
     */
    public void setFilingTime(String filingTime) {
        this.filingTime = filingTime == null ? null : filingTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.active_time
     *
     * @return the value of t_contingency_plan_tbl.active_time
     *
     * @mbggenerated
     */
    public String getActiveTime() {
        return activeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.active_time
     *
     * @param activeTime the value for t_contingency_plan_tbl.active_time
     *
     * @mbggenerated
     */
    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime == null ? null : activeTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.doc_name
     *
     * @return the value of t_contingency_plan_tbl.doc_name
     *
     * @mbggenerated
     */
    public String getDocName() {
        return docName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.doc_name
     *
     * @param docName the value for t_contingency_plan_tbl.doc_name
     *
     * @mbggenerated
     */
    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.doc_url
     *
     * @return the value of t_contingency_plan_tbl.doc_url
     *
     * @mbggenerated
     */
    public String getDocUrl() {
        return docUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.doc_url
     *
     * @param docUrl the value for t_contingency_plan_tbl.doc_url
     *
     * @mbggenerated
     */
    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl == null ? null : docUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.djb_name
     *
     * @return the value of t_contingency_plan_tbl.djb_name
     *
     * @mbggenerated
     */
    public String getDjbName() {
        return djbName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.djb_name
     *
     * @param djbName the value for t_contingency_plan_tbl.djb_name
     *
     * @mbggenerated
     */
    public void setDjbName(String djbName) {
        this.djbName = djbName == null ? null : djbName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_contingency_plan_tbl.djb_url
     *
     * @return the value of t_contingency_plan_tbl.djb_url
     *
     * @mbggenerated
     */
    public String getDjbUrl() {
        return djbUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_contingency_plan_tbl.djb_url
     *
     * @param djbUrl the value for t_contingency_plan_tbl.djb_url
     *
     * @mbggenerated
     */
    public void setDjbUrl(String djbUrl) {
        this.djbUrl = djbUrl == null ? null : djbUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contingency_plan_tbl
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
        TContingencyPlan other = (TContingencyPlan) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getFilingTime() == null ? other.getFilingTime() == null : this.getFilingTime().equals(other.getFilingTime()))
            && (this.getActiveTime() == null ? other.getActiveTime() == null : this.getActiveTime().equals(other.getActiveTime()))
            && (this.getDocName() == null ? other.getDocName() == null : this.getDocName().equals(other.getDocName()))
            && (this.getDocUrl() == null ? other.getDocUrl() == null : this.getDocUrl().equals(other.getDocUrl()))
            && (this.getDjbName() == null ? other.getDjbName() == null : this.getDjbName().equals(other.getDjbName()))
            && (this.getDjbUrl() == null ? other.getDjbUrl() == null : this.getDjbUrl().equals(other.getDjbUrl()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contingency_plan_tbl
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
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getFilingTime() == null) ? 0 : getFilingTime().hashCode());
        result = prime * result + ((getActiveTime() == null) ? 0 : getActiveTime().hashCode());
        result = prime * result + ((getDocName() == null) ? 0 : getDocName().hashCode());
        result = prime * result + ((getDocUrl() == null) ? 0 : getDocUrl().hashCode());
        result = prime * result + ((getDjbName() == null) ? 0 : getDjbName().hashCode());
        result = prime * result + ((getDjbUrl() == null) ? 0 : getDjbUrl().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contingency_plan_tbl
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
        sb.append(", createTime=").append(createTime);
        sb.append(", filingTime=").append(filingTime);
        sb.append(", activeTime=").append(activeTime);
        sb.append(", docName=").append(docName);
        sb.append(", docUrl=").append(docUrl);
        sb.append(", djbName=").append(djbName);
        sb.append(", djbUrl=").append(djbUrl);
        sb.append("]");
        return sb.toString();
    }
}