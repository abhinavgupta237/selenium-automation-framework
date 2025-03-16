package com.qasmarts.tests;

import com.qasmarts.framework.pages.DynamicLoadingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Dynamic Loading functionality
 * 
 * @author Abhinav Gupta
 */
public class DynamicLoadingTest extends BaseTest {
    
    /**
     * Test dynamic loading example 1
     */
    @Test(description = "Verify dynamic loading example 1")
    public void testDynamicLoadingExample1() {
        // Create page object and navigate to example 1
        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);
        dynamicLoadingPage.open(getBaseUrl(), 1);
        
        // Verify page heading
        Assert.assertEquals(dynamicLoadingPage.getPageHeading(), "Dynamically Loaded Page Elements",
                "Page heading should be 'Dynamically Loaded Page Elements'");
        
        // Verify sub-heading
        Assert.assertEquals(dynamicLoadingPage.getSubHeading(), "Example 1: Element on page that is hidden",
                "Sub-heading should describe Example 1");
        
        // Load content
        dynamicLoadingPage.loadContent();
        
        // Verify finish text
        Assert.assertEquals(dynamicLoadingPage.getFinishText(), "Hello World!",
                "Finish text should be 'Hello World!'");
        
        // Verify finish element is displayed
        Assert.assertTrue(dynamicLoadingPage.isFinishElementDisplayed(),
                "Finish element should be displayed after loading");
    }
    
    /**
     * Test dynamic loading example 2
     */
    @Test(description = "Verify dynamic loading example 2")
    public void testDynamicLoadingExample2() {
        // Create page object and navigate to example 2
        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);
        dynamicLoadingPage.open(getBaseUrl(), 2);
        
        // Verify page heading
        Assert.assertEquals(dynamicLoadingPage.getPageHeading(), "Dynamically Loaded Page Elements",
                "Page heading should be 'Dynamically Loaded Page Elements'");
        
        // Verify sub-heading
        Assert.assertEquals(dynamicLoadingPage.getSubHeading(), "Example 2: Element rendered after the fact",
                "Sub-heading should describe Example 2");
        
        // Verify finish element is not displayed initially
        Assert.assertFalse(dynamicLoadingPage.isFinishElementDisplayed(),
                "Finish element should not be displayed initially");
        
        // Load content
        dynamicLoadingPage.loadContent();
        
        // Verify finish text
        Assert.assertEquals(dynamicLoadingPage.getFinishText(), "Hello World!",
                "Finish text should be 'Hello World!'");
        
        // Verify finish element is displayed
        Assert.assertTrue(dynamicLoadingPage.isFinishElementDisplayed(),
                "Finish element should be displayed after loading");
    }
    
    /**
     * Test clicking start and waiting for loading separately
     */
    @Test(description = "Verify clicking start and waiting for loading separately")
    public void testManualLoadingSteps() {
        // Create page object and navigate to example 1
        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);
        dynamicLoadingPage.open(getBaseUrl(), 1);
        
        // Click start
        dynamicLoadingPage.clickStart();
        
        // Wait for loading
        dynamicLoadingPage.waitForLoading();
        
        // Verify finish text
        Assert.assertEquals(dynamicLoadingPage.getFinishText(), "Hello World!",
                "Finish text should be 'Hello World!'");
    }
} 