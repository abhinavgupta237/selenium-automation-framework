package com.qasmarts.tests;

import com.qasmarts.framework.pages.DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for Drag and Drop functionality
 * 
 * @author Abhinav Gupta
 */
public class DragAndDropTest extends BaseTest {
    private DragAndDropPage dragAndDropPage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects
     */
    @BeforeMethod
    public void setUpPageObjects() {
        dragAndDropPage = new DragAndDropPage(driver);
        dragAndDropPage.open(getBaseUrl());
    }
    
    /**
     * Test page elements
     */
    @Test(description = "Verify page elements on Drag and Drop page")
    public void testPageElements() {
        // Verify page heading
        Assert.assertEquals(dragAndDropPage.getPageHeading(), "Drag and Drop",
                "Page heading should match expected value");
        
        // Verify initial column headers
        Assert.assertEquals(dragAndDropPage.getColumnAHeaderText(), "A",
                "Column A header should be 'A'");
        Assert.assertEquals(dragAndDropPage.getColumnBHeaderText(), "B",
                "Column B header should be 'B'");
    }
    
    /**
     * Test drag from column A to column B using JavaScript
     * Note: Using JavaScript method as it's more reliable across browsers
     */
    @Test(description = "Verify drag and drop from A to B using JavaScript")
    public void testDragAToBWithJS() {
        // Record initial header values
        String initialAHeader = dragAndDropPage.getColumnAHeaderText();
        String initialBHeader = dragAndDropPage.getColumnBHeaderText();
        
        // Perform drag A to B
        dragAndDropPage.dragAToBWithJS();
        
        // Verify the headers are swapped
        Assert.assertEquals(dragAndDropPage.getColumnAHeaderText(), initialBHeader,
                "Column A should now have header '" + initialBHeader + "'");
        Assert.assertEquals(dragAndDropPage.getColumnBHeaderText(), initialAHeader,
                "Column B should now have header '" + initialAHeader + "'");
    }
    
    /**
     * Test drag from column B to column A using JavaScript
     * Note: Using JavaScript method as it's more reliable across browsers
     */
    @Test(description = "Verify drag and drop from B to A using JavaScript")
    public void testDragBToAWithJS() {
        // Record initial header values
        String initialAHeader = dragAndDropPage.getColumnAHeaderText();
        String initialBHeader = dragAndDropPage.getColumnBHeaderText();
        
        // Perform drag B to A
        dragAndDropPage.dragBToAWithJS();
        
        // Verify the headers are swapped
        Assert.assertEquals(dragAndDropPage.getColumnAHeaderText(), initialBHeader,
                "Column A should now have header '" + initialBHeader + "'");
        Assert.assertEquals(dragAndDropPage.getColumnBHeaderText(), initialAHeader,
                "Column B should now have header '" + initialAHeader + "'");
    }
    
    /**
     * Test multiple drag and drop operations
     */
    @Test(description = "Verify multiple drag and drop operations")
    public void testMultipleDragAndDrop() {
        // Verify initial column headers
        Assert.assertEquals(dragAndDropPage.getColumnAHeaderText(), "A",
                "Column A header should be 'A'");
        Assert.assertEquals(dragAndDropPage.getColumnBHeaderText(), "B",
                "Column B header should be 'B'");
        
        // Perform first drag A to B
        dragAndDropPage.dragAToBWithJS();
        
        // Verify headers swapped
        Assert.assertEquals(dragAndDropPage.getColumnAHeaderText(), "B",
                "Column A header should be 'B' after first swap");
        Assert.assertEquals(dragAndDropPage.getColumnBHeaderText(), "A",
                "Column B header should be 'A' after first swap");
        
        // Perform second drag A to B (now B to A)
        dragAndDropPage.dragAToBWithJS();
        
        // Verify headers swapped back
        Assert.assertEquals(dragAndDropPage.getColumnAHeaderText(), "A",
                "Column A header should be 'A' after second swap");
        Assert.assertEquals(dragAndDropPage.getColumnBHeaderText(), "B",
                "Column B header should be 'B' after second swap");
    }
} 