package com.lkyl.oceanframework.web.annotation;


import com.lkyl.oceanframework.web.filter.WebBusinessContextFilter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {WebBusinessContextFilter.class})
public @interface EnableWebContextInitializer {
}
