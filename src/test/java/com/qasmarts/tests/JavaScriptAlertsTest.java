package com.qasmarts.tests;

import com.qasmarts.framework.pages.JavaScriptAlertsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for JavaScript Alerts functionality
 * 
 * @author Abhinav Gupta
 */
public class JavaScriptAlertsTest extends BaseTest {
    private JavaScriptAlertsPage alertsPage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects
     */
    @BeforeMethod
    public void setUpPageObjects() {
        alertsPage = new JavaScriptAlertsPage(driver);
        alertsPage.open(getBaseUrl());
    }
    
    /**
     * Test page elements
     */
    @Test(description = "Verify page elements on JavaScript alerts page")
    public void testPageElements() {
        // Verify page heading
        Assert.assertEquals(alertsPage.getPageHeading(), "JavaScript Alerts",
                "Page heading should be 'JavaScript Alerts'");
    }
    
    /**
     * Test JavaScript alert
     */
    @Test(description = "Verify JavaScript alert functionality")
    public void testJsAlert() {
        // Click on JS Alert button and accept the alert
        alertsPage.clickJsAlert();
        
        // Verify the result text
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You successfully clicked an alert",
                "Result text should confirm alert was handled");
    }
    
    /**
     * Test JavaScript confirm dialog - Accept
     */
    @Test(description = "Verify JavaScript confirm dialog - Accept")
    public void testJsConfirmAccept() {
        // Click on JS Confirm button and accept the dialog
        alertsPage.clickJsConfirm(true);
        
        // Verify the result text
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You clicked: Ok",
                "Result text should confirm dialog was accepted");
    }
    
    /**
     * Test JavaScript confirm dialog - Cancel
     */
    @Test(description = "Verify JavaScript confirm dialog - Cancel")
    public void testJsConfirmCancel() {
        // Click on JS Confirm button and dismiss the dialog
        alertsPage.clickJsConfirm(false);
        
        // Verify the result text
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You clicked: Cancel",
                "Result text should confirm dialog was dismissed");
    }
    
    /**
     * Test JavaScript prompt - Accept with text
     */
    @Test(description = "Verify JavaScript prompt - Accept with text")
    public void testJsPromptAcceptWithText() {
        // Test text
        String testText = "Test Automation";
        
        // Click on JS Prompt button, enter text, and accept
        alertsPage.clickJsPrompt(testText, true);
        
        // Verify the result text
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You entered: " + testText,
                "Result text should show the entered text");
    }
    
    /**
     * Test JavaScript prompt - Accept with empty text
     */
    @Test(description = "Verify JavaScript prompt - Accept with empty text")
    public void testJsPromptAcceptWithEmptyText() {
        // Click on JS Prompt button, enter empty text, and accept
        alertsPage.clickJsPrompt("", true);
        
        // Verify the result text
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You entered:",
                "Result text should show no entered text");
    }
    
    /**
     * Test JavaScript prompt - Cancel
     */
    @Test(description = "Verify JavaScript prompt - Cancel")
    public void testJsPromptCancel() {
        // Click on JS Prompt button, enter text, and cancel
        alertsPage.clickJsPrompt("Ignored Text", false);
        
        // Verify the result text
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, "You entered: null",
                "Result text should show null for canceled prompt");
    }
    
    /**
     * Test waiting for specific result text
     */
    @Test(description = "Verify waiting for specific result text")
    public void testWaitForResult() {
        // Click on JS Alert button and accept the alert
        alertsPage.clickJsAlert();
        
        // Wait for specific result text and verify
        alertsPage.waitForResult("You successfully clicked an alert");
        
        // If we reach here without timeout exception, the test passed
        Assert.assertTrue(true, "Should wait for expected result text");
    }
} 