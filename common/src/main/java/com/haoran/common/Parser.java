package com.haoran.common;

import com.haoran.common.u.U4Object;

/**
 * @author hr.han
 * @date 2019/5/12 20:25
 */

public final class Parser {
    private Parser() {
    }

    private static final Character CHARACTER = ' ';
    private static final Boolean BOOLEAN = Boolean.FALSE;
    private static final Byte BYTE = (byte) 0;
    private static final Short SHORT = (short) 0;
    private static final Integer INTEGER = 0;
    private static final Long LONG = 0L;
    private static final Float FLOAT = 0F;
    private static final Double DOUBLE = 0D;

    public static Character parse2Character(Object obj) {
        return parse2Character(obj, CHARACTER);
    }

    public static Character parse2Character(Object obj, Character defaultValue) {
        String tmp = parse2String(obj);
        if (U4Object.isNullOrEmpty(tmp)) {
            return defaultValue;
        }
        return tmp.charAt(0);
    }

    public static Boolean parse2Boolean(Object obj) {
        return parse2Boolean(obj, BOOLEAN);
    }

    public static Boolean parse2Boolean(Object obj, Boolean defaultValue) {
        if (U4Object.isNull(obj)) {
            return defaultValue;
        }

        return Boolean.parseBoolean(parse2String(obj));
    }

    public static Byte parse2Byte(Object obj) {
        return parse2Byte(obj, 10, BYTE);
    }

    public static Byte parse2Byte(Object obj, Byte defaultValue) {
        return parse2Byte(obj, 10, defaultValue);
    }

    public static Byte parse2Byte(int radix, Object obj) {
        return parse2Byte(obj, radix, BYTE);
    }

    public static Byte parse2Byte(Object obj, int radix, Byte defaultValue) {
        if (U4Object.isNull(obj)) {
            return defaultValue;
        }

        return Byte.parseByte(parse2String(obj), radix);
    }

    public static Short parse2Short(Object obj) {
        return parse2Short(obj, 10, SHORT);
    }

    public static Short parse2Short(Object obj, Short defaultValue) {
        return parse2Short(obj, 10, defaultValue);
    }

    public static Short parse2Short(int radix, Object obj) {
        return parse2Short(obj, radix, SHORT);
    }

    public static Short parse2Short(Object obj, int radix, Short defaultValue) {
        if (U4Object.isNull(obj)) {
            return defaultValue;
        }

        return Short.parseShort(parse2String(obj), radix);
    }

    public static Integer parse2Integer(Object obj) {
        return parse2Integer(obj, 10, INTEGER);
    }

    public static Integer parse2Integer(Object obj, Integer defaultValue) {
        return parse2Integer(obj, 10, defaultValue);
    }

    public static Integer parse2Integer(int radix, Object obj) {
        return parse2Integer(obj, radix, INTEGER);
    }

    public static Integer parse2Integer(Object obj, int radix, Integer defaultValue) {
        if (U4Object.isNull(obj)) {
            return defaultValue;
        }

        return Integer.parseInt(parse2String(obj), radix);
    }

    public static Long parse2Long(Object obj) {
        return parse2Long(obj, 10, LONG);
    }

    public static Long parse2Long(Object obj, Long defaultValue) {
        return parse2Long(obj, 10, defaultValue);
    }

    public static Long parse2Long(int radix, Object obj) {
        return parse2Long(obj, radix, LONG);
    }

    public static Long parse2Long(Object obj, int radix, Long defaultValue) {
        if (U4Object.isNull(obj)) {
            return defaultValue;
        }

        return Long.parseLong(parse2String(obj), radix);
    }

    public static Float parse2Float(Object obj) {
        return parse2Float(obj, FLOAT);
    }

    public static Float parse2Float(Object obj, Float defaultValue) {
        if (U4Object.isNull(obj)) {
            return defaultValue;
        }

        return Float.parseFloat(parse2String(obj));
    }

    public static Double parse2Double(Object obj) {
        return parse2Double(obj, DOUBLE);
    }

    public static Double parse2Double(Object obj, Double defaultValue) {
        if (U4Object.isNull(obj)) {
            return defaultValue;
        }

        return Double.parseDouble(parse2String(obj));
    }

    public static String parse2String(Object obj) {
        return parse2String(obj, Constants.EMPTY);
    }

    public static String parse2String(Object obj, String defaultValue) {
        if (U4Object.isNull(obj)) {
            return defaultValue;
        }

        return String.valueOf(obj);
    }

}
