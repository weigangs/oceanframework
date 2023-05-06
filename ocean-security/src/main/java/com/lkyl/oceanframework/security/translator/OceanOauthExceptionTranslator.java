package com.lkyl.oceanframework.security.translator;

import com.lkyl.oceanframework.common.utils.exception.SystemExceptionEnum;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
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
    public ResponseEntity<?> translate(Exception e) throws Exception {
        log.error("error:", e);
        if(e instanceof AccessDeniedException){
            return ResponseEntity.ok(CommonResult.fail(SystemExceptionEnum.AUTH_FAILED_ERR.getCode(),
                    SystemExceptionEnum.AUTH_FAILED_ERR.getMsg()));
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            return ResponseEntity.ok(CommonResult.fail(SystemExceptionEnum.REQUEST_METHOD_ERR.getCode(),
                    SystemExceptionEnum.REQUEST_METHOD_ERR.getMsg()));
        }
        return ResponseEntity.ok(CommonResult.fail(SystemExceptionEnum.SYSTEM_ERR.getCode(),
                SystemExceptionEnum.SYSTEM_ERR.getMsg()));
    }
}
