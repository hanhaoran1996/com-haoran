package com.haoran.common;

/**
 * @author hr.han
 * @date 2019/5/12 18:26
 */
public enum Os {
    /**
     * win
     */
    WINDOWS,

    /**
     * linux / mac os
     */
    UNIX;

    public static Os get() {
        if (System.getProperty(PROPERTY_NAME).toLowerCase().startsWith(WIN_PREFIX)) {
            return WINDOWS;
        }
        return UNIX;
    }

    private static final String PROPERTY_NAME = "os.name";
    private static final String WIN_PREFIX = "win";
}
