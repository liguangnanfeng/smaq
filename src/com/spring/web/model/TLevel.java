package com.spring.web.model;

import java.io.Serializable;

public class TLevel implements Serializable {

    
    private static final long serialVersionUID = -9140465473993907575L;

    private Integer id;

    private String level1;

    private String level2;

    private String level3;

    private String level4;

    private String type;

    private String measures;

    private Integer industryId;

    private String name;

    public TLevel() {
    }

    public TLevel(Integer id, String level1, String level2, String level3, String level4, String type, String measures, Integer industryId, String name) {
        this.id = id;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
        this.type = type;
        this.measures = measures;
        this.industryId = industryId;
        this.name = name;
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

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLevel4() {
        return level4;
    }

    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}