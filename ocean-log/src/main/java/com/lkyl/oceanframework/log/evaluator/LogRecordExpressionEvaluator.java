package com.lkyl.oceanframework.log.evaluator;

import com.lkyl.oceanframework.log.context.LogRecordEvaluationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author nicholas
 */
public class LogRecordExpressionEvaluator extends CachedExpressionEvaluator {

    private Map<ExpressionKey, Expression> expressionCache = new ConcurrentHashMap<>(64);

    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);

    public String parseExpression(String conditionExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext) {
        return getExpression(this.expressionCache, methodKey, conditionExpression).getValue(evalContext, String.class);
    }

    public EvaluationContext createEvaluationContext(Method method, Object[] args, Object targetClass, Object ret, String errorMsg, BeanFactory beanFactory) {
        return new LogRecordEvaluationContext(targetClass, method, args, new StandardReflectionParameterNameDiscoverer(), ret, errorMsg, beanFactory);
    }

}
