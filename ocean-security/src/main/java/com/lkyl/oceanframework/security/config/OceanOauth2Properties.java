package com.lkyl.oceanframework.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "ocean.security.oauth2.resource")
public class OceanOauth2Properties {

    private String resourceId;

}
