package com.lkyl.oceanframework.web.service.impl;

import com.lkyl.oceanframework.log.annotation.LogRecord;
import com.lkyl.oceanframework.web.service.IRecordIpService;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月11日 0:16
 */
public class RecordIpServiceImpl implements IRecordIpService {
    @Override
    @LogRecord(content = "开始访问系统，IP为【#{@{ipFunction(#request)}}】，访问路径为【#{#request.getRequestURI()}】", bizNo = "0")
    public void recordIp(HttpServletRequest request) {

    }
}
