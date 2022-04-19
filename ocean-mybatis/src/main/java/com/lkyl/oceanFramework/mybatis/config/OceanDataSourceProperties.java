package com.lkyl.oceanframework.mybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ocean.datasource")
public class OceanDataSourceProperties {
    /**
     *
     * 数据库类型
     * 1. druid
     * 2. hikari
     */
    private String type;
    private String username;
    private String password;
    private String url;
    private String validateQuery = "SELECT 1";
    private Boolean testOnBorrow = true;
    private Boolean testOnReturn = false;
    private Boolean testWhileIdle = true;
    private Long timeBetweenEvictionRunsMillis = 60000L;
    private Integer maxActive = 20;
    private Integer initialSize = 5;
    private String driverClassName;
    /**
     * 启动服务时，是否校验数据库配置是否正确
     */
    private Boolean validateWhenBoot = true;

}
