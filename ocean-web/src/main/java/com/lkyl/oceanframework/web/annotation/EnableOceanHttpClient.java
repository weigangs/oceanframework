package com.lkyl.oceanframework.web.annotation;


import com.lkyl.oceanframework.web.config.OceanHttpClientConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration(value = OceanHttpClientConfig.class)
public @interface EnableOceanHttpClient {
}
