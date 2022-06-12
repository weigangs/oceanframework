package com.lkyl.oceanframework.log.service;


public interface IFunctionService {

     String apply(String functionName, String value);

     boolean beforeFunction(String functionName);
}
