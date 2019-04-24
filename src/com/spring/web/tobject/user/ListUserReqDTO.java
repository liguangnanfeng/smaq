package com.spring.web.tobject.user;

import com.spring.web.tobject.DTO;
import com.spring.web.util.ConstantsUtil;

/**
 * @Title: ListUserReqDTO
 * @Description: 用户列表输入DTO
 * @author FL
 * @date 2016年1月21日 上午10:26:08
 * @version V1.0
 */
public class ListUserReqDTO implements DTO {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -5901299405282579757L;
    /**
     * 会员名
     */
    private String userName;
    /**
     * 是否冻结
     */
    private String isFreeze;
    /**
     * 会员类型
     */
    private String userType;
    /**
     * 当前页显示条数。
     */
    private Integer rows;
    /**
     * 当前页码。
     */
    private Integer page;
    /**
     * 当前记录索引。
     */
    private Integer index;

    /**
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName 要设置的 userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return isFreeze
     */
    public String getIsFreeze() {
        return isFreeze;
    }

    /**
     * @param isFreeze 要设置的 isFreeze
     */
    public void setIsFreeze(String isFreeze) {
        this.isFreeze = isFreeze;
    }

    /**
     * @return userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType 要设置的 userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * (非 Javadoc)
     * 
     * @return
     * @see com.spring.web.tobject.user.ListUserReqDTO#getMaxRows()
     */
    public Integer getRows() {
        if (rows == null) {
            rows = ConstantsUtil.MAX_ROW;
        }
        return rows;
    }

    /**
     * @param maxRows 要设置的 maxRows
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * @return pageNumber
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param pageNumber 要设置的 pageNumber
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @param index 要设置的 index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return index
     */
    public Integer getIndex() {
        index = 0;
        if (rows != null && page != null && page > 0) {
            index = (rows * (page - 1));
        }
        return index;
    }
}
