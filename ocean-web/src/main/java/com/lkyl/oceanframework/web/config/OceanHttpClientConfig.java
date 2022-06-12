package com.lkyl.oceanframework.web.config;


import lombok.Data;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@Data
@Configuration
public class OceanHttpClientConfig {

    @Resource
    private OceanHttpClientProperties oceanHttpClientProperties;

    @Resource
    private HttpMessageConverters httpMessageConverters;

    @Bean
    public RestTemplateLogInterceptor restTemplateLogInterceptor(){
        return new RestTemplateLogInterceptor();
    }

    /**
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpMessageConverters.getConverters());
        restTemplate.setRequestFactory(httpRequestFactory());
        restTemplate.getInterceptors().add(restTemplateLogInterceptor());
        return restTemplate;
    }

    /**
     * 初始化HttpComponentsClientHttpRequestFactory
     * @return
     */
    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    /**
     * 实例化连接池，设置连接池管理器。
     * 这里需要以参数形式注入上面实例化的连接池管理器
     *
     * @return
     */
    @Bean
    public HttpClient httpClient() {
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig())
                .setConnectionManager(httpClientConnectionManager())
                .build();
    }

    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     *
     * @return
     */
    @Bean(name = "requestConfig")
    public RequestConfig requestConfig() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(oceanHttpClientProperties.getSocketTimeout()) //服务器返回数据(response)的时间，超过该时间抛出read timeout
                .setConnectTimeout(oceanHttpClientProperties.getConnectTimeout())//连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
                .setConnectionRequestTimeout(oceanHttpClientProperties.getConnectionRequestTimeout())//从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
//                .setStaleConnectionCheckEnabled(oceanHttpClientProperties.isStaleConnectionCheckEnabled())
                .build();
        return requestConfig;
    }

    /**
     * 首先实例化一个连接池管理器，设置最大连接数、并发连接数
     *
     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager httpClientConnectionManager() {
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
//                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager httpClientConnectionManager =
                new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        //最大连接数
        httpClientConnectionManager.setMaxTotal(oceanHttpClientProperties.getMaxTotal());
        //并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(oceanHttpClientProperties.getDefaultMaxPerRoute());
        httpClientConnectionManager.setValidateAfterInactivity(oceanHttpClientProperties.getValidateAfterInactivity());
        return httpClientConnectionManager;
    }

}
