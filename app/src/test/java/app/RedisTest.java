package app;

import com.haoran.data.redis.Redis;
import org.junit.Test;

/**
 * @author hanhaoran
 * @date 2019/9/17 20:16
 */

public class RedisTest {
    @Test
    public void test() {
        System.out.println(Redis.getSet("key", "value1"));
    }
}
