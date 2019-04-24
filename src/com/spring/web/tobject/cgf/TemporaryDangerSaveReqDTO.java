/**  
* Copyright(c)2017 Wuxi TongXiang Co.,wz. 
* All right reserved. 
*/ 

package com.spring.web.tobject.cgf;

import java.util.List;

import com.spring.web.model.FcTemporaryDangerItem;
import com.spring.web.model.FcTemporaryDangerStudy;
import com.spring.web.tobject.DTO;

/** 
 * @Title: ModelSaveReqDTO 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author CGF   
 * @date 2018年12月07日 上午09:00:45 
 * @version V1.0   
 */
public class TemporaryDangerSaveReqDTO implements DTO {

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -269168543571921411L;
    
    private FcTemporaryDangerStudy study;
    
    private List<FcTemporaryDangerItem> list;

	/**
	 * @return the study
	 */
	public FcTemporaryDangerStudy getStudy() {
		return study;
	}

	/**
	 * @param study the study to set
	 */
	public void setStudy(FcTemporaryDangerStudy study) {
		this.study = study;
	}

	/**
	 * @return the list
	 */
	public List<FcTemporaryDangerItem> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<FcTemporaryDangerItem> list) {
		this.list = list;
	}
    
}
