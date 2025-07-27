Feature: Ebay Assignment

  @Assignment
  Scenario Outline: Verify Item added to Cart
    Given User navigates to Ebay Url
    When User searches for "<SearchItem>"
    And User clicks on the desired product according to position "<Position>"
    Then User adds the product to the cart and verifies the cart count is updated



    Examples:
      | SearchItem | Position |
      | book     |  1       |

