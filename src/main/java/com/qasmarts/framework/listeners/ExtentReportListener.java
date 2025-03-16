package com.qasmarts.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TestNG listener that generates Extent Reports after test execution
 * 
 * @author Abhinav Gupta
 */
public class ExtentReportListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(ExtentReportListener.class);
    private static ExtentReports extent;
    private static final Map<String, ExtentTest> testMap = new HashMap<>();
    
    /**
     * Initializes the ExtentReports instance
     */
    private static synchronized ExtentReports getExtentInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportDir = "target/extent-reports";
            new File(reportDir).mkdirs();
            String reportPath = reportDir + "/TestReport_" + timestamp + ".html";
            
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Web Automation Test Results");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setEncoding("utf-8");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
        return extent;
    }
    
    @Override
    public void onStart(ITestContext context) {
        logger.info("Initializing ExtentReports");
        getExtentInstance();
    }
    
    @Override
    public void onFinish(ITestContext context) {
        logger.info("Flushing ExtentReports");
        if (extent != null) {
            extent.flush();
        }
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getInstanceName();
        String description = result.getMethod().getDescription();
        description = (description == null || description.isEmpty()) ? testName : description;
        
        ExtentTest test = getExtentInstance().createTest(testName, description);
        test.assignCategory(className);
        testMap.put(getTestUniqueId(result), test);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = getTest(result);
        if (test != null) {
            test.log(Status.PASS, "Test passed");
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = getTest(result);
        if (test != null) {
            test.log(Status.FAIL, "Test failed");
            test.log(Status.FAIL, result.getThrowable());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = getTest(result);
        if (test != null) {
            test.log(Status.SKIP, "Test skipped");
            if (result.getThrowable() != null) {
                test.log(Status.SKIP, result.getThrowable());
            }
        }
    }
    
    /**
     * Gets the ExtentTest object for the current test
     * 
     * @param result ITestResult instance
     * @return ExtentTest instance
     */
    private ExtentTest getTest(ITestResult result) {
        return testMap.get(getTestUniqueId(result));
    }
    
    /**
     * Generates a unique ID for the test
     * 
     * @param result ITestResult instance
     * @return Unique test ID
     */
    private String getTestUniqueId(ITestResult result) {
        return result.getInstanceName() + "_" + result.getMethod().getMethodName();
    }
} 