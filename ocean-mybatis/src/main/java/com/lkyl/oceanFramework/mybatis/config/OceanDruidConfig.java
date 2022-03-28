package com.lkyl.oceanframework.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "ocean.datasource")
public class OceanDruidConfig {

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

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //dataSource.setDriverClassName(driverClassName);//如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setValidationQuery(validateQuery);//用来检测连接是否有效
        dataSource.setTestOnBorrow(testOnBorrow);//借用连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(testOnReturn);//归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        //连接空闲时检测，如果连接空闲时间大于timeBetweenEvictionRunsMillis指定的毫秒，执行validationQuery指定的SQL来检测连接是否有效
        dataSource.setTestWhileIdle(testWhileIdle);//如果检测失败，则连接将被从池中去除
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);//1分钟
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        return dataSource;
    }

}
