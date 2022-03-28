package com.lkyl.oceanframework.common.utils.constant;

public enum DelFlagEnum {

    YES("1", "是"),
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
