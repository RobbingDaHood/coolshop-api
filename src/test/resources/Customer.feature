Feature: Customer

  Scenario: Create and get Customer
    Given the client creates new customer with name "Jørgen Petersen"
    And the registered customer has the name "Jørgen Petersen"
    When the client fetches the Customer
    Then the fetched customer has the name "Jørgen Petersen"