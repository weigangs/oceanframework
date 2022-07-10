package com.lkyl.oceanframework.common.utils.enums;

/**
 * @author nicholas
 */
public enum DelFlagEnum {

    //已删除
    YES("1", "是"),
    //未删除
    NO("0", "否");

    private String code;

    private String msg;

    DelFlagEnum(String code, String msg){
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
