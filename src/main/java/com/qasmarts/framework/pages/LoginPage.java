package com.qasmarts.framework.pages;

import com.qasmarts.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Login Page
 * 
 * @author Abhinav Gupta
 */
public class LoginPage extends BasePage {
    
    // Page elements using @FindBy annotation
    @FindBy(id = "username")
    private WebElement usernameInput;
    
    @FindBy(id = "password")
    private WebElement passwordInput;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    @FindBy(linkText = "Forgot Password")
    private WebElement forgotPasswordLink;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the login page
     * 
     * @param baseUrl Base URL of the application
     * @return The login page instance
     */
    public LoginPage open(String baseUrl) {
        driver.get(baseUrl + "/login");
        waitForPageLoad();
        return this;
    }
    
    /**
     * Enters username in the username field
     * 
     * @param username Username to enter
     * @return The login page instance
     */
    public LoginPage enterUsername(String username) {
        sendKeys(usernameInput, username);
        return this;
    }
    
    /**
     * Enters password in the password field
     * 
     * @param password Password to enter
     * @return The login page instance
     */
    public LoginPage enterPassword(String password) {
        sendKeys(passwordInput, password);
        return this;
    }
    
    /**
     * Clicks on the login button
     * 
     * @return The home page instance
     */
    public HomePage clickLoginButton() {
        click(loginButton);
        return new HomePage(driver);
    }
    
    /**
     * Performs login with given credentials
     * 
     * @param username Username to login with
     * @param password Password to login with
     * @return The home page instance
     */
    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }
    
    /**
     * Gets the error message if login fails
     * 
     * @return Error message text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }
    
    /**
     * Checks if error message is displayed
     * 
     * @return true if error message is displayed, false otherwise
     */
    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }
    
    /**
     * Clicks on forgot password link
     * 
     * @return The forgot password page instance
     */
    public ForgotPasswordPage clickForgotPassword() {
        click(forgotPasswordLink);
        return new ForgotPasswordPage(driver);
    }
    
    /**
     * Placeholder class for ForgotPasswordPage (to be implemented)
     */
    public static class ForgotPasswordPage extends BasePage {
        public ForgotPasswordPage(WebDriver driver) {
            super(driver);
        }
    }
} 