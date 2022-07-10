package com.lkyl.oceanframework.web.exception;

import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
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
        return ResponseEntity.ok(new CommonResult().setCode(CommonCode.EXCEPTION).setMsg(CommonCode.PLATFORM_ERR_MSG));
    }

    /**
     * handler global CommonException
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<?> handlerCommonException(CommonException e){
        return ResponseEntity.ok(new CommonResult().setCode(e.getCode()).setMsg(e.getMsg()));
    }
}
