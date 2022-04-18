package com.lkyl.oceanframework.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年04月18日 22:44
 */
public class OceanLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessToken = request.getParameter("access_token");
        if(StringUtils.isNotBlank(accessToken)){
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            if(oAuth2AccessToken != null){
                tokenStore.removeAccessToken(oAuth2AccessToken);
                OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
                tokenStore.removeRefreshToken(oAuth2RefreshToken);
                tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
            }
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        CommonResult result = new CommonResult();
        result.setCode(CommonCode.SUCCESS);
        result.setMsg(CommonCode.LOGOUT_MSG);
        result.setData(CommonCode.LOGOUT_MSG);
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();

    }
}
