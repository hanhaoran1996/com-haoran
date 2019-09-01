package com.haoran.common;

import com.google.common.base.Preconditions;
import com.haoran.common.u.U4Object;

import java.util.function.Function;

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

    public static <S, D> D parse(S src, Function<? super S, ? extends D> action) {
        return parse(src, null, action);
    }

    public static <S, D> D parse(S src, D defaultValue, Function<? super S, ? extends D> action) {
        if (U4Object.isNull(src)) {
            return defaultValue;
        }

        Preconditions.checkNotNull(action);
        D dest = action.apply(src);
        return U4Object.nonNull(dest) ? dest : defaultValue;
    }

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
        return parse(obj, defaultValue, o -> Boolean.parseBoolean(String.valueOf(o)));
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
        return parse(obj, defaultValue, o -> Byte.parseByte(String.valueOf(o), radix));
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
        return parse(obj, defaultValue, o -> Short.parseShort(String.valueOf(o), radix));
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
        return parse(obj, defaultValue, o -> Integer.parseInt(String.valueOf(o), radix));
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
        return parse(obj, defaultValue, o -> Long.parseLong(String.valueOf(o), radix));
    }

    public static Float parse2Float(Object obj) {
        return parse2Float(obj, FLOAT);
    }

    public static Float parse2Float(Object obj, Float defaultValue) {
        return parse(obj, defaultValue, o -> Float.parseFloat(String.valueOf(o)));
    }

    public static Double parse2Double(Object obj) {
        return parse2Double(obj, DOUBLE);
    }

    public static Double parse2Double(Object obj, Double defaultValue) {
        return parse(obj, defaultValue, o -> Double.parseDouble(String.valueOf(o)));
    }

    public static String parse2String(Object obj) {
        return parse2String(obj, Constants.EMPTY);
    }

    public static String parse2String(Object obj, String defaultValue) {
        return parse(obj, defaultValue, String::valueOf);
    }

}
