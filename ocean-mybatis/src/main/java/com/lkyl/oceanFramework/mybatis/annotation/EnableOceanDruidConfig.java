package com.lkyl.oceanframework.mybatis.annotation;

import com.lkyl.oceanframework.mybatis.config.OceanDruidConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = OceanDruidConfig.class)
public @interface EnableOceanDruidConfig {
}
