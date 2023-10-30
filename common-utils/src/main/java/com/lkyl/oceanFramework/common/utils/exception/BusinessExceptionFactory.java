package com.lkyl.oceanframework.common.utils.exception;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseException;

/**
 * @author nicholas
 * @date 2023/07/14 23:17
 */
public class BusinessExceptionFactory {

    public static BusinessException getException(IBaseException baseException) {
        return new BusinessException(baseException);
    }

    public static BusinessException getException(int code, String message) {
        return new BusinessException(code, message);
    }

    public static BusinessException getException(int code, String message, Throwable cause) {
        return new BusinessException(code, message, cause);
    }
}
