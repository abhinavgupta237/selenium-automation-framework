package com.qasmarts.tests;

import com.qasmarts.framework.pages.IFramePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for iFrame functionality
 * 
 * @author Abhinav Gupta
 */
public class IFrameTest extends BaseTest {
    private IFramePage iFramePage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects
     */
    @BeforeMethod
    public void setUpPageObjects() {
        iFramePage = new IFramePage(driver);
        iFramePage.open(getBaseUrl());
    }
    
    /**
     * Test page elements
     */
    @Test(description = "Verify page elements on iFrame page")
    public void testPageElements() {
        // Verify page heading
        Assert.assertEquals(iFramePage.getPageHeading(), "An iFrame containing the TinyMCE WYSIWYG Editor",
                "Page heading should match expected value");
        
        // Verify iFrame is present
        Assert.assertTrue(iFramePage.isIframePresent(), 
                "iFrame should be present on the page");
    }
    
    /**
     * Test entering text in iFrame
     */
    @Test(description = "Verify entering text in iFrame")
    public void testEnterTextInIframe() {
        // Text to enter in the iframe
        String textToEnter = "This is a test text entered in the iframe";
        
        // Enter text in iframe
        iFramePage.setIframeText(textToEnter);
        
        // Verify the text was entered correctly
        String actualText = iFramePage.getIframeText();
        Assert.assertEquals(actualText, textToEnter, 
                "Text in iframe should match what was entered");
    }
    
    /**
     * Test clearing text in iFrame
     */
    @Test(description = "Verify clearing text in iFrame")
    public void testClearTextInIframe() {
        // First, set some text
        iFramePage.setIframeText("Initial text that will be cleared");
        
        // Clear the text
        iFramePage.clearIframeText();
        
        // Verify text is cleared (TinyMCE might have a paragraph tag so trim it)
        String actualText = iFramePage.getIframeText().trim();
        Assert.assertTrue(actualText.isEmpty() || actualText.equals(""),
                "Text in iframe should be empty after clearing");
    }
    
    /**
     * Test switching between iFrame and main content
     */
    @Test(description = "Verify switching between iFrame and main content")
    public void testSwitchingBetweenContexts() {
        // Switch to iframe
        iFramePage.switchToIframe();
        
        // Perform an action in iframe
        Assert.assertTrue(iFramePage.getIframeText() != null,
                "Should be able to access iframe content");
        
        // Switch back to main content
        iFramePage.switchToMainContent();
        
        // Verify main content can be accessed by checking heading
        Assert.assertEquals(iFramePage.getPageHeading(), "An iFrame containing the TinyMCE WYSIWYG Editor",
                "Should be able to access main content after switching back");
    }
} 