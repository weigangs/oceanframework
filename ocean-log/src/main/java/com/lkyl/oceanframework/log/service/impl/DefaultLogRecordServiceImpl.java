package com.lkyl.oceanframework.log.service.impl;

import com.lkyl.oceanframework.log.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.logging.LogRecord;


public class DefaultLogRecordServiceImpl implements ILogRecordService {

    private final Logger businessLog = LoggerFactory.getLogger("businessLog");

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void record(LogRecord logRecord) {

        record(logRecord.getMessage());
    }

    @Override
    public void record(String msg) {
        if (businessLog.isInfoEnabled()) {
            businessLog.info("{}", msg);
        }
    }
}
