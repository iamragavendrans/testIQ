package com.orangehrm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();
    private static final String CONFIG_PATH = "config/config.properties";

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_PATH)) {
            if (is == null) {
                throw new IllegalStateException("Could not find " + CONFIG_PATH + " on classpath");
            }
            PROPERTIES.load(is);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {}

    public static String get(String key) {
        String sysValue = System.getProperty(key);
        if (sysValue != null && !sysValue.isBlank() && !"null".equalsIgnoreCase(sysValue)) {
            return sysValue;
        }
        return PROPERTIES.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Integer.parseInt(value.trim());
    }

    public static boolean getBool(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value.trim());
    }
}
