package com.haoran.data.redis;

import com.google.common.base.Stopwatch;
import com.haoran.common.Constants;
import com.haoran.common.NtConfig;
import com.haoran.common.Parser;
import com.haoran.common.u.U4Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hanhaoran
 * @date 2019/9/1 13:24
 */
class RedisClientLoader {
    private RedisClientLoader() {}

    static Jedis load() {
        return Handler.INSTANCE;
    }

    private static final Logger logger = LoggerFactory.getLogger(RedisClientLoader.class);
    private static final String KEY_REDIS_PREFIX = "redis.server.";
    private static final String KEY_ADDRESS = "address";
    private static final String REDIS_PROPERTIES = "redis.properties";

    private static final class Handler {
        static final Jedis INSTANCE;
        static {
            Stopwatch stopwatch = Stopwatch.createStarted();
            Jedis j;
            try {
                String redisServerAddress = NtConfig.getProperty(KEY_REDIS_PREFIX + KEY_ADDRESS);
                if (U4Object.isNullOrEmpty(redisServerAddress)) {
                    redisServerAddress = NtConfig.get(REDIS_PROPERTIES).getProperty(KEY_ADDRESS);
                }
                String host;
                Integer port;

                String[] parts = redisServerAddress.split(":");
                if (parts.length != Constants.TWO) {
                    throw new IllegalArgumentException();
                }

                host = parts[0];
                port = Parser.parse2Integer(parts[1], 6379);

                j = new Jedis(host, port);
            } catch (Exception e) {
                j = null;
                logger.error("unknown host", e);
                throw new IllegalStateException("redis client init failed...");
            }
            INSTANCE = j;
            Runtime.getRuntime().addShutdownHook(Executors.defaultThreadFactory().newThread(INSTANCE::close));
            logger.info("redis client init success with {} ms...", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }
}
