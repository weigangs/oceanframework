package com.lkyl.oceanframework.log.function;

import com.lkyl.oceanframework.log.enums.ParseFunctionEnum;

public interface IParseFunction<T>{

  default boolean executeBefore(){
    return false;
  }

  default String functionName(){
    return ParseFunctionEnum.DEFAULT_FUNCTION.getFunctionName();
  }

  String apply(T value);
}
