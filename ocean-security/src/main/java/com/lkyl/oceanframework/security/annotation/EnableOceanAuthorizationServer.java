package com.lkyl.oceanframework.security.annotation;


import com.lkyl.oceanframework.security.config.OceanAuthorizationServerConfigurer;
import com.lkyl.oceanframework.security.config.OceanWebSecurityConfigurer;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {OceanAuthorizationServerConfigurer.class, OceanWebSecurityConfigurer.class})
public @interface EnableOceanAuthorizationServer {
}
