package com.lkyl.oceanframework.common.utils.exception;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author nicholas
 * @date 2023年05月03日 17:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException implements IBaseException {

    private int errorCode;

    private String errMsg;

    public BusinessException(IBaseException baseEnum) {
        this.errorCode = baseEnum.getCode();
        this.errMsg = baseEnum.getMsg();
    }

    public BusinessException(int errorCode, String errMsg) {
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    public BusinessException(int errorCode, String errMsg, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    @Override
    public String getMessage() {
        return this.getErrMsg();
    }

    @Override
    public int getCode() {
        return this.errorCode;
    }

    @Override
    public String getMsg() {
        return this.getErrMsg();
    }
}
