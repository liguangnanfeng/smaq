package com.spring.web.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: ControllerInterceptor
 * @Description:
 * @author FL
 * @date 2016年1月26日 上午10:18:52
 * @version V1.0
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {
    /**
     * 最后执行，可用于释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 显示视图前执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println(request.getContentType() + "-----" + request.getCharacterEncoding() + "------"
                + request.getContextPath());
        System.out.println("MyInterceptor.postHandle()---viewName:" + modelAndView.getViewName());
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * Controller之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        System.out.println("MyInterceptor.preHandle()" + url);
        return super.preHandle(request, response, handler);
    }
}
