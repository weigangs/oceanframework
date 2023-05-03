package com.lkyl.oceanframework.security.filter;

import com.lkyl.oceanframework.common.utils.constant.BaseConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.exception.CommonExceptionEnum;
import com.lkyl.oceanframework.web.util.FilterUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  验证码校验过滤器
 */
public class VerifyCodeFilter extends GenericFilterBean {
  //绑定登录路径
  private static final String defaultFilterProcessUrl = "/oauth/token";

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
      String requestCaptcha = request.getParameter(BaseConstant.CAPTCHA_KEY);
      String genCaptcha = (String) request.getSession().getAttribute(BaseConstant.CAPTCHA_KEY);
      CommonException exception = null;
      if (StringUtils.isBlank(requestCaptcha)) {
        exception = new CommonException(CommonExceptionEnum.CAPTCHA_KEY_ERR);
      }else if (StringUtils.isBlank(genCaptcha)) {
        exception = new CommonException(CommonExceptionEnum.CAPTCHA_KEY_ERR);
      }else if(!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
        exception = new CommonException(CommonExceptionEnum.CAPTCHA_KEY_ERR);
      }

      if(null != exception){
        FilterUtil.handlerException(req, resp, exception);
        return;
      }
    }
    chain.doFilter(request, response);
  }
}
