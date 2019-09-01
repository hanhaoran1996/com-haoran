package com.haoran.data.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author hanhaoran
 * @date 2019/9/1 13:48
 */

public final class Redis {
    private static final Jedis J = RedisClientLoader.load();
    private static final String OK = "OK";

    public boolean set(String key, String value) {
        return OK.equals(J.set(key, value));
    }

    public boolean set(String key, String value, long expire, TimeUnit unit) {
        return OK.equals(J.psetex(key, unit.toMillis(expire), value));
    }

    public String setGet(String key, String value) {
        return J.getSet(key, value);
    }

    public String get(String key) {
        return J.get(key);
    }

    public Long expire(String key, long expire, TimeUnit unit) {
        return J.pexpire(key, unit.toMillis(expire));
    }


}
