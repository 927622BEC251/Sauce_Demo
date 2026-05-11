package com.shopbot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties was not found in test resources");
            }
            PROPERTIES.load(inputStream);
        } catch (IOException exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing config value for key: " + key);
        }
        return value.trim();
    }

    public static int getTimeout() {
        return Integer.parseInt(get("timeout"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }
}
