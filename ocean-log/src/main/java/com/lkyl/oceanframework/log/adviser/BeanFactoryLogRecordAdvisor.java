package com.lkyl.oceanframework.log.adviser;

import com.lkyl.oceanframework.log.parser.LogRecordOperationSource;
import com.lkyl.oceanframework.log.pointcut.LogRecordPointcut;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月28日 12:47
 */
public class BeanFactoryLogRecordAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private LogRecordOperationSource logRecordOperationSource;
    @Override
    public Pointcut getPointcut() {
        LogRecordPointcut logRecordPointcut = new LogRecordPointcut();
        logRecordPointcut.setLogRecordOperationSource(logRecordOperationSource);
        return logRecordPointcut;
    }

    public void setLogRecordOperationSource(LogRecordOperationSource logRecordOperationSource) {
        this.logRecordOperationSource = logRecordOperationSource;
    }
}
