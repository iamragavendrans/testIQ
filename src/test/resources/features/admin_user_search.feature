@admin @search
Feature: Admin user search
  As an Admin
  I want to search for system users
  So that I can manage their access

  Background:
    Given I am logged in as the default admin user
    When I open the "Admin" module from the sidebar

  @smoke
  Scenario: Search returns the Admin user
    When I search system users by username "Admin"
    Then at least 1 result row should be shown

  Scenario: Search with non-existent username shows no results
    When I search system users by username "nonexistent_user_xyz"
    Then a "No Records Found" message should be displayed
