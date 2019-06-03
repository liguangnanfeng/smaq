package com.spring.web.tobject;

import java.util.List;

/**
 * @Title: DetailviewResDTO
 * @Description: 详情数据输出DTO
 * @author FL
 * @date 2016年1月26日 上午11:57:12
 * @version V1.0
 */
public class DetailviewResDTO implements DTO {
    /**
     * @Fields serialVersionUID : 序列化号
     */
    private static final long serialVersionUID = 1422065925557160184L;
    private Integer total;
    private List<?> rows;

    /**
     * @param total 要设置的 total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param list 要设置的 list
     */
    public void setList(List<?> rows) {
        this.rows = rows;
    }

    /**
     * @return list
     */
    public List<?> getRows() {
        return rows;
    }
}
