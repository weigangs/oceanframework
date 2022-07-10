//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lkyl.oceanframework.log.spelExt;


import com.lkyl.oceanframework.log.spelExt.parser.LogRecordValueParser;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Map;

public abstract class LogRecordCachedExpressionEvaluator {
    private final LogRecordValueParser parser;
    private final ParameterNameDiscoverer parameterNameDiscoverer;

    protected LogRecordCachedExpressionEvaluator(LogRecordValueParser parser) {
        this.parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        Assert.notNull(parser, "SpelExpressionParser must not be null");
        this.parser = parser;
    }

    protected LogRecordCachedExpressionEvaluator() {
        this(new LogRecordValueParser(new SpelParserConfiguration()));
    }

    protected LogRecordValueParser getParser() {
        return this.parser;
    }

    protected ParameterNameDiscoverer getParameterNameDiscoverer() {
        return this.parameterNameDiscoverer;
    }

    protected Expression getExpression(Map<ExpressionKey, Expression> cache, AnnotatedElementKey elementKey, String expression) {
        LogRecordCachedExpressionEvaluator.ExpressionKey expressionKey = this.createKey(elementKey, expression);
        Expression expr = (Expression)cache.get(expressionKey);
        if (expr == null) {
            expr = this.parseExpression(expression);
            cache.put(expressionKey, expr);
        }

        return expr;
    }

    protected Expression parseExpression(String expression) {
        return this.getParser().parseExpression(expression);
    }

    private LogRecordCachedExpressionEvaluator.ExpressionKey createKey(AnnotatedElementKey elementKey, String expression) {
        return new LogRecordCachedExpressionEvaluator.ExpressionKey(elementKey, expression);
    }

    protected static class ExpressionKey implements Comparable<LogRecordCachedExpressionEvaluator.ExpressionKey> {
        private final AnnotatedElementKey element;
        private final String expression;

        protected ExpressionKey(AnnotatedElementKey element, String expression) {
            Assert.notNull(element, "AnnotatedElementKey must not be null");
            Assert.notNull(expression, "Expression must not be null");
            this.element = element;
            this.expression = expression;
        }

        @Override
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            } else if (!(other instanceof LogRecordCachedExpressionEvaluator.ExpressionKey)) {
                return false;
            } else {
                LogRecordCachedExpressionEvaluator.ExpressionKey otherKey = (LogRecordCachedExpressionEvaluator.ExpressionKey)other;
                return this.element.equals(otherKey.element) && ObjectUtils.nullSafeEquals(this.expression, otherKey.expression);
            }
        }

        @Override
        public int hashCode() {
            return this.element.hashCode() * 29 + this.expression.hashCode();
        }

        @Override
        public String toString() {
            return this.element + " with expression \"" + this.expression + "\"";
        }

        @Override
        public int compareTo(LogRecordCachedExpressionEvaluator.ExpressionKey other) {
            int result = this.element.toString().compareTo(other.element.toString());
            if (result == 0) {
                result = this.expression.compareTo(other.expression);
            }

            return result;
        }
    }
}
