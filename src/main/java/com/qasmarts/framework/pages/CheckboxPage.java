package com.qasmarts.framework.pages;

import com.qasmarts.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for Checkboxes Page
 * 
 * @author Abhinav Gupta
 */
public class CheckboxPage extends BasePage {
    
    @FindBy(css = "input[type='checkbox']")
    private List<WebElement> checkboxes;
    
    @FindBy(tagName = "h3")
    private WebElement pageHeading;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public CheckboxPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the checkboxes page
     * 
     * @param baseUrl Base URL of the application
     * @return The checkboxes page instance
     */
    public CheckboxPage open(String baseUrl) {
        driver.get(baseUrl + "/checkboxes");
        waitForPageLoad();
        return this;
    }
    
    /**
     * Gets the page heading
     * 
     * @return The heading text
     */
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    /**
     * Gets the total number of checkboxes
     * 
     * @return Number of checkboxes
     */
    public int getCheckboxCount() {
        return checkboxes.size();
    }
    
    /**
     * Checks if a checkbox is selected
     * 
     * @param index Checkbox index (zero-based)
     * @return true if checkbox is selected, false otherwise
     */
    public boolean isCheckboxSelected(int index) {
        if (index >= 0 && index < checkboxes.size()) {
            return checkboxes.get(index).isSelected();
        }
        throw new IndexOutOfBoundsException("Invalid checkbox index: " + index);
    }
    
    /**
     * Sets checkbox state
     * 
     * @param index Checkbox index (zero-based)
     * @param select true to select, false to unselect
     * @return The checkboxes page instance
     */
    public CheckboxPage setCheckbox(int index, boolean select) {
        if (index >= 0 && index < checkboxes.size()) {
            WebElement checkbox = checkboxes.get(index);
            if (checkbox.isSelected() != select) {
                click(checkbox);
            }
            return this;
        }
        throw new IndexOutOfBoundsException("Invalid checkbox index: " + index);
    }
    
    /**
     * Selects all checkboxes
     * 
     * @return The checkboxes page instance
     */
    public CheckboxPage selectAllCheckboxes() {
        for (int i = 0; i < checkboxes.size(); i++) {
            setCheckbox(i, true);
        }
        return this;
    }
    
    /**
     * Unselects all checkboxes
     * 
     * @return The checkboxes page instance
     */
    public CheckboxPage unselectAllCheckboxes() {
        for (int i = 0; i < checkboxes.size(); i++) {
            setCheckbox(i, false);
        }
        return this;
    }
    
    /**
     * Toggles a checkbox
     * 
     * @param index Checkbox index (zero-based)
     * @return The checkboxes page instance
     */
    public CheckboxPage toggleCheckbox(int index) {
        if (index >= 0 && index < checkboxes.size()) {
            WebElement checkbox = checkboxes.get(index);
            click(checkbox);
            return this;
        }
        throw new IndexOutOfBoundsException("Invalid checkbox index: " + index);
    }
} 