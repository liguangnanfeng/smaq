package com.spring.web.model;

import java.io.Serializable;

public class TItemSerious implements Serializable {
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = 2010759434091036806L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_item_serious_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_item_serious_tbl.levelid
     *
     * @mbggenerated
     */
    private Integer levelid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_item_serious_tbl.keywords
     *
     * @mbggenerated
     */
    private String keywords;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_item_serious_tbl
     *
     * @mbggenerated
     */
    public TItemSerious(Integer id, Integer levelid, String keywords) {
        this.id = id;
        this.levelid = levelid;
        this.keywords = keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_item_serious_tbl
     *
     * @mbggenerated
     */
    public TItemSerious() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_item_serious_tbl.id
     *
     * @return the value of t_item_serious_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_item_serious_tbl.id
     *
     * @param id the value for t_item_serious_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_item_serious_tbl.levelid
     *
     * @return the value of t_item_serious_tbl.levelid
     *
     * @mbggenerated
     */
    public Integer getLevelid() {
        return levelid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_item_serious_tbl.levelid
     *
     * @param levelid the value for t_item_serious_tbl.levelid
     *
     * @mbggenerated
     */
    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_item_serious_tbl.keywords
     *
     * @return the value of t_item_serious_tbl.keywords
     *
     * @mbggenerated
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_item_serious_tbl.keywords
     *
     * @param keywords the value for t_item_serious_tbl.keywords
     *
     * @mbggenerated
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_item_serious_tbl
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
        TItemSerious other = (TItemSerious) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLevelid() == null ? other.getLevelid() == null : this.getLevelid().equals(other.getLevelid()))
            && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_item_serious_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLevelid() == null) ? 0 : getLevelid().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_item_serious_tbl
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
        sb.append(", levelid=").append(levelid);
        sb.append(", keywords=").append(keywords);
        sb.append("]");
        return sb.toString();
    }
}