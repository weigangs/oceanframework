package com.lkyl.oceanframework.common.utils.utils;

import com.lkyl.oceanframework.common.utils.exception.BusinessException;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.result.CommonResult;

/**
 * @author nicholas
 * @date 2023/05/04 22:44
 */
public class ResultExceptionUtil {

    public static <T> CommonResult<T> handle(Exception e){
        if (e instanceof BusinessException) {
            return CommonResult.fail(((BusinessException) e).getErrorCode(), e.getMessage());
        }
        throw new CommonException(e);
    }
}
