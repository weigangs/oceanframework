package com.lkyl.oceanframework.mybatis.annotation;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableEncryptableProperties
@EnableOceanPageHelper
@EnableOceanDataSource
public @interface EnableOceanMybatis {
}
