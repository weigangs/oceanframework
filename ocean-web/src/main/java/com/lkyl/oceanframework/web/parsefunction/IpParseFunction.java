package com.lkyl.oceanframework.web.parsefunction;

import com.lkyl.oceanframework.common.utils.utils.IpAddressUtil;
import com.lkyl.oceanframework.log.function.IParseFunction;
import com.lkyl.oceanframework.web.enums.WebParseFunEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月10日 22:11
 */
public class IpParseFunction implements IParseFunction<HttpServletRequest> {
    @Override
    public String functionName() {
        return WebParseFunEnum.IP_FUNCTION.getFunctionName();
    }

    @Override
    public String apply(HttpServletRequest request) {
        return IpAddressUtil.getIpAddress(request);
    }
}
