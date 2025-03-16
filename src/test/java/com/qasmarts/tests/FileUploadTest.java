package com.qasmarts.tests;

import com.qasmarts.framework.pages.FileUploadPage;
import com.qasmarts.framework.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test class for File Upload functionality
 * 
 * @author Abhinav Gupta
 */
public class FileUploadTest extends BaseTest {
    private FileUploadPage fileUploadPage;
    private File tempFile;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects and creates a temporary file for upload
     */
    @BeforeMethod
    public void setUpPageObjects() throws IOException {
        fileUploadPage = new FileUploadPage(driver);
        
        // Create a temporary file for upload testing
        tempFile = createTempFile("test-upload", ".txt", "This is a test file for upload testing.");
    }
    
    /**
     * Test file upload functionality
     */
    @Test(description = "Verify file upload functionality")
    public void testFileUpload() {
        // Open file upload page
        fileUploadPage.open(getBaseUrl());
        
        // Verify page heading
        Assert.assertEquals(fileUploadPage.getPageHeading(), "File Uploader",
                "Page heading should be 'File Uploader'");
        
        // Upload file
        fileUploadPage.uploadFile(tempFile.getAbsolutePath());
        
        // Take screenshot after upload (for demonstration purposes)
        ScreenshotUtils.captureScreenshot(driver, "file_upload_success");
        
        // Verify upload was successful
        Assert.assertTrue(fileUploadPage.isUploadSuccessful(),
                "File upload should be successful");
        
        // Verify uploaded file name
        Assert.assertEquals(fileUploadPage.getUploadedFileName(), tempFile.getName(),
                "Uploaded file name should match the original file name");
    }
    
    /**
     * Test upload without selecting a file
     */
    @Test(description = "Verify error when uploading without selecting a file")
    public void testUploadWithoutFile() {
        // Open file upload page
        fileUploadPage.open(getBaseUrl());
        
        // Click upload without selecting a file
        fileUploadPage.clickUpload();
        
        // Verify page heading does not indicate success
        Assert.assertFalse(fileUploadPage.isUploadSuccessful(),
                "Upload without selecting a file should not be successful");
    }
    
    /**
     * Helper method to create a temporary file
     * 
     * @param prefix File name prefix
     * @param suffix File extension
     * @param content File content
     * @return Created temporary file
     * @throws IOException If file creation fails
     */
    private File createTempFile(String prefix, String suffix, String content) throws IOException {
        // Create temp directory if it doesn't exist
        Path tempDir = Paths.get("target/temp");
        if (!Files.exists(tempDir)) {
            Files.createDirectories(tempDir);
        }
        
        // Create temp file
        File tempFile = File.createTempFile(prefix, suffix, tempDir.toFile());
        tempFile.deleteOnExit(); // Clean up when JVM exits
        
        // Write content to file
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        
        return tempFile;
    }
} 