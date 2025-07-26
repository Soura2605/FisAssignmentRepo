package org.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestDataReader {

    private static final String CONFIG_PATH = "src/test/java/test/automation/resources/config/config.properties";
    private static final Properties properties = new Properties();

    static {
        try(InputStream input = new FileInputStream(CONFIG_PATH)) {
        }  catch (IOException e) {
            throw new RuntimeException("Failed to load config properties");
        }


    }

    public static String getBaseUrl(){

        return getBaseUrl() + properties.getProperty("app.base.url");
    }

    public static Properties getProperties(){

        return properties;
    }
}
