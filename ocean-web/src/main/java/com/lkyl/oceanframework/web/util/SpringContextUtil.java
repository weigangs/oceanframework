package com.lkyl.oceanframework.web.util;

import org.springframework.context.ApplicationContext;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 10:03
 */
public class SpringContextUtil {

    private SpringContextUtil(){}
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static  <T> T getBean(String beanName, Class<T> clazz){
        return (T)applicationContext.getBean(beanName, clazz);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

}
