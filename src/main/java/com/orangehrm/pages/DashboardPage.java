package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private static final By HEADER_TITLE = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private static final By USER_DROPDOWN = By.cssSelector(".oxd-userdropdown-tab");
    private static final By LOGOUT_LINK = By.xpath("//a[normalize-space()='Logout']");
    private static final By SIDEBAR_SEARCH = By.cssSelector("input.oxd-input[placeholder='Search']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        WaitUtils.waitForUrlContains(driver, "/dashboard");
        return isDisplayed(HEADER_TITLE);
    }

    public String getHeaderTitle() {
        return readText(HEADER_TITLE);
    }

    public void searchSidebar(String moduleName) {
        type(SIDEBAR_SEARCH, moduleName);
    }

    public void openModule(String moduleName) {
        By moduleLink = By.xpath("//aside//span[normalize-space()='" + moduleName + "']");
        click(moduleLink);
    }

    public void logout() {
        click(USER_DROPDOWN);
        click(LOGOUT_LINK);
    }
}
