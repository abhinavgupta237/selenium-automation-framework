package com.qasmarts.tests;

import com.qasmarts.framework.pages.LoginPage;
import com.qasmarts.framework.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Data-driven test class for Login functionality
 * 
 * @author Abhinav Gupta
 */
public class DataDrivenLoginTest extends BaseTest {
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
     * Data provider for login credentials
     * 
     * @return 2D array of login test data
     */
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            // username, password, shouldSucceed
            { "tomsmith", "SuperSecretPassword!", true },
            { "invaliduser", "SuperSecretPassword!", false },
            { "tomsmith", "wrongpassword", false },
            { "", "", false },
            { "tomsmith", "", false },
            { "", "SuperSecretPassword!", false }
        };
    }
    
    /**
     * Data provider that would normally use Excel data
     * Note: Since actual Excel file might not exist, this is a placeholder
     * 
     * @return 2D array of login test data
     */
    @DataProvider(name = "excelLoginData")
    public Object[][] getExcelLoginData() {
        // In a real implementation, this would use:
        // return ExcelUtils.getTestData("loginData.xlsx", "LoginCredentials");
        
        // For demonstration purposes, return hardcoded data
        return new Object[][] {
            // username, password, shouldSucceed
            { "tomsmith", "SuperSecretPassword!", true },
            { "invaliduser", "SuperSecretPassword!", false }
        };
    }
    
    /**
     * Data-driven test for login functionality
     * 
     * @param username Username to test
     * @param password Password to test
     * @param shouldSucceed Expected result (true for successful login, false for failure)
     */
    @Test(dataProvider = "loginData", description = "Verify login with different credentials")
    public void testLoginWithDifferentCredentials(String username, String password, boolean shouldSucceed) {
        // Open login page
        loginPage.open(getBaseUrl());
        
        // Attempt login
        if (username.isEmpty() && password.isEmpty()) {
            // If both fields are empty, just click login button
            loginPage.clickLoginButton();
        } else {
            // Otherwise enter the credentials provided
            loginPage.login(username, password);
        }
        
        if (shouldSucceed) {
            // If expected to succeed, verify URL contains "/secure"
            Assert.assertTrue(driver.getCurrentUrl().contains("/secure"),
                    "Login with valid credentials should redirect to secure page");
        } else {
            // If expected to fail, verify error message is displayed
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Login with invalid credentials should display error message");
        }
    }
    
    /**
     * Another data-driven test method to demonstrate using a different data provider
     * 
     * @param username Username to test
     * @param password Password to test
     * @param shouldSucceed Expected result (true for successful login, false for failure)
     */
    @Test(dataProvider = "excelLoginData", description = "Verify login with credentials from Excel")
    public void testLoginWithExcelData(String username, String password, boolean shouldSucceed) {
        // Open login page
        loginPage.open(getBaseUrl());
        
        // Attempt login
        loginPage.login(username, password);
        
        if (shouldSucceed) {
            // If expected to succeed, verify URL contains "/secure"
            Assert.assertTrue(driver.getCurrentUrl().contains("/secure"),
                    "Login with valid credentials should redirect to secure page");
        } else {
            // If expected to fail, verify error message is displayed
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Login with invalid credentials should display error message");
        }
    }
} 