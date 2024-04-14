package com.lkyl.oceanframework.common.utils.result;

import com.lkyl.oceanframework.common.utils.constant.ResultConstant;
import com.lkyl.oceanframework.common.utils.exception.base.IBaseException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author nicholas
 * @date 2023年05月03日 17:40
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    private T data;

    private int code = HttpStatus.OK.value();
    private String message = ResultConstant.OK_MSG;

    public CommonResult(int rspCode, String rspMsg) {
        this.code = rspCode;
        this.message = rspMsg;
    }

    public static <T>  CommonResult<T> ok(T data) {
        return new CommonResult<T>().setData(data);
    }

    public static <T>  CommonResult<T> ok() {
        return new CommonResult<>();
    }

    public static <T> CommonResult<T> fail(int code, String message) {
        return new CommonResult<>(code, message);
    }

    public static <T> CommonResult<T> fail(IBaseException e) {
        return new CommonResult<>(e.getCode(), e.getMsg());
    }


}
