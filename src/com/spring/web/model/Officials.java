package com.spring.web.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Officials implements Serializable {

    private static final long serialVersionUID = 6612833145415630815L;

    public Integer id;


    public String depart;


    public String name;


    public String code;


    public Integer uid;


    public Date time;


    public Integer flag;

    public Integer del;

    public String password;


    public Officials(Integer id, String depart, String name, String code, Integer uid, Date time, Integer flag, Integer del, String password) {
        this.id = id;
        this.depart = depart;
        this.name = name;
        this.code = code;
        this.uid = uid;
        this.time = time;
        this.flag = flag;
        this.del = del;
        this.password = password;
    }

    public Officials() {
    }

    @Override
    public String toString() {
        return "Officials{" +
                "id=" + id +
                ", depart='" + depart + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", uid=" + uid +
                ", time=" + time +
                ", flag=" + flag +
                ", del=" + del +
                ", password='" + password + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}