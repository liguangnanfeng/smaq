package com.spring.web.ibatis;

import java.util.List;

/**
 * @Title: PaginationResult
 * @Description: 分页结果
 * @author FL
 * @date 2016年1月26日 上午10:23:58
 * @version V1.0
 * @param <E>
 */
public interface PaginationResult<E> {
    /**
     * 当前页数据 。
     * 
     * @return
     */
    List<E> getData();

    /**
     * 总行数。
     * 
     * @return
     */
    int getRowTotal();

    /**
     * 只分页不统计总行数时true,该值并不真正代表数据库中还有更多的数据，只代表可能还有数据
     * 
     * @return
     */
    boolean hasMoreData();
}
