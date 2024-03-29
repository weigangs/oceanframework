package com.lkyl.oceanframework.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.common.utils.enums.SystemExceptionEnum;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败后，将失败结果转译后 返回给前端
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月03日 18:48
 */
@Slf4j
public class OceanAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("error: ", accessDeniedException);
        CommonResult<String> result = CommonResult.fail(SystemExceptionEnum.AUTH_FAILED_ERR.getCode(),
                SystemExceptionEnum.AUTH_FAILED_ERR.getMsg());
        try {
            ObjectMapper mapper = new ObjectMapper();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            log.error("error: ", e);
        }
    }
}
