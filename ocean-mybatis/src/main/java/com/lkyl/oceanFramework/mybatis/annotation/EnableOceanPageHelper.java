package com.lkyl.oceanframework.mybatis.annotation;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.lkyl.oceanframework.mybatis.config.OceanPageHelperAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration(value = OceanPageHelperAutoConfiguration.class, exclude = PageHelperAutoConfiguration.class)
public @interface EnableOceanPageHelper {
}
