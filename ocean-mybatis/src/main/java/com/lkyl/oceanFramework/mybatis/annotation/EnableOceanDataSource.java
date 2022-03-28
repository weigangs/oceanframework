package com.lkyl.oceanframework.mybatis.annotation;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.lkyl.oceanframework.mybatis.config.DataSourceProperties;
import com.lkyl.oceanframework.mybatis.config.OceanDruidConfiguration;
import com.lkyl.oceanframework.mybatis.config.OceanHikariConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties(DataSourceProperties.class)
@ImportAutoConfiguration(value = {OceanDruidConfiguration.class, OceanHikariConfiguration.class}, exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public @interface EnableOceanDataSource {
}
