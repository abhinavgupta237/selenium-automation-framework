package com.qasmarts.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Page object for Multiple Windows page
 * 
 * @author Abhinav Gupta
 */
public class WindowsPage extends BasePage {
    
    @FindBy(css = "div.example h3")
    private WebElement pageHeading;
    
    @FindBy(linkText = "Click Here")
    private WebElement clickHereLink;
    
    @FindBy(xpath = "//div[@class='example']//h3")
    private WebElement newWindowHeading;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public WindowsPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the windows page
     * 
     * @param baseUrl Base URL of the application
     */
    public void open(String baseUrl) {
        driver.get(baseUrl + "/windows");
        waitForPageToLoad();
    }
    
    /**
     * Gets the page heading
     * 
     * @return Page heading text
     */
    public String getPageHeading() {
        return pageHeading.getText();
    }
    
    /**
     * Clicks the "Click Here" link to open a new window
     */
    public void clickOpenNewWindowLink() {
        logger.info("Clicking link to open new window");
        clickHereLink.click();
        waitForNumberOfWindowsToBe(2);
    }
    
    /**
     * Waits for expected number of windows/tabs to be present
     * 
     * @param numberOfWindows Expected number of windows
     */
    public void waitForNumberOfWindowsToBe(int numberOfWindows) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
    }
    
    /**
     * Switches to the new window/tab
     * 
     * @return Original window handle
     */
    public String switchToNewWindow() {
        logger.info("Switching to new window");
        
        // Store original window handle
        String originalWindow = driver.getWindowHandle();
        
        // Switch to new window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        waitForPageToLoad();
        return originalWindow;
    }
    
    /**
     * Switches to window by handle
     * 
     * @param windowHandle Window handle to switch to
     */
    public void switchToWindow(String windowHandle) {
        logger.info("Switching to window with handle: {}", windowHandle);
        driver.switchTo().window(windowHandle);
        waitForPageToLoad();
    }
    
    /**
     * Gets all window handles
     * 
     * @return List of window handles
     */
    public List<String> getAllWindowHandles() {
        Set<String> windowHandles = driver.getWindowHandles();
        return new ArrayList<>(windowHandles);
    }
    
    /**
     * Gets the current window handle
     * 
     * @return Current window handle
     */
    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }
    
    /**
     * Closes the current window/tab and switches to original
     * 
     * @param originalWindow Original window handle
     */
    public void closeCurrentAndSwitchToOriginal(String originalWindow) {
        logger.info("Closing current window and switching to original");
        driver.close();
        driver.switchTo().window(originalWindow);
    }
    
    /**
     * Gets the heading text in the new window
     * 
     * @return Heading text in new window
     */
    public String getNewWindowHeading() {
        return newWindowHeading.getText();
    }
} 