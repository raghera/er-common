package com.vodafone.global.er.properties;

import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Ravi Aghera
 *
 * Loads all system properties and returns them upon request
 *
 */
public class CommonPropertyService {

    private static final Logger log = LoggerFactory.getLogger(CommonPropertyService.class);

    private static Properties properties = new Properties();
    private static String propertyFileName = "env.properties";

    static {
        try {
            final InputStream in = CommonPropertyService.class.getClassLoader().getResourceAsStream("env.properties");
            properties.load(in);
        } catch (IOException ioEx) {
            log.warn("Unable to load properties from file system - could not find propertyFileName: " + propertyFileName
                    + " Will use system defaults."
            );
        }
    }

    public static Optional<String> getProperty(String key, String defaultValue) {
        final String property = properties.getProperty(key, defaultValue);
        if(property != null) {
            return Optional.of(property);
        }
        log.info("Retrieved property using key={} value={}", key, property );
        return Optional.absent();
    }

    public static Optional<Boolean> getPropertyAsBoolean(String key, boolean defaultValue) {
        final String value = properties.getProperty(key, String.valueOf(defaultValue));

        if(value == null) {
            return Optional.absent();
        }
        final Boolean boolValue = Boolean.parseBoolean(value);

        log.info("Retrieved property using key={} value={}", key, boolValue);
        return Optional.of(boolValue);
    }

    public static String getPropertyFileName() {
        return propertyFileName;
    }

}
