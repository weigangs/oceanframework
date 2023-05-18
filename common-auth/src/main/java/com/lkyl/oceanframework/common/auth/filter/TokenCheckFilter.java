package com.lkyl.oceanframework.common.auth.filter;

import com.lkyl.oceanframework.common.auth.authentication.UserAuthenticationToken;
import com.lkyl.oceanframework.common.auth.token.TokenService;
import com.lkyl.oceanframework.common.utils.constant.OauthConstant;
import com.lkyl.oceanframework.common.utils.enums.SystemExceptionEnum;
import com.lkyl.oceanframework.common.utils.principal.UserPrincipal;
import com.lkyl.oceanframework.web.context.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/**
 * @author nicholas
 * @date 2023/05/15 20:44
 */
public class TokenCheckFilter extends OncePerRequestFilter {
    @Resource
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Authentication subject = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(subject) && !(subject instanceof AnonymousAuthenticationToken)) {
            context.setAuthentication(subject);
        } else {
            String token = this.extractToken(request);
            if (StringUtils.isNotBlank(token)) {

                UserPrincipal user = tokenService.readUserByToken(token)
                        .orElseThrow(() -> new AuthenticationServiceException(SystemExceptionEnum.INVALID_TOKEN.getMsg()));

                UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(Collections.emptyList());
                userAuthenticationToken.setAuthenticated(true);
                userAuthenticationToken.setPrincipal(user);

                context.setAuthentication(userAuthenticationToken);

                UserContext.setUser(user);

                tokenService.refreshToken(user.getUserCode());
            }

        }

        Optional.of(context).filter( ctx -> Objects.nonNull(ctx.getAuthentication()))
                .ifPresent(SecurityContextHolder::setContext);

        filterChain.doFilter(request, response);
    }

    public String extractToken(HttpServletRequest request) {

        return Optional.ofNullable(request.getHeader(OauthConstant.TOKEN_PREFIX)).filter(StringUtils::isNotBlank)
                .orElse(Strings.EMPTY);
    }

}
