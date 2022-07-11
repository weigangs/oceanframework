package com.lkyl.oceanframework.web.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月10日 22:13
 */
@AllArgsConstructor
@Getter
public enum  WebParseFunEnum {

    IP_FUNCTION("ipFunction", "获取请求IP"),
    USER_FUNCTION("userFunction", "获取user");

    private String functionName;

    private String comment;
}
