# Selenium Automation Framework

A comprehensive test automation framework built with Selenium WebDriver, TestNG, and REST Assured for both UI and API testing.

![Framework Overview](https://img.shields.io/badge/Framework-Selenium-green)
![TestNG](https://img.shields.io/badge/TestNG-7.8.0-blue)
![RestAssured](https://img.shields.io/badge/RestAssured-5.3.2-orange)

## 📖 Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Framework Structure](#framework-structure)
- [Test Cases](#test-cases)
- [Setup and Execution](#setup-and-execution)
- [Reporting](#reporting)
- [Extending the Framework](#extending-the-framework)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [Author](#author)
- [License](#license)

## 🚀 Introduction

Selenium Automation Framework एक modern, robust और scalable test automation solution है, जो web applications की testing के लिए बनाया गया है। यह framework multiple browsers (Chrome, Firefox, Edge) पर tests execute कर सकता है और UI testing के साथ-साथ API testing capabilities भी provide करता है।

Beginners के लिए: यह framework आपको आसानी से web applications के automated tests लिखने और run करने में help करता है। आप बिना complex code लिखे UI elements के साथ interact कर सकते हैं और API calls भी test कर सकते हैं।

## ✨ Features

हमारा framework कई powerful features offer करता है:

- **Page Object Model (POM)** - यह design pattern code को organized और maintainable रखता है। हर web page का एक अलग class होता है जो उस page के elements और actions को encapsulate करता है।

- **Data-driven Testing** - आप Excel files या TestNG DataProviders से test data read करके multiple test scenarios run कर सकते हैं, बिना code duplicate किए।

- **Parallel Test Execution** - Tests को parallel run करके execution time को significantly reduce करें।

- **Cross-browser Testing** - Tests को multiple browsers (Chrome, Firefox, Edge) पर run करके compatibility ensure करें।

- **REST API Testing** - REST Assured library का use करके API testing capabilities।

- **File Upload and Download Testing** - Files को upload और download करने की functionality test करें।

- **Extensive Reporting** - ExtentReports का use करके detailed और visually appealing test reports generate करें।

- **Screenshot Capture** - Test failure पर automatically screenshots capture करके debugging में help करें।

- **Logging** - Log4j2 का use करके detailed logging information capture करें।

- **Configuration Management** - Properties files के through environment-specific configurations manage करें।

- **Custom TestNG Listeners** - Enhanced reporting और test management के लिए custom TestNG listeners।

## 🏗️ Framework Structure

```
SeleniumAutomationFramework/
├── src/
│   ├── main/java/com/qasmarts/framework/
│   │   ├── config/            - Configuration management classes
│   │   │   ├── ConfigManager.java - Properties file reading and configuration
│   │   │   └── DriverManager.java - WebDriver initialization and management
│   │   ├── constants/         - Framework constants
│   │   │   └── Constants.java - Common constants used across framework
│   │   ├── exceptions/        - Custom exceptions
│   │   │   └── FrameworkException.java - Framework-specific exceptions
│   │   ├── listeners/         - TestNG listeners
│   │   │   ├── TestListener.java - Handles test events like pass/fail
│   │   │   └── ExtentReportListener.java - Creates extended reports
│   │   ├── pages/             - Page objects for each page in the application
│   │   │   ├── BasePage.java - Parent class with common methods
│   │   │   ├── LoginPage.java - Login page functionality
│   │   │   ├── HomePage.java - Home page functionality
│   │   │   ├── CheckboxPage.java - Checkbox page functionality
│   │   │   ├── DropdownPage.java - Dropdown functionality
│   │   │   ├── DynamicLoadingPage.java - Dynamic content loading
│   │   │   ├── JavaScriptAlertsPage.java - JS alert handling
│   │   │   ├── FileUploadPage.java - File upload functionality
│   │   │   ├── FileDownloadPage.java - File download functionality
│   │   │   ├── IFramePage.java - iFrame handling
│   │   │   ├── DragAndDropPage.java - Drag and drop functionality
│   │   │   ├── WindowsPage.java - Multiple windows handling
│   │   │   └── TablePage.java - Data table interactions
│   │   └── utils/             - Utility classes
│   │       ├── ExcelUtils.java - Excel file operations
│   │       ├── ScreenshotUtils.java - Screenshot capture
│   │       ├── WaitUtils.java - Explicit and fluent waits
│   │       ├── ApiUtils.java - REST API operations
│   │       └── JavaScriptUtils.java - JavaScript operations
│   ├── test/java/com/qasmarts/tests/
│   │   ├── BaseTest.java      - Parent test class with common setup
│   │   ├── LoginTest.java     - Login functionality tests
│   │   ├── HomePageTest.java  - Homepage tests
│   │   ├── CheckboxTest.java  - Checkbox functionality tests
│   │   ├── DropdownTest.java  - Dropdown functionality tests
│   │   ├── DynamicLoadingTest.java - Loading tests
│   │   ├── JavaScriptAlertsTest.java - Alert tests
│   │   ├── FileUploadTest.java - Upload tests
│   │   ├── FileDownloadTest.java - Download tests
│   │   ├── DataDrivenLoginTest.java - Data-driven tests
│   │   ├── IFrameTest.java - iFrame tests
│   │   ├── DragAndDropTest.java - Drag and drop tests
│   │   ├── WindowHandlingTest.java - Window handling tests
│   │   ├── TableTest.java - Table data tests
│   │   └── RestApiTest.java - API tests
│   └── test/resources/
│       ├── config/            - Configuration files
│       │   ├── config.properties - Framework configuration
│       │   └── log4j2.properties - Logging configuration
│       └── testdata/          - Test data files
│           └── loginData.xlsx - Test data for login tests
├── testng.xml                 - TestNG configuration
├── pom.xml                    - Maven dependencies
├── GIT_INSTRUCTIONS.md        - Git instructions in Hinglish
└── README.md                  - Project documentation
```

## 🧪 Test Cases

हमारा framework विभिन्न प्रकार के test cases को support करता है:

### 1. UI Tests

UI tests web application के user interface को validate करते हैं:

- **Login Tests (`LoginTest`)**: 
  - यह verify करता है कि users valid credentials के साथ login कर सकते हैं
  - Invalid credentials के case में error messages display होते हैं

- **Home Page Tests (`HomePageTest`)**:
  - Page elements properly display होते हैं
  - Navigation links काम करते हैं

- **Form Control Tests**:
  - Checkboxes select/deselect हो सकते हैं (`CheckboxTest`)
  - Dropdowns में options select हो सकते हैं (`DropdownTest`)

- **Dynamic Content Tests (`DynamicLoadingTest`)**:
  - Dynamic content properly load होता है
  - Loading indicators correctly display होते हैं

- **JavaScript Alert Tests (`JavaScriptAlertsTest`)**:
  - Alerts, confirms और prompts handle किए जा सकते हैं
  - User interactions correctly capture होते हैं

- **File Operation Tests**:
  - Files upload हो सकते हैं (`FileUploadTest`)
  - Files download हो सकते हैं (`FileDownloadTest`)

- **Advanced UI Interactions**:
  - iFrames access किए जा सकते हैं (`IFrameTest`)
  - Elements drag और drop किए जा सकते हैं (`DragAndDropTest`)
  - Multiple windows handle किए जा सकते हैं (`WindowHandlingTest`)
  - Table data access और validate किया जा सकता है (`TableTest`)

### 2. API Tests

API tests backend services को validate करते हैं:

- **GET Requests**:
  - API endpoints से data retrieve कर सकते हैं
  - Response codes और data structure validate कर सकते हैं

- **POST Requests**:
  - Data create कर सकते हैं
  - Valid और invalid payloads के behavior test कर सकते हैं

- **PUT/PATCH Requests**:
  - Existing data update कर सकते हैं
  - Conditional updates validate कर सकते हैं

- **DELETE Requests**:
  - Resources delete कर सकते हैं
  - Post-deletion state validate कर सकते हैं

- **Authentication Tests**:
  - Auth mechanisms (Basic, Token, OAuth) verify कर सकते हैं
  - Secured endpoints accessible हैं auth के साथ

### 3. Data-Driven Tests

Same test को different inputs के साथ run करते हैं:

- **Login with Multiple Credentials** (`DataDrivenLoginTest`):
  - Excel file से login credentials read करके various login scenarios test करते हैं
  - TestNG DataProvider का use करके multiple data sets के साथ tests run करते हैं

## 🛠️ Setup and Execution

### Prerequisites

शुरू करने से पहले, आपको निम्नलिखित tools install करने होंगे:

- **Java JDK 11 या higher**
  - Download: [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) या [OpenJDK](https://adoptopenjdk.net/)
  - Installation verify करें: `java -version`

- **Maven 3.6.3 या higher**
  - Download: [Maven](https://maven.apache.org/download.cgi)
  - Installation verify करें: `mvn -version`

- **Web Browsers**
  - [Google Chrome](https://www.google.com/chrome/)
  - [Mozilla Firefox](https://www.mozilla.org/firefox/)
  - [Microsoft Edge](https://www.microsoft.com/edge)

- **Integrated Development Environment (IDE)**
  - [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (Recommended)
  - [Eclipse](https://www.eclipse.org/downloads/)

### Installation

Step-by-step instructions for setting up the framework:

1. **Repository clone करें**:
   ```bash
   git clone https://github.com/yourusername/selenium-automation-framework.git
   ```

2. **Project directory में navigate करें**:
   ```bash
   cd selenium-automation-framework
   ```

3. **Dependencies install करें**:
   ```bash
   mvn clean install -DskipTests
   ```

### Configuration

**config.properties** file में basic configuration settings update करें:

```properties
# src/test/resources/config/config.properties
base.url=https://the-internet.herokuapp.com
default.browser=chrome
implicit.wait=10
explicit.wait=20
screenshot.path=test-output/screenshots
headless.mode=false
```

### Running Tests

Different ways to run tests:

**सभी tests run करें**:
```bash
mvn clean test
```

**Specific test group run करें**:
```bash
mvn clean test -Dgroups=smoke
```

**Specific browser के साथ run करें**:
```bash
mvn clean test -Dbrowser=chrome
```

**Specific TestNG suite run करें**:
```bash
mvn clean test -DsuiteXmlFile=smoke_test.xml
```

**IDE से TestNG file run करें**:
1. testng.xml file पर right click करें
2. Run As > TestNG Suite select करें

## 📊 Reporting

Test execution के बाद, reports निम्न locations पर available होते हैं:

- **TestNG Reports**: 
  - Location: `target/surefire-reports`
  - Format: HTML और XML reports
  - Access: `target/surefire-reports/index.html` open करें web browser में

- **Extent Reports**:
  - Location: `test-output/ExtentReports`
  - Format: Interactive HTML report with charts और graphs
  - Features: Pass/fail statistics, execution time, screenshots
  - Access: `test-output/ExtentReports/ExtentReport.html` open करें web browser में

- **Screenshots**:
  - Location: `test-output/screenshots`
  - Capture timing: Test failures पर automatically
  - Naming convention: `TestName_timestamp.png`

- **Logs**:
  - Location: `test-output/logs`
  - Format: Detailed execution logs
  - Level: INFO, ERROR, DEBUG

## 🔍 Extending the Framework

### Adding New Page Objects

New page के लिए Page Object add करने के steps:

1. `src/main/java/com/qasmarts/framework/pages` में new class create करें
2. `BasePage` class को extend करें
3. Page elements को `@FindBy` annotations के साथ define करें
4. Page-specific methods implement करें

Example:
```java
package com.qasmarts.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPage extends BasePage {
    
    @FindBy(id = "element-id")
    private WebElement pageElement;
    
    public NewPage(WebDriver driver) {
        super(driver);
    }
    
    public void openPage(String baseUrl) {
        driver.get(baseUrl + "/page-path");
        waitForPageToLoad();
    }
    
    public String getElementText() {
        return pageElement.getText();
    }
}
```

### Adding New Test Cases

New test cases add करने के steps:

1. `src/test/java/com/qasmarts/tests` में new test class create करें
2. `BaseTest` class को extend करें
3. Page objects initialize करें `@BeforeMethod` में
4. Test methods को `@Test` annotation के साथ add करें

Example:
```java
package com.qasmarts.tests;

import com.qasmarts.framework.pages.NewPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewPageTest extends BaseTest {
    
    private NewPage newPage;
    
    @BeforeMethod
    public void setUpPageObjects() {
        newPage = new NewPage(driver);
        newPage.openPage(getBaseUrl());
    }
    
    @Test(description = "Verify element text on the new page")
    public void testElementText() {
        String text = newPage.getElementText();
        Assert.assertEquals(text, "Expected Text", "Element text should match expected value");
    }
}
```

### Adding New API Tests

API tests add करने के steps:

1. `ApiUtils` class का use करें requests send करने के लिए
2. API responses को validate करने के लिए assertions add करें

Example:
```java
@Test(description = "Verify API endpoint returns correct data")
public void testApiEndpoint() {
    // Send API request
    Response response = apiUtils.sendGetRequest("/api/endpoint");
    
    // Validate response
    Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    Assert.assertEquals(response.jsonPath().getString("name"), "Expected Name",
            "Response should contain the correct name");
}
```

## 🔧 Troubleshooting

Common issues और उनके solutions:

### WebDriver Initialization Failures

**Issue**: Tests fails with error "Session not created" या "Driver not found"

**Solutions**:
- Browser version और WebDriver version compatible हैं verify करें
- WebDriverManager latest version use कर रहा है check करें
- Internet connection active है verify करें
- Antivirus या firewall WebDriver को block तो नहीं कर रहा check करें

### Element Not Found Exceptions

**Issue**: Tests fail with "NoSuchElementException"

**Solutions**:
- Element locators correct हैं verify करें
- Explicit waits का use करें element visible होने का wait करने के लिए
- Element iframe के अंदर तो नहीं है check करें
- Page fully load हुआ है verify करें
- Dynamic IDs का use हो रहा है तो different locator strategy try करें

### Data-Driven Test Issues

**Issue**: Data-driven tests नहीं चल रहे या incorrect data read कर रहे हैं

**Solutions**:
- Excel file correct location पर है verify करें
- Excel file format supported है check करें (XLS या XLSX)
- DataProvider correct data type return कर रहा है verify करें
- File permissions correct हैं check करें

### Test Execution Hangs

**Issue**: Tests execute होने के बाद hang हो जाते हैं

**Solutions**:
- Resource leaks (unclosed browsers) को fix करें
- Infinite loops या long waits को avoid करें
- Proper test cleanup implement करें
- Memory usage monitor करें large objects के liye

## 👥 Contributing

हमारे project में contribute करने के steps:

1. Repository fork करें
2. Feature branch create करें (`git checkout -b feature/amazing-feature`)
3. Changes commit करें (`git commit -m 'Add some amazing feature'`)
4. Branch push करें (`git push origin feature/amazing-feature`)
5. Pull Request open करें

Contribution guidelines:
- Code style guidelines follow करें
- Unit tests add करें new features के लिए
- Documentation update करें
- Pull request submit करने से पहले testing करें

## 👨‍💻 Author

**Abhinav Gupta**

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details. 