Feature: Order

  Scenario: Create and get Order
    Given the client creates new order
    And the registered customer is correct
    When the client fetches the order
    Then the fetched customer is correct