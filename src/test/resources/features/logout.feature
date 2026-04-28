@logout
Feature: Logout
  As a logged-in user
  I want to log out of the application
  So that my session is terminated

  @smoke
  Scenario: Logout returns user to the login page
    Given I am logged in as the default admin user
    When I logout
    Then I should be on the login page
