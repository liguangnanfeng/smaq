/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.model;

/** 
* @Title: HarmfulFactor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author FL   
* @date 2017年10月19日 下午5:12:26 
* @version V1.0   
*/
public class HarmfulFactor {
    
    /** 
    *  
    *  
    * @param name
    * @param remark 
    */ 
    
    public HarmfulFactor(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }
    private String name;
    private String remark;
    /** 
    * @return name 
    */
    public String getName() {
        return name;
    }
    /** 
    * @param name 要设置的 name 
    */
    public void setName(String name) {
        this.name = name;
    }
    /** 
    * @return remark 
    */
    public String getRemark() {
        return remark;
    }
    /** 
    * @param remark 要设置的 remark 
    */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
