package com.lkyl.oceanframework.web.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lkyl.oceanframework.web.message.converter.TextPlainMessageConverter;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class HttpsFeignConfig {

    @Bean
    public Decoder feignDecoder() {
        ObjectFactory objectFactory = () -> new HttpMessageConverters(new TextPlainMessageConverter());
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

//    @Bean
//    public Encoder feignEncoder(){
//        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
//        ObjectFactory objectFactory = () -> new HttpMessageConverters(jacksonConverter);
//        return new SpringEncoder(objectFactory);
//    }
//    public ObjectMapper customObjectMapper(){
//        ObjectMapper objectMapper = new ObjectMapper();
//        //Customize as much as you want
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//        return objectMapper;
//    }

//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
//
//    @Bean
//    public Client generateClient() {
//        try {
//            SSLContext ctx = SSLContext.getInstance("SSL");
//            X509TrustManager tm = new X509TrustManager() {
//                @Override
//                public void checkClientTrusted(X509Certificate[] chain, String authType) {
//                }
//
//                @Override
//                public void checkServerTrusted(X509Certificate[] chain, String authType) {
//                }
//
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//            };
//            ctx.init(null, new TrustManager[]{tm}, null);
//            return new Client.Default(ctx.getSocketFactory(), (hostname, session) -> true);
//        } catch (Exception e) {
//            return null;
//        }
//    }

}