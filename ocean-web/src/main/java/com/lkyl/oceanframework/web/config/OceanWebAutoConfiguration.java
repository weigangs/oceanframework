package com.lkyl.oceanframework.web.config;

import com.lkyl.oceanframework.common.utils.config.OceanErrorConfig;
import com.lkyl.oceanframework.web.annotation.EnableOceanWeb;
import com.lkyl.oceanframework.web.exception.GlobalExceptionController;
import com.lkyl.oceanframework.web.filter.ExceptionHandlerFilter;
import com.lkyl.oceanframework.web.filter.WebBusinessContextFilter;
import com.lkyl.oceanframework.web.parsefunction.IpParseFunction;
import com.lkyl.oceanframework.web.resolver.OceanFilterHandlerExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月10日 23:29
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(OceanHttpClientProperties.class)
public class OceanWebAutoConfiguration implements ImportAware {
    private AnnotationAttributes enableWebRecord;

    @Bean
    public OceanFilterHandlerExceptionResolver oceanFilterHandlerExceptionResolver() {
        return new OceanFilterHandlerExceptionResolver();
    }

    @Bean
    public ExceptionHandlerFilter exceptionHandlerFilter() {
        return new ExceptionHandlerFilter();
    }

    @Bean
    public OceanErrorConfig oceanErrorConfig() {
        return new OceanErrorConfig();
    }


    @Bean
    public OceanHttpClientConfig oceanHttpClientConfig() {
        return new OceanHttpClientConfig();
    }

    @Bean
    public GlobalExceptionController globalExceptionController() {
        return new GlobalExceptionController();
    }

    @Bean
    public WebContextInitialConfig webContextInitialConfig() {
        return new WebContextInitialConfig();
    }

    @Bean
    public WebBusinessContextFilter webBusinessContextFilter() {
         return new WebBusinessContextFilter();
    }

    @Bean
    public IpParseFunction ipParseFunction() {
        return new IpParseFunction();
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.enableWebRecord = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableOceanWeb.class.getName(), false));
        if (this.enableWebRecord == null) {
            log.info("@EnableOceanWeb is not present on importing class");
        }
    }
}
