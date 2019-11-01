package com.haoran.common.u;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author hr.han
 * @date 2019/5/13 15:58
 */
public final class U4Time {
    private U4Time() {}

    public static final String DTF_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DTF_PURE = "yyyyMMddHHmmssSSS";
    public static final String DTF_ACCURATE = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DTF_SIMPLE = "yy/MM/dd HH:mm:ss";

    public static final String DF_DEFAULT = "yyyy-MM-dd";
    public static final String DF_SLASH = "yyyy/MM/dd";
    public static final String DF_SIMPLE = "yy/MM/dd";

    public static final String TF_DEFAULT = "HH:mm:ss";
    public static final String TF_ACCURATE = "HH:mm:ss.SSS";
    public static final String TF_SIMPLE = "HH:mm";

    // -----------------------------------------------------------------------------------------------------------------
    // Get
    // -----------------------------------------------------------------------------------------------------------------

    public static String getDateTimeOfNow() {
        return getDateTimeOfNow(DTF_DEFAULT);
    }

    public static String getDateTimeOfNow(String pattern) {
        return getDateTime(LocalDateTime.now(), pattern);
    }

    public static String getDateTime(LocalDateTime localDateTime) {
        return getDateTime(localDateTime, DTF_DEFAULT);
    }

    public static String getDateTime(LocalDateTime localDateTime, String pattern) {
        return get(localDateTime, pattern);
    }

    public static String getTimeOfNow() {
        return getTimeOfNow(TF_DEFAULT);
    }

    public static String getTimeOfNow(String pattern) {
        return getTime(LocalTime.now(), pattern);
    }

    public static String getTime(LocalTime localTime) {
        return getTime(localTime, TF_DEFAULT);
    }

    public static String getTime(LocalTime localTime, String pattern) {
        return get(localTime, pattern);
    }

    public static String getDateOfNow() {
        return getDateOfNow(DF_DEFAULT);
    }

    public static String getDateOfNow(String pattern) {
        return getDate(LocalDate.now(), pattern);
    }

    public static String getDate(LocalDate localDate) {
        return getDate(localDate, DF_DEFAULT);
    }

    public static String getDate(LocalDate localDate, String pattern) {
        return get(localDate, pattern);
    }

    private static String get(TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Parse
    // -----------------------------------------------------------------------------------------------------------------

    public static LocalDateTime parse2DateTime(String dateTimeStr) {
        return parse2DateTime(dateTimeStr, DTF_DEFAULT);
    }

    public static LocalDateTime parse2DateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime parse2Time(String timeStr) {
        return parse2Time(timeStr, TF_DEFAULT);
    }

    public static LocalTime parse2Time(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parse2Date(String dateStr) {
        return parse2Date(dateStr, DF_DEFAULT);
    }

    public static LocalDate parse2Date(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Transfer
    // -----------------------------------------------------------------------------------------------------------------

    public static Date trans2Date(Calendar calendar) {
        return Date.from(calendar.toInstant());
    }

    public static Date trans2Date(Timestamp timestamp) {
        return Date.from(timestamp.toInstant());
    }

    public static Date trans2Date(Instant instant) {
        return Date.from(instant);
    }

    public static Date trans2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date trans2Date(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public static Calendar trans2Calendar(Date date) {
        return GregorianCalendar.from(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    public static Calendar trans2Calendar(Timestamp timestamp) {
        return GregorianCalendar.from(ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault()));
    }

    public static Calendar trans2Calendar(Instant instant) {
        return GregorianCalendar.from(ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()));
    }

    public static Calendar trans2Calendar(LocalDateTime localDateTime) {
        return GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()));
    }

    public static Calendar trans2Calendar(ZonedDateTime zonedDateTime) {
        return GregorianCalendar.from(zonedDateTime);
    }

    public static Timestamp trans2Timestamp(Date date) {
        return Timestamp.from(date.toInstant());
    }

    public static Timestamp trans2Timestamp(Calendar calendar) {
        return Timestamp.from(calendar.toInstant());
    }

    public static Timestamp trans2Timestamp(Instant instant) {
        return Timestamp.from(instant);
    }

    public static Timestamp trans2Timestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public static Timestamp trans2Timestamp(ZonedDateTime zonedDateTime) {
        return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
    }

    public static Instant trans2Instant(Date date) {
        return date.toInstant();
    }

    public static Instant trans2Instant(Calendar calendar) {
        return calendar.toInstant();
    }

    public static Instant trans2Instant(Timestamp timestamp) {
        return timestamp.toInstant();
    }

    public static Instant trans2Instant(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of(ZoneId.systemDefault().getId()));
    }

    public static Instant trans2Instant(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant();
    }

    public static LocalDateTime trans2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime trans2LocalDateTime(Calendar calendar) {
        return calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime trans2LocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public static LocalDateTime trans2LocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime trans2LocalDateTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalDateTime();
    }

    public static LocalDate trans2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate trans2LocalDate(Calendar calendar) {
        return calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate trans2LocalDate(Timestamp timestamp) {
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate trans2LocalDate(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate trans2LocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    public static LocalDate trans2LocalDate(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalDate();
    }

    public static LocalTime trans2LocalTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalTime trans2LocalTime(Calendar calendar) {
        return calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalTime trans2LocalTime(Timestamp timestamp) {
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalTime trans2LocalTime(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalTime trans2LocalTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }

    public static LocalTime trans2LocalTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalTime();
    }

}
