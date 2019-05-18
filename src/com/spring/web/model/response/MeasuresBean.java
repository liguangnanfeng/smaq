package com.spring.web.model.response;

import lombok.Data;

/**
 * @Author: 桃红梨白
 * @Date: 2019/04/25 17:15
 * 封装的是根据岗位信息查询到的所有的 company_manual信息,但是会进行筛选
 */
@Data
public class MeasuresBean {
    // 风险表id
    public Integer id;
    // 总公司id
    public Integer uid;
    // 关联的标准id,
    public Integer dmid;
    // 较大危险因素
    public String factors;
    // 主要的防治措施
    public String measures;
    // 主要的防范措施
    public String type;
    // 依据
    public String reference;

    @Override
    public String toString() {
        return "MeasuresBean{" +
                "id=" + id +
                ", uid=" + uid +
                ", dmid=" + dmid +
                ", factors='" + factors + '\'' +
                ", measures='" + measures + '\'' +
                ", type='" + type + '\'' +
                ", reference='" + reference + '\'' +
                '}';
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

    public Integer getDmid() {
        return dmid;
    }

    public void setDmid(Integer dmid) {
        this.dmid = dmid;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
