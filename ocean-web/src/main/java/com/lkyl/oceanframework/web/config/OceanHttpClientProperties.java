package com.lkyl.oceanframework.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "ocean.httpclient")
public class OceanHttpClientProperties {
    private Integer maxTotal = 300;

    private Integer defaultMaxPerRoute = 100;

    private Integer connectTimeout = 1000000;

    private Integer connectionRequestTimeout = 5000000;

    private Integer socketTimeout = 100000000;

    private Integer validateAfterInactivity = 2000;

    private boolean staleConnectionCheckEnabled = true;

//    //https 证书 路径
//    private String keyStorePath;
//    // 证书密码
//    private String keyStorepass;
}
