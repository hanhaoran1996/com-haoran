package com.haoran.com;

import com.haoran.common.NtConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author hr.han
 * @date 2019/8/10 20:07
 */

public class CommonTest {

    @Test
    public void test() {
        Assert.assertEquals("00000", NtConfig.getAppId());
        Assert.assertEquals("1.8", NtConfig.getJdkVersion());
    }
}
