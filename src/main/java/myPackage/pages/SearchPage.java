package myPackage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
    private WebDriver driver;

    // Locator for the search box
    private By searchBox = By.name("q");
    private By searchButton = By.xpath("//button[@type='submit']");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String productName) {
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.sendKeys(productName);
        driver.findElement(searchButton).click();
    }
}
