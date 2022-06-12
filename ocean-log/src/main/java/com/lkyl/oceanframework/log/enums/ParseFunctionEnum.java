package com.lkyl.oceanframework.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author nicholas
 */
@AllArgsConstructor
@Getter
public enum ParseFunctionEnum {

    DEFAULT_FUNCTION("defaultFunction", "默认EL解析方法");

    private String functionName;

    private String comment;
}
