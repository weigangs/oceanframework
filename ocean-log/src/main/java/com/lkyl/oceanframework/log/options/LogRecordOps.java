package com.lkyl.oceanframework.log.options;

import com.lkyl.oceanframework.log.enums.LogRecordEnum;
import lombok.Data;
import org.springframework.expression.Expression;

import java.util.List;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年06月01日 22:55
 */
@Data
public class LogRecordOps {

    private LogRecordEnum logRecordEnum;

    private Expression expression;

    private String expressString;
}
