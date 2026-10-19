package copilot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NaukriLoginPage {

    private WebDriver driver;

    private By usernameInput = By.id("usernameField");
    private By passwordInput = By.id("passwordField");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMsg = By.cssSelector(".error-msg");

    public NaukriLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        WebElement el = driver.findElement(usernameInput);
        el.clear();
        el.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement el = driver.findElement(passwordInput);
        el.clear();
        el.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isErrorDisplayed() {
        return !driver.findElements(errorMsg).isEmpty();
    }
}
