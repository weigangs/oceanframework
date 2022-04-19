package com.lkyl.oceanframework.mybatis.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

@ConditionalOnProperty(name = "ocean.datasource.type", havingValue = "hikari")

public class OceanHikariConfiguration{

//    @Resource
//    private StandardPBEStringEncryptor standardPBEStringEncryptor;

    @Bean
    public DataSource dataSource(OceanDataSourceProperties dataSourceProperties) throws SQLException {
//        if(PropertyValueEncryptionUtils.isEncryptedValue(dataSourceProperties.getPassword())){
//            dataSourceProperties.setPassword(PropertyValueEncryptionUtils.decrypt(dataSourceProperties.getPassword(), standardPBEStringEncryptor));
//        }
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(dataSourceProperties.getDriverClassName());
        config.addDataSourceProperty("url", dataSourceProperties.getUrl());
        config.addDataSourceProperty("user", dataSourceProperties.getUsername());
        config.setIdleTimeout(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        config.addDataSourceProperty("password", dataSourceProperties.getPassword());
        config.setMaximumPoolSize(dataSourceProperties.getMaxActive());
        config.setMinimumIdle(dataSourceProperties.getInitialSize());
        DataSource dataSource = new HikariDataSource(config);
        if(dataSourceProperties.getValidateWhenBoot()){//启动程序验证数据库连接
            dataSource.getConnection().close();
        }
        return dataSource;
    }

}
