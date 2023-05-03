package com.lkyl.oceanframework.log.enums;

import lombok.Getter;

@Getter
public enum SystemEnum {

    USER_NAME("SYSTEM", "SYSTEM");

    private String code;
    private String msg;

    SystemEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
