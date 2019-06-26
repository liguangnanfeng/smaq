package com.spring.web.model;

/**
 * TODO (安全生产标准化表)
 *
 * @author 桃红梨白
 * @Date 2019/6/26 11:58
 */
public class TSafetyStandard {
    private Integer id;
    private Integer userId;
    private String  name;
    private Integer industryId;
    private Integer parentId;
    private Integer type;
    private Integer flag;
    private String  content;
    private String  files;
    private String  fileAddress;
    private Integer del;

    public TSafetyStandard() {
    }

    public TSafetyStandard(Integer id, Integer userId, String name, Integer industryId, Integer parentId, Integer type, Integer flag, String content, String files, String fileAddress, Integer del) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.industryId = industryId;
        this.parentId = parentId;
        this.type = type;
        this.flag = flag;
        this.content = content;
        this.files = files;
        this.fileAddress = fileAddress;
        this.del = del;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
