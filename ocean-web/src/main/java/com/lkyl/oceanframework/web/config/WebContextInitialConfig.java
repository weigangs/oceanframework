package com.lkyl.oceanframework.web.config;

import com.lkyl.oceanframework.log.config.LogRecordProxyAutoConfiguration;
import com.lkyl.oceanframework.web.filter.WebBusinessContextFilter;
import com.lkyl.oceanframework.web.util.SpringContextUtil;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * web 组件一些需要配合spring启动时初始化的操作
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 10:20
 */

public class WebContextInitialConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.setApplicationContext(applicationContext);
    }
}
