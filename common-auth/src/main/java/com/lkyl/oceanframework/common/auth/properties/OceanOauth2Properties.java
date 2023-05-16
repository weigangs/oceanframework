package com.lkyl.oceanframework.common.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nicholas
 * @date 2023/05/14 22:10
 */
@ConfigurationProperties(prefix = "ocean.security.oauth")
@Component
@Data
public class OceanOauth2Properties {

    private List<String> permittedUrls;

}
