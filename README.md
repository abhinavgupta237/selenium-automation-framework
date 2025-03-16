# Automated Testing Framework for Web Applications

A robust and scalable test automation framework built with Selenium WebDriver, TestNG, and Maven.

<p align="center">
  <img src="https://img.shields.io/badge/Selenium-4.15.0-green" alt="Selenium Version"/>
  <img src="https://img.shields.io/badge/Java-11-orange" alt="Java Version"/>
  <img src="https://img.shields.io/badge/TestNG-7.8.0-blue" alt="TestNG Version"/>
  <img src="https://img.shields.io/badge/Maven-3.11.0-red" alt="Maven Version"/>
</p>

## Overview

This framework is designed to provide a solid foundation for web application testing with the following features:

- **Page Object Model (POM) Design Pattern**: For better maintainability and reusability of code
- **Cross-Browser Testing**: Run tests on Chrome, Firefox, Edge, and Safari
- **Parallel Execution**: Run tests in parallel to reduce execution time
- **Reporting**: Generate detailed HTML reports with screenshots for failed tests
- **Logging**: Comprehensive logging using Log4j2
- **Data-Driven Testing**: Support for test data from Excel files
- **CI/CD Integration**: Ready to integrate with CI/CD pipelines

## Framework Architecture

<p align="center">
  <pre>
  +-------------------+      +-------------------+      +-------------------+
  |                   |      |                   |      |                   |
  |    Test Classes   +----->+   Page Objects    +----->+   WebDriver API   |
  |                   |      |                   |      |                   |
  +--------+----------+      +-------------------+      +-------------------+
           |
           |
  +--------v----------+      +-------------------+      +-------------------+
  |                   |      |                   |      |                   |
  |  TestNG Listeners +----->+  Extent Reports   +----->+   Test Reports    |
  |                   |      |                   |      |                   |
  +-------------------+      +-------------------+      +-------------------+
  </pre>
</p>

## Tech Stack

- **Java**: Programming language
- **Selenium WebDriver**: For browser automation
- **TestNG**: Test framework for test organization and parallel execution
- **Maven**: Build and dependency management
- **WebDriverManager**: Automated driver management
- **ExtentReports**: HTML reporting
- **Log4j2**: Logging framework
- **Apache POI**: For Excel operations

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── qasmarts
│   │   │           └── framework
│   │   │               ├── BasePage.java
│   │   │               ├── WebDriverFactory.java
│   │   │               ├── config
│   │   │               │   └── ConfigManager.java
│   │   │               ├── listeners
│   │   │               │   ├── TestListener.java
│   │   │               │   └── ExtentReportListener.java
│   │   │               ├── pages
│   │   │               │   ├── LoginPage.java
│   │   │               │   └── HomePage.java
│   │   │               └── utils
│   │   │                   ├── ExcelUtils.java
│   │   │                   └── ScreenshotUtils.java
│   │   └── resources
│   │       ├── log4j2.xml
│   │       └── config.properties
│   └── test
│       ├── java
│       │   └── com
│       │       └── qasmarts
│       │           └── tests
│       │               ├── BaseTest.java
│       │               ├── LoginTest.java
│       │               └── HomePageTest.java
│       └── resources
│           └── testdata
│               └── testdata.xlsx
├── testng.xml
├── pom.xml
└── README.md
```

## Features

### Page Object Model

The framework uses the Page Object Model design pattern to create a clean and maintainable test structure. Each page in the application has a corresponding Page class that encapsulates page elements and actions.

**Example Page Object:**

```java
public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameInput;
    
    @FindBy(id = "password")
    private WebElement passwordInput;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }
}
```

### Cross-Browser Testing

Tests can be executed on different browsers by configuring the `browser` parameter in the TestNG XML file:

```xml
<test name="Chrome Tests">
    <parameter name="browser" value="chrome"/>
    <classes>
        <class name="com.qasmarts.tests.LoginTest"/>
    </classes>
</test>
```

### Parallel Execution

TestNG is configured to run tests in parallel to reduce execution time:

```xml
<suite name="Selenium Automation Framework Test Suite" parallel="tests" thread-count="3">
```

### Reporting

The framework generates detailed HTML reports using ExtentReports. Screenshots are automatically captured for failed tests.

Example report screenshot:
```
+-----------------------------------------------+
|  Test Report - Selenium Automation Framework  |
+-----------------------------------------------+
| Test Status  | Test Name         | Duration   |
+-----------------------------------------------+
| ✅ PASS      | testSuccessfulLogin | 3.45s    |
| ❌ FAIL      | testInvalidUsername | 2.89s    |
| ✅ PASS      | testForgotPassword  | 1.23s    |
+-----------------------------------------------+
```

### Logging

Comprehensive logging is implemented using Log4j2 to track test execution and debug issues:

```
2023-06-15 10:15:32 [main] INFO  com.qasmarts.tests.LoginTest - Starting test: testSuccessfulLogin
2023-06-15 10:15:33 [main] INFO  com.qasmarts.framework.BasePage - Navigating to URL: https://example.com/login
2023-06-15 10:15:34 [main] INFO  com.qasmarts.framework.pages.LoginPage - Entering username: testuser
2023-06-15 10:15:34 [main] INFO  com.qasmarts.framework.pages.LoginPage - Entering password: ****
2023-06-15 10:15:35 [main] INFO  com.qasmarts.framework.pages.LoginPage - Clicking login button
2023-06-15 10:15:36 [main] INFO  com.qasmarts.tests.LoginTest - Test passed: testSuccessfulLogin
```

### Data-Driven Testing

The framework supports data-driven testing using TestNG DataProvider and Apache POI for Excel data:

```java
@DataProvider(name = "loginData")
public Object[][] getLoginData() {
    return ExcelUtils.getTestData("testdata.xlsx", "LoginData");
}

