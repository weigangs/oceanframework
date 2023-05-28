package com.lkyl.oceanframework.web.exception;

import com.lkyl.oceanframework.common.utils.exception.BusinessException;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.enums.SystemExceptionEnum;
import com.lkyl.oceanframework.common.utils.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
    public ResponseEntity<CommonResult<String>> handlerException(Exception exception){
        log.error("error: ", exception);
        return ResponseEntity.ok(CommonResult.fail(SystemExceptionEnum.SYSTEM_ERR.getCode(),
                SystemExceptionEnum.SYSTEM_ERR.getMsg()));
    }

    /**
     * handler global CommonException
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonResult<String>> handlerCommonException(CommonException e){
        log.error("error: ", e);
        return ResponseEntity.ok(CommonResult.fail(e));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResult<String>> handlerCommonException(BusinessException e){
        log.error("error: ", e);
        return ResponseEntity.ok(CommonResult.fail(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResult<String>> handlerMethodArgsException(MethodArgumentNotValidException e) {

        return ResponseEntity.ok(CommonResult.fail(SystemExceptionEnum.SYSTEM_ERR.getCode(),
                this.wrapErrors(e.getBindingResult().getAllErrors())));
    }


    private String wrapErrors(List<ObjectError> errors) {
        if (CollectionUtils.isEmpty(errors)) {
            return "请求参数错误";
        }

        return "["+ errors.get(0).getDefaultMessage() +"]";
    }
}
