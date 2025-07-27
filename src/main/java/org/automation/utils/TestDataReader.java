package org.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataReader {

    private static final Logger logger = LogManager.getLogger(TestDataReader.class);
    private static final String CONFIG_PATH = "src/test/java/automation/resources/config/config.properties";
    private static final Properties properties = new Properties();

    static {
        try(InputStream input = new FileInputStream(CONFIG_PATH)) {
            properties.load(input);
            logger.info("Config properties loaded successfully from " + CONFIG_PATH);
        }  catch (IOException e) {
            throw new RuntimeException("Failed to load config properties");
        }


    }

    public static String getBaseUrl(){

        return properties.getProperty("app.base.url");
    }

    public static Properties getProperties(){

        return properties;
    }
}
