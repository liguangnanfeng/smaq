/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.tobject.cgf;

import java.util.List;
import com.spring.web.model.TModel;
import com.spring.web.model.TModelPart;
import com.spring.web.tobject.DTO;

/** 
 * @Title: ModelSaveReqDTO 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author CGF   
 * @date 2017年9月13日 上午9:29:45 
 * @version V1.0   
 */
public class ModelSaveReqDTO implements DTO {

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -269168543571921411L;
    
    private TModel model;
    
    private List<TModelPart> partList;

    /** 
     * @return model 
     */
    public TModel getModel() {
        return model;
    }

    /** 
     * @param model 要设置的 model 
     */
    public void setModel(TModel model) {
        this.model = model;
    }

    /** 
     * @return partList 
     */
    public List<TModelPart> getPartList() {
        return partList;
    }

    /** 
     * @param partList 要设置的 partList 
     */
    public void setPartList(List<TModelPart> partList) {
        this.partList = partList;
    }
    
}
