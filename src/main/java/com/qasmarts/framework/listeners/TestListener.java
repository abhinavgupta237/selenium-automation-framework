package com.qasmarts.framework.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG listener to handle test execution events
 * 
 * @author Abhinav Gupta
 */
public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    
    @Override
    public void onStart(ITestContext context) {
        logger.info("========== Starting Test Suite: {} ==========", context.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        logger.info("========== Finished Test Suite: {} ==========", context.getName());
        logger.info("Passed tests: {}", context.getPassedTests().size());
        logger.info("Failed tests: {}", context.getFailedTests().size());
        logger.info("Skipped tests: {}", context.getSkippedTests().size());
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: {}", result.getName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getName());
        logger.error("Exception: {}", result.getThrowable().getMessage());
        
        // Take screenshot on failure
        WebDriver driver = getDriverFromResult(result);
        if (driver != null) {
            takeScreenshot(driver, result.getName());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getName());
    }
    
    /**
     * Takes screenshot when test fails
     * 
     * @param driver WebDriver instance
     * @param testName Name of the test
     */
    private void takeScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if it doesn't exist
            Path screenshotsDir = Paths.get("target", "screenshots");
            if (!Files.exists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }
            
            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Path destination = Paths.get(screenshotsDir.toString(), testName + "_" + timestamp + ".png");
            
            // Save screenshot
            Files.copy(screenshot.toPath(), destination);
            logger.info("Screenshot saved to: {}", destination);
        } catch (IOException e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
        }
    }
    
    /**
     * Gets WebDriver from test result
     * 
     * @param result ITestResult instance
     * @return WebDriver instance or null if not found
     */
    private WebDriver getDriverFromResult(ITestResult result) {
        Object testInstance = result.getInstance();
        try {
            // Get driver field using reflection
            return (WebDriver) testInstance.getClass().getDeclaredField("driver").get(testInstance);
        } catch (Exception e) {
            logger.error("Failed to get WebDriver instance: {}", e.getMessage());
            return null;
        }
    }
} 