package myPackage.tests;

import myPackage.pages.LoginPage;
import myPackage.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Shopping {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Selenium Manager automatically handles driver setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Start browser maximized
        driver = new ChromeDriver(options);

        // Set global implicit wait for 10 seconds
        //driver.manage().timeouts().pageLoadTimeout(20);
        driver.get("https://www.flipkart.com");
    }

    @Test
    public void testLoginAndSearch() {
        // Creating LoginPage object and performing login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("your-email@example.com", "your-password");

        // Creating SearchPage object and performing product search
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchProduct("Laptop");

        // Add any assertion here based on your requirements
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
