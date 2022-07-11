package com.lkyl.oceanframework.log.service;


/**
 * @author nicholas
 */
public interface IFunctionService {

     String apply(String functionName, Object value);

     boolean beforeFunction(String functionName);
}
