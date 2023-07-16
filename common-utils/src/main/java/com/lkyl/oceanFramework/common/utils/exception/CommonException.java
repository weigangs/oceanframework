package com.lkyl.oceanframework.common.utils.exception;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author nicholas
 * @date 2023年05月03日 17:32
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CommonException extends RuntimeException implements IBaseException  {

    private int errorCode;

    private String errMsg;

    public CommonException(Throwable e) {
        super(e);
    }

    public CommonException(IBaseException iBaseException) {
        this.errorCode = iBaseException.getCode();
        this.errMsg = iBaseException.getMsg();
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
