package myPackage.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;

public class ExtentReporterUtil {
    private static ExtentReports extent;
    private static ExtentTest test;

    // Initialize the ExtentReports with a dynamic file name (timestamped report)
    public static void initializeReporter() {
        // Generate dynamic timestamp for unique report names
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = "reports/extentReport_" + timestamp + ".html";
        
        // Create the report file and configure the reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setDocumentTitle("Test Execution Report");

        // Initialize the ExtentReports instance and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    // Start a new test in the report
    public static void startTest(String testName, String description) {
        test = extent.createTest(testName, description);
    }

    // Log info messages to the report
    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    // Log success messages to the report
    public static void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
    }

    // Log failure messages to the report
    public static void logFail(String message) {
        if (test != null) {
            test.fail(message);
        }
    }

 // Capture and attach screenshot to the report with date-time in the filename
    public static String captureScreenshot(WebDriver driver, String screenshotName) throws InterruptedException {
        
    	// Dynamically get project root directory
        String projectPath = System.getProperty("user.dir");
        
    	// Define the path for screenshots directory
        String screenshotDir = projectPath + "/reports/screenshots/";

        // Check if the directory exists, if not, create it
        Path path = Paths.get(screenshotDir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path); // Create the directory if it doesn't exist
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Generate timestamp to add to the screenshot name
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        // Append the timestamp to the screenshot name
        String screenshotFileName = screenshotName + "_" + timestamp + ".png";

        // Take the screenshot and save it to the file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = screenshotDir + screenshotFileName;

        try {
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath)); // Save the screenshot
            
         // Ensure the file is completely written before returning
            File savedScreenshot = new File(screenshotPath);
            int attempts = 0;
            while (!savedScreenshot.exists() || savedScreenshot.length() == 0) {
                Thread.sleep(500); // Small delay to ensure file is written
                attempts++;
                if (attempts > 5) { // Avoid infinite loop
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    // Attach screenshot to the test report
    public static void attachScreenshot(String screenshotPath) {
        if (test != null) {
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    // End the current test and flush the report
    public static void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }
}

