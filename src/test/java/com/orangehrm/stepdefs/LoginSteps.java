package com.orangehrm.stepdefs;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private final DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());

    @Given("I am on the OrangeHRM login page")
    public void iAmOnLoginPage() {
        loginPage.open();
        Assert.assertTrue("Login page did not load", loginPage.isAtLoginPage());
    }

    @Given("I am logged in as the default admin user")
    public void iAmLoggedInAsAdmin() {
        loginPage.open();
        loginPage.loginAs(
                ConfigReader.get("default.username"),
                ConfigReader.get("default.password"));
        Assert.assertTrue("Dashboard did not load after default login", dashboardPage.isLoaded());
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWith(String username, String password) {
        loginPage.loginAs(username, password);
    }

    @When("I click the login button without entering credentials")
    public void iClickLoginWithoutCreds() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the dashboard")
    public void iShouldBeOnDashboard() {
        Assert.assertTrue("Dashboard not loaded", dashboardPage.isLoaded());
    }

    @Then("the dashboard header should be {string}")
    public void dashboardHeaderShouldBe(String expected) {
        Assert.assertEquals(expected, dashboardPage.getHeaderTitle());
    }

    @Then("I should see a login error containing {string}")
    public void iShouldSeeLoginError(String expected) {
        Assert.assertTrue("Error alert not displayed", loginPage.isErrorDisplayed());
        Assert.assertTrue(
                "Expected error to contain: " + expected + " but was: " + loginPage.getErrorMessage(),
                loginPage.getErrorMessage().toLowerCase().contains(expected.toLowerCase()));
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnLoginPage() {
        Assert.assertTrue("User no longer on login page", loginPage.isAtLoginPage());
    }

    @Then("I should be on the login page")
    public void iShouldBeOnLoginPage() {
        Assert.assertTrue("User not on login page", loginPage.isAtLoginPage());
    }
}
