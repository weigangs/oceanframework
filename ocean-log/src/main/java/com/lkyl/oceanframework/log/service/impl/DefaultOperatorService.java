package com.lkyl.oceanframework.log.service.impl;

import com.lkyl.oceanframework.log.enums.SystemEnum;
import com.lkyl.oceanframework.log.operator.Operator;
import com.lkyl.oceanframework.log.service.IOperatorGetService;

/**
 *
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年12月26日 22:48
 */
public class DefaultOperatorService implements IOperatorGetService {
    @Override
    public Operator getUser() {
        Operator operator = new Operator();
        operator.setOperatorId(SystemEnum.USER_NAME.getCode());
        operator.setLogin(false);
        operator.setOperatorName(SystemEnum.USER_NAME.getMsg());
        return operator;
    }
}
