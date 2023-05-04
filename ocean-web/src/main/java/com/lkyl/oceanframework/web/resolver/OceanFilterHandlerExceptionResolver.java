package com.lkyl.oceanframework.web.resolver;

import com.lkyl.oceanframework.web.util.FilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @version 1.0
 * @author : nicholas
 * @createTime : 2022年05月03日 18:05
 */
@Slf4j
public class OceanFilterHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("error: ", ex);
        FilterUtil.handlerException(request, response, ex);
        return new ModelAndView();
    }
}
