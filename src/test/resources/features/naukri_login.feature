Feature: Naukri login and recommended jobs

  Background:
    Given user opens the Naukri login page

  Scenario: Successful login redirects to recommended jobs
    When user logs in with valid credentials
    Then user should see recommended jobs page or dashboard

  Scenario: Invalid login shows error
    When user logs in with invalid credentials
    Then user should see an error message
