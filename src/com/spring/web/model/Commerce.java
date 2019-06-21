package com.spring.web.model;

import java.util.Date;

/**
 * 　* @description : TODO
 * 　* @ Author     ：小明！！！
 * 　* @ Date       ：Created in 10:16 2019/6/21
 *   * @ Description：工贸行业
 *
 */
public class Commerce {

    private Integer id;
    private Integer user_id;
    private String com_flag;
    private Date ctime;
    private Date utime;

    public Commerce() {
    }

    public Commerce(Integer id, Integer user_id, String com_flag, Date ctime, Date utime) {
        this.id = id;
        this.user_id = user_id;
        this.com_flag = com_flag;
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

    public String getCom_flag() {
        return com_flag;
    }

    public void setCom_flag(String com_flag) {
        this.com_flag = com_flag;
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
}
