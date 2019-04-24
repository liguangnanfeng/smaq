package com.spring.web.model;

import java.io.Serializable;

public class TTindustry implements Serializable {
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = 2117850168774682872L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_tindustry_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_tindustry_tbl.in1
     *
     * @mbggenerated
     */
    private Integer in1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_tindustry_tbl.in2a
     *
     * @mbggenerated
     */
    private String in2a;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_tindustry_tbl.in2o
     *
     * @mbggenerated
     */
    private String in2o;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tindustry_tbl
     *
     * @mbggenerated
     */
    public TTindustry(Integer id, Integer in1, String in2a, String in2o) {
        this.id = id;
        this.in1 = in1;
        this.in2a = in2a;
        this.in2o = in2o;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tindustry_tbl
     *
     * @mbggenerated
     */
    public TTindustry() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_tindustry_tbl.id
     *
     * @return the value of t_tindustry_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_tindustry_tbl.id
     *
     * @param id the value for t_tindustry_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_tindustry_tbl.in1
     *
     * @return the value of t_tindustry_tbl.in1
     *
     * @mbggenerated
     */
    public Integer getIn1() {
        return in1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_tindustry_tbl.in1
     *
     * @param in1 the value for t_tindustry_tbl.in1
     *
     * @mbggenerated
     */
    public void setIn1(Integer in1) {
        this.in1 = in1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_tindustry_tbl.in2a
     *
     * @return the value of t_tindustry_tbl.in2a
     *
     * @mbggenerated
     */
    public String getIn2a() {
        return in2a;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_tindustry_tbl.in2a
     *
     * @param in2a the value for t_tindustry_tbl.in2a
     *
     * @mbggenerated
     */
    public void setIn2a(String in2a) {
        this.in2a = in2a == null ? null : in2a.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_tindustry_tbl.in2o
     *
     * @return the value of t_tindustry_tbl.in2o
     *
     * @mbggenerated
     */
    public String getIn2o() {
        return in2o;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_tindustry_tbl.in2o
     *
     * @param in2o the value for t_tindustry_tbl.in2o
     *
     * @mbggenerated
     */
    public void setIn2o(String in2o) {
        this.in2o = in2o == null ? null : in2o.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tindustry_tbl
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
        TTindustry other = (TTindustry) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIn1() == null ? other.getIn1() == null : this.getIn1().equals(other.getIn1()))
            && (this.getIn2a() == null ? other.getIn2a() == null : this.getIn2a().equals(other.getIn2a()))
            && (this.getIn2o() == null ? other.getIn2o() == null : this.getIn2o().equals(other.getIn2o()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tindustry_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIn1() == null) ? 0 : getIn1().hashCode());
        result = prime * result + ((getIn2a() == null) ? 0 : getIn2a().hashCode());
        result = prime * result + ((getIn2o() == null) ? 0 : getIn2o().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tindustry_tbl
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
        sb.append(", in1=").append(in1);
        sb.append(", in2a=").append(in2a);
        sb.append(", in2o=").append(in2o);
        sb.append("]");
        return sb.toString();
    }
}