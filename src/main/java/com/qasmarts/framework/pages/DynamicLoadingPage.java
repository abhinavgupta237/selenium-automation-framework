package com.qasmarts.framework.pages;

import com.qasmarts.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object for Dynamic Loading Page
 * 
 * @author Abhinav Gupta
 */
public class DynamicLoadingPage extends BasePage {
    
    @FindBy(css = "div.example > h3")
    private WebElement pageHeading;
    
    @FindBy(css = "div.example > h4")
    private WebElement subHeading;
    
    @FindBy(tagName = "button")
    private WebElement startButton;
    
    @FindBy(css = "#loading")
    private WebElement loadingIndicator;
    
    @FindBy(css = "#finish")
    private WebElement finishElement;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the specified dynamic loading example
     * 
     * @param baseUrl Base URL of the application
     * @param exampleNumber Example number (1 or 2)
     * @return The dynamic loading page instance
     */
    public DynamicLoadingPage open(String baseUrl, int exampleNumber) {
        driver.get(baseUrl + "/dynamic_loading/" + exampleNumber);
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
     * Gets the sub-heading
     * 
     * @return The sub-heading text
     */
    public String getSubHeading() {
        return getText(subHeading);
    }
    
    /**
     * Clicks the start button to load the content
     * 
     * @return The dynamic loading page instance
     */
    public DynamicLoadingPage clickStart() {
        click(startButton);
        return this;
    }
    
    /**
     * Waits for the loading to complete
     * 
     * @return The dynamic loading page instance
     */
    public DynamicLoadingPage waitForLoading() {
        // Wait for loading indicator to be visible
        wait.until(ExpectedConditions.visibilityOf(loadingIndicator));
        
        // Wait for loading indicator to disappear
        wait.until(ExpectedConditions.invisibilityOf(loadingIndicator));
        
        return this;
    }
    
    /**
     * Gets the text of the finish element that appears after loading
     * 
     * @return The finish element text
     */
    public String getFinishText() {
        // Wait for finish element to be visible
        wait.until(ExpectedConditions.visibilityOf(finishElement));
        
        return getText(finishElement);
    }
    
    /**
     * Performs the complete loading sequence (click start and wait for loading)
     * 
     * @return The dynamic loading page instance
     */
    public DynamicLoadingPage loadContent() {
        clickStart();
        waitForLoading();
        return this;
    }
    
    /**
     * Checks if the finish element is displayed
     * 
     * @return true if finish element is displayed, false otherwise
     */
    public boolean isFinishElementDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(finishElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
} 