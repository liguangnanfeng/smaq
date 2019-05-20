/**  
* Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
* All right reserved. 
*/ 

package com.spring.web.tobject.cgf;

import com.spring.web.model.TCheckItem;
import com.spring.web.tobject.DTO;

import java.util.List;

public class ThreePartReqDTO implements DTO {

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    
    private static final long serialVersionUID = -269168543571921411L;
    
    private String name;
    
    private List<TCheckItem> list;

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
     * @return list 
     */
    public List<TCheckItem> getList() {
        return list;
    }

    /** 
     * @param list 要设置的 list 
     */
    public void setList(List<TCheckItem> list) {
        this.list = list;
    }
}
