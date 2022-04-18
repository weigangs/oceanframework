package com.lkyl.oceanframework.web.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableOceanErrorConfig
@EnableOceanHttpClient
public @interface EnableOceanWeb {
}
