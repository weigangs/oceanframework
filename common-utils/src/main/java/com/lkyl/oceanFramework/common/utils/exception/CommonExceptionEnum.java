package com.lkyl.oceanframework.common.utils.exception;

import com.lkyl.oceanframework.common.utils.exception.base.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonExceptionEnum implements IBaseEnum {
    // SYSTEM_ERR
    SYSTEM_ERR("10000", "系统异常！"),
    // CAPTCHA_KEY_ERR
    CAPTCHA_KEY_ERR("10001", "图形验证码错误！"),
    // 解密异常
    DECODE_ERR("10002", "解密异常！"),
    // REQUEST_METHOD_ERR
    REQUEST_METHOD_ERR("10003", "请求方法错误！"),

    // PERMISSION_DENY 11000~
    PERMISSION_DENY("11000", "未授权！"),
    // AUTH_FAILED_ERR
    AUTH_FAILED_ERR("11001", "认证失败！");

    private String code;

    private String msg;


}
