package com.spring.web.security;

import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.web.dao.UserMapper;
import com.spring.web.model.User;
import com.spring.web.util.ConstantsUtil;

/**
 * @Title: LanlinRealm
 * @Description: 权限验证
 * @author FL
 * @date 2016年1月21日 下午2:11:05
 * @version V1.0
 */
@Service
@Transactional
public class LanlinRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    /**
     * @param
     * @return
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录的用户名
        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
        // 获取用户对象
        User user = userMapper.selectUserByUserName(loginName);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> roleNames = new HashSet<String>();
            String role = "role_" + user.getUserType();
            if (user.getUserName().equals(ConstantsUtil.NO_PASSWORD_LOGIN3)) {
                role = "role_3";
            } else if (user.getUserName().equals(ConstantsUtil.NO_PASSWORD_LOGIN4)) {
                role = "role_4";
            } else if (user.getUserName().equals(ConstantsUtil.NO_PASSWORD_LOGIN5)) {
                role = "role_5";
            }
            roleNames.add(role);
            // 登录的用户角色
            info.setRoles(roleNames);
            Set<String> permissions = new HashSet<String>();
            info.addStringPermissions(permissions);
            return info;
        }
        return null;
    }

    /**
     * @param arg0
     * @return
     * @throws AuthenticationException
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        SimpleAuthenticationInfo info = null;
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userMapper.selectUserByUserName(token.getUsername());
        if (user != null) {
            String userName = user.getUserName();
            String password = user.getPsw();
            String name = getName();
            info = new SimpleAuthenticationInfo(userName, password, name);
            return info;
        }
        return null;
    }
}
