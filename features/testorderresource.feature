Feature: TestOrderResource
  ...

  Scenario: Request a prioritized list of tests using method 1
    Given I am about to run my tests
    When I GET a test order from "/testorder/1"
    Then it should return status "200" OK
    And the response should be a list of test classes to run as "application/json"
