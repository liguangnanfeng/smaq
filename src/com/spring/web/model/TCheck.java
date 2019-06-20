package com.spring.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TCheck implements Serializable {

    private static final long serialVersionUID = -4106012179160875840L;

    private Integer id;

    private Integer flag;

    private String title;

    private String depart;

    private String departImg;

    private Integer userId;

    private Integer createUser;

    private Integer modelId;

    private Integer type;

    private Integer industryId;

    private Integer industryType;

    private Date expectTime;

    private Date realTime;

    private String cheker;

    private String contact;

    private String dapartContact;

    private Integer status;

    private Date createTime;

    private Integer del;

    private String checkCompany;

    private String longitude;

    private String latitude;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDepartImg() {
        return departImg;
    }

    public void setDepartImg(String departImg) {
        this.departImg = departImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getIndustryType() {
        return industryType;
    }

    public void setIndustryType(Integer industryType) {
        this.industryType = industryType;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public Date getRealTime() {
        return realTime;
    }

    public void setRealTime(Date realTime) {
        this.realTime = realTime;
    }

    public String getCheker() {
        return cheker;
    }

    public void setCheker(String cheker) {
        this.cheker = cheker;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDapartContact() {
        return dapartContact;
    }

    public void setDapartContact(String dapartContact) {
        this.dapartContact = dapartContact;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getCheckCompany() {
        return checkCompany;
    }

    public void setCheckCompany(String checkCompany) {
        this.checkCompany = checkCompany;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "TCheck{" +
                "id=" + id +
                ", flag=" + flag +
                ", title='" + title + '\'' +
                ", depart='" + depart + '\'' +
                ", departImg='" + departImg + '\'' +
                ", userId=" + userId +
                ", createUser=" + createUser +
                ", modelId=" + modelId +
                ", type=" + type +
                ", industryId=" + industryId +
                ", industryType=" + industryType +
                ", expectTime=" + expectTime +
                ", realTime=" + realTime +
                ", cheker='" + cheker + '\'' +
                ", contact='" + contact + '\'' +
                ", dapartContact='" + dapartContact + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", del=" + del +
                ", checkCompany='" + checkCompany + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    public TCheck() {
    }

    public TCheck(Integer id, Integer flag, String title, String depart, String departImg, Integer userId, Integer createUser, Integer modelId, Integer type, Integer industryId, Integer industryType, Date expectTime, Date realTime, String cheker, String contact, String dapartContact, Integer status, Date createTime, Integer del, String checkCompany, String longitude, String latitude) {
        this.id = id;
        this.flag = flag;
        this.title = title;
        this.depart = depart;
        this.departImg = departImg;
        this.userId = userId;
        this.createUser = createUser;
        this.modelId = modelId;
        this.type = type;
        this.industryId = industryId;
        this.industryType = industryType;
        this.expectTime = expectTime;
        this.realTime = realTime;
        this.cheker = cheker;
        this.contact = contact;
        this.dapartContact = dapartContact;
        this.status = status;
        this.createTime = createTime;
        this.del = del;
        this.checkCompany = checkCompany;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}



