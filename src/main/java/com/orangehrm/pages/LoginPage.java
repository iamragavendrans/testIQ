package com.orangehrm.pages;

import com.orangehrm.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.name("username");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By ERROR_ALERT = By.cssSelector(".oxd-alert-content-text");
    private static final By LOGIN_PANEL_HEADER = By.cssSelector(".orangehrm-login-title");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(ConfigReader.get("base.url"));
        return this;
    }

    public boolean isAtLoginPage() {
        return isDisplayed(LOGIN_PANEL_HEADER);
    }

    public void enterUsername(String username) {
        type(USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        type(PASSWORD_INPUT, password);
    }

    public void clickLogin() {
        click(LOGIN_BUTTON);
    }

    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return readText(ERROR_ALERT);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(ERROR_ALERT);
    }
}
