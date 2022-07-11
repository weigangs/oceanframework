package com.lkyl.oceanframework.log.service.impl;

import com.lkyl.oceanframework.log.factory.ParseFunctionFactory;
import com.lkyl.oceanframework.log.function.IParseFunction;
import com.lkyl.oceanframework.log.service.IFunctionService;

/**
 * @author nicholas
 */
public class DefaultFunctionServiceImpl implements IFunctionService {

  private final ParseFunctionFactory parseFunctionFactory;

  public DefaultFunctionServiceImpl(ParseFunctionFactory parseFunctionFactory) {
    this.parseFunctionFactory = parseFunctionFactory;
  }

  @Override
  public String apply(String functionName, Object value) {
    IParseFunction function = parseFunctionFactory.getFunction(functionName);
    if (function == null) {
      return value.toString();
    }
    return function.apply(value);
  }

  @Override
  public boolean beforeFunction(String functionName) {
    return parseFunctionFactory.isBeforeFunction(functionName);
  }
}
