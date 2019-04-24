/**  
 * Copyright(c)2017 Wuxi Lanlin Co.,Ltd. 
 * All right reserved. 
 */
package com.junit;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.spring.web.controller.LoginController;
import com.spring.web.tobject.user.LoginReqDTO;

/**
 * @Title: Junit
 * @Description
 * @author CGF
 * @date 2017-2-8 上午11:27:55
 * @version V1.0
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:WebContent/WEB-INF/spring-servlet.xml"})
public class Junit extends AbstractJUnit4SpringContextTests {
    // 模拟request,response
    private MockHttpServletRequest request;
    @Autowired
    private LoginController loginController;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        request.addParameter("name", "admin");
        request.addParameter("pass", "123456");
        new MockHttpServletResponse();
    }

    @Test
    public void test() {
        LoginReqDTO dto = new LoginReqDTO();
        dto.setPassword("123456");
        dto.setUserName("admin");
        // loginController.login(dto, errors, request, response);
    }
}
