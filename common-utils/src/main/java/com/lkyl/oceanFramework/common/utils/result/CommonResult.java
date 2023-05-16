package com.lkyl.oceanframework.common.utils.result;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author nicholas
 * @date 2023年05月03日 17:40
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CommonResult<T> {

    private static final String OK_CODE = "0";

    private static final String OK_MSG = "OK";

    private T data;

    private String code = OK_CODE;
    private String message = OK_MSG;

    public CommonResult(String rspCode, String rspMsg) {
        this.code = rspCode;
        this.message = rspMsg;
    }

    public static <T>  CommonResult<T> ok(T data) {
        return new CommonResult<T>().setData(data);
    }

    public static <T> CommonResult<T> fail(String code, String message) {
        return new CommonResult<>(code, message);
    }

    public static <T> CommonResult<T> fail(IBaseException e) {
        return new CommonResult<>(e.getCode(), e.getMsg());
    }
}
