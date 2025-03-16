package com.qasmarts.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for File Download page
 * 
 * @author Abhinav Gupta
 */
public class FileDownloadPage extends BasePage {
    
    // Page elements
    @FindBy(css = "div.example h3")
    private WebElement pageHeading;
    
    @FindBy(css = "div.example a")
    private List<WebElement> downloadLinks;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public FileDownloadPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Open the file download page
     * 
     * @param baseUrl Base URL of the application
     */
    public void open(String baseUrl) {
        driver.get(baseUrl + "/download");
        waitForPageToLoad();
    }
    
    /**
     * Get the page heading text
     * 
     * @return Page heading text
     */
    public String getPageHeading() {
        return pageHeading.getText();
    }
    
    /**
     * Check if file list is displayed
     * 
     * @return true if file list is displayed, false otherwise
     */
    public boolean isFileListDisplayed() {
        return !downloadLinks.isEmpty();
    }
    
    /**
     * Get available file names for download
     * 
     * @return List of available file names
     */
    public List<String> getAvailableFiles() {
        return downloadLinks.stream()
                .map(WebElement::getText)
                .toList();
    }
    
    /**
     * Download a file by name
     * 
     * @param fileName Name of the file to download
     * @throws RuntimeException if file not found
     */
    public void downloadFile(String fileName) {
        logger.info("Downloading file: " + fileName);
        
        for (WebElement link : downloadLinks) {
            if (link.getText().contains(fileName)) {
                scrollToElement(link);
                link.click();
                // No need to wait for page load since it's a download
                return;
            }
        }
        
        throw new RuntimeException("File not found for download: " + fileName);
    }
    
    /**
     * Download a file by index (useful when exact file name is not known)
     * 
     * @param index Index of the file to download (0-based)
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void downloadFileByIndex(int index) {
        if (index < 0 || index >= downloadLinks.size()) {
            throw new IndexOutOfBoundsException("Invalid file index: " + index);
        }
        
        WebElement link = downloadLinks.get(index);
        logger.info("Downloading file by index " + index + ": " + link.getText());
        scrollToElement(link);
        link.click();
    }
} 