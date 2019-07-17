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

    public TSafety() {
    }

    public TSafety(Integer id, String name, Integer industryType, Integer parentId, Integer type, Integer flag, String content, String files, String fileAddress, Integer del, Integer oder) {
        this.id = id;
        this.name = name;
        this.industryType = industryType;
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
