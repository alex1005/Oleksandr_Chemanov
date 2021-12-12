Feature: OrangeHRM.com Work Shift Table Tests

  Scenario: Login test
    Given that I'm on Login page
    When I enter username "Admin"
    And I enter password "admin123"
    Then I get logged in

  Scenario: Add new Work Shift
    Given that I'm on Work Shifts page
    When I press add button
    And I enter shift name "Bernoulli"
    And enter time from "06:00" to "18:00"
    And assign employee "Alice Duval"
    And assign employee "Chenzira Chuki"
    And assign employee "Lisa Andrews"
    And press save button
    Then I see shift "Bernoulli" in the table

  Scenario: Delete a work shift record
    Given that I'm on Work Shifts page
    When I choose "Bernoulli" work shift
    And press delete button and submit deletion
    Then I see "Bernoulli" record deleted