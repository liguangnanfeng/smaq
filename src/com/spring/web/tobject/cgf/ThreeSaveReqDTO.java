/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.tobject.cgf;

import java.util.List;
import com.spring.web.model.TCheck;
import com.spring.web.tobject.DTO;

/**
 * @Title: ModelSaveReqDTO
 * @Description: 
 * @author CGF
 * @date 2017年9月13日 上午9:29:45
 * @version V1.0
 */
public class ThreeSaveReqDTO implements DTO {
    /**
     * @Fields serialVersionUID : 
     */
    private static final long serialVersionUID = -269168543571921411L;
    private TCheck check;
    private List<ThreePartReqDTO> partList;

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
    public List<ThreePartReqDTO> getPartList() {
        return partList;
    }

    /**
     * @param partList 要设置的 partList
     */
    public void setPartList(List<ThreePartReqDTO> partList) {
        this.partList = partList;
    }
}
