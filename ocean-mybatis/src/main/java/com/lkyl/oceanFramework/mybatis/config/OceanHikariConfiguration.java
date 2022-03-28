package com.lkyl.oceanframework.mybatis.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@ConditionalOnProperty(name = "ocean.datasource.type", havingValue = "hikari")
public class OceanHikariConfiguration{

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(dataSourceProperties.getDriverClassName());
        config.addDataSourceProperty("url", dataSourceProperties.getUrl());
        config.addDataSourceProperty("user", dataSourceProperties.getUsername());
        config.setIdleTimeout(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        config.addDataSourceProperty("password", dataSourceProperties.getPassword());
        config.setMaximumPoolSize(dataSourceProperties.getMaxActive());
        config.setMinimumIdle(dataSourceProperties.getInitialSize());
        return new HikariDataSource(config);
    }

}
