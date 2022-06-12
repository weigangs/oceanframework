package com.lkyl.oceanframework.common.utils.enums;

/**
 * @author nicholas
 */

public enum TokenStoreTypeEnum {
    IN_MEMORY("InMemory", "内存存储"),
    REDIS("redis", "redis存储");

    private String code;

    private String msg;

    TokenStoreTypeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
