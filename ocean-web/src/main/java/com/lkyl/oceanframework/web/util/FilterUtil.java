package com.lkyl.oceanframework.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
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
        CommonResult result = new CommonResult().setCode(CommonCode.EXCEPTION).setMsg(CommonCode.PLATFORM_ERR_MSG);
        if(e instanceof CommonException){
            result.setCode(((CommonException) e).getCode()).setMsg(((CommonException) e).getMsg());
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
