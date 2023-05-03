package com.lkyl.oceanframework.log.operator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 22:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {
    private String operatorId;
    private String operatorName;
    private boolean isLogin;


}
