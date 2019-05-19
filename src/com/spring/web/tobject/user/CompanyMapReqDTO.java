package com.spring.web.tobject.user;

import com.spring.web.tobject.DTO;

/**
 * @Title: CompanyMapReqDTO
 * @Description: 企业地图DTO
 * @author XLB
 * @date 2016年1月21日 上午10:26:08
 * @version V1.0
 */
public class CompanyMapReqDTO implements DTO {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -4458577692485125105L;
    
    private String companyName;
    
    private Integer districtId;
    
    private Integer townId;
    
    private Integer villageId;
    
    private String dlevel;
    
    private Integer page;
    
    private Integer limit;
    
    private String industry;

    /** 
     * @return companyName 
     */
    public String getCompanyName() {
        return companyName;
    }

    /** 
     * @param companyName 要设置的 companyName 
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** 
     * @return districtId 
     */
    public Integer getDistrictId() {
        return districtId;
    }

    /** 
     * @param districtId 要设置的 districtId 
     */
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    /** 
     * @return townId 
     */
    public Integer getTownId() {
        return townId;
    }

    /** 
     * @param townId 要设置的 townId 
     */
    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    /** 
     * @return villageId 
     */
    public Integer getVillageId() {
        return villageId;
    }

    /** 
     * @param villageId 要设置的 villageId 
     */
    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    /** 
     * @return dlevel 
     */
    public String getDlevel() {
        return dlevel;
    }

    /** 
     * @param dlevel 要设置的 dlevel 
     */
    public void setDlevel(String dlevel) {
        this.dlevel = dlevel;
    }

    /** 
     * @return page 
     */
    public Integer getPage() {
        return page;
    }

    /** 
     * @param page 要设置的 page 
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /** 
     * @return limit 
     */
    public Integer getLimit() {
        return limit;
    }

    /** 
     * @param limit 要设置的 limit 
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /** 
     * @return industry 
     */
    public String getIndustry() {
        return industry;
    }

    /** 
     * @param industry 要设置的 industry 
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
