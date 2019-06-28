package com.spring.web.model;

/**
 * TODO (安全生产标准化表)
 *
 * @author 桃红梨白
 * @Date 2019/6/26 11:58
 */
public class TSafetyStandard {
    /*主键id*/
    private Integer id;
    /*公司id*/
    private Integer userId;
    /*要素名称*/
    private String  name;
    /*行业id 1: 危化  2 : 工贸*/
    private Integer industryId;
    /*上级id*/
    private Integer parentId;
    /*1. 富文本 2.文件上传  3 链接*/
    private Integer type;
    /*1: A级要素  2 : B级要素*/
    private Integer flag;
    /*富文本内容*/
    private String  content;
    /*文件上传地址*/
    private String  files;
    /*链接地址*/
    private String  fileAddress;
    /*是否删除*/
    private Integer del;
    /*排序*/
    private Integer oder;

    public TSafetyStandard() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getOder() {
        return oder;
    }

    public void setOder(Integer oder) {
        this.oder = oder;
    }

    public TSafetyStandard(Integer id, Integer userId, String name, Integer industryId, Integer parentId, Integer type, Integer flag, String content, String files, String fileAddress, Integer del, Integer oder) {
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
        this.oder = oder;
    }
}
