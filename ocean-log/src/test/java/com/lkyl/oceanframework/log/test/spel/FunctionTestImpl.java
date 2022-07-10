package com.lkyl.oceanframework.log.test.spel;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年06月22日 22:37
 */
public class FunctionTestImpl implements FunctionTest {
    @Override
    public String functionName() {
        return "functionTest";
    }

    @Override
    public String apply(String value) {
        return value.toUpperCase();
    }
}
