/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.tobject.cgf;

import com.spring.web.tobject.DTO;
import com.spring.web.util.ConstantsUtil;

/** 
 * @Title: ModelSaveReqDTO 
 * @Description:
 * @author CGF
 * @date 2017年9月13日 上午9:29:45 
 * @version V1.0   
 */
public class CompanyListReqDTO implements DTO {

    /** 
    * @Fields serialVersionUID :
    */ 
    
    private static final long serialVersionUID = 6462838326959302623L;

    private String companyName;//企业名称
    
    private String villageName;//村庄名称
    
    @Override
	public String toString() {
		return "CompanyListReqDTO [companyName=" + companyName
				+ ", villageName=" + villageName + ", scale=" + scale
				+ ", isFreeze=" + isFreeze + ", tradeId=" + tradeId
				+ ", districtId=" + districtId + ", townId=" + townId
				+ ", villageId=" + villageId + ", userId=" + userId
				+ ", state=" + state + ", industry=" + industry
				+ ", industry2_=" + industry2_ + ", industry2_2=" + industry2_2
				+ ", industry2=" + industry2 + ", isTradeKey=" + isTradeKey
				+ ", isKey=" + isKey + ", key1=" + key1 + ", key2=" + key2
				+ ", key3=" + key3 + ", key4=" + key4 + ", doubleDanger="
				+ doubleDanger + ", cisDanger=" + cisDanger + ", dlevel="
				+ dlevel + ", danger=" + danger + ", hazard=" + hazard
				+ ", userIds=" + userIds + ", page=" + page + ", rows=" + rows
				+ ", index=" + index + "]";
	}

	/** 
     * @return villageName 
     */
    public String getVillageName() {
        return villageName;
    }

    /** 
     * @param villageName 要设置的 villageName 
     */
    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
    
    /** 
     * @return scale 
     */
    public String getScale() {
        return scale;
    }

    /** 
     * @param scale 要设置的 scale 
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    private String scale;

    private String isFreeze;
    
    private Integer tradeId;
    
    private Integer districtId;
    
    private Integer townId;
    
    private Integer villageId;
    
    private Integer userId;
    
    private String state;
    
    private String industry;
    
    private String industry2_;
    
    private String industry2_2;
    
    private String industry2;
    
    private Integer isTradeKey;//行业重点
    
    private Integer isKey;//村重点
    
    private Integer key1;//镇重点
    
    private Integer key2;//区重点
    
    private Integer key3;//市重点
    
    private Integer key4;//省重点
    
    private String doubleDanger;
    
    private String cisDanger;
    
    private String dlevel;
    
    private Integer danger;
    
    private Integer hazard;
    
    private String userIds;
    
    private Integer page;//页码
    
    private Integer rows;//每页条数
    
    private Integer index;//查询limit起始

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
     * @return isFreeze 
     */
    public String getIsFreeze() {
        return isFreeze;
    }

