package com.lkyl.oceanframework.security.annotation;

import com.lkyl.oceanframework.security.config.*;
import com.lkyl.oceanframework.security.provider.OceanLoginAuthenticationProvider;
import com.lkyl.oceanframework.security.swagger.OceanSecuritySwaggerConfig;
import com.lkyl.oceanframework.security.swagger.SwaggerConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * merge authorization server and authentication server to one single server
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties({OceanOauth2Properties.class, SwaggerConfigProperties.class})
@EnableVerifyCode
@Import(value = {AccessTokenStoreConfig.class, TokenStoreInMemory.class,
        Oauth2Config.class, OceanAuthorizationServerConfigurer.class,
        OceanResourceServerConfigurer.class, OceanWebSecurityConfigurer.class,
        OceanSecuritySwaggerConfig.class, OceanLoginAuthenticationProvider.class})
public @interface OceanOauth2Server {
}
