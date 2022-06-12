package com.lkyl.oceanframework.web.service.impl;

import com.lkyl.oceanframework.web.util.BusinessContextUtil;
import org.springframework.expression.spel.ast.Operator;

import java.util.Optional;

public class DefaultOperatorGetServiceImpl implements IOperatorGetService {

    @Override
    public Operator getUser() {
    //UserUtils 是获取用户上下文的方法
         return Optional.ofNullable(BusinessContextUtil.getBusinessContext().getUser())
                        .map(principal -> new Operator(principal.getName(), true))
                        .orElseThrow(()->new IllegalArgumentException("user is null"));

    }
}
