package com.qasmarts.tests;

import com.qasmarts.framework.pages.FileDownloadPage;
import com.qasmarts.framework.utils.ScreenshotUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Test class for File Download functionality
 * 
 * @author Abhinav Gupta
 */
public class FileDownloadTest extends BaseTest {
    private FileDownloadPage fileDownloadPage;
    private Path downloadDir;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects and configures download directory
     */
    @BeforeMethod
    public void setUpPageObjects() throws Exception {
        fileDownloadPage = new FileDownloadPage(driver);
        
        // Create a unique download directory for each test
        downloadDir = Paths.get("target/downloads", String.valueOf(System.currentTimeMillis()));
        Files.createDirectories(downloadDir);
        
        // Clean up any existing files from previous test runs
        cleanDownloadDirectory();
    }
    
    /**
     * Test download of text file
     */
    @Test(description = "Verify text file download")
    public void testTextFileDownload() throws Exception {
        // Open download page
        fileDownloadPage.open(getBaseUrl());
        
        // Verify page heading
        Assert.assertEquals(fileDownloadPage.getPageHeading(), "File Downloader",
                "Page heading should be 'File Downloader'");
        
        // Verify file list is displayed
        Assert.assertTrue(fileDownloadPage.isFileListDisplayed(),
                "File list should be displayed");
        
        // Download a text file
        String fileName = "sample.txt";
        fileDownloadPage.downloadFile(fileName);
        
        // Take screenshot after download action (for demonstration purposes)
        ScreenshotUtils.captureScreenshot(driver, "file_download_initiated");
        
        // Wait for file to download (may need to adjust timeout)
        boolean isDownloaded = waitForFileDownload(fileName, Duration.ofSeconds(10));
        
        // Verify file was downloaded
        Assert.assertTrue(isDownloaded, "File should be downloaded: " + fileName);
        
        // Verify file content (optional)
        Path downloadedFile = findDownloadedFile(fileName);
        String content = new String(Files.readAllBytes(downloadedFile));
        Assert.assertTrue(content.length() > 0, "Downloaded file should not be empty");
    }
    
    /**
     * Test download of PDF file
     */
    @Test(description = "Verify PDF file download")
    public void testPdfFileDownload() throws Exception {
        // Open download page
        fileDownloadPage.open(getBaseUrl());
        
        // Download a PDF file
        String fileName = "sample.pdf";
        fileDownloadPage.downloadFile(fileName);
        
        // Wait for file to download
        boolean isDownloaded = waitForFileDownload(fileName, Duration.ofSeconds(15));
        
        // Verify file was downloaded
        Assert.assertTrue(isDownloaded, "File should be downloaded: " + fileName);
        
        // Verify file size (basic validation for binary files)
        Path downloadedFile = findDownloadedFile(fileName);
        Assert.assertTrue(Files.size(downloadedFile) > 0,
                "Downloaded PDF file should not be empty");
    }
    
    /**
     * Helper method to wait for file download completion
     * 
     * @param fileName Name of the file being downloaded
     * @param timeout Maximum time to wait
     * @return true if file was downloaded successfully, false otherwise
     */
    private boolean waitForFileDownload(String fileName, Duration timeout) throws Exception {
        long endTime = System.currentTimeMillis() + timeout.toMillis();
        
        while (System.currentTimeMillis() < endTime) {
            // Check if file exists in download directory
            if (findDownloadedFile(fileName) != null) {
                return true;
            }
            
            // Wait a bit before checking again
            Thread.sleep(500);
        }
        
        return false;
    }
    
    /**
     * Find a downloaded file by name
     * 
     * @param fileName Name of the file to find
     * @return Path to the file, or null if not found
     */
    private Path findDownloadedFile(String fileName) throws Exception {
        File[] files = downloadDir.toFile().listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName) || file.getName().contains(fileName)) {
                    return file.toPath();
                }
            }
        }
        
        return null;
    }
    
    /**
     * Clean the download directory before test
     */
    private void cleanDownloadDirectory() throws Exception {
        File[] files = downloadDir.toFile().listFiles();
        
        if (files != null) {
            for (File file : files) {
                Files.deleteIfExists(file.toPath());
            }
        }
    }
    
    /**
     * Configure Chrome download settings (would be used in a real implementation)
     * Note: This is just for reference, as we're using the BaseTest's driver
     */
    private ChromeOptions configureDownloadSettings() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir.toAbsolutePath().toString());
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", false);
        options.setExperimentalOption("prefs", prefs);
        return options;
    }
} 