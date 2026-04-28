@login
Feature: OrangeHRM Login
  As a user of OrangeHRM
  I want to authenticate against the system
  So that I can access modules based on my permissions

  Background:
    Given I am on the OrangeHRM login page

  @smoke @positive
  Scenario: Login with valid Admin credentials
    When I login with username "Admin" and password "admin123"
    Then I should be redirected to the dashboard
    And the dashboard header should be "Dashboard"

  @negative
  Scenario Outline: Login fails with invalid credentials
    When I login with username "<username>" and password "<password>"
    Then I should see a login error containing "Invalid credentials"

    Examples:
      | username | password    |
      | Admin    | wrongpass   |
      | unknown  | admin123    |
      | hacker   | hacker      |

  @negative
  Scenario: Login fails when fields are empty
    When I click the login button without entering credentials
    Then I should remain on the login page
