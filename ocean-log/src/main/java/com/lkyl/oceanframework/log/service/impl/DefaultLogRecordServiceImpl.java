package com.lkyl.oceanframework.log.service.impl;

import com.lkyl.oceanframework.log.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.LogRecord;

@Slf4j
public class DefaultLogRecordServiceImpl implements ILogRecordService {

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void record(LogRecord logRecord) {

        log.info("【logRecord】log={}", logRecord);
    }
}
