package myPackage.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.function.Function;

public class FluentWaitUtil {
    private WebDriver driver;
    private FluentWait<WebDriver> fluentWait;

    // Constructor to initialize Fluent Wait globally
    public FluentWaitUtil(WebDriver driver) {
        this.driver = driver;
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))  // Global max wait time
                .pollingEvery(Duration.ofSeconds(3))  // Polling interval
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    // Generic method to find an element with Fluent Wait
    public WebElement waitForElement(By locator) {
        return fluentWait.until(driver -> driver.findElement(locator));
    }
}
