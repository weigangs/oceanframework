package com.lkyl.oceanframework.log.service;


import com.lkyl.oceanframework.log.operator.Operator;

/**
 * 如果@LogRecord
 */
public interface IOperatorGetService {

    /**
     * 可以在里面外部的获取当前登陆的用户，比如 UserContext.getCurrentUser()
     *
     * @return 转换成Operator返回
     */
    Operator getUser();
}
