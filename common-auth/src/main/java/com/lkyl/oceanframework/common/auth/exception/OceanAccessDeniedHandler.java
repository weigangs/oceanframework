package com.lkyl.oceanframework.common.auth.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.common.auth.enums.AuthExceptionEnum;
import com.lkyl.oceanframework.web.response.CommonResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 未授权操作，将失败结果转译后 返回给前端
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月03日 18:48
 */
@Slf4j
public class OceanAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)  {
        CommonResult<String> result = CommonResult.fail(AuthExceptionEnum.PERMISSION_DENY);

        try {
            ObjectMapper mapper = new ObjectMapper();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(HttpStatus.OK.value());
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            log.error("error: ", e);
        }
    }
}
