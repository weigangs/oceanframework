package com.lkyl.oceanframework.common.utils.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author nicholas
 * @date 2023/11/05 20:09
 */
public class DateUtil {

    public static final DateTimeFormatter FILENAME_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static boolean isTimeInRange(LocalTime timeToCheck, LocalTime startTime, LocalTime endTime) {
        if (startTime.isBefore(endTime)) {
            return !timeToCheck.isBefore(startTime) && !timeToCheck.isAfter(endTime);
        } else { // 如果开始时间晚于结束时间，处理跨越午夜的情况
            return !timeToCheck.isBefore(startTime) || !timeToCheck.isAfter(endTime);
        }
    }
}
