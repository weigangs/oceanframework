package com.lkyl.oceanframework.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author nicholas
 */
@AllArgsConstructor
@Getter
public enum ParseFunctionEnum {

    DEFAULT_FUNCTION("defaultFunction", "默认EL解析方法"),
    LOGOUT_FUNC("logoutFunc", "退出登录方法");

    private String functionName;

    private String comment;
}
