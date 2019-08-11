package com.haoran.common;

import com.haoran.common.utils.Files;
import com.haoran.common.utils.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author hr.han
 * @date 2019/5/12 20:17
 */

public final class NtConfig {
    private NtConfig() {}

    private static final Logger logger = LoggerFactory.getLogger(NtConfig.class);

    public static final String APP_PROPERTIES        = "META-INF/app.properties";
    private static final Properties PROPERTIES;

    private static final String APP_ID_KEY           = "app.id";
    private static final String JDK_VERSION_KEY      = "jdk.version";
    private static final String CONFIG_PATH_KEY      = "config.path";

    private static final String APP_ID;
    private static final String JDK_VERSION;
    private static final String CONFIG_PATH;

    private static final String APP_ID_DEF           = "00000";
    private static final String JDK_VERSION_DEF      = "1.8";
    private static final String CONFIG_PATH_WIN_DEF  = "C:/opt/settings";
    private static final String CONFIG_PATH_UNIX_DEF = "/opt/settings";

    static {
        Properties properties = null;
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(APP_PROPERTIES)) {
            properties = Files.read2Properties(stream, false);
        } catch (IOException e) {
            logger.error("load app properties failed", e);
        }

        if (Objects.isNullOrEmpty(properties)) {
            properties = new Properties();
        }

        PROPERTIES = properties;
        APP_ID = properties.getProperty(APP_ID_KEY, APP_ID_DEF);
        JDK_VERSION = properties.getProperty(JDK_VERSION_KEY, JDK_VERSION_DEF);
        CONFIG_PATH = properties.getProperty(
                CONFIG_PATH_KEY,
                (Os.WINDOWS == Os.get()) ? CONFIG_PATH_WIN_DEF : CONFIG_PATH_UNIX_DEF
        );
    }

    public static String getProperty(String key) {
        return getProperty(key, Const.EMPTY);
    }

    public static String getProperty(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }

    public static Properties get(String filename) {
        if (Objects.isNullOrEmpty(filename)) {
            return new Properties();
        }

        if (APP_PROPERTIES.equals(filename)) {
            return PROPERTIES;
        }

        Properties properties;

        File file = new File(CONFIG_PATH + Const.SLASH + filename);
        if (file.exists()) {
            properties = Files.read2Properties(file);
            return properties;
        }

        file = new File(CONFIG_PATH + Const.SLASH + APP_ID + Const.SLASH + filename);
        if (file.exists()) {
            properties = Files.read2Properties(file);
            return properties;
        }

        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            properties = Files.read2Properties(stream, false);
        } catch (IOException e) {
            throw new IllegalStateException("file [" + filename + "] doesn't exist, please recheck");
        }
        return properties;
    }

    public static String getAppId() {
        return APP_ID;
    }

    public static String getJdkVersion() {
        return JDK_VERSION;
    }

    public static String getConfigPath() {
        return CONFIG_PATH;
    }
}
