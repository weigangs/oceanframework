package com.lkyl.oceanframework.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author nicholas
 */

@AllArgsConstructor
@Getter
public enum LogRecordEnum {

    CONTENT("content", "文本模板"),
    OPERATOR("operator", "操作人"),
    BIZ_NO("bizNo", "业务编号"),
    CONDITION("condition","条件");

    private String attrName;

    private String message;

    public static LogRecordEnum getByAttrName(String attrName) {
        LogRecordEnum [] enums = values();
        for(LogRecordEnum e : enums) {
            if(e.getAttrName().equals(attrName)) {
                return e;
            }
        }
        return null;
    }

}
