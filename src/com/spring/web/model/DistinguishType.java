package com.spring.web.model;

import java.util.Date;
import java.util.Objects;

public class DistinguishType {

    private Integer id;
    private Integer user_id;
    private Integer pid;
    private Integer dep_id;
    private String danger_point;
    private Integer flag;
    private Integer del;
    private Date ctime;
    private Date utime;

    public DistinguishType() {
    }

    public DistinguishType(Integer id, Integer user_id, Integer pid, Integer dep_id, String danger_point, Integer flag, Integer del, Date ctime, Date utime) {
        this.id = id;
        this.user_id = user_id;
        this.pid = pid;
        this.dep_id = dep_id;
        this.danger_point = danger_point;
        this.flag = flag;
        this.del = del;
        this.ctime = ctime;
        this.utime = utime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }

    public String getDanger_point() {
        return danger_point;
    }

    public void setDanger_point(String danger_point) {
        this.danger_point = danger_point;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "DistinguishType{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", pid=" + pid +
                ", dep_id=" + dep_id +
                ", danger_point='" + danger_point + '\'' +
                ", flag=" + flag +
                ", del=" + del +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistinguishType that = (DistinguishType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(dep_id, that.dep_id) &&
                Objects.equals(danger_point, that.danger_point) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(del, that.del) &&
                Objects.equals(ctime, that.ctime) &&
                Objects.equals(utime, that.utime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user_id, pid, dep_id, danger_point, flag, del, ctime, utime);
    }
}
