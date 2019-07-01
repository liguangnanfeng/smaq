package com.spring.web.model;

import java.util.Date;

/**
 * @author ：小明！！！
 * @date ：Created in 2019/6/28 14:53
 * @description：隐患检查计划表
 */
public class HiddenPlan {

    private Integer id;
    private Integer uid;
    private Integer dpid;

    private Integer syn_month;
    private Integer syn_year;
    private String syn_ratio;

    private Integer eve_month;
    private Integer eve_year;
    private String eve_ratio;

    private Integer reg_month;
    private Integer reg_year;
    private String reg_ratio;

    private Integer sea_month;
    private Integer sea_year;
    private String sea_ratio;

    private Integer els_month;
    private Integer els_year;
    private String els_ratio;

    private Integer bas_month;
    private Integer bas_year;
    private String bas_ratio;

    private Integer total_count;
    private String total_ratio;

    private Date create_time;
    private Date update_time;

    public HiddenPlan() {
    }

    public HiddenPlan(Integer id, Integer uid, Integer dpid, Integer syn_month, Integer syn_year, String syn_ratio, Integer eve_month, Integer eve_year, String eve_ratio, Integer reg_month, Integer reg_year, String reg_ratio, Integer sea_month, Integer sea_year, String sea_ratio, Integer els_month, Integer els_year, String els_ratio, Integer bas_month, Integer bas_year, String bas_ratio, Integer total_count, String total_ratio, Date create_time, Date update_time) {
        this.id = id;
        this.uid = uid;
        this.dpid = dpid;
        this.syn_month = syn_month;
        this.syn_year = syn_year;
        this.syn_ratio = syn_ratio;
        this.eve_month = eve_month;
        this.eve_year = eve_year;
        this.eve_ratio = eve_ratio;
        this.reg_month = reg_month;
        this.reg_year = reg_year;
        this.reg_ratio = reg_ratio;
        this.sea_month = sea_month;
        this.sea_year = sea_year;
        this.sea_ratio = sea_ratio;
        this.els_month = els_month;
        this.els_year = els_year;
        this.els_ratio = els_ratio;
        this.bas_month = bas_month;
        this.bas_year = bas_year;
        this.bas_ratio = bas_ratio;
        this.total_count = total_count;
        this.total_ratio = total_ratio;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDpid() {
        return dpid;
    }

    public void setDpid(Integer dpid) {
        this.dpid = dpid;
    }

    public Integer getSyn_month() {
        return syn_month;
    }

    public void setSyn_month(Integer syn_month) {
        this.syn_month = syn_month;
    }

    public Integer getSyn_year() {
        return syn_year;
    }

    public void setSyn_year(Integer syn_year) {
        this.syn_year = syn_year;
    }

    public String getSyn_ratio() {
        return syn_ratio;
    }

    public void setSyn_ratio(String syn_ratio) {
        this.syn_ratio = syn_ratio;
    }

    public Integer getEve_month() {
        return eve_month;
    }

    public void setEve_month(Integer eve_month) {
        this.eve_month = eve_month;
    }

    public Integer getEve_year() {
        return eve_year;
    }

    public void setEve_year(Integer eve_year) {
        this.eve_year = eve_year;
    }

    public String getEve_ratio() {
        return eve_ratio;
    }

    public void setEve_ratio(String eve_ratio) {
        this.eve_ratio = eve_ratio;
    }

    public Integer getReg_month() {
        return reg_month;
    }

    public void setReg_month(Integer reg_month) {
        this.reg_month = reg_month;
    }

    public Integer getReg_year() {
        return reg_year;
    }

    public void setReg_year(Integer reg_year) {
        this.reg_year = reg_year;
    }

    public String getReg_ratio() {
        return reg_ratio;
    }

    public void setReg_ratio(String reg_ratio) {
        this.reg_ratio = reg_ratio;
    }

    public Integer getSea_month() {
        return sea_month;
    }

    public void setSea_month(Integer sea_month) {
        this.sea_month = sea_month;
    }

    public Integer getSea_year() {
        return sea_year;
    }

    public void setSea_year(Integer sea_year) {
        this.sea_year = sea_year;
    }

    public String getSea_ratio() {
        return sea_ratio;
    }

    public void setSea_ratio(String sea_ratio) {
        this.sea_ratio = sea_ratio;
    }

    public Integer getEls_month() {
        return els_month;
    }

    public void setEls_month(Integer els_month) {
        this.els_month = els_month;
    }

    public Integer getEls_year() {
        return els_year;
    }

    public void setEls_year(Integer els_year) {
        this.els_year = els_year;
    }

    public String getEls_ratio() {
        return els_ratio;
    }

    public void setEls_ratio(String els_ratio) {
        this.els_ratio = els_ratio;
    }

    public Integer getBas_month() {
        return bas_month;
    }

    public void setBas_month(Integer bas_month) {
        this.bas_month = bas_month;
    }

    public Integer getBas_year() {
        return bas_year;
    }

    public void setBas_year(Integer bas_year) {
        this.bas_year = bas_year;
    }

    public String getBas_ratio() {
        return bas_ratio;
    }

    public void setBas_ratio(String bas_ratio) {
        this.bas_ratio = bas_ratio;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public String getTotal_ratio() {
        return total_ratio;
    }

    public void setTotal_ratio(String total_ratio) {
        this.total_ratio = total_ratio;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }


    @Override
    public String toString() {
        return "HiddenPlan{" +
                "id=" + id +
                ", uid=" + uid +
                ", dpid=" + dpid +
                ", syn_month=" + syn_month +
                ", syn_year=" + syn_year +
                ", syn_ratio='" + syn_ratio + '\'' +
                ", eve_month=" + eve_month +
                ", eve_year=" + eve_year +
                ", eve_ratio='" + eve_ratio + '\'' +
                ", reg_month=" + reg_month +
                ", reg_year=" + reg_year +
                ", reg_ratio='" + reg_ratio + '\'' +
                ", sea_month=" + sea_month +
                ", sea_year=" + sea_year +
                ", sea_ratio='" + sea_ratio + '\'' +
                ", els_month=" + els_month +
                ", els_year=" + els_year +
                ", els_ratio='" + els_ratio + '\'' +
                ", bas_month=" + bas_month +
                ", bas_year=" + bas_year +
                ", bas_ratio='" + bas_ratio + '\'' +
                ", total_count=" + total_count +
                ", total_ratio='" + total_ratio + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
