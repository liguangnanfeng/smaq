package com.spring.web.model;

public class DangerLib {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danger_lib_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danger_lib_tbl.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danger_lib_tbl.name2
     *
     * @mbggenerated
     */
    private String name2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danger_lib_tbl.cas
     *
     * @mbggenerated
     */
    private String cas;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column danger_lib_tbl.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table danger_lib_tbl
     *
     * @mbggenerated
     */
    public DangerLib(Integer id, String name, String name2, String cas, String remark) {
        this.id = id;
        this.name = name;
        this.name2 = name2;
        this.cas = cas;
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table danger_lib_tbl
     *
     * @mbggenerated
     */
    public DangerLib() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danger_lib_tbl.id
     *
     * @return the value of danger_lib_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danger_lib_tbl.id
     *
     * @param id the value for danger_lib_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danger_lib_tbl.name
     *
     * @return the value of danger_lib_tbl.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danger_lib_tbl.name
     *
     * @param name the value for danger_lib_tbl.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danger_lib_tbl.name2
     *
     * @return the value of danger_lib_tbl.name2
     *
     * @mbggenerated
     */
    public String getName2() {
        return name2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danger_lib_tbl.name2
     *
     * @param name2 the value for danger_lib_tbl.name2
     *
     * @mbggenerated
     */
    public void setName2(String name2) {
        this.name2 = name2 == null ? null : name2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danger_lib_tbl.cas
     *
     * @return the value of danger_lib_tbl.cas
     *
     * @mbggenerated
     */
    public String getCas() {
        return cas;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danger_lib_tbl.cas
     *
     * @param cas the value for danger_lib_tbl.cas
     *
     * @mbggenerated
     */
    public void setCas(String cas) {
        this.cas = cas == null ? null : cas.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column danger_lib_tbl.remark
     *
     * @return the value of danger_lib_tbl.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column danger_lib_tbl.remark
     *
     * @param remark the value for danger_lib_tbl.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table danger_lib_tbl
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
        DangerLib other = (DangerLib) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getName2() == null ? other.getName2() == null : this.getName2().equals(other.getName2()))
            && (this.getCas() == null ? other.getCas() == null : this.getCas().equals(other.getCas()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table danger_lib_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getName2() == null) ? 0 : getName2().hashCode());
        result = prime * result + ((getCas() == null) ? 0 : getCas().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table danger_lib_tbl
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
        sb.append(", name=").append(name);
        sb.append(", name2=").append(name2);
        sb.append(", cas=").append(cas);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}