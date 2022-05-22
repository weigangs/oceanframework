package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.generator.OceanOauth2TokenGenerator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@ConditionalOnProperty(name="ocean.security.oauth2.tokenStoreType", havingValue = "redis")
public class AccessTokenStoreConfig{


    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory){
        return new RedisTokenStore(redisConnectionFactory);
    }

}
