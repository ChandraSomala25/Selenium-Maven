package myPackage.tests;

import myPackage.pages.LoginPage;
import myPackage.utils.WebDriverUtil;
import myPackage.utils.ExtentReporterUtil;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.ITestResult;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        // Initialize WebDriver and open the login page
        driver = WebDriverUtil.initializeDriver("chrome");
        driver.get("https://www.automationexercise.com/login");
        loginPage = new LoginPage(driver);

        // Initialize the ExtentReports
        ExtentReporterUtil.initializeReporter(); // This will generate the report with a timestamp
        ExtentReporterUtil.startTest("Login Test", "Test the login functionality of the application.");
    }

    @Test
    public void testInvalidLogin() {
        // Perform the invalid login test
        loginPage.enterEmail("wronguser@example.com");
        loginPage.enterPassword("wrongpassword123");
        loginPage.clickLogin();

        // Log the result of the test
        String errorMessage = loginPage.getErrorMessage();
        if (errorMessage != null) {
            ExtentReporterUtil.logFail("Login failed. Error message: " + errorMessage);
        } else {
            ExtentReporterUtil.logPass("Login was successful.");
        }
    }

    /*@Test
    public void testValidLogin() {
        // Perform the valid login test
        loginPage.enterEmail("validuser@example.com");
        loginPage.enterPassword("validpassword123");
        loginPage.clickLogin();

        // Log the result of the test
        String successMessage = loginPage.getSuccessMessage();
        if (successMessage != null) {
            ExtentReporterUtil.logPass("Login successful. Welcome message: " + successMessage);
        } else {
            ExtentReporterUtil.logFail("Login failed.");
        }
    }*/

    @AfterMethod
    public void captureScreenshotForAllTests(ITestResult result) throws InterruptedException {
    	
    	// Add a 5-second wait before capturing the screenshot
        try {
            Thread.sleep(2000);  // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Capture screenshot for both passed and failed tests
        String screenshotPath = ExtentReporterUtil.captureScreenshot(driver, result.getName());
        File screenshotFile = new File(screenshotPath);
        
        // Check if the file exists and is not empty before attaching it
        if (screenshotFile.exists() && screenshotFile.length() > 0) {
            ExtentReporterUtil.attachScreenshot(screenshotPath);
        } else {
            ExtentReporterUtil.logFail("Screenshot was not properly saved.");
        }

        // Log the test result
        if (ITestResult.FAILURE == result.getStatus()) {
            ExtentReporterUtil.logFail("Test failed: " + result.getName());
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            ExtentReporterUtil.logPass("Test passed: " + result.getName());
        }
    }

    @AfterClass
    public void teardown() {
        // Quit the WebDriver
        WebDriverUtil.quitDriver(driver);

        // End the test and generate the report
        ExtentReporterUtil.endTest();
    }
}



