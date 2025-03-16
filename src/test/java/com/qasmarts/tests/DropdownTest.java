package com.qasmarts.tests;

import com.qasmarts.framework.pages.DropdownPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class for Dropdown functionality
 * 
 * @author Abhinav Gupta
 */
public class DropdownTest extends BaseTest {
    private DropdownPage dropdownPage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects
     */
    @BeforeMethod
    public void setUpPageObjects() {
        dropdownPage = new DropdownPage(driver);
        dropdownPage.open(getBaseUrl());
    }
    
    /**
     * Test page elements
     */
    @Test(description = "Verify page elements on dropdown page")
    public void testPageElements() {
        // Verify page heading
        Assert.assertEquals(dropdownPage.getPageHeading(), "Dropdown List",
                "Page heading should be 'Dropdown List'");
        
        // Verify dropdown options count
        Assert.assertEquals(dropdownPage.getOptionCount(), 3,
                "There should be exactly 3 options in the dropdown (including default option)");
    }
    
    /**
     * Test dropdown options
     */
    @Test(description = "Verify dropdown options")
    public void testDropdownOptions() {
        // Get all options
        List<String> options = dropdownPage.getAllOptions();
        
        // Verify specific options
        Assert.assertTrue(options.contains("Please select an option"),
                "Dropdown should contain 'Please select an option'");
        Assert.assertTrue(options.contains("Option 1"),
                "Dropdown should contain 'Option 1'");
        Assert.assertTrue(options.contains("Option 2"),
                "Dropdown should contain 'Option 2'");
    }
    
    /**
     * Test selecting option by visible text
     */
    @Test(description = "Verify selecting dropdown option by visible text")
    public void testSelectByVisibleText() {
        // Select Option 1
        dropdownPage.selectByVisibleText("Option 1");
        
        // Verify selection
        Assert.assertEquals(dropdownPage.getSelectedOption(), "Option 1",
                "Option 1 should be selected");
        
        // Select Option 2
        dropdownPage.selectByVisibleText("Option 2");
        
        // Verify selection
        Assert.assertEquals(dropdownPage.getSelectedOption(), "Option 2",
                "Option 2 should be selected");
    }
    
    /**
     * Test selecting option by value
     */
    @Test(description = "Verify selecting dropdown option by value")
    public void testSelectByValue() {
        // Select Option 1 by value
        dropdownPage.selectByValue("1");
        
        // Verify selection
        Assert.assertEquals(dropdownPage.getSelectedOption(), "Option 1",
                "Option 1 should be selected");
        
        // Select Option 2 by value
        dropdownPage.selectByValue("2");
        
        // Verify selection
        Assert.assertEquals(dropdownPage.getSelectedOption(), "Option 2",
                "Option 2 should be selected");
    }
    
    /**
     * Test selecting option by index
     */
    @Test(description = "Verify selecting dropdown option by index")
    public void testSelectByIndex() {
        // Select Option 1 by index (1)
        dropdownPage.selectByIndex(1);
        
        // Verify selection
        Assert.assertEquals(dropdownPage.getSelectedOption(), "Option 1",
                "Option 1 should be selected");
        
        // Select Option 2 by index (2)
        dropdownPage.selectByIndex(2);
        
        // Verify selection
        Assert.assertEquals(dropdownPage.getSelectedOption(), "Option 2",
                "Option 2 should be selected");
    }
    
    /**
     * Test if dropdown is multiple select
     */
    @Test(description = "Verify dropdown is not multiple select")
    public void testIsMultiple() {
        // Verify dropdown is not multiple select
        Assert.assertFalse(dropdownPage.isMultiple(),
                "Dropdown should not allow multiple selections");
    }
} 