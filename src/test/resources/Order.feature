Feature: Order

  Scenario: Create and get Order
    Given the client creates new customer with name "Jørgen Petersen"
    And the client creates new order
    And the registered order is correct
    When the client fetches the order
    Then the fetched order is correct