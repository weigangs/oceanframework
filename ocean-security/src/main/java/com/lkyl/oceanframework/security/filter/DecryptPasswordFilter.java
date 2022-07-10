//package com.lkyl.oceanframework.security.filter;
//
//import com.lkyl.oceanframework.common.utils.constant.CommonCode;
//import com.lkyl.oceanframework.common.utils.exception.CommonException;
//import com.lkyl.oceanframework.common.utils.utils.RSAUtil;
//import com.lkyl.oceanframework.security.wrapper.SecurityRequestWrapper;
//import com.lkyl.oceanframework.web.util.FilterUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * TODO
// *
// * @version 1.0
// * @author: nicholas
// * @createTime: 2022年05月03日 16:36
// */
//public class DecryptPasswordFilter extends GenericFilterBean implements FactoryBean<DecryptPasswordFilter> {
//
//    @Value("${ocean.security.oauth2.privateKey}")
//    private String privateKey;
//
//    //绑定登录路径
//    private static final String defaultFilterProcessUrl = "/oauth/token";
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        SecurityRequestWrapper requestWrapper = new SecurityRequestWrapper(request);;
//        if ("POST".equalsIgnoreCase(requestWrapper.getMethod()) && defaultFilterProcessUrl.equals(requestWrapper.getServletPath())) {
//            String password = requestWrapper.getParameter("password");
//            if(StringUtils.isNotBlank(password)){
//                try {
//                    String decrypt = RSAUtil.decrypt(password, privateKey);
//                    requestWrapper.addParameter("password", decrypt);
//                } catch (Exception e) {
//                    logger.error("error:", e);
//                    FilterUtil.handlerException(servletRequest, servletResponse, new CommonException(CommonCode.EXCEPTION, "解密异常"));
//                    return;
//                }
//            }
//        }
//        filterChain.doFilter(requestWrapper, response);
//    }
//
//    @Override
//    public DecryptPasswordFilter getObject() throws Exception {
//        return this;
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return this.getClass();
//    }
//}
