package com.lkyl.oceanframework.web.exception;

import com.lkyl.oceanframework.common.utils.exception.BusinessException;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.exception.SystemExceptionEnum;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

    /**
     * handler system exception
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handlerException(Exception exception){
        log.error("error: ", exception);
        return ResponseEntity.ok(new CommonResult()
                .setCode(SystemExceptionEnum.SYSTEM_ERR.getCode())
                .setMessage(SystemExceptionEnum.SYSTEM_ERR.getMsg()));
    }

    /**
     * handler global CommonException
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<?> handlerCommonException(CommonException e){
        return ResponseEntity.ok(new CommonResult()
                .setCode(e.getErrorCode())
                .setMessage(e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerCommonException(BusinessException e){
        return ResponseEntity.ok(new CommonResult()
                .setCode(e.getErrorCode())
                .setMessage(e.getMessage()));
    }
}
