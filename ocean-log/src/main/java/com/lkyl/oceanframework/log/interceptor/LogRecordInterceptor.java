package com.lkyl.oceanframework.log.interceptor;

import com.lkyl.oceanframework.log.context.LogRecordContext;
import com.lkyl.oceanframework.log.options.LogRecordOps;
import com.lkyl.oceanframework.log.parser.LogRecordOperationSource;
import com.lkyl.oceanframework.log.result.MethodExecuteResult;
import com.lkyl.oceanframework.log.service.IFunctionService;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.*;

import static org.springframework.aop.support.AopUtils.getTargetClass;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月28日 13:50
 */
@Slf4j
public class LogRecordInterceptor implements MethodInterceptor {

    private LogRecordOperationSource logRecordOperationSource;

    private String tenantId;

    private IFunctionService iFunctionService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        // 记录日志
        return execute(invocation, invocation.getThis(), method, invocation.getArguments());
    }

    private Object execute(MethodInvocation invoker, Object target, Method method, Object[] args) throws Throwable {
        Class<?> targetClass = getTargetClass(target);
        Object ret = null;
        MethodExecuteResult methodExecuteResult = new MethodExecuteResult(true, null, "");
        LogRecordContext.putEmptySpan();
        Collection<LogRecordOps> operations = new ArrayList<>();
        Map<String, String> functionNameAndReturnMap = new HashMap<>();
        try {
            operations = logRecordOperationSource.computeLogRecordOperations(method, targetClass);
            List<String> spElTemplates = getBeforeExecuteFunctionTemplate(operations);
            //业务逻辑执行前的自定义函数解析
            functionNameAndReturnMap = processBeforeExecuteFunctionTemplate(spElTemplates, targetClass, method, args);
        } catch (Exception e) {
            log.error("log record parse before function exception", e);
        }
        try {
            ret = invoker.proceed();
        } catch (Exception e) {
            methodExecuteResult = new MethodExecuteResult(false, e, e.getMessage());
        }
        try {
            if (!CollectionUtils.isEmpty(operations)) {
                recordExecute(ret, method, args, operations, targetClass,
                        methodExecuteResult.isSuccess(), methodExecuteResult.getErrorMsg(), functionNameAndReturnMap);
            }
        } catch (Exception t) {
            //记录日志错误不要影响业务
            log.error("log record parse exception", t);
        } finally {
            LogRecordContext.clear();
        }
        if (methodExecuteResult.throwable != null) {
            throw methodExecuteResult.throwable;
        }
        return ret;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void  setLogRecordOperationSource(LogRecordOperationSource logRecordOperationSource) {
        this.logRecordOperationSource = logRecordOperationSource;
    }

    public void setFunctionService(IFunctionService iFunctionService) {
        this.iFunctionService = iFunctionService;
    }

    //记录日志
    private void recordExecute(Object ret, Method method, Object[] args, Collection<LogRecordOps> operations, Class<?> targetClass, boolean isSuccess, String errorMsg, Map<String, String> functionNameAndReturnMap) {

    }

    private List<String> getBeforeExecuteFunctionTemplate(Collection<LogRecordOps> operations) {

    }

    private Map<String, String> processBeforeExecuteFunctionTemplate(List<String> spElTemplates, Class<?> targetClass, Method method, Object[] args) {

    }
}
