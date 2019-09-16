package com.haoran.common;

import com.google.common.base.Preconditions;
import com.haoran.common.u.U4File;
import com.haoran.common.u.U4Object;
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
            properties = U4File.read2Properties(stream, false);
        } catch (IOException e) {
            logger.error("load app properties failed", e);
        }

        if (U4Object.isNullOrEmpty(properties)) {
            properties = new Properties();
        }

        PROPERTIES = properties;
        String appId = properties.getProperty(APP_ID_KEY);
        APP_ID = U4Object.isNullOrEmpty(appId) ? APP_ID_DEF : appId;
        String jdkVersion = properties.getProperty(JDK_VERSION_KEY, JDK_VERSION_DEF);
        JDK_VERSION = U4Object.isNullOrEmpty(jdkVersion) ? JDK_VERSION_DEF : jdkVersion;
        String configPath = properties.getProperty(CONFIG_PATH_KEY);
        CONFIG_PATH = U4Object.isNullOrEmpty(configPath)
                ? ((Os.WINDOWS == Os.get()) ? CONFIG_PATH_WIN_DEF : CONFIG_PATH_UNIX_DEF)
                : configPath;

        logger.info("********************************************************************");
        logger.info("****** APP CONFIGS *************************************************");
        logger.info("****************** app id: " + APP_ID);
        logger.info("****************** jdk version: " + JDK_VERSION);
        logger.info("****************** config path: " + CONFIG_PATH);
        logger.info("********************************************************************");
    }

    public static String getProperty(String key) {
        return getProperty(key, Constants.EMPTY);
    }

    public static String getProperty(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }

    public static Properties get(String filename) {
        Preconditions.checkNotNull(filename);

        if (APP_PROPERTIES.equals(filename)) {
            return PROPERTIES;
        }

        Properties properties;

        File file = new File(CONFIG_PATH + Constants.SEPARATOR_SLASH + filename);
        if (file.exists()) {
            properties = U4File.read2Properties(file);
            return properties;
        }

        file = new File(CONFIG_PATH + Constants.SEPARATOR_SLASH + APP_ID + Constants.SEPARATOR_SLASH + filename);
        if (file.exists()) {
            properties = U4File.read2Properties(file);
            return properties;
        }

        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            properties = U4File.read2Properties(stream, false);
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
