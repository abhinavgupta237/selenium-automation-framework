package com.qasmarts.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for iFrame page
 * 
 * @author Abhinav Gupta
 */
public class IFramePage extends BasePage {
    
    @FindBy(css = "div.example h3")
    private WebElement pageHeading;
    
    @FindBy(id = "mce_0_ifr")
    private WebElement iframeElement;
    
    @FindBy(id = "tinymce")
    private WebElement iframeTextArea;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public IFramePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the iFrame page
     * 
     * @param baseUrl Base URL of the application
     */
    public void open(String baseUrl) {
        driver.get(baseUrl + "/iframe");
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
     * Switches to the iframe
     */
    public void switchToIframe() {
        logger.info("Switching to iframe");
        driver.switchTo().frame(iframeElement);
    }
    
    /**
     * Switches back to the main content
     */
    public void switchToMainContent() {
        logger.info("Switching back to main content");
        driver.switchTo().defaultContent();
    }
    
    /**
     * Clears text in iframe editor
     */
    public void clearIframeText() {
        switchToIframe();
        iframeTextArea.clear();
        switchToMainContent();
    }
    
    /**
     * Sets text in iframe editor
     * 
     * @param text Text to set
     */
    public void setIframeText(String text) {
        logger.info("Setting iframe text: " + text);
        switchToIframe();
        iframeTextArea.clear();
        iframeTextArea.sendKeys(text);
        switchToMainContent();
    }
    
    /**
     * Gets text from iframe editor
     * 
     * @return Text from iframe
     */
    public String getIframeText() {
        switchToIframe();
        String text = iframeTextArea.getText();
        switchToMainContent();
        return text;
    }
    
    /**
     * Checks if iframe is present
     * 
     * @return true if iframe is present, false otherwise
     */
    public boolean isIframePresent() {
        return isElementDisplayed(iframeElement);
    }
} 