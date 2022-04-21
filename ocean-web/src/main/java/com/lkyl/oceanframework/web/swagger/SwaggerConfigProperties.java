package com.lkyl.oceanframework.web.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年04月21日 23:58
 */
@Data
@ConfigurationProperties(prefix = "spring.swagger")
public class SwaggerConfigProperties {
    private String basePackage;
    private String authServerAddress;
}
