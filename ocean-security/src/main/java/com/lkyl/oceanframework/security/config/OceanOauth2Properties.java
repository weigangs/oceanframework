package com.lkyl.oceanframework.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ocean.security.oauth2")
public class OceanOauth2Properties {

    private String resourceId;

    private String tokenStoreType = "inMemory";

    private String publicKey;

    private String privateKey;

}
