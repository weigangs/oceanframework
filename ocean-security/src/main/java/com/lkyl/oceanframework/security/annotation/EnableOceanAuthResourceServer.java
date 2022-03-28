package com.lkyl.oceanframework.security.annotation;


import com.lkyl.oceanframework.security.config.OceanOauth2Properties;
import com.lkyl.oceanframework.security.config.OceanResourceServerConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties(OceanOauth2Properties.class)
@Import(value = {OceanResourceServerConfigurer.class})
public @interface EnableOceanAuthResourceServer {
}
