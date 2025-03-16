package com.qasmarts.tests;

import com.qasmarts.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for Login functionality
 * 
 * @author Abhinav Gupta
 */
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects
     */
    @BeforeMethod
    public void setUpPageObjects() {
        loginPage = new LoginPage(driver);
    }
    
    /**
     * Test successful login
     */
    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        // Open login page
        loginPage.open(getBaseUrl());
        
        // Login with valid credentials
        LoginPage.HomePage homePage = loginPage.login("tomsmith", "SuperSecretPassword!");
        
        // Assert successful login (placeholder assertion for demo)
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"), 
                         "URL should contain '/secure' after successful login");
    }
    
    /**
     * Test login with invalid username
     */
    @Test(description = "Verify error message with invalid username")
    public void testInvalidUsername() {
        // Open login page
        loginPage.open(getBaseUrl());
        
        // Login with invalid username
        loginPage.login("invaliduser", "SuperSecretPassword!");
        
        // Assert error message
        Assert.assertTrue(loginPage.isErrorDisplayed(), 
                         "Error message should be displayed for invalid username");
        Assert.assertTrue(loginPage.getErrorMessage().contains("invalid"), 
                         "Error message should contain 'invalid' for invalid username");
    }
    
    /**
     * Test login with invalid password
     */
    @Test(description = "Verify error message with invalid password")
    public void testInvalidPassword() {
        // Open login page
        loginPage.open(getBaseUrl());
        
        // Login with invalid password
        loginPage.login("tomsmith", "wrongpassword");
        
        // Assert error message
        Assert.assertTrue(loginPage.isErrorDisplayed(), 
                         "Error message should be displayed for invalid password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("invalid"), 
                         "Error message should contain 'invalid' for invalid password");
    }
    
    /**
     * Test forgot password link
     */
    @Test(description = "Verify forgot password functionality")
    public void testForgotPassword() {
        // Open login page
        loginPage.open(getBaseUrl());
        
        // Click on forgot password
        LoginPage.ForgotPasswordPage forgotPasswordPage = loginPage.clickForgotPassword();
        
        // Assert navigation to forgot password page (placeholder assertion for demo)
        Assert.assertTrue(driver.getCurrentUrl().contains("/forgot_password"), 
                         "URL should contain '/forgot_password' after clicking forgot password link");
    }
} 