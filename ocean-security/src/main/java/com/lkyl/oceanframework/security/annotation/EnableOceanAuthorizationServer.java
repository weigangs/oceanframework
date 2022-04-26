package com.lkyl.oceanframework.security.annotation;


import com.lkyl.oceanframework.security.config.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties(OceanOauth2Properties.class)
@Import(value = {AccessTokenStoreConfig.class, Oauth2Config.class, OceanAuthorizationServerConfigurer.class, OceanAuthorizationWebSecurityConfigurer.class})
public @interface EnableOceanAuthorizationServer {
}
