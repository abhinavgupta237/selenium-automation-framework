package com.qasmarts.tests;

import com.qasmarts.framework.pages.HomePage;
import com.qasmarts.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for Home Page functionality
 * 
 * @author Abhinav Gupta
 */
public class HomePageTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects and logs in
     */
    @BeforeMethod
    public void setUpPageObjects() {
        loginPage = new LoginPage(driver);
        // Login before each test
        loginPage.open(getBaseUrl());
        homePage = loginPage.login("tomsmith", "SuperSecretPassword!");
    }
    
    /**
     * Test home page heading
     */
    @Test(description = "Verify home page heading after successful login")
    public void testHomePageHeading() {
        // Assert heading
        String heading = homePage.getHeading();
        Assert.assertEquals(heading, "Secure Area", 
                           "Home page heading should be 'Secure Area'");
    }
    
    /**
     * Test success message
     */
    @Test(description = "Verify success message is displayed after login")
    public void testSuccessMessage() {
        // Assert success message
        Assert.assertTrue(homePage.isSuccessMessageDisplayed(), 
                         "Success message should be displayed after login");
        String message = homePage.getSuccessMessage();
        Assert.assertTrue(message.contains("You logged into a secure area"), 
                         "Success message should contain 'You logged into a secure area'");
    }
    
    /**
     * Test logout functionality
     */
    @Test(description = "Verify logout functionality")
    public void testLogout() {
        // Logout
        LoginPage logoutResult = homePage.clickLogout();
        
        // Assert logout redirects to login page
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), 
                         "URL should contain '/login' after logout");
        
        // Assert flash message (placeholder assertion)
        Assert.assertTrue(driver.getPageSource().contains("You have been logged out"), 
                         "Page should contain logout confirmation message");
    }
    
    /**
     * Test page elements displayed correctly
     */
    @Test(description = "Verify all expected page elements are displayed")
    public void testPageElements() {
        // Placeholder test for verifying multiple elements on home page
        Assert.assertEquals(homePage.getSubHeading(), "Welcome to the Secure Area. When you are done click logout below.", 
                           "Sub-heading text should match expected value");
    }
    
    /**
     * Test navigation to profile page
     */
    @Test(description = "Verify navigation to profile page")
    public void testProfileNavigation() {
        // This test is a placeholder and may fail since it's using a hypothetical page
        // But it's included for demonstration purposes
        
        try {
            // Navigate to profile
            HomePage.ProfilePage profilePage = homePage.clickProfile();
            
            // Add assertions here if profile page exists
            // This is just a placeholder
        } catch (Exception e) {
            // If the profile button doesn't exist, this test would be skipped or marked as not applicable
            Assert.fail("This test is a placeholder for demonstration - profile navigation not implemented in the demo site");
        }
    }
} 