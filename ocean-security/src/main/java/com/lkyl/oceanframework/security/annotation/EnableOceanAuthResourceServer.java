package com.lkyl.oceanframework.security.annotation;


import com.lkyl.oceanframework.security.config.OceanOauth2Properties;
import com.lkyl.oceanframework.security.config.OceanResourceServerConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableResourceServer
@EnableConfigurationProperties(OceanOauth2Properties.class)
@Import(value = {OceanResourceServerConfigurer.class})
public @interface EnableOceanAuthResourceServer {
}
