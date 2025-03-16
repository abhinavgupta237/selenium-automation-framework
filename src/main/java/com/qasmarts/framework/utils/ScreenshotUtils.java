package com.qasmarts.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for capturing screenshots
 * 
 * @author Abhinav Gupta
 */
public class ScreenshotUtils {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "target/screenshots/";
    
    /**
     * Private constructor to prevent instantiation
     */
    private ScreenshotUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * Captures a screenshot and saves it to the specified directory
     * 
     * @param driver WebDriver instance
     * @param name Screenshot name
     * @return Path to the saved screenshot or null if failed
     */
    public static String captureScreenshot(WebDriver driver, String name) {
        if (driver == null) {
            logger.error("WebDriver is null, cannot capture screenshot");
            return null;
        }
        
        try {
            // Create screenshots directory if it doesn't exist
            Path screenshotsDir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }
            
            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = name + "_" + timestamp + ".png";
            Path destination = Paths.get(screenshotsDir.toString(), fileName);
            
            // Save screenshot
            Files.copy(screenshot.toPath(), destination);
            logger.info("Screenshot saved to: {}", destination);
            
            return destination.toString();
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }
    
    /**
     * Captures a screenshot and saves it with the current timestamp
     * 
     * @param driver WebDriver instance
     * @return Path to the saved screenshot or null if failed
     */
    public static String captureScreenshot(WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return captureScreenshot(driver, "screenshot_" + timestamp);
    }
    
    /**
     * Captures a screenshot and returns it as Base64 string
     * 
     * @param driver WebDriver instance
     * @return Base64 encoded screenshot string or null if failed
     */
    public static String captureScreenshotAsBase64(WebDriver driver) {
        if (driver == null) {
            logger.error("WebDriver is null, cannot capture screenshot");
            return null;
        }
        
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot as Base64: {}", e.getMessage());
            return null;
        }
    }
} 