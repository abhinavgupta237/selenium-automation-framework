package com.qasmarts.tests;

import com.qasmarts.framework.pages.CheckboxPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for Checkbox functionality
 * 
 * @author Abhinav Gupta
 */
public class CheckboxTest extends BaseTest {
    private CheckboxPage checkboxPage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects
     */
    @BeforeMethod
    public void setUpPageObjects() {
        checkboxPage = new CheckboxPage(driver);
        checkboxPage.open(getBaseUrl());
    }
    
    /**
     * Test page elements
     */
    @Test(description = "Verify page elements on checkbox page")
    public void testPageElements() {
        // Verify page heading
        Assert.assertEquals(checkboxPage.getPageHeading(), "Checkboxes",
                "Page heading should be 'Checkboxes'");
        
        // Verify checkbox count
        Assert.assertEquals(checkboxPage.getCheckboxCount(), 2,
                "There should be exactly 2 checkboxes on the page");
    }
    
    /**
     * Test default checkbox states
     */
    @Test(description = "Verify default checkbox states")
    public void testDefaultCheckboxStates() {
        // By default, first checkbox is unchecked and second checkbox is checked
        Assert.assertFalse(checkboxPage.isCheckboxSelected(0),
                "First checkbox should be unchecked by default");
        Assert.assertTrue(checkboxPage.isCheckboxSelected(1),
                "Second checkbox should be checked by default");
    }
    
    /**
     * Test selecting and unselecting checkboxes
     */
    @Test(description = "Verify selecting and unselecting checkboxes")
    public void testSelectingCheckboxes() {
        // Select first checkbox
        checkboxPage.setCheckbox(0, true);
        Assert.assertTrue(checkboxPage.isCheckboxSelected(0),
                "First checkbox should be checked after selecting it");
        
        // Unselect second checkbox
        checkboxPage.setCheckbox(1, false);
        Assert.assertFalse(checkboxPage.isCheckboxSelected(1),
                "Second checkbox should be unchecked after unselecting it");
    }
    
    /**
     * Test toggling checkboxes
     */
    @Test(description = "Verify toggling checkboxes")
    public void testTogglingCheckboxes() {
        // Initially, first checkbox is unchecked and second checkbox is checked
        boolean initialFirstCheckboxState = checkboxPage.isCheckboxSelected(0);
        boolean initialSecondCheckboxState = checkboxPage.isCheckboxSelected(1);
        
        // Toggle first checkbox
        checkboxPage.toggleCheckbox(0);
        Assert.assertNotEquals(checkboxPage.isCheckboxSelected(0), initialFirstCheckboxState,
                "First checkbox state should be toggled");
        
        // Toggle second checkbox
        checkboxPage.toggleCheckbox(1);
        Assert.assertNotEquals(checkboxPage.isCheckboxSelected(1), initialSecondCheckboxState,
                "Second checkbox state should be toggled");
    }
    
    /**
     * Test selecting all checkboxes
     */
    @Test(description = "Verify selecting all checkboxes")
    public void testSelectingAllCheckboxes() {
        // Select all checkboxes
        checkboxPage.selectAllCheckboxes();
        
        // Verify all checkboxes are selected
        Assert.assertTrue(checkboxPage.isCheckboxSelected(0),
                "First checkbox should be checked after selecting all");
        Assert.assertTrue(checkboxPage.isCheckboxSelected(1),
                "Second checkbox should be checked after selecting all");
    }
    
    /**
     * Test unselecting all checkboxes
     */
    @Test(description = "Verify unselecting all checkboxes")
    public void testUnselectingAllCheckboxes() {
        // Unselect all checkboxes
        checkboxPage.unselectAllCheckboxes();
        
        // Verify all checkboxes are unselected
        Assert.assertFalse(checkboxPage.isCheckboxSelected(0),
                "First checkbox should be unchecked after unselecting all");
        Assert.assertFalse(checkboxPage.isCheckboxSelected(1),
                "Second checkbox should be unchecked after unselecting all");
    }
} 