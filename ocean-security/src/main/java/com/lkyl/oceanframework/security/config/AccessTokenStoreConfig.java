package com.lkyl.oceanframework.security.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


public class AccessTokenStoreConfig implements FactoryBean<AccessTokenStoreConfig> {

    @ConditionalOnProperty(name="ocean.security.oauth2.tokenStoreType", havingValue = "redis")
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory){
        return new RedisTokenStore(redisConnectionFactory);
    }


    @ConditionalOnProperty(name="ocean.security.oauth2.tokenStoreType", havingValue = "inMemory")
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();

    }


    @Override
    public AccessTokenStoreConfig getObject() throws Exception {
        return this;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }
}
