package com.lkyl.oceanframework.mybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ocean.datasource")
public class DataSourceProperties {
    private String type;
    private String username;
    private String password;
    private String url;
    private String validateQuery = "SELECT 1";
    private Boolean testOnBorrow = false;
    private Boolean testOnReturn = false;
    private Boolean testWhileIdle = true;
    private Long timeBetweenEvictionRunsMillis = 60000L;
    private Integer maxActive = 20;
    private Integer initialSize = 5;
    private String driverClassName;

}
