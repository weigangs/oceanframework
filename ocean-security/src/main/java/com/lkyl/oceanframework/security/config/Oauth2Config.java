package com.lkyl.oceanframework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

public class Oauth2Config {
    @Bean
    public DefaultTokenServices defaultTokenServices(RedisConnectionFactory redisConnectionFactory){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(new RedisTokenStore(redisConnectionFactory));
        return tokenServices;
    }
}
