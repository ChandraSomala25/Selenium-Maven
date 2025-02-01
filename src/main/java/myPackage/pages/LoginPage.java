package myPackage.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Locators for Login
    private By loginButton = By.xpath("//a[@href='/account/login']");
    private By usernameField = By.xpath("//input[@class='_2IX_2- VJZDxU']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By submitButton = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(loginButton).click();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }
}

