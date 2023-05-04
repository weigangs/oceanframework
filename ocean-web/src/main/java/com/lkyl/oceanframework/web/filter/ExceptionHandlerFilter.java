package com.lkyl.oceanframework.web.filter;

import com.lkyl.oceanframework.web.resolver.OceanFilterHandlerExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月03日 18:02
 */

@Slf4j
public class ExceptionHandlerFilter extends GenericFilterBean {

    @Resource
    private OceanFilterHandlerExceptionResolver oceanFilterHandlerExceptionResolver;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain){
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //调用下一个filter链
        try {
            chain.doFilter(servletRequest, response);
        }catch (Exception e){
            oceanFilterHandlerExceptionResolver.resolveException(httpServletRequest,httpServletResponse,null,e);
        }
    }
}