    /** 
     * @param isFreeze 要设置的 isFreeze 
     */
    public void setIsFreeze(String isFreeze) {
        this.isFreeze = isFreeze;
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
     * @return userId 
     */
    public Integer getUserId() {
        return userId;
    }

    /** 
     * @param userId 要设置的 userId 
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 
     * @return state 
     */
    public String getState() {
        return state;
    }

    /** 
     * @param state 要设置的 state 
     */
    public void setState(String state) {
        this.state = state;
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

    /** 
     * @return industry2_ 
     */
    public String getIndustry2_() {
        return industry2_;
    }

    /** 
     * @param industry2_ 要设置的 industry2_ 
     */
    public void setIndustry2_(String industry2_) {
        this.industry2_ = industry2_;
    }

    /** 
     * @return industry2_2 
     */
    public String getIndustry2_2() {
        return industry2_2;
    }

    /** 
     * @param industry2_2 要设置的 industry2_2 
     */
    public void setIndustry2_2(String industry2_2) {
        this.industry2_2 = industry2_2;
    }

    /** 
     * @return isKey 
     */
    public Integer getIsKey() {
        return isKey;
    }

    /** 
     * @param isKey 要设置的 isKey 
     */
    public void setIsKey(Integer isKey) {
        this.isKey = isKey;
    }

    /** 
     * @return doubleDanger 
     */
    public String getDoubleDanger() {
        return doubleDanger;
    }

    /** 
     * @param doubleDanger 要设置的 doubleDanger 
     */
    public void setDoubleDanger(String doubleDanger) {
        this.doubleDanger = doubleDanger;
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
     * @return cisDanger 
     */
    public String getCisDanger() {
        return cisDanger;
    }

    /** 
     * @param cisDanger 要设置的 cisDanger 
     */
    public void setCisDanger(String cisDanger) {
        this.cisDanger = cisDanger;
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
     * @return danger 
     */
    public Integer getDanger() {
        return danger;
    }

    /** 
     * @param danger 要设置的 danger 
     */
    public void setDanger(Integer danger) {
        this.danger = danger;
    }

    /** 
     * @return hazard 
     */
    public Integer getHazard() {
        return hazard;
    }

    /** 
     * @param hazard 要设置的 hazard 
     */
    public void setHazard(Integer hazard) {
        this.hazard = hazard;
    }

    /** 
    * @return key2 
    */ 
    
    public Integer getKey2() {
        return key2;
    }

    /** 
    * @param key2 要设置的 key2 
    */
    public void setKey2(Integer key2) {
        this.key2 = key2;
    }

    /** 
     * @return isTradeKey 
     */
    public Integer getIsTradeKey() {
        return isTradeKey;
    }

    /** 
     * @param isTradeKey 要设置的 isTradeKey 
     */
    public void setIsTradeKey(Integer isTradeKey) {
        this.isTradeKey = isTradeKey;
    }

    /** 
     * @return key1 
     */
    public Integer getKey1() {
        return key1;
    }

    /** 
     * @param key1 要设置的 key1 
     */
    public void setKey1(Integer key1) {
        this.key1 = key1;
    }

    /** 
    * @return index 
    */ 
    
    public Integer getIndex() {
        if(null == index) {
            setIndex(getPage() * getRows());
        }
        return index;
    }

    /** 
    * @param index 要设置的 index 
    */
    public void setIndex(Integer index) {
        this.index = index;
    }
    

    /** 
    * @return page 
    */ 
    
    public Integer getPage() {
        if(null == page) {
            setPage(0);
        }
        return page;
    }

    /** 
    * @param page 要设置的 page 
    */
    public void setPage(Integer page) {
        this.page = page;
    }

    /** 
    * @return rows 
    */ 
    
    public Integer getRows() {
        if(null == rows) {
            setRows(ConstantsUtil.SELECT_COUNT);
        }
        return rows;
    }

    /** 
    * @param rows 要设置的 rows 
    */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /** 
    * @return industry2 
    */ 
    
    public String getIndustry2() {
        return industry2;
    }

    /** 
    * @param industry2 要设置的 industry2 
    */
    public void setIndustry2(String industry2) {
        this.industry2 = industry2;
    }

    /** 
    * @return tradeId 
    */ 
    
    public Integer getTradeId() {
        return tradeId;
    }

    /** 
    * @param tradeId 要设置的 tradeId 
    */
    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    /** 
    * @return userIds 
    */ 
    
    public String getUserIds() {
        return userIds;
    }

    /** 
    * @param userIds 要设置的 userIds 
    */
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    /** 
    * @return key3 
    */ 
    
    public Integer getKey3() {
        return key3;
    }

    /** 
    * @param key3 要设置的 key3 
    */
    public void setKey3(Integer key3) {
        this.key3 = key3;
    }

    /** 
    * @return key4 
    */ 
    
    public Integer getKey4() {
        return key4;
    }

    /** 
    * @param key4 要设置的 key4 
    */
    public void setKey4(Integer key4) {
        this.key4 = key4;
    }

}
