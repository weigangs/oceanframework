package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.generator.OceanOauth2TokenGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * token存储到内存中
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月22日 23:34
 */
@ConditionalOnMissingBean(TokenStore.class)
public class TokenStoreInMemory {

    @Bean
    public TokenStore tokenStore(){
        InMemoryTokenStore tokenStore = new InMemoryTokenStore();
        tokenStore.setAuthenticationKeyGenerator(new OceanOauth2TokenGenerator());
        return tokenStore;

    }
}
