package com.haoran.data.redis;

import com.haoran.common.Parser;
import com.haoran.common.u.U4Collection;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.haoran.common.u.U4Collection.*;

/**
 * @author hanhaoran
 * @date 2019/9/1 13:19
 */

public class RedisTest {
    @Test
    public void test() throws InterruptedException {
        Redis redis = new Redis();
        System.out.println(redis.get("A"));
        System.out.println(redis.set("A", "123"));
        System.out.println(redis.get("A"));
        System.out.println(redis.expire("A", 3, TimeUnit.SECONDS));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(redis.get("A"));
    }
}
