package com.lkyl.oceanframework.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 配置基于redis的token存储类
 */
@ConditionalOnProperty(name="ocean.security.oauth2.tokenStoreType", havingValue = "redis")
public class AccessTokenStoreConfig{


    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory){
        return new RedisTokenStore(redisConnectionFactory);
    }

}
