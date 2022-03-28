package com.lkyl.oceanframework.security.annotation;


import com.lkyl.oceanframework.security.config.OceanResourceServerConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {OceanResourceServerConfigurer.class})
public @interface EnableOceanAuthResourceServer {
}
