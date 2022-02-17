package com.lkyl.oceanframework.web.config;

import com.lkyl.oceanframework.web.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * 自定义restTemplate拦截器  在拦截器中进行了请求信息的打印，还对请求的返回做了异常处理(造成本次问题的问题的根源)
 */
@Component
@Slf4j
public class RestTemplateLogInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        // 打印访问前日志
        traceRequest(httpRequest, bytes);
        ClientHttpResponse execute = clientHttpRequestExecution.execute(httpRequest, bytes);

        // 请求的统一异常处理
        if (!execute.getStatusCode().is2xxSuccessful()) {
            throw new CommonException().exception(MessageFormat.format("请求错误, url={0}, statusCode={1}, statusText={2}", httpRequest.getURI(), execute.getStatusCode(), execute.getStatusText()));
        }

        // 打印访问后日志
        traceResponse(execute);
        return execute;
    }

    // 打印一条访问前日志
    private void traceRequest(HttpRequest httpRequest, byte[] bytes) {
        log.info("请求开始,url={},method={},RequestBody={}",httpRequest.getURI(), httpRequest.getMethod(), new String(bytes, StandardCharsets.UTF_8));
    }

    // 打印一条访问后日志
    private void traceResponse(ClientHttpResponse response) throws IOException {
        log.info("请求结束,statusCode={},statusText={}",response.getStatusCode(), response.getStatusText());
    }
}
