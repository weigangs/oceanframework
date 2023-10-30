package com.lkyl.oceanframework.common.utils.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nicholas
 * @date 2023/07/28 22:20
 */
public class IpUtil {

    public static String parseRequestIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if (StringUtils.isNotEmpty(ipAddress) && ipAddress.length() > 15) {
            String[] ipArray = ipAddress.split(",");
            ipAddress = ipArray[0];
        }
        return ipAddress;
    }

}
