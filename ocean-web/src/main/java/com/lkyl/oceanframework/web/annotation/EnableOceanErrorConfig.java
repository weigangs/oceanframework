package com.lkyl.oceanframework.web.annotation;

import com.lkyl.oceanframework.common.utils.config.OceanErrorConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = OceanErrorConfig.class)
public @interface EnableOceanErrorConfig {
}
