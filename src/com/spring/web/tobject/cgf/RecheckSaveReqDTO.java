/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.tobject.cgf;

import com.spring.web.model.TRecheck;
import com.spring.web.model.TRecheckItem;
import com.spring.web.tobject.DTO;

import java.util.List;

/** 
 * @Title: ModelSaveReqDTO 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author CGF   
 * @date 2017年9月13日 上午9:29:45 
 * @version V1.0   
 */
public class RecheckSaveReqDTO implements DTO {

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -269168543571921411L;
    
    private TRecheck recheck;
    
    private List<TRecheckItem> list;
    private String nextTime;

    /** 
    * @return list 
    */ 
    
    public List<TRecheckItem> getList() {
        return list;
    }

    /** 
    * @param list 要设置的 list 
    */
    public void setList(List<TRecheckItem> list) {
        this.list = list;
    }

    /** 
    * @return recheck 
    */ 
    
    public TRecheck getRecheck() {
        return recheck;
    }

    /** 
    * @param recheck 要设置的 recheck 
    */
    public void setRecheck(TRecheck recheck) {
        this.recheck = recheck;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }
}
