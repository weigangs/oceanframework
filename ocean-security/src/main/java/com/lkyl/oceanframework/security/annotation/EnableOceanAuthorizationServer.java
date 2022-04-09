package com.lkyl.oceanframework.security.annotation;


import com.lkyl.oceanframework.security.config.Oauth2Config;
import com.lkyl.oceanframework.security.config.OceanAuthorizationServerConfigurer;
import com.lkyl.oceanframework.security.config.OceanWebSecurityConfigurer;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableAuthorizationServer
@Import(value = {Oauth2Config.class, OceanAuthorizationServerConfigurer.class, OceanWebSecurityConfigurer.class})
public @interface EnableOceanAuthorizationServer {
}
