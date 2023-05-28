package com.lkyl.oceanframework.common.auth.token;

import cn.hutool.core.codec.Base64;
import com.lkyl.oceanframework.common.utils.constant.OauthConstant;
import com.lkyl.oceanframework.common.utils.principal.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class TokenService {
    @Value("${ocean.security.oauth.token.expireMinute:30}")
    private Integer tokenExpireMinute;

    @Resource
    private JwtEncoder jwtEncoder;

    @Resource
    private JwtDecoder jwtDecoder;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String createJwtToken(UserPrincipal userPrincipal) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .claim("userCode", userPrincipal.getUserCode())
                .build();
        // @formatter:on
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Jwt parseJwtToken(String jwtToken){
        return this.jwtDecoder.decode(jwtToken);
    }

    public String readUserRaw(String userCode) {
        return stringRedisTemplate.opsForValue().get(OauthConstant.LOGIN_USER_PREFIX + userCode);
    }

    public void refreshToken(String userCode){
        stringRedisTemplate.expire(OauthConstant.LOGIN_USER_PREFIX + userCode,
                Duration.ofSeconds(tokenExpireMinute * 60));
    }

    public void storeUser(UserPrincipal userPrincipal) {
        stringRedisTemplate.opsForValue().set(OauthConstant.LOGIN_USER_PREFIX + userPrincipal.getUserCode(),
                Base64.encode(SerializationUtils.serialize(userPrincipal)), Duration.ofSeconds(tokenExpireMinute * 60));
    }

    public Optional<UserPrincipal> readUser(String userCode) {
        return Optional.ofNullable(this.readUserRaw(userCode))
                .filter(StringUtils::isNotBlank)
                .map(String::getBytes).map(Base64::decode)
                .map(SerializationUtils::deserialize).map(UserPrincipal.class::cast);
    }

    public Optional<UserPrincipal> readUserByToken(String token) {
        String userCode = (String)this.parseJwtToken(token).getClaims().get("userCode");
        return this.readUser(userCode);
    }

    public void removeToken(String userCode) {

        if (StringUtils.isNotBlank(userCode)) {
            stringRedisTemplate.delete(OauthConstant.LOGIN_USER_PREFIX + userCode);
        }
    }
}
