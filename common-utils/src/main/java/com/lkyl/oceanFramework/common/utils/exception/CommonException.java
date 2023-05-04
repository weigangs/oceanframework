package com.lkyl.oceanframework.common.utils.exception;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author nicholas
 * @date 2023年05月03日 17:32
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class CommonException extends RuntimeException {

    private String errorCode;

    private String errMsg;

    public CommonException(IBaseEnum iBaseEnum) {
        this.errorCode = iBaseEnum.getCode();
        this.errMsg = iBaseEnum.getMsg();
    }

    @Override
    public String getMessage() {
        return this.getErrMsg();
    }
}
