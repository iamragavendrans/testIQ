package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage extends BasePage {

    private static final By PAGE_HEADER = By.xpath("//h6[normalize-space()='Admin']");
    private static final By USERNAME_FILTER = By.xpath(
            "//label[normalize-space()='Username']/../following-sibling::div//input");
    private static final By SEARCH_BUTTON = By.cssSelector("button[type='submit']");
    private static final By RESET_BUTTON = By.xpath("//button[normalize-space()='Reset']");
    private static final By RESULTS_COUNT = By.cssSelector(".oxd-text--span");
    private static final By RESULT_ROWS = By.cssSelector(".oxd-table-card");
    private static final By NO_RECORDS_MESSAGE = By.xpath("//span[normalize-space()='No Records Found']");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        WaitUtils.waitForUrlContains(driver, "/admin/viewSystemUsers");
        return isDisplayed(PAGE_HEADER);
    }

    public void searchByUsername(String username) {
        type(USERNAME_FILTER, username);
        click(SEARCH_BUTTON);
    }

    public int resultRowCount() {
        return driver.findElements(RESULT_ROWS).size();
    }

    public boolean noRecordsShown() {
        return isDisplayed(NO_RECORDS_MESSAGE);
    }

    public String getResultsSummary() {
        return readText(RESULTS_COUNT);
    }
}
