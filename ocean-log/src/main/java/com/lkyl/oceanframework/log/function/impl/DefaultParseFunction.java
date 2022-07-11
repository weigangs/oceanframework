package com.lkyl.oceanframework.log.function.impl;

import com.lkyl.oceanframework.log.enums.ParseFunctionEnum;
import com.lkyl.oceanframework.log.function.IParseFunction;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 23:05
 */
public class DefaultParseFunction implements IParseFunction<String> {
    @Override
    public String functionName() {
        return ParseFunctionEnum.DEFAULT_FUNCTION.getFunctionName();
    }

    @Override
    public String apply(String value) {

        throw new UnsupportedOperationException();
    }
}
