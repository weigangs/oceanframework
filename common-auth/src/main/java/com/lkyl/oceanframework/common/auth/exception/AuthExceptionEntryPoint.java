package com.lkyl.oceanframework.common.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.web.response.CommonResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

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

        CommonResult result = CommonResult.fail(HttpStatus.UNAUTHORIZED.value(),
                authException.getMessage());

        try {
            ObjectMapper mapper = new ObjectMapper();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(HttpStatus.OK.value());
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

}
