package com.lkyl.oceanframework.log.parser;

import com.lkyl.oceanframework.common.utils.utils.ObjectUtils;
import com.lkyl.oceanframework.log.annotation.LogRecord;
import com.lkyl.oceanframework.log.options.LogRecordOps;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 22:52
 */
public class LogRecordOperationSource {

    public Collection<LogRecordOps> computeLogRecordOperations(Method method, Class<?> targetClass) {
        LogRecord annotation = method.getAnnotation(LogRecord.class);
        if(ObjectUtils.isNotEmpty(annotation)) {

        }
    }
}
