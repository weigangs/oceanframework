package com.lkyl.oceanframework.web.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lkyl.oceanframework.common.utils.utils.ObjectUtils;
import com.lkyl.oceanframework.web.base.BusinessContext;
import com.lkyl.oceanframework.web.base.SysHeader;
import com.lkyl.oceanframework.web.base.WebBusinessContext;
import com.lkyl.oceanframework.web.constant.HeaderConstant;
import com.lkyl.oceanframework.web.util.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 初始化业务数据上线文
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月21日 13:43
 */
public class WebBusinessContextFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        this.initBusinessContext(request);
        //go on
        filterChain.doFilter(request, response);
    }

    /**
     * 初始化业务上下文
     * @param request
     */
    private void initBusinessContext(HttpServletRequest request) {

        SysHeader header = new SysHeader();
        header.setTenantId(request.getHeader(HeaderConstant.TENANT_ID));
        BusinessContext businessContext = new WebBusinessContext(header);
        businessContext.getParameterMap().putAll(request.getParameterMap());
        JSONObject requestBodyJson = this.extractRequestBody(request);
        if(ObjectUtils.isNotEmpty(requestBodyJson)) {
            businessContext.getParameterMap().putAll(requestBodyJson);
        }
        //set thread local
        ContextUtil.setBusinessContext(businessContext);

    }

    private JSONObject extractRequestBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            logger.warn("convert servlet request body to JSONObject error:", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    logger.warn("close bufferedReader error:", ex);
                }
            }
        }

        if(stringBuilder.length() > 0) {
            JSONObject requestBodyJson = JSON.parseObject(stringBuilder.toString());
            if(ObjectUtils.isNotEmpty(requestBodyJson)) {
                return requestBodyJson;
            }
        }

        return null;
    }
}
