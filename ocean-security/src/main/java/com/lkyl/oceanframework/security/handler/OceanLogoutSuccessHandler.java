package com.lkyl.oceanframework.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 退出登录处理逻辑
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年04月18日 22:44
 */
@Slf4j
public class OceanLogoutSuccessHandler implements LogoutSuccessHandler, LogoutHandler {

    @Resource
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        this.logout(request, response, authentication);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        String accessToken = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(accessToken)){
            if(accessToken.startsWith("Bearer")){
                accessToken = accessToken.replace("Bearer", "").trim();
            }
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            if(oAuth2AccessToken != null){
                tokenStore.removeAccessToken(oAuth2AccessToken);
                OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
                tokenStore.removeRefreshToken(oAuth2RefreshToken);
                tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
            }
        }
        response.setContentType("application/json;charset=utf-8");
       try {
           PrintWriter out = response.getWriter();
           CommonResult result = new CommonResult();
           result.setCode(CommonCode.SUCCESS);
           result.setMsg(CommonCode.LOGOUT_MSG);
           result.setData(CommonCode.LOGOUT_MSG);
           out.write(new ObjectMapper().writeValueAsString(result));
           out.flush();
           out.close();
       }catch (Exception e) {
           log.error("logout error:", e);
       }

    }
}
