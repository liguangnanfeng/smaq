package com.spring.web.model;

/**
 * TODO (安全生产标准话的原样表)
 *
 * @author 桃红梨白
 * @Date 2019/6/27 10:05
 */
public class TSafety {

    private Integer id;
    private String name;
    private Integer industryType;
    private String content;
    private Integer parentId;
    private Integer del;


    public TSafety() {
    }

    public TSafety(Integer id, String name, Integer industryType, String content, Integer parentId, Integer del) {
        this.id = id;
        this.name = name;
        this.industryType = industryType;
        this.content = content;
        this.parentId = parentId;
        this.del = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndustryType() {
        return industryType;
    }

    public void setIndustryType(Integer industryType) {
        this.industryType = industryType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
