package com.lkyl.oceanframework.log.pointcut;

import com.lkyl.oceanframework.log.parser.LogRecordOperationSource;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author nicholas
 */
public class LogRecordPointcut extends StaticMethodMatcherPointcut implements Serializable {
    // LogRecord的解析类
    private LogRecordOperationSource logRecordOperationSource;

    @Override
    public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
          // 解析 这个 method 上有没有 @LogRecord 注解，有的话会解析出来注解上的各个参数
        return !CollectionUtils.isEmpty(logRecordOperationSource.computeLogRecordOperations(method, targetClass));
    }

    public void setLogRecordOperationSource(LogRecordOperationSource logRecordOperationSource) {
        this.logRecordOperationSource = logRecordOperationSource;
    }
}
