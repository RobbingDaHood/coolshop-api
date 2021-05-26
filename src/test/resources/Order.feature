Feature: Order

  Scenario: Create and get Order
    Given the client creates new customer with name "Jørgen Petersen"
    And the client creates new order
    And the registered order is correct
    When the client fetches the order
    Then the fetched order is correct

  Scenario: Get all orders connected to one customer
    Given the client creates new customer with name "Jørgen Petersen"
    And the client fetches all orders belonging to the Customer
    When the client creates new order
    And the client fetches all orders belonging to the Customer
    Then the client have one more order