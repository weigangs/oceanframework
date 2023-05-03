package com.lkyl.oceanframework.log.parser;

import com.lkyl.oceanframework.log.annotation.LogRecord;
import com.lkyl.oceanframework.log.enums.LogRecordEnum;
import com.lkyl.oceanframework.log.options.LogRecordOps;
import com.lkyl.oceanframework.log.spelExt.parser.LogRecordValueParser;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 22:52
 */
public class LogRecordOperationSource {
    private static final LogRecordValueParser expressParser = new LogRecordValueParser(new SpelParserConfiguration());
    private static final TemplateParserContext context = new TemplateParserContext();

    public Collection<LogRecordOps> computeLogRecordOperations(Method method, Class<?> targetClass) {
        List<LogRecordOps> ops = new ArrayList<>();
        LogRecord annotation = method.getAnnotation(LogRecord.class);
        if(ObjectUtils.isNotEmpty(annotation)) {
            //content
            if (StringUtils.isNotBlank(annotation.content())) {
                LogRecordOps item = new LogRecordOps();
                item.setExpressString(annotation.content());
                item.setLogRecordEnum(LogRecordEnum.CONTENT);
                item.setExpression(expressParser.parseExpression(annotation.content(), context));
                ops.add(item);
            }

            // operator
            if (StringUtils.isNotBlank(annotation.operator())) {
                LogRecordOps item = new LogRecordOps();
                item.setExpressString(annotation.operator());
                item.setLogRecordEnum(LogRecordEnum.OPERATOR);
                item.setExpression(expressParser.parseExpression(annotation.operator(), context));
                ops.add(item);
            }
            // bizNo
            if (StringUtils.isNotBlank(annotation.bizNo())) {
                LogRecordOps item = new LogRecordOps();
                item.setExpressString(annotation.bizNo());
                item.setLogRecordEnum(LogRecordEnum.BIZ_NO);
                item.setExpression(expressParser.parseExpression(annotation.bizNo(), context));
                ops.add(item);
            }
            //condition
            if (StringUtils.isNotBlank(annotation.condition())) {
                LogRecordOps item = new LogRecordOps();
                item.setExpressString(annotation.condition());
                item.setLogRecordEnum(LogRecordEnum.CONDITION);
                item.setExpression(expressParser.parseExpression(annotation.condition(), context));
                ops.add(item);
            }

        }
        return ops;
    }
}
