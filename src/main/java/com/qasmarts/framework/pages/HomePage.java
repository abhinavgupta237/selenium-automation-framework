package com.qasmarts.framework.pages;

import com.qasmarts.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Home Page
 * 
 * @author Abhinav Gupta
 */
public class HomePage extends BasePage {
    
    // Page elements using @FindBy annotation
    @FindBy(css = "h1.heading")
    private WebElement heading;
    
    @FindBy(css = "h2")
    private WebElement subHeading;
    
    @FindBy(css = ".flash.success")
    private WebElement successMessage;
    
    @FindBy(linkText = "Logout")
    private WebElement logoutButton;
    
    @FindBy(css = "a.button")
    private WebElement profileButton;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the home page
     * 
     * @param baseUrl Base URL of the application
     * @return The home page instance
     */
    public HomePage open(String baseUrl) {
        driver.get(baseUrl + "/secure");
        waitForPageLoad();
        return this;
    }
    
    /**
     * Gets the page heading
     * 
     * @return The heading text
     */
    public String getHeading() {
        return getText(heading);
    }
    
    /**
     * Gets the page subheading
     * 
     * @return The subheading text
     */
    public String getSubHeading() {
        return getText(subHeading);
    }
    
    /**
     * Gets the success message displayed after login
     * 
     * @return The success message text
     */
    public String getSuccessMessage() {
        return getText(successMessage);
    }
    
    /**
     * Checks if success message is displayed
     * 
     * @return true if success message is displayed, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }
    
    /**
     * Clicks on logout button
     * 
     * @return The login page instance
     */
    public LoginPage clickLogout() {
        click(logoutButton);
        return new LoginPage(driver);
    }
    
    /**
     * Clicks on profile button
     * 
     * @return The profile page instance
     */
    public ProfilePage clickProfile() {
        click(profileButton);
        return new ProfilePage(driver);
    }
    
    /**
     * Placeholder class for ProfilePage (to be implemented)
     */
    public static class ProfilePage extends BasePage {
        public ProfilePage(WebDriver driver) {
            super(driver);
        }
    }
} 