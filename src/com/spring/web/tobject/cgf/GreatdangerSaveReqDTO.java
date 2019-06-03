/**  
* Copyright(c)2017 Wuxi TongXiang Co.,Zcl. 
* All right reserved. 
*/ 

package com.spring.web.tobject.cgf;

import com.spring.web.model.FcGreatDangerStudy;
import com.spring.web.model.FcNaturalDangerItem;
import com.spring.web.tobject.DTO;

import java.util.List;

/** 
 * @Title: ModelSaveReqDTO 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author CGF   
 * @date 2018年11月30日 上午19:9:45 
 * @version V1.0   
 */
public class GreatdangerSaveReqDTO implements DTO {

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -269168543571921411L;
    
    private FcGreatDangerStudy study;
    
    private List<FcNaturalDangerItem> list;

    /** 
    * @return list 
    */ 
    
    public List<FcNaturalDangerItem> getList() {
        return list;
    }

    /** 
    * @param list 要设置的 list 
    */
    public void setList(List<FcNaturalDangerItem> list) {
        this.list = list;
    }

    /** 
    * @return study 
    */ 
    
    public FcGreatDangerStudy getStudy() {
        return study;
    }

    /** 
    * @param study 要设置的 study 
    */
    public void setStudy(FcGreatDangerStudy study) {
        this.study = study;
    }
    
}
