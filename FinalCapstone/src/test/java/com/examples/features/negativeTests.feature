
Feature: Login and Password Validation

  Scenario Outline: Password and Email validation
    Given I am on the login page
    When I enter my "<Password>" into the password field
    Then it should report the password as "<PasswordValid>"
    Examples:
      | Password  | PasswordValid |
      | qeaTeam1!  | true  |
      | ABCDEFGh!  | true  |
      | 123456    | false |

  Scenario Outline:
    Given I am on the login page
    And I enter the email address "<Email>"
    Then it should report the email as "<EmailValid>"
    Examples:
      | Email | EmailValid |
      | joe@gmail.com | true |
      | a@b.cd        | true |
      | 123456        | false |