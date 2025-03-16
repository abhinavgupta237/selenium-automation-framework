package com.qasmarts.framework.pages;

import com.qasmarts.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object for Dropdown Page
 * 
 * @author Abhinav Gupta
 */
public class DropdownPage extends BasePage {
    
    @FindBy(id = "dropdown")
    private WebElement dropdown;
    
    @FindBy(tagName = "h3")
    private WebElement pageHeading;
    
    private Select dropdownSelect;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public DropdownPage(WebDriver driver) {
        super(driver);
        dropdownSelect = new Select(dropdown);
    }
    
    /**
     * Opens the dropdown page
     * 
     * @param baseUrl Base URL of the application
     * @return The dropdown page instance
     */
    public DropdownPage open(String baseUrl) {
        driver.get(baseUrl + "/dropdown");
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
     * Selects an option by visible text
     * 
     * @param text Option text
     * @return The dropdown page instance
     */
    public DropdownPage selectByVisibleText(String text) {
        dropdownSelect.selectByVisibleText(text);
        return this;
    }
    
    /**
     * Selects an option by value
     * 
     * @param value Option value
     * @return The dropdown page instance
     */
    public DropdownPage selectByValue(String value) {
        dropdownSelect.selectByValue(value);
        return this;
    }
    
    /**
     * Selects an option by index
     * 
     * @param index Option index (zero-based)
     * @return The dropdown page instance
     */
    public DropdownPage selectByIndex(int index) {
        dropdownSelect.selectByIndex(index);
        return this;
    }
    
    /**
     * Gets the text of the selected option
     * 
     * @return Selected option text
     */
    public String getSelectedOption() {
        return dropdownSelect.getFirstSelectedOption().getText();
    }
    
    /**
     * Gets all available options
     * 
     * @return List of option texts
     */
    public List<String> getAllOptions() {
        return dropdownSelect.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets the number of options
     * 
     * @return Number of options
     */
    public int getOptionCount() {
        return dropdownSelect.getOptions().size();
    }
    
    /**
     * Checks if the dropdown allows multiple selections
     * 
     * @return true if multiple selections are allowed, false otherwise
     */
    public boolean isMultiple() {
        return dropdownSelect.isMultiple();
    }
} 