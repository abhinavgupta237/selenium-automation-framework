package com.qasmarts.tests;

import com.qasmarts.framework.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Base Test class that all test classes can extend.
 * Contains common setup and teardown methods.
 * 
 * @author Abhinav Gupta
 */
public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://the-internet.herokuapp.com";
    
    /**
     * Setup method that runs before each test
     * 
     * @param browser Browser name from testng.xml
     */
    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        // Initialize WebDriver
        driver = WebDriverFactory.createDriver(browser);
    }
    
    /**
     * Cleanup method that runs after each test
     */
    @AfterMethod
    public void tearDown() {
        // Quit WebDriver
        WebDriverFactory.quitDriver(driver);
    }
    
    /**
     * Get the base URL for the application under test
     * 
     * @return Base URL
     */
    protected String getBaseUrl() {
        return BASE_URL;
    }
} 