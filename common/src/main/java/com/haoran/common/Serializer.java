package com.haoran.common;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @author hanhaoran
 * @date 2019/8/24 11:55
 */

public final class Serializer {
    private Serializer() {}

    private static final Gson GSON = new Gson();

    public static <T> String toJson(T t) {
        return GSON.toJson(t);
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }
}
