package com.qasmarts.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base Page class that all Page Objects extend.
 * Contains common methods for all page objects.
 * 
 * @author Abhinav Gupta
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;
    
    /**
     * Constructor to initialize the page objects
     * 
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Waits for element to be clickable and clicks on it
     * 
     * @param element WebElement to be clicked
     */
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    
    /**
     * Waits for element to be visible and sends text to it
     * 
     * @param element WebElement to send text to
     * @param text Text to be sent
     */
    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Gets text from element after waiting for it to be visible
     * 
     * @param element WebElement to get text from
     * @return Text of the element
     */
    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    
    /**
     * Checks if element is displayed
     * 
     * @param element WebElement to check
     * @return true if element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Scrolls to element using JavaScript executor
     * 
     * @param element WebElement to scroll to
     */
    protected void scrollToElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Waits for page to load completely
     */
    protected void waitForPageLoad() {
        wait.until(driver -> jsExecutor.executeScript("return document.readyState").equals("complete"));
    }
} 