package com.lkyl.oceanframework.common.auth.annotation;

import com.lkyl.oceanframework.common.auth.config.SecurityRestConfig;
import com.lkyl.oceanframework.common.auth.properties.OceanOauth2Properties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * merge authorization server and authentication server to one single server
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties({OceanOauth2Properties.class})
@Import(value = {SecurityRestConfig.class})
public @interface OceanOauth2Server {
}
