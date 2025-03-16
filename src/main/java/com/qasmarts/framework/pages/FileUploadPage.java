package com.qasmarts.framework.pages;

import com.qasmarts.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

/**
 * Page Object for File Upload Page
 * 
 * @author Abhinav Gupta
 */
public class FileUploadPage extends BasePage {
    
    @FindBy(css = "div.example h3")
    private WebElement pageHeading;
    
    @FindBy(id = "file-upload")
    private WebElement fileInput;
    
    @FindBy(id = "file-submit")
    private WebElement uploadButton;
    
    @FindBy(id = "uploaded-files")
    private WebElement uploadedFiles;
    
    @FindBy(css = "div.example h3")
    private WebElement uploadSuccessHeading;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public FileUploadPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the file upload page
     * 
     * @param baseUrl Base URL of the application
     */
    public void open(String baseUrl) {
        driver.get(baseUrl + "/upload");
        waitForPageToLoad();
    }
    
    /**
     * Gets the page heading
     * 
     * @return The heading text
     */
    public String getPageHeading() {
        return pageHeading.getText();
    }
    
    /**
     * Sets the file to upload
     * 
     * @param filePath Absolute path to the file
     */
    public void uploadFile(String filePath) {
        logger.info("Uploading file: " + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }
        
        fileInput.sendKeys(file.getAbsolutePath());
        clickUpload();
        waitForPageToLoad();
    }
    
    /**
     * Clicks the upload button
     */
    public void clickUpload() {
        logger.info("Clicking upload button");
        uploadButton.click();
        waitForPageToLoad();
    }
    
    /**
     * Gets the uploaded file name
     * 
     * @return The uploaded file name
     */
    public String getUploadedFileName() {
        return uploadedFiles.getText();
    }
    
    /**
     * Gets the upload success message
     * 
     * @return The success message
     */
    public String getUploadSuccessHeading() {
        return uploadSuccessHeading.getText();
    }
    
    /**
     * Checks if file upload was successful
     * 
     * @return true if upload was successful, false otherwise
     */
    public boolean isUploadSuccessful() {
        try {
            return getUploadSuccessHeading().equals("File Uploaded!");
        } catch (Exception e) {
            return false;
        }
    }
} 