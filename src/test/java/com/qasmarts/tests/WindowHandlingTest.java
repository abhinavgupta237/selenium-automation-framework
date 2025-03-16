package com.qasmarts.tests;

import com.qasmarts.framework.pages.WindowsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for Window Handling functionality
 * 
 * @author Abhinav Gupta
 */
public class WindowHandlingTest extends BaseTest {
    private WindowsPage windowsPage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects
     */
    @BeforeMethod
    public void setUpPageObjects() {
        windowsPage = new WindowsPage(driver);
        windowsPage.open(getBaseUrl());
    }
    
    /**
     * Test page elements
     */
    @Test(description = "Verify page elements on Windows page")
    public void testPageElements() {
        // Verify page heading
        Assert.assertEquals(windowsPage.getPageHeading(), "Opening a new window",
                "Page heading should match expected value");
    }
    
    /**
     * Test opening a new window and switching to it
     */
    @Test(description = "Verify opening new window and switching to it")
    public void testOpenAndSwitchToNewWindow() {
        // Get initial window handle
        String initialWindow = windowsPage.getCurrentWindowHandle();
        
        // Open new window
        windowsPage.clickOpenNewWindowLink();
        
        // Verify there are two windows open
        List<String> windowHandles = windowsPage.getAllWindowHandles();
        Assert.assertEquals(windowHandles.size(), 2,
                "There should be 2 windows open");
        
        // Switch to new window
        String originalWindow = windowsPage.switchToNewWindow();
        
        // Verify original window handle was returned correctly
        Assert.assertEquals(originalWindow, initialWindow,
                "Original window handle should match initial window handle");
        
        // Verify content in new window
        Assert.assertEquals(windowsPage.getNewWindowHeading(), "New Window",
                "New window heading should be 'New Window'");
        
        // Close new window and switch back
        windowsPage.closeCurrentAndSwitchToOriginal(originalWindow);
        
        // Verify we're back to the original window
        Assert.assertEquals(windowsPage.getPageHeading(), "Opening a new window",
                "Should be back to original window with correct heading");
    }
    
    /**
     * Test getting all window handles
     */
    @Test(description = "Verify getting all window handles")
    public void testGetAllWindowHandles() {
        // Verify initially there's only one window
        List<String> initialHandles = windowsPage.getAllWindowHandles();
        Assert.assertEquals(initialHandles.size(), 1,
                "Initially there should be only one window");
        
        // Open new window
        windowsPage.clickOpenNewWindowLink();
        
        // Verify there are now two windows
        List<String> afterHandles = windowsPage.getAllWindowHandles();
        Assert.assertEquals(afterHandles.size(), 2,
                "After opening new window, there should be two windows");
        
        // Verify new handle is different from initial
        String initialHandle = initialHandles.get(0);
        String newHandle = null;
        
        for (String handle : afterHandles) {
            if (!handle.equals(initialHandle)) {
                newHandle = handle;
                break;
            }
        }
        
        Assert.assertNotNull(newHandle, "New window handle should be found");
        Assert.assertNotEquals(newHandle, initialHandle,
                "New window handle should be different from initial handle");
        
        // Close the browser windows
        driver.quit();
        
        // Create new driver for other tests (normally handled by BaseTest but we're quitting explicitly)
        setupDriver();
    }
    
    /**
     * Test switching between multiple windows
     */
    @Test(description = "Verify switching between multiple windows")
    public void testSwitchingBetweenWindows() {
        // Get initial window handle
        String initialWindow = windowsPage.getCurrentWindowHandle();
        
        // Open new window
        windowsPage.clickOpenNewWindowLink();
        
        // Switch to new window
        String originalWindow = windowsPage.switchToNewWindow();
        
        // Verify content in new window
        Assert.assertEquals(windowsPage.getNewWindowHeading(), "New Window",
                "New window heading should be 'New Window'");
        
        // Switch back to original window
        windowsPage.switchToWindow(originalWindow);
        
        // Verify content in original window
        Assert.assertEquals(windowsPage.getPageHeading(), "Opening a new window",
                "Original window heading should be correct after switching back");
        
        // Switch to new window again
        List<String> allHandles = windowsPage.getAllWindowHandles();
        String newWindowHandle = null;
        
        for (String handle : allHandles) {
            if (!handle.equals(originalWindow)) {
                newWindowHandle = handle;
                break;
            }
        }
        
        Assert.assertNotNull(newWindowHandle, "New window handle should be found");
        
        // Switch to new window by handle
        windowsPage.switchToWindow(newWindowHandle);
        
        // Verify content in new window again
        Assert.assertEquals(windowsPage.getNewWindowHeading(), "New Window",
                "New window heading should be correct after switching by handle");
    }
} 