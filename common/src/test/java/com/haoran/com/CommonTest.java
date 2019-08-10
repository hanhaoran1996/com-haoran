package com.haoran.com;

import com.haoran.common.NtConfig;
import org.junit.Test;

/**
 * @author hr.han
 * @date 2019/8/10 20:07
 */

public class CommonTest {

    @Test
    public void test() {
        System.out.println(NtConfig.getAppId());
        System.out.println(NtConfig.getConfigPath());
        System.out.println(NtConfig.getJdkVersion());
        System.out.println(NtConfig.getProperties("test.properties"));
    }
}
