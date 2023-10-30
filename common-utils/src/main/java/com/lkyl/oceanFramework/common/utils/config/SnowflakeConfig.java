package com.lkyl.oceanframework.common.utils.config;

import cn.hutool.core.lang.Snowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nicholas
 * @date 2023/05/07 18:11
 * 使用spring bean的单例模式，初始化雪花算法
 */
@Configuration
public class SnowflakeConfig {

    @Value("${snowflake.workerId:1}")
    private long workerId;  //第几号机器

    @Value("${snowflake.datacenterId:2}")
    private long datacenterId;  //第几号机房

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(workerId, datacenterId);
    }

}
