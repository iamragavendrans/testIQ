@dashboard
Feature: Dashboard Navigation
  As an authenticated Admin
  I want to navigate between modules from the dashboard
  So that I can access the application's core functionality

  Background:
    Given I am logged in as the default admin user

  @smoke
  Scenario: Default dashboard is shown after login
    Then I should be redirected to the dashboard
    And the dashboard header should be "Dashboard"

  Scenario Outline: Navigate to core modules from sidebar
    When I open the "<module>" module from the sidebar
    Then the page header should be "<header>"

    Examples:
      | module | header |
      | Admin  | Admin  |
      | PIM    | PIM    |
      | Leave  | Leave  |
