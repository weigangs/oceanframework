package com.lkyl.oceanframework.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.common.utils.exception.CommonExceptionEnum;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    认证异常转译ENDPOINT
 */
@SuppressWarnings("all")
@Slf4j
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws ServletException {

        log.error("error:", authException);
        CommonResult result = new CommonResult(CommonExceptionEnum.PERMISSION_DENY.getCode(),
                CommonExceptionEnum.PERMISSION_DENY.getMsg());
//        Map map = new HashMap();
//        map.put("code", CommonCode.NO_AUTH);
//        map.put("msg", authException.getMessage());
//        map.put("path", request.getServletPath());
//        map.put("timestamp", String.valueOf(new Date().getTime()));
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

}
