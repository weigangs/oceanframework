package com.lkyl.oceanframework.common.utils.enums;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemExceptionEnum implements IBaseException {
    // SYSTEM_ERR
    SYSTEM_ERR(10000, "系统异常！"),
    // CAPTCHA_KEY_ERR
    CAPTCHA_KEY_ERR(10001, "图形验证码错误！"),
    // 解密异常
    DECODE_ERR(10002, "解密异常！"),
    // REQUEST_METHOD_ERR
    REQUEST_METHOD_ERR(10003, "请求方法错误！"),
    // REQUEST_LIMITED
    REQUEST_LIMITED(10004, "访问太过频繁！"),

    // PERMISSION_DENY 11000~
    PERMISSION_DENY(11000, "无操作权限！"),
    // AUTH_FAILED_ERR
    AUTH_FAILED_ERR(11001, "认证失败！"),
    //INVALID_TOKEN
    INVALID_TOKEN(11002, "无效的token！"),
    //
    PARSE_TOKEN_ERR(11003, "无效的token！");

    private int code;

    private String msg;


}
