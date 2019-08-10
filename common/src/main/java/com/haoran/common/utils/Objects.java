package com.haoran.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author hr.han
 * @date 2019/5/12 18:50
 */

public final class Objects {
    private Objects() {}

    public static <T> T requireNotNull(T obj) {
        if (isNull(obj)) {
            throw new NullPointerException();
        }

        return obj;
    }

    public static <T> T requireNotNull(T obj, String msg) {
        if (isNull(obj)) {
            throw new NullPointerException(msg);
        }

        return obj;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean areNulls(Object ... objs) {
        if (isNull(objs)) {
            return true;
        }

        for (Object obj : objs) {
            if (nonNull(obj)) {
                return false;
            }
        }
        return true;
    }

    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    public static boolean nonNulls(Object ... objs) {
        if (isNull(objs)) {
            return false;
        }

        for (Object obj : objs) {
            if (isNull(obj)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(String str) {
        return isNull(str) || str.isEmpty();
    }

    public static boolean nonNullOrEmpty(String str) {
        return nonNull(str) && !str.isEmpty();
    }

    public static boolean isNullOrEmpty(Collection<?> col) {
        return isNull(col) || col.isEmpty();
    }

    public static boolean nonNullOrEmpty(Collection<?> col) {
        return nonNull(col) && !col.isEmpty();
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean nonNullOrEmpty(Map<?, ?> map) {
        return nonNull(map) && !map.isEmpty();
    }

}
