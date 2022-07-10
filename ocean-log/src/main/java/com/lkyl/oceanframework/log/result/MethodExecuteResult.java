package com.lkyl.oceanframework.log.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年06月01日 22:40
 */

@Data
@AllArgsConstructor
public class MethodExecuteResult {

    private boolean success;

    public Throwable throwable;

    private String errorMsg;


}
