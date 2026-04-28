package com.orangehrm.stepdefs;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.AdminPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AdminSteps {

    private final AdminPage adminPage = new AdminPage(DriverFactory.getDriver());

    @When("I search system users by username {string}")
    public void iSearchByUsername(String username) {
        Assert.assertTrue("Admin page not loaded", adminPage.isLoaded());
        adminPage.searchByUsername(username);
    }

    @Then("at least {int} result row should be shown")
    public void atLeastNRowsShown(int expectedMin) {
        int actual = adminPage.resultRowCount();
        Assert.assertTrue(
                "Expected at least " + expectedMin + " rows but found " + actual,
                actual >= expectedMin);
    }

    @Then("a {string} message should be displayed")
    public void messageShouldBeDisplayed(String message) {
        if ("No Records Found".equalsIgnoreCase(message)) {
            Assert.assertTrue("Expected 'No Records Found' message", adminPage.noRecordsShown());
        } else {
            throw new IllegalArgumentException("Unsupported message assertion: " + message);
        }
    }
}
