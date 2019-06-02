/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.spring.web.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @Title: RolesAuthorizationFilter
 * @Description: 权限过滤
 * TODO 使用shiro安全框架
 * @author CGF
 * @date 2015-11-3 上午10:52:42
 * @version V1.0
 */
public class RolesAuthorizationFilter extends AuthorizationFilter {
    public boolean isAccessAllowed(ServletRequest request1, ServletResponse response1, Object mappedValue)
            throws IOException {
        // TODO Subject操作当前用户
        Subject subject = getSubject(request1, response1);
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (int i = 0; i < rolesArray.length; i++) {
            if (subject.hasRole(rolesArray[i])) {
                return true;
            }
        }
        return false;
    }
}
