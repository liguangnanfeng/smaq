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
}