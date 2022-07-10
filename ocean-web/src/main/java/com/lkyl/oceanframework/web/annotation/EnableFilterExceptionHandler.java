package com.lkyl.oceanframework.web.annotation;


import com.lkyl.oceanframework.common.utils.config.OceanErrorConfig;
import com.lkyl.oceanframework.web.filter.ExceptionHandlerFilter;
import com.lkyl.oceanframework.web.resolver.OceanFilterHandlerExceptionResolver;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {OceanFilterHandlerExceptionResolver.class, ExceptionHandlerFilter.class})
public @interface EnableFilterExceptionHandler {
}
