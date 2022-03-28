package com.lkyl.oceanframework.web.annotation;


import com.lkyl.oceanframework.web.config.OceanHttpClientConfig;
import com.lkyl.oceanframework.web.config.OceanHttpClientProperties;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties(OceanHttpClientProperties.class)
@Import(value = OceanHttpClientConfig.class)
public @interface EnableOceanHttpClient {
}
