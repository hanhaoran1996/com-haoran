package com.haoran.data.redis;

import com.haoran.common.Constants;
import com.haoran.common.NtConfig;
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
        return OK.equals(J.set(resetKey(key), value));
    }

    public static boolean set(String key, String value, long expire, TimeUnit unit) {
        return OK.equals(J.psetex(resetKey(key), unit.toMillis(expire), value));
    }

    public static String getSet(String key, String value) {
        return J.getSet(resetKey(key), value);
    }

    public static String get(String key) {
        return J.get(resetKey(key));
    }

    public static Long expire(String key, long expire, TimeUnit unit) {
        return J.pexpire(resetKey(key), unit.toMillis(expire));
    }

    private static String resetKey(String key) {
        return NtConfig.getAppId() + Constants.SEPARATOR_HYPHEN + key;
    }
}
