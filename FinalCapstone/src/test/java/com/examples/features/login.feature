
Feature: Login and Password Validation

#  Scenario: Login checking
#    Given I am on the home page bestbuy.com
#    When I click on the Account button
#    Then I should see a side panel with account options
#    When I click on the Create Account button
#    Then I should be redirected to the login page
#    And I can enter all of my account information

#  Scenario Outline: Password and Email validation
#    Given I am on the login page
#    When I enter my "<Password>" into the password field
#    Then it should report the password as "<PasswordValid>"
#    Examples:
#      | Password  | PasswordValid |
#      | qeaTeam1!  | true  |
#      | ABCDEFGh!  | true  |
#      | 123456    | false |
#
#  Scenario Outline:
#    Given I am on the login page
#    And I enter the email address "<Email>"
#    Then it should report the email as "<EmailValid>"
#    Examples:
#      | Email | EmailValid |
#      | joe@gmail.com | true |
#      | a@b.cd        | true |
#      | 123456        | false |

#  Scenario Outline: Password validation
#    When I enter my <Password>, it should check if <Valid>
#    Examples:
#      | Password      | Valid  |
#      |"qeaTeam1"     |"true"  |
#      |"password1"    |"true"  |
#      |"ABCDEFGh"     |"true"  |
#      |"-1-1-1-1-1-1" |"true"  |
#      |"abcdefghijklm"|"false" |
#
#  Scenario: Cleanup
#    Then I close the browser