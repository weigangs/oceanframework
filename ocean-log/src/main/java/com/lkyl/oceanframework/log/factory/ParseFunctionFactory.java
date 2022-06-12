package com.lkyl.oceanframework.log.factory;

import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import com.lkyl.oceanframework.common.utils.utils.ObjectUtils;
import com.lkyl.oceanframework.log.function.IParseFunction;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nicholas
 */
public class ParseFunctionFactory {
  private Map<String, IParseFunction> allFunctionMap;

  public ParseFunctionFactory(List<IParseFunction> parseFunctions) {
    if (CollectionUtils.isEmpty(parseFunctions)) {
      return;
    }
    allFunctionMap = new HashMap<>();
    parseFunctions.stream().filter(e->StringUtils.isNotBlank(e.functionName())).forEach(iParseFunction -> {
      if(ObjectUtils.isNotEmpty(allFunctionMap.get(iParseFunction.functionName()))) {
        throw new RuntimeException("create parseFunctionFactory error, duplicate functionName found: " + iParseFunction.functionName() + ".");
      }
      allFunctionMap.put(iParseFunction.functionName(), iParseFunction);
    });
  }

  public IParseFunction getFunction(String functionName) {
    return allFunctionMap.get(functionName);
  }

  public boolean isBeforeFunction(String functionName) {
    return allFunctionMap.get(functionName) != null && allFunctionMap.get(functionName).executeBefore();
  }
}
