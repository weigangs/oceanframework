package com.lkyl.oceanframework.log.test.spel;

public interface FunctionTest {
    default boolean executeBefore(){
        return false;
    }

    String functionName();

    String apply(String value);
}
