package com.lkyl.oceanframework.codegen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Environment {

    //
    DEFAULT("mybatis_default", "mybatis xml version");
    private final String code;

    private final String msg;
}
