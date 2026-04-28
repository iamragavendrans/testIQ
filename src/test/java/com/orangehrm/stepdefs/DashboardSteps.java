package com.orangehrm.stepdefs;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.utils.WaitUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class DashboardSteps {

    private final DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());

    @When("I open the {string} module from the sidebar")
    public void iOpenModule(String module) {
        dashboardPage.openModule(module);
    }

    @When("I logout")
    public void iLogout() {
        dashboardPage.logout();
    }

    @Then("the page header should be {string}")
    public void pageHeaderShouldBe(String expected) {
        By header = By.xpath("//h6[normalize-space()='" + expected + "']");
        WaitUtils.waitForVisible(DriverFactory.getDriver(), header);
        String actual = DriverFactory.getDriver().findElement(header).getText().trim();
        Assert.assertEquals(expected, actual);
    }
}
