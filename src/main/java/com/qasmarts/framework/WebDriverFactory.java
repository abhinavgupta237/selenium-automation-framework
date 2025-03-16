package com.qasmarts.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * Factory class to create and manage WebDriver instances
 * 
 * @author Abhinav Gupta
 */
public class WebDriverFactory {
    
    /**
     * Creates a WebDriver instance based on the browser name
     * 
     * @param browser Browser name (chrome, firefox, edge, safari)
     * @return WebDriver instance
     * @throws IllegalArgumentException if browser is not supported
     */
    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                // Add Chrome-specific options
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // Add Firefox-specific options
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                // Add Edge-specific options
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;
                
            case "safari":
                // Safari doesn't need WebDriverManager
                driver = new SafariDriver();
                break;
                
            default:
                throw new IllegalArgumentException("Browser " + browser + " is not supported");
        }
        
        // Common settings for all browsers
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        
        return driver;
    }
    
    /**
     * Quits the WebDriver instance
     * 
     * @param driver WebDriver instance to quit
     */
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
} 