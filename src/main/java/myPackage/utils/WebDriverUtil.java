package myPackage.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.IOException;

public class WebDriverUtil {

    // Method to initialize WebDriver
    public static WebDriver initializeDriver(String browser) {
        WebDriver driver = null;
        
        // Set the driver path (you can use WebDriverManager to handle this automatically)
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            System.out.println("Unsupported browser: " + browser);
        }
        
        // Maximize window and set implicit wait globally
        if (driver != null) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        }
        
        return driver;
    }

    // Method to quit WebDriver
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}

