package com.haoran.data.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

import static com.haoran.common.Constants.OK;

/**
 * @author hanhaoran
 * @date 2019/9/1 13:48
 */

public final class Redis {
    private static final Logger logger = LoggerFactory.getLogger(Redis.class);

    private static final Jedis J = RedisClientLoader.load();

    public static boolean set(String key, String value) {
        return OK.equals(J.set(key, value));
    }

    public static boolean set(String key, String value, long expire, TimeUnit unit) {
        return OK.equals(J.psetex(key, unit.toMillis(expire), value));
    }

    public static String setGet(String key, String value) {
        return J.getSet(key, value);
    }

    public static String get(String key) {
        return J.get(key);
    }

    public static Long expire(String key, long expire, TimeUnit unit) {
        return J.pexpire(key, unit.toMillis(expire));
    }
}
