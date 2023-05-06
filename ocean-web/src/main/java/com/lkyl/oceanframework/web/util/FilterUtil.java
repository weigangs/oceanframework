package com.lkyl.oceanframework.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.exception.SystemExceptionEnum;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月03日 22:00
 */
@Slf4j
public class FilterUtil {

    public static void handlerException(ServletRequest request, ServletResponse response, Exception e){
        CommonResult result = new CommonResult().setCode(SystemExceptionEnum.SYSTEM_ERR.getCode())
                .setMessage(SystemExceptionEnum.SYSTEM_ERR.getMsg());
        if(e instanceof CommonException){
            result.setCode(((CommonException)e).getErrorCode())
                    .setMessage(e.getMessage());
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e2) {
            log.error("error: ", e2);
        }
    }
}
