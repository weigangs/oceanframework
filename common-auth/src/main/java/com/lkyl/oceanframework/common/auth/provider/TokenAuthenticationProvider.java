//package com.lkyl.oceanframework.common.auth.provider;
//
//import com.lkyl.oceanframework.common.auth.authentication.UserAuthenticationToken;
//import com.lkyl.oceanframework.common.auth.token.TokenService;
//import com.lkyl.oceanframework.common.utils.constant.OauthConstant;
//import com.lkyl.oceanframework.common.utils.enums.SystemExceptionEnum;
//import com.lkyl.oceanframework.common.utils.exception.CommonException;
//import com.lkyl.oceanframework.common.utils.principal.UserPrincipal;
//import com.lkyl.oceanframework.web.context.UserContext;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextImpl;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collections;
//import java.util.Optional;
//
///**
// * @author nicholas
// * @date 2023/05/15 22:56
// */
//public class TokenAuthenticationProvider implements AuthenticationProvider {
//
//    @Resource
//    private TokenService tokenService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        UserPrincipal user = Optional.ofNullable(tokenService.readUser(
//                        ((UserAuthenticationToken) authentication).getAuthToken()
//                ))
//                .orElseThrow(() -> new CommonException(SystemExceptionEnum.INVALID_TOKEN));
//
//        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(Collections.emptyList());
//        userAuthenticationToken.setAuthenticated(true);
//        userAuthenticationToken.setPrincipal(user);
//
//        UserContext.setUser(user);
//
//        SecurityContextHolder.setContext(new SecurityContextImpl(userAuthenticationToken));
//
//        return userAuthenticationToken;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UserAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
