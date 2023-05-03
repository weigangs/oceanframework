package com.lkyl.oceanframework.common.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author nicholas
 * @date 2023年05月03日 17:40
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class CommonResult {

    private static final String OK_CODE = "0";

    private static final String OK_MSG = "OK";

    private String code = OK_CODE;
    private String message = OK_MSG;

}
