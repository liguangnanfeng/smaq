package com.spring.web.model;

import java.io.Serializable;

public class TLevel implements Serializable {

    private static final long serialVersionUID = -9140465473993907575L;

    private Integer id;

    private String industry;

    private String level1;

    private String level2;

    private String level3;

    private String factors;

    private Integer industry_id;

    private String type;

    private String measures;

    private String reference;

    private String name;

    public TLevel() {
    }

    public TLevel(Integer id, String industry, String level1, String level2, String level3, String factors, Integer industry_id, String type, String measures, String reference, String name) {
        this.id = id;
        this.industry = industry;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.factors = factors;
        this.industry_id = industry_id;
        this.type = type;
        this.measures = measures;
        this.reference = reference;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public Integer getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(Integer industry_id) {
        this.industry_id = industry_id;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}