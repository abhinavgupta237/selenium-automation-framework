package com.qasmarts.framework.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration manager for loading properties
 * 
 * @author Abhinav Gupta
 */
public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static final String CONFIG_FILE = "src/main/resources/config.properties";
    private static ConfigManager instance;
    private Properties properties;
    
    /**
     * Private constructor for singleton pattern
     */
    private ConfigManager() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            logger.info("Configuration loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration: {}", e.getMessage());
        }
    }
    
    /**
     * Gets the singleton instance
     * 
     * @return ConfigManager instance
     */
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
    
    /**
     * Gets a property value
     * 
     * @param key Property key
     * @return Property value or null if not found
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Gets a property value with default value
     * 
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Property value or default value if not found
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Gets a property as an integer
     * 
     * @param key Property key
     * @param defaultValue Default value if property not found or not an integer
     * @return Property value as integer or default value
     */
    public int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.warn("Property {} is not a valid integer: {}", key, value);
            return defaultValue;
        }
    }
    
    /**
     * Gets a property as a boolean
     * 
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Property value as boolean or default value
     */
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
} 