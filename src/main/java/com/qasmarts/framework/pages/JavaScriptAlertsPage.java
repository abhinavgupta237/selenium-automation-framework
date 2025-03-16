package com.qasmarts.framework.pages;

import com.qasmarts.framework.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object for JavaScript Alerts Page
 * 
 * @author Abhinav Gupta
 */
public class JavaScriptAlertsPage extends BasePage {
    
    @FindBy(css = "div.example > h3")
    private WebElement pageHeading;
    
    @FindBy(xpath = "//button[contains(text(),'Click for JS Alert')]")
    private WebElement jsAlertButton;
    
    @FindBy(xpath = "//button[contains(text(),'Click for JS Confirm')]")
    private WebElement jsConfirmButton;
    
    @FindBy(xpath = "//button[contains(text(),'Click for JS Prompt')]")
    private WebElement jsPromptButton;
    
    @FindBy(id = "result")
    private WebElement resultText;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the JavaScript alerts page
     * 
     * @param baseUrl Base URL of the application
     * @return The JavaScript alerts page instance
     */
    public JavaScriptAlertsPage open(String baseUrl) {
        driver.get(baseUrl + "/javascript_alerts");
        waitForPageLoad();
        return this;
    }
    
    /**
     * Gets the page heading
     * 
     * @return The heading text
     */
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    /**
     * Clicks the JS Alert button and accepts the alert
     * 
     * @return The JavaScript alerts page instance
     */
    public JavaScriptAlertsPage clickJsAlert() {
        click(jsAlertButton);
        
        // Wait for alert to be present
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        
        // Accept alert
        alert.accept();
        
        return this;
    }
    
    /**
     * Clicks the JS Confirm button and handles the confirmation dialog
     * 
     * @param accept true to accept, false to dismiss
     * @return The JavaScript alerts page instance
     */
    public JavaScriptAlertsPage clickJsConfirm(boolean accept) {
        click(jsConfirmButton);
        
        // Wait for alert to be present
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        
        // Accept or dismiss alert
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        
        return this;
    }
    
    /**
     * Clicks the JS Prompt button and enters the specified text
     * 
     * @param text Text to enter in prompt
     * @param accept true to accept, false to dismiss
     * @return The JavaScript alerts page instance
     */
    public JavaScriptAlertsPage clickJsPrompt(String text, boolean accept) {
        click(jsPromptButton);
        
        // Wait for alert to be present
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        
        // Enter text
        alert.sendKeys(text);
        
        // Accept or dismiss alert
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        
        return this;
    }
    
    /**
     * Gets the result text
     * 
     * @return The result text
     */
    public String getResultText() {
        return getText(resultText);
    }
    
    /**
     * Waits for a specific result text
     * 
     * @param expectedText Expected result text
     * @return The JavaScript alerts page instance
     */
    public JavaScriptAlertsPage waitForResult(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElement(resultText, expectedText));
        return this;
    }
} 