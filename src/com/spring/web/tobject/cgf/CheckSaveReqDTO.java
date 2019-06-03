/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.tobject.cgf;

import com.spring.web.model.TCheck;
import com.spring.web.model.TCheckPart;
import com.spring.web.tobject.DTO;

import java.util.List;

/** 
 * @Title: ModelSaveReqDTO 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author CGF   
 * @date 2017年9月13日 上午9:29:45 
 * @version V1.0   
 */
public class CheckSaveReqDTO implements DTO {

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -269168543571921411L;
    
    private TCheck check;
    
    private List<TCheckPart> partList;
    
    /** 
     * @return check 
     */
    public TCheck getCheck() {
        return check;
    }

    /** 
     * @param check 要设置的 check 
     */
    public void setCheck(TCheck check) {
        this.check = check;
    }

    /** 
     * @return partList 
     */
    public List<TCheckPart> getPartList() {
        return partList;
    }

    /** 
     * @param partList 要设置的 partList 
     */
    public void setPartList(List<TCheckPart> partList) {
        this.partList = partList;
    }

}
