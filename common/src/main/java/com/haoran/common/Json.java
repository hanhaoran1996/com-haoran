package com.haoran.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

/**
 * @author hanhaoran
 * @date 2019/8/24 11:55
 */

public final class Json {
    private Json() {}

    private static final Gson GSON = new Gson();

    public static <T> String toJson(T t) {
        return GSON.toJson(t);
    }

    public static JsonElement toJsonTree(Object o) {
        return GSON.toJsonTree(o);
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }
}
