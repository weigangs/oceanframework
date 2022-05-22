package com.lkyl.oceanframework.security.translator;

import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * only handler authentication error
 * such as login failed error
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年04月25日 21:17
 */
@Slf4j
public class OceanOauthExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        log.error("error:", e);
        if(e instanceof InvalidGrantException){
            return ResponseEntity.ok(new CommonResult().setCode(CommonCode.NO_AUTH).setMsg(CommonCode.LOGIN_FAILED_MSG));
        }else if(e instanceof AccessDeniedException){
            return ResponseEntity.ok(new CommonResult().setCode(CommonCode.NO_AUTH).setMsg(CommonCode.AUTH_FAILED_MSG));
        }else if(e instanceof InsufficientScopeException){
            return ResponseEntity.ok(new CommonResult().setCode(CommonCode.NO_AUTH).setMsg(CommonCode.INSUFFICIENT_SCOPE_EXCEPTION_MSG));
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            return ResponseEntity.ok(new CommonResult().setCode(CommonCode.NOT_FOUND).setMsg(CommonCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_MSG));
        }else if(e instanceof OAuth2Exception){
            return ResponseEntity.ok(new CommonResult().setCode(CommonCode.NO_AUTH).setMsg(CommonCode.AUTH_FAILED_MSG));
        }
        return ResponseEntity.ok(new CommonResult().setCode(CommonCode.EXCEPTION).setMsg(CommonCode.PLATFORM_ERR_MSG));
    }
}
