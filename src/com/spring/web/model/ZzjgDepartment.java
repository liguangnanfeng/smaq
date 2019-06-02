package com.spring.web.model;

import java.util.Date;

public class ZzjgDepartment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.ctime
     *
     * @mbggenerated
     */
    private Date ctime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.utime
     *
     * @mbggenerated
     */
    private Date utime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.del
     *
     * @mbggenerated
     */
    private Integer del;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.cid
     *
     * @mbggenerated
     */
    private Integer cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.pid
     *
     * @mbggenerated
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.level
     *
     * @mbggenerated
     */
    private Integer level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zzjg_department_tbl.uid
     *
     * @mbggenerated
     */
    private Integer uid;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    public ZzjgDepartment(Integer id, Date ctime, Date utime, Integer del, String name, Integer cid, Integer pid, Integer level, Integer uid) {
        this.id = id;
        this.ctime = ctime;
        this.utime = utime;
        this.del = del;
        this.name = name;
        this.cid = cid;
        this.pid = pid;
        this.level = level;
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    public ZzjgDepartment() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.id
     *
     * @return the value of zzjg_department_tbl.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.id
     *
     * @param id the value for zzjg_department_tbl.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.ctime
     *
     * @return the value of zzjg_department_tbl.ctime
     *
     * @mbggenerated
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.ctime
     *
     * @param ctime the value for zzjg_department_tbl.ctime
     *
     * @mbggenerated
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.utime
     *
     * @return the value of zzjg_department_tbl.utime
     *
     * @mbggenerated
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.utime
     *
     * @param utime the value for zzjg_department_tbl.utime
     *
     * @mbggenerated
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.del
     *
     * @return the value of zzjg_department_tbl.del
     *
     * @mbggenerated
     */
    public Integer getDel() {
        return del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.del
     *
     * @param del the value for zzjg_department_tbl.del
     *
     * @mbggenerated
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.name
     *
     * @return the value of zzjg_department_tbl.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.name
     *
     * @param name the value for zzjg_department_tbl.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.cid
     *
     * @return the value of zzjg_department_tbl.cid
     *
     * @mbggenerated
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.cid
     *
     * @param cid the value for zzjg_department_tbl.cid
     *
     * @mbggenerated
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.pid
     *
     * @return the value of zzjg_department_tbl.pid
     *
     * @mbggenerated
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.pid
     *
     * @param pid the value for zzjg_department_tbl.pid
     *
     * @mbggenerated
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.level
     *
     * @return the value of zzjg_department_tbl.level
     *
     * @mbggenerated
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.level
     *
     * @param level the value for zzjg_department_tbl.level
     *
     * @mbggenerated
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zzjg_department_tbl.uid
     *
     * @return the value of zzjg_department_tbl.uid
     *
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zzjg_department_tbl.uid
     *
     * @param uid the value for zzjg_department_tbl.uid
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
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
        ZzjgDepartment other = (ZzjgDepartment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()))
            && (this.getDel() == null ? other.getDel() == null : this.getDel().equals(other.getDel()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        result = prime * result + ((getDel() == null) ? 0 : getDel().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zzjg_department_tbl
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
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", del=").append(del);
        sb.append(", name=").append(name);
        sb.append(", cid=").append(cid);
        sb.append(", pid=").append(pid);
        sb.append(", level=").append(level);
        sb.append(", uid=").append(uid);
        sb.append("]");
        return sb.toString();
    }
}