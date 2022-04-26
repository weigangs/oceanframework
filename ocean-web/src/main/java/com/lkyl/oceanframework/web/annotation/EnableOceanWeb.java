package com.lkyl.oceanframework.web.annotation;

import com.lkyl.oceanframework.web.swagger.SwaggerConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties(SwaggerConfigProperties.class)
@EnableOceanErrorConfig
@EnableOceanHttpClient
@EnableGlobalExceptionHandler
//@Import(OceanWebSwaggerConfig.class)
public @interface EnableOceanWeb {
}
