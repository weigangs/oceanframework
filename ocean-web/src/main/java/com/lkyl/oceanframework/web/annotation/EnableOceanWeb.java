package com.lkyl.oceanframework.web.annotation;

import com.lkyl.oceanframework.web.swagger.OceanWebSwaggerConfig;
import com.lkyl.oceanframework.web.swagger.SwaggerConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties(SwaggerConfigProperties.class)
@EnableOceanErrorConfig
@EnableOceanHttpClient
//@Import(OceanWebSwaggerConfig.class)
public @interface EnableOceanWeb {
}
