package myPackage.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import myPackage.utils.LoggerUtil;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By emailField = By.xpath("//input[@data-qa='login-email']");
    private By passwordField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By errorMessage = By.xpath("//*[contains(text(), 'Your email or password is incorrect!')]");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailField);
        emailElement.sendKeys(email);
        LoggerUtil.info("Entered email: " + email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
        LoggerUtil.info("Entered password.");
    }

    public void clickLogin() {
        WebElement loginBtn = driver.findElement(loginButton);
        loginBtn.click();
        LoggerUtil.info("Clicked on login button.");
    }

    // Check if error message is displayed after login attempt
    public String getErrorMessage() {
        try {
            WebElement errorMsgElement = driver.findElement(errorMessage);
            if (errorMsgElement.isDisplayed()) {
                LoggerUtil.info("Error message found: " + errorMsgElement.getText());
                return errorMsgElement.getText();
            }
        } catch (NoSuchElementException e) {
            // Error message not found
            LoggerUtil.info("No error message displayed.");
        }
        return null;
    }
}



