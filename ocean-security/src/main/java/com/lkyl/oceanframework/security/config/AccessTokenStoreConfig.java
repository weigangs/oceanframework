package com.lkyl.oceanframework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


public class AccessTokenStoreConfig {


//    @ConditionalOnProperty(name="ocean.security.oauth2.tokenStoreType", havingValue = "redis")
//    @Bean
//    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory){
//        return new RedisTokenStore(redisConnectionFactory);
//    }


//    @ConditionalOnProperty(name="ocean.security.oauth2.tokenStoreType", havingValue = "inMemory")
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

}
