package com.lkyl.oceanframework.common.utils.exception;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseEnum;
import lombok.Data;

/**
 * @author nicholas
 * @date 2023年05月03日 17:21
 */
@Data
public class BusinessException extends RuntimeException {

    private String errorCode;

    private String errMsg;

    public BusinessException(IBaseEnum baseEnum) {
        this.errorCode = baseEnum.getCode();
        this.errMsg = baseEnum.getMsg();
    }

    public BusinessException(String errorCode, String errMsg) {
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    @Override
    public String getMessage() {
        return this.getErrMsg();
    }

}
