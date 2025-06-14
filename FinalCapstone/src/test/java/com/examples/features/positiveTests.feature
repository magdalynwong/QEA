@Positive
Feature: Adding and removing laptop from cart

  Scenario: Searching for Macbook Pro
    Given I am on the Best Buy home page
    When I close the ad modal
    And I search for "macbook pro"
    Then I should be on the search results page for "macbook pro"

  Scenario: Filtering laptop specs
    Given I performed the search for "macbook pro"
    When I check the 12” - 13.9” filter under Screen Size
    And I click the On Sale filter
    Then a macbook pro with 13.3” 8GB Memory and 256GB SSD should appear

  Scenario: Adding Macbook Pro to cart
    Given I am on the product's listing page
    When I click the “Add to Cart” button next to the laptop
    Then I should see the item in my cart with the total price

  Scenario: Removing item from cart
    Given I am on the above page
    When I click the "Remove" link under the item number drop down
    Then I should receive the message “We’ve removed this item from your cart.”

  Scenario: Login checking
    Given I am on the home page bestbuy.com
    When I click on the Account button
    Then I should see a side panel with account options
    When I click on the Create Account button
    Then I should be redirected to the login page
    And I can enter all of my account information