@Test(dataProvider = "loginData")
public void testLoginWithMultipleUsers(String username, String password, String expectedResult) {
    // Test implementation
}
```

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Browsers: Chrome, Firefox, Edge (as per your testing needs)

### Installation

1. Clone the repository
   ```
   git clone https://github.com/yourusername/selenium-automation-framework.git
   ```

2. Navigate to the project directory
   ```
   cd selenium-automation-framework
   ```

3. Build the project:
   ```
   mvn clean install -DskipTests
   ```

### Running Tests

To run the tests:

```
mvn test
```

To run specific test classes:

```
mvn test -Dtest=LoginTest
```

To run with a specific TestNG XML file:

```
mvn test -DsuiteXmlFile=testng.xml
```

### Running Tests with Specific Browser

```
mvn test -Dbrowser=chrome
```

Available browser options: `chrome`, `firefox`, `edge`, `safari`

## Customization

### Adding New Page Objects

1. Create a new class in the `com.qasmarts.framework.pages` package
2. Extend the `BasePage` class
3. Implement page-specific elements and methods

Example:
```java
public class ProductPage extends BasePage {
    @FindBy(id = "product-title")
    private WebElement productTitle;
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    public String getProductTitle() {
        return getText(productTitle);
    }
}
```

### Adding New Tests

1. Create a new class in the `com.qasmarts.tests` package
2. Extend test base class or implement setup/teardown methods
3. Create test methods with TestNG annotations
4. Add the test class to the TestNG XML file

Example:
```java
public class ProductTest extends BaseTest {
    private ProductPage productPage;
    
    @BeforeMethod
    public void setUp() {
        super.setUp();
        productPage = new ProductPage(driver);
    }
    
    @Test
    public void testProductDetails() {
        productPage.navigate("https://example.com/product/1");
        Assert.assertEquals(productPage.getProductTitle(), "Expected Product Title");
    }
}
```

## CI/CD Integration

### GitHub Actions Integration

Create a `.github/workflows/maven.yml` file:

```yaml
name: Java CI with Maven

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 11
      uses: actions/setup-java@v2
      with:
        java-version: '11'
        distribution: 'adopt'
    - name: Build with Maven
      run: mvn clean test
```

### Jenkins Integration

Create a `Jenkinsfile`:

```
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }
}
```

## Best Practices

1. **Use Page Object Model**: Keep all web element locators and page-specific methods in respective page classes
2. **Implement Proper Waits**: Avoid using Thread.sleep(), use Explicit Waits instead
3. **Handle Exceptions Properly**: Implement try-catch blocks where necessary
4. **Add Descriptive Assertions**: Use descriptive messages in assertions to make test failures more informative
5. **Maintain Test Independence**: Each test should be independent and not rely on other tests
6. **Use Test Categories**: Group tests using TestNG groups for selective execution
7. **Keep Tests Small and Focused**: Each test should verify a single functionality
8. **Implement Screenshots for Failures**: Capture screenshots when tests fail for easier debugging
9. **Use Proper Logging**: Log important steps and information during test execution
10. **Maintain Clean Code**: Follow code formatting and style guidelines

## Troubleshooting

### Common Issues

1. **WebDriver Initialization Failure**
   - Verify the browser driver compatibility with the browser version
   - Check if WebDriverManager is properly configured

2. **Element Not Found Exceptions**
   - Check if the element locator is correct
   - Implement proper waits before interacting with elements
   - Verify if element is in an iframe or shadow DOM

3. **Test Data Issues**
   - Ensure test data files are in the correct location
   - Verify data format and content

### Logging and Debugging

To enable verbose logging, modify the log level in `log4j2.xml`:

```xml
<Root level="debug">
    <AppenderRef ref="Console" />
    <AppenderRef ref="File" />
</Root>
```

## Author

**Abhinav Gupta**  
- Email: qasmarts@gmail.com
- LinkedIn: [Abhinav Gupta](https://www.linkedin.com/in/your-profile)
- GitHub: [YourGithubProfile](https://github.com/yourgithubprofile)

## License

This project is licensed under the MIT License. 