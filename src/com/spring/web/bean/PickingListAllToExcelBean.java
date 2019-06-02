/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.bean;

import java.io.Serializable;

/**
 * @Title: PickingListAllToExcelBean
 * @Description: 导出EXCEL接受Bean
 * @author FL
 * @date 2016年3月28日 上午9:57:48
 * @version V1.0
 */
public class PickingListAllToExcelBean implements Serializable {
    /**
     * @Fields serialVersionUID : 序列化编号
     */
    private static final long serialVersionUID = -2291388941883940691L;
    private String pickingProduct;// 提货商品
    private String cardNumber; // 卡号
    private String pickingNumber; // 提货号
    private String purposeSend; // 用途-派送范围
    private String purposeType;// 用途-商品类型
    private String isSell; // 是否卖出
    private String isPicking; // 是否提货
    private String sendTime;// 发货时间
    private String receipter; // 收货人
    private String receipterMobile; // 联系电话
    private String receipterAddress; // 收货地址
    private String shipCompany; // 快递公司
    private String shipNumber; // 运单号
    private String sellPeople; // 销售负责人
    private String createTime; // 创建时间
    private String issueTime; // 发卡日期
    private String validity; // 有效期
    private String endTime; // 有效期
    private String isEnd; // 有效期状态

    /**
     * @return pickingProduct
     */
    public String getPickingProduct() {
        return pickingProduct;
    }

    /**
     * @param pickingProduct 要设置的 pickingProduct
     */
    public void setPickingProduct(String pickingProduct) {
        this.pickingProduct = pickingProduct;
    }

    /**
     * @return cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber 要设置的 cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return pickingNumber
     */
    public String getPickingNumber() {
        return pickingNumber;
    }

    /**
     * @param pickingNumber 要设置的 pickingNumber
     */
    public void setPickingNumber(String pickingNumber) {
        this.pickingNumber = pickingNumber;
    }

    /**
     * @return purposeSend
     */
    public String getPurposeSend() {
        return purposeSend;
    }

    /**
     * @param purposeSend 要设置的 purposeSend
     */
    public void setPurposeSend(String purposeSend) {
        this.purposeSend = purposeSend;
    }

    /**
     * @return purposeType
     */
    public String getPurposeType() {
        return purposeType;
    }

    /**
     * @param purposeType 要设置的 purposeType
     */
    public void setPurposeType(String purposeType) {
        this.purposeType = purposeType;
    }

    /**
     * @return isSell
     */
    public String getIsSell() {
        return isSell;
    }

    /**
     * @param isSell 要设置的 isSell
     */
    public void setIsSell(String isSell) {
        this.isSell = isSell;
    }

    /**
     * @return isPicking
     */
    public String getIsPicking() {
        return isPicking;
    }

    /**
     * @param isPicking 要设置的 isPicking
     */
    public void setIsPicking(String isPicking) {
        this.isPicking = isPicking;
    }

    /**
     * @return sendTime
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime 要设置的 sendTime
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return receipter
     */
    public String getReceipter() {
        return receipter;
    }

    /**
     * @param receipter 要设置的 receipter
     */
    public void setReceipter(String receipter) {
        this.receipter = receipter;
    }

    /**
     * @return receipterMobile
     */
    public String getReceipterMobile() {
        return receipterMobile;
    }

    /**
     * @param receipterMobile 要设置的 receipterMobile
     */
    public void setReceipterMobile(String receipterMobile) {
        this.receipterMobile = receipterMobile;
    }

    /**
     * @return receipterAddress
     */
    public String getReceipterAddress() {
        return receipterAddress;
    }

    /**
     * @param receipterAddress 要设置的 receipterAddress
     */
    public void setReceipterAddress(String receipterAddress) {
        this.receipterAddress = receipterAddress;
    }

    /**
     * @return shipCompany
     */
    public String getShipCompany() {
        return shipCompany;
    }

    /**
     * @param shipCompany 要设置的 shipCompany
     */
    public void setShipCompany(String shipCompany) {
        this.shipCompany = shipCompany;
    }

    /**
     * @return shipNumber
     */
    public String getShipNumber() {
        return shipNumber;
    }

    /**
     * @param shipNumber 要设置的 shipNumber
     */
    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }

    /**
     * @return sellPeople
     */
    public String getSellPeople() {
        return sellPeople;
    }

    /**
     * @param sellPeople 要设置的 sellPeople
     */
    public void setSellPeople(String sellPeople) {
        this.sellPeople = sellPeople;
    }

    /**
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 要设置的 createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime 要设置的 endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return isEnd
     */
    public String getIsEnd() {
        return isEnd;
    }

    /**
     * @param isEnd 要设置的 isEnd
     */
    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * @return issueTime
     */
    public String getIssueTime() {
        return issueTime;
    }

    /**
     * @param issueTime 要设置的 issueTime
     */
    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    /**
     * @return validity
     */
    public String getValidity() {
        return validity;
    }

    /**
     * @param validity 要设置的 validity
     */
    public void setValidity(String validity) {
        this.validity = validity;
    }
}
