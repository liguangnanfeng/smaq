package com.spring.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContextHolder
    implements ApplicationContextAware
{

    public static ApplicationContext getWebApplicationContext()
    {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext arg0)
        throws BeansException
    {
        applicationContext = arg0;
    }

    private static ApplicationContext applicationContext;

}
